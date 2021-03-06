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
        <form action="${ctx }/test/list" id="queryForm">
            <div class="J_toolsBar clearfix">
                <div class="t_label"></div>
                <div class="t_text ml10">
                    <input placeholder="请输入区域名称" type="text" name="name" value="${queryDTO.sss }"/>
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
                                <span>2</span>
                            </td>
                            <td>
                                <span>3</span>
                            </td>
                            <td>
                                <span>4</span>
                            </td>
                            <td>
                                <span>5</span>
                            </td>
                            <td>
                                <span>操作</span>
                            </td>
                        </tr>
                        </thead>
                        <tbody>
                        <c:choose>
                            <c:when test="${(tests)!= null && fn:length(tests) > 0}">
                                <c:forEach items="${tests }" var="r">
                                    <tr>
                                        <td class="first">
                                            <div class="t_text tc">
                                                ${r.id }
                                            </div>
                                        </td>
                                        <td>
                                            <div class="t_text tc">
                                                ${r.id  }
                                            </div>
                                        </td>
                                        <td>
                                            <div class="t_text tc">
                                                ${r.sss  }
                                            </div>
                                        </td>
                                        <td>
                                            <div class="t_text tc">
                                                ${r.df  }
                                            </div>
                                        </td>
                                        <td>
                                            <div class="t_text tc">
                                                ${r.dfs  }
                                            </div>
                                        </td>
                                        <td>
                                            <div class="t_link">
                                                <a href="javascript:myEdit('${r.id }');"><i class="icon"></i>编辑</a>
                                                <a href="javascript:deleteById('${r.id }');"><i class="icon"></i>删除</a>
                                                <a href="javascript:more('${r.id }');"><i id="expand${r.id }" class="icon"></i>更多</a>
                                            </div>
                                        </td>
                                    </tr>
                                    <tr id="moreTitle${r.id }" style="display: none">
                                        <td>
                                            <div class="t_text tc">
                                                    123
                                            </div>
                                        </td>
                                        <td>
                                            <div class="t_text tc">
                                                1dffa
                                            </div>
                                        </td>
                                    </tr>
                                    <tr id="moreContent${r.id }" style="display: none">
                                        <td class="first">
                                            <div class="t_text tc">
                                                1234553
                                            </div>
                                        </td>
                                        <td>
                                            <div class="t_text tc">
                                                12klslk553
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
        $.post('${ctx}/test/dialog/test_edit?id='+id, {}, function(str){

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
                url : '${ctx}/test/ajax/delete',
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

    function more(i){
        $('#moreContent'+i).toggle();
        $('#moreTitle'+i).toggle();
        if ($('#moreContent'+i).is(":hidden")){
            $('#expand'+i).text("");
        }else{
            $('#expand'+i).text("");
        }
    }


</script>
</body>
</html>
