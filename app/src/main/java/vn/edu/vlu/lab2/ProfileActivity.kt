package vn.edu.vlu.lab2

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import vn.edu.vlu.lab2.databinding.ActivityProfileBinding

class ProfileActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProfileBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.root.applySystemBarsPadding()

        binding.tvValueEmail.text = intent.getStringExtra(EXTRA_EMAIL).orEmpty()
        binding.btnEdit.setOnClickListener {
            Toast.makeText(this, R.string.msg_edit_coming_soon, Toast.LENGTH_SHORT).show()
        }
        binding.btnLogout.setOnClickListener { finish() }   // back to the Login screen
    }

    companion object {
        const val EXTRA_EMAIL = "extra_email"
    }
}
