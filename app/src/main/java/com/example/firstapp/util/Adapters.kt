package com.example.firstapp.util

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.firstapp.R
import com.example.firstapp.model.Categoria
import com.example.firstapp.model.Produto
import com.example.firstapp.model.User


class UserAdapter(private val users: MutableList<User>): RecyclerView.Adapter<UserListItemHolder> (){
//    var list = listOf<User>()
//        set(value) {
//            field = value
//            notifyDataSetChanged()
//        }


    override fun getItemCount() = users.size

    override fun onBindViewHolder(holder: UserListItemHolder, position: Int) {
        val item = users[position]

        holder.name.text = item.name
        holder.login.text = item.login
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserListItemHolder {
        val li = LayoutInflater.from(parent.context)
        val view = li.inflate(R.layout.user_list_item, parent, false)
        return  UserListItemHolder(view)
    }
}

class CategoriaAdapter(private val categoria: MutableList<Categoria>): RecyclerView.Adapter<CategoriaListItemHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoriaListItemHolder {
        val li = LayoutInflater.from(parent.context)
        val view = li.inflate(R.layout.categoria_list_item, parent, false)
        return CategoriaListItemHolder(view)
    }

    override fun onBindViewHolder(holder: CategoriaListItemHolder, position: Int) {
        val item = categoria[position]

        holder.codCategoria.text = item.codCategoria.toString()
        holder.descCategoria.text = item.descCategoria.toString()
    }

    override fun getItemCount() = categoria.size

}

class ProdutoAdapter(private  val produtos: MutableList<Produto>): RecyclerView.Adapter<ProdutoListItemHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProdutoListItemHolder {
        val li = LayoutInflater.from(parent.context)
        val view = li.inflate(R.layout.produto_list_item, parent, false)
        return ProdutoListItemHolder(view)
    }

    override fun onBindViewHolder(holder: ProdutoListItemHolder, position: Int) {
        val item = produtos[position]

        holder.descProduto.text = item.descProduto
        holder.descCategoria.text = item.categoria?.descCategoria ?: ""
        val img = item.img
        if (img != null)
        {
            val bitmap = BitmapFactory.decodeByteArray(img, 0, img.size)
            holder.img.setImageBitmap(bitmap)
        }
    }

    override fun getItemCount() = produtos.size

}

class CustomSpinAdapter(context: Context, private val textViewResourceId: Int, private val values: MutableList<Categoria>) : ArrayAdapter<Categoria>(context, textViewResourceId, values) {
    override fun getCount() = values.size

    override fun getItem(position: Int): Categoria {
        return values[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    // And the "magic" goes here
    // This is for the "passive" state of the spinner
    fun getVieww(position: Int, convertView: View?, parent: ViewGroup?): View {
        // I created a dynamic TextView here, but you can reference your own  custom layout for each spinner item
        val label = super.getView(position, convertView, parent!!) as TextView
        label.setTextColor(Color.BLACK)
        // Then you can get the current item using the values array (Users array) and the current position
        // You can NOW reference each method you has created in your bean object (User class)
        label.text = "${values[position].codCategoria} - ${values[position].descCategoria}"

        // And finally return your dynamic (or custom) view for each spinner item
        return label
    }

    // And here is when the "chooser" is popped up
    // Normally is the same view, but you can customize it if you want
    override fun getDropDownView(
        position: Int, convertView: View?,
        parent: ViewGroup?
    ): View {
        val label = super.getDropDownView(position, convertView, parent) as TextView
        label.setTextColor(Color.BLACK)
        label.text = "${values[position].codCategoria} - ${values[position].descCategoria}"
        return label
    }
}