package fi.fimurito.mytimer.data

import fi.fimurito.mytimer.ui.login.LoginViewModelFactory

class LoginContainer(val userRepository: UserRepository) {
    val loginData = LoginUserData()

    val loginViewModelFactory = LoginViewModelFactory(userRepository)
}