package com.faztbit.alwaopportunity.domain.models

import com.faztbit.alwaopportunity.data.database.models.PriorityRoom

data class MachineDomain(
    val id: Int,
    val name: String,
    val priority: String
)
