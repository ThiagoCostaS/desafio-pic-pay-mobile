package com.thiagoc.desafiopicpay.usecase

import com.thiagoc.desafiopicpay.domain.UserRepositoryLocal
import com.thiagoc.desafiopicpay.domain.usecases.GetUsersLocalUseCase
import com.thiagoc.desafiopicpay.factory.UsersFactory.usersList
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import kotlin.test.assertEquals
import kotlin.test.fail

class GetUsersLocalUseCaseTest {

    private lateinit var repository: UserRepositoryLocal
    private lateinit var useCase: GetUsersLocalUseCase

    @Before
    fun setup() {
        repository = Mockito.mock(UserRepositoryLocal::class.java)
        useCase = GetUsersLocalUseCase(repository)
    }


    @Test
    fun `when getUsers is invoked, then return a list of users`() = runBlocking {

        Mockito.`when`(repository.getUsers()).thenReturn(usersList)

        val result = useCase()

        assertEquals(usersList, result)
    }

    @Test(expected = Exception::class)
    fun `when getUsers throws an exception, then rethrow the exception`() = runBlocking {
        val expectedMessage = "Error"
        Mockito.`when`(repository.getUsers()).thenThrow(Exception(expectedMessage))

        try {
            useCase()
            fail("Expected an exception to be thrown")
        } catch (e: Exception) {
            assertEquals(expectedMessage, e.message)
        }
    }
}
