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
	<script language="javascript">
		//begin ���ΰ 2009/3/30
	function chkInputJx(obj){
		var num = obj.value;
		if(num != ""){
		if(num.match(/^\d+\.{0,1}\d{0,3}$/)==null){
				alert("����Ϊ���֣�");
				document.getElementById("isErr").value = "1";
				return false;
			}
		if(num>100){
				alert("��ѵ�ɼ����ܴ���100��");
				document.getElementById("isErr").value = "1";
				return false;
			}
		}
		document.getElementById("isErr").value = "0";	
		return true;
	}
	function saveCj(){
		showTips('���������У���ȴ�......');
		if(document.getElementById("isErr").value == "1"){
			alert("��������ȷ�ĳɼ���");
		}else{
		cjSave('ArmyAchievementSave.do');
		}
	}
</script>
	<script language="javascript" src="js/function.js"></script>
	<script type="text/javascript" src="js/calendar.js"></script>
	<script type="text/javascript" src="js/calendar-zh.js"></script>
	<script type="text/javascript" src="js/calendar-setup.js"></script>
	<script language="javascript" src="js/jsFunction.js"></script>
	<script language="javascript" src="js/jxgl.js"></script>
	<script type="text/javascript" src="js/jxglFunction.js"></script>
		<script type='text/javascript' src='/xgxt/dwr/interface/GetListData.js'></script>
		<script type="text/javascript" src="/xgxt/dwr/interface/getJxLdjzList.js"></script>
		<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
		<script type='text/javascript' src='dwr/util.js'></script>
		<script language="javascript" src="js/AjaxFunction.js"></script>
	<body  <logic:notPresent name="cjlrType2"> onload="getJxcjbl();"</logic:notPresent> >
		<html:form action="/ArmyIntoAchievement" method="post">
			<div class="title">
				<div class="title_img" id="title_m">
					��ǰ����λ�ã���ѵ���� - ��ѵ���� - ��ѵ�ɼ�¼��
				</div>
			</div>
			<input type="hidden" id="pk" name="pk" value="" />
			<input type="hidden" name="ldbhV" id="ldbhV" />
			<input type="hidden" id="isErr" name="isErr" value="0" />
			<input type="hidden" id="realTable" name="realTable" value="${realTable }" />
			<input type="hidden" id="tableName" name="tableName" value="${tableName }" />
			<input type="hidden" id="title" name="title" value="${title }" />
			<input type="hidden" id="writeAble" name="writeAble" value="${writeAble }" />
			<input type="hidden" name="xyV" id="xyV" value="" />
			<input type="hidden" name="zyV" id="zyV" value="" />
			<input type="hidden" name="bjV" id="bjV" value="" />
			<input type="hidden" name="njV" id="njV" value="" />
			<input type="hidden" id="isFdy" name="isFdy" value="<bean:write name="isFdy" scope="session"/>"/>
			<input type="hidden" id="userName" name="userName" value="<bean:write name="userName" scope="session"/>"/>
			<input type="hidden" id="isBzr" name="isBzr" value="<bean:write name="bzrQx" scope="session"/>" />
			<input type="hidden" id="userType" name="userType" value="${userType }" />
			<fieldset>
				<legend>
					��ѯ����
				</legend>
				<table width="100%" class="tbstyle">
					<thead>
						<tr>
							<td align="left">
								�꼶��
								<html:select property="nj" style="width:170px" onchange="initZyList();initBjList()">
									<html:option value="" />
									<html:options collection="njList" property="nj" labelProperty="nj" />
								</html:select>
								&nbsp;��ȣ�
								<html:select property="nd" style="width:90px" onchange="">
									<html:option value=""></html:option>
									<html:options collection="xnList" property="nd"
										labelProperty="nd" />
								</html:select>
								&nbsp;<bean:message key="lable.xsgzyxpzxy" />��
								<html:select property="xydm" style="width:170px" styleId="xy" onchange="initZyList();initBjList()">
									<html:option value="" />
									<html:options collection="xyList" property="xydm" labelProperty="xymc" />
								</html:select>
							</td>
							<td rowspan="4" width="10" align="center" valign="middle">
								<input type="hidden" name="go" value="a" />
								<button type="button" class="button2" id="search_go"
									onclick="allNotEmpThenGo('/xgxt/ArmyIntoAchievement.do')">
									�� ѯ
								</button>
							</td>
						</tr>
						<tr>
							<td>
								רҵ��
								<html:select property="zydm" style="width:170px" styleId="zy" onchange="initBjList()">
									<html:option value="" />
									<html:options collection="zyList" property="zydm" labelProperty="zymc" />
								</html:select>
								&nbsp;�༶��
								<html:select property="bjdm" style="width:170px" styleId="bj">
									<html:option value=""></html:option>
									<html:options collection="bjList" property="bjdm" labelProperty="bjmc" />
								</html:select>
								<logic:notPresent name="cjlrType2">
								&nbsp;�������ӣ�
								<html:select property="ldbh"  onchange="">
									<html:option value=""></html:option>
									<html:options collection="ldList" property="lddm" labelProperty="ldmc" />
								</html:select>
								</logic:notPresent>
								<logic:present name="cjlrType2">
								&nbsp;�ɼ�����:<font color="red">ѵ���ɼ�
								<html:text property="xlcjbl" styleId="xlcjbl" style="width:35px" maxlength="3" value="60" onkeyup="chkInput(this,event)"/>%
								&nbsp;���Գɼ�
								<html:text property="kscjbl" styleId="kscjbl" style="width:35px" maxlength="3" value="40" onkeyup="chkInput(this,event)"/>%</font>
								</logic:present>
							</td>
						</tr>
						<logic:notPresent name="cjlrType2">
						<tr>
							<td>
								ѧ�ţ�
								<html:text property="xh" style="width:120px" />
								&nbsp;&nbsp;&nbsp;&nbsp;������
								<html:text property="xm" style="width:100px" />
								&nbsp;&nbsp;�Ա�
								<html:select property="xb" style="width:90px"
									styleId="xb" onchange="">
									<html:option value=""></html:option>
									<html:option value="��">��</html:option>
									<html:option value="Ů">Ů</html:option>
								</html:select>
								<!-- begin ���ΰ 2009/3/30 -->
								<logic:present name="jxgl_zjcm">
								&nbsp;�ɼ�����:<font color="red">��ѵʵ��
								<html:text property="xlcjbl" styleId="xlcjbl" style="width:35px" maxlength="3" value="" onkeyup="chkInput(this,event)" disabled="true"/>%
								&nbsp;��ѵ����
								<html:text property="kscjbl" styleId="kscjbl" style="width:35px" maxlength="3" value="" onkeyup="chkInput(this,event)" disabled="true"/>%</font>
								</logic:present>
								<!-- end ���ΰ 2009/3/30 -->
							</td>
						</tr>
						</logic:notPresent>
						<logic:present name="cjlrType2">
						<tr>
							<td>
								&nbsp;��ѯ�ɼ���ֵΪ:
								<input type = "test" name = "cj" maxlength="3" style="width:35px"/>
								&nbsp;&nbsp;&nbsp;&nbsp;��ѯ�ɼ���ֵ��
								<input type = "test" maxlength="3" name = "qszz" style="width:35px" onkeyup="chkInput(this,event)"/>&nbsp;��&nbsp;<input type = "test" maxlength="3" name = "jszz" style="width:35px" onkeyup="chkInput(this,event)"/>֮��
							</td>
						</tr>
						</logic:present>
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
						<%-- <font color="blue">��ʾ��˫��һ�п��Բ鿴��ϸ��Ϣ��������ͷ��������</font> --%>
					</legend>
					<table width="99%" id="rsTable" class="tbstyle" align="center">
						<thead>
							<tr align="center" style="cursor:hand">
								<td align="center">ѧ��</td>
								<td align="center">����</td>
								<td align="center">���</td>
								<td align="center">�Ա�</td>
								<td align="center">�꼶</td>
								<td align="center">�༶</td>
								<logic:present name="cjlrType2">
								<td align="center" style="width: 3%">ѵ���ɼ�</td>
								<td align="center" style="width: 3%">���Գɼ�</td>
								</logic:present>
								<!-- begin ���ΰ 2009/3/30 -->
								<logic:present name="jxgl_zjcm">
								<td align="center" style="width: 3%">��ѵʵ��</td>
								<td align="center" style="width: 3%">��ѵ����</td>
								</logic:present>
								<!-- end ���ΰ 2009/3/30 -->
								<td align="center" style="width: 3%">�ɼ�</td>
							</tr>
						</thead>
						<tbody id="rsTables">
						<logic:iterate name="rs" id="s">
						     <tr>
								<td align="left"><bean:write name="s" property="xhArray"/><html:hidden name="s" property="xhArray"/></td>
								<td align="left"><bean:write name="s" property="xmArray"/><html:hidden name="s" property="xmArray"/></td>
								<td align="left"><bean:write name="s" property="ndArray"/><html:hidden name="s" property="ndArray"/></td>
								<td align="left"><bean:write name="s" property="xb"/></td>
								<td align="left"><bean:write name="s" property="nj"/></td>
								<td align="left"><bean:write name="s" property="bjmc"/></td>
								<logic:present name="cjlrType2">
								<td align="left"><html:text styleId="xlcjtext" name="s" property="xlcjArray" style="width: 50px" size="8" maxlength="5" onblur="ResultCount()"/></td>
								<td align="left"><html:text styleId="kscjtext" name="s" property="kscjArray" style="width: 50px" size="8" maxlength="5" onblur="ResultCount()"/></td>
								</logic:present>
								<!-- begin ���ΰ 2009/3/30 -->
								<logic:present name="jxgl_zjcm">
								<td align="left"><html:text styleId="xlcjtext" name="s" property="xlcjArray" style="width: 50px" size="8" maxlength="5" onblur="if(chkInputJx(this)){ResultCount();}"/></td>
								<td align="left"><html:text styleId="kscjtext" name="s" property="kscjArray" style="width: 50px" size="8" maxlength="5" onblur="if(chkInputJx(this)){ResultCount();}"/></td>
								</logic:present>
								<!-- end ���ΰ 2009/3/30 -->
								<logic:equal value="10690" name="xxdm" scope = "session">
								<td align="left"><html:select name="s" property="cjArray"  style="width: 50px">
									<html:option value=""></html:option>
									<html:option value="����">����</html:option>
									<html:option value="�ϸ�">�ϸ�</html:option>
									<html:option value="���ϸ�">���ϸ�</html:option>
									</html:select>
								</td>
								</logic:equal>
								<logic:notEqual value="10690" name="xxdm" scope = "session">
								<td align="left"><html:text styleId="text" name="s" property="cjArray" style="width: 50px" size="8" maxlength="5"/></td>
								</logic:notEqual>
							 </tr>
						</logic:iterate>
						</tbody>
					</table>
					<p>&nbsp;</p>
				</fieldset>
			</logic:notEmpty>
			<div id="toolTipLayer" style="position:absolute; visibility: hidden" />
			</div>
			<div class="buttontool" id="btn"
					style="position: absolute;left:1%;top:100px" width="100%">
					<logic:equal value="yes" name="writeAble">
					<logic:equal value="10690" name="xxdm" scope = "session">
					<button type="button" class="button2"
						onclick="refreshForm('ArmyAchievementSave.do');"
						style="width:80px">
						�� ��
					</button>
					</logic:equal>
					<!-- begin ���ΰ 2009/3/30 -->
					<logic:equal value="11647" name="xxdm" scope = "session">
					<button type="button" class="button2"
						onclick="saveCj();"
						style="width:80px">
						�� ��
					</button>
					</logic:equal>
					<!-- end ���ΰ 2009/3/30 -->
					<logic:notEqual value="11647" name="xxdm" scope = "session">
					<logic:notEqual value="10690" name="xxdm" scope = "session">
					<button type="button" class="button2"
						onclick="cjSave('ArmyAchievementSave.do');"
						style="width:80px">
						�� ��
					</button>
					</logic:notEqual>
					</logic:notEqual>
					&nbsp;&nbsp;&nbsp;&nbsp;
					</logic:equal>
					<button type="button" class="button2"
						onclick="impAndChkData();"
						style="width:80px">
						��������
					</button>
					&nbsp;&nbsp;&nbsp;&nbsp;
					<button type="button" class="button2" onclick="dataExport()" style="width:80px">
						��������
					</button>
			</div>
		</html:form>
		<logic:equal value="ok" name="result">
			<script>
				alert("����ɹ���");
				document.getElementById("search_go").click();
			</script>
		</logic:equal>
		<script type="text/javascript" src="js/bottomButton.js"></script>
	</body>
</html>
