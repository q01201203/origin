<%@ page language="java" pageEncoding="utf-8" contentType="text/html;charset=utf-8"%>


<%@ include file="../../common/jstl.jsp"%>

<script type="text/javascript">
    //表单验证
    $(function(){

        $('#editForm').validator({
            rules: {
                money: [/^[1-9]+00$/,'请输入100的整数']
            },
            ignore: ':hidden',
            fields : {
					moneyActual : ':required;range[0~${appMoneyDetail.type == 1?(appMoneyDetail.moneyAsk > borrowLine
					?borrowLine:appMoneyDetail.moneyAsk):appMoneyDetail.moneyAsk}];money;'
            },
            valid : function(form){
                var laodIdx = layer.load();

                $('#editForm').ajaxSubmit({
                    data : {
                    },
                    traditional : true,
                    success : function(result){
                        layer.close(laodIdx);
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

        var message;
        var type = ${appMoneyDetail.type};
        var userName = "${appUser.nickname}";
        message = userName + "你好，你申请的";
        if (type == 1){
            message += "贷款";
        }else if(type == 2){
            message += "还款";
        }else if(type == 3){
            message += "提现";
        }else if(type == 4){
            message += "任务";
        }
        message += ${appMoneyDetail.moneyAsk} + "元";
        var messagef;
        if (${borrowLine <= 0 && appMoneyDetail.type == 1}){
            messagef = message + "未能通过审核,你已经没有借款额度了";
        }else{
            messagef = message + "实际到账"+ $('input[name="moneyActual"]').val() +"元" +"已成功通过审核";
        }

        $('input[name="message"]').val(messagef);

        $('input[name="moneyActual"]').on("input propertychange",function(){
            messagef = message + "实际到账"+ $('input[name="moneyActual"]').val() +"元" +"已成功通过审核";
            $('input[name="message"]').val(messagef);
        })

        $('input[name="status"]').on('click', function(){
            var val = $('input[name="status"]:checked').val();
            if(val == 2){
                $('#parentMenuSelect').show();
                messagef = message + "实际到账"+ $('input[name="moneyActual"]').val() +"元" +"已成功通过审核";
                $('input[name="message"]').val(messagef);
            }else{
                $('#parentMenuSelect').hide();
                messagef = message + "未能通过审核";
                if (${borrowLine <= 0 && appMoneyDetail.type == 1}){
                    messagef += ",你已经没有借款额度了";
                }
                $('input[name="message"]').val(messagef);
            }
        });


    });
</script>

<head>
</head>

<body>


<div id="addForm" class="mgt40">
    <form action="${ctx }/admin/appUser/money/ajax/update" id="editForm" method="post">
        <input type="hidden" name="id" value="${appMoneyDetail.id }"/>
        <div class="">
            <div class="J_formTable l_form_table">
                <table class="not_hightlight">
                    <tr>
                        <td class="l_title w200"> 状态</td>
                        <td>
                            <div class="J_toolsBar fl">
                                <div class="t_label ml10" style="width: 220px;">
                                    <!--<input type="text" name="status" value="${appMoneyDetail.status }" maxlength="20"/>-->
                                    <div class="t_check w200 ml10">
                                        <c:choose>
                                            <c:when test="${borrowLine <= 0 && appMoneyDetail.type == 1}">
                                                <label>
                                                    <input type="radio" name="status" value="3" checked="checked"/>审核不通过
                                                </label>
                                            </c:when>
                                            <c:otherwise>
                                                <label>
                                                    <input type="radio" name="status" value="2" checked="checked"/>审核通过
                                                </label>
                                                <label>
                                                    <input type="radio" name="status" value="3" />审核不通过
                                                </label>
                                            </c:otherwise>
                                        </c:choose>
                                    </div>
                                </div>
                            </div>
                        </td>
                    </tr>
                    <c:choose>
                        <c:when test="${borrowLine <= 0 && appMoneyDetail.type == 1}">
                        </c:when>
                        <c:otherwise>
                            <tr id="parentMenuSelect">
                                <td class="l_title w200"> 实际金额</td>
                                <td>
                                    <div class="J_toolsBar fl">
                                        <div class="t_text w200 ml10">
                                            <input type="text" name="moneyActual" value="${appMoneyDetail.moneyActual }" maxlength="20"/>
                                        </div>
                                    </div>
                                </td>
                            </tr>
                        </c:otherwise>
                    </c:choose>
                    <tr >
                        <td class="l_title w200"> 消息</td>
                        <td>
                            <div class="J_toolsBar fl">
                                <div class="t_text w200 ml10">
                                    <input type="text" name="message" value="" maxlength="100"/>
                                </div>
                            </div>
                        </td>
                    </tr>
                </table>
            </div>
        </div>
    </form>
</div>

</body>
</html>
