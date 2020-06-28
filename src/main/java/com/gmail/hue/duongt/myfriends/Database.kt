package com.gmail.hue.duongt.myfriends

import android.content.Context
import android.content.SharedPreferences




object Database {
    var openingItem: ItemData? = null
    val items: List<ItemData> = getItemsWithRatingFromShareReferences()

    fun saveRatings() {
        saveToShareReference()
    }

    private fun getItemsWithRatingFromShareReferences(): List<ItemData> {
        val items: List<ItemData> = arrayListOf(
            ItemData(0L,"HUỆ CRAZY", R.drawable.a, "Cô nàng ươm bướng nhưng giàu tình cảm đang trên con đường thực hiện đam mê", 2.0f),
            ItemData(1L, "NGÂN NHỎ", R.drawable.b, "Cô nàng bé nhỏ thích những điều viễn vong đang tìm kiếm mối tình đầu  ", 2.0f),
            ItemData(2L, "QUỲNH LÉP", R.drawable.c, "Cô nàng nhỏ nhắn đáng yêu có những anh chàng xung quanh nhưng vẫn chưa có ai chính thức", 2.0f),
            ItemData(3L, "TRANG Ú", R.drawable.f, "Cô nàng nhút nhát nhưng rất ấm áp rarat thích có người yêu nhưng chưa tìm được người thích hợp", 2.0f),
            ItemData(4L,"HIỆP LÌ", R.drawable.e, "Cô nàng sốc nổi hay dể nổi nóng có vài mối tình đi qua như tia chớp ", 2.0f),
            ItemData(5L,"XUÂN HÍ", R.drawable.g, "Cô nàng mắt hí nhưng có tinh thần nghĩa hiệp vẫn đang cô đơn sớm tối với 22 năm qua", 2.0f)
        )

        for (item in items) {
            val prefs: SharedPreferences? = MyApplication.appContext?.getSharedPreferences(
                "com.gmail.hue.duongt.myfriends", Context.MODE_PRIVATE
            )

            item.rating = prefs?.getFloat(item.id.toString(), 0.0f)!!
        }

        return items
    }

    private fun saveToShareReference() {
        val prefs: SharedPreferences? = MyApplication.appContext?.getSharedPreferences(
            "com.gmail.hue.duongt.myfriends", Context.MODE_PRIVATE
        )
        for (item in items) {
            prefs?.edit()?.putFloat(item.id.toString(), item.rating)?.apply()
        }
    }
}