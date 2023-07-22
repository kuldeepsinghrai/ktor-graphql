package com.kuldeepsinghrai.graphql

import com.apurebase.kgraphql.schema.dsl.SchemaBuilder
import com.kuldeepsinghrai.models.Dessert
import com.kuldeepsinghrai.models.DessertInput
import com.kuldeepsinghrai.respository.DessertRepository

fun SchemaBuilder.dessertSchema(){
    val repository = DessertRepository()

    inputType<DessertInput> {
        description = "The input of the dessert without the identifier"
    }

    type<Dessert>(){
        description = "Dessert object with attributes name, description and imageurl"
    }

    query("dessert"){
        resolver {dessertId:String->
            try {
                repository.getById(dessertId)
            }catch (e:Exception){
                null
            }
        }
    }
}