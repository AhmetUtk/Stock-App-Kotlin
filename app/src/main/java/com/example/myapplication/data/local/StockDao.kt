package com.example.myapplication.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface StockDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCompanyListings(
        companyListingEntities : List<CompanyListingEntitiy>
    )

    @Query("DELETE FROM companylistingentitiy")
    suspend fun clearCompanyListings()

    @Query(
        """
        SELECT * 
        FROM companylistingentitiy
        WHERE LOWER(name) like '%' || LOWER(:query) || '%' OR
        UPPER(:query) == symbol
    """
    )
    suspend fun searchCompanyListing(query: String): List<CompanyListingEntitiy>

}