package com.fedenintzel.petshopapp.data.repository

import com.fedenintzel.petshopapp.domain.model.User
import com.fedenintzel.petshopapp.domain.repository.AuthRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class AuthRepositoryImp @Inject constructor(
    private val firebaseAuth: FirebaseAuth,
    private val firestore: FirebaseFirestore
) : AuthRepository {

    override suspend fun login(username: String, password: String): User {
        val result = firebaseAuth.signInWithEmailAndPassword(username, password).await()
        val firebaseUser = result.user ?: throw Exception("Usuario no encontrado")

        return User(
            id = firebaseUser.uid,
            username = firebaseUser.displayName ?:"",
            email = firebaseUser.email ?: "",
            image = firebaseUser.photoUrl?.toString() ?: "",
            fullName = firebaseUser.displayName ?: "",

        )
    }

    override suspend fun register(
        fullName: String,
        username: String,
        email: String,
        password: String
    ): User {
        val result = firebaseAuth.createUserWithEmailAndPassword(email, password).await()
        val firebaseUser = result.user ?: throw Exception("Error en registro")

        // Acá deberíamos guardar el fullName y userName en Firestore, asociados al usuario



        return User(
            id = firebaseUser.uid,
            username = firebaseUser.displayName ?:"",
            email = firebaseUser.email ?: ""
        )
    }
}
