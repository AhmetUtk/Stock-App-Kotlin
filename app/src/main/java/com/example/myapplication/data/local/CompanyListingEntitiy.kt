package com.example.myapplication.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CompanyListingEntitiy(

    val name : String,
    val symbol : String,
    val exchange : String,
    @PrimaryKey val id : Int? = null

)
