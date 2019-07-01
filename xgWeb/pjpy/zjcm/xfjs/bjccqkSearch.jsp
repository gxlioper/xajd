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
	<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
	<script type='text/javascript' src='dwr/util.js'></script>
	<script type='text/javascript' src='/xgxt/dwr/interface/GetListData.js'></script>
	<script language="javascript" src="js/AjaxFunction.js"></script>
	<script language="javascript" src="js/xgutil.js"></script>
	<script language="javascript">	
		function changeSelect(dmId,mcId){
			setVal(mcId,selText(dmId));
		}
		
		function dataExport(){
			var url = "pjpyxfjs.do?method=expBjccqktjxx";
			url += "&nj="+val('nj');
			url += "&xydm="+val('xy');
			url += "&zydm="+val('zy');
			url += "&bjdm="+val('bj');
			url += "&xymc="+val('xymc');
			url += "&zymc="+val('zymc');
			url += "&bjmc="+val('bjmc');
			url += "&xn="+val('xn');
			url += "&xq="+val('xq');
			url += "&jclxdm="+val('jclxdm');
			url += "&jclxmc="+val('jclxmc');
			
			window.open(url);
		}
		
		//��ʾ��ϸ��Ϣ
		function showDetails(){
			url = "pjpyxfjs.do?method=bjccqktjDetails";
			var pk = val('pk');
			var pkValue = curr_row.getElementsByTagName("input")[0].value;
			
			url += "&pk=";
			url += pk;
			url += "&pkValue="; 
			url += pkValue;
			
			showTopWin(url);
		}
	</script>
	<body onload="xyDisabled('xy');">		
		<html:form action="/pjpyxfjs" method="post">
			<input type="hidden" name="xyV" id="xyV" value=""/>
			<input type="hidden" name="zyV" id="zyV" value=""/>
			<input type="hidden" name="bjV" id="bjV" value=""/>
			<input type="hidden" name="njV" id="njV" value=""/>
			<input type="hidden" id="tableName" name="tableName" value="<bean:write name="tableName" scope="request"/>" />
			<input type="hidden" id="realTable" name="realTable" value="<bean:write name="realTable" scope="request"/>" />
			<input type="hidden" id="userName" name="userName" value="<bean:write name="userName" scope="session"/>"/>
			<html:hidden property="xymc" styleId="xymc"/>
			<html:hidden property="zymc" styleId="zymc"/>
			<html:hidden property="bjmc" styleId="bjmc"/>
			<html:hidden property="jclxmc" styleId="jclxmc"/>
			<input type="hidden" name="pk" id="pk" value="${pk}"/>
			
			<div class="title">
				<div class="title_img" id="title_m">
					��ǰ����λ�ã�<bean:write name="title"/>
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
									<html:select property="nj" style=""
										onchange="initZyList();initBjList()">
										<html:option value=""></html:option>
										<html:options collection="njList" property="nj"
											labelProperty="nj" />
									</html:select>
									ѧ�꣺	
									<html:select property="xn" style="">
										<html:option value=""></html:option>
										<html:options collection="xnList" property="xn"
											labelProperty="xn" />
									</html:select>
									ѧ�ڣ�						
									<html:select property="xq" style="">
										<html:option value=""></html:option>
										<html:options collection="xqList" property="xqdm"
											labelProperty="xqmc" />
									</html:select>	
									������ͣ�
									<html:select property="jclxdm" onchange="changeSelect('jclxdm','jclxmc')">
										<html:option value=""></html:option>
										<html:options collection="jclxList" property="jclxdm" labelProperty="jclxmc"/>
									</html:select>				
								</td>
								<td width="10" rowspan="3" align="center" valign="middle">
									<input type="hidden" name="go" value="a" />
									<button type="button" class="button2" style="height:40px" id="search_go"
										onclick="allNotEmpThenGo('/xgxt/pjpyxfjs.do?method=bjccqkSearch')">
										��ѯ
									</button>
								</td>
							</tr>
							<tr>
								<td align="left" nowrap="nowrap">
									<bean:message key="lable.xsgzyxpzxy" />��
									<html:select property="xydm" style="" styleId="xy"
										onchange="initZyList();initBjList();changeSelect('xy','xymc')">
										<html:option value=""></html:option>
										<html:options collection="xyList" property="xydm"
											labelProperty="xymc" />
									</html:select>
									&nbsp;&nbsp;רҵ��
									<html:select property="zydm" style="" styleId="zy"
										onchange="initBjList();changeSelect('zy','zymc')">
										<html:option value=""></html:option>
										<html:options collection="zyList" property="zydm"
											labelProperty="zymc" />
									</html:select>
									&nbsp;&nbsp;�༶��
									<html:select property="bjdm" style="" styleId="bj" onchange="changeSelect('bj','bjmc')">
										<html:option value=""></html:option>
										<html:options collection="bjList" property="bjdm"
											labelProperty="bjmc" />
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
							<font color="blue">��ʾ��������ͷ��������;</font>
						</legend>
						<table width="100%" id="rsTable" class="tbstyle">
							<thead>
								<tr align="center" style="cursor:hand">
									<td>
										<input type="checkbox" id="selall" name="selall"
											onclick="selAll()">
									</td>
									<logic:iterate id="tit" name="topTr" offset="1">
										<td id="<bean:write name="tit" property="en"/>"
											onclick="tableSort(this)" nowrap="nowrap">
											<bean:write name="tit" property="cn" />
										</td>
									</logic:iterate>
								</tr>
							</thead>
							<logic:iterate name="rs" id="s">
								<tr onclick="rowOnClick(this);" ondblclick="showDetails()" style="cursor:hand">
									<td>
									<input type="checkbox" id="checkVal" name="checkVal" 
								 		value="<logic:iterate id="v" name="s" offset="0" length="1"><bean:write name="v"/></logic:iterate>">
									</td>										
									<logic:iterate id="v" name="s" offset="1" >
										<td align="left">
											<bean:write name="v" />
										</td>
									</logic:iterate>
								</tr>
							</logic:iterate>
						</table>
					</fieldset>
				</logic:notEmpty>
				
				<center>
					<div class="buttontool" id="btn"
						style="position: absolute;left:1%;top:100px">
						&nbsp;&nbsp;
						<button type="button" class="button2" onclick="dataExport()"
							style="width:80px">
							��������
						</button>
					</div>
				</center>
				<div id="tmpdiv"></div>
			</div>
			<div id="tmpdiv1"></div>
		</html:form>
		<logic:equal value="true" name="result">
			<script>
				alert("�����ɹ�!");
			</script>
		</logic:equal>
		<logic:equal value="false" name="result">
			<script>
				alert("����ʧ�ܣ�");
			</script>
		</logic:equal>
		<script type="text/javascript" src="js/bottomButton.js"></script>
	</body>
</html>
