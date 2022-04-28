package swu.cp316.cafe

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import swu.cp316.cafe.db.UserEntity

class RecyclerviewAdapter(val listener :RowClickListener ): RecyclerView.Adapter<RecyclerviewAdapter.MyViewHolder>() {

    var items = ArrayList<UserEntity>()
    fun setListData(data: ArrayList<UserEntity>){
        this.items = data
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerviewAdapter.MyViewHolder {
        val inflater = LayoutInflater.from(parent.context).inflate(R.layout.recyclerview_row,parent,false)
        return MyViewHolder(inflater,listener)
    }

    override fun getItemCount(): Int {
       return items.size
    }

    override fun onBindViewHolder(holder: RecyclerviewAdapter.MyViewHolder, position: Int) {
        holder.bind(items[position])
    }

    class MyViewHolder(view : View,val listener: RowClickListener): RecyclerView.ViewHolder(view){

        val tvName = view.findViewById<TextView>(R.id.tvName)
        val tvReview = view.findViewById<TextView>(R.id.tvReview)

        fun bind(data: UserEntity){
            tvName.text = data.name
            tvReview.text = data.review

        }
    }

    interface RowClickListener{
        fun onDeleteClickListener(user: UserEntity)
        fun onItemClickListener(user: UserEntity)
    }



}