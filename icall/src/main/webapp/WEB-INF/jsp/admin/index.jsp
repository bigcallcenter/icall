<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="formTag" tagdir="/WEB-INF/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<c:set var="static1" value="${ctx}/static"/>
<!DOCTYPE html>
<html>
<head>
   <meta charset="utf-8">
   <title>后台管理系统</title>
   <link href="http://libs.baidu.com/bootstrap/3.0.3/css/bootstrap.min.css" rel="stylesheet">
   <link rel="stylesheet" href="${static1 }/dynTabs/css/bootstrap-addtabs.css" type="text/css" media="screen" />
</head>
<body>
<nav class="navbar navbar-default" style="margin-bottom: 0px;" role="navigation">
   <div class="navbar-header">
      <a class="navbar-brand" href="#">bigcallcenter</a>
   </div>
</nav>
<div class="container-fluid" style="min-width:798px;" >
	<div class="row" style="margin-right:0px;">
		<div class="col-xs-1 col-lg-2"   style="padding-right:0px;">
			<div class="panel panel-primary">
			   <div class="panel-heading">
			      <h3 class="panel-title">用户菜单</h3>
			   </div>
			   <div class="panel-body" id="menus">
		      		<div class="btn-group-vertical" role="group" aria-label="...">
	                    <button type="button" class="btn btn-default" data-addtab="goodsManage" url="${ctx }/product/goodsManager.do"><i class="glyphicon glyphicon-envelope"></i>商品管理</button>
	                    <button type="button" class="btn btn-default" data-addtab="profile" url="/admin/profile"><i class="glyphicon glyphicon-user"></i>我的属性2</button>
	                    <button type="button" class="btn btn-default" data-addtab="message" url="/admin/message" content="指定内容3"><i class="glyphicon glyphicon-bullhorn"></i>指定内容</button>
	                    <button type="button" class="btn btn-default" data-addtab="setting" url="/admin/setting" title="指定标题4"><i class="glyphicon glyphicon-cog"></i>指定标题</button>
	                    <button type="button" class="btn btn-default" data-addtab="ajax" url="/admin/profiles" ajax='true'><i class="glyphicon glyphicon-user"></i>使用AJAX5</button>
 						<button type="button" class="btn btn-default" data-addtab="mail1" url="/admin/mail"><i class="glyphicon glyphicon-envelope"></i>我的邮件6</button>
	                    <button type="button" class="btn btn-default" data-addtab="profile1" url="/admin/profile"><i class="glyphicon glyphicon-user"></i>我的属性7</button>
	                    <button type="button" class="btn btn-default" data-addtab="message1" url="/admin/message" content="指定内容"><i class="glyphicon glyphicon-bullhorn"></i>指定内容7</button>
	                    <button type="button" class="btn btn-default" data-addtab="setting1" url="/admin/setting" title="指定标题9"><i class="glyphicon glyphicon-cog"></i>指定标题</button>
	                    <button type="button" class="btn btn-default" data-addtab="ajax1" url="/admin/profiles" ajax='true'><i class="glyphicon glyphicon-user"></i>使用AJAX12</button>
 <button type="button" class="btn btn-default" data-addtab="mail2" url="/admin/mail"><i class="glyphicon glyphicon-envelope"></i>我的邮件12</button>
	                    <button type="button" class="btn btn-default" data-addtab="profile2" url="/admin/profile"><i class="glyphicon glyphicon-user"></i>我的属性14</button>
	                    <button type="button" class="btn btn-default" data-addtab="message2" url="/admin/message" content="指定内容"><i class="glyphicon glyphicon-bullhorn"></i>指定内容16</button>
	                    <button type="button" class="btn btn-default" data-addtab="setting2" url="/admin/setting" title="指定标题"><i class="glyphicon glyphicon-cog"></i>指定标题16</button>
	                    <button type="button" class="btn btn-default" data-addtab="ajax2" url="/admin/profiles" ajax='true'><i class="glyphicon glyphicon-user"></i>使用AJA3dsfzX</button>
						<c:forEach var="menuInfo" items="${menuInfos }">
							${menuInfo.menuName }
						</c:forEach>
	                </div>
			   </div>
			</div>
		</div>
		<div class="col-xs-11 col-lg-10"  style="padding-left:0px;padding-right:0px;">
			<div id="tabs">
                <!-- Nav tabs -->
                <ul class="nav nav-tabs" role="tablist">
                    <li id="tab-home" role="presentation" class="active"><a href="#content-home" aria-controls="home" role="tab" data-toggle="tab">Home</a></li>                    
                </ul>
                <!-- Tab panes -->
                <div class="tab-content">
                    <div role="tabpanel" class="tab-pane active" id="content-home">
                    	管理员你好！
                    </div>                    
                </div>
            </div>
		</div>
	</div>
	
</div>

   <script src="http://libs.baidu.com/jquery/2.0.0/jquery.min.js"></script>
   <script src="http://libs.baidu.com/bootstrap/3.0.3/js/bootstrap.min.js"></script>
   <script src="${static1 }/dynTabs/js/bootstrap-addtabs.js"></script>
    <script type="text/javascript">
            $(function(){
                $('#tabs').addtabs({monitor:'#menus'});
            })
        </script>
</body>
</html>