package my.com.toru.critter.model

import com.google.gson.annotations.SerializedName

data class Critter(@SerializedName("name")  val str:String,
                   @SerializedName("name2") val str2:String)