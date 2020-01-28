package test.test.inv.presentation.main.home

import android.os.Bundle
import com.test.test.presentation.base.BaseFragment
import org.kodein.di.generic.instance
import test.test.inv.databinding.FragmentHomeBinding

class HomeFragment : BaseFragment<FragmentHomeBinding>() {

    override val kodeinModule = HomeModule.get(this)
    override val viewModel: HomeContract.ViewModel by instance()


    override fun viewCreated(savedInstanceState: Bundle?) {
        binding.viewModel = viewModel
    }


    companion object {
        fun newInstance() = HomeFragment()
    }

}