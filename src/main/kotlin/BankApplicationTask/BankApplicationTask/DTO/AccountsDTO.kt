package BankApplicationTask.BankApplicationTask.DTO

import BankApplicationTask.BankApplicationTask.Entity.Accounts
import BankApplicationTask.BankApplicationTask.Entity.Users
import com.fasterxml.jackson.annotation.JsonProperty
import org.apache.catalina.User


data class AccountsDTO(

    val account: String,

    @JsonProperty("user_id")
    var userId :String,

    @JsonProperty("account_password")
    var accountPassword: String,

    ) {



    //DTO를 Entity로 변환하기 위한 함수
    fun toEntity():Accounts {
        return Accounts(
            account =account,
            user = Users(userId,""),
            accountPassword =accountPassword
        )
    }



}