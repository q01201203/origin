<%@ page language="java" pageEncoding="utf-8" contentType="text/html;charset=utf-8"%>


<%@ include file="../../common/jstl.jsp"%>

<script type="text/javascript">

    //表单验证
    $(function(){

        $('#editForm').validator({
            ignore: ':hidden',
            fields : {
									mobile : ':required;length[~20]',
					validcode : ':required;length[~20]',
					type : ':required;length[~20]',
					createDate : ':required;length[~20]',
					status : ':required;length[~20]'
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


    });


</script>

<head>
</head>

<body>


<div id="addForm" class="mgt40">
    <form action="${ctx }/appValidcode/ajax/update" id="editForm" method="post">
        <input type="hidden" name="id" value="${appValidcode.id }"/>
        <div class="">
            <div class="J_formTable l_form_table">
                <table class="not_hightlight">
                    <tr>
                        <td class="l_title w200"> </td>
                        <td>
                            <div class="J_toolsBar fl">
                                <div class="t_text w200 ml10">
                                    <input type="text" name="mobile" value="${appValidcode.mobile }" maxlength="20"/>
                                </div>
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <td class="l_title w200"> </td>
                        <td>
                            <div class="J_toolsBar fl">
                                <div class="t_text w200 ml10">
                                    <input type="text" name="validcode" value="${appValidcode.validcode }" maxlength="20"/>
                                </div>
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <td class="l_title w200"> </td>
                        <td>
                            <div class="J_toolsBar fl">
                                <div class="t_text w200 ml10">
                                    <input type="text" name="type" value="${appValidcode.type }" maxlength="20"/>
                                </div>
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <td class="l_title w200"> </td>
                        <td>
                            <div class="J_toolsBar fl">
                                <div class="t_text w200 ml10">
                                    <input type="text" name="createDate" value="${appValidcode.createDate }" maxlength="20"/>
                                </div>
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <td class="l_title w200"> </td>
                        <td>
                            <div class="J_toolsBar fl">
                                <div class="t_text w200 ml10">
                                    <input type="text" name="status" value="${appValidcode.status }" maxlength="20"/>
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
