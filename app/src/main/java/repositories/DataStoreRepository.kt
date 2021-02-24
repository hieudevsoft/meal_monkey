package repositories


import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.createDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import values.Constants



class DataStoreRepository(context:Context){
    private var dataStore : DataStore<Preferences>?=null
    init {
        dataStore = context.createDataStore(
            name = Constants.PREFERENCE_NAME
        )
    }

    companion object{
        private val SKIP_KEY = booleanPreferencesKey(Constants.SKIP_KEY)
    }

    suspend fun saveStateSkip(isSkipBoarding:Boolean){
        dataStore?.edit {
            it[SKIP_KEY]  = isSkipBoarding
        }
    }

    fun readStateKip(): Flow<Boolean> {
        return dataStore?.data!!.map {
            it->
            it[SKIP_KEY]?:false
        }
    }

}