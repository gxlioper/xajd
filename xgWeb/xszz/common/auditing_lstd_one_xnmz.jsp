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
	<base target="_self">
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<link id="csss" rel="stylesheet" rev="stylesheet"
		href="js/calendar.css" type="text/css" media="all" />
	<script language="javascript">
</script>
	<body onload="checkWinType();document.all('buttonClose').focus()">
		<script language="javascript" src="js/commanFunction.js"></script>
		<script language="javascript" src="js/function.js"></script>
		<script language="javascript" src="js/xszzFunction.js"></script>
		<script type="text/javascript" src="js/calendar.js"></script>
		<script type="text/javascript" src="js/calendar-zh.js"></script>
		<script type="text/javascript" src="js/calendar-setup.js"></script>
		<script language="javascript" src="js/String.js"></script>
		<script language="javascript">
		function yz(){
			var xyshyj = document.getElementById('xyshyj').value;
			var xxshyj = document.getElementById('xxshyj').value;
			var fdyshyj = document.getElementById('fdyshyj').value;
			var isXX = document.getElementById('isXX').value;
			var xxsh = document.getElementById('xxsh').value;
			if(("ͨ��" == xxsh) && (isXX != "is")){
				alert("ѧУ�����ͨ�����������޸���˽��!");
	          	return false;
			}
			if(fdyshyj != null){
				if(fdyshyj.replace(/[^\u0000-\u00ff]/g, "**").length > 100){	         
	          		 alert("����Ա���������ܴ���100���ַ�");
	          		 return false;
	       		 }	
			}
			if(xyshyj != null){
	         	if(xyshyj.replace(/[^\u0000-\u00ff]/g, "**").length > 100){	         
	          		 alert("<bean:message key="lable.xsgzyxpzxy" />���������ܴ���100���ַ�");
	          		 return false;
	       		 }
	       	}
	       	if(xxshyj != null){
	         	if(xxshyj.replace(/[^\u0000-\u00ff]/g, "**").length > 100){	         
	          		 alert("ѧУ���������ܴ���100���ַ�");
	          		 return false;
	       		 }
	       	}
			
			refreshForm('/xgxt/auditing_lstd_one.do?actDo=save');Close();window.dialogArguments.document.getElementById('search_go').click();
		}
		function yzdx(obj,str){
			document.getElementById(str).value = obj.value;
		}
		function toPrintOut(titName){
			document.forms[0].action = "/xgxt/xnmz_knssqb.do";
			document.forms[0].submit();
		}
		</script>
		<html:form action="/data_search" method="post">
			<div class="title">
				<div class="title_img" id="title_m">
					��ǰ����λ�ã�ѧ������ - ��� - ѧ����ͥ���������� - �������
				</div>
			</div>
			<input type="hidden" name="pkVal" value="<bean:write name="pk"/>" />
			<input type="hidden" id="act" name="act"
				value="<bean:write name="act" scope="request"/>" />
			<input type="hidden" name="tName" value="<bean:write name="tName" />" />
			<input type="hidden" id="titName" name="titName"
				value="<bean:write name="titName" scope="request" />">
			<input type="hidden" id="xxsh" name="xxsh"
				value="<bean:write name="rs" property="xxsh" />" />
			<input type="hidden" id="isXX" name="isXX"
				value="<bean:write name="isXX" />" />
			<table width="98%" align="center" class="tbstyle">
				<thead>
					<tr style="height:22px">
						<td colspan="8" align="center">
						</td>
					</tr>
				</thead>
				<tr>
					<td align="right" colspan="3">
						ѧ�ţ�
					</td>
					<td align="left" colspan="2">
						<input type="hidden" name="xh" value="<bean:write name="rs" property="xh" />" />
						<bean:write name="rs" property="xh" />
					</td>
					<td width="16%">
						<div align="right">
							������
						</div>
					</td>
					<td colspan="3" align="left">
						<input type="hidden" name="xm" value="<bean:write name="rs" property="xm" />" />
						<bean:write name="rs" property="xm" />
					</td>
				</tr>
				<tr>
					<td colspan="3">
						<div align="right">
							ѧУ���ƣ�
						</div>
					</td>
					<td colspan="2" align="left">
						<input type="hidden" name="xxmc"
							value="<bean:write name="rs" property="xxmc" />" />
						<bean:write name="rs" property="xxmc" />
					</td>
					<td>
						<div align="right">
							<bean:message key="lable.xsgzyxpzxy" />���ƣ�
						</div>
					</td>
					<td colspan="3" align="left">
						<input type="hidden" name="xymc"
							value="<bean:write name="rs" property="xymc" />" />
						<bean:write name="rs" property="xymc" />
					</td>
				</tr>
				<tr>
					<td colspan="3">
						<div align="right">
							רҵ���ƣ�
						</div>
					</td>
					<td colspan="2" align="left">
						<input type="hidden" name="xmc" value="<bean:write name="rs" property="xmc" />" />
						<bean:write name="rs" property="xmc" />
					</td>
					<td>
						<div align="right">
							�꼶��
						</div>
					</td>
					<td colspan="3" align="left">
						<input type="hidden" name="nj" value="<bean:write name="rs" property="nj" />" />
						<bean:write name="rs" property="nj" />
					</td>
				</tr>
				<tr>
					<td colspan="3">
						<div align="right">
							�Ա�
						</div>
					</td>
					<td colspan="2" align="left">
						<input type="hidden" name="xb" value="<bean:write name="rs" property="xb" />" />
						<bean:write name="rs" property="xb" />
					</td>
					<td>
						<div align="right">
							�������£�
						</div>
					</td>
					<td colspan="3" align="left">
						<input type="hidden" name="csny"
							value="<bean:write name="rs" property="csny" />" />
						<bean:write name="rs" property="csny" />
					</td>
				</tr>
				<tr>
					<td colspan="3">
						<div align="right">
							�������ƣ�
						</div>
					</td>
					<td colspan="2" align="left">
						<input type="hidden" name="mzmc"
							value="<bean:write name="rs" property="mzmc" />" />
						<bean:write name="rs" property="mzmc" />
					</td>
					<td>
						<div align="right">
							���֤�ţ�
						</div>
					</td>
					<td colspan="3" align="left">
						<input type="hidden" name="sfzh"
							value="<bean:write name="rs" property="sfzh" />" />
						<bean:write name="rs" property="sfzh" />
					</td>
				</tr>
				<tr>
					<td colspan="3">
						<div align="right">
							������ò��
						</div>
					</td>
					<td colspan="2" align="left">
						<input type="hidden" name="zzmm"
							value="<bean:write name="rs" property="zzmm" />" />
						<bean:write name="rs" property="zzmm" />
					</td>
					<td>
						<div align="right">
							��ѧǰ���ڣ�
						</div>
					</td>
					<td colspan="3" align="left">
						<input type="hidden" name="rxqhk"
							value="<bean:write name="rs" property="rxqhk" />" />
						<bean:write name="rs" property="rxqhk" />
					</td>
				</tr>
				<tr>
					<td colspan="3">
						<div align="right">
							��ҵѧУ��
						</div>
					</td>
					<td colspan="2" align="left">
						<input type="hidden" name="byxx"
							value="<bean:write name="rs" property="byxx" />" />
						<bean:write name="rs" property="byxx" />
					</td>
					<td>
						<div align="right">
							�����س���
						</div>
					</td>
					<td colspan="3" align="left">
						<input type="hidden" name="grtc"
							value="<bean:write name="rs" property="grtc" />" />
						<bean:write name="rs" property="grtc" />
					</td>
				</tr>
				<tr>
					<td colspan="3">
						<div align="right">
							�Ƿ�²У�
						</div>
					</td>
					<td colspan="2" align="left">
						<input type="hidden" name="sfgc"
							value="<bean:write name="rs" property="sfgc" />" />
						<bean:write name="rs" property="sfgc" />
					</td>
					<td>
						<div align="right">
							�Ƿ��ף�
						</div>
					</td>
					<td colspan="3" align="left">
						<input type="hidden" name="sfdq"
							value="<bean:write name="rs" property="sfdq" />" />
						<bean:write name="rs" property="sfdq" />
					</td>
				</tr>
				<tr>
					<td colspan="3">
						<div align="right">
							�Ƿ���ʿ��Ů��
						</div>
					</td>
					<td colspan="2" align="left">
						<input type="hidden" name="sflszn"
							value="<bean:write name="rs" property="sflszn" />" />
						<bean:write name="rs" property="sflszn" />
					</td>
					<td>
						<div align="right">
							��ͥ�˿�����
						</div>
					</td>
					<td colspan="3" align="left">
						<input type="hidden" name="jtrks"
							value="<bean:write name="rs" property="jtrks" />" />
						<bean:write name="rs" property="jtrks" />
					</td>
				</tr>
				<tr>
					<td colspan="3">
						<div align="right">
							��ͥ�������룺
						</div>
					</td>
					<td colspan="2" align="left">
						<input type="hidden" name="jtyzbm"
							value="<bean:write name="rs" property="jtyzbm" />" />
						<bean:write name="rs" property="jtyzbm" />
					</td>
					<td>
						<div align="right">
							��ͥ��ϵ�绰��
						</div>
					</td>
					<td colspan="3" align="left">
						<input type="hidden" name="jtlxdh"
							value="<bean:write name="rs" property="jtlxdh" />" />
						<bean:write name="rs" property="jtlxdh" />
					</td>
				</tr>
				<tr>
					<td colspan="3">
						<div align="right">
							��ͥ��ϸͨѶ��ַ��
						</div>
					</td>
					<td colspan="6" align="left">
						<input type="hidden" name="jtxxtxdz"
							value="<bean:write name="rs" property="jtxxtxdz" />" />
						<bean:write name="rs" property="jtxxtxdz" />
					</td>
				</tr>
				<tr>
					<td width="4%" rowspan="6">
						<div align="center">
							��
							<br>
							ͥ
							<br>
							��
							<br>
							Ա
							<br>
							��
							<br>
							��
						</div>
					</td>
					<td width="8%">
						<div align="center">
							����
						</div>
					</td>
					<td width="8%">
						<div align="center">
							����
						</div>
					</td>
					<td width="8%">
						<div align="center">
							��ѧ����ϵ
						</div>
					</td>
					<td colspan="2">
						<div align="center">
							������ѧϰ��λ
						</div>
					</td>
					<td width="12%">
						<div align="center">
							ְҵ
						</div>
					</td>
					<td width="11%">
						<div align="center">
							������(Ԫ)
						</div>
					</td>
					<td width="11%">
						<div align="center">
							����״��
						</div>
					</td>
				</tr>
				<tr>
					<td width="6%">
						<div align="center">
							<input type="hidden" name="jtcy1_xm"
								value="<bean:write name="rs" property="jtcy1_xm" />" />
							<bean:write name="rs" property="jtcy1_xm" />
						</div>
					</td>
					<td width="6%">
						<div align="center">
							<input type="hidden" name="jtcy1_nl"
								value="<bean:write name="rs" property="jtcy1_nl" />" />
							<bean:write name="rs" property="jtcy1_nl" />
						</div>
					</td>
					<td>
						<div align="center">
							<input type="hidden" name="jtcy1_gx"
								value="<bean:write name="rs" property="jtcy1_gx" />" />
							<bean:write name="rs" property="jtcy1_gx" />
						</div>
					</td>
					<td colspan="2">
						<div align="center">
							<input type="hidden" name="jtcy1_gzdw"
								value="<bean:write name="rs" property="jtcy1_gzdw" />" />
							<bean:write name="rs" property="jtcy1_gzdw" />
						</div>
					</td>
					<td>
						<div align="center">
							<input type="hidden" name="jtcy1_zy"
								value="<bean:write name="rs" property="jtcy1_zy" />" />
							<bean:write name="rs" property="jtcy1_zy" />
						</div>
					</td>
					<td>
						<div align="center">
							<input type="hidden" name="jtcy1_nsr"
								value="<bean:write name="rs" property="jtcy1_nsr" />" />
							<bean:write name="rs" property="jtcy1_nsr" />
						</div>
					</td>
					<td>
						<div align="center">
							<input type="hidden" name="jtcy1_jkzk"
								value="<bean:write name="rs" property="jtcy1_jkzk" />" />
							<bean:write name="rs" property="jtcy1_jkzk" />
						</div>
					</td>
				</tr>
				<tr>
					<td width="6%">
						<div align="center">
							<input type="hidden" name="jtcy2_xm"
								value="<bean:write name="rs" property="jtcy2_xm" />" />
							<bean:write name="rs" property="jtcy2_xm" />
						</div>
					</td>
					<td width="6%">
						<div align="center">
							<input type="hidden" name="jtcy2_nl"
								value="<bean:write name="rs" property="jtcy2_nl" />" />
							<bean:write name="rs" property="jtcy2_nl" />
						</div>
					</td>
					<td>
						<div align="center">
							<input type="hidden" name="jtcy2_gx"
								value="<bean:write name="rs" property="jtcy2_gx" />" />
							<bean:write name="rs" property="jtcy2_gx" />
						</div>
					</td>
					<td colspan="2">
						<div align="center">
							<input type="hidden" name="jtcy2_gzdw"
								value="<bean:write name="rs" property="jtcy2_gzdw" />" />
							<bean:write name="rs" property="jtcy2_gzdw" />
						</div>
					</td>
					<td>
						<div align="center">
							<input type="hidden" name="jtcy2_zy"
								value="<bean:write name="rs" property="jtcy2_zy" />" />
							<bean:write name="rs" property="jtcy2_zy" />
						</div>
					</td>
					<td>
						<div align="center">
							<input type="hidden" name="jtcy2_nsr"
								value="<bean:write name="rs" property="jtcy2_nsr" />" />
							<bean:write name="rs" property="jtcy2_nsr" />
						</div>
					</td>
					<td>
						<div align="center">
							<input type="hidden" name="jtcy2_jkzk"
								value="<bean:write name="rs" property="jtcy2_jkzk" />" />
							<bean:write name="rs" property="jtcy2_jkzk" />
						</div>
					</td>
				</tr>
				<tr>
					<td width="6%">
						<div align="center">
							<input type="hidden" name="jtcy3_xm"
								value="<bean:write name="rs" property="jtcy3_xm" />" />
							<bean:write name="rs" property="jtcy3_xm" />
						</div>
					</td>
					<td width="6%">
						<div align="center">
							<input type="hidden" name="jtcy3_nl"
								value="<bean:write name="rs" property="jtcy3_nl" />" />
							<bean:write name="rs" property="jtcy3_nl" />
						</div>
					</td>
					<td>
						<div align="center">
							<input type="hidden" name="jtcy3_gx"
								value="<bean:write name="rs" property="jtcy3_gx" />" />
							<bean:write name="rs" property="jtcy3_gx" />
						</div>
					</td>
					<td colspan="2">
						<div align="center">
							<input type="hidden" name="jtcy3_gzdw"
								value="<bean:write name="rs" property="jtcy3_gzdw" />" />
							<bean:write name="rs" property="jtcy3_gzdw" />
						</div>
					</td>
					<td>
						<div align="center">
							<input type="hidden" name="jtcy3_zy"
								value="<bean:write name="rs" property="jtcy3_zy" />" />
							<bean:write name="rs" property="jtcy3_zy" />
						</div>
					</td>
					<td>
						<div align="center">
							<input type="hidden" name="jtcy3_nsr"
								value="<bean:write name="rs" property="jtcy3_nsr" />" />
							<bean:write name="rs" property="jtcy3_nsr" />
						</div>
					</td>
					<td>
						<div align="center">
							<input type="hidden" name="jtcy3_jkzk"
								value="<bean:write name="rs" property="jtcy3_jkzk" />" />
							<bean:write name="rs" property="jtcy3_jkzk" />
						</div>
					</td>
				</tr>
				<tr>
					<td width="6%">
						<div align="center">
							<input type="hidden" name="jtcy4_xm"
								value="<bean:write name="rs" property="jtcy4_xm" />" />
							<bean:write name="rs" property="jtcy4_xm" />
						</div>
					</td>
					<td width="6%">
						<div align="center">
							<input type="hidden" name="jtcy4_nl"
								value="<bean:write name="rs" property="jtcy4_nl" />" />
							<bean:write name="rs" property="jtcy4_nl" />
						</div>
					</td>
					<td>
						<div align="center">
							<input type="hidden" name="jtcy4_gx"
								value="<bean:write name="rs" property="jtcy4_gx" />" />
							<bean:write name="rs" property="jtcy4_gx" />
						</div>
					</td>
					<td colspan="2">
						<div align="center">
							<input type="hidden" name="jtcy4_gzdw"
								value="<bean:write name="rs" property="jtcy4_gzdw" />" />
							<bean:write name="rs" property="jtcy4_gzdw" />
						</div>
					</td>
					<td>
						<div align="center">
							<input type="hidden" name="jtcy4_zy"
								value="<bean:write name="rs" property="jtcy4_zy" />" />
							<bean:write name="rs" property="jtcy4_zy" />
						</div>
					</td>
					<td>
						<div align="center">
							<input type="hidden" name="jtcy4_nsr"
								value="<bean:write name="rs" property="jtcy4_nsr" />" />
							<bean:write nam="rs" propertye="jtcy4_nsr" />
						</div>
					</td>
					<td>
						<div align="center">
							<input type="hidden" name="jtcy4_jkzk"
								value="<bean:write name="rs" property="jtcy4_jkzk" />" />
							<bean:write name="rs" property="jtcy4_jkzk" />
						</div>
					</td>
				</tr>
				<tr>
					<td width="6%">
						<div align="center">
							<input type="hidden" name="jtcy5_xm"
								value="<bean:write name="rs" property="jtcy5_xm" />" />
							<bean:write name="rs" property="jtcy5_xm" />
						</div>
					</td>
					<td width="6%">
						<div align="center">
							<input type="hidden" name="jtcy5_nl"
								value="<bean:write name="rs" property="jtcy5_nl" />" />
							<bean:write name="rs" property="jtcy5_nl" />
						</div>
					</td>
					<td>
						<div align="center">
							<input type="hidden" name="jtcy5_gx"
								value="<bean:write name="rs" property="jtcy5_gx" />" />
							<bean:write name="rs" property="jtcy5_gx" />
						</div>
					</td>
					<td colspan="2">
						<div align="center">
							<input type="hidden" name="jtcy5_gzdw"
								value="<bean:write name="rs" property="jtcy5_gzdw" />" />
							<bean:write name="rs" property="jtcy5_gzdw" />
						</div>
					</td>
					<td>
						<div align="center">
							<input type="hidden" name="jtcy5_zy"
								value="<bean:write name="rs" property="jtcy5_zy" />" />
							<bean:write name="rs" property="jtcy5_zy" />
						</div>
					</td>
					<td>
						<div align="center">
							<input type="hidden" name="jtcy5_nsr"
								value="<bean:write name="rs" property="jtcy5_nsr" />" />
							<bean:write name="rs" property="jtcy5_nsr" />
						</div>
					</td>
					<td>
						<div align="center">
							<input type="hidden" name="jtcy5_jkzk"
								value="<bean:write name="rs" property="jtcy5_jkzk" />" />
							<bean:write name="rs" property="jtcy5_jkzk" />
						</div>
					</td>
				</tr>
				<tr>
					<td colspan="3">
						<div align="right">
							��ͥ�˾������룺
						</div>
					</td>
					<td colspan="2" align="left">
						<input type="hidden" name="jtrjnsr"
							value="<bean:write name="rs" property="jtrjnsr" />" />
						<bean:write name="rs" property="jtrjnsr" />
					</td>
					<td>
						<div align="right">
							��ͥǷծ�����
						</div>
					</td>
					<td colspan="3" align="left">
						<input type="hidden" name="jtqzqk"
							value="<bean:write name="rs" property="jtqzqk" />" />
						<bean:write name="rs" property="jtqzqk" />
					</td>
				</tr>
				<tr>
					<td colspan="3">
						<div align="right">
							ѧ���ѻ����������
						</div>
					</td>
					<td colspan="6" align="left">
						<input type="hidden" name="xsyhhjqk"
							value="<bean:write name="rs" property="xsyhhjqk" />" />
						<bean:write name="rs" property="xsyhhjqk" />
					</td>
				</tr>
				<tr>
					<td colspan="3">
						<div align="right">
							��ͥ������Ȼ�ֺ������
						</div>
					</td>
					<td colspan="6" align="left">
						<input type="hidden" name="jtzszrzhqk"
							value="<bean:write name="rs" property="jtzszrzhqk" />" />
						<bean:write name="rs" property="jtzszrzhqk" />
					</td>
				</tr>
				<tr>
					<td colspan="3">
						<div align="right">
							��ͥ����ͻ�������¼���
						</div>
					</td>
					<td colspan="6" align="left">
						<input type="hidden" name="jtzstfywsj"
							value="<bean:write name="rs" property="jtzstfywsj" />" />
						<bean:write name="rs" property="jtzstfywsj" />
					</td>
				</tr>
				<tr>
					<td colspan="3">
						<div align="right">
							��ͥ��Ա��м����������Ͷ����������
						</div>
					</td>
					<td colspan="6" align="left">
						<input type="hidden" name="jtcyndlrqk"
							value="<bean:write name="rs" property="jtcyndlrqk" />" />
						<bean:write name="rs" property="jtcyndlrqk" />
					</td>
				</tr>
				<tr>
					<td colspan="3">
						<div align="right">
							��ͥ��Աʧҵ�����
						</div>
					</td>
					<td colspan="6" align="left">
						<input type="hidden" name="jtcysyqk"
							value="<bean:write name="rs" property="jtcysyqk" />" />
						<bean:write name="rs" property="jtcysyqk" />
					</td>
				</tr>
				<tr>
					<td colspan="3">
						<div align="right">
							���������
						</div>
					</td>
					<td colspan="6" align="left">
						<input type="hidden" name="qtqk"
							value="<bean:write name="rs" property="qtqk" />" />
						<bean:write name="rs" property="qtqk" />
					</td>
				</tr>
				<tr>
					<td colspan="3">
						<div align="right">
							���������������룺
						</div>
					</td>
					<td colspan="2" align="left">
						<input type="hidden" name="mzbmyzbm"
							value="<bean:write name="rs" property="mzbmyzbm" />" />
						<bean:write name="rs" property="mzbmyzbm" />
					</td>
					<td>
						<div align="right">
							����������ϵ�绰��
						</div>
					</td>
					<td colspan="3" align="left">
						<input type="hidden" name="mzbmlxdh"
							value="<bean:write name="rs" property="mzbmlxdh" />" />
						<bean:write name="rs" property="mzbmlxdh" />
					</td>
				</tr>
				<tr>
					<td colspan="3">
						<div align="right">
							����������ϸͨѶ��ַ��
						</div>
					</td>
					<td colspan="6" align="left">
						<input type="hidden" name="mzbmxxtxdz"
							value="<bean:write name="rs" property="mzbmxxtxdz" />" />
						<bean:write name="rs" property="mzbmxxtxdz" />
					</td>
				</tr>
				<tr>
					<td colspan="3">
						<div align="center">
							����ʱ��
						</div>
					</td>
					<td colspan="2">
						<input type="hidden" name="sqsj"
							value="<bean:write name="rs" property="sqsj" />" />
						<bean:write name="rs" property="sqsj" />
					</td>
					<td>
						<div align="center">
							��˽��
						</div>
					</td>
					<td colspan="3">
						<html:select name="rs" property="yesNo">
							<html:options collection="chkList" property="en"
								labelProperty="cn" />
						</html:select>
					</td>
				</tr>
				<tr>
					<td colspan="3">
						<div align="left">
							����Ա������
						</div>
					</td>
					<td colspan="6">
						<html:textarea name="rs" property="fdyshyj" rows='5' style="width:100%" onblur="yzdx(this,'fdyshyj')"/>
						<input type="hidden" id="fdyshyj" name="fdyshyj" value="">
					</td>
				</tr>
				<tr>
					<td colspan="3">
						<div align="left">
							<bean:message key="lable.xsgzyxpzxy" />������
						</div>
					</td>
					<td colspan="6">
						<html:textarea name="rs" property="xyshyj" rows='5' style="width:100%" onblur="yzdx(this,'xyshyj')"/>
						<input type="hidden" id="xyshyj" name="xyshyj" value="">
					</td>
				</tr>
				<tr>
					<td colspan="3">
						<div align="left">
							ѧУ������
						</div>
					</td>
					<td colspan="6">
						<html:textarea name="rs" property="xxshyj" rows='5' style="width:100%" onblur="yzdx(this,'xxshyj')"/>
						<input type="hidden" id="xxshyj" name="xxshyj" value="">
					</td>
				</tr>
			</table>
			<div class="buttontool" align="center">
				<button class="button2" onclick="yz();" style="width:80px"
					id="buttonSave">
					��&nbsp;&nbsp;��
				</button>
				&nbsp;&nbsp;&nbsp;&nbsp;
				<logic:equal name="isXX" value="is">
					<button class="button2" style="width:80px"
						onClick="toPrintOut(document.getElementById('titName').value);">
						��&nbsp;&nbsp;ӡ
					</button>
				&nbsp;&nbsp;&nbsp;&nbsp;
				</logic:equal>
				<button class="button2" onclick="Close();return false;" style="width:80px"
					id="buttonClose">
					��&nbsp;&nbsp;��
				</button>
			</div>
		</html:form>
	</body>
</html>
