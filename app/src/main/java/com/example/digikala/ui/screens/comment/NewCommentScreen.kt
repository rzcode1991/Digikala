package com.example.digikala.ui.screens.comment

import android.net.Uri
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.digikala.R
import com.example.digikala.data.model.comment.NewComment
import com.example.digikala.data.network.NetworkResult
import com.example.digikala.ui.screens.comment.NewCommentValidator.validateCommentInputs
import com.example.digikala.ui.theme.bottomBarColor
import com.example.digikala.ui.theme.spacing
import com.example.digikala.utils.Constants.USER_NAME
import com.example.digikala.utils.Constants.USER_TOKEN
import com.example.digikala.viewModel.CommentViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@Composable
fun NewCommentScreen(
    navController: NavHostController,
    productId: String,
    productName: String,
    productImage: String,
    viewModel: CommentViewModel = hiltViewModel()
) {

    val context = LocalContext.current

    val decodedImage = Uri.decode(productImage)
    val scope = rememberCoroutineScope()
    val snackBarHostState = remember { SnackbarHostState() }
    val sliderScore = viewModel.sliderScore
    val mainTxt = viewModel.mainText.text
    var isButtonActive by remember {
        mutableStateOf(false)
    }
    var isButtonLoading by remember {
        mutableStateOf(false)
    }
    var snackBarText by remember {
        mutableStateOf("")
    }

    when(validateCommentInputs(
        sliderScore = sliderScore,
        commentTxt = mainTxt
    )){
        CommentsValidatorErrors.SLIDER_SCORE_ERROR -> {
            snackBarText = stringResource(id = R.string.please_select_score)
            isButtonActive = false
        }

        CommentsValidatorErrors.COMMENT_TXT_ERROR -> {
            snackBarText = stringResource(id = R.string.please_write_comment)
            isButtonActive = false
        }
        else -> {
            isButtonActive = true
        }
    }

    LaunchedEffect(true){
        viewModel.commentResponse.collectLatest { commentResult ->
            when(commentResult){
                is NetworkResult.Success -> {
                    if (commentResult.message.equals("کامنت با موفقیت ثبت شد")){ // bad backend
                        Toast.makeText(context, commentResult.message.toString(), Toast.LENGTH_SHORT).show()
                        navController.popBackStack()
                    }
                    isButtonLoading = false
                }
                is NetworkResult.Error -> {
                    Toast.makeText(context, commentResult.message.toString(), Toast.LENGTH_SHORT).show()
                    isButtonLoading = false
                }
                is NetworkResult.Loading -> {}
            }
        }
    }

    Scaffold(
        topBar = {
            NewCommentTopSection(
                navController = navController,
                productName = productName,
                decodedImage = decodedImage
            )
        },
        snackbarHost = {
            SnackbarHost(
                hostState = snackBarHostState
            ) {
                Snackbar(
                    snackbarData = it,
                    containerColor = Color.Black,
                    contentColor = Color.White
                )
            }
        }
    ) { paddingValues ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(MaterialTheme.colorScheme.bottomBarColor)
        ) {

            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(
                        horizontal = MaterialTheme.spacing.medium
                    )
            ) {

                item {
                    NewCommentScoreSection(viewModel = viewModel)
                }
                item {
                    NewCommentTitleSection(viewModel = viewModel)
                }
                item {
                    NewCommentPositivePointsSection(viewModel = viewModel)
                }
                item {
                    NewCommentNegativePointsSection(viewModel = viewModel)
                }
                item {
                    NewCommentMainTextSection(viewModel = viewModel)
                }
                item {
                    NewCommentPrivateSection(viewModel = viewModel)
                }
                item {
                    NewCommentSubmitButton(
                        isButtonActive = isButtonActive,
                        isButtonLoading = isButtonLoading,
                        onClick = {
                            if (isButtonActive) {

                                val newComment = NewComment(
                                    token = USER_TOKEN,
                                    productId = productId,
                                    star = sliderScore,
                                    title = viewModel.title.text,
                                    description = mainTxt,
                                    userName = USER_NAME
                                )
                                viewModel.setNewComment(newComment)
                                isButtonLoading = true

                            } else {
                                scope.launch {
                                    snackBarHostState.showSnackbar(
                                        message = snackBarText,
                                        duration = SnackbarDuration.Short
                                    )
                                }
                            }
                        }
                    )
                }
                item {
                    NewCommentRulesTxt(navController = navController)
                }

            }

        }

    }

}