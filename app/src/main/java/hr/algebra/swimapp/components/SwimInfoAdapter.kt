package hr.algebra.swimapp.components

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import hr.algebra.swimapp.R
import hr.algebra.swimapp.model.SwimInfo
import kotlinx.android.synthetic.main.swim_info_card.view.*

class SwimInfoAdapter(private val swimInfoList: List<SwimInfo>) : RecyclerView.Adapter<SwimInfoAdapter.SwimInfoViewHolder>(){

    inner class SwimInfoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val dayOfWeek: TextView = itemView.tvDayOfWeek
        val lapCount: TextView = itemView.tvLapCount
        val time: TextView = itemView.tvTime
        val distance: TextView = itemView.tvDistance
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SwimInfoViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.swim_info_card, parent, false)
        return SwimInfoViewHolder(itemView)
    }

    override fun getItemCount(): Int = swimInfoList.size

    override fun onBindViewHolder(holder: SwimInfoViewHolder, position: Int) {
        val currentInfo = swimInfoList[position]
        holder.dayOfWeek.text = currentInfo.dayOfWeek
        holder.lapCount.text = if(currentInfo.laps != 1) "${currentInfo.laps} laps" else "${currentInfo.laps} lap"
        holder.time.text = currentInfo.totalTime.toString()
        holder.distance.text = "${currentInfo.distance} m"
    }
}