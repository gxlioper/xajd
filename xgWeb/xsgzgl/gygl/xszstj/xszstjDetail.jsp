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
					<em>���ĵ�ǰλ��:</em><a>�༶ס�����ͳ��</a>
				</p>
			</div>
			
			<div class="toolbox" id="dgncz">
				<!-- ��ť -->
				<div class="buttonbox">
					<ul>
<%--						<li><a href="#" onclick="toExcel('rsTable');return false;" class="btn_dc">��������</a></li>--%>
						<li><a href="#" class="btn_dc" onclick="configureExportDataZdy('${path}');return false;">��������</a></li>
					</ul>
				</div>
			</div>
				<div class="formbox" style="height:688px">
					<h3 class="datetitle_01">
						<span>
							��ѯ���&nbsp;&nbsp;<logic:empty name="rs"><font color="red">û�����ͳ����Ϣ��</font></logic:empty>
						</span>
					</h3>
					<table width="99%" id="rsTable" class="formlist">
						<thead>
							<tr  align="center">
								<td rowspan="2"><bean:message key="lable.xsgzyxpzxy" /></td>
								<td rowspan="2">�꼶</td>
								<td rowspan="2">�༶</td>
								<logic:equal value="11799" name="xxdm">
									<td colspan="4">������</td>
								</logic:equal>
								<logic:notEqual value="11799" name="xxdm">
									<td colspan="3">������</td>
								</logic:notEqual>
								<logic:equal value="11799" name="xxdm">
									<td colspan="4">������</td>
								</logic:equal>
								<logic:notEqual value="11799" name="xxdm">
									<td colspan="3">������</td>
								</logic:notEqual>
								<logic:equal value="11799" name="xxdm">
									<td colspan="4">Ů����</td>
								</logic:equal>
								<logic:notEqual value="11799" name="xxdm">
									<td colspan="3">Ů����</td>
								</logic:notEqual>
<%--								<td>סУ����</td>--%>
<%--								<td>סУ������</td>--%>
<%--								<td>סУŮ����</td>--%>
<%--								<td>δסУ����</td>--%>
<%--								<td>δסУ������</td>--%>
<%--								<td>δסУŮ����</td>--%>
							</tr>
							<tr  align="center">
<%--								<td>ѧԺ</td>--%>
<%--								<td>�꼶</td>--%>
<%--								<td>�༶</td>--%>
								<td>������</td>
								<td>סУ����</td>
								<logic:equal value="11799" name="xxdm">
									<td>
										���߷�ס��
									</td>
									<td>
										�߶���
									</td>										
								</logic:equal>
								<logic:notEqual value="11799" name="xxdm">
									<td>δסУ����</td>
								</logic:notEqual>
								<td>������</td>
								<td>סУ������</td>
								<logic:equal value="11799" name="xxdm">
									<td>
										���߷�ס��
									</td>
									<td>
										�߶���
									</td>										
								</logic:equal>
								<logic:notEqual value="11799" name="xxdm">
									<td>δסУ������</td>
								</logic:notEqual>
								<td>Ů����</td>
								<td>סУŮ����</td>
								<logic:equal value="11799" name="xxdm">
									<td>
										���߷�ס��
									</td>
									<td>
										�߶���
									</td>										
								</logic:equal>
								<logic:notEqual value="11799" name="xxdm">
									<td>δסУŮ����</td>
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
