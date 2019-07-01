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
		<base target="_self"/>
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
	<script type="text/javascript">
		function percentOfBdSearch(){
			refreshForm('/xgxt/percentOfBdSearch.do');
		}
	</script>	
	<body onload="getQshListBe()">
		<script language="javascript" src="js/AjaxFunction.js"></script>
		<script language="javascript" src="js/function.js"></script>
		<script language="javascript" src="js/yxglFunction.js"></script>
		<script language="javascript" src="js/sharedFunction.js"></script>
		<script type='text/javascript' src='/xgxt/dwr/interface/yxglFun.js'></script>
		<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
		<script type='text/javascript' src='/xgxt/dwr/util.js'></script>
		<logic:present name="flag">
			<input type="hidden" id="flag" name="flag" value="<bean:write name="onload" />">		
		</logic:present>
		<html:form action="yxgl_xsgl.do">
		<input type="hidden" id="realTable" name="realTable" value="newstusinfo" >
		<input type="hidden" id="tableName" name="tableName" value="newstusinfo" >
		<input type="hidden" name="qshV" id="qshV" value=""/>
			<div class="title">
				<div class="title_img" id="title_m">
					��ǰ����λ�ã�ӭ�¹��� �� �������� �� ͳ�Ʋ�ѯ
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
									&nbsp;&nbsp;<bean:message key="lable.xsgzyxpzxy" />��
									<logic:equal name="userType" value="xy" scope="session">
									<html:select property="xydm" style="width:180px" styleId="xy"
										onchange="refreshForm('yxgl_xstj.do');getfocus('search_go')" disabled="true">
										<html:option value=""></html:option>
										<html:options collection="xyList" property="xydm"
											labelProperty="xymc" />
									</html:select>
									</logic:equal>
									<logic:notEqual name="userType" value="xy" scope="session">
										<html:select property="xydm" style="width:180px" styleId="xy"
											onchange="refreshForm('yxgl_xstj.do');getfocus('search_go')">
											<html:option value=""></html:option>
											<html:options collection="xyList" property="xydm"
											labelProperty="xymc" />
										</html:select>
									</logic:notEqual>
									&nbsp;&nbsp;רҵ��
									<html:select property="zydm" style="width:180px" styleId="zy"
										onchange="refreshForm('yxgl_xstj.do');getfocus('search_go')">
										<html:option value=""></html:option>
										<html:options collection="zyList" property="zydm"
											labelProperty="zymc" />
									</html:select>
									&nbsp;&nbsp;�༶��
									<html:select property="bjdm" style="width:180px" styleId="bj"
										onchange="refreshForm('yxgl_xstj.do');getfocus('search_go')">
										<html:option value=""></html:option>
										<html:options collection="bjList" property="bjdm"
											labelProperty="bjmc" />
									</html:select>									
								</td>
								<logic:equal value="10463" name="xxdm">
									<td width="50" rowspan="3" align="center" valign="middle">
									<input type="hidden" name="go" value="a" />
									<button class="button2" id="search_go"
										onclick="search2('yxgl_xstj.do')"
										onkeypress="keyPressDo(this)" style="height:40px;">
										ͳ��
									</button>
									</td>
								</logic:equal>
								<logic:notEqual value="10463" name="xxdm">
									<td width="50" rowspan="2" align="center" valign="middle">
									<input type="hidden" name="go" value="a" />
									<button class="button2" id="search_go"
										onclick="search2('yxgl_xstj.do')"
										onkeypress="keyPressDo(this)" style="height:40px;">
										ͳ��
									</button>
								</td>
								</logic:notEqual>
							</tr>
							<tr>
								<td>
									&nbsp;&nbsp;�꼶:
									<html:select  name="dqnd" property="nj" style="width:120px" styleId="nj" disabled="true"> 
										<html:option value=""></html:option>
										<html:options collection="njList" property="nj"
											labelProperty="nj" />
									</html:select>	
									&nbsp;&nbsp;�Ա�:
									<html:select property="xb" style="width:120px" styleId="bgzl">
										<html:option value=""></html:option>
										<html:options collection="xbList" property="cn"
											labelProperty="cn" />
									</html:select>	
									&nbsp;&nbsp;��������:
									<html:select property="bgzldm" style="width:120px" styleId="bgzl">
										<html:options collection="bgzlList" property="en"
											labelProperty="cn" />
									</html:select>		
									&nbsp;&nbsp;����ʡ��:
									<html:select property="sfdm" style="width:120px" styleId="sfdm">
									<html:option value=""></html:option>
										<html:options collection="sfList" property="sfdm"
											labelProperty="sfmc" />
									</html:select>	
								</td>
							</tr>	
							<%-- ���Ϲ�ҵ��ѧ--%>
							<logic:equal value="10463" name="xxdm">
								<tr>
									<td title="¥�����ƻ����Һű�ѡ�Ϻ�,����������Զ�ѡ���ǡ����ᱨ����">
										&nbsp;&nbsp;¥������
										<html:select property="lddm" style="width:120px" styleId="lddm"  
											onchange="judgeIsNull();initQsList();" onclick="judgeIsNull();">
										<html:option value=""></html:option>
										<html:options collection="ldList" property="lddm"
											labelProperty="ldh" />
										</html:select>	
										&nbsp;&nbsp;���Һ�
										<html:select property="qsh" style="width:120px" styleId="qsh">
										</html:select>
									</td>
								</tr>
							</logic:equal>							
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
				
						<table width="100%" id="rsTable" class="tbstyle">
							<thead>
								<tr align="center" style="cursor:hand">
									<td>���ҷ�Χ</td>
									<td>����ģ��</td>
									<td>������</td>
									<td>δ������</td>
									<td>������</td>
								</tr>
							</thead>
								<tr style="cursor:hand" onclick="rowOnClick(this)">
										<td><bean:write name="rs" property="moduleName"/></td>
										<td><bean:write name="rs" property="chickModule"/></td>
										<td><bean:write name="rs" property="yesCount"/></td>
										<td><bean:write name="rs" property="noCount"/></td>
										<td><bean:write name="rs" property="maxSum"/></td>
								</tr>
						</table>
						<br/>
						
					<logic:notEmpty name="rs2">	
						<br/><font color="red">��ϸ���</font>
						<table width="100%" id="rsTable" class="tbstyle">
							<thead>
			<%--				
							<logic:present name="henanSs">
								<tr align="center" style="cursor:hand">
									<td onclick="tableSort(this)" nowrap>���Һ�</td>
									<td onclick="tableSort(this)" nowrap>����ģ��</td>
									<td onclick="tableSort(this)" nowrap>������</td>
									<td onclick="tableSort(this)" nowrap>δ������</td>
									<td onclick="tableSort(this)" nowrap>���Ҵ�λ��</td>
								</tr>
							</logic:present>
							<logic:notPresent name="henanSs">
			--%>				
								<tr align="center" style="cursor:hand">
									<td onclick="tableSort(this)" nowrap>���ҷ�Χ</td>
									<td onclick="tableSort(this)" nowrap>����ģ��</td>
									<td onclick="tableSort(this)" nowrap>������</td>
									<td onclick="tableSort(this)" nowrap>δ������</td>
									<td onclick="tableSort(this)" nowrap>������</td>
								</tr>	
							</thead>
							<logic:iterate name="rs2" id="s">	
									<tr style="cursor:hand" onclick="rowOnClick(this)">
										<logic:iterate id="v" name="s" offset="0">
											<td nowrap>
												<bean:write name="v" />
											</td>
										</logic:iterate>
									</tr>
							</logic:iterate>
						</table>
					</logic:notEmpty>
					</fieldset>
				</logic:notEmpty>	
		</html:form>
		<script type="text/javascript" src="js/bottomButton.js"></script>
	</body>
</html>


