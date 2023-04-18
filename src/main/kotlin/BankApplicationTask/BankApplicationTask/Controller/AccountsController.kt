package BankApplicationTask.BankApplicationTask.Controller


import BankApplicationTask.BankApplicationTask.DTO.*
import BankApplicationTask.BankApplicationTask.Service.AccountsService
import mu.KotlinLogging
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(produces =["application/json; charset=UTF-8"] )
class AccountsController(private val accountsService : AccountsService) {
    private val logger = KotlinLogging.logger {  } //로그 라이브러리 사용

    //계좌 생성 API
    @PostMapping("/users/{id}/accounts/{accountId}")
    fun createAccount(@PathVariable("id") userId : String,@PathVariable("accountId") account:String,@RequestBody accountInfo: AccountsDTO) : ResponseEntity<AccountsDTO> {
        //requestBody 확인을 위한 로그
        logger.debug("계좌생성- userId : ${accountInfo.userId}, 계좌번호 : ${accountInfo.account} , 계좌비밀번호 : ${accountInfo.accountPassword}")

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

    //입금 API
    @PostMapping("/users/{id}/accounts/{accountId}/deposit")
    fun deposit(
        @PathVariable("id") userId: String,
        @PathVariable("accountId") account : String,
        @RequestBody depositInfo : DepositDTO
    ) :ResponseEntity<ResponseDTO>{
        logger.debug ("depositInfo : $depositInfo ,userId :$userId")

        //입금을 위한 서비스 호출
        val result = accountsService.insertDeposit(depositInfo)

        //결과 반환
        if(result != null){ //입금 완료 시 200 Ok, user_id,account,balance 가져오기
            return ResponseEntity
                .ok()
                .body(result)
        }else {
            return ResponseEntity(HttpStatus.BAD_REQUEST)
        }
    }


    //출금 API
    @PostMapping("/users/{id}/accounts/{accountId}/withdraw")
    fun withdraw(
        @PathVariable("id") userId : String,
        @PathVariable("accountId")  account : String,
        @RequestBody withdrawInfo : WithdrawDTO
    ): ResponseEntity<ResponseDTO>{

        //출금을 위한 서비스 호출
        val result = accountsService.withdraw(withdrawInfo)

        //결과 반환
        if(result != null){  //입금 완료 시 200 OK, user_id,account,balance 반환
            return  ResponseEntity
                .ok()
                .body(result)
        }else {
            return ResponseEntity(HttpStatus.BAD_REQUEST)
        }
    }
}