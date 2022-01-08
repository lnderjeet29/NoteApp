package com.inderjeet.noteapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.widget.PopupMenu
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.inderjeet.noteapp.R
import com.inderjeet.noteapp.databinding.ActivityItemContainBinding
import com.inderjeet.noteapp.model.Note

class Adapter(val onDelete:(Int)->Unit,val onEdit:(Note)->Unit, var onView:(Note)->Unit): ListAdapter<Note, Adapter.Viewholder>(NoteDiffUtil) {

    object NoteDiffUtil:DiffUtil.ItemCallback<Note>() {
        override fun areItemsTheSame(oldItem: Note, newItem: Note): Boolean {
           return oldItem.id==newItem.id
        }

        override fun areContentsTheSame(oldItem: Note, newItem: Note): Boolean {
            return oldItem==newItem
        }

    }

    inner class Viewholder(val binding: ActivityItemContainBinding):RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Viewholder =
        Viewholder(ActivityItemContainBinding.inflate(LayoutInflater.from(parent.context),parent,false))

    override fun onBindViewHolder(holder: Viewholder, position: Int) {

        val note=getItem(position)
        holder.binding.apply {
            tvtitle.text=note.title
            tvDes.text=note.description

            root.setOnLongClickListener {
                val popupMenu=PopupMenu(root.context,root)
                popupMenu.setForceShowIcon(true)
                popupMenu.inflate(R.menu.menu_option)
                popupMenu.setOnMenuItemClickListener {
                    when(it.itemId){
                        R.id.delete->{
                            onDelete.invoke(note.id)
                        }
                        R.id.Edit_txt->{
                            onEdit.invoke(note)
                        }
                        R.id.show->{
                            onView.invoke(note)
                        }
                    }
                    return@setOnMenuItemClickListener true
                }
                popupMenu.show()
                return@setOnLongClickListener true
            }
        }
    }
}