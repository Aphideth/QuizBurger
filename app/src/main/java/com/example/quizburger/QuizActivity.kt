package com.example.quizburger

import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.view.View
import android.widget.AutoCompleteTextView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_quiz.*


class QuizActivity : AppCompatActivity() {

    var questionList = ArrayList<Quiz>()
    var nbGoodAnswer: Int = 0
    var currentQuestion: Int = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz)

        questionList.add(Quiz("Combien de steak contient Le Magik Burger ?", "3", "1", "4", 3))
        questionList.add(Quiz("Combien de steak contient Le Kamikaze Burger ?", "3", "6", "4", 2))
        questionList.add(Quiz("Comment s'appelle le burger contenant du poulet ?", "Le poulet burger", "Le maxi cotecote", "Le chicken run !", 3))
        questionList.add(Quiz("Comment s'appelle les ailes de poulet épicées ?", "Les spicy wings", "Les ailes qui piquent la langue !", "Les spicy chickens", 1))
        questionList.add(Quiz("En combien de temps est préparé votre burger ?", "3 min", "5 min", "10 min", 2))

        displayQuiz(questionList.get(currentQuestion))
    }

    fun displayQuiz(quiz:Quiz) {
        questionQuiz.setText(quiz.question)
        answerQuiz1.setText(quiz.answer1)
        answerQuiz2.setText(quiz.answer2)
        answerQuiz3.setText(quiz.answer3)
    }

    fun controlerAnswer(valeur: Int){
        val quiz = questionList.get(currentQuestion)
        if(quiz.verifyAnswer(valeur)){
            nbGoodAnswer += 1
            Toast.makeText(this, "+1 point", Toast.LENGTH_SHORT).show()
        }else{
            Toast.makeText(this, "+0 point", Toast.LENGTH_SHORT).show()
        }

        currentQuestion ++

        if(currentQuestion >= questionList.size){
            var alert = AlertDialog.Builder (this)
            if(nbGoodAnswer > 2){
                alert.setTitle("Partie terminée")
                alert.setMessage("Tu as eu : $nbGoodAnswer bonnes réponses !\nBravo ! Clique sur le bouton SCORES pour voir ton cadeau !")
                alert.setPositiveButton("ok"){ dialog: DialogInterface?, which: Int -> finish()}
                alert.show()
            }else{
                alert.setTitle("Partie terminé")
                alert.setMessage("Tu as eu : $nbGoodAnswer bonnes réponses !\nDésolé, Meilleurs chances la prochaine fois !")
                alert.setPositiveButton("ok"){ dialogInterface: DialogInterface?, i: Int -> finish()}
                alert.show()
            }
            val save = getSharedPreferences("com.example.quizburger", Context.MODE_PRIVATE)
            save.edit().putInt("myScores", nbGoodAnswer).apply()
        }else{
            displayQuiz(questionList.get(currentQuestion))
        }
    }

    fun answerOneClick(view: View){
        controlerAnswer(1)
    }

    fun answerTwoClick(view: View){
        controlerAnswer(2)
    }

    fun answerThreeClick(view: View){
        controlerAnswer(3)
    }

}






