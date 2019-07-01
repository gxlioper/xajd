<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/comm/treeFrame.js"></script>
		<script type="text/javascript" src="xsgzgl/xpjpy/zhcp/zcxm/js/zcxm.js"></script>
		<style type="text/css">
		  body{
		  	overflow-x: auto !important;
		  }
			.OrgBox{
				font-size:12px;
				padding:5px 5px 5px 5px;
				clear:left;
				float:left;
				text-align:center;
				position:absolute;
				background:#fff url(/xgxt/pictures/pjpy/zhcp/organization_bg.gif) repeat-x left top;
				width:85px;
				height:128px;
				border:#adc8dc 1px solid;
				border-width:1px 2px 2px 1px;
			}
			.OrgBox img{
				width:60px;
				height:70px;
			}
			.OrgBox div{
				padding:5px 0;
				color:#08487e;
				font-weight:bold;
			}
			.OrgBox div span{
				color:#08487e;
				font-weight:bold;
				}
			#OrgBox1{width:100%;height:20px;padding:0px}
			#OrgBox1 a{ width:13px;height:13px;display:block;float:right;margin-right:3px;}
			#OrgBox1 a.edit{background:url("<%=stylePath %>images/ico_tablehead.png") no-repeat}
			#OrgBox1 a.add{background:url("<%=stylePath %>images/ico_07.gif") no-repeat}
			#OrgBox1 a.del{background:url("<%=stylePath %>images/ico_shwtg01.gif") no-repeat}
		</style>
		<script type="text/javascript">
			jQuery(function(){
				initZcxm();
			});
		</script>
	</head>
	<body>
	
	</body>
</html>

