package com.astery.elegionapp.ui.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.astery.elegionapp.R
import com.astery.elegionapp.ui.adapters.units.RoadMap
import com.google.android.material.card.MaterialCardView
import java.util.*

class RoadMapAdapter(private var units: ArrayList<RoadMap>?, private var context:Context) :
    RecyclerView.Adapter<RoadMapAdapter.ViewHolder>() {
    private var blockListener: BlockListener? = null

    fun setUnits(units: ArrayList<RoadMap>?) {
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
            .inflate(R.layout.unit_develoment_roadmap, viewGroup, false)
        return ViewHolder(view)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val unit = units!![position]

        holder.salary.text = unit.salary.toString()
        holder.occupation.text = unit.occupation

        if (unit.date != null){
            holder.date.text = context.resources.getString(R.string.development_start_work, unit.date.get(Calendar.DAY_OF_MONTH),
            unit.date.get(Calendar.MONTH), unit.date.get(Calendar.YEAR))
        }

        when (unit.state){
            -1 -> {
                holder.date.visibility = GONE
                holder.card.setCardBackgroundColor(context.resources.getColor(R.color.development_roadMap_old))
                holder.salary.setTextColor(context.resources.getColor(R.color.black))
                holder.occupation.setTextColor(context.resources.getColor(R.color.black))
            }
            1 ->{
                holder.date.visibility = GONE
                holder.card.setBackgroundColor(context.resources.getColor(R.color.development_roadMap_new))
            }
        }

    }

    override fun getItemCount(): Int {
        return if (units == null) 0 else units!!.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val icon: ImageView = itemView.findViewById(R.id.icon)
        val occupation: TextView = itemView.findViewById(R.id.occupation)
        val date: TextView = itemView.findViewById(R.id.date)
        val salary: TextView = itemView.findViewById(R.id.salary)
        val card: MaterialCardView = itemView.findViewById(R.id.card)

        init {
            itemView.setOnClickListener { v: View? ->
                if (blockListener != null) {
                    blockListener!!.onClick(adapterPosition)
                }
            }
        }
    }
}