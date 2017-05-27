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
        <form action="${ctx }/admin/appTask/list" id="queryForm">
            <div class="J_toolsBar clearfix">
                <div class="t_label"></div>
                <div class="t_text ml10">
                    <input placeholder="请输入任务名称" type="text" name="name" value="${queryDTO.taskName }"/>
                </div>
                <div class="t_button ml10">
                    <a class="abtn red" href="javascript:myQuery();">
                        <i class="icon"></i>查询
                    </a>
                </div>
                <div class="t_button ml10">
                    <a class="abtn blue" href="javascript:myEdit();">
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
                                <span>任务数量</span>
                            </td>
                            <td>
                                <span>任务类型</span>
                            </td>
                            <td>
                                <span>任务金额</span>
                            </td>
                            <td>
                                <span>任务图片地址</span>
                            </td>
                            <td>
                                <span>热门</span>
                            </td>
                            <td>
                                <span>开始时间</span>
                            </td>
                            <td>
                                <span>结束时间</span>
                            </td>
                            <td>
                                <span>简单步骤</span>
                            </td>
                            <td>
                                <span>详细步骤</span>
                            </td>
                            <td>
                                <span>任务链接</span>
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
                                                ${r.taskNumber  }
                                            </div>
                                        </td>
                                        <td>
                                            <div class="t_text tc">
                                                ${r.taskType  }
                                            </div>
                                        </td>
                                        <td>
                                            <div class="t_text tc">
                                                ${r.taskMoney  }
                                            </div>
                                        </td>
                                        <td>
                                            <div class="t_text tc">
                                                ${r.taskImg  }
                                            </div>
                                        </td>
                                        <td>
                                            <div class="t_text tc">
                                                ${r.taskHot  }
                                            </div>
                                        </td>
                                        <td>
                                            <div class="t_text tc">
                                                <fmt:formatDate value="${r.taskStartTime }" pattern="yyyy-MM-dd HH:mm:ss"/>
                                            </div>
                                        </td>
                                        <td>
                                            <div class="t_text tc">
                                                <fmt:formatDate value="${r.taskEndTime }" pattern="yyyy-MM-dd HH:mm:ss"/>
                                            </div>
                                        </td>
                                        <td>
                                            <div class="t_text tc">
                                                    ${r.taskSimpleStep  }
                                            </div>
                                        </td>
                                        <td>
                                            <div class="t_text tc">
                                                    ${r.taskDetailedStep  }
                                            </div>
                                        </td>
                                        <td>
                                            <div class="t_text tc">
                                                    ${r.taskLink  }
                                            </div>
                                        </td>
                                        <td>
                                            <div class="t_link">
                                                <a href="javascript:myEdit('${r.id }');"><i class="icon"></i>编辑</a>
                                                <a href="javascript:deleteById('${r.id }');"><i class="icon"></i>删除</a>
                                                <a href="${ctx}/admin/appTask/userTask/list?tid=${r.id}"><i class="icon"></i>领取人员</a>
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


    function deleteById(id){
        var content = '确定要删除数据吗？';
        layer.confirm(content, function(index){
            layer.close(index);

            var loadIdx = layer.load();
            $.ajax({
                url : '${ctx}/admin/appTask/ajax/delete',
                type : 'post',
                data : {
                    'id' : id
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
