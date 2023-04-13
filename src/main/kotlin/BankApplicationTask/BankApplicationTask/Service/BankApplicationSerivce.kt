package BankApplicationTask.BankApplicationTask.Service

import BankApplicationTask.BankApplicationTask.DTO.UsersDTO
import BankApplicationTask.BankApplicationTask.Entity.Users
import BankApplicationTask.BankApplicationTask.Repository.BankApplicationRepository
import mu.KotlinLogging
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional



@Service
class BankApplicationService(private val bankApplicationRepository : BankApplicationRepository) {

    private val logger = KotlinLogging.logger {  }

    @Transactional
    fun createUsers(userId: String, password: String): Users { //사용자 생성
        logger.debug("createUsers : userId $userId,password : $password")
        var usersDto  = UsersDTO(userId,password)
        return bankApplicationRepository.save(usersDto.toEntity()) // DB 저장결과 return
    }
}