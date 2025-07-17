package com.fedenintzel.petshopapp.data.repository

import com.fedenintzel.petshopapp.domain.model.User
import com.fedenintzel.petshopapp.domain.repository.AuthRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

/**
 * Implementación de AuthRepository que maneja la autenticación y persistencia
 * de datos de usuario utilizando Firebase Authentication y Firestore.
 *
 * Métodos:
 * - login: Autentica con email y password, luego carga datos desde FirebaseAuth (parcial).
 * - register: Crea un usuario en FirebaseAuth y guarda datos completos en Firestore.
 * - getCurrentUser: Recupera el perfil completo del usuario desde Firestore usando su UID.
 */

class AuthRepositoryImp @Inject constructor(
    private val firebaseAuth: FirebaseAuth,
    private val firestore: FirebaseFirestore
) : AuthRepository {

    /**
     * Inicia sesión con email y password usando Firebase Authentication.
     * Actualmente devuelve datos básicos desde FirebaseAuth.
     */
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

    /**
     * Registra un nuevo usuario en Firebase Authentication y guarda
     * información extendida (nombre, username, etc.) en Firestore.
     *
     * @param fullName nombre completo del usuario (solo para usarlo internamente).
     * @param firstName y lastName se almacenan por separado en Firestore.
     */

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

        // Acá guardamos el fullName, userName, etc en Firestore, asociados al usuario

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

    /**
     * Recupera el perfil completo del usuario actual desde Firestore.
     * Se espera que el documento tenga la estructura correspondiente al modelo User.
     *
     * @throws Exception si no hay sesión activa o si el documento no existe.
     */

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

