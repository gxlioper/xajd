<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@page import="java.util.List"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
		<script type='text/javascript' src='/xgxt/dwr/util.js'></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type='text/javascript' src="js/uicomm.js"></script>
		<script type='text/javascript' src="js/String.js"></script>
		
		<script language="javascript" src="js/function.js"></script>
		<script type="text/javascript" src="js/qtsjFunction.js"></script>
		<script type="text/javascript" src="js/commit.js"></script>
		<script language="javascript">
		//查询结果集
		function searchRs(){
			allNotEmpThenGo('dtxxTjbase.do');
		}

		//打印excel
		function getExcel(){
			var url="dtxxTj.do?method=downloadMultiExcel";
	 		window.open(url);			
		}
		
		</script>
	</head>
	<body>
		<html:form action="/dtxxTj" method="post">
			<!-- 隐藏域 -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<!-- 隐藏域 -->
			<div class="tab_cur" >
				<p class="location">
					<em>您的当前位置:</em><a>${title }</a>
				</p>
				<p class="help">
					<a href="#" onclick="return false;" onmousedown ="showHelpDiv()">使用帮助</a>
				</p>
			</div>
			<div  id="div_help" class="prompt">
			<h3>
				<span>提示：</span>
			</h3>
			<p>
				<span>
					默认显示当前年度（<font color="red">${dqnd}年度</font>）的党团信息
				</span>
			</p>
			<a class="close" title="隐藏"
				onclick="this.parentNode.style.display='none';"></a>
			</div>
			
			<div class="toolbox">
				<!-- 按钮 -->
				<div class="buttonbox">
					<ul>
						<li><a href="javascript:void(0);" onclick="getExcel();return false;" class="btn_down">党团发展备忘录</a></li>
					</ul>									
				</div>
				<!-- 过滤条件 -->
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- 过滤条件 end-->
			</div>
			<div class="formbox" style="height: 768px">
				<h3 class="datetitle_01">
					<span>
						查询结果&nbsp;&nbsp;<logic:empty name="rs"><font color="red">没有相关统计信息！</font></logic:empty>
					</span>
				</h3>
				<table width="99%" id="rsTable" class="dateline">
					<thead>
						<tr align="center">
							<td width="10%"></td>
							<td width="13%" colspan="2">入党积极分子</td>
							<td width="10%" colspan="2">发展对象</td>
							<td width="35%" colspan="3">提交预审发展对象（拟吸收为预备党员）</td>
							<td width="16%" colspan="3">确定为预备党员</td>
							<td width="17%" colspan="2">预备转正</td>
						</tr>
						<tr align="center" style="cursor:hand">
							<logic:iterate id="tit" name="topTr" offset="0">
								<td id="${tit }">
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
								<logic:iterate id="tit" name="topTr" offset="0">
									<td>
										&nbsp;
									</td>
								</logic:iterate>	
						 	</tr>
							<%}%>
						 </logic:empty>
						<logic:notEmpty name="rs">
							<logic:iterate name="rs" id="map">
								<tr>
									<td>
										<input type="hidden" name="xydm" value="${map.xydm }"/>
										${map.xymc }
									</td>
									<td>&nbsp;</td>
									<td>${map.rdjjfzxm }</td>
									
									<td>&nbsp;</td>
									<td>${map.fzdxxm }</td>
									
									<td>&nbsp;</td>
									<td>${map.ysfzdxxm }</td>
									<td>&nbsp;</td>
									
									<td>&nbsp;</td>
									<td>${map.ybdyxm }</td>
									<td>&nbsp;</td>
									
									<td>&nbsp;</td>
									<td>${map.zsdyxm }</td>
								</tr>
							</logic:iterate>
					</logic:notEmpty>
				</table>
				<!--分页显示-->
			   	 	<jsp:include flush="true" page="/sjcz/turnpage.jsp?form=dtxxTjForm"></jsp:include>
				<!--分页显示-->
			</div>
		</html:form>
		
		<%@ include file="/comm/delMessage.jsp"%>
		<%@ include file="/comm/loading.jsp"%>
	</body>
</html>
