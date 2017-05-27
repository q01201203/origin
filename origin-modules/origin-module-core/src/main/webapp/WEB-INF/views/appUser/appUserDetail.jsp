<%@ page language="java" pageEncoding="utf-8" contentType="text/html;charset=utf-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%@ include file="../common/common.jsp" %>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>App用户详情 - ${title }</title>
</head>
<body <%@ include file="../common/skin.jsp" %>>
	<%@ include file="../common/head.jsp" %>
    <%@ include file="../common/menu.jsp" %>
    <div class="J_content">
		<div class="mt20 plr20">
			<form action="${ctx }/user/ajax/save_pwd" method="post" id="myForm">
			<div class="J_formTable l_form_table">
				<table width="800px" class="not_hightlight">
					<tbody>
					<tr>
						<td class="l_title w200"><b class="cRed">*</b> 创建时间</td>
                         <td>
                             <div class="J_toolsBar fl">
                                 <div class="t_text w200 ml10">
									<input type="text" name="createDate"
										   value="<fmt:formatDate value="${appUser.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"/>
                                 </div>
                             </div>
                         </td>
                     </tr>
					 <tr>
						<td class="l_title "><b class="cRed">*</b> 更新时间</td>
                         <td>
                             <div class="J_toolsBar fl">
                                 <div class="t_text w200 ml10">
                                     <input type="text" name="updateDate"
											value="<fmt:formatDate value="${appUser.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"/>
                                 </div>
                             </div>
                         </td>
                     </tr>
					 <tr>
						<td class="l_title "><b class="cRed">*</b> 权限</td>
                         <td>
                             <div class="J_toolsBar fl">
                                 <div class="t_text w200 ml10">
                                     <input type="text" name="authority"  value="${appUser.authority}"/>
                                 </div>
                             </div>
                         </td>
                     </tr>
					<tr>
						<td class="l_title "><b class="cRed">*</b> 额度</td>
						<td>
							<div class="J_toolsBar fl">
								<div class="t_text w200 ml10">
									<input type="text" name="moneyMax"  value="${appUser.moneyMax}"/>
								</div>
							</div>
						</td>
					</tr>
					<tr>
						<td class="l_title "><b class="cRed">*</b> 支付宝名</td>
						<td>
							<div class="J_toolsBar fl">
								<div class="t_text w200 ml10">
									<input type="text" name="alipayUsername"  value="${appUser.alipayUsername}"/>
								</div>
							</div>
						</td>
					</tr>
					<tr>
						<td class="l_title "><b class="cRed">*</b> 支付宝账号</td>
						<td>
							<div class="J_toolsBar fl">
								<div class="t_text w200 ml10">
									<input type="text" name="alipayUseraccout"  value="${appUser.alipayUseraccout}"/>
								</div>
							</div>
						</td>
					</tr>
					<tr>
						<td class="l_title "><b class="cRed">*</b> 人脸图片地址</td>
						<td>
							<div class="J_toolsBar fl">
								<div class="t_text w200 ml10">
									<input type="text" name="imgFace"  value="${appUser.imgFace}"/>
								</div>
							</div>
						</td>
					</tr>
					<tr>
						<td class="l_title "><b class="cRed">*</b> 身份证正面图片地址</td>
						<td>
							<div class="J_toolsBar fl">
								<div class="t_text w200 ml10">
									<input type="text" name="imgIdFront"  value="${appUser.imgIdFront}"/>
								</div>
							</div>
						</td>
					</tr>
					<tr>
						<td class="l_title "><b class="cRed">*</b> 身份证背面图片地址</td>
						<td>
							<div class="J_toolsBar fl">
								<div class="t_text w200 ml10">
									<input type="text" name="imgIdBack"  value="${appUser.imgIdBack}"/>
								</div>
							</div>
						</td>
					</tr>
					<tr>
						<td class="l_title "><b class="cRed">*</b> 身份证名字</td>
						<td>
							<div class="J_toolsBar fl">
								<div class="t_text w200 ml10">
									<input type="text" name="userIdName"  value="${appUser.userIdName}"/>
								</div>
							</div>
						</td>
					</tr>
					<tr>
						<td class="l_title "><b class="cRed">*</b> 身份证号码</td>
						<td>
							<div class="J_toolsBar fl">
								<div class="t_text w200 ml10">
									<input type="text" name="userIdNumber"  value="${appUser.userIdNumber}"/>
								</div>
							</div>
						</td>
					</tr>
					<tr>
						<td class="l_title "><b class="cRed">*</b> 头像图片地址</td>
						<td>
							<div class="J_toolsBar fl">
								<div class="t_text w200 ml10">
									<input type="text" name="imgPortrait"  value="${appUser.imgPortrait}"/>
								</div>
							</div>
						</td>
					</tr>
					<tr>
						<td class="l_title "><b class="cRed">*</b> 昵称</td>
						<td>
							<div class="J_toolsBar fl">
								<div class="t_text w200 ml10">
									<input type="text" name="nickname"  value="${appUser.nickname}"/>
								</div>
							</div>
						</td>
					</tr>
					<tr>
						<td class="l_title "><b class="cRed">*</b> 群体</td>
						<td>
							<div class="J_toolsBar fl">
								<div class="t_text w200 ml10">
									<input type="text" name="category"  value="${appUser.category}"/>
								</div>
							</div>
						</td>
					</tr>
					<c:if test="${appUser.category eq 1}">
						<tr>
							<td class="l_title "><b class="cRed">*</b> 详细信息创建时间</td>
							<td>
								<div class="J_toolsBar fl">
									<div class="t_text w200 ml10">
										<input type="text" name="createDate"  value="${appStuDetail.createDate}"/>
									</div>
								</div>
							</td>
						</tr>
						<tr>
							<td class="l_title "><b class="cRed">*</b> 详细信息更新时间</td>
							<td>
								<div class="J_toolsBar fl">
									<div class="t_text w200 ml10">
										<input type="text" name="updateDate"  value="${appStuDetail.updateDate}"/>
									</div>
								</div>
							</td>
						</tr>
						<tr>
							<td class="l_title "><b class="cRed">*</b> 详细信息手机号</td>
							<td>
								<div class="J_toolsBar fl">
									<div class="t_text w200 ml10">
										<input type="text" name="infoMobile"  value="${appStuDetail.infoMobile}"/>
									</div>
								</div>
							</td>
						</tr>
						<tr>
							<td class="l_title "><b class="cRed">*</b> 学校</td>
							<td>
								<div class="J_toolsBar fl">
									<div class="t_text w200 ml10">
										<input type="text" name="infoSchool"  value="${appStuDetail.infoSchool}"/>
									</div>
								</div>
							</td>
						</tr>
						<tr>
							<td class="l_title "><b class="cRed">*</b> 院系</td>
							<td>
								<div class="J_toolsBar fl">
									<div class="t_text w200 ml10">
										<input type="text" name="infoDepartment"  value="${appStuDetail.infoDepartment}"/>
									</div>
								</div>
							</td>
						</tr>
						<tr>
							<td class="l_title "><b class="cRed">*</b> 班级</td>
							<td>
								<div class="J_toolsBar fl">
									<div class="t_text w200 ml10">
										<input type="text" name="infoClass"  value="${appStuDetail.infoClass}"/>
									</div>
								</div>
							</td>
						</tr>
						<tr>
							<td class="l_title "><b class="cRed">*</b> 宿舍楼号</td>
							<td>
								<div class="J_toolsBar fl">
									<div class="t_text w200 ml10">
										<input type="text" name="infoRoomnumber"  value="${appStuDetail.infoRoomnumber}"/>
									</div>
								</div>
							</td>
						</tr>
						<tr>
							<td class="l_title "><b class="cRed">*</b> 紧急联系人关系</td>
							<td>
								<div class="J_toolsBar fl">
									<div class="t_text w200 ml10">
										<input type="text" name="infoEmycontactRelation"  value="${appStuDetail.infoEmycontactRelation}"/>
									</div>
								</div>
							</td>
						</tr>
						<tr>
							<td class="l_title "><b class="cRed">*</b> 紧急联系人电话号码</td>
							<td>
								<div class="J_toolsBar fl">
									<div class="t_text w200 ml10">
										<input type="text" name="infoEmycontactMobile"  value="${appStuDetail.infoEmycontactMobile}"/>
									</div>
								</div>
							</td>
						</tr>
						<tr>
							<td class="l_title "><b class="cRed">*</b> 联系人关系</td>
							<td>
								<div class="J_toolsBar fl">
									<div class="t_text w200 ml10">
										<input type="text" name="infoContactRelation"  value="${appStuDetail.infoContactRelation}"/>
									</div>
								</div>
							</td>
						</tr>
						<tr>
							<td class="l_title "><b class="cRed">*</b> 联系人电话号码</td>
							<td>
								<div class="J_toolsBar fl">
									<div class="t_text w200 ml10">
										<input type="text" name="infoContactMobile"  value="${appStuDetail.infoContactMobile}"/>
									</div>
								</div>
							</td>
						</tr>
					</c:if>
					<c:if test="${appUser.category eq 2}">
						<tr>
							<td class="l_title "><b class="cRed">*</b> 详细信息创建时间</td>
							<td>
								<div class="J_toolsBar fl">
									<div class="t_text w200 ml10">
										<input type="text" name="createDate"  value="${appPersonDetail.createDate}"/>
									</div>
								</div>
							</td>
						</tr>
						<tr>
							<td class="l_title "><b class="cRed">*</b> 详细信息更新时间</td>
							<td>
								<div class="J_toolsBar fl">
									<div class="t_text w200 ml10">
										<input type="text" name="updateDate"  value="${appPersonDetail.updateDate}"/>
									</div>
								</div>
							</td>
						</tr>
						<tr>
							<td class="l_title "><b class="cRed">*</b> 详细信息收集号</td>
							<td>
								<div class="J_toolsBar fl">
									<div class="t_text w200 ml10">
										<input type="text" name="infoMobile"  value="${appPersonDetail.infoMobile}"/>
									</div>
								</div>
							</td>
						</tr>
						<tr>
							<td class="l_title "><b class="cRed">*</b> 公司名称</td>
							<td>
								<div class="J_toolsBar fl">
									<div class="t_text w200 ml10">
										<input type="text" name="infoCompanyName"  value="${appPersonDetail.infoCompanyName}"/>
									</div>
								</div>
							</td>
						</tr>
						<tr>
							<td class="l_title "><b class="cRed">*</b> 公司地址</td>
							<td>
								<div class="J_toolsBar fl">
									<div class="t_text w200 ml10">
										<input type="text" name="infoCompanyAddress"  value="${appPersonDetail.infoCompanyAddress}"/>
									</div>
								</div>
							</td>
						</tr>
						<tr>
							<td class="l_title "><b class="cRed">*</b> 常用QQ</td>
							<td>
								<div class="J_toolsBar fl">
									<div class="t_text w200 ml10">
										<input type="text" name="infoQq"  value="${appPersonDetail.infoQq}"/>
									</div>
								</div>
							</td>
						</tr>
						<tr>
							<td class="l_title "><b class="cRed">*</b> 常用微信</td>
							<td>
								<div class="J_toolsBar fl">
									<div class="t_text w200 ml10">
										<input type="text" name="infoWeixin"  value="${appPersonDetail.infoWeixin}"/>
									</div>
								</div>
							</td>
						</tr>
						<tr>
							<td class="l_title "><b class="cRed">*</b> 常用地址</td>
							<td>
								<div class="J_toolsBar fl">
									<div class="t_text w200 ml10">
										<input type="text" name="infoHome"  value="${appPersonDetail.infoHome}"/>
									</div>
								</div>
							</td>
						</tr>
						<tr>
							<td class="l_title "><b class="cRed">*</b> 紧急联系人关系</td>
							<td>
								<div class="J_toolsBar fl">
									<div class="t_text w200 ml10">
										<input type="text" name="category"  value="${appPersonDetail.infoEmycontactRelation}"/>
									</div>
								</div>
							</td>
						</tr>
						<tr>
							<td class="l_title "><b class="cRed">*</b> 紧急联系人电话号码</td>
							<td>
								<div class="J_toolsBar fl">
									<div class="t_text w200 ml10">
										<input type="text" name="infoEmycontactMobile"  value="${appPersonDetail.infoEmycontactMobile}"/>
									</div>
								</div>
							</td>
						</tr>
						<tr>
							<td class="l_title "><b class="cRed">*</b> 联系人关系</td>
							<td>
								<div class="J_toolsBar fl">
									<div class="t_text w200 ml10">
										<input type="text" name="infoContactRelation"  value="${appPersonDetail.infoContactRelation}"/>
									</div>
								</div>
							</td>
						</tr>
						<tr>
							<td class="l_title "><b class="cRed">*</b> 联系人电话号码</td>
							<td>
								<div class="J_toolsBar fl">
									<div class="t_text w200 ml10">
										<input type="text" name="infoContactMobile"  value="${appPersonDetail.infoContactMobile}"/>
									</div>
								</div>
							</td>
						</tr>
					</c:if>
					<c:if test="${ not empty appUserBank}">
						<tr>
							<td class="l_title "><b class="cRed">*</b> 银行卡名字</td>
							<td>
								<div class="J_toolsBar fl">
									<div class="t_text w200 ml10">
										<input type="text" name="infoContactMobile"  value="${appUserBank.bankName}"/>
									</div>
								</div>
							</td>
						</tr>
						<tr>
							<td class="l_title "><b class="cRed">*</b> 银行卡号</td>
							<td>
								<div class="J_toolsBar fl">
									<div class="t_text w200 ml10">
										<input type="text" name="infoContactMobile"  value="${appUserBank.bankNumber}"/>
									</div>
								</div>
							</td>
						</tr>
						<tr>
							<td class="l_title "><b class="cRed">*</b> 银行卡类型</td>
							<td>
								<div class="J_toolsBar fl">
									<div class="t_text w200 ml10">
										<input type="text" name="infoContactMobile"  value="${appUserBank.bankType}"/>
									</div>
								</div>
							</td>
						</tr>
						<tr>
							<td class="l_title "><b class="cRed">*</b> 银行卡预留手机号</td>
							<td>
								<div class="J_toolsBar fl">
									<div class="t_text w200 ml10">
										<input type="text" name="infoContactMobile"  value="${appUserBank.bankMobile}"/>
									</div>
								</div>
							</td>
						</tr>
					</c:if>
                     </tbody>
                     <tfoot>
                     	<tr>
	                     	<th colspan="2">
	                     		<div class="l_p_btn pd50">
									<div class="J_toolsBar">
										<div class="t_buttonGroup">
					                    	<a class="abtn red" href="javascript:mySubmit();">修改</a>
					                    </div>
									</div>
								</div>
	                     	</th>
	                     </tr>
                     </tfoot>
				</table>
			</div>
			</form>
		</div>
    </div>
	<%@ include file="../common/footer.jsp" %>
	<script type="text/javascript">
		$(function(){
			$('#myForm').validator({
				fields : {
					oldPwd : '原始密码:required;',
					pwd : '密码:required;password',
					pwd2: '确认密码 :required;password;match[pwd]'
				},
				valid : function(form){
					var loadIdx = layer.load();
					$('#myForm').ajaxSubmit({
						success : function(result){
							layer.close(loadIdx);
							if(result.success){
								layer.alert('修改成功，请重新登录', function(){
									window.location.href = '${ctx}/login_out';
								});
							}else{
								layer.alert(result.msg);
							}
						}
					});
				}
			});
			
		});
		
		function mySubmit(){
			$('#myForm').submit();
		}
	</script>
</body>
</html>