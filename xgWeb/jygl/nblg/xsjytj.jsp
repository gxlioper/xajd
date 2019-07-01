<%@ page language="java" contentType="text/html; charset=gb2312"%>
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
		<title>��ҵ������Ϣϵͳ</title>
		<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
		<meta http-equiv="Content-Language" content="GBK" />
		<meta name="Copyright" content="������� zfsoft" />
	</head>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<link id="csssDate" rel="stylesheet" rev="stylesheet"
		href="js/calendar.css" type="text/css" media="all" />
	<base target="_self">



	<script type="text/javascript" src="js/calendar.js"></script>
	<script type="text/javascript" src="js/calendar-zh.js"></script>
	<script type="text/javascript" src="js/calendar-setup.js"></script>
	<script language="javascript" src="js/jsFunction.js"></script>
	<script type="text/javascript" src="js/BatAlert.js"></script>
	<script language="javascript" src="js/function.js"></script>
	<script type='text/javascript' src='/xgxt/dwr/interface/GetListData.js'></script>
	<script type='text/javascript' src='/xgxt/dwr/interface/getTowdays.js'></script>
	<script type='text/javascript'
		src='/xgxt/dwr/interface/getScoreinfo.js'></script>
	<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
	<script type='text/javascript' src='dwr/util.js'></script>
	<script language="javascript" src="js/AjaxFunction.js"></script>
	<script type="text/javascript">
       function refreshtheweb(doType)
       
		{
		    if(doType=='go'){
		       document.forms[0].action = "xsjytj.do?act=go";
               document.forms[0].submit();
               return false;
		    }
			document.forms[0].action = "xsjytj.do";
            document.forms[0].submit();
            
		}
		
	   function  jyglDataExport(doType){  
	       if(doType=='bjjyqktjb'||doType=='zyjyqktjb'||doType=='fyjyqktjb'||doType=='jyqktjb'||doType=='fbjyqktjb'||doType=='zydkqktjb'||doType=='gzqktjb'){
	         document.forms[0].action = "/xgxt/jyglbyqxDataExport.do?tableName="+doType;
	         document.forms[0].target = "_blank";
	         document.forms[0].submit();
	         document.forms[0].target = "_self";    
	       }else{
		       document.forms[0].action = "/xgxt/jyglbyqxDataExport.do?tableName=view_jygl_xsjyqkb";
		       document.forms[0].target = "_blank";
		       document.forms[0].submit();
		       document.forms[0].target = "_self";
	       }
        }
    
    
    </script>


	<%
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
	%>
	<body>
		<html:form action="/xsjytj" method="post">
			<input type="hidden" name="zyV" id="zyV" />
			<input type="hidden" name="bjV" id="bjV" />
			<fieldset>
				<legend>
					ѧ����ҵͳ��
				</legend>
				<table width="100%" class="tbstyle">
					<thead>
						<tr>
							<td align="center" colspan="2">
								��ѡ��ͳ����Ŀ��
								<html:select name="rs1" property="tjlx"
									onchange="refreshtheweb()">
									<html:option value="">
										------------��ѡ��------------
									</html:option>
									<html:option value="�༶��ҵ�������ͳ�Ʊ�">
										�༶��ҵ�������ͳ�Ʊ�
									</html:option>
									<html:option value="רҵ��ҵ�������ͳ�Ʊ�">
										רҵ��ҵ�������ͳ�Ʊ�
									</html:option>
									<html:option value="��Ժ��ҵ�������ͳ�Ʊ�">
										��Ժ��ҵ�������ͳ�Ʊ�
									</html:option>
									<html:option value="��ҵ�������ͳ���ܱ�">
										��ҵ�������ͳ���ܱ�
									</html:option>
									<html:option value="�ְ��ҵ�����ϸ��">
										�ְ��ҵ�����ϸ��
									</html:option>
									<html:option value="רҵ�Կ����ͳ�Ʊ�">
										רҵ�Կ����ͳ�Ʊ�
									</html:option>
									<html:option value="��ҵ����״��ͳ�Ʊ�">
										��ҵ����״��ͳ�Ʊ�
									</html:option>
								</html:select>
							</td>
						</tr>
					</thead>
				</table>
				<logic:equal name="which" value="�༶��ҵ�������ͳ�Ʊ�">
					<input type="hidden" id="querry" name="querry"
						value="<bean:write name='querry' scope="request" />" />
					<table width="100%" id="rsTable" class="tbstyle">
						<tr>
							<td>
								<bean:message key="lable.xsgzyxpzxy" />��
								<html:select name="rs1" property="xydm" style="width:200px"
									styleId="xy" onchange="initZyList();initBjList();">
									<html:option value=""></html:option>
									<html:options collection="xyList" property="xydm"
										labelProperty="xymc" />
								</html:select>
								&nbsp;&nbsp;רҵ��
								<html:select name="rs1" property="zydm" style="width:180px"
									styleId="zy" onchange="initBjList()">
									<html:option value=""></html:option>
									<html:options collection="zyList" property="zydm"
										labelProperty="zymc" />
								</html:select>
								&nbsp;&nbsp;�༶��
								<html:select name="rs1" property="bjdm" style="width:180px"
									styleId="bj">
									<html:option value=""></html:option>
									<html:options collection="bjList" property="bjdm"
										labelProperty="bjmc" />
								</html:select>
							</td>
							<td align="right" width="10%">
								<button class="button2" style="width:100%"
									onclick="refreshtheweb('go')">
									ͳ��
								</button>
							</td>
						</tr>
					</table>
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
									<tr>
										<td align="center">
											<bean:write name="s" property="rownum" />
										</td>
										<td>
											<bean:write name="s" property="xymc" />
										</td>
										<td>
											<bean:write name="s" property="bjmc" />
										</td>
										<td align="center">
											<bean:write name="s" property="bjrs" />
										</td>
										<td align="center">
											<bean:write name="s" property="bzr" />
										</td>
										<td align="center">
											<bean:write name="s" property="lsdwrs" />
										</td>
										<td align="center">
											<bean:write name="s" property="qyrs" />
										</td>
										<td align="center">
											<bean:write name="s" property="jyl" />
										</td>
										<td align="center">
											<bean:write name="s" property="qyl" />
										</td>
									</tr>
								</logic:iterate>

							</table>
							<table width="100%">
								<tr align="center">
									<td align="center">
										<button onclick="jyglDataExport('bjjyqktjb')" class="button2">
											���ݵ���
										</button>
									</td>
								</tr>
							</table>
						</fieldset>
					</logic:notEmpty>
				</logic:equal>
				<logic:equal name="which" value="רҵ��ҵ�������ͳ�Ʊ�">
					<input type="hidden" id="querry" name="querry"
						value="<bean:write name='querry' scope="request" />" />
					<table width="100%" id="rsTable" class="tbstyle">
						<tr>
							<td>
								<bean:message key="lable.xsgzyxpzxy" />��
								<html:select name="rs1" property="xydm" style="width:200px"
									styleId="xy" onchange="initZyList();initBjList();">
									<html:option value=""></html:option>
									<html:options collection="xyList" property="xydm"
										labelProperty="xymc" />
								</html:select>
								&nbsp;&nbsp;רҵ��
								<html:select name="rs1" property="zydm" style="width:180px"
									styleId="zy" onchange="initBjList()">
									<html:option value=""></html:option>
									<html:options collection="zyList" property="zydm"
										labelProperty="zymc" />
								</html:select>
							</td>
							<td align="right" width="10%">
								<button class="button2" style="width:100%"
									onclick="refreshtheweb('go')">
									ͳ��
								</button>
							</td>
						</tr>
					</table>
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
									<tr>
										<td align="center">
											<bean:write name="s" property="rownum" />
										</td>
										<td>
											<bean:write name="s" property="xymc" />
										</td>
										<td>
											<bean:write name="s" property="zymc" />
										</td>
										<td align="center">
											<bean:write name="s" property="zyrs" />
										</td>
										<td align="center">
											<bean:write name="s" property="lsdwrs" />
										</td>
										<td align="center">
											<bean:write name="s" property="qyrs" />
										</td>
										<td align="center">
											<bean:write name="s" property="jyl" />
										</td>
										<td align="center">
											<bean:write name="s" property="qyl" />
										</td>
									</tr>
								</logic:iterate>

							</table>
							<table width="100%">
								<tr align="center">
									<td align="center">
										<button onclick="jyglDataExport('zyjyqktjb')" class="button2">
											���ݵ���
										</button>
									</td>
								</tr>
							</table>
						</fieldset>
					</logic:notEmpty>
				</logic:equal>
				<logic:equal name="which" value="��Ժ��ҵ�������ͳ�Ʊ�">
					<input type="hidden" id="querry" name="querry"
						value="<bean:write name='querry' scope="request" />" />
					<table width="100%" id="rsTable" class="tbstyle">
						<tr>
							<td>
								<bean:message key="lable.xsgzyxpzxy" />��
								<html:select name="rs1" property="xydm" style="width:200px"
									styleId="xy" onchange="initZyList();initBjList();">
									<html:option value=""></html:option>
									<html:options collection="xyList" property="xydm"
										labelProperty="xymc" />
								</html:select>
							</td>
							<td align="right" width="10%">
								<button class="button2" style="width:100%"
									onclick="refreshtheweb('go')">
									ͳ��
								</button>
							</td>
						</tr>
					</table>
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
									<tr>
										<td align="center">
											<bean:write name="s" property="rownum" />
										</td>
										<td>
											<bean:write name="s" property="xymc" />
										</td>
										<td align="center">
											<bean:write name="s" property="xyrs" />
										</td>
										<td align="center">
											<bean:write name="s" property="lsdwrs" />
										</td>
										<td align="center">
											<bean:write name="s" property="qyrs" />
										</td>
										<td align="center">
											<bean:write name="s" property="jyl" />
										</td>
										<td align="center">
											<bean:write name="s" property="qyl" />
										</td>
									</tr>
								</logic:iterate>

							</table>
							<table width="100%">
								<tr align="center">
									<td align="center">
										<button onclick="jyglDataExport('fyjyqktjb')" class="button2">
											���ݵ���
										</button>
									</td>
								</tr>
							</table>
						</fieldset>
					</logic:notEmpty>
				</logic:equal>
				<logic:equal name="which" value="��ҵ�������ͳ���ܱ�">
					<table width="100%" id="rsTable" class="tbstyle">
						<tr>
							<td>
								&nbsp;
							</td>
							<td align="right" width="10%">
								<button class="button2" style="width:100%"
									onclick="refreshtheweb('go')">
									ͳ��
								</button>
							</td>
						</tr>
					</table>
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
									<tr>
										<td align="center">
											<bean:write name="s" property="zrs" />
										</td>
										<td align="center">
											<bean:write name="s" property="lsdwrs" />
										</td>
										<td align="center">
											<bean:write name="s" property="qyrs" />
										</td>
										<td align="center">
											<bean:write name="s" property="qyl" />
										</td>
										<td align="center">
											<bean:write name="s" property="jyrs" />
										</td>
										<td align="center">
											<bean:write name="s" property="jyl" />
										</td>
									</tr>
								</logic:iterate>

							</table>
							<table width="100%">
								<tr align="center">
									<td align="center">
										<button onclick="jyglDataExport('jyqktjb')" class="button2">
											���ݵ���
										</button>
									</td>
								</tr>
							</table>
						</fieldset>
					</logic:notEmpty>
				</logic:equal>
				<logic:equal name="which" value="�ְ��ҵ�����ϸ��">
					<input type="hidden" id="querry" name="querry"
						value="<bean:write name='querry' scope="request" />" />
					<table width="100%" id="rsTable" class="tbstyle">
						<tr>
							<td>
								<bean:message key="lable.xsgzyxpzxy" />��
								<html:select name="rs1" property="xydm" style="width:200px"
									styleId="xy" onchange="initZyList();initBjList();">
									<html:option value=""></html:option>
									<html:options collection="xyList" property="xydm"
										labelProperty="xymc" />
								</html:select>
								&nbsp;&nbsp;רҵ��
								<html:select name="rs1" property="zydm" style="width:180px"
									styleId="zy" onchange="initBjList()">
									<html:option value=""></html:option>
									<html:options collection="zyList" property="zydm"
										labelProperty="zymc" />
								</html:select>
								&nbsp;&nbsp;�༶��
								<html:select name="rs1" property="bjdm" style="width:180px"
									styleId="bj">
									<html:option value=""></html:option>
									<html:options collection="bjList" property="bjdm"
										labelProperty="bjmc" />
								</html:select>
							</td>
							<td align="right" width="10%">
								<button class="button2" style="width:100%"
									onclick="refreshtheweb('go')">
									ͳ��
								</button>
							</td>
						</tr>
					</table>
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
									<tr>
										<td align="center">
											<bean:write name="s" property="rownum" />
										</td>
										<td>
											<bean:write name="s" property="xh" />
										</td>
										<td align="center">
											<bean:write name="s" property="xm" />
										</td>
										<td align="center">
											<bean:write name="s" property="xb" />
										</td>
										<td>
											<bean:write name="s" property="dwmc" />
										</td>
										<td>
											<bean:write name="s" property="dwdz" />
										</td>
										<td>
											<bean:write name="s" property="dwdh" />
										</td>
										<td align="center">
											<bean:write name="s" property="sfjy" />
										</td>
										<td align="center">
											<bean:write name="s" property="sfqy" />
										</td>
										<td align="center">
											<bean:write name="s" property="djsj" />
										</td>
									</tr>
								</logic:iterate>

							</table>
							<table width="100%">
								<tr align="center">
									<td align="center">
										<button onclick="jyglDataExport('fbjyqktjb')" class="button2">
											���ݵ���
										</button>
									</td>
								</tr>
							</table>
						</fieldset>
					</logic:notEmpty>
				</logic:equal>
				<logic:equal name="which" value="רҵ�Կ����ͳ�Ʊ�">
					<input type="hidden" id="querry" name="querry"
						value="<bean:write name='querry' scope="request" />" />
					<table width="100%" id="rsTable" class="tbstyle">
						<tr>
							<td>
								<bean:message key="lable.xsgzyxpzxy" />��
								<html:select name="rs1" property="xydm" style="width:200px"
									styleId="xy" onchange="initZyList();initBjList();">
									<html:option value=""></html:option>
									<html:options collection="xyList" property="xydm"
										labelProperty="xymc" />
								</html:select>
								&nbsp;&nbsp;רҵ��
								<html:select name="rs1" property="zydm" style="width:180px"
									styleId="zy" onchange="initBjList()">
									<html:option value=""></html:option>
									<html:options collection="zyList" property="zydm"
										labelProperty="zymc" />
								</html:select>
							</td>
							<td align="right" width="10%">
								<button class="button2" style="width:100%"
									onclick="refreshtheweb('go')">
									ͳ��
								</button>
							</td>
						</tr>
					</table>
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
									<tr>
										<td align="center">
											<bean:write name="s" property="rownum" />
										</td>
										<td>
											<bean:write name="s" property="xymc" />
										</td>
										<td>
											<bean:write name="s" property="zymc" />
										</td>
										<td align="center">
											<bean:write name="s" property="zyrs" />
										</td>
										<td align="center">
											<bean:write name="s" property="lsdwrs" />
										</td>
										<td align="center">
											<bean:write name="s" property="zydkrs" />
										</td>
										<td align="center">
											<bean:write name="s" property="dkl" />
										</td>
									</tr>
								</logic:iterate>

							</table>
							<table width="100%">
								<tr align="center">
									<td align="center">
										<button onclick="jyglDataExport('zydkqktjb')" class="button2">
											���ݵ���
										</button>
									</td>
								</tr>
							</table>
						</fieldset>
					</logic:notEmpty>
				</logic:equal>
				<logic:equal name="which" value="��ҵ����״��ͳ�Ʊ�">
					<table width="100%" id="rsTable" class="tbstyle">
						<tr>
							<td>
								&nbsp;
							</td>
							<td align="right" width="10%">
								<button class="button2" style="width:100%"
									onclick="refreshtheweb('go')">
									ͳ��
								</button>
							</td>
						</tr>
					</table>
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
									<tr>
										<td align="center">
											<bean:write name="s" property="rownum" />
										</td>
										<td align="center">
											<bean:write name="s" property="gzqk" />
										</td>
										<td align="center">
											<bean:write name="s" property="lsdwrs" />
										</td>
										<td align="center">
											<bean:write name="s" property="bl" />
										</td>
									</tr>
								</logic:iterate>

							</table>
							<table width="100%">
								<tr align="center">
									<td align="center">
										<button onclick="jyglDataExport('gzqktjb')" class="button2">
											���ݵ���
										</button>
									</td>
								</tr>
							</table>
						</fieldset>
					</logic:notEmpty>
				</logic:equal>
			</fieldset>
		</html:form>
	</body>
</html>

