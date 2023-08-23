package com.practice.nycschools

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.practice.nycschools.databinding.FragmentSecondBinding
import com.practice.nycschools.model.DataViewModel
import com.practice.nycschools.model.SchoolClass
import com.practice.nycschools.model.util.Utils
import com.practice.nycschools.service.RequestInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SecondFragment : Fragment() {

    private var _binding: FragmentSecondBinding? = null

    private lateinit var dataViewModel: DataViewModel

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dataViewModel = ViewModelProvider(this)[DataViewModel::class.java]

        // Create the observer which updates the UI.

        // Create the observer which updates the UI.
        val nameObserver: Observer<List<SchoolClass?>> =
            Observer { data: List<SchoolClass?>? ->
                if (!data.isNullOrEmpty()) {
                    val detail: String = Utils.BuildSchoolString(data[0])
                    binding.detailTextViewId.text = detail
                } else {
                    binding.detailTextViewId.setText(R.string.no_data_retrieved)
                }
            }

        // Observe the LiveData, passing in this activity as the LifecycleOwner and the observer.
        dataViewModel.currentSchool.observe(viewLifecycleOwner, nameObserver)

        // pass dbn from first fragment
        var dbn = dataViewModel.selectedData

        var mainActivity: MainActivity = activity as MainActivity
        val retrofit: Retrofit = mainActivity.retrofit
        val requestInterface: RequestInterface = retrofit.create(RequestInterface::class.java)
        val call: Call<List<SchoolClass>> = requestInterface.GetSchoolData(dbn.value)
        call.enqueue(object : Callback<List<SchoolClass>> {
            override fun onResponse(
                call: Call<List<SchoolClass>>,
                response: Response<List<SchoolClass>>
            ) {
                if (response.isSuccessful) dataViewModel.currentSchool.value = response.body()
            }

            override fun onFailure(call: Call<List<SchoolClass>>, t: Throwable) {}
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}