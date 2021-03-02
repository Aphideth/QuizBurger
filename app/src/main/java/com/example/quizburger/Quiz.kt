package com.example.quizburger

class Quiz  (var question: String, var answer1 : String, var answer2 : String, var answer3 : String, var goodAnswer: Int){

    fun verifyAnswer(valeur: Int): Boolean {
        if(valeur == goodAnswer){
            return true
        }
        return false
    }
}