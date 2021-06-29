package vms.android.simplequiz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val questionBank = listOf(  Question(R.string.question_1, true),
                                        Question(R.string.question_2, true),
                                        Question(R.string.question_3, false),
                                        Question(R.string.question_4, false),
                                        Question(R.string.question_5, false),
                                        Question(R.string.question_6, true),
                                        Question(R.string.question_7, false),
                                        Question(R.string.question_8, false),
                                        Question(R.string.question_9, true),
                                        Question(R.string.question_10, false))
    private var currentIndex = 0

    private val TAG = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.d(TAG, "onCreate() called")

        updateQuestion()

        yesButton.setOnClickListener {
            checkAnswer(true)
        }

        noButton.setOnClickListener {
            checkAnswer(false)
        }

        nextButton.setOnClickListener {
            nextQuestion()
        }

        displayedQuestion.setOnClickListener {
            nextQuestion()
        }

        previousButton.setOnClickListener {
            if (currentIndex == 0) {
                currentIndex = questionBank.size
            }

            currentIndex = (currentIndex - 1) % questionBank.size
            updateQuestion()
        }

    }

    override fun onStart() {
        super.onStart()
        Log.i(TAG, "onStart() called")
    }

    override fun onResume() {
        super.onResume()
        Log.w(TAG, "onResume() called")
    }

    override fun onPause() {
        super.onPause()
        Log.e(TAG, "onPause() called")
    }

    override fun onStop() {
        super.onStop()
        Log.e(TAG, "onStop() called")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.e(TAG, "onDestroy() called")
    }


    private fun nextQuestion() {
        currentIndex = (currentIndex + 1) % questionBank.size
        updateQuestion()
    }

    private fun updateQuestion() {
        val questionTextResId = questionBank[currentIndex].textResId
        displayedQuestion.setText(questionTextResId)
    }

    private fun checkAnswer(userAnswer: Boolean) {
        val correctAnswer = questionBank[currentIndex].answer

//        if (userAnswer == correctAnswer) {
//            Toast.makeText(this, R.string.correct_toast, Toast.LENGTH_LONG).show()
//        } else {
//            Toast.makeText(this, R.string.incorrect_toast, Toast.LENGTH_LONG).show()
//        }

        var messageResId =  if (userAnswer == correctAnswer) {
                                R.string.correct_toast
                            } else {
                                R.string.incorrect_toast
                            }

        Toast.makeText(this, messageResId, Toast.LENGTH_LONG).show()
    }

}