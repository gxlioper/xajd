<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@page import="java.util.List"%>
<%@page import="xsgzgl.gygl.comm.GyglNewInit"%>
<%@page import="common.Globals"%>
<%@page import="xgxt.action.Base"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%--<%@ include file="/syscommon/pagehead_V4.ini"%>
		--%>
		<%@ include file="/syscommon/head.ini"%>
		<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
		<script type='text/javascript' src='/xgxt/dwr/util.js'></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type='text/javascript' src="js/uicomm.js"></script>
		<script type='text/javascript' src="js/String.js"></script>
		
		<script language="javascript" src="js/function.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript" src="js/qtsjFunction.js"></script>
		<script type="text/javascript" src="js/commit.js"></script>
		<script type="text/javascript" src="js/xgutil.js"></script>
		<script language="javascript">
		//��ѯ�����
		function searchRs(){
			allNotEmpThenGo('gyglnew_zsxxgl_zsxxgl.do');
		}
		
		function modi(url,h,w){
			if(curr_row != null){
				if(curr_row.getElementsByTagName('input')[2].value =="��"){
					alertError('������λ������ס');
					return false;					
				}else if(curr_row.getElementsByTagName('input')[3].value !=""){
					alertError('��λ����ס����������ѧ�����ٽ�����ס��');
					return false;				
				}else{
					//showTopWin(url + '&pkValue='+curr_row.getElementsByTagName('input')[0].value,h,w);
					showDialog("��λ��ס", h, w, url + '&pkValue='+curr_row.getElementsByTagName('input')[0].value);
					return true;
				}
			}else{
				alertInfo('��ѡ��Ҫ������������');
				return false;
			}
		}

		function view(url,h,w){
			showDialog("��λ������Ϣ",h,w,url + '&pkValue='+curr_row.getElementsByTagName('input')[0].value);
		}	

		function cwdd_submit(){
			if($("rzsj").value==""){
				alertInfo("��ѡ����סʱ�䣡");
				return false;
			}else{
				document.forms[0].action = 'gyglnew_zsxxgl.do?method=zsxxglManage&go=go&doType=cwdd';
				document.forms[0].submit();
			}
		}
		
		function cwdd(){
			var checkBoxArr = document.getElementsByName("primarykey_checkVal");
			var sfbl = document.getElementsByName("sfbl");
			var njxybj = document.getElementsByName("njxybj");
			var index=new Array();
			var flag = "";
			var num=0;
			var tr_td=new Array();
			for(var i=0;i<checkBoxArr.length;i++){
				if(checkBoxArr[i].checked==true){
					num++;
					if(sfbl[i].value=="��"){
						flag="ѡ�м�¼�к��б����Ĵ�λ�����ɽ��жԵ���";
					}
					index[index.length]=njxybj[i].value;
					tr_td[tr_td.length]=checkBoxArr[i].parentNode.parentNode.cells;
				}
			}
			if(num!=2){
				alertError("ѡ�д�λ����Ϊ2����ѡ�����Ŵ�λ���в�����");
			}else{
				var xh0 = jQuery(tr_td[0][5]).text().trim();
				var xh1 = jQuery(tr_td[1][5]).text().trim();
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
					var confirmValue="ѡ�д�λ������ͬ�����潫�Զ����´�λ������";
					//�жϴ�λ�����꼶��ѧԺ���༶�Ƿ�һ��
					if(index[0]==index[1]){
						confirmValue="";
					}
					
					$("submit_bz").innerHTML=confirmValue;
					var idList = "";        

					jQuery("input[type='checkbox'][name='primarykey_checkVal']:checked").each(function(){
						idList += jQuery(this).val() + ',';        
					});

					url = "gyglnew_cwgl.do?method=cwDd&idList="+idList;
					showDialog('��λ�Ե�', 580, 250, url);
					//url = "gyglnew_zsxxgl.do?method=zsxxCwdd&idList="+idList;
					//showDialog('', 400, 250, url);
					//tipsWindown("��λ�Ե�","id:tempDiv","400","200","true","","true","id");
				}else{
					alertError(flag);
				}		
			}			
		}

		//����ѧ�Ų鿴
		function zxsxxView(xh){
			
				var pkValue=xh;
				var url="xsxx_tygl.do?method=ckZxsxx";
				url+="&xh="+pkValue;
				var width=850;
				showTopWin(url,850,640);
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
			//showTopWin(url,600,500);
			//refreshForm(url);
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
			//showTopWin('gyglnew_cwgl.do?method=cwglPlts',700,500);
			showDialog("����", 730, 365, 'gyglnew_cwgl.do?method=cwglPlts');
		}
		//������У
		function pllx(){
			showDialog("������У����", 760, 550, 'gyglnew_zsxxgl.do?method=plLx');
		}
		/**
		  * ������ͷ��
		  * @return
		  */
		 function gygl_exp_ctk(){
			 
			var url = "gyglnew_zsxxgl.do?method=expCtk";
		 	document.forms[0].action = url;
		 	document.forms[0].target = "_blank";
		 	document.forms[0].submit();
		 	document.forms[0].target = "_self";
			
		 }
		
		function zsxxglExportConfig() {
			customExport("gyglnew_zsxxgl_zsxxgl.do", zsxxglExportData);
		}
		//���ְཻͨҵ����ѧԺ
		function zsxxglExportConfigForgsjt() {
			customExport("xg_xajt_gxhzsdc.do", zsxxglExportDatagsjt);
		}
		function gxexport(){
			setSearchTj();
			var map = getSuperSearch();
			if(map["searchTj"].indexOf("searchModel.search_tj_ld")==-1){
				return alertError("��ѡ�����¥��!");
			}
			if(jQuery("#ld_ul").find(".selectedValue").length >1){
				return alertError("ֻ��ѡ��һ��¥��!");
			}
			document.forms[0].action = 'gyglnew_zsxxgl.do?method=geExport';
			document.forms[0].submit();
		}
			
		// ��������
		function zsxxglExportData() {
			setSearchTj();//���ø߼���ѯ����
			var url = "gyglnew_zsxxgl.do?method=zsxxglExportData&dcclbh=" + "gyglnew_zsxxgl_zsxxgl.do";//dcclbh,�������ܱ��
			url = addSuperSearchParams(url);//���ø߼���ѯ����
			jQuery("form").eq(0).attr("action", url);
			jQuery("form").eq(0).submit();
		}

		// ���ཻͨ���Ի���������
		function zsxxglExportDatagsjt() {
			setSearchTj();//���ø߼���ѯ����
			var url = "gyglnew_zsxxgl.do?method=zsxxglExportDataForGsjt&dcclbh=" + "xg_xajt_gxhzsdc.do";//dcclbh,�������ܱ��
			url = addSuperSearchParams(url);//���ø߼���ѯ����
			jQuery("form").eq(0).attr("action", url);
			jQuery("form").eq(0).submit();
		}

		// �°�鿴������
		function zxsxxView(xh) {
			showDialog("ѧ����Ϣ��ѯ", 850, 500, "xsxx_xsxxgl.do?method=xsxxglCk&xh=" + xh
					+ "&xs");
		}

		
		function wjxxView(index){

			var xh = jQuery("#xh"+index).val();
			var n = jQuery("#n"+index).val();
			if(n == 0) {
				alertError("������Υ�������");
				return false;
			}	
			showDialog("Υ����ϸ��Ϣ",690,400,"gyglnew_zsxxgl.do?method=wjxx&xh="+xh);
		}	
		
		//�޸ı�ע
		function updateBz(){
			var pkValues = jQuery("input[name='primarykey_checkVal']:checked").map(function(){ 
				return jQuery(this).val();
			}).get(); 
			if(pkValues.length==0){
				alertError("������ѡ��һ����¼�޸ı�ע��");
			}else if(pkValues.length==1){
				showDialog("�޸ı�ע", 800, 400, 'gyglnew_zsxxgl.do?method=updateBz&pkValue='+pkValues[0]);
			}else{
				showDialog("�����޸ı�ע", 600, 200, 'gyglnew_zsxxgl.do?method=updateBzBatch&pkValues='+pkValues);
			}
		}

		//���칤�̴�ѧ�����һ��ˣ�
		function qshr(){
			var pkValues = jQuery("input[name='primarykey_checkVal']:checked");
			if(pkValues.length != 1){
				showAlertDivLayer("��ѡ��һ����¼");				
			}else{
				var checkbox = pkValues[0];
				var lddm = jQuery(checkbox).siblings("[name='lddm']").val();
				var qsh = jQuery(checkbox).siblings("[name='qsh']").val();
				var cwh = jQuery(checkbox).siblings("[name='cwh']").val();
				showDialog("��ѡ��ѧ��",800,600,'xsxx_xsgl.do?method=showStudentsForQshr&lddm='+lddm+'&qsh='+qsh+'&cwh='+cwh);
			}
		}
		
		</script>
	</head>
	<body>
		<html:form action="/gyglnew_zsxxgl" method="post">
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
					����ס��ʱϵͳ�����ѧ�������Զ����´�λ���������Ե������ɲ���2����ͬ�Ա�����������1������ס�Ĵ�λ��
				</p>
				<a class="close" title="����" onclick="this.parentNode.style.display='none';"></a>
			</div>
			<!-- ��ʾ��Ϣ end-->	
			
			<div class="toolbox" id="dgncz">
				<!-- ��ť -->
				<div class="buttonbox">
					<ul>
						<logic:equal value="yes" name="writeAble">
							<logic:equal value="true" name="czkz">
							<li><a href="#" onclick="modi('gyglnew_cwgl.do?method=cwwhModi',800,450);return false;" class="btn_sh" > ��ס </a></li>
							<li><a href="#" class="btn_shbtg" onclick="plts();return false;">����</a></li>
							<li><a href="#" onclick="cwdd();return false;" class="btn_sx"> ��λ�Ե� </a></li>
							<logic:equal value="true" name="pllxtx">
								<li><a href="#" class="btn_shbtg" onclick="pllx();return false;">������У����</a></li>
							</logic:equal>
							<li><a href="#" onclick="cwrzglImpAndChkData();return false;" class="btn_dr">��������</a></li>
							</logic:equal>
							<logic:equal value="false" name="czkz">
							<li><a href="#"  class="btn_sh" title="���ڲ���ʱ�䷶Χ��" > <font color="gray">��ס</font> </a></li>
							<li><a href="#" class="btn_shbtg"  title="���ڲ���ʱ�䷶Χ��"><font color="gray">����</font></a></li>
							<li><a href="#"  class="btn_sx" title="���ڲ���ʱ�䷶Χ��"> <font color="gray">��λ�Ե�</font> </a></li>
							</logic:equal>
						</logic:equal>
						
						<%if(Globals.XXDM_zjgmzyjsxy.equals(Base.xxdm)){ %>
							<logic:equal value="yes" name="writeAble">
							<li><a href="#" class="btn_dc" onclick="setSearchTj();configureExportDataZdy('${path_dc}');return false;">�����������</a></li>
							<li><a href="#" onclick="setSearchTj();configureExportDataZdy('${path}');return false;" class="btn_dc">�����������</a></li>
							</logic:equal>
						<%} else { %>
							
							<li><a href="#" onclick="zsxxglExportConfig();return false;" class="btn_dc">����</a></li>
							<%--���ཻͨһ�жലλ���Ի��Զ��嵼����ͨ�õ�����ܵ�����--%>
							<logic:equal value="13519" name="xxdm">
								<li><a href="#" onclick="zsxxglExportConfigForgsjt();return false;" class="btn_dc">һ�жലλ����</a></li>
							</logic:equal>
							<li><a href="#" class="btn_dc" onclick="setSearchTj();configureExportDataZdy('gyglnew_zsxxgl_zsxxgl.do');return false;">�����������</a></li>			
						<%} %>
						<%if(Globals.XXDM_XZGYZYJSXY.equals(Base.xxdm)){ %>
							<logic:equal value="yes" name="writeAble">
							<li><a href="#" class="btn_dc" onclick="setSearchTj();gygl_exp_ctk();return false;">������ͷ��</a></li>
							</logic:equal>
						<%} %>
						<li><a href="#" onclick="gxexport();return false;" class="btn_dc">������������</a></li>
						<li><a href="javascript:void(0)" onclick="updateBz();return false;" class="btn_xg">�޸ı�ע</a></li>
						<!-- ���칤�̴�ѧ -->
							<logic:equal value="11799" name="xxdm">
								<li><a href="#" class="btn_sx" onclick="qshr();return false;">���һ���</a></li>
							</logic:equal>
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
							<logic:iterate name="rs" id="s" indexId="index">
								<tr onclick="rowOnClick(this);" style="cursor:hand" 
									ondblclick="view('gyglnew_cwgl.do?method=cwwhView',800,500);" 
									title="�����꼶:<logic:iterate id="v" name="s" offset="11" length="1">${v };</logic:iterate>����<bean:message key="lable.xb" />:<logic:iterate id="v" name="s" offset="14" length="1">${v }</logic:iterate><%if("bjdm".equals(GyglNewInit.CWFPDX)){%>;�����༶:<logic:iterate id="v" name="s" offset="15" length="1">${v }</logic:iterate><%} %><%else if("zydm".equals(GyglNewInit.CWFPDX)){%>;����רҵ:<logic:iterate id="v" name="s" offset="16" length="1">${v }</logic:iterate><%} %>">
									<td>								
										<input type="checkbox" name="primarykey_checkVal" id="pkV"
											value="<logic:iterate id="v" name="s" offset="0" length="1">${v }</logic:iterate>" />
										<input type="hidden" name="dis" id="dis"
											value="<logic:iterate id="v" name="s" offset="1" length="1">${v }</logic:iterate>" />
										<input type="hidden" name="sfbl" id="sfbl"
											value="<logic:iterate id="v" name="s" offset="10" length="1">${v }</logic:iterate>" />
										<input type="hidden" name="xh" id="xh${index}"
											value="<logic:iterate id="v" name="s" offset="6" length="1">${v }</logic:iterate>" />
										<input type="hidden" name="n" id="n${index}"
											value="<logic:iterate id="v" name="s" offset="19" length="1">${v }</logic:iterate>" />
										<input type="hidden" name="njxybj" id="njxybj"
											value="<logic:iterate id="v" name="s" offset="11" length="3">${v }</logic:iterate>"
										/>
										<input type="hidden" name="lddm" value="<logic:iterate id="v" name="s" offset="19" length="1">${v }</logic:iterate>" />
										<input type="hidden" name="qsh" value="<logic:iterate id="v" name="s" offset="3" length="1">${v }</logic:iterate>" />
										<input type="hidden" name="cwh" value="<logic:iterate id="v" name="s" offset="4" length="1">${v }</logic:iterate>" />
									</td>
									<logic:iterate id="v" name="s" offset="2" length="6" indexId="indexId">
										<td>
											<logic:equal name="indexId" value="6">
												<a class="name" href='javascript:void(0);' onclick='zxsxxView("${v }");return false;' >${v }</a>
											</logic:equal>
											<logic:notEqual name="indexId" value="6">
												${v }
											</logic:notEqual>
										</td>
									</logic:iterate>
									<logic:equal name="xxdm" value="12216">
										<logic:iterate id="v" name="s" offset="20" length="1" >
											<td>${v }</td>
										</logic:iterate>
									</logic:equal>
									
									<logic:iterate id="v" name="s" offset="8" length="3" indexId="indexId">
										<td>${v }</td>
									</logic:iterate>
									
										<logic:iterate id="v" name="s" offset="17" length="1">
											<td title="<logic:iterate id="vv" name="s" offset="18" length="1">${vv }</logic:iterate>">${v }</td>
										</logic:iterate>
										<logic:equal name="xxdm" value="13033">
											<logic:iterate id="v" name="s" offset="18" length="1">
												<td title="<logic:iterate id="v" name="s" offset="19" length="1">${v }</logic:iterate>">
													<a class="name" href='javascript:void(0);' onclick='wjxxView(${index});return false;' >${v }</a>
												</td>
											</logic:iterate>
										</logic:equal>
										<logic:equal name="xxdm" value="10868">
											<logic:iterate id="v" name="s" offset="19" length="3">
												<td>
													${v }
												</td>
											</logic:iterate>
										</logic:equal>
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
		   	 	<jsp:include flush="true" page="/sjcz/turnpage.jsp?form=gyglnewZsxxglForm"></jsp:include>
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
