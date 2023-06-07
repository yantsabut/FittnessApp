package com.example.fittnessapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.fittnessapp.adaptors.Exercisedapter
import com.example.fittnessapp.adaptors.ExercisesModel
import com.example.fittnessapp.databinding.ExerciseFragmentBinding
import com.example.fittnessapp.databinding.ExerciseListFragmentBinding
import com.example.fittnessapp.utils.FragmentManager
import com.example.fittnessapp.utils.MainViewModel
import pl.droidsonroids.gif.GifDrawable


class ExerciseFragment : Fragment() {
private lateinit var binding: ExerciseFragmentBinding
private var exerciseCounter = 0
    private var exList: ArrayList<ExercisesModel>? = null
    private val model: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = ExerciseFragmentBinding.inflate(inflater,  container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        model.mutableListExercise.observe(viewLifecycleOwner) {
              exList = it
            nextExercise()
        }
        binding.bNext.setOnClickListener {
            nextExercise()
        }
    }

    private fun nextExercise() {
        if (exerciseCounter < exList?.size!!) {
            val ex = exList?.get(exerciseCounter++)
            showExercise(ex)
        }
        else {
            Toast.makeText(activity, "Done", Toast.LENGTH_LONG).show()
        }
    }

    private fun showExercise(exercise: ExercisesModel?) = with(binding){
        if (exercise == null) return@with
    imMain.setImageDrawable(exercise.image?.let { GifDrawable(root.context.assets,exercise.image) })
        tvName.text = exercise.name
    }

    companion object {

        @JvmStatic
        fun newInstance() = ExerciseFragment()
            }
}
