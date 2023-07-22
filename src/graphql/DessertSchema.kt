package com.kuldeepsinghrai.graphql

import com.apurebase.kgraphql.schema.dsl.SchemaBuilder
import com.kuldeepsinghrai.data.desserts
import com.kuldeepsinghrai.models.Dessert
import com.kuldeepsinghrai.models.DessertInput
import com.kuldeepsinghrai.respository.DessertRepository

fun SchemaBuilder.dessertSchema() {
    val repository = DessertRepository()

    inputType<DessertInput> {
        description = "The input of the dessert without the identifier"
    }

    type<Dessert>() {
        description = "Dessert object with attributes name, description and imageurl"
    }

    query("dessert") {
        resolver { dessertId: String ->
            try {
                repository.getById(dessertId)
            } catch (e: Exception) {
                null
            }
        }
    }

    query("desserts") {
        resolver { ->
            try {
                repository.getAll();
            } catch (e: Exception) {
                emptyList<Dessert>()
            }
        }
    }

    mutation("createDessert") {
        description = "Create a new dessert"
        resolver { dessertInput: DessertInput ->
            try {
                val uid = java.util.UUID.randomUUID().toString()
                val dessert = Dessert(uid, dessertInput.name, dessertInput.description, dessertInput.description)
                repository.add(dessert)
            } catch (e: Exception) {
                null
            }
        }
    }

    mutation("deleteDessert") {
        resolver { dessertId: String ->
            try {
                repository.delete(dessertId)
            } catch (e: Exception) {
                null
            }
        }
    }

    mutation("updateDessert") {
        resolver { dessertId:String,dessertInput: DessertInput ->
            try {
                val dessert = Dessert(dessertId,dessertInput.name,dessertInput.description,dessertInput.imageUrl)
                repository.update(dessert)
                dessert
            } catch (e: Exception) {
                null
            }
        }
    }


}