<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@page import="java.util.List"%>
<%@page import="xgxt.utils.Pages"%>
<%@page import="xsgzgl.gygl.gyjldmgl.GyjldmglForm"%>
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
		//��Ԣ����������div���� ���ӡ��޸�
		var doType;
		function jllbDiv(type,topMsg){
			$("jllbdm").readOnly="";
			$("jllbdm").value="";
			$("jllbmc").value="";
			doType=type;
			var title = topMsg;
			if(type!="add"){
				if(curr_row != null){
					jllbdm=curr_row.getElementsByTagName('input')[0].value;
				}else{
					alertInfo('��ѡ��Ҫ�����������У�');
					return false;
				}
				//tipsWindown(topMsg,"id:tempDiv","350","220","true","","true","id");
				$("jllbdm").value=jQuery(curr_row.cells[1]).text();
				$("jllbmc").value=jQuery(curr_row.cells[2]).text();
				$("jldldm").value=curr_row.getElementsByTagName('input')[1].value;
				$("jllbkf").value=jQuery(curr_row.cells[3]).text();
				
				$("jllbdm").readOnly="true";
				jQuery("#jllbdm").css("color","grey");

				url="gyglnew_gyjldmgl.do?method=gyjllbZjxg&doType="+doType+"&jllbdm=" + jQuery(curr_row.cells[1]).text(); 
				url = url + "&jllbmc=" +jQuery(curr_row.cells[2]).text();
				url = url + "&jldldm=" +curr_row.getElementsByTagName('input')[1].value;
				url = url + "&jllbkf=" +jQuery(curr_row.cells[3]).text();
				
			}else{	
				if(type=="add"){
					url="gyglnew_gyjldmgl.do?method=gyjllbZjxg&doType="+doType;
				}		
				//tipsWindown(topMsg,"id:tempDiv","350","220","true","","true","id");
			}
			showDialog(title,420, 200, url);
		}
		function jllbDivSave(){
			if(doType!="update"){
				if($("jllbdm").value==""){
					alertInfo("��������������룡");
					return false;
				}
			}
			if($("jllbmc").value.trim()==""){
				alertInfo("���������������ƣ�");
				return false;
			}
			if($("jllbkf").value.trim()==""){
				alertInfo("������������۷�����");
				return false;
			}
			if($("jldldm").value.trim()==""){
				alertInfo("��ѡ����ɴ��࣡");
				return false;
			}
			allNotEmpThenGo('gyglnew_gyjldmgl.do?method=gyjllbManage&doType='+doType);
		}
			
			//��ѯ�����
			function searchRs(){
				allNotEmpThenGo('gyglnew_gyjldmgl.do?method=gyjllbManage');
			}
			

			//ɾ��
			function deljllb(doType){
				var jllbdm;
				if(curr_row != null){
					jllbdm=curr_row.getElementsByTagName('input')[0].value;
				}else{
					alertInfo('��ѡ��Ҫ�����������У�');
					return false;
				}
				confirmInfo("ȷ��ɾ����",function(data){
					if("ok"==data){
						allNotEmpThenGo('gyglnew_gyjldmgl.do?method=gyjllbManage&doType=del&jllbdm='+jllbdm);
					}
					return false;
				});
			}

			function newChgCode(obj){
				allNotEmpThenGo(obj.id);
			}
		</script>
	</head>
	<body >
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
				<logic:equal value="yes" name="writeAble">
				<div class="buttonbox">
					<ul>
						<li><a href="#" onclick="jllbDiv('add','���Ӽ������');return false;" class="btn_zj">����</a></li>
						<li><a href="#" onclick="jllbDiv('update','�޸ļ������');return false;" class="btn_xg">�޸�</a></li>
						<li><a href="#" onclick="deljllb();return false;" class="btn_sc">ɾ��</a></li>
					</ul>
				</div>
				</logic:equal>
			</div>
			    <div class="compTab" id="card">
				<div class="comp_title"><ul>
									<li>
										<a href="#" onclick="newChgCode(this);return false;" id="gyglnew_gyjldmgl.do?method=gyjldlManage">
											<span>
												<logic:equal value="11799" name="xxdm">
													���ʹ���
												</logic:equal>
												<logic:notEqual value="11799" name="xxdm">
													���ɴ���
												</logic:notEqual>
											</span>
										</a>
									</li>
									<li class="ha">
										<a href="#" onclick="newChgCode(this);return false;" id="gyglnew_gyjldmgl.do?method=gyjllbManage">
											<span>
												<logic:equal value="11799" name="xxdm">
													�������
												</logic:equal>
												<logic:notEqual value="11799" name="xxdm">
													�������
												</logic:notEqual>
											</span>
										</a>
									</li>
									<li>
										<a href="#" onclick="newChgCode(this);return false;" id="gyglnew_gyjldmgl.do?method=gyjlcfManage">
											<span>�������</span> 
										</a>
									</li>
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
							<logic:iterate name="rs" id="s" indexId="ind">
								<tr onclick="rowOnClick(this);" style="cursor:hand">
									<td style="display: none;">								
										<input type="checkbox" name="primarykey_cbv" id="pkV"
											value="<logic:iterate id="v" name="s" offset="0" length="1">${v }</logic:iterate>" />
										<input type="checkbox" name="primarykey_cbv" id="pkV"
											value="<logic:iterate id="v" name="s" offset="1" length="1">${v }</logic:iterate>" />
										
									</td>
									<logic:iterate id="v" name="s" offset="2" length="5">
										<td><bean:write name="v" format="ture"/></td>
									</logic:iterate>
								</tr>
							</logic:iterate>
							<%
							int rsNum = ((List)request.getAttribute("rs")).size();
							GyjldmglForm form = (GyjldmglForm)request.getAttribute("gyjldmglForm");
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
		   	 	<jsp:include flush="true" page="/sjcz/turnpage.jsp?form=gyjldmglForm"></jsp:include>
		   	 	<script type="text/javascript">
					$('choose').className="hide";
				</script>
				<!--��ҳ��ʾ-->
			</div>
			
			<div id="tempDiv" style="display: none">
				<div class="open_win01">
					<table align="center" class="formlist">
						<thead>
							<tr>
								<th colspan="2">
									<span></span>
								</th>
							</tr>
						</thead>
						<tbody>
							<tr id="tr_select_xn">
								<th>
									<span class="red">*</span>
									<logic:equal value="11799" name="xxdm">
										����������
									</logic:equal>
									<logic:notEqual value="11799" name="xxdm">
										����������
									</logic:notEqual>
								</th>
								<td>
									<input type="text" id="jllbdm" name="jllbdm" maxlength="100"/>
								</td>
							</tr>
							<tr>
								<th>
									<span class="red">*</span>
										<logic:equal value="11799" name="xxdm">
											�����������
										</logic:equal>
										<logic:notEqual value="11799" name="xxdm">
											�����������
										</logic:notEqual>										
								</th>
								<td>
									<input type="text" id="jllbmc" name="jllbmc" maxlength="100"/>
								</td>
							</tr>
							<tr>
								<th>
									<span class="red">*</span>
										<logic:equal value="11799" name="xxdm">
											�������۷�
										</logic:equal>
										<logic:notEqual value="11799" name="xxdm">
											�������۷�
										</logic:notEqual>
								</th>
								<td>
									<input type="text" id="jllbkf" name="jllbkf" onkeyup="value=value.replace(/[^\d]/g,'') " maxlength="100"/>
								</td>
							</tr>
							<tr id="tr_select_pfdx">
								<th>
									<span class="red">*</span>
										<logic:equal value="11799" name="xxdm">
											�������ʹ���
										</logic:equal>
										<logic:notEqual value="11799" name="xxdm">
											�������ɴ���
										</logic:notEqual>										
								</th>
								<td>
									<html:select property="jldldm" styleId="jldldm" style="width:150px;">
										<html:option value=""></html:option>
										<html:options collection="jldlList" property="jldldm" labelProperty="jldlmc" />
									</html:select>
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
										<button type="button" name="����" onclick="jllbDivSave()">
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
