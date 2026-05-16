package com.example.nimmaguru

import com.google.firebase.firestore.FirebaseFirestore

object FirebaseHelper {

    private val db = FirebaseFirestore.getInstance()

    fun addTutor(tutor: TutorModel) {
        db.collection("tutors")
            .document(tutor.id)
            .set(tutor)
    }

    fun getTutors(onResult: (List<TutorModel>) -> Unit) {
        db.collection("tutors")
            .get()
            .addOnSuccessListener { result ->
                val list = mutableListOf<TutorModel>()
                for (doc in result) {
                    val tutor = doc.toObject(TutorModel::class.java)
                    if (tutor != null) {
                        list.add(tutor)
                    }
                }
                onResult(list)
            }
    }
}
