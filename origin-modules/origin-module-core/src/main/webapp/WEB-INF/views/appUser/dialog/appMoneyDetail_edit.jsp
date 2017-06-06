<%@ page language="java" pageEncoding="utf-8" contentType="text/html;charset=utf-8"%>


<%@ include file="../../common/jstl.jsp"%>

<script type="text/javascript">
    //表单验证
    $(function(){

        $('#editForm').validator({
            ignore: ':hidden',
            fields : {
					moneyActual : ':required;range[0~${appMoneyDetail.moneyAsk}]'
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

        $('input[name="status"]').on('click', function(){
            var val = $('input[name="status"]:checked').val();
            if(val == 2){
                $('#parentMenuSelect').show();
            }else{
                $('#parentMenuSelect').hide();
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
                                        <label>
                                            <input type="radio" name="status" value="2" checked="checked"/>审核通过
                                        </label>
                                        <label>
                                            <input type="radio" name="status" value="3" />审核不通过
                                        </label>
                                    </div>
                                </div>
                            </div>
                        </td>
                    </tr>
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
                </table>
            </div>
        </div>
    </form>
</div>

</body>
</html>
