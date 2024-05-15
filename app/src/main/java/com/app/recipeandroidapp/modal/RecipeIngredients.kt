package com.app.recipeandroidapp.modal

import com.google.gson.annotations.SerializedName

data class IngredienteBase(
    @SerializedName("id")
    val id: Int,
    @SerializedName("nomesIngrediente")
    val nomesIngrediente: List<String>,
    @SerializedName("receita_id")
    val receitaId: Int
)