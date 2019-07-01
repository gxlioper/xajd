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
		//��ѯ�����
		function searchRs(){
			allNotEmpThenGo('dtxxTjbase.do');
		}

		//��ӡexcel
		function getExcel(){
			var url="dtxxTj.do?method=downloadMultiExcel";
	 		window.open(url);			
		}
		
		</script>
	</head>
	<body>
		<html:form action="/dtxxTj" method="post">
			<!-- ������ -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<!-- ������ -->
			<div class="tab_cur" >
				<p class="location">
					<em>���ĵ�ǰλ��:</em><a>${title }</a>
				</p>
				<p class="help">
					<a href="#" onclick="return false;" onmousedown ="showHelpDiv()">ʹ�ð���</a>
				</p>
			</div>
			<div  id="div_help" class="prompt">
			<h3>
				<span>��ʾ��</span>
			</h3>
			<p>
				<span>
					Ĭ����ʾ��ǰ��ȣ�<font color="red">${dqnd}���</font>���ĵ�����Ϣ
				</span>
			</p>
			<a class="close" title="����"
				onclick="this.parentNode.style.display='none';"></a>
			</div>
			
			<div class="toolbox">
				<!-- ��ť -->
				<div class="buttonbox">
					<ul>
						<li><a href="javascript:void(0);" onclick="getExcel();return false;" class="btn_down">���ŷ�չ����¼</a></li>
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
				<table width="99%" id="rsTable" class="dateline">
					<thead>
						<tr align="center">
							<td width="10%"></td>
							<td width="13%" colspan="2">�뵳��������</td>
							<td width="10%" colspan="2">��չ����</td>
							<td width="35%" colspan="3">�ύԤ��չ����������ΪԤ����Ա��</td>
							<td width="16%" colspan="3">ȷ��ΪԤ����Ա</td>
							<td width="17%" colspan="2">Ԥ��ת��</td>
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
				<!--��ҳ��ʾ-->
			   	 	<jsp:include flush="true" page="/sjcz/turnpage.jsp?form=dtxxTjForm"></jsp:include>
				<!--��ҳ��ʾ-->
			</div>
		</html:form>
		
		<%@ include file="/comm/delMessage.jsp"%>
		<%@ include file="/comm/loading.jsp"%>
	</body>
</html>
