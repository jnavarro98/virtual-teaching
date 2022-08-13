package com.virtualteaching.studentsmagnet.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.virtualteaching.studentsmagnet.model.IconButton

/* Handles operations on iconButtonsLiveData and holds details about it. */
class DataSource(iconButtons: List<IconButton>) {
    private val initialIconButtonList = iconButtons
    private val iconButtonLiveData = MutableLiveData(initialIconButtonList)

    /* Adds iconButton to liveData and posts value. */
    fun addIconButton(iconButton: IconButton) {
        val currentList = iconButtonLiveData.value
        if (currentList == null) {
            iconButtonLiveData.postValue(listOf(iconButton))
        } else {
            val updatedList = currentList.toMutableList()
            updatedList.add(0, iconButton)
            iconButtonLiveData.postValue(updatedList)
        }
    }

    /* Removes iconButton from liveData and posts value. */
    fun removeIconButton(iconButton: IconButton) {
        val currentList = iconButtonLiveData.value
        if (currentList != null) {
            val updatedList = currentList.toMutableList()
            updatedList.remove(iconButton)
            iconButtonLiveData.postValue(updatedList)
        }
    }

    //TODO: Maybe is useful in the future

    /* Returns iconButton given an ID.
    fun getIconButtonForId(id: Long): IconButton? {
        iconButtonLiveData.value?.let { iconButtons ->
            return iconButtons.firstOrNull{ it.id == id}
        }
        return null
    } */

    fun getIconButtonList(): LiveData<List<IconButton>> {
        return iconButtonLiveData
    }

    companion object {
        private var INSTANCE: DataSource? = null

        fun getDataSource(iconButtons: List<IconButton>): DataSource {
            return synchronized(DataSource::class) {
                val newInstance = INSTANCE ?: DataSource(iconButtons)
                INSTANCE = newInstance
                newInstance
            }
        }
    }
}