package com.astery.elegionapp.ui.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.astery.elegionapp.R
import com.astery.elegionapp.ui.adapters.units.Vacation
import java.util.*

class VacationAdapter(private var units: ArrayList<Vacation>?, private var context:Context, private val isActual:Boolean) :
    RecyclerView.Adapter<VacationAdapter.ViewHolder>() {
    private var blockListener: BlockListener? = null

    fun setUnits(units: ArrayList<Vacation>?) {
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
            .inflate(R.layout.unit_request_vacation, viewGroup, false)
        return ViewHolder(view)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val unit = units!![position]

        holder.name.text = unit.name
        holder.change.text = unit.changer
        holder.date.text = dateToString(unit.until)
        holder.icon.setImageBitmap(unit.image)


    }

    /** translate calendar to subjective string*/
    private fun dateToString(cal:Calendar):String{
        // TODO - fix it
        val now = clean(Calendar.getInstance())

        Log.i("main", now.time.time.toString() +" " + clean(cal).time.time)
        if (now.time.time ==  clean(cal).time.time + 1){
            return context.resources.getString(R.string.vacation_tomorrow, getVacation());
        }

        if (now.time.time + 30 > clean(cal).time.time ){
            return context.resources.getString(R.string.vacation_soon, getVacation(), clean(cal).time.time-now.time.time);
        }
        return context.resources.getString(R.string.vacation_not_soon, getVacation())
    }


    /** prepare calendar to be converted to date to be converted to long in sql */
    private fun clean(cal: Calendar): Calendar {
        cal[Calendar.HOUR_OF_DAY] = cal.getActualMinimum(Calendar.HOUR_OF_DAY)
        cal[Calendar.MINUTE] = cal.getActualMinimum(Calendar.MINUTE)
        cal[Calendar.SECOND] = cal.getActualMinimum(Calendar.SECOND)
        cal[Calendar.MILLISECOND] = cal.getActualMinimum(Calendar.MILLISECOND)
        return cal
    }

    private fun getVacation():String{
        if (isActual) return context.getString(R.string.vacation_actual)
        return context.getString(R.string.vacation_plan)
    }

    override fun getItemCount(): Int {
        return if (units == null) 0 else units!!.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val icon: ImageView = itemView.findViewById(R.id.icon)
        val name: TextView = itemView.findViewById(R.id.name)
        val date: TextView = itemView.findViewById(R.id.vacationTime)
        val change: TextView = itemView.findViewById(R.id.vacationChange)

        init {
            itemView.setOnClickListener { v: View? ->
                if (blockListener != null) {
                    blockListener!!.onClick(adapterPosition)
                }
            }
        }
    }
}