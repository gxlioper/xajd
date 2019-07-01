<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@page import="common.Globals"%>
<%@page import="xgxt.action.Base"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- ���ߣ�ΰ����� -->
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script language="javascript" src="dwr/interface/getCommGygl.js"></script>
		
		<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
		<script type='text/javascript' src='/xgxt/dwr/util.js'></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		
		<script language="javascript" defer="defer">
		//��ѯ�����
		function searchRs(){
			allNotEmpThenGo('gyglnew_tsgl_tsgl.do');
		}
		
		//ɾ��������Ϣ
		function deleteTsglInfo(){
		
			var pkArr=document.getElementsByName("primarykey_checkVal");
			
			var flag=false;
			for(i=0;i<pkArr.length;i++){
				
				if(pkArr[i].checked){
					
					flag=true;
				}
			}
			
			if(flag){
				
				confirmInfo("�ò�������ɾ��������ѡ�����ݣ��Ƿ�ȷ������������",function(tag){
					if(tag=="ok"){
						refreshForm('gyglnew_tsgl_tsgl.do?doType=delete');
					}
				});
				
			}else{
				alertInfo("�빴ѡ��Ҫɾ��������!",function(tag){
					if(tag=="ok"){
						return false;
					}
				});
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
		
		function tsxxcxExportConfig() {
			//DCCLBH�������ܱ��,ִ�е������� 
			customExport("gyglnew_tsgl_tsgl.do", tsxxcxExportData);
		}
				
			
		
		// ��������
		function tsxxcxExportData() {
			setSearchTj();//���ø߼���ѯ����
			var url = "gyglnew_tsgl.do?method=tsxxcxExportData&dcclbh=" + "gyglnew_tsgl_tsgl.do";//dcclbh,�������ܱ��
			url = addSuperSearchParams(url);//���ø߼���ѯ����
			jQuery("form").eq(0).attr("action", url);
			jQuery("form").eq(0).submit();
		}

		function modi1(url,h,w){
			if(curr_row != null){
				//showTopWin(;
				showDialog("������Ϣ�鿴", 760, 400, url + '&pkValue='+curr_row.getElementsByTagName('input')[0].value);
				return true;
			}else{
				alertInfo('��ѡ��Ҫ�޸ĵ������У�');
				return false;
			}
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
		
		
		<html:form action="/gyglnew_tsgl">
			<!-- ������ -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<!-- ������ -->

			<!-- �๦�ܲ����� -->
			<div class="toolbox">
				<!-- ��ť -->
				<div class="buttonbox">
					<ul>
						<logic:equal name="writeAble" value="yes">
							<li><a href="#" class="btn_sc" onclick="deleteTsglInfo();return false;">ɾ��</a></li>
						</logic:equal>
						
						<%--<li><a href="#" class="btn_dc" onclick="setSearchTj();configureExportData();return false;">��������</a></li>
						--%><li><a href="#" class="btn_dc" onclick="tsxxcxExportConfig();return false;">����</a></li>
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
				<table summary="" class="dateline" align="" width="100%">
					<!-- ��ͷ -->
					<thead>
						<tr align="center" style="cursor:hand">
							<td width="5px">
								<input type="checkbox" id="selall" name="selall" onclick="selAll()" />
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
					<!-- ��ͷ end-->
					<!--���� -->
					<logic:iterate name="rsArrList" id="s" indexId="index">
						<tr onclick="rowOnClick(this);" ondblclick="modi1('gyglnew_tsgl.do?method=tsglView',700,500);" style="cursor:hand">
							<logic:iterate id="v" name="s" offset="0" length="1">
								<td align="center" width="5px">
									<input type="checkbox" id="pk_${index }"
									name="primarykey_checkVal" value="${v }"/>
								</td>
							</logic:iterate>
							<!-- ��ʾ��Ϣ -->
							<logic:iterate id="v" name="s" offset="1" length="1">
									<td>
									<a class="name" style="cursor:hand" href="#" onclick="zxsxxView('${v}')" return false ;>
										${v }
									</a>
									</td>
							</logic:iterate>
							
							
							<logic:iterate id="v" name="s" offset="2" length="8">
								<td align="left">
									${v }
								</td>
							</logic:iterate>
							<logic:iterate id="v" name="s" offset="10" length="1">
								<td title="<logic:iterate id="vv" name="s" offset="11" length="1">${vv }</logic:iterate>">${v }</td>
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
				</table>
				<!--��ҳ-->
				<jsp:include flush="true" page="/sjcz/turnpage.jsp?form=gyglnewTsglForm"></jsp:include>
				<!--��ҳend-->
				</div>
			</div>
			<!-- ��ѯ��� end-->
		
		<!-- ��ʾ��Ϣ -->
		<%@ include file="/comm/other/tsxx.jsp"%>
		</html:form>
		
	</body>
</html>