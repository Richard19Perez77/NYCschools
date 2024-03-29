package com.practice.nycschools

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import com.practice.nycschools.databinding.FragmentSecondBinding
import com.practice.nycschools.model.DataViewModel
import com.practice.nycschools.model.SchoolClass
import com.practice.nycschools.model.util.Utils
import com.practice.nycschools.service.RequestInterface
import dagger.hilt.android.AndroidEntryPoint
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import javax.inject.Inject

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
@AndroidEntryPoint
class SecondFragment : Fragment() {

    @Inject
    lateinit var retrofit: Retrofit

    private var _binding: FragmentSecondBinding? = null

    private val dataViewModel: DataViewModel by viewModels()

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private val args: SecondFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Create the observer which updates the UI.
        // val args: SecondFragmentArgs by navArgs()
        // val receivedString = args.selectedData

        // Create the observer which updates the UI.
        val nameObserver: Observer<List<SchoolClass?>> =
            Observer { data: List<SchoolClass?>? ->
                binding.loadingProgressBar.visibility = View.GONE
                if (!data.isNullOrEmpty()) {
                    val detail: String? = data[0]?.let { Utils.buildSchoolString(it) }
                    binding.detailTextViewId.text = detail
                    binding.schoolTitle.text = getText(R.string.school_test_details)
                    binding.schoolTitle.visibility = View.VISIBLE
                    binding.detailTextViewId.visibility = View.VISIBLE
                } else {
                    binding.detailTextViewId.setText(R.string.no_data_retrieved)
                    binding.detailTextViewId.visibility = View.VISIBLE
                }
            }

        // Observe the LiveData, passing in this activity as the LifecycleOwner and the observer.
        dataViewModel.currentSchool.observe(viewLifecycleOwner, nameObserver)
 //       dataViewModel.selectedData.observe(viewLifecycleOwner, selectedObserver)

        val dbn = args.selected
        val requestInterface: RequestInterface = retrofit.create(RequestInterface::class.java)
        val call: Call<List<SchoolClass>>? = dbn?.let { requestInterface.getSchoolData(it) }
        call?.enqueue(object : Callback<List<SchoolClass>> {

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