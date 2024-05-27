package com.example.digikala.ui.screens.comment

object NewCommentValidator {

    fun validateCommentInputs(
        sliderScore: Int,
        commentTxt: String
    ): CommentsValidatorErrors{

        return when {
            sliderScore < 1 -> CommentsValidatorErrors.SLIDER_SCORE_ERROR
            commentTxt.length < 20 -> CommentsValidatorErrors.COMMENT_TXT_ERROR
            else -> CommentsValidatorErrors.COMMENT_VALIDATOR_OKAY
        }

    }

}