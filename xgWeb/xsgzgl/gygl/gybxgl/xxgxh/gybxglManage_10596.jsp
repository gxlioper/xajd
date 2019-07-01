<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@page import="java.util.List"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- ���ߣ�zhangh -->
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
		//��ѯ�����
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
				alertInfo('��ѡ��Ҫ�����������У�');
				return false;
			}
		}
		function gybxglExportConfig() {
			//DCCLBH�������ܱ��,ִ�е������� 
			customExport("gyglnew_gybxgl_gybxgl.do", gybxglExportData);
			}
				
			
				
		// ��������
		function gybxglExportData() {
			setSearchTj();//���ø߼���ѯ����
			var url = "gyglnew_gybxgl.do?method=gybxglExportData&dcclbh=" + "gyglnew_gybxgl_gybxgl.do";//dcclbh,�������ܱ��
			url = addSuperSearchParams(url);//���ø߼���ѯ����
			jQuery("form").eq(0).attr("action", url);
			jQuery("form").eq(0).submit();
		}
		</script>
	</head>
	<body>
		<!-- ���� -->
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>${title }</a>
			</p>
		</div>
		<!-- ���� end-->
		
		
		<html:form action="/gyglnew_gybxgl">
			<!-- ������ -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<!-- ������ -->

			<!-- �๦�ܲ����� -->
			<div class="toolbox">
				<!-- ��ť -->
				<div class="buttonbox">
					<ul>
						<logic:equal value="yes" name="writeAble">						
						<li><a href="#" onclick="update('gyglnew_gybxgl.do?method=gybxglUpdate','update');return false;" class="btn_sh">���� </a></li>
						<li><a href="#" onclick="batchData('primarykey_checkVal','gyglnew_gybxgl.do?method=gybxglManage&doType=del','del');return false;" class="btn_sc"> ɾ�� </a></li>
						</logic:equal>
						<%--<li><a href="#" onclick="setSearchTj();configureExportDataZdy('${path}');return false;" class="btn_dc">��������</a></li>
					--%>
						<li><a href="#" class="btn_dc" onclick="gybxglExportConfig();return false;">����</a></li>
					</ul>
				</div>
				<!-- ��ť end-->	
				
				<!-- �������� -->
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- �������� end-->
				
			</div>
			<!-- �๦�ܲ����� end -->
			
			<!-- ��ѯ���-->
			<div class="formbox">		
				<h3 class="datetitle_01">
					<span>
						��ѯ���
						<logic:empty name="rsArrList">
							&nbsp;&nbsp;<font color="red">δ�ҵ��κμ�¼��</font> 
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
					<!-- ��ͷ -->
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
					<!-- ��ͷ end-->
					<!--���� -->
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
							<!-- ��ʾ��Ϣ -->
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
				<!--��ҳ-->
				<jsp:include flush="true" page="/sjcz/turnpage.jsp?form=gyglnewGybxglForm"></jsp:include>
				<!--��ҳend-->
				</div>
			</div>
			<!-- ��ѯ��� end-->
			
		</html:form>
	</body>
</html>