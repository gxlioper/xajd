
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
		<style type="text/css">
<!--
.style1 {color: #FF0000}
-->
</style>
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
	<script type='text/javascript' src='dwr/util.js'></script>
	<script type="text/javascript" src="dwr/interface/nbtyxszz.js"></script>
	<script type="text/javascript" src="js/BatAlert.js"></script>
	<script type="text/javascript" src="js/Function.js"></script>
	<script type="text/javascript" src="js/calendar.js"></script>
	<script type="text/javascript" src="js/calendar-zh.js"></script>
	<script type="text/javascript" src="js/calendar-setup.js"></script>
	
	<script language="javascript">	
     function rychSqSave(){
     	showTips("�����У����Ե�...");
        refreshForm("/xgxt/gdbyTyxx.do?method=tyxxOne&doType=modi");
        if($("buttonSave")){
          $("buttonSave").disabled=true;
        }
     }
    function rychSqPrint(){
        window.open('nbtyJtjjkns.do?method=jtjjknsPrint&pkValue=${pkValue}');
        }	
</script>
	<%--		<input type="hidden" id="printpk" value="${printpk }"/>--%>
	<body>
	<link id="csssDate" rel="stylesheet" rev="stylesheet"
		href="js/calendar.css" type="text/css" media="all" />
		<script language="javascript" src="js/function.js"></script>
		<script type="text/javascript" src="/xgxt/dwr/engine.js"></script>
		<script type="text/javascript" src="/xgxt/dwr/util.js"></script>
		<script type="text/javascript"
			src="/xgxt/dwr/interface/getStuDetails.js"></script>
		   <script language="javascript" src="js/pjpy/pjpy_zjsyzy.js"></script>
		   <html:form action="/gdbyTyxx" method="post">
		   	<input type="hidden" id="disableEle" name="disableEle"
					value="xm-xb-xy-nj-zy-bj" />
			<input type="hidden" id="readonlyEle" name="readonlyEle" value="" />
			<input type="hidden" id="getStuInfo" name="getStuInfo"
					value="xh-xm-xb-xymc-nj-zymc-bjmc" />
			<input type="hidden" id="url" name="url" value="/gdbyTyxx.do?method=tyxxOne&doType=view" />
			<input type="hidden" id="isFdy" name="isFdy" value="${fdyQx }" />
			<input type="hidden" name="message" id="message" value="${message }"/>
			<input type="hidden" name="pkValue" id="pkValue" value="${pkValue}"/>
			<div class="title">
				<div class="title_img" id="title_m">
					��ǰλ�ã����Ž��� - ��Ա��Ϣ - ��Ա��Ϣ��ѯ
				</div>
			</div>

			<logic:present name="result">
			<script>
				alert(''+$('message').value);
				if (window.dialogArguments) {
					window.close();
					window.dialogArguments.document.getElementById('search_go').click();
				}
			</script>
			</logic:present>

			<table width="99%" id="rsT" class="tbstyle">
				<thead>
					<tr style="height:22px">
						<td colspan="4" align="center">
							<logic:equal name="doType" value="view">
								<b>��Ա��Ϣ��ѯ</b>
							</logic:equal>
							<logic:notEqual name="doType" value="view">
								<b>��Ա��Ϣ�޸�</b>
							</logic:notEqual>
						</td>
					</tr>
				</thead>
				<tr>
				<td align="right" style="width: 10%">
						<font color="red">*</font>ѧ�ţ�
					</td>
					<td align="left">
					<html:text property="xh" readonly="true"  styleId="xh" value="${xh}"/>
					<html:hidden property="save_xh" value="${xh}"/>
					<html:hidden property="primarykey_xh" value="${xh}"/>
					</td>
					<td align="right" style="width: 10%">
						������
					</td>
					<td align="left" style="width: 40%">
					<html:text name="rs" property="xm" readonly="true"  styleId="xh" value="${rs.xm}"/>
					</td>
				</tr>
				<tr>
					<td align="right" style="width: 10%">
						<font color="red">*</font>�Ա�
					</td>
					<td align="left">
					<html:text name="rs" property="xb" readonly="true"  styleId="xb" value="${rs.xb}"/>
					</td>
					<td align="right" style="width: 10%">
						�꼶��
					</td>
					<td align="left" style="width: 40%">
					<html:text name="rs" property="nj" readonly="true"  styleId="nj" value="${rs.nj}"/>
					</td>
				</tr>
				<tr>
					<td align="right" style="width: 10%">
						<font color="red">*</font><bean:message key="lable.xsgzyxpzxy" />��
					</td>
					<td align="left">
					<html:text name="rs" property="xymc" readonly="true"  styleId="xy" value="${rs.xymc}"/>
					</td>
					<td align="right" style="width: 10%">
						רҵ��
					</td>
					<td align="left" style="width: 40%">
					<html:text name="rs" property="zymc" readonly="true"  styleId="nj" value="${rs.zymc}"/>
					</td>
				</tr>
				<tr>
					<td align="right" style="width: 10%">
						<font color="red">*</font> �༶��
					</td>
					<td align="left">
					<html:text name="rs" property="bjmc" readonly="true"  styleId="xy" value="${rs.bjmc}"/>
					</td>
					<td align="right" style="width: 10%">
						�������ڣ�
					</td>
					<td align="left" style="width: 40%">
						 <html:text name="rs" property="rtrq" styleId="rtrq" 
					onclick="return showCalendar('rtrq','y-mm-dd');" 
					onblur="dateFormatChg(this)" readonly="true" />	
					</td>
				</tr>
				<tr style="height:22px">
					<td align="right">
						���ŵص㣺
					</td>
					<td align="left">
						<html:text name="rs" property="rtdd" />
					</td>
					<td>
						�Ƿ��ڼ���
					</td>
					<td>
						<html:text name="rs" property="sfzj"/>
					</td>
					
				</tr>
				
				<tr style="height:22px">
					<td align="right">
						��ʱ���κ�ְ��:
						<br />
						<span class="style1">(��100��)&nbsp;</span>
					</td>
					<td colspan="3" align="left">
						<html:textarea rows="8" styleId="hsrhzw" style="width:98%" name="rs" property="save_hsrhzw" onblur="chLeng(this,100)"/>
					</td>
				</tr>	
				<tr style="height:22px">
					<td align="right">
						�μ�����:
						<br />
						<span class="style1">(��100��)&nbsp;</span>
					</td>
					<td colspan="3" align="left">
						<html:textarea rows="8" styleId="cjst" style="width:98%" name="rs" property="save_cjst" onblur="chLeng(this,100)"/>
					</td>
				</tr>	
				<tr style="height:22px">
					<td align="right">
						�μ����ʵ����
						<br />
						<span class="style1">(��100��)&nbsp;</span>
					</td>
					<td colspan="3" align="left">
						<html:textarea rows="8" styleId="cjshsj" style="width:98%" name="rs" property="save_cjshsj" onblur="chLeng(this,100)"/>
					</td>
				</tr>
				<tr style="height:22px">
					<td align="right">
						־Ը�ߵ������¼��
						<br />
						<span class="style1">(��200��)&nbsp;</span>
					</td>
					<td colspan="3" align="left">
						<html:textarea rows="8" style="width:98%" styleId="zyzqk" name="rs" property="save_zyzqk" onblur="chLeng(this,200)"/>
					</td>
				</tr>
				<tr style="height:22px">
					<td align="right">
						�μ���ѵ�����
						<br />
						<span class="style1">(��200��)&nbsp;</span>
					</td>
					<td colspan="3" align="left">
						<html:textarea rows="8" style="width:98%" styleId="cjpxqk" name="rs" property="save_cjpxqk" onblur="chLeng(this,200)"/>
					</td>
				</tr>
				<logic:equal name="isFdy" value="true">
					<tr style="height:22px">
						<td align="right">
							�������
							<br />
							<span class="style1">(��200��)&nbsp;</span>
						</td>
						<td colspan="3" align="left">
							<html:textarea rows="8" style="width:98%" styleId="hjqk" name="rs" property="save_hjqk" onblur="chLeng(this,200)"/>
						</td>
					</tr>
				</logic:equal>
			</table>
			<div class="buttontool" id="btn"  width="100%">	
					<logic:notEqual name="doType" value="view">
						<button type="button" class="button2" id="buttonSave" onclick="rychSqSave();" style="width:80px">
							��  �� 
						</button>
					</logic:notEqual>
					&nbsp;&nbsp;&nbsp;&nbsp;
						<button type="button" class="button2" onclick="Close();return false;" style="width:80px" id="buttonClose">
							�� ��
						</button>
			</div>
			<logic:equal name="done" value="true">
				<script>
			          alert("����ɹ���");
			        </script>
			</logic:equal>
			<logic:equal name="done" value="false">
				<script>
			          alert("����ʧ�ܣ�");
			    </script>
			</logic:equal>
		</html:form>
	</body>


</html>

