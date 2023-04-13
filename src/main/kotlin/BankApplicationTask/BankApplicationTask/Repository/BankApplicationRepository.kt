package BankApplicationTask.BankApplicationTask.Repository

import BankApplicationTask.BankApplicationTask.Entity.Users
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface BankApplicationRepository : JpaRepository<Users,String>{


}