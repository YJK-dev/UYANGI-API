package com.example.uyangi.domain.auth

import com.example.uyangi.common.UyangiException
import com.example.uyangi.domain.user.User
import com.example.uyangi.domain.user.UserRepository
import org.mindrot.jbcrypt.BCrypt
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class SigninService @Autowired constructor(
    private val userRepository: UserRepository
) {

    fun signin(signinRequest: SigninRequest): SigninResponse {
        val user = userRepository
            .findByEmail(signinRequest.email.toLowerCase())
            ?: throw UyangiException("로그인 정보를 확인해주세요.")

        if (isNotValidPassword(signinRequest.password, user.password)) {
            throw UyangiException("로그인 정보를 확인해주세요.")
        }

        user.fcmToken = signinRequest.fcmToken
        userRepository.save(user)

        return responseWithTokens(user)
    }

    private fun isNotValidPassword(
        plain: String,
        hashed: String
    ) = BCrypt.checkpw(plain, hashed).not()

    private fun responseWithTokens(user: User) = user.id?.let { userId ->
        SigninResponse(
            JWTUtil.createToken(user.email),
            JWTUtil.createRefreshToken(user.email),
            user.name,
            userId
        )
    } ?: throw IllegalStateException("user.id 없음.")

}