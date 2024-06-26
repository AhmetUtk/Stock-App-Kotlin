package com.example.myapplication.data.mapper

import com.example.myapplication.data.local.CompanyListingEntitiy
import com.example.myapplication.data.remote.dto.CompanyInfoDto
import com.example.myapplication.domain.model.CompanyInfo
import com.example.myapplication.domain.model.CompanyListing

fun CompanyListingEntitiy.toCompanyListing(): CompanyListing{
    return CompanyListing(
        name= name,
        symbol=symbol,
        exchange = exchange
    )
}

fun CompanyListing.toCompanyListingEntitiy(): CompanyListingEntitiy{
    return CompanyListingEntitiy(
        name= name,
        symbol=symbol,
        exchange = exchange
    )
}

fun CompanyInfoDto.toCompanyInfo(): CompanyInfo {
    return CompanyInfo(
        symbol = symbol ?: "",
        description = description ?: "",
        name = name ?: "",
        country = country ?: "",
        industry = industry ?: ""
    )
}