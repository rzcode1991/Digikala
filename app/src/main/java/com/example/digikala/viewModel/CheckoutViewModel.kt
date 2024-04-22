package com.example.digikala.viewModel

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.ViewModel
import com.example.digikala.R
import com.example.digikala.data.model.checkout.DayAndDate
import com.example.digikala.repository.CheckoutRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import java.util.Calendar
import javax.inject.Inject

@HiltViewModel
class CheckoutViewModel @Inject constructor(
    private val repository: CheckoutRepository
): ViewModel() {

    @Composable
    fun getNextEvenDays(): List<DayAndDate> {
        val today = Calendar.getInstance()
        val currentDayOfWeek = today.get(Calendar.DAY_OF_WEEK)

        val currentDate = Calendar.getInstance()
        currentDate.add(
            Calendar.DAY_OF_YEAR,
            calculateDaysAfter(currentDayOfWeek)
        )

        if (currentDayOfWeek % 2 != 0) {
            currentDate.add(Calendar.DAY_OF_YEAR, 1)
        }

        val nextEvenDays = mutableListOf<DayAndDate>()

        repeat(5) { count ->
            val dayOfWeek = mapToPersianDay(currentDate.get(Calendar.DAY_OF_WEEK))
            val month = mapToPersianMonth(currentDate.get(Calendar.MONTH))
            nextEvenDays.add(
                DayAndDate(
                    dayOfWeek,
                    currentDate.get(Calendar.DAY_OF_MONTH),
                    month
                )
            )
            currentDate.add(
                Calendar.DAY_OF_YEAR,
                if (nextEvenDays[count].day == mapToPersianDay(Calendar.WEDNESDAY)) 3 else 2
            )
        }

        return nextEvenDays
    }

    private fun calculateDaysAfter(today: Int): Int {
        return when(today){
            Calendar.SUNDAY -> 2
            Calendar.MONDAY -> 5
            Calendar.TUESDAY -> 3
            Calendar.WEDNESDAY -> 3
            Calendar.THURSDAY -> 3
            Calendar.FRIDAY -> 3
            Calendar.SATURDAY -> 3
            else -> 0
        }
    }

    @Composable
    private fun mapToPersianDay(dayOfWeek: Int): String {
        return when (dayOfWeek) {
            Calendar.SUNDAY -> stringResource(id = R.string.sun)
            Calendar.MONDAY -> stringResource(id = R.string.mon)
            Calendar.TUESDAY -> stringResource(id = R.string.tue)
            Calendar.WEDNESDAY -> stringResource(id = R.string.wed)
            Calendar.THURSDAY -> stringResource(id = R.string.thu)
            Calendar.FRIDAY -> stringResource(id = R.string.fri)
            Calendar.SATURDAY -> stringResource(id = R.string.sat)
            else -> ""
        }
    }

    @Composable
    private fun mapToPersianMonth(month: Int): String {
        return when (month) {
            Calendar.JANUARY -> stringResource(id = R.string.jan)
            Calendar.FEBRUARY -> stringResource(id = R.string.feb)
            Calendar.MARCH -> stringResource(id = R.string.mar)
            Calendar.APRIL -> stringResource(id = R.string.apr)
            Calendar.MAY -> stringResource(id = R.string.may)
            Calendar.JUNE -> stringResource(id = R.string.jun)
            Calendar.JULY -> stringResource(id = R.string.jul)
            Calendar.AUGUST -> stringResource(id = R.string.aug)
            Calendar.SEPTEMBER -> stringResource(id = R.string.sep)
            Calendar.OCTOBER -> stringResource(id = R.string.oct)
            Calendar.NOVEMBER -> stringResource(id = R.string.nov)
            Calendar.DECEMBER -> stringResource(id = R.string.dec)
            else -> ""
        }
    }


}