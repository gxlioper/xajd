<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@page import="java.util.List"%>
<%@page import="common.Globals"%>
<%@page import="xsgzgl.gygl.comm.GyglNewInit"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
		<script type='text/javascript' src='/xgxt/dwr/util.js'></script>
		<script type='text/javascript' src="js/uicomm.js"></script>
		<script type='text/javascript' src="js/String.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		
		<script language="javascript" src="js/function.js"></script>
		<script type="text/javascript" src="js/qtsjFunction.js"></script>
		<script type="text/javascript" src="js/commit.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript" src="js/xgutil.js"></script>
		<script language="javascript">
		//��ѯ�����
		function searchRs(){
			allNotEmpThenGo('gyglnew_cwgl_cwgl.do');
		}

		//��ס
		function modi(url,h,w){
			var checkBoxArr = jQuery("input[name='primarykey_checkVal']:checked");
			var num=checkBoxArr.size();
			if(num==1){
				var sfbl=jQuery("input[name='primarykey_checkVal']:checked~input").eq(1).val();
				if(sfbl=="��"){
					alertError('������λ������ס');
					return false;
				}else if(jQuery("input[name='primarykey_checkVal']:checked~input").eq(2).val()!=""){
					alertError('��λ����ס����������ѧ�����ٽ�����ס��');
					return false;
				}else{
					showDialog('��λ��ס', h, w, url + '&pkValue='+checkBoxArr.val());
					return true;
				}
			} else {
				alertInfo('��ѡ��һ��Ҫ������������');
				return false;
			}
		}

		function view(url,h,w){

			
			//showTopWin(url + '&pkValue='+curr_row.getElementsByTagName('input')[0].value,h,w);

			showDialog('�鿴��λ��ϸ��Ϣ', 790, 525, url + '&pkValue='+curr_row.getElementsByTagName('input')[0].value);
		}	

		//��������
		function modibl(){
			var checkBoxArr = document.getElementsByName("primarykey_checkVal");
			var dis = document.getElementsByName("dis");
			var flag = "";
			var num=0;
			for(var i=0;i<checkBoxArr.length;i++){
				if(checkBoxArr[i].checked==true){
					num++;	
					if(dis[i].value=="disabled"){
						flag="ѡ�м�¼�к��в��ɱ����Ĵ�λ����ѡ��δ������δ��ס�Ĵ�λ��"
						break;	
					}				
				}
			}
			if(num==0){
				alertInfo("��ѡ��Ҫ�����ļ�¼��");
			}else{
				if (flag==""){
					//showTopWin('gyglnew_cwgl.do?method=cwwhModibl',350,200);
					showDialog('����������λ��Ϣ', 500, 300, 'gyglnew_cwgl.do?method=cwwhModibl');
					//showTopWin('gyglnew_cwgl.do?method=cwwhModibl&pkValue='+curr_row.getElementsByTagName('input')[0].value,350,200);
					return true;
				}else{
					alertError(flag);
				}		
			}		
		}

		//ɾ��
		function del(){
			var checkBoxArr = document.getElementsByName("primarykey_checkVal");
			var xh = document.getElementsByName("xh");
			var flag = "";
			var num=0;
			for(var i=0;i<checkBoxArr.length;i++){
				if(checkBoxArr[i].checked==true){
					num++;	
					if(xh[i].value!=""){
						alertError("ѡ�м�¼�к�������ס�Ĵ�λ����ѡ��δ��ס�Ĵ�λ����ɾ����");
						return false;
					}	
				}
			}
			if(num==0){
				alertInfo("��ѡ��Ҫ�����ļ�¼��");
			}else{
				if (flag==""){
					batchData('primarykey_checkVal','gyglnew_cwgl.do?method=cwglManage&go=go&doType=del','del');
				}else{
					alertError(flag);
				}		
			}
		}

		function cwdd_submit(){
			if($("rzsj").value==""){
				alertInfo("��ѡ����סʱ�䣡");
				return false;
			}else{
				document.forms[0].action = 'gyglnew_cwgl.do?method=cwglManage&go=go&doType=cwdd';
				document.forms[0].submit();
			}
		}

		function cwdd(){
			var checkBoxArr = document.getElementsByName("primarykey_checkVal");
			var sfbl = document.getElementsByName("sfbl");
			var flag = "";
			var num=0;
			var tr_td=new Array();
			for(var i=0;i<checkBoxArr.length;i++){
				if(checkBoxArr[i].checked==true){
					num++;
					if(sfbl[i].value=="��"){
						flag="ѡ�м�¼�к��б����Ĵ�λ�����ɽ��жԵ���";
					}
					tr_td[tr_td.length]=checkBoxArr[i].parentNode.parentNode.cells;
				}
			}
			if(num!=2){
				alertError("ѡ�д�λ����Ϊ2����ѡ�����Ŵ�λ���в�����");
			}else{
				var xh0 = jQuery(tr_td[0][7]).text().trim();
				var xh1 = jQuery(tr_td[1][7]).text().trim();
				if(<%="bjdm".equals(GyglNewInit.CWFPDX)%>) {
					xh0 = jQuery(tr_td[0][8]).text().trim();
					xh1 = jQuery(tr_td[1][8]).text().trim();
				}
				if(<%="zydm".equals(GyglNewInit.CWFPDX)%>) {
					xh0 = jQuery(tr_td[0][8]).text().trim();
					xh1 = jQuery(tr_td[1][8]).text().trim();
				}
				if (flag==""){
					//�ж�ѧ���Ƿ�Ϊ�գ�����λ�Ƿ���ס
					if(xh0==""&&xh1==""){
						alertInfo("ѡ�д�λ��ѧ����ס������Ե���");
						return false;
					}
					//�ж��Ա��Ƿ�һ��
					if(jQuery(tr_td[0][4]).text().trim() != jQuery(tr_td[1][4]).text().trim()){
						alertError("ѡ�д�λ�Ա�ͬ���޷��Ե���");
						return false;
					}
					var confirmValue="ѡ�д�λ������ͬ�����潫�Զ����´�λ������";;
					//�жϴ�λ�����꼶��ѧԺ���༶�Ƿ�һ��
					if(<%="bjdm".equals(GyglNewInit.CWFPDX)%> 
							&& tr_td[0][5].innerHTML==tr_td[1][5].innerHTML 
							&& tr_td[0][6].innerHTML==tr_td[1][6].innerHTML 
							&& tr_td[0][7].innerHTML==tr_td[1][7].innerHTML){
						confirmValue="";
					}
					if(<%="zydm".equals(GyglNewInit.CWFPDX)%> 
						&& tr_td[0][5].innerHTML==tr_td[1][5].innerHTML 
						&& tr_td[0][6].innerHTML==tr_td[1][6].innerHTML 
						&& tr_td[0][7].innerHTML==tr_td[1][7].innerHTML){
					confirmValue="";
					}

					if(<%="xydm".equals(GyglNewInit.CWFPDX)%>
							&& tr_td[0][5].innerHTML==tr_td[1][5].innerHTML 
							&& tr_td[0][6].innerHTML==tr_td[1][6].innerHTML){
						confirmValue="";
					}
					
					$("submit_bz").innerHTML=confirmValue;
					var idList = "";        

					jQuery("input[type='checkbox'][name='primarykey_checkVal']:checked").each(function(){
						idList += jQuery(this).val() + ',';        
					});
					url = "gyglnew_cwgl.do?method=cwDd&idList="+idList;
					url1 =encodeURI(encodeURI(url));
					showDialog('��λ�Ե�', 580, 250, url1);
					//tipsWindown("��λ�Ե�","id:tempDiv","400","200","true","","true","id");
				}else{
					alertError(flag);
				}		
			}		
		}
		

		/*
		���ݵ�����
		*/	
		function cwrzglImpAndChkData(){
			var realTable = "";
			var tableName = "";
			var sty = "toolbar=no,location=no,directories=no,status=yes";
			sty += ",menubar=no,scrollbars=yes,resizable=yes,width=600,height=400,top=100";
			sty += ",left=200";
			if($("realTable")) realTable = document.getElementById("realTable").value;
			if($("tableName")) tableName = document.getElementById("tableName").value;
			url = 'gyglnew_cwrzgl.do?method=importData';
			url += "&realTable=" + realTable;
			url += "&tableName=" + tableName;
			//window.open(url,'',sty);
			showDialog('��������', 600, 300, url);
		}

		//����
		function plts(){
			var checkBoxArr = document.getElementsByName("primarykey_checkVal");
			var xh = document.getElementsByName("xh");
			var flag = "";
			var num=0;
			for(var i=0;i<checkBoxArr.length;i++){
				if(checkBoxArr[i].checked==true){
					num++;	
					if(xh[i].value==""){
						alertError("ѡ�м�¼�к���δ��ס�Ĵ�λ����ѡ������ס�Ĵ�λ�������ޣ�");
						return false;
					}	
				}
			}
			if(num==0){
				var searchTjstr = document.getElementById("searchTjstr").value;
				if(searchTjstr==''){
					alertError("��ѡ��ɸѡ��������ѯ������������ޣ�");
					return false;
				}
			}
			showDialog('����', 730, 365, 'gyglnew_cwgl.do?method=cwglPlts');
			
		}

		function cwcsh(){
			var checkBoxArr = document.getElementsByName("primarykey_checkVal");
			var xh = document.getElementsByName("xh");
			var xydm = document.getElementsByName("dis");
			var flag = "";
			var num=0;
			for(var i=0;i<checkBoxArr.length;i++){
				if(checkBoxArr[i].checked==true){
					num++;	
					if(xh[i].value!=""){
						alertError("ѡ�м�¼�к�������ס�Ĵ�λ����ѡ��δ��ס�Ĵ�λ���д�λ������ʼ����");
						return false;
					}else if(xydm[i].value=="") {
						alertError("ѡ�м�¼�к���δ����Ĵ�λ��������г�ʼ��������");
						return false;
					}
				}
			}
			//showTopWin('gyglnew_cwgl.do?method=cwglCwssInit',600,300);
			showDialog('��λ��ʼ��', 600, 300, 'gyglnew_cwgl.do?method=cwglCwssInit');
		}

		//����ѧ�Ų鿴
		function zxsxxView(xh){
			
				var pkValue=xh;
				var url="xsxx_tygl.do?method=ckZxsxx";
				url+="&xh="+pkValue;
				var width=850;
				//showTopWin(url,850,640);
				showDialog('�鿴ѧ����ϸ��Ϣ',850,640,url);
		}
				
		function cwxxglExportConfig() {
		//DCCLBH�������ܱ��,ִ�е������� 
		customExport("gyglnew_cwgl_cwgl.do", cwxxglExportData);
		}
		
	
		
		// ��������
		function cwxxglExportData() {
			setSearchTj();//���ø߼���ѯ����
			var url = "gyglnew_cwgl.do?method=cwxxglExportData&dcclbh=" + "gyglnew_cwgl_cwgl.do";//dcclbh,�������ܱ��
			url = addSuperSearchParams(url);//���ø߼���ѯ����
			jQuery("form").eq(0).attr("action", url);
			jQuery("form").eq(0).submit();
		}
	
		
		</script>
	</head>
	<body>
		<html:form action="/gyglnew_cwgl" method="post">
			<!-- ������ -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<input type="hidden" id="searchTjstr" value="<bean:write name="searchTjstr"/>"/>
			<input type="hidden" id="yrzcws" value="<bean:write name="yrzcws"/>"/>
			<input type="hidden" id="wrzcws" value="<bean:write name="wrzcws"/>"/>
			<!-- ������ -->
			<div class="tab_cur" >
				<p class="location">
					<em>���ĵ�ǰλ��:</em><a>${title }</a>
				</p>
				<p class="help">
					<a href="#" onclick="return false;" onmousedown ="showHelpDiv()">ʹ�ð���</a>
				</p>
			</div>
			<!-- ��ʾ��Ϣ end-->
			<div class="prompt" id="div_help" style="display: none">
				<h3>
					<span>��ʾ��</span>
				</h3>
				<p>
					��ɾ�������ɲ���δ��ס�Ĵ�λ������ס��ʱϵͳ�����ѧ�������Զ����´�λ������<br/>���Ե������ɲ���2����ͬ�Ա�����������1������ס�Ĵ�λ��
				</p>
				<a class="close" title="����"  onclick="this.parentNode.style.display='none';"></a>
			</div>
			<!-- ��ʾ��Ϣ end-->	
			<div class="toolbox" id="dgncz">
				<!-- ��ť -->
				<div class="buttonbox">
					<ul>
						<logic:equal value="yes" name="writeAble">
						<li><a href="#" onclick="showDialog('���Ӵ�λ��Ϣ', 600, 330, 'gyglnew_cwgl.do?method=cwwhAdd');return false;" class="btn_zj"> ���� </a></li><%--
						<li><a href="#" onclick="modi('gyglnew_cwgl.do?method=cwwhModi',800,600);return false;" class="btn_xg"> �޸� </a></li>
						--%><li><a href="#" onclick="del();return false;" class="btn_sc"> ɾ�� </a></li>
						<li><a href="#" onclick="modibl();return false;" class="btn_sz"> �������� </a></li>
						<li><a href="#" onclick="modi('gyglnew_cwgl.do?method=cwwhModi',800,450);return false;" class="btn_sh"> ��ס </a></li>
						<li><a href="#" onclick="plts();return false;" class="btn_shbtg">����</a></li>
						<li><a href="#" onclick="cwdd();return false;" class="btn_sx"> ��λ�Ե� </a></li>						
						<li><a href="#" onclick="cwcsh();return false;" class="btn_csh">��λ������ʼ��</a></li>
						<li><a href="#" onclick="cwrzglImpAndChkData();return false;" class="btn_dr">��������</a></li>
						</logic:equal>
						<%if(Globals.XXDM_zjgmzyjsxy.equals(Base.xxdm)||Globals.XXDM_ZJCMXY.equals(Base.xxdm)){ %>
							<li><a href="#" onclick="setSearchTj();configureExportDataZdy('${path}');return false;" class="btn_dc">��������</a></li>
						<%} else { %>
							<li><a href="#" class="btn_dc" onclick="cwxxglExportConfig();return false;">����</a></li>
						<%} %>
					</ul>
				</div>
				<!-- �������� -->
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- �������� end-->
			</div>
				<div class="formbox">
					<h3 class="datetitle_01">
						<span>
							��ѯ���&nbsp;&nbsp;<font color="blue">������ͷ��������;˫����λ��ʾ��λ������Ϣ</font>
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
									ondblclick="view('gyglnew_cwgl.do?method=cwwhView',900,700);">
									<td>								
										<input type="checkbox" name="primarykey_checkVal" id="pkV"
											value="<logic:iterate id="v" name="s" offset="0" length="1">${v }</logic:iterate>" />
										<input type="hidden" name="dis" id="dis"
											value="<logic:iterate id="v" name="s" offset="1" length="1">${v }</logic:iterate>" />
										
										<!-- ����hidden����ͬ�Ĵ�λ���䷽ʽsfbl��xh�ֶ��ڽ�����Ĳ�ͬλ�� -->
										<%if("xydm".equals(GyglNewInit.CWFPDX)) {%>	
										<input type="hidden" name="sfbl" id="sfbl"
											value="<logic:iterate id="v" name="s" offset="10" length="1">${v }</logic:iterate>" />
										<input type="hidden" name="xh" id="xh"
											value="<logic:iterate id="v" name="s" offset="8" length="1">${v }</logic:iterate>" />
										<%}else{ %>
										<input type="hidden" name="sfbl" id="sfbl"
											value="<logic:iterate id="v" name="s" offset="11" length="1">${v }</logic:iterate>" />
										<input type="hidden" name="xh" id="xh"
											value="<logic:iterate id="v" name="s" offset="9" length="1">${v }</logic:iterate>" />
										<%} %>	
										
									</td>
									<%if("xydm".equals(GyglNewInit.CWFPDX)) {%>	
										<logic:iterate id="v" name="s" offset="2" length="6" >
											<td>${v }</td>
										</logic:iterate>
										<logic:iterate id="v" name="s" offset="8" length="1">
											<td><a class="name" href="#" onclick="zxsxxView('${v }')">${v }</a></td>
										</logic:iterate>
										<logic:iterate id="v" name="s" offset="9">
											<td>${v }</td>
										</logic:iterate>
									<%}else{ %>
										<logic:iterate id="v" name="s" offset="2" length="7">
											<td>${v }</td>
										</logic:iterate>
										<logic:iterate id="v" name="s" offset="9" length="1">
										<td><a class="name" href="#" onclick="zxsxxView('${v }')">${v }</a></td>
										</logic:iterate>
										<logic:iterate id="v" name="s" offset="10">
											<td>${v }</td>
										</logic:iterate>
										
									<%} %>	
									
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
		   	 	<jsp:include flush="true" page="/sjcz/turnpage.jsp?form=gyglnewCwglForm"></jsp:include>
				<!--��ҳ��ʾ-->
			</div>
			
			
			<div id="tempDiv" style="display: none">
				<div class="open_win01">
					<table align="center" class="formlist">
						<thead>
							<tr>
								<th colspan="2">
									<span>�Ե�ʱ��</span>
								</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<th width="60px">
									��סʱ��
								</th>
								<td>
									<input type="text" id="rzsj" name="rzsj" onkeypress="onlyBackSpace(this,event);"
										onclick="return showCalendar(this.id,'yyyy-MM-dd')"/>
								</td>
							</tr>
							<tr>
								<th>
									��ע
								</th>
								<td>
									<input type="text" id="bz" name="bz" style="width: 95%;height: 50px" />
								</td>
							</tr>
							<tr>
								<th>
									ע��
								</th>
								<td>
									<div id="submit_bz" class="bz" style="color: red;"></div>
								</td>
							</tr>
						</tbody>
						<tfoot>
							<tr>
								<td colspan="2">
									
									<div class="btn">
										<button type="button" name="����"  onclick="cwdd_submit()">
											���� 
										</button>
										<button type="button" name="ȡ��"  onclick="closeWindown();return false;">
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
			<script>
				alertInfo('${message}');
			</script>
		</logic:present>
		<%@ include file="/comm/delMessage.jsp"%>
		<%@ include file="/comm/loading.jsp"%>
	</body>
</html>
