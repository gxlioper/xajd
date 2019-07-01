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
		<title><bean:message key="lable.title" />
		</title>
		<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
		<meta http-equiv="Pragma" http-equiv="no-cache" />
		<meta http-equiv="Cache-Control"
			http-equiv="no-cache, must-revalidate" />
		<meta http-equiv="Expires" http-equiv="0" />

		<meta name="Copyright" content="������� zfsoft" />
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
	<script language="javascript" src="js/function.js"></script>
	<script type='text/javascript'
		src='/xgxt/dwr/interface/getOtherData.js'></script>
	<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
	<script type='text/javascript' src='dwr/util.js'></script>
	<script language="javascript" src="js/qgzxFunction.js"></script>
	<script type="text/javascript">
	function getDw(){
		var pk = document.getElementById("xmdm").value;
		if(pk!=null && pk!=""){
		getOtherData.getDwmc(pk,function(data){
			document.getElementById("sqdw").value = data[0];
			document.getElementById("fzr").value = data[1];
			document.getElementById("gwlxdh").value = data[2];
		});
		}		
	}
	
	function chkisDataExist(obj){
		var value = obj.split("-");
		if($("stuPass")){
			if(document.getElementById("stuPass").value == "false"){
				alert("��ѧ��û��ͨ��<bean:message key="lable.xsgzyxpzxy" />�Ƽ�����ʱ���ܰ��Ÿ�λ��");
				return false;
			}
		}
		for(var i=0; i<value.length; i++){
			if(document.getElementById(value[i]).value==""){
				alert("�뽫��\*�ŵ���Ŀ��д������");				
				return false;
			}
		}
		refreshForm('qgzxZgdzdx.do?method=saveGwfp');
	}
	</script>
	<body>
		<html:form action="/qgzxZgdzdx.co" method="post">
			<div class="title">
				<div class="title_img" id="title_m">
					��ǰλ�ã��ڹ���ѧ - ���� - ֱ�ӷ����λ
				</div>
			</div>
			<logic:equal value="student" name="userOnLine">
				<br />
				<br />
				<center>
					��ҳ��ֻ��ѧУ�û��ſ��Է��ʣ�
				</center>
			</logic:equal>
			<logic:equal value="xy" name="userType">
				<br />
				<br />
				<center>
					��ҳ��ֻ��ѧУ�û��ſ��Է��ʣ�
				</center>
			</logic:equal>

			<logic:notEqual value="xy" name="userType">
				<logic:notEqual value="stu" name="userType">
<%--				<input type="hidden" name="stuPass" id="stuPass" value="${stuPass}"/><!-- �ж�ѧ���Ƿ�ͨ��<bean:message key="lable.xsgzyxpzxy" />���Ƽ� -->--%>
					<logic:equal value="true" name="fpFlag">
						<logic:empty name="rs">
							<br />
							<br />
							<p align="center">
								�д�������
							</p>
						</logic:empty>
						<logic:notEmpty name="rs">
							<input type="hidden" id="xxdm" value="${xxdm }"/>
							<input type="hidden" id="getStuInfo" name="getStuInfo"
								value="xm-xb-xymc-nj-zymc-bjmc" />
							<input type="hidden" id="url" name="url"
								value="/qgzx/zgdzdx/gwfp.jsp" />
							<input type="hidden" name="xh" id="xh"
								value="<bean:write name='rs' property="xh" />">
							<input type="hidden" name="mes" id="mes" value="${hmdMember}">
							<table width="100%" id="rsT" class="tbstyle">
								<thead>
									<tr style="height:22px">
										<td height="18" colspan="4" align="center">
											<b>��д�����</b>
										</td>
									</tr>
								</thead>
								<tr style="height:22px">
									<logic:equal name="userOnLine" value="teacher" scope="session">
										<td align="right">
											<font color="red">*</font>ѧ�ţ�
										</td>
										<td align="left">
											<html:text name='rs' property="xh" styleId="xh"
												onkeypress="autoFillStuInfo(event.keyCode,this)" />
											<button type="button" onclick="showTopWin('/xgxt/stu_info.do',750,550);"
												class="btn_01" id="buttonFindStu">
												ѡ��
											</button>
										</td>
									</logic:equal>
									<logic:equal name="userOnLine" value="student" scope="session">
										<td align="right">
											<font color="red">*</font>ѧ�ţ�
										</td>
										<td align="left">
											<bean:write name='rs' property="xh" />
										</td>
									</logic:equal>
									<td align="right">
										<font color="red">*</font>��λ���ƣ�
									</td>
									<logic:equal value="modi" name="doType">
										<td align="left">
											<input type="hidden" id="isModi" name="isModi"
												value="<bean:write name="doType" scope="request"/>" />
											<input type="hidden" name="gwmc" id="gwmc"
												value="<bean:write name='rs' property='gwmc'/>">
											<html:select name="rs" property="gwdm" styleId="gwmc"
												style="width:150px" disabled="true" onchange="">
												<html:option value=""></html:option>
												<html:options collection="gwList" property="gwdmgwsbsj"
													labelProperty="gwdm" />
											</html:select>
										</td>
									</logic:equal>
									<logic:notEqual value="modi" name="doType">
										<td align="left">
											<html:select name="rs" property="gwmc" styleId="gwmc"
												style="width:150px" onchange="">
												<html:option value=""></html:option>
												<html:options collection="gwList" property="gwdmgwsbsj"
													labelProperty="gwdm" />
											</html:select>
										</td>
									</logic:notEqual>
								</tr>
								<tr style="height:22px">
									<td align="right">
										������
									</td>
									<td align="left">
										<bean:write name='rs' property="xm" />
									</td>
									<td align="right">
										��ȣ�
									</td>
									<td align="left">
										<html:text name="rs" property="nd" readonly="true" />
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
										ѧ�꣺
									</td>
									<td align="left">
										<html:text name="rs" property="xn" readonly="true" />
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
										ѧ�ڣ�
									</td>
									<td align="left">
										<html:text name="rs" property="xq" readonly="true" />
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
										<html:text name='rs' property="lxdh" />
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
										�ɲμ��ڹ���ѧʱ�䣺
									</td>

									<td align="left">
										<html:text name='rs' property="kcjqgzxsj" />
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
										�Ƿ�ƶ������
									</td>
									<td align="left">
										<bean:write name='rs' property="sfpks" />
										<input type="hidden" name="sfpks" id="sfpks" />
									</td>
								</tr>
								<tr align="left" style="height:22px">
									<td align="right">
										�������ɣ�
									</td>
									<td colspan="3">
										<html:textarea name='rs' property='sqly' styleId="sqly"
											style="width:99%" rows='5' />
									</td>
								</tr>
								<tr align="left" style="height:22px">
									<td align="right">
										��ע��
									</td>
									<td colspan="3">
										<html:textarea name='rs' property='bz' styleId="bz"
											style="width:99%" rows='5' />
									</td>
								</tr>
							</table>
							<div class="buttontool" align="center">
								<button type="button" class="button2" onclick="chkisDataExist('xh-gwmc')">
									�� �� �� ��
								</button>
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<button type="button" class="button2"
									onclick="expAppTab('rsT','�ڹ���ѧ��λ�����','')">
									�� ӡ Ԥ ��
								</button>
							</div>
<%--							<logic:present name="stuPass">--%>
<%--							<logic:equal value="false" name="stuPass">--%>
<%--								<script>--%>
<%--									alert("��ѧ��û��ͨ��<bean:message key="lable.xsgzyxpzxy" />�Ƽ�����ʱ���ܰ��Ÿ�λ��");--%>
<%--								</script>--%>
<%--							</logic:equal>--%>
<%--							</logic:present>--%>
						</logic:notEmpty>
					</logic:equal>
					<!--������ʱ����  -->
				</logic:notEqual>
				<!--��ѧ���û�-->
			</logic:notEqual>
			<logic:equal value="false" name="fpFlag">
								<script>
									alert("���ڲ���ѧУֱ�ӷ����λ��ʱ���ڣ��޷�ֱ�ӷ��䣡");
								</script>
							</logic:equal>
			<!--��<bean:message key="lable.xsgzyxpzxy" />�û�-->
			<logic:notEmpty name="result">
				<logic:equal name="result" value="true">
					<script>
				    	alert("�����ɹ���");
				    </script>
				</logic:equal>
				<logic:equal name="result" value="false">
					<logic:present name="hmdMember">
						<script>
				    	alert("��ѧ���Ѿ��������ڹ���ѧ���������޷������λ��");
				    </script>
					</logic:present>
					<logic:notPresent name="hmdMember">
						<script>
				    	alert("����ʧ�ܣ�");
				    </script>
					</logic:notPresent>
				</logic:equal>
			</logic:notEmpty>
		</html:form>
	</body>
</html>
