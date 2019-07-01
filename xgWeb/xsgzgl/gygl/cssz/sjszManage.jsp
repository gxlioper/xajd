<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@page import="java.util.List"%>
<%@page import="xgxt.utils.Pages"%>
<%@page import="xsgzgl.gygl.cssz.CsszForm"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type='text/javascript' src="js/uicomm.js"></script>
		<script type='text/javascript' src="js/String.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		
		<script language="javascript" src="js/function.js"></script>
		<script type="text/javascript" src="js/qtsjFunction.js"></script>
		<script type="text/javascript" src="js/commit.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script language="javascript">
			function saveData(url,sfqy){
				var form = document.forms[0];
				var kssj = jQuery('#kssj').val();
				var jssj = jQuery('#jssj').val();
				if(jQuery('#kssj').val()=="" && jQuery('#jssj').val()=="" && sfqy=="yes"){
					alertInfo('��ʼʱ�������ʱ�䣬������дһ����ܿ�����ȡ����ס����ť��');
					return false;
				}
				
				if(jQuery('#kssj').val()!="" && jQuery('#jssj').val()!="" && jQuery('#kssj').val()>= jQuery('#jssj').val()){
					alertError('����ʱ�������ڿ�ʼʱ�䣡');
					return false;
				}
				url += "&kssj=" + kssj;
				url += "&jssj=" + jssj;
				
				form.action = url;
				form.submit();
			}
			
			//��ѯ�����
			function searchRs(){
				allNotEmpThenGo('gyglnew_cssz_sjsz.do');
			}
			
			// ��������
			function batchOption(sfqy){
				var size = jQuery("input[type='checkbox'][name='primarykey_cbv']:checked").size();
				var idList = "";        

				jQuery("input[type='checkbox'][name='primarykey_cbv']:checked").each(function(){
					//alert(this.val());
					//alert(jQuery(this).val())
					idList += jQuery(this).val() + ',';        
					// alert(jQuery("input[type='checkbox'][name='primarykey_cbv']:checked").val());
				});
				//alert(jQuery("input[type='checkbox'][name='primarykey_cbv']:checked").val());
				//alert(idList);
				if(size == 0){
					alertInfo('��ѡ��Ҫ������¼��');
					return false;
				}
				
				if('yes' == sfqy){
					//tipsWindown("����ʱ������","id:tempDiv","350","150","true","","true","id");
					//var form = document.forms[0];
					var url = "gyglnew_cssz.do?method=qySjsz&idList="+idList;
					//form.action = url;
					//form.submit();
					showDialog("����ʱ������",450,320,url);
				}else{
					var ts = "��ѡ" + size + "����¼����״̬��Ϊ��ȷ��ѡ��"; 
					confirmInfo(ts, function(t){
						if(t == 'ok'){
							saveData('gyglnew_cssz.do?method=sjszManage&sfqy=no&doType=xg');
						}
					});	
				}
			}
		</script>
	</head>
	<body>
		<html:form action="/gyglnew_cssz" method="post">
			<!-- ������ -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<div class="tab_cur" >
				<p class="location">
					<em>���ĵ�ǰλ��:</em><a>${title }</a>
				</p>
				<p class="help" >
					<a href="#" onclick="return false;" onmousedown ="showHelpDiv();">ʹ�ð���</a>
				</p>
			</div>
			<!-- ��ʾ��Ϣ end-->
			<div class="prompt" id="div_help" style="display: none">
				<h3>
					<span>��ʾ��</span>
				</h3>
				<p>
					�˹�����;Ϊ���á���λ��ס�����еġ�ȡ����ס����ť����ʹ��Ȩ�ޣ�����״̬Ϊ���ǡ��������õ�ʱ����ڣ�<br/>���꼶<bean:message key="lable.xsgzyxpzxy" />�ɽ��С�ȡ����ס��������
					��ѡ����꼶<bean:message key="lable.xsgzyxpzxy" />���������á����á��򡰲����á���
				</p>
				<a class="close" title="����"
				   onclick="this.parentNode.style.display='none';"></a>
			</div>
			<!-- ��ʾ��Ϣ end-->	
			<div class="toolbox" id="dgncz">
				<!-- ��ť -->
				<logic:equal value="yes" name="writeAble">
				<div class="buttonbox">
					<ul>
						<li><a href="#" onclick="batchOption('yes');return false;" class="btn_shtg">����</a></li>
						<li><a href="#" onclick="batchOption('no');return false;" class="btn_shbtg">������</a></li>
					</ul>
				</div>
				</logic:equal>
			</div>
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
								<tr onclick="rowOnClick(this);" style="cursor:hand">
									<td>								
										<input type="checkbox" name="primarykey_cbv" id="pkV"
											value="<logic:iterate id="v" name="s" offset="0" length="1">${v }</logic:iterate>" />
										
									</td>
									<logic:iterate id="v" name="s" offset="1" length="8">
										<td>${v }</td>
									</logic:iterate>
								</tr>
							</logic:iterate>
							<%
							int rsNum = ((List)request.getAttribute("rs")).size();
							CsszForm form = (CsszForm)request.getAttribute("gyglnewCsszForm");
							int pageSize = form.getPages().getPageSize();
							if(rsNum < pageSize){
								for(int i=0; i<(pageSize-rsNum); i++){
							%>
							<tr>
								<td>
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
		   	 	<jsp:include flush="true" page="/sjcz/turnpage.jsp?form=gyglnewCsszForm"></jsp:include>
				<!--��ҳ��ʾ-->
			</div>
			
			<div id="tempDiv" style="display: none">
				<div class="open_win01">
					<table align="center" class="formlist">
						<thead>
							<tr>
								<th colspan="2">
									<span>��ѡ��ȡ����ס����ť�ɲ���ʱ��</span>
								</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<th>
									<span class="red">*</span>��ʼʱ��
								</th>
								<td>
									<input type="text" id="kssj" name="kssj" onkeypress="onlyBackSpace(this,event);"
										onclick="return showCalendar(this.id,'yyyy-MM-dd HH:mm:ss')"/>
								</td>
							</tr>
							<tr>
								<th>
									<span class="red">*</span>����ʱ��
								</th>
								<td>
									<input type="text" id="jssj" name="jssj" onkeypress="onlyBackSpace(this,event);"
										onclick="return showCalendar(this.id,'yyyy-MM-dd HH:mm:ss')"/>
								</td>
							</tr>
						</tbody>
						<tfoot>
							<tr>
								<td colspan="2">
									<div class="bz">
										"<span class="red">*</span>"Ϊ������
									</div>
									<div class="btn">
										<button type="button" name="����" onclick="saveData('gyglnew_cssz.do?method=sjszManage&sfqy=yes&doType=xg','yes');return false;">
											�� ��
										</button>
										<button type="button" name="ȡ��" onclick="closeWindown();return false;">
											ȡ ��
										</button>
									</div>
								</td>
							</tr>
						</tfoot>
					</table>
				</div>
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
