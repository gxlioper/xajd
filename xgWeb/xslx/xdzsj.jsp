<%@ page language="java" contentType="text/html; charset=GBK"%>

<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean"
	prefix="bean"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html"
	prefix="html"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-logic"
	prefix="logic"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-tiles"
	prefix="tiles"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-template"
	prefix="template"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-nested"
	prefix="nested"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml" lang="gb2312">
	<head>
		<title><bean:message key="lable.title" />
		</title>
		<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
		<meta http-equiv="Pragma" http-equiv="no-cache" />
		<meta http-equiv="Cache-Control" http-equiv="no-cache" />
		<meta http-equiv="Expires" http-equiv="0" />
		<meta name="Copyright" content="正方软件 zfsoft" />
	</head>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<base target="_self">
	
	<script language="javascript" src="/xgxt/js/function.js"></script>
	<script language="javascript" src="/xgxt/js/stuinfoFunction.js"></script>
	<script type='text/javascript' src='/xgxt/dwr/interface/GetListData.js'></script>
	<script type="text/javascript" src="/xgxt/dwr/engine.js"></script>
	<script type="text/javascript" src="/xgxt/dwr/util.js"></script>
	<script type="text/javascript" src="js/AjaxFunction.js"></script>
	<script type="text/javascript" src="/xgxt/dwr/interface/getSjInfo.js"></script>
	<script type="text/javascript">
	function expDate_lx(){
		document.forms[0].action = "/xgxt/leaveExpDate.do";
		document.forms[0].target = "_blank";
		document.forms[0].submit();
		document.forms[0].target = "_self";
	}	
	
	function che(){
		var xm = document.getElementById("xm").value;
		var xydm = document.getElementById("xy").value;
		getSjInfo.getSjInfo(xm,xydm,function(data){
			if(data==true){
				alert('<bean:message key="lable.xsgzyxpzxy" />(系)被重复分配！');
				return false;
			}
			if(data==false){
				refreshForm('/xgxt/xdzsj.do?doType=save');				
			}
		});
	}
	</script>
	<body >
		<%
			response.setHeader("Pragma", "No-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);
		%>
		<center>
			<html:form action="/xdzsj.do" method="post">
				<div class="title">
					<div class="title_img" id="title_m">
						当前位置: 学生离校 - 系党支部书记信息					
					</div>
				</div>
				<table class="tbstyle" width="100%">
				<thead>
								<tr align="center" style="cursor:hand">									
									<td colspan="2">
										<center>系党支部书记信息</center>
									</td>	
								</tr>
							</thead>
				<tr>
				<td align="right">
					用户名：
				</td>
				<td>
					<html:text property="dzsxm" name="rs" styleId="xm"/>
				</td>
				</tr>
				<tr>
				<td align="right">
					<bean:message key="lable.xsgzyxpzxy" />(系)：
				</td>
				<td>
					<html:select property="xydm" style="width:180px"
											styleId="xy" name="rs">
											<html:option value=""></html:option>
											<html:options collection="xyList" property="xydm"
												labelProperty="xymc" />
										</html:select>
				</td>
				</tr>
				</table>
				<br/>
				<br/>
				<br/>
				<br/>
				<br/>
				<br/>
				<br/>
				<br/>
				<logic:present name="writeAble">
				<logic:equal value="yes" name="writeAble">				
				<div class="buttontool" id="btn" width="100%" align="buttom">
							<button class="button2"
								onclick="che()"
								style="width:80px">
								保 存
							</button>
							
							&nbsp;&nbsp;&nbsp;&nbsp;
							<button class="button2" onclick="Close();return false;" style="width:80px">
								关 闭
							</button>
				</div>
				</logic:equal>
				</logic:present>
				<logic:present name="result">
				<logic:equal value="true" name="result">
				<script>
					alert("操作成功！");
					Close();
					window.dialogArguments.document.getElementById('search_go').click();
				</script>
				</logic:equal>
				<logic:equal value="false" name="result">
				<script>
					alert("操作失败！");
				</script>
				</logic:equal>
				</logic:present>				
			</html:form>
		</center>
	</body>
</html>
