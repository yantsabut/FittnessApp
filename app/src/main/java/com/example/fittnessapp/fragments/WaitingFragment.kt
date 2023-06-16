package com.example.fittnessapp.fragments

import android.os.Bundle
import android.os.CountDownTimer
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import com.example.fittnessapp.R
import com.example.fittnessapp.databinding.WaitiingFragmentBinding
import com.example.fittnessapp.utils.FragmentManager
import com.example.fittnessapp.utils.TimesUtils

const val COUNT_DOWN_TIME = 11000L

class WaitingFragment : Fragment() {
    private lateinit var binding: WaitiingFragmentBinding
    private lateinit var timer: CountDownTimer
    private var ab: ActionBar? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = WaitiingFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        ab = (activity as AppCompatActivity).supportActionBar
        ab?.title = getString(R.string.waiting)
        binding.pBar.max = COUNT_DOWN_TIME.toInt()
        starTimer()
    }

    private fun starTimer() = with(binding) {
        timer = object : CountDownTimer(COUNT_DOWN_TIME, 1) {

            override fun onTick(restTime: Long) {
                tvTimer.text = TimesUtils.getTime(restTime)
                pBar.progress = restTime.toInt()
            }

            override fun onFinish() {
                FragmentManager.setFragment(
                    ExerciseFragment.newInstance(),
                    activity as AppCompatActivity
                )

            }

        }.start()
    }

    override fun onDetach() {
        super.onDetach()
        timer.cancel()
    }

    companion object {

        @JvmStatic
        fun newInstance() = WaitingFragment()
    }
}
