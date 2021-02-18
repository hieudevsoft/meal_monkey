package viewmodels

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import repositories.RepositoryGoogle

class ViewModelsSignInGoogle: ViewModel() {
    private val repositoryGoogle:RepositoryGoogle = RepositoryGoogle.instance
    var didRetrieveIntentGoogle = false

    fun getRequestTimeOutSignInWithGoogle() = repositoryGoogle.getRequestTimeOutSignInWithGoogle()
    fun retrieveIntentSignInGoogle(activity:AppCompatActivity) = repositoryGoogle.retrieveIntentSignInWithGoogle(activity)
    fun getIntentSignInWithGoogle(): LiveData<Intent> = repositoryGoogle.getIntentSignInWithGoogle()
}