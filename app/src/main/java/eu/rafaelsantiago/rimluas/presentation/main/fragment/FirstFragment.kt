package eu.rafaelsantiago.rimluas.presentation.main.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import eu.rafaelsantiago.rimluas.R
import eu.rafaelsantiago.rimluas.domain.enum.LuasStopEnum
import eu.rafaelsantiago.rimluas.presentation.main.adapter.LuasRecyclerViewAdapter
import eu.rafaelsantiago.rimluas.presentation.main.viewmodel.MainViewModel

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {
    private lateinit var viewModel: MainViewModel
    private lateinit var recyclerView: RecyclerView
    private lateinit var luasRecyclerViewAdapter: LuasRecyclerViewAdapter
    private lateinit var viewManager: RecyclerView.LayoutManager

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_first, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupTramsRecyclerView(view)
        setupViewModel()
    }

    private fun setupTramsRecyclerView(view: View) {
        viewManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        luasRecyclerViewAdapter = LuasRecyclerViewAdapter(listOf())

        recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView)?.apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = luasRecyclerViewAdapter
        }!!
    }

    private fun setupViewModel() {
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        viewModel.getStopForecast(LuasStopEnum.STILLORGAN)
        viewModel.forecastData.observe(requireActivity(), Observer { forecast ->
            activity?.title = forecast.stopName
            luasRecyclerViewAdapter.updateData(forecast.lines[0].trams)
        })
    }
}