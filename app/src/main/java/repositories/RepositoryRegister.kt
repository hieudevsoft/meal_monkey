package repositories

import androidx.lifecycle.LiveData
import request_clients.RegisterWithEmail


class RepositoryRegister private constructor() {
    private val registerWithEmail: RegisterWithEmail = RegisterWithEmail.instance

    private object Holder { val instance = RepositoryRegister()}
    companion object {
        @JvmStatic
        val instance : RepositoryRegister by lazy { Holder.instance }
    }

    fun getResultRegister():LiveData<Boolean> = registerWithEmail.getResultRegister()
    fun getRequestTimeOutRegister():LiveData<Boolean> = registerWithEmail.getRequestTimeOutRegister()
    fun register(email:String,password:String) = registerWithEmail.register(email,password)
    fun getResultException():LiveData<String> = registerWithEmail.getResultException()

}