<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<%@ include file="common/common.jsp" %>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>登录 - ${title }</title>
</head>

<script type="text/javascript">

function mySubmit(){
	
	var username = $.trim($('#username').val());
	var pwd = $.trim($('#pwd').val());
	
	if(!username){
		layer.alert('请输入账号');
		return;
	}
	
	if(!pwd){
		layer.aletr('请输入密码');
		return;
	}
	
	$('#mySubmit').submit();
}

</script>

<body>
    <div class="J_loginMain">
        <div class="l_inner">
        	<form action="${ctx }/do_login" method="post" id="mySubmit">
	            <div class="i_main">
	                <div class="m_txt">
	                	<c:choose>
	                		<c:when test="${not empty msg }">${msg }</c:when>
	                		<c:otherwise>任信花后台管理系统</c:otherwise>
	                	</c:choose>
	                </div>
	                <div class="m_input">
	                    <input placeholder="请输入帐号" type="text" name="username" id="username"/>
	                </div>
	                <div class="m_input">
	                    <input placeholder="请输入密码" type="password" name="pwd" id="pwd"/>
	                </div>
					<div class="m_input">
						<input type="text" id="code_input" value="" placeholder="请输入验证码"/>
					</div>
					<div id="v_container" style="width: 200px;height: 50px;margin-top: 15px"></div>
	                <div class="m_btn">
	                    <a id="my_button">登录</a>
	                </div>
	            </div>
            </form>
        </div>
    </div>
	<%@ include file="common/footer.jsp" %>
</body>
</html>

<script src="${ctx }/static/plugins/yzm/gVerify.js"></script>
<script>
    var verifyCode = new GVerify("v_container");

    document.getElementById("my_button").onclick = function(){
        var res = verifyCode.validate(document.getElementById("code_input").value);
        //mySubmit();
        if(res){
            mySubmit();
        }else{
            alert("验证码错误");
        }
    }

    $(function(){

        document.onkeydown = function(e){
            var ev = document.all ? window.event : e;
            if(ev.keyCode==13) {
                var res = verifyCode.validate(document.getElementById("code_input").value);
                //mySubmit();
                if(res){
                    mySubmit();
                }else{
                    alert("验证码错误");
                }
            }
        }

    });
</script>