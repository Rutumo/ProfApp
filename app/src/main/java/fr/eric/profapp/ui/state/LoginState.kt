package fr.eric.profapp.ui.state

data class LoginState(
    val username : String = "",
    val password : String = "",
    val error: Boolean = false,
    val errorMessage: String = ""
)
