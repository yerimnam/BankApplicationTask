package BankApplicationTask.BankApplicationTask.DTO

import BankApplicationTask.BankApplicationTask.Entity.Accounts
import BankApplicationTask.BankApplicationTask.Entity.Users


data class AccountsDTO(val account: String,
                       var userId :String,
                       var accountPassword: String,
                       var amount : Long,
                       var balance : Long,
    ) {


        constructor(account: String, userId: String, accountPassword: String):this(account,userId,accountPassword,0,0)
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