package BankApplicationTask.BankApplicationTask.Service


import BankApplicationTask.BankApplicationTask.DTO.AccountsDTO
import BankApplicationTask.BankApplicationTask.Entity.Accounts
import BankApplicationTask.BankApplicationTask.Repository.AccountsRepository
import mu.KotlinLogging
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class AccountsService(private val accoutsRepository : AccountsRepository) {
    private val logger = KotlinLogging.logger {   } //로그 라이브러리

    //계좌생성
    @Transactional
    fun createAccounts(accountInfo: AccountsDTO): Accounts {
        logger.debug("accountService - accountInfo : $accountInfo")

        return accoutsRepository.save(accountInfo.toEntity()) // DB에 생성된 계좌정보 insert

    }
}