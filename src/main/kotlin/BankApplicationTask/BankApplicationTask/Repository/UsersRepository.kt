package BankApplicationTask.BankApplicationTask.Repository

import BankApplicationTask.BankApplicationTask.DTO.UsersDTO
import BankApplicationTask.BankApplicationTask.Entity.Users
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UsersRepository : JpaRepository<Users,String>{
  fun findByUserId(toEntity: String): Users



}