package at.spengergasse.helpateer.data

import com.google.gson.annotations.SerializedName

data class User(@SerializedName("_id") val Id: String, val fullName: String, val Email: String)