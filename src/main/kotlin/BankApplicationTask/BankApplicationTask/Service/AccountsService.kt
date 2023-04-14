package BankApplicationTask.BankApplicationTask.Service


import BankApplicationTask.BankApplicationTask.DTO.AccountsDTO
import BankApplicationTask.BankApplicationTask.DTO.DepositDTO
import BankApplicationTask.BankApplicationTask.DTO.ResponseDTO
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

    @Transactional
    fun insertDeposit(depositInfo: DepositDTO): ResponseDTO {
        //확인을 위한 로그
        logger.debug ("insertDeposit : $depositInfo")
        //DB에 저장될 총 금액
        var totalamount = depositInfo.amount


        //입금 서비스
        //계좌 금액 조회 : 회원 아이디와 계좌번호로 조회
        var currentBalance : Accounts = accoutsRepository.findByAccount(depositInfo.toEntity())

        //잔액 = 0 ->insert 하기
        if(currentBalance.balance == 0L){
            accoutsRepository.save(depositInfo.toEntity()) //현재 잔액이 0이면 insert
        }else{
            // 잔액 있다면 : 잔액 +=입금액  =>update될 통장 잔고 금액
            totalamount += currentBalance.balance //기존 잔고와 입금액 합
            depositInfo.amount += currentBalance.balance //총 잔액
            accoutsRepository.save(depositInfo.toEntity())
        }

        //반환을 위한 조회
        val response  = accoutsRepository.findByAccount(depositInfo.toEntity())

        //반환할 user_id,account,balance DTO에 담아서 반환
        return response.toDTO()


    }

}


