<%@ page language="java" pageEncoding="utf-8" contentType="text/html;charset=utf-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%@ include file="../common/common.jsp" %>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<title>${menu_name } - ${title }</title>
</head>
<link href="${ctx }/static/plugins/chosen_v1.6.2/chosen.css" rel="stylesheet" />

<body <%@ include file="../common/skin.jsp" %>>
<%@ include file="../common/head.jsp" %>
<%@ include file="../common/menu.jsp" %>
<div class="J_content">
	<div class="mt20 plr20">
		<form action="${ctx }/admin/appTask/userTask/list" id="queryForm">
			<div class="J_toolsBar clearfix">
				<div class="t_label"></div>
				<div class="t_text ml10">
					<input placeholder="请输入任务名称" type="text" name="task_name" value=""/>
				</div>
				<div class="t_text ml10">
					<input placeholder="请输入手机号" type="text" name="mobile" value=""/>
				</div>
				<div class="t_text ml10">
					<input placeholder="请输入审核状态" type="text" name="status" value=""/>
				</div>
				<div class="t_button ml10">
					<a class="abtn red" href="javascript:myQuery();">
						<i class="icon"></i>查询
					</a>
				</div>
			</div>
			<div class="J_table mt20">
				<div class="t_table">
					<table>
						<thead>
						<tr>

							<td>
								<span>序号</span>
							</td>
							<td>
								<span>任务名</span>
							</td>
							<td>
								<span>金额</span>
							</td>
							<td>
								<span>领取人手机号</span>
							</td>
							<td>
								<span>审核时间</span>
							</td>
							<td>
								<span>审核状态</span>
							</td>
							<td>
								<span>审核</span>
							</td>
						</tr>
						</thead>
						<tbody>
						<c:choose>
							<c:when test="${(appUserTasks)!= null && fn:length(appUserTasks) > 0}">
								<c:forEach items="${appUserTasks }" var="r">
									<tr>
										<td class="first">
											<div class="t_text tc">
													${r.id }
											</div>
										</td>
										<td>
											<div class="t_text tc">
													${r.appTask.taskName  }
											</div>
										</td>
										<td>
											<div class="t_text tc">
													${r.appTask.taskMoney  }
											</div>
										</td>
										<td>
											<div class="t_text tc">
													${r.appUser.mobile  }
											</div>
										</td>
										<td>
											<div class="t_text tc">
												<fmt:formatDate value="${r.auditTime  }" pattern="yyyy-MM-dd HH:mm:ss"/>
											</div>
										</td>
										<td>
											<div class="t_text tc">
												<c:choose>
													<c:when test="${r.status  eq 1}">
														通过
													</c:when>
													<c:when test="${r.status  eq 2}">
														不通过
													</c:when>
													<c:otherwise>
														待审核
													</c:otherwise>
												</c:choose>

											</div>
										</td>
										<td>
											<div class="t_link">
												<c:if test="${r.status  eq 0}">
													<a href="javascript:taskSuccess('${r.id}')"><i class="icon"></i>通过</a>
													<a href="javascript:taskFail('${r.id }');"><i class="icon"></i>不通过</a>
												</c:if>
											</div>
										</td>
									</tr>
								</c:forEach>
								<%@ include file="../common/pager.jsp"%>
							</c:when>
							<c:otherwise>
								<tr>
									<td colspan="12">
										<div class="J_null mt40">
											<img src="${ctx }/static/images/null.png">
											<p>暂无相关数据</p>
										</div>
									</td>
								</tr>
							</c:otherwise>
						</c:choose>
						</tbody>
					</table>
				</div>
			</div>
		</form>
	</div>
</div>
<script src="${ctx }/static/plugins/chosen_v1.6.2/chosen.jquery.js"></script>
<script type="text/javascript">

    function mySubmit(){
        $('#editForm').submit();
    }

    function myQuery(){
        $('#queryForm').submit();
    }


    function taskSuccess(id){
        var content = '确定任务审核通过吗？';
        layer.confirm(content, function(index){
            layer.close(index);
            var loadIdx = layer.load();
            $.ajax({
                url : '${ctx}/admin/appTask/userTask/ajax/updateUserTaskStatus',
                type : 'post',
                data : {
                    'id' : id,
					'status':1
                },
                traditional : true,
                success : function(result){
                    layer.close(loadIdx);
                    if(result.success){
                        layer.alert('操作成功', function(){
                            window.location.reload();
                        });
                    }else{
                        layer.alert('操作失败');
                    }
                }
            });
        });
    }

    function taskFail(id){
        var content = '确定任务审核不通过吗？';
        layer.confirm(content, function(index){
            layer.close(index);
            var loadIdx = layer.load();
            $.ajax({
                url : '${ctx}/admin/appTask/userTask/ajax/updateUserTaskStatus',
                type : 'post',
                data : {
                    'id' : id,
					'status':2
                },
                traditional : true,
                success : function(result){
                    layer.close(loadIdx);
                    if(result.success){
                        layer.alert('操作成功', function(){
                            window.location.reload();
                        });
                    }else{
                        layer.alert('操作失败');
                    }
                }
            });
        });
    }
</script>
</body>
</html>
