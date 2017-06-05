<%@ page language="java" pageEncoding="utf-8" contentType="text/html;charset=utf-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%@ include file="../common/common.jsp" %>
<head>
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
						<td class="l_title w200"><b class="cRed">*</b> 创建时间</td>
                         <td>
                             <div class="J_toolsBar fl">
                                 <div class="t_text w200 ml10">
									<input type="text" name="createDate"
										   value="<fmt:formatDate value="${appTask.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"/>
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
											value="<fmt:formatDate value="${appTask.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"/>
                                 </div>
                             </div>
                         </td>
                     </tr>
					 <tr>
						<td class="l_title "><b class="cRed">*</b> 任务名称</td>
                         <td>
                             <div class="J_toolsBar fl">
                                 <div class="t_text w200 ml10">
                                     <input type="text" name="taskName"  value="${appTask.taskName}"/>
                                 </div>
                             </div>
                         </td>
                     </tr>
					<tr>
						<td class="l_title "><b class="cRed">*</b> 任务数量</td>
						<td>
							<div class="J_toolsBar fl">
								<div class="t_text w200 ml10">
									<input type="text" name="taskNumber"  value="${appTask.taskNumber}"/>
								</div>
							</div>
						</td>
					</tr>
					<tr>
						<td class="l_title "><b class="cRed">*</b> 任务类型</td>
						<td>
							<div class="J_toolsBar fl">
								<div class="t_text w200 ml10">
									<input type="text" name="taskType"  value="${appTask.taskType}"/>
								</div>
							</div>
						</td>
					</tr>
					<tr>
						<td class="l_title "><b class="cRed">*</b> 任务金额</td>
						<td>
							<div class="J_toolsBar fl">
								<div class="t_text w200 ml10">
									<input type="text" name="taskMoney"  value="${appTask.taskMoney}"/>
								</div>
							</div>
						</td>
					</tr>
					<tr>
						<td class="l_title "><b class="cRed">*</b> 任务图片地址</td>
						<td>
							<div class="J_toolsBar fl">
								<div class="t_text w200 ml10">
									<input type="text" name="taskImg"  value="${appTask.taskImg}"/>
								</div>
							</div>
						</td>
					</tr>
					<tr>
						<td class="l_title "><b class="cRed">*</b> 任务热门</td>
						<td>
							<div class="J_toolsBar fl">
								<div class="t_text w200 ml10">
									<input type="text" name="taskHot"  value="${appTask.taskHot}"/>
								</div>
							</div>
						</td>
					</tr>
					<tr>
						<td class="l_title "><b class="cRed">*</b> 任务开始时间</td>
						<td>
							<div class="J_toolsBar fl">
								<div class="t_text w200 ml10">
									<input type="text" name="taskStartTime"
										   value="<fmt:formatDate value="${appTask.taskStartTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"/>
								</div>
							</div>
						</td>
					</tr>
					<tr>
						<td class="l_title "><b class="cRed">*</b> 任务结束时间</td>
						<td>
							<div class="J_toolsBar fl">
								<div class="t_text w200 ml10">
									<input type="text" name="taskEndTime"
										   value="<fmt:formatDate value="${appTask.taskEndTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"/>
								</div>
							</div>
						</td>
					</tr>
					<tr>
						<td class="l_title "><b class="cRed">*</b> 任务简单步骤</td>
						<td>
							<div class="J_toolsBar fl">
								<div class="t_text w200 ml10">
									<input type="text" name="taskSimpleStep"  value="${appTask.taskSimpleStep}"/>
								</div>
							</div>
						</td>
					</tr>
					<tr>
						<td class="l_title "><b class="cRed">*</b> 任务详细步骤</td>
						<td>
							<div class="J_toolsBar fl">
								<div class="t_text w200 ml10">
									<input type="text" name="taskDetailedStep"  value="${appTask.taskDetailedStep}"/>
								</div>
							</div>
						</td>
					</tr>
					<tr>
						<td class="l_title "><b class="cRed">*</b> 任务链接</td>
						<td>
							<div class="J_toolsBar fl">
								<div class="t_text w200 ml10">
									<input type="text" name="taskLink"  value="${appTask.taskLink}"/>
								</div>
							</div>
						</td>
					</tr>
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