package uz.gita.book_application.presentation.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import uz.gita.book_application.data.local.room.entites.BookEntity
import uz.gita.book_application.databinding.ItemAuthorBookBinding


class BookAdapter : ListAdapter<BookEntity, BookAdapter.ContactViewHolder>(ContactDiffUtil) {
    object ContactDiffUtil : DiffUtil.ItemCallback<BookEntity>() {
        override fun areItemsTheSame(oldItem: BookEntity, newItem: BookEntity): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: BookEntity,
            newItem: BookEntity
        ): Boolean {
            return oldItem == newItem
        }
    }

    private var updateListener: ((BookEntity) -> Unit)? = null
    fun setUpdateListener(block: (BookEntity) -> Unit) {
        updateListener = block
    }

    private var deleteListener: ((BookEntity) -> Unit)? = null
    fun setDeleteListener(block: (BookEntity) -> Unit) {
        deleteListener = block
    }


    inner class ContactViewHolder(private val binding: ItemAuthorBookBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind() {
            getItem(absoluteAdapterPosition).apply {
                binding.bookName.text = title
                binding.authorName.text = author
                binding.bookDescription.text = description
                binding.pageCount.text = pageCount.toString()
                binding.btnFavourite.visibility = View.GONE
                if (isSynchronized == 1) {
                    binding.synImage.visibility = View.GONE
                } else {
                    binding.synImage.visibility = View.VISIBLE
                }
                binding.btnEdit.setOnClickListener {
                    updateListener?.invoke(getItem(absoluteAdapterPosition))
                }
                binding.btnDelete.setOnClickListener {
                    deleteListener?.invoke(getItem(absoluteAdapterPosition))
                }

            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ContactViewHolder(
            ItemAuthorBookBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        holder.bind()
    }
}
