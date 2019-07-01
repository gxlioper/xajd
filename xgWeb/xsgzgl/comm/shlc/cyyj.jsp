<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		
	</head>

	<body>
		<select style="width: 260px;" id="cyyj">
			<option value=""></option>
		</select>
		<a class="name" onclick="szShyj();">设置常用意见</a>
	</body>
	
	<script type="text/javascript">
		function szShyj(){
			var gnid = "<%=request.getParameter("gnid") %>";

			showDialog("设置常用审核意见",550,480,"comm_spl.do?method=szShyj&gnid="+gnid);
		}
		
		function setCyyj(){
			var gnid = "<%=request.getParameter("gnid") %>";
			jQuery.post("comm_spl.do?method=getShyjList",{id:gnid},function(data){
				
				jQuery("#cyyj option:not(:first)").remove();
				
				jQuery.each(data,function(i,n){
					
					var item = n["shyj"].length > 18 ? n["shyj"].substring(0,18)+"..." : n["shyj"];
					
					jQuery("#cyyj").append("<option value='"+n["shyj"]+"'>"+item+"</option>");
				});
			},"json");
		}
		
		jQuery(function(){
			
			setCyyj();
			
			var id = "<%=request.getParameter("id") %>";
			
			jQuery("#cyyj").bind("change",function(){
				jQuery("#"+id).val(this.value);
			});
			
		});
	</script>
</html>
