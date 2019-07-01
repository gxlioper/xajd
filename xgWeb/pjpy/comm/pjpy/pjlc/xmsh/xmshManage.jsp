<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- 作者：伟大的骆 -->
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script type='text/javascript' src='/xgxt/dwr/interface/getCommPjpy.js'></script>
		<script language="javascript" defer="defer">
		//查询结果集
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
				alertInfo("请选择需要审核的项目！");
				return false;
			}	
		}
		
		//显示项目过滤Div
		function showSearchXmDiv(){
			viewTempDiv("过滤条件","searchXmDiv",400,200);
		}
		
		//根据过滤条件过滤出项目
		function setNewXm(){
		
			var xmdm = $("xmdm").value;// 项目代码
				$("hid_xmdm").value = xmdm;
			
			var xmmc = $("xmmc").value;// 项目名称
				$("hid_xmmc").value = xmmc;
		
			var ywmc = $("ywmc").value;// 英文名称
				$("hid_ywmc").value = ywmc;
		
			var xmxz = $("select_xmxz").value;// 项目性质
				$("hid_xmxz").value = xmxz;
		
			var xmfw = $("select_xmfw").value;// 项目范围
				$("hid_xmfw").value = xmfw;
		
			var xmlx = $("select_xmlx").value;// 项目类型
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
		
			var xmdm = $("shxm").value;// 项目代码
			var userName = $("userName").value;// 项目代码
			var shjb = $("shjb").value;// 审核级别
			
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
						viewTempDiv("岗位选择","gwxzDiv",400,200);
						$("div_gwxz").innerHTML = divHtml;
					}
				}
			});
			
			if(flag){
				searchRsList();
			}
			
			dwr.engine.setAsync(true);
		}
		
		//项目审核
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
					viewTempDiv("状态选择","shztDiv",300,200);
				}else{
					showShInfo("update");
				}
			}else{
				alertInfo("请勾选需要审核的申请记录！");
			}
			
		}
		
		//保存审核状态
		function saveShzt(){
		
			var msg = "确认修改选中记录的审核状态？\n";
				msg+= "注：\n";
				msg+= "通  过：下一级别可查看到该申请记录\n";
				msg+= "不通过：下一级别无法查看到该申请记录\n";
				msg+= "退  回：需要上一级别重新审核通过后，本级别才查看到该申请记录";
				
			if (confirm(msg)) {
				saveUpdate('/xgxt/pjpyXmsh.do?method=xmshManage&doType=sh','');
			}
		}
		
		//显示审核信息
		function showShInfo(type){
			var url = "/xgxt/pjpyXmsh.do?method=xmshDetail";
				url+="&shjb="+$("shjb").value;
				url+="&shxm="+$("shxm").value;
			showInfo(url,type,'800','600');
		}
		
		function searchRsList(){
			var url = "/xgxt/pjpyXmsh.do?method=xmshManage";
			var xmdm = "";// 项目代码
			if($("hid_xmdm")){
				xmdm =$("hid_xmdm").value;
			}
			var xmmc = "";// 项目名称
			if($("hid_xmmc")){
				xmmc =$("hid_xmmc").value;
			}
			var ywmc = "";// 英文名称
			if($("hid_ywmc")){
				ywmc =$("hid_ywmc").value;
			}
			var xmxz = "";// 项目性质
			if($("hid_xmxz")){
				xmxz =$("hid_xmxz").value;
			}
			var xmfw = "";// 项目范围
			if($("hid_xmfw")){
				xmfw =$("hid_xmfw").value;
			}
			var xmlx = "";// 项目类型
			if($("hid_xmlx")){
				xmlx =$("hid_xmlx").value;
			}
		
			url+= "&xmdm="+xmdm;
			url+= "&xmmc="+xmmc;
			url+= "&ywmc="+ywmc;
			url+= "&xmxz="+xmxz;
			url+= "&xmfw="+xmfw;
			url+= "&xmlx="+xmlx;
	
			showTips('数据查询中，请稍候......');
			
			allNotEmpThenGo(url);
		}
		
		//前往项目上报
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
		<!-- 标题 -->
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>评奖评优 - 我的评奖 - 项目审核</a>
			</p>

			<!-- 在线帮助 -->
			<p class="help">
				<a href="#" onclick="return false;" onmousedown ="showHelpDiv();">使用帮助</a>
			</p>
			<!-- 在线帮助 end -->
			
			<!-- 相关功能 -->
			<p class="other" style="position:relative;">
				<a href="#" onclick="return false;" 
					onmouseover ="displayDiv(['liucheng_bar','liucheng_bar'],'')"
					style="display:block;height:30px;">相关功能</a>
			</p>
			<!-- 相关功能 end-->
			
		</div>
		<!-- 标题 end-->
		
		<!-- 提示信息 end-->
		<div class="prompt" id="div_help" style="display: none">
			<h3>
				<span>提示：</span>
			</h3>
			<p>
				1.侧边栏中列出的项目为您<font color="blue">有资格审核,并且在审核时间内</font>的所有项目。</br>
				2.如果项目过多的话，您可以点击<font color="blue">查询项目</font>进行过滤。</br>
				3.如果您拥有多重审核身份，在审核前，系统会提示您确定需要以何种<font color="blue">身份</font>进行审核。</br>
				4.如果您勾选<font color="blue">一条</font>记录，点击<font color="blue">审核</font>，系统将展现该学生详细的审核页面。</br>
				5.如果您勾选<font color="blue">多条</font>记录，点击<font color="blue">审核</font>，系统将不展现详细页面，而执行批量审核操作。</br>
				6.如果您想查看某学生的详细申请信息，可以<font color="blue">双击</font>该条记录。
			</p>
		</div>
		<!-- 提示信息 end-->		
		
		<!-- 快捷方式 -->
		<div class="liucheng_xg_pj" id="div_other_gnmk" style=""
			onmouseover="displayDiv(['liucheng_bar','liucheng_bar'],'')"
			onmouseout="displayDiv(['liucheng_bar','liucheng_bar'],'none')">
		
			<div class="liucheng_bar" id="liucheng_bar" style="position:absolute;top:30px;right:0px;width:auto;background:#fff;display:none">
				<div class="con">
				
					<div class="liucheng_font floatleft">
				    	<a href="#" onclick="goOtherGnmk('pjpy_mypj.do');return false;">
				    		<img src="<%=stylePath%>/images/blue/48-1/Function79.png" />
							<p>我的评奖</p>
						</a>   	
					</div>
					
				    <div class="liucheng_font floatleft">
				    	<a href="#" onclick="goOtherGnmk('pjpy_pjlc_xssq.do');return false;">
				    		<img src="<%=stylePath%>/images/blue/48-1/Function18.png" />
							<p>学生申请</p>
						</a>   	
					</div>
					
					<div class="liucheng_font floatleft">
						<a href="#" onclick="showTopWin('/xgxt/pjpyXmsb.do?method=sbfwChoose',600,550);return false;">
				    		<img src="<%=stylePath%>/images/blue/48-1/Function46.png" />
							<p>老师上报</p>
						</a>
					</div>
					
					<div class="liucheng_font floatleft">
						<a href="#" onclick="goOtherGnmk('pjpy_pjlc_jgcx.do?shzt=');return false;">
				    		<img src="<%=stylePath%>/images/blue/48-1/Function12.png" />
							<p>结果查询</p>
						</a>
					</div>
					
				</div>
			</div>
		</div>
		<!-- 快捷方式 end-->

		<html:form action="/pjpyXmsh">
		
			<!-- 隐藏域 -->
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
			<button type="button" id="forward" onclick="goXmsb()" style="display: none">跳转</button>
			<!-- 隐藏域 end-->
			
			<!-- 多功能操作区 -->
			<div class="toolbox">
			
				<!-- 按钮 -->
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
								查询项目
							</a>
							<logic:equal name="fwfs" value="homepage">
								<input type="hidden" name="fwfs" id="fwfs" value="${fwfs}"/>
								<li>
									<a href="#" onclick="returnHomPage('');return false;" class="btn_fh">返回</a>
								</li>
							</logic:equal>
							
						</li>
					</ul>
					<button type="button" class="btn_cx" id="search_go" style="display: none" onclick="allNotEmpThenGo('/xgxt/pjpyXmsh.do?method=xmshManage');"></button>
				</div>
				<!-- 按钮 end-->
				
				<!-- 过滤条件 -->
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- 过滤条件 end-->
			</div>
			<!-- 多功能操作区 end-->
			
			<!-- 内容显示区开始 -->
			<div class="main_box">
				<div class="mid_box">
					<div class="title">
						<p>
							<!-- 查询得到的数据量显示区域 -->
						</p>
					</div>
				</div>
				<!-- From内容 -->
				<table align="center" width="100%">
					<tr>
						<td colspan="2">
							<h3 class="datetitle_01">
								<span>
									注:评奖学年(${pjxn }) 评奖学期(${pjxqmc }) 评奖年度(${pjnd })
								</span>
							</h3>
						</td>
					</tr>
					<tr>
						<td width="20%" valign="top" style="overflow-x: auto;">
							<!-- 侧边栏 -->
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
							<!-- 侧边栏 end-->
						</td>
						<td width="80%" valign="top" style="position: relative;">
							<div id="dy_area">
								<span class="formbox">
									<table class="dateline" width="100%">
										<!-- 标题 -->
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
										<!-- 标题 end-->
										
										<!-- 内容 -->
										<logic:equal name="hadRs" value="yes">
								    	<tbody>
									    	<logic:iterate name="rsArrList" id="rs" indexId="index">
									    		<tr onclick="rowOnClick(this);" ondblclick="showShInfo('view');">
									    			<!-- 文件内容 -->
									    			<logic:iterate id="v" name="rs" offset="0" length="1">
														<td align="center" width="5px">
															<logic:iterate id="sjzt" name="rs" offset="8" length="1">
																<!-- 下一级别尚未审核 -->
																<logic:equal name="sjzt" value="未审核">
																	<input type="checkbox" id="pk_${index }"
																		name="primarykey_checkVal"  
																		value="${v }"/>
																</logic:equal>
																<!-- 下一级别已退回 -->
																<logic:equal name="sjzt" value="退回">
																	<input type="checkbox" id="pk_${index }"
																		name="primarykey_checkVal"  
																		value="${v }"/>
																</logic:equal>
																<!-- 下一级别通过 -->
																<logic:equal name="sjzt" value="通过">
																	<input type="checkbox" id="pk_${index }"
																		name="primarykey_checkVal"  
																		value="${v }" disabled="disabled"/>
																</logic:equal>
																<!-- 下一级别不通过 -->
																<logic:equal name="sjzt" value="不通过">
																	<input type="checkbox" id="pk_${index }"
																		name="primarykey_checkVal"  
																		value="${v }" disabled="disabled"/>
																</logic:equal>
																<!-- 下一级别需重申 -->
																<logic:equal name="sjzt" value="需重审">
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
															
													<!-- 学院 -->
													<logic:iterate id="v" name="rs" offset="4" length="1">
														<td align="left" nowrap="nowrap">
															${v }
														</td>
													</logic:iterate>
													<!-- 专业 -->
													<logic:iterate id="v" name="rs" offset="5" length="1">
														<td align="left" nowrap="nowrap">
															${v }
														</td>
													</logic:iterate>
													<!-- 班级 -->
													<logic:iterate id="v" name="rs" offset="6" length="1">
														<td align="left" nowrap="nowrap">
															${v }
														</td>
													</logic:iterate>
												
													<!-- 审核结果 -->
													<logic:iterate id="v" name="rs" offset="7" length="1">
														<td align="left" nowrap="nowrap">
															<logic:equal name="v" value="未审核">
																<p><img src="<%=stylePath%>images/ico_dsh.gif" width="52" height="18" /></p>
															</logic:equal>
															<logic:equal name="v" value="通过">
																<p><img src="<%=stylePath%>images/ico_shtg.gif" width="52" height="18" /></p>
															</logic:equal>
															<logic:equal name="v" value="不通过">
																<p><img src="<%=stylePath%>images/ico_shbtg.gif" width="52" height="18" /></p>
															</logic:equal>
															<logic:equal name="v" value="退回">
																<p><img src="<%=stylePath%>images/ico_shth.gif" width="52" height="18" /></p>
															</logic:equal>
															<logic:equal name="v" value="需重审">
																<p><img src="<%=stylePath%>images/ico_shxcs.gif" width="52" height="18" /></p>
															</logic:equal>
														</td>
													</logic:iterate>
												</tr>
									    	</logic:iterate>
										</tbody>
										</logic:equal>
										<!-- 补空行 -->
										<%@ include file="/comm/noRows.jsp"%>
										<!-- 补空行 end-->
									</table>
								</span>
							</div>
						</td>
					</tr>
				</table>
				<!--分页显示-->
				<jsp:include flush="true" page="/sjcz/turnpage.jsp?form=pjpyXmshForm"></jsp:include>
				<!--分页显示-->
			</div>
			<!-- 内容显示区开始 end-->
			
			<!-- 项目查询Div-->
			<div id="searchXmDiv" style="display: none;">
				<div class="tab">
					<table class="formlist">
						<thead>
							<tr>
								<th colspan="4">
									<span>请录入相应条件以便过滤出你想要的项目</span>
								</th>
							</tr>
						</thead>
						<tbody>						
							<tr>
								<th width="20%">
									项目代码
								</th>
								<td width="30%">
									<html:text property="xmdm" styleId="xmdm" style="width:100px"/>
								</td>
								<th width="20%">
									项目名称
								</th>
								<td width="30%">
									<html:text property="xmmc" styleId="xmmc" style="width:100px"/>
								</td>
							</tr>
							<tr>
								<th>
									英文名称
								</th>
								<td>
									<html:text property="ywmc" styleId="ywmc" style="width:100px"/>
								</td>
								<th>
									项目范围
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
									项目类型
								</th>
								<td>
									<html:select property="xmlx" styleId="select_xmlx" style="width:100px" onchange="">
										<html:option value=""></html:option>
										<html:options collection="xmlxList" property="en" labelProperty="cn" />
									</html:select>
								</td>
								<th>
									项目性质
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
									<!-- 确定 -->
									<button type="button" onclick="setNewXm();hiddenMessage(true,true);">
										<bean:message key="lable.btn_qd_space" />
									</button>
									<!-- 关闭 -->
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
			<!-- 项目查询Div end-->
			
			<!-- 审核岗位选择Div-->
			<div id="gwxzDiv" style="display: none;">
				<div class="tab">
					<table class="formlist">
						<thead>
							<tr>
								<th colspan="2">
									<span>你拥有多级审核岗位，请选择本次需要审核的级别</span>
								</th>
							</tr>
						</thead>
						<tbody>						
							<tr>
								<th width="20%">
									岗位选择
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
									<!-- 确定 -->
									<button type="button" onclick="searchRsList();hiddenMessage(true,true);">
										<bean:message key="lable.btn_qd_space" />
									</button>
									<!-- 关闭 -->
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
			<!-- 项目查询Div end-->
			
			<!-- 审核状态选择Div-->
			<div id="shztDiv" style="display: none;">
				<div class="tab">
					<table class="formlist">
						<thead>
							<tr>
								<th colspan="2">
									<span>请确认所勾选申请记录的审核状态</span>
								</th>
							</tr>
						</thead>
						<tbody>						
							<tr>
								<th width="20%">
									状态选择
								</th>
								<td>
									<input type="radio" name="rad_shzt" id="shzt_tg" value="通过" onclick="$('shzt').value = this.value" checked="checked"/>通过
									</br>
									<input type="radio" name="rad_shzt" id="shzt_btg" value="不通过" onclick="$('shzt').value = this.value"/>不通过
									</br>
									<!-- 第一级没有退回 -->
									<logic:notEqual name="shjb" value="1">
									<input type="radio" name="rad_shzt" id="shzt_th" value="退回" onclick="$('shzt').value = this.value"/>退回
									</br>
									</logic:notEqual>
									<input type="hidden" name="shzt"id="shzt" value="通过"/>
								</td>
							</tr>					
						</tbody>
						<tfoot>
						<tr>
							<td colspan='2'>
								<div class="btn">
									<!-- 确定 -->
									<button type="button" onclick="saveShzt();hiddenMessage(true,true);">
										<bean:message key="lable.btn_qd_space" />
									</button>
									<!-- 关闭 -->
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
			<!-- 审核状态Div end-->
			
			<!-- 提示信息 -->
			<%@ include file="/comm/other/tsxx.jsp"%>
		</html:form>
	</body>
</html>