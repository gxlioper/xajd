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
		<script language="javascript" src="js/function.js"></script>
		<script language="javascript" src="js/sztzFunction.js"></script>
		<script type="text/javascript" src="js/calendar.js"></script>
		<script type="text/javascript" src="js/calendar-zh.js"></script>
		<script type="text/javascript" src="js/calendar-setup.js"></script>
		<script type='text/javascript' src='/xgxt/dwr/interface/GetListData.js'></script>
		<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
		<script type='text/javascript' src='dwr/util.js'></script>
		<script language="javascript"  src="js/AjaxFunction.js"></script>		

		<center>
			<html:form action="/bjlhdx_sztz" method="post">
					<input type="hidden" name="zyV" id="zyV" />
					<input type="hidden" name="bjV" id="bjV" />			
				<div class="title">
					<div class="title_img" id="title_m">
						��ǰ����λ�ã�������չ - �������� - ���÷ֳ�ʼ��
					</div>
				</div>
				<fieldset>
					<legend>
						���÷ֳ�ʼ��
					</legend>
					<table width="80%" align="center" class="tbstyle">
						<thead>
							<tr align="center">
								<td height="25" colspan="2">
									���÷ֳ�ʼ���趨
								</td>
							</tr>
						</thead>
						<tr>
							<td width="45%" height="25" align="right">
								�꼶��
							</td>
							<td height="25" align="left">
								<html:select property="nj" style="width:60px"
									onchange="initZyList();initBjList();refreshForm('/xgxt/bjlhdx_sztz.do?method=creditPointInit');">
									<html:option value=""></html:option>
									<html:options collection="njList" property="nj"
										labelProperty="nj" />
								</html:select>
							</td>
						</tr>
						<tr>
							<td width="45%" height="25" align="right">
								<bean:message key="lable.xsgzyxpzxy" />��
							</td>
							<td height="25" align="left">

								<html:select property="xydm" style="width:180px" styleId="xy"
									onchange="initZyList();initBjList();refreshForm('/xgxt/bjlhdx_sztz.do?method=creditPointInit');">
									<html:option value=""></html:option>
									<html:options collection="xyList" property="xydm"
										labelProperty="xymc" />
								</html:select>
							</td>
						</tr>
						<tr>
							<td align="right">
								רҵ��
							</td>
							<td align="left">
								<html:select property="zydm" style="width:180px" styleId="zy"
									onchange="initBjList();refreshForm('/xgxt/bjlhdx_sztz.do?method=creditPointInit');">
									<html:option value=""></html:option>
									<html:options collection="zyList" property="zydm"
										labelProperty="zymc" />
								</html:select>
							</td>
						</tr>
						<tr>
							<td align="right">
								�༶��
							</td>
							<td align="left">
								<html:select property="bjdm" style="width:120px" styleId="bj"
									onchange="refreshForm('/xgxt/bjlhdx_sztz.do?method=creditPointInit');">
									<html:option value=""></html:option>
									<html:options collection="bjList" property="bjdm"
										labelProperty="bjmc" />
								</html:select>
							</td>
						</tr>
						<tr>
							<td align="right">
								��ʼ��ֵ��
							</td>
							<td align="left">
								<html:text property="csf" styleId="csf"
									onkeypress='return sztzNumInputValue(this,6,event)'></html:text>
							</td>
						</tr>
						<thead>
							<tr>
								<td height="25" colspan="3" align="center">
									<button class="button2" id="buttonSave" onclick="saveCsf()">
										����
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
							�����ѯ
						</legend>
						<table width="80%" align="center" class="tbstyle">
							<thead>
								<tr align="center">
									<td>
										�꼶
									</td>
									<td>
										<bean:message key="lable.xsgzyxpzxy" />
									</td>
									<td>
										רҵ
									</td>
									<td>
										�༶
									</td>
									<td>
										��ʼ��ֵ
									</td>
								</tr>
							</thead>
							<logic:iterate name="rs" id="s">
								<tr onclick="rowOnClick(this)" style="cursor:hand"
									ondblclick="xfsbManageView()">

									<logic:iterate id="v" name="s">
										<td nowrap>
											<bean:write name="v" />
										</td>
									</logic:iterate>
								</tr>
							</logic:iterate>
						</table>
					</fieldset>
				</logic:notEmpty>
				<logic:notEmpty name="done">
					<logic:equal name="done" value="true">
						<script>alert("��ʼ���ɹ�!")</script>
					</logic:equal>
					<logic:equal name="done" value="false">
						<script>alert("��ʼ��ʧ��!")</script>
					</logic:equal>
				</logic:notEmpty>
			</html:form>
		</center>
	</body>
	<script type="text/javascript">
	 function saveCsf(){
	   	var nj = $("nj").value;
	   	var xydm = $("xydm").value;
	   	var zydm = $("zydm").value;
	   	var bjdm = $("bjdm").value;    
	    var csf  = $("csf").value;
	    var nj   = document.forms[0].nj.options[document.forms[0].nj.selectedIndex].text;
	    var xymc = document.forms[0].xydm.options[document.forms[0].xydm.selectedIndex].text;
	    var zymc = document.forms[0].zydm.options[document.forms[0].zydm.selectedIndex].text;
	    var bjmc = document.forms[0].bjdm.options[document.forms[0].bjdm.selectedIndex].text;
	    if(csf==""){
	       alert("��ʼ��ֵ����Ϊ�գ�");
	       return false;
	    }
	    if(csf.match(/^\d+\.{0,1}\d{0,3}$/)==null){
		  alert("��ʼ��ֵ��Ϊ���֣�");
		  return false;
	    }
	    var context = "�˲�������";
	    if(nj==""&&xydm==""&&zydm==""&&bjdm==""){	       
	       context+="ȫУȫ��";
	    }else{
	       if(nj!=""){
	           context+=nj+"�꼶 ";
	       }
	       if(xydm!=""){
	           context+=xymc+" ";
	       }
	       if(zydm!=""){
	           context+=zymc+" ";
	       }
	       if(bjdm!=""){
	           context+=bjmc;
	       }	       
	    }
	    context += "ѧ��\n�������÷ֳ��Ի���\n��ʼ��ֵΪ"+csf+"��\n\nȷ��Ҫִ�д˲�����";
	    if(confirm(context)){
	       refreshForm("/xgxt/bjlhdx_sztz.do?method=creditPointInit&doType=save");
	       $("buttonSave").disabled=true;
	    }
	 }
	</script>
</html>
