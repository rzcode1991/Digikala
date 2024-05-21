package com.example.digikala.viewModel

import androidx.lifecycle.ViewModel
import com.example.digikala.repository.CommentRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CommentViewModel @Inject constructor(
    private val repository: CommentRepository
): ViewModel() {



}