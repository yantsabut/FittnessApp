package com.example.fittnessapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.fittnessapp.adaptors.Exercisedapter
import com.example.fittnessapp.databinding.ExerciseListFragmentBinding
import com.example.fittnessapp.utils.FragmentManager
import com.example.fittnessapp.utils.MainViewModel


class ExerciseListFragment : Fragment() {
private lateinit var binding: ExerciseListFragmentBinding
private lateinit var adapter: Exercisedapter
    private val model: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = ExerciseListFragmentBinding.inflate(inflater,  container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
        model.mutableListExercise.observe(viewLifecycleOwner) {
         adapter.submitList(it)
        }
    }

    private fun init() = with(binding){
    adapter = Exercisedapter()
        rcView.layoutManager = LinearLayoutManager(activity)
        rcView.adapter = adapter
        bStart.setOnClickListener {
            FragmentManager.setFragment(WaitingFragment.newInstance(), activity as AppCompatActivity)
        }
    }

    companion object {

        @JvmStatic
        fun newInstance() = ExerciseListFragment()
            }
    }
