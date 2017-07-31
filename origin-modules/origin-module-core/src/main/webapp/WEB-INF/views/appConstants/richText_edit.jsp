<%@ page language="java" pageEncoding="utf-8" contentType="text/html;charset=utf-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%@ include file="../common/common.jsp" %>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>${menu_name } - ${title }</title>
</head>

<body <%@ include file="../common/skin.jsp" %>>
<%@ include file="../common/head.jsp" %>
<%@ include file="../common/menu.jsp" %>
<div class="J_content">
    <div class="mt20 plr20">
        <form action="${ctx }/admin/appConstants/ajax/update" id="editForm" method="post">
            <input type="hidden" name="id" value="${appConstants.id }"/>
            <div class="">
                <div class="J_formTable l_form_table">
                    <table class="not_hightlight">
                        <tbody id="addContent">
                        <tr>
                            <td class="l_title w200"><b class="cRed">*</b>内容</td>
                            <td>
                                <!-- 加载编辑器的容器-->
                                <script id="uEcontainer" name="value" type="text/plain">${appConstants.value}</script>
                            </td>
                        </tr>
                        </tbody>
                        <tr>
                            <td class="l_title w200"><b class="cRed">*</b>操作</td>
                            <td>
                                <input type="submit">
                            </td>
                        </tr>
                    </table>
                </div>
            </div>
        </form>
    </div>
</div>
<script type="text/javascript">
    //表单验证
    $(function(){

        $('#editForm').validator({
            ignore: ':hidden',
            fields : {
                type : ':required;',
                key : ':required;'
            },
            valid : function(form){

                $('#editForm').ajaxSubmit({
                    data : {
                    },
                    traditional : true,
                    success : function(result){
                        if(result.success){
                            alert('保存成功');
                            window.location.reload();
                        }else{
                            alert(result.msg);
                        }
                    }
                });
            }
        });
    });

</script>
<script type="text/javascript">
    window.UEDITOR_HOME_URL = "${ctx}/static/plugins/uEditor/";
</script>
<script type="text/javascript" src="${ctx}/static/plugins/uEditor/ueditor.config.js"></script>
<!-- 编辑器源码文件 -->
<script type="text/javascript" src="${ctx}/static/plugins/uEditor/ueditor.all.js"></script>
<!-- 实例化编辑器 -->
<script type="text/javascript">
    var editor = UE.getEditor('uEcontainer');
</script>
</body>
</html>
