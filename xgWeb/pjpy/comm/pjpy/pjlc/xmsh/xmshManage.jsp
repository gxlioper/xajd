<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- ���ߣ�ΰ����� -->
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script type='text/javascript' src='/xgxt/dwr/interface/getCommPjpy.js'></script>
		<script language="javascript" defer="defer">
		//��ѯ�����
		function searchRs(){
		
			var li_num = document.getElementsByTagName('li').length;
			var flag = false;
			
			for(var i=0;i<li_num;i++){
			
				var obj = document.getElementsByTagName('li')[i];
				
				if(obj.className = "Child"){
					var id = "li_"+i;
					if($(id) && $(id).style.background=="#dce8f8"){
						flag = true;
						break;
					}
				}
			}
			
			if(flag){
				searchByUserGw();
			}else{
				alertInfo("��ѡ����Ҫ��˵���Ŀ��");
				return false;
			}	
		}
		
		//��ʾ��Ŀ����Div
		function showSearchXmDiv(){
			viewTempDiv("��������","searchXmDiv",400,200);
		}
		
		//���ݹ����������˳���Ŀ
		function setNewXm(){
		
			var xmdm = $("xmdm").value;// ��Ŀ����
				$("hid_xmdm").value = xmdm;
			
			var xmmc = $("xmmc").value;// ��Ŀ����
				$("hid_xmmc").value = xmmc;
		
			var ywmc = $("ywmc").value;// Ӣ������
				$("hid_ywmc").value = ywmc;
		
			var xmxz = $("select_xmxz").value;// ��Ŀ����
				$("hid_xmxz").value = xmxz;
		
			var xmfw = $("select_xmfw").value;// ��Ŀ��Χ
				$("hid_xmfw").value = xmfw;
		
			var xmlx = $("select_xmlx").value;// ��Ŀ����
				$("hid_xmlx").value = xmlx;
			
			var xmInfo = [xmdm,xmmc,ywmc,xmxz,xmfw,xmlx];
			
			var userStatus = $("userStatus").value;
			var userName = $("userName").value;
			var userDep = $("userDep").value;
			
			dwr.engine.setAsync(false);
			
			getCommPjpy.getXmInfoList(xmInfo,userStatus,userName,userDep,function(data){
				if( data != null && data.length > 0){
					$("left_ul").innerHTML = "";
					var divHtml = "";
					for(var i=0;i<data.length;i++){
						divHtml += "<li id=\"li_"+i+"\" class=\"Child\">";
						divHtml += "<a href=\"#\" name=\"left_a\" id=\"left_a_"+i+"\" onclick=\"setLiClick('"+i+"');return false;\" style=\"\">";
						divHtml += "<span class=\"ico\"></span>";
						divHtml += data[i].xmmc;
						divHtml += "</a>";
						divHtml += "<input type=\"hidden\" id=\"xmdm_"+i+"\" value=\""+data[i].xmdm+"\"/>";
						divHtml += "</li>";	
					}
					
					$("left_ul").innerHTML = divHtml;
				}else{
					$("left_ul").innerHTML = "";
				}
			});
			
			dwr.engine.setAsync(true);
			
		}
		
		function searchByUserGw(){
		
			var xmdm = $("shxm").value;// ��Ŀ����
			var userName = $("userName").value;// ��Ŀ����
			var shjb = $("shjb").value;// ��˼���
			
			var flag = false;
			
			dwr.engine.setAsync(false);
			getCommPjpy.getXmszInfo(xmdm,userName,function(data){
				if( data != null && data.length > 0){
					if(data.length == 1){
						flag = true;
						$("shjb").value = data[0].shjb;
						$("spgw").value = data[0].spgw;
					}else{
					
						var divHtml = "";
						
						for(var i=0;i<data.length;i++){
							if(i!=0){
								divHtml+= "</br>";
							}
							divHtml+= "<input type=\"radio\"";
							divHtml+= "name=\"gwxz\"";
							if(i==0){
								divHtml+= "checked=\"checked\"";
							}
							divHtml+= "id=\"gwxz"+data[i].spgw+"\"";
							divHtml+= "onclick=\"$('spgw').value=this.value;";
							divHtml+= "$('shjb').value="+data[i].shjb+"\"";
							divHtml+= "value=\""+data[i].spgw+"\">";
							divHtml+= data[i].gwmc;
							if(i==0){
								$("shjb").value = data[i].shjb;
								$("spgw").value = data[i].spgw;
							}
						}
						viewTempDiv("��λѡ��","gwxzDiv",400,200);
						$("div_gwxz").innerHTML = divHtml;
					}
				}
			});
			
			if(flag){
				searchRsList();
			}
			
			dwr.engine.setAsync(true);
		}
		
		//��Ŀ���
		function clickXmsh(){
		
			var num = document.getElementsByName("primarykey_checkVal").length;
			var flag = false;
			var n = 0;
			
			for(var i=0;i<num;i++){
				var obj = document.getElementsByName("primarykey_checkVal")[i];
				if(obj.checked == true){
					flag = true;
					n++;
				}
			}
			
			if(flag){
				if(n>1){
					viewTempDiv("״̬ѡ��","shztDiv",300,200);
				}else{
					showShInfo("update");
				}
			}else{
				alertInfo("�빴ѡ��Ҫ��˵������¼��");
			}
			
		}
		
		//�������״̬
		function saveShzt(){
		
			var msg = "ȷ���޸�ѡ�м�¼�����״̬��\n";
				msg+= "ע��\n";
				msg+= "ͨ  ������һ����ɲ鿴���������¼\n";
				msg+= "��ͨ������һ�����޷��鿴���������¼\n";
				msg+= "��  �أ���Ҫ��һ�����������ͨ���󣬱�����Ų鿴���������¼";
				
			if (confirm(msg)) {
				saveUpdate('/xgxt/pjpyXmsh.do?method=xmshManage&doType=sh','');
			}
		}
		
		//��ʾ�����Ϣ
		function showShInfo(type){
			var url = "/xgxt/pjpyXmsh.do?method=xmshDetail";
				url+="&shjb="+$("shjb").value;
				url+="&shxm="+$("shxm").value;
			showInfo(url,type,'800','600');
		}
		
		function searchRsList(){
			var url = "/xgxt/pjpyXmsh.do?method=xmshManage";
			var xmdm = "";// ��Ŀ����
			if($("hid_xmdm")){
				xmdm =$("hid_xmdm").value;
			}
			var xmmc = "";// ��Ŀ����
			if($("hid_xmmc")){
				xmmc =$("hid_xmmc").value;
			}
			var ywmc = "";// Ӣ������
			if($("hid_ywmc")){
				ywmc =$("hid_ywmc").value;
			}
			var xmxz = "";// ��Ŀ����
			if($("hid_xmxz")){
				xmxz =$("hid_xmxz").value;
			}
			var xmfw = "";// ��Ŀ��Χ
			if($("hid_xmfw")){
				xmfw =$("hid_xmfw").value;
			}
			var xmlx = "";// ��Ŀ����
			if($("hid_xmlx")){
				xmlx =$("hid_xmlx").value;
			}
		
			url+= "&xmdm="+xmdm;
			url+= "&xmmc="+xmmc;
			url+= "&ywmc="+ywmc;
			url+= "&xmxz="+xmxz;
			url+= "&xmfw="+xmfw;
			url+= "&xmlx="+xmlx;
	
			showTips('���ݲ�ѯ�У����Ժ�......');
			
			allNotEmpThenGo(url);
		}
		
		//ǰ����Ŀ�ϱ�
		function goXmsb(){
			var url = "pjpyXmsb.do?method=xmsbManage";
				url+= "&xmdm="+$("xmdm").value;
				url+= "&bjdm="+$("bjdm").value;
			
			showWaitingDiv("30000");
			
			refreshForm(url);
		}
		</script> 	
	</head>
	<body onload="" >
		<!-- ���� -->
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>�������� - �ҵ����� - ��Ŀ���</a>
			</p>

			<!-- ���߰��� -->
			<p class="help">
				<a href="#" onclick="return false;" onmousedown ="showHelpDiv();">ʹ�ð���</a>
			</p>
			<!-- ���߰��� end -->
			
			<!-- ��ع��� -->
			<p class="other" style="position:relative;">
				<a href="#" onclick="return false;" 
					onmouseover ="displayDiv(['liucheng_bar','liucheng_bar'],'')"
					style="display:block;height:30px;">��ع���</a>
			</p>
			<!-- ��ع��� end-->
			
		</div>
		<!-- ���� end-->
		
		<!-- ��ʾ��Ϣ end-->
		<div class="prompt" id="div_help" style="display: none">
			<h3>
				<span>��ʾ��</span>
			</h3>
			<p>
				1.��������г�����ĿΪ��<font color="blue">���ʸ����,���������ʱ����</font>��������Ŀ��</br>
				2.�����Ŀ����Ļ��������Ե��<font color="blue">��ѯ��Ŀ</font>���й��ˡ�</br>
				3.�����ӵ�ж��������ݣ������ǰ��ϵͳ����ʾ��ȷ����Ҫ�Ժ���<font color="blue">���</font>������ˡ�</br>
				4.�������ѡ<font color="blue">һ��</font>��¼�����<font color="blue">���</font>��ϵͳ��չ�ָ�ѧ����ϸ�����ҳ�档</br>
				5.�������ѡ<font color="blue">����</font>��¼�����<font color="blue">���</font>��ϵͳ����չ����ϸҳ�棬��ִ��������˲�����</br>
				6.�������鿴ĳѧ������ϸ������Ϣ������<font color="blue">˫��</font>������¼��
			</p>
		</div>
		<!-- ��ʾ��Ϣ end-->		
		
		<!-- ��ݷ�ʽ -->
		<div class="liucheng_xg_pj" id="div_other_gnmk" style=""
			onmouseover="displayDiv(['liucheng_bar','liucheng_bar'],'')"
			onmouseout="displayDiv(['liucheng_bar','liucheng_bar'],'none')">
		
			<div class="liucheng_bar" id="liucheng_bar" style="position:absolute;top:30px;right:0px;width:auto;background:#fff;display:none">
				<div class="con">
				
					<div class="liucheng_font floatleft">
				    	<a href="#" onclick="goOtherGnmk('pjpy_mypj.do');return false;">
				    		<img src="<%=stylePath%>/images/blue/48-1/Function79.png" />
							<p>�ҵ�����</p>
						</a>   	
					</div>
					
				    <div class="liucheng_font floatleft">
				    	<a href="#" onclick="goOtherGnmk('pjpy_pjlc_xssq.do');return false;">
				    		<img src="<%=stylePath%>/images/blue/48-1/Function18.png" />
							<p>ѧ������</p>
						</a>   	
					</div>
					
					<div class="liucheng_font floatleft">
						<a href="#" onclick="showTopWin('/xgxt/pjpyXmsb.do?method=sbfwChoose',600,550);return false;">
				    		<img src="<%=stylePath%>/images/blue/48-1/Function46.png" />
							<p>��ʦ�ϱ�</p>
						</a>
					</div>
					
					<div class="liucheng_font floatleft">
						<a href="#" onclick="goOtherGnmk('pjpy_pjlc_jgcx.do?shzt=');return false;">
				    		<img src="<%=stylePath%>/images/blue/48-1/Function12.png" />
							<p>�����ѯ</p>
						</a>
					</div>
					
				</div>
			</div>
		</div>
		<!-- ��ݷ�ʽ end-->

		<html:form action="/pjpyXmsh">
		
			<!-- ������ -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<input type="hidden" name="shxm" id="shxm" value="${shxm }"/>
			<input type="hidden" name="spgw" id="spgw" value="${spgw }"/>
			<input type="hidden" name="shjb" id="shjb" value="${shjb }"/>
			
			<input type="hidden" id="hid_xmdm" value="${hid_xmdm }"/>
			<input type="hidden" id="hid_xmmc" value="${hid_xmmc }"/>
			<input type="hidden" id="hid_ywmc" value="${hid_ywmc }"/>
			<input type="hidden" id="hid_xmxz" value="${hid_xmxz }"/>
			<input type="hidden" id="hid_xmfw" value="${hid_xmfw }"/>
			<input type="hidden" id="hid_xmlx" value="${hid_xmlx }"/>
			<input type="hidden" name="bjdm" id="bjdm" value=""/>
			<button type="button" id="forward" onclick="goXmsb()" style="display: none">��ת</button>
			<!-- ������ end-->
			
			<!-- �๦�ܲ����� -->
			<div class="toolbox">
			
				<!-- ��ť -->
				<div class="buttonbox">
					<ul>
						<li>
							<a href="#" id="btn_sh"
								onclick="clickXmsh();return false;"
								class="btn_sh">
								<bean:message key="lable.btn_sh" />
							</a>
							<a href="#"
								onclick="showSearchXmDiv();return false;"
								class="btn_cx">
								��ѯ��Ŀ
							</a>
							<logic:equal name="fwfs" value="homepage">
								<input type="hidden" name="fwfs" id="fwfs" value="${fwfs}"/>
								<li>
									<a href="#" onclick="returnHomPage('');return false;" class="btn_fh">����</a>
								</li>
							</logic:equal>
							
						</li>
					</ul>
					<button type="button" class="btn_cx" id="search_go" style="display: none" onclick="allNotEmpThenGo('/xgxt/pjpyXmsh.do?method=xmshManage');"></button>
				</div>
				<!-- ��ť end-->
				
				<!-- �������� -->
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- �������� end-->
			</div>
			<!-- �๦�ܲ����� end-->
			
			<!-- ������ʾ����ʼ -->
			<div class="main_box">
				<div class="mid_box">
					<div class="title">
						<p>
							<!-- ��ѯ�õ�����������ʾ���� -->
						</p>
					</div>
				</div>
				<!-- From���� -->
				<table align="center" width="100%">
					<tr>
						<td colspan="2">
							<h3 class="datetitle_01">
								<span>
									ע:����ѧ��(${pjxn }) ����ѧ��(${pjxqmc }) �������(${pjnd })
								</span>
							</h3>
						</td>
					</tr>
					<tr>
						<td width="20%" valign="top" style="overflow-x: auto;">
							<!-- ����� -->
							<div class="listbox" style="height:480px;overflow-x:hidden;overflow-y:auto;vertical-align: top;">	
								<div class="titlelist" style="height: 477px;">
									<ul id="left_ul">
										<logic:notEmpty name="xhxmList">
											<logic:iterate id="xmnr" name="xhxmList" indexId="index">
												<logic:equal name="xmnr" property="xmdm" value="${shxm}">
													<li id="li_${index}" class="Child" style="background:#dce8f8">
														<a href="#" name="left_a" id="left_a_${index}" 
															onclick="setLiClick('${index}');searchByUserGw();return false;">
															<span class="ico"></span>${xmnr.xmmc}
														</a>
														<input type="hidden" id="xmdm_${index }" value="${xmnr.xmdm}"/>
													</li>
												</logic:equal>
												<logic:notEqual name="xmnr" property="xmdm" value="${shxm}">
													<li id="li_${index}" class="Child">
														<a href="#" name="left_a" id="left_a_${index}" 
															onclick="setLiClick('${index}');searchByUserGw();return false;">
															<span class="ico"></span>${xmnr.xmmc}
														</a>
														<input type="hidden" id="xmdm_${index }" value="${xmnr.xmdm}"/>
													</li>
												</logic:notEqual>
											</logic:iterate>
										</logic:notEmpty>
									</ul>
								</div>
							</div>
							<!-- ����� end-->
						</td>
						<td width="80%" valign="top" style="position: relative;">
							<div id="dy_area">
								<span class="formbox">
									<table class="dateline" width="100%">
										<!-- ���� -->
								    	<thead>
											<tr>
												<td width="5px">
													<input type="checkbox" id="selall" name="selall" onclick="selAll()" />
												</td>
												<logic:iterate id="tit" name="topTr" offset="1" length="7">
													<td>
														<bean:write name="tit" property="cn" />
													</td>
												</logic:iterate>
								      		</tr>
										</thead>
										<!-- ���� end-->
										
										<!-- ���� -->
										<logic:equal name="hadRs" value="yes">
								    	<tbody>
									    	<logic:iterate name="rsArrList" id="rs" indexId="index">
									    		<tr onclick="rowOnClick(this);" ondblclick="showShInfo('view');">
									    			<!-- �ļ����� -->
									    			<logic:iterate id="v" name="rs" offset="0" length="1">
														<td align="center" width="5px">
															<logic:iterate id="sjzt" name="rs" offset="8" length="1">
																<!-- ��һ������δ��� -->
																<logic:equal name="sjzt" value="δ���">
																	<input type="checkbox" id="pk_${index }"
																		name="primarykey_checkVal"  
																		value="${v }"/>
																</logic:equal>
																<!-- ��һ�������˻� -->
																<logic:equal name="sjzt" value="�˻�">
																	<input type="checkbox" id="pk_${index }"
																		name="primarykey_checkVal"  
																		value="${v }"/>
																</logic:equal>
																<!-- ��һ����ͨ�� -->
																<logic:equal name="sjzt" value="ͨ��">
																	<input type="checkbox" id="pk_${index }"
																		name="primarykey_checkVal"  
																		value="${v }" disabled="disabled"/>
																</logic:equal>
																<!-- ��һ����ͨ�� -->
																<logic:equal name="sjzt" value="��ͨ��">
																	<input type="checkbox" id="pk_${index }"
																		name="primarykey_checkVal"  
																		value="${v }" disabled="disabled"/>
																</logic:equal>
																<!-- ��һ���������� -->
																<logic:equal name="sjzt" value="������">
																	<input type="checkbox" id="pk_${index }"
																		name="primarykey_checkVal"  
																		value="${v }" disabled="disabled"/>
																</logic:equal>
															</logic:iterate>
															
														</td>
													</logic:iterate>
									    			<logic:iterate id="v" name="rs" offset="1" length="3">
														<td align="left" nowrap="nowrap">
															${v }
														</td>
													</logic:iterate>
															
													<!-- ѧԺ -->
													<logic:iterate id="v" name="rs" offset="4" length="1">
														<td align="left" nowrap="nowrap">
															${v }
														</td>
													</logic:iterate>
													<!-- רҵ -->
													<logic:iterate id="v" name="rs" offset="5" length="1">
														<td align="left" nowrap="nowrap">
															${v }
														</td>
													</logic:iterate>
													<!-- �༶ -->
													<logic:iterate id="v" name="rs" offset="6" length="1">
														<td align="left" nowrap="nowrap">
															${v }
														</td>
													</logic:iterate>
												
													<!-- ��˽�� -->
													<logic:iterate id="v" name="rs" offset="7" length="1">
														<td align="left" nowrap="nowrap">
															<logic:equal name="v" value="δ���">
																<p><img src="<%=stylePath%>images/ico_dsh.gif" width="52" height="18" /></p>
															</logic:equal>
															<logic:equal name="v" value="ͨ��">
																<p><img src="<%=stylePath%>images/ico_shtg.gif" width="52" height="18" /></p>
															</logic:equal>
															<logic:equal name="v" value="��ͨ��">
																<p><img src="<%=stylePath%>images/ico_shbtg.gif" width="52" height="18" /></p>
															</logic:equal>
															<logic:equal name="v" value="�˻�">
																<p><img src="<%=stylePath%>images/ico_shth.gif" width="52" height="18" /></p>
															</logic:equal>
															<logic:equal name="v" value="������">
																<p><img src="<%=stylePath%>images/ico_shxcs.gif" width="52" height="18" /></p>
															</logic:equal>
														</td>
													</logic:iterate>
												</tr>
									    	</logic:iterate>
										</tbody>
										</logic:equal>
										<!-- ������ -->
										<%@ include file="/comm/noRows.jsp"%>
										<!-- ������ end-->
									</table>
								</span>
							</div>
						</td>
					</tr>
				</table>
				<!--��ҳ��ʾ-->
				<jsp:include flush="true" page="/sjcz/turnpage.jsp?form=pjpyXmshForm"></jsp:include>
				<!--��ҳ��ʾ-->
			</div>
			<!-- ������ʾ����ʼ end-->
			
			<!-- ��Ŀ��ѯDiv-->
			<div id="searchXmDiv" style="display: none;">
				<div class="tab">
					<table class="formlist">
						<thead>
							<tr>
								<th colspan="4">
									<span>��¼����Ӧ�����Ա���˳�����Ҫ����Ŀ</span>
								</th>
							</tr>
						</thead>
						<tbody>						
							<tr>
								<th width="20%">
									��Ŀ����
								</th>
								<td width="30%">
									<html:text property="xmdm" styleId="xmdm" style="width:100px"/>
								</td>
								<th width="20%">
									��Ŀ����
								</th>
								<td width="30%">
									<html:text property="xmmc" styleId="xmmc" style="width:100px"/>
								</td>
							</tr>
							<tr>
								<th>
									Ӣ������
								</th>
								<td>
									<html:text property="ywmc" styleId="ywmc" style="width:100px"/>
								</td>
								<th>
									��Ŀ��Χ
								</th>
								<td>
									<html:select property="xmfw" styleId="select_xmfw" style="width:100px" onchange="">
										<html:option value=""></html:option>
										<html:options collection="xmfwList" property="dm" labelProperty="mc" />
									</html:select>
								</td>
							</tr>
							<tr>
								<th>
									��Ŀ����
								</th>
								<td>
									<html:select property="xmlx" styleId="select_xmlx" style="width:100px" onchange="">
										<html:option value=""></html:option>
										<html:options collection="xmlxList" property="en" labelProperty="cn" />
									</html:select>
								</td>
								<th>
									��Ŀ����
								</th>
								<td>
									<html:select property="xmxz" styleId="select_xmxz" style="width:100px" onchange="">
										<html:option value=""></html:option>
										<html:options collection="xmxzList" property="dm" labelProperty="mc" />
									</html:select>
								</td>
							</tr>						
						</tbody>
						<tfoot>
						<tr>
							<td colspan='4'>
								<div class="btn">
									<!-- ȷ�� -->
									<button type="button" onclick="setNewXm();hiddenMessage(true,true);">
										<bean:message key="lable.btn_qd_space" />
									</button>
									<!-- �ر� -->
									<button type="button" onclick="hiddenMessage(true,true);return false;">
										<bean:message key="lable.btn_gb_space" />
									</button>
								</div>
							</td>
						</tr>
						</tfoot>
					</table>
				</div>
			</div>
			<!-- ��Ŀ��ѯDiv end-->
			
			<!-- ��˸�λѡ��Div-->
			<div id="gwxzDiv" style="display: none;">
				<div class="tab">
					<table class="formlist">
						<thead>
							<tr>
								<th colspan="2">
									<span>��ӵ�ж༶��˸�λ����ѡ�񱾴���Ҫ��˵ļ���</span>
								</th>
							</tr>
						</thead>
						<tbody>						
							<tr>
								<th width="20%">
									��λѡ��
								</th>
								<td>
									<div id="div_gwxz">
										
									</div>
								</td>
							</tr>					
						</tbody>
						<tfoot>
						<tr>
							<td colspan='2'>
								<div class="btn">
									<!-- ȷ�� -->
									<button type="button" onclick="searchRsList();hiddenMessage(true,true);">
										<bean:message key="lable.btn_qd_space" />
									</button>
									<!-- �ر� -->
									<button type="button" onclick="hiddenMessage(true,true);return false;">
										<bean:message key="lable.btn_gb_space" />
									</button>
								</div>
							</td>
						</tr>
						</tfoot>
					</table>
				</div>
			</div>
			<!-- ��Ŀ��ѯDiv end-->
			
			<!-- ���״̬ѡ��Div-->
			<div id="shztDiv" style="display: none;">
				<div class="tab">
					<table class="formlist">
						<thead>
							<tr>
								<th colspan="2">
									<span>��ȷ������ѡ�����¼�����״̬</span>
								</th>
							</tr>
						</thead>
						<tbody>						
							<tr>
								<th width="20%">
									״̬ѡ��
								</th>
								<td>
									<input type="radio" name="rad_shzt" id="shzt_tg" value="ͨ��" onclick="$('shzt').value = this.value" checked="checked"/>ͨ��
									</br>
									<input type="radio" name="rad_shzt" id="shzt_btg" value="��ͨ��" onclick="$('shzt').value = this.value"/>��ͨ��
									</br>
									<!-- ��һ��û���˻� -->
									<logic:notEqual name="shjb" value="1">
									<input type="radio" name="rad_shzt" id="shzt_th" value="�˻�" onclick="$('shzt').value = this.value"/>�˻�
									</br>
									</logic:notEqual>
									<input type="hidden" name="shzt"id="shzt" value="ͨ��"/>
								</td>
							</tr>					
						</tbody>
						<tfoot>
						<tr>
							<td colspan='2'>
								<div class="btn">
									<!-- ȷ�� -->
									<button type="button" onclick="saveShzt();hiddenMessage(true,true);">
										<bean:message key="lable.btn_qd_space" />
									</button>
									<!-- �ر� -->
									<button type="button" onclick="hiddenMessage(true,true);return false;">
										<bean:message key="lable.btn_gb_space" />
									</button>
								</div>
							</td>
						</tr>
						</tfoot>
					</table>
				</div>
			</div>
			<!-- ���״̬Div end-->
			
			<!-- ��ʾ��Ϣ -->
			<%@ include file="/comm/other/tsxx.jsp"%>
		</html:form>
	</body>
</html>