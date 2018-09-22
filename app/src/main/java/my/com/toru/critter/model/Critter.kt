package my.com.toru.critter.model

import com.google.gson.annotations.SerializedName

data class Critter(@SerializedName("name")  val str:String,
                   @SerializedName("name2") val str2:String)


data class CritterDBModel(val date:String,
                          val time:String)


class CritterDB(){
    constructor(date:String,
                time:String) : this()

    var date:String = ""
    var time:String = ""
}