package io.issc.android_dev_tutorial_kt

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment.Companion.findNavController
import androidx.navigation.fragment.findNavController
import io.issc.android_dev_tutorial_kt.databinding.FragmentNav2Binding
import io.issc.android_dev_tutorial_kt.databinding.FragmentP1Binding
import io.issc.android_dev_tutorial_kt.databinding.FragmentP2Binding

class FrgNav2 : BaseFragment() {
    lateinit var binding: FragmentNav2Binding

    lateinit var btn1: Button
    lateinit var btn2: Button
    lateinit var btn3: Button

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNav2Binding.inflate(layoutInflater)
//
//        arguments.let {
//            val msg = it?.getString("key")
//            Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
//        }

        btn1 = binding.btnNav1
        btn2 = binding.btnNav2
        btn3 = binding.btnNav3

        btn2.setOnClickListener{
            Toast.makeText(context, "Already in Page 2", Toast.LENGTH_SHORT).show()
        }

        btn1.setOnClickListener{
            val controller = findNavController()
            val bundle = Bundle()
            bundle.putString("key", "Hello from Page 2")
            controller.navigate(R.id.action_frgNav2_to_frgNav1, bundle)
        }

        btn3.setOnClickListener{
            val controller = findNavController()
            controller.navigate(R.id.action_frgNav2_to_frgNav3)}


        return binding.root
    }

}