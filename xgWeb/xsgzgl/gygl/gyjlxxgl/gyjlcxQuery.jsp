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
			refreshForm('gyglnew_gyjlgl_gyjlcx.do?doType=query');
		}
		function view(url,h,w){
			var xh = curr_row.getElementsByTagName('input')[0].value;
			var wjsj = curr_row.getElementsByTagName('input')[1].value;
			showTopWin(url + '&xh='+xh+'&wjsj='+wjsj,h,w);
		}

		function setDivHeight(){
			if($("table_rs")){
				jQuery("#div_rs").height(jQuery("#table_rs").height()+20);	
			}
		}
		
		//ɾ��
		function del(){
			var pkArr=document.getElementsByName("div_pkValue");
			var flag=false;
			for(i=0;i<pkArr.length;i++){
				if(pkArr[i].checked){
					pkArr[i].value+="!!!splitOne!!!"+document.getElementsByName("wjsj")[i].value;
					flag=true;
				}
			}
			if(flag){
				confirmInfo("�ò�������ɾ��������ѡ�����ݣ��Ƿ�ȷ������������",function(data){
					if("ok"==data){
						refreshForm('gyglnew_gyjlgl.do?method=gyjlcxQuery&doType=del');
					}
					return false;
				});
			}else{
				alertInfo("�빴ѡ��Ҫɾ��������!",function(tag){
					return false;
				});
			}
		}
		//�޸�
		function showModi(){
		
			var len=jQuery("[name=div_pkValue]:checked").length;
			
			if(len==1){	
				
				var xh=jQuery("[name=div_pkValue]:checked").val();
				
				var jlsj=jQuery("[name=div_pkValue]:checked").parent().parent().find("td").eq(6).html();

				var url="gyglnew_gyjlgl_gyjlxg.do";
				
				url+="?xh="+xh+"&jlsj="+jlsj;
				 
				showTopWin(url,800,600);
			}else{
				
				alertInfo("�빴ѡһ����Ҫ�޸ĵļ�¼��");
				
				return false;
			}
		}


		/*
		function modi(url,h,w){
			if(curr_row != null){
				showTopWin(url + '&pkValue='+curr_row.getElementsByTagName('input')[0].value,h,w);
				return true;
			}else{
				alert('��ѡ��Ҫ�����������У�');
				return false;
			}
		}
		*/
		</script>
	</head>
	<body>
		<html:form action="/gyglnew_gyjlgl" method="post">
			<!-- ������ -->
			<%@ include file="/comm/hiddenValue.jsp"%>			
			<input type="hidden" id="searchTjstr" value="<bean:write name="searchTjstr"/>"/>
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
						<logic:equal value="yes" name="writeAble">
						<li><a href="#" onclick="showTopWin('gyglnew_gyjlgl_gyjlzj.do',800,600);return false;" class="btn_zj">����</a></li>
						<li><a href="#" onclick="showModi();return false;" class="btn_xg">�޸�</a></li>
						<li><a href="#" onclick="del();return false;" class="btn_sc"> ɾ�� </a></li>
						</logic:equal>
						<li><a href="#" class="btn_qx" onclick="choiceFields();return false;">��������</a></li>
						<li><a href="#" class="btn_dc" onclick="configureExportData();return false;">��������</a></li>
					</ul>
				</div>
				<!-- �������� -->
				<%@ include file="/xsgzgl/gygl/gyjlxxgl/gyglGjcx.jsp"%>
				<!-- �������� end-->
			</div>
				<div class="formbox">
					<h3 class="datetitle_01">
						<span>
							��ѯ���&nbsp;&nbsp;<font color="blue">������ͷ��������;˫����¼�ɲ鿴;</font>
						</span>
					</h3>
					<div class="con_overlfow">
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
								ondblclick="view('gyglnew_gyjlgl.do?method=gyjlcxView',800,650);">
									<logic:iterate id="v" name="s" offset="2" length="1">
										<td align="center" width="5px">
											<input type="checkbox" id="pk_${index }"
											name="div_pkValue" value="${v }" <logic:iterate id="v" name="s" offset="0" length="1">${v }</logic:iterate>/>
											<input type="hidden" id="wjsj" name="wjsj"
											value="<logic:iterate id="v" name="s" offset="7" length="1">${v }</logic:iterate>"/>
											<input type="hidden" id="xh" name="xh"
											value="${v }"/>
										</td>
									</logic:iterate>
									<!-- ��ʾ��Ϣ -->
									<logic:iterate id="v" name="s" offset="2">
										<td align="left" nowrap="nowrap">
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
									<td nowrap="nowrap">
										&nbsp;
									</td>
								</logic:iterate>
						 	</tr>
							<%}}%>
						</logic:notEmpty>
					</table>
					</div>
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
