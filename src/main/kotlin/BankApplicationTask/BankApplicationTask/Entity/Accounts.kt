package BankApplicationTask.BankApplicationTask.Entity


import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table
import org.hibernate.annotations.OnDelete
import org.hibernate.annotations.OnDeleteAction


@Entity
@Table(name="accounts")
class Accounts(

    @Id val account:String,
    @ManyToOne
    @JoinColumn(name="user_id")
    var userId: Users,

    @Column
    var accountPassword: String,

    @Column
    var amount: Long,

    @Column
    var balance: Long

){
    constructor(userId:Users,account:String,accountPassword: String) :this(account,userId,accountPassword,0,0)
//    fun toDTO() : AccountsDTO{
//        return AccountsDTO(
//            account=account,
//            userId = userId,
//            accountPassword = accountPassword,
//            amount = amount,
//            balance = balance
//
//        )
//    }
}