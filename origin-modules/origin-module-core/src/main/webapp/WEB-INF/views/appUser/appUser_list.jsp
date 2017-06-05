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
        <form action="${ctx }/admin/appUser/user/list" id="queryForm">
            <div class="J_toolsBar clearfix">
                <div class="t_label">手机号</div>
                <div class="t_text ml10">
                    <input placeholder="请输入手机号" type="text" name="mobile" value="${queryDTO.mobile }"/>
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
                                <span>手机号</span>
                            </td>
                            <td>
                                <span>操作</span>
                            </td>
                        </tr>
                        </thead>
                        <tbody>
                        <c:choose>
                        <c:when test="${(appUsers)!= null && fn:length(appUsers) > 0}">
                        <c:forEach items="${appUsers }" var="r">
                        <tr>
                            <td class="first">
                                <div class="t_text tc">
                                        ${r.id }
                                </div>
                            </td>
                            <td>
                                <div class="t_text tc">
                                        ${r.mobile  }
                                </div>
                            </td>
                            <td>
                                <div class="t_link">
                                    <!--<a href="javascript:myEdit('${r.id }');"><i class="icon"></i>编辑</a>
                                    <a href="javascript:deleteById('${r.id }');"><i class="icon"></i>删除</a>-->
                                    <a href="${ctx}/admin/appUser/user/detail/appUser_edit?id=${r.id }"><i class="icon"></i>详细信息</a>
                                    <a href="${ctx}/admin/appTask/userTask/list?uid=${r.id}"><i class="icon"></i>审核任务</a>
                                    <a href="${ctx }/admin/appUser/money/list?type=1&uid=${r.id}"><i class="icon"></i>借款记录</a>
                                    <a href="${ctx }/admin/appUser/money/list?type=2&uid=${r.id}"><i class="icon"></i>还款记录</a>
                                    <a href="${ctx }/admin/appUser/money/list?type=3&uid=${r.id}"><i class="icon"></i>提现记录</a>
                                    <a href="${ctx }/admin/appUser/money/list?type=4&uid=${r.id}"><i class="icon"></i>收入记录</a>
                                </div>
                            </td>
                        </tr>
                </c:forEach>
                </c:when>
                <c:otherwise>
                    <tr>
                        <td colspan="6">
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
        $.post('${ctx}/admin/appUser/user/dialog/appUser_edit?id='+id, {}, function(str){

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
                url : '${ctx}/admin/appUser/user/ajax/delete',
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
