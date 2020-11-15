package com.padc.grocerymtkm.data.vos

import android.graphics.Bitmap
import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
class GroceryVO(
    var name: String?="",
    var description: String? = "",
    var amount: Int? = 0,
    var image: String? = ""
)