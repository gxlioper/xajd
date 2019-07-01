<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@page import="java.util.List"%>
<%@page import="xgxt.utils.Pages"%>
<%@page import="xgxt.szdw.bjlh.fdycpwj.BjlhFdycpwjForm"%>
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
		var wjid;
		function cpwjDiv(type,tipMsg){
			$("wjmc").value="";
			doType=type;
			if(type!="add"){
				if(curr_row != null){
					wjid=curr_row.getElementsByTagName('input')[0].value;
				}else{
					alertInfo('��ѡ��Ҫ�����������У�');
					return false;
				}
			}
			if(type=="update"){
				$("tr_select_xn").style.display="none";
				$("wjmc").value=curr_row.cells[3].innerText;
			}else{
				$("tr_select_xn").style.display="";
			}
			tipsWindown(tipMsg,"id:tempDiv","350","170","true","","true","id");
		}
		function cpwjDivSave(){
			if(doType!="update"){
				if($("select_xn").value==""){
					alertInfo("��ѡ��ѧ�꣡");
					return false;
				}
			}
			if($("wjmc").value.trim()==""){
				alertInfo("�������ʾ����ƣ�");
				return false;
			}
			allNotEmpThenGo('bjlh_fdykh_cpwjgl.do?doType='+doType+'&wjid='+wjid);
		}
		
		function modiCpwj(type){
			var wjid;
			if(type=="update"){
				if(curr_row != null){
					wjid=curr_row.getElementsByTagName('input')[0].value;
				}else{
					alertInfo('��ѡ��Ҫ�����������У�');
					return false;
				}
			}
			var url="bjlh_fdycpwj.do?method=cpwjglEdit&doType="+type+"&wjid="+wjid;
			showTopWin(url,600,400);
		}
			
			//��ѯ�����
			function searchRs(){
				allNotEmpThenGo('bjlh_fdykh_cpwjgl.do');
			}
			

			//ɾ��
			function delCpwj(doType){
				var wjid;
				if(curr_row != null){
					wjid=curr_row.getElementsByTagName('input')[0].value;
				}else{
					alertInfo('��ѡ��Ҫ�����������У�');
					return false;
				}
				confirmInfo("ȷ��ɾ����",function(data){
					if("ok"==data){
						allNotEmpThenGo('bjlh_fdykh_cpwjgl.do?doType=del&wjid='+wjid);
					}
				});
			}
			//�Ƿ����ò����ʾ�
			function sfqyCpwj(sfqy,msg){
				var wjid;
				if(curr_row != null){
					wjid=curr_row.getElementsByTagName('input')[0].value;
				}else{
					alertInfo('��ѡ��Ҫ�����������У�');
					return false;
				}
				confirmInfo("ȷ��"+msg+"��",function(data){
					if("ok"==data){
						allNotEmpThenGo('bjlh_fdykh_cpwjgl.do?doType=sfqy&wjid='+wjid+"&sfqy="+sfqy);
					}
				});
			}
			//����
			function copyCpwj(doType){
				var wjid;
				if(type=="update"){
					if(curr_row != null){
						wjid=curr_row.getElementsByTagName('input')[0].value;
					}else{
						alertInfo('��ѡ��Ҫ�����������У�');
						return false;
					}
				}
				confirmInfo("ȷ��������",function(data){
					if("ok"==data){
						//var wjid=jQuery("input[type='checkbox'][name='primarykey_cbv']:checked").first().val();
						allNotEmpThenGo('bjlh_fdykh_cpwjgl.do?doType=copy&wjid='+wjid);
					}
				});
			}
			//�����ʾ�����ά��
			function cpwjStwh(){
				var wjid;
				if(curr_row != null){
					if(curr_row.getElementsByTagName('input')[1].value>0){
						alertInfo('��ǰ�ʾ������𣬲��ɽ�������ά��');
						return false;	
					}
					wjid=curr_row.getElementsByTagName('input')[0].value;
				}else{
					alertInfo('��ѡ��Ҫ�����������У�');
					return false;
				}
				var url="bjlh_fdycpwj.do?method=cpwjglStwh&doType=add&wjid="+wjid;
				allNotEmpThenGo(url);
			}
			//�����ʾ�Ԥ��
			function cpwjglView(){
				var wjid;
				if(curr_row != null){
					wjid=curr_row.getElementsByTagName('input')[0].value;
				}else{
					alertInfo('��ѡ��Ҫ�����������У�');
					return false;
				}
				var url="bjlh_fdycpwj.do?method=cpwjglView&wjid="+wjid;
				showTopWin(url,800,800);
			}
		</script>
	</head>
	<body>
		<html:form action="/bjlh_fdycpwj" method="post">
			<!-- ������ -->
			<%@ include file="/comm/hiddenValue.jsp"%>
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
						<li><a href="#" onclick="cpwjDiv('add','�����ʾ�');return false;" class="btn_zj">����</a></li>
						<li><a href="#" onclick="cpwjDiv('update','�޸��ʾ�');return false;" class="btn_xg">�޸�</a></li>
						<li><a href="#" onclick="delCpwj();return false;" class="btn_sc">ɾ��</a></li>
						<li><a href="#" onclick="cpwjStwh();return false;" class="btn_xg">����ά��</a></li>
						<li><a href="#" onclick="cpwjglView();return false;" class="btn_yl">Ԥ��</a></li>
						<li><a href="#" onclick="cpwjDiv('copy','�����ʾ�');return false;" class="btn_fz">����</a></li>
						<li><a href="#" onclick="sfqyCpwj('��','����');return false;" class="btn_shtg">����</a></li>
						<li><a href="#" onclick="sfqyCpwj('��','ͣ��');return false;" class="btn_shbtg">ͣ��</a></li>
						</logic:equal>
					</ul>
				</div>
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
										<input type="hidden" name="djnum" value="<logic:iterate id="v" name="s" offset="7" length="1">${v }</logic:iterate>"/>
									</td>
									<logic:iterate id="v" name="s" offset="1" length="6">
										<td><bean:write name="v" format="ture"/></td>
									</logic:iterate>
								</tr>
							</logic:iterate>
							<%
							int rsNum = ((List)request.getAttribute("rs")).size();
							BjlhFdycpwjForm form = (BjlhFdycpwjForm)request.getAttribute("bjlhFdycpwjForm");
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
		   	 	<jsp:include flush="true" page="/sjcz/turnpage.jsp?form=bjlhFdycpwjForm"></jsp:include>
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
										<html:options collection="addxnList" property="xn" labelProperty="xn" />
									</html:select>
								</td>
							</tr>
							<tr>
								<th>
									<span class="red">*</span>�ʾ�����
								</th>
								<td>
									<input type="text" id="wjmc" name="wjmc" maxlength="100"/>
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
