package com.example.digikala.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.digikala.data.model.comment.NewComment
import com.example.digikala.data.model.productDetails.Comment
import com.example.digikala.data.network.NetworkResult
import com.example.digikala.data.source.ProductCommentsDataSource
import com.example.digikala.data.source.UserCommentsDataSource
import com.example.digikala.repository.CommentRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CommentViewModel @Inject constructor(
    private val repository: CommentRepository
): ViewModel() {

    var sliderValue = MutableStateFlow(0f)
    var sliderScore = MutableStateFlow(0)
    var title by mutableStateOf(TextFieldValue(""))
    var positivePointTxt by mutableStateOf(TextFieldValue(""))
    var positivePointsList by mutableStateOf(listOf<String>())
    var negativePointTxt by mutableStateOf(TextFieldValue(""))
    var negativePointsList by mutableStateOf(listOf<String>())
    var mainText by mutableStateOf(TextFieldValue(""))
    var switchState by mutableStateOf(false)

    val commentResponse = MutableStateFlow<NetworkResult<String>>(NetworkResult.Loading())

    fun setNewComment(newComment: NewComment){
        viewModelScope.launch(Dispatchers.IO) {
            commentResponse.emit(repository.setNewComment(newComment))
        }
    }

    fun onSliderValueChanged(newValue: Float){
        viewModelScope.launch {
            sliderValue.emit(newValue)
        }
    }
    fun onSliderScoreChanged(newValue: Int){
        viewModelScope.launch {
            sliderScore.emit(newValue)
        }
    }
    fun onTitleChanged(newValue: TextFieldValue){
        title = newValue
    }
    fun onPositivePointChanged(newValue: TextFieldValue){
        positivePointTxt = newValue
    }
    fun addPositivePoint(newPositivePoint: String){
        positivePointsList = positivePointsList + newPositivePoint
    }
    fun removePositivePoint(positivePointItem: String){
        positivePointsList = positivePointsList - positivePointItem
    }
    fun onNegativePointChanged(newValue: TextFieldValue){
        negativePointTxt = newValue
    }
    fun addNegativePoint(newPositivePoint: String){
        negativePointsList = negativePointsList + newPositivePoint
    }
    fun removeNegativePoint(positivePointItem: String){
        negativePointsList = negativePointsList - positivePointItem
    }
    fun onMainTxtChanged(newValue: TextFieldValue){
        mainText = newValue
    }
    fun onSwitchStateChanged(newState: Boolean){
        switchState = newState
    }

    var productCommentsList: Flow<PagingData<Comment>> = flow { emit(PagingData.empty()) }

    var userCommentsList: Flow<PagingData<Comment>> = flow { emit(PagingData.empty()) }

    fun getProductCommentsList(productId: String){
        productCommentsList = Pager(
            PagingConfig(pageSize = 5)
        ){
            ProductCommentsDataSource(repository, productId)
        }.flow.cachedIn(viewModelScope)
    }

    fun getUserCommentsList(userToken: String){
        userCommentsList = Pager(
            PagingConfig(pageSize = 5)
        ){
            UserCommentsDataSource(repository, userToken)
        }.flow.cachedIn(viewModelScope)
    }

}