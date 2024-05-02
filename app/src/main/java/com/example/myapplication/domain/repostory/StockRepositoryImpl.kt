package com.example.myapplication.domain.repostory

import android.net.http.HttpException
import com.example.myapplication.data.csv.CSVParser
import com.example.myapplication.data.local.StockDatabase
import com.example.myapplication.data.mapper.toCompanyListing
import com.example.myapplication.data.mapper.toCompanyListingEntitiy
import com.example.myapplication.data.remote.StockApi
import com.example.myapplication.domain.model.CompanyInfo
import com.example.myapplication.domain.model.CompanyListing
import com.example.myapplication.domain.model.IntradayInfo
import com.example.myapplication.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class StockRepositoryImpl @Inject constructor(
    val api : StockApi,
    val db : StockDatabase,
    private val companyListingsParser: CSVParser<CompanyListing>,
    private val intradayInfoParser: CSVParser<IntradayInfo>,

    ): StockRepository {
    private val dao = db.dao
    
    override suspend fun getCompanyListing(
        fetchFromRemote: Boolean,
        query: String
    ): Flow<Resource<List<CompanyListing>>> {
        return flow {
            emit(Resource.Loading(true))
            val localListing = dao.searchCompanyListing(query)
            emit(Resource.Success(
                data = localListing.map { it.toCompanyListing() }
            ))

            val isDbEmpty = localListing.isEmpty() && query.isBlank()
            val shouldJustLoadFromCache =!isDbEmpty && !fetchFromRemote
            if (shouldJustLoadFromCache){
                emit(Resource.Loading(false))
                return@flow
            }
            val remoteListings = try {
                val response = api.getListings()
                companyListingsParser.parse(response.byteStream())


            }catch (e : IOException){
                e.printStackTrace()
                emit(Resource.Error("Couldn't load data"))
                null

            }catch (e : retrofit2.HttpException){
                e.printStackTrace()
                emit(Resource.Error("Couldn't load data"))
                null

            }

            remoteListings?.let { listings ->
                dao.clearCompanyListings()
                dao.insertCompanyListings(
                    listings.map { it.toCompanyListingEntitiy() }
                )
                emit(Resource.Success(
                    data = dao
                        .searchCompanyListing("")
                        .map { it.toCompanyListing() }
                ))
                emit(Resource.Loading(false))
            }

            }
        }

    override suspend fun getIntradayInfo(symbol: String): Resource<List<IntradayInfo>> {
        TODO("Not yet implemented")
    }

    override suspend fun getCompanyInfo(symbol: String): Resource<CompanyInfo> {
        TODO("Not yet implemented")
    }
}

