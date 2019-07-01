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
		<meta name="Copyright" content="������� zfsoft" />
	</head>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<base target="_self">
	<%
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
	%>
	<body onload="xyDisabled('xy');">
		<center>
			<script language="javascript" src="/xgxt/js/function.js"></script>
			<script language="javascript" src="/xgxt/js/jsFunction.js"></script>
			<script type="text/javascript" src="/xgxt/dwr/interface/GetListData.js"></script>
			<script type="text/javascript" src="/xgxt/dwr/engine.js"></script>
			<script type="text/javascript" src="/xgxt/dwr/util.js"></script>
			<script type="text/javascript" src="/xgxt/js/jxglFunction.js"></script>
			<html:form action="/jxgl" method="post">
				<input type="hidden" id="userType" name="userType"  value="<bean:write name="userType"/>" />
				<input type="hidden" id="realTalbe" name="realTable"  value="<bean:write name="realTable"/>" />
				<input type="hidden" id="tableName" name="tableName"  value="<bean:write name="tableName"/>" />
				<input type="hidden" id="xyV" name="xyV"  value="" />
				<input type="hidden" id="zyV" name="zyV"  value="" />
				<input type="hidden" id="bjV" name="bjV"  value="" />
				<input type="hidden" id="delPk" name="delPk"  value="" />				
				
				<div class="title">
					<div class="title_img" id="title_m">
						��ǰ����λ�ã���ѵ���� - ��ѵ���� - ��ѵ�Ŷӻ�
					</div>
				</div>
				<fieldset>
					<legend>
						�� ѯ
					</legend>
					<table width="100%" class="tbstyle">
						<thead>
							<tr>
								<td align="left">
									ѧ�꣺
									<html:select property="xn">
										<html:option value=""></html:option>
										<html:options collection="xnList" labelProperty="xn" property="xn"/>
									</html:select>
									��ȣ�
									<html:select property="nd">
										<html:option value=""></html:option>
										<html:options collection="xnList" labelProperty="nd" property="nd"/>
									</html:select>
									ѧ�ڣ�
									<html:select property="xq">
										<html:option value=""></html:option>
										<html:options collection="xqList" labelProperty="xqmc" property="xqdm"/>
									</html:select>
									���ӣ�
									<html:select property="lddm">
										<html:option value=""></html:option>
										<html:options collection="ldList" labelProperty="bzmc" property="bzdm"/>
									</html:select>
									���
									<html:select property="jxdm">
										<html:option value=""></html:option>
										<html:options collection="jxList" labelProperty="jxmc" property="jxdm"/>
									</html:select>
								</td>
								<td width="10" rowspan="2" align="center" valign="middle">
									<input type="hidden" name="go" value="a" />
									<button type="button" class="button2" style="" id="search_go"
										onclick="document.forms[0].go.value='go';refreshForm('/xgxt/jxgl.do?method=jxtdhjSearch');">
										��ѯ
									</button>
								</td>
							</tr>							
						</thead>
					</table>
				</fieldset>
				<logic:empty name="rs">
					<br />
					<br />
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
							<font color="blue">��ʾ��˫��һ�п���ѡ����������ͷ��������</font>
						</legend>
						<table width="100%" id="rsTable" class="tbstyle">
							<thead>
								<tr align="center" style="cursor:hand">		
									<td>
										<input type="checkbox" name="checkAll" value="all" onclick="check()">
									</td>							
									<logic:iterate id="tit" name="topTr" offset="0">
										<td id="<bean:write name="tit" property="en"/>"
											onclick="tableSort(this)" nowrap>
											<bean:write name="tit" property="cn" />
										</td>
									</logic:iterate>
								</tr>
							</thead>
								<logic:iterate name="rs" id="s">
									<tr onclick="rowOnClick(this)" style="cursor:hand"
										ondblclick="sendJsghInfo()">
										<td>
										
										<logic:iterate id="v" name="s" offset="0" length="1">
											<input type="checkbox" name="checkOne" value="<bean:write name="v"/>">
											<input type="hidden" value="<bean:write name="v" />"/>
										</logic:iterate>										
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
					<br />
					<br />
				</logic:notEmpty>
				<center>
					<div class="buttontool" id="btn"
						style="position: absolute;left:1%;top:100px" width="100%">
						<logic:equal value="yes" name="writeAble">
							<button type="button" class="button2" onclick="showTopWin('jxgl.do?method=showJxtdhjAdd',500,300)"
								style="width:80px">
								�� ��
							</button>
							&nbsp;&nbsp;&nbsp;&nbsp;
							<button type="button" class="button2" onclick="modiInfo('jxgl.do?method=showJxtdhjModi',500,300)"
								style="width:80px">
								�� ��
							</button>
							&nbsp;&nbsp;&nbsp;&nbsp;
							<button type="button" class="button2" onclick="batch('jxgl.do?method=delJxtdhj')"
								style="width:80px">
								ɾ ��
							</button>
							&nbsp;&nbsp;&nbsp;&nbsp;
							<button type="button" class="button2"
								onclick="showTopWin('/xgxt/data_import.do',600,480)"
								style="width:80px">
								��������
							</button>						
							&nbsp;&nbsp;&nbsp;&nbsp;
							<button type="button" class="button2" onclick="dataExport()" style="width:80px">
								��������
							</button>
						</logic:equal>
					</div>
				</center>
				<logic:present name="result">
					<logic:equal value="true" name="result">
					<script>
						alert('�����ɹ�!');
						Close();						
					</script>
					</logic:equal>
					<logic:equal value="false" name="result">
						<logic:notEmpty name="mes">
						<input name="mes" id="mes" value="${msg}"/>
						<script>
							alert(document.getElementById("msg").value);
						</script>
						</logic:notEmpty>
						<logic:empty name="mes">
						<script>
							alert('����ʧ�ܣ�');
						</script>
						</logic:empty>
					</logic:equal>
				</logic:present>
				
			</html:form>
			<div id="tmpdiv"></div>
		</center>
	</body>
	<script language="javascript">
					document.getElementById("btn").style.pixelTop = document.body.clientHeight - 35;
					document.getElementById("btn").style.width = "96%";
					window.setInterval("initBTNTool('btn')",1);
	</script>
</html>
