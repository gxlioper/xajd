<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		
		<script type="text/javascript">
			jQuery(function(){
				var gnbz = jQuery("#gnbz").val();
				
				jQuery.ajaxSetup({async:false});
					jQuery.post("xtwh_cxpz.do?method=getCxpzInfo",{gnbz:gnbz,cxpz:"yes"},function(json){
						var colList = json["colList"];
						var tr = jQuery("<tr></tr>");
						
						jQuery.each(colList,function(i,n){
							
							if (!n["hidden"]){
								var td = jQuery("<th></th>");
									td.css("width",n["width"]);
									td.html(n["label"]);
									tr.append(td);
							}
							
						});
						
						var thead = jQuery("<thead></thead>");
						thead.append(tr);
						jQuery("#demoTable").append(thead);
						
					},"json");
					
				jQuery.ajaxSetup({async:true});

			});
		</script>
		
	</head>

	<body>
		<html:form action="/xtwh_cxpz">
			<table id="demoTable" class="dateline" width="100%"></table>
		</html:form>
	</body>
</html>
