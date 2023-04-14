package BankApplicationTask.BankApplicationTask.Controller


import BankApplicationTask.BankApplicationTask.DTO.AccountsDTO
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
    fun createAccount(@PathVariable("id") userId : String,@PathVariable("accountId") account:String,@RequestBody accountInfo: AccountsDTO) : ResponseEntity<AccountsDTO> {
        //requestBody 확인을 위한 로그
        logger.debug("계좌생성- userId : ${accountInfo.userId}, 계좌번호 : ${accountInfo.account} , 계좌번호 : ${accountInfo.accountPassword}")

        //계좌생성을 위한 service메소드 호출
        val createAccount = accountsService.createAccounts(accountInfo)
        //계좌생성 완료 확인을 위한 로그
        logger.debug { "$createAccount" }

        //Reponse 처리
        if( createAccount != null){
            return ResponseEntity(HttpStatus.OK) //계좌 생성 성공 시 200 상태코드 반환
        }else {
            return ResponseEntity(HttpStatus.BAD_REQUEST)
        }

    }
}