package swu.cp316.cafe

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class MyAdapter(val context: Context,private val photoItems: IntArray,
                private val items: Array<String>,
                private val place: Array<String>,
                private val des: Array<String>): RecyclerView.Adapter<ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.row,parent,false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return items.size
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindImgView.setImageResource(photoItems[position])
        holder.bindTextValue!!.text = items[position]
        holder.bindTextDes!!.text = place[position]



        holder.itemView.setOnClickListener {
            val posi: String = items[position]
            //Toast.makeText(context,posi,Toast.LENGTH_LONG).show()

            var intent = Intent(context,ThirdActivity::class.java)
            //var intent2 = Intent(context,Fragment1::class.java)
            intent.putExtra("Image",photoItems[position])
            intent.putExtra("Title",items[position])
            intent.putExtra("Place",place[position])
            intent.putExtra("Des",des[position])
            context.startActivity(intent)

        }

    }


}

class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
    var bindImgView: ImageView = itemView.findViewById(R.id.imageView)
    val bindTextValue: TextView? = itemView.findViewById(R.id.txtTitle)
    val bindTextDes: TextView? = itemView.findViewById(R.id.txtDescription)

}