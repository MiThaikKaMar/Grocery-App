package com.padc.grocerymtkm.network.remoteconfig

import android.util.Log
import com.google.firebase.ktx.Firebase
import com.google.firebase.remoteconfig.ktx.remoteConfig
import com.google.firebase.remoteconfig.ktx.remoteConfigSettings

object FirebaseRemoteConfigManager {

   private val remoteConfig = Firebase.remoteConfig
    init {
        val configSettings = remoteConfigSettings{
            minimumFetchIntervalInSeconds = 0
        }
        remoteConfig.setConfigSettingsAsync(configSettings)
    }

    fun setUpRemoteConfigWithDefaultValues(){
        val defaultValues : Map<String,Any> = hashMapOf(
            "mainScreenAppBarTitle" to "Grocery-App",
            "layout" to 0
        )
        remoteConfig.setDefaultsAsync(defaultValues)
    }

    fun fetchRemoteConfig(){
        remoteConfig.fetch()
            .addOnCompleteListener { task ->
                if (task.isSuccessful){
                    Log.d("Firebase","Firebase Remote Config Fetch Successful!")
                    remoteConfig.activate().addOnCompleteListener {
                        Log.d("Firebase","Firebase Remote Config Activate Successful!")
                    }
                }else{
                    Log.d("Firebase","Firebase Remote Config Fetch Fail!!")
                }
            }
    }

    fun getToolbarName():String{
        val name= remoteConfig.getValue("mainScreenAppBarTitle").asString()
        Log.e("Name",name)
        return name
    }

    fun getLayout():Long{
        return remoteConfig.getValue("layout").asLong()
    }
}