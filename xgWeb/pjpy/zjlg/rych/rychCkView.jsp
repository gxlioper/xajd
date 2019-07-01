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
		<style type="text/css">
<!--
.style1 {color: #FF0000}
-->
</style>
	</head>
	<%
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
	%>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<base target="_self">
	<script type="text/javascript" src="js/BatAlert.js"></script>
	<script language="javascript" src="js/function.js"></script>
	<script language="javascript" src="js/pjpyFunction.js"></script>
	<body onload="pjpy_initCheck()">
		<html:form action="/zjlgPjpy" method="post">
			<div class="title">
				<div class="title_img" id="title_m">
					��ǰλ�ã��������� - ��� - �����ƺ���� - ��Ϣ�鿴
				</div>
			</div>

			<table width="99%" id="rsT" class="tbstyle">
				<thead>
					<div align="right">
						<logic:equal value="up" name="view">
							<input  id="up" value="��һ��" disabled="true">
						</logic:equal>
						<logic:notEqual value="up" name="view">
							<input  id="up" value="��һ��"
								onclick="showTips('ˢ�������У����Ժ�...');pjpy_ChangeRecord('up','/xgxt/zjlgPjpy.do?method=rychCkView');">
						</logic:notEqual>
						&nbsp; &nbsp;
						<logic:equal value="next" name="view">
							<input  id="next" value="��һ��" disabled="true">
						</logic:equal>
						<logic:notEqual value="next" name="view">
							<input  id="next" value="��һ��"
								onclick="showTips('ˢ�������У����Ժ�...');pjpy_ChangeRecord('next','/xgxt/zjlgPjpy.do?method=rychCkView');">
						</logic:notEqual>
						&nbsp; &nbsp;&nbsp; &nbsp;
						<logic:equal value="true" name="sel">
							<input type="checkbox" id="selected" onclick="pjpy_Shot(this);"
								checked="true" />&nbsp;ѡ��
					    </logic:equal>
						<logic:notEqual value="true" name="sel">
							<input type="checkbox" id="selected" onclick="pjpy_Shot(this);" />&nbsp;ѡ��
					    </logic:notEqual>
					</div>
					<tr style="height:22px">
						<td colspan="4" align="center">
							<b>ѧ�������ƺ���Ϣ</b>
						</td>
					</tr>
				</thead>
				<tr style="height:22px">
					<td align="right" style="width: 10%">
						ѧ�ţ�
					</td>
					<td align="left">
						<bean:write name='rs' property="xh" />
					</td>
					<td align="right" style="width: 10%">
						ѧ�꣺
					</td>
					<td align="left" style="width: 40%">
						<html:select name="rsOther" property="xn" disabled="true">
							<html:options collection="xnList" property="xn"
								labelProperty="xn" />
						</html:select>
					</td>
				</tr>
				<tr style="height:22px">
					<td align="right">
						������
					</td>
					<td align="left">
						<bean:write name='rs' property="xm" />
					</td>
					<td align="right">
						ѧ�ڣ�
					</td>
					<td align="left" style="width: 40%">
						<html:select name="rsOther" property="xq" disabled="true">
							<html:options collection="xqList" property="xqdm"
								labelProperty="xqmc" />
						</html:select>
					</td>
				</tr>
				<tr style="height:22px">
					<td align="right">
						�Ա�
					</td>
					<td align="left">
						<bean:write name='rs' property="xb" />
					</td>
					<td align="right">
						<font color="red">*</font>�����ƺţ�
					</td>
					<td align="left">
						<bean:write name='rsOther' property="rychmc" />
					</td>
				</tr>
				<tr style="height:22px">
					<td align="right">
						�꼶��
					</td>
					<td align="left">
						<bean:write name='rs' property="nj" />
					</td>
					<td align="right">
						��Դ�أ�
					</td>
					<td align="left">
						<bean:write name='rs' property="syd" />
					</td>
				</tr>
				<tr style="height:22px">
					<td align="right">
						<bean:message key="lable.xsgzyxpzxy" />��
					</td>
					<td align="left">
						<bean:write name='rs' property="xymc" />
					</td>
					<td align="right">
						��ϵ�绰��
					</td>
					<td align="left">
						<bean:write name='rs' property="lxdh" />
					</td>
				</tr>
				<tr style="height:22px">
					<td align="right">
						רҵ��
					</td>
					<td align="left">
						<bean:write name='rs' property="zymc" />
					</td>
					<td align="right">
						����ְ��
					</td>
					<td align="left">
						<bean:write name='rs' property="zw" />
					</td>
				</tr>
				<tr style="height:22px">
					<td align="right">
						�༶��
					</td>
					<td align="left">
						<bean:write name='rs' property="bjmc" />
					</td>
					<td align="right">
						������ò��
					</td>
					<td align="left">
						${rs.zzmmmc }
					</td>
				</tr>
				<tr style="height:22px">
					<td align="right">
						��ͥ��ַ��
					</td>
					<td align="left">
						<bean:write name='rs' property="jtdz" />
					</td>
					<td align="right">
						�س���
					</td>
					<td align="left">
						<bean:write name='rs' property="tc" />
					</td>
				</tr>
				<tr style="height:22px">
					<td align="right">
						�۲�ɼ���
					</td>
					<td align="left">
						<bean:write name='rsOther' property="zhcj" />
					</td>
					<td align="right">
						�۲�������
					</td>
					<td align="left">
						<bean:write name='rsOther' property="zcbjpm" />
					</td>
					
				</tr>
				<tr>
				<td align="right">
						������
					</td>
					<td align="left">
						<bean:write name='rsOther' property="dycj" />
					</td>
				<td align="right">
						����������
					</td>
					<td align="left">
						<bean:write name='rsOther' property="dypm" />
					</td>	
				
				</tr>
					<tr>
				<td align="right">
						������
					</td>
					<td align="left">
						<bean:write name='rsOther' property="zycj" />
					</td>
				    <td align="right">
						����������
					</td>
					<td align="left">
						<bean:write name='rsOther' property="zypm" />
					</td>
				
				</tr>
				<tr style="height:22px">
					
					<td align="right">
						������
					</td>
					<td align="left">
						<bean:write name='rsOther' property="tycj" />
					</td>
				    <td align="right">
						Ӣ��ȼ��ɼ���
					</td>
					<td align="left">
						<bean:write name='rsOther' property="yydjcj" />
					</td>
				</tr>
					<tr style="height:22px">
					<td align="right">
						������ɼ���
					</td>
					<td align="left">
						<bean:write name='rsOther' property="jsjdjcy" />
					</td>
					  <td align="right">
					  ������γ�����
					</td>
					<td align="left">
					<bean:write name='rsOther' property="bjg" />
					</td>
				
				</tr>
				<tr style="height:22px">
					<td align="right">
						���˼�飺
					</td>
					<td colspan="3" align="left">
						<bean:write name='rsOther' property="bz" />
					</td>
				</tr>
				<tr style="height:22px">
				<td colspan="4" align="left">
				<fieldset>
				<legend>
					�������ƺ���������
				</legend>
				<table width="99%" align="center" class="tbstyle">				
				<tr style="height:22px">
					<td align="right" style="width: 15%">
						��<bean:message key="lable.xsgzyxpzxy" />���ޣ�
					</td>
					<td align="left">
						<bean:write name='xycprs' scope="request"/>
					</td>
					<td align="right"  style="width: 15%">
						רҵ���ޣ�
					</td>
					<td align="left" style="width: 40%">
						<bean:write name='zycprs'  scope="request"/>
					</td>
				</tr>
								<tr style="height:22px">
					<td align="right" >
						�༶���ޣ�
					</td>
					<td align="left">
						<bean:write name='bjcprs' scope="request"/>
					</td>
					<td align="right">
						
					</td>
					<td align="left">
						
					</td>
				</tr>
				</table>
				</fieldset>
				</td>
				</tr>
								<tr style="height:22px">
				<td colspan="4" align="left">
				<fieldset>
				<legend>
					�������ƺ���ͨ������
				</legend>
				<table width="99%" align="center" class="tbstyle">				
				<tr style="height:22px">
					<td align="right" style="width: 15%">
						��<bean:message key="lable.xsgzyxpzxy" />ͨ��������
					</td>
					<td align="left">
						<bean:write name='xyPassRs' scope="request"/>
					</td>
					<td align="right"  style="width: 15%">
						רҵͨ��������
					</td>
					<td align="left" style="width: 40%">
						<bean:write name='zyPassRs'  scope="request"/>
					</td>
				</tr>
								<tr style="height:22px">
					<td align="right" >
						�༶ͨ��������
					</td>
					<td align="left">
						<bean:write name='bjPassRs' scope="request"/>
					</td>
					<td align="right">
						ȫУͨ��������
					</td>
					<td align="left">
						<bean:write name='allPassRs' scope="request"/>
					</td>
				</tr>
				</table>
				</fieldset>
				</td>
				</tr>
				<tr>
					<td colspan="4">
						<table width="99%" align="center" class="tbstyle">
							<thead>
								<tr>
									<td align="center" style="cursor:hand">
										��������ѧ��
									</td>
								</tr>
							</thead>
							<tr>
								<td>
									<logic:empty name="rhJxjList">
										<p>
										<div align="center">
											���޻�ѧ���¼!
										</div>
									</logic:empty>
									<logic:notEmpty name="rhJxjList">
										<fieldset>
											<legend>
												��ѧ���б��б�
											</legend>
											<table width="100%" align="center" class="tbstyle" id="tTb">
												<thead>
													<tr>
														<td align="center" style="cursor:hand" style="width:50px">
															ѧ��
														</td>
														<td align="center" style="cursor:hand">
															��ѧ��
														</td>
													</tr>
												</thead>
												<logic:iterate name="rhJxjList" id="s">
													<tr>
														<td>
															<bean:write name="s" property="xn" />
														</td>
														<td>
															<bean:write name="s" property="jxjmc" />
														</td>
													</tr>
												</logic:iterate>
											</table>
										</fieldset>
									</logic:notEmpty>
								</td>
							</tr>
						</table>

					</td>
				</tr>
			</table>
			<div class="buttontool" align="center">

				<button type="button" class="button2" id="buttonClose" onclick="Close();return false;">
					�� ��
				</button>
			</div>
		</html:form>
	</body>
</html>
