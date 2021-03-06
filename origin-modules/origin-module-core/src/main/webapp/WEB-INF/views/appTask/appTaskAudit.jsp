<%@ page language="java" pageEncoding="utf-8" contentType="text/html;charset=utf-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%@ include file="../common/common.jsp" %>
<script type="text/javascript">
    var parentChosen;
    $(function(){
        parentChosen = $(".chosen-select").chosen({
            no_results_text: "未找到状态",
            width : '100%'
        });
    });
</script>
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
                <div class="t_label">状态</div>
                <div class="t_label ml10" style="width: 220px;">
                    <select  class="chosen-select" name="status" title="选择状态">
                        <option value="" >选择状态</option>
                        <option value="1" ${queryDTO.appMoneyDetail.status eq 1 ? "selected=\"selected\"":""}>待审核</option>
                        <option value="2" ${queryDTO.appMoneyDetail.status eq 2 ? "selected=\"selected\"":""}>审核通过</option>
                        <option value="3" ${queryDTO.appMoneyDetail.status eq 3 ? "selected=\"selected\"":""}>审核不通过</option>
                    </select>
                    <input type="hidden" name="tid" value="${queryDTO.tid}">
                    <input type="hidden" name="uid" value="${queryDTO.uid}">
                </div>
                <div class="t_button ml10">
                    <a class="abtn red" href="javascript:myQuery();">
                        <i class="icon"></i>查询
                    </a>
                </div>
                <!--<div class="t_button ml10">
                    <a class="abtn blue" href="javascript:myEdit();">
                        <i class="icon"></i>新增
                    </a>
                </div>-->
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
                                <span>修改时间</span>
                            </td>
                            <td>
                                <span>申请金额</span>
                            </td>
                            <td>
                                <span>实际金额</span>
                            </td>
                            <td>
                                <span>类型</span>
                            </td>
                            <td>
                                <span>状态</span>
                            </td>
                            <td>
                                <span>任务名称</span>
                            </td>
                            <td>
                                <span>完成任务的用户名</span>
                            </td>
                            <td>
                                <span>完成任务的手机号</span>
                            </td>
                            <td>
                                <span>用户</span>
                            </td>
                            <td>
                                <span>操作</span>
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
                                                ${r.mid }
                                            </div>
                                        </td>
                                        <td>
                                            <div class="t_text tc">
                                                <fmt:formatDate value="${r.appMoneyDetail.createDate }" pattern="yyyy-MM-dd HH:mm:ss"/>
                                            </div>
                                        </td>
                                        <td>
                                            <div class="t_text tc">
                                                <fmt:formatDate value="${r.appMoneyDetail.updateDate }" pattern="yyyy-MM-dd HH:mm:ss"/>
                                            </div>
                                        </td>
                                        <td>
                                            <div class="t_text tc">
                                                ${r.appMoneyDetail.moneyAsk  }
                                            </div>
                                        </td>
                                        <td>
                                            <div class="t_text tc">
                                                 ${r.appMoneyDetail.moneyActual  }
                                            </div>
                                        </td>
                                        <td>
                                            <div class="t_text tc">
                                                用户收入
                                            </div>
                                        </td>
                                        <td>
                                            <div class="t_text tc">
                                                <c:choose>
                                                    <c:when test="${r.appMoneyDetail.status  eq 1}">审核中</c:when>
                                                    <c:when test="${r.appMoneyDetail.status  eq 2}">审核通过</c:when>
                                                    <c:when test="${r.appMoneyDetail.status  eq 3}">审核未通过</c:when>
                                                </c:choose>
                                            </div>
                                        </td>
                                        <td>
                                            <div class="t_text tc">
                                                ${r.appMoneyDetail.taskName}
                                            </div>
                                        </td>
                                        <td>
                                            <div class="t_text tc">
                                                    ${r.appMoneyDetail.taskUsername}
                                            </div>
                                        </td>
                                        <td>
                                            <div class="t_text tc">
                                                    ${r.appMoneyDetail.taskMobile}
                                            </div>
                                        </td>
                                        <td>
                                            <div class="t_text tc">
                                                ${r.appUser.mobile  }
                                            </div>
                                        </td>
                                        <td>
                                            <div class="t_link">
                                                <c:if test="${r.appMoneyDetail.status  eq 1}">
                                                    <a href="javascript:myEdit('${r.mid }');"><i class="icon"></i>审核</a>
                                                </c:if>
                                            </div>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </c:when>
                            <c:otherwise>
                                <tr>
                                    <td colspan="13">
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
        $.post('${ctx}/admin/appUser/money/dialog/appMoneyDetail_edit?id='+id, {}, function(str){

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
                url : '${ctx}/appMoneyDetail/ajax/delete',
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
