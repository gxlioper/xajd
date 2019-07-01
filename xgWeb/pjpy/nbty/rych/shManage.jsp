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
	<%
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
	%>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css" type="text/css" media="all" />
	<link id="csssDate" rel="stylesheet" rev="stylesheet" href="js/calendar.css" type="text/css" media="all" />

	<script language="javascript" src="js/function.js"></script>
	<script language="javascript" src="js/String.js"></script>
	<script language="javascript" src="js/jsFunction.js"></script>
	<script type="text/javascript" src="js/calendar.js"></script>
	<script type="text/javascript" src="js/calendar-zh.js"></script>
	<script type="text/javascript" src="js/calendar-setup.js"></script>
	<script type='text/javascript' src='/xgxt/dwr/interface/GetListData.js'></script>
	<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
	<script type="text/javascript" src="/xgxt/dwr/interface/getInsureInfo.js"></script>
	<script type='text/javascript' src='dwr/util.js'></script>
	<script language="javascript" src="js/AjaxFunction.js"></script>
	<script language="javascript" src="js/qtsjFunction.js"></script>

	<script language="javascript">
		function modi(url){
			if(curr_row != null){
				showTopWin(url + '&pkValue='+curr_row.getElementsByTagName('input')[0].value,700,500);
				return true;
			}else{
				alert('��ѡ��Ҫ�޸ĵ������У�');
				return false;
			}
		}
	</script>
	<body>
		<html:form action="nbty_rych" method="post">
			<input type="hidden" name="zyV" id="zyV" />
			<input type="hidden" name="bjV" id="bjV" />
			<input type="hidden" name="isFdy" id="isFdy" value="${fdyQx}" />
			<input type="hidden" name="userName" id="userName" value="${userName}" />
			<input type="hidden" name="realTable" id="realTable" value="xsbxb" />
			<input type="hidden" name="save_sqsj" id="save_sqsj" value="${nowTime}"/>
			<div class="title">
				<div class="title_img" id="title_m">
					��ǰ����λ�ã��������� - ��� - �����ƺ����					
				</div>
			</div>			
			<fieldset>
				<legend>
					��ѯ����
				</legend>
				<table width="100%" class="tbstyle">
					<thead>
						<tr>
							<td align="left">
								ѧ�꣺
								<html:select property="queryequals_xn">
									<html:options collection="xnList" property="xn"
										labelProperty="xn" />
								</html:select>
								&nbsp;&nbsp;�꼶��
								<html:select property="queryequals_nj">
									<html:option value=""></html:option>
									<html:options collection="njList" property="nj"
										labelProperty="nj" />
								</html:select>
								&nbsp;&nbsp;ѧ�ţ�
								<html:text property="querylike_xh"></html:text>
								������
								<html:text property="querylike_xm"></html:text>
							</td>
							<td width="10" align="center" valign="middle" rowspan="3">
								<input type="hidden" name="go" value="a" />
								<button type="button" class="button2" id="search_go"
									onclick="allNotEmpThenGo('nbty_rych.do?method=rychSh&doType=go')" style="height: 40px">
									�� ѯ
								</button>
							</td>
						</tr>
							<tr>
						<td>
							<bean:message key="lable.xsgzyxpzxy" />��
							<logic:notEqual name="userType" value="xy">
								<logic:equal name="userOnLine" value="student">
									<html:select  disabled="true" property="queryequals_xydm" style="width:180px"
							  		 styleId="xy" onchange="initZyList();initBjList();">
							   		<html:options collection="xyList" property="xydm"
										labelProperty="xymc" />
									
							   		</html:select>
								</logic:equal>
							    	<html:select property="queryequals_xydm" style="width:180px"
									 onchange="initZyList();initBjList();">
									<html:option value=""></html:option>
									<html:options collection="xyList" property="xydm"
									labelProperty="xymc" />
							  	 	</html:select>
							  	 	<html:hidden property="queryequals_xydm" value="${xydm}"/>
							</logic:notEqual>
							<logic:equal name="userType" value="xy">
							<html:select  disabled="true" property="queryequals_xydm" style="width:180px"
							     onchange="initZyList();initBjList();">
							   
							   <html:options collection="xyList" property="xydm"
									labelProperty="xymc" />
									 </html:select>
									 <html:hidden property="queryequals_xydm" value="${xydm}"/>
							</logic:equal>
							&nbsp;&nbsp;רҵ��
							<logic:equal name="userOnLine" value="student">
								<html:select disabled="true" property="queryequals_zydm" style="width:180px" styleId="zy"
								onchange="initBjList()">
								<html:option value=""></html:option>
								<html:options collection="zyList" property="zydm"
									labelProperty="zymc" />
								</html:select>
							</logic:equal>
							<logic:notEqual name="userOnLine" value="student">
							<html:select property="queryequals_zydm" style="width:180px" styleId="zy"
								onchange="initBjList()">
								<html:option value=""></html:option>
								<html:options collection="zyList" property="zydm"
									labelProperty="zymc" />
							</html:select>
							</logic:notEqual>
							&nbsp;&nbsp;�༶��
							<logic:equal name="userOnLine" value="student">
								<html:select disabled="true" property="queryequals_bjdm" style="width:180px" styleId="bj">
								<html:options collection="bjList" property="bjdm"
									labelProperty="bjmc" />
								</html:select>
							</logic:equal>
							
							<logic:notEqual name="userOnLine" value="student">
							<html:select  property="queryequals_bjdm" style="width:180px" styleId="bj">
									<html:option value=""></html:option>
								<html:options collection="bjList" property="bjdm"
									labelProperty="bjmc" />
							</html:select>
							</logic:notEqual>
							
						</td>
						</tr>
						<tr>
						<td>
							<logic:equal name="isFdy" value="true">
								����Ա��ˣ�
								<html:select property="queryequals_fdysh" style="width:180px"
								styleId="xy" onchange="initZyList();initBjList();">
								<html:option value=""></html:option>
								<html:option value="��ͨ��" >��ͨ��</html:option>
								<html:option value="ͨ��" >ͨ��</html:option>
								<html:option value="δ���" >δ���</html:option>
								</html:select>
								<bean:message key="lable.xsgzyxpzxy" />��ˣ�
								<html:select property="queryequals_xysh" style="width:180px"
								styleId="xy" onchange="initZyList();initBjList();">
								<html:option value=""></html:option>
								<html:option value="��ͨ��" >��ͨ��</html:option>
								<html:option value="ͨ��" >ͨ��</html:option>
								<html:option value="δ���" >δ���</html:option>
								</html:select>
								ѧУ��ˣ�
								<html:select property="queryequals_xxsh" style="width:180px"
								styleId="xy" onchange="initZyList();initBjList();">
								<html:option value=""></html:option>
								<html:option value="��ͨ��" >��ͨ��</html:option>
								<html:option value="ͨ��" >ͨ��</html:option>
								<html:option value="δ���" >δ���</html:option>
								</html:select>
								
							</logic:equal>
							<logic:equal name="userType" value="xy">
								<logic:equal name="isFdy" value="false">
								<bean:message key="lable.xsgzyxpzxy" />��ˣ�
								<html:select property="queryequals_xysh" style="width:180px"
								styleId="xy" onchange="initZyList();initBjList();">
								<html:option value=""></html:option>
								<html:option value="��ͨ��" >��ͨ��</html:option>
								<html:option value="ͨ��" >ͨ��</html:option>
								<html:option value="δ���" >δ���</html:option>
								</html:select>
								ѧУ��ˣ�
								<html:select property="queryequals_xxsh" style="width:180px"
								styleId="xy" onchange="initZyList();initBjList();">
								<html:option value=""></html:option>
								<html:option value="��ͨ��" >��ͨ��</html:option>
								<html:option value="ͨ��" >ͨ��</html:option>
								<html:option value="δ���" >δ���</html:option>
								</html:select>
								</logic:equal>
							</logic:equal>
							<logic:equal name="userType" value="xx">
								ѧУ��ˣ�
								<html:select property="queryequals_xxsh" style="width:180px"
								styleId="xy" onchange="initZyList();initBjList();">
								<html:option value=""></html:option>
								<html:option value="��ͨ��" >��ͨ��</html:option>
								<html:option value="ͨ��" >ͨ��</html:option>
								<html:option value="δ���" >δ���</html:option>
								</html:select>
							</logic:equal><logic:equal name="userType" value="admin">
								ѧУ��ˣ�
								<html:select property="queryequals_xxsh" style="width:180px"
								styleId="xy" onchange="initZyList();initBjList();">
								<html:option value=""></html:option>
								<html:option value="��ͨ��" >��ͨ��</html:option>
								<html:option value="ͨ��" >ͨ��</html:option>
								<html:option value="δ���" >δ���</html:option>
								</html:select>
							</logic:equal>
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
						<font color="blue">��ʾ��˫��һ�п��Բ鿴��ϸ��Ϣ��������ͷ��������</font>
					</legend>
					<table width="99%" id="rsTable" class="tbstyle">
						<thead>
							<tr align="center" style="cursor:hand">
								<td>
									<input type="checkbox"  name="all" value="all" onclick="chec()">
									
								</td>
								<logic:iterate id="tit" name="topTr" offset="1" indexId="index">
									<logic:notEqual name="index" value="1">
										<td id="<bean:write name="tit" property="en"/>"
										onclick="tableSort(this)" nowrap>
										<bean:write name="tit" property="cn" />
									</td>
									</logic:notEqual>
								</logic:iterate>
							</tr>
						</thead>
						<logic:iterate name="rs" id="s">
							<tr onclick="rowMoreClick(this,'',event);"
								ondblclick="modi('nbty_rych.do?method=shOne&doType=view')" style="cursor:hand">
								<td>
									<logic:iterate id="v" name="s" offset="0" length="1">
										<input type="checkbox" name="primarykey_cbv" id="pkV" 
											   value="<logic:iterate id="v" name="s" offset="0" length="1">${v}</logic:iterate>" 
											   <logic:iterate id="v" name="s" offset="1" length="1">${v }</logic:iterate>>
									</logic:iterate>
								</td>
								<td>
									<logic:iterate id="v" name="s" offset="2" length="1">
										<input type="hidden" value="<bean:write name="v"/>" />
									</logic:iterate>
									<logic:iterate id="v" name="s" offset="2" length="1">
										<bean:write name="v" />
									</logic:iterate>
								</td>
								<logic:iterate id="v" name="s" offset="3">
									<td nowrap>
										<bean:write name="v" />
									</td>
								</logic:iterate>
							</tr>
						</logic:iterate>
					</table>
				</fieldset>
				<TABLE width="99%" id="Table" class="tbstyle">
					<TR>
						<TD height=3></TD>
					</TR>
					<TR>
						<TD>
							<jsp:include flush="true"
								page="/sjcz/turnpage.jsp?form=nbtyPjpyForm"></jsp:include>
						</TD>
					</TR>
					<TR>
						<TD height=3></TD>
					</TR>
				</TABLE>
			</logic:notEmpty>
			<logic:equal name="writeAbled" value="yes">
			<div class="buttontool" id="btn" style="position: absolute;left:1%;top:100px" width="100%">	
					<button type="button" class="button2" onclick="dataBatch('nbty_rych.do?method=rychSh&doType=save&shjg=ͨ��')"
						style="width:80px">
						���ͨ��
					</button>
					&nbsp;&nbsp;&nbsp;&nbsp;
					<button type="button" class="button2" onclick="dataBatch('nbty_rych.do?method=rychSh&doType=save&shjg=��ͨ��')"
						style="width:80px">
						��˲�ͨ��
					</button>
					</div>
			</logic:equal>	
				<script language="javascript">
						document.getElementById("btn").style.pixelTop = document.body.clientHeight - 35;
						document.getElementById("btn").style.width = "96%";
						window.setInterval("initBTNTool('btn')",1);
				</script>
				<logic:present name="result">
				<logic:equal value="true" name="result">
					<script>
						alert('�����ɹ���');
					</script>			
				</logic:equal>
				<logic:notEqual value="true" name="result">
					<script>
						alert('����ʧ�ܣ�');
					</script>
				</logic:notEqual>
				</logic:present>
		</html:form>
	</body>
</html>
