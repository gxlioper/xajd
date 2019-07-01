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
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<base target="_self">
	<link id="csssDate" rel="stylesheet" rev="stylesheet"
		href="js/calendar.css" type="text/css" media="all" />
	<script language="javascript" src="js/function.js"></script>
	<script type='text/javascript' src='/xgxt/dwr/interface/getStuDetails.js'></script>
	<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
	<script type='text/javascript' src='dwr/util.js'></script>
	<script type="text/javascript" src="js/calendar.js"></script>
	<script type="text/javascript" src="js/calendar-zh.js"></script>
	<script type="text/javascript" src="js/calendar-setup.js"></script>
	<%
	response.setHeader("Pragma","No-cache");
	response.setHeader("Cache-Control","no-cache");
	response.setDateHeader("Expires", 0);
	%>
	<body onload="">
		<html:form action="/zjgszy_gygl" method="post">
		<div class="title">
				<div class="title_img" id="title_m">
					��ǰ����λ�ã� ��Ԣ���� - ��Ϣά�� - ��Ԣ����֧
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
				<input type="hidden" id="pkValue" name="pkValue"
					value="<bean:write name="pkValue" scope="request"/>" />							
			<table width="100%" class="tbstyle">
					<thead>
						<tr>
							<td colspan="4" align="center">
								��Ԣ����֧��Ϣ
							</td>
						</tr>
					</thead>
					<tr>
						<td align="right" width="15%">
							<font color="red">*</font>ѧ�ţ�
						</td>
						<td align="left" width="25%">
							<html:text name='rs' property="xh" styleId="xh" readonly="true" />
						</td>
						<td align="right" width="15%">
							<font color="red">*</font>¥����
						</td>
						<td align="left">
							<html:select name='rs' property="lddm" style="width:150px">
								<html:option value=""></html:option>
								<html:options collection="ldList" property="lddm"
									labelProperty="ldmc" />
							</html:select>
						</td>
					</tr>
					<tr>
						<td align="right">
							������
						</td>
						<td align="left">
							<bean:write name='rs' property="xm" />
						</td>
						<td align="right">
							���ţ�
						</td>
						<td align="left">
							<html:text name='rs' property="bm" styleId="bm" maxlength="25"/>
						</td>
					</tr>
					<tr>
						<td align="right">
							�Ա�
						</td>
						<td align="left">
							<bean:write name='rs' property="xb" />
						</td>
						<td align="right">
							<font color="red">*</font>ְ��
						</td>
						<td align="left">
							<html:text name='rs' property="zw" styleId="zw" maxlength="10" />
						</td>
					</tr>
					<tr>
						<td align="right">
							�꼶��
						</td>
						<td align="left">
							<bean:write name='rs' property="nj" />
						</td>
						<td align="right">
							<font color="red">*</font>��ְ���ڣ�
						</td>
						<td align="left">
							<html:text name='rs' property="rzrq" styleId="rzrq" 
								style="cursor:hand;"  onblur="dateFormatChg(this)"
								onclick="return showCalendar('rzrq','y-mm-dd');" readonly="true" />
						</td>
					</tr>
					<tr>
						<td align="right">
							<bean:message key="lable.xsgzyxpzxy" />���ƣ�
						</td>
						<td align="left">
							<bean:write name='rs' property="xymc" />
						</td>
						<td align="right">
							��ְ���ڣ�
						</td>
						<td align="left">
							<html:text name='rs' property="lzrq" styleId="lzrq" onblur="dateFormatChg(this)"
								style="cursor:hand;"
								onclick="if($('rzrq').value.replace(' ','')==''){return false;}else{return showCalendar('lzrq','y-mm-dd');}"
								readonly="true" />
						</td>
					</tr>
					<tr>
						<td align="right">
							רҵ���ƣ�
						</td>
						<td align="left">
							<bean:write name='rs' property="zymc" />
						</td>
						<td align="right">
							��ϵ�绰��
						</td>
						<td align="left">
							<html:text name='rs' property="lxdh" styleId="lxdh" maxlength="25"/>
						</td>
					</tr>
					<tr>
						<td align="right">
							�༶���ƣ�
						</td>

						<td align="left">
							<bean:write name='rs' property="bjmc" />
						</td>
						<td align="right">

						</td>
						<td align="left">
						</td>
					</tr>
					<tr>
						<td align="right">
							��ע��<br>
							(��200��)
						</td>
						<td align="left" colspan="3">
							<html:textarea name='rs' property='bz' styleId="bz"
								style="width:500px" rows='5' />
						</td>
					</tr>
				</table>
				<div class="buttontool" align="center">
					<button class="button2" onclick="tzzModi('xh-lddm-zw-rzrq')"
						style="width:80px" id="buttonSave">
						�� ��
					</button>
					&nbsp;&nbsp;&nbsp;&nbsp;
					<button class="button2" onclick="Close();return false;" style="width:80px"
						id="buttonClose">
						�� ��
					</button>
				</div>
				</logic:notEmpty>
		</html:form>
		<logic:equal value="true" name="done">
			<script type="text/javascript">
	          alert("�����ɹ���");
	          Close();
			  window.dialogArguments.document.getElementById('search_go').click();
	    </script>
		</logic:equal>
		<logic:equal value="false" name="done">
			<script type="text/javascript">
	          alert("����ʧ��,������Ѵ������\"*\"����Ŀ��ͬ�ļ�¼��������������ݺ����ύ��");	            
	    </script>
		</logic:equal>
	</body>
	<script type="text/javascript">
     function tzzModi(mustFill){
        var lzrq = document.getElementById('lzrq').value.replace(" ","");
	    var rzrq = document.getElementById('rzrq').value.replace(" ","");
	    if(rzrq!=""&&lzrq!=""
	           &&rzrq>=lzrq){
		   alert('��ְ����Ӧ������ְ���ڣ�');
		   return false;
	    } 
	    if($("bz").value.length>200){
	      alert("��ע�������ܴ���200�֣�");
	      return false;
	    }
	    var eles = mustFill.split("-");
	    for (i = 0; i < eles.length; i++) {
		    if (document.getElementById(eles[i]).value == "") {
			alert("�뽫��\"*\"�ŵ���Ŀ����������");
			return false;
		    }		
	    }
	    refreshForm("/xgxt/zjgszy_gygl.do?method=gyTzzModi&doType=save");
	    $("buttonSave").disabled=true;
     }
  </script>
</html>
