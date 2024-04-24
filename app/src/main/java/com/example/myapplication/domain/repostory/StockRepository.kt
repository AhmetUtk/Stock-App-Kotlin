package com.example.myapplication.domain.repostory

import androidx.room.Query
import com.example.myapplication.domain.model.CompanyListing
import com.example.myapplication.util.Resource

interface StockRepository {

    suspend fun getCompanyListing(
        fetchFromRemote : Boolean,
        query: String
    ): kotlinx.coroutines.flow.Flow<Resource<List<CompanyListing>>>

}