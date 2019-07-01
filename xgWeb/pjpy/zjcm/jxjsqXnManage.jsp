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
		<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
		<meta http-equiv="Pragma" http-equiv="no-cache" />
		<meta http-equiv="Cache-Control" http-equiv="no-cache" />
		<meta http-equiv="Expires" http-equiv="0" />
		<meta name="Copyright" content="������� zfsoft" />
	</head>
	<%
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
	%>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<link id="csssDate" rel="stylesheet" rev="stylesheet"
		href="js/calendar.css" type="text/css" media="all" />
	<script language="javascript" src="js/function.js"></script>
	<script type="text/javascript" src="js/calendar.js"></script>
	<script type="text/javascript" src="js/calendar-zh.js"></script>
	<script type="text/javascript" src="js/calendar-setup.js"></script>
	<script type='text/javascript' src='/xgxt/dwr/interface/GetListData.js'></script>
	<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
	<script type='text/javascript' src='dwr/util.js'></script>
	<script language="javascript" src="js/AjaxFunction.js"></script>
	<script language="javascript" src="js/pjpy/pjpyFunction.js"></script>
	<script language="javascript">
	
	//�ж��Ƿ�ı��ѯ��ѧ��
	function checkJxj(){
		var searchJxjdm = $("searchJxjdm").value;
		var jxjdm = $("jxjdm").value;
		if(jxjdm != searchJxjdm){
			alert("��ѧ�����͸ı䣬�����²�ѯ���ٲ���!");
			return false;
		}
		return true;
	}
	
	//չ���걨����ϸ��Ϣ
	function showXsInfo(){
		
		var jxjdm = $("jxjdm").value;
		var sbznum = $("sbznum").value;
		var sel = curr_row.getElementsByTagName('input')[0].checked;
		url = "/xgxt/zjcmPjpy.do?method=jxjsqXnUpdate";
		
		if(jxjdm==""){
			alert("��ȷ����ѧ������");
			return false;
		}
		
		url+="&jxjdm="+jxjdm;
		url+="&sel="+sel;
		url+="&sbznum="+sbznum;
		
		for(var i = 0;i<sbznum;i++){
			if($("sbzxh"+i)){
				url+="&sbzxh"+i+"="+$("sbzxh"+i).value;
			}
		}
		showInfo(url,'view','800','600');
	}
	
	//�걨��ѧ��
	function sbJxj(){
		
		var jxjdm = $("jxjdm").value;
		var jxjmc = "";
		var msg = "";
		var checkBoxArr = document.getElementsByName("checkVal");
		var flag = false;
	
		for(var i=0;i<checkBoxArr.length;i++){
			if(checkBoxArr[i].checked==true){
				flag = true;
			}
		}
	
		if(flag){
			for(var i=0;i<$("jxjdm").options.length;i++){
				if($("jxjdm").options[i].selected){
					jxjmc=$("jxjdm").options[i].text;
				}
			}
		
			msg = "������ѡ��ѧ���걨"+jxjmc+",ȷ����?"
		
			if (confirm(msg)) {
				saveUpdate('/xgxt/zjcmPjpy.do?method=jxjsqXnManage&doType=save','');
			}
		}else{
			alert("�빴ѡҪ�걨��ѧ��");
			return false;
		}	
	}
	
	//��ѯ������������������
	function searchMd(){
		if($('jxjdm').value==''){
			alert('��ȷ��Ҫ�걨�Ľ�ѧ��');
			return false;
		}									
		showTips('��������������������ȴ�......');
		allNotEmpThenGo('/xgxt/zjcmPjpy.do?method=jxjsqXnManage');
	}
	</script>
	<body onload="xyDisabled('xy');">
		<html:form action="/zjcmPjpy" method="post">
			<input type="hidden" id="realTable" name="realTable" value="${realTable }" />
			<input type="hidden" id="tableName" name="tableName" value="${tableName }" />
			<input type="hidden" id="title" name="title" value="${title }" />
			<input type="hidden" id="writeAble" name="writeAble" value="${writeAble }" />
			<input type="hidden" name="xyV" id="xyV" value="" />
			<input type="hidden" name="zyV" id="zyV" value="" />
			<input type="hidden" name="bjV" id="bjV" value="" />
			<input type="hidden" name="njV" id="njV" value="" />
			<input type="hidden" id="message" name="message" value="${message}"/>
			<input type="hidden" id="searchJxjdm" name="searchJxjdm" value="${searchJxjdm}"/>
			<input type="hidden" id="sbznum" name=sbznum value="${sbznum}"/>
			<input type="hidden" id="isFdy" name="isFdy" value="<bean:write name="isFdy" scope="session"/>"/>
			<input type="hidden" id="userName" name="userName" value="<bean:write name="userName" scope="session"/>"/>
			<input type="hidden" id="isBzr" name="isBzr" value="<bean:write name="bzrQx" scope="session"/>" />
			<logic:present name="sbzxh">
			<logic:iterate name="sbzxh" id="num" indexId="index">
				<input type="hidden" id="sbzxh${index }" name="sbzxh" value="${num}"/>
			</logic:iterate>
			</logic:present>
			<div class="title">
				<div class="title_img" id="title_m">
					��ǰ����λ�ã�
					<bean:write name="title" />
				</div>
			</div>
			<logic:notEmpty name="msg">
				<div align="center">
					<FONT color="red" size="6"><bean:write name="msg" /></FONT>
				</div>
			</logic:notEmpty>
			<logic:empty name="msg">
				<div class="rightcontent">
					<fieldset>
						<legend>
							�� ѯ
						</legend>
						<table width="100%" class="tbstyle">
							<thead>
								<tr>
									<td align="left">
										�꼶��
										<html:hidden property="nj"/>
										<html:select property="nj" style="" disabled="true">
											<html:option value=""></html:option>
											<html:options collection="njList" property="nj" labelProperty="nj" />
										</html:select>
										&nbsp;&nbsp;ѧ�꣺
										<html:hidden property="xn"/>
										<html:select property="xn" style="" onchange="" disabled="true">
											<html:options collection="xnList" property="xn" labelProperty="xn" />
										</html:select>
										&nbsp;&nbsp;ѧ�ڣ� 
										<html:hidden property="xq"/>
										<html:select property="xq" style="" onchange="" disabled="true">
											<html:option value=""></html:option>
											<html:options collection="xqList" property="xqdm" labelProperty="xqmc" />
										</html:select>
									</td>
									<td width="10" rowspan="3" align="center" valign="middle">
										<input type="hidden" name="go" value="a" />
										<button class="button2" style="height:40px" id="search_go"
											onclick="searchMd()">
											��ѯ
										</button>
									</td>
								</tr>
								<tr>
									<td align="left" nowrap>
										<bean:message key="lable.xsgzyxpzxy" />��
										<html:hidden property="xydm"/>
										<html:select property="xydm" style="" styleId="xy" disabled="true">
											<html:option value=""></html:option>
											<html:options collection="xyList" property="xydm"
												labelProperty="xymc" />
										</html:select>
										&nbsp;&nbsp;רҵ��
										<html:hidden property="zydm"/>
										<html:select property="zydm" style="" styleId="zy" disabled="true">
											<html:option value=""></html:option>
											<html:options collection="zyList" property="zydm"
												labelProperty="zymc" />
										</html:select>
										&nbsp;&nbsp;�༶��
										<html:hidden property="bjdm"/>
										<html:select property="bjdm" style="" styleId="bj" disabled="true">
											<html:option value=""></html:option>
											<html:options collection="bjList" property="bjdm" labelProperty="bjmc" />
										</html:select>
									</td>
								</tr>
								<tr>
									<td align="left" nowrap>
										ѧ�ţ�
										<html:text property="xh" style="" maxlength="20"/>
										&nbsp;&nbsp;������
										<html:text property="xm" style="" maxlength="20"/>
										&nbsp;&nbsp;<font color="red">*</font>��ѧ��
										<html:select property="jxjdm" style="" styleId="jxjdm">
											<html:option value=""></html:option>
											<html:options collection="jxjList" property="jxjdm" labelProperty="jxjmc" />
										</html:select>
									</td>
								</tr>
							</thead>
						</table>
					</fieldset>
					<logic:empty name="rs">
						<p align="center">
							δ�ҵ��κμ�¼��
						</p>
					</logic:empty>
					<logic:notEmpty name="rs">
						<fieldset>
							<legend>
								��¼����
								<bean:write name="rsNum" />
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<font color="blue">��ʾ��˫���ɲ鿴��ϸ��Ϣ��������ѡ��.</font>
							</legend>
							<table width="100%" id="rsTable" class="tbstyle">
								<thead>	
									<tr align="center" style="cursor:hand">
										<td>
											<input type="checkbox" id="selall" name="selall" onclick="selAll()">
										</td>
										<logic:iterate id="tit" name="topTr" offset="0">
											<td id="<bean:write name="tit" property="en"/>"
												onclick="tableSort(this)" nowrap>
												<bean:write name="tit" property="cn" />
											</td>
										</logic:iterate>
									</tr>
								</thead>
								<logic:iterate name="rs" id="s" indexId="index">
									<tr onclick="rowOnClick(this);" style="cursor:hand"
										ondblclick="if(checkJxj()){showXsInfo()}">
										<td align="center">
											<input type="checkbox" id="checkVal${index }" name="checkVal" 
											value="<logic:iterate id="v" name="s" offset="0" length="1"><bean:write name="v"/></logic:iterate>">
										</td>
										<logic:iterate id="v" name="s" offset="1">
											<td align="left">
												<bean:write name="v" />
											</td>
										</logic:iterate>
									</tr>
								</logic:iterate>
							</table>
						</fieldset>
					</logic:notEmpty>
					<div id="tmpdiv1"></div>
					<logic:equal value="yes" name="writeAble">
					<div id="toolTipLayer"
						style="position:absolute; visibility: hidden" /></div>
					<center>
					<div class="buttontool" id="btn"
						style="position: absolute;left:1%;top:100px" width="100%">
						<logic:equal name="writeAble" value="yes">
							<button class="button2"
								onclick="if(checkJxj()){sbJxj()}"
								style="width:80px">
								��	��
							</button>
						</logic:equal>
					</div>
					</center>
					</logic:equal>
				</div>
			</logic:empty>
		</html:form>
		<logic:equal value="true" name="result">
			<script>
				alert("�����ɹ�!");
			</script>
		</logic:equal>
		<logic:equal value="false" name="result">
			<script>
				alert("����ʧ��");
			</script>
		</logic:equal>
		<logic:present name="message">
			<script>
				alert(''+document.getElementById('message').value);
			</script>
		</logic:present>
		<script type="text/javascript" src="js/bottomButton.js"></script>
	</body>
</html>
