<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>学生列表</title>
<%
	pageContext.setAttribute("basePath", request.getContextPath());
%>

<!-- Bootstrap  -->
<link href="${basePath }/static/bootstrap-3.3.7-dist/css/bootstrap.min.css" rel="stylesheet">
<!-- 自定义样式 -->
<link href="${basePath }/static/css/index.css" rel="stylesheet">
<!-- jQuery (Bootstrap 的所有 JavaScript 插件都依赖 jQuery，所以必须放在前边) -->
<script src="${basePath }/static/js/jquery-1.12.4.min.js"></script>
<!-- 加载 Bootstrap 的所有 JavaScript 插件。你也可以根据需要只加载单个插件。 -->
<script src="${basePath }/static/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
</head>
<body>
	<div class="container">
	    <!-- 页头 -->
	    <div class="row">
			<div class="page-header">
		  		<h1>SSM-CRUD <small>基于整合ssm-crud学生信息的增删改查</small></h1>
			</div>
		</div>
		<!-- 按钮 -->
		<div class="row">
			<div class="col-md-4 col-md-offset-10">
				<button class="btn btn-primary">查询</button>
				<button class="btn btn-primary">新增</button>
				<button class="btn btn-danger">删除</button>
			</div>
		</div>
		<!-- 显示表格数据 -->
		<div class="row" >
			<div class="table-responsive">
				<table class="table table-bordered table-hover">
					<tr>
						<th>#</th>
						<th>学生姓名</th>
						<th>性别</th>
						<th>邮箱</th>
						<th>所在学院</th>
						<th>操作</th>
					</tr>
					<c:forEach items="${pageInfo.list }" var="stus">
						<tr>
							<th>${stus.stuId }</th>
							<th>${stus.stuName }</th>
							<th>${stus.gender }</th>
							<th>${stus.email }</th>
							<th>${stus.institute.insName }</th>
							<th>
							<button class="btn btn-primary btn-sm">
								<span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>
								编辑
							</button>
							<button class="btn btn-danger btn-sm">
								<span class="glyphicon glyphicon-trash" aria-hidden="true"></span>
								删除
							</button>
						</th>
						</tr>
					</c:forEach>
				</table>
			</div>
		</div>
		<!-- 显示分页信息 -->
		<div class="row">
			<!-- 分页文字信息 -->
			<div class="col-md-6">
				当前 ${pageInfo.pageNum }页,总${pageInfo.pages }
				页,总 ${pageInfo.total } 条记录
			</div>
			<!-- 分页条信息 -->
			<div class="col-md-6 col-md-offset-8">
				<nav aria-label="Page navigation">
				  <ul class="pagination">
				  	<li><a href="${basePath }/list?pageNumber=1">首页</a></li>
				  		<c:if test="${pageInfo.hasPreviousPage }">
					    	<li><a href="${basePath }/list?pageNumber=${pageInfo.pageNum-1}" aria-label="Previous">
					        	<span aria-hidden="true">&laquo;</span>
					      	</a></li>
				      	</c:if>
				      	<c:forEach items="${pageInfo.navigatepageNums }" var="page_Num">
				      		<c:if test="${page_Num == pageInfo.pageNum }">
				   	 			<li class="active"><a href="#">${page_Num }</a></li>
				      		</c:if>
				      		<c:if test="${page_Num != pageInfo.pageNum }">
				   	 			<li><a href="${basePath }/list?pageNumber=${page_Num }">${page_Num }</a></li>
				      		</c:if>
				      	</c:forEach>
				      	<c:if test="${pageInfo.hasNextPage }">
						    <li><a href="${basePath }/list?pageNumber=${pageInfo.pageNum+1 }" aria-label="Next">
						        <span aria-hidden="true">&raquo;</span>
						    </a></li>
				      	</c:if>
				      	<li><a href="${basePath }/list?pageNumber=${pageInfo.pages}">尾页</a></li>
				  </ul>
				</nav>
			</div>
		</div>
	</div>
</body>
</html>