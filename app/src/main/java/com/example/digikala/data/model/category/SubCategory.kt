package com.example.digikala.data.model.category

data class SubCategory(
    val tool: List<CategoryDataItem>,
    val digital: List<CategoryDataItem>,
    val mobile: List<CategoryDataItem>,
    val supermarket: List<CategoryDataItem>,
    val fashion: List<CategoryDataItem>,
    val native: List<CategoryDataItem>,
    val toy: List<CategoryDataItem>,
    val beauty: List<CategoryDataItem>,
    val home: List<CategoryDataItem>,
    val book: List<CategoryDataItem>,
    val sport: List<CategoryDataItem>
)