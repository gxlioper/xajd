<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- ���ߣ�ΰ����� -->
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<link rel="stylesheet" type="text/css" href="js/jquery/themes/default/easyui.css" />
	    <link rel="stylesheet" type="text/css" href="js/jquery/themes/icon.css" />
	    <script language="javascript" src="js/check.js"></script>
	    <script language="javascript" src="js/pjpy/szgyyq/pjpy_szgyyq.js"></script>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script language="javascript" defer="defer">
		//ǰ���ҵ�����
		function goMypj(){
			var url = "pjpy_szgyyq_mypj.do";
			
			showWaitingDiv("30000");
			
			refreshForm(url);
		}
		
		function searchRs(doType){
			if("jsfspm"==doType){
				showWaitingDiv("30000");
				allNotEmpThenGo('/xgxt/pjpy_szgyyq_zhcp.do?doType='+doType);
			}else if(checkSearch()){
				allNotEmpThenGo('/xgxt/pjpy_szgyyq_zhcp.do');
			}
		}

		function jszhf(){
			tipsWindown("����ѡ��","id:tempDiv","375","225","true","","true","id");
		}
		
		//����ɷ��ѯ
		function checkSearch(){
		
			var flag = true;
			//var is_default = $("is_default").value;
			
			//if(is_default!=""){
				var xn_num =  jQuery("a[name=a_name_xn]").length;
				var xq_num =  jQuery("a[name=a_name_xq]").length;
				
				if(xn_num != "1"){
					alertError("ѧ����������Ϊ�գ���ֻ�ܲ�ѯһ����");
					flag = false;
				}else if( xq_num != "1"){
					alertError("ѧ����������Ϊ�գ���ֻ�ܲ�ѯһ����");
					flag = false;
				}
			//}
			return flag;
		}

		function exportZhcpBjhzb(){

			setSearchTj(); 
			var bj_num =  jQuery("a[name=a_name_bj]").length;
			
			if(bj_num != "1"){
				alertError("�༶����Ϊ�գ���ֻ�ܵ���һ����");
				return false;
			}
			var url = "/xgxt/pjpy_szgyyq_zhcp.do?doType=export";
		 	document.forms[0].action = url;
		 	document.forms[0].target = "_blank";
		 	document.forms[0].submit();
		 	document.forms[0].target = "_self";
		}
		
		//ǰ���ҵ�����
		function goMypj(){
			var url = "pjpy_szgyyq_mypj.do";
			
			showWaitingDiv("30000");
			
			refreshForm(url);
		}
		</script>
	</head>
	<body>
	
		<!-- ���� -->
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>�ۺ��������ɿ� - �ҵĹ��� - �ۺϲ���</a>
			</p>
			<p class="help">
				<a href="#" onclick="return false;" onmousedown ="showHelpDiv();">ʹ�ð���</a>
			</p>
		</div>
		<!-- ���� end-->
		
		<!-- ��ʾ��Ϣ end-->
		<div class="prompt" id="div_help" style="display: none">
			<h3>
				<span>��ʾ��</span>
			</h3>
			<p>
				<span>
				1.�������в������ǻ���<font color="blue">${xn }</font>ѧ�꣬<font color="blue">${xqmc }</font>ѧ�� չ���ġ�</br>
				2.��ͨ�����<font color="blue">�����ۺϷ�</font>������ѧ�����ۺϷ֣�ϵͳ����ÿһ���µ�һ���賿�Զ����㡣</br>
				3.��ͨ�����<font color="blue">�����༶���ܱ�</font>������ѡ�а༶�����Ļ��������</br>
				</span>
			</p>
			<a class="close" title="����"  onclick="this.parentNode.style.display='none';"></a>
		</div>
		<!-- ��ʾ��Ϣ end-->
	
	
	
		<html:form action="/szgyyq_mypj_tea" method="post">
			<!-- ������ -->
			<%@ include file="/comm/hiddenValue.jsp"%>
<%--			<input type="hidden" id="searchTjstr" value="<bean:write name="searchTjstr"/>"/>--%>
			<!-- ������ -->
			<!-- ��ʾ��Ϣ end-->	
			<div class="toolbox" id="dgncz">
				<!-- ��ť -->
				<div class="buttonbox">
					<ul>
						<!-- ����begin -->
						<li>
							<a href="#" onclick="goMypj();return false;" class="btn_fh">
								����
							</a>
						</li>
						<!-- ����end -->
						<logic:equal value="admin" name="userType">
							<li><a href="#" onclick="jszhf();return false;" class="btn_zj"> �����ۺϷ� </a></li>
						</logic:equal>
						<logic:equal value="xx" name="userType">
							<li><a href="#" onclick="jszhf();return false;" class="btn_zj"> �����ۺϷ� </a></li>
						</logic:equal>
						
						<li><a href="#" class="btn_dc" onclick="setSearchTj();configureExportData();return false;">��������</a></li>
						<li><a href="#" class="btn_qx" onclick="choiceFields();return false;">�����ֶ�ȷ��</a></li>
						<li><a href="#" class="btn_dc" onclick="exportZhcpBjhzb();return false;">�����༶���ܱ�</a></li>
					</ul>
				</div>
				<!-- �������� -->
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- �������� end-->
			</div>
				<div class="formbox">
					<h3 class="datetitle_01">
						<span>
							��ѯ���&nbsp;&nbsp;<font color="blue">������ͷ��������</font>
						</span>
					</h3>
					<div class="con_overlfow" style="min-height: 230px;overflow-x:auto;overflow-y:hidden;">
					<table width="99%" id="rsTable" class="dateline">
						<thead>
							<tr align="center" style="cursor:hand">
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
								<logic:iterate id="tit" name="topTr" offset="0" length='11'>
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
									<logic:iterate id="v" name="s">
										<td><nobr>${v }</nobr></td>
									</logic:iterate>
								</tr>
							</logic:iterate>
					</logic:notEmpty>
				</table>
				</div>
				<!--��ҳ��ʾ-->
		   	 	<jsp:include flush="true" page="/sjcz/turnpage.jsp?form=pjpySzgyyqTeaForm"></jsp:include>
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
									<span id="th_span_lable">��ѡ���ۺϷּ��㷶Χ</span>
								</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<th>
									�꼶
								</th>
								<td>
									<html:select property="nj" styleId="nj" styleClass="select"
										onchange="initZyList();initBjList()" >
										<html:option value=""></html:option>
										<html:options collection="njList" property="nj"
											labelProperty="nj" />
									</html:select>
								</td>
							</tr>
							<tr>
								<th>
									<bean:message key="lable.xb" />
								</th>
								<td>
									<html:select property="xydm" onchange="initZyList();initBjList()"
										style="width:180px"  styleId="xy">
										<html:option value=""></html:option>
										<html:options collection="xyList" property="xydm"
											labelProperty="xymc" />
									</html:select>
								</td>
							</tr>
							<tr>
								<th>
									רҵ
								</th>
								<td>
									<html:select property="zydm" onchange="initBjList()" 
										style="width:180px" styleId="zy">
										<html:option value=""></html:option>
										<html:options collection="zyList" property="zydm"
											labelProperty="zymc" />
									</html:select>
								</td>
							</tr>
							<tr>
								<th>
									�༶
								</th>
								<td>
									<html:select property="bjdm" 
										style="width:180px" styleId="bj">
										<html:option value=""></html:option>
										<html:options collection="bjList" property="bjdm"
											labelProperty="bjmc" />
									</html:select>
								</td>
							</tr>
						</tbody>
						<tfoot>
							<tr>
								<td colspan="2">
									<div id="submit_bz" class="bz" style="color: red;">
										
									</div>
									<div class="btn">
										<button type="button" name="����"  onclick="searchRs('jsfspm')">
											�� �� 
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
		<%@ include file="/comm/delMessage.jsp"%>
		<%@ include file="/comm/loading.jsp"%>
		<%@ include file="/comm/other/tsxx.jsp"%>
	</body>
</html>