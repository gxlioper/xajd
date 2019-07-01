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
		<meta http-equiv="Cache-Control"
			http-equiv="no-cache, must-revalidate" />
		<meta http-equiv="Expires" http-equiv="0" />
		<meta name="Copyright" content="������� zfsoft" />
	</head>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<base target="_self">
	<link id="csssDate" rel="stylesheet" rev="stylesheet"
		href="js/calendar.css" type="text/css" media="all" />
	<script language="javascript" src="js/function.js"></script>
	<script type="text/javascript" src="js/calendar.js"></script>
	<script type="text/javascript" src="js/calendar-zh.js"></script>
	<script type="text/javascript" src="js/calendar-setup.js"></script>
	<script type='text/javascript' src='/xgxt/dwr/interface/GetListData.js'></script>
	<script type='text/javascript' src='/xgxt/dwr/interface/getStuDetails.js'></script>
	<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
	<script type='text/javascript' src='dwr/util.js'></script>
	<script language="javascript" src="js/AjaxFunction.js"></script>
	<script language="javascript" src="js/qgzxFunction.js"></script>
	<%
	response.setHeader("Pragma","No-cache");
	response.setHeader("Cache-Control","no-cache");
	response.setDateHeader("Expires", 0);
	%>
	<body onload="checkWinType();dataManLoad();">	
		<html:form action="/gygl_gzcj_zsjl" method="post">
			<div class="title">
				<div class="title_img" id="title_m">
					<span id="tipFollow"></span>
				</div>
			</div>
			<logic:empty name="rs">
				<br />
				<br />
				<p align="center">
					�д�������
				</p>
			</logic:empty>
			<logic:notEmpty name="rs">
				<input type="hidden" id="doType" name="doType"
					value="<bean:write name="doType" scope="request"/>" />
				<input type="hidden" id="readonlyEle" name="readonlyEle" value="" />
				<input type="hidden" id="disableEle" name="disableEle" value="" />
				<input type="hidden" id="getStuInfo" name="getStuInfo"value="xm-xb-nj-xymc-zymc-bjmc" />
				<input type="hidden" id="url" name="url" value="/gygl_gzcj_zsjl.do" />
				<input type="hidden" id="ssbh" name="ssbh"
					value="<bean:write name="rs" property="ssbh" scope="request"/>" />
				<input type="hidden" id="pkValue" name="pkValue"
					value="<bean:write name="pkValue" scope="request"/>" />
				<table width="100%" class="tbstyle">
					<thead>
						<tr>
							<td colspan="4" align="center">
								ס�޼���
							</td>
						</tr>
					</thead>					
					<tr>
						<td align="right">
							<font color="red">*</font>ѧ�ţ�
						</td>
						<td align="left">
							<html:text name='rs' property="xh" readonly="readonly" onblur="dctStuXh()"
								styleId="xh" onkeypress="autoFillStuInfo(event.keyCode,this)" />
							<button onclick="showTopWin('/xgxt/stu_info.do',750,550);"
								class="btn_01" id="buttonFindStu" style="display:none" >
								ѡ��
							</button>
						</td>
						<td align="center" rowspan="4">
							��<p>Ƭ</p>
						</td>
						<td align="left" rowspan="4">
							<img border="0" style="height:100px;width:90px;" src="/xgxt/pictures/<bean:write name="rs" property="xh" />.jpg">
						</td>
					</tr>					
					<tr>
						<td align="right">
							������
						</td>
						<td align="left">
							<bean:write name="rs" property="xm" scope="request"/>
						</td>										
					</tr>
					<tr>
						<td align="right">
							���֤�ţ�
						</td>
						<td align="left">
							<bean:write name="rs" property="sfzh" scope="request"/>
						</td>			
					</tr>					
					<tr>
						<td align="right">
							�������ڣ�
						</td>
						<td align="left">
							<bean:write name="rs" property="csrq" scope="request"/>
						</td>		
					</tr>
					<tr>
						<td align="right">
							�Ա�
						</td>
						<td align="left">
							<bean:write name="rs" property="xb" scope="request"/>
						</td>
						<td align="right">
							¥����
						</td>
						<td align="left">
							<bean:write name="rs" property="ldmc" scope="request"/>
						</td>		
					</tr>
					<tr>
						<td align="right">
							�꼶��
						</td>
						<td align="left">
							<bean:write name="rs" property="nj" scope="request"/>
						</td>
						<td align="right">
							���Һţ�
						</td>
						<td align="left">
							<bean:write name="rs" property="qsh" scope="request"/>
						</td>	
					</tr>
					<tr>
						<td align="right">
							<bean:message key="lable.xsgzyxpzxy" />:
						</td>
						<td align="left">
							<bean:write name="rs" property="xymc" scope="request"/>
						</td>
						<td align="right">
							��λ�ţ�
						</td>
						<td align="left">
							<bean:write name="rs" property="cwh" scope="request"/>
						</td>
					</tr>
					<tr>
						<td align="right">
							רҵ:
						</td>
						<td align="left">
							<bean:write name="rs" property="zymc" scope="request"/>
						</td>
						<td align="right">
							<font color="red">*</font>ʱ�䣺
						</td>
						<td align="left">
							<logic:notEqual value="add" name="doType">
								<html:text name="rs" property="wjsj" value="${rs.wjsj}" readonly="true"></html:text>
							</logic:notEqual>							
							<logic:equal value="add" name="doType">
								<html:text name="rs" property="wjsj" readonly="true" onblur="dateFormatChg(this)"
									onclick="return showCalendar('wjsj','y-mm-dd');" style="cursor:hand "/>
							</logic:equal>
						</td>
					</tr>
					<tr>
						<td align="right">
							�༶:
						</td>
						<td align="left">
							<bean:write name="rs" property="bjmc" scope="request"/>
						</td>
						<td align="right">
							�������
						</td>
						<td align="left">
							<html:select name="rs" property="wjlbdm" style="width:150px" styleId="wjlb">
								<html:options collection="wjlbList" property="wjlbdm" labelProperty="wjlbmc" />
							</html:select>
						</td>
					</tr>
					<tr>
						<td align="right">
							<font color="red">*</font>���ɣ�									
						</td>
						<td align="left">
							<html:select name="rs" property="wjsy" style="width:150px" styleId="wjsy">
								<html:options collection="wjsyList" property="wjsydm" labelProperty="wjsymc" />
							</html:select>
						</td>
						<td align="right">
							��¼�ˣ�
						</td>
						<td align="left">
							<input type="text" name="jlr" id="jlr" readonly="readonly" value="<bean:write name="rs" property="jlr" />"/>
						</td>	
					</tr>
					<tr>
						<td align="right">
							����˵����									
						</td>
						<td align="left" colspan="3">
							<textarea rows="4" cols="78" name="sysm" id="sysm" type="_moz">${rs.sysm}</textarea>			
						</td>
					</tr>
					<tr>
						<td align="right">
							��������								
						</td>
						<td align="left" colspan="3">
							<html:text name='rs' property="cljg" styleId="cljg" />
						</td>					
					</tr>
					<tr>
						<td align="right">
							��ע��									
						</td>
						<td align="left" colspan="3">
							<textarea rows="4" cols="78" name="bz" id="bz" type="_moz">${rs.bz}</textarea>			
						</td>					
					</tr>
				</table>
				<table width="100%" class="tbstyle" style="d">
					<thead>
						<tr onclick="if(document.getElementById('xswjxx').style.display == 'none'){document.getElementById('xswjxx').style.display = ''}else{document.getElementById('xswjxx').style.display = 'none'};">
							<td colspan="5" align="center">
								ѧ��Υ����Ϣ
							</td>
						</tr>
					</thead>
					<tbody id="xswjxx" style="display: none">
						<tr bgcolor="#D0E0EE">
							<td align="center">Υ��ʱ��</td>
							<td align="center">Υ�����</td>
							<td align="center">Υ������</td>
							<td align="center">������</td>
							<td align="center">��¼��</td>
						</tr>
						<logic:iterate id="xswjxx" name="wjxxList">
							<tr>
								<td><bean:write name="xswjxx" property="wjsj"/></td>
								<td><bean:write name="xswjxx" property="wjlbmc"/></td>
								<td><bean:write name="xswjxx" property="wjsymc"/></td>
								<td><bean:write name="xswjxx" property="cljg"/></td>
								<td><bean:write name="xswjxx" property="jlr"/></td>
							</tr>
						</logic:iterate>
					</tbody>
				</table>
				<div class="buttontool" align="center">
					<button class="button2" onclick="dataCanModi(true)" style="width:80px" id="buttonModi">
						�� ��
					</button>
					<button class="button2" onclick="qgzxSave('/xgxt/zsjlSave.do?pkValue=' + document.getElementById('pkValue').value,'xh-wjsj-wjsy')" style="width:80px" id="buttonSave">
						�� ��
					</button>
					&nbsp;&nbsp;&nbsp;&nbsp;
					<button class="button2" onclick="Close();return false;" style="width:80px" id="buttonClose">
						�� ��
					</button>
				</div>
			</logic:notEmpty>
			<logic:equal value="view" name="result">
				<script>
					alert("��ѧ��δס��!");
				</script>
			</logic:equal>
			<logic:equal value="ok" name="result">
				<script>
					alert("�����ɹ�!");
					Close();
					dialogArgumentsQueryChick();
				</script>
			</logic:equal>
			<logic:equal value="no" name="result">
				<script>
					alert("����ʧ��!");
				</script>
			</logic:equal>	
		</html:form>
  </body>
</html>
