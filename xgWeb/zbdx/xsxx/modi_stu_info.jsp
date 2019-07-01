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
	<%
response.setHeader("Pragma","No-cache");
response.setHeader("Cache-Control","no-cache");
response.setDateHeader("Expires", 0);
%>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<base target="_self">
	<script language="javascript">
</script>
	<body>
		<script language="javascript" src="js/function.js"></script>
		<html:form action="/data_search" method="post">
			<div class="title">
					<logic:equal name="userOnLine" value="student" scope="session">
							<div class="title_img" id="title_m">
								��ǰλ�ã�ѧ����Ϣ - ��Ϣ�޸� - �޸ĸ�����Ϣ
							</div>
					</logic:equal>
					<logic:equal name="userOnLine" value="teacher" scope="session">
							<div class="title_img" id="title_m">
								��ǰλ�ã�ѧ����Ϣ - ��Ϣ�޸� - �޸�ѧ����ͥ��Ϣ
							</div>
					</logic:equal>
						
			</div>
<%-- 			<logic:notEqual name="userOnLine" value="student" scope="session">
				<br />
				<br />
				<p align="center">
					��ҳ��ֻ��ѧ�����Է��ʣ�
				</p>
			</logic:notEqual>   --%>
			<logic:equal name="userType" value="admin" scope="session">
			<br />
			<br />
			<br />
			<p align="center">��ҳ��ֻ��ѧ����<bean:message key="lable.xsgzyxpzxy" />�û����Է���</p>
			</logic:equal>
			<logic:equal name="userType" value="xx" scope="session">
			<br />
			<br />
			<br />
			<p align="center">��ҳ��ֻ��ѧ����<bean:message key="lable.xsgzyxpzxy" />�û����Է���</p>
			</logic:equal>
			
			<logic:equal name="sqsjFlag" value="1">
					<script>
   			 alert("�����趨ʱ�䷶Χ��,�ݲ���������!");
    		 location.href="about:blank";
   			 </script>
   			</logic:equal>
			<logic:equal name="userOnLine" value="student" scope="session">
				<logic:present name="rs">
					<logic:equal name="dataSaved" value="ok" scope="request">
						<script>
    alert("����ɹ���");
    </script>
					</logic:equal>
					<input type="hidden" id="disableEle" name="disableEle"
						value="xm-xb-xy-nj-zy-bj" />
					<input type="hidden" id="getStuInfo" name="getStuInfo"
						value="xm-xb-xymc-nj-zymc-bjmc" />
					<input type="hidden" id="url" name="url" value="/comm_apply.do" />
					
					<logic:notEmpty name="gwcxview">
					<table width="100%" class="tbstyle">
						<thead>
							<tr>
								<td colspan="4" align="center">
									ѧ��������Ϣ
								</td>
							</tr>
						</thead>
						<tr>
							<td align="right">
								ѧ�ţ�
							</td>
							<td align="left">
								<bean:write name="userName" scope="session" />
							</td>
							<td align="right">
								������
							</td>
							<td align="left">
								<bean:write name="rs" property="xm" />
							</td>
						</tr>
						<tr>
							<td align="right">
								<bean:message key="lable.xsgzyxpzxy" />��
							</td>
							<td align="left">
								<bean:write name="rs" property="xymc" />
							</td>
							<td align="right">
								רҵ��
							</td>
							<td align="left">
								<bean:write name="rs" property="zymc" />
							</td>
						</tr>
						<tr>
							<td align="right">
								�꼶��
							</td>
							<td align="left">
								<bean:write name="rs" property="nj" />
							</td>
							<td align="right">
								�༶��
							</td>
							<td align="left">
								<bean:write name="rs" property="bjmc" />
							</td>
						</tr>
						<tr>
							<td align="right">
								����ţ�
							</td>
							<td align="left">
								<input type="text" style="width:200px" name="ssbh" id="ssbh"
									value="<bean:write name="rs" property="ssbh"/>" />
							</td>
							<td align="right">
								����绰��
							</td>
							<td align="left">
								<input type="text" style="width:200px" name="qsdh" id="qsdh"
									value="<bean:write name="rs" property="qsdh"/>" />
							</td>
						</tr>
						<tr>
							<td align="right">
								�ֻ����룺
							</td>
							<td align="left">
								<input type="text" style="width:200px" name="sjhm" id="sjhm"
									value="<bean:write name="rs" property="sjhm"/>" />
							</td>
							<td align="right">
								E-Mail��
							</td>
							<td align="left">
								<input type="text" style="width:200px" name="email" id="email"
									value="<bean:write name="rs" property="email"/>" />
							</td>
						</tr>
						<tr>
							<td align="right">
								��ͥ���ڵأ�
							</td>
							<td align="left">
								<input type="text" style="width:200px" name="jtszd" id="jtszd"
									value="<bean:write name="rs" property="jtszd"/>" />
							</td>
							<td align="right">
								��ͥ���ڵ�ַ�ʱࣺ
							</td>
							<td align="left">
								<input type="text" style="width:200px" name="yb" id="yb"
									value="<bean:write name="rs" property="yb"/>" />
							</td>
						</tr>
						<tr>
							<td align="right">
								����ͨ�ŵ�ַ��
							</td>
							<td colspan="3" align="left">
								<input type="text" style="width:600px" name="brtxdz" id="brtxdz"
									value="<bean:write name="rs" property="brtxdz"/>" />
							</td>
						</tr>
						
						<thead>
							<tr>
								<td colspan="4" style="cursor:hand" align="center"
									onclick="document.getElementById('jt1').style.display=(document.getElementById('jt1').style.display==''?'none':'')">
									ѧ����ͥ��Ա��Ϣ1
								</td>
							</tr>
						</thead>
						<tr id="jt1">
							<td colspan="4">
								<table width="100%" class="tbstyle">
									<tr>
										<td align="right">
											������
										</td>
										<td align="left">
											<input type="text" style="width:200px" name="jtcy1_xm"
												id="jtcy1_xm"
												value="<bean:write name="rs" property="jtcy1_xm"/>" />
										</td>
										<td align="right">
											�뱾�˹�ϵ��
										</td>
										<td align="left">
											<input type="text" style="width:200px" name="jtcy1_gx"
												id="jtcy1_gx"
												value="<bean:write name="rs" property="jtcy1_gx"/>" />
										</td>
									</tr>
									<tr>
										<td align="right">
											���֤���룺
										</td>
										<td align="left">
											<input type="text" style="width:200px" name="jtcy1_sfzh"
												id="jtcy1_sfzh"
												value="<bean:write name="rs" property="jtcy1_sfzh"/>" />
										</td>
										<td align="right">
											ְҵ��
										</td>
										<td align="left">
											<input type="text" style="width:200px" name="jtcy1_zy"
												id="jtcy1_zy"
												value="<bean:write name="rs" property="jtcy1_zy"/>" />
										</td>
									</tr>
									<tr>
										<td align="right">
											������ַ��
										</td>
										<td colspan="3" align="left">
											<input type="text" style="width:600px" name="jtcy1_gzdz"
												id="jtcy1_gzdz"
												value="<bean:write name="rs" property="jtcy1_gzdz"/>" />
										</td>
									</tr>
									<tr>
										<td align="right">
											ְ��
										</td>
										<td align="left">
											<input type="text" style="width:200px" name="jtcy1_zw"
												id="jtcy1_zw"
												value="<bean:write name="rs" property="jtcy1_zw"/>" />
										</td>
										<td align="right">
											��ϵ�绰1��
										</td>
										<td align="left">
											<input type="text" style="width:200px" name="jtcy1_lxdh1"
												id="jtcy1_lxdh1"
												value="<bean:write name="rs" property="jtcy1_lxdh1"/>" />
										</td>
									</tr>
									<tr>
										<td align="right">
											��ϵ�绰2��
										</td>
										<td align="left">
											<input type="text" style="width:200px" name="jtcy1_lxdh1"
												id="jtcy1_lxdh1"
												value="<bean:write name="rs" property="jtcy1_lxdh1"/>" />
										</td>
										<td align="right">
											&nbsp;
										</td>
										<td align="left">
											&nbsp;
										</td>
									</tr>
								</table>
							</td>
						</tr>
						<thead>
							<tr>
								<td colspan="4" style="cursor:hand" align="center"
									onclick="document.getElementById('jt2').style.display=(document.getElementById('jt2').style.display==''?'none':'')">
									ѧ����ͥ��Ա��Ϣ2
								</td>
							</tr>
						</thead>
						<tr id="jt2" style="display:none">
							<td colspan="4">
								<table width="100%" class="tbstyle">
									<tr>
										<td align="right">
											������
										</td>
										<td align="left">
											<input type="text" style="width:200px" name="jtcy2_xm"
												id="jtcy2_xm"
												value="<bean:write name="rs" property="jtcy2_xm"/>" />
										</td>
										<td align="right">
											�뱾�˹�ϵ��
										</td>
										<td align="left">
											<input type="text" style="width:200px" name="jtcy2_gx"
												id="jtcy2_gx"
												value="<bean:write name="rs" property="jtcy2_gx"/>" />
										</td>
									</tr>
									<tr>
										<td align="right">
											���֤���룺
										</td>
										<td align="left">
											<input type="text" style="width:200px" name="jtcy2_sfzh"
												id="jtcy2_sfzh"
												value="<bean:write name="rs" property="jtcy2_sfzh"/>" />
										</td>
										<td align="right">
											ְҵ��
										</td>
										<td align="left">
											<input type="text" style="width:200px" name="jtcy2_zy"
												id="jtcy2_zy"
												value="<bean:write name="rs" property="jtcy2_zy"/>" />
										</td>
									</tr>
									<tr>
										<td align="right">
											������ַ��
										</td>
										<td colspan="3" align="left">
											<input type="text" style="width:600px" name="jtcy2_gzdz"
												id="jtcy2_gzdz"
												value="<bean:write name="rs" property="jtcy2_gzdz"/>" />
										</td>
									</tr>
									<tr>
										<td align="right">
											ְ��
										</td>
										<td align="left">
											<input type="text" style="width:200px" name="jtcy2_zw"
												id="jtcy2_zw"
												value="<bean:write name="rs" property="jtcy2_zw"/>" />
										</td>
										<td align="right">
											��ϵ�绰1��
										</td>
										<td align="left">
											<input type="text" style="width:200px" name="jtcy2_lxdh1"
												id="jtcy2_lxdh1"
												value="<bean:write name="rs" property="jtcy2_lxdh1"/>" />
										</td>
									</tr>
									<tr>
										<td align="right">
											��ϵ�绰2��
										</td>
										<td align="left">
											<input type="text" style="width:200px" name="jtcy2_lxdh2"
												id="jtcy2_lxdh2"
												value="<bean:write name="rs" property="jtcy2_lxdh2"/>" />
										</td>
										<td align="right">
											&nbsp;
										</td>
										<td align="left">
											&nbsp;
										</td>
									</tr>
								</table>
							</td>
						</tr>
						<thead>
							<tr>
								<td colspan="4" style="cursor:hand" align="center"
									onclick="document.getElementById('jt3').style.display=(document.getElementById('jt3').style.display==''?'none':'')">
									ѧ����ͥ��Ա��Ϣ3
								</td>
							</tr>
						</thead>
						<tr id="jt3" style="display:none">
							<td colspan="4">
								<table width="100%" class="tbstyle">
									<tr>
										<td align="right">
											������
										</td>
										<td align="left">
											<input type="text" style="width:200px" name="jtcy3_xm"
												id="jtcy3_xm"
												value="<bean:write name="rs" property="jtcy3_xm"/>" />
										</td>
										<td align="right">
											�뱾�˹�ϵ��
										</td>
										<td align="left">
											<input type="text" style="width:200px" name="jtcy3_gx"
												id="jtcy3_gx"
												value="<bean:write name="rs" property="jtcy3_gx"/>" />
										</td>
									</tr>
									<tr>
										<td align="right">
											���֤���룺
										</td>
										<td align="left">
											<input type="text" style="width:200px" name="jtcy3_sfzh"
												id="jtcy3_sfzh"
												value="<bean:write name="rs" property="jtcy3_sfzh"/>" />
										</td>
										<td align="right">
											ְҵ��
										</td>
										<td align="left">
											<input type="text" style="width:200px" name="jtcy3_zy"
												id="jtcy3_zy"
												value="<bean:write name="rs" property="jtcy3_zy"/>" />
										</td>
									</tr>
									<tr>
										<td align="right">
											������ַ��
										</td>
										<td colspan="3" align="left">
											<input type="text" style="width:600px" name="jtcy3_gzdz"
												id="jtcy3_gzdz"
												value="<bean:write name="rs" property="jtcy3_gzdz"/>" />
										</td>
									</tr>
									<tr>
										<td align="right">
											ְ��
										</td>
										<td align="left">
											<input type="text" style="width:200px" name="jtcy3_zw"
												id="jtcy3_zw"
												value="<bean:write name="rs" property="jtcy3_zw"/>" />
										</td>
										<td align="right">
											��ϵ�绰1��
										</td>
										<td align="left">
											<input type="text" style="width:200px" name="jtcy3_lxdh1"
												id="jtcy3_lxdh1"
												value="<bean:write name="rs" property="jtcy3_lxdh1"/>" />
										</td>
									</tr>
									<tr>
										<td align="right">
											��ϵ�绰2��
										</td>
										<td align="left">
											<input type="text" style="width:200px" name="jtcy3_lxdh2"
												id="jtcy3_lxdh2"
												value="<bean:write name="rs" property="jtcy3_lxdh2"/>" />
										</td>
										<td align="right">
											&nbsp;
										</td>
										<td align="left">
											&nbsp;
										</td>
									</tr>
								</table>
							</td>
						</tr>
					</table>
					<br />
					<div class="buttontool" align="center">
						<button class="button2"
							onclick="refreshForm('modi_stu_info.do?act=save')">
							�� �� �� Ϣ
						</button>
					</div>					
					</logic:notEmpty>
					<logic:empty name="gwcxview"> 
					<table width="100%" class="tbstyle">
						<thead>
							<tr>
								<td colspan="4" align="center">
									ѧ��������Ϣ
								</td>
							</tr>
						</thead>
						<tr>
							<td align="right">
								ѧ�ţ�
							</td>
							<td align="left">
								<bean:write name="userName" scope="session" />
							</td>
							<td align="right">
								������
							</td>
							<td align="left">
								<bean:write name="rs" property="xm" />
							</td>
						</tr>
						<tr>
							<td align="right">
								<bean:message key="lable.xsgzyxpzxy" />��
							</td>
							<td align="left">
								<bean:write name="rs" property="xymc" />
							</td>
							<td align="right">
								רҵ��
							</td>
							<td align="left">
								<bean:write name="rs" property="zymc" />
							</td>
						</tr>
						<tr>
							<td align="right">
								�꼶��
							</td>
							<td align="left">
								<bean:write name="rs" property="nj" />
							</td>
							<td align="right">
								�༶��
							</td>
							<td align="left">
								<bean:write name="rs" property="bjmc" />
							</td>
						</tr>
						<tr>
							<td align="right">
								����ţ�
							</td>
							<td align="left">
								<input type="text" style="width:200px" name="ssbh" id="ssbh"
									value="<bean:write name="rs" property="ssbh"/>" />
							</td>
							<td align="right">
								����绰��
							</td>
							<td align="left">
								<input type="text" style="width:200px" name="qsdh" id="qsdh"
									value="<bean:write name="rs" property="qsdh"/>" />
							</td>
						</tr>
						<tr>
							<td align="right">
								�ֻ����룺
							</td>
							<td align="left">
								<input type="text" style="width:200px" name="sjhm" id="sjhm"
									value="<bean:write name="rs" property="sjhm"/>" />
							</td>
							<td align="right">
								E-Mail��
							</td>
							<td align="left">
								<input type="text" style="width:200px" name="email" id="email"
									value="<bean:write name="rs" property="email"/>" />
							</td>
						</tr>
						<tr>
							<td align="right">
								��ͥ���ڵأ�
							</td>
							<td align="left">
								<input type="text" style="width:200px" name="jtszd" id="jtszd"
									value="<bean:write name="rs" property="jtszd"/>" />
							</td>
							<td align="right">
								��ͥ���ڵ�ַ�ʱࣺ
							</td>
							<td align="left">
								<input type="text" style="width:200px" name="yb" id="yb"
									value="<bean:write name="rs" property="yb"/>" />
							</td>
						</tr>
						<tr>
							<td align="right">
								����ͨ�ŵ�ַ��
							</td>
							<td colspan="3" align="left">
								<input type="text" style="width:600px" name="brtxdz" id="brtxdz"
									value="<bean:write name="rs" property="brtxdz"/>" />
							</td>
						</tr>
						<thead>
							<tr>
								<td colspan="4" style="cursor:hand" align="center"
									onclick="document.getElementById('jt1').style.display=(document.getElementById('jt1').style.display==''?'none':'')">
									ѧ����ͥ��Ա��Ϣ1
								</td>
							</tr>
						</thead>
						<tr id="jt1">
							<td colspan="4">
								<table width="100%" class="tbstyle">
									<tr>
										<td align="right">
											������
										</td>
										<td align="left">
											<input type="text" style="width:200px" name="jtcy1_xm"
												id="jtcy1_xm" readonly="yes"
												value="<bean:write name="rs" property="jtcy1_xm"/>" />
										</td>
										<td align="right">
											�뱾�˹�ϵ��
										</td>
										<td align="left">
											<input type="text" style="width:200px" name="jtcy1_gx"
												id="jtcy1_gx" readonly="yes"
												value="<bean:write name="rs" property="jtcy1_gx"/>" />
										</td>
									</tr>
									<tr>
										<td align="right">
											���֤���룺
										</td>
										<td align="left">
											<input type="text" style="width:200px" name="jtcy1_sfzh"
												id="jtcy1_sfzh" readonly="yes"
												value="<bean:write name="rs" property="jtcy1_sfzh"/>" />
										</td>
										<td align="right">
											ְҵ��
										</td>
										<td align="left">
											<input type="text" style="width:200px" name="jtcy1_zy"
												id="jtcy1_zy"  readonly="yes"
												value="<bean:write name="rs" property="jtcy1_zy"/>" />
										</td>
									</tr>
									<tr>
										<td align="right">
											������ַ��
										</td>
										<td colspan="3" align="left">
											<input type="text" style="width:600px" name="jtcy1_gzdz"
												id="jtcy1_gzdz"  readonly="yes"
												value="<bean:write name="rs" property="jtcy1_gzdz"/>" />
										</td>
									</tr>
									<tr>
										<td align="right">
											ְ��
										</td>
										<td align="left">
											<input type="text" style="width:200px" name="jtcy1_zw"
												id="jtcy1_zw" readonly="yes"
												value="<bean:write name="rs" property="jtcy1_zw"/>" />
										</td>
										<td align="right">
											��ϵ�绰1��
										</td>
										<td align="left">
											<input type="text" style="width:200px" name="jtcy1_lxdh1"
												id="jtcy1_lxdh1"  readonly="yes"
												value="<bean:write name="rs" property="jtcy1_lxdh1"/>" />
										</td>
									</tr>
									<tr>
										<td align="right">
											��ϵ�绰2��
										</td>
										<td align="left">
											<input type="text" style="width:200px" name="jtcy1_lxdh1"
												id="jtcy1_lxdh1"  readonly="yes"
												value="<bean:write name="rs" property="jtcy1_lxdh1"/>" />
										</td>
										<td align="right">
											&nbsp;
										</td>
										<td align="left">
											&nbsp;
										</td>
									</tr>
								</table>
							</td>
						</tr>
						<thead>
							<tr>
								<td colspan="4" style="cursor:hand" align="center"
									onclick="document.getElementById('jt2').style.display=(document.getElementById('jt2').style.display==''?'none':'')">
									ѧ����ͥ��Ա��Ϣ2
								</td>
							</tr>
						</thead>
						<tr id="jt2" style="display:none">
							<td colspan="4">
								<table width="100%" class="tbstyle">
									<tr>
										<td align="right">
											������
										</td>
										<td align="left">
											<input type="text" style="width:200px" name="jtcy2_xm"
												id="jtcy2_xm" readonly="yes"
												value="<bean:write name="rs" property="jtcy2_xm"/>" />
										</td>
										<td align="right">
											�뱾�˹�ϵ��
										</td>
										<td align="left">
											<input type="text" style="width:200px" name="jtcy2_gx"
												id="jtcy2_gx"  readonly="yes"
												value="<bean:write name="rs" property="jtcy2_gx"/>" />
										</td>
									</tr>
									<tr>
										<td align="right">
											���֤���룺
										</td>
										<td align="left">
											<input type="text" style="width:200px" name="jtcy2_sfzh"
												id="jtcy2_sfzh" readonly="yes"
												value="<bean:write name="rs" property="jtcy2_sfzh"/>" />
										</td>
										<td align="right">
											ְҵ��
										</td>
										<td align="left">
											<input type="text" style="width:200px" name="jtcy2_zy"
												id="jtcy2_zy" readonly="yes"
												value="<bean:write name="rs" property="jtcy2_zy"/>" />
										</td>
									</tr>
									<tr>
										<td align="right">
											������ַ��
										</td>
										<td colspan="3" align="left">
											<input type="text" style="width:600px" name="jtcy2_gzdz"
												id="jtcy2_gzdz"  readonly="yes"
												value="<bean:write name="rs" property="jtcy2_gzdz"/>" />
										</td>
									</tr>
									<tr>
										<td align="right">
											ְ��
										</td>
										<td align="left">
											<input type="text" style="width:200px" name="jtcy2_zw"
												id="jtcy2_zw" readonly="yes"
												value="<bean:write name="rs" property="jtcy2_zw"/>" />
										</td>
										<td align="right">
											��ϵ�绰1��
										</td>
										<td align="left">
											<input type="text" style="width:200px" name="jtcy2_lxdh1"
												id="jtcy2_lxdh1" readonly="yes"
												value="<bean:write name="rs" property="jtcy2_lxdh1"/>" />
										</td>
									</tr>
									<tr>
										<td align="right">
											��ϵ�绰2��
										</td>
										<td align="left">
											<input type="text" style="width:200px" name="jtcy2_lxdh2"
												id="jtcy2_lxdh2"  readonly="yes"
												value="<bean:write name="rs" property="jtcy2_lxdh2"/>" />
										</td>
										<td align="right">
											&nbsp;
										</td>
										<td align="left">
											&nbsp;
										</td>
									</tr>
								</table>
							</td>
						</tr>
						<thead>
							<tr>
								<td colspan="4" style="cursor:hand" align="center"
									onclick="document.getElementById('jt3').style.display=(document.getElementById('jt3').style.display==''?'none':'')">
									ѧ����ͥ��Ա��Ϣ3
								</td>
							</tr>
						</thead>
						<tr id="jt3" style="display:none">
							<td colspan="4">
								<table width="100%" class="tbstyle">
									<tr>
										<td align="right">
											������
										</td>
										<td align="left">
											<input type="text" style="width:200px" name="jtcy3_xm"
												id="jtcy3_xm" readonly="yes"
												value="<bean:write name="rs" property="jtcy3_xm"/>" />
										</td>
										<td align="right">
											�뱾�˹�ϵ��
										</td>
										<td align="left">
											<input type="text" style="width:200px" name="jtcy3_gx"
												id="jtcy3_gx"  readonly="yes"
												value="<bean:write name="rs" property="jtcy3_gx"/>" />
										</td>
									</tr>
									<tr>
										<td align="right">
											���֤���룺
										</td>
										<td align="left">
											<input type="text" style="width:200px" name="jtcy3_sfzh"
												id="jtcy3_sfzh"  readonly="yes"
												value="<bean:write name="rs" property="jtcy3_sfzh"/>" />
										</td>
										<td align="right">
											ְҵ��
										</td>
										<td align="left">
											<input type="text" style="width:200px" name="jtcy3_zy"
												id="jtcy3_zy" readonly="yes"
												value="<bean:write name="rs" property="jtcy3_zy"/>" />
										</td>
									</tr>
									<tr>
										<td align="right">
											������ַ��
										</td>
										<td colspan="3" align="left">
											<input type="text" style="width:600px" name="jtcy3_gzdz"
												id="jtcy3_gzdz" readonly="yes"
												value="<bean:write name="rs" property="jtcy3_gzdz"/>" />
										</td>
									</tr>
									<tr>
										<td align="right">
											ְ��
										</td>
										<td align="left">
											<input type="text" style="width:200px" name="jtcy3_zw"
												id="jtcy3_zw" readonly="yes"
												value="<bean:write name="rs" property="jtcy3_zw"/>" />
										</td>
										<td align="right">
											��ϵ�绰1��
										</td>
										<td align="left">
											<input type="text" style="width:200px" name="jtcy3_lxdh1"
												id="jtcy3_lxdh1" readonly="yes"
												value="<bean:write name="rs" property="jtcy3_lxdh1"/>" />
										</td>
									</tr>
									<tr>
										<td align="right">
											��ϵ�绰2��
										</td>
										<td align="left">
											<input type="text" style="width:200px" name="jtcy3_lxdh2"
												id="jtcy3_lxdh2" readonly="yes"
												value="<bean:write name="rs" property="jtcy3_lxdh2"/>" />
										</td>
										<td align="right">
											&nbsp;
										</td>
										<td align="left">
											&nbsp;
										</td>
									</tr>
								</table>
							</td>
						</tr>
					</table>
					<br />
					<div class="buttontool" align="center">
						<button class="button2"
							onclick="refreshForm('modi_stu_info.do?act=save')">
							�� �� �� Ϣ
						</button>
					</div>
					</logic:empty>
				</logic:present>
			</logic:equal>


			<logic:equal name="userOnLine" value="teacher" scope="session">
			  <logic:equal name="userType" value="xy" scope="session">
				 <logic:present name="rs1"> 
					<logic:equal name="dataSaved" value="ok" scope="request">
						<script>
					    alert("����ɹ���");
					    </script>
					</logic:equal>
 					<input type="hidden" id="disableEle" name="disableEle"
						value="xm-xb-xy-nj-zy-bj" />
					<input type="hidden" id="getStuInfo" name="getStuInfo"
						value="xm-xb-xymc-nj-zymc-bjmc" />
					<input type="hidden" id="url" name="url" value="/sjcz/modi_stu_info.jsp" />
					<table width="100%" class="tbstyle">
 					
						<thead>
							<tr>
								<td colspan="4" align="center">
									ѧ��������Ϣ
								</td>
							</tr>
						</thead>
						<tr>
								<td align="right">
									<font color="red">*</font>ѧ�ţ�
								</td>
								<td align="left">
									<html:text name="rs1" property="xh" styleId="xh"
										onkeypress="autoFillStuInfo(event.keyCode,this)" />
									<button onclick="showTopWin('/xgxt/stu_info.do',750,550);"
										class="btn_01" id="buttonFindStu">
										ѡ��
									</button>
								</td>
							<td align="right">
								������
							</td>
							<td align="left">
								<bean:write name="rs1" property="xm" scope="request"/>
							</td>
						</tr>
						<tr>
							<td align="right">
								<bean:message key="lable.xsgzyxpzxy" />��
							</td>
							<td align="left">
								<bean:write name="rs1" property="xymc" />
							</td>
							<td align="right">
								רҵ��
							</td>
							<td align="left">
								<bean:write name="rs1" property="zymc" />
							</td>
						</tr>
						<tr>
							<td align="right">
								�꼶��
							</td>
							<td align="left">
								<bean:write name="rs1" property="nj" />
							</td>
							<td align="right">
								�༶��
							</td>
							<td align="left">
								<bean:write name="rs1" property="bjmc" />
							</td>
						</tr>
					<tr>
							<td align="right">
								����ţ�
							</td>
							<td align="left">
								<input type="text" style="width:200px" name="ssbh" id="ssbh"
									value="<bean:write name="rs1" property="ssbh"/>" />
							</td>
							<td align="right">
								����绰��
							</td>
							<td align="left">
								<input type="text" style="width:200px" name="qsdh" id="qsdh"
									value="<bean:write name="rs1" property="qsdh"/>" />
							</td>
						</tr>
						<tr>
							<td align="right">
								�ֻ����룺
							</td>
							<td align="left">
								<input type="text" style="width:200px" name="sjhm" id="sjhm"
									value="<bean:write name="rs1" property="sjhm"/>" />
							</td>
							<td align="right">
								E-Mail��
							</td>
							<td align="left">
								<input type="text" style="width:200px" name="email" id="email"
									value="<bean:write name="rs1" property="email"/>" />
							</td>
						</tr>
						<tr>
							<td align="right">
								��ͥ���ڵأ�
							</td>
							<td align="left">
								<input type="text" style="width:200px" name="jtszd" id="jtszd"
									value="<bean:write name="rs1" property="jtszd"/>" />
							</td>
							<td align="right">
								��ͥ���ڵ�ַ�ʱࣺ
							</td>
							<td align="left">
								<input type="text" style="width:200px" name="yb" id="yb"
									value="<bean:write name="rs1" property="yb"/>" />
							</td>
						</tr>
						<tr>
							<td align="right">
								����ͨ�ŵ�ַ��
							</td>
							<td colspan="3" align="left">
								<input type="text" style="width:600px" name="brtxdz" id="brtxdz"
									value="<bean:write name="rs1" property="brtxdz"/>" />
							</td>
						</tr>
						<thead>
							<tr>
								<td colspan="4" style="cursor:hand" align="center"
									onclick="document.getElementById('jt1').style.display=(document.getElementById('jt1').style.display==''?'none':'')">
									ѧ����ͥ��Ա��Ϣ1
								</td>
							</tr>
						</thead>
						<tr id="jt1">
							<td colspan="4">
								<table width="100%" class="tbstyle">
									<tr>
										<td align="right">
											������
										</td>
										<td align="left">
											<input type="text" style="width:200px" name="jtcy1_xm"
												id="jtcy1_xm"
												value="<bean:write name="rs1" property="jtcy1_xm"/>" />
										</td>
										<td align="right">
											�뱾�˹�ϵ��
										</td>
										<td align="left">
											<input type="text" style="width:200px" name="jtcy1_gx"
												id="jtcy1_gx"
												value="<bean:write name="rs1" property="jtcy1_gx"/>" />
										</td>
									</tr>
									<tr>
										<td align="right">
											���֤���룺
										</td>
										<td align="left">
											<input type="text" style="width:200px" name="jtcy1_sfzh"
												id="jtcy1_sfzh"
												value="<bean:write name="rs1" property="jtcy1_sfzh"/>" />
										</td>
										<td align="right">
											ְҵ��
										</td>
										<td align="left">
											<input type="text" style="width:200px" name="jtcy1_zy"
												id="jtcy1_zy"
												value="<bean:write name="rs1" property="jtcy1_zy"/>" />
										</td>
									</tr>
									<tr>
										<td align="right">
											������ַ��
										</td>
										<td colspan="3" align="left">
											<input type="text" style="width:600px" name="jtcy1_gzdz"
												id="jtcy1_gzdz"
												value="<bean:write name="rs1" property="jtcy1_gzdz"/>" />
										</td>
									</tr>
									<tr>
										<td align="right">
											ְ��
										</td>
										<td align="left">
											<input type="text" style="width:200px" name="jtcy1_zw"
												id="jtcy1_zw"
												value="<bean:write name="rs1" property="jtcy1_zw"/>" />
										</td>
										<td align="right">
											��ϵ�绰1��
										</td>
										<td align="left">
											<input type="text" style="width:200px" name="jtcy1_lxdh1"
												id="jtcy1_lxdh1"
												value="<bean:write name="rs1" property="jtcy1_lxdh1"/>" />
										</td>
									</tr>
									<tr>
										<td align="right">
											��ϵ�绰2��
										</td>
										<td align="left">
											<input type="text" style="width:200px" name="jtcy1_lxdh1"
												id="jtcy1_lxdh1"
												value="<bean:write name="rs1" property="jtcy1_lxdh1"/>" />
										</td>
										<td align="right">
											&nbsp;
										</td>
										<td align="left">
											&nbsp;
										</td>
									</tr>
								</table>
							</td>
						</tr>
						<thead>
							<tr>
								<td colspan="4" style="cursor:hand" align="center"
									onclick="document.getElementById('jt2').style.display=(document.getElementById('jt2').style.display==''?'none':'')">
									ѧ����ͥ��Ա��Ϣ2
								</td>
							</tr>
						</thead>
						<tr id="jt2" style="display:none">
							<td colspan="4">
								<table width="100%" class="tbstyle">
									<tr>
										<td align="right">
											������
										</td>
										<td align="left">
											<input type="text" style="width:200px" name="jtcy2_xm"
												id="jtcy2_xm"
												value="<bean:write name="rs1" property="jtcy2_xm"/>" />
										</td>
										<td align="right">
											�뱾�˹�ϵ��
										</td>
										<td align="left">
											<input type="text" style="width:200px" name="jtcy2_gx"
												id="jtcy2_gx"
												value="<bean:write name="rs1" property="jtcy2_gx"/>" />
										</td>
									</tr>
									<tr>
										<td align="right">
											���֤���룺
										</td>
										<td align="left">
											<input type="text" style="width:200px" name="jtcy2_sfzh"
												id="jtcy2_sfzh"
												value="<bean:write name="rs1" property="jtcy2_sfzh"/>" />
										</td>
										<td align="right">
											ְҵ��
										</td>
										<td align="left">
											<input type="text" style="width:200px" name="jtcy2_zy"
												id="jtcy2_zy"
												value="<bean:write name="rs1" property="jtcy2_zy"/>" />
										</td>
									</tr>
									<tr>
										<td align="right">
											������ַ��
										</td>
										<td colspan="3" align="left">
											<input type="text" style="width:600px" name="jtcy2_gzdz"
												id="jtcy2_gzdz"
												value="<bean:write name="rs1" property="jtcy2_gzdz"/>" />
										</td>
									</tr>
									<tr>
										<td align="right">
											ְ��
										</td>
										<td align="left">
											<input type="text" style="width:200px" name="jtcy2_zw"
												id="jtcy2_zw"
												value="<bean:write name="rs1" property="jtcy2_zw"/>" />
										</td>
										<td align="right">
											��ϵ�绰1��
										</td>
										<td align="left">
											<input type="text" style="width:200px" name="jtcy2_lxdh1"
												id="jtcy2_lxdh1"
												value="<bean:write name="rs1" property="jtcy2_lxdh1"/>" />
										</td>
									</tr>
									<tr>
										<td align="right">
											��ϵ�绰2��
										</td>
										<td align="left">
											<input type="text" style="width:200px" name="jtcy2_lxdh2"
												id="jtcy2_lxdh2"
												value="<bean:write name="rs1" property="jtcy2_lxdh2"/>" />
										</td>
										<td align="right">
											&nbsp;
										</td>
										<td align="left">
											&nbsp;
										</td>
									</tr>
								</table>
							</td>
						</tr>
						<thead>
							<tr>
								<td colspan="4" style="cursor:hand" align="center"
									onclick="document.getElementById('jt3').style.display=(document.getElementById('jt3').style.display==''?'none':'')">
									ѧ����ͥ��Ա��Ϣ3
								</td>
							</tr>
						</thead>
						<tr id="jt3" style="display:none">
							<td colspan="4">
								<table width="100%" class="tbstyle">
									<tr>
										<td align="right">
											������
										</td>
										<td align="left">
											<input type="text" style="width:200px" name="jtcy3_xm"
												id="jtcy3_xm"
												value="<bean:write name="rs1" property="jtcy3_xm"/>" />
										</td>
										<td align="right">
											�뱾�˹�ϵ��
										</td>
										<td align="left">
											<input type="text" style="width:200px" name="jtcy3_gx"
												id="jtcy3_gx"
												value="<bean:write name="rs1" property="jtcy3_gx"/>" />
										</td>
									</tr>
									<tr>
										<td align="right">
											���֤���룺
										</td>
										<td align="left">
											<input type="text" style="width:200px" name="jtcy3_sfzh"
												id="jtcy3_sfzh"
												value="<bean:write name="rs1" property="jtcy3_sfzh"/>" />
										</td>
										<td align="right">
											ְҵ��
										</td>
										<td align="left">
											<input type="text" style="width:200px" name="jtcy3_zy"
												id="jtcy3_zy"
												value="<bean:write name="rs1" property="jtcy3_zy"/>" />
										</td>
									</tr>
									<tr>
										<td align="right">
											������ַ��
										</td>
										<td colspan="3" align="left">
											<input type="text" style="width:600px" name="jtcy3_gzdz"
												id="jtcy3_gzdz"
												value="<bean:write name="rs1" property="jtcy3_gzdz"/>" />
										</td>
									</tr>
									<tr>
										<td align="right">
											ְ��
										</td>
										<td align="left">
											<input type="text" style="width:200px" name="jtcy3_zw"
												id="jtcy3_zw"
												value="<bean:write name="rs1" property="jtcy3_zw"/>" />
										</td>
										<td align="right">
											��ϵ�绰1��
										</td>
										<td align="left">
											<input type="text" style="width:200px" name="jtcy3_lxdh1"
												id="jtcy3_lxdh1"
												value="<bean:write name="rs1" property="jtcy3_lxdh1"/>" />
										</td>
									</tr>
									<tr>
										<td align="right">
											��ϵ�绰2��
										</td>
										<td align="left">
											<input type="text" style="width:200px" name="jtcy3_lxdh2"
												id="jtcy3_lxdh2"
												value="<bean:write name="rs1" property="jtcy3_lxdh2"/>" />
										</td>
										<td align="right">
											&nbsp;
										</td>
										<td align="left">
											&nbsp;
										</td>
									</tr>
								</table>
							</td>
						</tr>
					</table>
					<br />
					<div class="buttontool" align="center">
						<button class="button2"
							onclick="refreshForm('modi_stu_info.do?act=save');this.disabled=true">
							�� �� �� Ϣ
						</button>
					</div>
				</logic:present>
				</logic:equal>
			</logic:equal>			
		</html:form>
		<logic:equal value="true" name="result">
			<script>
				alert("�����ɹ�!");				
			</script>
		</logic:equal>
		<logic:equal value="false" name="result">
			<script>
				alert("����ʧ��!");				
			</script>
		</logic:equal>
	</body>
</html>
