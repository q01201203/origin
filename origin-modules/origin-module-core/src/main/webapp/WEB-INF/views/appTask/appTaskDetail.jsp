<%@ page language="java" pageEncoding="utf-8" contentType="text/html;charset=utf-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%@ include file="../common/common.jsp" %>
<script type="text/javascript">
    var parentChosen;
    $(function(){
        parentChosen = $(".chosen-select").chosen({
            no_results_text: "未找到",
            width : '100%'
        });
    });
</script>
<link href="${ctx }/static/plugins/chosen_v1.6.2/chosen.css" rel="stylesheet" />
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>${menu_name } - ${title }</title>
</head>
<body <%@ include file="../common/skin.jsp" %>>
	<%@ include file="../common/head.jsp" %>
    <%@ include file="../common/menu.jsp" %>
    <div class="J_content">
		<div class="mt20 plr20">
			<form action="${ctx }/admin/appTask/ajax/update" method="post" id="myForm">
				<input type="hidden" name="id" value="${appTask.id }"/>
			<div class="J_formTable l_form_table">
				<table width="800px" class="not_hightlight">
					<tbody>
					<c:if test="${operation == 'update'}">
						<tr>
							<td class="l_title w200"><b class="cRed">*</b> 创建时间</td>
							<td>
								<div class="J_toolsBar fl">
									<div class="t_text w200 ml10">
										<fmt:formatDate value="${appTask.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
									</div>
								</div>
							</td>
						</tr>
						<tr>
							<td class="l_title "><b class="cRed">*</b> 更新时间</td>
							<td>
								<div class="J_toolsBar fl">
									<div class="t_text w200 ml10">
										<fmt:formatDate value="${appTask.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
									</div>
								</div>
							</td>
						</tr>
					</c:if>
					 <tr>
						<td class="l_title "><b class="cRed">*</b> 任务名称</td>
                         <td>
                             <div class="J_toolsBar fl">
                                 <div class="t_text w200 ml10">
									 <c:if test="${operation == 'update'}">
										 ${appTask.taskName}
									 </c:if>
									 <c:if test="${operation == 'add'}">
										 <input type="text" name="taskName"  value="${appTask.taskName}"/>
									 </c:if>
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
								<div class="t_label ml10" style="width: 220px;">
									<select data-placeholder="选择父菜单" class="chosen-select" name="taskType">
										<option value="" >选择类型</option>
										<option value="1" ${appTask.taskType eq 1 ? "selected=\"selected\"":""}>普通任务</option>
										<option value="2" ${appTask.taskType eq 2 ? "selected=\"selected\"":""}>高额任务</option>
										<option value="3" ${appTask.taskType eq 3 ? "selected=\"selected\"":""}>限时任务</option>
									</select>
								</div>
							</div>
						</td>
					</tr>
					<tr>
						<td class="l_title "><b class="cRed">*</b> 任务金额</td>
						<td>
							<div class="J_toolsBar fl">
								<div class="t_text w200 ml10">
									<c:if test="${operation == 'update'}">
										${appTask.taskMoney}
									</c:if>
									<c:if test="${operation == 'add'}">
										<input type="text" name="taskMoney"  value="${appTask.taskMoney}"/>
									</c:if>
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
								<div class="t_check w200 ml10">
									<c:choose>
										<c:when test="${appTask.taskHot eq 0}">
											<label>
												<input type="radio" name="taskHot" value="1" />推荐
											</label>
											<label>
												<input type="radio" name="taskHot" value="0" checked="checked"/>不推荐
											</label>
										</c:when>
										<c:when test="${appTask.taskHot eq 1}">
											<label>
												<input type="radio" name="taskHot" value="1" checked="checked"/>推荐
											</label>
											<label>
												<input type="radio" name="taskHot" value="0" />不推荐
											</label>
										</c:when>
										<c:otherwise>
											<label>
												<input type="radio" name="taskHot" value="1" />推荐
											</label>
											<label>
												<input type="radio" name="taskHot" value="0" />不推荐
											</label>
										</c:otherwise>
									</c:choose>
								</div>
							</div>
						</td>
					</tr>
					<tr>
						<td class="l_title "><b class="cRed">*</b> 任务开始时间</td>
						<td>
							<div class="J_toolsBar fl">
								<div class="t_text w200 ml10">
									<input id="startDate" type="text" name="taskStartTime" class="Wdate" placeholder="选择日期"
										   onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate:'#F{$dp.$D(\'endDate\')}'})"
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
									<input id="endDate" type="text" name="taskEndTime" class="Wdate" placeholder="选择日期"
										   onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'startDate\')}'})"
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
											<c:if test="${operation == 'update'}">
												<a class="abtn red" href="javascript:updateTask();">修改</a>
											</c:if>
											<c:if test="${operation == 'add'}">
												<a class="abtn red" href="javascript:addTask();">添加</a>
											</c:if>
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
	<script src="${ctx }/static/plugins/chosen_v1.6.2/chosen.jquery.js"></script>
	<script type="text/javascript">
		$(function(){
			$('#myForm').validator({
				fields : {
				},
				valid : function(form){
					var loadIdx = layer.load();
					$('#myForm').ajaxSubmit({
						success : function(result){
							layer.close(loadIdx);
                            if(result.success){
                                layer.alert('保存成功', function(){
                                    window.location.reload();
                                });
                            }else{
                                layer.alert(result.msg);
                            }
						}
					});
				}
			});
			
		});
		
		function addTask(){
		    if (confirm("确认添加吗？添加后将无法修改任务名称和金额").title("提示")){
                $('#myForm').submit();
			}
		}
        function updateTask(){
			$('#myForm').submit();
        }

	</script>
</body>
</html>