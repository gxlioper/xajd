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
	<link id="csssDate" rel="stylesheet" rev="stylesheet"
		href="js/calendar.css" type="text/css" media="all" />
	<base target="_self">
	<script language="javascript" src="js/function.js"></script>
	<script language="javascript" src="js/stuinfoFunction.js"></script>
	<script type="text/javascript" src="js/sharedFunction.js"></script>
	<script type="text/javascript" src="js/AjaxFunction.js"></script>		
	<script type='text/javascript' src='/xgxt/dwr/interface/GetListData.js'></script>
	<script type="text/javascript" src="/xgxt/dwr/engine.js"></script>
	<script type="text/javascript" src="/xgxt/dwr/util.js"></script>
	<script language="javascript">	
	function chec(){
      for(i=0;i<document.getElementsByName("pk").length;i++){
      	document.getElementsByName("pk")[i].checked=document.getElementsByName("gwmc")[0].checked;
      }
    }
    
    function show(url){    	
    	var xh = curr_row.cells[0].innerText;
    	
    	url += "&xh=";
    	url += xh;
    	
    	showTopWin(url);
    }
</script>
	<body onload="check_user();">
		<%
			response.setHeader("Pragma", "No-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);
		%>
		<center>

			<html:form action="/xbemyStuStatus.do" method="post">
				<input type="hidden" name="userType" value="<bean:write name="userType"/>" />
				<input type="hidden" name="tableName" value="view_xbemy_xszzysqb" />
				<input type="hidden" name="realTable" value="xbemy_xszzysqb" />
				<input type="hidden" name="xyV" value="" />
				<input type="hidden" name="zyV" value="" />
				<input type="hidden" name="bjV" value="" />
				<div class="title">
					<div class="title_img" id="title_m">
						��ǰλ�ã�ѧ����Ϣ - ѧ���춯���� - תרҵ��������ѯ
					</div>
				</div>
				<fieldset>
					<legend>
						�� ѯ &amp; �� ��
					</legend>
					<table width="100%" class="tbstyle">
						<thead>
							<tr>
								<td align="left">
									ѧ�ţ�
									<html:text property="xh" style="width:120px"/>									
									&nbsp;&nbsp;������
									<html:text property="xm" style="width:120px"/>	
									&nbsp;&nbsp;׼��֤�ţ�
									<html:text property="zkzh" style="width:120px"/>										
								</td>
								<td width="10" rowspan="2" align="center" valign="middle">
									<input type="hidden" name="go" value="a" />
									<button class="button2" style="height:40px" id="search_go"
										onclick="allNotEmpThenGo('xbemyStuStatus.do?method=TransferSpecialtyQuery')">
										��ѯ
									</button>
								</td>
							</tr>
							<tr>
								<td>	
									<bean:message key="lable.xsgzyxpzxy" />��
									<html:select property="xydm" styleId="xy" onchange="initZyList();">
									<html:option value=""></html:option>
									<html:options collection="xyList" property="xydm" labelProperty="xymc"/>
									</html:select>
									&nbsp;&nbsp;רҵ��
									<html:select property="zydm"
										styleId="zy" style="width:180px">
										<html:option value=""></html:option>
										<html:options collection="zyList" property="zydm"
											labelProperty="zymc" />
									</html:select>									
									&nbsp;&nbsp;תרҵ���ͣ�
									<html:select property="zxlx">
									<html:option value=""></html:option>
									<html:option value="ת��">ת��</html:option>
									<html:option value="ת��">ת��</html:option>
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
						δ�ҵ��κμ�¼��
					</p>
				</logic:empty>
				<logic:notEmpty name="rs">
					<fieldset>
						<legend>
							��¼����
							<bean:write name="rsNum" />
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<font color="blue">��ʾ��˫��һ�в鿴��ϸ��Ϣ��������ͷ��������</font>
						</legend>
						<table width="100%" id="rsTable" class="tbstyle">
							<thead>
								<tr align="center" style="cursor:hand">									
									<logic:iterate id="tit" name="topTr" offset="0">
										<td id="<bean:write name="tit" property="en"/>"
											onclick="tableSort(this)" nowrap>
											<bean:write name="tit" property="cn" />
										</td>
									</logic:iterate>
								</tr>
							</thead>
							<logic:iterate name="rs" id="s">
								<tr onclick="rowMoreClick(this,'',event);" style="cursor:hand"
									ondblclick="javascript:if(curr_row==null){alert('��ѡ��Ҫ�޸ĵļ�¼!');return false} else{refreshForm('xbemyStuStatus.do?method=showTransferSpecialty&xh='+curr_row.cells[1].innerText+'&sqrq='+curr_row.cells[0].innerText)}">
									<logic:iterate id="v" name="s" offset="0">
										<td align="left">
											<bean:write name="v" />
										</td>
									</logic:iterate>
								</tr>
							</logic:iterate>
						</table>
					</fieldset>
				</logic:notEmpty>	
				<logic:equal value="yes" name="writeAble" scope="request">
					<div class="buttontool" id="btn"
						style="position: absolute;left:1%;top:100px" width="100%">
						<button class="button2" onclick="refreshForm('xbemyStuStatus.do?method=showTransferSpecialty')"
							style="width:80px">
							�� ��
						</button>
						&nbsp;&nbsp;&nbsp;&nbsp;
						<button class="button2" onclick="javascript:if(curr_row==null){alert('��ѡ��Ҫ�޸ĵļ�¼!');return false} else{refreshForm('xbemyStuStatus.do?method=showTransferSpecialty&xh='+curr_row.cells[1].innerText+'&sqrq='+curr_row.cells[0].innerText)}" style="width:80px">
							�� ��
						</button>
						&nbsp;&nbsp;&nbsp;&nbsp;
						<button class="button2" onclick="javascript:if(curr_row==null){alert('��ѡ��Ҫ�޸ĵļ�¼!');return false} else{if(confirm('��ȷ��ɾ���ü�¼��'))refreshForm('xbemyStuStatus.do?method=TransferSpecialtyQuery&doType=del&xh='+curr_row.cells[1].innerText+'&sqrq='+curr_row.cells[0].innerText)}" style="width:80px">
							ɾ ��
						</button>
						&nbsp;&nbsp;&nbsp;&nbsp;
						<button class="button2"
							onclick="impAndChkData();"
							style="width:80px">
							��������
						</button>
						&nbsp;&nbsp;&nbsp;&nbsp;
						<button class="button2"
							onclick="dataExport1('xbemyStuStatus.do?method=expData')"
							style="width:80px">
							��������
						</button>
						&nbsp;&nbsp;&nbsp;&nbsp;
						<button class="button2" onclick="expTab('rsTable','תѧ������Ϣ','')">
							��ӡ�б�
						</button>
					</div>
					<script language="javascript">
						document.getElementById("btn").style.pixelTop = document.body.clientHeight - 35;
						document.getElementById("btn").style.width = "96%";
						window.setInterval("initBTNTool('btn')",1);
				</script>
				</logic:equal>
			</html:form>
		</center>
		<script type="text/javascript" src="js/bottomButton.js"></script>
	</body>
</html>

