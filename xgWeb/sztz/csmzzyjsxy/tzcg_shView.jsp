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
<%@ include file="/syscommon/pagehead_V4.ini"%>
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
<%
response.setHeader("Pragma","No-cache");
response.setHeader("Cache-Control","no-cache");
response.setDateHeader("Expires", 0);
%>
	<body onload="initCheck()">
		<script language="javascript" src="js/function.js"></script>
		<script language="javascript" src="js/sztzFunction.js"></script>
		<html:form action="/csmz_sztz.do?method=tzcg_sh" method="post">
	    <input type="hidden" name="pkValue" id="pkValue" value="${pkValue}">
		<input type="hidden" name="xmid" id="xmid" value="${xmid}">
		<input type="hidden" name="xmjb" id="xmjb" value="${xmjb}">	
		<input type="hidden" name="jxlb" id="jxlb" value="${jxlb}">		
			<div class="tab_cur">
				<p class="location">
					<em>���ĵ�ǰλ��:</em><a>${tips }</a>
				</p>
			</div>
			<div class="tab">
			  <table width="100%"  border="0" class="formlist">
			    <thead>
			    	<tr>
			        	<th colspan="4"><span>�������</span></th>
			        </tr>
			    </thead>
			     <tfoot>
			      <tr>
			        <td colspan="4">
			          <div class="btn">
			            <button name="�ر�" onclick="Close();return false;">�ر�</button>
			          </div>
			         </td>
			      </tr>
			    </tfoot>

					<div align="right">	
					<logic:equal value="up" name="view">
						<input type="button"  id="up" value="��һ��" disabled="true">
					</logic:equal>
					<logic:notEqual value="up" name="view">
						<input type="button" id="up" value="��һ��" onclick="showTips('ˢ�������У����Ժ�...');changerecord('up','/xgxt/csmz_sztz.do?method=tzcg_shView');">
					</logic:notEqual>
					&nbsp; &nbsp;
					<logic:equal value="next" name="view">
						<input type="button"  id="next" value="��һ��" disabled="true">
					</logic:equal>
					<logic:notEqual value="next" name="view">
						<input type="button" id="next" value="��һ��" onclick="showTips('ˢ�������У����Ժ�...');changerecord('next','/xgxt/csmz_sztz.do?method=tzcg_shView');">
					</logic:notEqual>
					&nbsp; &nbsp;&nbsp; &nbsp;
					<logic:equal value="true" name="sel">
						<input type="checkbox" id="selected" onclick="shot(this);" checked="true"/>&nbsp;ѡ��
					</logic:equal>
					<logic:notEqual value="true" name="sel">
					<input type="checkbox" id="selected" onclick="shot(this);"/>&nbsp;ѡ��
					</logic:notEqual>
					</div>
				<tr >
					<th align="right" style="width: 100px" >
						ѧ��
				    </th>
					<td align="left" style="width: 250px">
					<bean:write name='rs' property="xh" />						
					</td>
				    <th align="right" style="width: 100px" >
						��Ŀ����
					</th>
					<td align="left" style="width: 450px" >
					<bean:write name='rs' property="xmmc" />					
					</td>
				</tr>
				<tr >
					<th align="right">
						����
					</th>
					<td align="left">
						<bean:write name='rs' property="xm" />
					</td>
					<th align="right">
						ѧ��
					</th>
					<td align="left">
						<bean:write name='rs' property="xn" />
					</td>
				</tr>
				<tr >
					<th align="right">
						�Ա�
					</th>
					<td align="left">
						<bean:write name='rs' property="xb" />
					</td>
					<th align="right">
						ѧ��
					</th>
					<td align="left">
						<bean:write name='rs' property="xq" />
					</td>
				</tr>	
				 <tr >
					<th align="right">
						<bean:message key="lable.xsgzyxpzxy" />
					</th>
					<td align="left">
						<bean:write name='rs' property="xymc" />
					</td>
					<th align="right">
						������Ŀ
					</th>
					<td>
						<bean:write name='rs' property="kmmc" />
					</td>				
				<tr >
					<th align="right">
						רҵ
					</th>
					<td align="left">
						<bean:write name='rs' property="zymc" />
					</td>
					<th align="right">
						�(��Ŀ)����
					</th>
					<td align="left">
						<bean:write name='rs' property="xmjb" />
					</td>
				</tr>
				<tr >
					<th align="right">
						�༶
					</th>
					<td align="left">
						<bean:write name='rs' property="bjmc" />
					</td>
					<th align="right">
						<logic:equal value="12104" name="xxdm">
							       ָ����ʦ
							    </logic:equal>
							    <logic:notEqual value="12104" name="xxdm">
							       ���쵥λ
							    </logic:notEqual>
					</th>
					<td align="left">
						<bean:write name='rs' property="zzdw" />
					</td>
				</tr>
				<tr align="left">
					<th align="right">
						�����ɫ
					</th>
					<td align="left">
						<bean:write name='rs' property="cyjs" />
					</td>
					<th align="right">
						����ʱ��
					</th>
					<td align="left">
						<bean:write name='rs' property="zbsj" />
					</td>
				</tr>	
				<tr >
					<th align="right">
					   �걨ʱ��
					</th>
					<td align="left">
						<bean:write name='rs' property="sbsj" />
					</td>
					
					
					
						<logic:equal name="jxsb" value="xssb">
						<th align="right">
							�񽱽���
						</th>
						<td align="left">
							<bean:write name='rs' property="jxm" />
						</td>
						</logic:equal>
						<logic:equal name="jxsb" value="lssb">
						<th align="right">
							�񽱽���
						</th>
						<td align="left">
							<bean:write name='rs' property="jxm" />
						</td>
							
						</logic:equal>
					
					
				</tr>
				<tr >
					<th align="right">
						��Ŀ������<br>(�걨��)
					</th>
					<td align="left">
						<bean:write name='rs' property="xmsbr" />
					</td>
					<td align="right">
					   
					</td>
					<td align="left">
						
					</td>
				</tr>				
				<tr align="left">
					<th align="right">
						��Ŀ����
					</th>
					<td colspan="3">
						<textarea rows="5" cols="66"><bean:write name='rs' property="xmms" /></textarea>	
					</td>
				</tr>
				<tr align="left">
					<th align="right">
						�ɹ�����
					</th>
					<td colspan="3">
					<textarea rows="5" cols="66"><bean:write name='rs' property="cgms" /></textarea>		
					</td>
				</tr>				
     		</table>
			
		</html:form>		
	</body>
	<script type="text/javascript">
	
	</script>
</html>
