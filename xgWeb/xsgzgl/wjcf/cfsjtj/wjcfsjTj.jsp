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
		<!-- 标题 -->
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>${title }</a>
			</p>
		</div>


		<html:form action="/wjcfCfshwh_cfsjwh" method="post">

			<!-- 隐藏域 -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<!-- 隐藏域 -->
			<input type="hidden" name="xmdm" id="xxmmdm" value="${xmdm }" />

			<!-- 多功能操作区 -->
			<div class="toolbox" id="dgncz">
				<!-- 按钮 -->
				
				<div class="buttonbox">
					<ul>
						<li><a href="#" class="btn_dc" onclick="exportData();return false;">导出数据</a></li>
					</ul>
				</div>
			<div class="buttonbox">
			当前学年：<html:select name="wjcfCfsjtjActionForm" property="xn" style="width:100px">
					<html:options collection="xnList" property="tjxn" labelProperty="tjxn"/>
				</html:select>
				<input type="hidden" name="dqxn" id="dqxn" value="${dqxn}" />
			统计类型：&nbsp;<html:select name="wjcfCfsjtjActionForm" property="cftjlx" style="width:100px" onchange="aaa();">
				<!--<html:option value="1">按照学年统计</html:option>-->
				<html:option value="2">按照<bean:message key="lable.xb" />统计</html:option>
				<html:option value="3">学年一览</html:option>
			</html:select>
			<!--开始学年：<html:select name="wjcfCfsjtjActionForm" property="ksxn" style="width:100px" styleId="ksxn">
					<html:options collection="xnList" property="tjxn" labelProperty="tjxn"/>
				</html:select>
			结束学年：<html:select name="wjcfCfsjtjActionForm" property="jsxn" style="width:100px" styleId="jsxn">
					<html:options collection="xnList" property="tjxn" labelProperty="tjxn"/>
				</html:select>-->
			<input type="hidden" name="cftjlx" id="cftjlx" value="${cftjlx}" />
			<input type="hidden" name="ksxn" id="ksxn" value="${ksxn}" />
			<input type="hidden" name="jsxn" id="jsxn" value="${jsxn}" />
			<input type="hidden" name="doType" id="doType" value="" />


			<button type="button"  class="" style="height: 23px" id="" onclick="tj();return false;" >
				统计查询
			</button>
			
			</div>

			</div>
			<h3 class="datetitle_01">
					<span> 查询结果&nbsp;&nbsp;  </span>
			</h3>
			<table width="99%" id="rsTable" class="dateline">
			<logic:notEmpty name="tjList">
			<thead>
			<logic:equal name="cftjlx" value="1">
				<tr align="center" style="coursor: hand">
				<td rowspan="2" width="4%" align="center">序号</td>
				<td rowspan="2" width="7%" align="center">学年</td>
				<td rowspan="2" width="10%" align="center">学生总数</td>
				<%List list=(List)request.getAttribute("cflbsList");
				  int size=list.size();
				%>
				<td colspan="<%=size %>" align="center">违纪处分情况（人次）</td>
				<td colspan="2" align="center">受处分总数</td>
				<td rowspan="2" align="center">备注</td>
				</tr>
				<tr align="center" style="coursor: hand">
					<logic:iterate id="cflb" name="cflbsList">
						<td><bean:write name="cflb" property="mc"/></td>
					</logic:iterate>
					<td>人次</td>
					<td>比例</td>
				</tr>
			</logic:equal>
			<logic:equal name="cftjlx" value="2">
				<tr align="center" style="coursor: hand">
				<td rowspan="2" width="4%" align="center">序号</td>
				<td rowspan="2" width="8%" align="center">院系</td>
				<td rowspan="2" width="9%" align="center">学生总数</td>
				<%List list=(List)request.getAttribute("cflbsList");
				  int size=list.size();
				%>
				<td colspan="<%=size %>" align="center">违纪处分情况（人次）</td>
				<td colspan="2" align="center">受处分总数</td>
				<td rowspan="2" align="center">备注</td>
				</tr>
				<tr align="center" style="coursor: hand">
					<logic:iterate id="cflb" name="cflbsList">
						<td><bean:write name="cflb" property="mc"/></td>
					</logic:iterate>
					<td>人次</td>
					<td>比例</td>
				</tr>
			</logic:equal>
			<logic:equal name="cftjlx" value="3">
			<tr align="center" style="coursor: hand">
			<td width="4%" align="center">序号</td>
			<td width="7%" align="center">学号</td>
			<td width="6%" align="center">姓名</td>
			<td width="8%" align="center">性别</td>
			<td align="center">院系</td>
			<td width="8%" align="center">班级</td>
			<td width="8%" align="center">违纪时间</td>
			<td width="8%" align="center">发文号</td>
			<td width="8%" align="center">发文时间</td>
			<td width="8%" align="center">处分类型</td>
			<td width="8%" align="center">处分理由</td>
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
