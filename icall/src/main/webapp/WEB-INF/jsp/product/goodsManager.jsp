<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
   <title>商品管理</title>
   <link href="http://libs.baidu.com/bootstrap/3.0.3/css/bootstrap.min.css" rel="stylesheet">

</head>
<body>

<div class="container" id="businessEname_div">
		<div class="row"> 
				<h5>商圈列表</h5> 
					<table class="table table-bordered table-striped table-hover data-table"> 
						<thead>
							<tr> 
							    <th style="vertical-align:middle;width:10px;"><input type="checkbox" name="chkAll" id="chkAll"></th>  
								<th>logo</th>
								<th>名称</th> 
								<th>英文名</th> 
								<th>注册日期</th>  
							</tr>  
						</thead>
						<tbody id="tby"> 
						  
   						</tbody>
   					</table> 
			      <input type="hidden" id="totalPage_input"/> 
			  <ul class="pagination">
				    <li><a href="javascript:void(0);" id="firstPage">首页</a></li>
				    <li><a href="javascript:void(0);" id="shang">上一页</a></li>
				    <li><a href="javascript:void(0);" id="xia">下一页</a></li>
				    <li><a href="javascript:void(0);" id="lastPage">末页</a></li>  
				    <li>共<lable id="totalPage"></lable>页</li>  
				    <li>第<lable id="currentPage"></lable>页</li>  
				    <li>共<lable id="totalRows"></lable>条记录</li>   
			</ul>
    </div>
  </div>	 
      <script src="http://libs.baidu.com/jquery/2.0.0/jquery.min.js"></script>
   <script src="http://libs.baidu.com/bootstrap/3.0.3/js/bootstrap.min.js"></script> 
  <script type="text/javascript">
  $(document).ready(function() { 
	  
      var currentPage=1; //第几页 
      //分页查询  
	  var  queryByPage=function(){
		  $("#tby tr").remove();      
		  $.ajax({      
	    		type: "post",        
	    		url: "${ctx}/product/queryGoods.do?currentPage="+currentPage,        
	    		dataType: "json",   /*这句可用可不用，没有影响*/  
	    		contentType: "application/json; charset=utf-8",      
	    		success: function (data) {
	    		if(data.msg=='error')
	    		{
				alert(0);;	    		
	    		return;
	    		}
	    			var array=data.businessList;       
	    			var tby=$("#tby");  
	    			var totalPage=data.totalPage;   
	    			$("#totalPage_input").val(totalPage);   
	    			$("#currentPage").html(currentPage);  
	    			$("#totalRows").html(data.totalRows);  
	    			$("#totalPage").html(totalPage);
	    			//循环json中的数据 
	    			for(var i=0,len=array.length;i<len;i++){   
	    				var td1=$("<td style='vertical-align:middle;width:10px;'><input type='checkbox' name='chk'></td>");  
	    				var td2 =$("<td width='140px'><img  src='http://blog.csdn.net/huahuagongzi9999/article/details/"+array[i].goodsName+"' style='width:135px;height:125px;background-color: none;border: none;'></td>");
	    				var td3 =$("<td>"+array[i].goodsName+"</td>");  
	    				var td4 =$("<td>"+array[i].goodsName+"</td>");   
	    				var td5 =$("<td>"+array[i].goodsName+"</td>");   
	    				var tr=$("<tr></tr>"); 
	    				tr.append(td1).append(td2).append(td3).append(td4).append(td5);
	    				tr.appendTo(tby);    
	    			}  
	    		},      
	    		error: function (XMLHttpRequest, textStatus, errorThrown) {     
	    		alert(errorThrown);     
	    		}     
		 });    
	  }  
      //初始化列表 
      queryByPage();
      
      //首页
      $("#firstPage").bind("click",function(){
    	  currentPage=1;
    	  queryByPage(); 
      });   
      
      //上一页  
      $("#shang").click(function(){
        if(currentPage==1){
          alert("已经到达第一页");
          return ;
        }else{
          currentPage--; 
          queryByPage(); 
        }
      });
      
      //下一页  
      $("#xia").click(function(){ 
        if(currentPage==$("#totalPage_input").val()){
          alert("已经到达最后一页");
          return ;
        }else{ 
          currentPage++;
          queryByPage(); 
        }
      });
      
      //末页
      $("#lastPage").bind("click",function(){ 
    	  currentPage=$("#totalPage_input").val(); 
    	  queryByPage();  
      });
      
      //隔行变色 
	 function changeColor(){
		$("#tby>tr:odd").css("background-color","#E9EBEF");
		$("#tby>tr:even").css("background-color","#ffffff");
	 } 
      
  });  
  
  </script> 
</body>
</html>