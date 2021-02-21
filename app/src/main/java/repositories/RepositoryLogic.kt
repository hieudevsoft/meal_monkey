package repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import logics.UserRegister

class RepositoryLogic private constructor() {
    private object Holder{
        val instance = RepositoryLogic()
    }

    companion object{
        val instance : RepositoryLogic by lazy { Holder.instance }
    }
    private val userRegister = UserRegister.instance


    private val validEmail : MutableLiveData<Boolean> = MutableLiveData(true)
    fun getValidEmail():LiveData<Boolean> = validEmail
    fun validEmail(email:String){
        validEmail.postValue(userRegister.validEmail(email))
    }

    private val validName : MutableLiveData<Boolean> = MutableLiveData(true)
    fun getValidName():LiveData<Boolean> = validName
    fun validName(name:String){
        validName.postValue(userRegister.validName(name))
    }

    private val validPhone : MutableLiveData<Boolean> = MutableLiveData(true)
    fun getValidPhoneNumber():LiveData<Boolean> = validPhone
    fun validPhoneNumber(phone:String){
        validPhone.postValue(userRegister.validPhoneNumber(phone))
    }

    private val validAddress : MutableLiveData<Boolean> = MutableLiveData(true)
    fun getValidAddress():LiveData<Boolean> = validAddress
    fun validAddress(address:String){
        validAddress.postValue(userRegister.validAddress(address))
    }

    private val validPassword : MutableLiveData<Boolean> = MutableLiveData(true)
    fun getValidPassword():LiveData<Boolean> = validPassword
    fun validPassword(password:String){
        validPassword.postValue(userRegister.validPassword(password))
    }

    private val validConfirmPassword : MutableLiveData<Boolean> = MutableLiveData(true)
    fun getValidConfirmPassword():LiveData<Boolean> = validConfirmPassword
    fun validConfirmPassword(password:String,confirm:String){
        validConfirmPassword.postValue(userRegister.validConfirmPassword(password,confirm))
    }

    private val validAccount : MutableLiveData<Boolean> = MutableLiveData(true)
    fun getValidAccount():LiveData<Boolean> = validAccount
    fun validAccount(name:String,email:String,phoneNumber:String,address: String,password: String,confirmPassword: String){
        validAccount.postValue(userRegister.validAccount(name,email,phoneNumber,address,password,confirmPassword))
    }
}