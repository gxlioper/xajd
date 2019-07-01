<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@page import="java.util.List"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/function.js"></script>
		<script type="text/javascript" src="js/qtsjFunction.js"></script>
		<script type="text/javascript" src="js/commit.js"></script>
		<script type="text/javascript">
		function getXsxx(obj,rzzt,xb){
			var url='gyglnew_xszstj.do?method=xszstjXsxx';
			var curr_row_input=obj.parentNode.parentNode.getElementsByTagName('input');
			var parame='&nj='+curr_row_input[0].value+'&xydm='+curr_row_input[1].value+'&bjdm='+curr_row_input[2].value;
			parame+='&rzzt='+rzzt+'&xb='+xb;
			window.open(url+parame);
		}
		</script>
	</head>
	<body>
		<html:form action="/gyglnew_xszstj" method="post">
			<html:hidden property="nj"/>
			<html:hidden property="xydm"/>
			<div class="tab_cur" >
				<p class="location">
					<em>您的当前位置:</em><a>班级住宿情况统计</a>
				</p>
			</div>
			
			<div class="toolbox" id="dgncz">
				<!-- 按钮 -->
				<div class="buttonbox">
					<ul>
<%--						<li><a href="#" onclick="toExcel('rsTable');return false;" class="btn_dc">导出报表</a></li>--%>
						<li><a href="#" class="btn_dc" onclick="configureExportDataZdy('${path}');return false;">导出数据</a></li>
					</ul>
				</div>
			</div>
				<div class="formbox" style="height:688px">
					<h3 class="datetitle_01">
						<span>
							查询结果&nbsp;&nbsp;<logic:empty name="rs"><font color="red">没有相关统计信息！</font></logic:empty>
						</span>
					</h3>
					<table width="99%" id="rsTable" class="formlist">
						<thead>
							<tr  align="center">
								<td rowspan="2"><bean:message key="lable.xsgzyxpzxy" /></td>
								<td rowspan="2">年级</td>
								<td rowspan="2">班级</td>
								<logic:equal value="11799" name="xxdm">
									<td colspan="4">总人数</td>
								</logic:equal>
								<logic:notEqual value="11799" name="xxdm">
									<td colspan="3">总人数</td>
								</logic:notEqual>
								<logic:equal value="11799" name="xxdm">
									<td colspan="4">男生数</td>
								</logic:equal>
								<logic:notEqual value="11799" name="xxdm">
									<td colspan="3">男生数</td>
								</logic:notEqual>
								<logic:equal value="11799" name="xxdm">
									<td colspan="4">女生数</td>
								</logic:equal>
								<logic:notEqual value="11799" name="xxdm">
									<td colspan="3">女生数</td>
								</logic:notEqual>
<%--								<td>住校人数</td>--%>
<%--								<td>住校男生数</td>--%>
<%--								<td>住校女生数</td>--%>
<%--								<td>未住校人数</td>--%>
<%--								<td>未住校男生数</td>--%>
<%--								<td>未住校女生数</td>--%>
							</tr>
							<tr  align="center">
<%--								<td>学院</td>--%>
<%--								<td>年级</td>--%>
<%--								<td>班级</td>--%>
								<td>总人数</td>
								<td>住校人数</td>
								<logic:equal value="11799" name="xxdm">
									<td>
										非走非住数
									</td>
									<td>
										走读数
									</td>										
								</logic:equal>
								<logic:notEqual value="11799" name="xxdm">
									<td>未住校人数</td>
								</logic:notEqual>
								<td>男生数</td>
								<td>住校男生数</td>
								<logic:equal value="11799" name="xxdm">
									<td>
										非走非住数
									</td>
									<td>
										走读数
									</td>										
								</logic:equal>
								<logic:notEqual value="11799" name="xxdm">
									<td>未住校男生数</td>
								</logic:notEqual>
								<td>女生数</td>
								<td>住校女生数</td>
								<logic:equal value="11799" name="xxdm">
									<td>
										非走非住数
									</td>
									<td>
										走读数
									</td>										
								</logic:equal>
								<logic:notEqual value="11799" name="xxdm">
									<td>未住校女生数</td>
								</logic:notEqual>
								
							</tr>
						</thead>
						<tbody>
						<logic:empty name="rs">
						<logic:equal value="11799" name="xxdm">
							<%
							for(int i=0; i<13; i++){
							%>
							<tr>
								<logic:iterate id="tit" name="topTr" offset="0">
									<td>
										&nbsp;
									</td>
								</logic:iterate>	
						 	</tr>
							<%}%>
						</logic:equal>
						<logic:notEqual value="11799" name="xxdm">						
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
						</logic:notEqual>
						 </logic:empty>
						<logic:notEmpty name="rs">
							<logic:iterate name="rs" id="map">
								<tr onclick="rowOnClick(this);">
									<td><input type="hidden" value="${map.nj }"/>
									    <input type="hidden" value="${map.xydm }"/>
									    <input type="hidden" value="${map.bjdm }"/>
									    ${map.xymc }</td>
									<td>${map.nj }</td>
									<td>${map.bjmc }</td>
<%--									<td>${map.zrs }</td>--%>
<%--									<td>${map.zxrs }/${map.zxrsbfb}%</td>--%>
<%--									<td>${map.wzxrs }/${map.wzxrsbfb}%</td>--%>
<%--									<td>${map.mrs }/${map.mrsbfb}%</td>--%>
<%--									<td>${map.zxmrs }/${map.zxmrsbfb}%</td>--%>
<%--									<td>${map.wzxmrs }/${map.wzxmrsbfb}%</td>--%>
<%--									<td>${map.wrs }/${map.wrsbfb}%</td>--%>
<%--									<td>${map.zxwrs }/${map.zxwrsbfb}%</td>--%>
<%--									<td>${map.wzxwrs }/${map.wzxwrsbfb}%</td>--%>
									<td><a href="#" onclick="getXsxx(this,'','')">${map.zrs }</a></td>
									<td><a href="#" onclick="getXsxx(this,'yes','')">${map.zxrs }<br/>${map.zxrsbfb}%</a></td>
									
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
					</tbody>
				
			   
				</table>
			</div>
		</html:form>
		
	</body>
</html>
