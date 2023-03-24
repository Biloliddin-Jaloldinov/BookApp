package uz.gita.exam9.presentation.ui.screens

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber
import uz.gita.exam9.R
import uz.gita.exam9.aaa.service.MyService
import uz.gita.exam9.data.local.room.entites.BookEntity
import uz.gita.exam9.databinding.ScreenBooksListBinding
import uz.gita.exam9.presentation.adapters.BookAdapter
import uz.gita.exam9.presentation.dialogs.AddBookDialog
import uz.gita.exam9.presentation.dialogs.EditBookDialog
import uz.gita.exam9.presentation.viewmodel.AuthorBooksViewModel
import uz.gita.exam9.presentation.viewmodel.impl.AuthorBooksViewModelImpl
import uz.gita.exam9.utils.myApply


@AndroidEntryPoint
class AuthorBooksListScreen : Fragment(R.layout.screen_books_list) {
    private val binding by viewBinding(ScreenBooksListBinding::bind)
    private val viewModel: AuthorBooksViewModel by viewModels<AuthorBooksViewModelImpl>()
    private val adapter = BookAdapter()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = binding.myApply {
        Timber.tag("TTT").d("Main")

        requireActivity().startService(Intent(requireContext(), MyService::class.java))

        recycleRV.adapter = adapter
        viewModel.errorLiveData.observe(viewLifecycleOwner, errorObserver)
        viewModel.booksListLiveData.observe(viewLifecycleOwner, booksListObserver)
        viewModel.openAddContactDialogLiveData.observe(viewLifecycleOwner, openAddContactDialogObserver)
        viewModel.openEditContactDialogLiveData.observe(viewLifecycleOwner, openEditContactDialogObserver)
        binding.addBtn.setOnClickListener {
            viewModel.openAddDialog()
        }
        adapter.setUpdateListener {
            viewModel.openEditDialog(it)
        }
        adapter.setDeleteListener {
            viewModel.deleteBook(it)
        }
        btnSync.setOnClickListener {
            viewModel.syncDatabase()
        }
    }

    private val openAddContactDialogObserver = Observer<Unit> {
        val dialog = AddBookDialog()
        dialog.setAddContactListener {
            viewModel.addBook(it)
        }
        dialog.show(childFragmentManager, "ADD_BOOK")
    }
    private val openEditContactDialogObserver = Observer<BookEntity> { it ->
        val editDialog = EditBookDialog(it)
        editDialog.setAddContactListener {
            viewModel.editBook(it)
        }
        editDialog.show(childFragmentManager, editDialog::class.toString())
    }
    private val errorObserver = Observer<String> {
        Toast.makeText(requireContext(), this.toString(), Toast.LENGTH_SHORT)
    }
    private val booksListObserver = Observer<List<BookEntity>> {
        adapter.submitList(it)
    }


}
