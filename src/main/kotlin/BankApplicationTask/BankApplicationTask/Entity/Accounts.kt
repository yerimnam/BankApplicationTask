package BankApplicationTask.BankApplicationTask.Entity


import BankApplicationTask.BankApplicationTask.DTO.AccountsDTO
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table
import org.hibernate.annotations.NotFound
import org.hibernate.annotations.NotFoundAction
import org.hibernate.annotations.OnDelete
import org.hibernate.annotations.OnDeleteAction


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
    constructor(account: String,userId: Users,accountPassword: String) :this(account, userId,accountPassword,0,0)
    fun toDTO() : AccountsDTO{
        return AccountsDTO(
            account=account,
            userId = "",
            accountPassword = accountPassword,
            amount = amount,
            balance = balance

        )
    }
}