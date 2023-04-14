package BankApplicationTask.BankApplicationTask.DTO

import BankApplicationTask.BankApplicationTask.Entity.Accounts
import BankApplicationTask.BankApplicationTask.Entity.Users
import com.fasterxml.jackson.annotation.JsonProperty


data class AccountsDTO(

    val account: String,
    @JsonProperty("user_id")
    var userId :String,
    @JsonProperty("account_password")
    var accountPassword: String,
    var amount : Long,
    var balance : Long,
    ) {



    //DTO를 Entity로 변환하기 위한 함수
    fun toEntity():Accounts {
        return Accounts(
            account =account,
            userId = Users(userId,""),
            accountPassword = accountPassword,
            amount = amount,
            balance =balance
        )
    }
}