package com.kirollos.dataSource.data.local

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.kirollos.dataSource.domain.model.Result

class ListTypeConverter(var gson: Gson = Gson()) {
    @TypeConverter
    fun stringToSomeObjectList(data: String?): MutableList<Result> {
        if (data == null) {
            return mutableListOf()
        }
        val listType = object : TypeToken<MutableList<Result?>?>() {}.type
        return gson.fromJson(data, listType)
    }

    @TypeConverter
    fun someObjectListToString(someObjects: MutableList<Result?>?): String {
        return gson.toJson(someObjects)
    }

}