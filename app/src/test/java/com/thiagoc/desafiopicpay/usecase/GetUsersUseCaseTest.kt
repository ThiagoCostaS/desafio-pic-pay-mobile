package com.thiagoc.desafiopicpay.usecase

import com.thiagoc.desafiopicpay.domain.UserRepository
import com.thiagoc.desafiopicpay.domain.usecases.GetUsersUseCase
import com.thiagoc.desafiopicpay.factory.UsersFactory.usersList
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`
import kotlin.test.assertEquals
import kotlin.test.fail

class GetUsersUseCaseTest {

    private lateinit var repository: UserRepository
    private lateinit var useCase: GetUsersUseCase

    @Before
    fun setup() {
        repository = mock(UserRepository::class.java)
        useCase = GetUsersUseCase(repository)
    }

    @Test
    fun `when getUsers is invoked, then return a list of users`() = runBlocking {

        `when`(repository.getUsers()).thenReturn(usersList)

        val result = useCase()

        assertEquals(usersList, result)
    }

    @Test(expected = Exception::class)
    fun `when getUsers throws an exception, then rethrow the exception`() = runBlocking {

        `when`(repository.getUsers()).thenThrow(Exception("Error"))

        try {
            useCase()
            fail("Expected an exception to be thrown")
        } catch (e: Exception) {
            assertEquals("Error", e.message)
        }
    }
}
