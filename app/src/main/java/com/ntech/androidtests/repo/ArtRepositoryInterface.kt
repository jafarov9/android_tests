package com.ntech.androidtests.repo

import androidx.lifecycle.LiveData
import com.ntech.androidtests.model.ImageResponse
import com.ntech.androidtests.roomdb.Art
import com.ntech.androidtests.util.Resource

interface ArtRepositoryInterface {

    suspend fun insertArt(art: Art)

    suspend fun deleteArt(art: Art)

    fun getArt() : LiveData<List<Art>>

    suspend fun searchImage(imageString: String) : Resource<ImageResponse>
}