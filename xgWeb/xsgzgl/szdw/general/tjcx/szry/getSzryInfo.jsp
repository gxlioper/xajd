<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%--<%@ include file="/syscommon/pagehead_V4.ini"%>
		 --%>
		 <%@ include file="/syscommon/head.ini"%>
		<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
		<script type='text/javascript' src='/xgxt/dwr/util.js'></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type='text/javascript' src="js/uicomm.js"></script>
		<script type='text/javascript' src="js/String.js"></script>
		<script type='text/javascript' src="js/xgutil.js"></script>
		<script type='text/javascript' src='/xgxt/dwr/engine.js'></script> 
		<script type='text/javascript' src='/xgxt/dwr/interface/exportData.js'></script>
		<script language="javascript" defer="defer">
		function exportSzryInfo(){
			var url="general_szdw.do?method=exportSzryInfo&rand=<%=System.currentTimeMillis() %>";
			document.forms[0].action=url;
			document.forms[0].target="_blank";
			document.forms[0].submit();
			document.forms[0].target = "_self";	
		}
		
		</script>
	</head>
	<body>
		<html:form action="/general_szdw" method="post">
			<html:hidden property="jryx"/>
			<html:hidden property="bmdm"/>
			<html:hidden property="xb"/>
			<html:hidden property="fdydb"/>
			<html:hidden property="bzrdb"/>
			
			<button type="button" style="display:none;" id="search_go" 
					onclick="allNotEmpThenGo('general_szdw.do?method=getSzryInfo')">
			</button>
			
			<!-- 多功能操作区 -->
			<div class="toolbox">
				<!-- 过滤条件 -->	
				
				<!-- 过滤条件 end-->
			</div>
			
			<div class="formbox" style="overflow-x:hidden;overflow-y:auto;"> 
				<h3 class="datetitle_01">
					<span> 查询结果&nbsp;&nbsp;</span>
				</h3>
				<table class="dateline tablenowrap" width="100%">
					<thead>
						<tr>
							<th>职工号</th>
							<th>姓名</th>
							<th>部门代码</th>
							<th>部门名称</th>
							<th>联系电话</th>
						</tr>
					</thead>
					<tbody>
						<logic:iterate id="b" name="szryList">
							<tr>
								<td>${b.zgh }</td>
								<td>${b.xm }</td>
								<td>${b.bmdm }</td>
								<td>${b.bmmc }</td>
								<td>${b.lxdh }</td>
							</tr>
						</logic:iterate>
					</tbody>
				</table>
			</div>
			<jsp:include flush="true" page="/sjcz/turnpage.jsp?form=generalSzdwGeneralForm"></jsp:include>
			<script type="text/javascript">
				$('choose').className="hide";
			</script>
			<br/><br/>
			<table class="dateline tablenowrap" width="100%">
				<tfoot>
					<tr>
					<td colspan="4" align="right">
						<button type="button"  onclick="exportSzryInfo();">导出</button>
						<button type="button"  onclick="iFClose();return false;">关闭</button>
					</td>
					</tr>
				</tfoot>
			</table>
		</html:form>
	</body>
</html>