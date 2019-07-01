<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@page import="java.util.List"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type='text/javascript' src="js/xgutil.js"></script>
		<script language="javascript" src="js/function.js"></script>
		<script type="text/javascript" src="js/qtsjFunction.js"></script>
		<script type="text/javascript" src="js/commit.js"></script>
		<script language="javascript">
		//查询结果集
		function searchRs(){
			allNotEmpThenGo('gyglnew_xszstj_xszstj.do');
		}

		function modi(url,h,w){
			if(curr_row != null){
				showDialog("查看学生住宿情况统计",h,w,url + '&nj='+curr_row.getElementsByTagName('input')[0].value+
						'&xydm='+curr_row.getElementsByTagName('input')[1].value);
				//showDialog('', 760, 525, 'gyglnew_qsgl.do?method=qswhAdd');
				return true;
			}else{
				alertInfo('请选择要操作的数据行！');
				return false;
			}
		}

		function getXsxx(obj,rzzt,xb){
			var url='gyglnew_xszstj.do?method=xszstjXsxx';
			var curr_row_input=obj.parentNode.parentNode.getElementsByTagName('input');
			var parame='&nj='+curr_row_input[0].value+'&xydm='+curr_row_input[1].value;
			parame+='&rzzt='+rzzt+'&xb='+xb;
			window.open(url+parame);
		}
		function getBjXsxx(obj,rzzt,xb){
			var url='gyglnew_xszstj.do?method=xszstjXsxx';
			var curr_row_input=obj.parentNode.parentNode.getElementsByTagName('input');
			var parame='&bjdm='+curr_row_input[0].value;
			parame+='&rzzt='+rzzt+'&xb='+xb;
			window.open(url+parame);
		}
		</script>
		
		<style type="text/css">
			#rsTable a:hover {color: blue!important;text-decoration: underline}	/* 鼠标移动到链接上 */
		</style>
	</head>
	<body>
		<html:form action="/gyglnew_xszstj" method="post">
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
						<li><a href="#" onclick="modi('gyglnew_xszstj.do?method=xszstjDetail',1024,525);return false;" class="btn_cx">查看详细信息</a></li>
<%--						<li><a href="#" onclick="toExcel('rsTable');return false;" class="btn_dc">导出报表</a></li>--%>
						<logic:equal name="writeAble" value="yes">
						<li><a href="#" class="btn_dc" onclick="setSearchTj();configureExportDataZdy('${path}');return false;">导出数据</a></li>
						</logic:equal>
						<li><a href="#" class="btn_dc" onclick="showDialog('', 400, 220, 'gyglnew_xszstj.do?method=qsfbbTj');return false;">寝室分布表</a></li>
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
					<div style="overflow-x:auto;">
					<logic:equal value="true" name="fdybzr">
						<table width="99%" id="rsTable" class="dateline">
							<thead align="center">
								<tr  align="center">
									<td rowspan="2">班级</td>
									<logic:equal value="11799" name="xxdm">
										<td colspan="4" align="center">总人数</td>
									</logic:equal>
									<logic:notEqual value="11799" name="xxdm">
										<td colspan="3" align="center">总人数</td>
		<%--								<td>住校人数</td>--%>
		<%--								<td>未住校人数</td>--%>
									</logic:notEqual>
									<logic:equal value="11799" name="xxdm">
										<td colspan="4">男生数</td>
									</logic:equal>
									<logic:notEqual value="11799" name="xxdm">
										<td colspan="3">男生数</td>
		<%--								<td>住校男生数</td>--%>
		<%--								<td>未住校男生数</td>--%>
									</logic:notEqual>
									<logic:equal value="11799" name="xxdm">
										<td colspan="4">女生数</td>
									</logic:equal>
									<logic:notEqual value="11799" name="xxdm">
										<td colspan="3">女生数</td>
		<%--								<td>住校女生数</td>--%>
		<%--								<td>未住校女生数</td>--%>
									</logic:notEqual>
								</tr>
								<tr  align="center">
									<td>总人数</td>
									<td>住校人数</td>
									<logic:equal value="11799" name="xxdm">
										<td>
											非走非住人数
										</td>
										<td>
											走读人数
										</td>										
									</logic:equal>
									<logic:notEqual value="11799" name="xxdm">
										<td>未住校人数</td>
									</logic:notEqual>
									<td>总人数</td>
									<td>住校人数</td>
									<logic:equal value="11799" name="xxdm">
										<td>
											非走非住人数
										</td>
										<td>
											走读人数
										</td>										
									</logic:equal>
									<logic:notEqual value="11799" name="xxdm">
										<td>未住校人数</td>
									</logic:notEqual>
									<td>总人数</td>
									<td>住校人数</td>
									<logic:equal value="11799" name="xxdm">
										<td>
											非走非住人数
										</td>
										<td>
											走读人数
										</td>										
									</logic:equal>
									<logic:notEqual value="11799" name="xxdm">
										<td>未住校人数</td>
									</logic:notEqual>
								</tr>
							</thead>
							<logic:empty name="rs">
							<logic:equal value="11799" name="xxdm">
								<%
								for(int i=0; i<13; i++){
								%>
								<tr>
									<td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td>
									<td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td>
							 	</tr>
								<%}%>
							</logic:equal>
							<logic:notEqual value="11799" name="xxdm">								
							  <%
								for(int i=0; i<11; i++){
								%>
								<tr>
									<td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td>
									<td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td>
							 	</tr>
								<%}%>
							</logic:notEqual>
							 </logic:empty>
							<logic:notEmpty name="rs">
								<logic:iterate name="rs" id="map">
									<tr onclick="rowOnClick(this);" ondblclick="modi('gyglnew_xszstj.do?method=xszstjDetail',1024,500);" style="cursor:hand">
										<td><input type="hidden" value="${map.bjdm }"/>${map.bjmc }</td>
	<%--									<td>${map.zrs }</td>--%>
	<%--									<td>${map.zxrs }<br/>${map.zxrsbfb}%</td>--%>
	<%--									<td>${map.wzxrs }<br/>${map.wzxrsbfb}%</td>--%>
	<%--									<td>${map.mrs }<br/>${map.mrsbfb}%</td>--%>
	<%--									<td>${map.zxmrs }<br/>${map.zxmrsbfb}%</td>--%>
	<%--									<td>${map.wzxmrs }<br/>${map.wzxmrsbfb}%</td>--%>
	<%--									<td>${map.wrs }<br/>${map.wrsbfb}%</td>--%>
	<%--									<td>${map.zxwrs }<br/>${map.zxwrsbfb}%</td>--%>
	<%--									<td>${map.wzxwrs }<br/>${map.wzxwrsbfb}%</td>--%>
										<td><a href="#" onclick="getBjXsxx(this,'','')">${map.zrs }</a></td>
										<td><a href="#" onclick="getBjXsxx(this,'yes','')">${map.zxrs }<br/>${map.zxrsbfb}%</a></td>
										
										<logic:equal value="11799" name="xxdm">
											<td><a href="#" onclick="getBjXsxx(this,'no','')">${map.fzfzrs }<br/>${map.fzfzrsbfb }%</a></td>
											<td><a href="#" onclick="getBjXsxx(this,'no','')">${map.zdrs }<br/>${map.zdrsbfb }%</a></td>		
										</logic:equal>
										
										<logic:notEqual value="11799" name="xxdm">										
											<td><a href="#" onclick="getBjXsxx(this,'no','')">${map.wzxrs }<br/>${map.wzxrsbfb}%</a></td>
										</logic:notEqual>
										
										<td><a href="#" onclick="getBjXsxx(this,'','m')">${map.mrs}<br/>${map.mrsbfb}%</a></td>
										<td><a href="#" onclick="getBjXsxx(this,'yes','m')">${map.zxmrs}<br/>${map.zxmrsbfb}%</a></td>
										
										<logic:equal value="11799" name="xxdm">
											<td><a href="#" onclick="getBjXsxx(this,'no','m')">${map.fzfzmrs}<br/>${map.fzfzmrsbfb}%</a></td>
											<td><a href="#" onclick="getBjXsxx(this,'no','m')">${map.zdmrs}<br/>${map.zdmrsbfb}%</a></td>
										</logic:equal>
										
										<logic:notEqual value="11799" name="xxdm">										
											<td><a href="#" onclick="getBjXsxx(this,'no','m')">${map.wzxmrs }<br/>${map.wzxmrsbfb}%</a></td>
										</logic:notEqual>
										
										<td><a href="#" onclick="getBjXsxx(this,'','w')">${map.wrs}<br/>${map.wrsbfb}%</a></td>
										<td><a href="#" onclick="getBjXsxx(this,'yes','w')">${map.zxwrs}<br/>${map.zxwrsbfb}%</a></td>
										
										<logic:equal value="11799" name="xxdm">
											<td><a href="#" onclick="getBjXsxx(this,'no','w')">${map.fzfzwrs}<br/>${map.fzfzwrsbfb}%</a></td>
											<td><a href="#" onclick="getBjXsxx(this,'no','w')">${map.zdwrs}<br/>${map.zdwrsbfb}%</a></td>
										</logic:equal>
										
										<logic:notEqual value="11799" name="xxdm">
											<td><a href="#" onclick="getBjXsxx(this,'no','w')">${map.wzxwrs }<br/>${map.wzxwrsbfb}%</a></td>
										</logic:notEqual>
									</tr>
								</logic:iterate>
						</logic:notEmpty>
					</table>
					</logic:equal>
					<logic:notEqual value="true" name="fdybzr">
						<table width="99%" id="rsTable" class="dateline">
							<thead align="center">
								<tr  align="center">
									<td rowspan="2"><bean:message key="lable.xsgzyxpzxy" /></td>
									<td rowspan="2">年级</td>
									<logic:equal value="11799" name="xxdm">
										<td colspan="4" align="center">总人数</td>
									</logic:equal>
									<logic:notEqual value="11799" name="xxdm">
										<td colspan="3" align="center">总人数</td>
		<%--								<td>住校人数</td>--%>
		<%--								<td>未住校人数</td>--%>
									</logic:notEqual>
									<logic:equal value="11799" name="xxdm">
										<td colspan="4">男生数</td>
									</logic:equal>
									<logic:notEqual value="11799" name="xxdm">
										<td colspan="3">男生数</td>
		<%--								<td>住校男生数</td>--%>
		<%--								<td>未住校男生数</td>--%>
									</logic:notEqual>
									<logic:equal value="11799" name="xxdm">
										<td colspan="4">女生数</td>
									</logic:equal>
									<logic:notEqual value="11799" name="xxdm">
										<td colspan="3">女生数</td>
		<%--								<td>住校女生数</td>--%>
		<%--								<td>未住校女生数</td>--%>
									</logic:notEqual>
								</tr>
								<tr  align="center">
									<td>总人数</td>
									<td>住校人数</td>
									<logic:equal value="11799" name="xxdm">
										<td>
											非走非住人数
										</td>
										<td>
											走读人数
										</td>										
									</logic:equal>
									<logic:notEqual value="11799" name="xxdm">
										<td>未住校人数</td>
									</logic:notEqual>
									<td>总人数</td>
									<td>住校人数</td>
									<logic:equal value="11799" name="xxdm">
										<td>
											非走非住人数
										</td>
										<td>
											走读人数
										</td>										
									</logic:equal>
									<logic:notEqual value="11799" name="xxdm">
										<td>未住校人数</td>
									</logic:notEqual>
									<td>总人数</td>
									<td>住校人数</td>
									<logic:equal value="11799" name="xxdm">
										<td>
											非走非住人数
										</td>
										<td>
											走读人数
										</td>										
									</logic:equal>
									<logic:notEqual value="11799" name="xxdm">
										<td>未住校人数</td>
									</logic:notEqual>
								</tr>
							</thead>
							<logic:empty name="rs">
								<logic:equal value="11799" name="xxdm">
									<%
									for(int i=0; i<13; i++){
									%>
									<tr>
										<td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td>
										<td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td>
								 	</tr>
									<%}%>
								</logic:equal>
								<logic:notEqual value="11799" name="xxdm">
								  	<%
									for(int i=0; i<11; i++){
									%>
									<tr>
										<td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td>
										<td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td>
								 	</tr>
									<%}%>
								</logic:notEqual>
							 </logic:empty>
							<logic:notEmpty name="rs">
								<logic:iterate name="rs" id="map">
									<tr onclick="rowOnClick(this);" ondblclick="modi('gyglnew_xszstj.do?method=xszstjDetail',1024,500);" style="cursor:hand">
										<td><input type="hidden" value="${map.nj }"/>
										    <input type="hidden" value="${map.xydm }"/>${map.xymc }</td>
										<td>${map.nj }</td>
	<%--									<td>${map.zrs }</td>--%>
	<%--									<td>${map.zxrs }<br/>${map.zxrsbfb}%</td>--%>
	<%--									<td>${map.wzxrs }<br/>${map.wzxrsbfb}%</td>--%>
	<%--									<td>${map.mrs }<br/>${map.mrsbfb}%</td>--%>
	<%--									<td>${map.zxmrs }<br/>${map.zxmrsbfb}%</td>--%>
	<%--									<td>${map.wzxmrs }<br/>${map.wzxmrsbfb}%</td>--%>
	<%--									<td>${map.wrs }<br/>${map.wrsbfb}%</td>--%>
	<%--									<td>${map.zxwrs }<br/>${map.zxwrsbfb}%</td>--%>
	<%--									<td>${map.wzxwrs }<br/>${map.wzxwrsbfb}%</td>--%>
										<td><a href="#" onclick="getXsxx(this,'','')">${map.zrs }</a></td>
										<td><a href="#" onclick="getXsxx(this,'yes','')">${map.zxrs }<br/>${map.zxrsbfb}%</a></td>
										<!-- 重庆工商大学个性化 -->
										<logic:equal value="11799" name="xxdm">
											<td><a href="#" onclick="getXsxx(this,'no','')">${map.fzfzrs }<br/>${map.fzfzrsbfb }%</a></td>
											<td><a href="#" onclick="getXsxx(this,'no','')">${map.zdrs }<br/>${map.zdrsbfb }%</a></td>		
										</logic:equal>
										
										<logic:notEqual value="11799" name="xxdm">		
											<td><a href="#" onclick="getXsxx(this,'no','')">${map.wzxrs }<br/>${map.wzxrsbfb}%</a></td>
										</logic:notEqual>
										
										<td><a href="#" onclick="getXsxx(this,'','m')">${map.mrs }<br/>${map.mrsbfb}%</a></td>
										<td><a href="#" onclick="getXsxx(this,'yes','m')">${map.zxmrs }<br/>${map.zxmrsbfb}%</a></td>
										
										<logic:equal value="11799" name="xxdm">
											<td><a href="#" onclick="getXsxx(this,'no','m')">${map.fzfzmrs}<br/>${map.fzfzmrsbfb}%</a></td>
											<td><a href="#" onclick="getXsxx(this,'no','m')">${map.zdmrs}<br/>${map.zdmrsbfb}%</a></td>
										</logic:equal>
										
										<logic:notEqual value="11799" name="xxdm">
											<td><a href="#" onclick="getXsxx(this,'no','m')">${map.wzxmrs }<br/>${map.wzxmrsbfb}%</a></td>
										</logic:notEqual>
										
										<td><a href="#" onclick="getXsxx(this,'','w')">${map.wrs }<br/>${map.wrsbfb}%</a></td>
										<td><a href="#" onclick="getXsxx(this,'yes','w')">${map.zxwrs }<br/>${map.zxwrsbfb}%</a></td>
										
										<logic:equal value="11799" name="xxdm">
											<td><a href="#" onclick="getXsxx(this,'no','w')">${map.fzfzwrs}<br/>${map.fzfzwrsbfb}%</a></td>
											<td><a href="#" onclick="getXsxx(this,'no','w')">${map.zdwrs}<br/>${map.zdwrsbfb}%</a></td>
										</logic:equal>
										
										<logic:notEqual value="11799" name="xxdm">
											<td><a href="#" onclick="getXsxx(this,'no','w')">${map.wzxwrs }<br/>${map.wzxwrsbfb}%</a></td>
										</logic:notEqual>
									</tr>
								</logic:iterate>
						</logic:notEmpty>
					</table>
				</logic:notEqual>
			</div>
					<!--分页显示-->
			   	 	<jsp:include flush="true" page="/sjcz/turnpage.jsp?form=gyglnewXszstjForm"></jsp:include>
					<!--分页显示-->
			</div>
		</html:form>
		
		<%@ include file="/comm/delMessage.jsp"%>
		<%@ include file="/comm/loading.jsp"%>
	</body>
</html>
