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
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<base target="_self">

	<script type="text/javascript" src="/xgxt/dwr/engine.js"></script>
	<script type="text/javascript" src="/xgxt/dwr/util.js"></script>
	<script type="text/javascript" src="js/stuinfoFunction.js"></script>
	<script language="javascript" src="js/function.js"></script>
	<script language="javascript">
	function addInfo(){
		if(curr_row == null){
			alert("�˲�����Ҫ��ѡ�е��У�����Ҫ��ӱ�ע���У�");
			return false;
		}
		showTopWin("addStuInfo.do?xh=" + curr_row.cells[0].innerText,400,300,false);
	}
</script>
	<body>
		<%
			response.setHeader("Pragma", "No-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);
		%>
		<center>

			<html:form action="/stuSchoolingInfo" method="post">
				<div class="title">
					<div class="title_img" id="title_m">
						��ǰλ�ã�ѧ����Ϣ - ѧ����Ϣ - ѧ����Ϣ
					</div>
				</div>
				<fieldset>
					<legend>
						�� ѯ
					</legend>
						<input type="hidden" id="realTable" name="realTable" value="<bean:write name="realTable"/>"/>
						<input type="hidden" id="tableName" name="tableName" value="<bean:write name="tableName"/>"/>
					<table width="100%" class="tbstyle">
						<thead>
							<tr>
								<td align="left">
									�꼶��
									<html:select property="nj" style="width:60px"
										onchange="refreshForm('/xgxt/stuSchoolingInfo.do')">
										<html:option value=""></html:option>
										<html:options collection="njList" property="nj"
											labelProperty="nj" />
									</html:select>
									&nbsp;&nbsp;ѧ�꣺
									<html:select property="xn" style="width:100px" >
										<html:option value=""></html:option>
										<html:options collection="xnList" property="xn"
											labelProperty="xn" />
									</html:select>
									&nbsp;&nbsp;<bean:message key="lable.xsgzyxpzxy" />��
									<html:select property="xydm" style="width:170px"
										onchange="refreshForm('/xgxt/stuSchoolingInfo.do')">
										<html:option value=""></html:option>
										<html:options collection="xyList" property="xydm"
											labelProperty="xymc" />
									</html:select>
									&nbsp;&nbsp;רҵ��
									<html:select property="zydm" style="width:140px" styleId="zy"
										onchange="refreshForm('/xgxt/stuSchoolingInfo.do')">
										<html:option value=""></html:option>
										<html:options collection="zyList" property="zydm"
											labelProperty="zymc" />
									</html:select>
								</td>
								<td width="10" rowspan="2" align="center" valign="middle">
									<input type="hidden" name="go" value="a" />
									<button type="button" class="button2" style="height:40px" id="search_go"
										onclick="allNotEmpThenGo('/xgxt/stuSchoolingInfo.do');">
										��ѯ
									</button>
								</td>
							</tr>
							<tr>
								<td align="left" nowrap>
									�༶��
									<html:select property="bjdm" style="width:120px" styleId="bj">
										<html:option value=""></html:option>
										<html:options collection="bjList" property="bjdm"
											labelProperty="bjmc" />
									</html:select>
									&nbsp;&nbsp;ѧ�ţ�
									<html:text property="xh" />
									&nbsp;&nbsp;������
									<html:text property="xm" />
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
									<logic:iterate id="tit" name="topTr" offset="1">
										<td id="<bean:write name="tit" property="en"/>"
											onclick="tableSort(this)" nowrap>
											<bean:write name="tit" property="cn" />
										</td>
									</logic:iterate>
								</tr>
							</thead>
							<logic:iterate name="rs" id="s">
								<tr onclick="rowOnClick(this)" style="cursor:hand"
									ondblclick="ljsdaUpdate('stu_schoolinginfo_one.do?doType=modi&pkValue=',500,400)">
									<td>
										<logic:iterate id="v" name="s" offset="1" length="1">
											<input type="hidden" value="<bean:write name="v" />" />
										</logic:iterate>
										<logic:iterate id="v" name="s" offset="2" length="1">
											<bean:write name="v" />
										</logic:iterate>
									</td>
									<logic:iterate id="v" name="s" offset="3">
										<td align="left">
											<bean:write name="v" />
										</td>
									</logic:iterate>
								</tr>
							</logic:iterate>
						</table>
			  			<TABLE width="99%" id="Table" class="tbstyle">
								<TR>
									<TD height=3></TD>
								</TR>
								<TR>
									<TD>
										<jsp:include flush="true"
											page="/sjcz/turnpage.jsp?form=commanForm"></jsp:include>
									</TD>
								</TR>
								<TR>
									<TD height=3></TD>
								</TR>
						</TABLE>
					</fieldset>
				</logic:notEmpty>
				<logic:notEmpty name="userOper">
					<logic:equal value="yes" name="userOper">
						<logic:equal value="yes" name="writeAble">
						<div class="buttontool" id="btn"
							style="position: absolute;left:1%;top:100px" width="100%">
							<button type="button" class="button2"
								onclick="showTopWin('stu_schoolinginfo_one.do?doType=add',500,400,false)"
								style="width:80px">
								�� ��
							</button>
							&nbsp;&nbsp;&nbsp;&nbsp;
							<button type="button" class="button2"
								onclick="ljsdaUpdate('stu_schoolinginfo_one.do?doType=modi&pkValue=',500,400)"
								style="width:80px">
								�� ��
							</button>
							&nbsp;&nbsp;&nbsp;&nbsp;
							<button type="button" class="button2"
								onclick="stuinfoDel('stu_schoolinginfo_one.do?doType=del&pkValue=')"
								style="width:80px">
								ɾ ��
							</button>
							&nbsp;&nbsp;&nbsp;&nbsp;
							<button type="button" class="button2"
								onclick="impAndChkData();"
								style="width:80px">
								��������
							</button>
							&nbsp;&nbsp;&nbsp;&nbsp;
							<button type="button" class="button2"
								onclick="dataExport()"
								style="width:80px">
								��������
							</button>
							&nbsp;&nbsp;&nbsp;&nbsp;
							<button type="button" class="button2" onclick=" expTab('rsTable','ѧ��ѧ����Ϣ','')">
								��ӡ�б�
							</button>
						</div>
						</logic:equal>
					</logic:equal>
				</logic:notEmpty>
			</html:form>
			<logic:equal value="ok" name="result">
			<script>
				alert("�����ɹ�!");
			</script>
		</logic:equal>
		<logic:equal value="no" name="result">
			<script>
				alert("����ʧ��!");
			</script>
		</logic:equal>
		</center>
		<script type="text/javascript" src="js/bottomButton.js"></script>
	</body>
</html>
