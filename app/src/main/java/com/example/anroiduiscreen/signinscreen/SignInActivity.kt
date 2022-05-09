package com.example.anroiduiscreen.signinscreen

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.text.Editable
import android.text.SpannableString
import android.text.Spanned
import android.text.TextPaint
import android.text.TextWatcher
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.util.Patterns
import android.view.View
import android.view.WindowManager
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.anroiduiscreen.POST
import com.example.anroiduiscreen.R
import com.example.anroiduiscreen.ResponseCallback
import com.example.anroiduiscreen.TWOHUNDRED
import com.example.anroiduiscreen.URL
import com.example.anroiduiscreen.homescreen.HomeScreenActivity
import com.example.anroiduiscreen.requestApiCall
import com.example.anroiduiscreen.signupscreen.SignUpActivity
import kotlinx.android.synthetic.main.activity_sign_in.btnSignIn
import kotlinx.android.synthetic.main.activity_sign_in.emailInputText
import kotlinx.android.synthetic.main.activity_sign_in.inputEmail
import kotlinx.android.synthetic.main.activity_sign_in.inputPassword
import kotlinx.android.synthetic.main.activity_sign_in.passwordInputText
import kotlinx.android.synthetic.main.activity_sign_in.textDoNotHaveAccount
import org.json.JSONObject
import java.net.URL

class SignInActivity : AppCompatActivity() {

    lateinit var progressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)
        window.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
        progressBar = findViewById(R.id.loadingDialog)
        signInClick()
        spannableText()

        emailInputText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable) {
                inputEmail.error = null
            }
        })

        passwordInputText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable) {
                inputPassword.error = null
            }
        })

    }

    private fun simpleApiCall() {
        val url = URL(URL)
        val jsonObject = JSONObject()
        jsonObject.apply {
            put(getString(R.string.email), emailInputText.text.toString())
            put(getString(R.string.password), passwordInputText.text.toString())
        }

        Thread(Runnable {
            try {
                requestApiCall(jsonObject, url, POST, TWOHUNDRED, object : ResponseCallback {
                    override fun onSuccess(output: String) {
                        runOnUiThread {
                            progressBar.visibility = View.GONE
                            Toast.makeText(this@SignInActivity, "Success", Toast.LENGTH_SHORT).show()
                            val intent = Intent(this@SignInActivity, HomeScreenActivity::class.java)
                            startActivity(intent)
                        }
                    }

                    override fun onFailure(responseCode: Int, output: String) {
                        runOnUiThread {
                            Toast.makeText(this@SignInActivity, "failure", Toast.LENGTH_SHORT).show()
                            progressBar.visibility = View.GONE
                        }
                    }
                })
            } catch (exception: Exception) {
                exception.printStackTrace()
                progressBar.visibility = View.GONE
            }
        }).start()
    }

    private fun signInClick() {
        btnSignIn.setOnClickListener {
            if (emailInputText.text.toString().isEmpty() && passwordInputText.text.toString().isEmpty()) {
                inputEmail.error = getString(R.string.enter_email_address)
                inputPassword.error = getString(R.string.enter_password)
                progressBar.visibility = View.INVISIBLE
            } else if (emailInputText.text.toString().isEmpty() && passwordInputText.text.toString().isNotEmpty()) {
                emailInputText.error = getString(R.string.enter_email_address)
            } else if (emailInputText.text.toString().isNotEmpty() && passwordInputText.text.toString().isEmpty()) {
                inputPassword.error = getString(R.string.enter_password)
            } else if (!Patterns.EMAIL_ADDRESS.matcher(emailInputText.text.toString()).matches()) {
                inputEmail.error = getString(R.string.enter_proper_email_address)
            } else if (passwordInputText.text.toString().length < 6) {
                inputPassword.error = getString(R.string.password_maximum_contain_6_character)
            } else if (emailInputText.text.toString().isNotEmpty() && passwordInputText.text.toString().isNotEmpty()) {
                simpleApiCall()
                progressBar.visibility = View.VISIBLE
            }
        }
    }

    private fun spannableText() {
        val spannableString = SpannableString(getString(R.string.do_not_have_not_an_account_sign_up))
        val clickableSpan: ClickableSpan = object : ClickableSpan() {
            override fun onClick(p0: View) {
                val intent = Intent(applicationContext, SignUpActivity::class.java)
                startActivity(intent)
            }

            @SuppressLint("ResourceAsColor")
            @RequiresApi(Build.VERSION_CODES.M)
            override fun updateDrawState(ds: TextPaint) {
                super.updateDrawState(ds)
                ds.color = ContextCompat.getColor(applicationContext, R.color.spannableStringColor)
                ds.isUnderlineText = false
            }
        }
        spannableString.setSpan(clickableSpan, 24, 31, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        textDoNotHaveAccount.setText(spannableString, TextView.BufferType.SPANNABLE)
        textDoNotHaveAccount.movementMethod = LinkMovementMethod.getInstance()
    }
}