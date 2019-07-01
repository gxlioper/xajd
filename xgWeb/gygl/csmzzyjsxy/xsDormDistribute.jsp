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
		<title><bean:message key="lable.title" /></title>
		<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
		<meta http-equiv="Content-Language" content="GBK" />
		<meta name="Copyright" content="������� zfsoft" />
	</head>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<script language="javascript">
      function hiddenField() {
	     i = document.getElementsByTagName("select").length;
	    for (j = 0; j < i; j++) {
		  document.getElementsByTagName("select")[j].style.visibility = "hidden";
	    } 
      }
      function setTBGbed(){
          totalBed.innerText="0";
          boyBed.innerText="0";
          girlBed.innerText="0"; 
      }
    </script>
	<script language="javascript" src="js/function.js"></script>
	<script language="javascript" src="js/AjaxFunction.js"></script>
	<script type='text/javascript'
		src='/xgxt/dwr/interface/GetGyglDataInfo.js'></script>
	<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
	<script type='text/javascript' src='dwr/util.js'></script>
	<script language="javascript" src="js/gygl/csmzGyglFunction.js"></script>
	<base target="_self" />
	<%
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
	%>
	<body onload="">
		<html:form action="/csmz_gygl" method="post">
			<html:hidden name="csmz_gyglForm" property="conditionSqlText"
				styleId="conditionSqlText" />
			<html:hidden name="csmz_gyglForm" property="conditionSqlValue"
				styleId="conditionSqlValue" />
			<html:hidden name="csmz_gyglForm" property="count" styleId="count" />
			<html:hidden name="csmz_gyglForm" property="boy" styleId="boy" />
			<html:hidden name="csmz_gyglForm" property="girl" styleId="girl" />
			<input type="hidden" id="nj" name="nj" value="<bean:write name="nj" scope="request"/>" />
			<input type="hidden" name="oldCondiSqlValue" id="oldCondiSqlValue" value="<bean:write name="oldCondiSqlValue"/>" />
			<input type="hidden" id="doType" name="doType" value=""/>				
			<input type="hidden" name="cwfps" id="cwfps" />
			<input type="button" style="display: none" id="acwfp" onclick="addDisDormList2()">
			<input type="hidden" id="xbmc" value="<bean:message key="lable.xb" />"/>
			<div class="title">
				<div class="title_img" id="title_m">
					��ǰ����λ�ã���Ԣ���� - ������� - ���Ữ�� --�������Ữ��
				</div>
			</div>
			<input type="hidden" name="xxdm" id="xxdm" value="" />
			<div class="rightcontent">
				<fieldset>
					<div align="right">
						<legend>
							���Ữ��
						</legend>
						<table width="98%" align="center" class="tbstyle" bgcolor="#D0E0EE">
							<tr align="center">
								<td width="30%" align="left" rowspan="2">
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;У������
									<html:select property="xqdm" style="width:130px" styleId="xq"
										onchange="xsInitLdXbXdList()">
										<html:option value="">--��ѡ��--</html:option>
										<html:options collection="xiaoqquList" property="dm"
											labelProperty="xqmc" />
									</html:select>
									<br>
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;�Ա��޶���
									<html:select property="xb" style="width:130px" styleId="xb"
										onchange="xsinitSsFpLdList()">
										<html:options collection="xbxdList" property="dm"
											labelProperty="mc" />
									</html:select>
									<br>
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<font color="red">*</font>¥������
									<html:select property="lddm" styleId="ld" style="width:130px"
										onfocus="beforSsFpSubmit()"
										onchange="xsInitSsFpSsXxList();SsFpYFpSxXxListInit();">
										<html:options collection="ldList" property="dm"
											labelProperty="mc" />
									</html:select>
									<br>
									<br>
								</td>
								<td width="70%" align="left">
									�꼶��
									<html:select property="nj" styleId="nj" style="width:60px"
										disabled="true" onfocus="beforSsFpSubmit()">
										<html:options collection="njList" property="nd"
											labelProperty="nd" />
									</html:select>
									&nbsp;&nbsp;&nbsp;
									<font color="red">*</font><bean:message key="lable.xsgzyxpzxy" />��
									<html:select property="xydm" style="width:200px" styleId="xy"
										onchange="getXsSSFPData();SsFpYFpSxXxListInit()"
										onfocus="beforSsFpSubmit()">
										<html:option value="">--��ѡ��--</html:option>
										<html:options collection="xyList" property="xydm"
											labelProperty="xymc" />
									</html:select>
								</td>
							</tr>
							<tr>
								<td>
									&nbsp;&nbsp;δ����ѧ������(��):
									<span id="allbody" style="width: 80px"><bean:write
											name="allbody" scope="request" /> </span> &nbsp;(Ů):
									<span id="allgirl" style="width: 80px"> <bean:write
											name="allboy" scope="request" /> </span> &nbsp;(��):
									<span id="allboy" style="width: 80px"> <bean:write
											name="allgirl" scope="request" /> </span>

									<br>
									�ѻ�����(��)λ��(λ):
									<span id="totalBed" style="width: 80px"><bean:write
											name="totalBed" scope="request" />
									</span> (Ů):
									<span id="girlBed" style="width: 80px"><bean:write
											name="girlBed" scope="request" />
									</span>(��):
									<span id="boyBed" style="width: 80px"><bean:write
											name="boyBed" scope="request" />
									</span> (���):
									<span id="bgBed" style="width: 60px"><bean:write
											name="bgBed" scope="request" /> </span>
									<%--							<div align="right">--%>
									<%--								<a   href="/xgxt/csmz_gygl.do?method=hFDeTailView"  style="font-weight: bold" target="_blank" title="���������ϸ��Ϣ��ѯ">������ϸ</a>	--%>
									<%--								</div>--%>
								</td>									
							</tr>
							<tr align="center" bgcolor="#D0E0EE">
								<td  valign="top" colspan="2">
									<table width="97%" align="center" class="tbstyle">
										<tr align="center">

											<td align="center">
												δ��������
											</td>
											<td></td>
											<td align="center">
												�ѻ������
											</td>
										</tr>

										<tr align="center">
											<td rowspan="2" valign="top">
												���ұ��/�ܴ�λ��/ʣ�ലλ��
												<br>
												<html:select property="oracleItem" style="width:100%;"
													size="27" styleId="oracleList" ondblclick="addDisDorm()"
													multiple="multiple">
													<html:options collection="ssxxList" property="dm"
														labelProperty="mc" />
												</html:select>
											</td>
											<td valign="top">
												<div title="���ַ�ʽ">
													���ַ�ʽ��
													<br>
													<html:select property="fpfs" styleId="fpsf">
														<html:option value="ss">����������</html:option>
														<html:option value="cw">����λ��</html:option>
													</html:select>
												</div>
												<br>
												<br>
												<br>
												<br>
												<br>
												<font color="blue">����</font>
												<br>
												<button class="button2" onclick="addDisDorm()"
													style="width:50px;height: 20px" title="���Ữ��">
													&rarr;

												</button>
												<br>
												<br>
												<br>
												<br>
												<font color="blue">�ͷ�</font>
												<br>
												<button class="button2" onclick="delDisDorm()"
													style="width:50px;height: 20px" title="�����ͷ�">
													&larr;
												</button>
											</td>
											<td valign="top">
												<bean:message key="lable.xsgzyxpzxy" />/���ұ��/�ܴ�λ��/���ִ�λ��
												<br>
												<html:select property="sql" style="width:100%;" size="26"
													styleId="sql" ondblclick="delDisDorm()" multiple="multiple">
													<html:options collection="ssfpList" property="dm"
														labelProperty="mc" />
												</html:select>
											</td>
										</tr>
									</table>
								</td>
							</tr>
							<tr  align="right">
								<td colspan="2">
									
								<input class="button2" type="button" name="button1"
										style="width:100px" value="ȷ ��" onclick="xsDormDistSave();" />
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<input type="button" class="button2" name="button2"
										style="width:100px" value="�� ��"
										onclick="refreshForm('/xgxt/csmz_gygl.do?method=xsDorm_Distribute')" />
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								</td>
							</tr>

						</table>
					</div>
				</fieldset>
			</div>
		</html:form>
		<logic:notEmpty name="doFlag">
			<logic:equal name="doFlag" value="true">
				<script>				   
					alert("�����ɹ�!");					
				</script>
			</logic:equal>
			<logic:equal name="doFlag" value="false">
				<script>				    
					alert("����ʧ��!");
				</script>
			</logic:equal>
		</logic:notEmpty>
	</body>
</html>
