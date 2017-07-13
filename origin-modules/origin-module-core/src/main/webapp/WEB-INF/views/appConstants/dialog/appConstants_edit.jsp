<%@ page language="java" pageEncoding="utf-8" contentType="text/html;charset=utf-8"%>


<%@ include file="../../common/jstl.jsp"%>

<head>
    <link href="${ctx }/static/plugins/chosen_v1.6.2/chosen.css" rel="stylesheet" />
    <link rel="stylesheet" type="text/css" href="${ctx }/static/plugins/oss-h5-upload-js-direct/style.css"/>
</head>

<body>
<div id="addForm" class="mgt40">
    <form action="${ctx }/admin/appConstants/ajax/update" id="editForm" method="post">
        <input type="hidden" name="id" value="${appConstants.id }"/>
        <div class="">
            <div class="J_formTable l_form_table">
                <table class="not_hightlight">
                    <tr>
                        <td class="l_title w200">类型</td>
                        <td>
                            <div class="J_toolsBar fl">
                                <div class="t_label w200 ml10">
                                    <select id="type" title="选择类型" class="chosen-select" data-rule="required" name="type"
                                    ${operation == 'update'? "disabled=\"disabled\"":""} >
                                        <c:if test="${operation == 'update'}">
                                            <option id="type1" value="1" ${appConstants.type eq 1 ? "selected=\"selected\"":""}>文字</option>
                                            <option id="type2" value="2" ${appConstants.type eq 2 ? "selected=\"selected\"":""} >图片</option>
                                        </c:if>
                                        <c:if test="${operation == 'add'}">
                                            <option id="type1" value="1" selected="selected">文字</option>
                                            <option id="type2" value="2" >图片</option>
                                        </c:if>
                                    </select>
                                </div>
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <td class="l_title w200">字段名 </td>
                        <td>
                            <div class="J_toolsBar fl">
                                <div class="t_label w200 ml10">
                                    <select id="key" title="选择字段" class="chosen-select" data-rule="required" name="key"
                                    ${operation == 'update'? "disabled=\"disabled\"":""} >
                                        <c:if test="${operation == 'update'}">
                                            <option id="version" value="version" ${appConstants.key eq 'version' ? "selected=\"selected\"":""}>版本号</option>
                                            <option id="radio" value="radio" ${appConstants.key eq 'radio' ? "selected=\"selected\"":""}>广播文字</option>
                                            <option id="banner" value="banner" ${appConstants.key eq 'banner' ? "selected=\"selected\"":""}>轮播图</option>
                                            <option id="headTip" value="headTip" ${appConstants.key eq 'headTip' ? "selected=\"selected\"":""}>顶部图片</option>
                                            <option id="logo" value="logo" ${appConstants.key eq 'logo' ? "selected=\"selected\"":""}>logo图标</option>
                                        </c:if>
                                        <c:if test="${operation == 'add'}">
                                            <option id="radio" value="radio" selected="selected">广播文字</option>
                                            <option id="banner" value="banner" >轮播图</option>
                                        </c:if>
                                    </select>
                                </div>
                            </div>
                        </td>
                    </tr>
                    <tbody id="addContent">
                        <tr>
                            <td class="l_title w200"><b class="cRed">*</b>内容 </td>
                            <td>
                                <div class="J_toolsBar fl">
                                    <div class="t_text w200 ml10">
                                        <input id="valueContent" type="text" name="value" value="${appConstants.value}" data-rule="required;length[~16]"/>
                                    </div>
                                </div>
                            </td>
                        </tr>
                    </tbody>
                    <tbody id="addPic" hidden="hidden">
                        <tr>
                            <td class="l_title w200"><b class="cRed">*</b> 图片</td>
                            <td>
                                <div class="J_toolsBar fl">
                                    <div class="t_text w240 ml10">
                                        <input id="value" onclick="javascript:selectFile('value');" type="text" name="value"
                                               value="${appConstants.value}" data-rule="required"/>
                                    </div>
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td class="l_title "> 操作</td>
                            <td>
                                <div class="J_toolsBar fl">
                                    <div class="t_label w240 ml10">
                                        <div id="container">
                                            <a href="javascript:myVisit('value');" class='btn'>查看</a>
                                            <a href="javascript:selectFile('value');" class='btn'>选择文件</a>
                                            <a href="javascript:myUpload('value');" class='btn'>开始上传</a>
                                        </div>
                                    </div>
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td class="l_title "></td>
                            <td name="value">
                                <div id="uploadTip">
                                    <div  class="J_toolsBar fl">
                                        <div class="t_label w240 ml10">
                                            <div id="ossfile">你的浏览器不支持flash,Silverlight或者HTML5！</div>
                                            <pre id="console"></pre>
                                        </div>
                                    </div>
                                </div>
                            </td>
                        </tr>
                    </tbody>
                </table>
            </div>
        </div>
    </form>
</div>
<%@ include file="../../common/ossUpload.jsp"%>
</body>
</html>

<script type="text/javascript">
    //表单验证
    $(function(){

        $('#editForm').validator({
            ignore: ':hidden',
            fields : {
                type : ':required;',
                key : ':required;',
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
<script type="text/javascript">
    var parentChosen;
    $(function(){

        parentChosen = $(".chosen-select").chosen({
            no_results_text: "未找到状态",
            width : '100%'
        });

        /*var div1 = '<tr><td class="l_title w200">内容</td><td><div class="J_toolsBar fl"> <div class="t_text w200 ml10">' +
            '<input type="text" name="value" value="" /></div></div></td></tr>';

        var div2 =  '<tr> <td class="l_title "><b class="cRed">*</b> 图片</td> <td> <div class="J_toolsBar fl"> ' +
            '<div class="t_text w240 ml10"> <input id="value" onclick="javascript:selectFile(\'value\');" type="text" name="value"  value=""/> ' +
            '</div> </div> </td> </tr> <tr> <td class="l_title "><b class="cRed">*</b> 操作</td> <td> <div class="J_toolsBar fl"> ' +
            '<div class="t_label w240 ml10"> <div id="container"> <a href="javascript:myVisit(\'value\');" class='+'btn'+'>查看</a> ' +
            '<a href="javascript:selectFile(\'value\');" class='+'btn'+'>选择文件</a> <a href="javascript:myUpload(\'value\');" class='+'btn'+'>开始上传</a> ' +
            '</div> </div> </div> </td> </tr> <tr> <td class="l_title "></td> <td name="value"> <div id="uploadTip"> ' +
            '<div  class="J_toolsBar fl"> <div class="t_label w240 ml10"> <div id="ossfile"></div> ' +
            '<pre id="console"></pre> </div> </div> </div> </td> </tr>';*/

        $('#type').change(function(){
            var sValue = $(this).val();
            if(sValue == 1) {
                $('#radio').attr('selected',true);
                //$('#content').empty().append(div1);
                $('#addContent').show();
                $('#addPic').hide();
                $('#valueContent').removeAttr("disabled");
                $('#value').attr("disabled","disabled");
                $('#key').chosen("destroy").chosen({width : '100%'});
            } else if (sValue == 2){
                $('#banner').attr('selected',true);
                //$('#content').empty().append(div2);
                $('#addContent').hide();
                $('#addPic').show();
                $('#valueContent').attr("disabled","disabled");
                $('#value').removeAttr("disabled");
                $('#key').chosen("destroy").chosen({width : '100%'});
            }
        });

        $('#key').change(function(){
            var sValue = $(this).val();
            if (sValue == 'radio'){
                $('#type1').attr('selected',true);
                //$('#content').empty().append(div1);
                $('#addContent').show();
                $('#addPic').hide();
                $('#valueContent').removeAttr("disabled");
                $('#value').attr("disabled","disabled");
                $('#type').chosen("destroy").chosen({width : '100%'});
            }else if (sValue == 'banner'){
                $('#type2').attr('selected',true);
                //$('#content').empty().append(div2);
                $('#addContent').hide();
                $('#addPic').show();
                $('#valueContent').attr("disabled","disabled");
                $('#value').removeAttr("disabled");
                $('#type').chosen("destroy").chosen({width : '100%'});
            }
        });

        if(${operation == 'update'}){
            if (${appConstants.type eq 1}){
                $('#addContent').show();
                $('#addPic').hide();
                $('#valueContent').removeAttr("disabled");
                $('#value').attr("disabled","disabled");
            }
            if (${appConstants.type eq 2}){
                $('#addContent').hide();
                $('#addPic').show();
                $('#valueContent').attr("disabled","disabled");
                $('#value').removeAttr("disabled");
            }
        }
    });
</script>
