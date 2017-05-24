<%@ page language="java" pageEncoding="utf-8" contentType="text/html;charset=utf-8"%>


<%@ include file="../../common/jstl.jsp"%>

<script type="text/javascript">

    //表单验证
    $(function(){

        $('#editForm').validator({
            ignore: ':hidden',
            fields : {
					//createDate : ':required;',
					//updateDate : ':required;',
					mobile : ':required;mobile',
					pwd : ':required;password',
					payPwd : ':length[6]',
					//authority : '',
					//moneyMax : '',
					//alipayUsername : '',
					//alipayUseraccout : '',
					//imgFace : '',
					//imgIdFront : '',
					//imgIdBack : '',
					//userIdName : '',
					userIdNumber : 'ID_card',
					//imgPortrait : '',
					//nickname : '',
					//category : ':required;',
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
    <form action="${ctx }/appUser/ajax/update" id="editForm" method="post">
        <input type="hidden" name="id" value="${appUser.id }"/>
        <div class="">
            <div class="J_formTable l_form_table">
                <table class="not_hightlight">
                    <tr>
                        <td class="l_title w200"> 创建时间</td>
                        <td>
                            <div class="J_toolsBar fl">
                                <div class="t_text w200 ml10">
                                    <input type="text" readonly="readonly" name="createDate" value="${appUser.createDate }" maxlength="20"/>
                                </div>
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <td class="l_title w200"> 更新时间</td>
                        <td>
                            <div class="J_toolsBar fl">
                                <div class="t_text w200 ml10">
                                    <input type="text" readonly="readonly" name="updateDate" value="${appUser.updateDate }" maxlength="20"/>
                                </div>
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <td class="l_title w200"> 手机号</td>
                        <td>
                            <div class="J_toolsBar fl">
                                <div class="t_text w200 ml10">
                                    <input type="text"  name="mobile" value="${appUser.mobile }" maxlength="20"/>
                                </div>
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <td class="l_title w200"> 密码</td>
                        <td>
                            <div class="J_toolsBar fl">
                                <div class="t_text w200 ml10">
                                    <input type="text"  name="pwd" value="${appUser.pwd }" maxlength="20"/>
                                </div>
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <td class="l_title w200"> 支付密码</td>
                        <td>
                            <div class="J_toolsBar fl">
                                <div class="t_text w200 ml10">
                                    <input type="text" name="payPwd" value="${appUser.payPwd }" maxlength="20"/>
                                </div>
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <td class="l_title w200"> 权限</td>
                        <td>
                            <div class="J_toolsBar fl">
                                <div class="t_text w200 ml10">
                                    <input type="text" name="authority" value="${appUser.authority }" maxlength="20"/>
                                </div>
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <td class="l_title w200"> 额度</td>
                        <td>
                            <div class="J_toolsBar fl">
                                <div class="t_text w200 ml10">
                                    <input type="text" name="moneyMax" value="${appUser.moneyMax }" maxlength="20"/>
                                </div>
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <td class="l_title w200"> 支付宝名称</td>
                        <td>
                            <div class="J_toolsBar fl">
                                <div class="t_text w200 ml10">
                                    <input type="text" name="alipayUsername" value="${appUser.alipayUsername }" maxlength="20"/>
                                </div>
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <td class="l_title w200"> 支付宝账号</td>
                        <td>
                            <div class="J_toolsBar fl">
                                <div class="t_text w200 ml10">
                                    <input type="text" name="alipayUseraccout" value="${appUser.alipayUseraccout }" maxlength="20"/>
                                </div>
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <td class="l_title w200"> 人脸图片图片地址</td>
                        <td>
                            <div class="J_toolsBar fl">
                                <div class="t_text w200 ml10">
                                    <input type="text" name="imgFace" value="${appUser.imgFace }" maxlength="20"/>
                                </div>
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <td class="l_title w200"> 身份证正面图片地址</td>
                        <td>
                            <div class="J_toolsBar fl">
                                <div class="t_text w200 ml10">
                                    <input type="text" name="imgIdFront" value="${appUser.imgIdFront }" maxlength="20"/>
                                </div>
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <td class="l_title w200"> 身份证背面图片地址</td>
                        <td>
                            <div class="J_toolsBar fl">
                                <div class="t_text w200 ml10">
                                    <input type="text" name="imgIdBack" value="${appUser.imgIdBack }" maxlength="20"/>
                                </div>
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <td class="l_title w200"> 身份证名字</td>
                        <td>
                            <div class="J_toolsBar fl">
                                <div class="t_text w200 ml10">
                                    <input type="text" name="userIdName" value="${appUser.userIdName }" maxlength="20"/>
                                </div>
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <td class="l_title w200"> 身份证号</td>
                        <td>
                            <div class="J_toolsBar fl">
                                <div class="t_text w200 ml10">
                                    <input type="text" name="userIdNumber" value="${appUser.userIdNumber }" maxlength="20"/>
                                </div>
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <td class="l_title w200"> 头像图片地址</td>
                        <td>
                            <div class="J_toolsBar fl">
                                <div class="t_text w200 ml10">
                                    <input type="text" name="imgPortrait" value="${appUser.imgPortrait }" maxlength="20"/>
                                </div>
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <td class="l_title w200"> 昵称</td>
                        <td>
                            <div class="J_toolsBar fl">
                                <div class="t_text w200 ml10">
                                    <input type="text" name="nickname" value="${appUser.nickname }" maxlength="20"/>
                                </div>
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <td class="l_title w200"> 群体</td>
                        <td>
                            <div class="J_toolsBar fl">
                                <div class="t_text w200 ml10">
                                    <select data-placeholder="选择群体" class="chosen-select" name="category">
                                        <c:choose>
                                            <c:when test="${appUser.category eq 1}">
                                                <option value="1" selected="selected">学生</option>
                                                <option value="2" >社会人群</option>
                                            </c:when>
                                            <c:when test="${appUser.category eq 2}">
                                                <option value="1" >学生</option>
                                                <option value="2" selected="selected">社会人群</option>
                                            </c:when>
                                            <c:otherwise>
                                                <option >请选择</option>
                                                <option value="1" >学生</option>
                                                <option value="2" >社会人群</option>
                                            </c:otherwise>
                                        </c:choose>
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
