<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
		<script type='text/javascript' src='/xgxt/dwr/util.js'></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type='text/javascript' src="js/uicomm.js"></script>
		
		<script language="javascript" src="js/String.js"></script>
		<script language="javascript" src="js/function.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript" src="js/BatAlert.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/exportNew/import.js"></script>
		<script language="javascript">
		//����
		function gyglryFp(){
			var pkValue=document.getElementsByName("checkVal");
			var n=0;
			for(i=0;i<pkValue.length;i++){
				if(pkValue[i].checked){
					n++;
				}
			}
			if(n==1 ){
				var pkValue = jQuery("input[name='checkVal']:checked").eq(0).val();
				var pks=pkValue.split("!$$!");
				var lddm=pks[0];
				var ch=pks[1].replace("#","").trim();
				var qsh=pks[2].replace("#","").trim();
				var url = 'gyglnew_gyglry.do?method=gyglryFp';
				url+="&lddm="+lddm;
				if(ch!=""){url+="&fp_ch="+ch;};
				if(qsh!=""){url+="&fp_qsh="+qsh;};
				url+="&old_xh="+jQuery("input[name='checkVal']:checked+input").eq(0).val();
				url+="&pkValue="+pkValue;
				//showTopWin(url,800,600);
				showDialog('������Ա����', 800, 525, url);
				
			}else  {
				alertInfo('��ѡ��һ��');
				return false;
			}
		}
		//ȡ������
		function gyglryQxfp(){
			var pkValue=document.getElementsByName("checkVal");
			var n=0;
			for(i=0;i<pkValue.length;i++){
				if(pkValue[i].checked){
					n++;
				}
			}
			if(n==1 ){
				if(jQuery("input[name='checkVal']:checked+input").eq(0).val()==""){
					alertInfo("������¼��δ���䣬����ȡ����");
					return false;
				}
				var oldxh =jQuery("input[name='checkVal']:checked+input").eq(0).val();
				showDialog('', 350,220, 'gyglnew_gyglry.do?method=gyglryQxfp&oldxh='+oldxh);
			}else  {
				alertInfo('��ѡ��һ��');
				return false;
			}
		}

		function gyglryQxfp_save(){
			var pkValue = jQuery("input[name='checkVal']:checked").eq(0).val();
			var pks=pkValue.split("!$$!");
			var lddm=pks[0];
			var ch=pks[1].replace("#","").trim();
			var qsh=pks[2].replace("#","").trim();
			var url = 'gyglnew_gyglry.do?method=gyglryManage&doType=qxfp';
			url+="&lddm="+lddm;
			if(ch!=""){url+="&fp_ch="+ch};
			if(qsh!=""){url+="&fp_qsh="+qsh};
			url+="&old_xh="+jQuery("input[name='checkVal']:checked+input").eq(0).val();
			url+="&pkValue="+pkValue;
			confirmInfo("ȷ��ȡ��������",function(ok){
				if(ok=="ok"){
					allNotEmpThenGo(url);
				}
			})
		}
		
		function searchRs(){
			allNotEmpThenGo('gyglnew_gyglry_gyglry.do');
		}

		function modi(url,h,w){
			if(curr_row != null){
				showOpenWindow(url + '&pkValue='+curr_row.getElementsByTagName('input')[0].value,h,w);
				return true;
			}else{
				alertInfo('��ѡ��Ҫ�����������У�');
				return false;
			}
		}

		//ͬ���ɲ�������˼������--���ݴ�ѧ 
      	function gyglryTbgbxx(){
      		jQuery.post('gyglnew_gyglry.do?method=gyglryTbgbxx',{},function(data){
      			if ("true" == data){
      				alertInfo('ͬ�����ݳɹ�!');
      			} else {
      				alertInfo('ͬ������ʧ��!');
      			}
      		});
      	}
		
		function qszwhExportConfig() {
		//DCCLBH�������ܱ��,ִ�е������� 
		customExport("gyglnew_gyglry_gyglry.do", qszwhExportData);
		}
		
	
		
		// ��������
		function qszwhExportData() {
			setSearchTj();//���ø߼���ѯ����
			var url = "gyglnew_gyglry.do?method=qszwhExportData&dcclbh=" + "gyglnew_gyglry_gyglry.do";//dcclbh,�������ܱ��
			url = addSuperSearchParams(url);//���ø߼���ѯ����
			jQuery("form").eq(0).attr("action", url);
			jQuery("form").eq(0).submit();
		}

		jQuery(function(){
			var btndr=jQuery("#btn_dr");
			//����
			if(btndr!=null){
				btndr.click(function () {
					//����ͨ�õĵ���function�������ǵ��빦��ģ����롣
					toImportDataNew("IMPORT_N380501_GYGLRY");
					return false;
				});
			}
		});
		//���뵼��ҳ�淽��
		function importGygly(){
			var url = "gyglnew_gyglry.do?method=gyglxDr";
			var title = "����";
			showDialog(title, 770, 350, url);
		}
		</script>
	</head>
	<body >

		<html:form action="/gyglnew_gyglry" method="post">
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
					�����������š����Һ�ֵΪ��#����ά��¥����Ϣ�������Һ�Ϊ��#����ά���㳤��Ϣ������ά�����ҳ���Ϣ��
				</p>
				<a class="close" title="����" onclick="this.parentNode.style.display='none';"></a>
			</div>
			<!-- ��ʾ��Ϣ end-->	
			<!-- ģ������ -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<div class="toolbox" id="dgncz">
				<!-- ��ť -->
				<div class="buttonbox">
					<ul>
						<logic:equal value="yes" name="writeAble">
						<li><a href="#" onclick="gyglryFp();return false;" class="btn_xg"> ���� </a></li>
						<li><a href="#" onclick="gyglryQxfp();return false;" class="btn_sc"> ȡ������ </a></li>
						<%--<li><a href="#" class="btn_dr" onclick="impAndChkData();return false;">��������</a></li>	--%>
						<logic:equal value="10657" name="xxdm">							
							<li><a href="#" onclick="gyglryTbgbxx();return false;" class="btn_sx"> ͬ���ɲ����� </a></li>
						</logic:equal>
						
						<%--<li><a href="#" class="btn_dc" onclick="setSearchTj();configureExportData();return false;">��������</a></li>
						--%>
						<logic:equal name="xxdm" value="10868">
						<li><a href="#" class="btn_dr" id="btn_dr">����</a></li>	
						</logic:equal>
						<!-- ���ݴ�ѧ -->
						<logic:equal name="xxdm" value="10351">	
						<li><a href="#" class="btn_dr" id="btn_dr">����</a></li>	
						</logic:equal>
						<!-- �������ѧԺ -->
						<logic:equal name="xxdm" value="12309">
						<li><a href="#" class="btn_dr" onclick="importGygly()">����</a></li>
						</logic:equal>
						</logic:equal>
						<li><a href="#" class="btn_dc" onclick="qszwhExportConfig();return false;">����</a></li>
					</ul>
				</div>
				<!-- �������� -->
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- �������� end-->
			</div>
			<div class="formbox" id="cxjg">
				<h3 class="datetitle_01">
					<span>
						��ѯ���&nbsp;&nbsp;<font color="blue"></font>
					</span>
				</h3>
				<logic:notEmpty name="rsList">
				<div class="con_overlfow" >
					<table summary="" class="dateline" width="100%">
						<thead>
							<tr align="center" style="cursor:hand">
								<td>
									<input type="checkbox" name="all" value="all" onclick="chec_page()" />
								</td>
								<logic:iterate id="tit" name="topTr" indexId="index">
									<td onclick="tableSort(this)" >
										${tit }
									</td>
								</logic:iterate>
							</tr>
						</thead>
						<tbody>
						<logic:equal value="10466" name="xxdm">
									<logic:iterate name="rsList" id="s" indexId="index">
										<tr onclick="rowMoreClick(this,'',event);"
											style="cursor: hand" ondblclick="">
											<td>
												<input type="checkbox" name="checkVal" id="pkV"
													value="<logic:iterate id="v" name="s" offset="0" length="1">${v}</logic:iterate>" />
												<input type="hidden"
													value="<logic:iterate id="v" name="s" offset="6" length="1">${v}</logic:iterate>" />
											</td>
											<logic:iterate id="v" name="s" offset="1" length="8">
												<td nowrap>
													<bean:write name="v" />
												</td>
											</logic:iterate>
											<td onmouseout="BatAlert.showInfo();"
												onmouseover="BatAlert.showInfo('<%=((String[]) s)[9] == null ? ""
									: ((String[]) s)[9].replace("\r", "")
											.replace("\n", "")%>');">
												<%
													String[] strs = (String[]) s;
																	if (strs[9] != null && strs[9].length() > 9) {
												%><%=strs[9].substring(0, 10)%>...<%
													} else {
												%><%=strs[9]%>
												<%
													}
												%>
											</td>
										</tr>
									</logic:iterate>
								</logic:equal>
								<logic:notEqual value="10466" name="xxdm">
									<logic:iterate name="rsList" id="s" indexId="index">
										<tr onclick="rowMoreClick(this,'',event);"
											style="cursor: hand" ondblclick="">
											<td>
												<input type="checkbox" name="checkVal" id="pkV"
													value="<logic:iterate id="v" name="s" offset="0" length="1">${v}</logic:iterate>" />
												<input type="hidden"
													value="<logic:iterate id="v" name="s" offset="6" length="1">${v}</logic:iterate>" />
											</td>
											<logic:iterate id="v" name="s" offset="1" length="9">
												<td nowrap>
													<bean:write name="v" />
												</td>
											</logic:iterate>
											<td onmouseout="BatAlert.showInfo();"
												onmouseover="BatAlert.showInfo('<%=((String[]) s)[10] == null ? ""
									: ((String[]) s)[10].replace("\r", "")
											.replace("\n", "")%>');">
												<%
													String[] strs = (String[]) s;
																	if (strs[10] != null && strs[10].length() > 10) {
												%><%=strs[10].substring(0, 10)%>...<%
													} else {
												%><%=strs[10]%>
												<%
													}
												%>
											</td>
										</tr>
									</logic:iterate>
								</logic:notEqual>
							</tbody>
					</table>
					<!--��ҳ��ʾ-->
			   	 	<jsp:include flush="true" page="/sjcz/turnpage.jsp?form=gyglnewGyglryForm"></jsp:include>
					<!--��ҳ��ʾ-->
					</div>
				</logic:notEmpty>
			</div>
			
			
			<div id="tempDiv" style="display: none">
				<div class="open_win01">
					<table align="center" class="formlist">
						<thead>
							<tr>
								<th colspan="2">
									<span>������Ա����</span>
								</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<th>
									<font color="red">*</font>����ʱ��
								</th>
								<td>
									<html:text property="rzjsrq" styleId="rzjsrq" onkeypress="onlyBackSpace(this,event);" 
										onclick="return showCalendar(this.id,'yyyy-MM-dd')"></html:text>
								</td>
							</tr>
<%--							<tr>--%>
<%--								<th>--%>
<%--									��ע--%>
<%--								</th>--%>
<%--								<td>--%>
<%--									<input type="text" name="bz"  maxlength="100"/>--%>
<%--								</td>--%>
<%--							</tr>--%>
						</tbody>
						<tfoot>
							<tr>
								<td colspan="2">
									
									<div class="btn">
										<button type="button" name="����"  onclick="gyglryQxfp_save()">
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
	</body>
</html>
