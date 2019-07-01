<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@page import="java.util.List"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- 作者：zhangh -->
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script language="javascript" src="dwr/interface/getCommGygl.js"></script>
		<script type="text/javascript" src="js/commit.js"></script>
		<script type='text/javascript' src='/xgxt/dwr/util.js'></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type='text/javascript' src="js/uicomm.js"></script>
		<script language="javascript" defer="defer">
		//查询结果集
		function searchRs(){
			allNotEmpThenGo('gyglnew_gybxgl_gybxgl.do');
		}

		function update(url){
			if(curr_row != null){
				url += "&pk="+curr_row.getElementsByTagName('input')[0].value;
				url += "&xh="+curr_row.getElementsByTagName('input')[1].value;
				//showTopWin(url,800,600);
				showDialog('', 800, 500, url);
				return true;
			}else{
				alertInfo('请选择要操作的数据行！');
				return false;
			}
		}
		function gybxglExportConfig() {
			//DCCLBH导出功能编号,执行导出函数 
			customExport("gyglnew_gybxgl_gybxgl.do", gybxglExportData);
			}
				
			
				
		// 导出方法
		function gybxglExportData() {
			setSearchTj();//设置高级查询条件
			var url = "gyglnew_gybxgl.do?method=gybxglExportData&dcclbh=" + "gyglnew_gybxgl_gybxgl.do";//dcclbh,导出功能编号
			url = addSuperSearchParams(url);//设置高级查询参数
			jQuery("form").eq(0).attr("action", url);
			jQuery("form").eq(0).submit();
		}
		</script>
	</head>
	<body>
		<!-- 标题 -->
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>${title }</a>
			</p>
		</div>
		<!-- 标题 end-->
		
		
		<html:form action="/gyglnew_gybxgl">
			<!-- 隐藏域 -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<!-- 隐藏域 -->

			<!-- 多功能操作区 -->
			<div class="toolbox">
				<!-- 按钮 -->
				<div class="buttonbox">
					<ul>
						<logic:equal value="yes" name="writeAble">						
						<li><a href="#" onclick="update('gyglnew_gybxgl.do?method=gybxglUpdate','update');return false;" class="btn_sh">处理 </a></li>
						<li><a href="#" onclick="batchData('primarykey_checkVal','gyglnew_gybxgl.do?method=gybxglManage&doType=del','del');return false;" class="btn_sc"> 删除 </a></li>
						</logic:equal>
						<%--<li><a href="#" onclick="setSearchTj();configureExportDataZdy('${path}');return false;" class="btn_dc">导出数据</a></li>
					--%>
						<li><a href="#" class="btn_dc" onclick="gybxglExportConfig();return false;">导出</a></li>
					</ul>
				</div>
				<!-- 按钮 end-->	
				
				<!-- 过滤条件 -->
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- 过滤条件 end-->
				
			</div>
			<!-- 多功能操作区 end -->
			
			<!-- 查询结果-->
			<div class="formbox">		
				<h3 class="datetitle_01">
					<span>
						查询结果
						<logic:empty name="rsArrList">
							&nbsp;&nbsp;<font color="red">未找到任何记录！</font> 
						</logic:empty>
						<logic:notEmpty name="rsArrList">
							&nbsp;&nbsp;
							<font color="blue">
							
							</font> 
						</logic:notEmpty>
					</span>
				</h3>				
				<div class="con_overlfow" >
				<table summary="" class="dateline" width="100%">
					<!-- 表头 -->
					<thead>
						<tr align="center" style="cursor:hand">
							<td width="5px">
								<input type="checkbox" id="selall" name="selall" onclick="selAll()" />
							</td>
							<logic:iterate id="tit" name="topTr" offset="0" >
								<td id="<bean:write name="tit" property="en"/>"
									onclick="tableSort(this)">
									<bean:write name="tit" property="cn" />
								</td>
							</logic:iterate>
						</tr>
					</thead>
					<!-- 表头 end-->
					<!--内容 -->
					<logic:iterate name="rsArrList" id="s" indexId="index">
						<tr onclick="rowOnClick(this);" ondblclick="update('gyglnew_gybxgl.do?method=gybxglView')" style="cursor:hand">
							<td align="center" width="5px">
								<logic:iterate id="v" name="s" offset="0" length="1">
									<input type="checkbox" id="pk_${index }" name="primarykey_checkVal" value="${v }"/>
								</logic:iterate>
								
								<logic:iterate id="v" name="s" offset="4" length="1">
									<input type="hidden" value="${v }"/>
								</logic:iterate>
							</td>
							<!-- 显示信息 -->
							<logic:iterate id="v" name="s" offset="1">
								<td align="left">
									${v }
								</td>
							</logic:iterate>
						</tr>
					</logic:iterate>
					<%
					int rsNum = ((List)request.getAttribute("rsArrList")).size();
					int pageSize = (Integer)(request.getAttribute("pageSize"));
					if(rsNum < pageSize){
						for(int i=0; i<(pageSize-rsNum); i++){
					%>
					<tr>
						<td>
							<input type="checkbox" disabled="disabled"></input>
						</td>
						<logic:iterate id="tit" name="topTr" offset="0">
							<td>
								&nbsp;
							</td>
						</logic:iterate>
				 	</tr>
					<%}}%>
				</table>
				<!--分页-->
				<jsp:include flush="true" page="/sjcz/turnpage.jsp?form=gyglnewGybxglForm"></jsp:include>
				<!--分页end-->
				</div>
			</div>
			<!-- 查询结果 end-->
			
		</html:form>
	</body>
</html>