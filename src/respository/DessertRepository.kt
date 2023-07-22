package com.kuldeepsinghrai.respository

import com.kuldeepsinghrai.data.desserts
import com.kuldeepsinghrai.models.Dessert
import java.lang.Exception

class DessertRepository:RepositoryInterface<Dessert> {
    override fun getById(id: String): Dessert {
        return try {
            desserts.find { it.id ==id }?:throw Exception("No dessert with that ID exist")
        }catch (e:Exception){
            throw Exception("Cannot find dessert")
        }
    }

    override fun getAll(): List<Dessert> {
        return desserts
    }

    override fun delete(id: String): Boolean {
        return try {
           val dessert = desserts.find { it.id ==id }?:throw Exception("No dessert with that ID exist")
            desserts.remove(dessert)
            true
        }catch (e:Exception){
            throw Exception("Cannot find dessert")
        }
    }

    override fun update(entry: Dessert): Dessert {
        return try {
            val dessert = desserts.find { it.id ==entry.id }?.apply {
                name = entry.name
                description = entry.description
                imageUrl = entry.imageUrl
            }?:throw Exception("No dessert with that ID exist")
            dessert
        }catch (e:Exception){
            throw Exception("Cannot find dessert")
        }
    }

    override fun add(entry: Dessert): Dessert {
        desserts.add(entry)
        return entry
    }

}