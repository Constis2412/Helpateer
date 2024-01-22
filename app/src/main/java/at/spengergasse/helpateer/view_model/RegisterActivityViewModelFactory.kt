package at.spengergasse.helpateer.view_model

import android.app.Application
import androidx.lifecycle.ViewModelProvider
import at.spengergasse.helpateer.repository.AuthRepository

class RegisterActivityViewModelFactory(
    val authRepository: AuthRepository,
    val application: Application
) : ViewModelProvider.Factory {

}