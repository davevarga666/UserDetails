package com.davevarga.userdetails

import android.app.Application
import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.observe
import androidx.navigation.findNavController
import com.davevarga.userdetails.R.id.action_inputFragment_to_detailsFragment
import com.davevarga.userdetails.databinding.ActivityMainBinding
import com.davevarga.userdetails.databinding.FragmentInputBinding
import com.davevarga.userdetails.databinding.FragmentInputBindingImpl
import kotlinx.android.synthetic.main.fragment_input.*
import androidx.lifecycle.Observer
import java.util.*


class InputFragment : Fragment() {

    private lateinit var binding: FragmentInputBinding
    private val viewModel: UserViewModel by activityViewModels()

//    private val viewModelFactory: UserViewModelFactory by lazy {
//        UserViewModelFactory(Application())
//    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_input, container, false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.editBirthday.setOnClickListener { datePick() }

        binding.saveButton.setOnClickListener { view: View ->
            view.findNavController().navigate(action_inputFragment_to_detailsFragment)

        }

        observerUserModel()
    }

    private fun datePick() {
        val cal = Calendar.getInstance()
        val year = cal.get(Calendar.YEAR)
        val month = cal.get(Calendar.MONTH)
        val day = cal.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog =
            DatePickerDialog(
                requireContext(), DatePickerDialog.OnDateSetListener
                { view, year, monthOfYear, dayOfMonth ->
                    editBirthday.setText("$year-$monthOfYear-$dayOfMonth")
                }, year, month, day
            )
        datePickerDialog.show()
    }

    private fun observerUserModel() {
        viewModel.user.observe(viewLifecycleOwner, Observer { user ->
            binding.userInfo = user ?: User()
        })
    }

}


