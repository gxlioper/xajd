<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ include file="/syscommon/pagehead.ini"%>
<script type="text/javascript" src="pjpy/xibeierminyuan/js/xbemyJs.js"></script>
<script type="text/javascript" src="pjpy/zjjd/zjjdjs/zjjdjs.js"></script>
<script type="text/javascript" src="pjpy/czxx/czxx.js"></script> 
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'showRych.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
   <html:form action="nbty_rych">
   <input type =hidden name="method" value="rychSh">
   <input type =hidden name="doType" value="save">
   	<div class="title">
				<div class="title_img" id="title_m">
					��ǰλ�ã��������� - �����ƺ���� - �������
				</div>
			</div>


			<table width="99%" id="rsT" class="tbstyle">
				<thead>
					<tr style="height:22px">
						<td colspan="4" align="center">
							<b>�����ƺ����</b>
						</td>
					</tr>
				</thead>
				<tr style="height:22px">
					<td align="right" style="width: 10%">
						<font color="red">*</font>ѧ�ţ�
					</td>
					<td>
						<bean:write name="rs" property="xh" />	
				    </td>
					<td align="right" style="width: 10%">
						ѧ�꣺
					</td>
					<td align="left" style="width: 40%">
						<bean:write name="rs" property="xn" />	
					</td>
				</tr>
				<tr style="height:22px">
					<td align="right">
						������
					</td>
					<td align="left">
					<logic:notEmpty name="rs">
						<bean:write name='rs' property="xm" />
						</logic:notEmpty>
					</td>
					<td align="right">
						ѧ�ڣ�
					</td>
					<td align="left">
						<logic:notEmpty name="rs">
						<bean:write name='rs' property="xqmc" />
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
					</td>
					<td align="right">
						<font color="red">*</font>�����ƺţ�
					</td>
					<td align="left">
						<logic:notEmpty name="rs">
						<bean:write name='rs' property="rychmc" />
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
					</logic:notEmpty>
					</td>
					<td align="right">
						����ְ��
					</td>
					<td align="left">
						<bean:write name="rs"  property="xrzw"/>
					</td>
				</tr>
				<tr style="height:22px">
					<td align="right">
						רҵ��
					</td>
					<td align="left">
					<logic:notEmpty name="rs">
						<bean:write name="rs" property="zymc"/>
					</logic:notEmpty>
					</td>
					<td align="right">
						ѧ��ƽ���ɼ�
					</td>
					<td align="left">
						<bean:write name="rs" property="xnpjcj"/>
					</td>
				</tr>
				<tr style="height:22px">
					<td align="right">
						�༶��
					</td>
					<td align="left">
					<logic:notEmpty name="rs">
						<bean:write name='rs' property="bjmc" />
					</logic:notEmpty>
					</td>
					<td align="right">
						����ְʱ�䣺
					</td>
					<td align="left">
					<logic:notEmpty name="rs">
					   <bean:write name='rs' property="rxzsj"/>
					 </logic:notEmpty>
					</td>
				</tr>
				<tr>
					<td align="right">
						���
					</td>
					<td>
						<html:select property="primarykey_sh">
							<option value=""></option>
							<option value="δͨ��">δͨ��</option>
							<option value="��ͨ��">��ͨ��</option>
							<option value="ûͨ��">ûͨ��</option>
						</html:select>
					</td>
					<td align="right">
						
					</td>
					<td>
						
					</td>
				</tr>
				<tr>
				<td colspan=2 align="center">
					<input type=submit onclick="window.close();return false;"  value="����">
				</td>
				<td  colspan=2 align="center">
					<input type=button onclick="window.close();return false;" value="�ر�">
				</td>
				</tr>
				</table>
   </html:form>
  </body>
</html>
