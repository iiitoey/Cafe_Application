package swu.cp316.cafe

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import swu.cp316.cafe.db.RoomAppDb
import swu.cp316.cafe.db.UserEntity

class ThirdActivityViewModel(app:Application): AndroidViewModel(app) {

    lateinit var allUsers: MutableLiveData<List<UserEntity>>

    init {
        allUsers = MutableLiveData()
    }

    fun getAllUsersObservers():MutableLiveData<List<UserEntity>>{
        return allUsers
    }

    fun getAllUsers(){
        val userDao = RoomAppDb.getAppDatabase((getApplication()))?.UserDao()
        val list = userDao?.getAllUserInfo()

        allUsers.postValue(list)
    }

    fun insertUserInfo(entity: UserEntity){
        val userDao = RoomAppDb.getAppDatabase(getApplication())?.UserDao()
        userDao?.insertUser(entity)
        getAllUsers()
    }

    fun updateUserInfo(entity: UserEntity){
        val userDao = RoomAppDb.getAppDatabase(getApplication())?.UserDao()
        userDao?.updateUser(entity)
        getAllUsers()
    }
    fun deleteUserInfo(entity: UserEntity){
        val userDao = RoomAppDb.getAppDatabase(getApplication())?.UserDao()
        userDao?.deleteUser(entity)
        getAllUsers()
    }


}