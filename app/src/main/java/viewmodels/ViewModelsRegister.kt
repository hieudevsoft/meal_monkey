package viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import repositories.RepositoryRegister

class ViewModelsRegister: ViewModel() {
    private val repositoryRegister = RepositoryRegister.instance
    var didRegisterSuccess = false

    fun getResultRegister():LiveData<Boolean> = repositoryRegister.getResultRegister()
    fun getRequestTimeOutRegister():LiveData<Boolean> = repositoryRegister.getRequestTimeOutRegister()
    fun register(email:String,password:String) = repositoryRegister.register(email,password)
    fun getResultException():LiveData<String> = repositoryRegister.getResultException()
}