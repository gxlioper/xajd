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
		//��ѯ�����
		function searchRs(){
			allNotEmpThenGo('gyglnew_xszstj_xszstj.do');
		}

		function modi(url,h,w){
			if(curr_row != null){
				showDialog("�鿴ѧ��ס�����ͳ��",h,w,url + '&nj='+curr_row.getElementsByTagName('input')[0].value+
						'&xydm='+curr_row.getElementsByTagName('input')[1].value);
				//showDialog('', 760, 525, 'gyglnew_qsgl.do?method=qswhAdd');
				return true;
			}else{
				alertInfo('��ѡ��Ҫ�����������У�');
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
			#rsTable a:hover {color: blue!important;text-decoration: underline}	/* ����ƶ��������� */
		</style>
	</head>
	<body>
		<html:form action="/gyglnew_xszstj" method="post">
			<!-- ������ -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<!-- ������ -->
			<div class="tab_cur" >
				<p class="location">
					<em>���ĵ�ǰλ��:</em><a>${title }</a>
				</p>
			</div>
			
			<div class="toolbox" id="dgncz">
				<!-- ��ť -->
				<div class="buttonbox">
					<ul>
						<li><a href="#" onclick="modi('gyglnew_xszstj.do?method=xszstjDetail',1024,525);return false;" class="btn_cx">�鿴��ϸ��Ϣ</a></li>
<%--						<li><a href="#" onclick="toExcel('rsTable');return false;" class="btn_dc">��������</a></li>--%>
						<logic:equal name="writeAble" value="yes">
						<li><a href="#" class="btn_dc" onclick="setSearchTj();configureExportDataZdy('${path}');return false;">��������</a></li>
						</logic:equal>
						<li><a href="#" class="btn_dc" onclick="showDialog('', 400, 220, 'gyglnew_xszstj.do?method=qsfbbTj');return false;">���ҷֲ���</a></li>
					</ul>									
				</div>
				<!-- �������� -->
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- �������� end-->
			</div>
				<div class="formbox" style="height: 768px">
					<h3 class="datetitle_01">
						<span>
							��ѯ���&nbsp;&nbsp;<logic:empty name="rs"><font color="red">û�����ͳ����Ϣ��</font></logic:empty>
						</span>
					</h3>
					<div style="overflow-x:auto;">
					<logic:equal value="true" name="fdybzr">
						<table width="99%" id="rsTable" class="dateline">
							<thead align="center">
								<tr  align="center">
									<td rowspan="2">�༶</td>
									<logic:equal value="11799" name="xxdm">
										<td colspan="4" align="center">������</td>
									</logic:equal>
									<logic:notEqual value="11799" name="xxdm">
										<td colspan="3" align="center">������</td>
		<%--								<td>סУ����</td>--%>
		<%--								<td>δסУ����</td>--%>
									</logic:notEqual>
									<logic:equal value="11799" name="xxdm">
										<td colspan="4">������</td>
									</logic:equal>
									<logic:notEqual value="11799" name="xxdm">
										<td colspan="3">������</td>
		<%--								<td>סУ������</td>--%>
		<%--								<td>δסУ������</td>--%>
									</logic:notEqual>
									<logic:equal value="11799" name="xxdm">
										<td colspan="4">Ů����</td>
									</logic:equal>
									<logic:notEqual value="11799" name="xxdm">
										<td colspan="3">Ů����</td>
		<%--								<td>סУŮ����</td>--%>
		<%--								<td>δסУŮ����</td>--%>
									</logic:notEqual>
								</tr>
								<tr  align="center">
									<td>������</td>
									<td>סУ����</td>
									<logic:equal value="11799" name="xxdm">
										<td>
											���߷�ס����
										</td>
										<td>
											�߶�����
										</td>										
									</logic:equal>
									<logic:notEqual value="11799" name="xxdm">
										<td>δסУ����</td>
									</logic:notEqual>
									<td>������</td>
									<td>סУ����</td>
									<logic:equal value="11799" name="xxdm">
										<td>
											���߷�ס����
										</td>
										<td>
											�߶�����
										</td>										
									</logic:equal>
									<logic:notEqual value="11799" name="xxdm">
										<td>δסУ����</td>
									</logic:notEqual>
									<td>������</td>
									<td>סУ����</td>
									<logic:equal value="11799" name="xxdm">
										<td>
											���߷�ס����
										</td>
										<td>
											�߶�����
										</td>										
									</logic:equal>
									<logic:notEqual value="11799" name="xxdm">
										<td>δסУ����</td>
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
									<td rowspan="2">�꼶</td>
									<logic:equal value="11799" name="xxdm">
										<td colspan="4" align="center">������</td>
									</logic:equal>
									<logic:notEqual value="11799" name="xxdm">
										<td colspan="3" align="center">������</td>
		<%--								<td>סУ����</td>--%>
		<%--								<td>δסУ����</td>--%>
									</logic:notEqual>
									<logic:equal value="11799" name="xxdm">
										<td colspan="4">������</td>
									</logic:equal>
									<logic:notEqual value="11799" name="xxdm">
										<td colspan="3">������</td>
		<%--								<td>סУ������</td>--%>
		<%--								<td>δסУ������</td>--%>
									</logic:notEqual>
									<logic:equal value="11799" name="xxdm">
										<td colspan="4">Ů����</td>
									</logic:equal>
									<logic:notEqual value="11799" name="xxdm">
										<td colspan="3">Ů����</td>
		<%--								<td>סУŮ����</td>--%>
		<%--								<td>δסУŮ����</td>--%>
									</logic:notEqual>
								</tr>
								<tr  align="center">
									<td>������</td>
									<td>סУ����</td>
									<logic:equal value="11799" name="xxdm">
										<td>
											���߷�ס����
										</td>
										<td>
											�߶�����
										</td>										
									</logic:equal>
									<logic:notEqual value="11799" name="xxdm">
										<td>δסУ����</td>
									</logic:notEqual>
									<td>������</td>
									<td>סУ����</td>
									<logic:equal value="11799" name="xxdm">
										<td>
											���߷�ס����
										</td>
										<td>
											�߶�����
										</td>										
									</logic:equal>
									<logic:notEqual value="11799" name="xxdm">
										<td>δסУ����</td>
									</logic:notEqual>
									<td>������</td>
									<td>סУ����</td>
									<logic:equal value="11799" name="xxdm">
										<td>
											���߷�ס����
										</td>
										<td>
											�߶�����
										</td>										
									</logic:equal>
									<logic:notEqual value="11799" name="xxdm">
										<td>δסУ����</td>
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
										<!-- ���칤�̴�ѧ���Ի� -->
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
					<!--��ҳ��ʾ-->
			   	 	<jsp:include flush="true" page="/sjcz/turnpage.jsp?form=gyglnewXszstjForm"></jsp:include>
					<!--��ҳ��ʾ-->
			</div>
		</html:form>
		
		<%@ include file="/comm/delMessage.jsp"%>
		<%@ include file="/comm/loading.jsp"%>
	</body>
</html>
