package BankApplicationTask.BankApplicationTask.Controller

import BankApplicationTask.BankApplicationTask.Entity.Users
import BankApplicationTask.BankApplicationTask.Service.BankApplicationService
import mu.KotlinLogging
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController


@RestController
class BankApplicationController(private val bankApplicationService : BankApplicationService) {
    private val logger =KotlinLogging.logger {  }


        @GetMapping("/users")
        fun test():String{
               logger.debug("Test 작동?")
                return "정상작동"
        }


        //사용자 생성을 하는 컨트롤러
       @PostMapping("/users/{id}")
        fun createUsers(@PathVariable("id") userId :String,@RequestBody password:String ) : ResponseEntity<Users>{
                logger.debug ("createUsers - userId : $userId, password : $password") // 나중에 ? 지우기 ->
                //서비스의 메소드 호출
                val iscreated  = bankApplicationService.createUsers(userId,password) //사용자 생성을 위한 service메소드 호출
                logger.debug ("iscreated : $iscreated")
                if( iscreated != null ){ // 사용자 정보가 생성이 됐다면 Reponse: OK , body : 없음
                         return ResponseEntity(HttpStatus.OK)
                }else {//생성이 되지 않았다면 Reponse :badrequest
                        return ResponseEntity(HttpStatus.BAD_REQUEST)
                }
        }

}