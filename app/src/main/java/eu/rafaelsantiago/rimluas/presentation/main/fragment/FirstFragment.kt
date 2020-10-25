package eu.rafaelsantiago.rimluas.presentation.main.fragment

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
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
        setHasOptionsMenu(true)
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

        viewModel.getStopForecast()
        viewModel.forecastData.observe(requireActivity(), Observer { forecast ->
            activity?.title = forecast.stopName
            val tramDirection = viewModel.getTramDirection(forecast)
            luasRecyclerViewAdapter.updateData(tramDirection.trams)
        })
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_main, menu)

        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_refresh -> {
                Snackbar.make(activity?.window?.decorView?.findViewById(android.R.id.content)!!, "Refreshing Luas forecast...", Snackbar.LENGTH_LONG).show()

                viewModel.getStopForecast()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}