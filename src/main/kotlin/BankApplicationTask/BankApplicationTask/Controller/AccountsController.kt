package BankApplicationTask.BankApplicationTask.Controller


import BankApplicationTask.BankApplicationTask.Entity.Accounts
import BankApplicationTask.BankApplicationTask.Entity.Users
import BankApplicationTask.BankApplicationTask.Service.AccountsService
import mu.KotlinLogging
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/users", produces =["application/json; charset=UTF-8"] )
class AccountsController(private val accountsService : AccountsService) {
    private val logger = KotlinLogging.logger {  } //로그 라이브러리 사용

    //계좌 생성 API
    @PostMapping("/{id}/accounts/{accountId}")
    fun createAccount(@PathVariable("id")userId: Users,@PathVariable("accountId") account:String,@RequestBody accountPassword: String) : ResponseEntity<Accounts> {
        logger.debug("계좌생성- userId : $userId,account : $account, accountPassword : $accountPassword")
        //전달받은 파라미터들을 DTO담기
//        val accountsInfo = AccountsDTO(userId,account,accountPassword)Í

        //계좌생성을 위한 service메소드 호출
        val createAccount = accountsService.createAccounts(userId,account,accountPassword)

        if( createAccount != null){
            return ResponseEntity(HttpStatus.OK) //계좌 생성 성공 시 200 상태코드 반환
        }else {
            return ResponseEntity(HttpStatus.BAD_REQUEST)
        }

    }
}