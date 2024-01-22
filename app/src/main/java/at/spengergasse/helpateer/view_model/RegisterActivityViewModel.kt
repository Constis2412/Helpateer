package at.spengergasse.helpateer.view_model

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import at.spengergasse.helpateer.data.User
import at.spengergasse.helpateer.repository.AuthRepository

class RegisterActivityViewModel(val authRepository: AuthRepository, val application: Application) :
    ViewModel() {
    private val isLoaded: MutableLiveData<Boolean> =
        MutableLiveData<Boolean>().apply { value = false }
    private val errorMessage: MutableLiveData<HashMap<String, String>> = MutableLiveData()
    private var isUnique: MutableLiveData<Boolean> =
        MutableLiveData<Boolean>().apply { value = false }
    private var user: MutableLiveData<User> = MutableLiveData()

    fun getIsLoaded(): LiveData<Boolean> = isLoaded
    fun getErrorMessage(): LiveData<HashMap<String, String>> = errorMessage
    fun getIsUnique(): LiveData<Boolean> = isUnique
    fun getUser(): LiveData<User> = user

    //fun validateEmailAddress(body: ValidateEmailBody){}


}