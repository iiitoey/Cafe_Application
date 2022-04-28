package swu.cp316.cafe

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.FirebaseDatabase
import swu.cp316.cafe.databinding.ActivityLoginBinding
import swu.cp316.cafe.databinding.ActivityThirdBinding

class Login : AppCompatActivity() {

    lateinit var email:String
    lateinit var password:String
    private var mAuth: FirebaseAuth? = null
    private lateinit var database: FirebaseDatabase
    lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mAuth = FirebaseAuth.getInstance()


        binding.btnRegister.setOnClickListener {
            val intent = Intent(this,Register::class.java)
            startActivity(intent)
        }

        binding.btnLogin.setOnClickListener {
            signIn()
        }

        binding.btnNotLogin.setOnClickListener {
            val intent = Intent(this,SecondActivity::class.java)
            startActivity(intent)
        }
    }

    private fun signIn() {
        email =binding.txtReEmail!!.text.toString()
        password = binding.txtRePassword!!.text.toString()

        mAuth!!.signInWithEmailAndPassword(email,password).addOnCompleteListener(this) { task ->
            if (task.isSuccessful) {
                Log.d("MyApp", "Create New User Success!")
                val user = mAuth!!.currentUser
                updateUI(user)
            } else {
                Log.w("MyApp", "Failure Process!", task.exception)
                Toast.makeText(this@Login, "Authentication Fail", Toast.LENGTH_SHORT).show()
                updateUI(null)
            }
        }

    }

    private fun updateUI(user: FirebaseUser?) {
        if(user != null){
            val uid = user.uid
            val email = user.email
            Toast.makeText(this@Login,"Welcome :$email your ID is: $uid",Toast.LENGTH_SHORT).show()
            val intentSession = Intent(this,SecondActivity::class.java)
            startActivity(intentSession)

        }
    }
}