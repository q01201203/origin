<%--
  Created by IntelliJ IDEA.
  User: lc
  Date: 2017/6/23
  Time: 11:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="jstl.jsp" %>

<input type="hidden" name="myradio" value="local_name" />
<input type="hidden" name="myradio" value="random_name" checked=true/>
<input type="hidden" id='dirname' placeholder="如果不填，默认是上传到根目录" size=50>
<a id="selectfiles" href="javascript:void(0);" class='btn' style="visibility:hidden">选择文件</a>
<a id="postfiles" href="javascript:void(0);" class='btn' style="visibility:hidden">开始上传</a>

<script type="text/javascript">
    function selectFile(name) {
        $('#uploadTip').remove();
        var div = '<div id="uploadTip">'
            + '<div class="J_toolsBar fl">'
            + '<div class="t_label w240 ml10">'
            + '<div id="ossfile"></div>'
            + '<pre id="console"></pre>'
            + '</div>'
            + '</div>'
            + '</div>';
        $("td[name=" + name + "]").empty().append(div);
        document.getElementById('selectfiles').click();
    }

    function myUpload(name) {
        if (name == null || name == '') {
            //document.getElementById('ossfile').innerHTML = '';
            //document.getElementById('selectfiles').click();
        } else {
            document.getElementById('postfiles').name = name;
            document.getElementById('postfiles').click();
        }
    }

    function myVisit(name) {
        var url = $("#" + name).val();
        if (url != null && url != '') {
            if (url.indexOf('http://') < 0 && url.indexOf('https://') < 0) {
                url = 'http://' + url;
            }
            window.open(url);
        }
    }
</script>
<script type="text/javascript" src="${ctx }/static/plugins/oss-h5-upload-js-direct/lib/crypto1/crypto/crypto.js"></script>
<script type="text/javascript" src="${ctx }/static/plugins/oss-h5-upload-js-direct/lib/crypto1/hmac/hmac.js"></script>
<script type="text/javascript" src="${ctx }/static/plugins/oss-h5-upload-js-direct/lib/crypto1/sha1/sha1.js"></script>
<script type="text/javascript" src="${ctx }/static/plugins/oss-h5-upload-js-direct/lib/base64.js"></script>
<script type="text/javascript" src="${ctx }/static/plugins/oss-h5-upload-js-direct/lib/plupload-2.1.2/js/plupload.full.min.js"></script>
<script type="text/javascript" src="${ctx }/static/plugins/oss-h5-upload-js-direct/upload.js"></script>