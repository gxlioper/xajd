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
	<script language="javascript" src="js/function.js"></script>	
	<script type="text/javascript" src="js/calendar.js"></script>
	<script type="text/javascript" src="js/calendar-zh.js"></script>
	<script type="text/javascript" src="js/calendar-setup.js"></script>
	<script type="text/javascript" src="/xgxt/dwr/interface/getInsureInfo.js"></script>
	<script type="text/javascript" src="/xgxt/dwr/engine.js"></script>
	<script type="text/javascript" src="/xgxt/dwr/util.js"></script>
	<script language="javascript" src="js/qtsjFunction.js"></script>
	<script language="javascript">
	
	</script>
	<%
	response.setHeader("Pragma","No-cache");
	response.setHeader("Cache-Control","no-cache");
	response.setDateHeader("Expires", 0);
	%>
	<body onload="checkWinType();">		
		<html:form action="/bxgl.do" method="post">
			<div class="title">
				<div class="title_img" id="title_m">
					��ǰ����λ�ã��������� - ������Ϣ - Ͷ����Ϣ����
				</div>
			</div>
				<logic:equal name="rs" property="stuExists" value="no">
					<script>
    					alert("�������ѧ����Ч!");
    				</script>
				</logic:equal>
				<input type="hidden" id="disableEle" name="disableEle"
					value="xh-xm-xb-xy-nj-zy-bj-xz-sfzh-sjhm" />
				<input type="hidden" id="readonlyEle" name="readonlyEle" value="" />
				<input type="hidden" id="getStuInfo" name="getStuInfo"
					value="xh-xm-xb-xymc-nj-zymc-bjmc-sfzh-sjhm-xz" />
				<input type="hidden" id="url" name="url" value="/bxgl.do?method=tbxxAdd" />
				<fieldset>
					<legend>
						ѧ��������Ϣά��
					</legend>
					<table width="100%" class="tbstyle">
						<thead>
							<tr>
								<td colspan="4" align="center">
									ѧ��������Ϣά��
								</td>
							</tr>
						</thead>
						<tr>
							<td align="right">
								<font color="red">*</font>ѧ�ţ�
							</td>
							<td align="left">
								<html:text name='rs' 
								           property="save_xh" 
								           readonly="readonly"
									       styleId="xh" 
									       onkeypress="autoFillStuInfo(event.keyCode,this);" />
								<button onclick="showTopWin('/xgxt/stu_info.do',750,550);"
									class="button2" 
									id="buttonFindStu">
									ѡ��
								</button>
							</td>
							<td align="right">
								����ƾ֤�ţ�
							</td>
							<td align="left">
								<html:text name='rs' property="save_bxpzh" styleId="bxpzh" maxlength="10"/>
								<!--Ĭ�����ͨ��-->
								<input type="hidden" name="save_xxsh" value="ͨ��" />
								<!--endĬ�����ͨ��-->
							</td>
						</tr>
						<tr>
							<td align="right">
								������
							</td>
							<td align="left">
								<html:text name='rs' property="xm" styleId="xm" disabled="true"/>
							</td>
							<td align="right">
								<font color="red">*</font>��ȣ�
							</td>
							<td align="left">
								<html:select name="rs" property="save_nd"
									styleId="nd" onchange="getInsureInfoExist();">
									<html:options collection="xnList" property="nd"
										labelProperty="nd" />
								</html:select>
							</td>
						</tr>
						<tr>
							<td align="right">
								�Ա�
							</td>
							<td align="left">
								<html:text name='rs' property="xb" styleId="xb" disabled="true" />
							</td>
							<td align="right">
								<font color="red">*</font>���չ�˾��
							</td>
							<td align="left">
								<html:select name="rs" 
								             property="save_tbgsdm"
									         styleId="tbgsdm" 
									         onchange="loadBxxzList()">
									<html:option value=""></html:option>
									<html:options collection="bxgsdmList" property="bxgsdm"
										labelProperty="bxgsmc" />
								</html:select>
							</td>
						</tr>
						<tr>
							<td align="right">
								�꼶��
							</td>
							<td align="left">
								<html:text name='rs' property="nj" styleId="nj" disabled="true" />
							</td>
							<td align="right">
								<font color="red">*</font>Ͷ�����֣�
							</td>
							<td align="left">
								<html:select name="rs" 
								             property="save_tbxzdm" 
								             styleId="tbxzdm" 
								             onchange="loadBxnxAndBf()">
									<html:option value=""></html:option>
									<logic:notEmpty name="tbxzdmList">
										<html:options collection="tbxzdmList" property="bxxzdm"
											labelProperty="bxxzmc" />
									</logic:notEmpty>
								</html:select>
							</td>
						</tr>
						<tr>
							<td align="right">
								<bean:message key="lable.xsgzyxpzxy" />��
							</td>
							<td align="left">
								<html:text name='rs' property="xymc" styleId="xy" disabled="true" />
							</td>
							<td align="right">
								<font color="red">*</font>Ͷ�����ڣ�
							</td>
							<td align="left">
								<html:text name='rs' 
								           property="save_tbsj" 
								           styleId="tbsj"
									       onblur="dateFormatChg(this)" 
									       onclick="return showCalendar('tbsj','y-mm-dd');"
									       style="cursor:hand;"/>
							</td>
						</tr>
						<tr>
							<td align="right">
								רҵ��
							</td>
							<td align="left">
								<html:text name='rs' property="zymc" styleId="zy" disabled="true" />
							</td>
							<td align="right">
								<font color="red">*</font>�������ޣ�
							</td>
							<td align="left">
								<html:text name="rs" property="save_bxnx" styleId="bxnx" maxlength="10" onkeyup="value=value.replace(/[^\d|.]/g,'') "/>(��)
							</td>	
						</tr>
						<tr>
							<td align="right">
								�༶��
							</td>
							<td align="left">
								<html:text name='rs' property="bjmc" styleId="bj" disabled="true" />
							</td>
							<td align="right">
								���ѣ�
							</td>
							<td align="left">
								<html:text name='rs' property="save_bf" styleId="bf" style="width:90px" onkeyup="value=value.replace(/[^\d|.]/g,'') " maxlength="6"/>(Ԫ)
							</td>
						</tr>
						<tr>
							<td align="right">
								ѧ�ƣ�
							</td>
							<td align="left">
								<html:text name='rs' property="xz" styleId="xz" disabled="true" />
							</td>
						    <td align="right">
								�˱����ڣ�
							</td>
							<td align="left">
								<html:text name='rs' 
								           property="save_tuibsj" 
								           styleId="tuibsj"
									       onblur="dateFormatChg(this)" 
									       style="cursor:hand;"
									       onclick="return showCalendar('tuibsj','y-mm-dd');" />
							</td>	
						</tr>
						<tr>
							<td align="right">
								���֤�ţ�
							</td>
							<td align="left">
								<html:text name='rs' property="sfzh" styleId="sfzh" disabled="true" />
							</td>
							<td align="right">
								�˱���ǣ�
							</td>
							<td align="left">
								<html:select name="rs" property="save_tbbj" style="width:90px"
									styleId="tbbj">
									<html:option value=""></html:option>
									<html:option value="δ��">δ��</html:option>
									<html:option value="����">����</html:option>
								</html:select>
							</td>
						</tr>
						<tr>
							<td align="right">
								�ֻ����룺
							</td>
							<td align="left">
								<html:text name='rs' property="sjhm" styleId="sjhm" disabled="true" />
							</td>
							 <td align="right">
								�ɷѱ�ǣ�
							</td>
							<td align="left">
								<html:select name="rs" property="save_jfbj" style="width:90px" styleId="jfbj">
								    <html:option value="��">��</html:option>
								    <html:option value="��">��</html:option>
								</html:select>
							</td>
						</tr>
						<!--�㶫Ů��ְҵ����<bean:message key="lable.xsgzyxpzxy" />-->
						<logic:equal value="12742" name="xxdm">
						<tr>	
							<td align="right">
								���յ��Σ�
							</td>							
							<td align="left">
								<html:select name="rs" property="save_bxdc" onchange="">
									<html:option value="">---��ѡ��---</html:option>
									<html:options collection="bxdcList" property="dcdm" labelProperty="dcmc"/>
								</html:select>							
							</td>
							<td>
							</td>
							<td>
							</td>
						</tr>
						</logic:equal>
						<!--�ǹ㶫Ů��ְҵ����<bean:message key="lable.xsgzyxpzxy" />-->
						<tr align="left">
							<td align="right">
								��ע��
							</td>
							<td colspan="3">
								<html:textarea name='rs' property='save_bz' style="width:99%"
									rows='5'  onblur="chLeng(this,250)"/>
							</td>
						</tr>
					</table>
					<div class="buttontool" align="center">
						<button class="button2" onclick="saveData('bxgl.do?method=tbxxAdd&type=add','xh-nd-tbxzdm-tbsj-bxnx-tbgsdm')"
							style="width:80px" id="buttonSave">
							�� ��
						</button>
						&nbsp;&nbsp;&nbsp;&nbsp;
						<button class="button2" onclick="Close();return false;" style="width:80px"
							id="buttonClose">
							�� ��
						</button>
					</div>
				</fieldset>
		</html:form>
		<logic:present name="result">
			<intput type="hidden" id="message" value="${message}"/>
			<script>
				alert(document.getElementById('message').value);
				if(window.dialogArguments){
			 		window.dialogArguments.document.getElementById('search_go').onclick();
			 	}
				Close();
			</script>
		</logic:present>
	</body>
</html>
