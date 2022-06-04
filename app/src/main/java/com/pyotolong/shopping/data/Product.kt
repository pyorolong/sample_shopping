package com.pyotolong.shopping.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Product(
    var id: Int?,
    var title: String?,
    var description: String?,
    var price: Long?,
    var discountPercentage: Float?,
    var rating: Float?,
    var stock: Int?,
    var brand: String?,
    var category: String?,
    var thumbnail: String?,
    var images: List<String>
) : Parcelable