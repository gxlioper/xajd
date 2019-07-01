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
		<script language="javascript">
		//��ѯ�����
		function searchRs(){
			allNotEmpThenGo('gyglnew_gyjlgl_gyjlgl.do');
		}
		function view(url,h,w){
			showTopWin(url + '&pkValue='+curr_row.getElementsByTagName('input')[0].value,h,w);
		}
		function wj(){
			var flag = false;
			var pkV=document.getElementsByName("primarykey_checkVal");
			for(i=0;i<pkV.length;i++){
				if(pkV[i].checked && !pkV[i].disabled){
					flag=true;
				}
			}
			if(flag){
				showTopWin('gyglnew_gyjlgl.do?method=gyjlxxEdit',700,500);
				return false;
			}else{
				alertInfo('�����ٹ�ѡһ�������в�����');
				return false;
			}
		}
		</script>
	</head>
	<body>
		<html:form action="/gyglnew_gyjlgl" method="post">
			<!-- ������ -->
			<%@ include file="/comm/hiddenValue.jsp"%>			
			<input type="hidden" id="searchTjstr" value="<bean:write name="searchTjstr"/>"/>
			<input type="hidden" id="yrzcws" value="<bean:write name="num"/>"/>
			<input type="hidden" id="num" value="<bean:write name="num"/>"/>
			<!-- ������ -->
			<div class="tab_cur" >
				<p class="location">
					<em>���ĵ�ǰλ��:</em><a>${title }</a>
				</p>
			</div>
			<!-- ��ʾ��Ϣ end-->
<%--			<div class="prompt" id="promptTs">--%>
<%--				<h3>--%>
<%--					<span>ϵͳ��ʾ��</span>--%>
<%--				</h3>--%>
<%--				<p>--%>
<%--					����ѡ��Ĭ������ȫ�����ݼ�����ѡ������ѡ��ѧ������ѡ����ѧ������ʱ��ͬʱ��������ס����--%>
<%--				</p>--%>
<%--				<a class="close" title="����"--%>
<%--				   onclick="this.parentNode.style.display='none';"></a>--%>
<%--			</div>--%>
			<!-- ��ʾ��Ϣ end-->	
			<div class="toolbox" id="dgncz">
				<!-- ��ť -->
				<div class="buttonbox">
					<ul>
						<logic:equal value="yes" name="writeAble">
						<li><a href="#" onclick="wj()" class="btn_sh"> Υ�� </a></li>
						</logic:equal>
						<li><a href="#" onclick="setSearchTj();configureExportDataZdy('${path}');return false;" class="btn_dc">��������</a></li>
					</ul>
				</div>
				<!-- �������� -->
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- �������� end-->
			</div>
				<div class="formbox">
					<h3 class="datetitle_01">
						<span>
							��ѯ���&nbsp;&nbsp;<font color="blue">������ͷ��������;˫����¼�ɲ鿴;</font>
						</span>
					</h3>
					<table width="99%" id="rsTable" class="dateline">
						<thead>
							<tr align="center" style="cursor:hand">
								<td>
									<input type="checkbox" name="all" value="all" onclick="chec()"/>
								</td>
								<logic:iterate id="tit" name="topTr" offset="0" >
									<td id="<bean:write name="tit" property="en"/>"
										onclick="tableSort(this)"
										nowrap>
										<bean:write name="tit" property="cn" />
									</td>
								</logic:iterate>
							</tr>
						</thead>
						<logic:empty name="rsArrList">
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
						<logic:notEmpty name="rsArrList">
							<logic:iterate name="rsArrList" id="s">
								<logic:iterate name="s" id="dis" offset="1" length="1"></logic:iterate>
								<tr onclick="rowOnClick(this);" style="cursor:hand" 
								ondblclick="sendXx();">
									<logic:iterate id="v" name="s" offset="0" length="1">
										<td align="center" width="5px">
											<input type="checkbox" id="pk_${index }"
											name="primarykey_checkVal" value="${v }"/>
											<input type="hidden" id="xh" name="xh"
											value="${v }"/>
										</td>
									</logic:iterate>
									<!-- ��ʾ��Ϣ -->
									<logic:iterate id="v" name="s">
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
			   	 	<jsp:include flush="true" page="/sjcz/turnpage.jsp?form=gyjlxxglForm"></jsp:include>
					<!--��ҳ��ʾ-->
				</div>
			</div>
		</html:form>
		
		<%@ include file="/comm/delMessage.jsp"%>
		<%@ include file="/comm/loading.jsp"%>
	</body>
</html>
