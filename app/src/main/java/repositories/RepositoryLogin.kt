package repositories

import androidx.lifecycle.LiveData
import request_clients.LoginWithEmail


class RepositoryLogin private constructor() {
    private val logInWithEmail: LoginWithEmail = request_clients.LoginWithEmail.instance

    private object Holder { val instance = RepositoryLogin()}
    companion object {
        @JvmStatic
        val instance : RepositoryLogin by lazy { Holder.instance }
    }

    fun getResultLogin():LiveData<Boolean> = logInWithEmail.getResultLogin()
    fun getRequestTimeOutLogin():LiveData<Boolean> = logInWithEmail.getRequestTimeOutLogin()
    fun login(email:String,password:String) = logInWithEmail.login(email,password)
    fun getResultException():LiveData<String> = logInWithEmail.getResultException()

}