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
		<%
			response.setHeader("Pragma", "No-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);
		%>
		<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
		<meta http-equiv="Pragma" http-equiv="no-cache" />
		<meta http-equiv="Cache-Control" http-equiv="no-cache" />
		<meta http-equiv="Expires" http-equiv="0" />
		<meta name="Copyright" content="正方软件 zfsoft" />
	</head>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<script language="javascript">
</script>
	<body onload="viewBdZc('bd');viewBdZc('zc')">
		<script language="javascript" src="js/function.js"></script>
		<script language="javascript">
function dataExport2() {
	document.forms[0].action = "/xgxt/yxglExpData.do";
	document.forms[0].target = "_blank";
	document.forms[0].submit();
	document.forms[0].target = "_self";
}
function bdzc(str){
	var xh = document.getElementById('xh').value;
	if(xh == null || xh == ''){
		alert('学号不能为空！');
		return false;
	}
	document.forms[0].action = "/xgxt/yxgl_bdzc.do?doType="+str;
	document.forms[0].submit();
}
function eidtAndView(obj){
	var array = obj.getElementsByTagName('input');
	var array2 = array[0].value.split('!@!');
	showTopWin('/xgxt/yxgl_bdzcInfo.do?pk='+array2[0]+array2[1]+array2[2]
		+'&xh='+array2[0]+'&xn='+array2[1]+'&xq='+array2[2],600,500);
}
function getMoreInfo(){
	var xh = document.getElementById('xh').value;
	var xn = document.getElementById('xn').value;
	var xq = document.getElementById('xq').value;
	if(xh == null || xh == ''||xn == null || xn == ''||xq == null || xq == ''){
		alert('学号,学年,学期均不能为空！');
		return false;
	}
	showTopWin('/xgxt/yxgl_bdzcInfo.do?pk='+xh+xn+xq+'&xh='+xh+'&xn='+xn+'&xq='+xq,600,500);
}
function viewBdZc(str){
	var obj;
	if(str == 'bd'){
		obj = $('sfbd');
		if(obj.value == '已报到'){
			$('bddm').disabled=true;
			$('bddm').value='';
		}else{
			$('bddm').disabled=false;
		}
	}else{
		obj = $('sfzc');
		if(obj.value == '未注册'){
			$('zcdm').disabled=true;
			$('zcdm').value='';
		}else{
			$('zcdm').disabled=false;
		}
	}
}
	</script>
		<html:form action="yxgl_bdzc.do" method="post">
			<div class="title">
				<div class="title_img" id="title_m">
					<bean:write name="tips" scope="request" />
				</div>
			</div>
			<input type="hidden" id="tableName" name="tableName"
				value="<bean:write name="tableName" scope="request"/>" />
			<fieldset>
				<table width="100%" class="tbstyle">
					<thead>
						<tr>
							<td align="left" colspan="2">
								年级：
								<html:select property="nj" style="width:60px" styleId="nj"
									onchange="refreshForm('/xgxt/yxgl_bdzc.do?doType=xydm')">
									<html:options collection="njList" property="nj"
										labelProperty="nj" />
								</html:select>
								&nbsp;&nbsp;学年
								<html:select property="xn" style="width:100px" styleId="xn"
									onchange="refreshForm('/xgxt/yxgl_bdzc.do?doType=xydm')">
									<html:options collection="xnList" property="xn"
										labelProperty="xn" />
								</html:select>
								&nbsp;&nbsp;学期
								<html:select property="xq" style="width:50px" styleId="xq"
									onchange="refreshForm('/xgxt/yxgl_bdzc.do?doType=xydm')">
									<html:options collection="xqList" property="xqdm"
										labelProperty="xqmc" />
								</html:select>
								&nbsp;&nbsp;学号：
								<input type="text" name="xh" maxlength='20' style="width:180px" 
									onkeypress="if(event.keyCode==13){bdzc('save');}"/>
								&nbsp;
								<font color="red">(录入学号后按回车即可完成注册)</font>
							</td>
						</tr>
						<tr>
							<td align="left">
								<bean:message key="lable.xsgzyxpzxy" />：
								<html:select property="xydm" style="width:160px" styleId="xydm"
									onchange="refreshForm('/xgxt/yxgl_bdzc.do?doType=xydm')">
									<html:option value=""></html:option>
									<html:options collection="xyList" property="xydm"
										labelProperty="xymc" />
								</html:select>
								&nbsp;&nbsp;专业：
								<html:select property="zydm" style="width:180px" styleId="zydm"
									onchange="refreshForm('/xgxt/yxgl_bdzc.do')">
									<html:option value=""></html:option>
									<html:options collection="zyList" property="zydm"
										labelProperty="zymc" />
								</html:select>
								&nbsp;&nbsp;班级：
								<html:select property="bjdm" style="width:200px" styleId="bjdm">
									<html:option value=""></html:option>
									<html:options collection="bjList" property="bjdm"
										labelProperty="bjmc" />
								</html:select>
							</td>
							<td width="10" rowspan="2" align="center" valign="middle">
								<button class="button2" style="height:40px" id="search_go"
									onclick="allNotSelect('/xgxt/yxgl_bdzc.do?doType=query')">
									查询
								</button>
							</td>
						</tr>
						<tr>
							<td align="left">
								是否报到：
								<html:select property="sfbd" style="width:80px" styleId="sfbd" onchange="viewBdZc('bd')">
									<html:option value=""></html:option>
									<html:option value="已报到">已报到</html:option>
									<html:option value="未报到">未报到</html:option>
								</html:select>
								&nbsp;&nbsp;报到情况：
								<html:select property="bddm" style="width:120px" styleId="bddm">
									<html:option value=""></html:option>
									<html:options collection="bdList" property="dm"
										labelProperty="mc" />
								</html:select>
								&nbsp;&nbsp;是否注册：
								<html:select property="sfzc" style="width:80px" styleId="sfzc" onchange="viewBdZc('zc')">
									<html:option value=""></html:option>
									<html:option value="已注册">已注册</html:option>
									<html:option value="未注册">未注册</html:option>
								</html:select>
								&nbsp;&nbsp;注册情况：
								<html:select property="zcdm" style="width:120px" styleId="zcdm">
									<html:option value=""></html:option>
									<html:options collection="zcList" property="dm"
										labelProperty="mc" />
								</html:select>
							</td>
						</tr>
					</thead>
				</table>
			</fieldset>
			<logic:empty name="rs">
				<br />
				<br />
				<p align="center">
					未找到任何记录！
				</p>
			</logic:empty>
			<logic:notEmpty name="rs">
				<fieldset>
					<legend>
						记录数：
						<bean:write name="rsNum" />
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<font color="blue">提示：双击一行可以查看详细信息，并可对部分信息进行维护；单击表头可以排序</font>
					</legend>
					<table width="100%" id="rsTable" class="tbstyle">
						<thead>
							<tr align="center" style="cursor:hand">
								<logic:iterate id="tit" name="topTr" offset="1">
									<td id="<bean:write name="tit" property="en"/>"
										onclick="tableSort(this)" nowrap="nowrap">
										<bean:write name="tit" property="cn" />
									</td>
								</logic:iterate>
							</tr>
						</thead>
						<logic:iterate name="rs" id="s">
							<tr onclick="rowOnClick(this)" style="cursor:hand;"
								ondblclick="eidtAndView(this);">
								<logic:iterate id="v" name="s" offset="1" length="1">
									<td align="center">
										<logic:iterate id="v2" name="s" offset="0" length="1">
											<input type="hidden" value="<bean:write name="v2" />">
										</logic:iterate>								
										<bean:write name="v" />
									</td>
								</logic:iterate>
								<logic:iterate id="v" name="s" offset="2">
									<td align="center">
										<bean:write name="v" />
									</td>
								</logic:iterate>
							</tr>
						</logic:iterate>
					</table>
				</fieldset>
			</logic:notEmpty>
			<div id="toolTipLayer" style="position:absolute; visibility: hidden" /></div>
			<center>
				<div class="buttontool" id="btn"
					style="position: absolute;left:1%;top:100px" width="100%">
					<button class="button2" onclick="bdzc('save');" style="width:80px">
						注册
					</button>
					&nbsp;&nbsp;&nbsp;&nbsp;
					<button class="button2" onclick="bdzc('del');"
						style="width:80px">
						取消注册
					</button>
					&nbsp;&nbsp;&nbsp;&nbsp;
					<button class="button2" onclick="getMoreInfo();"
						style="width:80px">
						详细信息
					</button>
					&nbsp;&nbsp;&nbsp;&nbsp;
					<button class="button2" onclick="dataExport2()" style="width:80px">
						导出数据
					</button>
				</div>
			</center>
			<div id="tmpdiv"></div>
		</html:form>
		<script type="text/javascript" src="js/bottomButton.js"></script>
		<logic:equal value="yes" name="result">
			<script type="text/javascript">
				alert('操作成功！');
				document.getElementById('xh').focus();
			</script>
		</logic:equal>
		<logic:equal value="no" name="result">
			<script type="text/javascript">
				alert('操作失败！');
			</script>
		</logic:equal>
		<logic:equal value="no" name="check">
			<script type="text/javascript">
				alert('该学号不存在！');
				document.getElementById('xh').focus();
			</script>
		</logic:equal>
		
	</body>
</html>
