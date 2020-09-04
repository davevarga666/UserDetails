package com.davevarga.userdetails.ui

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.app.DatePickerDialog
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.BitmapFactory
import android.icu.text.SimpleDateFormat
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import androidx.core.content.FileProvider
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import com.davevarga.userdetails.R.id.action_inputFragment_to_detailsFragment
import com.davevarga.userdetails.databinding.FragmentInputBinding
import androidx.lifecycle.Observer
import com.davevarga.userdetails.R
import com.davevarga.userdetails.models.User
import kotlinx.android.synthetic.main.fragment_input.*
import java.io.File
import java.io.IOException
import java.util.*


class InputFragment : Fragment() {

    private lateinit var binding: FragmentInputBinding
    private lateinit var viewModel: UserViewModel

    private val REQUEST_MULTIPLE_PERMISSIONS = 100
    private lateinit var currentPhotoPath: String
    private lateinit var photoFile: File


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

        viewModel = activity?.run {
            ViewModelProviders.of(this).get(UserViewModel::class.java)
        } ?: throw Exception("Invalid Activity")


        val permissions = listOf<String>(
            Manifest.permission.CAMERA,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
        )
        val photoButtonAction = photoButton

        photoButtonAction.setOnClickListener {
            if (!hasPermissions(requireContext(), permissions[0], permissions[1])) {
                requestPermissions(
                    arrayOf(permissions[0], permissions[1]),
                    REQUEST_MULTIPLE_PERMISSIONS
                )
            } else {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    dispatchTakePictureIntent()
                }
            }
        }

        binding.editBirthday.setOnClickListener { datePick() }

        binding.saveButton.setOnClickListener { view: View ->
            viewModel.insert(binding.userInfo!!)
            view.findNavController().navigate(action_inputFragment_to_detailsFragment)

        }

        binding.photoID.setOnClickListener {
            if (it != null)
                binding.expandedImage.visibility = (View.VISIBLE)
        }

        binding.expandedImage.setOnClickListener {
            if (it != null)
                it.visibility = (View.INVISIBLE)
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

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String?>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            REQUEST_MULTIPLE_PERMISSIONS ->
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(requireActivity(), "permissions granted", Toast.LENGTH_SHORT).show()
                    dispatchTakePictureIntent()
                } else {
                    Toast.makeText(requireActivity(), "permissions denied", Toast.LENGTH_SHORT).show()
                }
        }
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_MULTIPLE_PERMISSIONS && resultCode == Activity.RESULT_OK) {

            val myBitmap = BitmapFactory.decodeFile(photoFile.absolutePath)
            binding.photoID.setImageBitmap(myBitmap)
            binding.expandedImage.setImageBitmap(myBitmap)

        }
    }

    fun hasPermissions(context: Context, vararg permissions: String): Boolean = permissions.all {
        ActivityCompat.checkSelfPermission(context, it) == PackageManager.PERMISSION_GRANTED
    }

    @SuppressLint("SimpleDateFormat")
    @RequiresApi(Build.VERSION_CODES.N)
    @Throws(IOException::class)
    private fun createImageFile(): File {
        val timeStamp: String = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
        val storageDir: File? = activity?.getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        return File.createTempFile(
            "JPEG_${timeStamp}_",
            ".jpg",
            storageDir
        ).apply {
            currentPhotoPath = absolutePath
        }
    }

    @RequiresApi(Build.VERSION_CODES.N)
    private fun dispatchTakePictureIntent() {
        Intent(MediaStore.ACTION_IMAGE_CAPTURE).also { takePictureIntent ->
            takePictureIntent.resolveActivity(requireActivity().packageManager)?.also {
                photoFile = createImageFile()
                photoFile.also {
                    val photoURI: Uri = FileProvider.getUriForFile(
                        requireContext(),
                        "com.davevarga.android.fileprovider",
                        it
                    )
                    takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI)
                    startActivityForResult(takePictureIntent, REQUEST_MULTIPLE_PERMISSIONS)
                }
            }
        }
    }
}