package com.example.digikala.ui.screens.productDetails

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.digikala.R
import com.example.digikala.data.model.productDetails.Price
import com.example.digikala.ui.components.TopSectionWithBackArrowAndText
import com.example.digikala.ui.theme.bottomBarColor
import com.example.digikala.ui.theme.digikalaRed
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.patrykandpatrick.vico.compose.axis.horizontal.rememberBottomAxis
import com.patrykandpatrick.vico.compose.axis.vertical.rememberStartAxis
import com.patrykandpatrick.vico.compose.chart.Chart
import com.patrykandpatrick.vico.compose.chart.column.columnChart
import com.patrykandpatrick.vico.compose.chart.line.lineChart
import com.patrykandpatrick.vico.compose.chart.line.lineSpec
import com.patrykandpatrick.vico.compose.chart.scroll.rememberChartScrollSpec
import com.patrykandpatrick.vico.compose.component.shape.shader.verticalGradient
import com.patrykandpatrick.vico.compose.component.textComponent
import com.patrykandpatrick.vico.core.axis.AxisItemPlacer
import com.patrykandpatrick.vico.core.chart.composed.plus
import com.patrykandpatrick.vico.core.chart.values.AxisValuesOverrider
import com.patrykandpatrick.vico.core.component.shape.DashedShape
import com.patrykandpatrick.vico.core.component.shape.LineComponent
import com.patrykandpatrick.vico.core.entry.ChartEntryModel
import com.patrykandpatrick.vico.core.entry.composed.plus
import com.patrykandpatrick.vico.core.entry.entriesOf
import com.patrykandpatrick.vico.core.entry.entryModelOf
import com.patrykandpatrick.vico.core.scroll.AutoScrollCondition
import com.patrykandpatrick.vico.core.scroll.InitialScroll

@Composable
fun PriceChart(
    navController: NavHostController,
    jsonString: String?
){

    if (jsonString != null){

        var priceList by remember {
            mutableStateOf<List<Price>>(emptyList())
        }

        val priceListType = object : TypeToken<List<Price>>() {}.type
        priceList = Gson().fromJson(jsonString, priceListType)

        val chartModel = createChartEntryModel(priceList)


        Scaffold(
            topBar = {
                TopSectionWithBackArrowAndText(
                    navController = navController,
                    title = stringResource(id = R.string.price_chart)
                )
            }
        ) { paddingValues ->

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(paddingValues)
            ) {

                PriceLineChart(chartModel, priceList)

            }

        }

    }
}


private fun createChartEntryModel(priceList: List<Price>): ChartEntryModel {
    val entryPrices = priceList.takeLast(6).map { it.price }
    return entryModelOf(*entryPrices.toTypedArray().reversedArray())
}


@Composable
fun PriceLineChart(
    model: ChartEntryModel,
    priceList: List<Price>,
    scrollable: Boolean = true,
    initialScroll: InitialScroll = InitialScroll.Start,
) {
    ChartCard(
        title = stringResource(id = R.string.product_price_chart),
        subTitle = stringResource(id = R.string.product_price_chart_time)
    ) {
        Chart(
            chart = lineChart(
                lines = listOf(
                    lineSpec(
                        lineColor = MaterialTheme.colorScheme.digikalaRed
                    )
                )
            ),
            model = model,
            startAxis = rememberStartAxis(),
            bottomAxis = rememberBottomAxis(
                itemPlacer = AxisItemPlacer.Horizontal.default(
                    spacing = 20,
                    offset = 10,
                    shiftExtremeTicks = false
                ),
                guideline = LineComponent(
                    color = Color.Red.toArgb(),
                    thicknessDp = 1.dp.value,
                    shape = DashedShape(
                        dashLengthDp = 2.dp.value,
                        gapLengthDp = 4.dp.value
                    ),
                )
            ),
            chartScrollSpec = rememberChartScrollSpec(
                isScrollEnabled = scrollable,
                initialScroll = initialScroll
            )
        )

        Row() {

            Spacer(modifier = Modifier.weight(0.142f))

            val persianMonths = createPersianMonthArray(priceList)
            persianMonths.forEach { monthName ->
                Text(
                    style = MaterialTheme.typography.titleSmall,
                    modifier = Modifier
                        .weight(0.142f),
                    text = monthName,
                    textAlign = TextAlign.Center
                )
            }

        }



    }
}


@Composable
private fun ChartCard(
    title: String,
    subTitle: String,
    chart: @Composable ColumnScope.() -> Unit
) {
    Card(
        modifier = Modifier.padding(8.dp),
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(2.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.bottomBarColor
        )
    ) {

        Column(modifier = Modifier.padding(16.dp)) {

            chart()

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = title,
                style = MaterialTheme.typography.displaySmall,
            )

            Text(
                text = subTitle,
                style = MaterialTheme.typography.headlineMedium,
            )

        }

    }
}

private fun createPersianMonthArray(priceList: List<Price>): Array<String> {
    return priceList.takeLast(6).mapNotNull { price ->
        val numberMonth = price.persianDate.split("/").getOrNull(1)?.toIntOrNull()
        numberMonth?.let { index ->
            mapOf(
                1 to "فروردین",
                2 to "اردیبهشت",
                3 to "خرداد",
                4 to "تیر",
                5 to "مرداد",
                6 to "شهریور",
                7 to "مهر",
                8 to "آبان",
                9 to "آذر",
                10 to "دی",
                11 to "بهمن",
                12 to "اسفند"
            )[index] ?: "Invalid Month"
        }
    }.toTypedArray().reversedArray()
}



// more sample charts:
@Composable
fun ColumnChartWithNegativeValues() {
    ChartCard(
        title = "نمودار ستونی منفی",
        subTitle = "مثالی برای استفاده از نمودار ستونی با مقادیر منفی"
    ) {
        Chart(
            chart = columnChart(
                dataLabel = textComponent(),
            ),
            model = entryModelOf(2f, -1f, 4f, -2f, 1f, 5f, -3f),
            startAxis = rememberStartAxis(),
            bottomAxis = rememberBottomAxis(),
        )
    }
}

@Composable
fun DefaultLineChart(
    model: ChartEntryModel = entryModelOf(2f, 1f, 4f, 8f, 1f, 5f, 7f),
    scrollable: Boolean = true,
    initialScroll: InitialScroll = InitialScroll.Start,
) {
    ChartCard(
        title = "نمودار خطی",
        subTitle = "مثالی برای استفاده از نمودار خطی"
    ) {
        Chart(
            chart = lineChart(),
            model = model,
            startAxis = rememberStartAxis(),
            bottomAxis = rememberBottomAxis(),
            chartScrollSpec = rememberChartScrollSpec(
                isScrollEnabled = scrollable,
                initialScroll = initialScroll
            )
        )
    }
}


@Composable
fun DefaultColumnChart(
    model: ChartEntryModel = entryModelOf(2f, 1f, 4f, 8f, 1f, 5f, 7f),
    oldModel: ChartEntryModel? = null,
    scrollable: Boolean = true,
    initialScroll: InitialScroll = InitialScroll.Start,
    autoScrollCondition: AutoScrollCondition<ChartEntryModel> = AutoScrollCondition.Never,
) {
    ChartCard(
        title = "نمودار ستونی",
        subTitle = "مثالی برای استفاده از نمودار ستونی"
    ) {
        Chart(
            chart = columnChart(),
            model = model,
            oldModel = oldModel,
            startAxis = rememberStartAxis(),
            bottomAxis = rememberBottomAxis(),
            chartScrollSpec = rememberChartScrollSpec(
                isScrollEnabled = scrollable,
                initialScroll = initialScroll,
                autoScrollCondition = autoScrollCondition,
            ),
        )
    }


}


private val model1 = entryModelOf(0, 2, 4, 0, 2)
private val model2 = entryModelOf(1, 3, 4, 1, 3)
private val model3 = entryModelOf(entriesOf(3, 2, 2, 3, 1), entriesOf(1, 3, 1, 2, 3))

@Composable
fun LineChartDark() {
    ChartCard(
        title = "نمودار خطی رنگی دوتایی",
        subTitle = "مثالی برای استفاده از نمودار خطی رنگی دوتایی بدون بک گراند"
    ) {
        val yellow = Color(0xFFFFAA4A)
        val pink = Color(0xFFFF4AAA)
        val red = MaterialTheme.colorScheme.digikalaRed

        Chart(
            modifier = Modifier.padding(8.dp),
            chart = lineChart(
                lines = listOf(
                    lineSpec(
                        lineColor = yellow,
                    ),
                    lineSpec(
                        lineColor = pink,
                        lineBackgroundShader = verticalGradient(
                            arrayOf(pink.copy(0.5f), pink.copy(alpha = 0f)),
                        ),
                    ),
                ),
                axisValuesOverrider = AxisValuesOverrider.fixed(maxY = 4f),
            ),
            model = model3,
        )
    }
}

@Composable
fun ComposedLineChart() {
    ChartCard(
        title = "نمودار خطی رنگی دوتایی",
        subTitle = "مثالی برای استفاده از خطی رنگی دوتایی"
    ) {
        Chart(
            chart = lineChart() + lineChart(
                lines = listOf(
                    lineSpec(
                        lineColor = Color.Blue,
                        lineBackgroundShader = verticalGradient(
                            colors = arrayOf(
                                Color.Blue.copy(alpha = 0.4f),
                                Color.Blue.copy(alpha = 0f),
                            ),
                        ),
                    ),
                ),
            ),
            model = model1 + model2,
            startAxis = rememberStartAxis(),
        )
    }
}