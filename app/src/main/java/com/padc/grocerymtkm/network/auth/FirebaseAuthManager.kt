package com.padc.grocerymtkm.network.auth

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserProfileChangeRequest
import com.padc.grocerymtkm.R
import com.padc.grocerymtkm.utils.EM_NO_INTERNET

object FirebaseAuthManager : AuthManager {

    private val mFirebaseAuth :FirebaseAuth= FirebaseAuth.getInstance()

    override fun login(
        email: String,
        password: String,
        onSuccess: () -> Unit,
        onFailure: (String) -> Unit
    ) {
        mFirebaseAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener {
            if (it.isSuccessful && it.isComplete){
                onSuccess()
            }else{
                onFailure(it.exception?.message ?: EM_NO_INTERNET)
            }
        }
    }

    override fun register(
        email: String,
        password: String,
        userName: String,
        onSuccess: () -> Unit,
        onFailure: (String) -> Unit
    ) {
        mFirebaseAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener {
            if(it.isComplete && it.isSuccessful){
                mFirebaseAuth.currentUser?.updateProfile(
                    UserProfileChangeRequest.Builder().setDisplayName(userName).build()
                )
                onSuccess()
            }else{
                onFailure(it.exception?.message ?: EM_NO_INTERNET)
            }
        }
    }

    override fun getUserName(): String {
        return mFirebaseAuth.currentUser?.displayName ?: ""
    }
}