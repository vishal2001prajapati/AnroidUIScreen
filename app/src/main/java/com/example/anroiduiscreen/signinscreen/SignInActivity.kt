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
import android.util.Log
import android.view.View
import android.view.WindowManager
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.anroiduiscreen.R
import com.example.anroiduiscreen.homescreen.HomeScreenActivity
import com.example.anroiduiscreen.signupscreen.SignUpActivity
import kotlinx.android.synthetic.main.activity_sign_in.btnSignIn
import kotlinx.android.synthetic.main.activity_sign_in.emailInputText
import kotlinx.android.synthetic.main.activity_sign_in.inputEmail
import kotlinx.android.synthetic.main.activity_sign_in.inputPassword
import kotlinx.android.synthetic.main.activity_sign_in.passwordInputText
import kotlinx.android.synthetic.main.activity_sign_in.textDoNotHaveAccount
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.net.HttpURLConnection
import java.net.URL

class SignInActivity : AppCompatActivity() {

    private val emailPattern by lazy { "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+" }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)
        window.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
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

        btnSignIn.setOnClickListener {
            Thread(Runnable {
                try {
                    sendPostRequest(emailInputText.text.toString(),passwordInputText.text.toString())
                } catch (exception: Exception) {
                    exception.printStackTrace()
                }
            }).start()
        }

    }

    private fun sendPostRequest(email: String, password: String) {

        val url = URL("https://6283714992a6a5e46223ee72.mockapi.io/api/v1/createUser")
        val jsonObject = JSONObject()
        jsonObject.apply {
            put("email", email)
            put("password", password)
        }

        with(url.openConnection() as HttpURLConnection) {
            requestMethod = "POST"
            setRequestProperty("Content-Type", "application/json")
            val outputStreamWriter = OutputStreamWriter(outputStream)
            outputStreamWriter.apply {
                write(jsonObject.toString())
                flush()
            }
            Log.d("URL", "$url")
            Log.d("jsonObject", "$jsonObject")
            Log.d("Response Code", "$responseCode")

            BufferedReader(InputStreamReader(inputStream)).use {
                val response = StringBuffer()
                var inputLine = it.readLine()
                while (inputLine != null) {
                    response.append(inputLine)
                    inputLine = it.readLine()
                }
                Log.d("response", "$response")
            }
        }
    }

    private fun signInClick() {




        btnSignIn.setOnClickListener {
            if (emailInputText.text.toString().isEmpty() && passwordInputText.text.toString().isEmpty()) {
                inputEmail.error = getString(R.string.enter_email_address)
                inputPassword.error = getString(R.string.enter_password)
            } else if (emailInputText.text.toString().isEmpty() && passwordInputText.text.toString().isNotEmpty()) {
                emailInputText.error = getString(R.string.enter_email_address)
            } else if (emailInputText.text.toString().isNotEmpty() && passwordInputText.text.toString().isEmpty()) {
                inputPassword.error = getString(R.string.enter_password)
            } else if (passwordInputText.text.toString().length < 6) {
                inputPassword.error = getString(R.string.password_maximum_contain_8_character)
            } else if (emailInputText.text.toString().isNotEmpty() && passwordInputText.text.toString().isNotEmpty()) {
                Toast.makeText(this, getString(R.string.sign_in), Toast.LENGTH_LONG).show()
                val intent = Intent(applicationContext, HomeScreenActivity::class.java)
                startActivity(intent)
            }

            if (emailInputText.text.toString().matches(emailPattern.toRegex())) {
                Log.d(getString(R.string.email_address), getString(R.string.perfect_email))
            } else {
                inputEmail.error = getString(R.string.enter_proper_email_address)
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