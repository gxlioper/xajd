<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		
	</head>

	<body>
		<select style="width: 260px;" id="cyyy">
			<option value=""></option>
		</select>
		<a class="name" onclick="szWfxcyyy();">设置未返校常用原因</a>
	</body>
	
	<script type="text/javascript">
		function szWfxcyyy(){			
			showDialog("设置未返校常用原因",550,480,"jlkjxy_jqfxwh.do?method=szCyyy");
		}
		
		function setCyyy(){			
			jQuery.post("jlkjxy_jqfxwh.do?method=getCyyyList",{},function(data){				
				jQuery("#cyyy option:not(:first)").remove();				
				jQuery.each(data,function(i,n){					
					var item = n["cyyy"].length > 18 ? n["cyyy"].substring(0,18)+"..." : n["cyyy"];					
					jQuery("#cyyy").append("<option value='"+n["cyyy"]+"'>"+item+"</option>");
				});
			},"json");
		}
		
		jQuery(function(){			
			setCyyy();			
			var id = "<%=request.getParameter("id") %>";		
			jQuery("#cyyy").bind("change",function(){
				jQuery("#"+id).val(this.value);
			});
			
		});
	</script>
</html>
