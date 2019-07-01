<%@ page language="java" pageEncoding="GBK"%>

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
<html>
	<head>
		<title><bean:message key="lable.title" />
		</title>
		<%
			response.setHeader("Pragma", "No-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);
		%>
		<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
		<meta http-equiv="Pragma" http-equiv="no-cache" />
		<meta http-equiv="Cache-Control" http-equiv="no-cache" />
		<meta http-equiv="Expires" http-equiv="0" />
		<meta name="Copyright" content="������� zfsoft" />
	</head>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<link id="csss" rel="stylesheet" rev="stylesheet"
		href="js/calendar.css" type="text/css" media="all" />
	<base target="_self">
	<script type="text/javascript" src="js/stuinfoFunction.js"></script>
	<script language="javascript" src="js/function.js"></script>
	<script language="javascript" src="js/commanFunction.js"></script>
	<script language="javascript" src="js/calendar.js"></script>
	<script language="javascript" src="js/calendar-zh.js"></script>
	<script language="javascript" src="js/calendar-setup.js"></script>
	<script type="text/javascript" src="/xgxt/dwr/interface/getXjydInfo.js"></script>
	<script type="text/javascript" src="/xgxt/dwr/engine.js"></script>
	<script type="text/javascript" src="/xgxt/dwr/util.js"></script>
	<body>
		<html:form action="/modi_stu_famil" method="post">
			<div class="title">
				<div class="title_img" id="title_m">
					��ǰλ�ã�ѧ����Ϣ - ѧ����ͥ��Ϣ - ��Ϣά��
				</div>
			</div>
			<input type="hidden" value="<bean:write name="oper"/>" id="oper" />
			<input type="hidden" name="url" id="url"
				value="/sjcz/stu_family_modify.jsp">
			<input type="hidden" name="variable" id="variable" value="ydinfo">
			<input type="hidden" name="redirect" id="redirect" value="true">
			<input type="hidden" name="page" id="page" value="stuinfo">
			<input type="hidden" name="notnull" id="notnull" value="xh">
			<logic:equal name="userOnLine" value="student" scope="session">
				<br />
				<br />
				<br />
				<center>
					<p>
						��ҳ��ѧ�������Է��ʣ�
					</p>
				</center>
			</logic:equal>
			<logic:equal name="userType" value="xy" scope="session">
				<br />
				<br />
				<br />
				<center>
					<p>
						��ҳ��ֻ��ѧУ�͹���Ա�û����Է��ʣ�
					</p>
				</center>
			</logic:equal>

			<logic:equal name="userOnLine" value="teacher" scope="session">
				<logic:notEqual value="xy" name="userType" scope="session">
					<table width="100%" class="tbstyle">
						<thead align="center">
							<tr><td align="center" colspan="4">
								<b>ѧ����ͥ��Ϣ</b>
							</td></tr>
						</thead>
						<tr>
							<td align="right" width="15%">
								<font color="red">*</font>ѧ�ţ�
							</td>
							<td align="left" width="25%">
								<logic:equal value="update" name="oper">
									<html:text property="xh" readonly="true" styleId="xh"
										style="cursor:hand" />
								</logic:equal>
								<logic:equal value="add" name="oper">
									<html:text name="rs" property="xh" styleId="xh"
										onkeypress="if(event.keyCode == 13) autoFillStuInfo2(this);" />
									<button align="left" class="button2"
										onclick="showTopWin('/xgxt/stu_info.do?oper=<bean:write name="oper"/>',750,550);"
										id="buttonFindStu">
										ѡ��
									</button>
								</logic:equal>
								<br>
							</td>
							<td align="right">
								<bean:message key="lable.xsgzyxpzxy" />��
								<br>
							</td>
							<td align="left" width="35%">
								<bean:write name="rs" property="xymc" />
							</td>
						<tr>
							<td align="right" width="15%">
								�꼶��
								<br>
							</td>
							<td align="left" width="30%">
								<bean:write name="rs" property="nj" />
							</td>
							<td align="right">
								רҵ��
								<br>
							</td>
							<td align="left">
								<bean:write name="rs" property="zymc" />
							</td>
						</tr>
						<tr>
							<td align="right">
								������
								<br>
							</td>
							<td align="left">
								<bean:write name="rs" property="xm" />
							</td>
							<td align="right">
								�༶��
								<br>
							</td>
							<td align="left">
								<bean:write name="rs" property="bjmc" />
							</td>
						</tr>
						<tr>
						<td align="right">
							��ͥ��ַ��
						</td>
						<td align="left">
							<html:text name="rs" property="jtszd" maxlength="25"
								style="width:100%" />
						</td>
						<td align="right">
							�������룺
						</td>
						<td>
							<html:text name="rs" property="jtyb"
								onkeyup="value=value.replace(/[^\d]/g,'') " maxlength="10"
								style="width:100%" />
						</td>
					</tr>
					<tr>
						<td align="right">
							��ͥ�绰��
						</td>
						<td align="left" colspan="3">
							<html:text name="rs" property="lxdh1" maxlength="25"
								style="width:100%" />
						</td>
					</tr>
						<tr>
							<td align="right">
								��ͥ����״����
							</td>
							<td colspan="3">
								<html:textarea name="rs" property="jjzk"
									style="width:100%;height:45"></html:textarea>
							</td>
						</tr>
					</table>
					<table width="100%" class="tbstyle">
						<thead>
							<tr>
								<td style="cursor:hand" align="center"
									onclick="document.getElementById('jt1').style.display=(document.getElementById('jt1').style.display==''?'none':'')">
									ѧ����ͥ��Ա��Ϣ1
								</td>
							</tr>
						</thead>
						<tr id="jt1">
							<td>
								<fieldset>
									<legend>
										��ͥ��Ա1
									</legend>
									<table width="100%" class="tbstyle">
										<tr>
											<td align="right" width="15%">
												������
												<br>
											</td>
											<td align="left" width="25%">
												<html:text name="rs" property="jtcy1_xm" styleId="xm" />
												<br>
											</td>
											<td align="right" width="15%">
												�뱾�˹�ϵ��
											</td>
											<td align="left" width="30%">
												<html:text name="rs" property="jtcy1_gx" styleId="ch" />
											</td>
										</tr>
										<tr>
											<td align="right" width="15%">
												�������ڣ�
											</td>
											<td align="left" width="25%">
												<html:text name="rs" property="jtcy1_nl" styleId="nl"
													onclick="return showCalendar('jtcy1_nl','y-mm-dd');" />
											</td>

											<td align="right" width="15%">
												���֤�ţ�
											</td>
											<td align="left" width="30%">
												<html:text name="rs" property="jtcy1_sfzh" styleId="sfzh" />
											</td>
										</tr>
										<tr>
											<td align="right" width="15%">
												���壺
												<br>
											</td>
											<td align="left" width="25%">
												<html:select name="rs" property="jtcy1_mz"
													styleId="jtcy1_mz" style="width:150px">
													<html:option value=""></html:option>
													<html:options collection="mzList" property="mzdm"
														labelProperty="mzmc" />
												</html:select>
												<br>
											</td>
											<td align="right" width="15%">
												������ò��
												<br>
											</td>
											<td align="left" width="30%">
												<html:select name="rs" property="jtcy1_zzmm"
													styleId="jtcy1_zzmm" style="width:150px">
													<html:option value=""></html:option>
													<html:options collection="zzmmList" property="zzmmdm"
														labelProperty="zzmmmc" />
												</html:select>
												<br>
											</td>
										</tr>
										<tr>
											<td align="right" width="15%">
												ְҵ��
											</td>
											<td align="left" width="30%">
												<html:text name="rs" property="jtcy1_zy" styleId="jtcy1_zy" />

											</td>
											<td align="right" width="15%">
												ְ��
											</td>
											<td align="left" width="30%">
												<html:text name="rs" property="jtcy1_zw" styleId="jtcy1_zw" />
											</td>
										</tr>
										<tr>
											<td align="right" width="15%">
												������λ�绰��
												<br>
											</td>
											<td align="left" width="25%">
												<html:text name="rs" property="jtcy1_lxdh2" styleId="lxdh2" />
												<br>
											</td>
											<td align="right" width="15%">
												���˵绰��
												<br>
											</td>
											<td align="left" width="30%">
												<html:text name="rs" property="jtcy1_lxdh1" styleId="lxdh1" />
												<br>
											</td>
										</tr>
										<tr>
											<td align="right" width="15%">
												������ַ��
											</td>
											<td align="left" width="200">
												<html:text name="rs" property="jtcy1_gzdz" styleId="jtcy1_gzdz" />
											</td>
											<td align="right" width="15%">
												�ں���ϵ�ˣ�
											</td>
											<td align="left" width="200">
												<html:text name="rs" property="jtcy1_lxr" styleId="jtcy1_lxr" />
											</td>
										</tr>
									</table>
								</fieldset>
							</td>
						</tr>
						<thead>
							<tr>
								<td style="cursor:hand" align="center"
									onclick="document.getElementById('jt2').style.display=(document.getElementById('jt2').style.display==''?'none':'')">
									ѧ����ͥ��Ա��Ϣ2
								</td>
							</tr>
						</thead>
						<tr id="jt2">
							<td>
								<fieldset>
									<legend>
										��ͥ��Ա2
									</legend>
									<table width="100%" class="tbstyle">
										<tr>
											<td align="right" width="15%">
												������
												<br>
											</td>
											<td align="left" width="25%">
												<html:text name="rs" property="jtcy2_xm" styleId="jtcy2_xm" />
												<br>
											</td>
											<td align="right" width="15%">
												�뱾�˹�ϵ��
											</td>
											<td align="left" width="30%">
												<html:text name="rs" property="jtcy2_gx" styleId="jtcy2_gx" />
											</td>
										</tr>
										<tr>
											<td align="right" width="15%">
												�������ڣ�
											</td>
											<td align="left" width="25%">
												<html:text name="rs" property="jtcy2_nl" styleId="jtcy2_nl"
													onclick="return showCalendar('jtcy2_nl','y-mm-dd');" />
											</td>
											<td align="right" width="15%">
												���֤�ţ�
											</td>
											<td align="left" width="30%">
												<html:text name="rs" property="jtcy2_sfzh"
													styleId="jtcy2_sfzh" />
											</td>
										</tr>
										<tr>
											<td align="right" width="15%">
												���壺
												<br>
											</td>
											<td align="left" width="25%">
												<html:select name="rs" property="jtcy2_mz"
													styleId="jtcy2_mz" style="width:150px">
													<html:option value=""></html:option>
													<html:options collection="mzList" property="mzdm"
														labelProperty="mzmc" />
												</html:select>
												<br>
											</td>
											<td align="right" width="15%">
												������ò��
												<br>
											</td>
											<td align="left" width="30%">
												<html:select name="rs" property="jtcy2_zzmm"
													styleId="jtcy2_zzmm" style="width:150px">
													<html:option value=""></html:option>
													<html:options collection="zzmmList" property="zzmmdm"
														labelProperty="zzmmmc" />
												</html:select>
												<br>
											</td>
										</tr>
										<tr>
											<td align="right" width="15%">
												ְҵ��
											</td>
											<td align="left" width="30%">
												<html:text name="rs" property="jtcy2_zy" styleId="jtcy2_zy" />
											</td>
											<td align="right" width="15%">
												ְ��
											</td>
											<td align="left" width="30%">
												<html:text name="rs" property="jtcy2_zw" styleId="jtcy2_zw" />
											</td>
										</tr>
										<tr>
											<td align="right" width="15%">
												������λ�绰��
												<br>
											</td>
											<td align="left" width="25%">
												<html:text name="rs" property="jtcy2_lxdh2"
													styleId="jtcy2_lxdh2" />
												<br>
											</td>
											<td align="right" width="15%">
												���˵绰��
												<br>
											</td>
											<td align="left" width="30%">
												<html:text name="rs" property="jtcy2_lxdh1"
													styleId="jtcy2_lxdh1" />
												<br>
											</td>
										</tr>
										<tr>
											<td align="right" width="15%">
												������ַ��
											</td>
											<td align="left" width="200">
												<html:text name="rs" property="jtcy2_gzdz"
													styleId="jtcy2_gzdz" />
											</td>
											<td align="right" width="15%">
												�ں���ϵ�ˣ�
											</td>
											<td align="left" width="200">
												<html:text name="rs" property="jtcy2_lxr" styleId="jtcy2_lxr" />
											</td>
										</tr>
									</table>
								</fieldset>
							</td>
						</tr>
						<thead>
							<tr>
								<td style="cursor:hand" align="center"
									onclick="document.getElementById('jt3').style.display=(document.getElementById('jt3').style.display==''?'none':'')">
									ѧ����ͥ��Ա��Ϣ3
								</td>
							</tr>
						</thead>
						<tr id="jt3" style="display:none">
							<td>
								<fieldset>
									<legend>
										��ͥ��Ա3
									</legend>
									<table width="100%" class="tbstyle">
										<tr>
											<td align="right" width="15%">
												������
												<br>
											</td>
											<td align="left" width="25%">
												<html:text name="rs" property="jtcy3_xm" styleId="jtcy3_xm" />
												<br>
											</td>
											<td align="right" width="15%">
												�뱾�˹�ϵ��
											</td>
											<td align="left" width="30%">
												<html:text name="rs" property="jtcy3_gx" styleId="jtcy3_gx" />
											</td>
										</tr>
										<tr>
											<td align="right" width="15%">
												�������ڣ�
											</td>
											<td align="left" width="25%">
												<html:text name="rs" property="jtcy3_nl" styleId="jtcy3_nl"
													onclick="return showCalendar('jtcy3_nl','y-mm-dd');" />
											</td>
											<td align="right" width="15%">
												���֤�ţ�
											</td>
											<td align="left" width="30%">
												<html:text name="rs" property="jtcy3_sfzh"
													styleId="jtcy3_sfzh" />
											</td>
										</tr>
										<tr>
											<td align="right" width="15%">
												���壺
												<br>
											</td>
											<td align="left" width="25%">
												<html:select name="rs" property="jtcy3_mz"
													styleId="jtcy3_mz" style="width:150px">
													<html:option value=""></html:option>
													<html:options collection="mzList" property="mzdm"
														labelProperty="mzmc" />
												</html:select>
												<br>
											</td>
											<td align="right" width="15%">
												������ò��
												<br>
											</td>
											<td align="left" width="30%">
												<html:select name="rs" property="jtcy3_zzmm"
													styleId="jtcy3_zzmm" style="width:150px">
													<html:option value=""></html:option>
													<html:options collection="zzmmList" property="zzmmdm"
														labelProperty="zzmmmc" />
												</html:select>
												<br>
											</td>
										</tr>
										<tr>
											<td align="right" width="15%">
												ְҵ��
											</td>
											<td align="left" width="30%">
												<html:text name="rs" property="jtcy3_zy" styleId="jtcy3_zy" />
											</td>
											<td align="right" width="15%">
												ְ��
											</td>
											<td align="left" width="30%">
												<html:text name="rs" property="jtcy3_zw" styleId="jtcy3_zw" />
											</td>
										</tr>
										<tr>
											<td align="right" width="15%">
												������λ�绰��
												<br>
											</td>
											<td align="left" width="25%">
												<html:text name="rs" property="jtcy3_lxdh2"
													styleId="jtcy3_lxdh2" />
												<br>
											</td>
											<td align="right" width="15%">
												���˵绰��
												<br>
											</td>
											<td align="left" width="30%">
												<html:text name="rs" property="jtcy3_lxdh1"
													styleId="jtcy3_lxdh1" />
												<br>
											</td>
										</tr>
										<tr>
											<td align="right" width="15%">
												������ַ��
											</td>
											<td align="left" width="200">
												<html:text name="rs" property="jtcy3_gzdz"
													styleId="jtcy3_gzdz" />
											</td>
											<td align="right" width="15%">
												�ں���ϵ�ˣ�
											</td>
											<td align="left" width="200">
												<html:text name="rs" property="jtcy3_lxr" styleId="jtcy3_lxr" />
											</td>
										</tr>
									</table>
								</fieldset>
							</td>
						</tr>
					</table>
				</logic:notEqual>
			</logic:equal>
			
			<logic:notEqual value="student" name="user">
					<center>
						<div class="buttontool" id="btn" width="100%">
							&nbsp;&nbsp;&nbsp;&nbsp;
							<logic:notPresent name="cWrite">
								<button style="width:80px" class="button2"
									onclick="stuinfoSave('modi_stu_famil.do?doType=modify&oper=','family')">
									�� ��
								</button>
							</logic:notPresent>
							<logic:present name="cWrite">
								<button style="width:80px" class="button2" disabled="disabled">
									�� ��
								</button>
							</logic:present>
							&nbsp;&nbsp;&nbsp;&nbsp;
							<button style="width:80px" class="button2" onclick="Close();return false;">
								�� ��
							</button>
						</div>
					</center>
			</logic:notEqual>
			<logic:notEmpty name="result">
				<logic:equal value="true" name="result">
					<script>
					alert("�����ɹ���");
					Close();
					window.dialogArguments.document.getElementById('search_go').click();		
					</script>
				</logic:equal>
				<logic:equal value="false" name="result">
					<script>
					alert("����ʧ�ܣ�");
					</script>
				</logic:equal>
			</logic:notEmpty>
		</html:form>
	</body>
</html>
