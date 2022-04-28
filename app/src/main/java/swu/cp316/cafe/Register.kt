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
import swu.cp316.cafe.databinding.ActivityRegisterBinding
import swu.cp316.cafe.databinding.ActivityThirdBinding

class Register : AppCompatActivity() {

    lateinit var binding: ActivityRegisterBinding
    lateinit var email:String
    lateinit var password:String

    private lateinit var database: FirebaseDatabase
    private var mAuth: FirebaseAuth? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)


        //Firebase
        mAuth = FirebaseAuth.getInstance()
        database = FirebaseDatabase.getInstance()


        binding.btnReRegister!!.setOnClickListener {
            createAccount()
        }

        //กลับหน้าLogin
        binding.btnReLogin!!.setOnClickListener {
            backtoLogin()
        }
    }

    private fun backtoLogin() {
        val backLogin = Intent(this,Login::class.java)
        startActivity(backLogin)
    }

    override fun onStart(){
        super.onStart()
        val currentUser = mAuth!!.currentUser
        updateUI(currentUser)
    }

    private fun createAccount() {
        email =binding.txtReEmail!!.text.toString()
        password = binding.txtRePassword!!.text.toString()


        mAuth!!.createUserWithEmailAndPassword(email,password).addOnCompleteListener(this) { task ->
            if (task.isSuccessful) {
                Log.d("MyApp", "Create New User Success!")
                val user = mAuth!!.currentUser
                val databaseReference = database.reference.child("users").push()
                databaseReference.child("uid").setValue(user!!.uid)
                databaseReference.child("email").setValue(user!!.email)
                databaseReference.child("name").setValue(binding.txtName.text.toString())
                databaseReference.child("age").setValue(binding.txtAge.text.toString())
                updateUI(user)
            } else {
                Log.w("MyApp", "Failure Process!", task.exception)
                Toast.makeText(this@Register, "Authentication Fail", Toast.LENGTH_SHORT).show()
                updateUI(null)
            }
        }
    }

    private fun updateUI(user: FirebaseUser?) {
        if(user != null){
            val uid = user.uid
            val email = user.email
            Toast.makeText(this@Register,"Welcome :$email your ID is: $uid",Toast.LENGTH_SHORT).show()
            val intentSession = Intent(this,SecondActivity::class.java)

            startActivity(intentSession)
        }
    }
}