package BankApplicationTask.BankApplicationTask.Entity


import BankApplicationTask.BankApplicationTask.DTO.ResponseDTO
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table
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
    var userId: Users,

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
    constructor(account : String,userId : Users,accountPassword: String) :this(account, userId,"", 0,0)

    //DepositDTO를 위한 생성자
    constructor(account: String,amount: Long) :this(account, Users("",""),"",amount,0)

    fun toDTO() : ResponseDTO{
        return ResponseDTO(
            account =account,
            userId = "",
            balance = balance
        )
    }
}