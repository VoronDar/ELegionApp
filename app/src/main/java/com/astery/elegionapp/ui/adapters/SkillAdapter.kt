package com.astery.elegionapp.ui.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.astery.elegionapp.R
import com.astery.elegionapp.ui.adapters.units.Skill
import java.util.*

class SkillAdapter(private var units: ArrayList<Skill>?, private var context:Context) :
    RecyclerView.Adapter<SkillAdapter.ViewHolder>() {
    private var blockListener: BlockListener? = null

    fun setUnits(units: ArrayList<Skill>?) {
        this.units = units
        notifyDataSetChanged()
    }

    fun setBlockListener(block_listener: BlockListener?) {
        blockListener = block_listener
    }

    interface BlockListener {
        fun onClick(position: Int)
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.unit_development_skills, viewGroup, false)
        return ViewHolder(view)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val unit = units!![position]

        holder.text.text = unit.title

    }

    override fun getItemCount(): Int {
        return if (units == null) 0 else units!!.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val text: TextView = itemView.findViewById(R.id.title)

        init {
            itemView.setOnClickListener { v: View? ->
                if (blockListener != null) {
                    blockListener!!.onClick(adapterPosition)
                }
            }
        }
    }
}