<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@page import="java.util.List"%>
<%@page import="xgxt.utils.Pages"%>
<%@page import="xgxt.szdw.bjlh.fdykh.BjlhFdykhForm"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/function.js"></script>
		<script type="text/javascript" src="js/qtsjFunction.js"></script>
		<script type="text/javascript" src="js/commit.js"></script>
		<script language="javascript">
		//�����ʾ�div���� ���ӡ��޸ġ�����
		var doType;
		var khbid;
		function cpwjDiv(type,topMsg){
			$("khbmc").value="";
			doType=type;
			if(type!="add"){
				if(curr_row != null){
					khbid=curr_row.getElementsByTagName('input')[0].value;
				}else{
					alertInfo('��ѡ��Ҫ�����������У�');
					return false;
				}
			}
			var div_height="200";
			if(type=="update"){
				$("tr_select_xn").style.display="none";
				$("tr_select_pfdx").style.display="none";
				div_height="120";
				$("khbmc").value=curr_row.cells[3].innerText;
			}else if(type=="add"){
				$("tr_select_xn").style.display="";
				$("tr_select_pfdx").style.display="";
				div_height="200";
			}else if(type=="copy"){
				$("tr_select_xn").style.display="";
				$("tr_select_pfdx").style.display="none";
				div_height="150";
			}
			tipsWindown(topMsg,"id:tempDiv","350",div_height,"true","","true","id");
		}
		function cpwjDivSave(){
			if(doType!="update"){
				if($("select_xn").value==""){
					alertInfo("��ѡ��ѧ�꣡");
					return false;
				}
			}
			if($("khbmc").value.trim()==""){
				alertInfo("�������ʾ����ƣ�");
				return false;
			}
			var url='bjlh_fdykh_khcpbgl.do?doType='+doType+'&khbid='+khbid
			if(doType=="copy"){
				var pfdx=curr_row.cells[4].innerText
				if(pfdx=="ѧ��"){
					pfdx="xs";
				}else if(pfdx=="����С��"){
					pfdx="pfxz";
				}
				url+="&pfdx="+pfdx;
			}
			allNotEmpThenGo(url);
		}
		
		function modiCpwj(type){
			var khbid;
			if(type=="update"){
				if(curr_row != null){
					khbid=curr_row.getElementsByTagName('input')[0].value;
				}else{
					alertInfo('��ѡ��Ҫ�����������У�');
					return false;
				}
			}
			var url="bjlh_fdykh.do?method=cpwjglEdit&doType="+type+"&khbid="+khbid;
			showTopWin(url,600,400);
		}
			
			//��ѯ�����
			function searchRs(){
				allNotEmpThenGo('bjlh_fdykh_khcpbgl.do');
			}
			

			//ɾ��
			function delCpwj(doType){
				var khbid;
				if(curr_row != null){
					khbid=curr_row.getElementsByTagName('input')[0].value;
				}else{
					alertInfo('��ѡ��Ҫ�����������У�');
					return false;
				}
				confirmInfo("ȷ��ɾ����",function(data){
					if("ok"==data){
						allNotEmpThenGo('bjlh_fdykh_khcpbgl.do?doType=del&khbid='+khbid);
					}
				});
			}
			//�Ƿ����ò����ʾ�
			function sfqyCpwj(sfqy,msg){
				var khbid;
				if(curr_row != null){
					khbid=curr_row.getElementsByTagName('input')[0].value;
				}else{
					alertInfo('��ѡ��Ҫ�����������У�');
					return false;
				}
				confirmInfo("ȷ��"+msg+"��",function(data){
					if("ok"==data){
						allNotEmpThenGo('bjlh_fdykh_khcpbgl.do?doType=sfqy&khbid='+khbid+"&sfqy="+sfqy);
					}
				});
			}
			//����
			function copyCpwj(doType){
				var khbid;
				if(type=="update"){
					if(curr_row != null){
						khbid=curr_row.getElementsByTagName('input')[0].value;
					}else{
						alertInfo('��ѡ��Ҫ�����������У�');
						return false;
					}
				}
				confirmInfo("ȷ��������",function(data){
					if("ok"==data){
						//var khbid=jQuery("input[type='checkbox'][name='primarykey_cbv']:checked").first().val();
						allNotEmpThenGo('bjlh_fdykh_khcpbgl.do?doType=copy&khbid='+khbid);
					}
				});
			}
			//�����ʾ�����ά��
			function cpwjStwh(){
				var khbid;
				if(curr_row != null){
					khbid=curr_row.getElementsByTagName('input')[0].value;
				}else{
					alertInfo('��ѡ��Ҫ�����������У�');
					return false;
				}
				var url="bjlh_fdykh.do?method=fdykhXmwh&khbid="+khbid;
				allNotEmpThenGo(url);
			}
			//�����ʾ�Ԥ��
			function cpwjglView(){
				var khbid;
				if(curr_row != null){
					khbid=curr_row.getElementsByTagName('input')[0].value;
				}else{
					alertInfo('��ѡ��Ҫ�����������У�');
					return false;
				}
				var url="bjlh_fdykh.do?method=fdykhView&khbid="+khbid;
				showTopWin(url,800,600);
			}
		</script>
	</head>
	<body onload="">
		<html:form action="/bjlh_fdykh" method="post">
			<!-- ������ -->
			<%@ include file="/comm/hiddenValue.jsp"%>
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
						<li><a href="#" onclick="cpwjDiv('add','���ӿ��˱�');return false;" class="btn_zj">����</a></li>
						<li><a href="#" onclick="cpwjDiv('update','�޸Ŀ��˱�����');return false;" class="btn_xg">�޸�</a></li>
						<li><a href="#" onclick="delCpwj();return false;" class="btn_sc">ɾ��</a></li>
						<li><a href="#" onclick="cpwjStwh();return false;" class="btn_xg">������Ŀά��</a></li>
						<li><a href="#" onclick="cpwjglView();return false;" class="btn_yl">Ԥ��</a></li>
						<li><a href="#" onclick="cpwjDiv('copy','���ƿ��˱�');return false;" class="btn_fz">����</a></li>
						<li><a href="#" onclick="sfqyCpwj('��','����');return false;" class="btn_shtg">����</a></li>
						<li><a href="#" onclick="sfqyCpwj('��','ͣ��');return false;" class="btn_shbtg">ͣ��</a></li>
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
									<logic:iterate id="v" name="s" offset="1" length="8">
										<td><bean:write name="v" format="ture"/></td>
									</logic:iterate>
								</tr>
							</logic:iterate>
							<%
							int rsNum = ((List)request.getAttribute("rs")).size();
							BjlhFdykhForm form = (BjlhFdykhForm)request.getAttribute("bjlhFdykhForm");
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
		   	 	<jsp:include flush="true" page="/sjcz/turnpage.jsp?form=bjlhFdykhForm"></jsp:include>
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
									<span class="red">*</span>ѧ��
								</th>
								<td>
									<html:select property="xn" styleId="select_xn" style="width:150px;">
										<html:options collection="xnList" property="xn" labelProperty="xn" />
									</html:select>
								</td>
							</tr>
							<tr>
								<th>
									<span class="red">*</span>�ʾ�����
								</th>
								<td>
									<input type="text" id="khbmc" name="khbmc" maxlength="100"/>
								</td>
							</tr>
							<tr id="tr_select_pfdx">
								<th>
									<span class="red">*</span>���ֶ���
								</th>
								<td>
									<html:select property="pfdx" styleId="select_pfdx" style="width:150px;">
										<html:options collection="pfdxList" property="dm" labelProperty="mc" />
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
										<button type="button" name="����" onclick="cpwjDivSave()">
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
