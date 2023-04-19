package BankApplicationTask.BankApplicationTask.Service


import BankApplicationTask.BankApplicationTask.DTO.AccountsDTO
import BankApplicationTask.BankApplicationTask.DTO.DepositDTO
import BankApplicationTask.BankApplicationTask.DTO.ResponseDTO
import BankApplicationTask.BankApplicationTask.DTO.WithdrawDTO
import BankApplicationTask.BankApplicationTask.Entity.Accounts
import BankApplicationTask.BankApplicationTask.Repository.AccountsRepository
import com.fasterxml.jackson.databind.ObjectMapper
import mu.KotlinLogging
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class AccountsService(private val accoutsRepository : AccountsRepository, private val mapper: ObjectMapper) {
    private val logger = KotlinLogging.logger {   } //로그 라이브러리

    //계좌생성
    @Transactional
    fun createAccounts(accountInfo: AccountsDTO): Boolean {
        logger.info { mapper.writeValueAsString(accountInfo) }

        //계좌 생성전 사용자의 계좌 조회
        val isAccount : Accounts? = accoutsRepository.findByAccount(accountInfo.account)

        //조회 결과에 따른 처리
        if(isAccount == null){//조회된 계좌가 없을 경우
            //계좌 생성 및 저장
            logger.info ("계좌 생성 완료 : $isAccount")
            accoutsRepository.save(accountInfo.toEntity())
            return  true
        }else { //이미 생성된 계좌가 있는 경우
            logger.info("이미 생성된 계좌 : $isAccount")
            return false
        }
    }

    //입금
    @Transactional
    fun insertDeposit(depositInfo: DepositDTO): ResponseDTO {
        //확인을 위한 로그
        logger.debug ("insertDeposit : $depositInfo")

        //입금 서비스
        //계좌 금액 조회 : 계좌번호로 회원 정보 조회
        var currentBalance : Accounts? = accoutsRepository.findByAccount(depositInfo.account)
        logger.info { mapper.writeValueAsString(currentBalance) }

        //입금 잔액 update (잔고가 있어도, 0원이어도 모두 update 됨)
        if(currentBalance != null){
            val responseDeposit = accoutsRepository.save(currentBalance.updateBalance(depositInfo))
            logger.info { mapper.writeValueAsString(responseDeposit) }

            //반환할 user_id,account,balance DTO에 담아서 반환
            return responseDeposit.toDTO()

        }else {
            return ResponseDTO("","",0)
        }
    }

    //출금
    @Transactional
    fun withdraw(withdrawInfo: WithdrawDTO): ResponseDTO {
        //확인을 위한 로그
        logger.debug { "withdrawInfo : $withdrawInfo" }

        //계좌번호로 계좌정보 조회
        val currentBalance: Accounts? = accoutsRepository.findByAccount(withdrawInfo.account)

        if (currentBalance != null) {
            if (currentBalance.balance - withdrawInfo.amount >= 0L) { //1) 정상 출금 가능 : 기존 잔액 - 원하는 출금 금액>=0
                //출금 처리
                val responseWithdraw = accoutsRepository.save(currentBalance.updateWithdraw(withdrawInfo))
                logger.info { mapper.writeValueAsString(responseWithdraw) }

                return responseWithdraw.toDTO()
            } else if (currentBalance.balance - withdrawInfo.amount < 0L) {//2) 기존 잔액 - 원하는 출금 금액 <0
                // 잔액이 부족-> 현재 잔액 반환 , DB update x
                return currentBalance.toDTO()
            } else { //3)잔액 =0
                // 잔액 부족 : 현재 잔액 반환,DB update x
                return currentBalance.toDTO()
            }

        }else {
            return ResponseDTO("","",0)
        }

    }

}


