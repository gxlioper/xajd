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
		//��ѯ�����
		function searchRs(){
			allNotEmpThenGo('dtjs_dtxxgl_dtxxgl.do');
		}
		//ͬ������������ò
		function tbgxzzmm(){
			confirmInfo("ȷ��ͬ������������ò��Ϣ��",function(ok){
					if("ok"==ok){
						allNotEmpThenGo('dtjs_dtxxgl_dtxxgl.do?doType=tbgxzzmm');
					}
				});
		}

		//��ס
		function modi(url,h,w){
			if(curr_row != null){

				showDialog('ѧ��������Ϣ�޸�', h, w, url + '&xh='+curr_row.getElementsByTagName('input')[0].value);
				//showTopWin(url + '&xh='+curr_row.getElementsByTagName('input')[0].value,h,w);
				return true;
			}else{
				alertInfo('��ѡ��Ҫ������������');
				return false;
			}
		}
		
		function view(url,h,w){
			showDialog('ѧ��������Ϣ�鿴', h, w, url + '&xh='+curr_row.getElementsByTagName('input')[0].value);
			//showTopWin(url + '&xh='+curr_row.getElementsByTagName('input')[0].value,h,w);
		}	

		/*
		���ݵ���
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

		// ��������
		function expData() {
			document.forms[0].action = "dtjs_dtxxgl.do?method=exportData";
			document.forms[0].target = "_blank";
			document.forms[0].submit();
			document.forms[0].target = "_self";
		}
		
		function xsdtxxglExportConfig() {
			//DCCLBH�������ܱ��,ִ�е������� 
			customExport("dtjs_dtxxgl_xsdtxxgl.do", xsdtxxglExportData);
		}
			
		
			
		// ��������
		function xsdtxxglExportData() {
			setSearchTj();//���ø߼���ѯ����
			var url = "dtjs_dtxxgl.do?method=xsdtxxglExportData&dcclbh=" + "dtjs_dtxxgl_xsdtxxgl.do";//dcclbh,�������ܱ��
			url = addSuperSearchParams(url);//���ø߼���ѯ����
			jQuery("form").eq(0).attr("action", url);
			jQuery("form").eq(0).submit();
		}

		</script>
	</head>
	<body>
		<html:form action="/dtjs_dtxxgl" method="post">
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
						<!-- ��дȨ begin-->
							<logic:equal name="writeAble" value="yes">
						<li><a href="#" onclick="showDialog('ѧ��������Ϣ����', 800, 410,'dtjs_dtxxgl.do?method=dtxxAdd');return false;" class="btn_zj"> ���� </a></li>
						<li><a href="#" onclick="modi('dtjs_dtxxgl.do?method=dtxxUpdate',750,380);return false;" class="btn_xg"> �޸� </a></li>
						<li><a href="#" onclick="batchData('primarykey_checkVal','dtjs_dtxxgl_dtxxgl.do?doType=del','del');return false;" class="btn_sc"> ɾ�� </a></li>
						<li><a href="#" class="btn_sx" onclick="tbgxzzmm();return false;">ͬ������������ò</a></li>
						<li><a href="#" onclick="impData();return false;" class="btn_dr">��������</a></li>
						</logic:equal>
						<!-- ��дȨ end-->
						<li><a href="#" onclick="expData();return false;" class="btn_dc">��������</a></li>
						<%--<li><a href="#" onclick="xsdtxxglExportConfig();return false;" class="btn_dc">����</a></li>--%>
<!--						<li><a href="#" class="btn_qx" onclick="choiceFields();return false;">�����ֶ�ȷ��</a></li>-->
						
					</ul>
				</div>
				<!-- �������� -->
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- �������� end-->
			</div>
				<div class="formbox">
					<h3 class="datetitle_01">
						<span>
							��ѯ���&nbsp;&nbsp;<font color="blue">������ͷ��������;˫����ʾѧ�����Ž��������Ϣ</font>
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
				<!--��ҳ��ʾ-->
		   	 	<jsp:include flush="true" page="/sjcz/turnpage.jsp?form=dtjsDtxxglForm"></jsp:include>
				<!--��ҳ��ʾ-->
			</div>

		</html:form>
		<logic:present name="message">
			<script>
				alertInfo('${message}');
			</script>
		</logic:present>
	</body>
</html>
