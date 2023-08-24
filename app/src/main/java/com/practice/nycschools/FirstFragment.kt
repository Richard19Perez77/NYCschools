package com.practice.nycschools

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.practice.nycschools.databinding.FragmentFirstBinding
import com.practice.nycschools.model.DataViewModel
import com.practice.nycschools.model.NYCListClass
import com.practice.nycschools.service.DataAdapter
import com.practice.nycschools.service.RequestInterface
import dagger.hilt.android.AndroidEntryPoint
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import javax.inject.Inject


/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
@AndroidEntryPoint
class FirstFragment : Fragment() {

    @Inject
    lateinit var retrofit: Retrofit

    private var _binding: FragmentFirstBinding? = null

    private val dataViewModel: DataViewModel by viewModels()

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Create the observer which updates the UI.
        val nameObserver: Observer<List<NYCListClass>> =
            Observer { data: List<NYCListClass> ->
                binding.loadingProgressBar.visibility = View.GONE
                binding.recyclerViewId.adapter = DataAdapter(data, this)
                binding.recyclerViewId.visibility = View.VISIBLE
            }

        // Observe the LiveData, passing in this activity as the LifecycleOwner and the observer.
        dataViewModel.currentData.observe(viewLifecycleOwner, nameObserver)


        val requestInterface: RequestInterface = retrofit.create(RequestInterface::class.java)
        val call: Call<List<NYCListClass>> = requestInterface.getNYCdata()
        call.enqueue(object : Callback<List<NYCListClass>> {

            override fun onResponse(
                call: Call<List<NYCListClass>>,
                response: Response<List<NYCListClass>>
            ) {
                dataViewModel.currentData.value = response.body()
            }

            override fun onFailure(call: Call<List<NYCListClass>>, t: Throwable) {
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun startSecond(dbn: String) {
        val action = com.practice.nycschools.FirstFragmentDirections
                .actionFirstFragmentToSecondFragment(dbn)
        findNavController().navigate(action)
    }
}