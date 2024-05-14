package com.ilmiddin1701.yolharakatiqoidalari.fragments

import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.navigation.fragment.findNavController
import com.ilmiddin1701.yolharakatiqoidalari.adapters.SpinnerAdapter
import com.ilmiddin1701.yolharakatiqoidalari.databinding.FragmentAddSymbolBinding
import com.ilmiddin1701.yolharakatiqoidalari.db.MyDbHelper
import com.ilmiddin1701.yolharakatiqoidalari.models.SymbolData
import com.ilmiddin1701.yolharakatiqoidalari.utils.MyData
import java.io.File
import java.io.FileOutputStream

class AddSymbolFragment : Fragment() {
    private val binding by lazy { FragmentAddSymbolBinding.inflate(layoutInflater) }
    private lateinit var myDbHelper: MyDbHelper
    private lateinit var spinnerAdapter: SpinnerAdapter
    var p = 0
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        myDbHelper = MyDbHelper(requireContext())
        binding.apply {
            val p2 = arguments?.getInt("keyPosition")
            val list = arrayListOf("Yo'q", "Ogohlantiruvchi", "Imtiyozli", "Ta'qiqlovchi", "Buyuruvchi")
            if (p2 == 1) {
                image.setImageURI(Uri.parse(MyData.data?.image))
                edtName.setText(MyData.data?.name)
                edtAbout.setText(MyData.data?.about)
            }
            btnBack.setOnClickListener {
                findNavController().popBackStack()
            }
            spinnerAdapter = SpinnerAdapter(list)
            spinner.adapter = spinnerAdapter

            spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    if (position == 0) {
                        tvChecked.text = null
                    } else {
                        tvChecked.text = list[position]
                        p = position
                    }
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {}
            }
            binding.image.setOnClickListener {
                if (edtName.text.isNotEmpty()) {
                    getImageCount.launch("image/*")
                }
            }
        }
        return binding.root
    }

    private val getImageCount = registerForActivityResult(ActivityResultContracts.GetContent()) {
        binding.apply {
            it ?: return@registerForActivityResult
            image.setImageURI(it)
            val inputStream = activity?.contentResolver?.openInputStream(it)
            val file = File(activity?.filesDir, "${edtName.text}.jpg")
            val fileOutputStream = FileOutputStream(file)
            inputStream?.copyTo(fileOutputStream)
            inputStream?.close()
            fileOutputStream.close()
            val absolutPath = file.absolutePath

            val p1 = arguments?.getInt("keyPosition")
            btnSave.setOnClickListener {
                when (p1) {
                    0 -> {
                        if (edtName.text.isNotEmpty() && edtAbout.text.isNotEmpty() && tvChecked.text.isNotEmpty()) {
                            myDbHelper.addSymbol(
                                SymbolData(
                                    absolutPath,
                                    edtName.text.toString(),
                                    edtAbout.text.toString(),
                                    p, 0
                                )
                            )
                            findNavController().popBackStack()
                        } else {
                            Toast.makeText(context, "Ma'lumotlar kiritilmagan!", Toast.LENGTH_SHORT).show()
                        }
                    }
                    1 -> {
                        MyData.data?.image = absolutPath
                        MyData.data?.name = edtName.text.toString()
                        MyData.data?.about = edtAbout.text.toString()
                        MyData.data?.isChecked = p
                        if (edtName.text.isNotEmpty() && edtAbout.text.isNotEmpty() && tvChecked.text.isNotEmpty()) {
                            myDbHelper.editSymbol(MyData.data!!)
                            findNavController().popBackStack()
                        } else {
                            Toast.makeText(context, "Ma'lumotlar kiritilmagan!", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }
        }
    }
}