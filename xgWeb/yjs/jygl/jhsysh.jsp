<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean"
	prefix="bean"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html"
	prefix="html"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-logic"
	prefix="logic"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html locale="true">
<head>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<script language="JavaScript" src="style/dmwh.js"></script>
	<script language="JavaScript" src="style/xjgl.js"></script>
	<script language="JavaScript" src="style/jhgl.js"></script>
	<script language="JavaScript" src="style/szgl.js"></script>
	<script language="javascript" src="style/function.js"></script>
	<script language="javascript">
		function dtan(){
	    	document.getElementById("btn").style.pixelTop = document.body.offsetHeight - 40;
	    	document.getElementById("btn").style.width = "100%";
	    	window.setInterval("initBTNTool('btn')",1);
		}
		function cz(a,b){
    	var dm = document.forms[0].dm.value;
     	var page = document.forms[0].page.value;
    	if(b=="sh"){
			 if(document.forms[0].dm.value==""){
			   alert("\u8bf7\u9009\u62e9\u8981\u4fee\u6539\u7684\u8bb0\u5f55\uff01");
			   return false;
			 }
		     window.showModalDialog(a+"&dm="+dm+"&page="+page,window,"Status:NO;dialogWidth:500px;dialogHeight:350px");
	     }else if(b=="plsh"){
	     	changTab(a+"&page="+page);
	     }else if(b=="ck"){
			 if(document.forms[0].dm.value==""){
			   alert("\u8bf7\u9009\u62e9\u8981\u4fee\u6539\u7684\u8bb0\u5f55\uff01");
			   return false;
			 }
		     window.showModalDialog(a+"&dm="+dm+"&page="+page,window,"Status:NO;dialogWidth:500px;dialogHeight:350px");
	     }else if(b=="del"){
	     	changTab(a+"&page="+page);
	     }
		}
	</script>
</head>

<body>
	<html:form action="jhsysh">
	<input type="hidden" name="dm" value="">
	<input type="hidden" name="page" value="${page}">
	<input type="hidden" name="form_action" value="/yjsjwgl/jhsysh.do"/>
		<div id="title">
			<div class="titiel_img"></div>
			当前所在位置：计划生育管理-->计划生育审核
		</div>
		<div>
			<fieldset>
				<legend>
					<font color="blue">双击行可查看详细信息</font>
				</legend>
				<table width="99%">
					<thead>${toolMenu }</thead>
					<thead>
						<tr>
							<td width="3%"  align="center">
						   		<input type="checkbox" name="qbxz" value="all" onclick="cjdyxz('checkvalue')">
						    </td>
							<td align="center" width="5%">
								学号
							</td>
							<td align="center" width="7%">
								姓名
							</td>
							<td align="center" width="5%">
								年级
							</td>
							<td align="center" width="5%">
								婚姻状况
							</td>
							<td align="center" width="5%">
								申请时间
							</td>
							<td align="center" width="5%">
								审核状态
							</td>
						</tr>
					</thead>
					<logic:present name="jhsyList">
					<logic:iterate id="model" name="jhsyList" scope="request">
						<tr style="cursor: hand" onclick="cols(this)"
								id="<bean:write name='model' property='xh'/>"
								ondblclick="cz('/yjsjwgl/jhsysh.do?act=ck','ck')">
							<td align="center"><input type="checkbox" name="checkvalue" value="<bean:write name='model' property='xh'/>"><br></td>
							<td align="center">
								<bean:write name="model" property="xh" />
							</td>
							<td align="center">
								<bean:write name="model" property="xm" />
							</td>
							<td align="center">
								<bean:write name="model" property="nj" />
							</td>
							<td align="center">
								<bean:write name="model" property="hyzk" />
							</td>
							<td align="center">
								<bean:write name="model" property="sqsj" />
							</td>
							<logic:equal value="bm" name="page">
							<td align="center">
								<bean:write name="model" property="bmshzt" />
							</td>
							</logic:equal>
							<logic:equal value="xy" name="page">
							<td align="center">
								<bean:write name="model" property="xyshzt" />
							</td>
							</logic:equal>
							<logic:equal value="ds" name="page">
							<td align="center">
								<bean:write name="model" property="dsshzt" />
							</td>
							</logic:equal>
							<logic:equal value="yws" name="page">
							<td align="center">
								<bean:write name="model" property="ywsshzt" />
							</td>
							</logic:equal>
					</logic:iterate>
					</logic:present>
				</table>
			</fieldset>
		</div>
		<div id="search_r">
			<div id="tmp"></div>
		</div>
		<div align="center"><div> 
			</div><div id="btn" style="position: absolute; left:0px;top:0px"><div> 
						<!--<logic:equal name="canWrite" value="1"></logic:equal>--> 
				<button class="button2" style="width: 70px;" onclick="cz('/yjsjwgl/jhsysh.do?act=plsh','plsh')"> 
					批量审核 
				</button> 
				<button class="button2" style="width: 70px;" onclick="cz('/yjsjwgl/jhsysh.do?act=sh','sh')"> 
					审    核 
				</button> 
				<button class="button2" style="width: 70px;" onclick="cz('/yjsjwgl/jhsysh.do?act=delete','del')"> 
					删除记录 
				</button></div>
			</div>
			<script>
		     dtan();
		    </script>	
		</div>
		<logic:equal name="result" value="plsh">
				<script>
			    alert("<bean:write name='jyglForm' property='message'/>");
			  	</script>
		</logic:equal>
		<logic:equal name="result" value="del">
				<script>
			    alert("<bean:write name='jyglForm' property='message'/>");
			  	</script>
		</logic:equal>
	</html:form>

</body>

</html:html>

