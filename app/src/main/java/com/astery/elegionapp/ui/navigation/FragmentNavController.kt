package com.astery.elegionapp.ui.navigation

import android.os.Bundle
import com.astery.elegionapp.ui.views.XFragment
import com.astery.elegionapp.ui.views.fragments.*
import com.google.android.material.transition.MaterialSharedAxis

/** navigation controller
 * fragments can start another fragment not with calling this fragment itself, but starting action.
 */
enum class FragmentNavController {
    AUTH {
        override val thisFragment: XFragment?
        get() = AuthFragment()
        override val parent: FragmentNavController?
        get() = null
        override val transition: NavigationTransition
        get() = SharedAxisTransition().setAxis(MaterialSharedAxis.Z).setFirstParent(true)
    },
    LOGIN {
        override val thisFragment: XFragment?
            get() = LoginFragment()
        override val parent: FragmentNavController?
            get() = null
        override val transition: NavigationTransition
            get() = SharedAxisTransition().setAxis(MaterialSharedAxis.Z).setFirstParent(true)
    },
    REQUESTS {
        override val thisFragment: XFragment?
            get() = VacationFragment()
        override val parent: FragmentNavController?
            get() = null
        override val transition: NavigationTransition
            get() = SharedAxisTransition().setAxis(MaterialSharedAxis.Z).setFirstParent(true)
    },
    DEVELOPMENT {
        override val thisFragment: XFragment?
            get() = DevelopmentFragment()
        override val parent: FragmentNavController?
            get() = null
        override val transition: NavigationTransition
            get() = SharedAxisTransition().setAxis(MaterialSharedAxis.Z).setFirstParent(true)
    };

    /** transition settings. It may be useful if it's required to get action from different places */
    protected var transitionBundle: Bundle? = null

    constructor(transitionBundle: Bundle) {
        this.transitionBundle = transitionBundle
    }

    constructor() {}

    /** get XFragment object for this action  */
    abstract val thisFragment: XFragment?

    /** get XFragment when back pressed  */
    abstract val parent: FragmentNavController?

    /** get transition to transform to this action */
    abstract val transition: NavigationTransition?

    /** throws runtime exception if the action started from wrong fragment. This method prevent you from creating new communication
     * with two fragment without declaring it here  */
    fun checkFriends(controller: FragmentNavController) {
        throw RuntimeException(name + " started from wrong fragment " + controller.name)
    }
}