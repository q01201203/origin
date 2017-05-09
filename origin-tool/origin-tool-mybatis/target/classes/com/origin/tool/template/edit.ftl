<%@ page language="java" pageEncoding="utf-8" contentType="text/html;charset=utf-8"%>


<%@ include file="../../common/jstl.jsp"%>

<script type="text/javascript">

    //表单验证
    _&(function(){

        _&('#editForm').validator({
            ignore: ':hidden',
            fields : {
				<#list fieldList as field>
					<#if field.fieldName != 'id'>
					<#if field_has_next>
					${field.fieldName!''} : '${field.description!''}:required;length[~20]',
					<#else>
					${field.fieldName!''} : '${field.description!''}:required;length[~20]'
					</#if>
					</#if>
				</#list>
            },
            valid : function(form){
                var laodIdx = layer.load();

                _&('#editForm').ajaxSubmit({
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
    <form action="_&{ctx }/${_model!''}/ajax/update" id="editForm" method="post">
        <input type="hidden" name="id" value="_&{${_model!''}.id }"/>
        <div class="">
            <div class="J_formTable l_form_table">
                <table class="not_hightlight">
					<#list fieldList as field>
					<#if field.fieldName != 'id'>
                    <tr>
                        <td class="l_title w200"> ${field.description!''}</td>
                        <td>
                            <div class="J_toolsBar fl">
                                <div class="t_text w200 ml10">
                                    <input type="text" name="${field.fieldName}" value="_&{${_model}.${field.fieldName!''} }" maxlength="20"/>
                                </div>
                            </div>
                        </td>
                    </tr>
                    </#if>
					</#list>
                </table>
            </div>
        </div>
    </form>
</div>

</body>
</html>
