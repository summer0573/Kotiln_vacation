package com.example.quizquiz

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

class QuizFragment : Fragment(),
        QuizStartFragment.QuizStartListener
{
    override fun onQuizStart() {
        Log.d("mytag", "시작하기")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(
            R.layout.quiz_fragment,
            container,
            false
        )

        childFragmentManager
            .beginTransaction().add(R.id.fragment_container, QuizStartFragment())
            .commit()

        return view
    }
}
