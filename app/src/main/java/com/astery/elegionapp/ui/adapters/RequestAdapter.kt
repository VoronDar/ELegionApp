package com.astery.elegionapp.ui.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.astery.elegionapp.R
import com.astery.elegionapp.ui.adapters.units.Request
import com.astery.elegionapp.ui.adapters.units.RequestState
import com.astery.elegionapp.ui.views.utils.SD
import java.util.*

class RequestAdapter(private var units: ArrayList<Request>?, private var context:Context) :
    RecyclerView.Adapter<RequestAdapter.ViewHolder>() {
    private var blockListener: BlockListener? = null

    fun setUnits(units: ArrayList<Request>?) {
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
            .inflate(R.layout.unit_request_requestes, viewGroup, false)
        return ViewHolder(view)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val unit = units!![position]
        holder.title.text = unit.title
        when (unit.state){
            // TODO (set drawables)
            RequestState.accepted -> SD.setDrawable(holder.icon, R.drawable.ic_elegion, context)
            RequestState.inProgress -> SD.setDrawable(holder.icon, R.drawable.ic_elegion, context)
            RequestState.wait -> SD.setDrawable(holder.icon, R.drawable.ic_elegion, context)
        }
    }

    override fun getItemCount(): Int {
        return if (units == null) 0 else units!!.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val icon: ImageView = itemView.findViewById(R.id.icon)
        val title: TextView = itemView.findViewById(R.id.title)

        init {
            itemView.setOnClickListener { v: View? ->
                if (blockListener != null) {
                    blockListener!!.onClick(adapterPosition)
                }
            }
        }
    }
}