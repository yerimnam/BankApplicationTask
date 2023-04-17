package BankApplicationTask.BankApplicationTask.Entity


import BankApplicationTask.BankApplicationTask.DTO.DepositDTO
import BankApplicationTask.BankApplicationTask.DTO.ResponseDTO
import jakarta.persistence.*
import org.hibernate.annotations.NotFound
import org.hibernate.annotations.NotFoundAction


@Entity
@Table(name="accounts")
class Accounts(

    @Id
    val account:String,

    @ManyToOne
    @JoinColumn(name="user_id")
    @NotFound(action = NotFoundAction.IGNORE)
    var user: Users,

    @Column
    var accountPassword: String,

    @Column
    var amount: Long,

    @Column
    var balance: Long

){
    //빈생성자
    constructor():this("", Users("",""),"",0,0)

    //AccountDTO를 위한 생성자
    constructor(account : String,user : Users,accountPassword: String) :this(account, user,accountPassword, 0,0)

    //DepositDTO를 위한 생성자
    constructor(account: String,amount: Long) :this(account, Users("",""),"",amount,0)

    fun toDTO() : ResponseDTO{
        return ResponseDTO(
            account =account,
            userId = user.userId, //user객체의 userID 사용
            balance = balance
        )
    }

    //입금 금액 update를 위한 function
    fun updateDeposit(depositDTO: DepositDTO): Accounts {
        balance += depositDTO.amount //현재 잔고 = 현재잔고 + 입금 금액
        return this
    }
}