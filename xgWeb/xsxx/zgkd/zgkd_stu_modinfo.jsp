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
		<meta http-equiv="Cache-Control" http-equiv="no-cache, must-revalidate" />
		<meta http-equiv="Expires" http-equiv="0" />
		<meta name="Copyright" content="������� zfsoft" />
	</head>
	<%
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
	%>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css" type="text/css" media="all" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="js/calendar.css" type="text/css" media="all" />
	<base target="_self">

	<script language="javascript" src="js/function.js"></script>
	<script language="javascript" src="js/commanFunction.js"></script>
	<script language="javascript" src="js/xsxx/xsxxzgkdFunction.js"></script>
	<script language="javascript" src="js/calendar.js"></script>
	<script language="javascript" src="js/calendar-zh.js"></script>
	<script language="javascript" src="js/calendar-setup.js"></script>
	<script type="text/javascript" src="/xgxt/dwr/engine.js"></script>
	<script type="text/javascript" src="/xgxt/dwr/util.js"></script>
	<script>
		
	</script>
	<body onload="showColumns()">
		<html:form action="/xsxx_zgkd.do" method="post">
			<input type="hidden" id="zdList" name="zdList" value="<bean:write name="zdList"/>"/>
			<input type="hidden" name="url" id="url" value="/xsxx/zgkd/zgkd_stu_modinfo.jsp">
			<input type="hidden" name="redirect" id="redirect" value="">
			<input type="hidden" name="variable" id="variable" value="">
			<div class="title">
				<div class="title_img" id="title_m">
					��ǰλ�ã�ѧ����Ϣ - ��Ϣ�޸� - �޸ĸ�����Ϣ
				</div>
			</div>
			<logic:equal name="userType" value="admin" scope="session">
				<br />
				<br />
				<br />
				<p align="center">
					��ҳ��ֻ��ѧ����<bean:message key="lable.xsgzyxpzxy" />�û����Է���
				</p>
			</logic:equal>

			<logic:equal name="userType" value="xx" scope="session">
				<br />
				<br />
				<br />
				<p align="center">
					��ҳ��ֻ��ѧ����<bean:message key="lable.xsgzyxpzxy" />�û����Է���
				</p>
			</logic:equal>
			<logic:equal value="student" name="userOnLine">
			<logic:equal name="sqsjFlag" value="1">
				<script>
		   			 alert("�����趨ʱ�䷶Χ��,�ݲ����Ÿù���!");
		    		 location.href="about:blank";
   			 	</script>
			</logic:equal>
			</logic:equal>
			<table class="tbstyle" id="rsTable" width="100%">
			<thead>
			<tr>
			<td align="center" colspan="4">
				������Ϣ�޸�
				<logic:present name="shjg">
				<font color="red">
				(���δͨ��)
				</font>
				</logic:present>
			</td>
			</tr>
			</thead>
			<tr>
			<td align="right">
				<font color="red">*</font>ѧ�ţ�
			</td>
			<td>
				<html:text property="xh" name="rs" disabled="true" styleId="xh" maxlength="20"/>	
				<html:hidden property="xh" name="rs"/>			
				<logic:equal value="xy" name="userType">
					<button type="button" align="left" class="button2"
						onclick="showTopWin('/xgxt/stu_info.do',750,550);"
						id="buttonFindStu">
						ѡ��
					</button>
				</logic:equal>
			</td>
			<td align="right">
				������
			</td>
			<td>
				<html:text property="xm" name="rs" disabled="true" maxlength="16" styleId="xm"/>
			</td>
			</tr>
			
			<tr>
			<td align="right">
				<font color="red">*</font><bean:message key="lable.xsgzyxpzxy" />��
			</td>
			<td>
				<html:select property="xydm" name="rs" disabled="true" styleId="xy">
					<html:option value=""></html:option>
					<html:options collection="xyList" property="xydm" labelProperty="xymc"/>
				</html:select>
			</td>
			<td align="right">
				<font color="red">*</font>רҵ��
			</td>
			<td>
				<html:select property="zydm" name="rs" disabled="true" styleId="zy">
					<html:option value=""></html:option>
					<html:options collection="zyList" property="zydm" labelProperty="zymc"/>
				</html:select>
			</td>
			</tr>
			
			<tr>
			<td align="right">
				�꼶��
			</td>
			<td>
				<html:select property="nj" name="rs" disabled="true" styleId="nj">
					<html:option value=""></html:option>
					<html:options collection="njList" property="nj" labelProperty="nj"/>
				</html:select>
			</td>
			<td align="right">
				<font color="red">*</font>�༶��
			</td>
			<td>
				<html:select property="bjdm" name="rs" disabled="true" styleId="bj">
					<html:option value=""></html:option>
					<html:options collection="bjList" property="bjdm" labelProperty="bjmc"/>
				</html:select>
			</td>
			</tr>
			
			<tr>
			<td align="right">
				�Ա�
			</td>
			<td>
				<html:radio property="xb" value="��" name="rs" disabled="true" styleId="xbn">��</html:radio>
				<html:radio property="xb" value="Ů" name="rs" disabled="true"  styleId="xbv">Ů</html:radio>
			</td>
			<td align="right">
				ѧ�ƣ�
			</td>
			<td>
				<html:text property="xz" name="rs" disabled="true" maxlength="2" onkeyup="value=value.replace(/[^\d]/g,'')"/>��
			</td>
			</tr>
			
			<tr>
			<td align="right">
				���壺
			</td>
			<td>
				<html:select property="mz" name="rs" disabled="true" styleId="mz">
					<html:option value=""></html:option>
					<html:options collection="mzList" property="mzdm" labelProperty="mzmc"/>
				</html:select>
			</td>
			<td align="right">
				������ò��
			</td>
			<td>
				<html:select property="zzmm" name="rs" disabled="true" styleId="zzmm">
					<html:option value=""></html:option>
					<html:options collection="zzmmList" property="zzmmdm" labelProperty="zzmmmc"/>
				</html:select>
			</td>
			</tr>
			
			<tr>
			<td align="right">
				ѧ��״̬��
			</td>
			<td>
				<html:select property="xjztm" name="rs" disabled="true" styleId="xjztm">
					<html:option value=""></html:option>
					<html:options collection="xjztList" property="zxdmmc" labelProperty="zxdmmc"/>
				</html:select>
			</td>
			<td align="right">
				�������ڣ�
			</td>
			<td>
				<html:text property="csrq" name="rs" disabled="true" readonly="" onclick="return showCalendar('csrq','y-mm-dd');" styleId="csrq"/>
			</td>
			</tr>
			
			<tr>
			<td align="right">
				����ƴ����
			</td>
			<td>
				<html:text property="xmpy" name="rs" disabled="true" styleId="xmpy" maxlength="32"/>
			</td>
			<td align="right">
				��������
			</td>
			<td>
				<html:text property="cym" name="rs" disabled="true" styleId="cym" maxlength="16"/>
			</td>
			</tr>
			
			<tr>
			<td align="right">
				��ߣ�
			</td>
			<td>
				<html:text property="sg" name="rs" disabled="true" styleId="sg" maxlength="10" onkeyup="value=value.replace(/[^\d]/g,'')"/> ����
			</td>
			<td align="right">
				���أ�
			</td>
			<td>
				<html:text property="tz" name="rs" disabled="true" styleId="tz" maxlength="10" onkeyup="value=value.replace(/[^\d]/g,'')"/> ǧ��
			</td>
			</tr>
			
			<tr>
			<td align="right">
				���֤�ţ�
			</td>
			<td>
				<html:text property="sfzh" name="rs" disabled="true" styleId="sfzh" maxlength="18"/>
			</td>
			<td align="right">
				�س���
			</td>
			<td>
				<html:text property="tc" name="rs" disabled="true" styleId="tc" maxlength="32"/>
			</td>
			</tr>
			
			<tr>
			<td align="right">
				������ʽ��
			</td>
			<td>
				<html:text property="pyfs" name="rs" disabled="true" styleId="pyfs" maxlength="32"/>
			</td>
			<td align="right">
				������Σ�
			</td>
			<td>
				<html:text property="pycc" name="rs" disabled="true" styleId="pycc" maxlength="32"/>
			</td>
			</tr>
			
			<tr>
			<td align="right">
				��ѧ��ʽ��
			</td>
			<td>
				<html:text property="rxfs" name="rs" disabled="true" styleId="rxfs" maxlength="32"/>
			</td>
			<td align="right">
				�������
			</td>
			<td>
				<html:text property="kslb" name="rs" disabled="true" styleId="kslb" maxlength="32"/>
			</td>
			</tr>
			
			<tr>
			<td align="right">
				��Դ������
			</td>
			<td>
				<html:text property="syd" name="rs" disabled="true" styleId="syd" maxlength="25"/>
			</td>
			<td align="right">
				���᣺
			</td>
			<td>
				<html:text property="jg" name="rs" disabled="true" styleId="jg" maxlength="10"/>
			</td>
			</tr>
			
			<tr>
			<td align="right">
				�������䣺
			</td>
			<td>
				<html:text property="dzyx" name="rs" disabled="true" styleId="dzyx" maxlength="32"/>
			</td>
			<td align="right">
				��ϵ�绰��
			</td>
			<td>
				<html:text property="lxdh" name="rs" disabled="true" styleId="lxdh" maxlength="15"/>
			</td>
			</tr>
			
			<tr>
			<td align="right">
				�ֻ����룺
			</td>
			<td>
				<html:text property="sjhm" name="rs" disabled="true" styleId="sjhm" maxlength="11" onkeyup="value=value.replace(/[^\d]/g,'')"/>
			</td>
			<td align="right">
				��ͥ��ַ��
			</td>
			<td>
				<html:text property="jtszd" name="rs" disabled="true" styleId="jtszd" maxlength="25"/>
			</td>
			</tr>
			
			<tr>
			<td align="right">
				��ͥ�ʱࣺ
			</td>
			<td>
				<html:text property="yb" name="rs" disabled="true" styleId="yb" maxlength="10" onkeyup="value=value.replace(/[^\d]/g,'')"/>
			</td>
			<td align="right">
				��ͥ���������
			</td>
			<td>
				<html:text property="jjzk" name="rs" disabled="true" styleId="jjzk" maxlength="100"/>
			</td>
			</tr>
			
			<tr>
			<td align="right">
				�����ţ�
			</td>
			<td>
				<html:text property="ssbh" name="rs" disabled="true" styleId="ssbh" maxlength="20"/>
			</td>
			<td align="right">	
				ƶ���ȼ���		
			</td>
			<td>
				<bean:write name="pksdj"/>				
			</td>
			</tr>
			<tr>
			<td align="right">
				���ҵ绰��
			</td>
			<td colspan="3">
				<html:text property="qsdh" name="rs" disabled="true" styleId="qsdh" maxlength="10" style="width:100%"/>
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
			<fieldset>
				<legend>
					��ͥ��Ա1
				</legend>
			<table class="tbstyle" width="100%">						
			<tr>
			<td align="right">
				������
			</td>
			<td>
				<html:text property="jtcy1_xm" name="rs" disabled="true" styleId="jtcy1_xm" maxlength="25"/>
			</td>
			<td align="right">
				�뱾�˹�ϵ��
			</td>
			<td>
				<html:text property="jtcy1_gx" name="rs" disabled="true" styleId="jtcy1_gx" maxlength="10"/>
			</td>
			</tr>
			
			<tr>
			<td align="right">
				�������ڣ�
			</td>
			<td>
				<html:text property="jtcy1_nl" name="rs" disabled="true" styleId="jtcy1_nl" readonly="" onclick="return showCalendar('jtcy1_nl','y-mm-dd');" styleId="jtcy1_nl"/>
			</td>
			<td align="right">
				���֤�ţ�
			</td>
			<td>
				<html:text property="jtcy1_sfzh" name="rs" disabled="true" styleId="jtcy1_sfzh" maxlength="18"/>
			</td>
			</tr>
			
			<tr>
			<td align="right">
				���壺
			</td>
			<td>
				<html:select property="jtcy1_mz" name="rs" disabled="true" styleId="jtcy1_mz">
					<html:option value=""></html:option>
					<html:options collection="mzList" property="mzdm" labelProperty="mzmc"/>
				</html:select>
			</td>
			<td align="right">
				������ò��
			</td>
			<td>
				<html:select property="jtcy1_zzmm" name="rs" disabled="true" styleId="jtcy1_zzmm">
					<html:option value=""></html:option>
					<html:options collection="zzmmList" property="zzmmdm" labelProperty="zzmmmc"/>
				</html:select>
			</td>
			</tr>
			
			<tr>
			<td align="right">
				ְҵ��
			</td>
			<td>
				<html:text property="jtcy1_zy" name="rs" disabled="true" styleId="jtcy1_zy" maxlength="10"/>
			</td>
			<td align="right">
				ְ��
			</td>
			<td>
				<html:text property="jtcy1_zw" name="rs" disabled="true" styleId="jtcy1_zw" maxlength="10"/>
			</td>
			</tr>
				
			<tr>
			<td align="right">
				������λ�绰��
			</td>
			<td>
				<html:text property="jtcy1_lxdh1" name="rs" disabled="true" styleId="jtcy1_lxdh1" maxlength="25"/>
			</td>
			<td align="right">
				���˵绰��
			</td>
			<td>
				<html:text property="jtcy1_lxdh2" name="rs" disabled="true" styleId="jtcy1_lxdh2" maxlength="25"/>
			</td>
			</tr>
			
			<tr>
			<td align="right">
				������ַ��
			</td>
			<td colspan="3">
				<html:text property="jtcy1_gzdz" name="rs" disabled="true" styleId="jtcy1_gzdz" maxlength="25" style="width:100%"/>
			</td>			
			</tr>			
			</table>
			</fieldset>
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
			<tr id="jt2">
			<td colspan="4">
			<fieldset>
				<legend>
					��ͥ��Ա2
				</legend>
			<table class="tbstyle" width="100%">				
			<tr>
			<td align="right">
				������
			</td>
			<td>
				<html:text property="jtcy2_xm" name="rs" disabled="true" styleId="jtcy2_xm" maxlength="25"/>
			</td>
			<td align="right">
				�뱾�˹�ϵ��
			</td>
			<td>
				<html:text property="jtcy2_gx" name="rs" disabled="true" styleId="jtcy2_gx" maxlength="10"/>
			</td>
			</tr>
			
			<tr>
			<td align="right">
				�������ڣ�
			</td>
			<td>
				<html:text property="jtcy2_nl" name="rs" disabled="true" styleId="jtcy2_nl" readonly="" onclick="return showCalendar('jtcy2_nl','y-mm-dd');" styleId="jtcy2_nl"/>
			</td>
			<td align="right">
				���֤�ţ�
			</td>
			<td>
				<html:text property="jtcy2_sfzh" name="rs" disabled="true" styleId="jtcy2_sfzh" maxlength="18"/>
			</td>
			</tr>
			
			<tr>
			<td align="right">
				���壺
			</td>
			<td>
				<html:select property="jtcy2_mz" name="rs" disabled="true" styleId="jtcy2_mz">
					<html:option value=""></html:option>
					<html:options collection="mzList" property="mzdm" labelProperty="mzmc"/>
				</html:select>
			</td>
			<td align="right">
				������ò��
			</td>
			<td>
				<html:select property="jtcy2_zzmm" name="rs" disabled="true" styleId="jtcy2_zzmm">
					<html:option value=""></html:option>
					<html:options collection="zzmmList" property="zzmmdm" labelProperty="zzmmmc"/>
				</html:select>
			</td>
			</tr>
			
			<tr>
			<td align="right">
				ְҵ��
			</td>
			<td>
				<html:text property="jtcy2_zy" name="rs" disabled="true" styleId="jtcy2_zy" maxlength="10"/>
			</td>
			<td align="right">
				ְ��
			</td>
			<td>
				<html:text property="jtcy2_zw" name="rs" disabled="true" styleId="jtcy2_zw" maxlength="10"/>
			</td>
			</tr>
				
			<tr>
			<td align="right">
				������λ�绰��
			</td>
			<td>
				<html:text property="jtcy2_lxdh1" name="rs" disabled="true" styleId="jtcy2_lxdh1" maxlength="25"/>
			</td>
			<td align="right">
				���˵绰��
			</td>
			<td>
				<html:text property="jtcy2_lxdh2" name="rs" disabled="true" styleId="jtcy2_lxdh2" maxlength="25"/>
			</td>
			</tr>
			
			<tr>
			<td align="right">
				������ַ��
			</td>
			<td colspan="3">
				<html:text property="jtcy2_gzdz" name="rs" disabled="true" styleId="jtcy2_gzdz" maxlength="25" style="width:100%"/>
			</td>			
			</tr>
			
			</table>
			</fieldset>
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
			<tr id="jt3">
			<td colspan="4">
			<fieldset>
				<legend>
					��ͥ��Ա3
				</legend>
			<table class="tbstyle" width="100%">				
			<tr>
			<td align="right">
				������
			</td>
			<td>
				<html:text property="jtcy3_xm" name="rs" disabled="true" styleId="jtcy3_xm" maxlength="25"/>
			</td>
			<td align="right">
				�뱾�˹�ϵ��
			</td>
			<td>
				<html:text property="jtcy3_gx" name="rs" disabled="true" styleId="jtcy3_gx" maxlength="10"/>
			</td>
			</tr>
			
			<tr>
			<td align="right">
				�������ڣ�
			</td>
			<td>
				<html:text property="jtcy3_nl" name="rs" disabled="true" styleId="jtcy3_nl" readonly="" onclick="return showCalendar('jtcy3_nl','y-mm-dd');" styleId="jtcy3_nl"/>
			</td>
			<td align="right">
				���֤�ţ�
			</td>
			<td>
				<html:text property="jtcy3_sfzh" name="rs" disabled="true" styleId="jtcy3_sfzh" maxlength="18"/>
			</td>
			</tr>
			
			<tr>
			<td align="right">
				���壺
			</td>
			<td>
				<html:select property="jtcy3_mz" name="rs" disabled="true" styleId="jtcy3_mz">
					<html:option value=""></html:option>
					<html:options collection="mzList" property="mzdm" labelProperty="mzmc"/>
				</html:select>
			</td>
			<td align="right">
				������ò��
			</td>
			<td>
				<html:select property="jtcy3_zzmm" name="rs" disabled="true" styleId="jtcy3_zzmm">
					<html:option value=""></html:option>
					<html:options collection="zzmmList" property="zzmmdm" labelProperty="zzmmmc"/>
				</html:select>
			</td>
			</tr>
			
			<tr>
			<td align="right">
				ְҵ��
			</td>
			<td>
				<html:text property="jtcy3_zy" name="rs" disabled="true" styleId="jtcy3_zy" maxlength="10"/>
			</td>
			<td align="right">
				ְ��
			</td>
			<td>
				<html:text property="jtcy3_zw" name="rs" disabled="true" styleId="jtcy3_zw" maxlength="10"/>
			</td>			
			</tr>
				
			<tr>
			<td align="right">
				������λ�绰��
			</td>
			<td>
				<html:text property="jtcy3_lxdh1" name="rs" disabled="true" styleId="jtcy3_lxdh1" maxlength="25"/>
			</td>
			<td align="right">
				���˵绰��
			</td>
			<td>
				<html:text property="jtcy3_lxdh2" name="rs" disabled="true" styleId="jtcy3_lxdh2" maxlength="25"/>
			</td>
			</tr>
			
			<tr>
			<td align="right">
				������ַ��
			</td>
			<td colspan="3">
				<html:text property="jtcy3_gzdz" name="rs" disabled="true" styleId="jtcy3_gzdz" maxlength="25" style="width:100%"/>
			</td>			
			</tr>
			
			</table>
			</fieldset>
			</td>
			</tr>
			</table>
			<logic:equal value="yes" name="writeAble">
				<div class="buttontool" align="center">
					<button type="button" class="button2"
						onclick="refreshForm('xsxx_zgkd.do?method=saveStuinfoModi')">
						������Ϣ
					</button>
				</div>
			</logic:equal>
			
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
		</html:form>
	</body>
</html>
