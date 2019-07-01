<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- 作者：伟大的骆 -->
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<link rel="stylesheet" type="text/css" href="js/jquery/themes/default/easyui.css" />
	    <link rel="stylesheet" type="text/css" href="js/jquery/themes/icon.css" />
	    <script language="javascript" src="js/check.js"></script>
	    <script language="javascript" src="js/pjpy/szgyyq/pjpy_szgyyq.js"></script>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script language="javascript" defer="defer">
		//前往我的评奖
		function goMypj(){
			var url = "pjpy_szgyyq_mypj.do";
			
			showWaitingDiv("30000");
			
			refreshForm(url);
		}
		
		//初始化
		function onShow(){
		
			var czxm = $("shxm").value;

			if(czxm == ""){
				var id = "left_a_0";
				if($(id)){
					$(id).click();
				}
			}else{
				searchRs();
			}
		}
		
		//查询结果集
		function searchRs(){
			var url = "szgyyq_mypj_tea.do?method=getFsshInfoList";

			$("divWaiting").style.display="none";
			$("divDisable").style.display="none";
			
			//项目代码
			var xmdm = $("shxm").value;
			//查询类型
			var mhcx_lx = "";
			if($("mhcx_lx")){
				mhcx_lx = $("mhcx_lx").value;
			}
			
			//模糊查询
			var input_mhcx = "";
			
			if($("input_mhcx")){
				input_mhcx = $("input_mhcx").value;
				if(input_mhcx == ""){
					input_mhcx = " ";
				}else{
					input_mhcx=escape(input_mhcx);
				}
			}

			//学年
			var xn = new Array();
			var xn_num = jQuery("a[name=a_name_xn]").length;
		
			var num = 0;
			
			if(xn_num != 0){
				for(var i=0;i<xn_num;i++){
					var id = jQuery("a[name=a_name_xn]")[i].id;
					xn[num] = id.replace("a_id_","");
					num++;
				}
			}else{
				xn = [" "];
			}
			
			//学期
			var xq = new Array();
			var xq_num = jQuery("a[name=a_name_xq]").length;
		
			num = 0;
			
			if(xq_num != 0){
				for(var i=0;i<xq_num;i++){
					var id = jQuery("a[name=a_name_xq]")[i].id;
					xq[num] = id.replace("a_id_","");
					num++;
				}
			}else{
				xq = [" "];
			}
			
			var otherValue = [
				mhcx_lx,
				input_mhcx,
				xmdm,
				xn.join("!!##!!"),
				xq.join("!!##!!")	
			];
			
			if(checkSearch()){
				searchRsByAjax(url,otherValue);
				if($("is_default")){
					$("is_default").value="no";
				}
			}
		}
		
		//检验可否查询
		function checkSearch(){
		
			var flag = true;
			var is_default = $("is_default").value;
			
			if(is_default!=""){
				var xn_num =  jQuery("a[name=a_name_xn]").length;
				var xq_num =  jQuery("a[name=a_name_xq]").length;

				if(xn_num != 1 && flag){
					alertError("学年条件不可为空，且只能查询当前学年！");
					flag = false;
				}
				
				if( xq_num != 1 && flag){
					alertError("学期条件不可为空，且只能查询当前学期！");
					flag = false;
				}
				
				if(flag){
					var xnid = jQuery("a[name=a_name_xn]")[0].id;
					var xqid = jQuery("a[name=a_name_xq]")[0].id;
					
					var xn_value =xnid.replace("a_id_","");
					var xq_value =xqid.replace("a_id_","");
					var dqxn=jQuery("#xn").val();
					var dqxq=jQuery("#xq").val();
					
					if(xn_value!=dqxn){
						alertError("仅可选择当前学年！");
						flag = false;
					}
					
					if(xq_value!=dqxq && flag){
						alertError("仅可选择当前学期！");
						flag = false;
					}
				}
				
			}
			return flag;
		}
		
		//显示审核详细
		function showFsshDetail(){
		
			var num = jQuery("input[name=checkVal]:checked").length;
			
			if(num == 1){
				var xh = jQuery("input[name=checkVal]:checked")[0].value;
				var xmdm = $("shxm").value;
				
				var url = "/xgxt/szgyyq_mypj_tea.do?method=fsshDetail";
				url+="&xh="+xh;
				url+="&xmdm="+xmdm;

				showTopWin(url,'800','600');	
			}else if(num >1){
				var xmdm = $("shxm").value;
				if($("rad_th") && $("span_th") && xmdm=="szyc_5sb"){
					$("rad_th").style.display="none";
					$("span_th").style.display="none";
				}else if($("rad_th") && $("span_th")){
					$("rad_th").style.display="";
					$("span_th").style.display="";
				}
				tipsWindown("系统提示","id:div_plsh","400","180","true","","true","id");
			}else if(num == 0){
				alertError("请勾选您想要审核的记录");
				return false;
			}
		}
		
		//保存审核状态
		function saveShzt(){
		
			var flag = true;
				
			//用户类型
			var yhlx = $("yhlx").value;
			//项目代码
			var xmdm = $("shxm").value;
			//审核状态
			var shzt = $("hid_shzt").value;
			
			var url="szgyyq_mypj_tea.do?method=savePlShzt";
			
			var i = 0;
			//ID
			var xh = new Array(); 						  
			jQuery("input[name=checkVal]:checked").each(
			function(){
				xh[i] = jQuery(this).val();
				i++;
			});
			
			//参数
		 	var parameter = {
		 		"xmdm":xmdm,
		 		"yhlx":yhlx,
		 		"shzt":shzt,
				"xh":xh.join("!!@@!!")
			};
			
			$("divWaiting").style.display="";
			$("divDisable").style.display="";

			jQuery.post(url,parameter,function(result){doSuccess(result)});
		}
		
		//执行成功
		function doSuccess(result){
			$("divWaiting").style.display="none";
			$("divDisable").style.display="none";
			alertInfo(result,function(tag){				
				if(tag == "ok"){
					closeWindown();
					$("search_go").click();}});
		}
		
		//显示学生投诉Div
		function showXstsDiv(tsr,btsr,tsnr,tssj){
			$("tsr").value = tsr;
			$("btsr").value = btsr;
			$("tsnr").value = tsnr;
			$("p_tssj").innerHTML = tssj;
			tipsWindown("系统提示","id:div_xsts","350","380","true","","true","id");
		}
		
		//保存投诉处理
		function saveTscl(){
		
			var clyj = $("clyj").value;
			
			if(clyj == ""){
				alertError("处理意见不可为空，请确认！");
				return false;
			}
			
			var url="szgyyq_mypj_tea.do?method=saveTscl";
			$("divWaiting").style.display="";
			$("divDisable").style.display="";

			var xn = $("xn").value;
			var xq = $("xq").value;
			var btsr = $("btsr").value;
			var tsr = $("tsr").value;
			var xmlx = $("shxm").value;
			
			//参数
		 	var parameter = {
		 		"xn":xn,
		 		"xq":xq,
				"btsr":btsr,
				"tsr":tsr,
				"xmlx":xmlx,
				"clyj":escape(clyj)
			};
			
			jQuery.post(url,parameter,function(result){doSuccess(result);});
		}
		
		// ------------2011.11.21 qlj-------------
		jQuery(function(){
			if(jQuery("#yhlx").val()!="bz"){
				$("div_superSh").style.display=""; 
				//$("div_advanced").style.display="none"; 
			}
		});
		</script>
	</head>
	<body onload="onShow()" >
	
		<!-- 标题 -->
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>综合素质养成课 - 我的工作 - 分数审核</a>
			</p>
			<p class="help">
				<a href="#" onclick="return false;" onmousedown ="showHelpDiv();">使用帮助</a>
			</p>
		</div>
		<!-- 标题 end-->
		
		<!-- 提示信息 end-->
		<div class="prompt" id="div_help" style="display: none">
			<h3>
				<span>提示：</span>
			</h3>
			<p>
				<span>
				1.以下所有操作都是基于<font color="blue">${xn }</font>学年，<font color="blue">${xqmc }</font>学期 展开的。</br>
				2.可通过点击<font color="blue">审核</font>按钮对一个或多个学生审核。</br>
				3.当勾选一个学生时，执行单个学生审核，当勾选多个学生时，执行批量学生审核。
				</span>
			</p>
			<a class="close" title="隐藏"  onclick="this.parentNode.style.display='none';"></a>
		</div>
		<!-- 提示信息 end-->
		
		<html:form action="/szgyyq_mypj_tea" method="post">

			<!-- 隐藏域 -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<!-- 操作项目  -->
			<input type="hidden" name="is_default" id="is_default" value=""/>
			<input type="hidden" name="czxm" id="shxm" value="${czxm }"/>
			<input type="hidden" name="xn" id="xn" value="${xn }"/>
			<input type="hidden" name="xq" id="xq" value="${xq }"/>
			<input type="hidden" name="yhlx" id="yhlx" value="${yhlx }"/>
			<input type="hidden" name="tsr" id="tsr" value=""/>
			<input type="hidden" name="btsr" id="btsr" value=""/>
			<!-- 隐藏域 end-->
			
			<!-- 多功能操作区 -->
			<div class="toolbox">
				<!-- 按钮 -->
				<div class="buttonbox">
					<ul>
						<li>
							<a href="#" onclick="goMypj();return false;" class="btn_fh">
								返回
							</a>
						</li>
						<li>
							<a href="#" onclick="showFsshDetail();return false;" class="btn_sh">
								审核
							</a>
						</li>	
					</ul>
				</div>
				<!-- 按钮 end-->
		
				<logic:equal name="yhlx" value="bz">
				<!-- 过滤条件 -->
				<!-- 模糊查询 -->
				<div class="search_advanced" >
					<div class="adv_filter">
						<table border="0" width="100%">
							<tbody>
								<tr>
									<td style="padding-left:68px;">
										查询条件：
								        <input type="text" name="searchModel.input_mhcx"
											id="input_mhcx" value="${searchTj.input_mhcx }" size="50"
											onkeypress="if(pressEnter(event)){searchRs();return false;}"
											onfocus="setTsMsg();displayMsgDiv('input_mhcx_msg');"
											onblur="hideMsgDiv('input_mhcx_msg')" />
			
										<div id="input_mhcx_msg" class="hide"
											style="left: 140px;top: 122px;">
											<div class="prompcon" style="width: 250px">
												<p id="tsxx_span"></p>
											</div>
										</div>
										
										<button type="button" class="btn_cx" id="search_go"
											onclick="searchRs();return false;">
											查 询
										</button>
			
										<button type="button" class="btn_cz" onclick="czSuperSearch()">
											重 置
										</button>
										<br>
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										<html:radio property="searchModel.mhcx_lx" value="all" onclick="$('mhcx_lx').value = this.value"/>
										全部
										<!-- 模糊循环 -->
										<html:radio property="searchModel.mhcx_lx" value="xh" onclick="$('mhcx_lx').value = this.value"/>学号
										<html:radio property="searchModel.mhcx_lx" value="xm" onclick="$('mhcx_lx').value = this.value"/>姓名
										<input type="hidden" id="mhcx_lx" value="all"/>
									</td>
								</tr>
							</tbody>
						</table>
					</div>
					
				
					<!-- 已选条件(个性化) -->
					<div class="selected-attr" id="yxtj_gxh_div" style="display: none">
						<h3>
							已选条件：
						</h3>
						<dl id="yxtj_gxh_dl">
				
						</dl>
					</div>
					<!-- 已选条件 end-->
					
					<!-- 高级查询(个性化)-->
					<div class="prop-item" id="gjcx_gxh_div" style="display: none">
						<!-- 学年 -->
						<dl>
							<dt>学年：</dt>
							<dd>
								<ul>
									<logic:iterate name="xnTjList" id="xnMap" indexId="list_num">
										<logic:notEqual name="list_num" value="0">
											<li>
												<a href="#" class=""
													onclick="clickTj('xn','${xnMap.xn }');creatGxhClickedTj('xn','学年','${xnMap.xn }','${xnMap.xn }',this);return false;" 
													id="tj_xn_${xnMap.xn }" name="tj_xn">
													${xnMap.xn }
												</a>
											</li>
										</logic:notEqual>
									</logic:iterate>	
								</ul>
							</dd>
						</dl>
						<!-- 学年 end-->
						
						<!-- 学期 -->
						<dl>
							<dt>学期：</dt>
							<dd>
								<ul>
									<logic:iterate name="xqTjList" id="xqMap" indexId="list_num">
										<li>
											<a href="#" class=""
												onclick="clickTj('xq','${xqMap.xqdm }');creatGxhClickedTj('xq','学期','${xqMap.xqdm }','${xqMap.xqmc }',this);return false;" 
												id="tj_xq_${xqMap.xqdm }" name="tj_xq">
												${xqMap.xqmc }
											</a>
										</li>
									</logic:iterate>	
								</ul>
							</dd>
						</dl>
						<!-- 学期 end-->			
					</div>
					<!-- 高级查询 end-->
				</div>

				<!-- 伸缩按钮 -->
				<div class="more--item_bottom">
					<p>
						<a href="#" class="down"
							onclick="showGxhTbody(this,'up','down','收 起','更 多');return false">
							更 多
						</a>
					</p>
				</div>
				<!-- 伸缩按钮 end-->
				
				<!-- 构建条件 -->
				<div id="searchGxhTjDiv" style="display:none">
					<logic:present name="searchTj">
						<!-- 点击条件 -->
						<logic:notEmpty name="searchTj" property="search_tj_xn">
							<!-- 学年 -->
							<logic:iterate name="searchTj" property="search_tj_xn" id="queryValue">
								<input type="hidden" title="xn" name="search_tj_xn" value="${queryValue }" />
							</logic:iterate>
							<!-- 学期 -->
							<logic:iterate name="searchTj" property="search_tj_xq" id="queryValue">
								<input type="hidden" title="xq" name="searchModel.search_tj_xq" value="${queryValue }" />
							</logic:iterate>
						</logic:notEmpty>	

						<!-- 构建已选条件 -->
						<script language="javascript" defer="defer">
							setTimeout("creatClickedTjByGxhSearch()",500);
						</script>
					</logic:present>
				</div>
				<!-- 构建条件end-->
				</logic:equal>
				
				<div id="div_superSh" style="display:none">
					<%@ include file="/comm/search/superSearchArea.jsp"%>
				</div>
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
									
								</span>
							</h3>
						</td>
					</tr>
					<tr>
						<td width="20%" valign="top" style="overflow-x: auto;">
							<!-- 侧边栏 -->
							<div class="listbox">	
								<div class="titlelist" style="height: 370px;">
									<ul id="left_ul">
										<logic:notEmpty name="cshXmList">
											<logic:iterate id="xmnr" name="cshXmList" indexId="index">
												<logic:equal name="xmnr" property="xmdm" value="${czxm}">
													<li id="li_${index}" class="Child" style="background:#dce8f8">
														<a href="#" name="left_a" id="left_a_${index}" 
															onclick="if(checkHadEdit()){setLiClick('${index}');searchRs();};return false;">
															<span class="ico"></span>${xmnr.xmmc}
														</a>
														<input type="hidden" id="xmdm_${index }" value="${xmnr.xmdm}"/>
													</li>
												</logic:equal>
												<logic:notEqual name="xmnr" property="xmdm" value="${czxm}">
													<li id="li_${index}" class="Child">
														<a href="#" name="left_a" id="left_a_${index}" 
															onclick="if(checkHadEdit()){setLiClick('${index}');searchRs();};return false;">
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
							<div id="div_rs" style="width:650px;height:400px;overflow-x:auto;overflow-y:auto;">
							</div>
						</td>
					</tr>
				</table>
				<!--分页显示-->
				<jsp:include flush="true" page="/sjcz/turnpage.jsp?form=pjpySzgyyqTeaForm"></jsp:include>
				 <script type="text/javascript">
				     $('choose').className="hide";
				 </script>
				<!--分页显示-->
			</div>
			<!-- 内容显示区 end-->
			
			<!-- 批量审核弹出层 -->
			<div id="div_plsh" style="display:none">
				<div class="open_win01">
					<table align="center" class="formlist">
						<thead>
							<tr>
								<th colspan="2">
									<span>请确定审核内容</span>
								</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<th width="30%">
									审核状态
								</th>
								<td>
									<!-- 班长 -->
									<logic:equal name="yhlx" value="bz">
										<html:radio property="shzt" value="tg" onclick="$('hid_shzt').value=this.value">通过</html:radio>
										<html:radio property="shzt" value="btg" onclick="$('hid_shzt').value=this.value">不通过</html:radio>
									</logic:equal>
									<!-- 班主任 -->
									<logic:equal name="yhlx" value="bzr">
										<html:radio property="shzt" value="tg" onclick="$('hid_shzt').value=this.value">通过</html:radio>
										<html:radio property="shzt" value="btg" onclick="$('hid_shzt').value=this.value">不通过</html:radio>
									</logic:equal>
									<!-- 学院 -->
									<logic:equal name="yhlx" value="xy">
										<html:radio property="shzt" value="tg" onclick="$('hid_shzt').value=this.value">通过</html:radio>
										<html:radio property="shzt" value="btg" onclick="$('hid_shzt').value=this.value">不通过</html:radio>
										<html:radio property="shzt" value="th" styleId="rad_th" onclick="$('hid_shzt').value=this.value"><span id="span_th">退回</span></html:radio>
									</logic:equal>
									<!-- 学校 -->
									<logic:equal name="yhlx" value="xx">
										<html:radio property="shzt" value="tg" onclick="$('hid_shzt').value=this.value">通过</html:radio>
										<html:radio property="shzt" value="btg" onclick="$('hid_shzt').value=this.value">不通过</html:radio>
										<html:radio property="shzt" value="th" onclick="$('hid_shzt').value=this.value">退回</html:radio>
									</logic:equal>
									<input type="hidden" id="hid_shzt" value="tg"/>
								</td>
							</tr>
							<tr>
								<th width="30%">
									说明
								</th>
								<td>
									如果选择通过的话，那么勾选记录的分数就<br/>会以前一级别的分数为准，建议选择单条记<br/>录进行审核，查看详细分数。								
								</td>
							</tr>
							
						</tbody>
						<tfoot>
							<tr>
								<td colspan="2">
									<div class="bz">
										
									</div>
									<div class="btn">
										<button type="button" id="btn_bc" onclick="saveShzt();return false;">
											确 定
										</button>
										<button type="button" id="btn_gb" onclick="closeWindown();return false;">
											关 闭
										</button>
									</div>
								</td>
							</tr>
						</tfoot>
					</table>
				</div>
			</div>
			
			<!-- 学生申诉弹出层 -->
			<div id="div_xsts" style="display:none">
				<div class="open_win01">
					<table align="center" class="formlist">
						<thead>
							<tr>
								<th colspan="2">
									<span>请填写处理意见</span>
								</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<th width="30%">
									投诉内容
								</th>
								<td>
									<textarea id="tsnr" rows="5" cols="" 
										style="word-break:break-all;width:100%"></textarea>
								</td>
							</tr>
							<tr id="tr_clr">
								<th width="30%">
									投诉时间
								</th>
								<td>
									<p id="p_tssj"></p>
								</td>
							</tr>
							<tr id="tr_clyj">
								<th width="30%">
									处理意见<br/>
									<font color="red"><限500字></font>
								</th>
								<td>
									<textarea id="clyj" rows="5" cols="" 
										style="word-break:break-all;width:100%"  
										onblur="chLeng(this,500)"></textarea>
								</td>
							</tr>
						</tbody>
						<tfoot>
							<tr>
								<td colspan="2">
									<div class="bz">
										
									</div>
									<div class="btn">
										<button type="button" id="btn_bc" onclick="saveTscl()">
											保 存
										</button>
										<button type="button" id="btn_gb" onclick="closeWindown();return false;">
											关 闭
										</button>
									</div>
								</td>
							</tr>
						</tfoot>
					</table>
				</div>
			</div>
			
			<!-- 提示信息 -->
			<%@ include file="/comm/other/tsxx.jsp"%>
		</html:form>
	</body>
</html>