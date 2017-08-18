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
        <form action="${ctx }/admin/appConstants/list" id="queryForm">
            <div class="J_toolsBar clearfix">
                <div class="t_label">字段名</div>
                <div class="t_text ml10">
                    <input placeholder="请输入字段名称" type="text" name="key" value="${queryDTO.key }"/>
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
                                <span>修改时间</span>
                            </td>
                            <td>
                                <span>类型</span>
                            </td>
                            <td>
                                <span>字段</span>
                            </td>
                            <td>
                                <span>说明</span>
                            </td>
                            <td style="width: 400px">
                                <span>内容</span>
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
                            <c:when test="${(appConstantss)!= null && fn:length(appConstantss) > 0}">
                                <c:forEach items="${appConstantss }" var="r">
                                    <tr>
                                        <td class="first">
                                            <div class="t_text tc">
                                                ${r.id }
                                            </div>
                                        </td>
                                        <td>
                                            <div class="t_text tc">
                                                <fmt:formatDate value="${r.createDate  }" pattern="yyyy-MM-dd HH:mm:ss"/>
                                            </div>
                                        </td>
                                        <td>
                                            <div class="t_text tc">
                                                <fmt:formatDate value="${r.updateDate  }" pattern="yyyy-MM-dd HH:mm:ss"/>
                                            </div>
                                        </td>
                                        <td>
                                            <div class="t_text tc">
                                                <c:if test="${r.type eq 1}">
                                                    文字
                                                </c:if>
                                                <c:if test="${r.type eq 2}">
                                                    图片
                                                </c:if>
                                            </div>
                                        </td>
                                        <td>
                                            <div class="t_text tc">
                                                ${r.key  }
                                            </div>
                                        </td>
                                        <td>
                                            <div class="t_text tc">
                                                <c:choose>
                                                    <c:when test="${r.key eq 'androidversion' }">
                                                        android版本号
                                                    </c:when>
                                                    <c:when test="${r.key eq 'iosversion' }">
                                                        ios版本号
                                                    </c:when>
                                                    <c:when test="${r.key eq 'banner' }">
                                                        轮播图
                                                    </c:when>
                                                    <c:when test="${r.key eq 'headTip' }">
                                                        顶部展示图
                                                    </c:when>
                                                    <c:when test="${r.key eq 'logo' }">
                                                        logo
                                                    </c:when>
                                                    <c:when test="${r.key eq 'radio' }">
                                                        广播
                                                    </c:when>
                                                    <c:when test="${r.key eq 'interestRate' }">
                                                        利率
                                                    </c:when>
                                                    <c:when test="${r.key eq 'overdueInterestRate' }">
                                                        逾期利率
                                                    </c:when>
                                                    <c:when test="${r.key eq 'registerAgreement' }">
                                                        注册协议
                                                    </c:when>
                                                    <c:when test="${r.key eq 'withholdingAgreement' }">
                                                        代扣协议
                                                    </c:when>
                                                    <c:when test="${r.key eq 'taskinstructions' }">
                                                        任务须知
                                                    </c:when>
                                                    <c:when test="${r.key eq 'loanAgreement' }">
                                                        借款协议
                                                    </c:when>
                                                </c:choose>
                                            </div>
                                        </td>
                                        <td>
                                            <c:choose>
                                                <c:when test="${r.key eq 'registerAgreement' || r.key eq 'withholdingAgreement'
                                                ||r.key eq 'taskinstructions'||r.key eq 'loanAgreement'}">
                                                    <div class="t_link">
                                                        <a href="${ctx}/admin/appConstants/dialog/richText_edit?id=${r.id }"><i class="icon"></i>显示内容</a>
                                                    </div>
                                                </c:when>
                                                <c:otherwise>
                                                    <div class="t_text tc">
                                                        ${r.value}
                                                    </div>
                                                </c:otherwise>
                                            </c:choose>
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
                                                <c:choose>
                                                    <c:when test="${r.key eq 'androidversion' }">
                                                    </c:when>
                                                    <c:when test="${r.key eq 'iosversion' }">
                                                    </c:when>
                                                    <c:when test="${r.key eq 'registerAgreement' || r.key eq 'withholdingAgreement'
                                                    ||r.key eq 'taskinstructions'||r.key eq 'loanAgreement'}">
                                                        <a href="${ctx}/admin/appConstants/dialog/richText_edit?id=${r.id }"><i class="icon"></i>编辑</a>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <a href="javascript:myEdit('${r.id }');"><i class="icon"></i>编辑</a>
                                                        <c:choose>
                                                            <c:when test="${r.deleteFlag eq 0 }">
                                                                <a href="javascript:deleteById('${r.id }', '1');"><i class="icon"></i>删除</a>
                                                            </c:when>
                                                            <c:otherwise>
                                                                <a href="javascript:deleteById('${r.id }', '0');"><i class="icon"></i>恢复</a>
                                                            </c:otherwise>
                                                        </c:choose>
                                                    </c:otherwise>
                                                </c:choose>
                                            </div>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </c:when>
                            <c:otherwise>
                                <tr>
                                    <td colspan="9">
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
        $.post('${ctx}/admin/appConstants/dialog/appConstants_edit?id='+id, {}, function(str){

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
                url : '${ctx}/admin/appConstants/ajax/delete',
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
