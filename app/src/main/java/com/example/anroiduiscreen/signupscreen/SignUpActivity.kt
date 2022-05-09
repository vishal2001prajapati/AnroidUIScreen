package com.example.anroiduiscreen.signupscreen

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.SpannableString
import android.text.Spanned
import android.text.TextPaint
import android.text.TextWatcher
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.util.Log
import android.util.Patterns
import android.view.View
import android.view.WindowManager
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import androidx.core.widget.doOnTextChanged
import com.example.anroiduiscreen.R
import com.example.anroiduiscreen.homescreen.HomeScreenActivity
import com.example.anroiduiscreen.signinscreen.SignInActivity
import kotlinx.android.synthetic.main.activity_sign_in.emailInputText
import kotlinx.android.synthetic.main.activity_sign_in.inputEmail
import kotlinx.android.synthetic.main.activity_sign_in.textDoNotHaveAccount
import kotlinx.android.synthetic.main.activity_sign_up.btnCreateAccount
import kotlinx.android.synthetic.main.activity_sign_up.inputName
import kotlinx.android.synthetic.main.activity_sign_up.inputPhoneNumber
import kotlinx.android.synthetic.main.activity_sign_up.inputUserEmail
import kotlinx.android.synthetic.main.activity_sign_up.nameInputText
import kotlinx.android.synthetic.main.activity_sign_up.userEmailInputText
import kotlinx.android.synthetic.main.activity_sign_up.userInputPassword
import kotlinx.android.synthetic.main.activity_sign_up.userPasswordInputText
import kotlinx.android.synthetic.main.activity_sign_up.userPhoneNumber

class SignUpActivity : AppCompatActivity() {

    private val emailPattern by lazy { "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+" }
    private val fullNamePattern by lazy { "^[a-zA-Z]{4,}(?: [a-zA-Z]+)?(?: [a-zA-Z]+)?\$" }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        window.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
        spannableText()
        createAccountButtonClick()
        textChanged()
    }

    private fun textChanged() {
        nameInputText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable) {
                inputName.error = null
                if (nameInputText.text.toString().matches(fullNamePattern.toRegex())) {
                    nameInputText.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_email_perfect, 0)
                } else {
                    nameInputText.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0)
                }
            }
        })

        userEmailInputText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable) {
                inputUserEmail.error = null
                if (Patterns.EMAIL_ADDRESS.matcher(userEmailInputText.text.toString()).matches()) {
                    userEmailInputText.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_email_perfect, 0)
                } else {
                    userEmailInputText.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0)
                }

            }
        })

        userPhoneNumber.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable) {
                inputPhoneNumber.error = null
                if (userPhoneNumber.text.toString().length == 10) {
                    userPhoneNumber.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_email_perfect, 0)
                } else {
                    userPhoneNumber.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0)
                }
            }
        })

        userPasswordInputText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable) {
                userInputPassword.error = null
            }
        })
    }

    private fun createAccountButtonClick() {
        btnCreateAccount.setOnClickListener {
            if (nameInputText.text.toString().isEmpty()) {
                inputName.error = getString(R.string.enter_your_full_name)
            }

            if (userEmailInputText.text.toString().isEmpty()) {
                inputUserEmail.error = getString(R.string.enter_email_address)
            }

            if (userEmailInputText.text.toString().matches(emailPattern.toRegex())) {
                Log.d(getString(R.string.email_address), getString(R.string.perfect_email))
            } else {
                inputUserEmail.error = getString(R.string.enter_proper_email_address)
            }

            if (userPhoneNumber.text.toString().isEmpty()) {
                inputPhoneNumber.error = getString(R.string.enter_phone_number)
            }

            if (userPhoneNumber.text.toString().length < 10) {
                inputPhoneNumber.error = getString(R.string.phone_number_validation_digit)
            }

            if (userPasswordInputText.text.toString().isEmpty()) {
                userInputPassword.error = getString(R.string.enter_password)
            }

            if (userPasswordInputText.text.toString().length < 8) {
                userInputPassword.error = getString(R.string.password_maximum_contain_8_character)
            }

            if (nameInputText.text.toString().isNotEmpty() && userEmailInputText.text.toString().matches(emailPattern.toRegex()) && userPhoneNumber.text.toString().length >= 10 && userPasswordInputText.text.toString().isNotEmpty()) {
                Toast.makeText(applicationContext, getString(R.string.account_created), Toast.LENGTH_SHORT).show()
                val intent = Intent(applicationContext, SignInActivity::class.java)
                startActivity(intent)
                finish()
            } else {
                Log.d(getString(R.string.validation),getString(R.string.account_is_not_created))
            }
        }
    }

    private fun spannableText() {
        val spannableString = SpannableString(getString(R.string.already_have_an_account))
        val clickableSpan: ClickableSpan = object : ClickableSpan() {
            override fun onClick(p0: View) {
                val intent = Intent(applicationContext, SignInActivity::class.java)
                startActivity(intent)
                finish()
            }

            @SuppressLint("ResourceAsColor")
            @RequiresApi(Build.VERSION_CODES.M)
            override fun updateDrawState(ds: TextPaint) {
                super.updateDrawState(ds)
                ds.color = ContextCompat.getColor(applicationContext, R.color.spannableStringColor)
                ds.isUnderlineText = false
            }
        }
        spannableString.setSpan(clickableSpan, 25, 32, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        textDoNotHaveAccount.setText(spannableString, TextView.BufferType.SPANNABLE)
        textDoNotHaveAccount.movementMethod = LinkMovementMethod.getInstance()
    }
}