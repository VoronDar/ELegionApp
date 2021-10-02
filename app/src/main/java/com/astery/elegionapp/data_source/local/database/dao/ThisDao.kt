package com.astery.elegionapp.data_source.local.database.dao

import androidx.room.*
import androidx.room.Dao
import com.astery.elegionapp.pojo.User
import io.reactivex.Single

@Dao
interface ThisDao {
    /** return items with all of its advises  */
    @Query("SELECT * FROM user WHERE id = :itemId")
    fun getItemById(itemId: String?): Single<User?>?

    /** return items with all of its advises  */
    @get:Query("SELECT * FROM user")
    val item: Single<List<User?>?>?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addUser(user: User?)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addUsers(users: List<User?>?)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateUser(user: User?)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateUsers(users: List<User?>?)
}