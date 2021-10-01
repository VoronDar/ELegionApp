package com.astery.elegionapp.ui.views

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.astery.elegionapp.listeners.ResultListener
import com.astery.elegionapp.ui.navigation.FragmentNavController
import com.astery.elegionapp.ui.navigation.NavigationTransition
import com.astery.elegionapp.ui.navigation.ParentActivity
import com.astery.elegionapp.ui.navigation.SharedAxisTransition
import com.google.android.material.transition.MaterialSharedAxis

abstract class XFragment : Fragment() {


    protected var bind: ViewBinding? = null
    protected val binding get() = bind!!



    override fun onDestroyView() {
        super.onDestroyView()
        bind = null
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setTitle()
        setViewModelListeners()
        setListeners()
        prepareAdapters()
    }

    /** set on click listener to this view that change fragments, but it requires bundle at the moment of declaring*/
    protected fun clickToMove(view: View, type: FragmentNavController, bundle:Bundle?){
        view.setOnClickListener {
            (activity as ParentActivity).move(type, bundle)}
    }

    /** set on click listener to this view that change fragments, but it requires bundle at the moment of moving*/
    protected fun clickToMove(view: View, type: FragmentNavController, bundle:BundleGettable){
        view.setOnClickListener {
            (activity as ParentActivity).move(type, bundle.getBundle())}
    }

    /** wrap listener from viewModel to change fragment if the result - success*/
    protected fun getPreparedToMoveListener(
        type: FragmentNavController,
        bundle: Bundle?
    ): ResultListener {
        return object : ResultListener() {
            override fun success() {
                (activity as ParentActivity).move(type, bundle)

            }
        }
    }

    /** set transition between two fragments */
    fun setTransition(transition: NavigationTransition){
        when(transition::class.java.simpleName) {
            "SharedAxisTransition" -> {
                if (transition.isFirst) {
                    exitTransition = MaterialSharedAxis((transition as SharedAxisTransition).axis, transition.firstParent)
                    reenterTransition = MaterialSharedAxis(transition.axis, !transition.firstParent)
                } else {
                    enterTransition = MaterialSharedAxis((transition as SharedAxisTransition).axis,  transition.firstParent)
                    returnTransition = MaterialSharedAxis(transition.axis, transition.firstParent)
                }
            }
        }
    }

    /** do smt when backPressed
     * @return false if there is no special action for back*/
    abstract fun onBackPressed():Boolean

    protected fun setTitle(){
        (activity as ParentActivity).changeTitle(getTitle())
    }


    /** set onClick listeners (mostly for applying actions)*/
    protected abstract fun setListeners()
    /** set listeners to viewModel changes */
    protected abstract fun setViewModelListeners()
    /** set units, layout params to adapters*/
    protected abstract fun prepareAdapters()

    /** return title */
    protected abstract fun getTitle():String


    interface BundleGettable{
        fun getBundle():Bundle
    }
}