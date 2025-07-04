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
        password: String,
        firstName: String,
        lastName: String
    ): User {
        val result = firebaseAuth.createUserWithEmailAndPassword(email, password).await()
        val firebaseUser = result.user ?: throw Exception("Error en registro")

        // Acá deberíamos guardar el fullName, userName, etc en Firestore, asociados al usuario

        val userData = mapOf(
            "firstName" to firstName,
            "lastName" to lastName,
            "username" to username,
            "email" to email,
            "image" to firebaseUser.photoUrl?.toString().orEmpty(),
            "createdAt" to System.currentTimeMillis()
        )

        firestore.collection("users")
            .document(firebaseUser.uid)
            .set(userData)
            .await() // bloqueamos hasta que se guarde correctamente

        return User(
            id = firebaseUser.uid,
            username = username,
            email = email,
            fullName = "$firstName $lastName",
            image = firebaseUser.photoUrl?.toString().orEmpty(),
            firstName = firstName,
            lastName = lastName
        )
    }

    override suspend fun getCurrentUser(): User {
        val uid = firebaseAuth.currentUser?.uid ?: throw Exception("Usuario no logueado")
        val snapshot = firestore.collection("users").document(uid).get().await()
        val userData = snapshot.toObject(User::class.java)
            ?: throw Exception("Datos del usuario no encontrados")

        return User(
            id = uid,
            username = userData.username,
            email = userData.email,
            fullName = "${userData.firstName} ${userData.lastName}",
            image = userData.image,
            firstName = userData.firstName,
            lastName = userData.lastName
        )
    }
}

