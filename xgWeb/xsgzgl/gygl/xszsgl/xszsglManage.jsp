<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@page import="java.util.List"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script language="javascript" src="js/function.js"></script>
		<script type="text/javascript" src="js/qtsjFunction.js"></script>
		<script type="text/javascript" src="js/commit.js"></script>
		<script language="javascript">
		//��ѯ�����
		function searchRs(){
			allNotEmpThenGo('gyglnew_xszsgl_xszsgl.do');
		}
		
		function modi(url,h,w){
			if(curr_row != null){
				showTopWin(url + '&pkValue='+curr_row.getElementsByTagName('input')[0].value,h,w);
				return true;
			}else{
				alert('��ѡ��Ҫ�����������У�');
				return false;
			}
		}
		//����ѧ�Ų鿴
		function zxsxxView(xh){
			
				var pkValue=xh;
				var url="xsxx_tygl.do?method=ckZxsxx";
				url+="&xh="+pkValue;
				var width=850;
				//showTopWin(url,850,640);
				showDialog('�鿴ѧ����ϸ��Ϣ', 850, 500, url)
		}
		function bzxbzsz(){
			var checkbox=document.getElementsByName("primarykey_checkVal");
			var num=0;
			var xhs="";
			for(i=0; i<checkbox.length;i++){
				if(checkbox[i].checked==true){
					num++;
					xhs+=checkbox[i].value+"-";
				}
			}
			if(num==0){
				alertInfo("�빴ѡҪ���õ�ѧ����");
				return false;
			}
			var url="gyglnew_xszsgl.do?method=bzxbzsz";
			url+="&xhs="+xhs;
			//showTopWin(url,500,300);
			showDialog('�߶���ע����', 500, 250, url);
		}
		function view(url,h,w){
			//showTopWin(url + '&pkValue='+curr_row.getElementsByTagName('input')[0].value,h,w);
			//600,400
			showDialog('�߶���ע', 600, 300, url+ '&pkValue='+curr_row.getElementsByTagName('input')[0].value);
		}	
		
		
		function xszsxxExportConfig() {
			//DCCLBH�������ܱ��,ִ�е������� 
			//showOpenWindow('configureExportData.do?method=choiceExportFields&tableName='+tableName,1000,600);
			customExport("gyglnew_xszsgl_xszsgl.do", xszsxxExportData);
			}
			
		
			
		// ��������
		function xszsxxExportData() {
			setSearchTj();//���ø߼���ѯ����
			var url = "gyglnew_xszsgl.do?method=xszsxxExportData&dcclbh=" + "gyglnew_xszsgl_xszsgl.do";//dcclbh,�������ܱ��
			url = addSuperSearchParams(url);//���ø߼���ѯ����
			jQuery("form").eq(0).attr("action", url);
			jQuery("form").eq(0).submit();
		}
		</script>
	</head>
	<body>
		<html:form action="/gyglnew_xszsgl" method="post">
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
<%--						<li><a href="#" class="btn_dc" onclick="showTopWin('gyglnew_tsgl.do?method=tsglPladd',700,500);return false;">����</a></li>--%>
						<%--<li><a href="#" class="btn_dc" onclick="setSearchTj();configureExportData();return false;">��������</a></li>--%>
						<logic:equal name="writeAble" value="yes">
						<li><a href="#" class="btn_dc" onclick="xszsxxExportConfig();return false;">����</a></li>
						</logic:equal>
						<li><a href="#" class="btn_sz" onclick="bzxbzsz();return false;">�߶���ע����</a></li>
					</ul>
				</div>
				<!-- �������� -->
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- �������� end-->
			</div>
				<div class="formbox">
					<h3 class="datetitle_01">
						<span>
							��ѯ���&nbsp;&nbsp;<font color="blue">������ͷ��������;</font>
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
								<tr onclick="rowOnClick(this);" style="cursor:hand" ondblclick="view('gyglnew_xszsgl.do?method=xszsxxView',600,400);">
									<logic:equal name="xxdm" value="11052">
										<logic:iterate id="z" name="s" offset="10" length="1">
											<logic:equal value="��" name="z">
												<logic:iterate id="v" name="s" offset="0" length="1">
												<td align="center" width="5px">
													<input type="checkbox" id="pk_${index }"
													name="primarykey_checkVal" value="${v }"/>
												</td>
												</logic:iterate>
											</logic:equal>
											<logic:equal value="��" name="z">
												<logic:iterate id="v" name="s" offset="0" length="1">
												<td align="center" width="5px">
													<input type="checkbox" id="pk_${index }"
													name="primarykey_checkVal" value="${v }" disabled="disabled"/>
												</td>
												</logic:iterate>
											</logic:equal>
										</logic:iterate>									
										<!-- ��ʾ��Ϣ -->								
										
										<logic:iterate id="v" name="s" offset="0" length="10">
											<td align="left">
												${v }
											</td>
										</logic:iterate>
									</logic:equal>
									<logic:notEqual name="xxdm" value="11052">
										<logic:iterate id="z" name="s" offset="9" length="1">
											<logic:equal value="��" name="z">
												<logic:iterate id="v" name="s" offset="0" length="1">
												<td align="center" width="5px">
													<input type="checkbox" id="pk_${index }"
													name="primarykey_checkVal" value="${v }"/>
												</td>
												</logic:iterate>
											</logic:equal>
											<logic:equal value="��" name="z">
												<logic:iterate id="v" name="s" offset="0" length="1">
												<td align="center" width="5px">
													<input type="checkbox" id="pk_${index }"
													name="primarykey_checkVal" value="${v }" disabled="disabled"/>
												</td>
												</logic:iterate>
											</logic:equal>
										</logic:iterate>									
										<!-- ��ʾ��Ϣ -->								
										
										<logic:iterate id="v" name="s" offset="0" length="1">
											<td>
											<a class="name" style="cursor:hand" href="#" onclick="zxsxxView('${v}')" return false ;>
												${v }
											</a>
											</td>
										</logic:iterate>							
										
										<logic:iterate id="v" name="s" offset="1" length="8">
											<td align="left">
												${v }
											</td>
										</logic:iterate>
										<logic:iterate id="v" name="s" offset="10" length="2">
											<td align="left">
												${v }
											</td>
										</logic:iterate>
									</logic:notEqual>
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
			   	 	<jsp:include flush="true" page="/sjcz/turnpage.jsp?form=gyglnewXszsglForm"></jsp:include>
					<!--��ҳ��ʾ-->
				</div>
			</div>
		</html:form>
		
		<%@ include file="/comm/delMessage.jsp"%>
		<%@ include file="/comm/loading.jsp"%>
	</body>
</html>
