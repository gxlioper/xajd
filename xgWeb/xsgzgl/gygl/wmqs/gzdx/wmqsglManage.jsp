<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@page import="java.util.List"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- ���ߣ�zhangh -->
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script language="javascript" src="dwr/interface/getCommGygl.js"></script>
		<script type="text/javascript" src="js/commit.js"></script>
		<script language="javascript" defer="defer">
		//��ѯ�����
		function searchRs(){
			$("doType").value="";
			allNotEmpThenGo('gzdx_gygl_wmqs_qsgl.do');
		}
		
		function update(url){
			if(curr_row != null){
				url += "&pk="+curr_row.getElementsByTagName('input')[0].value;
				url += "&xh="+curr_row.getElementsByTagName('input')[1].value;
				showTopWin(url,800,600);
				return true;
			}else{
				alertInfo('��ѡ��Ҫ�����������У�');
				return false;
			}
		}

		function wmqsglDel(){
			var checkbox=document.getElementsByName("primarykey_checkVal");
			var count=0;
			for(var i=0;i<checkbox.length;i++){
				if(checkbox[i].checked){
					count++;
				}
			}
			if(count==0){
				alertInfo("��ѡ����Ҫɾ�����������ң�");
				return false;
			}
			confirmInfo("ȷ��ɾ��ѡ�е�������",function(data){
				if("ok"==data){
					$("doType").value="del";
					allNotEmpThenGo('gzdx_gygl_wmqs_qsgl.do');
				}
			});
		}

		function wmqsglModi(doType){
			if("add"==doType){
				showTopWin('gzdxWmqs.do?method=wmqsglModi&doType='+doType,'800','550');
				return false;
			}
			var pkValue=document.getElementsByName("primarykey_checkVal");
			var n=0;
			for(i=0;i<pkValue.length;i++){
				if(pkValue[i].checked){
					n++;
				}
			}
			if(n==1 || curr_row){
				var pkValue = curr_row.getElementsByTagName('input')[0].value;
				var url = 'gzdxWmqs.do?method=wmqsglModi&doType='+doType+"&pkValue="+pkValue;
				showTopWin(url,800,550);
			}else if (null == curr_row) {
					alertInfo('��ѡ��һ��');
					return false;
			}
			//showTopWin('gzdxWmqs.do?method=wmqsglModi&doType='+doType+"&pkValue="+,'600','500');
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
		
		<html:form action="/gzdxWmqs.do?method=qsshManage">
			<!-- ������ -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<!-- ������ -->

			<!-- �๦�ܲ����� -->
			<div class="toolbox">
				<!-- ��ť -->
				<div class="buttonbox">
					<ul>
						<logic:equal value="yes" name="writeAble">						
						<li><a href="#" onclick="wmqsglModi('add');return false;" class="btn_zj">���� </a></li>
						<li><a href="#" onclick="wmqsglModi('update');return false;" class="btn_xg">�޸�</a></li>
						<li><a href="#" onclick="wmqsglDel();return false;" class="btn_sc">ɾ�� </a></li>
						</logic:equal>
<%--						<li><a href="#" class="btn_qx" onclick="choiceFields();return false;">��������</a></li>--%>
<%--						<li><a href="#" class="btn_dc" onclick="configureExportData();return false;">��������</a></li>--%>
<%--						<li><a href="#" onclick="dataExport();return false;" class="btn_dc">�������� </a></li>--%>
<li><a href="#" class="btn_dc" onclick="setSearchTj();configureExportDataZdy('${path}');return false;">��������</a></li>
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
				<table summary="" class="dateline" width="100%">
					<!-- ��ͷ -->
					<thead>
						<tr align="center" style="cursor:hand">
							<td width="5px">
								<input type="checkbox" id="selall" name="selall" onclick="selAll()" />
							</td>
							<logic:iterate id="tit" name="topTr" offset="0" >
								<td id="<bean:write name="tit" property="en"/>"
									onclick="tableSort(this)">
									<bean:write name="tit" property="cn" />
								</td>
							</logic:iterate>
						</tr>
					</thead>
					<!-- ��ͷ end-->
					<!--���� -->
					<logic:iterate name="rsArrList" id="s" indexId="index">
						<tr onclick="rowOnClick(this);" ondblclick="" style="cursor:hand">
							<td align="center" width="5px">
								<logic:iterate id="v" name="s" offset="0" length="1">
									<input type="checkbox" id="pk_${index }" name="primarykey_checkVal" value="${v }"/>
								</logic:iterate>
								
								<logic:iterate id="v" name="s" offset="4" length="1">
									<input type="hidden" value="${v }"/>
								</logic:iterate>
							</td>
							<!-- ��ʾ��Ϣ -->
							<logic:iterate id="v" name="s" offset="1">
								<td align="left">
									${v }
								</td>
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
							<input type="checkbox" disabled="disabled"></input>
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
				<jsp:include flush="true" page="/sjcz/turnpage.jsp?form=gzdxGyglWmqsForm"></jsp:include>
				<!--��ҳend-->
				</div>
			</div>
			<!-- ��ѯ��� end-->
			
			
			
			
			
			
			<div id="tempDiv" style="display: none">
				<div class="open_win01">
					<table align="center" class="formlist">
						<thead>
							<tr>
								<th colspan="2">
									<span id="th_span_lable">��ѡ�����״̬</span>
								</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<th id="th_rzsj">
									���״̬
								</th>
								<td>
									<select name="shzt" id="shzt" style="width: 185px;">
										<option value=""></option>
										<option value="ͨ��">ͨ��</option>
										<option value="��ͨ��">��ͨ��</option>
									</select>
								</td>
							</tr>
							<tr id="tr_tsyy">
								<th>
									��ע
								</th>
								<td>
									<textarea rows="3" cols="23" name="shbz" id="shbz"></textarea>
								</td>
							</tr>
						</tbody>
						<tfoot>
							<tr>
								<td colspan="2">
									<div id="submit_bz" class="bz" style="color: red;">
										
									</div>
									<div class="btn">
										<button type="button" name="����"  onclick="saveWmqsshSubmit()">
											ȷ �� 
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
			
			<logic:present name="message">
				<script>
					alert("${message}");
				</script>
			</logic:present>
			
		</html:form>
	</body>
</html>