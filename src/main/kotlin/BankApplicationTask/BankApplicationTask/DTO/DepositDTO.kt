package BankApplicationTask.BankApplicationTask.DTO

import BankApplicationTask.BankApplicationTask.Entity.Accounts

data class DepositDTO(

        var account : String,
        var amount : Long

){
//    fun toEntity():Accounts {
//        return Accounts(
//            account = account,
//            amount = amount
//        )
//    }
}
