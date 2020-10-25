package eu.rafaelsantiago.rimluas.presentation.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import eu.rafaelsantiago.rimluas.R
import eu.rafaelsantiago.rimluas.domain.enum.LuasStopEnum
import eu.rafaelsantiago.rimluas.presentation.viewmodel.MainViewModel

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {
    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_first, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupViewModel()

        viewModel.getStopForecast(LuasStopEnum.STILLORGAN.abbrev)
        viewModel.forecastData.observe(requireActivity(), Observer { forecast ->
            view.findViewById<TextView>(R.id.textview_first).text = """ 
                $forecast
                ===
                ${forecast.lines[0].name}
                ===
                ${forecast.lines[0].trams[0]}
                ===
                ${forecast.lines[0].trams[0].dueMins}
            """.trimIndent()
        })
    }

    private fun setupViewModel() {
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
    }
}