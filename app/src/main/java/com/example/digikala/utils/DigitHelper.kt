package com.example.digikala.utils

import com.example.digikala.utils.Constants.PERSIAN_LANG
import com.example.digikala.utils.Constants.USER_LANGUAGE
import java.text.DecimalFormat

object DigitHelper {

    fun engToFa(englishNumber: String): String{
        if (USER_LANGUAGE == PERSIAN_LANG){
            var result = ""
            var fa = '۰'
            for (ch in englishNumber){
                fa = ch
                when(ch){
                    '0' -> fa = '۰'
                    '1' -> fa = '۱'
                    '2' -> fa = '۲'
                    '3' -> fa = '۳'
                    '4' -> fa = '۴'
                    '5' -> fa = '۵'
                    '6' -> fa = '۶'
                    '7' -> fa = '۷'
                    '8' -> fa = '۸'
                    '9' -> fa = '۹'
                }
                result = "$result$fa"
            }
            return result
        }else{
            return englishNumber
        }
    }

    private fun separateByComma(price: String): String{
        val priceFormat = DecimalFormat("###,###")
        return priceFormat.format(Integer.valueOf(price))
    }

    fun engToFaAndSeparateByComma(price: String): String{
        val priceWithoutComma = price.replace(",", "")
        val persianPrice = engToFa(priceWithoutComma)
        return separateByComma(persianPrice)
    }

    fun applyDiscount(price: Long, discountPercent: Int): Long{
        return if (discountPercent > 0){
            val discountAmount = (price * discountPercent) / 100
            price - discountAmount
        }else{
            price
        }
    }


}

