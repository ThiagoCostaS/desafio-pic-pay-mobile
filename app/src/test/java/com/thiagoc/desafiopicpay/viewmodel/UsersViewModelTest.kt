package com.thiagoc.desafiopicpay.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.thiagoc.desafiopicpay.data.usecases.GetUsersUseCase
import com.thiagoc.desafiopicpay.domain.UserDomain
import com.thiagoc.desafiopicpay.presentation.viewmodel.UserListViewAction
import com.thiagoc.desafiopicpay.presentation.viewmodel.UserListViewState
import com.thiagoc.desafiopicpay.presentation.viewmodel.UsersViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`
import kotlin.test.assertEquals

class UsersViewModelTest {

    private lateinit var getUsersUseCase: GetUsersUseCase
    private lateinit var viewModel: UsersViewModel

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @OptIn(ExperimentalCoroutinesApi::class)
    @get:Rule
    val testCoroutineRule = MainCoroutineRule()

    @Before
    fun setUp() {
        getUsersUseCase = mock(GetUsersUseCase::class.java)
        viewModel = UsersViewModel(getUsersUseCase)
    }

    @Test
    fun `when dispatchAction GetUsers is called and use case returns users, then update view state with ShowUsers`() {
        testCoroutineRule.testDispatcher.runBlockingTest {
            // given
            val userList = listOf(
                UserDomain("1", "John", 3, "https://example.com/john-doe.jpg"),
                UserDomain("2", "Jane", 4, "https://example.com/jane-doe.jpg")
            )
            `when`(getUsersUseCase()).thenReturn(userList)

            // when
            viewModel.dispatchAction(UserListViewAction.GetUsers)

            // then
            val expectedState = UserListViewState.ShowUsers(userList)
            assertEquals(viewModel.viewState.value, expectedState)
        }
    }

    @Test
    fun `when dispatchAction GetUsers is called and use case throws an exception, then update view state with Error`() {
        testCoroutineRule.testDispatcher.runBlockingTest {
            // given
            val errorMessage = "Failed to load users"
            `when`(getUsersUseCase()).thenThrow(Exception(errorMessage))

            // when
            viewModel.dispatchAction(UserListViewAction.GetUsers)

            // then
            val expectedState = UserListViewState.Error(errorMessage)
            assertEquals(viewModel.viewState.value, expectedState)
        }
    }

}