package swu.cp316.cafe

import android.graphics.drawable.ClipDrawable.VERTICAL
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.get
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import swu.cp316.cafe.databinding.ActivityThirdBinding
import swu.cp316.cafe.db.UserEntity

class ThirdActivity : AppCompatActivity() , RecyclerviewAdapter.RowClickListener  {

    private lateinit var binding: ActivityThirdBinding
    private lateinit var recyclerviewAdapter: RecyclerviewAdapter
    private lateinit var viewModel: ThirdActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityThirdBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var intent = getIntent()
        binding.txtHead!!.setText("" + intent.getStringExtra("Title"))
        binding.txtDes1!!.setText("" + intent.getStringExtra("Des"))
        binding.imageView2!!.setImageResource(intent.getIntExtra("Image", 0))

        binding.edtName!!.setText("" + intent.getStringExtra("Title"))


        binding.recyclerView1.apply {
            layoutManager = LinearLayoutManager(this@ThirdActivity)
            recyclerviewAdapter = RecyclerviewAdapter(this@ThirdActivity)
            adapter = recyclerviewAdapter
            val divider = DividerItemDecoration(applicationContext, LinearLayout.VERTICAL)
            addItemDecoration(divider)
        }



        viewModel = ViewModelProviders.of(this).get(ThirdActivityViewModel::class.java)
        viewModel.getAllUsersObservers().observe(this, Observer {
            recyclerviewAdapter.setListData(ArrayList(it))
            recyclerviewAdapter.notifyDataSetChanged()
        })

        binding.btnReview.setOnClickListener {
            val name = binding.edtName.text.toString()
            val review = binding.edtComment.text.toString()
            val user = UserEntity(0,name, review)
            viewModel.insertUserInfo(user)

            binding.edtName.setText("" + intent.getStringExtra("Title"))
            binding.edtComment.setText("")

            Toast.makeText(this,"ขอบคุณสำหรับการแสดงความคิดเห็น",Toast.LENGTH_SHORT).show()

        }

    }

    override fun onDeleteClickListener(user: UserEntity) {
        TODO("Not yet implemented")
    }

    override fun onItemClickListener(user: UserEntity) {
        TODO("Not yet implemented")
    }



}