<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

	<script type="text/javascript">
		$(document).ready(function(){
			
			$("#btnLogin").click(function(){
				login();
			});
			
			$("#searchUserId").click(function () {
	            $("#searchUserIdModal").modal();
	        });

	        $("#searchUserPwd").click(function () {
	            $("#searchUserPwdModal").modal();
	        });

	        $("input:text[numberOnly]").on("keyup", function () {
	            $(this).val($(this).val().replace(/[^0-9]/g, ""));
	        });
	        
		});
		
		function login(){
			
			var userId = $("#userId").val();
			var userPassword = $("#userPassword").val();
			var returnUrl = $("#returnUrl").val();
			
			var param = {'userId' : userId, 'userPassword': userPassword, 'siteCd': '016001', 'returnUrl':returnUrl};
			
			$.ajax({
				
				url: '/v1/api/login',
				method: 'post',
				data: param,
				success:function(data){
					if(data.success){
						create_cookie(data.tokenId,userId,userPassword);
						//$("#tokenId").val(data.tokenId);
						//$("#successForm").submit();
					}else{
						alert(data.message);
					}	
				}
			});
		}
		
		function fn_search_user_id() {
	        
	        var employee_no = $("#employeeNo").val();
	        var user_name = $("#userName").val();

	        if (employee_no == "") {
	            alert("사번을 입력해주세요.");
	            return false;
	        }

	        if (user_name == "") {
	            alert("이름을 입력해주세요.");
	            return false;
	        }

	        var param = {
	            employeeNo: employee_no,
	            userNm: user_name
	        }

	        $.ajax({
	            url: '/v1/api/user/id',
	            type: 'GET',
	            data: param,
	            success: function (data) {
	                if (data.success) {
	                    alert(data.userId);
	                    $("#searchUserIdModal").modal('hide');
	                } else {
	                    alert(data.message);
	                }
	            },
	            error: function (request, status, error) {
	                alert("code:" + request.status + "\n" + "message:" + request.responseText + "\n" + "error:" + error);
	                return false;
	            }
	        });

	        modal_clear();
	        
	    }

	    function fn_search_user_pwd() {

	        var user_id = $("#userId2").val();
	        var employee_no = $("#employeeNo2").val();
	        var user_name = $("#userName2").val();
	        var user_phone = $("#phoneNum").val();

	        if (user_id == "" || user_id == null) {
	            alert("아이디를 입력해주세요.");
	            return false;
	        }

	        if (employee_no == "" || employee_no == null) {
	            alert("사번을 입력해주세요.");
	            return false;
	        }

	        if (user_name == "" || user_name == null) {
	            alert("이름을 입력해주세요.");
	            return false;
	        }

	        if (user_phone == "" || user_phone == null) {
	            alert("휴대폰 번호를 입력해주세요.");
	            return false;
	        } else {
	            if (!checkPhoneNumber(user_phone)) {
	                alert("유효하지 않는 휴대폰 번호 입니다.");
	                return false;
	            }
	        }
	        
	        var param = {
	            userId : user_id,
	            employeeNo: employee_no,
	            userNm: user_name,
	            mobileNm: user_phone
	        }

	        $.ajax({
	            url: '/v1/api/user/pwd',
	            type: 'GET',
	            data: param,
	            success: function (data) {
	                if (data.success) {
	                    alert(data.message); 
	                    $("#searchUserPwdModal").modal('hide');
	                } else {
	                    alert(data.message);
	                }

	            },
	            error: function (request, status, error) {
	                alert("code:" + request.status + "\n" + "message:" + request.responseText + "\n" + "error:" + error);
	                return false;
	            }
	        });

	        modal_clear();

	    }

	    function modal_clear() {

	        $("#employeeNo").val("");
	        $("#userName").val("");

	        $("#userId2").val("");
	        $("#employeeNo2").val("");
	        $("#userName2").val("");
	        $("#phoneNum").val("");
	    }

	    function checkPhoneNumber(num) {
	        var regExp = /(01[016789])([1-9]{1}[0-9]{3,4})([0-9]{4})$/;

	        var myArray;

	        if (regExp.test(num)) {
	            myArray = regExp.exec(num);
	            return true;
	        } else {
	            return false;
	        }
	  
	    }
	    
	    function create_cookie(tokenVal, userId, userPwd){
	    	
	    	var param = {'tokenId' : tokenVal, 'userId': userId, 'userPwd': userPwd};
	    	
			$.ajax({
				url: 'http://ebeduadmin.hunet.co.kr/Login/CreateCookie',
				method: 'post',
				data: param,
				dataType: 'json',
				success:function(data){
					if(data.FLAG){
						$("#tokenId").val(data.TOKEN_ID);
						$("#adminId").val(userId);
						$("#adminPwd").val(userPwd);
						$("#successForm").submit();
					}else{
						alert(data.message);
					}	
				}
			});	
	    }
	    
	</script>
	
	<style type="text/css">
	  .modal-header, h4, .close {
	    background-color: #5cb85c;
	    color:white !important;
	    text-align: center;
	    font-size: 30px;
	  }
	  .modal-footer {
	    background-color: #f9f9f9;
	  }
	  
	  .modal-open {
	    overflow:auto;
	    padding-right:0 !important;
	  }
	  
	  /* label{
	      color:#151414;
	      font-size:14px;
	  } */
	</style>

	<!-- container -->
	<form id="successForm" method="post" action="${returnUrl}">
		<input type="hidden" id="tokenId" name="tokenId" value=""/>
		<input type="hidden" id="adminId" name="adminId" value=""/>
		<input type="hidden" id="adminPwd" name="adminPwd" value=""/>
	</form>
	
	<input type="hidden" id="returnUrl" name="returnUrl" value="${returnUrl}"/>
	<div class="login_2019">
		<div class="login-field">
			<ul>
				<li><input type="text" id="userId" name="userId" style="ime-mode:inactive" placeholder="아이디를 입력하세요."/></li>
				<li><input type="password" id="userPassword" name="userPassword" placeholder="비밀번호를 입력하세요."/></li>
				<li class="save">
					<a href="#" id="searchUserId" >아이디 찾기</a> &nbsp; | &nbsp; <a href="#" id="searchUserPwd">비밀번호 찾기</a>
				</li>
			</ul>
			<a href="#" id="btnLogin">
				<img src="http://img.hunet.co.kr/gw/login/2016/btn-login.png" class="btn"/>
			</a>
		</div>
	</div>
	
	<div class="container">
	    <div id="searchUserIdModal" class="modal fade" role="dialog">
	        <div class="modal-dialog">
	    
	            <!-- Modal content-->
	            <div class="modal-content">
	      
	                <div class="modal-header" style="padding:35px 50px;">
	                    <h4><span class="glyphicon glyphicon-lock"></span> 아이디 찾기</h4>
	                    <button type="button" class="close" data-dismiss="modal">&times;</button>
	                </div>
	                <div class="modal-body" style="padding:40px 50px;">
	          
	                    <form role="form">
	                        <div class="form-group">
	                            <label for="usrname"><span class="glyphicon glyphicon-circle-arrow-right"></span> 사번</label>
	                            <input type="text" class="form-control" id="employeeNo" placeholder="사번">
	                        </div>
	                        <div class="form-group">
	                            <label for="psw"><span class="glyphicon glyphicon-circle-arrow-right"></span> 이름</label>
	                            <input type="text" class="form-control" id="userName" placeholder="이름">
	                        </div>
	                        
	                        <button type="submit" onclick="fn_search_user_id(); return false;" class="btn btn-success btn-block"><span class="glyphicon glyphicon-off"></span> 찾기 </button>
	                    </form>
	                </div>
	            </div>
	        </div>
	    </div>
	
	    <div id="searchUserPwdModal" class="modal fade" role="dialog">
	        <div class="modal-dialog">
	    
	            <!-- Modal content-->
	            <div class="modal-content">
	      
	                <div class="modal-header" style="padding:35px 50px;">
	                    <h4><span class="glyphicon glyphicon-lock"></span> 비밀번호 찾기</h4>
	                    <button type="button" class="close" data-dismiss="modal">&times;</button>
	                </div>
	                <div class="modal-body" style="padding:40px 50px;">
	          
	                    <form role="form">
	                        <div class="form-group">
	                            <label for="usrname"><span class="glyphicon glyphicon-circle-arrow-right"></span> 아이디</label>
	                            <input type="text" class="form-control" id="userId2" placeholder="id">
	                        </div>
	                        <div class="form-group">
	                            <label for="usrname"><span class="glyphicon glyphicon-circle-arrow-right"></span> 사번</label>
	                            <input type="text" class="form-control" id="employeeNo2" placeholder="사번">
	                        </div>
	                        <div class="form-group">
	                            <label for="psw"><span class="glyphicon glyphicon-circle-arrow-right"></span> 이름</label>
	                            <input type="text" class="form-control" id="userName2" placeholder="이름">
	                        </div>
	                        <div class="form-group">
	                            <label for="psw"><span class="glyphicon glyphicon-circle-arrow-right"></span> 휴대폰번호</label>
	                            <input type="text" class="form-control" id="phoneNum" placeholder="휴대폰번호" maxlength="11" numberOnly/>
	                        </div>
	                        
	                        <button type="submit" onclick="fn_search_user_pwd(); return false;" class="btn btn-success btn-block"><span class="glyphicon glyphicon-off"></span> 찾기 </button>
	                    </form>
	                </div>
	            </div>
	        </div>
	    </div>
	</div>
