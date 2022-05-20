package com.ntech.androidtests.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ntech.androidtests.model.ImageResponse
import com.ntech.androidtests.repo.ArtRepositoryInterface
import com.ntech.androidtests.roomdb.Art
import com.ntech.androidtests.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ArtViewModel @Inject constructor(
    private val repository: ArtRepositoryInterface
): ViewModel() {

    //Art Fragment

    val artList = repository.getArt()

    //Image API fragment

    private val images = MutableLiveData<Resource<ImageResponse>>()
    val imageList: LiveData<Resource<ImageResponse>>
        get() = images

    private val selectedImage = MutableLiveData<String>()
    val selectedImageUrl: LiveData<String>
        get() = selectedImage

    // Art details fragment

    private var insertArt = MutableLiveData<Resource<Art>>()
    val insertArtMessage: LiveData<Resource<Art>>
        get() = insertArt

    fun resetInsertArtMsg() {
        insertArt = MutableLiveData<Resource<Art>>()
    }

    fun setSelectedImage(url: String) {
        selectedImage.postValue(url)
    }

    fun deleteArt(art: Art) {
        viewModelScope.launch {
            repository.deleteArt(art)
        }
    }

    fun insertArt(art: Art) {
        viewModelScope.launch {
            repository.insertArt(art)
        }
    }

    fun searchImage(searchString: String) {
        if(searchString.isEmpty()) {
            return
        }

        images.value = Resource.loading(null)

        viewModelScope.launch {
            val response = repository.searchImage(searchString)
            images.value = response
        }
    }

    fun makeArt(name : String, artistName : String, year : String) {
        if (name.isEmpty() || artistName.isEmpty() || year.isEmpty() ) {
            insertArt.postValue(Resource.error("Enter name, artist, year", null))
            return
        }
        val yearInt = try {
            year.toInt()
        } catch (e: Exception) {
            insertArt.postValue(Resource.error("Year should be number",null))
            return
        }

        val art = Art(name, artistName, yearInt,selectedImage.value?: "")
        insertArt(art)
        setSelectedImage("")
        insertArt.postValue(Resource.success(art))
    }
}