<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@page import="java.util.List"%>
<%@page import="xgxt.utils.Pages"%>
<%@page import="xgxt.szdw.bjlh.fdykh.BjlhFdykhForm"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/qtsjFunction.js"></script>
		<script type="text/javascript" src="js/commit.js"></script>
		<script language="javascript">
				
			//��ѯ�����
			function searchRs(){
				allNotEmpThenGo('bjlh_fdykh_khcptj.do');
			}
			
			//�����ʾ�Ԥ��
			function cpwjglTjOne(){
				var pk;
				if(curr_row != null){
					pk=curr_row.getElementsByTagName('input')[0].value;
				}else{
					alertInfo('��ѡ��Ҫ�����������У�');
					return false;
				}
				var pkv=pk.split("!!one!!");
				var url="bjlh_fdykh.do?method=cpwjglTj&xn="+pkv[0]+"&wjid="+pkv[1]+"&fdyid="+pkv[2];
				showTopWin(url,800,800);
			}
			
			function fdycjTjWh(){
				var pk;
				if(curr_row != null){
					pk=curr_row.getElementsByTagName('input')[0].value;
				}else{
					alertInfo('��ѡ��Ҫά���������У�');
					return false;
				}
				var pkv=pk.split("!!one!!");
				var url="bjlh_fdykh.do?method=fdycjTjWh&xn="+pkv[0]+"&fdyid="+pkv[1];
				showTopWin(url,600,450);
			}
			
			function fdycjTjCk(){
				var pk;
				if(curr_row != null){
					pk=curr_row.getElementsByTagName('input')[0].value;
				}else{
					alertInfo('��ѡ��Ҫ�鿴�������У�');
					return false;
				}
				var pkv=pk.split("!!one!!");
				var url="bjlh_fdykh.do?method=fdycjTjCk&xn="+pkv[0]+"&fdyid="+pkv[1];
				showTopWin(url,600,450);
			}
			
			function fdycjTjDc(){
				var url = "bjlh_fdykh.do?method=fdycjTjDc";
				document.forms[0].action = url;
				document.forms[0].target = "_blank";
				document.forms[0].submit();
				document.forms[0].target = "_self";	
			}
			//������ϸ�鿴
			function pfmxck(){
				var url = "bjlh_fdykh.do?method=pfmxCk";
				if(curr_row == null){
					alertInfo('��ѡ��Ҫ�鿴�������У�');
					return false;
				}
				var xn=curr_row.cells[1].innerText;
				var fdyid=curr_row.cells[2].innerText;
				url+="&xn="+xn+"&fdyid="+fdyid;
				showTopWin(url,600,450);
			}
			
			var DCCLBH ='bjlh_fdykh.do';
			function exportConfig(){
				customExport(DCCLBH, exportData);
			}

			//������ѯ���
			function exportData(){
				setSearchTj();//���ø߼���ѯ����
				var url = "bjlh_fdykh.do?method=exportData&dcclbh=" + DCCLBH;//dcclbh,�������ܱ��
				url = addSuperSearchParams(url);//���ø߼���ѯ����
				jQuery("form").eq(0).attr("action", url);
				jQuery("form").eq(0).submit();
			}
		</script>
	</head>
	<body>
		<html:form action="/bjlh_fdykh" method="post">
			<!-- ������ -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<div class="tab_cur" >
				<p class="location">
					<em>���ĵ�ǰλ��:</em><a>${title }</a>
				</p>
			</div>
			<logic:notEqual value="yes" name="czxygxh">
				<div class="toolbox">
					<div class="buttonbox">
						<ul>
							<li><a href="#" onclick="exportConfig();return false;" class="btn_dc">����</a></li>
						
						</ul>
					</div>
				</div>
			</logic:notEqual>
			<logic:equal value="yes" name="czxygxh">
				<div class="toolbox">
				<!-- ��ť -->
					<div class="buttonbox">
						<ul>
<%--							<li><a href="#" onclick="cpwjglTjOne();return false;" class="btn_yl">ͳ��</a></li>--%>
								<logic:equal value="yes" name="writeAble">
								<li><a href="#" onclick="fdycjTjWh();return false;" class="btn_xg">ά��</a></li>
								</logic:equal>
								<li><a href="#" onclick="fdycjTjCk();return false;" class="btn_ck">�鿴</a></li>
								<logic:equal value="yes" name="writeAble">
								<li><a href="#" onclick="impAndChkData();return false;" class="btn_dr">����</a></li>
								</logic:equal>
								<li><a href="#" onclick="fdycjTjDc();return false;" class="btn_dc">����</a></li>
								<li><a href="#" onclick="pfmxck();return false;" class="btn_ck">������ϸ</a></li>
						</ul>
					</div>
				</div>
			</logic:equal>
				<logic:equal name="superSearch" value="yes">
			     	<%@ include file="/comm/search/superSearchArea.jsp"%>
			    </logic:equal>
				<div class="formbox">
					<h3 class="datetitle_01">
						<span>
							��ѯ���&nbsp;&nbsp;
						</span>
					</h3>
					<table width="99%" id="rsTable" class="dateline">
						<thead>
							<tr align="center" style="cursor:hand">
<%--								<td style="display: none;">--%>
<%--								</td>--%>
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
							<logic:iterate name="rs" id="s">
								<tr onclick="rowOnClick(this);" style="cursor:hand">
									<td style="display: none;">
										<input type="hidden" value="<logic:iterate id="v" name="s" offset="0" length="1">${v}</logic:iterate>"/>
									</td>
									<logic:iterate id="v" name="s" offset="1">
										<td>${v }</td>
									</logic:iterate>
								</tr>
							</logic:iterate>
							<%
							int rsNum = ((List)request.getAttribute("rs")).size();
							BjlhFdykhForm form = (BjlhFdykhForm)request.getAttribute("bjlhFdykhForm");
							int pageSize = form.getPages().getPageSize();
							if(rsNum < pageSize){
								for(int i=0; i<(pageSize-rsNum); i++){
							%>
							<tr>
								<logic:iterate id="tit" name="topTr" offset="0">
									<td>
										&nbsp;
									</td>
								</logic:iterate>
						 	</tr>
							<%}}%>
					</logic:notEmpty>
				</table>
				<!--��ҳ��ʾ-->
		   	 	<jsp:include flush="true" page="/sjcz/turnpage.jsp?form=bjlhFdykhForm"></jsp:include>
				<!--��ҳ��ʾ-->
			</div>
		</html:form>
		<logic:present name="message">
			<input type="hidden" id="message" name="message" value="${message }"/>
			<script type="text/javascript">
				alertInfo($('message').value);
			</script>
		</logic:present>
	</body>
</html>
