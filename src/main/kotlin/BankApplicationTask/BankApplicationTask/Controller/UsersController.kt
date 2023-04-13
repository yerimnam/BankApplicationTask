package BankApplicationTask.BankApplicationTask.Controller


import BankApplicationTask.BankApplicationTask.Entity.Accounts
import BankApplicationTask.BankApplicationTask.Entity.Users
import BankApplicationTask.BankApplicationTask.Service.UsersService
import mu.KotlinLogging
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/users", produces =["application/json; charset=UTF-8"] )
class UsersController(private val usersService : UsersService) {
    private val logger =KotlinLogging.logger {  } //로그 라이브러리 사용

        //사용자 생성 api
       @PostMapping("/{id}")
        fun createUsers(@PathVariable("id") userId :String,@RequestBody password:String ) : ResponseEntity<Users>{
                logger.debug ("createUsers - userId : $userId, password : $password") //로거

                //사용자 생성을 위한 service메소드 호출
                val iscreated  = usersService.createUsers(userId,password)
                logger.debug ("iscreated : $iscreated")

                //응답 처리
                if( iscreated != null ){ // 사용자 정보가 생성이 됐다면 Reponse: OK , body : 없음
                         return ResponseEntity(HttpStatus.OK)
                }else {//생성이 되지 않았다면 Reponse :badrequest
                        return ResponseEntity(HttpStatus.BAD_REQUEST)
                }
        }

}