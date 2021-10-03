package com.astery.elegionapp.ui.views.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.VERTICAL
import com.astery.elegionapp.R
import com.astery.elegionapp.architecture.App
import com.astery.elegionapp.data_source.local.database.db_utils.LocalLoadable
import com.astery.elegionapp.databinding.FragmentRequestsBinding
import com.astery.elegionapp.ui.adapters.RequestAdapter
import com.astery.elegionapp.ui.adapters.VacationAdapter
import com.astery.elegionapp.ui.adapters.forms_adapter.EditTextElement
import com.astery.elegionapp.ui.adapters.forms_adapter.FormsAdapter
import com.astery.elegionapp.ui.adapters.forms_adapter.LongEditTextElement
import com.astery.elegionapp.ui.adapters.forms_adapter.SpinnerElement
import com.astery.elegionapp.ui.adapters.units.Request
import com.astery.elegionapp.ui.adapters.units.Vacation
import com.astery.elegionapp.ui.viewmodels.VacationViewModel
import com.astery.elegionapp.ui.views.XFragment
import com.astery.elegionapp.ui.views.utils.BlockableBundle
import com.astery.elegionapp.ui.views.utils.FormDialogue
import java.util.ArrayList

/** */
class VacationFragment : XFragment() {

    private lateinit var thisBinding:FragmentRequestsBinding

    private val viewModel: VacationViewModel by viewModels()


    private lateinit var requestAdapter: RequestAdapter
    private lateinit var disappearAdapter: VacationAdapter
    private lateinit var plannedAdapter: VacationAdapter

    private var blockable: BlockableBundle = BlockableBundle()


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
        // TODO - make them work
        thisBinding.planning.showAllPlanning.visibility = GONE
        thisBinding.dissapears.showAllDissapeared.visibility = GONE

        thisBinding.requestVacation.setOnClickListener {
            val view = FormDialogue(this,
                R.layout.form, getString(R.string.vacation_alert_vacation))

            val adapter = FormsAdapter(this)
            adapter.addItemForBranch(SpinnerElement(viewModel.getVacationOptions(), this, "option"))
                .addItem(EditTextElement("Дата выхода с работы", this, "start"))
                .addItem(EditTextElement("Что случилось?", this, "ill"), 2)
                .addItem(EditTextElement("Что случилось?", this, "ill"), 3)
                .addItem(EditTextElement("Дата возвращения на работу", this, "end"))
                .addItem(EditTextElement("Контакт срочной связи", this, "contact"))
                .addItem(LongEditTextElement("К кому обращаться во время отсутствия", this, "changer"))
                .draw(0)

            blockable.addView(view.view.findViewById(R.id.accept), "load")
            blockable.addView(view.view.findViewById(R.id.attach), "load")

            view.view.findViewById<ViewGroup>(R.id.elements).addView(adapter.form)
            view.view.findViewById<View>(R.id.accept).setOnClickListener {
                blockable.lock(true, "load")

                viewModel.sendVocation(adapter.getResult(), object:LocalLoadable{
                    override fun onCompleteListener() {
                        blockable.lock(false, "load")
                        view.close()
                    }

                    override fun onErrorListener() {
                        blockable.lock(false, "load")
                        view.close()
                    }
                }) }

            view.view.findViewById<View>(R.id.exit).setOnClickListener {
                view.close()
            }

        }
        thisBinding.requests.requestRequest.setOnClickListener {
            val view = FormDialogue(this,
                R.layout.form, getString(R.string.vacation_alert_vacation))

            val adapter = FormsAdapter(this)
            adapter.addItem(SpinnerElement(viewModel.getRequestOptions(), this, "type"))
                .addItem(EditTextElement("Статья расходов", this, "spendings"))
                .addItem(LongEditTextElement("Ценность приобретения", this, "value"))
                .draw(0)

            blockable.addView(view.view.findViewById(R.id.accept), "load")
            blockable.addView(view.view.findViewById(R.id.attach), "load")

            view.view.findViewById<ViewGroup>(R.id.elements).addView(adapter.form)
            view.view.findViewById<View>(R.id.accept).setOnClickListener {
                blockable.lock(true, "load")

                viewModel.sendVocation(adapter.getResult(), object:LocalLoadable{
                    override fun onCompleteListener() {
                        blockable.lock(false, "load")
                        view.close()
                    }

                    override fun onErrorListener() {
                        blockable.lock(false, "load")
                        view.close()
                    }
                }) }

            view.view.findViewById<View>(R.id.exit).setOnClickListener {
                view.close()
            }

        }
    }

    override fun onBackPressed(): Boolean {
        return false
    }

    override fun getTitle(): String? {
        return getString(R.string.title_vacation)
    }
}