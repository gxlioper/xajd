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
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />

	<link id="csssDate" rel="stylesheet" rev="stylesheet"
		href="js/calendar.css" type="text/css" media="all" />
	<script language="javascript">	
	
	function updateCssz(type){
		if(type == "add"){
			showTopWin('/xgxt/xmlgwmbj.do?method=csszUpdate&doType='+type,300,350)
		}else if(type == "edit" || type == "view"){
			if(curr_row == null){
				alert('��ѡ��Ҫ�޸ĵ���Ϣ!');
				return false;
			}else{
				var pk = curr_row.cells[0].getElementsByTagName('input')[0].value;
				showTopWin('/xgxt/xmlgwmbj.do?method=csszUpdate&doType='+type+'&pk='+pk,300,350)
			}
		}else if(type == "del"){
			if(curr_row == null){
				alert('��ѡ��Ҫɾ������Ϣ!');
				return false;
			}else{
				var pk = curr_row.cells[0].getElementsByTagName('input')[0].value;
				if (confirm("ȷ��Ҫɾ���ֶ���(�ᵼ�����ݶ�ʧ,������!)\n���\"ȷ��\"��ɾ���ֶΣ�\n���\"ȡ��\"��������ɾ����")) {
					showTips('���������У���ȴ�......');
					refreshForm('/xgxt/xmlgwmbj.do?method=csszManage&doType='+type+'&pk='+pk)
				}
			}
		}
	}
	
	</script>
	<body>
		<script language="javascript" src="js/function.js"></script>
		<script type="text/javascript" src="js/calendar.js"></script>
		<script type="text/javascript" src="js/calendar-zh.js"></script>
		<script type="text/javascript" src="js/calendar-setup.js"></script>
		<script language="javascript" src="js/jsFunction.js"></script>
		<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
		<script type='text/javascript' src='dwr/util.js'></script>
		<script language="javascript" src="js/AjaxFunction.js"></script>
		<html:form action="/xmlgwmbj" method="post">
			<input type="hidden" id="userName" name="userName" value="<bean:write name="userName" scope="session"/>"/>
			<input type="hidden" id="userType" name="userType" value="<bean:write name="userType" scope="request"/>" />
			<input type="hidden" id="tableName" name="tableName" value="<bean:write name="tableName" scope="request"/>" />
			<input type="hidden" id="realTable" name="realTable" value="<bean:write name="realTable" scope="request"/>" />
			<div class="title">
				<div class="title_img" id="title_m">
					��ǰ����λ�ã�<bean:write name="title" />
				</div>
			</div>
			<div class="rightcontent">
				<fieldset>
					<legend>
						�� ѯ
					</legend>
					<table width="100%" class="tbstyle">
						<thead>
							<tr>
								<td align="left">
									&nbsp;&nbsp;ѧ�꣺
									<html:select property="xn" style="">
										<html:options collection="xnList" property="xn"
											labelProperty="xn" />
									</html:select>	
									&nbsp;&nbsp;ѧ�ڣ�
									<html:select property="xq" style="">
										<html:option value=""></html:option>
										<html:options collection="xqList" property="xqdm"
											labelProperty="xqmc" />
									</html:select>	
								</td>
								<td width="10" rowspan="32" align="center" valign="middle">
									<input type="hidden" name="go" value="a" />
									<button type="button" class="button2" style="" id="search_go"
										onclick="allNotEmpThenGo('/xgxt/xmlgwmbj.do?method=csszManage')">
										��ѯ
									</button>
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
							<font color="blue">��ʾ��������ͷ��������</font>
						</legend>
						<table width="100%" id="rsTable" class="tbstyle">
							<thead>
								<tr align="center" style="cursor:hand">
									<logic:iterate id="tit" name="topTr" offset="1" length="1">
										<td id="<bean:write name="tit" property="en"/>"
											onclick="tableSort(this)" nowrap>
											<bean:write name="tit" property="cn" />
										</td>
									</logic:iterate>
									<logic:iterate id="tit" name="topTr" offset="2">
										<td id="<bean:write name="tit" property="en"/>"
											onclick="tableSort(this)" nowrap>
											<bean:write name="tit" property="cn" />
										</td>
									</logic:iterate>
								</tr>
							</thead>
							<logic:iterate name="rs" id="s">
								<tr onclick="rowOnClick(this);" style="cursor:hand"
									ondblclick="updateCssz('view')">
									<td>
										<logic:iterate id="v" name="s" offset="0" length="1">
											<input type="hidden" value="<bean:write name="v" />" />
										</logic:iterate>
										<logic:iterate id="v" name="s" offset="1" length="1">
											<bean:write name="v" />
										</logic:iterate>
									</td>
									<logic:iterate id="v" name="s" offset="2">
										<td align="left">
											<bean:write name="v" />
										</td>
									</logic:iterate>
								</tr>
							</logic:iterate>
						</table>
						<TABLE width="100%" id="Table" class="tbstyle">
							<TR>
								<TD height=3></TD>
							</TR>
							<TR>
								<TD>
									<jsp:include flush="true"
											page="/sjcz/turnpage.jsp?form=xmlgSzdwForm"></jsp:include>
								</TD>
							</TR>
							<TR>
								<TD height=3></TD>
							</TR>
						</TABLE>
					</fieldset>
				</logic:notEmpty>
				<div id="tmpdiv1"></div>
				<div id="toolTipLayer" style="position:absolute; visibility: hidden" /></div>
				<center>
					<div class="buttontool" id="btn"
						style="position: absolute;left:1%;top:100px" width="100%">
							<logic:equal name="writeAble" value="yes">
								<button type="button" class="button2"
									onclick="updateCssz('add')"
									style="width:80px">
									�� ��
								</button>
								&nbsp;
								<button type="button" class="button2"
									onclick="updateCssz('edit')"
									style="width:80px">
									�޸�
								</button>
								&nbsp;
								<button type="button" class="button2"
									onclick="updateCssz('del')"
									style="width:80px">
									ɾ ��
								</button>
								&nbsp;
								<button type="button" class="button2" 
									onclick="impAndChkData()"
									style="width:80px">
									��������
								</button>
								&nbsp;
								<button type="button" class="button2" 
									onclick="dataExport()"
									style="width:80px">
									��������
								</button>
						</logic:equal>
							&nbsp;&nbsp;&nbsp;&nbsp;
					</div>
				</center>
				<div id="tmpdiv"></div>
			</div>
		</html:form>
		<logic:equal value="yes" name="result">
			<script>
				//alert("�����ɹ�!");
			</script>
		</logic:equal>
		<logic:equal value="no" name="result">
			<script>
				//alert("����ʧ��");
			</script>
		</logic:equal>
		<script type="text/javascript" src="js/bottomButton.js"></script>
	</body>
</html>
