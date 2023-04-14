package BankApplicationTask.BankApplicationTask.Repository

import BankApplicationTask.BankApplicationTask.Entity.Accounts
import BankApplicationTask.BankApplicationTask.Entity.Users
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface AccountsRepository : JpaRepository<Accounts,String> {

    abstract fun findByAccount(toEntity: Accounts): Accounts


}