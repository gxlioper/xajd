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
		<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
		<meta http-equiv="Content-Language" content="GBK" />
		<meta name="Copyright" content="正方软件 zfsoft" />
	</head>
	<%
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
	%>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<base target="_self">
	<script language="javascript" src="js/function.js"></script>
	<script type='text/javascript' src='/xgxt/dwr/interface/GetListData.js'></script>
	<script type="text/javascript" src="/xgxt/dwr/engine.js"></script>
	<script type="text/javascript" src="/xgxt/dwr/util.js"></script>
	<script type="text/javascript" src="js/AjaxFunction.js"></script>
	<script>
		function checkBj(){
			var bjValue = document.getElementById("bj").value;
			if(bjValue==""){
				alert("请选择班级");
				return false;
			}
			return true;
		}
	</script>
	<body>
		<html:form action="/insureChargeList" method="post">
			<input type="hidden" name="dm" value="" />
			<input type="hidden" id="zyV" name="zyV" value="" />
			<input type="hidden" id="xyV" name="xyV" value="" />
			<input type="hidden" id="bjV" name="bjV" value="" />
			<div class="title">
				<div class="title_img" id="title_m">
					当前所在位置：保险清单 - 投保交费清单
				</div>
			</div>
			<fieldset>
				<legend>
					选择班级
				</legend>
				<table class="tbstyle">
					<thead>
						<tr>
							<td>
								年级：
								<html:select property="nj" style="width:90px"
									onchange="initZyList();initBjList();" styleId="nj">
									<html:option value=""></html:option>
									<html:options collection="njList" property="nj"
										labelProperty="nj" />
								</html:select>
								&nbsp;&nbsp;<bean:message key="lable.xsgzyxpzxy" />：
								<html:select property="xydm" style="width:180px"
									onchange="initZyList();initBjList();" styleId="xy">
									<html:option value=""></html:option>
									<html:options collection="xyList" property="xydm"
										labelProperty="xymc" />
								</html:select>
								&nbsp;&nbsp;专业：
								<html:select property="zydm" style="width:180px" styleId="zy"
									onchange="initBjList();">
									<html:option value=""></html:option>
									<html:options collection="zyList" property="zydm"
										labelProperty="zymc" />
								</html:select>
								班级：
								<html:select property="bjdm" style="width:120px" styleId="bj">
									<html:option value=""></html:option>
									<html:options collection="bjList" property="bjdm"
										labelProperty="bjmc" />
								</html:select>
							</td>
						</tr>
					</thead>
				</table>
			</fieldset>
			<div class="buttontool" id="btn"
				style="position: absolute;left:1%;top:100px" width="100%">
				<button class="button2"
					onclick="javascript:var bjdm=document.getElementById('bj').value;if(checkBj()) window.open('/xgxt/insureChargeList.do?method=createFirstPage&&bjdm='+bjdm)">
					交费清单
				</button>
			</div>
			<script language="javascript">
				document.getElementById("btn").style.pixelTop = document.body.clientHeight - 35;
				document.getElementById("btn").style.width = "96%";
				window.setInterval("initBTNTool('btn')",1);
			</script>
		</html:form>
	</body>
</html>
