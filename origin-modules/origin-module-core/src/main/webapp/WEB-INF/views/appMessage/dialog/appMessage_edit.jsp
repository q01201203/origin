<%@ page language="java" pageEncoding="utf-8" contentType="text/html;charset=utf-8"%>


<%@ include file="../../common/jstl.jsp"%>

<script type="text/javascript">

    //表单验证
    $(function(){

        $('#editForm').validator({
            ignore: ':hidden',
            fields : {
					createDate : ':required;length[~20]',
					updateDate : ':required;length[~20]',
					type : ':required;length[~20]',
					status : ':required;length[~20]',
					content : ':required;length[~20]',
					contentExtra : ':required;length[~20]',
					uid : ':required;length[~20]',
					deleteFlag : ':required;length[~20]'
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
    <form action="${ctx }/admin/appMessage/ajax/update" id="editForm" method="post">
        <div class="">
            <div class="J_formTable l_form_table">
                <table class="not_hightlight">
                    <tr>
                        <td class="l_title w200">消息内容</td>
                        <td>
                            <div class="J_toolsBar fl">
                                <div class="t_text w200 ml10">
                                    <input type="text" name="content" value="" maxlength="100"/>
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
