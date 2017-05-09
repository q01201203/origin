
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://jsptags.com/tags/navigation/pager" prefix="pg"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<style type="text/css">
	.pageLink {
		border: 1px solid #dddddd;
		padding: 4px 12px;
		text-decoration: none;
	}

</style>
</head>

<body>
    <input type="hidden" name="currentPage" id="currentPage">
	<div style="text-align: center; border: 0;padding: 4px 12px;" class="pageDiv mt20">
		<pg:pager url="#" items="${page.total}" maxPageItems="${page.pageSize}" maxIndexPages="1000" isOffset="true">
		          第:${page.pageNum }页/共:${page.pages}页
			<pg:first>
				<a id="first" href="javascript:void(0)" class="pageLink">首页</a>
			</pg:first>
			<c:if test="${page.pageNum != 1 && page.pages > 0 }">
			  <a id="prev" href="javascript:void(0)" class="pageLink">上一页</a>
			</c:if>
			<c:if test="${page.pageNum != page.pages  && page.total > 0 }">
			   <a id="next" href="javascript:void(0)" class="pageLink">下一页</a>
			</c:if>
			<pg:last>
			   <a id="last" href="javascript:void(0)" class="pageLink">尾页</a>
			</pg:last>
			总共：${page.total}条
		</pg:pager>
	</div>
<script type="text/javascript">
	$(function(){
		var currentPage = ${page.pageNum};
		var totalPage = ${page.pages };
		$("#first").bind("click",function(event){
			pageAction(1);
		});
		$("#prev").bind("click",function(event){
			if(currentPage>1){
				currentPage--;
			}else{
				currentPage = 1;
			}
			pageAction(currentPage);
		});
		$("#next").bind("click",function(event){
			console.log("totalPage:"+totalPage);
			console.log("currentPage:"+currentPage);
			if(totalPage>currentPage){
				currentPage++;
			}else{
				currentPage=totalPage;
			}
			pageAction(currentPage);
		});
		$("a[name='doNumberPage']").bind("click",function(event){
			pageAction(currentPage);
		});
		$("#last").bind("click",function(event){
			pageAction(totalPage);
		});
	});
	function pageAction(currentPage){
		$("#currentPage").val(currentPage);
		$("#queryForm").submit();
	}
</script>
</body>
</html>

