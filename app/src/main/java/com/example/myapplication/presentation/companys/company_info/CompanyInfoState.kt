package com.example.myapplication.presentation.companys.company_info

import com.example.myapplication.domain.model.CompanyInfo
import com.example.myapplication.domain.model.IntradayInfo

data class CompanyInfoState(
    val stockInfos: List<IntradayInfo> = emptyList(),
    val company: CompanyInfo? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)