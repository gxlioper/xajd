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
	<body onload="changView(document.getElementById('lb'))">
		<script language="javascript" src="js/function.js"></script>
		<script type='text/javascript'
			src='/xgxt/dwr/interface/GetListData.js'></script>
		<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
		<script type='text/javascript' src='dwr/util.js'></script>
		<script language="javascript" src="js/AjaxFunction.js"></script>
		<script language="javascript">
	    function jyxxtj(){
	    	var nd = document.getElementById('nd').value;
	    	if(nd == ''){
	    		alert('��Ȳ���Ϊ�գ�');
	    		return  false;
	    	}
		 	document.forms[0].action = "/xgxt/jyxxtj.do?doType=tj";
		 	document.forms[0].submit();
        }
        function changView(obj){
        	if(obj.value=='bjdm'){
        		document.getElementById('bj').disabled=false;
        		document.getElementById('zy').disabled=false;
        	}else if(obj.value=='zydm'){
        		document.getElementById('bj').value='';
        		document.getElementById('bj').disabled=true;
        		document.getElementById('zy').disabled=false;
        	}else if(obj.value=='xydm'){
        		document.getElementById('bj').value='';
        		document.getElementById('zy').value='';
        		document.getElementById('bj').disabled=true;
        		document.getElementById('zy').disabled=true;
        	}else{
        		document.getElementById('bj').disabled=false;
        		document.getElementById('zy').disabled=false;
        	}
        }
        function tjExport() {
			document.forms[0].action = "/xgxt/jyxxtj.do?doType=tjdc";
			document.forms[0].target = "_blank";
			document.forms[0].submit();
			document.forms[0].target = "_self";
		}
		</script>
		<html:form action="/jyxxlr" method="post">
			<input type="hidden" name="zyV" id="zyV" />
			<input type="hidden" name="bjV" id="bjV" />
			<input type="hidden" name="pkStr" id="pkStr" />
			<input type="hidden" id="realTable" name="realTable"
				value="<bean:write name='realTable' scope="request" />" />
			<input type="hidden" id="tableName" name="tableName"
				value="<bean:write name='tableName' scope="request" />"/>
			<div class="title">
				<div class="title_img" id="title_m">
					��ǰ����λ�ã���ҵ���� - ͳ�Ʒ��� - ��ҵ��Ϣͳ��
				</div>
			</div>
			<fieldset>
				<legend>
					�� ѯ
				</legend>
					<table width="100%" class="tbstyle">
						<thead>
							<tr style="cursor:hand">
								<td>
									 ��ȣ�
								    <html:select property="nd" style="width:100px" styleId="nd">
											<html:options collection="ndList" property="nd"
												labelProperty="nd" />
									</html:select>
									&nbsp;&nbsp;�꼶��
								    <html:select property="nj" style="width:100px" onchange="initZyList();initBjList()">
								    		<html:option value=""></html:option>
											<html:options collection="njList" property="nj"
												labelProperty="nj" />
									</html:select>
									&nbsp;&nbsp;ͳ�����
								   	<html:select property="lb" style="width:120px" styleId="lb" onchange="changView(this)">
								   			<html:option value="xydm"><bean:message key="lable.xsgzyxpzxy" /></html:option>
											<html:option value="zydm">רҵ</html:option>
											<html:option value="bjdm">�༶</html:option>
											<html:option value="jyqy">����ҵ����</html:option>
											<html:option value="sydqdm">����Դ��Ϣ</html:option>
											<html:option value="qybz">��ǩԼ��־</html:option>
											<html:option value="xscc">��ѧ�����</html:option>
								 	</html:select>
									&nbsp;&nbsp;<bean:message key="lable.xsgzyxpzxy" />��
									<logic:equal value="xy" name="userType">
										<html:select property="xydm" style="width:200px" styleId="xy" disabled="true">
											<html:option value=""></html:option>
											<html:options collection="xyList" property="xydm"
												labelProperty="xymc" />
								 		</html:select>
									</logic:equal>
									<logic:notEqual value="xy" name="userType">
										<html:select property="xydm" style="width:150px" styleId="xy" onchange="initZyList();initBjList()">
											<html:option value=""></html:option>
											<html:options collection="xyList" property="xydm"
												labelProperty="xymc" />
								 		</html:select>
									</logic:notEqual>
								</td>
								<td width="10" rowspan="2" align="center" valign="middle">
									<button class="button2" style="height:40px"
										onclick="jyxxtj()" id="query_go">
										��ѯ
									</button>
								</td>
							</tr>
							<tr>
								<td>
									רҵ��
								   	<html:select property="zydm" style="width:200px" styleId="zy" onchange="initBjList()">
											<html:option value=""></html:option>
											<html:options collection="zyList" property="zydm"
												labelProperty="zymc" />
								 	</html:select>
								 	&nbsp;&nbsp;�༶��
								   	<html:select property="bjdm" style="width:220px" styleId="bj">
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
						<font color="blue">��ʾ��������ͷ��������</font>
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
							<tr onclick="rowOnClick(this)" style="cursor:hand">
								<logic:iterate id="v" name="s" offset="0">
									<td align="center">
										${v}
									</td>
								</logic:iterate>
							</tr>
						</logic:iterate>
					</table>
				</fieldset>
			</logic:notEmpty>
				<div class="buttontool" id="btn"
					style="position: absolute;left:0px;top:100px" width="100%">
					<button class="button2" onclick="tjExport()"
							style="width:80px">
							��������
					</button>			
				</div>
		</html:form>
		<script type="text/javascript" src="js/bottomButton.js"></script>
	</body>
</html>
