<%@ page language="java" pageEncoding="utf-8" contentType="text/html;charset=utf-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%@ include file="../common/common.jsp" %>
<head>
	<link rel="stylesheet" type="text/css" href="${ctx }/static/plugins/oss-h5-upload-js-direct/style.css"/>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>${menu_name } - ${title }</title>
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
						<td class="l_title" style="width: 220px"></td>
						<td style="width: 240px"></td>
						<td></td>
					</tr>
					<tr>
						<td class="l_title"><b class="cRed">*</b> 创建时间</td>
                         <td>
                             <div class="J_toolsBar fl">
                                 <div class="t_text w240 ml10">
									 <fmt:formatDate value="${appUser.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
                                 </div>
                             </div>
                         </td>
                     </tr>
					 <tr>
						<td class="l_title "><b class="cRed">*</b> 更新时间</td>
                         <td>
                             <div class="J_toolsBar fl">
                                 <div class="t_text w240 ml10">
									 <fmt:formatDate value="${appUser.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
                                 </div>
                             </div>
                         </td>
                     </tr>
					 <tr>
						<td class="l_title "><b class="cRed">*</b> 权限</td>
                         <td>
                             <div class="J_toolsBar fl">
                                 <div class="t_text w240 ml10">
									 <c:choose>
										 <c:when test="${appUser.authority eq 100}">
											 低
										 </c:when>
										 <c:when test="${appUser.authority eq 101}">
											 高
										 </c:when>
									 </c:choose>
                                 </div>
                             </div>
                         </td>
                     </tr>
					<tr>
						<td class="l_title "><b class="cRed">*</b> 额度</td>
						<td>
							<div class="J_toolsBar fl">
								<div class="t_text w240 ml10">
									${appUser.moneyMax}
								</div>
							</div>
						</td>
						<td>
							<div class="J_toolsBar fl">
								<div class="t_label w240 ml10">
									<a href="javascript:myEditMoneyMax('${appUser.id }');" class='btn'>修改额度</a>
								</div>
							</div>
						</td>
					</tr>
					<tr>
						<td class="l_title "><b class="cRed">*</b> 芝麻授权用户名</td>
						<td>
							<div class="J_toolsBar fl">
								<div class="t_text w240 ml10">
									${appUser.zhimaCertName}
								</div>
							</div>
						</td>
					</tr>
					<tr>
						<td class="l_title "><b class="cRed">*</b> 芝麻授权用户身份证</td>
						<td>
							<div class="J_toolsBar fl">
								<div class="t_text w240 ml10">
									${appUser.zhimaCertNo}
								</div>
							</div>
						</td>
					</tr>
					<tr>
						<td class="l_title "><b class="cRed">*</b> 芝麻信用分[350-950]</td>
						<td>
							<div class="J_toolsBar fl">
								<div class="t_text w240 ml10">
									<c:choose>
										<c:when test="${appZhimas[0].errorMessage == null ||appZhimas[0].errorMessage == ''}">
											${appZhimas[0].score}
										</c:when>
										<c:otherwise>
											${appZhimas[0].errorMessage}
										</c:otherwise>
									</c:choose>
								</div>
							</div>
						</td>
					</tr>
					<tr>
						<td class="l_title "><b class="cRed">*</b> 芝麻行业关注名单是否命中(命中则有过不良记录)</td>
						<td>
							<div class="J_toolsBar fl">
								<div class="t_text w240 ml10">
									<c:choose>
										<c:when test="${appZhimas[1].errorMessage == null ||appZhimas[1].errorMessage == ''}">
											${appZhimas[1].isMatched}
										</c:when>
										<c:otherwise>
											${appZhimas[1].errorMessage}
										</c:otherwise>
									</c:choose>
								</div>
							</div>
						</td>
					</tr>
					<tr>
						<td class="l_title " style="height: auto"><b class="cRed">*</b> 芝麻欺诈评分[0-100](分数越高信息越真实)</td>
						<td>
							<div class="J_toolsBar fl">
								<div class="t_text w240 ml10">
									<c:choose>
										<c:when test="${appZhimas[2].errorMessage == null ||appZhimas[2].errorMessage == ''}">
											${appZhimas[2].score}
										</c:when>
										<c:otherwise>
											${appZhimas[2].errorMessage}
										</c:otherwise>
									</c:choose>
								</div>
							</div>
						</td>
					</tr>
					<tr>
						<td class="l_title "><b class="cRed">*</b> 芝麻欺诈信息验证</td>
						<td>
							<div class="J_toolsBar fl">
								<div class="t_text w240 ml10" <c:if test="${appZhimas[3] !=null}">style="height: auto"</c:if>>
									<c:choose>
										<c:when test="${appZhimas[3].errorMessage == null ||appZhimas[3].errorMessage == ''}">
											${appZhimas[3].verifyCode}
										</c:when>
										<c:otherwise>
											${appZhimas[3].errorMessage}
										</c:otherwise>
									</c:choose>
								</div>
							</div>
						</td>
						<td>
							<div class="J_toolsBar fl">
								<div class="t_label w240 ml10">
									<a target="view_window" href="https://b.zmxy.com.cn/product/productDetail.htm?productId=w1010100000000002859&scene=nav" class='btn'>查看验证码说明</a>
								</div>
							</div>
						</td>
					</tr>
					<tr>
						<td class="l_title "><b class="cRed">*</b> 芝麻欺诈关注清单是否命中</td>
						<td>
							<div class="J_toolsBar fl">
								<div class="t_text w240 ml10">
									<c:choose>
										<c:when test="${appZhimas[4].errorMessage == null ||appZhimas[4].errorMessage == ''}">
											${appZhimas[4].hit}
											<c:if test="${appZhimas[4].riskCode!=null &&appZhimas[4].riskCode!='null'&&
											appZhimas[4].riskCode!=''}">,${appZhimas[4].riskCode}</c:if>
										</c:when>
										<c:otherwise>
											${appZhimas[4].errorMessage}
										</c:otherwise>
									</c:choose>
								</div>
							</div>
						</td>
						<td>
							<div class="J_toolsBar fl">
								<div class="t_label w240 ml10">
									<a target="view_window" href="https://b.zmxy.com.cn/product/productDetail.htm?productId=w1010100003000001283&scene=nav" class='btn'>查看风险代码表说明</a>
								</div>
							</div>
						</td>
					</tr>
					<tr>
						<td class="l_title "><b class="cRed">*</b> 人脸图片地址</td>
						<td>
							<div class="J_toolsBar fl">
								<div class="t_text w240 ml10" >
									<input id="imgFace" type="text" name="imgFace"  value="${appUser.imgFace}"/>
								</div>
							</div>
						</td>
						<td>
							<div class="J_toolsBar fl">
								<div class="t_label w240 ml10">
									<a href="javascript:myVisit('imgFace');" class='btn'>查看</a>
								</div>
							</div>
						</td>
					</tr>
					<tr>
						<td class="l_title "><b class="cRed">*</b> 身份证正面图片地址</td>
						<td>
							<div class="J_toolsBar fl">
								<div class="t_text w240 ml10" >
									<input id="imgIdFront" type="text" name="imgIdFront"  value="${appUser.imgIdFront}"/>
								</div>
							</div>
						</td>
						<td>
							<div class="J_toolsBar fl">
								<div class="t_label w240 ml10">
									<a href="javascript:myVisit('imgIdFront');" class='btn'>查看</a>
								</div>
							</div>
						</td>
					</tr>
					<tr>
						<td class="l_title "><b class="cRed">*</b> 身份证背面图片地址</td>
						<td>
							<div class="J_toolsBar fl">
								<div class="t_text w240 ml10" >
									<input id="imgIdBack" type="text" name="imgIdBack"  value="${appUser.imgIdBack}"/>
								</div>
							</div>
						</td>
						<td>
							<div class="J_toolsBar fl">
								<div class="t_label w240 ml10">
									<a href="javascript:myVisit('imgIdBack');" class='btn'>查看</a>
								</div>
							</div>
						</td>
					</tr>
					<tr>
						<td class="l_title "><b class="cRed">*</b> 身份证名字</td>
						<td>
							<div class="J_toolsBar fl">
								<div class="t_text w240 ml10">
									${appUser.userIdName}
								</div>
							</div>
						</td>
					</tr>
					<tr>
						<td class="l_title "><b class="cRed">*</b> 身份证号码</td>
						<td>
							<div class="J_toolsBar fl">
								<div class="t_text w240 ml10" >
									${appUser.userIdNumber}
								</div>
							</div>
						</td>
					</tr>
					<tr>
						<td class="l_title "><b class="cRed">*</b> 头像图片地址</td>
						<td>
							<div class="J_toolsBar fl">
								<div class="t_text w240 ml10" >
									<input id="imgPortrait" type="text" name="imgPortrait"  value="${appUser.imgPortrait}"/>
								</div>
							</div>
						</td>
						<td>
							<div class="J_toolsBar fl">
								<div class="t_label w240 ml10">
									<a href="javascript:myVisit('imgPortrait');" class='btn'>查看</a>
								</div>
							</div>
						</td>
					</tr>
					<tr>
						<td class="l_title "><b class="cRed">*</b> 昵称</td>
						<td>
							<div class="J_toolsBar fl">
								<div class="t_text w240 ml10">
									${appUser.nickname}
								</div>
							</div>
						</td>
					</tr>
					<tr>
						<td class="l_title "><b class="cRed">*</b> 群体</td>
						<td>
							<div class="J_toolsBar fl">
								<div class="t_text w240 ml10">
									<c:if test="${appUser.category eq 1}">
										学生
									</c:if>
									<c:if test="${appUser.category eq 2}">
										社会人群
									</c:if>
								</div>
							</div>
						</td>
					</tr>
					<c:if test="${appUser.category eq 1}">
						<!--<tr>
							<td class="l_title "><b class="cRed">*</b> 详细信息手机号</td>
							<td>
								<div class="J_toolsBar fl">
									<div class="t_text w240 ml10">
											${appStuDetail.infoMobile}
									</div>
								</div>
							</td>
						</tr>-->
						<tr>
							<td class="l_title "><b class="cRed">*</b> 学校</td>
							<td>
								<div class="J_toolsBar fl">
									<div class="t_text w240 ml10">
											${appStuDetail.infoSchool}
									</div>
								</div>
							</td>
						</tr>
						<tr>
							<td class="l_title "><b class="cRed">*</b> 院系</td>
							<td>
								<div class="J_toolsBar fl">
									<div class="t_text w240 ml10">
											${appStuDetail.infoDepartment}
									</div>
								</div>
							</td>
						</tr>
						<tr>
							<td class="l_title "><b class="cRed">*</b> 班级</td>
							<td>
								<div class="J_toolsBar fl">
									<div class="t_text w240 ml10">
											${appStuDetail.infoClass}
									</div>
								</div>
							</td>
						</tr>
						<tr>
							<td class="l_title "><b class="cRed">*</b> 宿舍楼号</td>
							<td>
								<div class="J_toolsBar fl">
									<div class="t_text w240 ml10">
											${appStuDetail.infoRoomnumber}
									</div>
								</div>
							</td>
						</tr>
						<tr>
							<td class="l_title "><b class="cRed">*</b> 紧急联系人关系</td>
							<td>
								<div class="J_toolsBar fl">
									<div class="t_text w240 ml10">
										<c:choose>
											<c:when test="${appStuDetail.infoEmycontactRelation eq 1}">
												父母
											</c:when>
											<c:when test="${appStuDetail.infoEmycontactRelation eq 2}">
												朋友
											</c:when>
											<c:when test="${appStuDetail.infoEmycontactRelation eq 3}">
												同学
											</c:when>
											<c:when test="${appStuDetail.infoEmycontactRelation eq 4}">
												同事
											</c:when>
											<c:when test="${appStuDetail.infoEmycontactRelation eq 5}">
												亲戚
											</c:when>
										</c:choose>
									</div>
								</div>
							</td>
						</tr>
						<tr>
							<td class="l_title "><b class="cRed">*</b> 紧急联系人电话号码</td>
							<td>
								<div class="J_toolsBar fl">
									<div class="t_text w240 ml10">
											${appStuDetail.infoEmycontactMobile}
									</div>
								</div>
							</td>
						</tr>
						<tr>
							<td class="l_title "><b class="cRed">*</b> 联系人关系</td>
							<td>
								<div class="J_toolsBar fl">
									<div class="t_text w240 ml10">
										<c:choose>
											<c:when test="${appStuDetail.infoContactRelation eq 1}">
												父母
											</c:when>
											<c:when test="${appStuDetail.infoContactRelation eq 2}">
												朋友
											</c:when>
											<c:when test="${appStuDetail.infoContactRelation eq 3}">
												同学
											</c:when>
											<c:when test="${appStuDetail.infoContactRelation eq 4}">
												同事
											</c:when>
											<c:when test="${appStuDetail.infoContactRelation eq 5}">
												亲戚
											</c:when>
										</c:choose>
									</div>
								</div>
							</td>
						</tr>
						<tr>
							<td class="l_title "><b class="cRed">*</b> 联系人电话号码</td>
							<td>
								<div class="J_toolsBar fl">
									<div class="t_text w240 ml10">
											${appStuDetail.infoContactMobile}
									</div>
								</div>
							</td>
						</tr>
					</c:if>
					<c:if test="${appUser.category eq 2}">
						<!--<tr>
							<td class="l_title "><b class="cRed">*</b> 详细信息手机号</td>
							<td>
								<div class="J_toolsBar fl">
									<div class="t_text w240 ml10">
											${appPersonDetail.infoMobile}
									</div>
								</div>
							</td>
						</tr>-->
						<tr>
							<td class="l_title "><b class="cRed">*</b> 公司名称</td>
							<td>
								<div class="J_toolsBar fl">
									<div class="t_text w240 ml10">
											${appPersonDetail.infoCompanyName}
									</div>
								</div>
							</td>
						</tr>
						<tr>
							<td class="l_title "><b class="cRed">*</b> 公司地址</td>
							<td>
								<div class="J_toolsBar fl">
									<div class="t_text w240 ml10">
											${appPersonDetail.infoCompanyAddress}
									</div>
								</div>
							</td>
						</tr>
						<tr>
							<td class="l_title "><b class="cRed">*</b> 常用QQ</td>
							<td>
								<div class="J_toolsBar fl">
									<div class="t_text w240 ml10">
											${appPersonDetail.infoQq}
									</div>
								</div>
							</td>
						</tr>
						<tr>
							<td class="l_title "><b class="cRed">*</b> 常用微信</td>
							<td>
								<div class="J_toolsBar fl">
									<div class="t_text w240 ml10">
											${appPersonDetail.infoWeixin}
									</div>
								</div>
							</td>
						</tr>
						<tr>
							<td class="l_title "><b class="cRed">*</b> 常用地址</td>
							<td>
								<div class="J_toolsBar fl">
									<div class="t_text w240 ml10">
											${appPersonDetail.infoHome}
									</div>
								</div>
							</td>
						</tr>
						<tr>
							<td class="l_title "><b class="cRed">*</b> 紧急联系人关系</td>
							<td>
								<div class="J_toolsBar fl">
									<div class="t_text w240 ml10">
										<c:choose>
											<c:when test="${appPersonDetail.infoEmycontactRelation eq 1}">
												父母
											</c:when>
											<c:when test="${appPersonDetail.infoEmycontactRelation eq 2}">
												朋友
											</c:when>
											<c:when test="${appPersonDetail.infoEmycontactRelation eq 3}">
												同学
											</c:when>
											<c:when test="${appPersonDetail.infoEmycontactRelation eq 4}">
												同事
											</c:when>
											<c:when test="${appPersonDetail.infoEmycontactRelation eq 5}">
												亲戚
											</c:when>
										</c:choose>
									</div>
								</div>
							</td>
						</tr>
						<tr>
							<td class="l_title "><b class="cRed">*</b> 紧急联系人电话号码</td>
							<td>
								<div class="J_toolsBar fl">
									<div class="t_text w240 ml10">
											${appPersonDetail.infoEmycontactMobile}
									</div>
								</div>
							</td>
						</tr>
						<tr>
							<td class="l_title "><b class="cRed">*</b> 联系人关系</td>
							<td>
								<div class="J_toolsBar fl">
									<div class="t_text w240 ml10">
										<c:choose>
											<c:when test="${appPersonDetail.infoContactRelation eq 1}">
												父母
											</c:when>
											<c:when test="${appPersonDetail.infoContactRelation eq 2}">
												朋友
											</c:when>
											<c:when test="${appPersonDetail.infoContactRelation eq 3}">
												同学
											</c:when>
											<c:when test="${appPersonDetail.infoContactRelation eq 4}">
												同事
											</c:when>
											<c:when test="${appPersonDetail.infoContactRelation eq 5}">
												亲戚
											</c:when>
										</c:choose>
									</div>
								</div>
							</td>
						</tr>
						<tr>
							<td class="l_title "><b class="cRed">*</b> 联系人电话号码</td>
							<td>
								<div class="J_toolsBar fl">
									<div class="t_text w240 ml10">
											${appPersonDetail.infoContactMobile}
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
									<div class="t_text w240 ml10">
											${appUserBank.bankName}
									</div>
								</div>
							</td>
						</tr>
						<tr>
							<td class="l_title "><b class="cRed">*</b> 银行卡号</td>
							<td>
								<div class="J_toolsBar fl">
									<div class="t_text w240 ml10">
											${appUserBank.bankNumber}
									</div>
								</div>
							</td>
						</tr>
						<tr>
							<td class="l_title "><b class="cRed">*</b> 银行卡类型</td>
							<td>
								<div class="J_toolsBar fl">
									<div class="t_text w240 ml10">
										<c:choose>
											<c:when test="${appUserBank.bankType eq 0}">
												储蓄卡
											</c:when>
											<c:when test="${appUserBank.bankType eq 1}">
												信用卡
											</c:when>
										</c:choose>
									</div>
								</div>
							</td>
						</tr>
						<tr>
							<td class="l_title "><b class="cRed">*</b> 银行卡预留手机号</td>
							<td>
								<div class="J_toolsBar fl">
									<div class="t_text w240 ml10">
											${appUserBank.bankMobile}
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
											<!--<a class="abtn red" href="javascript:mySubmit();">修改</a>-->
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

        function myEditMoneyMax(id){
            var loadIdx = layer.load();
            var title = '添加区域';
            if(!id){
                id = '';
            }else{
                title = '修改区域';
            }
            $.post('${ctx}/admin/appUser/user/dialog/appUser_edit', {id:id}, function(str){

                layer.close(loadIdx);

                layer.open({
                    title : title,
                    type : 1,
                    area : ['700px', '450px'],
                    content : str,
                    btn : ['确定', '取消'],
                    yes : function(index, layero){
                        mySubmit();
                    },
                    btn2 : function(index, layero){
                        layer.close(index);
                    }
                });
            });
        }

        function mySubmit(){
            $('#editForm').submit();
        }

        function myVisit(name){
            var url = $("#"+name).val();
            if( url != null && url != ''){
                if (url.indexOf('http://')<0 && url.indexOf('https://')<0){
                    url = 'http://' +url;
                }
                window.open(url);
            }
        }

	</script>
</body>
</html>