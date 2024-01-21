package at.spengergasse.helpateer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View

import at.spengergasse.helpateer.databinding.ActivityRegisterBinding

class MainActivity : AppCompatActivity(), View.OnClickListener, View.OnKeyListener, View.OnFocusChangeListener {
    lateinit var mBinding: ActivityRegisterBinding

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

    private fun validateFullName(): Boolean {
        var eMessage: String? = null
        val value: String = mBinding.fullnameE.toString()
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

    private fun validateEmail(): Boolean {
        var eMessage: String? = null
        val value: String = mBinding.emailE.text.toString()
        if (value.isEmpty()) {
            eMessage = "Email is required"
        } else if (!Patterns.EMAIL_ADDRESS.matcher(value).matches()) {
            eMessage = "Email is invalid"
        }

        if (eMessage != null) {
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

    private fun validatePasswordAndCondirmPassword(): Boolean{
        var eMessage: String? = null
        val password = mBinding.passwordE.text.toString()
        val confirmPassword = mBinding.cpasswordE.text.toString()

        if(password != confirmPassword){
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
                    } else {
                        validateEmail()
                    }
                }

                R.id.password_e -> {
                    if (hasFocus) {
                        if (mBinding.passwordL.isErrorEnabled) {
                            mBinding.passwordL.isErrorEnabled = false
                        }
                    } else {
                        validatePassword()
                    }
                }

                R.id.cpassword_e -> {
                    if (hasFocus) {
                        if (mBinding.cpasswordL.isErrorEnabled) {
                            mBinding.cpasswordL.isErrorEnabled = false
                        }
                    } else {
                        validateComfirmPassword()
                    }
                }
            }
        }

    }
    override fun onKey(view: View?, event: Int, keyEvent: KeyEvent?): Boolean {
       return false
    }


}




