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
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<base target="_self">
	<link id="csssDate" rel="stylesheet" rev="stylesheet"
		href="js/calendar.css" type="text/css" media="all" />
	<script language="javascript">
	</script>
	<script type="text/javascript">
		function setID(){
			var sj = document.getElementById("dtsj").value;
			var xh = document.getElementById("xh").value;
			if(""==xh || null == xh){
				alert('��ѡ��ѧ��');
				return ;
			}
			if(""==sj || null == sj){
				alert('���ʱ����д����');
				return ;
			}
			var yt_id = sj+xh;
			document.getElementById("yt_id").value=yt_id;
		}
</script>
	<%
	response.setHeader("Pragma","No-cache");
	response.setHeader("Cache-Control","no-cache");
	response.setDateHeader("Expires", 0);
	%>
	<body onload="checkWinType();dataManLoad();">
		<script language="javascript" src="js/function.js"></script>
		<script type="text/javascript" src="js/calendar.js"></script>
		<script type="text/javascript" src="js/calendar-zh.js"></script>
		<script type="text/javascript" src="js/calendar-setup.js"></script>
		<html:form action="/xljk_ytqk" method="post">
			<div class="title">
				<div class="title_img" id="title_m">
					<span id="tipFollow"></span>
				</div>
			</div>
			<logic:notEmpty name="rs">
				<logic:equal name="rs" property="stuExists" value="no">
					<script>
				    alert("�������ѧ����Ч!");
				    </script>
				</logic:equal>
				<input type="hidden" id="doType" name="doType"
					value="<bean:write name="doType" scope="request"/>" />
				<input type="hidden" id="pkValue" name="pkValue"
					value="<bean:write name="pkValue" scope="request"/>" />
				<input type="hidden" id="disableEle" name="disableEle"
					value="xm-xb-xzb-xymc-ssbh" />
				<input type="hidden" id="readonlyEle" name="readonlyEle" value="" />
			
				<input type="hidden" id="getStuInfo" name="getStuInfo"
					value="xm-xb-xymc-nj-zymc-bjmc-xzb-ssbh-sjhm-lxdzxx" />
				<input type="hidden" id="url" name="url"
					value="/sjcz/xlytqkb.jsp" />
				<fieldset>
					<legend>
						�����ղ���Ϣά��
					</legend>
					<table width="100%" class="tbstyle">
						<thead>
							<tr>
								<td colspan="4" align="center">
									�����ղ���Ϣά��
								</td>
							</tr>
						</thead>
						<tr>
							<td align="right">
								<font color="red">*</font>��ţ�
							</td>
							<td colspan="3">
								<html:text property="yt_id" name="rs" styleId="yt_id" readonly="true"/>
							</td>
						</tr>
						<tr>
							<td align="right">
								<font color="red">*</font>ѧ�ţ�
							</td>
							<td align="left">
								<html:text name='rs' property="xh" readonly="readonly"
									styleId="xh" onkeypress="autoFillStuInfo(event.keyCode,this)" />
								<button type="button" onclick="showTopWin('/xgxt/stu_info.do',750,550);"
									class="btn_01" id="buttonFindStu" style="display:none">
									ѡ��
								</button>
							</td>
							<td align="right">
								������
							</td>
							<td align="left">
								<html:text name='rs' property="xm" styleId="xm" />
							</td>
						</tr>
						<tr>
							<td align="right">
								�Ա�
							</td>
							<td align="left">
								<html:text name='rs' property="xb" styleId="xb" />
							</td>
							<td align="right">
								�༶��
							</td>
							<td align="left">
								<html:text name='rs' property="bjmc" styleId="xzb" />
							</td>
						</tr>
						<tr>
							<td align="right">
								<bean:message key="lable.xsgzyxpzxy" />��
							</td>
							<td align="left">
								<html:text name='rs' property="xymc" styleId="xymc" />
							</td>
							<td align="right"> 
								���᣺ 
							</td>
							<td align="left">
								<html:text name='rs' property="ssbh" styleId="ssbh" />
							</td>
						</tr>
						<tr>
							<td align="right"> 
								�绰�� 
							</td>
							<td align="left">
								<html:text name='rs' property="sjhm" styleId="sjhm" />
							</td>
							<td align="right"> 
								���䣺 
							</td>
							<td align="left">
								<html:text name='rs' property="lxdzxx" styleId="lxdzxx" />
							</td>
						</tr>
						<tr>
							<td align="right"> 
								��ѷ֣� 
							</td>
							<td align="left">
								<html:text name='rs' property="chf" styleId="chf" />
							</td>
							<td align="right"> 
								ʱ�䣺 
							</td>
							<td align="left">
								<html:text name='rs' property="dtsj" styleId="dtsj" readonly="true" />
							</td>
						</tr>
						<tr align="left">
							<td align="right">��������� 
							</td>
							<td colspan="3">
								<html:textarea name='rs' property='cbqk' style="width:99%"
									rows='5' />
							</td>
						</tr>
						<tr align="left">
							<td align="right">Լ̸���: 
							</td>
							<td colspan="3">
								<html:textarea name='rs' property='ytqk' style="width:99%"
									rows='5' />
							</td>
						</tr>
						<tr>
							<td align="right">����ʶ��  
							</td>
							<td align="left">
								<html:text name='rs' property="cbsb" styleId="cbsb" />
							</td>
							<td align="right">��ע����  
							</td>
							<td align="left">
								<html:text name='rs' property="gzjb" styleId="gzjb" />
							</td>
						</tr>
						<tr align="left">
							<td align="right">Լ̸����: 
							</td>
							<td colspan="3">
								<html:textarea name='rs' property='ytjy' style="width:99%"
									rows='5' />
							</td>
						</tr>
					</table>
					<div class="buttontool" align="center">
						<button type="button" class="button2" onclick="dataCanModi(true)"
							style="width:80px" id="buttonModi">
							�� ��
						</button>
						<button type="button" class="button2" onclick="dataDoSave('yt_id')"
							style="width:80px" id="buttonSave">
							�� ��
						</button>
						&nbsp;&nbsp;&nbsp;&nbsp;
						<button type="button" class="button2" onclick="Close();return false;" style="width:80px"
							id="buttonClose">
							�� ��
						</button>
					</div>
				</fieldset>
			</logic:notEmpty>
			<jsp:include flush="true" page="/sjcz/include/modiPageJudge.jsp"></jsp:include>
		</html:form>
		<script type="text/javascript">
				var yt_id = document.getElementById("yt_id").value;
				if(null == yt_id || ""==yt_id){
					var dtsj = document.getElementById("dtsj");
					dtsj.attachEvent('onblur',function(){
					    setID();
					});
					dtsj.attachEvent('onclick',function(){
					    return showCalendar('dtsj','y-mm-dd');
					});
				}
		</script>						
	</body>
</html>
