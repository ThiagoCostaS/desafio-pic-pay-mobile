package com.thiagoc.desafiopicpay.usecase

import com.nhaarman.mockitokotlin2.verify
import com.thiagoc.desafiopicpay.domain.UserRepositoryLocal
import com.thiagoc.desafiopicpay.domain.usecases.SaveUsersLocalUseCase
import com.thiagoc.desafiopicpay.factory.UsersFactory.usersList
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito

class SaveUsersLocalUseCaseTest {


    private lateinit var repository: UserRepositoryLocal
    private lateinit var saveUsersUseCase: SaveUsersLocalUseCase

    @Before
    fun setup() {
        repository = Mockito.mock(UserRepositoryLocal::class.java)
        saveUsersUseCase = SaveUsersLocalUseCase(repository)
    }


    @Test
    fun `when saveUsers is invoked, then save users`() = runBlocking {
        saveUsersUseCase(usersList)

        verify(repository).saveUsers(usersList)
    }
}