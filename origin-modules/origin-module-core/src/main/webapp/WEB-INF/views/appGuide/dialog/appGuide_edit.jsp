<%@ page language="java" pageEncoding="utf-8" contentType="text/html;charset=utf-8"%>


<%@ include file="../../common/jstl.jsp"%>



<head>
    <link href="${ctx }/static/plugins/chosen_v1.6.2/chosen.css" rel="stylesheet" />
</head>

<body>


<div id="addForm" class="mgt40">
    <form action="${ctx }/admin/appGuide/ajax/update" id="editForm" method="post">
        <input type="hidden" name="id" value="${appGuide.id }"/>
        <div class="">
            <div class="J_formTable l_form_table">
                <table class="not_hightlight">
                    <tr>
                        <td class="l_title w200">安全指南标题 </td>
                        <td>
                            <div class="J_toolsBar fl">
                                <div class="t_text w200 ml10">
                                    <input type="text" name="guideName" value="${appGuide.guideName }" maxlength="20"/>
                                </div>
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <td class="l_title w200">安全指南内容 </td>
                        <td>
                            <div class="J_toolsBar fl">
                                <div class="t_text w200 ml10">
                                    <input type="text" name="guideContent" value="${appGuide.guideContent }" maxlength="140"/>
                                </div>
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <td class="l_title w200">安全指南分类 </td>
                        <td>
                            <div class="J_toolsBar fl">
                                <div class="t_label w200 ml10">
                                    <select data-placeholder="选择安全指南分类" class="chosen-select" name="guideType" ${appGuide.id != null ? " disabled=\"disabled\"":""}>
                                        <option value="1" ${appGuide.guideType eq 1 ? "selected=\"selected\"":""}>注册登录</option>
                                        <option value="2" ${appGuide.guideType eq 2 ? "selected=\"selected\"":""}>贷款</option>
                                        <option value="3" ${appGuide.guideType eq 3 ? "selected=\"selected\"":""}>还款</option>
                                    </select>
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
<script type="text/javascript">

    //表单验证
    $(function(){

        $('#editForm').validator({
            ignore: ':hidden',
            fields : {
                guideType : ':required;',
                guideName : ':required;length[~20]',
                guideContent : ':required;length[~120]'
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

    var parentChosen;
    $(function() {
        parentChosen = $(".chosen-select").chosen({
            no_results_text: "未找到分类",
            width: '100%'
        });
    });
</script>