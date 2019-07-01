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
<html:html>
<head>
	<base target="_self" />
	<title><bean:message key="lable.title" /></title>
	<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
	<meta name="Copyright" content="������� zfsoft" />
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<%
		response.setHeader("Pragma", "no-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
	%>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<link id="csss" rel="stylesheet" rev="stylesheet"
		href="js/calendar.css" type="text/css" media="all" />
	<script language="javascript" src="js/commanFunction.js"></script>
	<script language="javascript" src="js/function.js"></script>
	<script language="javascript" src="js/xszzFunction.js"></script>
	<script language="javascript" src="js/calendar.js"></script>
	<script language="javascript" src="js/calendar-zh.js"></script>
	<script language="javascript" src="js/calendar-setup.js"></script>
	<script language="javascript" src="js/String.js"></script>
	<script language="javascript">
		function checkStuInfo(){
			var xh = document.getElementById('xh').value;
			if(xh == ""){
				alert("ѧ�Ų���Ϊ��!");
				return ;
			}
			refreshForm("whlg_xszz.do?method=gjzxdkhkxysq&doType=add");
		}
		
		function toPrintOut(){//�����Ӧ�Ĵ�ӡҳ��
			document.forms[0].action = "";
			document.forms[0].submit();
		}
	</script>
</head>
<body>
	<div class="title">
		<div class="title_img" id="title_m">
			��ǰ����λ�ã�ѧ������ - ������ѧ�����Э�� - ������ѧ�����Э��
		</div>
	</div>
	<logic:equal name="sfksq" value="-1">
		<center>
			<p>
				���ڲ�������ʱ���ڣ�����
			</p>
		</center>
	</logic:equal>
	<%--
	<logic:equal name="sfksq" value="1">
		--%>
	<html:form action="/whlg_xszz.do?method=gjzxdkhkxysq" method="post">
		<input type="hidden" id="url" name="url"
			value="/whlg_xszz.do?method=gjzxdkhkxysq" />
		<input type="hidden" id="getStuInfo" name="getStuInfo"
			value="xm-xb-xymc-bjmc-nj-zzmmmc-sfzh-zymc" />
		<input type="hidden" id="userOnLine" name="userOnLine"
			value="<bean:write name="userOnLine" scope="session"/>" />
		<%--<input type="hidden" id="sqsj" name="sqsj"
				value="<bean:write name="rs" property="sqsj" />">
			<input type="hidden" id="xysh" name="xysh"
				value="<bean:write name="rs" property="xysh" />">
			<input type="hidden" id="xyshyj" name="xyshyj"
				value="<bean:write name="rs" property="xyshyj" />">
			<input type="hidden" id="xxsh" name="xxsh"
				value="<bean:write name="rs" property="xxsh" />">
			<input type="hidden" id="xxshyj" name="xxshyj"
				value="<bean:write name="rs" property="xxshyj" />">
			--%>
		<table class="tbstyle" width="100%">
			<tbody>
			<tr>
				<logic:equal name="userOnLine" value="teacher" scope="session">
						<td align="center" style="width:10%">
							<font color="red">*</font>ѧ��
						</td>
						<td align="left" >
							<html:text name='rs' property="xh" styleId="xh"
								onkeypress="autoFillStuInfo(event.keyCode,this)" readonly="true" />
							<button type="button" onclick="showTopWin('/xgxt/stu_info.do',750,550);"
								class="btn_01" id="buttonFindStu">
								ѡ��
							</button>
						</td>
					</logic:equal>
					<logic:equal name="userOnLine" value="student" scope="session">
						<td align="center" style="width:10%">
							<font color="red">*</font>ѧ��
						</td>
						<td align="left">
							<input type="text" id="xh" name="xh"
								value="<bean:write name='rs' property="xh" />" readonly="readonly"/>
						</td>
					</logic:equal>
					<td>
						<div align="center">
							������ò
						</div>
					</td>
					<td>
						<input type="text" id="zzmmmc" name="zzmmmc"				
							value="<bean:write name='rs' property="zzmmmc" />" readonly="readonly"/>
					</td>
				</tr>
				<tr>
					<td scope="row">
						<div align="center">
							�Ա�
						</div>
					</td>
					<td>
						<input type="text" id="xb" name="xb" 
							value="<bean:write name='rs' property="xb" />" readonly="readonly"/>
					</td>
					<td>
						<div align="center">
							���֤��
						</div>
					</td>
					<td>
						<input type="text" id="sfzh" name="sfzh" 
							value="<bean:write name='rs' property="sfzh" />" readonly="readonly"/>
					</td>
				</tr>
				<tr>
					<td scope="row">
						<div align="center">
							<bean:message key="lable.xsgzyxpzxy" />
						</div>
					</td>
					<td>
						<input type="text" id="xymc" name="xymc"
							value="<bean:write name='rs' property="xymc" />" readonly="readonly"/>
					</td>
					<td scope="row">
						<div align="center">
							רҵ
						</div>
					</td>
					<td>
						<input type="text" id="zymc" name="zymc"		
						value="<bean:write name='rs' property="zymc" />" readonly="readonly"/>
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							����
						</div>
					</td>
					<td  scope="col">
						<input type="text" id="xm" name="xm"
							value="<bean:write name='rs' property="xm" />" readonly="readonly"/>
					</td>
					<td scope="row">
						<div align="center">
							�༶
						</div>
					</td>
					<td>
						<input type="text" id="bjmc" name="bjmc"
							
							value="<bean:write name='rs' property="bjmc" />" readonly="readonly"/>
					</td>
				</tr>
				<tr>
					<td align="center" colspan="4" bgcolor="#D0E0EE">
						�й����й�����ѧ�����Э��
					</td>
				</tr>
				<tr>
					<td scope="row">
						<div align="center">
							����ˣ��׷���
						</div>
					</td>
					<td colspan="3">
						<input type="text" id="jkr" name="jkr"  style="width:80%"
							value="<bean:write name='rs' property="jkr" />"/>
					</td>
				</tr>
				<tr>
					<td scope="row">
						<div align="center">
							��Ч֤������
						</div>
					</td>
					<td colspan="3">
						<input type="text" id="yxzjhm" name="yxzjhm" style="width:80%"
							value="<bean:write name='rs' property="yxzjhm" />"/>
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							ס��
						</div>
					</td>
					<td colspan="3">
						<input type="text" id="zs" name="zs" style="width:80%"
							value="<bean:write name='rs' property="zs" />"/>
					</td>
				</tr>
				<tr>
					<td scope="row">
						<div align="center">
							������λ
						</div>
					</td>
					<td colspan="3">
						<input type="text" id="gzdw" name="gzdw" style="width:80%"
							value="<bean:write name='rs' property="gzdw" />"/>
					</td>
				</tr>
				<tr>
					<td scope="row">
						<div align="center">
							��������
						</div>
					</td>
					<td colspan="3">
						<input type="text" id="yzbm" name="yzbm"  style="width:80%"
						value="<bean:write name='rs' property="yzbm" />"/>
					</td>
				</tr>
				<tr>
					<td scope="row">
						<div align="center">
							��ϵ�绰
						</div>
					</td>
					<td colspan="3">
						<input type="text" id="lxdh" name="lxdh" style="width:80%"
							value="<bean:write name='rs' property="lxdh" />"/>
					</td>
				</tr>
				<tr>
					<td scope="row">
						<div align="center">
							�����ˣ��ҷ���
						</div>
					</td>
					<td colspan="3">
						<input type="text" id="bjmc" name="bjmc" value="�й����йɷ����޹�˾�人����֧��"
							readonly="readonly"  style="width:80%" />
					</td>
				</tr>
				<tr>
					<td scope="row">
						<div align="center">
							��ַ
						</div>
					</td>
					<td colspan="3">
						<input type="text" id="bjmc" name="bjmc" value="�人�к�ɽ��׿��Ȫ��·39��"
							readonly="readonly"   style="width:80%"/>
					</td>
				</tr>
				<tr>
					<td scope="row">
						<div align="center">
							��������
						</div>
					</td>
					<td colspan="3">
						<input type="text" id="bjmc" name="bjmc" value="430079"
							readonly="readonly"   style="width:80%"/>
					</td>
				</tr>
				<tr>
					<td scope="row">
						<div align="center">
							��ϵ�绰
						</div>
					</td>
					<td colspan="3">
						<input type="text" id="bjmc" name="bjmc" style="width:80%"
							value="027-87521649��027-87521897" readonly="readonly" />
					</td>
				</tr>
			</tbody>
			<tbody id="tableadddel">
				<tr style="height:150px">
					<td colspan="4">
						<div style="width:90%">
							��Э��Ϊ�׷����ҷ�ǩ���ġ��й����й�����ѧ�������ͬ������ͬ��ţ�
							<input type="text" id="htbh" name="htbh"
								value="<bean:write name='rs' property="htbh" />" />
							�ţ�<br> Լ���Ĵ���Э�飬������ȷ�׷����ҷ��黹������ѧ����ƻ������ס�������Э��ͬ��󣬶������»���Э�飺<br><br>
							һ����ֹ
							<html:text name='rs' property="jzrq" styleId="jzrq"
								onblur="dateFormatChg(this)" style="cursor:hand;"
								onclick="return showCalendar('jzrq','y-mm-dd');" />
							�� �׷����ҷ���ù�����ѧ����������
							<input type="text" id="fkje" name="fkje"
								value="<bean:write name='rs' property="fkje" />" />
							Ԫ����д���� <br><br>
							�����׷���
							<html:text name='rs' property="jf_lkxxsj" styleId="jf_lkxxsj"
								onblur="dateFormatChg(this)" style="cursor:hand;"
								onclick="return showCalendar('jf_lkxxsj','y-mm-dd');" />
							����<input type="text" id="jf_lkxx_yy" name="jf_lkxx_yy"
								value="<bean:write name='rs' property="jf_lkxx_yy" />" />
							ԭ�� ��ʽ�뿪
							<input type="text" id="jflkxx_mc" name="jflkxx_mc"
								value="<bean:write name='rs' property="jflkxx_mc" />" />
							������ѧУ���� <br><br>
							�����׷��������µ�
								<input type="text" id="fk_fs" name="fk_fs"
								value="<bean:write name='rs' property="fk_fs" />" />
							��ʽ ��
							<input type="text" id="fk_jtfs" name="fk_jtfs"
								value="<bean:write name='rs' property="fk_jtfs" />" />
							���£� <br>��
							<input type="text" id="fjq" name="fjq"
								value="<bean:write name='rs' property="fjq" />" />
							�� �黹�������Ϣ��<br>��
							<html:text name='rs' property="fklx_kssj" styleId="fklx_kssj"
								onblur="dateFormatChg(this)" style="cursor:hand;"
								onclick="return showCalendar('fklx_kssj','y-mm-dd');" />
							��
							<html:text name='rs' property="fklx_jssj" styleId="fklx_jssj"
								onblur="dateFormatChg(this)" style="cursor:hand;"
								onclick="return showCalendar('fklx_jssj','y-mm-dd');" />
							��
							<input type="text" id="fklxys" name="fklxys"
								value="<bean:write name='rs' property="fklxys" />" />
							�¹黹������Ϣ��<br> ��
							<html:text name='rs' property="fkbjlx_kssj" styleId="fkbjlx_kssj"
								onblur="dateFormatChg(this)" style="cursor:hand;"
								onclick="return showCalendar('fkbjlx_kssj','y-mm-dd');" />
							��
							<html:text name='rs' property="fkbjlx_jssj" styleId="fkbjlx_jssj"
								onblur="dateFormatChg(this)" style="cursor:hand;"
								onclick="return showCalendar('fkbjlx_jssj','y-mm-dd');" />
							��
							<input type="text" id="fkbjlxys" name="fkbjlxys"
								value="<bean:write name='rs' property="fkbjlxys" />" />
							�¹黹�������Ϣ��<br><br>
							�ġ���˫��ȷ�ϵĽ��ڱ�����Э�������ڼ䣬����������ִ���й���������ͬ���η������ʡ�<br>
							�����������ʵ������ҷ���ִ�е���������ʣ���������֪ͨ�׷���
							<br><br>
							�塢�׷���Ȩ�ҷ�ֱ�ӴӼ׷����ҷ��������˻��пۿ���ڹ黹��Ϣ��<br> �˻�����Ϊ��
							<input type="text" id="zffm" name="zffm"
								value="<bean:write name='rs' property="zffm" />" />
							���˻���Ϊ��
							<input type="text" id="zfh" name="zfh"
								value="<bean:write name='rs' property="zfh" />" />
							��<br><br>
							�����׷���ŵ����У�������׺�һ�����ڽ����й����й�����ѧ������ϵ��ʽȷ�Ϻ������ͻ��ҷ���<br>
							��׷�������λ����ϵ��ʽ�б䶯�����뼰ʱ֪ͨ�ҷ���
							<br><br>�ߡ���Э����������׷��Ѿ����ҷ������˳�ֵ�Э�̣�
							<br><br>�ˡ���Э����Ϊ��������ѧ�������ͬ������ɲ��֣��롶������ѧ�������ͬ������ͬ�ȷ���Ч����
						</div>
					</td>
				</tr>
			</tbody>
		</table>
		<div class="buttontool" id="btn" style="position: absolute;width:100%">
			<logic:notEqual value="view" name="rs" property="doType">
				<button type="button" class="button2" onClick="checkStuInfo();" style="width:80px">
					�ύ����
				</button>&nbsp;&nbsp;&nbsp;&nbsp;
				<button type="button" class="button2" onClick="toPrintOut();" style="width:80px">
					�� ӡ
				</button>&nbsp;&nbsp;&nbsp;&nbsp;
				</logic:notEqual>
			<logic:notEqual value="student" scope="session" name="userOnLine">
				<button type="button" class="button2" onClick="Close();" style="width:80px">
					�ر�
				</button>
			</logic:notEqual>
		</div>
	</html:form>
	<%--
	</logic:equal>
--%>
</body>
<logic:present name="ok">
	<logic:match value="ok" name="ok">
		<script language="javascript">
	   			alert("����ɹ���");
	   			if(window.dialogArguments){
	   				dialogArgumentsQueryChick();
	   				Close();
	   			}
	   		
	        </script>
	</logic:match>
	<logic:match value="no" name="ok">
		<script language="javascript">
	        	alert("����ʧ�ܣ�");
	        </script>
	</logic:match>
</logic:present>
<logic:present name="have">
	<logic:match value="have" name="have">
		<script language="javascript">
	         	alert("��ͨ����ˣ��������룡");
	         </script>
	</logic:match>
</logic:present>
</html:html>
