package com.example.myapplication.domain.repostory

import com.example.myapplication.domain.model.CompanyInfo
import com.example.myapplication.domain.model.CompanyListing
import com.example.myapplication.domain.model.IntradayInfo
import com.example.myapplication.util.Resource
import kotlinx.coroutines.flow.Flow

interface StockRepository {


    suspend fun getIntradayInfo(
        symbol: String
    ): Resource<List<IntradayInfo>>

    suspend fun getCompanyInfo(
        symbol: String
    ): Resource<CompanyInfo>

    suspend fun getCompanyListing(
        fetchFromRemote: Boolean,
        query: String
    ): Flow<Resource<List<CompanyListing>>>
}