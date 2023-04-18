package BankApplicationTask.BankApplicationTask.Service

import BankApplicationTask.BankApplicationTask.DTO.UsersDTO
import BankApplicationTask.BankApplicationTask.DTO.UsersResponseDTO
import BankApplicationTask.BankApplicationTask.Entity.Users
import BankApplicationTask.BankApplicationTask.Repository.UsersRepository
import mu.KotlinLogging
import org.apache.catalina.User
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class UsersService(private val usersRepository: UsersRepository ) {

    private val logger = KotlinLogging.logger {  }

    //사용자 생성
    @Transactional
    fun createUsers(userInfo :UsersDTO) : Boolean {
        logger.debug("createUsers - usersDto : $userInfo")


        // 사용자 생성 전 사용자 조회
        val isJoined : Users?= usersRepository.findByUserId(userInfo.userId)
        if (isJoined == null) { // 사용자가 없다면
            // 저장 후 반환
            usersRepository.save(userInfo.toEntity())
            return true
        } else {
            logger.info { "가입된 사용자 - $isJoined" }
            return false
        }
    }

}