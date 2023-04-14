package BankApplicationTask.BankApplicationTask.DTO

import com.fasterxml.jackson.annotation.JsonProperty

data class ResponseDTO(

    @JsonProperty("user_id")
    var userId: String,
    var account: String,
    var balance: Long
)
