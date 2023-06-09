package BankApplicationTask.BankApplicationTask.DTO

import BankApplicationTask.BankApplicationTask.Entity.Users
import com.fasterxml.jackson.annotation.JsonProperty

data class UsersDTO(
    @JsonProperty("user_id")
    var userId :String,
    var password :String

    ){
    constructor(userId:String) :this(userId,"")
    fun toEntity():Users{ //DTO를 Entity로 변환하가 위한 함수
        return Users(userId=userId,password = password)
    }
}