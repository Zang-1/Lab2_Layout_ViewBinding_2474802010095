package vn.edu.vlu.lab2

import android.content.Intent
import android.os.Bundle
import android.text.method.PasswordTransformationMethod
import android.util.Patterns
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import vn.edu.vlu.lab2.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.root.applySystemBarsPadding()

        setupUI()
    }

    private fun setupUI() {
        binding.btnLogin.setOnClickListener { handleLogin() }

        binding.tvForgotPassword.setOnClickListener {
            Toast.makeText(this, R.string.msg_forgot_password, Toast.LENGTH_SHORT).show()
        }

        binding.cbShowPassword.setOnCheckedChangeListener { _, isChecked ->
            binding.edtPassword.transformationMethod =
                if (isChecked) null                                  // show characters
                else PasswordTransformationMethod.getInstance()
            binding.edtPassword.setSelection(binding.edtPassword.length()) // keep cursor at the end
        }
    }

    private fun handleLogin() {
        // TextInputEditText.text is nullable => use ?. and orEmpty()
        val email = binding.edtEmail.text?.toString().orEmpty().trim()
        val password = binding.edtPassword.text?.toString().orEmpty().trim()

        // Clear errors from the previous attempt
        binding.tilEmail.error = null
        binding.tilPassword.error = null

        when {
            email.isEmpty() || password.isEmpty() -> {
                Toast.makeText(this, R.string.msg_missing_info, Toast.LENGTH_SHORT).show()
                binding.tvStatus.setText(R.string.status_missing)
            }
            !Patterns.EMAIL_ADDRESS.matcher(email).matches() ->
                binding.tilEmail.error = getString(R.string.err_email_invalid)
            password.length < 6 ->
                binding.tilPassword.error = getString(R.string.err_password_short)
            else -> {
                // MOCK authentication: no server call yet. Real authentication comes in later labs.
                binding.tvStatus.text = getString(R.string.status_login_ok, email)
                val intent = Intent(this, ProfileActivity::class.java)
                intent.putExtra(ProfileActivity.EXTRA_EMAIL, email)
                startActivity(intent)
            }
        }
    }
}
