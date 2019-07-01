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
	<base target="_self"/>
	<%
response.setHeader("Pragma","No-cache");
response.setHeader("Cache-Control","no-cache");
response.setDateHeader("Expires", 0);
%>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<link id="csssDate" rel="stylesheet" rev="stylesheet"
		href="js/calendar.css" type="text/css" media="all" />
	<script language="javascript">
</script>
	<body>
		<script language="javascript" src="js/function.js"></script>
		<script language="javascript" src="js/sztzFunction.js"></script>
		<script type='text/javascript' src='/xgxt/dwr/interface/getSztzData.js'></script>
		<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
		<script type='text/javascript' src='dwr/util.js'></script>
		<script type="text/javascript" src="js/calendar.js"></script>
		<script type="text/javascript" src="js/calendar-zh.js"></script>
		<script type="text/javascript" src="js/calendar-setup.js"></script>
<html:form action="/tzbj_sq" method="post">
			<div class="title">
				<div class="title_img" id="title_m">
					��ǰ����λ�ã�������չ - ���� - ѧ������
				</div>
			</div>
			<logic:empty name="rs">
				<br />
				<br />
				<p align="center">
					�д�������
				</p>
			</logic:empty>
			<input type="hidden" id="realTable" name="realTable"
			   value="<bean:write name="realTable" scope="request"/>" />
			<input type="hidden" id="pkValue" name="pkValue"
			   value="<bean:write name="pkValue" scope="request"/>" />
			<input type="hidden" id="doType" name="doType"
			   value="<bean:write name="doType" scope="request"/>"/>   
			<input type="hidden" id="disableEle" name="disableEle"
					value="xm-xb-xy-nj-zy-bj" />
			<input type="hidden" id="getStuInfo" name="getStuInfo"
					value="xm-xb-xymc-nj-zymc-bjmc" />   
			<input type="hidden" id="url" name="url" value="/tzbj_sq.do" />     
			<logic:equal value="1" name="sqsjTag">
			   <br />
				<br />
				<p align="center">
					<font color="red">�����趨ʱ�䷶Χ��,�ݲ���������!</font>
				</p>
			</logic:equal>   
			<logic:notEqual value="1" name="sqsjTag">
			<logic:notEmpty name="rs">   
				<fieldset>
					<legend>
						��д�����
					</legend>
					<table width="100%" class="tbstyle">
							<thead>
								<tr style="height:22px">
									<td colspan="4" align="center">
										<b>��д�����</b>
									</td>
								</tr>
							</thead>
							<tr valign="middle">
								<logic:equal name="userOnLine" value="teacher" scope="session">
									<td align="right" width="10%" >
										<font color="red">*</font>ѧ�ţ�
									</td>
									<td align="left">
										<html:text name='rs' property="xh" styleId="xh" readonly="true"
											onkeypress="autoFillStuInfo(event.keyCode,this)" />
									<logic:notEqual value="modi" name="doType">	
										<button onclick="showTopWin('/xgxt/stu_info.do',750,550);"
											class="btn_01" id="buttonFindStu">
											ѡ��										
										</button>
									</logic:notEqual>	
									</td>
								</logic:equal>
								<logic:equal name="userOnLine" value="student" scope="session">
									<td align="right" width="15%">
										<font color="red">*</font>ѧ�ţ�
									</td>
									<td align="left">
										<input type="hidden" name="xh" id="xh" readonly="true"
											value="<bean:write name='rs' property="xh" />">
										<bean:write name='rs' property="xh" />
									</td>
								</logic:equal>
								<td align="right" width="25%">
									��ȣ�
								</td>
								<td align="left">
									<html:text name="rs" property="nd" readonly="true"></html:text>
								</td>								
						</tr>
						<tr valign="middle">
								<td align="right">
									������
								</td>
								<td align="left">
									<bean:write name='rs' property="xm" />
								</td>
								<td align="right">
									ѧ�꣺
								</td>
								<td align="left">
									<html:text name="rs" property="xn" readonly="true"></html:text>
								</td>
							</tr>
						<tr style="height:22px">
								<td align="right">
									�Ա�
								</td>
								<td align="left">
									<bean:write name='rs' property="xb" />
								</td>
								<td align="right" nowrap>
									ѧ�ڣ�
								</td>
								<td align="left">
									<html:text name="rs" property="xq" styleId="xq" readonly="true" />
								</td>
								
						</tr>
						<tr style="height:22px">
								<td align="right">
									�꼶��
								</td>
								<td align="left">
									<bean:write name='rs' property="nj" />
								</td>
								<td align="right" nowrap>
									<font color="red">*</font>������չ�༶��
								</td>
								<td align="left">
									<html:select name="rs" property="dm" styleId="dm"
										onchange="refreshForm('/xgxt/tzbj_sq.do')"
										style="width=150px;background-color:#FFFFFF;">
										<option value=""></option>
										<html:options collection="tzBjList" property="dm"
											labelProperty="mc"></html:options>
									</html:select>
								</td>								
						</tr>
						<tr style="height:22px">
								<td align="right">
									<bean:message key="lable.xsgzyxpzxy" />��
								</td>
								<td align="left">
									<bean:write name='rs' property="xymc" />
								</td>
								<td align="right" nowrap>
									����ʱ�䣺
								</td>
								<td align="left">
									<bean:write name="rs" property="kssj" />
								</td>
						</tr>
						<tr style="height:22px">
								<td align="right">
									רҵ��
								</td>
								<td align="left">
									<bean:write name='rs' property="zymc" />
								</td>								
								<td align="right" nowrap>
									��ҵʱ�䣺
								</td>
								<td align="left">
									<bean:write name="rs" property="jssj" />
								</td>
						</tr>
						<tr style="height:22px">
								<td align="right">
									�༶��
								</td>
								<td align="left">
									<bean:write name='rs' property="bjmc" />
								</td>
								<td align="right" nowrap>
									
								</td>
								<td align="left">
									
								</td>
						</tr>																								
						<tr align="left" valign="top">
							<td align="right">
								 ����������
							</td>
							<td colspan="4">	 
								<bean:write name="rs" property="nr"/>
							</td>
						</tr>
						<tr align="left" valign="top">
							<td  align="right">
								 �������ɣ�
							</td>
							<td colspan="4">	 
								<html:textarea name="rs" property="sqly" rows="6"  
									style="width:95% "></html:textarea>
							</td>
						</tr>							
					</table>
					<div class="buttontool" id="btn" align="center">
						<button class="button2"
							onclick="toSave('/xgxt/tzbj_sq.do?doType=save','xh-dm');"
							style="width:80px" id="buttonSave">
							�ύ����
						</button>
					</div>
				</fieldset>
			</logic:notEmpty>
		  </logic:notEqual> 	
		</html:form>
		<script type="text/javascript" src="js/bottomButton.js"></script>
	</body>
	<logic:equal value="" name="pkValue">
	    <logic:equal value="ok" name="done">
		<script type="text/javascript">
	     alert("����ɹ���");
	    </script>
	    </logic:equal>
     </logic:equal> 
    <logic:equal value="" name="pkValue"> 	    
	    <logic:equal value="no" name="done">
		<script type="text/javascript">
	     alert("����ʧ�ܣ�");
	    </script>
	    </logic:equal>
	</logic:equal>	
	<logic:notEqual value="" name="pkValue">
		<logic:equal value="ok" name="done">
		<script type="text/javascript">
	      alert("�޸ĳɹ���");
		  Close();
		  window.dialogArguments.document.getElementById('search_go').click();
	    </script>
		</logic:equal>
		<logic:equal value="no" name="done">
		<script type="text/javascript">
	      alert("�޸�ʧ�ܣ�");
		  Close();
		  window.dialogArguments.document.getElementById('search_go').click();
	    </script>
		</logic:equal>
	</logic:notEqual>
</html>

		
		
		
		
