<%@ page language="java" pageEncoding="utf-8" contentType="text/html;charset=utf-8"%>


<%@ include file="../../common/jstl.jsp"%>

<script type="text/javascript">
    //表单验证
    $(function(){

        $('#editForm').validator({
            rules: {
                money: [/^[1-9][0-9]*00$/,'请输入100的整数']
            },
            ignore: ':hidden',
            fields : {
					moneyActual : ':required;range[0~${appMoneyDetail.type == 1?(appMoneyDetail.moneyAsk > borrowLine
					?borrowLine:appMoneyDetail.moneyAsk):(appMoneyDetail.type == 3?(appMoneyDetail.moneyAsk > appUser.balance
					?appUser.balance:appMoneyDetail.moneyAsk):appMoneyDetail.moneyAsk)}];${(appMoneyDetail.type == 1||
					appMoneyDetail.type == 2)?"money;":""}'
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

        var moneyActualSelector = $('input[name="moneyActual"]');
        var messageSelector = $('textarea[name="message"]');
        var message;
        var type = ${appMoneyDetail.type};
        var userName = "${appUser.nickname}";
        message = userName + "你好，你申请的";
        if (type == 1){
            var interestRate = 1 + ${interestRate};
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
            if (type == 1){
                var principal = toDecimal2(accDiv(moneyActualSelector.val(),interestRate));
                var interest = numSub(moneyActualSelector.val(),principal);
                messagef = message + "实际到账"+ principal + "元，手续费和利息为" + interest +"已成功通过审核";
                $('div[name="tips"]').text("打款金额为"+principal+"元");
            }else{
                messagef = message + "实际到账"+ moneyActualSelector.val() +"元" +"已成功通过审核";
            }
        }

        messageSelector.val(messagef);

        moneyActualSelector.on("input propertychange",function(){
            if (type == 1){
                var principal = toDecimal2(accDiv(moneyActualSelector.val(),interestRate));
                var interest = numSub(moneyActualSelector.val(),principal);
                messagef = message + "实际到账"+ principal + "元，手续费和利息为" + interest +"已成功通过审核";
                $('div[name="tips"]').text("打款金额为"+principal+"元");
            }else{
                messagef = message + "实际到账"+ moneyActualSelector.val() +"元" +"已成功通过审核";
            }
            messageSelector.val(messagef);
        })

        $('input[name="status"]').on('click', function(){
            var val = $('input[name="status"]:checked').val();
            if(val == 2){
                $('#parentMenuSelect').show();
                if (type == 1){
                    var principal = toDecimal2(accDiv(moneyActualSelector.val(),interestRate));
                    var interest = numSub(moneyActualSelector.val(),principal);
                    messagef = message + "实际到账"+ principal + "元，手续费和利息为" + interest +"已成功通过审核";
                    $('div[name="tips"]').text("打款金额为"+principal+"元");
                }else{
                    messagef = message + "实际到账"+ moneyActualSelector.val() +"元" +"已成功通过审核";
                }
                messageSelector.val(messagef);
            }else{
                $('#parentMenuSelect').hide();
                messagef = message + "未能通过审核";
                if (${borrowLine <= 0 && appMoneyDetail.type == 1}){
                    messagef += ",你已经没有借款额度了";
                }
                messageSelector.val(messagef);
            }
        });


    });

    function numSub(num1, num2) {
        var baseNum, baseNum1, baseNum2;
        var precision;// 精度
        try {
            baseNum1 = num1.toString().split(".")[1].length;
        } catch (e) {
            baseNum1 = 0;
        }
        try {
            baseNum2 = num2.toString().split(".")[1].length;
        } catch (e) {
            baseNum2 = 0;
        }
        baseNum = Math.pow(10, Math.max(baseNum1, baseNum2));
        precision = (baseNum1 >= baseNum2) ? baseNum1 : baseNum2;
        return ((num1 * baseNum - num2 * baseNum) / baseNum).toFixed(precision);
    };

    function accDiv(arg1,arg2){
        var t1=0,t2=0,r1,r2;
        try{t1=arg1.toString().split(".")[1].length}catch(e){}
        try{t2=arg2.toString().split(".")[1].length}catch(e){}
        with(Math){
            r1=Number(arg1.toString().replace(".",""))
            r2=Number(arg2.toString().replace(".",""))
            return (r1/r2)*pow(10,t2-t1);
        }
    }

    function toDecimal2(x) {
        var f = parseFloat(x);
        if (isNaN(f)) {
            return false;
        }
        var f = Math.round(x*100)/100;
        var s = f.toString();
        var rs = s.indexOf('.');
        if (rs < 0) {
            rs = s.length;
            s += '.';
        }
        while (s.length <= rs + 2) {
            s += '0';
        }
        return s;
    }
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
                                <td class="l_title w200">
                                    <c:choose>
                                        <c:when test="${appMoneyDetail.type == 1}">
                                            用户需还金额
                                        </c:when>
                                        <c:otherwise>
                                            实际金额
                                        </c:otherwise>
                                    </c:choose>
                                </td>
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
                    <c:choose>
                        <c:when test="${appMoneyDetail.type == 1}">
                            <tr >
                                <td class="l_title w200"> 提示</td>
                                <td>
                                    <div class="J_toolsBar fl">
                                        <div name="tips" class="t_text w200 ml10">
                                        </div>
                                    </div>
                                </td>
                            </tr>
                        </c:when>
                    </c:choose>
                    <tr >
                        <td class="l_title w200"> 消息</td>
                        <td>
                            <div class="J_toolsBar fl">
                                <div class="t_textarea w200 ml10">
                                    <!--<input type="text" name="message" value="" maxlength="100"/>-->
                                    <textarea name="message" value="" maxlength="100"></textarea>
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
