package com.app.recipeandroidapp.modal

import com.google.gson.annotations.SerializedName

data class Recipe(
    @SerializedName("id")
    val id: Int,
    @SerializedName("receita")
    val nomeReceita: String,
    @SerializedName("ingredientes")
    val ingredientes: String,
    @SerializedName("modo_preparo")
    val modoPreparo: String,
    @SerializedName("link_imagem")
    val linkImagem: String,
    @SerializedName("tipo")
    val tipo: String,
    @SerializedName("created_at")
    val dataCriacao: String,
    @SerializedName("IngredientesBase")
    val ingredientesBase: List<IngredienteBase>
)

