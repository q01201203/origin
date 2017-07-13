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

        var limitTimeContent = $("#limitTime").html();

        if (parentChosen.val() == 3){
            $("#limitTime").empty().append(limitTimeContent);
        }else{
            $("#limitTime").empty();
        }

        $(".chosen-select").chosen().change(function(){
            //do
			if (parentChosen.val() == 3){
                $("#limitTime").empty().append(limitTimeContent);
			}else{
                $("#limitTime").empty();
			}
        });
    });
</script>
<head>
	<link href="${ctx }/static/plugins/chosen_v1.6.2/chosen.css" rel="stylesheet" />
	<link rel="stylesheet" type="text/css" href="${ctx }/static/plugins/oss-h5-upload-js-direct/style.css"/>
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
				<table width="1200px" class="not_hightlight">
					<thead>
					<tr>
						<td style="width: 200px">
						</td>
						<td style="width: 450px">
						</td>
						<td style="width: 250px">
						</td>
					</tr>
					</thead>
					<tbody>
					<c:if test="${operation == 'update'}">
						<tr>
							<td class="l_title"> 创建时间</td>
							<td>
								<div class="J_toolsBar fl">
									<div class="t_text w240 ml10">
										<fmt:formatDate value="${appTask.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
									</div>
								</div>
							</td>
						</tr>
						<tr>
							<td class="l_title "> 更新时间</td>
							<td>
								<div class="J_toolsBar fl">
									<div class="t_text w240 ml10">
										<fmt:formatDate value="${appTask.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
									</div>
								</div>
							</td>
						</tr>
					</c:if>
					 <tr>
						<td class="l_title "><c:if test="${operation == 'add'}"><b class="cRed">*</b></c:if> 任务名称</td>
                         <td>
                             <div class="J_toolsBar fl">
                                 <div class="t_text w240 ml10">
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
								<div class="t_text w240 ml10">
									<input type="text" name="taskNumber"  value="${appTask.taskNumber}"/>
								</div>
							</div>
						</td>
					</tr>
					<tr>
						<td class="l_title "><b class="cRed">*</b> 任务类型</td>
						<td>
							<div class="J_toolsBar fl">
								<div class="t_label w240 ml10">
									<select id="taskType" data-placeholder="选择类型" class="chosen-select" name="taskType">
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
						<td class="l_title "><c:if test="${operation == 'add'}"><b class="cRed">*</b></c:if> 任务金额</td>
						<td>
							<div class="J_toolsBar fl">
								<div class="t_text w240 ml10">
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
								<div class="t_text w240 ml10">
									<input id="taskImg" onclick="javascript:selectFile('taskImg');" type="text" name="taskImg"  value="${appTask.taskImg}"/>
								</div>
							</div>
						</td>
						<td>
							<div class="J_toolsBar fl">
								<div class="t_label w240 ml10">
									<div id="container">
										<a href="javascript:myVisit('taskImg');" class='btn'>查看</a>
										<a href="javascript:selectFile('taskImg');" class='btn'>选择文件</a>
										<a href="javascript:myUpload('taskImg');" class='btn'>开始上传</a>
									</div>
								</div>
							</div>
						</td>
						<td name="taskImg">
							<div id="uploadTip">
								<div  class="J_toolsBar fl">
									<div class="t_label w240 ml10">
										<div id="ossfile">你的浏览器不支持flash,Silverlight或者HTML5！</div>
										<pre id="console"></pre>
									</div>
								</div>
							</div>
						</td>
					</tr>
					<tr>
						<td class="l_title "><b class="cRed">*</b> 任务热门</td>
						<td>
							<div class="J_toolsBar fl">
								<div class="t_check w240 ml10">
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
					<tbody id="limitTime">
						<tr>
							<td class="l_title "><b class="cRed">*</b> 任务开始时间</td>
							<td>
								<div class="J_toolsBar fl">
									<div class="t_text w240 ml10">
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
									<div class="t_text w240 ml10">
										<input id="endDate" type="text" name="taskEndTime" class="Wdate" placeholder="选择日期"
											   onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'startDate\')}'})"
											   value="<fmt:formatDate value="${appTask.taskEndTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"/>
									</div>
								</div>
							</td>
						</tr>
					</tbody>
					<tr>
						<td class="l_title "><b class="cRed">*</b> 任务简单步骤</td>
						<td>
							<div class="J_toolsBar fl">
								<div class="t_textarea w240 ml10">
									<textarea cols="31" rows="5" name="taskSimpleStep" style="width: auto">${appTask.taskSimpleStep}</textarea>
								</div>
							</div>
						</td>
					</tr>
					<tr>
						<td class="l_title "><b class="cRed">*</b> 任务详细步骤</td>
						<td>
							<div class="J_toolsBar fl">
								<div class="t_text w240 ml10">
									<input id="taskDetailedStep" onclick="javascript:selectFile('taskDetailedStep');" type="text" name="taskDetailedStep"  value="${appTask.taskDetailedStep}"/>
								</div>
							</div>
						</td>
						<td>
							<div class="J_toolsBar fl">
								<div class="t_label w240 ml10">
									<a href="javascript:myVisit('taskDetailedStep');" class='btn'>查看</a>
									<a href="javascript:selectFile('taskDetailedStep');" class='btn'>选择文件</a>
									<a href="javascript:myUpload('taskDetailedStep');" class='btn'>开始上传</a>
								</div>
							</div>
						</td>
						<td name="taskDetailedStep">
						</td>
					</tr>
					<tr>
						<td class="l_title "><b class="cRed">*</b> 任务链接</td>
						<td>
							<div class="J_toolsBar fl">
								<div class="t_text w240 ml10">
									<input id="taskLink" type="text" name="taskLink"  value="${appTask.taskLink}"/>
								</div>
							</div>
						</td>
						<td>
							<div class="J_toolsBar fl">
								<div class="t_label w240 ml10">
									<a href="javascript:myVisit('taskLink');" class='btn'>查看</a>
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
											<input type="hidden" name="myradio" value="local_name" />
											<input type="hidden" name="myradio" value="random_name" checked=true/>
											<input type="hidden" id='dirname' placeholder="如果不填，默认是上传到根目录" size=50>
											<a id="selectfiles" href="javascript:void(0);" class='btn' style="visibility:hidden">选择文件</a>
											<a id="postfiles" href="javascript:void(0);" class='btn' style="visibility:hidden">开始上传</a>
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
                rules: {
                    number: [/^[1-9]\d*$/,'请输入大于0的正整数']
                },
				fields : {
                    taskName : ':required;length[~6]',
                    taskNumber : ':required;digits;number',
                    taskType : ':required;',
                    taskMoney : ':required;digits',
                    taskImg : ':required;',
                    taskHot : ':checked;',
                    taskStartTime : ':required;',
                    taskEndTime : ':required;',
                    taskSimpleStep : ':required;length[~80]',
                    taskDetailedStep : ':required;',
                    taskLink : ':required;'
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
		    if (confirm("确认添加吗？添加后将无法修改任务名称和金额")){
                $('#myForm').submit();
			}
		}
        function updateTask(){
			$('#myForm').submit();
        }

        function selectFile(name){
            $('#uploadTip').remove();
			var div = '<div id="uploadTip">'
				+'<div class="J_toolsBar fl">'
				+'<div class="t_label w240 ml10">'
				+ '<div id="ossfile"></div>'
				+ '<pre id="console"></pre>'
				+ '</div>'
                + '</div>'
				+ '</div>';
			$("td[name="+name+"]").empty().append(div);
			document.getElementById('selectfiles').click();
        }

        function myUpload(name){
            if(name == null || name == ''){
                //document.getElementById('ossfile').innerHTML = '';
                //document.getElementById('selectfiles').click();
			}else{
                document.getElementById('postfiles').name = name;
                document.getElementById('postfiles').click();
			}
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
<script type="text/javascript" src="${ctx }/static/plugins/oss-h5-upload-js-direct/lib/crypto1/crypto/crypto.js"></script>
<script type="text/javascript" src="${ctx }/static/plugins/oss-h5-upload-js-direct/lib/crypto1/hmac/hmac.js"></script>
<script type="text/javascript" src="${ctx }/static/plugins/oss-h5-upload-js-direct/lib/crypto1/sha1/sha1.js"></script>
<script type="text/javascript" src="${ctx }/static/plugins/oss-h5-upload-js-direct/lib/base64.js"></script>
<script type="text/javascript" src="${ctx }/static/plugins/oss-h5-upload-js-direct/lib/plupload-2.1.2/js/plupload.full.min.js"></script>
<script type="text/javascript" src="${ctx }/static/plugins/oss-h5-upload-js-direct/upload.js"></script>