package io.issc.android_dev_tutorial_kt

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import io.issc.android_dev_tutorial_kt.databinding.FragmentNav3Binding
import io.issc.android_dev_tutorial_kt.databinding.FragmentP1Binding
import io.issc.android_dev_tutorial_kt.databinding.FragmentP3Binding

class FrgNav3 : BaseFragment() {
    lateinit var binding: FragmentNav3Binding
    lateinit var btn1: Button
    lateinit var btn2: Button
    lateinit var btn3: Button

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNav3Binding.inflate(layoutInflater)

        btn1 = binding.btnNav1
        btn2 = binding.btnNav2
        btn3 = binding.btnNav3

        btn3.setOnClickListener{
            Toast.makeText(context, "Already in Page 3", Toast.LENGTH_SHORT).show()
        }

        btn1.setOnClickListener{
            val controller = findNavController()
            controller.navigate(R.id.action_frgNav3_to_frgNav1)
        }

        btn2.setOnClickListener{
            val controller = findNavController()
            controller.navigate(R.id.action_frgNav3_to_frgNav2)}

        return binding.root
    }

}