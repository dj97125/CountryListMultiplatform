package data

import kotlinx.serialization.*

@Serializable
data class CountryResponse(
    @SerialName("capital")
    val capital: String? = null,
    @SerialName("code")
    val code: String? = null,
    @SerialName("flag")
    val flag: String? = null,
    @SerialName("name")
    val name: String? = null,
    @SerialName("region")
    val region: String? = null
)
