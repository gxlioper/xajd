<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>

<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/check.js"></script>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script type="text/javascript">
		
			function aaa(){
				var cftjlx=$("cftjlx").value;
				//document.getElementById("cftjlx")
				//document.getElementsByName("cftjlx")
	
				if(cftjlx=="1"){
					document.getElementById("ksxn").disabled = false;
					document.getElementById("jsxn").disabled = false;
				}else{
					document.getElementById("ksxn").disabled = true;
					document.getElementById("jsxn").disabled = true;
				}



				}
			function tj(){
				var cftjlx=$("cftjlx").value;
				var dqxn=$("dqxn").value;
				var jsxn=$("jsxn").value;
				var ksxn=$("ksxn").value;
				jQuery("#cftjlx").val(cftjlx);
				jQuery("#ksxn").val(ksxn);
				jQuery("#jsxn").val(jsxn);
				jQuery("#dqxn").val(dqxn);
				jQuery("#doType").val("");
				document.forms[0].action="wjcfCfsjtj.do";
				document.forms[0].submit();
				
				}
			function exportData(){
			var cftjlx=$("cftjlx").value;
			var dqxn=$("dqxn").value;
				var doType="";
				if(cftjlx=="2"){
					doType="xytj";
				}else if(cftjlx=="3"){
					doType="yltj"
				}
				jQuery("#doType").val(doType);
				jQuery("#cftjlx").val(cftjlx);
				jQuery("#dqxn").val(dqxn);

				document.forms[0].action="wjcfCfsjtj.do";
				document.forms[0].target = "_blank";
				document.forms[0].submit();
				document.forms[0].target = "_self";
				}

		
		</script>

  </head>
  
  <body onload="aaa()">
		<!-- ���� -->
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>${title }</a>
			</p>
		</div>


		<html:form action="/wjcfCfshwh_cfsjwh" method="post">

			<!-- ������ -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<!-- ������ -->
			<input type="hidden" name="xmdm" id="xxmmdm" value="${xmdm }" />

			<!-- �๦�ܲ����� -->
			<div class="toolbox" id="dgncz">
				<!-- ��ť -->
				
				<div class="buttonbox">
					<ul>
						<li><a href="#" class="btn_dc" onclick="exportData();return false;">��������</a></li>
					</ul>
				</div>
			<div class="buttonbox">
			��ǰѧ�꣺<html:select name="wjcfCfsjtjActionForm" property="xn" style="width:100px">
					<html:options collection="xnList" property="tjxn" labelProperty="tjxn"/>
				</html:select>
				<input type="hidden" name="dqxn" id="dqxn" value="${dqxn}" />
			ͳ�����ͣ�&nbsp;<html:select name="wjcfCfsjtjActionForm" property="cftjlx" style="width:100px" onchange="aaa();">
				<!--<html:option value="1">����ѧ��ͳ��</html:option>-->
				<html:option value="2">����<bean:message key="lable.xb" />ͳ��</html:option>
				<html:option value="3">ѧ��һ��</html:option>
			</html:select>
			<!--��ʼѧ�꣺<html:select name="wjcfCfsjtjActionForm" property="ksxn" style="width:100px" styleId="ksxn">
					<html:options collection="xnList" property="tjxn" labelProperty="tjxn"/>
				</html:select>
			����ѧ�꣺<html:select name="wjcfCfsjtjActionForm" property="jsxn" style="width:100px" styleId="jsxn">
					<html:options collection="xnList" property="tjxn" labelProperty="tjxn"/>
				</html:select>-->
			<input type="hidden" name="cftjlx" id="cftjlx" value="${cftjlx}" />
			<input type="hidden" name="ksxn" id="ksxn" value="${ksxn}" />
			<input type="hidden" name="jsxn" id="jsxn" value="${jsxn}" />
			<input type="hidden" name="doType" id="doType" value="" />


			<button type="button"  class="" style="height: 23px" id="" onclick="tj();return false;" >
				ͳ�Ʋ�ѯ
			</button>
			
			</div>

			</div>
			<h3 class="datetitle_01">
					<span> ��ѯ���&nbsp;&nbsp;  </span>
			</h3>
			<table width="99%" id="rsTable" class="dateline">
			<logic:notEmpty name="tjList">
			<thead>
			<logic:equal name="cftjlx" value="1">
				<tr align="center" style="coursor: hand">
				<td rowspan="2" width="4%" align="center">���</td>
				<td rowspan="2" width="7%" align="center">ѧ��</td>
				<td rowspan="2" width="10%" align="center">ѧ������</td>
				<%List list=(List)request.getAttribute("cflbsList");
				  int size=list.size();
				%>
				<td colspan="<%=size %>" align="center">Υ�ʹ���������˴Σ�</td>
				<td colspan="2" align="center">�ܴ�������</td>
				<td rowspan="2" align="center">��ע</td>
				</tr>
				<tr align="center" style="coursor: hand">
					<logic:iterate id="cflb" name="cflbsList">
						<td><bean:write name="cflb" property="mc"/></td>
					</logic:iterate>
					<td>�˴�</td>
					<td>����</td>
				</tr>
			</logic:equal>
			<logic:equal name="cftjlx" value="2">
				<tr align="center" style="coursor: hand">
				<td rowspan="2" width="4%" align="center">���</td>
				<td rowspan="2" width="8%" align="center">Ժϵ</td>
				<td rowspan="2" width="9%" align="center">ѧ������</td>
				<%List list=(List)request.getAttribute("cflbsList");
				  int size=list.size();
				%>
				<td colspan="<%=size %>" align="center">Υ�ʹ���������˴Σ�</td>
				<td colspan="2" align="center">�ܴ�������</td>
				<td rowspan="2" align="center">��ע</td>
				</tr>
				<tr align="center" style="coursor: hand">
					<logic:iterate id="cflb" name="cflbsList">
						<td><bean:write name="cflb" property="mc"/></td>
					</logic:iterate>
					<td>�˴�</td>
					<td>����</td>
				</tr>
			</logic:equal>
			<logic:equal name="cftjlx" value="3">
			<tr align="center" style="coursor: hand">
			<td width="4%" align="center">���</td>
			<td width="7%" align="center">ѧ��</td>
			<td width="6%" align="center">����</td>
			<td width="8%" align="center">�Ա�</td>
			<td align="center">Ժϵ</td>
			<td width="8%" align="center">�༶</td>
			<td width="8%" align="center">Υ��ʱ��</td>
			<td width="8%" align="center">���ĺ�</td>
			<td width="8%" align="center">����ʱ��</td>
			<td width="8%" align="center">��������</td>
			<td width="8%" align="center">��������</td>
			</tr>
			</logic:equal>
			</thead>			
			</logic:notEmpty>

			<logic:notEqual value="0" name="cftjlx">
			<%int i=1; %>
				<logic:iterate id="cftj" name="tjList">
					<tr>
						<td><%=i %></td>
						<%i=i+1; %>
						<logic:iterate id="v" name="cftj">
							<td>${v} </td>
						</logic:iterate>
						<logic:equal value="2" name="cftjlx">
						<td></td>
						</logic:equal>
					</tr>
				</logic:iterate>			
			</logic:notEqual>

			</table>
			</html:form>
			
  </body>
</html>
