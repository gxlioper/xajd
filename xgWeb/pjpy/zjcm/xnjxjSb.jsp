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
		<title><bean:message key="lable.title" /></title>
		<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
		<meta http-equiv="Content-Language" content="GBK" />
		<meta name="Copyright" content="正方软件 zfsoft" />
	</head>
	<%
response.setHeader("Pragma","No-cache");
response.setHeader("Cache-Control","no-cache");
response.setDateHeader("Expires", 0);
%>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<script type="text/javascript">
	function saveSq(){
		var jxjdm = $("jxjdm").value;
		if(jxjdm == ""){
			alert("奖学金代码不能为空，请确认！！");
			return false;
		}
		showTips('处理数据中，请等待......');
		$("buttonSave").disabled=true;
		$("buttonPrint").disabled=true;
		$("buttonClose").disabled=true;
		refreshForm('/xgxt/zjcm_xnjxj.do?method=xnJxjSb&doType=save');
	}
	
	function jxjPrint(){
	    var url = "/xgxt/zjcm_xnjxj.do?method=jxjDjb";
	    var xh = $("xh").value;
	    var jxjdm = $("jxjdm").value;
	    var selectId = $("jxjdm").selectedIndex;
	    var jxjmc = $("jxjdm").options[selectId].text;
	    if(xh == ""){
	    	alert("请确定登记者学号");
	    	return false;
	    }else{
	    	url+="&xh="+xh;
	    }
	    if(jxjdm == ""){
	   		alert("请确定奖学金名称");
	    	return false;
	    }else{
	   		url+="&jxjmc="+jxjmc;
	    }
        document.forms[0].action = url;
	    document.forms[0].target = "_blank";
	    document.forms[0].submit();
	    document.forms[0].target = "_self";
	}
	</script>
	<body>
		<script language="javascript" src="js/function.js"></script>
		<html:form action="/zjcm_xnjxj" method="post">
			<input type="hidden" name="pm" id="pm" value="${pm}"/>
			<input type="hidden" id="msg" name="msg" value="${msg}"/>
			<div class="title">
				<div class="title_img">
					当前所在位置：<bean:write name="title" />
				</div>
			</div>
			<fieldset>
				<legend>
					
				</legend>
				<table width="100%" class="tbstyle" align="center">
					<thead>
						<tr align="center">
							<td colspan="4">
								校内奖学金上报
							</td>
						</tr>
					</thead>
					<tr>
						<td align="right" width="20%">
							学号：
						</td>
						<td align="left" width="30%">
							<input type="hidden" name="xh" id="xh"value="${xh}"  />
							<bean:write name="rs" property="xh"/>
						</td>
						<td align="right" width="20%">
							评奖学年：
						</td>
						<td align="left" width="30%">
							<input type="hidden" name="xn" id="xn"value="${xn}"  />
							<html:select property="xn" style="" disabled="true">
								<html:options collection="xnList" property="xn" labelProperty="xn" />
							</html:select>
						</td>
					</tr>
					<tr>
						<td align="right" width="20%">
							姓名：
						</td>
						<td align="left" width="30%">
							<bean:write name="rs" property="xm"/>
						</td>
						<td align="right" width="20%">
							评奖学期：
						</td>
						<td align="left" width="30%">
							<input type="hidden" name="xq" id="xq"value="${xq}"  />
							<html:select property="xq" style="" disabled="true">
								<html:option value=""></html:option>
								<html:options collection="xqList" property="xqdm" labelProperty="xqmc" />
							</html:select>
						</td>
					</tr>
					<tr>
						<td align="right" width="20%">
							性别：
						</td>
						<td align="left" width="30%">
							<bean:write name="rs" property="xb"/>
						</td>
						<td align="right" width="20%">
							奖学金：
						</td>
						<td align="left" width="30%">
							<html:select property="jxjdm" style="">
								<html:options collection="jxjList" property="dm" labelProperty="mc" />
							</html:select>
						</td>
					</tr>
					<tr>
						<td align="right" width="20%">
							<bean:message key="lable.xsgzyxpzxy" />名称：
						</td>
						<td align="left" width="30%">
							<bean:write name="rs" property="xymc"/>
						</td>
						<td align="right" width="20%">
							德育分：
						</td>
						<td align="left" width="30%">
							<bean:write name="rs" property="dyf"/>
						</td>
					</tr>
					<tr>
						<td align="right" width="20%">
							专业名称：
						</td>
						<td align="left" width="30%">
							<bean:write name="rs" property="zymc"/>
						</td>
						<td align="right" width="20%">
							智育分：
						</td>
						<td align="left" width="30%">
							<bean:write name="rs" property="zyf"/>
						</td>
					</tr>
					<tr>
						<td align="right" width="20%">
							班级名称：
						</td>
						<td align="left" width="30%">
							<bean:write name="rs" property="bjmc"/>
						</td>
						<td align="right" width="20%">
							体育分：
						</td>
						<td align="left" width="30%">
							<bean:write name="rs" property="tyf"/>
						</td>
					</tr>
					<tr>
						<td align="right" width="20%">
							拖欠学费：
						</td>
						<td align="left" width="30%">
							<html:select property="tqxf">
								<html:option value="no">无</html:option>
								<html:option value="yes">有</html:option>
							</html:select>
						</td>
						<td align="right" width="20%">
							能力分：
						</td>
						<td align="left" width="30%">
							<bean:write name="rs" property="nlf"/>
						</td>
					</tr>
					<tr>
						<td align="right" width="20%">
							不及格科目数：
						</td>
						<td align="left" width="30%">
							<html:text name="rs" property="bjgkms" style="width:10%" 
							onkeyup="value=value.replace(/[^\d]/g,'') " maxlength="1"/>门
						</td>
						<td align="right" width="20%">
							综合分：
						</td>
						<td align="left" width="30%">
							<bean:write name="rs" property="zhf"/>
						</td>
					</tr>
					<tr>
						<td align="right" width="20%">
							英语过级情况：
						</td>
						<td align="left" width="30%">
							<html:select property="yygjqk">
								<html:option value=""></html:option>
								<html:options collection="yyList" property="dm" labelProperty="mc" />
							</html:select>
						</td>
						<td align="right" width="20%">
							班级排名：
						</td>
						<td align="left" width="30%">
							第<bean:write name="rs" property="pm"/>名
						</td>
					</tr>
					<tr>
						<td align="right" width="20%">
							计算机过级情况：
						</td>
						<td align="left" width="30%">
							<html:select property="jsjgjqk">
								<html:option value=""></html:option>
								<html:options collection="jsjList" property="dm" labelProperty="mc" />
							</html:select>
						</td>
						<td align="right" width="20%">
							旷课情况：
						</td>
						<td align="left" width="30%">
							<bean:write name="rs" property="kkqk"/>
						</td>
					</tr>
					<tr>
						<td align="right" width="20%">
							获国家奖学金情况：
						</td>
						<td align="left" colspan="3">
							<html:textarea property="hgjjxjqk" style="width:100%" rows="3" onblur="chLeng(this,250)"/>
						</td>
					</tr>
					<logic:notEmpty name="cfqk">
					<tr>
						<td align="center" colspan="4">
							在校违纪情况：
						</td>
					</tr>
					<logic:iterate name="wjcfList" id="s" indexId="index">
						<tr>
							<td align="left" colspan="4">
								<bean:write name="s" property="xn" />&nbsp;
								<bean:write name="s" property="xqmc" />&nbsp;
								<bean:write name="s" property="cflbmc" />&nbsp;
							</td>
						</tr>
					</logic:iterate>
					</logic:notEmpty>
					<tr bgcolor="EEF4F9" align="center">
						<td colspan="4">
						<button class="button2"
							onclick="saveSq();"
							id="buttonSave"
							style="width:80px">
							保 存
						</button>
						&nbsp;&nbsp;&nbsp;
						<button class="button2"
							onclick="jxjPrint()"
							id="buttonPrint"
							style="width:80px">
							打印
						</button>
						&nbsp;&nbsp;&nbsp;
						<button class="button2"
							onclick="Close();return false;"
							id="buttonClose"
							style="width:80px">
							关闭
						</button>
						</td>
					</tr>
				</table>
			</fieldset>
		<logic:present name="msg">
			<script>
				alert(''+document.getElementById('msg').value);
			</script>
		</logic:present>
		<logic:equal value="true" name="result">
			<script>
				alert("操作成功!");
			</script>
		</logic:equal>
		<logic:equal value="false" name="result">
			<script>
				alert("操作失败");
			</script>
		</logic:equal>		
		</html:form>
	</body>
</html>
