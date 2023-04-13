package BankApplicationTask.BankApplicationTask.Service


import BankApplicationTask.BankApplicationTask.Entity.Accounts
import BankApplicationTask.BankApplicationTask.Entity.Users
import BankApplicationTask.BankApplicationTask.Repository.AccountsRepository
import com.fasterxml.jackson.databind.util.JSONPObject
import jakarta.persistence.Id
import mu.KotlinLogging
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class AccountsService(private val accoutsRepository : AccountsRepository) {
    private val logger = KotlinLogging.logger {   } //로그 라이브러리

    //계좌생성
    @Transactional
    fun createAccounts(userId: Users,account:String,accountPassword:String): Accounts {
        logger.debug ("createAccounts - userId: $userId,account: $account,accountPassword : $accountPassword")

        return accoutsRepository.save(Accounts(userId,account,accountPassword)) // DB에 생성된 계좌정보 insert

    }
}