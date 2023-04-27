package com.thiagoc.desafiopicpay.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.thiagoc.desafiopicpay.domain.usecases.GetUsersLocalUseCase
import com.thiagoc.desafiopicpay.domain.usecases.GetUsersUseCase
import com.thiagoc.desafiopicpay.domain.usecases.SaveUsersLocalUseCase
import com.thiagoc.desafiopicpay.factory.UsersFactory.usersList
import com.thiagoc.desafiopicpay.presentation.viewmodel.UserListViewAction
import com.thiagoc.desafiopicpay.presentation.viewmodel.UserListViewState
import com.thiagoc.desafiopicpay.presentation.viewmodel.UsersViewModel
import com.thiagoc.desafiopicpay.viewmodel.livedatautils.getOrAwaitValue
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class UsersViewModelTest {

    @Mock
    private lateinit var getUsersUseCase: GetUsersUseCase
    @Mock
    private lateinit var getUsersLocalUseCase: GetUsersLocalUseCase
    @Mock
    private lateinit var saveUsersLocalUseCase: SaveUsersLocalUseCase

    private lateinit var viewModel: UsersViewModel


    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)

        viewModel = UsersViewModel(getUsersUseCase, getUsersLocalUseCase, saveUsersLocalUseCase)
    }

    @Test
    fun `dispatchAction should get users and update view state to ShowUsers`() = runBlocking {
        val expectedViewState = UserListViewState.ShowUsers(usersList)

        `when`(getUsersUseCase()).thenReturn(expectedViewState.users)
        `when`(getUsersLocalUseCase()).thenReturn(usersList)

        viewModel.dispatchAction(UserListViewAction.GetUsers)

        delay(100)
        val actualViewState = viewModel.viewState.getOrAwaitValue()

        assertEquals(expectedViewState, actualViewState)
    }

    @Test
    fun `dispatchAction should update view state to Error if an exception is thrown`() =
        runBlocking {
            val expectedErrorMessage = "Unknown error"
            `when`(getUsersUseCase()).thenThrow(RuntimeException(expectedErrorMessage))

            viewModel.dispatchAction(UserListViewAction.GetUsers)

            delay(100)
            val actualViewState = viewModel.viewState.getOrAwaitValue()
            assertEquals(UserListViewState.Error(expectedErrorMessage), actualViewState)
        }
}