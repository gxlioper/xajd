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
	<body onload="xyDisabled('xy');">
		<script language="javascript" src="js/function.js"></script>
		<script language="javascript" src="/xgxt/pjpy/xibeierminyuan/js/xbemyJs.js"></script>
		<script language="javascript" src="/xgxt/wjcf/shgc/shgcjs/shgcjs.js"></script>
		<script language="javascript" src="js/AjaxFunction.js"></script>
		<script type='text/javascript' src='/xgxt/dwr/interface/GetListData.js'></script>
		<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
	<script type='text/javascript' src='/xgxt/dwr/util.js'></script>
		<script language="javascript" src="/xgxt/pjpy/xibeierminyuan/js/xbemyJs.js"></script>
		<script language="javascript" src="pjpy/csmz/csmzJs/csmzjs.js"></script>
		<script type="text/javascript" src="pjpy/ahjg/ahjgjs/ahjgjs.js"></script>
		<html:form action="/pjpyahjgwh" method="post">
			<div class="title">
				<div class="title_img" id="title_m">
					<bean:message key="pjpy_ahjg_bjbkl" bundle="pjpyahjg"/>
				</div>
			</div>
			<input type="hidden" id="userType" name="userType"
				value="${userType }"/>
			<input type="hidden" name="xyV" value=""/>
			<input type="hidden" name="zyV" value=""/>
			<input type="hidden" name="bjV" value=""/>
			<input type="hidden" name="realTable" id="realTable" value="${realTable}"/>
			<input type="hidden" name="tableName" id="tableName" value="${tableName}"/>
			<fieldset>
				<legend>
					��ѯ����
				</legend>
				<table width="100%" class="tbstyle">
					<thead>
						<tr>
							<td align="left">
								ѧ�꣺
								<html:select  property="xn" style="width:110px" 
									styleId="xn" styleClass="select">
									<html:options collection="xnList" property="xn"
										labelProperty="xn" />
								</html:select>
								&nbsp;&nbsp;
								<bean:message key="lable.xsgzyxpzxy" />��
								<html:select property="xydm" style="width:200px" 
								styleClass="select" styleId="xy" onchange="initZyList();initBjList()">
									<html:option value=""></html:option>
									<html:options collection="xyList" property="xydm"
										labelProperty="xymc" />
								</html:select>
							</td>
							<td width="10" rowspan="2" align="center" valign="middle">
								<input type="hidden" name="go" value="a" />
								<button class="button2" style="height:40px" id="search_go"
									onclick="allNotEmpThenGo('bjbklqry.do')">
									��ѯ
								</button>
							</td>
						</tr>
						<tr>
							<td align="left" nowrap>
								רҵ��
								<html:select property="zydm" style="width:200px" styleId="zy"
								styleClass="select" onchange="initBjList()">
									<html:option value=""></html:option>
									<html:options collection="zyList" property="zydm" 
									labelProperty="zymc"/>
								</html:select>
								&nbsp;&nbsp;�༶��
								<html:select property="bjdm" style="200px" styleId="bj"
								styleClass="select">
									<html:option value=""></html:option>
									<html:options collection="bjList" property="bjdm" labelProperty="bjmc"/>
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
						<font color="blue">��ʾ����ɫΪ�༶ƽ�������ʳ���15%��˫��һ�п��Բ鿴��ϸ��Ϣ��������ͷ��������</font>
					</legend>
					<table width="100%" id="rsTable" class="tbstyle">
						<thead>
							<tr align="center" style="cursor:hand">
								<td nowrap>
									<input type="checkbox" id="cb" name="cb" onclick="selectAll()" />
								</td>
								<logic:iterate id="tit" name="topTr" offset="2">
									<td id="<bean:write name="tit" property="en"/>"
										onclick="tableSort(this)" nowrap>
										<bean:write name="tit" property="cn" />
									</td>
								</logic:iterate>
							</tr>
						</thead>
						<logic:iterate name="rs" id="s">
							<tr onclick="rowOnClick(this)"
								style="cursor:hand;" align="center"
								ondblclick="modidata('bjbklmodi.do?pkValue=','550','400')"
								bgcolor="<logic:iterate id="v" name="s" offset="1" length="1"><bean:write name="v"/></logic:iterate>">
								<td>
									<input type="hidden" id="pkValue" name="pkValue" value="<logic:iterate id="v" name="s" offset="0" length="1"><bean:write name="v"/></logic:iterate>"/>
									<input type="checkbox" id="cbv" name="cbv" value="<logic:iterate id="v" name="s" offset="0" length="1"><bean:write name="v"/></logic:iterate>" />
								</td>							
								<logic:iterate id="v" name="s" offset="2">
									<td>
										<bean:write name="v" />
									</td>
								</logic:iterate>
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
							<button class="button2" id="btn_save"
								onclick="showTopWin('bjbkladd.do','550','400')"
								style="width:80px">
								����
							</button>
							&nbsp;&nbsp;&nbsp;
							<button class="button2" id="btn_modi"
								onclick="modidata('bjbklmodi.do?pkValue=','550','400')"
								style="width:80px">
								�޸�
							</button>
							&nbsp;&nbsp;&nbsp;
							<button class="button2" id="btn_del"
								onclick="delshjxj('bjbkldel.do')"
								style="width:80px">
								����ɾ��
							</button>
							&nbsp;&nbsp;&nbsp;
							<button class="button2" id="btn_imp"
								onclick="impAndChkData();"
								style="width:80px">
								��������
							</button>
							&nbsp;&nbsp;&nbsp;
							<button class="button2" id="btn_exp"
								onclick="dataExport()"
								style="width:80px">
								��������
							</button>
						</div>
					</center>
				</logic:equal>
			<div id="tmpdiv"></div>
		</html:form>
		<script language="javascript">
			document.getElementById("btn").style.pixelTop = document.body.clientHeight - 35;
			document.getElementById("btn").style.width = "96%";
			window.setInterval("initBTNTool('btn')",1);
		</script>
		<logic:notEmpty name="result">
			<logic:equal value="view" name="result">
				<script>
					document.getElementById('search_go').click();
				</script>
			</logic:equal>
		</logic:notEmpty>	
	</body>
</html>
