<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- ���ߣ�ΰ����� -->
<jsp:directive.page import="java.util.ArrayList" />
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/xgutil.js"></script>
		<script language="javascript" src="js/check.js"></script>
		<script language="javascript" src="js/xszz/xszzFunction.js"></script>
		<script language="javascript" src="js/xszz/xszzComm.js"></script>
		<script type='text/javascript' src="js/comm/message.js"></script>
		<script type='text/javascript'
			src='/xgxt/dwr/interface/getXszzInfo.js'></script>
		<script language="javascript">
		//ͳ�Ʊ���
		function tjBb(){
			
			var xmdm = $("xmdm").value;
			var xxdm = $("xxdm").value;
			var xydm = $('xy').value;
			var xn = $('xn').value;
			var xq = $('xq').value;
			var url = "/xgxt/commXszz.do?method=tjbb&xmdm="+xmdm;
			
			if(xmdm == ""){
				alert("��ѡ����Ҫͳ�Ƶ���Ŀ");
				return false;
			} else if ("1004" ==xmdm) {//��ɫͨ��
				if (""==xydm){
					alert("��ѡ��Ժϵ��");
					return false;
				}
				url += "&xydm="+xydm;
			} else if ("1001" ==xmdm){//������־��ѧ��
				if (""==xn){
					alert("��ѡ��ѧ�꣡");
					return false;
				}
				url += "&xn="+xn;
			} else if ("1003"==xmdm){//ѧ������ѧ��
				
			} else if ("1002"==xmdm){//������ѧ��
				if (""==xn){
					alert("��ѡ��ѧ�꣡");
					return false;
				}
				if (""==xq){
					alert("��ѡ��ѧ�ڣ�");
					return false;
				}
				url += "&xn="+xn+"&xq="+xq;
			} else if(xmdm == "5002"){//������
				if (""==xydm){
					alert("��ѡ��Ժϵ��");
					return false;
				}
				url += "&xydm="+xydm;
			} else {
				alert("������˼�����ͳ�Ʊ�û������\n����������ɫͨ����������־��ѧ��ѧ������ѧ�ѡ�������ѧ����������");
				return false;
			}
			
			document.forms[0].action = url;
			document.forms[0].target = "_blank";
			document.forms[0].submit();
			document.forms[0].target = "_self";
			
		}
		
function showExpDiv(divId,isbackDiv,divColor){
	if($('##floatDiv##')==null){//��ʾ������  
		var floatDiv = document.createElement('div');
		floatDiv.id = "##floatDiv##";
		floatDiv.style.position = "absolute";
		floatDiv.style.width = "600px";
		floatDiv.style.height = "400px";
		floatDiv.style.backgroundColor = "#FFFFFF";
		
		var x_pixel = 100;
		var y_pixel = 25;
		floatDiv.style.left = "100px";
		floatDiv.style.top = "100px";
		floatDiv.style.zIndex  = 1001;
		
		
		floatDiv.innerHTML = document.getElementById(divId).innerHTML;
		document.getElementById(divId).innerHTML = "";
		document.body.appendChild(floatDiv);
	}else{
		$('##floatDiv##').style.display = "block";
	}
	
	if($('##backDiv##')==null && isbackDiv ==true){//��ʾ����  
		var backDiv = document.createElement('div');
		backDiv.id = "##backDiv##";
		backDiv.style.backgroundColor = "Black";
		backDiv.style.filter = "alpha(opacity=70)";
		backDiv.style.MozOpacity = "0.70";
		backDiv.style.position = "absolute";
		backDiv.style.left = "0px";
		backDiv.style.top = "0px";
		backDiv.style.width = Math.max(document.body.scrollWidth, document.body.clientWidth) +"px";
		backDiv.style.height = Math.max(document.body.scrollHeight, document.body.clientHeight)+"px";
		document.body.appendChild(backDiv);
		$('##backDiv##').style.xIndex = 1000;
	}else if(isbackDiv ==true){
		$('##backDiv##').style.display = "block";
	}  
  	i = document.getElementsByTagName("select").length;
	for (j = 0; j < i; j++) {
		var obj = document.getElementsByTagName("select")[j];
		var id = obj.id;
		var arr = id.split("_");
		var splitName = arr[0];
		
		if(splitName != "select"){
			obj.style.display = "none";
		}
	}
	
}
 
function hiddenExpDiv(){

	if($('##backDiv##')!=null){ 
		$('##backDiv##').style.display = "none";
    }         
    
    if($("##floatDiv##")){ 
		$("##floatDiv##").style.display = "none";
    }
    
	i = document.getElementsByTagName("select").length;
	
	for (j = 0; j < i; j++) {
		var obj = document.getElementsByTagName("select")[j];
		var id = obj.id;
		if($(id)){
			obj.style.display = "";
		}
	} 
}

	function getXm(){
			var text="";
			var xmdm=$("xmdm").value;
			if($("xmlb")){
				var tableName = "xszz_zzxmb"; 
				var dm = "xmdm"; 
				var mc = "xmmc";
				var msg="";
				var pk = "xmlb";
				var pkValue = $("xmlb").value;
				var id = "xmdm";
					
				if(pkValue == ""){
					pk = "";
				}
				getXszzInfo.getXszzxmList(tableName, dm, mc, msg, pk, pkValue,function(data){
					for(i=0;i<data.length;i++){
							dm=""+data[i].dm;
							mc=""+data[i].mc;
							if(xmdm==dm){
								text+="<li>&nbsp;&nbsp;&nbsp;&nbsp;<a href=\"#\"  style='color:#0A63BF'  onclick=\"setXmdm('"+dm+"');checkTimes('"+dm+"');changeWin();\"><span class='ico'></span>"+mc+"</a></li>";
							}else{
								text+="<li>&nbsp;&nbsp;&nbsp;&nbsp;<a href=\"#\"  onclick=\"setXmdm('"+dm+"');checkTimes('"+dm+"');changeWin();\"><span class='ico'></span>"+mc+"</a></li>";
							}
							
							
					}
					document.getElementById('xmxslb').innerHTML = text;
				});
			}
		}
		
		
		function checkTimes(dm){
			allNotEmpThenGo('/xgxt/commXszz.do?method=jgcxManage&xmdm='+dm);
		}
		
		function setXmdm(dm){
			$("xmdm").value=""+dm;
		}
		
		function loadXmList(obj){
			var xmlb = jQuery(obj).val();
			if (xmlb != ''){
				jQuery('li[name='+xmlb+']',jQuery('#xmxslb')).attr('style','display:');
				jQuery('li[name!='+xmlb+']',jQuery('#xmxslb')).attr('style','display:none');
			} else {
				jQuery('li',jQuery('#xmxslb')).attr('style','display:');
			}
		}
		
	
		jQuery(function(){
			
			
			changeWin();
		});
		
		function changeWin() {
			var left=parent.document.getElementById("left");
			var right=parent.document.getElementById("right");
			
			if (left.className == "hide") {
<%--				left.className = "hide";--%>
<%--				right.style.width = "100%";--%>
<%--				if (jQuery('div.rightframe04')){--%>
<%--					jQuery('div.rightframe04').attr('class','rightframe04_hidden');--%>
<%--				}--%>
<%--			} --%>
<%--			else {--%>
				
				right.style.width = "100%";
				
				if (jQuery(document).find('div.rightframe04')){
					jQuery(document).find('div.rightframe04').attr('class','rightframe04_hidden');
				}
			}
		}
		
		
		</script>
		<style type="css">
.text-overflow {
	display: block; /*�����������*/
	width: 16em;
	word-break: keep-all; /* ������ */
	white-space: nowrap; /* ������ */
	overflow: hidden; /* ���ݳ������ʱ���س������ֵ����� */
	text-overflow: ellipsis;
	/* ���������ı����ʱ��ʾʡ�Ա��(...) ������overflow:hidden;һ��ʹ�á�*/
}
		
		</style>
	</head>

	<body onload="changeWin();getTjsz();">
		<!-- ���� -->
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a><bean:write name="title" /> </a>
			</p>
		</div>
		<!-- ���� end-->
		<html:form action="/commXszz">
			<!-- ������ -->
			<html:hidden property="iskns" styleId="iskns" />
			<%@ include file="/comm/hiddenValue.jsp"%>
			<input type="hidden" id="xmjb" value="${xmjb }" />
			<input type="hidden" id="xmdm" name="xmdm" value="${xmdm}" />
			<input type="hidden" id="rsNum" name="rsNum" value="${rsNum }" />
			<input type="hidden" id="widthType" name="widthType"
				value="${widthType}" />
			<%
					String widthType = request.getAttribute("widthType").toString();
					String width = "";
					if ("dbsx".equalsIgnoreCase(widthType)) {
						width = "width:81.5%";
					} else if ("kjfs".equalsIgnoreCase(widthType)) {
						width = "width:81.5%";
					}
			%>
			<!-- ������ end-->
			<div class="toolbox">
				<!-- ��ť -->
				<div class="buttonbox">
					<ul>
						<logic:equal value="yes" name="writeAble">
							<li>
								<a href="#" onclick="showAddJgJsp();return false;" class="btn_zj">����</a>
							</li>
							<li>
								<a href="#"
									onclick="showOpenInfo('/xgxt/commXszz.do?method=jgcxUpdate&iskns=${xszzTyForm.iskns }','update','','800','600');return false;"
									class="btn_xg">�޸�</a>
							</li>
							<li>
								<a href="#"
									onclick="sumitInfo('/xgxt/commXszz.do?method=jgcxManage','del');return false;"
									class="btn_sc">ɾ��</a>
							</li>
							<!-- ����ѧԺ -->
							<logic:equal value="10395" name="xxdm">
								<a href="#" onclick="tjBb();return false;" class="btn_tj">ͳ�Ʊ���</a>
							</logic:equal>
							<li>
								<a href="#" onclick="impInfo();return false;" class="btn_dr">����</a>
							</li>
							<li>
								<a href="#" title="����е������ݵĻ��������˰�ť����ͬ�����״̬�Ա�֤�����ڱ�ϵͳ�е�ͳһ"
									onclick="showTips('���������У���ȴ�......');refreshForm('/xgxt/commXszz.do?method=jgcxManage&doType=tb');return false;"
									class="btn_ccg">ͬ�����״̬</a>
							</li>

							<logic:equal value="yes" property="iskns" name="xszzTyForm">
								<logic:equal value="true" name="knsdl">
									<logic:equal value="true" name="jtqkdc">
										<li>
											<a href="#" title="Ϊ��֤����ͳһ�����ڵ������ݺ����˰�ť"
												onclick="showTips('���������У���ȴ�......');refreshForm('commXszz.do?method=jgcxManage&doType=jtqkdcTb');return false;"
												class="btn_ccg">ͬ����ͥ�������</a>
										</li>
									</logic:equal>
								</logic:equal>
							</logic:equal>

						</logic:equal>
						<logic:equal name="writeAble" value="yes" >
						<li>
							<a href="#" onclick="expInfo();return false;" class="btn_dc">����</a>
						</li>
						</logic:equal>
						<%--						<li>--%>
						<%--							<a href="#" --%>
						<%--								onclick="if($('xmdm').value == ''){alert('��ѡ����Ҫ��������Ŀ')}else{showExpDiv('expEiv',true,'#C7DEFC');getTableZd();}" --%>
						<%--								class="btn_dc">�����ӷ�</a>--%>
						<%--						</li>		--%>
					</ul>
				</div>
			</div>

			<div class="leftframe04" id="a11">
				<div class="menulist">
					<!--��start-->
					<div class="menutitle">
						<h3>
							<span class="title"> <logic:empty name="mklx">
					      	������Ŀ
					  </logic:empty> <logic:notEmpty name="mklx">
									<logic:equal name="mklx" value="zz">
					      	������Ŀ
					      </logic:equal>
									<logic:equal name="mklx" value="pj">
					      	������Ŀ
					      </logic:equal>
								</logic:notEmpty> </span>
						</h3>
						<!--CNLTreeMenu Start:-->
						<div class="CNLTreeMenu1" id="CNLTreeMenu1"
							style="height:420px; overflow-x:hidden;overflow-y:auto;">
							<p class="choose_select">
								<label>
									��Ŀ���
								</label>
								<html:select property="xmlb" style="width:80px" styleId="xmlb" onchange="loadXmList(this)">
									<html:option value=""></html:option>
									<html:options collection="xmlbList" property="en"
										labelProperty="cn" />
								</html:select>
							</p>
							<ul id="xmxslb">
								<logic:iterate id="cxxm" name="xmList">
									<li name="${cxxm.xmlb}"
										class='<logic:equal value="${xmdm}" name="cxxm" property="xmdm">Current</logic:equal>'>
										<a  href="#" class='Child'
											title = "${cxxm.xmmc}"
											onclick="setXmdm('${cxxm.xmdm}');checkTimes('${cxxm.xmdm}');return false"
											style='float:left;width:110px;text-overflow:ellipsis;white-space:nowrap;overflow:hidden;'>${cxxm.xmmc}</a>
									</li>
								</logic:iterate>
							</ul>
						</div>
					</div>
					<!--��end-->
				</div>
			</div>

			<div class="rightframe04" style="<%=width%>">
				<!--��ѯ����-->
				<div class="searchtab">
					<table width="100%" border="0">
						<tfoot>
							<tr>
								<td colspan="6">
									<div class="btn">
										<input type="hidden" name="go" value="a" />
										<button type="button" class="btn_cx" id="search_go"
											onclick="allNotEmpThenGo('/xgxt/commXszz.do?method=jgcxManage');return false;">
											�� ѯ
										</button>
										&nbsp;&nbsp;&nbsp;&nbsp;
										<button type="button" class="btn_cz" id="btn_cz"
											onclick="czSearchCond('nj-xn-xq-nd-kssj-jssj-xy-zy-bj-xh-xm-shzt1-shzt2-shzt3');return false;">
											�� ��
										</button>
									</div>
								</td>
							</tr>
						</tfoot>
						<tbody>
							<!-- ��һ�� -->
							<tr>
								<th>
									ѧ��
								</th>
								<td>
									<html:select property="xn" style="width:100px" styleId="xn">
										<html:options collection="xnList" property="xn"
											labelProperty="xn" />
									</html:select>
								</td>
								<th>
									���
								</th>
								<td>
									<html:select property="nd" style="width:100px" styleId="nd">
										<html:options collection="ndList" property="nd"
											labelProperty="nd" />
									</html:select>
								</td>
								<th>
									ѧ��
								</th>
								<td>
									<html:select property="xq" style="width:100px" styleId="xq">
										<html:option value=""></html:option>
										<html:options collection="xqList" property="xqdm"
											labelProperty="xqmc" />
									</html:select>
								</td>
							</tr>
							<!-- �ڶ��� -->
							<tr>
								<th>
									�꼶
								</th>
								<td>
									<html:select property="nj" style="width:100px" styleId="nj"
										onchange="initZyList();initBjList()">
										<html:option value=""></html:option>
										<html:options collection="njList" property="nj"
											labelProperty="nj" />
									</html:select>
								</td>
								<th>
									ѧ��
								</th>
								<td>
									<!-- ѧ���û� -->
									<logic:equal name="userType" value="stu">
										<html:text property="xh" styleId="xh" style="width:100px"
											readonly="true" />
									</logic:equal>
									<!-- ѧ���û� -->
									<logic:notEqual name="userType" value="stu">
										<html:text property="xh" styleId="xh" style="width:100px"
											maxlength="20" />
									</logic:notEqual>
								</td>
								<th>
									����
								</th>
								<td>
									<html:text property="xm" styleId="xm" style="width:100px"
										maxlength="20" />
								</td>
							</tr>
							<!-- �ڶ��� -->
							<tr>
								<th>
									<bean:message key="lable.xsgzyxpzxy" />
								</th>
								<td>
									<!-- ��ѧԺ�û� -->
									<logic:equal name="isxy" value="true">
										<html:hidden property="xydm" />
										<html:select property="xydm" style="width:100px" styleId="xy"
											disabled="true">
											<html:option value=""></html:option>
											<html:options collection="xyList" property="xydm"
												labelProperty="xymc" />
										</html:select>
									</logic:equal>
									<!-- ��ѧԺ�û� end -->
									<!-- ��ѧԺ�û� -->
									<logic:equal name="isxy" value="false">
										<html:select property="xydm" style="width:100px" styleId="xy"
											onchange="initZyList();initBjList();">
											<html:option value=""></html:option>
											<html:options collection="xyList" property="xydm"
												labelProperty="xymc" />
										</html:select>
									</logic:equal>
									<!-- ��ѧԺ�û� end-->
								</td>
								<th>
									רҵ
								</th>
								<td>
									<html:select property="zydm" style="width:100px" styleId="zy"
										onchange="initBjList()">
										<html:option value=""></html:option>
										<html:options collection="zyList" property="zydm"
											labelProperty="zymc" />
									</html:select>
								</td>
								<th>
									�༶
								</th>
								<td>
									<html:select property="bjdm" style="width:100px" styleId="bj">
										<html:option value=""></html:option>
										<html:options collection="bjList" property="bjdm"
											labelProperty="bjmc" />
									</html:select>
								</td>
							</tr>
							<!-- ������ -->

							<!-- ������ -->
							<tr>
								<th>
									����ʱ���ѯ��Χ
								</th>
								<td colspan="3">
									<html:text property="kssj" styleId="kssj"
										onblur="dateFormatChg(this)" style="width:100px;cursor:hand;"
										onclick="return showCalendar('kssj','y-mm-dd');" />��
								
									<html:text property="jssj" styleId="jssj"
										onblur="dateFormatChg(this)" style="width:100px;cursor:hand;"
										onclick="return showCalendar('jssj','y-mm-dd');" />
								</td>
								<th>
									<span id="jbId" style="display : none"> ��Ŀ���� </span>
								</th>
								<td>
									<span id="jbVId" style="display : none"> <html:select
											property="xmzzjb" style="width:100px" styleId="xmzzjb">
											<html:option value="">----��ѡ��----</html:option>
										</html:select> </span>
								</td>
								
							</tr>
							<!-- ������ -->
							<tr>
								<th>
									<span id="zt1Id" style="display : none"> һ����� </span>
								</th>
								<td>
									<span id="zt1VId" style="display : none"> <html:select
											property="shzt1" style="width:100px" styleId="shzt1">
											<html:option value="">----��ѡ��----</html:option>
											<html:options collection="shList" property="en"
												labelProperty="cn" />
										</html:select> </span>
								</td>
								<th>
									<span id="zt2Id" style="display : none"> ������� </span>
								</th>
								<td>
									<span id="zt2VId" style="display : none"> <html:select
											property="shzt2" style="width:100px" styleId="shzt2">
											<html:option value="">----��ѡ��----</html:option>
											<html:options collection="shList" property="en"
												labelProperty="cn" />
										</html:select> </span>
								</td>
								<th>
									<span id="zt3Id" style="display : none"> ������� </span>
								</th>
								<td>
									<span id="zt3VId" style="display : none"> <html:select
											property="shzt3" style="width:100px" styleId="shzt3">
											<html:option value="">----��ѡ��----</html:option>
											<html:options collection="shList" property="en"
												labelProperty="cn" />
										</html:select> </span>
								</td>
							</tr>
						</tbody>
					</table>

				</div>



				<!-- �������� end-->
				<!-- ��ѯ���-->
				<h3 class="datetitle_01">
					<span> ��ѯ���&nbsp;&nbsp; <logic:notEmpty name="rsList">
							<font color="blue">��ʾ��������ͷ��������˫����¼�ɲ鿴��ϸ��Ϣ</font>
						</logic:notEmpty> </span>
				</h3>
				<div class="con_overlfow">
					<table summary="" class="dateline tablenowrap" width="100%"
						id="rsTable">
						<!-- ��ͷ -->
						<thead>
							<tr align="center" style="cursor:hand">
								<td>
									<!-- �ٵ�CHECKBOX ��ֹȫѡ������-->
									<input type="checkbox" name="jiade" value="all" onclick="" />
									<input type="checkbox" id="selall" name="selall"
										onclick="selAll()" />
								</td>
								<logic:iterate id="tit" name="topTr" offset="0">
									<td id="<bean:write name="tit" property="en"/>"
										onclick="tableSort(this)" nowrap>
										<bean:write name="tit" property="cn" />
									</td>
								</logic:iterate>
							</tr>
						</thead>
						<!-- ��ͷ end-->
						<!--���� -->
						<tbody>
							<logic:notEmpty name="rsList">
								<logic:iterate name="rsList" id="rs" indexId="index">
									<tr onclick="rowOnClick(this);" style="cursor:hand"
										ondblclick="showOpenInfo('/xgxt/commXszz.do?method=jgcxUpdate&iskns=${xszzTyForm.iskns }','view','','800','600');">
										<td align="center">
											<logic:equal name="rs" property="delete" value="yes">
												<input type="checkbox" id="checkVal"
													name="primarykey_checkVal" value="${rs.pk }" />
											</logic:equal>
											<logic:equal name="rs" property="delete" value="no">
												<input type="checkbox" id="checkVal" disabled="disabled"
													name="primarykey_checkVal" value="${rs.pk }" />
											</logic:equal>
										</td>
										<td align="center">
											${rs.xh }
										</td>
										<td align="center">
											${rs.xm }
										</td>
										<td align="center">
											${rs.xb }
										</td>
										<td align="center">
											${rs.nj }
										</td>
										<td align="center">
											${rs.xymc }
										</td>
										<td align="center">
											${rs.zymc }
										</td>
										<td align="center">
											${rs.bjmc }
										</td>
										<td align="center">
											${rs.xmmc }
										</td>
										<logic:notEmpty name="rs" property="xmzzjb">
											<logic:equal name="rs" property="sqzq" value="ѧ��">
												<td align="center">
													${rs.xn }
												</td>
											</logic:equal>
											<logic:equal name="rs" property="sqzq" value="ѧ��">
												<td align="center">
													${rs.xn }
												</td>
												<td align="center">
													${rs.xq }
												</td>
											</logic:equal>
											<logic:equal name="rs" property="sqzq" value="���">
												<td align="center">
													${rs.nd }
												</td>
											</logic:equal>
										</logic:notEmpty>
										<td align="center">
											${rs.sqsj }
										</td>
										
										<!-- ����ʦ�� -->
											<logic:equal name="xxdm" value="88888">
												<!-- ��ɫͨ�� -->
												<logic:equal name="rs" property="xmdm" value="5002">
													<td align="center">
														${rs.sfdb }
													</td>
													<td align="center">
														${rs.kzzd3 }
													</td>
													<td align="center">
														${rs.jtrjsr }
													</td>
												</logic:equal>
												
											</logic:equal>
										
										<logic:empty name="rs" property="xmzzjb">
											<td align="center">
												<logic:equal name="rs" property="shjb" value="�������">
															�������
														</logic:equal>
												<logic:equal name="rs" property="shjb" value="һ�����">
															${rs.shzt1 }
														</logic:equal>
												<logic:equal name="rs" property="shjb" value="�������">
															һ����ˣ�${rs.shzt1 }��<br>
														������ˣ�${rs.shzt2 }�� 
												</logic:equal>
												<logic:equal name="rs" property="shjb" value="�������">
															һ����ˣ�${rs.shzt1 }��<br>
														������ˣ�${rs.shzt2 }�� 
													<br>
														������ˣ�${rs.shzt3 }�� 
												</logic:equal>
											</td>
										</logic:empty>
										<logic:notEmpty name="rs" property="xmzzjb">
											<logic:equal name="rs" property="sffj" value="�ּ�">
												<td align="center">
													${rs.xmzzjb }
												</td>
											</logic:equal>
											<logic:equal name="rs" property="sfje" value="��Ҫ">
												<td align="center">
													${rs.xmzzje }
												</td>
											</logic:equal>
											<!-- ����ʦ�� -->
											<logic:equal name="xxdm" value="10477">
												<!-- ��ɫͨ�� -->
												<logic:equal name="rs" property="xmdm" value="1004">
													<td align="center">
														${rs.yjje }
													</td>
													<td align="center">
														${rs.sjje }
													</td>
												</logic:equal>
												<!-- ������ѧ���� -->
												<logic:equal name="rs" property="xmdm" value="5003">
													<td align="center">
														${rs.dknd }
													</td>
													<td align="center">
														${rs.dkje }
													</td>
												</logic:equal>
											</logic:equal>
											<!-- ����ʦ�� end-->
											<logic:equal name="rs" property="shjb" value="�������">
												<td align="center">
													�������
												</td>
											</logic:equal>
											<logic:equal name="rs" property="shjb" value="һ�����">
												<td align="center">
													${rs.shzt1 }
												</td>
											</logic:equal>
											<logic:equal name="rs" property="shjb" value="�������">
												<td align="center">
													${rs.shzt1 }
												</td>
												<td align="center">
													${rs.shzt2 }
												</td>
											</logic:equal>
											<logic:equal name="rs" property="shjb" value="�������">
												<td align="center">
													${rs.shzt1 }
												</td>
												<td align="center">
													${rs.shzt2 }
												</td>
												<td align="center">
													${rs.shzt3 }
												</td>
											</logic:equal>
										</logic:notEmpty>
									</tr>
								</logic:iterate>
							</logic:notEmpty>
							<%
									ArrayList list = ((ArrayList) request.getAttribute("rsList"));
									int rsNum = 0;
									if (list != null) {
										rsNum = list.size();
									}
									int pageSize = (Integer) request.getAttribute("pageSize");
									for (int i = 0; i < (pageSize - rsNum); i++) {
							%>
							<tr>
								<td>
									<input type="checkbox" value="" disabled="true" />
								</td>
								<logic:iterate id="tit" name="topTr" offset="0">
									<td>
										&nbsp;
									</td>
								</logic:iterate>
							</tr>
							<%
							}
							%>
						</tbody>
						<!--���� end -->
					</table>
				</div>
				<!--��ҳ-->
				<jsp:include flush="true" page="/sjcz/turnpage.jsp?form=xszzTyForm"></jsp:include>
				<!--��ҳend-->
				<script type="text/javascript">
									$('choose').className="hide";
							</script>
			</div>
			<!-- ��ѯ��� end-->
		</html:form>
		<div id="tmpdiv1"></div>
		<!-- ��ʾ��Ϣ -->
		<%@ include file="other/tsxx.jsp"%>
		<!-- ��ʾ��Ϣ end-->

		<!-- �������ƵĲ� -->
		<%@ include file="/comm/exp/commExp.jsp"%>
		<!-- �������ƵĲ� end-->
	</body>
</html>
