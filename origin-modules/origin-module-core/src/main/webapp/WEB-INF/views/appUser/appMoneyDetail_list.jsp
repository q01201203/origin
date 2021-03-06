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
    <link href="${ctx }/static/plugins/chosen_v1.6.2/chosen.css" rel="stylesheet" />
</head>

<body <%@ include file="../common/skin.jsp" %>>
<%@ include file="../common/head.jsp" %>
<%@ include file="../common/menu.jsp" %>
<div class="J_content">
    <div class="mt20 plr20">
        <form action="${ctx }/admin/appUser/money/list" id="queryForm">
            <div class="J_toolsBar clearfix">
                <div class="t_label">状态</div>
                <div class="t_label ml10" style="width: 220px;">
                    <select title="选择状态" class="chosen-select" name="status">
                        <option value="" >选择状态</option>
                        <option value="1" ${queryDTO.status eq 1 ? "selected=\"selected\"":""}>待审核</option>
                        <option value="2" ${queryDTO.status eq 2 ? "selected=\"selected\"":""}>审核通过</option>
                        <option value="3" ${queryDTO.status eq 3 ? "selected=\"selected\"":""}>审核不通过</option>
                    </select>
                    <input type="hidden" name="type" value="${queryDTO.type}">
                    <input type="hidden" name="uid" value="${queryDTO.uid}">
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
                            <c:if test="${queryDTO.type eq 1}">
                                <td>
                                    <span>还款期限</span>
                                </td>
                                <td>
                                    <span>截止日期</span>
                                </td>
                                <td>
                                    <span>还款状态</span>
                                </td>
                            </c:if>
                            <c:if test="${queryDTO.type eq 2}">
                                <td>
                                    <span>还款方式</span>
                                </td>
                                <td>
                                    <span>还款时间</span>
                                </td>
                                <td>
                                    <span>逾期滞纳金</span>
                                </td>
                                <td>
                                    <span>对应借款记录</span>
                                </td>
                                <td>
                                    <span>UPay订单号</span>
                                </td>
                            </c:if>
                            <c:if test="${queryDTO.type eq 4}">
                                <td>
                                    <span>任务名称</span>
                                </td>
                                <td>
                                    <span>完成任务的用户名</span>
                                </td>
                                <td>
                                    <span>完成任务的手机号</span>
                                </td>
                            </c:if>
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
                            <c:when test="${(appMoneyDetails)!= null && fn:length(appMoneyDetails) > 0}">
                                <c:forEach items="${appMoneyDetails }" var="r">
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
                                                <fmt:formatDate value="${r.updateDate }" pattern="yyyy-MM-dd HH:mm:ss"/>
                                            </div>
                                        </td>
                                        <td>
                                            <div class="t_text tc">
                                                ${r.moneyAsk  }
                                            </div>
                                        </td>
                                        <td>
                                            <div class="t_text tc">
                                                 ${r.moneyActual  }
                                            </div>
                                        </td>
                                        <td>
                                            <div class="t_text tc">
                                                <c:choose>
                                                    <c:when test="${r.type eq 1 }">借款</c:when>
                                                    <c:when test="${r.type eq 2 }">还款</c:when>
                                                    <c:when test="${r.type eq 3 }">提现</c:when>
                                                    <c:when test="${r.type eq 4 }">用户收入</c:when>
                                                </c:choose>
                                            </div>
                                        </td>
                                        <td>
                                            <div class="t_text tc">
                                                <c:choose>
                                                    <c:when test="${r.status  eq 1}">审核中</c:when>
                                                    <c:when test="${r.status  eq 2}">审核通过</c:when>
                                                    <c:when test="${r.status  eq 3}">审核未通过</c:when>
                                                </c:choose>
                                            </div>
                                        </td>
                                        <c:if test="${queryDTO.type eq 1}">
                                            <td>
                                                <div class="t_text tc">
                                                    <c:choose>
                                                        <c:when test="${r.repayTimeType eq 1 }">7天</c:when>
                                                        <c:when test="${r.repayTimeType eq 2 }">15天</c:when>
                                                    </c:choose>
                                                </div>
                                            </td>
                                            <td>
                                                <div class="t_text tc">
                                                        <fmt:formatDate value="${r.repayDeadline}" pattern="yyyy-MM-dd HH:mm:ss"/>
                                                </div>
                                            </td>
                                            <td>
                                                <div class="t_text tc">
                                                    <c:choose>
                                                        <c:when test="${r.repayStatus eq 1 }">已还款</c:when>
                                                        <c:when test="${r.repayStatus eq 0 }">未还款</c:when>
                                                    </c:choose>
                                                </div>
                                            </td>
                                        </c:if>
                                        <c:if test="${queryDTO.type eq 2}">
                                            <td>
                                                <div class="t_text tc">
                                                    <c:choose>
                                                        <c:when test="${r.repayWay eq 1 }">支付宝</c:when>
                                                        <c:when test="${r.repayWay eq 2 }">银行卡</c:when>
                                                        <c:when test="${r.repayWay eq 3 }">余额</c:when>
                                                    </c:choose>
                                                </div>
                                            </td>
                                            <td>
                                                <div class="t_text tc">
                                                    <fmt:formatDate value="${r.repayTime  }" pattern="yyyy-MM-dd HH:mm:ss"/>
                                                </div>
                                            </td>
                                            <td>
                                                <div class="t_text tc">
                                                        ${r.delayMoney}
                                                </div>
                                            </td>
                                            <td>
                                                <div class="t_link">
                                                    <a href="${ctx}/admin/appUser/money/list?type=1&id=${r.pid}"><i class="icon"></i>查看</a>
                                                </div>
                                            </td>
                                            <td>
                                                <div class="t_link">
                                                    <a onclick="getOrderStatus(${r.extensionOne})"><i class="icon"></i>查看订单（${r.extensionOne}）结果</a>
                                                </div>
                                            </td>
                                        </c:if>
                                        <c:if test="${queryDTO.type eq 4}">
                                            <td>
                                                <div class="t_text tc">
                                                    ${r.taskName}
                                                </div>
                                            </td>
                                            <td>
                                                <div class="t_text tc">
                                                        ${r.taskUsername}
                                                </div>
                                            </td>
                                            <td>
                                                <div class="t_text tc">
                                                        ${r.taskMobile}
                                                </div>
                                            </td>
                                        </c:if>
                                        <td>
                                            <div class="t_text tc">
                                                ${r.appUser.mobile  }
                                            </div>
                                        </td>
                                        <td>
                                            <div class="t_link">
                                                <c:if test="${r.status  eq 1}">
                                                    <a href="javascript:myEdit('${r.id }');"><i class="icon"></i>审核</a>
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
            title = '审核';
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
    
    
    function getOrderStatus(orderid) {
        if (orderid !=null && orderid !=''){
            orderid = orderid+'';
            var mer_date = orderid.substring(0,8);
            $.get("http://106.14.11.68:8070/renxinhuaAPI/app/upay/merOrderInfoQuery?order_id="+orderid+"&mer_date="+mer_date
                , function(result){
                    if(result.code == 1){
                        alert("信息描述："+result.message +"\n    " +
                            "金额： "+result.data.amount/100 +"元\n    " +
                            "联动交易号："+result.data.tradeNo +"\n    " +
                            "对账日期： "+result.data.settleDate +"\n    " +
                            "交易状态： "+result.data.tradeState +"\n    "+
                            "支付日期： "+result.data.payDate);
                    }else{
                        alert("信息描述："+result.message);
                    }

                });
        }else{
            alert("无订单号");
        }
    }
</script>
</body>
</html>
