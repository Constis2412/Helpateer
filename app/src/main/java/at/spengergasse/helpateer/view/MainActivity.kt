package at.spengergasse.helpateer.view

import android.os.Bundle
import android.util.Patterns
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import at.spengergasse.helpateer.R
import at.spengergasse.helpateer.databinding.ActivityRegisterBinding
import at.spengergasse.helpateer.repository.AuthRepository
import at.spengergasse.helpateer.utility.APIService
import at.spengergasse.helpateer.view_model.RegisterActivityViewModel
import at.spengergasse.helpateer.view_model.RegisterActivityViewModelFactory

class MainActivity : AppCompatActivity(), View.OnClickListener, View.OnKeyListener,
    View.OnFocusChangeListener {
    private lateinit var mBinding: ActivityRegisterBinding
    private lateinit var mViewModel: RegisterActivityViewModel

    //lateinit var usernameInput: EditText
    //lateinit var passwordInput: EditText
    //lateinit var loginbutton: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityRegisterBinding.inflate(LayoutInflater.from(this))
        setContentView(mBinding.root)
        mBinding.fullnameE.onFocusChangeListener = this
        mBinding.emailE.onFocusChangeListener = this
        mBinding.passwordE.onFocusChangeListener = this
        mBinding.cpasswordE.onFocusChangeListener = this
        //mViewModel = ViewModelProvider(this, RegisterActivityViewModelFactory(AuthRepository(APIService.getService()), application))
            //.get(RegisterActivityViewModel::class.java)
        //setupObservers()

        /*
        usernameInput = findViewById(R.id.username_input)
        passwordInput = findViewById(R.id.password_input)
        loginbutton = findViewById(R.id.login_button)

        loginbutton.setOnClickListener {
            var username = usernameInput.text.toString()
            var password = passwordInput.text.toString()
            Log.i("TestLogin","Username: $username, Password: $password")
        }*/
    }

    /*
        private fun setupObservers(){
            mViewModel.getIsLoaded().observe(this){
                    mBinding.progressBar.isVisible = it

            }

            mViewModel.getIsUnique().observe(this){
                if(validateEmail(shouldUpdate = false)){
                    if(it){
                        mBinding.emailL.apply{
                            if(isErrorEnabled)isErrorEnabled = false
                            setStartIconDrawable(R.drawable.check_circle)
                        }
                    }else{
                        mBinding.emailL.apply{
                            if(startIconDrawable != null) startIconDrawable = null
                            isErrorEnabled = true
                            error = "Email is already taken"
                        }
                    }
                }
            }

            mViewModel.getErrorMessage().observe(this){
                val formErrorKeys = arrayOf("fullName", "email", "password")
                val message = StringBuilder()
                it.map{entry ->
                    if(formErrorKeys.contains(entry.key)){
                        when(entry.key){
                            "fullName" -> {
                                mBinding.fullnameL.apply{
                                    isErrorEnabled = true
                                    error = entry.value
                                }
                            }
                            "email" -> {
                                mBinding.emailL.apply {
                                    isErrorEnabled = true
                                    error = entry.value
                                }
                            }
                            "password" -> {
                                mBinding.passwordL.apply {
                                    isErrorEnabled = true
                                    error = entry.value
                                }
                            }
                        }
                    }else{
                        message.append(entry.value).append("\n")
                    }

                    if(message.isNotEmpty()){
                        AlertDialog.Builder(this)
                            .setIcon(R.drawable.info)
                            .setTitle("Information")
                            .setMessage(message)
                            .setPositiveButton("OK"){dialog, _ -> dialog!!.dismiss()}
                            .show()
                    }
                }
            }

            mViewModel.getUser().observe(this){

            }
        }

     */

    private fun validateFullName(): Boolean {
        var eMessage: String? = null
        val value: String = mBinding.fullnameE.text.toString()
        if (value.isEmpty()) {
            eMessage = "Full Name is required"
        }

        if (eMessage != null) {
            mBinding.fullnameL.apply {
                isErrorEnabled = true
                error = eMessage
            }
        }
        return eMessage == null

    }

    private fun validateEmail(): Boolean { //shouldUpdate: Boolean = true
        var eMessage: String? = null
        val value: String = mBinding.emailE.text.toString()
        if (value.isEmpty()) {
            eMessage = "Email is required"
        } else if (!Patterns.EMAIL_ADDRESS.matcher(value).matches()) {
            eMessage = "Email is invalid"
        }

        if (eMessage != null ) {//&& shouldUpdate
            mBinding.emailL.apply {
                isErrorEnabled = true
                error = eMessage
            }
        }


        return eMessage == null
    }

    private fun validatePassword(): Boolean {
        var eMessage: String? = null
        val value: String = mBinding.passwordE.text.toString()
        if (value.isEmpty()) {
            eMessage = "Password is required"
        } else if (value.length < 4) {
            eMessage = "Password must be 4 char long"
        }
        if (eMessage != null) {
            mBinding.passwordL.apply {
                isErrorEnabled = true
                error = eMessage
            }
        }
        return eMessage == null
    }

    fun validateComfirmPassword(): Boolean {
        var eMessage: String? = null
        val value1: String = mBinding.cpasswordE.text.toString()

        if (value1.isEmpty()) {
            eMessage = "Comfirm password is required"
        } else if (value1.length < 4) {
            eMessage = "Comfirm password must be 4 char long"
        }

        if (eMessage != null) {
            mBinding.cpasswordL.apply {
                isErrorEnabled = true
                error = eMessage
            }
        }
        return eMessage == null
    }

    private fun validatePasswordAndCondirmPassword(): Boolean {
        var eMessage: String? = null
        val password = mBinding.passwordE.text.toString()
        val confirmPassword = mBinding.cpasswordE.text.toString()

        if (password != confirmPassword) {
            eMessage = "Confirm Password doesnt match Password"
        }

        if (eMessage != null) {
            mBinding.cpasswordL.apply {
                isErrorEnabled = true
                error = eMessage
            }
        }
        return eMessage == null
    }

    override fun onClick(view: View?) {

    }

    override fun onFocusChange(view: View?, hasFocus: Boolean) {
        if (view != null) {
            when (view.id) {
                R.id.fullname_e -> {
                    if (hasFocus) {
                        if (mBinding.fullnameL.isErrorEnabled) {
                            mBinding.fullnameL.isErrorEnabled = false
                        }
                    } else {
                        validateFullName()
                    }
                }

                R.id.email_e -> {
                    if (hasFocus) {
                        if (mBinding.emailL.isErrorEnabled) {
                            mBinding.emailL.isErrorEnabled = false
                        }
                    }else{
                        validateEmail()
                    }

                }

                R.id.password_e -> {
                    if (hasFocus) {
                        if (mBinding.passwordL.isErrorEnabled) {
                            mBinding.passwordL.isErrorEnabled = false
                        }
                    } else {
                        if (validatePassword() && mBinding.cpasswordE.text!!.isNotEmpty() &&
                            validateComfirmPassword() && validatePasswordAndCondirmPassword()
                        ) {
                            if (mBinding.cpasswordL.isErrorEnabled) {
                                mBinding.cpasswordL.isErrorEnabled = false

                            }
                            mBinding.cpasswordL.apply {
                                setStartIconDrawable(R.drawable.check_circle)
                            }
                        }
                    }
                }

                R.id.cpassword_e -> {
                    if (hasFocus) {
                        if (mBinding.cpasswordL.isErrorEnabled) {
                            mBinding.cpasswordL.isErrorEnabled = false
                        }
                    } else {
                        if (validateComfirmPassword() && validatePassword() &&
                            validatePasswordAndCondirmPassword()
                        ) {
                            if (mBinding.passwordL.isErrorEnabled) {
                                mBinding.passwordL.isErrorEnabled = false

                            }
                            mBinding.cpasswordL.apply {
                                setStartIconDrawable(R.drawable.check_circle)
                            }
                        }
                    }
                }
            }

        }
    }

    override fun onKey(v: View?, keyCode: Int, event: KeyEvent?): Boolean {
        TODO("Not yet implemented")
    }
}




