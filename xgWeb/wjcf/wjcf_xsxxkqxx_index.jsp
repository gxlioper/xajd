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
response.setHeader("Pragma","No-cache");
response.setHeader("Cache-Control","no-cache");
response.setDateHeader("Expires", 0);
%>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />

	<link id="csssDate" rel="stylesheet" rev="stylesheet"
		href="js/calendar.css" type="text/css" media="all" />
	<body>
	<script language="javascript">
		function kqcc_view()
		{
			var xn_id=curr_row.cells[0].getElementsByTagName("input")[0].value;
			showTopWin("/xgxt/Wjcf_Kqcc.do?act=wjcf_kqcc&doType=kqcc_view&xn_id="+xn_id,525,400);
		}
		function xsxxkqxx_xskqmx()
		{
			var xh=document.all['xh'].value;
			var rq=document.all['rq'].value;
			if(""==xh)
			{
				alert("����дѧ�ţ�");
				document.all['xh'].focus();
				return false;
			}
			if(""==rq)
			{
				alert("����д�����·�!");
				document.all['rq'].focus();
				return false;
			}
			showTopWin('/xgxt/Wjcf_Xskqxx.do?act=wjcf_xsxxkqxx&doType=xsxxkqxx_xskqmx&xh='+xh+'&rq='+rq,650,450);
		}
		
		function xsxxkqxx_xykqtj()
		{
			var xydm=document.all['xydm'].value;
			var rq=document.all['rq'].value;
			if(""==xydm)
			{
				alert("��ѡ��<bean:message key="lable.xsgzyxpzxy" />��");
				document.all['xydm'].focus();
				return false;
			}
			if(""==rq)
			{
				alert("����д�����·�!");
				document.all['rq'].focus();
				return false;
			}
			showTopWin('/xgxt/Wjcf_Xskqxx.do?act=wjcf_xsxxkqxx&doType=xsxxkqxx_xykqtj&xydm='+xydm+'&rq='+rq,350,300);
		}
		
		function xsxxkqxx_qxkqtj()
		{
			var rq=document.all['rq'].value;
			if(""==rq)
			{
				alert("����д�����·�!");
				document.all['rq'].focus();
				return false;
			}
			showTopWin('/xgxt/Wjcf_Xskqxx.do?act=wjcf_xsxxkqxx&doType=xsxxkqxx_qxkqtj&rq='+rq,650,450);
		}
		
		function data_Export()
		{
			document.forms[0].action = "/xgxt/Wjcf_Xskqxx.do?act=wjcf_xsxxkqxx&doType=export_data";
			document.forms[0].target = "_blank";
			document.forms[0].submit();
			document.forms[0].target = "_self";
		}
		
		function data_inport()
		{
			showTopWin('/xgxt/Wjcf_Xskqxx.do?act=wjcf_xsxxkqxx&doType=inport_data',600,480)
		}
		
	</script>
		<script language="javascript" src="js/function.js"></script>
		<script type="text/javascript" src="js/calendar.js"></script>
		<script type="text/javascript" src="js/calendar-zh.js"></script>
		<script type="text/javascript" src="js/calendar-setup.js"></script>
		<html:form action="/Wjcf_Xskqxx" method="post">
			<div class="title">
				<div class="title_img" id="title_m">
					<logic:equal value="11049" name="xxdm">
						��ǰ����λ�ã��ճ����� - ���ڹ��� - ѧ��������Ϣ
					</logic:equal>
					<logic:notEqual value="11049" name="xxdm">
						��ǰ����λ�ã�Υ�ʹ��� - ѧ������ - ������Ϣά��
					</logic:notEqual>
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
									�꼶��
									<html:select property="nj" style="width:60px" styleId="nj" onchange="refreshForm('/xgxt/Wjcf_Xskqxx.do?act=wjcf_xsxxkqxx')">
										<html:option value=""></html:option>
										<html:options collection="njList" property="nj" labelProperty="nj" />
									</html:select>
									&nbsp;&nbsp;<bean:message key="lable.xsgzyxpzxy" />��
									<html:select property="xydm" style="width:120px" styleId="xy" onchange="refreshForm('/xgxt/Wjcf_Xskqxx.do?act=wjcf_xsxxkqxx')">
										<html:option value=""></html:option>
										<html:options collection="xyList" property="xydm" labelProperty="xymc" />
									</html:select>
									&nbsp;&nbsp;רҵ��
									<html:select property="zydm" style="width:120px" styleId="zy" onchange="refreshForm('/xgxt/Wjcf_Xskqxx.do?act=wjcf_xsxxkqxx')">
										<html:option value=""></html:option>
										<html:options collection="zyList" property="zydm" labelProperty="zymc" />
									</html:select>
									&nbsp;&nbsp;�༶��
									<html:select property="bjdm" style="width:120px" styleId="bj">
										<html:option value=""></html:option>
										<html:options collection="bjList" property="bjdm" labelProperty="bjmc" />
									</html:select>
								</td>
								<td width="10" rowspan="2" align="center" valign="middle">
									<input type="hidden" name="go" value="a" />
									<button type="button" class="button2" style="height:40px" id="search_go"
										onclick="allNotEmpThenGo('/xgxt/Wjcf_Xskqxx.do?act=wjcf_xsxxkqxx&doType=xsxxkqxx_search')">
										��ѯ
									</button>
								</td>
							</tr>
							<tr>
								<td align="left" nowrap>
									ѧ�ţ�
									<html:text property="xh" styleId="xh" style="width: 80px"></html:text>
									&nbsp;&nbsp;������
									<html:text property="xm" styleId="xm" style="width: 80px"></html:text>
									
									&nbsp;&nbsp;�����·ݣ�
									<html:text style="cursor:hand; width:85px;" styleId="dateF" property="rq" onclick="return showCalendar('dateF','yy-mm');"/>
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
									<logic:iterate id="tit" name="topTr" offset="1">
										<td id="<bean:write name="tit" property="en"/>"
											onclick="tableSort(this)" nowrap>
											<bean:write name="tit" property="cn" />
										</td>
									</logic:iterate>
								</tr>
							</thead>
							<logic:iterate name="rs" id="s">
									<tr onclick="rowOnClick(this);" style="cursor:hand" ondblclick="">
										<td>
											<bean:write name="s" property="XH"/>
										</td>
										<td>
											<bean:write name="s" property="XN"/>
										</td>
										<td>
											<bean:write name="s" property="XQ"/>
										</td>
										<td>
											<bean:write name="s" property="KCMC"/>
										</td>
										<td>
											<bean:write name="s" property="MC"/>
										</td>
										<td>
											<bean:write name="s" property="XM"/>
										</td>
										<td>
											<bean:write name="s" property="XB"/>
										</td>
										<td>
											<bean:write name="s" property="XYMC"/>
										</td>
										<td>
											<bean:write name="s" property="ZYMC"/>
										</td>
										<td>
											<bean:write name="s" property="BJMC"/>
										</td>
										<td>
											<bean:write name="s" property="KQSJ"/>
										</td>
									</tr>
							</logic:iterate>
						</table>
					</fieldset>
				</logic:notEmpty>
				<div id="toolTipLayer" style="position:absolute; visibility: hidden" /></div>
				<logic:equal value="yes" name="writeAble" scope="request">
					<center>
						<div class="buttontool" id="btn"
							style="position: absolute;left:1%;top:100px" width="100%">
							&nbsp;&nbsp;&nbsp;&nbsp;
							<button type="button" class="button2" onclick="xsxxkqxx_xskqmx();" style="width:110px" >
								ѧ���¿�����ϸ
							</button>
							&nbsp;&nbsp;&nbsp;&nbsp;
							<button type="button" class="button2" onclick="xsxxkqxx_xykqtj();" style="width:110px">
								<bean:message key="lable.xsgzyxpzxy" />�¿�����
							</button>
							&nbsp;&nbsp;&nbsp;&nbsp;
							<button type="button" class="button2" onclick="xsxxkqxx_qxkqtj();" style="width:110px">
								ȫУ�¿�����
							</button>
							&nbsp;&nbsp;&nbsp;&nbsp;
							<button type="button" class="button2" onclick="data_Export()" style="width:80px">
								��������
							</button>
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<a href="xlsDown/<bean:write name="realTable" scope="request"/>.doc" target="_blank">ģ������</a>
						</div>
					</center>
					<script language="javascript">
					document.getElementById("btn").style.pixelTop = document.body.clientHeight - 35;
					document.getElementById("btn").style.width = "96%";
					window.setInterval("initBTNTool('btn')",1);
					</script>
				</logic:equal>
				<div id="tmpdiv"></div>
			</div>
		</html:form>
	</body>
</html>

