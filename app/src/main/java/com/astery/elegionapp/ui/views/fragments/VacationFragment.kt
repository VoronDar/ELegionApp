package com.astery.elegionapp.ui.views.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.VERTICAL
import com.astery.elegionapp.architecture.App
import com.astery.elegionapp.databinding.FragmentRequestsBinding
import com.astery.elegionapp.ui.adapters.RequestAdapter
import com.astery.elegionapp.ui.adapters.VacationAdapter
import com.astery.elegionapp.ui.adapters.units.Request
import com.astery.elegionapp.ui.adapters.units.Vacation
import com.astery.elegionapp.ui.viewmodels.VacationViewModel
import com.astery.elegionapp.ui.views.XFragment
import java.util.ArrayList

/** */
class VacationFragment : XFragment() {

    private lateinit var thisBinding:FragmentRequestsBinding

    private val viewModel: VacationViewModel by viewModels()


    private lateinit var requestAdapter: RequestAdapter
    private lateinit var disappearAdapter: VacationAdapter
    private lateinit var plannedAdapter: VacationAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        bind = FragmentRequestsBinding.inflate(inflater, container, false)
        thisBinding = binding as FragmentRequestsBinding
        return binding.root
    }




    override fun prepareAdapters(){
        this.requestAdapter = RequestAdapter(null, requireContext())
        thisBinding.requests.rvRequestes.adapter = this.requestAdapter
        thisBinding.requests.rvRequestes.layoutManager = LinearLayoutManager(requireContext(), VERTICAL, false)

        this.disappearAdapter = VacationAdapter(null, requireContext(), true)
        thisBinding.dissapears.rvDissapeared.adapter = this.disappearAdapter
        thisBinding.dissapears.rvDissapeared.layoutManager = LinearLayoutManager(requireContext(), VERTICAL, false)

        this.plannedAdapter = VacationAdapter(null, requireContext(), false)
        thisBinding.planning.rvPlanning.adapter = this.plannedAdapter
        thisBinding.planning.rvPlanning.layoutManager = LinearLayoutManager(requireContext(), VERTICAL, false)
    }

    override fun setViewModelListeners() {
        viewModel.repository = (requireActivity().application as App).container.repository
        viewModel.getData()

        viewModel.requests.observe(viewLifecycleOwner,
            { t -> this.requestAdapter.setUnits(t as ArrayList<Request>) })
        viewModel.disappeared.observe(viewLifecycleOwner,
            { t -> this.disappearAdapter.setUnits(t as ArrayList<Vacation>) })
        viewModel.planned.observe(viewLifecycleOwner,
            { t -> this.plannedAdapter.setUnits(t as ArrayList<Vacation>) })

    }

    override fun setListeners() {
    }

    override fun onBackPressed(): Boolean {
        return false
    }

    override fun getTitle(): String? {
        return "Заявки"
    }
}