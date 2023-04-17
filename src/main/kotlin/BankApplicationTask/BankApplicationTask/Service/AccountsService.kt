package BankApplicationTask.BankApplicationTask.Service


import BankApplicationTask.BankApplicationTask.DTO.AccountsDTO
import BankApplicationTask.BankApplicationTask.DTO.DepositDTO
import BankApplicationTask.BankApplicationTask.DTO.ResponseDTO
import BankApplicationTask.BankApplicationTask.Entity.Accounts
import BankApplicationTask.BankApplicationTask.Repository.AccountsRepository
import com.fasterxml.jackson.databind.ObjectMapper
import mu.KotlinLogging
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class AccountsService(private val accoutsRepository : AccountsRepository, private val mapper: ObjectMapper) {
    private val logger = KotlinLogging.logger {   } //로그 라이브러리

    //계좌생성
    @Transactional
    fun createAccounts(accountInfo: AccountsDTO): Accounts {
        logger.debug("accountService - accountInfo : $accountInfo")
        logger.info { mapper.writeValueAsString(accountInfo) }

        return accoutsRepository.save(accountInfo.toEntity()) // DB에 생성된 계좌정보 insert

    }

    @Transactional
    fun insertDeposit(depositInfo: DepositDTO): ResponseDTO {
        //확인을 위한 로그
        logger.debug ("insertDeposit : $depositInfo")

        //입금 서비스
        //계좌 금액 조회 : 계좌번호로 회원 정보 조회
        var currentBalance : Accounts = accoutsRepository.findByAccount(depositInfo.account)
        logger.info { mapper.writeValueAsString(currentBalance) }

        //입금 잔액 update (잔고가 있어도, 0원이어도 모두 update 됨)
        val response = accoutsRepository.save(currentBalance.updateDeposit(depositInfo))
        logger.info { mapper.writeValueAsString(response) }
        //반환할 user_id,account,balance DTO에 담아서 반환
        return response.toDTO()


    }

}


