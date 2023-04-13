package BankApplicationTask.BankApplicationTask.DTO

import BankApplicationTask.BankApplicationTask.Entity.Users

data class UsersDTO(var userId :String, var password :String){

    fun toEntity():Users{ //DTO를 Entity로 변환하가 위한 함수
        return Users(userId=userId,password = password)
    }
}