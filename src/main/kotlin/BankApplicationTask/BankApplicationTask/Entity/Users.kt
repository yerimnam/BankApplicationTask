package BankApplicationTask.BankApplicationTask.Entity

import com.fasterxml.jackson.annotation.JsonProperty
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import jakarta.persistence.OneToMany
import jakarta.persistence.Table
import org.hibernate.annotations.NotFound
import org.hibernate.annotations.NotFoundAction

@Entity
@Table(name="users")
 class Users(

    @Id
    @JsonProperty("user_id")
    var userId : String,

    @Column
    var password : String

){
     constructor() :this("","")
 }

