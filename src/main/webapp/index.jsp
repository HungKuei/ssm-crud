<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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

<!-- Modal修改学生弹窗 -->
	<div class="modal fade" id="stuEditModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
		<div class="modal-dialog" role="document">
		    <div class="modal-content">
			    <div class="modal-header">
			        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
			        <h4 class="modal-title" id="myModalLabel">修改学生</h4>
			    </div>
			    <div class="modal-body">
			    	<form class="form-horizontal">
						<div class="form-group">
						    <label class="col-sm-2 control-label">学生姓名</label>
						    <div class="col-sm-10">
						        <p class="form-control-static" id="edit_name"></p>
						    </div>
						</div>
						<div class="form-group">
						    <label class="col-sm-2 control-label">学生性别</label>
						    <div class="col-sm-10">
						        <label class="radio-inline">
								    <input type="radio" name="gender" id="edit_radio_gender" value="M" checked="checked"> 男
								</label>
								<label class="radio-inline">
								    <input type="radio" name="gender" id="edit_radio_gender" value="W"> 女
								</label>
						    </div>
						</div>
						<div class="form-group">
						    <label class="col-sm-2 control-label">学生邮箱</label>
						    <div class="col-sm-10">
						        <input type="text" class="form-control" name="email" id="edit_email" placeholder="请输入正确的邮箱">
						        <span class="help-block"></span>
						    </div>
						</div>
						<div class="form-group">
						    <label class="col-sm-2 control-label">所在学院</label>
						    <div class="col-sm-5">
						    	<select class="form-control" name="insId"></select>
						    </div>
						</div>
					</form>
			    </div>
			    <div class="modal-footer">
			        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
			        <button type="button" class="btn btn-primary" id="update_stu_btn">修改</button>
			    </div>
		    </div>
		</div>
	</div>

	<!-- Modal添加学生弹窗 -->
	<div class="modal fade" id="stuModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
		<div class="modal-dialog" role="document">
		    <div class="modal-content">
			    <div class="modal-header">
			        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
			        <h4 class="modal-title" id="myModalLabel">添加学生</h4>
			    </div>
			    <div class="modal-body">
			    	<form class="form-horizontal">
						<div class="form-group">
						    <label class="col-sm-2 control-label">学生姓名</label>
						    <div class="col-sm-10">
						        <input type="text" class="form-control" name="stuName" id="input_name" placeholder="请输入姓名">
						        <span class="help-block"></span>
						    </div>
						</div>
						<div class="form-group">
						    <label class="col-sm-2 control-label">学生性别</label>
						    <div class="col-sm-10">
						        <label class="radio-inline">
								    <input type="radio" name="gender" id="radio_gender" value="M" checked="checked"> 男
								</label>
								<label class="radio-inline">
								    <input type="radio" name="gender" id="radio_gender" value="W"> 女
								</label>
						    </div>
						</div>
						<div class="form-group">
						    <label class="col-sm-2 control-label">学生邮箱</label>
						    <div class="col-sm-10">
						        <input type="text" class="form-control" name="email" id="input_email" placeholder="请输入正确的邮箱">
						        <span class="help-block"></span>
						    </div>
						</div>
						<div class="form-group">
						    <label class="col-sm-2 control-label">所在学院</label>
						    <div class="col-sm-5">
						    	<select class="form-control" name="insId"></select>
						    </div>
						</div>
					</form>
			    </div>
			    <div class="modal-footer">
			        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
			        <button type="button" class="btn btn-primary" id="save_stu_btn">保存</button>
			    </div>
		    </div>
		</div>
	</div>
	
	<div class="container">
	    <!-- 页头 -->
	    <div class="row">
			<div class="page-header">
		  		<h1>SSM-CRUD <small>基于整合ssm-crud学生信息的增删改查</small></h1>
			</div>
		</div>
		<!-- 按钮 -->
		<div class="row">
			<div class="col-md-4 col-md-offset-8">
				<button class="btn btn-danger" style="float:right">删除</button>
				<button class="btn btn-primary" id="add_stu_btn" style="float:right;margin-right:5px">新增</button>
				<button class="btn btn-primary" id="query_stu_btn" style="float:right;margin-right:5px">查询</button>
			</div>
		</div>
		<!-- 显示表格数据 -->
		<div class="row" >
			<div class="table-responsive">
				<table class="table table-bordered table-hover" id="stus_table">
					<thead>
						<tr>
							<th>#</th>
							<th>学生姓名</th>
							<th>性别</th>
							<th>邮箱</th>
							<th>所在学院</th>
							<th>操作</th>
						</tr>
					</thead>
					<tbody></tbody>
				</table>
			</div>
		</div>
		<!-- 显示分页信息 -->
		<div class="row">
			<!-- 分页文字信息 -->
			<div class="col-md-6" id="page_info_area">
			</div>
			<div class="col-md-6" id="page_nav_area">
			</div>
		</div>
	</div>
	<script type="text/javascript">
		var totalPage;
	    //页面加载完以后，直接发送ajax请求，拿到分页数据
		$(function(){
			requestPage(1);
		});

		function requestPage(pageNumber){
			$.ajax({
				url:"${basePath }/students",
				data:"pageNumber="+pageNumber,
				type:"GET",
				success: function(result){
					//console.log(result);
					//解析数据和分页信息
					build_stus_table(result);
					build_page_info(result);
					build_page_nav(result);
				}
			});
		}

		function build_stus_table(result){
			$("#stus_table tbody").empty();
			var stus = result.extend.pageInfo.list;
			$.each(stus, function(index, items){
				//alert(items.stuName);
				var stuIdTd = $("<td></td>").append(items.stuId);
				var stuNameTd = $("<td></td>").append(items.stuName);
				var genderTd = $("<td></td>").append(items.gender=='M'?"男":"女");
				var emailTd = $("<td></td>").append(items.email);
				var insNameTd = $("<td></td>").append(items.institute.insName);
				var editBtn = $("<button></button>").addClass("btn btn-primary btn-sm edit_btn")
								.append($("<span></span>").addClass("glyphicon glyphicon-pencil delete_btn")).append("编辑");
				//未编辑按钮添加id属性来表示学生的id
				editBtn.attr("edit-id", items.stuId);
				var delBtn = $("<button></button>").addClass("btn btn-danger btn-sm")
								.append($("<span></span>").addClass("glyphicon glyphicon-trash")).append("删除");
				var btnTd = $("<td></td>").append(editBtn).append(" ").append(delBtn);
				$("<tr></tr>").append(stuIdTd)
					.append(stuNameTd)
					.append(genderTd)
					.append(emailTd)
					.append(insNameTd)
					.append(btnTd)
					.appendTo("#stus_table tbody");
				});
			}

		function build_page_info(result){
			$("#page_info_area").empty();
			$("#page_info_area").append("当前"+ 
				result.extend.pageInfo.pageNum +"页,总"+ 
				result.extend.pageInfo.pages +"页,总 "+ 
				result.extend.pageInfo.total +"条记录");
				totalPage=result.extend.pageInfo.total;
			}

		function build_page_nav(result){
			$("#page_nav_area").empty();
			var ul = $("<ul></ul>").addClass("pagination");
			var firstPageLi = $("<li></li>").append($("<a></a>").append("首页").attr("href","#"));
			var prePageLi = $("<li></li>").append($("<a></a>").append("&laquo;").attr("href","#"));
			if(result.extend.pageInfo.hasPreviousPage == false){
				firstPageLi.addClass("disabled");
				prePageLi.addClass("disabled");
			}else{
				firstPageLi.click(function(){
					requestPage(1);
				});
				prePageLi.click(function(){
					requestPage(result.extend.pageInfo.pageNum-1)	
				});
			}
			
			var nextPageLi = $("<li></li>").append($("<a></a>").append("&raquo;").attr("href","#"));
			var lastPageLi = $("<li></li>").append($("<a></a>").append("尾页").attr("href", "#"));
			if(result.extend.pageInfo.hasNextPage == false){
				nextPageLi.addClass("disabled");
				lastPageLi.addClass("disabled");
			}else{
				nextPageLi.click(function(){
					requestPage(result.extend.pageInfo.pageNum+1);
				});
				lastPageLi.click(function(){
					requestPage(result.extend.pageInfo.pages);	
				});
			}
			
			ul.append(firstPageLi).append(prePageLi),

			$.each(result.extend.pageInfo.navigatepageNums, function(index, items){
				var numLi = $("<li></li>").append($("<a></a>").append(items));
				if(result.extend.pageInfo.pageNum == items){
					numLi.addClass("active");
					}
				numLi.click(function(){
					requestPage(items);
					});
				ul.append(numLi);	
				});
			ul.append(nextPageLi).append(lastPageLi);
			var nav = $("<nav></nav>").css("float","right").append(ul);
			nav.appendTo("#page_nav_area");
			};
		
		$("#query_stu_btn").click(function(){
			requestPage(1);
			});

		function reset_form(model){
			$(model)[0].reset();
			$(model).find("*").removeClass("has-error has-success");
			$(model).find(".help-block").text("");
			};
		
		//点击弹出模态框
		$("#add_stu_btn").click(function(){
			//清除表单数据
			reset_form("#stuModal form");
			getIns("#stuModal select");
			$("#stuModal").modal({
				backdrop:"static"
				});
			});

		//查询学院信息
		function getIns(model){
			$(model).empty();
			$.ajax({
				url:"${basePath}/ins",
				type:"GET",
				success:function(result){
					//console.log(result);
					$.each(result.extend.Ins, function(index, items){
						var option = $("<option></option>").append(items.insName).attr("value", items.insId);
						option.appendTo(model);
						});
				}
			});
		}

		//检验用户名是否可用
		$("#input_name").change(function(){
			//发送ajax请求校验用户名是否可用
			var stuName = this.value;
			$.ajax({
				url:"${basePath}/checkuser",
				data:"stuName="+stuName,
				type:"POST",
				success:function(result){
					if(result.code == 100){
						show_validata_msg("#input_name", "success", "用户名可用！");
						$("#save_stu_btn").attr("ajax-va","success");
					}else{
						show_validata_msg("#input_name", "error", result.extend.va_msg);
						$("#save_stu_btn").attr("ajax-va","error");
					}
				}
			});
		});

		//点击保存学生
		$("#save_stu_btn").click(function(){
			//对提交给服务器的数据进行校验
			if(!validata_add_form()){
				return false;
				} 
			//用户名去重
			if($(this).attr("ajax-va")=="error"){
				return false;
				}
			//发送ajax请求保存数据
			$.ajax({
				url:"${basePath}/students",
				type:"POST",
				data:$("#stuModal form").serialize(),
				success:function(result){
					//alert(result.msg);
					if(result.code == 100){
						//保存成功隐藏模态框
						$('#stuModal').modal('hide');
						//保存成功跳转页面最后一页
						requestPage(totalPage);
					}else{
						//console.log(result);
						//显示错误信息
						if(undefined != result.extend.fieldErrors.stuName){
							show_validata_msg("#input_name", "error", result.extend.fieldErrors.stuName);
						}
						if(undefined != result.extend.fieldErrors.email){
							show_validata_msg("#input_email", "error", result.extend.fieldErrors.email);
						}
					}
				}
			});
		});

		//数据校验
		function validata_add_form(){
			var stuName = $("#input_name").val();
			var regName = /(^[a-zA-Z0-9_-]{6,16}$)|(^[\u2E80-\u9FFF]{2,5})/;
			if(!regName.test(stuName)){
				//alert("用户名必须是2-5位中文或者6-16位的数字和英文组合！")
				show_validata_msg("#input_name", "error", "用户名必须是2-5位中文或者6-16位的数字和英文组合！");
				return false;
			}else{
				show_validata_msg("#input_name", "success", "");
			};
			var email = $("#input_email").val();
			var regEmail = /^([a-z0-9_\.-]+)@([\da-z\.-]+)\.([a-z\.]{2,6})$/;
			if(!regEmail.test(email)){
				//alert("邮箱格式不正确！");
				show_validata_msg("#input_email", "error", "邮箱格式不正确！");
				return false;
			}else{
				show_validata_msg("input_email", "success", "");
				}
			return true;
			};
		//显示数据校验信息
		function show_validata_msg(model, status, msg){
			//清除当前元素的校验状态
			$(model).parent().removeClass("has-success has-error");
			$(model).next("span").text("");
			if("success" == status){
				$(model).parent().addClass("has-success");
				$(model).next("span").text(msg);	
			}else if("error" == status){
				$(model).parent().addClass("has-error");
				$(model).next("span").text(msg);
			}
		};

		$(document).on("click", ".edit_btn", function(){
			//alert("edit");
			//显示修改信息模态框
			getIns("#stuEditModal select");
			//查出学生信息并显示
			getStu($(this).attr("edit-id"));
			//把员工的id传递给模态框的更新按钮
			//$("#emp_update_btn").attr("edit-id",$(this).attr("edit-id"));
			$("#stuEditModal").modal({
				backdrop:"static"
			});
		});

		//根据Id查询学生信息的ajax请求
		function getStu(id){
			$.ajax({
				url:"${basePath}/stu/"+id,
				type:"GET",
				success:function(result){
					//console.log(result);
					stuData = result.extend.stu;
					$("#edit_name").text(stuData.stuName);
					$("#stuEditModal input[name=gender]").val([stuData.gender]);
					$("#edit_email").val(stuData.email);
					$("#stuEditModal select").val([stuData.insId]);
				}
			});
		}
	</script>
</body>
</html>