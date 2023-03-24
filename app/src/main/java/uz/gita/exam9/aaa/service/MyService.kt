package uz.gita.exam9.aaa.service

import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.net.wifi.WifiManager
import android.os.Build
import android.os.IBinder
import android.util.Log
import androidx.core.app.NotificationCompat
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import uz.gita.exam9.R
import uz.gita.exam9.aaa.ConnectionReceiver
import uz.gita.exam9.aaa.WifiReceiver
import uz.gita.exam9.data.local.room.entites.BookEntity
import uz.gita.exam9.domain.usecase.impl.AuthorBookUseCaseImpl
import uz.gita.exam9.utils.isConnected
import javax.inject.Inject
import javax.inject.Singleton


@AndroidEntryPoint
@Singleton
class MyService : Service() {


    override fun onBind(intent: Intent?): IBinder? = null
    private var manager: NotificationManager? = null
    private val scope: CoroutineScope = CoroutineScope(Dispatchers.IO)
    private lateinit var booksList: List<BookEntity>

    @Inject
    lateinit var useCase: AuthorBookUseCaseImpl

    override fun onCreate() {
        super.onCreate()
        createChannel()

    }

    @SuppressLint("WrongConstant")
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        val wifiReceiver = WifiReceiver()
        this.registerReceiver(wifiReceiver, IntentFilter(WifiManager.WIFI_STATE_CHANGED_ACTION))
        wifiReceiver.setCheckInternetListener {
            if (it) {
                Log.d("TTT", "Service wifi $it")
                checkConnection()
            }
        }

        val mobileNetworkReceiver = ConnectionReceiver()
        this.registerReceiver(mobileNetworkReceiver, IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION))
        mobileNetworkReceiver.setCheckInternetListener {
            if (it) {
                Log.d("TTT", "Service mobile $it")
                checkConnection()
            }
        }
        return START_STICKY
    }


    private fun downloadBooks() {
        booksList = useCase.getAddBooks()
        useCase.getBookFromServer()
    }

    private fun checkConnection() {
        if (isConnected(this)) {
            booksList = useCase.getAddBooks()
            Log.d("TTT", "${booksList.size}")
            if (booksList.isEmpty()) return
            isConnected(this)
            for (i in booksList.indices) {
                val book = booksList[i]

                useCase.syncBook(book).onEach {
                    Log.d("TTT", "${book.title}")
                    it.onSuccess {
                        createNotification(book.title)
                        if (booksList.size == i + 1)
                            downloadBooks()
                    }
                }.launchIn(scope)
            }
        }
    }

    private fun createChannel() {
        if (Build.VERSION.SDK_INT >= 26) {
            val channel = NotificationChannel("123", "Example", NotificationManager.IMPORTANCE_DEFAULT)
            channel.setSound(null, null)
            manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            manager?.createNotificationChannel(channel)
        }
    }

    private fun createNotification(bookName: String) {
        val notification = NotificationCompat.Builder(this, "123")
            .setSmallIcon(R.drawable.book_image)
            .setContentTitle(bookName)
            .setContentText("Book saved in server")
            .setStyle(NotificationCompat.DecoratedCustomViewStyle())
            .build()

        startForeground(12, notification)
        startForeground(11, notification)
    }
}