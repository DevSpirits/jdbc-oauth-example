package kr.co.woosuk.oauth.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import kr.co.woosuk.oauth.entity.CustomClientDetails;
import kr.co.woosuk.oauth.service.ClientService;

@RestController
@RequestMapping("/oauth")
public class ClientController {

	@Autowired
	private ClientService clientService;
	
	@RequestMapping(value="/client", method=RequestMethod.POST)
	@ApiOperation(value=" Client 등록 ")
    @ApiImplicitParams(value={
    		@ApiImplicitParam(name="clientId", dataType="string", paramType="query", value=" client 아이디 "), 
    		@ApiImplicitParam(name="clientSecret", dataType="string", paramType="query", value=" client 패스워드 "),
    		@ApiImplicitParam(name="scope", dataType="string", paramType="query", value=" 권한 "),
    		@ApiImplicitParam(name="authorizedGrantTypes", dataType="string", paramType="query", value=" 인증 타입 "),
    		@ApiImplicitParam(name="authorities", dataType="string", paramType="query", value=" 권한명칭 "),
    		@ApiImplicitParam(name="accessTokenValidity", dataType="string", paramType="query", value=" 엑세스 토큰 유지 시간 "),
    		@ApiImplicitParam(name="refreshTokenValidity", dataType="string", paramType="query", value=" 리프래시 토큰 유지 시간 ")
    })
	public CustomClientDetails createClientDetailInfo(CustomClientDetails clientDetails) throws Exception{
		
		return clientService.createClientDetail(clientDetails);
	
	}
}
