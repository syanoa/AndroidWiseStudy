package com.wayne.algorithm.ktxs

import android.content.Context
import android.location.Geocoder
import android.os.CountDownTimer
import com.google.gson.*
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.*
import java.lang.Exception
import java.lang.reflect.Type
import java.util.*
import kotlin.coroutines.Continuation
import kotlin.math.roundToInt

val gson by lazy {
    Gson()
}

val formatGson: Gson by lazy {
    GsonBuilder().setPrettyPrinting().create()
}

fun Any?.toJson(): String? = try {
    gson.toJson(this)
} catch (e: Exception) {
    null
}

inline fun <reified T> String.jsonAs(): T = gson.fromJson(this, object : TypeToken<T>() {}.type)

inline fun <reified T> JsonElement.jsonAs(): T =
    gson.fromJson(this, object : TypeToken<T>() {}.type)

fun Any?.toFormatJson(): String {
    val jsonElement = JsonParser().parse(toJson())
    return formatGson.toJson(jsonElement)
}

val String.asJsonObject: JsonObject
    get() = JsonParser().parse(this).asJsonObject

@DslMarker
@Target(AnnotationTarget.TYPE)
annotation class AnyDslMarker

inline operator fun <T> T?.invoke(block: (@AnyDslMarker T).() -> Unit) {
    this?.block()
}



class JsonObjectAdapter : JsonDeserializer<String> {
    override fun deserialize(
        json: JsonElement,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): String? {
        return when {
            json.isJsonPrimitive && json.asJsonPrimitive.isString -> json.asString
            json.isJsonObject || json.isJsonArray -> json.asJsonObject.toJson()
            json.isJsonNull -> null
            else -> json.toString()
        }
    }
}

inline fun <reified T> JsonElement.hasObjectFieldJsonAs(): T {
    val gson = GsonBuilder().registerTypeAdapter(String::class.java, JsonObjectAdapter()).create()
    return gson.fromJson(this, object : TypeToken<T>() {}.type)
}

inline fun countdown(
    time: Long,
    interval: Long = 1000,
    crossinline onTick: (Long) -> Unit = {},
    crossinline onFinish: () -> Unit = {},
) = object : CountDownTimer(time, interval) {
    override fun onTick(millisUntilFinished: Long) {
        onTick((millisUntilFinished / 1000f).roundToInt().toLong())
    }

    override fun onFinish() {
        onFinish()
    }
}

fun FloatArray.copy(block: FloatArray.() -> Unit): FloatArray {
    val res = copyOf()
    res.block()
    return res
}



suspend fun Pair<Double, Double>.getAddressByLon2Lat(appContext: Context): String = withContext(Dispatchers.IO) {
    val geocoder = Geocoder(appContext, Locale.getDefault())
    return@withContext this@getAddressByLon2Lat.let { (lon, lat) ->
        geocoder.getFromLocation(lat, lon, 1).let {
            if (it.isEmpty()) {
                "Unknown"
            } else {
                it[0].getAddressLine(0)
            }
        }
    }
}