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
		<script type="text/javascript">
		function rychSqPrint(){
        window.open('nbtyJtjjkns.do?method=jtjjknsPrint&pkValue=${pkValue}');
        }	
		</script>
		<title><bean:message key="lable.title" /></title>
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
	<script type="text/javascript" src="js/BatAlert.js"></script>
	<script type="text/javascript" src="js/check.js"></script>
	<script type="text/javascript" src="js/calendar.js"></script>
	<script type="text/javascript" src="js/calendar-zh.js"></script>
	<script type="text/javascript" src="js/calendar-setup.js"></script>
	<script language="javascript">
 function dosubmit(){ 
 		var write="";
        var qjsj = "";
        var qjqssj="";
        var qjjssj="";
        if($("write")){
        	write=$("write").value;
        }
        if(write=="no"){
        	alert("�����ͨ���������޸ģ�");
        	return false;
        }
        if($("qjsj")){
          qjsj=$("qjsj").value;
        }
        if($("qjsj")){
        	qjsj=$("qjsj").value;
        }
        if(qjsj==""){
       		alert("�������������д��");
       		return false;
        }
        if(chkNumInput($("qjsj"))){
        	alert("�����ʱ���ʽ����");
        	return false;
        }
        if($("qjqssj") && $("qjjssj")){
        	qjqssj=$("qjqssj").value;
        	qjjssj=$("qjjssj").value;
        }
        if(qjqssj!="" && qjjssj!=""){
        	if(qjqssj>qjjssj){
        		alert("��ٿ�ʼʱ�����ڽ���ʱ�䣡");
        		return false;
        	}
        }else{
        	alert("���ʱ��û����д��ȫ��");
        	return false;
        }
        qjsj=eval(qjsj);
        qjqssj=eval(qjqssj);
        qjjssj=eval(qjjssj);
        if(qjjssj-qjqssj+1<qjsj || qjjssj-qjqssj>qjsj){
        	alert("���ʱ�����벻��ȷ��");
        	return false;
        }
		showTips("�����У���ȴ�...");
		refreshForm("/xgxt/rcswKqglXskq.do?method=rcswKqglXskqOne&doType=modi");
	}
	</script>
	<%
	response.setHeader("Pragma","No-cache");
	response.setHeader("Cache-Control","no-cache");
	response.setDateHeader("Expires", 0);
	%>
	<body>		
		  <html:form action="/rcswKqglXskq.do?method=rcswKqglXskqOne&doType=modi" method="post">
		    <input type="hidden" id="readonlyEle" name="readonlyEle" value="" />
			<input type="hidden" id="url" name="url" value="/rcswKqglXskq.do?method=rcswKqglXskqOne=view" />
			<input type="hidden" id="isFdy" name="isFdy" value="${fdyQx}" />
			<input type="hidden" id="save_xh" name="save_xh" value="${rs.xh}" />
			<input type="hidden" id="save_xn" name="save_xn" value="${rs.xn}" />
			<input type="hidden" id="save_xq" name="save_xq" value="${rs.xq}" />
			<input type="hidden" id="pkValue" name="pkValue" value="${pkValue}" />
			<input type="hidden" id="save_sqsj" name="save_sqsj" value="${rs.sqsj}" />
			<input type="hidden" id="write" name="write" value="${write}" styleId="write"/>
			<fieldset>
			<div class="title">
			<div class="title_img" id="title_m">
					��ǰλ�ã��ճ����� - ���ڹ��� - ѧ����ٽ����ѯ
			</div>
			</div>
			<logic:present name="result">
			</logic:present>

			<table width="99%" id="rsT" class="tbstyle">
				<thead>
					<tr style="height:22px">
						<td colspan="4" align="center">
							<b>ѧ���������</b>
						</td>
					</tr>
				</thead>
				<tr width=100%>
				<td align="right"  style="width: 10%">
						<font color="red">*</font>ѧ�ţ�
					</td>
					<td align="left" style="width: 20%">
						<bean:write name="rs" property="xh"/>
						<html:hidden property="save_xh" value="${rs.xh}"/>
					</td>
					<td align="right" style="width: 10%">
						������
					</td>
					<td align="left"  style="width: 30%">
					<logic:notEmpty name="rs">
						<bean:write name='rs' property="xm" />
						</logic:notEmpty>
					</td>
				</tr>
				<tr style="height:22px">
					<td align="right">
						�Ա�
					</td>
					<td align="left">
					<logic:notEmpty name="rs">
						<bean:write name='rs' property="xb" />
					</logic:notEmpty>
					<td align="right">
						�꼶��
					</td>
					<td align="left">
					<logic:notEmpty name="rs">
						<bean:write name='rs' property="nj" />
					</logic:notEmpty>	
					</td>
				</tr>
				<tr style="height:22px">
					<td align="right">
						<bean:message key="lable.xsgzyxpzxy" />��
					</td>
					<td align="left">
					<logic:notEmpty name="rs">
						<bean:write name='rs' property="xymc" />
						<html:hidden property="save_xydm" value="${rs.xydm}"/>
					</logic:notEmpty>
					</td>
					<td align="right">
						רҵ��
					</td>
					<td align="left">
					<logic:notEmpty name="rs">
						<bean:write name='rs' property="zymc" />
						<html:hidden property="save_zydm" value="${rs.zydm}"/>
					</logic:notEmpty>
					</td>	
				</tr>
				<tr style="height:22px">
					<td align="right">
						�༶��
					</td>
					<td align="left">
					<logic:notEmpty name="rs">
						<bean:write name='rs' property="bjmc" />
						<html:hidden property="save_bjdm" value="${rs.bjdm}"/>
					</logic:notEmpty>
					</td>
					<td align="right">
						<font color="red">*</font>������ͣ�
					</td>
					<td align="left">
						<bean:write name="rs" property="qjlxmc"/>
					</td>
				</tr>
				<tr style="height:22px">
					<td align="right">
						<font color="red"></font>��ȣ�
					</td>
					<td align="left">
						<bean:write name="rs" property="nd" />
					</td>	
					<td align="right">
						<font color="red">*</font>���ʱ�䣺
					</td>
					<td align="left">
						<html:text name="rs" property="save_qjqssj" styleId="qjqssj" style="width:80px"
							onclick="return showCalendar('qjqssj','y-mm-dd');" 
							onblur="dateFormatChg(this)" readonly="true" />��
						 <html:text name="rs" property="save_qjjssj" styleId="qjjssj" style="width:80px"
							onclick="return showCalendar('qjjssj','y-mm-dd');" 
							onblur="dateFormatChg(this)" readonly="true" />
					</td>
				</tr>
				<tr style="height:22px">
					<td align="right" style="width: 10%">
						ѧ�꣺
					</td>
					<td align="left" style="width: 40%">
						<bean:write name="rs" property="xn" />	
						<html:hidden property="save_xn"  value="${rs.xn}"/>
					</td>
					<td align="right">
						<font color="red">*</font>���������
					</td>
					<td align="left">
						<html:text name="rs" property="save_qjsj" styleId="qjsj" style="width:80px"	/>��
					</td>
				</tr>
				<tr style="height:22px">
					<td align="right">
						ѧ�ڣ�
					</td>
					<td align="left">
						<bean:write name="rs" property="xqmc"/>
						<html:hidden property="save_xq" value="${rs.xq}"/>
					</td>
					<td align="right">
							��Ԣ����Ա��
					</td>
					<td align="left">
					  	<logic:notEmpty name="gygly">
							<bean:write name="gygly"/>
						</logic:notEmpty>
					</td>
					</tr>
				<tr style="height:22px">
					<td align="right">
						�������ɣ�
						<br />
						<span class="style1">(��200��)&nbsp;</span>
					</td>
					<td colspan="3" align="left">
						<html:textarea rows="8" styleId="qjly" name="rs" style="width:98%" property="save_qjly" onblur="chLeng(this,200)"/>
					</td>
				</tr>
				<tr style="height:22px">
					<td align="right">
						��ע��
						<br />
						<span class="style1">(��200��)&nbsp;</span>
					</td>
					<td colspan="3" align="left">
						<html:textarea rows="8" style="width:98%" name="rs" styleId="bz" property="save_bz" onblur="chLeng(this,200)"/>
					</td>
				</tr>
			</table>
					<div class="buttontool" align="center">
						<logic:equal name="doType" value="save">
							<button type="button" class="button2"	onclick="dosubmit()" id="btn" style="width:80px" >
								�� ��
							</button>
						</logic:equal>
						&nbsp;&nbsp;&nbsp;&nbsp;
						<button type="button" class="button2" onclick="Close();return false;" style="width:80px" id="buttonClose">
							�� ��
						</button>
					</div>
				</fieldset>
		</html:form>
		<logic:present name="result">
		<input type="hidden" id="message" value="${message}"/>
		<script>
				alert(''+$('message').value);
				if (window.dialogArguments) {
					window.close();
					window.dialogArguments.document.getElementById('search_go').click();
				}
			</script>
		</logic:present>
	</body>
</html>