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
	<script language="javascript" src="js/String.js"></script>
	<script type='text/javascript' src='dwr/util.js'></script>
	<script language="javascript" src="js/qgzxFunction.js"></script>
	<script language="javascript">
		function yz(){
			var xxqk = document.getElementById('xxqk').value.trim();
			var mqqgzxqk = document.getElementById('mqqgzxqk').value.trim();
			var xssq = document.getElementById('xssq').value.trim();
			var bz = document.getElementById('bz').value.trim();
			
			if(xxqk != null){
	         	if(xxqk.replace(/[^\u0000-\u00ff]/g, "**").length > 200){	         
	          		 alert("ѧϰ������ܴ���200���ַ�");
	          		 return false;
	       		 }
	       	}
	       	if(mqqgzxqk != null){
	         	if(mqqgzxqk.replace(/[^\u0000-\u00ff]/g, "**").length > 200){	         
	          		 alert("Ŀǰ�ڹ���ѧ������ܴ���200���ַ�");
	          		 return false;
	       		 }
	       	}
	       	if(xssq != null){
	         	if(xssq.replace(/[^\u0000-\u00ff]/g, "**").length > 500){	         
	          		 alert("�������ɲ��ܴ���500���ַ�");
	          		 return false;
	       		 }
	       	}
	       	if(bz != null){
	         	if(bz.replace(/[^\u0000-\u00ff]/g, "**").length > 120){	         
	          		 alert("��ע���ܴ���120���ַ�");
	          		 return false;
	       		 }
	       	}
			chkisDataExist();
		}
		
		function xfhj(){
			var mnyjngzfy = document.getElementById('mnyjngzfy').value;
			var jtmntg = document.getElementById('jtmntg').value;
			if((mnyjngzfy == null) || (mnyjngzfy == "")){
				mnyjngzfy = "0";
			}
			if((jtmntg == null) || (jtmntg == "")){
				jtmntg = "0";
			}
			var hjmnsqfy = Math.round(mnyjngzfy) - Math.round(jtmntg);
			document.getElementById('hjmnsqfy').value=hjmnsqfy;
		}
		
		function print(){
			document.forms[0].action = "/xgxt/qgzx_jsxx_gwsqPrint.do";
			document.forms[0].submit();
		}
		
		function getGw(){
			var temp = $('xmdm').options[$('xmdm').selectedIndex].innerHTML;
			if((temp == null) || (temp == "")){
				temp = " ";
			}
			document.getElementById('gw').value=temp;
		}
	</script>
	<body>
		<html:form action="/data_search" method="post">
		<input type="hidden" id="xbmc" value="<bean:message key="lable.xb" />"/>
			<div class="title">
				<div class="title_img" id="title_m">
					��ǰλ�ã��ڹ���ѧ - ��λ���� - ��д�����
				</div>
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
				<input type="hidden" id="jtjjknqk" name="jtjjknqk"
					value="<bean:write name="rs" property="jtjjknqk" scope="request" />">
				<input type="hidden" id="cxdd" name="cxdd"
					value="<bean:write name="rs" property="cxdd" scope="request" />">
				<input type="hidden" name="gw" id="gw"
					value="<bean:write name='rs' property='gw'/>">
				<table width="100%" id="rsT" class="tbstyle">
					<thead>
						<tr style="height:22px">
							<td colspan="8" align="center">
								<b>��д�����</b>
							</td>
						</tr>
					</thead>
					<tr style="height:22px">
						<logic:equal name="userOnLine" value="teacher" scope="session">
							<td align="right" colspan="2" width="16%">
								<font color="red">*</font>ѧ�ţ�
							</td>
							<td align="left" colspan="3" width="34%">
								<html:text name='rs' property="xh" styleId="xh" readonly="true"
									onkeypress="autoFillStuInfo(event.keyCode,this)" />
								<button type="button" onclick="showTopWin('/xgxt/stu_info.do',750,550);"
									class="btn_01" id="buttonFindStu">
									ѡ��
								</button>
							</td>
						</logic:equal>
						<logic:equal name="userOnLine" value="student" scope="session">
							<td align="right" colspan="2" width="16%">
								<font color="red">*</font>ѧ�ţ�
							</td>
							<td align="left" colspan="3" width="34%">
								<bean:write name='rs' property="xh" />
							</td>
						</logic:equal>
						<td align="right" width="16%">
							<font color="red">*</font>��λ���ƣ�
						</td>
							<td align="left" colspan="2">
								<html:select name="rs" property="xmdm" styleId="xmdm"
									style="width:150px" onchange="getGw();">
									<html:option value=""></html:option>
									<html:options collection="gwList" property="gwdmgwsbsj"
										labelProperty="gwdm" />
								</html:select>
							</td>
					</tr>

					<tr style="height:22px">
						<td align="center" colspan="2">
							����
						</td>
						<td colspan="3">
							<input type="text" readonly="readonly" id="xm" name="xm"
								style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="xm"/>">
						</td>
						<td align="center">
							�Ա�
						</td>
						<td colspan="2">
							<input type="text" readonly="readonly" id="xb" name="xb"
								style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="xb"/>">
						</td>
					</tr>
					<tr style="height:22px">
						<td align="center" colspan="2">
							������ò
						</td>
						<td colspan="3">
							<input type="text" readonly="readonly" id="zzmm" name="zzmm"
								style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="zzmm"/>">
						</td>
						<td align="center">
							�����
						</td>
						<td colspan="2">
							<input type="text" readonly="readonly" id="ssbh" name="ssbh"
								style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="ssbh"/>">
						</td>
					</tr>
					<tr style="height:22px">
						<td align="center" colspan="2">
							<bean:message key="lable.xsgzyxpzxy" />����
						</td>
						<td colspan="3">
							<input type="text" readonly="readonly" id="xymc" name="xymc"
								style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="xymc"/>">
						</td>
						<td align="center">
							רҵ����
						</td>
						<td colspan="2">
							<input type="text" readonly="readonly" id="zymc" name="zymc"
								style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="zymc"/>">
						</td>
					</tr>
					<tr style="height:22px">
						<td align="center" colspan="2">
							�༶����
						</td>
						<td colspan="3">
							<input type="text" readonly="readonly" id="bjmc" name="bjmc"
								style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="bjmc"/>">
						</td>
						<td align="center">
							����ʱ��
						</td>
						<td colspan="2">
							<input type="text" readonly="readonly" id="sqsj" name="sqsj"
								style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="sqsj"/>">
						</td>
					</tr>
					<tr style="height:22px">
						<td align="center" colspan="2">
							����ְ��
						</td>
						<td colspan="3">
							<input type="text" maxlength="100" id="btzw" name="btzw"
								style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="btzw"/>">
						</td>
						<td align="center">
							�кμ����س�
						</td>
						<td colspan="2">
							<input type="text" maxlength="100" id="yhtc" name="yhtc"
								style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="yhtc"/>">
						</td>
					</tr>
					<tr style="height:22px">
						<td align="center" colspan="2">
							����
						</td>
						<td colspan="3">
							<input type="text" maxlength="100" id="jg" name="jg"
								style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="jg"/>">
						</td>
						<td align="center">
							ѧ���绰
						</td>

						<td colspan="2">
							<input type="text" maxlength="15" id="lxdh" name="lxdh"
								style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="lxdh"/>"
								onkeyup="value=value.replace(/[^\d]/g,'') "
								onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
						</td>
					</tr>
					<tr style="height:22px">
						<td align="center" colspan="2">
							���������޲�����γ�
						</td>
						<td colspan="3">
							<input type="text" maxlength="100" id="bkhywbjgkc"
								name="bkhywbjgkc" style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="bkhywbjgkc"/>">
						</td>
						<td align="center">
							�Ƿ�Ը��ͳһ����
						</td>
						<td colspan="2" align="center">
							<logic:present name="rs" property="sfyytytp">
								<logic:match value="��" name="rs" property="sfyytytp">
									<input type="radio" name="sfyytytp" value="��" checked>&nbsp;&nbsp;��
							    <input type="radio" name="sfyytytp" value="��">&nbsp;&nbsp;��
							</logic:match>
								<logic:match value="��" name="rs" property="sfyytytp">
									<input type="radio" name="sfyytytp" value="��">&nbsp;&nbsp;��
							    <input type="radio" name="sfyytytp" value="��" checked>&nbsp;&nbsp;��
							</logic:match>
							</logic:present>
							<logic:notPresent name="rs" property="sfyytytp">
								<input type="radio" name="sfyytytp" value="��">&nbsp;&nbsp;�� 
							<input type="radio" name="sfyytytp" value="��" checked>&nbsp;&nbsp;��
						</logic:notPresent>
						</td>
					</tr>
					<tr>
						<td colspan="2">
							<div align="center">
								ѧϰ���
							</div>
						</td>
						<td colspan="6">
							<textarea id="xxqk" name="xxqk" style="width:100%;" value=""
								rows="2" type="_moz">
							<bean:write name="rs" property="xxqk" />
							</textarea>
						
						</td>
					</tr>
					<logic:equal name="IsKns" value="yes">
						<tr>
							<td rowspan="6" scope="row" width="4%">
								<div align="center">
									��
									<br />
									ͥ
									<br />
									��
									<br />
									Ҫ
									<br />
									��
									<br />
									Ա
									<br />
									��
									<br />
									��
								</div>
							</td>
							<td width="12%">
								<div align="center">
									����
								</div>
							</td>
							<td width="10%">
								<div align="center">
									��ν
								</div>
							</td>
							<td width="10%">
								<div align="center">
									����
								</div>
							</td>
							<td width="14%">
								<div align="center">
									����״��
								</div>
							</td>
							<td colspan="2">
								<div align="center">
									����(ѧϰ)��λ��ְ��
								</div>
							</td>
							<td width="12%">
								<div align="center">
									������(Ԫ)
								</div>
							</td>
						</tr>
						<tr>
							<td>
								<div align="center">
									<input type="text" id="jtcy1_xm" readonly="readonly"
										name="jtcy1_xm" style="width:100%;heigh:100%"
										value="<bean:write name="rs" property="jtcy1_xm"/>">
								</div>
							</td>
							<td>
								<div align="center">
									<input type="text" id="jtcy1_cw" readonly="readonly"
										name="jtcy1_cw" style="width:100%;heigh:100%"
										value="<bean:write name="rs" property="jtcy1_cw"/>">
								</div>
							</td>
							<td>
								<div align="center">
									<input type="text" id="jtcy1_nl" readonly="readonly"
										name="jtcy1_nl" style="width:100%;heigh:100%"
										value="<bean:write name="rs" property="jtcy1_nl"/>">
								</div>
							</td>
							<td>
								<div align="center">
									<input type="text" id="jtcy1_jkzk" readonly="readonly"
										name="jtcy1_jkzk" style="width:100%;heigh:100%"
										value="<bean:write name="rs" property="jtcy1_jkzk"/>">
								</div>
							</td>
							<td colspan="2">
								<div align="center">
									<input type="text" id="jtcy1_gzdwjzw" readonly="readonly"
										name="jtcy1_gzdwjzw" style="width:100%;heigh:100%"
										value="<bean:write name="rs" property="jtcy1_gzdwjzw"/>">
								</div>
							</td>
							<td>
								<div align="center">
									<input type="text" id="jtcy1_nsr" readonly="readonly"
										name="jtcy1_nsr" style="width:100%;heigh:100%"
										value="<bean:write name="rs" property="jtcy1_nsr"/>">
								</div>
							</td>
						</tr>
						<tr>
							<td>
								<div align="center">
									<input type="text" id="jtcy2_xm" readonly="readonly"
										name="jtcy2_xm" style="width:100%;heigh:100%"
										value="<bean:write name="rs" property="jtcy2_xm"/>">
								</div>
							</td>
							<td>
								<div align="center">
									<input type="text" id="jtcy2_cw" readonly="readonly"
										name="jtcy2_cw" style="width:100%;heigh:100%"
										value="<bean:write name="rs" property="jtcy2_cw"/>">
								</div>
							</td>
							<td>
								<div align="center">
									<input type="text" id="jtcy2_nl" readonly="readonly"
										name="jtcy2_nl" style="width:100%;heigh:100%"
										value="<bean:write name="rs" property="jtcy2_nl"/>">
								</div>
							</td>
							<td>
								<div align="center">
									<input type="text" id="jtcy2_jkzk" readonly="readonly"
										name="jtcy2_jkzk" style="width:100%;heigh:100%"
										value="<bean:write name="rs" property="jtcy2_jkzk"/>">
								</div>
							</td>
							<td colspan="2">
								<div align="center">
									<input type="text" id="jtcy2_gzdwjzw" readonly="readonly"
										name="jtcy2_gzdwjzw" style="width:100%;heigh:100%"
										value="<bean:write name="rs" property="jtcy2_gzdwjzw"/>">
								</div>
							</td>
							<td>
								<div align="center">
									<input type="text" id="jtcy2_nsr" readonly="readonly"
										name="jtcy2_nsr" style="width:100%;heigh:100%"
										value="<bean:write name="rs" property="jtcy2_nsr"/>">
								</div>
							</td>
						</tr>
						<tr>
							<td>
								<div align="center">
									<input type="text" id="jtcy3_xm" readonly="readonly"
										name="jtcy3_xm" style="width:100%;heigh:100%"
										value="<bean:write name="rs" property="jtcy3_xm"/>">
								</div>
							</td>
							<td>
								<div align="center">
									<input type="text" id="jtcy3_cw" readonly="readonly"
										name="jtcy3_cw" style="width:100%;heigh:100%"
										value="<bean:write name="rs" property="jtcy3_cw"/>">
								</div>
							</td>
							<td>
								<div align="center">
									<input type="text" id="jtcy3_nl" readonly="readonly"
										name="jtcy3_nl" style="width:100%;heigh:100%"
										value="<bean:write name="rs" property="jtcy3_nl"/>">
								</div>
							</td>
							<td>
								<div align="center">
									<input type="text" id="jtcy3_jkzk" readonly="readonly"
										name="jtcy3_jkzk" style="width:100%;heigh:100%"
										value="<bean:write name="rs" property="jtcy3_jkzk"/>">
								</div>
							</td>
							<td colspan="2">
								<div align="center">
									<input type="text" id="jtcy3_gzdwjzw" readonly="readonly"
										name="jtcy3_gzdwjzw" style="width:100%;heigh:100%"
										value="<bean:write name="rs" property="jtcy3_gzdwjzw"/>">
								</div>
							</td>
							<td>
								<div align="center">
									<input type="text" id="jtcy3_nsr" readonly="readonly"
										name="jtcy3_nsr" style="width:100%;heigh:100%"
										value="<bean:write name="rs" property="jtcy3_nsr"/>">
								</div>
							</td>
						</tr>
						<tr>
							<td>
								<div align="center">
									<input type="text" id="jtcy4_xm" readonly="readonly"
										name="jtcy4_xm" style="width:100%;heigh:100%"
										value="<bean:write name="rs" property="jtcy4_xm"/>">
								</div>
							</td>
							<td>
								<div align="center">
									<input type="text" id="jtcy4_cw" readonly="readonly"
										name="jtcy4_cw" style="width:100%;heigh:100%"
										value="<bean:write name="rs" property="jtcy4_cw"/>">
								</div>
							</td>
							<td>
								<div align="center">
									<input type="text" id="jtcy4_nl" readonly="readonly"
										name="jtcy4_nl" style="width:100%;heigh:100%"
										value="<bean:write name="rs" property="jtcy4_nl"/>">
								</div>
							</td>
							<td>
								<div align="center">
									<input type="text" id="jtcy4_jkzk" readonly="readonly"
										name="jtcy4_jkzk" style="width:100%;heigh:100%"
										value="<bean:write name="rs" property="jtcy4_jkzk"/>">
								</div>
							</td>
							<td colspan="2">
								<div align="center">
									<input type="text" id="jtcy4_gzdwjzw" readonly="readonly"
										name="jtcy4_gzdwjzw" style="width:100%;heigh:100%"
										value="<bean:write name="rs" property="jtcy4_gzdwjzw"/>">
								</div>
							</td>
							<td>
								<div align="center">
									<input type="text" id="jtcy4_nsr" readonly="readonly"
										name="jtcy4_nsr" style="width:100%;heigh:100%"
										value="<bean:write name="rs" property="jtcy4_nsr"/>">
								</div>
							</td>
						</tr>
						<tr>
							<td>
								<div align="center">
									<input type="text" id="jtcy5_xm" readonly="readonly"
										name="jtcy5_xm" style="width:100%;heigh:100%"
										value="<bean:write name="rs" property="jtcy5_xm"/>">
								</div>
							</td>
							<td>
								<div align="center">
									<input type="text" id="jtcy5_cw" readonly="readonly"
										name="jtcy5_cw" style="width:100%;heigh:100%"
										value="<bean:write name="rs" property="jtcy5_cw"/>">
								</div>
							</td>
							<td>
								<div align="center">
									<input type="text" id="jtcy5_nl" readonly="readonly"
										name="jtcy5_nl" style="width:100%;heigh:100%"
										value="<bean:write name="rs" property="jtcy5_nl"/>">
								</div>
							</td>
							<td>
								<div align="center">
									<input type="text" id="jtcy5_jkzk" readonly="readonly"
										name="jtcy5_jkzk" style="width:100%;heigh:100%"
										value="<bean:write name="rs" property="jtcy5_jkzk"/>">
								</div>
							</td>
							<td colspan="2">
								<div align="center">
									<input type="text" id="jtcy5_gzdwjzw" readonly="readonly"
										name="jtcy5_gzdwjzw" style="width:100%;heigh:100%"
										value="<bean:write name="rs" property="jtcy5_gzdwjzw"/>">
								</div>
							</td>
							<td>
								<div align="center">
									<input type="text" id="jtcy5_nsr" readonly="readonly"
										name="jtcy5_nsr" style="width:100%;heigh:100%"
										value="<bean:write name="rs" property="jtcy5_nsr"/>">
								</div>
							</td>
						</tr>
						<tr>
							<td colspan="2">
								<div align="center">
									�¶�
								</div>
							</td>
							<td colspan="3" align="center">
								<bean:write name="rs" property="sfgr" />
								<input type="hidden" id="sfgr" name="sfgr"
									value="<bean:write name="rs" property="sfgr" scope="request" />">
							</td>
							<td>
								<div align="center">
									��ʿ��Ů
								</div>
							</td>
							<td colspan="2" align="center">
								<bean:write name="rs" property="sflszn" />
								<input type="hidden" id="sflszn" name="sflszn"
									value="<bean:write name="rs" property="sflszn" scope="request" />">
							</td>
						</tr>
						<tr>
							<td colspan="2">
								<div align="center">
									�����뻧
								</div>
							</td>
							<td colspan="3" align="center">
								<bean:write name="rs" property="sfwsrh" />
								<input type="hidden" id="sfwsrh" name="sfwsrh"
									value="<bean:write name="rs" property="sfwsrh" scope="request" />">
							</td>
							<td>
								<div align="center">
									�ز���
								</div>
							</td>
							<td colspan="2" align="center">
								<bean:write name="rs" property="sfzbh" />
								<input type="hidden" id="sfzbh" name="sfzbh"
									value="<bean:write name="rs" property="sfzbh" scope="request" />">
							</td>
						</tr>
						<tr>
							<td colspan="2">
								<div align="center">
									�ͱ���
								</div>
							</td>
							<td colspan="3" align="center">
								<bean:write name="rs" property="sfdbh" />
								<input type="hidden" id="sfdbh" name="sfdbh"
									value="<bean:write name="rs" property="sfdbh" scope="request" />">
							</td>
							<td>
								<div align="center">
									��ĸ˫�¸�
								</div>
							</td>
							<td colspan="2" align="center">
								<bean:write name="rs" property="sffmsxg" />
								<input type="hidden" id="sffmsxg" name="sffmsxg"
									value="<bean:write name="rs" property="sffmsxg" scope="request" />">
							</td>
						</tr>
						<tr>
							<td colspan="2">
								<div align="center">
									��ũ��
								</div>
							</td>
							<td colspan="3" align="center">
								<bean:write name="rs" property="sfcnh" />
								<input type="hidden" id="sfcnh" name="sfcnh"
									value="<bean:write name="rs" property="sfcnh" scope="request" />">
							</td>
							<td>
								<div align="center">
									������(��ͥ���벻����֧����ѧ��������)
								</div>
							</td>
							<td colspan="2" align="center">
								<bean:write name="rs" property="sfdsr" />
								<input type="hidden" id="sfdsr" name="sfdsr"
									value="<bean:write name="rs" property="sfdsr" scope="request" />">
							</td>
						</tr>
						<tr>
							<td colspan="2">
								<div align="center">
									��ͥ�������Ѿ������
								</div>
							</td>
							<td colspan="6">
								<bean:write name="rs" property="jtjjknqk" />
							</td>
						</tr>
					</logic:equal>
					<logic:equal name="IsKns" value="no">
						<tr>
							<td rowspan="6" scope="row" width="4%">
								<div align="center">
									��
									<br />
									ͥ
									<br />
									��
									<br />
									Ҫ
									<br />
									��
									<br />
									Ա
									<br />
									��
									<br />
									��
								</div>
							</td>
							<td width="12%">
								<div align="center">
									����
								</div>
							</td>
							<td width="10%">
								<div align="center">
									��ν
								</div>
							</td>
							<td width="10%">
								<div align="center">
									����
								</div>
							</td>
							<td width="14%">
								<div align="center">
									����״��
								</div>
							</td>
							<td colspan="2">
								<div align="center">
									����(ѧϰ)��λ��ְ��
								</div>
							</td>
							<td width="12%">
								<div align="center">
									������(Ԫ)
								</div>
							</td>
						</tr>
						<tr>
							<td>
								<div align="center">
									<input type="text" id="jtcy1_xm" maxlength="40" name="jtcy1_xm"
										style="width:100%;heigh:100%"
										value="<bean:write name="rs" property="jtcy1_xm"/>">
								</div>
							</td>
							<td>
								<div align="center">
									<input type="text" id="jtcy1_cw" maxlength="40" name="jtcy1_cw"
										style="width:100%;heigh:100%"
										value="<bean:write name="rs" property="jtcy1_cw"/>">
								</div>
							</td>
							<td>
								<div align="center">
									<input type="text" id="jtcy1_nl" maxlength="10" name="jtcy1_nl"
										style="width:100%;heigh:100%"
										value="<bean:write name="rs" property="jtcy1_nl"/>"
										onkeyup="value=value.replace(/[^\d]/g,'') "
										onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
								</div>
							</td>
							<td>
								<div align="center">
									<input type="text" id="jtcy1_jkzk" maxlength="40"
										name="jtcy1_jkzk" style="width:100%;heigh:100%"
										value="<bean:write name="rs" property="jtcy1_jkzk"/>">
								</div>
							</td>
							<td colspan="2">
								<div align="center">
									<input type="text" id="jtcy1_gzdwjzw" maxlength="100"
										name="jtcy1_gzdwjzw" style="width:100%;heigh:100%"
										value="<bean:write name="rs" property="jtcy1_gzdwjzw"/>">
								</div>
							</td>
							<td>
								<div align="center">
									<input type="text" id="jtcy1_nsr" maxlength="10"
										name="jtcy1_nsr" style="width:100%;heigh:100%"
										value="<bean:write name="rs" property="jtcy1_nsr"/>"
										onkeyup="value=value.replace(/[^\d]/g,'') "
										onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
								</div>
							</td>
						</tr>
						<tr>
							<td>
								<div align="center">
									<input type="text" id="jtcy2_xm" maxlength="40" name="jtcy2_xm"
										style="width:100%;heigh:100%"
										value="<bean:write name="rs" property="jtcy2_xm"/>">
								</div>
							</td>
							<td>
								<div align="center">
									<input type="text" id="jtcy2_cw" maxlength="40" name="jtcy2_cw"
										style="width:100%;heigh:100%"
										value="<bean:write name="rs" property="jtcy2_cw"/>">
								</div>
							</td>
							<td>
								<div align="center">
									<input type="text" id="jtcy2_nl" maxlength="10" name="jtcy2_nl"
										style="width:100%;heigh:100%"
										value="<bean:write name="rs" property="jtcy2_nl"/>"
										onkeyup="value=value.replace(/[^\d]/g,'') "
										onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
								</div>
							</td>
							<td>
								<div align="center">
									<input type="text" id="jtcy2_jkzk" maxlength="40"
										name="jtcy2_jkzk" style="width:100%;heigh:100%"
										value="<bean:write name="rs" property="jtcy2_jkzk"/>">
								</div>
							</td>
							<td colspan="2">
								<div align="center">
									<input type="text" id="jtcy2_gzdwjzw" maxlength="100"
										name="jtcy2_gzdwjzw" style="width:100%;heigh:100%"
										value="<bean:write name="rs" property="jtcy2_gzdwjzw"/>">
								</div>
							</td>
							<td>
								<div align="center">
									<input type="text" id="jtcy2_nsr" maxlength="10"
										name="jtcy2_nsr" style="width:100%;heigh:100%"
										value="<bean:write name="rs" property="jtcy2_nsr"/>"
										onkeyup="value=value.replace(/[^\d]/g,'') "
										onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
								</div>
							</td>
						</tr>
						<tr>
							<td>
								<div align="center">
									<input type="text" id="jtcy3_xm" maxlength="40" name="jtcy3_xm"
										style="width:100%;heigh:100%"
										value="<bean:write name="rs" property="jtcy3_xm"/>">
								</div>
							</td>
							<td>
								<div align="center">
									<input type="text" id="jtcy3_cw" maxlength="40" name="jtcy3_cw"
										style="width:100%;heigh:100%"
										value="<bean:write name="rs" property="jtcy3_cw"/>">
								</div>
							</td>
							<td>
								<div align="center">
									<input type="text" id="jtcy3_nl" maxlength="10" name="jtcy3_nl"
										style="width:100%;heigh:100%"
										value="<bean:write name="rs" property="jtcy3_nl"/>"
										onkeyup="value=value.replace(/[^\d]/g,'') "
										onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
								</div>
							</td>
							<td>
								<div align="center">
									<input type="text" id="jtcy3_jkzk" maxlength="40"
										name="jtcy3_jkzk" style="width:100%;heigh:100%"
										value="<bean:write name="rs" property="jtcy3_jkzk"/>">
								</div>
							</td>
							<td colspan="2">
								<div align="center">
									<input type="text" id="jtcy3_gzdwjzw" maxlength="100"
										name="jtcy3_gzdwjzw" style="width:100%;heigh:100%"
										value="<bean:write name="rs" property="jtcy3_gzdwjzw"/>">
								</div>
							</td>
							<td>
								<div align="center">
									<input type="text" id="jtcy3_nsr" maxlength="10"
										name="jtcy3_nsr" style="width:100%;heigh:100%"
										value="<bean:write name="rs" property="jtcy3_nsr"/>"
										onkeyup="value=value.replace(/[^\d]/g,'') "
										onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
								</div>
							</td>
						</tr>
						<tr>
							<td>
								<div align="center">
									<input type="text" id="jtcy4_xm" maxlength="40" name="jtcy4_xm"
										style="width:100%;heigh:100%"
										value="<bean:write name="rs" property="jtcy4_xm"/>">
								</div>
							</td>
							<td>
								<div align="center">
									<input type="text" id="jtcy4_cw" maxlength="40" name="jtcy4_cw"
										style="width:100%;heigh:100%"
										value="<bean:write name="rs" property="jtcy4_cw"/>">
								</div>
							</td>
							<td>
								<div align="center">
									<input type="text" id="jtcy4_nl" maxlength="10" name="jtcy4_nl"
										style="width:100%;heigh:100%"
										value="<bean:write name="rs" property="jtcy4_nl"/>"
										onkeyup="value=value.replace(/[^\d]/g,'') "
										onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
								</div>
							</td>
							<td>
								<div align="center">
									<input type="text" id="jtcy4_jkzk" maxlength="40"
										name="jtcy4_jkzk" style="width:100%;heigh:100%"
										value="<bean:write name="rs" property="jtcy4_jkzk"/>">
								</div>
							</td>
							<td colspan="2">
								<div align="center">
									<input type="text" id="jtcy4_gzdwjzw" maxlength="100"
										name="jtcy4_gzdwjzw" style="width:100%;heigh:100%"
										value="<bean:write name="rs" property="jtcy4_gzdwjzw"/>">
								</div>
							</td>
							<td>
								<div align="center">
									<input type="text" id="jtcy4_nsr" maxlength="10"
										name="jtcy4_nsr" style="width:100%;heigh:100%"
										value="<bean:write name="rs" property="jtcy4_nsr"/>"
										onkeyup="value=value.replace(/[^\d]/g,'') "
										onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
								</div>
							</td>
						</tr>
						<tr>
							<td>
								<div align="center">
									<input type="text" id="jtcy5_xm" maxlength="40" name="jtcy5_xm"
										style="width:100%;heigh:100%"
										value="<bean:write name="rs" property="jtcy5_xm"/>">
								</div>
							</td>
							<td>
								<div align="center">
									<input type="text" id="jtcy5_cw" maxlength="40" name="jtcy5_cw"
										style="width:100%;heigh:100%"
										value="<bean:write name="rs" property="jtcy5_cw"/>">
								</div>
							</td>
							<td>
								<div align="center">
									<input type="text" id="jtcy5_nl" maxlength="10" name="jtcy5_nl"
										style="width:100%;heigh:100%"
										value="<bean:write name="rs" property="jtcy5_nl"/>"
										onkeyup="value=value.replace(/[^\d]/g,'') "
										onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
								</div>
							</td>
							<td>
								<div align="center">
									<input type="text" id="jtcy5_jkzk" maxlength="40"
										name="jtcy5_jkzk" style="width:100%;heigh:100%"
										value="<bean:write name="rs" property="jtcy5_jkzk"/>">
								</div>
							</td>
							<td colspan="2">
								<div align="center">
									<input type="text" id="jtcy5_gzdwjzw" maxlength="100"
										name="jtcy5_gzdwjzw" style="width:100%;heigh:100%"
										value="<bean:write name="rs" property="jtcy5_gzdwjzw"/>">
								</div>
							</td>
							<td>
								<div align="center">
									<input type="text" id="jtcy5_nsr" maxlength="10"
										name="jtcy5_nsr" style="width:100%;heigh:100%"
										value="<bean:write name="rs" property="jtcy5_nsr"/>"
										onkeyup="value=value.replace(/[^\d]/g,'') "
										onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
								</div>
							</td>
						</tr>
						<tr>
							<td colspan="2">
								<div align="center">
									�¶�
								</div>
							</td>
							<td colspan="3" align="center">
								<logic:present name="rs" property="sfgr">
									<logic:match value="��" name="rs" property="sfgr">
										<input type="radio" name="sfgr" value="��" checked>&nbsp;&nbsp;��
							    <input type="radio" name="sfgr" value="��">&nbsp;&nbsp;��
							</logic:match>
									<logic:match value="��" name="rs" property="sfgr">
										<input type="radio" name="sfgr" value="��">&nbsp;&nbsp;��
							    <input type="radio" name="sfgr" value="��" checked>&nbsp;&nbsp;��
							</logic:match>
								</logic:present>
								<logic:notPresent name="rs" property="sfgr">
									<input type="radio" name="sfgr" value="��">&nbsp;&nbsp;�� 
							<input type="radio" name="sfgr" value="��" checked>&nbsp;&nbsp;��
						</logic:notPresent>
							</td>
							<td>
								<div align="center">
									��ʿ��Ů
								</div>
							</td>
							<td colspan="2" align="center">
								<logic:present name="rs" property="sflszn">
									<logic:match value="��" name="rs" property="sflszn">
										<input type="radio" name="sflszn" value="��" checked>&nbsp;&nbsp;��
							    <input type="radio" name="sflszn" value="��">&nbsp;&nbsp;��
							</logic:match>
									<logic:match value="��" name="rs" property="sflszn">
										<input type="radio" name="sflszn" value="��">&nbsp;&nbsp;��
							    <input type="radio" name="sflszn" value="��" checked>&nbsp;&nbsp;��
							</logic:match>
								</logic:present>
								<logic:notPresent name="rs" property="sflszn">
									<input type="radio" name="sflszn" value="��">&nbsp;&nbsp;�� 
							<input type="radio" name="sflszn" value="��" checked>&nbsp;&nbsp;��
						</logic:notPresent>
							</td>
						</tr>
						<tr>
							<td colspan="2">
								<div align="center">
									�����뻧
								</div>
							</td>
							<td colspan="3" align="center">
								<logic:present name="rs" property="sfwsrh">
									<logic:match value="��" name="rs" property="sfwsrh">
										<input type="radio" name="sfwsrh" value="��" checked>&nbsp;&nbsp;��
							    <input type="radio" name="sfwsrh" value="��">&nbsp;&nbsp;��
							</logic:match>
									<logic:match value="��" name="rs" property="sfwsrh">
										<input type="radio" name="sfwsrh" value="��">&nbsp;&nbsp;��
							    <input type="radio" name="sfwsrh" value="��" checked>&nbsp;&nbsp;��
							</logic:match>
								</logic:present>
								<logic:notPresent name="rs" property="sfwsrh">
									<input type="radio" name="sfwsrh" value="��">&nbsp;&nbsp;�� 
							<input type="radio" name="sfwsrh" value="��" checked>&nbsp;&nbsp;��
						</logic:notPresent>
							</td>
							<td>
								<div align="center">
									�ز���
								</div>
							</td>
							<td colspan="2" align="center">
								<logic:present name="rs" property="sfzbh">
									<logic:match value="��" name="rs" property="sfzbh">
										<input type="radio" name="sfzbh" value="��" checked>&nbsp;&nbsp;��
							    <input type="radio" name="sfzbh" value="��">&nbsp;&nbsp;��
							</logic:match>
									<logic:match value="��" name="rs" property="sfzbh">
										<input type="radio" name="sfzbh" value="��">&nbsp;&nbsp;��
							    <input type="radio" name="sfzbh" value="��" checked>&nbsp;&nbsp;��
							</logic:match>
								</logic:present>
								<logic:notPresent name="rs" property="sfzbh">
									<input type="radio" name="sfzbh" value="��">&nbsp;&nbsp;�� 
							<input type="radio" name="sfzbh" value="��" checked>&nbsp;&nbsp;��
						</logic:notPresent>
							</td>
						</tr>
						<tr>
							<td colspan="2">
								<div align="center">
									�ͱ���
								</div>
							</td>
							<td colspan="3" align="center">
								<logic:present name="rs" property="sfdbh">
									<logic:match value="��" name="rs" property="sfdbh">
										<input type="radio" name="sfdbh" value="��" checked>&nbsp;&nbsp;��
							    <input type="radio" name="sfdbh" value="��">&nbsp;&nbsp;��
							</logic:match>
									<logic:match value="��" name="rs" property="sfdbh">
										<input type="radio" name="sfdbh" value="��">&nbsp;&nbsp;��
							    <input type="radio" name="sfdbh" value="��" checked>&nbsp;&nbsp;��
							</logic:match>
								</logic:present>
								<logic:notPresent name="rs" property="sfdbh">
									<input type="radio" name="sfdbh" value="��">&nbsp;&nbsp;�� 
							<input type="radio" name="sfdbh" value="��" checked>&nbsp;&nbsp;��
						</logic:notPresent>
							</td>
							<td>
								<div align="center">
									��ĸ˫�¸�
								</div>
							</td>
							<td colspan="2" align="center">
								<logic:present name="rs" property="sffmsxg">
									<logic:match value="��" name="rs" property="sffmsxg">
										<input type="radio" name="sffmsxg" value="��" checked>&nbsp;&nbsp;��
							    <input type="radio" name="sffmsxg" value="��">&nbsp;&nbsp;��
							</logic:match>
									<logic:match value="��" name="rs" property="sffmsxg">
										<input type="radio" name="sffmsxg" value="��">&nbsp;&nbsp;��
							    <input type="radio" name="sffmsxg" value="��" checked>&nbsp;&nbsp;��
							</logic:match>
								</logic:present>
								<logic:notPresent name="rs" property="sffmsxg">
									<input type="radio" name="sffmsxg" value="��">&nbsp;&nbsp;�� 
							<input type="radio" name="sffmsxg" value="��" checked>&nbsp;&nbsp;��
						</logic:notPresent>
							</td>
						</tr>
						<tr>
							<td colspan="2">
								<div align="center">
									��ũ��
								</div>
							</td>
							<td colspan="3" align="center">
								<logic:present name="rs" property="sfcnh">
									<logic:match value="��" name="rs" property="sfcnh">
										<input type="radio" name="sfcnh" value="��" checked>&nbsp;&nbsp;��
							    <input type="radio" name="sfcnh" value="��">&nbsp;&nbsp;��
							</logic:match>
									<logic:match value="��" name="rs" property="sfcnh">
										<input type="radio" name="sfcnh" value="��">&nbsp;&nbsp;��
							    <input type="radio" name="sfcnh" value="��" checked>&nbsp;&nbsp;��
							</logic:match>
								</logic:present>
								<logic:notPresent name="rs" property="sfcnh">
									<input type="radio" name="sfcnh" value="��">&nbsp;&nbsp;�� 
							<input type="radio" name="sfcnh" value="��" checked>&nbsp;&nbsp;��
						</logic:notPresent>
							</td>
							<td>
								<div align="center">
									������(��ͥ���벻����֧����ѧ��������)
								</div>
							</td>
							<td colspan="2" align="center">
								<logic:present name="rs" property="sfdsr">
									<logic:match value="��" name="rs" property="sfdsr">
										<input type="radio" name="sfdsr" value="��" checked>&nbsp;&nbsp;��
							    <input type="radio" name="sfdsr" value="��">&nbsp;&nbsp;��
							</logic:match>
									<logic:match value="��" name="rs" property="sfdsr">
										<input type="radio" name="sfdsr" value="��">&nbsp;&nbsp;��
							    <input type="radio" name="sfdsr" value="��" checked>&nbsp;&nbsp;��
							</logic:match>
								</logic:present>
								<logic:notPresent name="rs" property="sfdsr">
									<input type="radio" name="sfdsr" value="��">&nbsp;&nbsp;�� 
							<input type="radio" name="sfdsr" value="��" checked>&nbsp;&nbsp;��
						</logic:notPresent>
							</td>
						</tr>
					</logic:equal>
					<tr>
						<td colspan="2">
							<div align="center">
								ÿ��Ӧ���ɸ��ַ���
							</div>
						</td>
						<td colspan="3">
							<input type="text" id="mnyjngzfy" name="mnyjngzfy" maxlength="10"
								style="width:100%;heigh:100%" onblur="xfhj();"
								value="<bean:write name="rs" property="mnyjngzfy"/>"
								onkeyup="value=value.replace(/[^\d]/g,'') "
								onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
						</td>
						<td>
							<div align="center">
								��ͥÿ���ṩ
							</div>
						</td>
						<td colspan="2">
							<input type="text" id="jtmntg" name="jtmntg" maxlength="10"
								style="width:100%;heigh:100%" onblur="xfhj();"
								value="<bean:write name="rs" property="jtmntg"/>"
								onkeyup="value=value.replace(/[^\d]/g,'') "
								onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
						</td>
					</tr>
					<tr>
						<td colspan="2">
							<div align="center">
								�ϼ�ÿ����ȱ����
							</div>
						</td>
						<td colspan="3">
							<input type="text" id="hjmnsqfy" name="hjmnsqfy"
								readonly="readonly" style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="hjmnsqfy"/>">
						</td>
						<td>
							<div align="center">
								Ƿ��ѧ����
							</div>
						</td>
						<td colspan="2">
							<input type="text" id="qjxfs" name="qjxfs" maxlength="10"
								style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="qjxfs"/>"
								onkeyup="value=value.replace(/[^\d]/g,'') "
								onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
						</td>
					</tr>
					<tr>
						<td colspan="2">
							<div align="center">
								�Ѵ������༰���
							</div>
						</td>
						<td colspan="6">
							<input type="text" id="yhdkzljje" maxlength="200"
								name="yhdkzljje" style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="yhdkzljje"/>">
						</td>
					</tr>
					<tr>
						<td colspan="2">
							<div align="center">
								Ŀǰ�ڹ���ѧ���
							</div>
						</td>
						<td colspan="6">
							<textarea id="mqqgzxqk" name="mqqgzxqk" style="width:100%;"
								value="" rows="6" type="_moz">
							<bean:write name="rs" property="mqqgzxqk" />
							</textarea>
						</td>
					</tr>
					<tr>
						<td align="center" colspan="2">
							��������
						</td>
						<td colspan="6">
							<textarea id="xssq" name="xssq" style="width:100%;" value=""
								rows="6" type="_moz">
							<bean:write name="rs" property="xssq" />
						</textarea>
						</td>
					</tr>
					<tr>
						<td align="center" colspan="2">
							��ע
						</td>
						<td colspan="6">
							<textarea id="bz" name="bz" style="width:100%;" value="" rows="6"
								type="_moz">
							<bean:write name="rs" property="bz" />
						</textarea>
						</td>
					</tr>
				</table>
				<div class="buttontool" align="center">
					<button type="button" class="button2" onclick="yz();">
						�� �� �� ��
					</button>
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<button type="button" class="button2" onclick="print();">
						�� ӡ Ԥ ��
					</button>
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
