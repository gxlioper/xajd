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
	<body onload="getNowDate();">
		<script language="javascript" src="js/AjaxFunction.js"></script>
		<script language="javascript" src="js/function.js"></script>
		<script language="javascript" src="js/yxglFunction.js"></script>
		<script language="javascript" src="js/sharedFunction.js"></script>
		<script type='text/javascript' src='/xgxt/dwr/interface/yxglFun.js'></script>
		<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
		<script type='text/javascript' src='/xgxt/dwr/util.js'></script>
		<html:form action="yxgl_xsgl.do" method="post">
			<div class="title">
				<div class="title_img" id="title_m">
					��ǰ����λ�ã�ӭ�¹��� �� �������� �� �����ʲ�ѯ
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<span id="newdate" style="color:#BB44BB" ></span>
				</div>
			</div>
				<logic:empty name="rs">
					<p align="center">
						δ�ҵ��κμ�¼��
					</p>
				</logic:empty>
				<logic:notEmpty name="rs">
					<fieldset>
					<%-- �����ʲ�ѯ  ��Ժ��Ϲ�ҵ��ѧ--%>
					<logic:present name="percentOfBdSearch">
						<!-- ȫУ��Χͳ�� -->
						<table width="100%" id="rsTable" class="tbstyle">
							<thead>
								<tr align="center" style="cursor:hand">
									<td onclick="tableSort(this)">ͳ�����в���</td>
									<td onclick="tableSort(this)">Ӧ��������</td>
									<td onclick="tableSort(this)">ʵ��������</td>
									<td onclick="tableSort(this)">������(%)</td>
									<td  style="width:25%">��ӡԤ��</td>
								</tr>
							</thead>
								<tr style="cursor:hand" onclick="rowOnClick(this)">
										<td>ȫУͳ��</td>
										<td><bean:write name="rs" property="totalNum"/></td>
										<td><bean:write name="rs" property="OkNum"/></td>
										<td><bean:write name="rs" property="percentBd"/></td>
										<td>
										<select name="tag" id="tag" style="width:110px">
											<option value="">--��ѡ��--</option>
											<option value="ybd">�ѱ���ѧ������</option>
											<option value="wbd">δ����ѧ������</option>
										</select>&nbsp;&nbsp;
										<a href="javascript:getTotalStuData('print');" style="font-weight:bold;">��ӡ</a>&nbsp;&nbsp;&nbsp;
										<a href="javascript:getTotalStuData('exp');" style="font-weight:bold;">����</a>
										</td>
								</tr>
						</table>
						<br/>
						
						<!-- ��������ͳ�� -->
						<table width="100%" id="rsTable" class="tbstyle">
							<thead>
								<tr align="center" style="cursor:hand">
									<td onclick="tableSort(this)">��������</td>
									<td onclick="tableSort(this)">Ӧ��������</td>
									<td onclick="tableSort(this)">ʵ��������</td>
									<td onclick="tableSort(this)">������(%)</td>
									<td onclick="tableSort(this)" style="width:25%">��ӡԤ��</td>
								</tr>
							</thead>
							<logic:iterate name="rs2" id="s">	
								<tr style="cursor:hand" onclick="rowOnClick(this)">
									<td>
									<input type="hidden" name="xydm" value="<bean:write name="s" property="xydm"/>"/>		
									<bean:write name="s" property="xymc"/></td>
									<td><bean:write name="s" property="totalNum"/></td>
									<td><bean:write name="s" property="OkNum"/></td>
									<td><bean:write name="s" property="percentBd"/></td>
									<td>
									<select name="tag" id="tag" style="width:110px">
										<option value="">--��ѡ��--</option>
										<option value="ybd">�ѱ���ѧ������</option>
										<option value="wbd">δ����ѧ������</option>
									</select>&nbsp;&nbsp;
									<a href="javascript:getStuDataByXy('print');" style="font-weight:bold;">��ӡ</a>&nbsp;&nbsp;&nbsp;
									<a href="javascript:getStuDataByXy('exp');" style="font-weight:bold;">����</a>
									</td>
								</tr>
							</logic:iterate>	
						</table>
						<br/>
					</logic:present>
					</fieldset>
				</logic:notEmpty>	
		</html:form>
	</body>
</html>


