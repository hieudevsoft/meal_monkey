package viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import repositories.RepositoryLogin

class ViewModelsLogin: ViewModel() {
    private val repositoryLogin = RepositoryLogin.instance
    var didLoginSuccess = false

    fun getResultLogin():LiveData<Boolean> = repositoryLogin.getResultLogin()
    fun getRequestTimeOutLogin():LiveData<Boolean> = repositoryLogin.getRequestTimeOutLogin()
    fun login(email:String,password:String) = repositoryLogin.login(email,password)
    fun getResultException():LiveData<String> = repositoryLogin.getResultException()
}