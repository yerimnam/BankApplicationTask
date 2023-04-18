package BankApplicationTask.BankApplicationTask.DTO

import BankApplicationTask.BankApplicationTask.Entity.Accounts

data class DepositDTO(

        var account : String, //계좌번호
        var amount : Long //입금금액

)
