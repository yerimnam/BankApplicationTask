package BankApplicationTask.BankApplicationTask.Service

import BankApplicationTask.BankApplicationTask.DTO.UsersDTO
import BankApplicationTask.BankApplicationTask.Entity.Users
import BankApplicationTask.BankApplicationTask.Repository.UsersRepository
import mu.KotlinLogging
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class UsersService(private val usersRepository: UsersRepository ) {

    private val logger = KotlinLogging.logger {  }

    //사용자 생성
    @Transactional
    fun createUsers(userInfo :UsersDTO) : Users {
        logger.debug("createUsers - usersDto : $userInfo")

        return usersRepository.save(userInfo.toEntity()) // DB 저장결과 return
    }
}