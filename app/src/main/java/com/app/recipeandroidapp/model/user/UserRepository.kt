package com.app.recipeandroidapp.model.user


class UserRepository constructor(var dao: UserDao) {
    var users = dao.getAll()

    fun add(user: User){
        dao.insert(user)
        users = dao.getAll()
    }
    fun update(user: User){
        dao.update(user)
        users = dao.getAll()
    }
}