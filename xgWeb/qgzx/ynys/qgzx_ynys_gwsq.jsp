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
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
	%>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<base target="_self">
	<script language="javascript" src="js/function.js"></script>
	<script language="javascript" src="js/qgzxFunction.js"></script>
	<body>
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
				<p align="center" />
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
							<td width="22%" align="right">
								<font color="red">*</font>ѧ�ţ�
							</td>
							<td width="7%" align="left">
								<html:text name='rs' property="xh" styleId="xh"
									onkeypress="autoFillStuInfo(event.keyCode,this)" />
								<button type="button" onClick="showTopWin('/xgxt/stu_info.do',750,550);"
									class="btn_01" id="buttonFindStu">
									ѡ��
								</button>
							</td>
						</logic:equal>
						<logic:equal name="userOnLine" value="student" scope="session">
							<td width="35%" align="right">
								<font color="red">*</font>ѧ�ţ�
							</td>
							<td width="10%" align="left">
								<bean:write name='rs' property="xh" />
							</td>
						</logic:equal>
						<td width="24%" align="right">
							<font color="red">*</font>��λ���ƣ�
						</td>
						<td width="2%" align="left">
							<html:select name="rs" property="xmdm" styleId="xmdm"
								style="width:150px" >
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
							<html:text name='rs' property="lxdh" readonly="true"
								styleId="lxdh" />
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
							����ţ�
						</td>
						<td align="left">
							<html:text name='rs' property="ssbh" readonly="true" />
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
							���壺
						</td>
						<td align="left">
							<html:text name='rs' property="mzmc" readonly="true" />
						</td>


					</tr>

					<tr>
						<td align="right">
							��ͥ��ַ��
						</td>
						<td>
							<bean:write name='rs' property="jtdz" />
						</td>
						<td align="right">
							������ò��
						</td>
						<td>
							<html:text name='rs' property="zzmmmc" readonly="true" />
						</td>
					</tr>
					<tr>
						<td align="right">
							��ͥ�ʱࣺ
						</td>
						<td>
							<bean:write name='rs' property="jtyb" />
						</td>
						<td align="right">
							��ͥ�绰��
						</td>
						<td>
							<html:text name='rs' property="jtdh" readonly="true" />
						</td>
					</tr>
					<tr>

						<td align="right">
							��ͥ��Ա��
						</td>
						<td colspan="4">
							<html:text name="rs" property="jtcy" styleId="jtcy"
								style="width:100%;height:30px" />
						</td>
					</tr>
					<tr>
						<td align="right">
							�� ��
						</td>
						<td>

							<html:select name="rs" property="xg" styleId="xg">
								<html:option value="">------��ѡ��------</html:option>
								<html:option value="1">1</html:option>
								<html:option value="2">2</html:option>
								<html:option value="3">3</html:option>
								<html:option value="4">4</html:option>
								<html:option value="5">5</html:option>
								<html:option value="6">6</html:option>
								<html:option value="7">7</html:option>
								<html:option value="8">8</html:option>
								<html:option value="9">9</html:option>
								<html:option value="10">10</html:option>
							</html:select>
							<br />
							(����Ϊ1������Ϊ10��
						<td align="right">
							ƶ���ȼ���
						</td>
						<td>
							<html:select name="rs" property="pkdj" styleId="pkdj">
								<html:option value="">------��ѡ��------</html:option>
								<html:option value="1">1</html:option>
								<html:option value="2">2</html:option>
								<html:option value="3">3</html:option>
							</html:select>
						</td>
					</tr>
					<tr>
						<td align="right">
							��ͥ�����룺
						</td>
						<td width="200px">
							<html:text name="rs" property="jtysr" styleId="jtysr"
								style="width:140px" />
							Ԫ/��
						</td>
						<td align="right">
							�س���
						</td>
						<td>
							<html:text name="rs" property="yhtc" styleId="yhtc" />
						</td>
					</tr>
					<tr>
						<td align="right">
							������ѧ���
						</td>
						<td>
							<html:select name="rs" property="gjzxdk" styleId="gjzxdk">
								<html:option value="��">��</html:option>
								<html:option value="��">��</html:option>
							</html:select>
						</td>
						<td align="right">
							�ɲμ��ڹ���ѧʱ�䣺
						</td>
						<td>
							<html:select name="rs" property="xskysj" styleId="xskysj"
								style="width:150px" onchange="selIndex(this,'kcjqgzxsj')">
								<html:option value=""></html:option>
								<html:options collection="kysjList" property="kcjsjdm"
									labelProperty="kcjsjmc" />
							</html:select>
						</td>
					</tr>
					<tr>
						<td align="right">
							����������
						</td>
						<td>
							<html:textarea name="rs" property="zzqk" styleId="zzqk"
								style="width:200px;height:45px" />
						</td>
						<td align="right">
							ѧ������ʱ�䣺
						</td>
						<td>
							<html:textarea name="rs" property="kcjqgzxsj" styleId="kcjqgzxsj"
								readonly="true" style="width:200px;height:45px" />
						</td>
					</tr>
					<tr>
						<td align="right">
							��ţ�
						</td>
						<td>
							${rs.bh}
						</td>
						<td align="right">
							���ţ�
						</td>
						<td>
							${rs.gh}
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
					<tr align="left" style="height:22px">
						<td align="right">
							�Ͷ�����
						</td>
						<td colspan="3">
							<html:textarea name='rs' property='ldyx' styleId="ldyx"
								style="width:99%" rows='5' />
						</td>
					</tr>

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
							ѧУ��������
						</td>
						<td colspan="3">
							${rs.xxshyj}
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
	</body>
</html>
