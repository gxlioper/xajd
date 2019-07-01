<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@page import="java.util.List"%>
<%@page import="xgxt.utils.Pages"%>
<%@page import="xsgzgl.jcsj.bmdmwh.BmdmwhForm"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/function.js"></script>
		<script type="text/javascript" src="js/qtsjFunction.js"></script>
		<script type="text/javascript" src="js/commit.js"></script>
		<script language="javascript">
		//���Ŵ���div���� ���ӡ��޸�
		var doType;
		function bmdmDiv(type,topMsg){
			$("bmdm").readOnly="";
			$("bmdm").value="";
			$("bmmc").value="";
			doType=type;
			if(type!="add"){
				if(curr_row != null){
					bmdm=curr_row.getElementsByTagName('input')[0].value;
				}else{
					alertInfo('��ѡ��Ҫ�����������У�');
					return false;
				}
				$("bmdm").value=curr_row.cells[1].innerHTML;
				$("bmmc").value=curr_row.cells[2].innerHTML;
				$("bmlb").value=curr_row.cells[3].innerHTML;
				$("bmdm").readOnly="true";
			}
			tipsWindown(topMsg,"id:tempDiv","350","200","true","","true","id");
		}
		function bmdmDivSave(){
			if(doType!="update"){
				if($("bmdm").value==""){
					alertInfo("�����벿�Ŵ��룡");
					return false;
				}
			}
			if($("bmmc").value.trim()==""){
				alertInfo("�����벿�����ƣ�");
				return false;
			}
			if($("select_bmlb").value.trim()==""){
				alertInfo("��ѡ�������");
				return false;
			}
			allNotEmpThenGo('jcsj_bmdmwh_bmdmwh.do?doType='+doType);
		}
			
			//��ѯ�����
			function searchRs(){
				allNotEmpThenGo('jcsj_bmdmwh_bmdmwh.do');
			}
			

			//ɾ��
			function delBmdm(doType){
				var bmdm;
				if(curr_row != null){
					bmdm=curr_row.getElementsByTagName('input')[0].value;
				}else{
					alertInfo('��ѡ��Ҫ�����������У�');
					return false;
				}
				confirmInfo("ȷ��ɾ����",function(data){
					if("ok"==data){
						allNotEmpThenGo('jcsj_bmdmwh_bmdmwh.do?doType=del&bmdm='+bmdm);
					}
				});
			}

			//����쳣����
			function checkExcData(){
				allNotEmpThenGo('jcsj_bmdmwh_bmdmwh.do?doType=checkExcData');
			}

			function newChgCode(obj){
				allNotEmpThenGo(obj.id+".do");
			}
		</script>
	</head>
	<body onload="">
		<html:form action="/jcsj_bmdmwh" method="post">
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
<%--						<logic:equal value="yes" name="writeAble">--%>
						<li><a href="#" onclick="bmdmDiv('add','���Ӳ���');return false;" class="btn_zj">����</a></li>
						<li><a href="#" onclick="bmdmDiv('update','�޸Ĳ���');return false;" class="btn_xg">�޸�</a></li>
						<li><a href="#" onclick="delBmdm();return false;" class="btn_sc">ɾ��</a></li>
						<li><a href="#" onclick="checkExcData();return false;" class="btn_cs">�쳣���</a></li>
<%--						</logic:equal>--%>
					</ul>
				</div>
				<!-- �������� start-->				
				<div class="searchtab">
					<table width="100%" border="0" id="searchTab">
						<tbody>
							<tr>
								<th>�������</th>
								<td>
									<html:select property="query_bmlb" style="width:150px;">
										<html:option value=""></html:option>
										<html:optionsCollection name="bmlbList" label="mc" value="dm" />
									</html:select>
								</td>
								<th>�Ƿ��쳣����</th>
								<td>
									<html:select property="query_sfycsj" style="width:150px;">
										<html:option value=""></html:option>
										<html:option value="��">��</html:option>
										<html:option value="��">��</html:option>
									</html:select>
								</td>
								<th>��������(����)</th>
								<td>
									<html:text property="query_text"></html:text>
								</td>
							</tr>
						</tbody>
						<tfoot>
							<tr>
								<td colspan="8">
									<div class="btn">
										<button type="button" class="btn_cx" id="search_go" 
											onclick="allNotEmpThenGo('jcsj_bmdmwh_bmdmwh.do');">
											�� ѯ
										</button>
									</div>
								</td>
							</tr>
						</tfoot>
					</table>					
				</div>
				<!-- �������� end-->
			</div>
<%--				<logic:equal name="superSearch" value="yes">--%>
<%--			     	<%@ include file="/comm/search/superSearchArea.jsp"%>--%>
<%--			    </logic:equal>--%>
			    
			    <div class="compTab" id="card">
				<div class="comp_title"><ul>
									<li class="ha"><a href="#" onclick="newChgCode(this)" id="jcsj_bmdmwh_bmdmwh"><span>���Ŵ���</span> </a>
									<li><a href="#" onclick="newChgCode(this)" id="jcsj_zydmwh_zydmwh"><span>רҵ����</span> </a>
									<li><a href="#" onclick="newChgCode(this)" id="jcsj_bjdmwh_bjdmwh"><span>�༶����</span> </a>
									<li><a href="#" onclick="newChgCode(this)" id="jcsj_xsxxwh_xsxxwh"><span>ѧ����Ϣ</span> </a>
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
							BmdmwhForm form = (BmdmwhForm)request.getAttribute("jcsjBmdmwhForm");
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
		   	 	<jsp:include flush="true" page="/sjcz/turnpage.jsp?form=jcsjBmdmwhForm"></jsp:include>
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
									<span class="red">*</span>���Ŵ���
								</th>
								<td>
									<input type="text" id="bmdm" name="bmdm" maxlength="100"/>
								</td>
							</tr>
							<tr>
								<th>
									<span class="red">*</span>��������
								</th>
								<td>
									<input type="text" id="bmmc" name="bmmc" maxlength="100"/>
								</td>
							</tr>
							<tr id="tr_select_pfdx">
								<th>
									<span class="red">*</span>�������
								</th>
								<td>
									<html:select property="bmlb" styleId="select_bmlb" style="width:150px;">
										<html:option value=""></html:option>
										<html:optionsCollection name="bmlbList" label="mc" value="dm" />
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
										<button type="button" name="����" onclick="bmdmDivSave();return false;">
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
