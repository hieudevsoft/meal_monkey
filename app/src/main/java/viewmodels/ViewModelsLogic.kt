package viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import repositories.RepositoryLogic

class ViewModelsLogic: ViewModel() {
    private val repositoryLogic = RepositoryLogic.instance
    fun getValidEmail():LiveData<Boolean> = repositoryLogic.getValidEmail()
    fun getValidName():LiveData<Boolean> = repositoryLogic.getValidName()
    fun getValidPhone():LiveData<Boolean> = repositoryLogic.getValidPhoneNumber()
    fun getValidAddress():LiveData<Boolean> = repositoryLogic.getValidAddress()
    fun getValidPassword():LiveData<Boolean> = repositoryLogic.getValidPassword()
    fun getValidConfirmPassword():LiveData<Boolean> = repositoryLogic.getValidConfirmPassword()
    fun getValidAccount():LiveData<Boolean> = repositoryLogic.getValidAccount()

    fun validEmail(email:String){
        repositoryLogic.validEmail(email)
    }
    fun validName(name:String){
        repositoryLogic.validName(name)
    }
    fun validPhoneNumber(phone:String){
        repositoryLogic.validPhoneNumber(phone)
    }
    fun validAddress(address:String){
        repositoryLogic.validAddress(address)
    }
    fun validPassword(password:String){
        repositoryLogic.validPassword(password)
    }
    fun validConfirmPassword(password: String,confirmPassword:String){
        repositoryLogic.validConfirmPassword(password,confirmPassword)
    }
    fun validAccount(name:String,email:String,phoneNumber:String,address: String,password: String,confirmPassword: String){
        repositoryLogic.validAccount(name,email,phoneNumber,address,password,confirmPassword)
    }
}