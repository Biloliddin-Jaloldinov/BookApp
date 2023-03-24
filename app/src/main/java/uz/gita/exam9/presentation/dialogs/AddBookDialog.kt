package uz.gita.exam9.presentation.dialogs

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import by.kirich1409.viewbindingdelegate.viewBinding
import uz.gita.exam9.utils.myApply
import uz.gita.exam9.R
import uz.gita.exam9.data.local.room.entites.BookEntity
import uz.gita.exam9.databinding.DialogAddBookBinding

class AddBookDialog : DialogFragment(R.layout.dialog_add_book) {
    private var addContactListener: ((BookEntity) -> Unit)? = null
    private val binding by viewBinding(DialogAddBookBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = binding.myApply {
        buttonAdd.setOnClickListener {
            addContactListener?.invoke(
                BookEntity(
                    title = binding.inputBookName.text.toString(),
                    author = binding.inputAuthor.text.toString(),
                    description = binding.inputDescription.text.toString(),
                    pageCount = binding.inputPageCount.text.toString().toIntOrNull() ?: 0,
                    fav = false
                )
            )
            dismiss()
        }
        buttonCancel.setOnClickListener {
            dismiss()
        }
    }

    fun setAddContactListener(block: (BookEntity) -> Unit) {
        addContactListener = block
    }

    override fun onResume() {
        super.onResume()
        val params = dialog!!.window!!.attributes
        params.width = ViewGroup.LayoutParams.MATCH_PARENT
        params.height = ViewGroup.LayoutParams.MATCH_PARENT
        dialog!!.window!!.attributes = params
    }

}


