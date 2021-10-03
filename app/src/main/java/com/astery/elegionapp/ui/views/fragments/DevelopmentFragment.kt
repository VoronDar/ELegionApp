package com.astery.elegionapp.ui.views.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.HORIZONTAL
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.astery.elegionapp.R
import com.astery.elegionapp.architecture.App
import com.astery.elegionapp.data_source.local.database.db_utils.LocalLoadable
import com.astery.elegionapp.databinding.FragmentDevelopmentBinding
import com.astery.elegionapp.ui.adapters.*
import com.astery.elegionapp.ui.adapters.forms_adapter.*
import com.astery.elegionapp.ui.adapters.units.RoadMap
import com.astery.elegionapp.ui.adapters.units.Skill
import com.astery.elegionapp.ui.adapters.units.Task
import com.astery.elegionapp.ui.viewmodels.DevelopmentViewModel
import com.astery.elegionapp.ui.views.XFragment
import com.astery.elegionapp.ui.views.utils.BlockableBundle
import com.astery.elegionapp.ui.views.utils.FormDialogue
import java.util.*

/** */
class DevelopmentFragment : XFragment() {

    private lateinit var thisBinding:FragmentDevelopmentBinding

    private val viewModel: DevelopmentViewModel by viewModels()


    private lateinit var roadMapAdapter: RoadMapAdapter
    private lateinit var goalAdapter: TasksAdapter
    private lateinit var taskAdapter: TasksAdapter
    private lateinit var skillsAdapter: SkillAdapter

    private var blockable: BlockableBundle = BlockableBundle()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        bind = FragmentDevelopmentBinding.inflate(inflater, container, false)
        thisBinding = binding as FragmentDevelopmentBinding
        return binding.root
    }




    override fun prepareAdapters(){
        this.roadMapAdapter = RoadMapAdapter(null, requireContext())
        thisBinding.roadmap.rvRoadmap.adapter = this.roadMapAdapter
        thisBinding.roadmap.rvRoadmap.layoutManager = LinearLayoutManager(requireContext(), HORIZONTAL, false)

        this.skillsAdapter = SkillAdapter(null, requireContext())
        thisBinding.skills.rvSkills.adapter = skillsAdapter
        thisBinding.skills.rvSkills.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.HORIZONTAL);

        this.goalAdapter = TasksAdapter(this, thisBinding.goals.goalsElements)
        this.goalAdapter.setBlockListener(object:TasksAdapter.BlockListener{
            override fun onClick(position: Int) {

                val view = FormDialogue(
                    this@DevelopmentFragment,
                    R.layout.form, getString(R.string.vacation_alert_vacation))

                val adapter = FormsAdapter(this@DevelopmentFragment)
                adapter.addItem(NotEditableEditTextElement(goalAdapter.units[position].title, this@DevelopmentFragment, "goal"))
                    .addItem(LongEditTextElement("Опишите почему считаете, что цель выполнена", this@DevelopmentFragment, "description"))
                    .draw(0)

                blockable.addView(view.view.findViewById(R.id.accept), "load")
                blockable.addView(view.view.findViewById(R.id.attach), "load")

                view.view.findViewById<ViewGroup>(R.id.elements).addView(adapter.form)
                view.view.findViewById<View>(R.id.accept).setOnClickListener {
                    blockable.lock(true, "load")

                    viewModel.sendGoal(adapter.getResult(), object:LocalLoadable{
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
        })

        this.taskAdapter = TasksAdapter(this, thisBinding.tesks.tasksElements)
        this.taskAdapter.setBlockListener(object:TasksAdapter.BlockListener{
            override fun onClick(position: Int) {

                val view = FormDialogue(
                    this@DevelopmentFragment,
                    R.layout.form, getString(R.string.vacation_alert_vacation))

                val adapter = FormsAdapter(this@DevelopmentFragment)
                adapter.addItem(NotEditableEditTextElement(goalAdapter.units[position].title, this@DevelopmentFragment, "task"))
                    .addItem(LongEditTextElement("Опишите почему считаете, что задача выполнена", this@DevelopmentFragment, "description"))
                    .draw(0)

                blockable.addView(view.view.findViewById(R.id.accept), "load")
                blockable.addView(view.view.findViewById(R.id.attach), "load")

                view.view.findViewById<ViewGroup>(R.id.elements).addView(adapter.form)
                view.view.findViewById<View>(R.id.accept).setOnClickListener {
                    blockable.lock(true, "load")

                    viewModel.sendTask(adapter.getResult(), object:LocalLoadable{
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
        })


    }

    override fun setViewModelListeners() {
        viewModel.repository = (requireActivity().application as App).container.repository
        viewModel.getData()

        viewModel.roadMap.observe(viewLifecycleOwner,
            { t -> this.roadMapAdapter.setUnits(t as ArrayList<RoadMap>) })
        viewModel.tasks.observe(viewLifecycleOwner,
            { t -> this.taskAdapter.draw(t as ArrayList<Task>) })
        viewModel.goals.observe(viewLifecycleOwner,
            { t -> this.goalAdapter.draw(t as ArrayList<Task>) })
        viewModel.skills.observe(viewLifecycleOwner,
            { t -> this.skillsAdapter.setUnits(t as ArrayList<Skill>) })

    }

    override fun setListeners() {
    }

    override fun onBackPressed(): Boolean {
        return false
    }

    override fun getTitle(): String? {
        return getString(R.string.title_development)
    }
}