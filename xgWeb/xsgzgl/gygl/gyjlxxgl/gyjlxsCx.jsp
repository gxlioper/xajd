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
			allNotEmpThenGo('gyglnew_gyjlgl_gyjlxscx.do');
		}
		function sendXx(){			
			if(window.opener == undefined){					 				
				var url = window.dialogArguments.document.forms[0].url.value;
				url+="&xh="+curr_row.getElementsByTagName('input')[0].value;
				window.dialogArguments.document.forms[0].action = url;
				window.dialogArguments.document.forms[0].submit();
			}else{
				var url = window.opener.document.forms[0].url.value;
				url+="&xh="+curr_row.getElementsByTagName('input')[0].value;
				window.opener.document.forms[0].action = url;
				window.opener.document.forms[0].submit();
			}

			window.close();
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
			<div class="toolbox" id="dgncz">
			<!-- ��ť -->
				<div class="buttonbox">
					<ul>
					</ul>
				</div>
				<!-- �������� -->
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- �������� end-->
			</div>
				<div class="formbox">
					<h3 class="datetitle_01">
						<span>
							��ѯ���&nbsp;&nbsp;<font color="blue">��ʾ��������ͷ��������˫��ѡ��ѧ����Ϣ.</font>
						</span>
					</h3>
					<table width="99%" id="rsTable" class="dateline">
						<thead>
							<tr align="center" style="cursor:hand">
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
									<td style="display: none;">
										<input type="hidden" id="xh" name="xh"
											value="${v }"/>
									</td></logic:iterate>
									<!-- ��ʾ��Ϣ -->
									<logic:iterate id="v" name="s">
										<td align="left">
											${v }
										</td>
									</logic:iterate>
								</tr>
							</logic:iterate>
							<%--<%
							int rsNum = ((List)request.getAttribute("rsArrList")).size();
							int pageSize = (Integer)(request.getAttribute("pageSize"));
							if(rsNum < pageSize){
								for(int i=0; i<(pageSize-rsNum); i++){
							%>
							<tr>
								<logic:iterate id="tit" name="topTr" offset="0" length="1">
									<td>
										&nbsp;
									</td>
								</logic:iterate>
						 	</tr>
							<%}}%>
						--%></logic:notEmpty>
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
