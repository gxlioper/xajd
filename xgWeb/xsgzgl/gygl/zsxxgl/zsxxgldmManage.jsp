<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@page import="java.util.List"%>
<%@page import="xgxt.utils.Pages"%>
<%@page import="xsgzgl.gygl.zsxxgl.ZsxxglForm"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script language="javascript" src="js/function.js"></script>
		<script type="text/javascript" src="js/qtsjFunction.js"></script>
		<script type="text/javascript" src="js/commit.js"></script>
		
		<script type='text/javascript' src="js/comm/message.js"></script>
		<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
		<script type='text/javascript' src='/xgxt/dwr/util.js'></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type='text/javascript' src="js/uicomm.js"></script>
		<script type='text/javascript' src="js/String.js"></script>
		<script type='text/javascript' src="js/xgutil.js"></script>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script language="javascript">
		//��Ԣ���ɴ������div���� ���ӡ��޸�
		var doType;
		function jldlDiv(type,topMsg){
		
			doType=type;
			var title = topMsg;
			if(type!="add"){
				if(curr_row != null){
					jldldm=curr_row.getElementsByTagName('input')[0].value;
				}else{
					alertInfo('��ѡ��Ҫ�����������У�');
					return false;
				}	
				url="gyglnew_zsxxgl.do?method=rzyyZjxg&doType="+doType+"&rzyydm=" + jQuery(curr_row.cells[1]).text(); 
				url = url + "&rzyymc=" +jQuery(curr_row.cells[2]).text();
			}else{
				if(type=="add"){
					url="gyglnew_zsxxgl.do?method=rzyyZjxg&doType="+doType;
				}
			}
			showDialog(title, 350, 200, url);
		}
			
			//��ѯ�����
			function searchRs(){
				allNotEmpThenGo('gyglnew_zsxxgl.do?method=zsxxgldmManage');
			}
			

			//ɾ��
			function deljldl(doType){
				var rzyydm;
				if(curr_row != null){
					rzyydm=curr_row.getElementsByTagName('input')[0].value;
				}else{
					alertInfo('��ѡ��Ҫ�����������У�');
					return false;
				}
				confirmInfo("ȷ��ɾ����",function(data){
					if("ok"==data){
						allNotEmpThenGo('gyglnew_zsxxgl.do?method=zsxxgldmManage&doType=del&rzyydm='+rzyydm);
					}
					return false;
				});
			}

			function newChgCode(obj){
				allNotEmpThenGo(obj.id);
			}
		</script>
	</head>
	<body>
		<html:form action="/gyglnew_gyjldmgl" method="post">
			<!-- ������ -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<button type="button" style="display:none" id="search_go" onclick="searchRs()">
			</button>
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
						<li><a href="#" onclick="jldlDiv('add','������ס����');return false;" class="btn_zj">����</a></li>
						<li><a href="#" onclick="jldlDiv('update','�޸���ס����');return false;" class="btn_xg">�޸�</a></li>
						<li><a href="#" onclick="deljldl();return false;" class="btn_sc">ɾ��</a></li>
						</logic:equal>
					</ul>
				</div>
			</div>
			    <div class="compTab" id="card">
				<div class="comp_title"><ul>
									<li class="ha"><a href="#" onclick="newChgCode(this);return false;" id="gyglnew_zsxxgl.do?method=zsxxgldmManage"><span>��סԭ��</span> </a>
				</div>
			    
				<div class="formbox">
					<h3 class="datetitle_01">
						<span>
							��ѯ���&nbsp;&nbsp;
						</span>
					</h3>
					<table width="99%" id="rsTable" class="dateline">
						<thead>
							<tr align="center" style="cursor:hand">
								<td style="display: none;">
									<input type="checkbox" name="all" value="all" onclick="chec()"/>
								</td>
								<logic:iterate id="tit" name="topTr" offset="0">
									<td id="${tit }" onclick="tableSort_hc(this,1)">
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
								<td style="display: none;">
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
								<tr onclick="rowOnClick(this);" style="cursor:hand">
									<td style="display: none;">								
										<input type="checkbox" name="primarykey_cbv" id="pkV"
											value="<logic:iterate id="v" name="s" offset="0" length="1">${v }</logic:iterate>" />
										
									</td>
									<logic:iterate id="v" name="s" offset="1" length="2">
										<td><bean:write name="v" format="ture"/></td>
									</logic:iterate>
								</tr>
							</logic:iterate>
							<%
							int rsNum = ((List)request.getAttribute("rs")).size();
							ZsxxglForm form = (ZsxxglForm)request.getAttribute("gyglnewZsxxglForm");
							int pageSize = form.getPages().getPageSize();
							if(rsNum < pageSize){
								for(int i=0; i<(pageSize-rsNum); i++){
							%>
							<tr>
								<td style="display: none;">
									<input type="checkbox" value="" disabled="disabled"></input>
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
		   	 	<jsp:include flush="true" page="/sjcz/turnpage.jsp?form=gyglnewZsxxglForm"></jsp:include>
		   	 	<script type="text/javascript">
					$('choose').className="hide";
				</script>
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
