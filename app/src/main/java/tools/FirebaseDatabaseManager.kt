package tools

import android.util.Log
import classes.Profile
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class FirebaseDatabaseManager private constructor(){
    private val database = Firebase.database
    private object Holder{
        val instance = FirebaseDatabaseManager()
    }
    companion object{
        private val TAG = "FirebaseDatabaseManager"
        @JvmStatic
        val instance : FirebaseDatabaseManager by lazy { Holder.instance }
    }

    fun writeProfile(references:String,profile:Profile){
        database.getReference(references).child(profile.userID!!).setValue(profile.toMap())
    }

    fun retrievingListProfile(references: String){
        database.getReference(references).addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                snapshot.children.forEach { item ->
                    if(item.value is Map<*, *>){
                        val profile = Profile().fromObject(item.value as Map<String, Any?>)
                        Log.d(TAG, "onDataChange: ${profile}")
                    }

                }
            }

            override fun onCancelled(error: DatabaseError) {
                Log.d(TAG, "onCancelled: Cancelling read data profile list")
            }

        })
    }

}