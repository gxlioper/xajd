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
		<meta http-equiv="Cache-Control" http-equiv="no-cache" />
		<meta http-equiv="Expires" http-equiv="0" />
		<meta name="Copyright" content="������� zfsoft" />
	</head>
	<script language="javascript" >
		function qgzx_result_print()
		{
			openWin("/xgxt/post_stu_result.do?doType=print",800,650);
		}
		function check_user()
		{
			var userType1=document.all['userType1'].value;
			if("xy"==userType1)
			{
				document.getElementById('xy').disabled=true;
			}
			else
			{
				document.getElementById('xy').disabled=false;
			}
		}
	</script>
	<%
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
	%>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />

	<link id="csssDate" rel="stylesheet" rev="stylesheet"
		href="js/calendar.css" type="text/css" media="all" />
	<body onload="check_user();xyDisabled('xy');removeXnXq()">
	
		<script language="javascript" src="js/BatAlert.js"></script>
		<script language="javascript" src="js/function.js"></script>
		<script language="javascript" src="js/jsFunction.js"></script>
		<script type="text/javascript" src="js/calendar.js"></script>
		<script type="text/javascript" src="js/calendar-zh.js"></script>
		<script type="text/javascript" src="js/calendar-setup.js"></script>
		<script type="text/javascript" src="js/lrh_new_js.js"></script>
		<script type='text/javascript' src='/xgxt/dwr/interface/cqkjFunc.js'></script>
		<script type="text/javascript" src="/xgxt/dwr/util.js"></script>	
		<script language="javascript" src="js/qgzxFunction.js"></script>
		<script language="javascript" src="js/xgutil.js"></script>
		
		<html:form action="/post_stu_result" method="post">
		<object id="WebBrowser" width=0 height=0 classid="CLSID:8856F961-340A-11D0-A96B-00C04FD705A2"></object>
			<div class="title">
				<div class="title_img" id="title_m">
					��ǰ����λ�ã�
					<bean:write name="tips" scope="request" />
				</div>
			</div>

				<div class="rightcontent">

					<fieldset>
						<legend>
							�� ѯ
						</legend>
						
						<input type="hidden" id="userType" name="userType"
							value="<bean:write name="userType" scope="request"/>" />
						<input type="hidden" id="tableName" name="tableName"
							value="<bean:write name="tableName" scope="request"/>" />
						<input type="hidden" id="act" name="act"
							value="<bean:write name="act" scope="request"/>" />
						<input type="hidden" id="realTable" name="realTable"
							value="<bean:write name="realTable" scope="request"/>" />
						<input type="hidden" id="pk" name="pk"
							value="<bean:write name="pk" scope="request"/>" />
						<input type="hidden" id="userType1" name="userType1"
							value="<bean:write name="userType1" scope="request"/>" />
						<table width="100%" class="tbstyle">
							<thead>
								<tr>
									<td align="left">
										�꼶��
										<html:select property="nj" style="width:80px"
											onchange="refreshForm('/xgxt/post_stu_result.do')">
											<html:option value=""></html:option>
											<html:options collection="njList" property="nj"
												labelProperty="nj" />
										</html:select>
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;ѧ�꣺
										<html:select property="xn" style="width:100px" styleId="xn" >
													<html:options collection="xnList" property="xn"
														labelProperty="xn" />
												</html:select>

										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;ѧ�ڣ�
										<html:select property="xq" style="width:90px" styleId="xq">
														<html:option value=""></html:option>
														<html:options collection="xqList" property="xqdm"
															labelProperty="xqmc" />
													</html:select>
										&nbsp;&nbsp;&nbsp;ѧ�ţ�
										<html:text property="xh" style="width:85px"></html:text>	
										
										<!-- liang : �㽭����ְҵ����<bean:message key="lable.xsgzyxpzxy" /> -->
										<logic:equal value="12861" name="xxdm">
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;���˵�λ��
											<html:select property="yrdwdm" style="width:90px">
												<html:option value=""></html:option>
												<html:options collection="yrdwList" 
													property="yrdwdm" labelProperty="yrdwmc"/>
											</html:select>
										</logic:equal>									
									</td>
									<td width="10" rowspan="2" align="center" valign="middle">
										<input type="hidden" name="go" value="a" />
										<button type="button" class="button2" style="height:40px" id="search_go"
											onclick="allNotEmpThenGo('/xgxt/post_stu_result.do?doType=search')">
											��ѯ
										</button>
									</td>
								</tr>
								<tr>
									<td align="left" nowrap>
										<bean:message key="lable.xsgzyxpzxy" />��
										<html:select property="xydm" style="width:180px" styleId="xy" 
											onchange="refreshForm('/xgxt/post_stu_result.do')">
											<html:option value=""></html:option>
											<html:options collection="xyList" property="xydm"
												labelProperty="xymc" />
										</html:select>
										&nbsp;&nbsp;רҵ��
										<html:select property="zydm" style="width:180px" styleId="zy"
											onchange="refreshForm('/xgxt/post_stu_result.do')">
											<html:option value=""></html:option>
											<html:options collection="zyList" property="zydm"
												labelProperty="zymc" />
										</html:select>
										&nbsp;&nbsp;�༶��
										<html:select property="bjdm" style="width:180px" styleId="bj">
											<html:option value=""></html:option>
											<html:options collection="bjList" property="bjdm"
												labelProperty="bjmc" />
										</html:select>
									</td>
								</tr>
							</thead>
						</table>
					</fieldset>
					<logic:empty name="rs">
						<p align="center">
							δ�ҵ��κμ�¼��
						</p>
					</logic:empty>
					<logic:notEmpty name="rs">
						<fieldset>
							<legend>
								��¼����
								<bean:write name="rsNum" />
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<font color="blue">��ʾ��˫��һ�п��Բ鿴��ϸ��Ϣ��������ͷ��������;��סCtrl����ѡ�������¼</font>
							</legend>
							<table width="100%" id="rsTable" class="tbstyle">
								<thead>
									<tr align="center" style="cursor:hand">
											<logic:iterate id="tit" name="topTr" offset="1">
												<td id="<bean:write name="tit" property="en"/>"
													onclick="tableSort(this)" nowrap>
													<bean:write name="tit" property="cn" />
												</td>
											</logic:iterate>
									</tr>
								</thead>
								<logic:iterate name="rs" id="s">
											<tr onclick="rowMoreClick(this,'',event);" style="cursor:hand"
												ondblclick="viewMore('view')">
												<td>
													<logic:iterate id="v" name="s" offset="0" length="1">
														<input type="hidden" value="<bean:write name="v"/>" />
													</logic:iterate>
													<logic:iterate id="v" name="s" offset="1" length="1">
														<bean:write name="v" />
													</logic:iterate>
												</td>
												<logic:iterate id="v" name="s" offset="2">
												<td nowrap>
													<bean:write name="v" />
												</td>
												</logic:iterate>
											</tr>
								</logic:iterate>
							</table>
						</fieldset>
					</logic:notEmpty>
					<div id="toolTipLayer"
						style="position:absolute; visibility: hidden" /></div>
					<logic:equal value="yes" name="writeAble" scope="request">
						<center>
							<div class="buttontool" id="btn"
								style="position: absolute;left:1%;top:100px" width="100%">
							        <button type="button" class="button2" onclick="dataExport()" style="width:80px">
										��������
									</button>
		
								&nbsp;&nbsp;&nbsp;&nbsp;
								<button type="button" class="button2" onclick="qgzx_result_print()" style="width:80px">
								��ӡ
								</button>
							</div>
						</center>
					</logic:equal>
			<div id="tmpdiv"></div>
			</div>
		</html:form>
		<logic:equal name="result" value="ok">
			<script language="javascript">
      				alert("�����ɹ���");
	  		</script>
		</logic:equal>
		<logic:equal name="result" value="no">
			<script language="javascript">
	  				alert("����ʧ��! ");
	  		</script>
		</logic:equal>
		<script type="text/javascript" src="js/bottomButton.js"></script>
	</body>
</html>

