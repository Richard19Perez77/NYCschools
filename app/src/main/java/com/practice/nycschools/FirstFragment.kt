package com.practice.nycschools

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.practice.nycschools.databinding.FragmentFirstBinding
import com.practice.nycschools.model.DataViewModel
import com.practice.nycschools.model.NYCListClass
import com.practice.nycschools.service.DataAdapter
import com.practice.nycschools.service.RequestInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit


/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null

    private lateinit var dataViewModel: DataViewModel

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dataViewModel = ViewModelProvider(this).get(modelClass = DataViewModel::class.java)


        // Create the observer which updates the UI.
        val nameObserver: Observer<List<NYCListClass?>> =
            Observer { data: List<NYCListClass?>? ->
                binding.recyclerViewId.adapter = DataAdapter(data, this, dataViewModel)
            }

        // Observe the LiveData, passing in this activity as the LifecycleOwner and the observer.
        dataViewModel.currentData.observe(viewLifecycleOwner, nameObserver)

        var mainActivity: MainActivity = activity as MainActivity
        val retrofit: Retrofit = mainActivity.retrofit
        val requestInterface: RequestInterface = retrofit.create(RequestInterface::class.java)
        val call: Call<List<NYCListClass>> = requestInterface.GetNYCdata()
        call.enqueue(object : Callback<List<NYCListClass>> {
            override fun onResponse(
                call: Call<List<NYCListClass>>,
                response: Response<List<NYCListClass>>
            ) {
                dataViewModel.currentData.value = response.body()
            }

            override fun onFailure(call: Call<List<NYCListClass>>, t: Throwable) {}
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}