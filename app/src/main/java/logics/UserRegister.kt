package logics

import java.util.regex.Pattern

class UserRegister private constructor(){
    private object Holder{
        val instance = UserRegister()
    }
    companion object{
        val instance: UserRegister by lazy { Holder.instance }
    }
    fun validName(s:String):Boolean{
        val listCharacterError = listOf("!","@","#","$","%","^","&","*","(",")",",",".","<",">","?","/")
        listCharacterError.forEach {
            if(s.contains(it,true)) {
                return false
            }
        }
        return true
    }

    fun validEmail(email:String):Boolean {
        val pattern = Pattern.compile("^[\\w-.]+@([\\w-]+\\.)+[\\w-]{2,4}\$")
        val matcher = pattern.matcher(email)
        return matcher.matches()
    }

    fun validPhoneNumber(phone:String):Boolean{
        val pattern = Pattern.compile("^[+]*[(]?[0-9]{1,4}[)]{0,1}[-\\s\\./0-9]*\$")
        val matcher = pattern.matcher(phone)
        return matcher.matches()
    }

    fun validAddress(address:String):Boolean{
        val listCharacterError = listOf("#","$","%","&","<",">","?")
        listCharacterError.forEach {
            if(address.contains(it,true)) {
                return false
            }
        }
        return true
    }

    fun validPassword(password:String):Boolean = password.length>6

    fun validConfirmPassword(password:String,confirmPassword:String):Boolean {
        return password == confirmPassword
    }

    fun validAccount(name:String,email:String,phoneNumber:String,address: String,password: String,confirmPassword: String):Boolean
    =       validName(name) &&
            validEmail(email) &&
            validPhoneNumber(phoneNumber) &&
            validAddress(address) &&
            validPassword(password) &&
            validConfirmPassword(password,confirmPassword)

}