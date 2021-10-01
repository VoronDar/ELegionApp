package com.astery.elegionapp.ui.navigation

import android.os.Bundle
import com.astery.elegionapp.ui.views.XFragment

/** navigation controller
 * fragments can start another fragment not with calling this fragment itself, but starting action.
 */
enum class FragmentNavController {
    AUTH {
        override val thisFragment: XFragment?
            get() = TODO("Not yet implemented")
        override val parent: FragmentNavController?
            get() = TODO("Not yet implemented")
        override val transition: NavigationTransition?
            get() = TODO("Not yet implemented")
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