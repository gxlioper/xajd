<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@page import="java.util.List"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>

	<%--<%@ include file="/syscommon/pagehead_V4.ini"%>--%>
		<%@ include file="/syscommon/head.ini"%>
		<script language="javascript" src="js/function.js"></script>
		<script type="text/javascript" src="js/qtsjFunction.js"></script>
		<script type="text/javascript" src="js/commit.js"></script>
		<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
		<script type='text/javascript' src='/xgxt/dwr/util.js'></script>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script language="javascript">
		//查询结果集
		function searchRs(){
			allNotEmpThenGo('dtjs_dtxxgl_dtxxcx.do');
		}

		// 导出数据
		function expData() {
			document.forms[0].action = "dtjs_dtxxgl.do?method=exportData2";
			document.forms[0].target = "_blank";
			document.forms[0].submit();
			document.forms[0].target = "_self";
		}

		function view(url,h,w){
			//showTopWin(url + '&xh='+curr_row.getElementsByTagName('input')[0].value,h,w);
			showDialog('学生党团信息查看',h,w,url + '&xh='+curr_row.getElementsByTagName('input')[0].value);
		}
		
		function xsdtxxcxExportConfig() {
			//DCCLBH导出功能编号,执行导出函数 
			customExport("dtjs_dtxxgl_xsdtxxcx.do", xsdtxxcxExportData);
		}
			
		
			
		// 导出方法
		function xsdtxxcxExportData() {
			setSearchTj();//设置高级查询条件
			var url = "dtjs_dtxxgl.do?method=xsdtxxcxExportData&dcclbh=" + "dtjs_dtxxgl_xsdtxxcx.do";//dcclbh,导出功能编号
			url = addSuperSearchParams(url);//设置高级查询参数
			jQuery("form").eq(0).attr("action", url);
			jQuery("form").eq(0).submit();
		}
		</script>
	</head>
	<body>
		<html:form action="/dtjs_dtxxgl" method="post">
			<!-- 隐藏域 -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<!-- 隐藏域 -->
			<div class="tab_cur" >
				<p class="location">
					<em>您的当前位置:</em><a>${title }</a>
				</p>
				<p class="help">
					<a href="#" onclick="return false;" onmousedown ="showHelpDiv();">使用帮助</a>
				</p>
			</div>
			<!-- 提示信息 end-->
			<div class="prompt" id="div_help" style="display: none">
				<h3>
					<span>提示：</span>
				</h3>
				<p>
					学生当前阶段的党团建设信息不可删除
				</p>
				<a class="close" title="隐藏"
				   onclick="this.parentNode.style.display='none';"></a>
			</div>
			<!-- 提示信息 end-->	
			<div class="toolbox" id="dgncz">
				<!-- 按钮 -->
				<div class="buttonbox">
					<ul>
						<!-- 读写权 begin-->
						<logic:equal name="writeAble" value="yes">
						<li><a href="#" onclick="batchData('primarykey_checkVal','dtjs_dtxxgl_dtxxcx.do?doType=del','del');return false;" class="btn_sc"> 删除 </a></li>
						</logic:equal>
						<!-- 读写权 end-->
						<li><a href="#" onclick="xsdtxxcxExportConfig();return false;" class="btn_dc">导出 </a></li>
						<%--<li><a href="#" onclick="setSearchTj();configureExportDataZdy('${path}');return false;" class="btn_dc">导出数据</a></li>
					--%></ul>
				</div>
				<!-- 过滤条件 -->
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- 过滤条件 end-->
			</div>
				<div class="formbox">
					<h3 class="datetitle_01">
						<span>
							查询结果&nbsp;&nbsp;<font color="blue">单击表头可以排序;</font>
						</span>
					</h3>
					<table width="99%" id="rsTable" class="dateline">
						<thead>
							<tr align="center" style="cursor:hand">
								<td>
									<input type="checkbox" name="all" value="all" onclick="chec()"/>
								</td>
								<logic:iterate id="tit" name="topTr" offset="0">
									<td id="${tit }" onclick="tableSort(this)">
										${tit }
									</td>
								</logic:iterate>
							</tr>
						</thead>
 						<logic:empty name="rs">
						  <%
							for(int i=0; i<11; i++){
							%>
							<tr>
								<td>
									<input type="checkbox" name="pk" value="" disabled="disabled"></input>
								</td>
								<logic:iterate id="tit" name="topTr" offset="0">
									<td>
										&nbsp;
									</td>
								</logic:iterate>	
						 	</tr>
							<%}%> 
						 </logic:empty>
						<logic:notEmpty name="rs">
							<logic:iterate name="rs" id="s">
								<tr onclick="rowOnClick(this);" style="cursor:hand" 
								ondblclick="view('dtjs_dtxxgl.do?method=dtxxUpdate&act=view',750,400);">
									<td>								
										<input type="checkbox" name="primarykey_checkVal" id="pkV"
											value="<logic:iterate id="v" name="s" offset="2" length="1">${v }</logic:iterate>" 
											<logic:iterate id="v" name="s" offset="1" length="1">${v }</logic:iterate>/>
									</td>
									<logic:iterate id="v" name="s" offset="2">
										<td>${v }</td>
									</logic:iterate>
								</tr>
							</logic:iterate>
							<%
							int rsNum = ((List)request.getAttribute("rs")).size();
							int pageSize = (Integer)(request.getAttribute("pageSize"));
							if(rsNum < pageSize){
								for(int i=0; i<(pageSize-rsNum); i++){
							%>
							<tr>
								<td>
									<input type="checkbox" name="primarykey_cbv" value="" disabled="disabled"></input>
								</td>
								<logic:iterate id="tit" name="topTr" offset="0">
									<td>
										&nbsp;
									</td>
								</logic:iterate>
						 	</tr>
							<%}}%>
					</logic:notEmpty>
				</table>
				<!--分页显示-->
		   	 	<jsp:include flush="true" page="/sjcz/turnpage.jsp?form=dtjsDtxxglForm"></jsp:include>
				<!--分页显示-->
			</div>

		</html:form>
		<logic:present name="message">
			<script>
				alertInfo('${message}');
			</script>
		</logic:present>
	</body>
</html>
