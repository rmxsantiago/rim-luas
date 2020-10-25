package eu.rafaelsantiago.rimluas.presentation.main.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import eu.rafaelsantiago.rimluas.R
import eu.rafaelsantiago.rimluas.domain.model.Tram

class LuasRecyclerViewAdapter(private var listOfTrams: List<Tram>): RecyclerView.Adapter<LuasRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.adapter_tram_row, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount() = listOfTrams.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bindView(listOfTrams[position])

    fun updateData(updatedListOfTrams: List<Tram>) {
        listOfTrams = updatedListOfTrams
        notifyDataSetChanged()
    }

    class ViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        fun bindView(tram: Tram) {
            view.findViewById<TextView>(R.id.txtDestination).text = tram.destination
            view.findViewById<TextView>(R.id.txtDueMinutes).text = tram.dueMins
        }
    }

}