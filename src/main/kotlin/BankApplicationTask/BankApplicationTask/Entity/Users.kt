package BankApplicationTask.BankApplicationTask.Entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import jakarta.persistence.OneToMany
import jakarta.persistence.Table

@Entity
@Table(name="users")
 class Users(

    @Id
    var userId : String,

    @Column
    var password : String



)

