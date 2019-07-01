<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- ���ߣ�ΰ����� -->
<jsp:directive.page import="java.util.ArrayList" />
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/xgutil.js"></script>
		<script language="javascript" src="js/xszz/xszzFunction.js"></script>
		<script type='text/javascript'
			src='/xgxt/dwr/interface/getXszzInfo.js'></script>
		<script language="javascript" src="js/xszz/xszzComm.js"></script>
		<script language="javascript">
		
		//��ʾ���水ť
		function showSaveBtn(){
			var rsNum = $("rsNum").value;
			
			if(rsNum != "" && rsNum != "0"){
				$("saveBtn").style.display = "";
			}else{
				$("saveBtn").style.display = "none";
			}
		}
		
		//�޸���������
		function saveRssz(cz){
			
			var xmdm = $("queryequals_xmdm").value;
		
			if(xmdm == ""){
				alert("��ָ����Ҫ���õ���Ŀ!");
				return false;	
			}
			if (confirm('ȷ���÷�����')){
				saveUpdate('/xgxt/commXszz.do?method=rsszManage&doType=save&cz='+cz,'');
			}
		}
		//���ÿ��Ƽ���
		function setKzjb(){	
			
			var xmdm = $("queryequals_xmdm").value;
			var tableName="xszz_zzxmb";
			var colList =["kzjb"];
			var pk = "xmdm";
			var pkValue = xmdm;
			dwr.engine.setAsync(false);
			getXszzInfo.getXszzInfo(tableName, pk, pkValue,colList,function(data){ 
				if(data!=null){
					var kzjb = data.kzjb;
					$("kzjb").value = kzjb;	
					$("kzjbSelect").value = kzjb;			
				}
			});
			
			dwr.engine.setAsync(true);
		}
		
		//��ʾ����ҳ��
		function showSzym(){
			var iskns = $('iskns').value;
			var xmdm = $("queryequals_xmdm").value;
			
			if(xmdm != ""){
			
				var url = "/xgxt/commXszz.do?method=rsszUpdate&iskns="+iskns;
					url+="&xmdm="+xmdm;
					
				showTopWin(url,'800','600');
			}else{
			
				alert("��ָ����Ҫ���õ���Ŀ!");
				
			}
		}
		
		//��ѯ��������
		function searchXmrs(){
			var xmdm = $("queryequals_xmdm").value;
			if(xmdm != ""){
				allNotEmpThenGo('/xgxt/commXszz.do?method=rsszManage&xmdm='+xmdm);
			}else{
				alert("��ָ����Ҫ���õ���Ŀ!");		
			}
		}
		
		//�ж��Ƿ����޸Ĺ�����Ϣ û�б���
		function checkXxbc(){
			var len=0;
			if($("number")){
				 var obj = document.getElementsByName("number");
				 len=obj.length;
			}
			for(i=0;i<len;i++){
				var hid_szrs=$("hid_szrs"+i).value;
				var szrs=$("szrs"+i).value;
				if(hid_szrs !=szrs){
					if (confirm("������������δ���������,Ҫ������?")) {
						checkRssz();
						return false;
						
					}else{
						searchXmrs();
						return false;
					}
				}
			}
			
			searchXmrs();
		}
		
		function checkRssz(cz){
			var len=0;
			if($("number")){
				 var obj = document.getElementsByName("number");
				 len=obj.length;
			}
			for(i=0;i<len;i++){
				var szrs=$("szrs"+i).value;
				if(szrs.length>1 && szrs.substring(0,1)=="0"){
					alert("������������Ϊ����!");
					$("szrs"+i).focus();
					return false;
				}
				
				if(szrs==null || szrs==""){
					alert("�������ò���Ϊ��!");
					$("szrs"+i).focus();
					return false;
				}
			}
			saveRssz(cz);
		}
		
		
		
		function getXm(){
			var text="<span>";
			var xmdm=$("queryequals_xmdm").value;
			var num=document.getElementsByName("rsxmdm").length;
			if($("xmlb")){
				for(i=0;i<num;i++){	
					var dm=document.getElementsByName("rsxmdm")[i].value;
					var mc=document.getElementsByName("rsxmmc")[i].value;
					var lx=document.getElementsByName("rsxmlb")[i].value;
					if($("xmlb").value==lx || $("xmlb").value==""){
						if(xmdm==dm){
							text+="<li id='"+lx+"' style='background:#dce8f8'>&nbsp;&nbsp;&nbsp;&nbsp;<a href=\"#\"  style='color:#0A63BF'  onclick='setXmdm(\""+dm+"\");checkXxbc()'><span class='ico'>"+mc+"</span></a></li>";
						}else{
							text+="<li id='"+lx+"'>&nbsp;&nbsp;&nbsp;&nbsp;<a href=\"#\"  onclick='setXmdm(\""+dm+"\");checkXxbc()'><span class='ico'></span>"+mc+"</a></li>";
						}
					}
				}
				text+="</span>"
				document.getElementById('xmxslb').innerHTML = text;
				
			}
		}
		
		function setXmdm(dm){
			$("queryequals_xmdm").value=dm;
			setKzjb();
		}
		
		function checkCsh(){
			if($("number")){
				 var obj = document.getElementsByName("number");
				 len=obj.length;
				for(i=0;i<len;i++){
					var hid_szrs=$("hid_szrs"+i).value;
					var szrs=$("szrs"+i).value;
					if(hid_szrs !=szrs){
						if (confirm("������������δ���������,Ҫ������?")) {
							checkRssz('csh');
							return false;
							
						}else{
							showSzym();
							return false;
						}
					}
				}
			}
			showSzym();
		}
		
		function isNotCsh(){
			var cz=$("cz").value;
			if("csh"==cz){
				$("cz").value="bc";
				showSzym();
			}
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
		</script>
	</head>
	<body onload="showSaveBtn();isNotCsh()">
		<!-- ���� -->
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a><bean:write name="title" /> </a>
			</p>
		</div>
		<!-- ���� end-->
		<html:form action="/commXszz">
			<%
					String widthType = request.getAttribute("widthType").toString();
					String width = "";
					if ("dbsx".equalsIgnoreCase(widthType)) {
						width = "width:81.5%";
					} else if ("kjfs".equalsIgnoreCase(widthType)) {
						width = "width:81.5%";
					}
			%>
			<!-- ������ -->
			<html:hidden property="iskns" styleId="iskns" />
			<%@ include file="/comm/hiddenValue.jsp"%>
			<input type="hidden" id="cz" name="cz" value="${cz}" />
			<input type="hidden" id="rsNum" name="rsNum" value="${rsNum }" />
			<input type="hidden" id="queryequals_xmdm" name="queryequals_xmdm"
				value="${xmdm}" />
			<input type="hidden" id="widthType" name="widthType"
				value="${widthType}" />
			<!-- ������ end-->
			<div class="toolbox">
				<!-- ��ť -->
				<logic:equal value="yes" name="writeAble">
					<div class="buttonbox">
						<ul>
							<li>
								<a href="#" onclick="checkCsh();return false;" class="btn_csh">��ʼ��</a>
							</li>
							<li>
								<p id="saveBtn" style="display : none">
									<a href="#" onclick="checkRssz();return false;" class="btn_ccg">����</a>
								</p>
							</li>
						</ul>
					</div>
				</logic:equal>
			</div>

			<!-- �๦�ܲ����� -->
			<div class="leftframe04">
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
								<html:select property="queryequals_xmlb" style="width:80px"
									styleId="xmlb" onchange="loadXmList(this)">
									<html:option value=""></html:option>
									<html:options collection="xmlbList" property="en"
										labelProperty="cn" />
								</html:select>
							</p>
							<ul id="xmxslb">
								<logic:iterate id="rssz" name="zzxmList">
									<li name="${rssz.lb}"
										class='<logic:equal value="${xmdm}" name="rssz" property="dm">Current</logic:equal>'>
										<a href="#" class='Child' title="${rssz.mc}"
											onclick="setXmdm('${rssz.dm}');checkXxbc();return false;"
											style='float:left;width:110px;text-overflow:ellipsis;white-space:nowrap;overflow:hidden;'>${rssz.mc}</a>
									</li>
								</logic:iterate>
							</ul>
						</div>

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
								<td colspan="10">
									<div class="bz">
										<font color="blue">&nbsp;&nbsp;&nbsp;&nbsp;��ǰѧ�꣺${xn}&nbsp;&nbsp;&nbsp;&nbsp;
											��ǰѧ�ڣ�${xq}&nbsp;&nbsp;&nbsp;&nbsp;��ǰ��ȣ�${nd }</font>
									</div>
									<div class="btn">
										<input type="hidden" name="go" value="a" />
										<button type="button" class="btn_cx" id="search_go" onclick="checkXxbc();return false;">
											�� ѯ
										</button>
										&nbsp;&nbsp;&nbsp;&nbsp;
										<button type="button" class="btn_cz" id="btn_cz"
											onclick="czSearchCond('nj-kzjbSelect-xy-zy-bj');return false;">
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
									���Ƽ���
								</th>
								<td>
									<html:hidden property="kzjb" styleId="kzjb" />
									<html:select property="kzjb" style="width:100px"
										styleId="kzjbSelect" onchange="" disabled="true">
										<html:option value=""></html:option>
										<html:options collection="kzjbList" property="en"
											labelProperty="cn" />
									</html:select>
								</td>
								<th>
									�꼶
								</th>
								<td>
									<html:select property="queryequals_nj" styleId="nj"
										style="width:100px" onchange="initZyList();initBjList()">
										<html:option value=""></html:option>
										<html:options collection="njList" property="nj"
											labelProperty="nj" />
									</html:select>
								</td>
								<th>
									<bean:message key="lable.xsgzyxpzxy" />
								</th>
								<td>
									<!--  ��ѧԺ�û� -->
									<logic:notEqual name="userType" value="xy">
										<html:select property="queryequals_xydm" style="width:100px"
											styleId="xy" onchange="initZyList();initBjList();">
											<html:option value=""></html:option>
											<html:options collection="xyList" property="xydm"
												labelProperty="xymc" />
										</html:select>
									</logic:notEqual>
									<!--  ��ѧԺ�û� -->
									<logic:equal name="userType" value="xy">
										<html:hidden property="queryequals_xydm" />
										<html:select property="queryequals_xydm" style="width:100px"
											styleId="xy" disabled="true">
											<html:option value=""></html:option>
											<html:options collection="xyList" property="xydm"
												labelProperty="xymc" />
										</html:select>
									</logic:equal>
								</td>
							</tr>
							<!-- �ڶ��� -->
							<tr>

								<th>
									רҵ
								</th>
								<td>
									<html:select property="queryequals_zydm" style="width:100px"
										styleId="zy" onchange="initBjList()">
										<html:option value=""></html:option>
										<html:options collection="zyList" property="zydm"
											labelProperty="zymc" />
									</html:select>
								</td>
								<th>
									�༶
								</th>
								<td>
									<html:select property="queryequals_bjdm" style="width:100px"
										styleId="bj">
										<html:option value=""></html:option>
										<html:options collection="bjList" property="bjdm"
											labelProperty="bjmc" />
									</html:select>
								</td>
								<th>

								</th>
								<td>

								</td>
							</tr>
						</tbody>
					</table>
				</div>
				<!-- �������� end-->
				<!-- ��ѯ���-->
				<h3 class="datetitle_01">
					<span> ��ѯ���&nbsp;&nbsp; <logic:empty name="rs">
							<font color="red">δ�ҵ��κμ�¼��</font>
						</logic:empty> <logic:notEmpty name="rs">
							<!-- �жϸ���Ŀ�Ƿ������������ -->
							<logic:notEmpty name="xmInfo" property="rssx">
								<font color="blue"> ע������Ŀ��������Ϊ${xmInfo.rssx }��,Ŀǰ�ѷ��� <logic:empty
										name="xmInfo" property="szrs">0</logic:empty> <logic:notEmpty
										name="xmInfo" property="szrs">${xmInfo.szrs }</logic:notEmpty>
									�� </font>
							</logic:notEmpty>
							<logic:empty name="xmInfo" property="rssx">
								<font color="blue">ע������Ŀ�������������ޡ�</font>
							</logic:empty>
						</logic:notEmpty> </span>
				</h3>
				<div class="con_overlfow">
					<table width="100%" class="dateline tablenowrap">
						<!-- ��ͷ -->
						<thead>
							<tr align="center" style="cursor:hand">
								<logic:iterate id="tit" name="topTr" offset="0">
									<td id="<bean:write name="tit" property="en"/>"
										onclick="tableSort(this)" nowrap>
										<bean:write name="tit" property="cn" />
									</td>
								</logic:iterate>
							</tr>
						</thead>
						<!-- ��ͷ end-->


						<tbody>
							<logic:notEmpty name="rs">
								<!--���� -->
								<logic:iterate name="rs" id="s" indexId="index">
									<tr onclick="rowOnClick(this);" style="cursor:hand"
										ondblclick="">
										<!-- �������� -->
										<logic:iterate id="v" name="s" offset="4">
											<td align="left">
												${v }
											</td>
										</logic:iterate>
										<!-- �������� -->
										<logic:iterate id="v" name="s" offset="2" length="1">
											<td align="left">
												<input type="hidden" name="number" id="number" />
												<!-- ���� -->
												<logic:iterate id="p" name="s" offset="0" length="1">
													<input type="hidden" name="primarykey_checkVal"
														id="pk${index }" value="${p }" />
												</logic:iterate>
												<!-- �Ƿ���� -->
												<logic:iterate id="cz" name="s" offset="1" length="1">
													<input type="hidden" name="iscz" id="iscz${index }"
														value="${cz }" />
												</logic:iterate>
												<!-- �����꼶 -->
												<logic:iterate id="sznj" name="s" offset="3" length="1">
													<input type="hidden" name="sznj" id="sznj${index }"
														value="${sznj }" />
												</logic:iterate>

												<!-- ����ʱ��������� �����ж����õ�ʱ���Ƿ�ı� -->
												<input type="hidden" name="hid_szrs" id="hid_szrs${index }"
													value="${v }" />
												<input type="type" name="szrs" onblur="" id="szrs${index }"
													value="${v }" onkeydown="return onlyNum(this,5)"
													onmousedown="return onlyNum(this,5)"
													style="width:50%;ime-mode:disabled" />
											</td>
										</logic:iterate>
									</tr>
								</logic:iterate>
							</logic:notEmpty>
							<!--���� end -->
							<%
									ArrayList list = ((ArrayList) request.getAttribute("rs"));
									int rsNum = 0;
									if (list != null) {
										rsNum = list.size();
									}
									int pageSize = (Integer) request.getAttribute("pageSize");
									for (int i = 0; i < (pageSize - rsNum); i++) {
							%>
							<tr>
								<logic:iterate id="tit" name="topTr" offset="0" length="8">
									<td>
										&nbsp;
									</td>
								</logic:iterate>
								<logic:equal name="sffj" value="�ּ�">
									<td>
										&nbsp;
									</td>
								</logic:equal>
								<logic:iterate id="tit" name="topTr" offset="7" length="2">
									<td>
										&nbsp;
									</td>
								</logic:iterate>
							</tr>
							<%
							}
							%>
						</tbody>

					</table>
				</div>
				<!--��ҳ-->
				<jsp:include flush="true" page="/sjcz/turnpage.jsp?form=xszzTyForm"></jsp:include>
				<!--��ҳend-->
				<script type="text/javascript">
									$('choose').className="hide";
									</script>
		</html:form>
		<!-- ��ʾ��Ϣ -->
		<%@ include file="other/tsxx.jsp"%>
		<!-- ��ʾ��Ϣ end-->
	</body>
</html>
