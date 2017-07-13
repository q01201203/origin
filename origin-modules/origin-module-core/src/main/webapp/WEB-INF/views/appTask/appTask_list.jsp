<%@ page language="java" pageEncoding="utf-8" contentType="text/html;charset=utf-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%@ include file="../common/common.jsp" %>
<head>
    <link href="${ctx }/static/plugins/chosen_v1.6.2/chosen.css" rel="stylesheet" />
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>${menu_name } - ${title }</title>
</head>
<script type="text/javascript">
    var parentChosen;
    $(function(){
        parentChosen = $(".chosen-select").chosen({
            no_results_text: "未找到",
            width : '100%'
        });
    });
</script>
<body <%@ include file="../common/skin.jsp" %>>
<%@ include file="../common/head.jsp" %>
<%@ include file="../common/menu.jsp" %>
<div class="J_content">
    <div class="mt20 plr20">
        <form action="${ctx }/admin/appTask/list" id="queryForm">
            <div class="J_toolsBar clearfix">
                <div class="t_label">任务名称</div>
                <div class="t_text ml10">
                    <input placeholder="请输入任务名称" type="text" name="taskName" value="${queryDTO.taskName }"/>
                </div>
                <div class="t_label w240 ml10">
                    <select data-placeholder="选择类型" class="chosen-select" name="taskType">
                        <option value="" >选择类型</option>
                        <option value="1" ${queryDTO.taskType eq 1 ? "selected=\"selected\"":""}>普通任务</option>
                        <option value="2" ${queryDTO.taskType eq 2 ? "selected=\"selected\"":""}>高额任务</option>
                        <option value="3" ${queryDTO.taskType eq 3 ? "selected=\"selected\"":""}>限时任务</option>
                    </select>
                </div>
                <div class="t_button ml10">
                    <a class="abtn red" href="javascript:myQuery();">
                        <i class="icon"></i>查询
                    </a>
                </div>
                <div class="t_button ml10">
                    <a class="abtn blue" href="${ctx}/admin/appTask/task/detail/appTask_edit?operation=add">
                        <i class="icon"></i>新增
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
                                <span>创建时间</span>
                            </td>
                            <td>
                                <span>任务名字</span>
                            </td>
                            <td>
                                <span>任务类型</span>
                            </td>
                            <td>
                                <span>状态</span>
                            </td>
                            <td>
                                <span>操作</span>
                            </td>
                        </tr>
                        </thead>
                        <tbody>
                        <c:choose>
                            <c:when test="${(appTasks)!= null && fn:length(appTasks) > 0}">
                                <c:forEach items="${appTasks }" var="r">
                                    <tr>
                                        <td class="first">
                                            <div class="t_text tc">
                                                ${r.id }
                                            </div>
                                        </td>
                                        <td>
                                            <div class="t_text tc">
                                                <fmt:formatDate value="${r.createDate }" pattern="yyyy-MM-dd HH:mm:ss"/>
                                            </div>
                                        </td>
                                        <td>
                                            <div class="t_text tc">
                                                ${r.taskName  }
                                            </div>
                                        </td>
                                        <td>
                                            <div class="t_text tc">
                                                <c:if test="${r.taskType eq 1}">
                                                    普通任务
                                                </c:if>
                                                <c:if test="${r.taskType eq 2}">
                                                    高额任务
                                                </c:if>
                                                <c:if test="${r.taskType eq 3}">
                                                    限时任务
                                                </c:if>
                                            </div>
                                        </td>
                                        <td>
                                            <div class="t_text tc">
                                                <c:choose>
                                                    <c:when test="${r.deleteFlag eq 0 }">
                                                        <label class="normal_flag">正常</label>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <label class="delete_flag">删除</label>
                                                    </c:otherwise>
                                                </c:choose>
                                            </div>
                                        </td>
                                        <td>
                                            <div class="t_link">
                                                <!--<a href="javascript:myEdit('${r.id }');"><i class="icon"></i>编辑</a>
                                                <a href="javascript:deleteById('${r.id }');"><i class="icon"></i>删除</a>-->
                                                <a href="${ctx}/admin/appTask/task/detail/appTask_edit?id=${r.id }&operation=update"><i class="icon">&#xe5d4;</i>详细信息</a>
                                                <a href="${ctx}/admin/appTask/userTask/list?tid=${r.id}"><i class="icon"></i>审批领取人员</a>
                                                <c:choose>
                                                    <c:when test="${r.deleteFlag eq 0 }">
                                                        <a href="javascript:deleteById('${r.id }', '1');"><i class="icon"></i>删除</a>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <a href="javascript:deleteById('${r.id }', '0');"><i class="icon"></i>恢复</a>
                                                    </c:otherwise>
                                                </c:choose>
                                            </div>
                                        </td>
                                    </tr>
                                </c:forEach>
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
                <%@ include file="../common/pager.jsp"%>
            </div>
        </form>
    </div>
</div>
<script src="${ctx }/static/plugins/chosen_v1.6.2/chosen.jquery.js"></script>
<script type="text/javascript">
    function myEdit(id){
        var loadIdx = layer.load();
        var title = '添加区域';
        if(!id){
            id = '';
        }else{
            title = '修改区域';
        }
        $.post('${ctx}/admin/appTask/dialog/appTask_edit?id='+id, {}, function(str){

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

    function myQuery(){
        $('#queryForm').submit();
    }

    function deleteById(id, deleteFlag){
        var ids = new Array();
        ids.push(id);

        var content = '';
        if(deleteFlag == '0'){
            content = '确定要恢复数据吗？';
        }else{
            content = '确定要删除数据吗？';
        }

        layer.confirm(content, function(index){
            layer.close(index);

            var loadIdx = layer.load();
            $.ajax({
                url : '${ctx}/admin/appTask/ajax/delete',
                type : 'post',
                data : {
                    'id' : ids,
                    'deleteFlag' : deleteFlag
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
