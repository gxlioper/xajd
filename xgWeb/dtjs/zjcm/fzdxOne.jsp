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
	function saveFzdx(url){
		var kssj = document.getElementById("kssj").value;
		var jssj = document.getElementById("jssj").value;
		if(document.getElementById("xh").value == "" && document.getElementById("xm").value == ""){
			alert("����ȷ����������ݣ�");
			return false;
		}
		if(document.getElementById("jssj").value == ""){
			alert("��ȷ�Ϸ�չ�������ʱ�䣡");
			return false;
		}
		if(document.getElementById("kssj").value == ""){
			alert("����Ŀǰ�������뵳�������ӣ��޷�����Ϊ��չ����");
			return false;
		} 
		if(kssj>jssj){
			alert("����ʱ�䲻��С�ڿ�ʼʱ�䣬���������ã�");
			return false;
		}else {
			showTips('���������У���ȴ�......');
			refreshForm(url);
		}
	}
	</script>
	<%
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
	%>
	<body onload="checkWinType();dataManLoad();">
		<script language="javascript" src="js/function.js"></script>
		<script language="javascript" src="js/sxjyFunction.js"></script>
		<script type="text/javascript" src="js/calendar.js"></script>
		<script type="text/javascript" src="js/calendar-zh.js"></script>
		<script type="text/javascript" src="js/calendar-setup.js"></script>
		<html:form action="/data_search" method="post">
			<div class="title">
				<div class="title_img" id="title_m">
					<span id="tipFollow"></span>
				</div>
			</div>
			<logic:empty name="rs">
				<br />
				<br />
				<p align="center">
					�д�������
				</p>
			</logic:empty>
			<input type="hidden" id="url" name="url"
				value="/dtjs/zjcm/fzdxOne.jsp" />
			<input type="hidden" id="getStuInfo" name="getStuInfo"
				value="xm-xb-xymc-nj-zymc-bjmc" />
			<input type="hidden" id="doType" name="doType"
				value="<bean:write name="doType" scope="request"/>" />
			<logic:present name="db">
				<input type="hidden" id="disableEle" name="disableEle"
					value="xm-xb-xy-nj-zy-bj-nd-xn-xq-kssj-jssj-cc" />
			</logic:present>
			<logic:notPresent name="db">
				<input type="hidden" id="disableEle" name="disableEle"
					value="xm-xb-xy-nj-zy-bj-nd-xn-xq-cc" />
			</logic:notPresent>
			<input type="hidden" id="readonlyEle" name="readonlyEle" value="" />
			<logic:notEmpty name="rs">
			<input type="hidden" id="ndV" name="ndV" value="<bean:write name="rs" property="nd" />" />
			<input type="hidden" id="xmV" name="xmV" value="<bean:write name="rs" property="xm" />" />
			<input type="hidden" id="xqV" name="xqV" value="<bean:write name="rs" property="xq" />" />
			<input type="hidden" id="bzV" name="bzV" value="<bean:write name="rs" property="bz" />" />
			<input type="hidden" id="xnV" name="xnV" value="<bean:write name="rs" property="xn" />" />
			<input type="hidden" id="kssjV" name="kssjV" value="<bean:write name="rs" property="kssj" />" />
				<fieldset>
					<legend>
						��չ����
					</legend>
					<table width="100%" class="tbstyle">
						<tr>
							<td align="right">
								ѧ�ţ�
							</td>
							<td align="left">
								<html:text name='rs' property="xh" readonly="readonly"
									styleId="xh" />
								<button type="button" onclick="showTopWin('/xgxt/stu_info.do',750,550);"
									class="btn_01" id="buttonFindStu" style="display:none">
									ѡ��
								</button>
							</td>
							<td align="right">
								��ȣ�
							</td>
							<td align="left">
								<logic:equal value="update" name="type">
									<html:select name="rs" property="nd" style="width:90px"
										styleId="nd">
										<html:options collection="xnList" property="nd"
											labelProperty="nd" />
									</html:select>
								</logic:equal>
								<logic:notEqual value="update" name="type">
									<html:text name='rsZjcm' property="nd" styleId="nd"
										style="width:90px" />
								</logic:notEqual>
							</td>
						</tr>
						<tr>
							<td align="right">
								������
							</td>
							<td align="left">
								<html:text name='rs' property="xm" styleId="xm" />
							</td>
							<td align="right">
								ѧ�꣺
							</td>
							<td align="left">
								<logic:equal value="update" name="type">
									<html:select name="rs" property="xn" style="width:90px"
										styleId="xn">
										<html:options collection="xnList" property="xn"
											labelProperty="xn" />
									</html:select>
								</logic:equal>
								<logic:notEqual value="update" name="type">
									<html:text name='rsZjcm' property="xn" styleId="xn"
										style="width:90px" />
								</logic:notEqual>
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
								ѧ�ڣ�
							</td>
							<td align="left">
								<logic:equal value="update" name="type">
									<html:select name="rs" property="xq" style="width:90px"
										styleId="xq">
										<html:option value=""></html:option>
										<html:options collection="xqList" property="xqdm"
											labelProperty="xqmc" />
									</html:select>
								</logic:equal>
								<logic:notEqual value="update" name="type">
									<html:text name='rsZjcm' property="xq" styleId="xq"
										style="width:90px" />
								</logic:notEqual>
							</td>
						</tr>
						<tr>
							<td align="right">
								�꼶��
							</td>
							<td align="left">
								<html:text name='rs' property="nj" styleId="nj" />
							</td>
							<td align="right">
								��չ����ʼʱ�䣺
							</td>
							<td align="left">
								<logic:equal value="update" name="type">
									<html:text name='rs' property="kssj" styleId="kssj"
										onblur="dateFormatChg(this)" style="cursor:hand;"
										onclick="return showCalendar('kssj','y-mm-dd');" />
								</logic:equal>
								<logic:notEqual value="update" name="type">
									<html:text name='rsZjcm' property="kssj" styleId="kssj"
										onblur="dateFormatChg(this)" style="cursor:hand;"
										onclick="return showCalendar('kssj','y-mm-dd');"  />
								</logic:notEqual>
							</td>
						</tr>
						<tr>
							<td align="right">
								<bean:message key="lable.xsgzyxpzxy" />��
							</td>
							<td align="left">
								<html:text name='rs' property="xymc" styleId="xy" />
							</td>
							<td align="right">
								��չ�������ʱ�䣺
							</td>
							<td align="left">
								<html:text name='rs' property="jssj" styleId="js"
									onblur="dateFormatChg(this)" style="cursor:hand;"
									onclick="return showCalendar('jssj','y-mm-dd');" />
							</td>
						</tr>
						<tr>
							<td align="right">
								רҵ��
							</td>
							<td align="left">
								<html:text name='rs' property="zymc" styleId="zy"
									style="width:200px" />
							</td>
							<td align="right">
							</td>
							<td align="left">
							</td>
						</tr>
						<tr>
							<td align="right">
								�༶��
							</td>
							<td align="left">
								<html:text name='rs' property="bjmc" styleId="bj"
									style="width:200px" />
							</td>
							<td align="right">
							</td>
							<td align="left">
							</td>
						</tr>
						<tr>
							<td align="right">
								ѧ����Σ�
							</td>
							<td align="left">
								<logic:equal value="update" name="type">
									<html:select name='rs' property="xsccdm" style="width:90px"
										styleId="cc">
										<html:option value=""></html:option>
										<html:options collection="xsccList" property="xsccdm"
											labelProperty="xsccmc" />
									</html:select>
								</logic:equal>
								<logic:notEqual value="update" name="type">
									<html:text name='rsZjcm' property="xsccmc" styleId="cc"
										style="width:90px" />
								</logic:notEqual>
							</td>
							<td align="right">
							</td>
							<td align="left">
							</td>
						</tr>
						<tr align="left">
							<td align="right">
								��ע��
							</td>
							<td colspan="3">
								<html:textarea name='rs' styleId="bz" property='bz'
									style="width:99%" rows='5' />
							</td>
						</tr>
					</table>
				</fieldset>
				<div class="buttontool">
					<button type="button" class="button2" style="width:80px" id="buttonModi">
						�� ��
					</button>
					<logic:notPresent name="db">
						<button type="button" class="button2" style="width:80px" id="buttonSave"
							onclick="saveFzdx('dtjs_zjcm.do?method=saveFzdx')">
							�� ��
						</button>
					</logic:notPresent>
					<logic:present name="db">
						<input type="hidden" id="buttonSave" name="buttonSave"/>
					</logic:present>
					&nbsp;&nbsp;
					<button type="button" class="button2" onclick="Close();return false;" style="width:80px"
						id="buttonClose">
						�� ��
					</button>
				</div>
				<logic:present name="inserted">
					<logic:equal name="inserted" value="ok">
						<script>
    					alert("��Ϣ��ӳɹ���");
    					dialogArgumentsQueryChick();
						Close();
   					 </script>
					</logic:equal>
					<logic:equal name="inserted" value="no">
						<script>
    					alert("��Ϣ���ʧ�ܣ�");
   					 </script>
					</logic:equal>
				</logic:present>
			</logic:notEmpty>
		</html:form>
	</body>
</html>
