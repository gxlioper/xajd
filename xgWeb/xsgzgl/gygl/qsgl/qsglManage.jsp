<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@page import="java.util.List"%>
<%@page import="xgxt.utils.Pages"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type='text/javascript' src="js/uicomm.js"></script>
		<script type='text/javascript' src="js/String.js"></script>
		<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
		<script type='text/javascript' src='/xgxt/dwr/util.js'></script>
		
		<script language="javascript" src="js/function.js"></script>
		<script type="text/javascript" src="js/qtsjFunction.js"></script>
		<script type="text/javascript" src="js/commit.js"></script>
		<script type="text/javascript" src="js/comm/message.js"></script>
		<script language="javascript">
		function modi(url,h,w){
			if(curr_row != null){
				var flag = true;
				var sTitle = "�޸�������Ϣ";
				if(url == "gyglnew_qsgl.do?method=qswhView"){
					flag = false;
					sTitle = "�鿴������Ϣ";
				}
				if(curr_row.getElementsByTagName('input')[1].value != 'ȫ��' && flag){
					alertError('����������ס���ݲ����޸���Ϣ��');
					return false;
				}
				
				//showTopWin(url + '&pkValue='+curr_row.getElementsByTagName('input')[0].value,h,w);
				showDialog(sTitle, w, h, url + '&pkValue='+curr_row.getElementsByTagName('input')[0].value);
				return true;
			}else{
				alertError('��ѡ��Ҫ�����������У�');
				return false;
			}
		}
		
		//��ѯ�����
		function searchRs(){
			allNotEmpThenGo('gyglnew_qsgl_qsgl.do?doType=go');
		}

		function importUpdateQsxx(){
			var realTable = "";
			var tableName = "";
			var sty = "toolbar=no,location=no,directories=no,status=yes";
			sty += ",menubar=no,scrollbars=yes,resizable=yes,width=600,height=400,top=100";
			sty += ",left=200";
			if($("realTable")) realTable = document.getElementById("realTable").value;
			if($("tableName")) tableName = document.getElementById("tableName").value;
			url = 'gyglnew_qsgl.do?method=importData';
			url += "&realTable=" + realTable;
			url += "&tableName=" + tableName;
			//showTopWin(url,600,500);
			//refreshForm(url);
			//window.open(url,'',sty);
			showDialog('�������������Ϣ',560,250,url);
		}

		function qscsh(){
			var checkBoxArr = document.getElementsByName("primarykey_cbv");
			var dis = document.getElementsByName("dis");
			var flag = "";
			var num=0;
			for(var i=0;i<checkBoxArr.length;i++){
				if(checkBoxArr[i].checked==true){
					num++;	
					if(dis[i].value!="ȫ��"){
						alertError("ѡ�м�¼�к�������ס�Ĵ�λ����ѡ��δ��ס�Ĵ�λ���д�λ������ʼ����");
						return false;
					}	
				}
			}
			//showTopWin('gyglnew_qsgl.do?method=qsglQsssInit',600,250);
			showDialog('����������ʼ��', 520, 270, 'gyglnew_qsgl.do?method=qsglQsssInit');
		}

		//ɾ��
		function del(){
			var checkBoxArr = document.getElementsByName("primarykey_cbv");
			var dis = document.getElementsByName("dis");
			var flag = "";
			var num=0;
			for(var i=0;i<checkBoxArr.length;i++){
				if(checkBoxArr[i].checked==true){
					num++;	
					if(dis[i].value!="ȫ��"){
						flag="ѡ�м�¼�к��зǿ����ң���ѡ��ȫ����λδ��ס�����ҽ���ɾ��������"
						break;	
					}	
				}
			}
			if(num==0){
				alertInfo("��ѡ��Ҫ�����ļ�¼��");
			}else{
				if (flag==""){
					batchData('primarykey_cbv','gyglnew_qsgl.do?method=qsglManage&go=go&doType=del','del');
				}else{
					alertError(flag);
				}		
			}
		}
				
		function qsxxglExportConfig() {
		//DCCLBH�������ܱ��,ִ�е������� 
		customExport("gyglnew_qsgl_qsgl.do", qsxxglExportData);
		}
		
	
		
		// ��������
		function qsxxglExportData() {
			setSearchTj();//���ø߼���ѯ����
			var url = "gyglnew_qsgl.do?method=qsxxglExportData&dcclbh=" + "gyglnew_qsgl_qsgl.do";//dcclbh,�������ܱ��
			url = addSuperSearchParams(url);//���ø߼���ѯ����
			jQuery("form").eq(0).attr("action", url);
			jQuery("form").eq(0).submit();
		}

		//������������
		function plwhTypeOfQs(){
			var checkBoxArr = document.getElementsByName("primarykey_cbv");
			var dis = document.getElementsByName("dis");
			var primarykey_checkVal = new Array();
			var flag = "";
			var num=0;
			for(var i=0;i<checkBoxArr.length;i++){
				if(checkBoxArr[i].checked==true){
					primarykey_checkVal.push(checkBoxArr[i].value);
					num++;	
					if(dis[i].value!="ȫ��"){
						flag="ѡ�м�¼�к��зǿ����ң���ѡ��ȫ����λδ��ס�����ҽ��в�����"
						break;	
					}	
				}
			}
			if(num==0){
				alertInfo("��ѡ��Ҫ�����ļ�¼��");
			}else{
				if (flag==""){
					//gyglnew_qsgl.do?method=
					showDialog("�����޸�", 588, 248, "gyglnew_qsgl.do?method=plwhTypeOfQs&primarykey_checkVal="+primarykey_checkVal);
				}else{
					alertError(flag);
				}		
			}
		}
		</script>
	</head>
	<body>
		<html:form action="/gyglnew_qsgl" method="post">
			<!-- ������ -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<!-- ������ -->
			<input type="hidden" id="kcshqss" value="${kcshqss}"/>
			<input type="hidden" id="searchTjstr" value="${searchTjstr}"/>
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
					�������еĴ�λ����סʱ�����Ҳ���ɾ��������������ʼ���������ɲ���ȫ����λδ��ס�����ң�<br/>
					��ѯ����еġ����д�λ����ָ��ȥ������λ�������еĿ��д�λ��������
				</p>
				<a class="close" title="����" onclick="this.parentNode.style.display='none';"></a>
			</div>
			<!-- ��ʾ��Ϣ end-->	
			
			<div class="toolbox" id="dgncz">
				<!-- ��ť -->
				<div class="buttonbox">
					<ul>
						<logic:equal value="yes" name="writeAble">
						<li><a href="#" onclick="showDialog('����������Ϣ', 760, 480, 'gyglnew_qsgl.do?method=qswhAdd');;return false;" class="btn_zj"> ���� </a></li>
						<li><a href="#" onclick="modi('gyglnew_qsgl.do?method=qswhModi',480,760);return false;" class="btn_xg"> �޸� </a></li>
						<li><a href="#" onclick="plwhTypeOfQs();return false;" class="btn_xg"> �����޸� </a></li>
						<li><a href="#" onclick="del();return false;" class="btn_sc"> ɾ�� </a></li>
						<%--<li><a href="#" class="btn_dr" onclick="impAndChkData();return false;">��������</a></li>		
						<li><a href="#" class="btn_dc" onclick="setSearchTj();configureExportData();return false;">��������</a></li>
						<li><a href="#" class="btn_qx" onclick="choiceFields();return false;">�����ֶ�ȷ��</a></li>--%>
						<li><a href="#" onclick="qscsh();return false;" class="btn_csh">����������ʼ��</a></li>
						<li><a href="#" onclick="importUpdateQsxx();return false;" class="btn_dr">�������������Ϣ</a></li>
						</logic:equal>
						<%--<li><a href="#" class="btn_dc" onclick="setSearchTj();configureExportDataZdy('${path }');return false;">��������</a></li>--%>
						<%--<li><a href="#" class="btn_dc" onclick="setSearchTj();configureExportData();return false;">��������</a></li>--%>
						<li><a href="#" class="btn_dc" onclick="qsxxglExportConfig();return false;">����</a></li>
					</ul>
				</div>
				
			     <logic:equal name="superSearch" value="yes">
			     	<%@ include file="/comm/search/superSearchArea.jsp"%>
			     </logic:equal>
			    

			</div>
				 <logic:present name="message">
			     <input type="hidden" id="message" name="message" value="${message }"/>
			     <script type="text/javascript">
			     showAlertDivLayer($('message').value,{},{"clkFun":function(){
	 				if (parent.window){
	 					refershParent();
	 				}
	 			   }});
			     </script>
		         </logic:present>
				<div class="formbox">
					<h3 class="datetitle_01">
						<span>
							��ѯ���&nbsp;&nbsp;<font color="blue">������ͷ��������;˫��������ʾ���һ�����Ϣ������λ�����Ϣ</font>
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
								<logic:iterate id="dis" offset="10" length="1" name="s"></logic:iterate>
								<tr onclick="rowOnClick(this);" style="cursor:hand" 
									ondblclick="modi('gyglnew_qsgl.do?method=qswhView',480,760);">
									<td>								
										<input type="checkbox" name="primarykey_cbv" id="pkV" 
											value="<logic:iterate id="v" name="s" offset="0" length="1">${v }</logic:iterate>" />
										<input type="hidden" name="dis" id="dis"
											value="<logic:iterate id="v" name="s" offset="9" length="1">${v }</logic:iterate>" />										
									</td>
									<logic:iterate id="v" name="s" offset="1" length="9">
										<td>${v }</td>
									</logic:iterate>
								</tr>
							</logic:iterate>
							<%
							int rsNum = ((List)request.getAttribute("rs")).size();
							int pageSize = (Integer)request.getAttribute("pageSize");
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
		   	 	<jsp:include flush="true" page="/sjcz/turnpage.jsp?form=gyglnewQsglForm"></jsp:include>
				<!--��ҳ��ʾ-->
			</div>
		</html:form>

	</body>
</html>
