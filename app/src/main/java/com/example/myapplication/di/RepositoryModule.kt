package com.example.myapplication.di


import com.example.myapplication.data.csv.CSVParser
import com.example.myapplication.data.csv.CompanyListingsParser
import com.example.myapplication.data.csv.IntradayInfoParser
import com.example.myapplication.data.repostory.StockRepositoryImpl
import com.example.myapplication.domain.model.CompanyListing
import com.example.myapplication.domain.model.IntradayInfo
import com.example.myapplication.domain.repostory.StockRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindCompanyListingsParser(
        companyListingsParser: CompanyListingsParser
    ): CSVParser<CompanyListing>

    @Binds
    @Singleton
    abstract fun bindIntradayInfoParser(
        intradayInfoParser: IntradayInfoParser
    ): CSVParser<IntradayInfo>

    @Binds
    @Singleton
    abstract fun bindStockRepository(
        stockRepositoryImpl: StockRepositoryImpl
    ): StockRepository
}