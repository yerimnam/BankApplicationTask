package BankApplicationTask.BankApplicationTask.DTO

import com.fasterxml.jackson.annotation.JsonProperty

data class UsersResponseDTO(
  @JsonProperty("user_id")
  var userId: String,
  var password : String
) {

}