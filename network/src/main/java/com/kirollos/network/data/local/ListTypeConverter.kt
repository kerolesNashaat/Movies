package com.kirollos.network.data.local

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.kirollos.network.domain.model.Result

class ListTypeConverter(var gson: Gson = Gson()) {
    @TypeConverter
    fun stringToSomeObjectList(data: String?): List<Result> {
        if (data == null) {
            return emptyList()
        }
        val listType = object : TypeToken<List<Result?>?>() {}.type
        return gson.fromJson(data, listType)
    }

    @TypeConverter
    fun someObjectListToString(someObjects: List<Result?>?): String {
        return gson.toJson(someObjects)
    }

}