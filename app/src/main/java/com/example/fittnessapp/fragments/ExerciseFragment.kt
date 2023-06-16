package com.example.fittnessapp.fragments

import android.os.Bundle
import android.os.CountDownTimer
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.activityViewModels
import com.example.fittnessapp.R
import com.example.fittnessapp.adaptors.ExercisesModel
import com.example.fittnessapp.databinding.ExerciseFragmentBinding
import com.example.fittnessapp.utils.FragmentManager
import com.example.fittnessapp.utils.MainViewModel
import com.example.fittnessapp.utils.TimesUtils
import pl.droidsonroids.gif.GifDrawable


class ExerciseFragment : Fragment() {
    private lateinit var binding: ExerciseFragmentBinding
    private var exerciseCounter = 0
    private var timer: CountDownTimer? = null
    private var exList: ArrayList<ExercisesModel>? = null
    private var ab: ActionBar? = null
    private var currentDay = 0
    private val model: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = ExerciseFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        currentDay = model.currentDay
        exerciseCounter = model.getExerciseCount()
        ab = (activity as AppCompatActivity).supportActionBar
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
            val ex = exList?.get(exerciseCounter++) ?: return
            showExercise(ex)
            setExerciseType(ex)
            showNextExercise()
        } else {
            exerciseCounter++
            FragmentManager.setFragment(
                DayFinishFragment.newInstance(),
                activity as AppCompatActivity
            )
        }
    }

    private fun showExercise(exercise: ExercisesModel) = with(binding) {
        imMain.setImageDrawable(GifDrawable(root.context.assets, exercise.image))
        tvName.text = exercise.name
        val title = "$exerciseCounter / ${exList?.size}"
        ab?.title = title
    }

    private fun setExerciseType(exercise: ExercisesModel) {
        if (exercise.time.startsWith("x")) {
            binding.tvTime.text = exercise.time
        } else {
            starTimer(exercise)
        }
    }

    private fun showNextExercise() = with(binding) {
        if (exerciseCounter < exList?.size!!) {
            val ex = exList?.get(exerciseCounter) ?: return
            imNext.setImageDrawable(GifDrawable(root.context.assets, ex.image))
            setTimeType(ex)
        } else {
            imNext.setImageDrawable(GifDrawable(root.context.assets, "biscuits.gif"))
            tvName.text = getString(R.string.done)
        }
    }

    private fun setTimeType(ex: ExercisesModel) {
        if (ex.time.startsWith("x")) {
            binding.tvNextName.text = ex.time
        } else {
            val name = ex.name + ": ${TimesUtils.getTime(ex.time.toLong() * 1000)}"
            binding.tvNextName.text = name
        }
    }

    private fun starTimer(exercise: ExercisesModel) = with(binding) {
        progressBar.max = exercise.time.toInt() * 1000
        timer?.cancel()
        timer = object : CountDownTimer(exercise.time.toLong() * 1000, 1) {

            override fun onTick(restTime: Long) {
                tvTime.text = TimesUtils.getTime(restTime)
                progressBar.progress = restTime.toInt()
            }

            override fun onFinish() {
                nextExercise()
            }

        }.start()
    }

    override fun onDetach() {
        super.onDetach()
        model.savePref(currentDay.toString(), exerciseCounter - 1)
        timer?.cancel()
    }

    companion object {

        @JvmStatic
        fun newInstance() = ExerciseFragment()
    }
}
