<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@page import="java.util.List"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
		<script type='text/javascript' src='/xgxt/dwr/util.js'></script>
		<script type="text/javascript" src="js/qtsjFunction.js"></script>
		<script type="text/javascript" src="js/commit.js"></script>
		<script language="javascript">
		//查询结果集
		function searchRs(){
			allNotEmpThenGo('dtjs_dtxxgl_dtxxgl.do');
		}
		//同步更新政治面貌
		function tbgxzzmm(){
			confirmInfo("确定同步更新政治面貌信息吗？",function(ok){
					if("ok"==ok){
						allNotEmpThenGo('dtjs_dtxxgl_dtxxgl.do?doType=tbgxzzmm');
					}
				});
		}

		//入住
		function modi(url,h,w){
			if(curr_row != null){

				showDialog('学生党团信息修改', h, w, url + '&xh='+curr_row.getElementsByTagName('input')[0].value);
				//showTopWin(url + '&xh='+curr_row.getElementsByTagName('input')[0].value,h,w);
				return true;
			}else{
				alertInfo('请选择要操作的数据行');
				return false;
			}
		}
		
		function view(url,h,w){
			showDialog('学生党团信息查看', h, w, url + '&xh='+curr_row.getElementsByTagName('input')[0].value);
			//showTopWin(url + '&xh='+curr_row.getElementsByTagName('input')[0].value,h,w);
		}	

		/*
		数据导入
		*/	
		function impData(){
			var realTable = "";
			var tableName = "";
			var sty = "toolbar=no,location=no,directories=no,status=yes";
			sty += ",menubar=no,scrollbars=yes,resizable=yes,width=650,height=420,top=100";
			sty += ",left=200";
			if($("realTable")) realTable = document.getElementById("realTable").value;
			if($("tableName")) tableName = document.getElementById("tableName").value;
			url = 'dtjs_dtxxgl.do?method=importData';
			url += "&realTable=" + realTable;
			url += "&tableName=" + tableName;
			//showTopWin(url,600,500);
			//refreshForm(url);
			window.open(url,'',sty);
		}

		// 导出数据
		function expData() {
			document.forms[0].action = "dtjs_dtxxgl.do?method=exportData";
			document.forms[0].target = "_blank";
			document.forms[0].submit();
			document.forms[0].target = "_self";
		}
		
		function xsdtxxglExportConfig() {
			//DCCLBH导出功能编号,执行导出函数 
			customExport("dtjs_dtxxgl_xsdtxxgl.do", xsdtxxglExportData);
		}
			
		
			
		// 导出方法
		function xsdtxxglExportData() {
			setSearchTj();//设置高级查询条件
			var url = "dtjs_dtxxgl.do?method=xsdtxxglExportData&dcclbh=" + "dtjs_dtxxgl_xsdtxxgl.do";//dcclbh,导出功能编号
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
			</div>
			<div class="toolbox" id="dgncz">
				<!-- 按钮 -->
				<div class="buttonbox">
					<ul>
						<!-- 读写权 begin-->
							<logic:equal name="writeAble" value="yes">
						<li><a href="#" onclick="showDialog('学生党团信息增加', 800, 410,'dtjs_dtxxgl.do?method=dtxxAdd');return false;" class="btn_zj"> 增加 </a></li>
						<li><a href="#" onclick="modi('dtjs_dtxxgl.do?method=dtxxUpdate',750,380);return false;" class="btn_xg"> 修改 </a></li>
						<li><a href="#" onclick="batchData('primarykey_checkVal','dtjs_dtxxgl_dtxxgl.do?doType=del','del');return false;" class="btn_sc"> 删除 </a></li>
						<li><a href="#" class="btn_sx" onclick="tbgxzzmm();return false;">同步更新政治面貌</a></li>
						<li><a href="#" onclick="impData();return false;" class="btn_dr">导入数据</a></li>
						</logic:equal>
						<!-- 读写权 end-->
						<li><a href="#" onclick="expData();return false;" class="btn_dc">导出数据</a></li>
						<%--<li><a href="#" onclick="xsdtxxglExportConfig();return false;" class="btn_dc">导出</a></li>--%>
<!--						<li><a href="#" class="btn_qx" onclick="choiceFields();return false;">导出字段确认</a></li>-->
						
					</ul>
				</div>
				<!-- 过滤条件 -->
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- 过滤条件 end-->
			</div>
				<div class="formbox">
					<h3 class="datetitle_01">
						<span>
							查询结果&nbsp;&nbsp;<font color="blue">单击表头可以排序;双击显示学生党团建设基本信息</font>
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
											value="<logic:iterate id="v" name="s" offset="2" length="1">${v }</logic:iterate>" />
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
