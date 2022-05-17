import com.example.uyangi.common.ApiResponse
import com.example.uyangi.domain.auth.SignupRequest
import com.example.uyangi.domain.auth.SignupService
// import com.example.uyangi.domain.auth.UserContextHolder
// import com.example.uyangi.domain.user.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1")
class UserApiController @Autowired constructor(
    private val signupService: SignupService,
    // private val userService: UserService,
   //  private val userContextHolder: UserContextHolder
) {

    @PostMapping("/users")
    fun signup(@RequestBody signupRequest: SignupRequest) =
        ApiResponse.ok(signupService.signup(signupRequest))

//    @PutMapping("/users/fcm-token")
//    fun updateFcmToken(@RequestBody fcmToken: String) =
//        userContextHolder.id?.let { userId ->
//            ApiResponse.ok(userService.updateFcmToken(userId, fcmToken))
//        } ?: ApiResponse.error("토큰 갱신 실패")

}