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
<base target="_self" />
<head>
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
	<script type='text/javascript' src='/xgxt/dwr/interface/getGdnzHdHtxx.js'></script>
	<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
	<script type='text/javascript' src='dwr/util.js'></script>
	<script language="javascript" src="js/AjaxFunction.js"></script>
	<script language="javascript">
		function yz(){
			var xh = document.getElementById('xh').value;
			var hth = document.getElementById('hth').value;
			if((xh == null) || (xh == "")){
				alert("ѧ�Ų���Ϊ��!");
				return false;
			}
			if((hth == null) || (hth == "")){
				alert("��ͬ�Ų���Ϊ��!");
				return false;
			}
			document.forms[0].action = "/xgxt/gnnzzy_gjzxdk.do?method=hdxxsq&act=save";
			document.forms[0].submit();
		}
		
		function toPrintOut(){//�����Ӧ�Ĵ�ӡҳ��
			document.forms[0].action = "/xgxt/gnnzzy_gjzxdk.do?method=hdxxsqb";
			document.forms[0].submit();
		}
		
	</script>
</head>

<body>
	<div class="title">
		<div class="title_img" id="title_m">
			��ǰ����λ�ã�ѧ������ - ���������Ϣ
		</div>
	</div>
	<logic:equal name="sfksq" value="-1">
		<center>
			<p>
				���ڲ�������ʱ���ڣ�����
			</p>
		</center>
	</logic:equal>
		<html:form action="gnnzzy_gjzxdk.do?method=hdxxsq" method="post">
			<input type="hidden" id="url" name="url"
				value="/gnnzzy_gjzxdk.do?method=hdxxsq" />
			<input type="hidden" id="getStuInfo" name="getStuInfo"
				value="xm-xb-xymc-bjmc" />
			<input type="hidden" id="yhkje" name="yhkje"
				value="<bean:write name="rs" property="yhkje"/>">
			<input type="hidden" id="yqqs" name="yqqs"
				value="<bean:write name="rs" property="yqqs"/>">
			<input type="hidden" id="yqbj" name="yqbj"
				value="<bean:write name="rs" property="yqbj"/>">
			<input type="hidden" id="yqfx" name="yqfx"
				value="<bean:write name="rs" property="yqfx"/>">
			<input type="hidden" id="yqyy" name="yqyy"
				value="<bean:write name="rs" property="yqyy"/>">
			<input type="hidden" id="bz" name="bz"
				value="<bean:write name="rs" property="bz"/>">

			<logic:present name="ok">
				<logic:match value="ok" name="ok">
					<script language="javascript">
	         	alert("����ɹ���");
	         	</script>
				</logic:match>
				<logic:match value="no" name="ok">
					<script language="javascript">
	         	alert("����ʧ�ܣ�");
	         	</script>
				</logic:match>
			</logic:present>

			<table class="tbstyle" width="90%">
				<tr>
					<logic:equal name="userOnLine" value="teacher" scope="session">
						<td align="center" width="16%">
							<font color="red">*</font>ѧ��
						</td>
						<td align="left" width="34%">
							<html:text name='rs' property="xh" styleId="xh"
								onkeypress="autoFillStuInfo(event.keyCode,this)" readonly="true" />
							<button onclick="showTopWin('/xgxt/stu_info.do',750,550);"
								class="btn_01" id="buttonFindStu">
								ѡ��
							</button>
						</td>
					</logic:equal>
					<logic:equal name="userOnLine" value="student" scope="session">
						<td align="center" width="16%">
							<font color="red">*</font>ѧ��
						</td>
						<td align="left" width="34%">
							<input type="text" id="xh" name="xh"
								style="width:100%;heigh:100%"
								value="<bean:write name='rs' property="xh" />" readonly="true">
						</td>
					</logic:equal>
					<td width="16%" scope="col">
						<div align="center">
							����
						</div>
					</td>
					<td width="34%" scope="col">
						<input type="text" id="xm" name="xm" style="width:100%;heigh:100%"
							value="<bean:write name='rs' property="xm" />" readonly="true">
					</td>
				</tr>
				<tr>
					<td width="16%" scope="row">
						<div align="center">
							�Ա�
						</div>
					</td>
					<td width="34%">
						<input type="text" id="xb" name="xb" style="width:100%;heigh:100%"
							value="<bean:write name='rs' property="xb" />" readonly="true">
					</td>
					<td>
						<div align="center">
							�꼶
						</div>
					</td>
					<td>
						<input type="text" id="nj" name="nj" style="width:100%;heigh:100%"
							value="<bean:write name='rs' property="nj" />" readonly="true">
					</td>
				</tr>
				<tr>
					<td scope="row">
						<div align="center">
							���֤��
						</div>
					</td>
					<td>
						<input type="text" id="sfzh" name="sfzh"
							style="width:100%;heigh:100%"
							value="<bean:write name='rs' property="sfzh" />" readonly="true">
					</td>
					<td>
						<div align="center">
							<bean:message key="lable.xsgzyxpzxy" />
						</div>
					</td>
					<td>
						<input type="text" id="xymc" name="xymc"
							style="width:100%;heigh:100%"
							value="<bean:write name='rs' property="xymc" />" readonly="true">
					</td>
				</tr>
				<tr>
					<td scope="row">
						<div align="center">
							רҵ
						</div>
					</td>
					<td>
						<input type="text" id="zymc" name="zymc"
							style="width:100%;heigh:100%"
							value="<bean:write name='rs' property="zymc" />" readonly="true">
					</td>
					<td>
						<div align="center">
							�༶
						</div>
					</td>
					<td>
						<input type="text" id="bjmc" name="bjmc"
							style="width:100%;heigh:100%"
							value="<bean:write name='rs' property="bjmc" />" readonly="true">
					</td>
				</tr>
				<tr>
					<td scope="row">
						<div align="center">
							ѧ��
						</div>
					</td>
					<td>
						<input type="text" id="xz" name="xz" style="width:100%;heigh:100%"
							value="<bean:write name='rs' property="xz" />"
							readonly="readonly">
					</td>
					<td>
						<div align="center">
							������ϵ�绰
						</div>
					</td>
					<td align="center">
						<input type="text" id="grlxdh" name="grlxdh"
							style="width:100%;heigh:100%"
							value="<bean:write name='rs' property="grlxdh" />"
							readonly="readonly">
					</td>
				</tr>
				<tr>
					<td scope="row">
						<div align="center">
							�����ַ
						</div>
					</td>
					<td>
						<input type="text" id="yxdz" name="yxdz"
							style="width:100%;heigh:100%"
							value="<bean:write name='rs' property="yxdz" />"
							readonly="readonly">
					</td>
					<td>
						<div align="center">
							�������ڵ�
						</div>
					</td>
					<td align="center">
						<input type="text" id="hjszd" name="hjszd"
							style="width:100%;heigh:100%"
							value="<bean:write name='rs' property="hjszd" />"
							readonly="readonly">
					</td>
				</tr>
				<tr>
					<td scope="row">
						<div align="center">
							��ͥ��ס��ַ
						</div>
					</td>
					<td colspan="3">
						<input type="text" id="jtjzdz" name="jtjzdz"
							style="width:100%;heigh:100%"
							value="<bean:write name='rs' property="jtjzdz" />"
							readonly="readonly">
					</td>
				</tr>
				<tr>
					<td scope="row">
						<div align="center">
							��������
						</div>
					</td>
					<td>
						<input type="text" id="yzbm" name="yzbm"
							style="width:100%;heigh:100%"
							value="<bean:write name='rs' property="yzbm" />"
							readonly="readonly">
					</td>
					<td>
						<div align="center">
							��ͥ��ϵ�绰
						</div>
					</td>
					<td>
						<input type="text" id="jtlxdh" name="jtlxdh"
							style="width:100%;heigh:100%"
							value="<bean:write name='rs' property="jtlxdh" />"
							readonly="readonly">
					</td>
				</tr>
				<tr>
					<td scope="row">
						<div align="center">
							������λ����
						</div>
					</td>
					<td>
						<input type="text" id="gzdwmc" name="gzdwmc"
							style="width:100%;heigh:100%"
							value="<bean:write name='rs' property="gzdwmc" />"
							maxlength="100">
					</td>
					<td>
						<div align="center">
							������λ�ʱ�
						</div>
					</td>
					<td>
						<input type="text" id="gzdwyb" name="gzdwyb"
							style="width:100%;heigh:100%"
							value="<bean:write name='rs' property="gzdwyb" />" maxlength="6"
							onkeyup="value=value.replace(/[^\d]/g,'') "
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
				</tr>
				<tr>
					<td scope="row">
						<div align="center">
							������λ��ַ
						</div>
					</td>
					<td>
						<input type="text" id="gzdwdz" name="gzdwdz"
							style="width:100%;heigh:100%"
							value="<bean:write name='rs' property="gzdwdz" />"
							maxlength="200">
					</td>
					<td>
						<div align="center">
							������λ�绰
						</div>
					</td>
					<td>
						<input type="text" id="dwdh" name="dwdh"
							style="width:100%;heigh:100%"
							value="<bean:write name='rs' property="dwdh" />" maxlength="15"
							onkeyup="value=value.replace(/[^\d]/g,'') "
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
				</tr>
				<tr>
					<td scope="row">
						<div align="center">
							<font color="red">*</font>��ͬ��
						</div>
					</td>
					<td>
						<html:select name="rs" property="hth" style="width:100%"
							onchange="getGdnzHtXxxx();">
							<html:option value=""></html:option>
							<html:options collection="hthList" property="hth"
								labelProperty="hth" />
						</html:select>
					</td>
					<td>
						<div align="center">
							��ͬ���
						</div>
					</td>
					<td>
						<input type="text" id="htje" name="htje"
							style="width:100%;heigh:100%"
							value="<bean:write name='rs' property="htje" />" readonly="readonly">
					</td>
				</tr>
				<tr>
					<td scope="row">
						<div align="center">
							��ͬ�ܽ��
						</div>
					</td>
					<td>
						<input type="text" id="htzje" name="htzje"
							style="width:100%;heigh:100%"
							value="<bean:write name='rs' property="htzje" />" readonly="readonly">
					</td>
					<td>
						<div align="center">
							��������
						</div>
					</td>
					<td>
						<input type="text" id="dkqx" name="dkqx"
							style="width:100%;heigh:100%"
							value="<bean:write name='rs' property="dkqx" />"
							readonly="readonly">
					</td>
				</tr>
				<tr>
					<td scope="row">
						<div align="center">
							�Ը�Ϣ��������
						</div>
					</td>
					<td>
						<input type="text" id="zfxjtrq" name="zfxjtrq"
							style="width:100%;heigh:100%"
							value="<bean:write name='rs' property="zfxjtrq" />"
							readonly="readonly">
					</td>
					<td>
						<div align="center">
							����������
						</div>
					</td>
					<td>
						<input type="text" id="nll" name="nll"
							style="width:100%;heigh:100%"
							value="<bean:write name='rs' property="nll" />"
							readonly="readonly">
					</td>
				</tr>
				<tr>
					<td scope="row">
						<div align="center">
							�����ʻ�����
						</div>
					</td>
					<td>
						<input type="text" id="hkzhlx" name="hkzhlx"
							style="width:100%;heigh:100%"
							value="<bean:write name='rs' property="hkzhlx" />" readonly="readonly">
					</td>
					<td>
						<div align="center">
							�����ʻ�����
						</div>
					</td>
					<td>
						<input type="text" id="hkzhhm" name="hkzhhm"
							style="width:100%;heigh:100%"
							value="<bean:write name='rs' property="hkzhhm" />" readonly="readonly">
					</td>
				</tr>
				<tr>
					<td scope="row">
						<div align="center">
							����ʱ��
						</div>
					</td>
					<td>
						<input type="text" id="hksj" name="hksj"
							style="width:100%;heigh:100%"
							value="<bean:write name='rs' property="hksj" />"
							readonly="readonly">
					</td>
					<td colspan="2">
					</td>
				</tr>
			</table>
	<logic:equal name="sfksq" value="1">
			<div class="buttontool" id="btn" style="position: absolute;width:90%">
				<button class="button2" onClick="yz();">
					�ύ����
				</button>
				<button class="button2" onClick="toPrintOut();">
					��&nbsp;&nbsp;&nbsp;&nbsp;ӡ
				</button>
			</div>
	</logic:equal>
		</html:form>
</body>
</html:html>
