package kr.co.woosuk.oauth.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import kr.co.woosuk.oauth.entity.User;
import kr.co.woosuk.oauth.service.UserService;

@RestController
@RequestMapping("/create")
public class DefaultController {

	@Autowired
	private UserService userService;
	
	@RequestMapping(value = "/user", method = RequestMethod.POST)
	@ApiOperation(value=" 사용자 등록 ")
    @ApiImplicitParams(value={
    		@ApiImplicitParam(name="username", dataType="string", paramType="query", value=" 유저 아이디 "), 
    		@ApiImplicitParam(name="password", dataType="string", paramType="query", value=" 유저 비밀번호 ")}
    )
	public User create(User user) throws Exception{
		return userService.createUser(user);
	}
	
}
