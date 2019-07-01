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
		<meta http-equiv="Cache-Control"
			http-equiv="no-cache, must-revalidate" />
		<meta http-equiv="Expires" http-equiv="0" />

		<meta name="Copyright" content="正方软件 zfsoft" />
	</head>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css" type="text/css" media="all" />
	<base target="_self">
	<link id="csssDate" rel="stylesheet" rev="stylesheet" href="js/calendar.css" type="text/css" media="all" />
	<script language="javascript" src="js/function.js"></script>
	<script type="text/javascript" src="/xgxt/dwr/engine.js"></script>
	<script type="text/javascript" src="/xgxt/dwr/util.js"></script>
	<script type="text/javascript" src="/xgxt/dwr/interface/pjpyZjsyzy.js"></script>
	<script language="javascript">
	function commit(obj){
		var values = obj.split('-');
		for(var i=0; i<values.length; i++){
			if(document.getElementById(values[i]).value == ''){
				alert('请将所有的项目填写完整！');
				return false;
			}
		}
		refreshForm('pjpy_zjsyzy.do?method=saveBsxm');
	}
	
	function changeCzlx(){
		var xmdm = document.getElementById('xmdm').value;
		pjpyZjsyzy.changeCzlx(xmdm,function(data){
			document.getElementById("czlx").value=data;
		});
	}
</script>
	<%
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
	%>
	<body onload="changeCzlx()">
		
		<html:form action="/pjpy_zjsyzy.do" method="post">
			<div class="title">
				<div class="title_img" id="title_m">
					当前位置：评奖评优 - 参数设置 - 综测评分项目维护
				</div>
			</div>			
				<fieldset>
					<legend>
						综测评分项目维护
					</legend>
					
					<table width="100%" class="tbstyle">
						<tr>
							<td align="right">
								所属项目名称：
							</td>
							<td>
								<html:select property="ssjfxm" name="rs" styleId="ssjfxm" onchange="refreshForm('pjpy_zjsyzy.do?method=showAddBsxm')">
								<html:option value=""></html:option>
								<html:options collection="ssjfxmList" property="en" labelProperty="cn"/>
								</html:select>
							</td>							
						</tr>
						<tr>
							<td align="right">
								项目名称：
							</td>
							<td>
								<html:select property="xmdm" name="rs" styleId="xmdm" onchange="changeCzlx()">
								<html:options collection="bsxmList" property="xmdm" labelProperty="xmmc"/>
								</html:select>
							</td>							
						</tr>
						<tr>
							<td align="right">
								分值：
							</td>
							<td>
								<html:text property="xmjf" name="rs" styleId="xmjf" maxlength="5" />分
							</td>							
						</tr>	
						<tr>
							<td align="right">
								操作类型：
							</td>
							<td>
								<html:text property="czlx" name="rs" styleId="czlx" disabled="true"/>
							</td>							
						</tr>						
					</table>
				</fieldset>
				<div class="buttontool">
					<button type="button" class="button2"
						onclick="commit('xmdm-xmjf')"
						style="width:80px" id="buttonSave">
						保 存
					</button>
					&nbsp;&nbsp;&nbsp;&nbsp;
					<button type="button" class="button2" onclick="Close();return false;" style="width:80px"
						id="buttonClose">
						关 闭
					</button>
				</div>
				<logic:present name="result">
				<logic:equal value="true" name="result">
				<script>
					alert('操作成功!');
					Close();
					window.dialogArguments.document.getElementById('search_go').click();
				</script>
				</logic:equal>
				<logic:equal value="false" name="result">				
				<script>
					alert('操作失败！');
					Close();
				</script>
				</logic:equal>
				</logic:present> 
		</html:form>
	</body>
</html>
