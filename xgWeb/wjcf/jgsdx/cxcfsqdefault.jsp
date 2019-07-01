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
    <base target="_self">
	<head>
		<title><bean:message key="lable.title" /></title>
		<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
		<meta http-equiv="Pragma" http-equiv="no-cache" />
		<meta http-equiv="Cache-Control"
			http-equiv="no-cache, must-revalidate" />
		<meta http-equiv="Expires" http-equiv="0" />

		<meta name="Copyright" content="������� zfsoft" />
	</head>
	<%
    response.setHeader("Pragma","No-cache");
    response.setHeader("Cache-Control","no-cache");
    response.setDateHeader("Expires", 0);
    %>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<base target="_self">
	<body>
	    <script language="javascript" src="js/function.js"></script>
	    <script type="text/javascript" src="js/wjcfFuction.js"></script>
	    <script type="text/javascript" src="js/calendar.js"></script>
	    <script type="text/javascript" src="js/calendar-zh.js"></script>
	    <script type="text/javascript" src="js/calendar-setup.js"></script>
	    <script type="text/javascript" src="/xgxt/dwr/interface/getWjcfInfo.js"></script>
	    <script type="text/javascript" src="/xgxt/dwr/engine.js"></script>
	    <script type="text/javascript" src="/xgxt/dwr/util.js"></script>
	    <script type="text/javascript" src="wjcf/csmz/csmzJs/csmzJs.js"></script>
		<html:form action="/wjcfjgsdxwh.do" method="post" >
			<div class="title">
				<div class="title_img" id="title_m">
					<bean:message key="wjcf_jgsdx_cxcfsq" bundle="wjcfjgsdx"/>
				</div>
			</div>
			<logic:empty name="rs">			
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
                <input type="hidden" id="disableEle" name="disableEle"
					     value="xm-xb-xy-nj-zy-bj" />
				<input type="hidden" id="getStuInfo" name="getStuInfo" value="xm-xb-xymc-nj-zymc-bjmc" />
				<input type="hidden" id="url" name="url" value="/wjcf_jgsdx_cxcfsqdefault.do" />
<%--                <input type="hidden" id="cfid" name="cfid" --%>
<%--                       value="<bean:write name="cfid" scope="request"/>" />--%>
                  <input type="hidden" id="pkValue" name="pkValue" value="${pkVal }"/>
                  <input type="hidden" id="tabType" name="tabType" value="wjcf_cxcfb"/>               
                <fieldset>
					<legend>
						��д�����
					</legend>
				<table class="tbstyle" id="rsTable" width="100%" style="cursor:hand">
					<thead>
							<tr style="height:22px">
								<td colspan="4" align="center">
									<b><bean:message key="wjcf_csmz_cxcfsqb" bundle="wjcfcsmz"/></b>
								</td>
							</tr>
					</thead>
					<tr align="center" style="cursor:hand">
						<logic:equal name="userOnLine" value="teacher" scope="session">
							<td align="right" width="15%">
								<font color="red">*</font>ѧ�ţ�
							</td>
							<td align="left" width="25%">
							    <html:text name='rs' property="xh" styleId="xh"
									 onkeypress="if(event.keyCode == 13) return false;" readonly="true" />
								<logic:notEqual value="domodi" name="modi">
								<button type="button" onclick="showTopWin('/xgxt/stu_info.do',750,550);"  class="btn_01" id="buttonFindStu">
										ѡ��
								</button>
								</logic:notEqual>
							</td>
						</logic:equal>
						<logic:equal name="userOnLine" value="student" scope="session">
							<td align="right" width="15%">
								<font color="red">*</font>ѧ�ţ�
							</td>
							<td align="left" width="25%">
							    <input type="text" id="xh" name="xh"  value="<bean:write name='rs' property="xh"  />" readonly="true" />
							</td>
						</logic:equal>
						<td  align="right" width="15%">
							<font color="red">*</font>
							�����ļ��ţ�
						</td>
						<td align="left" width="30%">
							<html:text name='rs' property="cfwh" styleId="cfwh" readonly="true"/>
							<button type="button" onclick="wjcfInfoBylxck('lxck')"class="button2" id="buttonFindWjcf">
										ѡ��
							</button>
						</td>
											
					</tr>
					<tr align="center" style="cursor:hand">
						<td width="10%" align="right" >
							������
						</td>
						<td align="left">
							<html:text name='rs' property="xm" readonly="true" />
						</td>
						<td align="right">
							ѧ�꣺
						</td>
						<td align="left">
							<html:text name='rs' property="xn" readonly="true" />
						</td>						
					</tr>
					<tr align="center" style="cursor:hand">
						<td  align="right">							
							�Ա�
						</td>
						<td align="left">
							<html:text name='rs' property="xb" readonly="true" />
						</td>
						<td  align="right">
					       ѧ�ڣ�
					    </td>
					    <td align="left">
					       <html:text name='rs' property="xq" readonly="true" />
					    </td>
					</tr>
					<tr align="center" style="cursor:hand">
						<td  align="right">
					       �꼶��
					    </td>
					    <td align="left">
					     <html:text name='rs' property="nj" readonly="true" />  
					    </td>
						 <td  align="right">
					       ����ʱ�䣺
					    </td>
					    <td align="left">
					       <html:text name='rs' property="cfsj" readonly="true" />
					    </td>
					</tr>
					<tr>
					    <td  align="right">
					       <bean:message key="lable.xsgzyxpzxy" />��
					    </td>
					    <td align="left">
					       <html:text name='rs' property="xymc" readonly="true" />
					    </td>
					   <td  align="right">
					       �������
					    </td>
					    <td align="left" colspan="">
					       <html:text name='rs' property="cflbmc" readonly="true" />
					    </td>
					</tr>
					<tr>
					    <td  align="right">
					       רҵ��
					    </td>
					    <td align="left">
					       <html:text name='rs' property="zymc" readonly="true" />
					    </td>
					    <td  align="right">
					       �������ɣ�
					    </td>
					    <td align="left" colspan="">
					       <html:text name='rs' property="cfyymc" readonly="true" />
					    </td>
					</tr>
					<tr>
					    <td  align="right">
					       �༶��
					    </td>
					    <td align="left">
					       <html:text name='rs' property="bjmc" readonly="true" />
					    </td>
					    <td align="right">
					    	����ʱ�䣺
					    </td>
					    <td align="left">
					    	<html:text name='rs' property="cxsj" readonly="true"></html:text>
					    </td>
					</tr>
					<tr>
						<td align="right">
							�����������ɣ�
						</td>
						<td align="left" colspan="3"><html:textarea property="bz" styleId="bz"
							 styleClass="inputtext;" rows="8" style="width:100%;word-break:break-all;"></html:textarea>
						</td>
					</tr>					
				</table>
				<div class="buttontool" align="center">
					<button type="button" class="button2" onclick="savesqinfo('xh','cfwh','cxcfsqsave.do')">
						�� �� �� ��
					</button>
					
				</div>
             </fieldset>
			</logic:notEmpty>
			 <logic:notEmpty name="inserted">
				<logic:equal name="inserted" value="yes">
					<script>
                      alert("�ύ�ɹ���");                
                    </script>
				</logic:equal>
				<logic:equal name="inserted" value="no">
					<script>
                      alert("�ύʧ��,�����������,�����ظ�����!");                   
                    </script>
				</logic:equal>
			</logic:notEmpty>
			<logic:equal value="no" name="isHG">
				<script>
                      alert("���������������ϸ񣬴���������һ���������!");                   
                    </script>
			</logic:equal>
		</html:form>
</html>
