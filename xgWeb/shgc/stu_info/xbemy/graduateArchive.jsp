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
<html:html>
<head>
	<title><bean:message key="lable.title" /></title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<meta http-equiv="Copyright" content="������� zfsoft">
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<script type="text/javascript" src="js/function.js"></script>
	<script type="text/javascript" src="js/stuinfoFunction.js"></script>
	<script type="text/javascript" src="js/jsFunction.js"></script>
	<script type='text/javascript' src='/xgxt/dwr/interface/GetListData.js'></script>
	<script type="text/javascript" src="js/AjaxFunction.js"></script>
	<script>
		
	</script>

</head>
<%
		response.setHeader("Pragma", "no-cache");
		response.setHeader("Cache-Control", "No-cache");
		response.setDateHeader("Expires", 0);
%>
<base target="_self">
<body onload="check_user();">
	<html:form action="/xbemyArchive">
		<input type="hidden" value="<bean:write name="userType"/>" id="userType" name="userType" />
		<input type="hidden" name="realTable" value="xbemy_bydab" />
		<input type="hidden" name="tableName" value="view_xbemy_bydab" />
		<input type="hidden" name="xyV" value="" />
		<input type="hidden" name="zyV" value="" />
		<input type="hidden" name="bjV" value="" />
		<div class="title">
			<div class="title_img" id="title_m">
				��ǰ����λ�ã�ѧ����Ϣ - �������� - ��ҵѧ������
			</div>
		</div>
		<div class="rightcontent">
			<logic:notEqual value="student" name="userOnLine" scope="session">
				<fieldset>
					<legend>
						�� ѯ
					</legend>
					<table class="tbstyle" width="100%">
						<thead>
							<tr>
								<td align="left">
									�꼶��
									<html:select property="nj"  styleId="nj"
										onchange="initZyList();initBjList();">
										<html:option value=""></html:option>
										<html:options collection="njList" property="nj"
											labelProperty="nj" />
										<bean:write name="njList" />
									</html:select>
									&nbsp;&nbsp;ѧ�ţ�
									<html:text property="xh" style="width:100px" maxlength="20"></html:text>
									&nbsp;&nbsp;������
									<html:text property="xm" style="width:100px" maxlength="20"></html:text>
									&nbsp;&nbsp;���������
									<html:select property="ddqkdm">
										<html:option value=""></html:option>
										<html:options collection="ddqkList" property="dddm" labelProperty="ddmc"/>
									</html:select>
									
									
									
								</td>
								<td width="10" rowspan="2" align="center" valign="middle">
									<input type="hidden" name="go" value="a" />
									<button class="button2" style="height:40px" id="search_go"
										onclick="allNotEmpThenGo('xbemyArchive.do?method=graduateArchive')">
										��ѯ
									</button>
								</td>
							</tr>
							<tr>
								<td align="left" nowrap>
									<bean:message key="lable.xsgzyxpzxy" />��
									<html:select property="xydm" style="width:200px" styleId="xy"
										onchange="initZyList();initBjList();">
										<html:option value=""></html:option>
										<html:options collection="xyList" property="xydm"
											labelProperty="xymc" />
									</html:select>
									&nbsp;&nbsp;רҵ��
									<html:select property="zydm"  style="width:200px" styleId="zy"
										onchange="initBjList();">
										<html:option value=""></html:option>
										<html:options collection="zyList" property="zydm"
											labelProperty="zymc" />
									</html:select>
									&nbsp;&nbsp;�༶��
									<html:select property="bjdm" style="width:200px" styleId="bj">
										<html:option value=""></html:option>
										<html:options collection="bjList" property="bjdm"
											labelProperty="bjmc" />
									</html:select>
									
								</td>
							</tr>
						</thead>
					</table>
				</fieldset>
			</logic:notEqual>
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
						<font color="blue">��ʾ��˫��һ�п��Բ鿴��ϸ��Ϣ��������ͷ��������</font>
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
								ondblclick="showTopWin('xbemyArchive.do?method=showGraduateArchive&xh='+curr_row.cells[0].innerText)">
								<td>
									<logic:iterate id="v" name="s" offset="0" length="1">
										<input type="hidden" value="<bean:write name="v"/>" />
									</logic:iterate>
									<logic:iterate id="v" name="s" offset="0" length="1">
										<bean:write name="v" />
									</logic:iterate>
								</td>
								<logic:iterate id="v" name="s" offset="1">
									<td nowrap>
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
				<div class="buttontool" id="btn"
					style="position: absolute;left:1%;top:100px" width="100%">
					<button class="button2"
						onclick="showTopWin('xbemyArchive.do?method=showGraduateArchive',600,480)"
						style="width:80px">
						�� ��
					</button>
					&nbsp;&nbsp;&nbsp;&nbsp;
					<button class="button2"
						onclick="javascript:if(curr_row==null){alert('��ѡ��Ҫ�޸ĵļ�¼!');return false;}else{showTopWin('xbemyArchive.do?method=showGraduateArchive&xh='+curr_row.cells[0].innerText)}"
						style="width:80px">
						�� ��
					</button>
					&nbsp;&nbsp;&nbsp;&nbsp;
					<button class="button2"
						onclick="javascript:if(curr_row==null){alert('��ѡ��Ҫɾ���ļ�¼!');return false;}else{if(confirm('��ȷ��ɾ��������¼��'))refreshForm('xbemyArchive.do?method=graduateArchive&doType=del&xh='+curr_row.cells[0].innerText)}"
						style="width:80px">
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
						onclick="dataExport()"
						style="width:80px">
						��������
					</button>
					&nbsp;&nbsp;&nbsp;&nbsp;
					<button class="button2" onclick="expTab('rsTable','��ҵ������ת�������','')">
						��ӡ�б�
					</button>
				</div>
			</logic:equal>
			<logic:notEmpty name="result" scope="request">
				<logic:equal value="true" name="result" scope="request">
					<script>
					alert("�����ɹ���");
					Close();					
					document.getElementById('search_go').click();		
				</script>
				</logic:equal>
				<logic:equal value="false" name="result" scope="request">
					<script>
					alert("����ʧ�ܣ�");
				</script>
				</logic:equal>
			</logic:notEmpty>
		</div>
	</html:form>
	<logic:equal value="yes" name="writeAble">
		<script language="javascript">
			document.getElementById("btn").style.pixelTop = document.body.clientHeight - 35;
			document.getElementById("btn").style.width = "96%";
			window.setInterval("initBTNTool('btn')",1);
	</script>
	</logic:equal>
</html:html>
