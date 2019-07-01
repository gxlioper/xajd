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
	<script language="javascript" src="js/function.js"></script>
	<script language="javascript" src="js/qgzxFunction.js"></script>
	<body>
		<logic:equal value="yes" name="IsKns">
		<html:form action="/data_search" method="post">
			<div class="title">
			   <logic:equal name="do" value="no">
				<div class="title_img" id="title_m">
					��ǰλ�ã��ڹ���ѧ - ��λ���� - ��д�����
				</div>
				</logic:equal>
				<logic:equal name="do" value="yes">
				<div class="title_img" id="title_m">
					��ǰλ�ã��ڹ���ѧ - ��λ���� - �޸������
				</div>
				</logic:equal>
			</div>
			<logic:empty name="rs">
				<br />
				<br />
				<p align="center">
					�д�������
				</p>
			</logic:empty>
			<logic:notEmpty name="rs">
				<logic:equal name="rs" property="stuExists" value="no">
					<script>
    				alert("�������ѧ����Ч!");
    				</script>
				</logic:equal>
				<logic:equal name="sqsjFlag" value="1">
					<script>
    				alert("�����趨ʱ�䷶Χ��,�ݲ���������!");
    				location.href="about:blank";
    				</script>
				</logic:equal>
				<input type="hidden" id="disableEle" name="disableEle"
					value="xm-xb-xy-nj-zy-bj" />
				<input type="hidden" id="getStuInfo" name="getStuInfo"
					value="xm-xb-xymc-nj-zymc-bjmc" />
				<input type="hidden" id="url" name="url" value="/post_stu_apply.do" />
				<input type="hidden" id="gwsbsj" name="gwsbsj" />
				<table width="100%" id="rsT" class="tbstyle">
					<thead>
						<tr style="height:22px">
							<td colspan="4" align="center">
							<logic:equal name="do" value="no">
								<b>��д�����</b>
							</logic:equal>
							<logic:equal name="do" value="yes">
							   <b>�޸������</b>
							</logic:equal>
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
						<td align="left">
							<html:select name="rs" property="xmdm" styleId="xmdm" style="width:150px"
								>
								<html:option value=""></html:option>
								<html:options collection="gwList" property="gwdmgwsbsj"
									labelProperty="gwdm" />
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
							���
						</td>
						<td align="left">
							<html:text name="rs" property="nd" readonly="true"></html:text>
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
							<html:text name="rs" property="xn" readonly="true"></html:text>
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
							<html:text name="rs" property="xq" readonly="true"></html:text>
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
					<logic:notEqual value="11417" name="xxdm">
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
						    <logic:present name="showshgc">
						    	<html:select name="rs" property="xskysj" styleId="xskysj" style="width:150px" onchange="selIndex(this,'kcjqgzxsj')">
								<html:option value=""></html:option>
								<html:options collection="kysjList" property="kcjsjdm"
									labelProperty="kcjsjmc" />
							</html:select>
						    </logic:present>
						    <logic:notPresent name="showshgc">
								<html:text name='rs' property="kcjqgzxsj" />
							</logic:notPresent>
						</td>
					</tr>
					</logic:notEqual>
					<logic:equal value="11417" name="xxdm">
						<tr style="height:22px">
						<td align="right">
							רҵ��
						</td>
						<td align="left" colspan="3">
							<bean:write name='rs' property="zymc" />
						</td>						
					</tr>
					</logic:equal>
					<tr style="height:22px">
						<td align="right">
							�༶��
						</td>
						<td align="left">
							<bean:write name='rs' property="bjmc" />
						</td>
						<td align="right">
						</td>
						<td align="left">
						    <bean:write name='rs' property="sfpks"/>
						</td>
					</tr>
					<logic:present name="showshgc">
					<tr>
						<td align="right">
							���ţ�
						</td>
						<td>
							<html:text name='rs' property="kh" />
						</td>
						<td align="right">
							ѧ������ʱ�䣺
						</td>
						<td>
							<html:text name="rs" property="kcjqgzxsj" styleId="kcjqgzxsj" readonly="true"/>
						</td>
					</tr>
					</logic:present>
					<logic:present name="showshgc">
					<tr>
						<td align="right">
							������ò��
						</td>
						<td>
							<bean:write name='rs' property="zzmm"/>
						</td>
						<td align="right">
						</td>
						<td>
						</td>
					</tr>
					<tr>
						<td align="right">
							����������
						</td>
						<td colspan="3">
							<html:textarea name='rs' property='gzjl' styleId="gzjl"
								style="width:99%" rows='5' />
						</td>
					</tr>
					</logic:present>
					<tr align="left" style="height:22px">
						<td align="right">
							�������ɣ�
						</td>
						<td colspan="3">
							<html:textarea name='rs' property='xssq' styleId="xssq"
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
					<logic:equal value="�㽭��ѧ" name="xxmc">
					<logic:present name="kxList">
					<tr align="left" style="height:22px">
						<td colspan="4">
							<table border="0" cellpadding="0" cellspacing="0" align="center">
								<tr>
									<td align="center">ʱ��</td>
									<td>����һ</td>
									<td>���ڶ�</td>
									<td>������</td>
									<td>������</td>
									<td>������</td>
									<td>������</td>
									<td>������</td>
								</tr>								
								<logic:iterate id="kxsj" name="kxList">
								<tr>
									<td>${kxsj.sj}</td>
									<td align="center">${kxsj.mon}</td>
									<td align="center">${kxsj.tue}</td>
									<td align="center">${kxsj.wed}</td>
									<td align="center">${kxsj.thu}</td>
									<td align="center">${kxsj.fri}</td>
									<td align="center">${kxsj.sat}</td>
									<td align="center">${kxsj.sun}</td>
								</tr>
								</logic:iterate>								
							</table>
						</td>
					</tr>
					</logic:present>
					<logic:present name="kxbz">
					<tr align="left" style="height:22px">
						<td colspan="4" align="center">
						��ά������ʱ��
						</td>
					</tr>
					</logic:present>
					</logic:equal>
				</table>
				<div class="buttontool" align="center">
				<logic:equal name="do" value="no" scope="request">
					<button type="button" class="button2"
						onclick="dataDoSaveApply3('/xgxt/applySave.do','xmdm','gwsq')">
						�� �� �� ��
					</button>
				</logic:equal>
				<logic:equal name="do" value="yes" scope="request">
					<button type="button" class="button2"
						onclick="dataDoSaveApply3('/xgxt/applySave.do','xmdm','gwsq')">
						�� �� �� ��
					</button>
				</logic:equal>
					<logic:notPresent name="zdy">
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<button type="button" class="button2" onclick="expAppTab('rsT','�ڹ���ѧ��λ�����','')">
						�� ӡ Ԥ ��
					</button>
					</logic:notPresent>
					<logic:present name="zdy">
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<button type="button" class="button2" onclick="printReport('qgzx_bb_gwsqb.do')">
						�� ӡ Ԥ ��
					</button>
					</logic:present>
				</div>
			</logic:notEmpty>
			<logic:notEmpty name="inserted">
				<logic:equal name="inserted" value="ok">
					<script>
    alert("����ɹ���");
    </script>
				</logic:equal>
				<logic:equal name="inserted" value="no">
					<script>
    alert("����ʧ�ܣ�");
    </script>
				</logic:equal>
				<logic:equal name="inserted" value="nopks">
					<script>
    alert("����ʧ�ܣ�������ƶ������������");
    </script>
				</logic:equal>
			</logic:notEmpty>
		</html:form>
		</logic:equal>
		<logic:equal value="no" name="IsKns">
			<div align="center" ><font color="red" size="5">ֻ���������ſ�������</font></div>
		</logic:equal>
	</body>
</html>
