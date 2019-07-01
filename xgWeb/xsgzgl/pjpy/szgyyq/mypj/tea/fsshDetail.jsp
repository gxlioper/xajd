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
		
		//刷新页面
		function reflashShxxDetail(){
			
			var xh = $("xh").value;
			var xmdm = $("xmdm").value;

			var url = "szgyyq_mypj_tea.do?method=fsshDetail";
				url+="&xh="+xh;
				url+="&xmdm="+xmdm;
				
			refreshForm(url)
		}
		
		//保存审核分
		function saveShf(){
		
			//用户类型
			var yhlx = $("yhlx").value;
			//项目代码
			var xmdm = $("xmdm").value;
			
			var flag = true;
			
			if(yhlx == "bz" || yhlx == "bzr"){//班主任 或 班长
			
				var num = jQuery("input[name=bzrshf]").length;
				
				for(var i=0;i<num;i++){
					var obj = jQuery("input[name=bzrshf]")[i];
					if(obj.value == ""){
						alertError("第"+(i+1)+"行班主任审核分为空，请确认");
						flag = false;
					}
				}
				
				var url="szgyyq_mypj_tea.do?method=saveShf";
				
				//ID
				var i = 0; 
				var id = new Array();   				  
				jQuery("input[name=checkVal][disabled!=true]").each(function(){if(!this.disabled){id[i] = jQuery(this).val();i++;}});
				
				//班主任审核分
				i = 0; 
				var bzrshf = new Array();   				  
				jQuery("input[name=bzrshf]").each(function(){bzrshf[i] = jQuery(this).val();i++;});
				
				//参数
			 	var parameter = {
			 		"xmdm":xmdm,
			 		"yhlx":yhlx,
					"id":id.join("!!@@!!"),
					"bzrshf":bzrshf.join("!!@@!!")
				};
				
				$("divWaiting").style.display="";
				$("divDisable").style.display="";
				
				jQuery.post(url,parameter,function(result){saveSuccess(result)});
			}else if(yhlx == "xy"){//学院
				var num = jQuery("input[name=xyshf]").length;
				
				for(var i=0;i<num;i++){
					var obj = jQuery("input[name=xyshf]")[i];
					if(obj.value == ""){
						alertError("第"+(i+1)+"行"+jQuery("#xbmc").val()+"审核分为空，请确认");
						flag = false;
					}
				}
				
				var url="szgyyq_mypj_tea.do?method=saveShf";
				
				//ID
				var i = 0; 
				var id = new Array();   			  
				jQuery("input[name=checkVal][disabled!=true]").each(function(){id[i] = jQuery(this).val();i++;});
				
				//学院审核分
				i = 0; 
				var xyshf = new Array();   				  
				jQuery("input[name=xyshf]").each(function(){xyshf[i] = jQuery(this).val();i++;});
				
				//参数
			 	var parameter = {
			 		"xmdm":xmdm,
			 		"yhlx":yhlx,
					"id":id.join("!!@@!!"),
					"xyshf":xyshf.join("!!@@!!")
				};
				
				$("divWaiting").style.display="";
				$("divDisable").style.display="";
				
				jQuery.post(url,parameter,function(result){saveSuccess(result)});
				
			}else if(yhlx == "xx"){//学校
				var num = jQuery("input[name=xxshf]").length;
				
				for(var i=0;i<num;i++){
					var obj = jQuery("input[name=xxshf]")[i];
					if(obj.value == ""){
						alertError("第"+(i+1)+"行学校审核分为空，请确认");
						flag = false;
					}
				}
				
				var url="szgyyq_mypj_tea.do?method=saveShf";
				
				//ID
				var i = 0; 
				var id = new Array();   				  
				jQuery("input[name=checkVal][disabled!=true]").each(function(){id[i] = jQuery(this).val();i++;});
				
				//学校审核分
				i = 0; 
				var xxshf = new Array();   				  
				jQuery("input[name=xxshf]").each(function(){xxshf[i] = jQuery(this).val();i++;});
				
				//参数
			 	var parameter = {
			 		"xmdm":xmdm,
			 		"yhlx":yhlx,
					"id":id.join("!!@@!!"),
					"xxshf":xxshf.join("!!@@!!")
				};
				
				$("divWaiting").style.display="";
				$("divDisable").style.display="";
				
				jQuery.post(url,parameter,function(result){saveSuccess(result)});
			}
		}
		
		//保存成功
		function saveSuccess(result){
		
			$("divWaiting").style.display="none";
			$("divDisable").style.display="none";
			
			alertInfo(result,function(tag){
				if(tag == "ok"){
				
				}
			});
		}
		
		//保存审核状态
		function saveShzt(shzt){
		
			var flag = true;
			var num = jQuery("input[name=checkVal]:checked").length;
			
			if(num == 0){
				alertError("请勾选希望审核的记录");
				flag = false;
			}
			
			if(flag){
				
				//用户类型
				var yhlx = $("yhlx").value;
				//项目代码
				var xmdm = $("xmdm").value;

			
				if(yhlx == "bz" || yhlx == "bzr"){//班主任或班长
				
					var num = jQuery("input[name=bzrshf]").length;
					
					for(var i=0;i<num;i++){
						var obj = jQuery("input[name=bzrshf]")[i];
						if(obj.value == ""){
							alertError("第"+(i+1)+"行班主任审核分为空，请确认");
							flag = false;
						}
					}
					
					var url="szgyyq_mypj_tea.do?method=saveShzt";
					
					var i = 0;
					//ID
					var id = new Array();
					//班主任审核分
					var bzrshf = new Array();   						  
					jQuery("input[name=checkVal]:checked").each(
					function(){
						id[i] = jQuery(this).val();
						bzrshf[i] = $("bzrshf_"+jQuery(this).val()).value;
						i++;
					});
					
					//参数
				 	var parameter = {
				 		"xmdm":xmdm,
				 		"yhlx":yhlx,
				 		"shzt":shzt,
						"id":id.join("!!@@!!"),
						"bzrshf":bzrshf.join("!!@@!!")
					};
					
					$("divWaiting").style.display="";
					$("divDisable").style.display="";
					
					jQuery.post(url,parameter,function(result){shSuccess(result)});
					
				}else if(yhlx == "xy"){//学院
				
					var num = jQuery("input[name=xyshf]").length;
					
					for(var i=0;i<num;i++){
						var obj = jQuery("input[name=xyshf]")[i];
						if(obj.value == ""){
							alertError("第"+(i+1)+"行学院审核分为空，请确认");
							flag = false;
						}
					}
					
					var url="szgyyq_mypj_tea.do?method=saveShzt";
					
					var i = 0;
					//ID
					var id = new Array();
					//学院审核分
					var xyshf = new Array();   						  
					jQuery("input[name=checkVal]:checked").each(
					function(){
						id[i] = jQuery(this).val();
						xyshf[i] = $("xyshf_"+jQuery(this).val()).value;
						i++;
					});
					
					//参数
				 	var parameter = {
				 		"xmdm":xmdm,
				 		"yhlx":yhlx,
				 		"shzt":shzt,
						"id":id.join("!!@@!!"),
						"xyshf":xyshf.join("!!@@!!")
					};
					
					$("divWaiting").style.display="";
					$("divDisable").style.display="";
					
					jQuery.post(url,parameter,function(result){shSuccess(result)});
					
				}else if(yhlx == "xx"){//学校
				
					var num = jQuery("input[name=xxshf]").length;
					
					for(var i=0;i<num;i++){
						var obj = jQuery("input[name=xxshf]")[i];
						if(obj.value == ""){
							alertError("第"+(i+1)+"行学校审核分为空，请确认");
							flag = false;
						}
					}
					
					var url="szgyyq_mypj_tea.do?method=saveShzt";
					
					var i = 0;
					//ID
					var id = new Array();
					//学校审核分
					var xxshf = new Array();   						  
					jQuery("input[name=checkVal]:checked").each(
					function(){
						id[i] = jQuery(this).val();
						xxshf[i] = $("xxshf_"+jQuery(this).val()).value;
						i++;
					});
					
					//参数
				 	var parameter = {
				 		"xmdm":xmdm,
				 		"yhlx":yhlx,
				 		"shzt":shzt,
						"id":id.join("!!@@!!"),
						"xxshf":xxshf.join("!!@@!!")
					};
					
					$("divWaiting").style.display="";
					$("divDisable").style.display="";
					
					jQuery.post(url,parameter,function(result){shSuccess(result)});
					
				}
			}
		}
		
		//保存成功
		function shSuccess(result){
		
			$("divWaiting").style.display="none";
			$("divDisable").style.display="none";
			
			alertInfo(result,function(tag){
				if(tag == "ok"){
					$("btn_sx").click();
				}
			});
		}
		
		//显示学生投诉Div
		function showXsssDiv(id,ssnr,sssj,clr,clyj){
			$("ssnr").value = ssnr;
			$("xmid").value = id;
			$("p_sssj").innerHTML = sssj;			
			$("p_clr").innerHTML = clr;
			$("clyj").value = clyj;
			
			if(clr == ""){
				$("btn_bc").style.display = "";
			}else{
				$("btn_bc").style.display = "none";
			}
			tipsWindown("系统提示","id:div_xsss","350","380","true","","true","id");
		}
		
		//保存投诉处理
		function saveSscl(){
		
			var clyj = $("clyj").value;
			
			if(clyj == ""){
				alertError("处理意见不可为空，请确认！");
				return false;
			}
			
			var url="szgyyq_mypj_tea.do?method=saveSscl";
			$("divWaiting").style.display="";
			$("divDisable").style.display="";

			var xn = $("xn").value;
			var xq = $("xq").value;
			var xh = $("xh").value;
			var xmid = $("xmid").value;
			var xmlx = $("xmdm").value;
			
			//参数
		 	var parameter = {
		 		"xn":xn,
		 		"xq":xq,
				"xh":xh,
				"xmid":xmid,
				"xmlx":xmlx,
				"clyj":escape(clyj)
			};

			jQuery.post(url,parameter,function(result){doSuccess(result);});
		}
		
				
		//执行成功
		function doSuccess(result){
			$("divWaiting").style.display="none";
			$("divDisable").style.display="none";
			alertInfo(result,function(tag){				
				if(tag == "ok"){
					closeWindown();
					$("btn_sx").click();}});
		}
		
		function setMrf(){
			var yhlx=$("yhlx").value;
			var xmdm=$("xmdm").value;
			var bzrlrf=document.getElementsByName("bzrshf");
			var xylrf=document.getElementsByName("xyshf");
			var xxlrf=document.getElementsByName("xxshf");
			var mrf=document.getElementsByName("mrf");
			for(var i=0;i<mrf.length;i++){
				if(bzrlrf[i] || xylrf[i] || xxlrf[i]){
					if($("yhlx").value=="bz"){
						bzrlrf[i].value=mrf[i].value;
					}else if($("yhlx").value=="bzr"){
						bzrlrf[i].value=mrf[i].value;
					}else if($("yhlx").value=="xy"){
						xylrf[i].value=mrf[i].value;
					}else if($("yhlx").value=="xx"){
						xxlrf[i].value=mrf[i].value;
					}
				}
			}
			
		}
		
		</script>
	</head>
	<body onload="" >
	
		<!-- 标题 -->
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>综合素质养成课 - 我的工作 - 分数详细</a>
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
				1.以下所有操作都是基于<font color="blue">${xn }</font>学年，<font color="blue">${xqmc}</font>学期 展开的。</br>
				2.结果集中仅展示上一级审核通过的记录。</br>
				3.如果下一级别用户已经审核过，那么，本级不可操作。</br>
				4.如果<bean:message key="lable.xb" />用户审核通过，学校用户未审核的话，那么该条记录的分数也在算作最终分数。</br>
				5.如果审核状态为<font color="blue">需重审</font>的话，标明该记录被上级用户退回，需要重新审核。
				</span>
			</p>
			<a class="close" title="隐藏"  onclick="this.parentNode.style.display='none';"></a>
		</div>
		<!-- 提示信息 end-->
		
		<html:form action="/szgyyq_mypj_tea" method="post">

			<!-- 隐藏域 -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<input type="hidden" name="xn" id="xn" value="${xn }"/>
			<input type="hidden" name="xq" id="xq" value="${xq }"/>
			<input type="hidden" name="xh" id="xh" value="${xh }"/>
			<input type="hidden" name="xmdm" id="xmdm" value="${xmdm }"/>
			<input type="hidden" name="shxm" id="shxm" value="${xmdm }"/>
			<input type="hidden" name="xmid" id="xmid" value=""/>
			<input type="hidden" name="yhlx" id="yhlx" value="${yhlx }"/>
			<input type="hidden" id="xbmc" value="<bean:message key="lable.xb" />"/>
			<!-- 刷新  -->
			<button type="button" id="btn_sx" onclick="reflashShxxDetail()" style="display:none">
				刷新
			</button>
			<!-- 隐藏域 end-->
			
			<!-- 学生基本信息 -->
			<table class="formlist" width="">
				<thead onclick="">
					<tr>
						<th colspan="4">
							<span>${xmmc }审核情况
								<logic:notEmpty name="zhcpsd">
									【基础分(${zhcpsd.zxmjcf }) 总分(${zhcpsd.zxmzgf })】
								</logic:notEmpty>
							</span>
						</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<th width="20%">学号</th>
						<td width="30%">
							${stuInfo.xh }
						</td>
						<th width="20%">姓名</th>
						<td width="30%">
							${stuInfo.xm }
						</td>
					</tr>
					<tr>
						<td colspan="4">
							<div style="height:360px;overflow-x:auto;overflow-y:auto;">
								<table class="dateline" width="100%">
								
									<!-- 标题 -->
							    	<thead>
										<tr>
											<td>
												<input type="checkbox" id="selall" name="selall" onclick="selAll()" />
											</td>
											<logic:iterate id="tit" name="topTr">
												<td>
													<bean:write name="tit" property="cn" />
												</td>
											</logic:iterate>
							      		</tr>
									</thead>
									<!-- 标题 end-->
										
									<!-- 1.读书活动 -->
									<logic:equal name="xmdm" value="szyq_dshdjzb">
										<logic:iterate name="infoList" id="info">
											<tr>
												<td>
													<logic:notEmpty name="info" property="id">
														<logic:equal name="info" property="kfsh" value="yes">
															<input type="checkbox" name="checkVal" value="${info.id }" />
														</logic:equal>
														<logic:equal name="info" property="kfsh" value="no">
															<input type="checkbox" name="checkVal" disabled="disabled" />
														</logic:equal>
													</logic:notEmpty>
												</td>
												<td title="${info.alldsmc }">
													<!-- 读书名称 -->
													${info.dsmc }
												</td>
												<td>
													<!-- 读书日期 -->
													${info.dsrq }
												</td>
												<td title="${info.alldsxd }">
													<!-- 读书心得 -->
													${info.dsxd }
												</td>
												<td>
													<!-- 是否获奖 -->
													${info.sfhj }
												</td>
												<td>
													<!-- 申请分 -->
													${info.sqf }
												</td>
												<!-- 班长用户 -->
												<logic:equal name="yhlx" value="bz">
													<td>
														<!-- 班主任审核分 -->
														<logic:notEmpty name="info" property="id">
															<logic:equal name="info" property="kfsh" value="yes">
																<input type="text" name="bzrshf" 
																	id="bzrshf_${info.id }" 
																	value="${info.bzrlrf }" 
																	onkeyup="checkInputNum(this)"
																	onblur="checkInputNum(this)"
																	maxlength="5" 
																	style="width : 50px;ime-mode:disabled;"/>
															</logic:equal>
															<logic:equal name="info" property="kfsh" value="no">
																${info.bzrlrf }
															</logic:equal>	
														</logic:notEmpty>	
													</td>	
													<td>
														<!-- 审核状态 -->
														${info.bzrsh }
													</td>
												</logic:equal>
												<!-- 班长用户 end-->
												
												<!-- 班主任用户 -->
												<logic:equal name="yhlx" value="bzr">
													<td>
														<!-- 班主任审核分 -->
														<logic:notEmpty name="info" property="id">
															<logic:equal name="info" property="kfsh" value="yes">
																<input type="text" name="bzrshf" 
																	id="bzrshf_${info.id }" 
																	value="${info.bzrlrf }" 
																	onkeyup="checkInputNum(this)"
																	onblur="checkInputNum(this)"
																	maxlength="5" 
																	style="width : 50px;ime-mode:disabled;"/>
															</logic:equal>
															<logic:equal name="info" property="kfsh" value="no">
																${info.bzrlrf }
															</logic:equal>	
														</logic:notEmpty>	
													</td>	
													<td>
														<!-- 审核状态 -->
														${info.bzrsh }
													</td>
												</logic:equal>
												<!-- 班主任用户 end-->
												
												<!-- 学院用户 -->
												<logic:equal name="yhlx" value="xy">
													<td>
														<!-- 班主任审核分 -->
														${info.bzrshf }
													</td>
													<td>
														<!-- 学院审核分 -->
														<logic:notEmpty name="info" property="id">
															<logic:equal name="info" property="kfsh" value="yes">
																<input type="text" name="xyshf" 
																	id="xyshf_${info.id }" 
																	value="${info.xylrf }" 
																	onkeyup="checkInputNum(this)"
																	onblur="checkInputNum(this)"
																	maxlength="5" 
																	style="width : 50px;ime-mode:disabled;"/>
															</logic:equal>
															<logic:equal name="info" property="kfsh" value="no">
																${info.xylrf }
															</logic:equal>	
														</logic:notEmpty>	
													</td>
													<td>
														<!-- 审核状态 -->
														${info.xysh }
													</td>
													<td>
														<!-- 申诉 -->
														<logic:notEmpty name="info" property="id">
															<!-- 是否有申诉 -->
															<logic:empty name="info" property="sssj">
																无
															</logic:empty>
															<logic:notEmpty name="info" property="sssj">
																<!-- 是否被处理 -->
																<logic:notEmpty name="info" property="clr">
																	<a href="#" onclick="showXsssDiv('${info.id }','${info.ssnr }','${info.sssj }','${info.clr }','${info.clyj }')">
																		<font color="blue">
																			已处理
																		</font>
																	</a>
																</logic:notEmpty>
																<logic:empty name="info" property="clr">
																	<a href="#" onclick="showXsssDiv('${info.id }','${info.ssnr }','${info.sssj }','','')">
																		<font color="blue">
																			有
																		</font>
																	</a>
																</logic:empty>
															</logic:notEmpty>
														</logic:notEmpty>
													</td>
												</logic:equal>
												<!-- 学院用户 end-->
												
												<!-- 学校用户 -->
												<logic:equal name="yhlx" value="xx">
													<td>
														<!-- 班主任审核分 -->
														${info.bzrshf }
													</td>
													<td>
														<!-- 学院审核分 -->
														${info.xyshf }
													</td>
													<td>
														<!-- 学校审核分 -->
														<logic:notEmpty name="info" property="id">
															<logic:equal name="info" property="kfsh" value="yes">
																<input type="text" name="xxshf" 
																	id="xxshf_${info.id }" 
																	value="${info.xxlrf }" 
																	onkeyup="checkInputNum(this)"
																	onblur="checkInputNum(this)"
																	maxlength="5" 
																	style="width : 50px;ime-mode:disabled;"/>
															</logic:equal>
															<logic:equal name="info" property="kfsh" value="no">
																${info.xxlrf }
															</logic:equal>	
														</logic:notEmpty>	
													</td>
													<td>
														<!-- 审核状态 -->
														${info.xxsh }
													</td>
													<td>
														<!-- 申诉 -->
														<logic:notEmpty name="info" property="id">
															<!-- 是否有申诉 -->
															<logic:empty name="info" property="sssj">
																无
															</logic:empty>
															<logic:notEmpty name="info" property="sssj">
																<!-- 是否被处理 -->
																<logic:notEmpty name="info" property="clr">
																	<a href="#" onclick="showXsssDiv('${info.id }','${info.ssnr }','${info.sssj }','${info.clr }','${info.clyj }')">
																		<font color="blue">
																			已处理
																		</font>
																	</a>
																</logic:notEmpty>
																<logic:empty name="info" property="clr">
																	<a href="#" onclick="showXsssDiv('${info.id }','${info.ssnr }','${info.sssj }','','')">
																		<font color="blue">
																			有
																		</font>
																	</a>
																</logic:empty>
															</logic:notEmpty>
														</logic:notEmpty>
													</td>
												</logic:equal>
												<!-- 学校用户 end-->
												<logic:equal name="info" property="kfsh" value="yes">
												<logic:equal name="yhlx" value="bz">
													<input type="hidden" name="mrf" value="${info.sqf }"/>
												</logic:equal>
												<logic:equal name="yhlx" value="bzr">
													<input type="hidden" name="mrf" value="${info.sqf }"/>
												</logic:equal>
												<logic:equal name="yhlx" value="xy">
													<input type="hidden" name="mrf" value="${info.bzrlrf }"/>
												</logic:equal>
												<logic:equal name="yhlx" value="xx">
													<input type="hidden" name="mrf" value="${info.xylrf }"/>
												</logic:equal>
												</logic:equal>
											</tr>
										</logic:iterate>
									</logic:equal>
									<!-- 读书活动 end-->
									
									<!--2.语言表达  start-->
									<logic:equal name="xmdm" value="szyq_yybdjzb">
										<logic:iterate name="infoList" id="info">
											<tr>
												<td>
													<logic:notEmpty name="info" property="id">
														<logic:equal name="info" property="kfsh" value="yes">
															<input type="checkbox" name="checkVal" value="${info.id }" />
														</logic:equal>
														<logic:equal name="info" property="kfsh" value="no">
															<input type="checkbox" name="checkVal" disabled="disabled" />
														</logic:equal>
													</logic:notEmpty>
												</td>
												<td title="${info.allyybdnr }">
													<!-- 语言表达内容 -->
													${info.yybdnr }
												</td>
												<td>
													<!-- 日期 -->
													${info.xthdrq }
												</td>
												<td>
													<!-- 申请分 -->
													${info.sqf }
												</td>
												<!-- 班长用户 -->
												<logic:equal name="yhlx" value="bz">
													<td>
														<!-- 班主任审核分 -->
														<logic:notEmpty name="info" property="id">
															<logic:equal name="info" property="kfsh" value="yes">
																<input type="text" name="bzrshf" 
																	id="bzrshf_${info.id }" 
																	value="${info.bzrlrf }" 
																	onkeyup="checkInputNum(this)"
																	onblur="checkInputNum(this)"
																	maxlength="5" 
																	style="width : 50px;ime-mode:disabled;"/>
															</logic:equal>
															<logic:equal name="info" property="kfsh" value="no">
																${info.bzrlrf }
															</logic:equal>	
														</logic:notEmpty>	
													</td>	
													<td>
														<!-- 审核状态 -->
														${info.bzrsh }
													</td>
												</logic:equal>
												<!-- 班长用户 end-->
												
												<!-- 班主任用户 -->
												<logic:equal name="yhlx" value="bzr">
													<td>
														<!-- 班主任审核分 -->
														<logic:notEmpty name="info" property="id">
															<logic:equal name="info" property="kfsh" value="yes">
																<input type="text" name="bzrshf" 
																	id="bzrshf_${info.id }" 
																	value="${info.bzrlrf }" 
																	onkeyup="checkInputNum(this)"
																	onblur="checkInputNum(this)"
																	maxlength="5" 
																	style="width : 50px;ime-mode:disabled;"/>
															</logic:equal>
															<logic:equal name="info" property="kfsh" value="no">
																${info.bzrlrf }
															</logic:equal>	
														</logic:notEmpty>	
													</td>	
													<td>
														<!-- 审核状态 -->
														${info.bzrsh }
													</td>
												</logic:equal>
												<!-- 班主任用户 end-->
												
												<!-- 学院用户 -->
												<logic:equal name="yhlx" value="xy">
													<td>
														<!-- 班主任审核分 -->
														${info.bzrshf }
													</td>
													<td>
														<!-- 学院审核分 -->
														<logic:notEmpty name="info" property="id">
															<logic:equal name="info" property="kfsh" value="yes">
																<input type="text" name="xyshf" 
																	id="xyshf_${info.id }" 
																	value="${info.xylrf }" 
																	onkeyup="checkInputNum(this)"
																	onblur="checkInputNum(this)"
																	maxlength="5" 
																	style="width : 50px;ime-mode:disabled;"/>
															</logic:equal>
															<logic:equal name="info" property="kfsh" value="no">
																${info.xylrf }
															</logic:equal>	
														</logic:notEmpty>	
													</td>
													<td>
														<!-- 审核状态 -->
														${info.xysh }
													</td>
													<td>
														<!-- 申诉 -->
														<logic:notEmpty name="info" property="id">
															<!-- 是否有申诉 -->
															<logic:empty name="info" property="sssj">
																无
															</logic:empty>
															<logic:notEmpty name="info" property="sssj">
																<!-- 是否被处理 -->
																<logic:notEmpty name="info" property="clr">
																	<a href="#" onclick="showXsssDiv('${info.id }','${info.ssnr }','${info.sssj }','${info.clr }','${info.clyj }')">
																		<font color="blue">
																			已处理
																		</font>
																	</a>
																</logic:notEmpty>
																<logic:empty name="info" property="clr">
																	<a href="#" onclick="showXsssDiv('${info.id }','${info.ssnr }','${info.sssj }','','')">
																		<font color="blue">
																			有
																		</font>
																	</a>
																</logic:empty>
															</logic:notEmpty>
														</logic:notEmpty>
													</td>
												</logic:equal>
												<!-- 学院用户 end-->
												
												<!-- 学校用户 -->
												<logic:equal name="yhlx" value="xx">
													<td>
														<!-- 班主任审核分 -->
														${info.bzrshf }
													</td>
													<td>
														<!-- 学院审核分 -->
														${info.xyshf }
													</td>
													<td>
														<!-- 学校审核分 -->
														<logic:notEmpty name="info" property="id">
															<logic:equal name="info" property="kfsh" value="yes">
																<input type="text" name="xxshf" 
																	id="xxshf_${info.id }" 
																	value="${info.xxlrf }" 
																	onkeyup="checkInputNum(this)"
																	onblur="checkInputNum(this)"
																	maxlength="5" 
																	style="width : 50px;ime-mode:disabled;"/>
															</logic:equal>
															<logic:equal name="info" property="kfsh" value="no">
																${info.xxlrf }
															</logic:equal>	
														</logic:notEmpty>	
													</td>
													<td>
														<!-- 审核状态 -->
														${info.xxsh }
													</td>
													<td>
														<!-- 申诉 -->
														<logic:notEmpty name="info" property="id">
															<!-- 是否有申诉 -->
															<logic:empty name="info" property="sssj">
																无
															</logic:empty>
															<logic:notEmpty name="info" property="sssj">
																<!-- 是否被处理 -->
																<logic:notEmpty name="info" property="clr">
																	<a href="#" onclick="showXsssDiv('${info.id }','${info.ssnr }','${info.sssj }','${info.clr }','${info.clyj }')">
																		<font color="blue">
																			已处理
																		</font>
																	</a>
																</logic:notEmpty>
																<logic:empty name="info" property="clr">
																	<a href="#" onclick="showXsssDiv('${info.id }','${info.ssnr }','${info.sssj }','','')">
																		<font color="blue">
																			有
																		</font>
																	</a>
																</logic:empty>
															</logic:notEmpty>
														</logic:notEmpty>
													</td>
												</logic:equal>
												<!-- 学校用户 end-->
												<logic:equal name="info" property="kfsh" value="yes">
													<logic:equal name="yhlx" value="bz">
														<input type="hidden" name="mrf" value="${info.sqf }"/>
													</logic:equal>
													<logic:equal name="yhlx" value="bzr">
														<input type="hidden" name="mrf" value="${info.sqf }"/>
													</logic:equal>
													<logic:equal name="yhlx" value="xy">
														<input type="hidden" name="mrf" value="${info.bzrlrf }"/>
													</logic:equal>
													<logic:equal name="yhlx" value="xx">
														<input type="hidden" name="mrf" value="${info.xylrf }"/>
													</logic:equal>
												</logic:equal>
											</tr>
										</logic:iterate>
									</logic:equal>
									<!--2.语言表达  end-->
									
									<!--3.ivt论坛  start -->
									<logic:equal name="xmdm" value="szyq_ivtltb">
										<logic:iterate name="infoList" id="info">
											<tr>
												<td>
													<logic:notEmpty name="info" property="id">
														<logic:equal name="info" property="kfsh" value="yes">
															<input type="checkbox" name="checkVal" value="${info.id }" />
														</logic:equal>
														<logic:equal name="info" property="kfsh" value="no">
															<input type="checkbox" name="checkVal" disabled="disabled" />
														</logic:equal>
													</logic:notEmpty>
												</td>
												<td title="${info.alljztm }">
													<!-- 讲座题目 -->
													${info.jztm }
												</td>
												<td>
													<!-- 日期 -->
													${info.xthdrq }
												</td>
												<td>
													<!-- 进场登记-->
													${info.jcdj }
												</td>
												<td>
													<!-- 出场登记 -->
													${info.ccdj }
												</td>
												<td>
													<!-- 申请分 -->
													${info.sqf }
												</td>
												<!-- 班长用户 -->
												<logic:equal name="yhlx" value="bz">
													<td>
														<!-- 班主任审核分 -->
														<logic:notEmpty name="info" property="id">
															<logic:equal name="info" property="kfsh" value="yes">
																<input type="text" name="bzrshf" 
																	id="bzrshf_${info.id }" 
																	value="${info.bzrlrf }" 
																	onkeyup="checkInputNum(this)"
																	onblur="checkInputNum(this)"
																	maxlength="5" 
																	style="width : 50px;ime-mode:disabled;"/>
															</logic:equal>
															<logic:equal name="info" property="kfsh" value="no">
																${info.bzrlrf }
															</logic:equal>	
														</logic:notEmpty>	
													</td>	
													<td>
														<!-- 审核状态 -->
														${info.bzrsh }
													</td>
												</logic:equal>
												<!-- 班长用户 end-->
												
												<!-- 班主任用户 -->
												<logic:equal name="yhlx" value="bzr">
													<td>
														<!-- 班主任审核分 -->
														<logic:notEmpty name="info" property="id">
															<logic:equal name="info" property="kfsh" value="yes">
																<input type="text" name="bzrshf" 
																	id="bzrshf_${info.id }" 
																	value="${info.bzrlrf }" 
																	onkeyup="checkInputNum(this)"
																	onblur="checkInputNum(this)"
																	maxlength="5" 
																	style="width : 50px;ime-mode:disabled;"/>
															</logic:equal>
															<logic:equal name="info" property="kfsh" value="no">
																${info.bzrlrf }
															</logic:equal>	
														</logic:notEmpty>	
													</td>	
													<td>
														<!-- 审核状态 -->
														${info.bzrsh }
													</td>
												</logic:equal>
												<!-- 班主任用户 end-->
												
												<!-- 学院用户 -->
												<logic:equal name="yhlx" value="xy">
													<td>
														<!-- 班主任审核分 -->
														${info.bzrshf }
													</td>
													<td>
														<!-- 学院审核分 -->
														<logic:notEmpty name="info" property="id">
															<logic:equal name="info" property="kfsh" value="yes">
																<input type="text" name="xyshf" 
																	id="xyshf_${info.id }" 
																	value="${info.xylrf }" 
																	onkeyup="checkInputNum(this)"
																	onblur="checkInputNum(this)"
																	maxlength="5" 
																	style="width : 50px;ime-mode:disabled;"/>
															</logic:equal>
															<logic:equal name="info" property="kfsh" value="no">
																${info.xylrf }
															</logic:equal>	
														</logic:notEmpty>	
													</td>
													<td>
														<!-- 审核状态 -->
														${info.xysh }
													</td>
													<td>
														<!-- 申诉 -->
														<logic:notEmpty name="info" property="id">
															<!-- 是否有申诉 -->
															<logic:empty name="info" property="sssj">
																无
															</logic:empty>
															<logic:notEmpty name="info" property="sssj">
																<!-- 是否被处理 -->
																<logic:notEmpty name="info" property="clr">
																	<a href="#" onclick="showXsssDiv('${info.id }','${info.ssnr }','${info.sssj }','${info.clr }','${info.clyj }')">
																		<font color="blue">
																			已处理
																		</font>
																	</a>
																</logic:notEmpty>
																<logic:empty name="info" property="clr">
																	<a href="#" onclick="showXsssDiv('${info.id }','${info.ssnr }','${info.sssj }','','')">
																		<font color="blue">
																			有
																		</font>
																	</a>
																</logic:empty>
															</logic:notEmpty>
														</logic:notEmpty>
													</td>
												</logic:equal>
												<!-- 学院用户 end-->
												
												<!-- 学校用户 -->
												<logic:equal name="yhlx" value="xx">
													<td>
														<!-- 班主任审核分 -->
														${info.bzrshf }
													</td>
													<td>
														<!-- 学院审核分 -->
														${info.xyshf }
													</td>
													<td>
														<!-- 学校审核分 -->
														<logic:notEmpty name="info" property="id">
															<logic:equal name="info" property="kfsh" value="yes">
																<input type="text" name="xxshf" 
																	id="xxshf_${info.id }" 
																	value="${info.xxlrf }" 
																	onkeyup="checkInputNum(this)"
																	onblur="checkInputNum(this)"
																	maxlength="5" 
																	style="width : 50px;ime-mode:disabled;"/>
															</logic:equal>
															<logic:equal name="info" property="kfsh" value="no">
																${info.xxlrf }
															</logic:equal>	
														</logic:notEmpty>	
													</td>
													<td>
														<!-- 审核状态 -->
														${info.xxsh }
													</td>
													<td>
														<!-- 申诉 -->
														<logic:notEmpty name="info" property="id">
															<!-- 是否有申诉 -->
															<logic:empty name="info" property="sssj">
																无
															</logic:empty>
															<logic:notEmpty name="info" property="sssj">
																<!-- 是否被处理 -->
																<logic:notEmpty name="info" property="clr">
																	<a href="#" onclick="showXsssDiv('${info.id }','${info.ssnr }','${info.sssj }','${info.clr }','${info.clyj }')">
																		<font color="blue">
																			已处理
																		</font>
																	</a>
																</logic:notEmpty>
																<logic:empty name="info" property="clr">
																	<a href="#" onclick="showXsssDiv('${info.id }','${info.ssnr }','${info.sssj }','','')">
																		<font color="blue">
																			有
																		</font>
																	</a>
																</logic:empty>
															</logic:notEmpty>
														</logic:notEmpty>
													</td>
												</logic:equal>
												<!-- 学校用户 end-->
												<logic:equal name="info" property="kfsh" value="yes">
													<logic:equal name="yhlx" value="bz">
														<input type="hidden" name="mrf" value="${info.sqf }"/>
													</logic:equal>
													<logic:equal name="yhlx" value="bzr">
														<input type="hidden" name="mrf" value="${info.sqf }"/>
													</logic:equal>
													<logic:equal name="yhlx" value="xy">
														<input type="hidden" name="mrf" value="${info.bzrlrf }"/>
													</logic:equal>
													<logic:equal name="yhlx" value="xx">
														<input type="hidden" name="mrf" value="${info.xylrf }"/>
													</logic:equal>
												</logic:equal>
											</tr>
										</logic:iterate>
									</logic:equal>
									<!--3.ivt论坛  end -->
									
									<!--4.文体活动 start -->
									<logic:equal name="xmdm" value="szyq_xthddjb">
										<logic:iterate name="infoList" id="info">
											<tr>
												<td>
													<logic:notEmpty name="info" property="id">
														<logic:equal name="info" property="kfsh" value="yes">
															<input type="checkbox" name="checkVal" value="${info.id }" />
														</logic:equal>
														<logic:equal name="info" property="kfsh" value="no">
															<input type="checkbox" name="checkVal" disabled="disabled" />
														</logic:equal>
													</logic:notEmpty>
												</td>
												<td title="${info.allhdnr }">
													<!-- 活动内容 -->
													${info.hdnr }
												</td>
												<td>
													<!-- 日期 -->
													${info.xthdrq }
												</td>
												<td>
													<!-- 奖励等级 -->
													${info.jldj }
												</td>
												<td>
													<!-- 申请分 -->
													${info.sqf }
												</td>
												<!-- 班长用户 -->
												<logic:equal name="yhlx" value="bz">
													<td>
														<!-- 班主任审核分 -->
														<logic:notEmpty name="info" property="id">
															<logic:equal name="info" property="kfsh" value="yes">
																<input type="text" name="bzrshf" 
																	id="bzrshf_${info.id }" 
																	value="${info.bzrlrf }" 
																	onkeyup="checkInputNum(this)"
																	onblur="checkInputNum(this)"
																	maxlength="5" 
																	style="width : 50px;ime-mode:disabled;"/>
															</logic:equal>
															<logic:equal name="info" property="kfsh" value="no">
																${info.bzrlrf }
															</logic:equal>	
														</logic:notEmpty>	
													</td>	
													<td>
														<!-- 审核状态 -->
														${info.bzrsh }
													</td>
												</logic:equal>
												<!-- 班长用户 end-->
												
												<!-- 班主任用户 -->
												<logic:equal name="yhlx" value="bzr">
													<td>
														<!-- 班主任审核分 -->
														<logic:notEmpty name="info" property="id">
															<logic:equal name="info" property="kfsh" value="yes">
																<input type="text" name="bzrshf" 
																	id="bzrshf_${info.id }" 
																	value="${info.bzrlrf }" 
																	onkeyup="checkInputNum(this)"
																	onblur="checkInputNum(this)"
																	maxlength="5" 
																	style="width : 50px;ime-mode:disabled;"/>
															</logic:equal>
															<logic:equal name="info" property="kfsh" value="no">
																${info.bzrlrf }
															</logic:equal>	
														</logic:notEmpty>	
													</td>	
													<td>
														<!-- 审核状态 -->
														${info.bzrsh }
													</td>
												</logic:equal>
												<!-- 班主任用户 end-->
												
												<!-- 学院用户 -->
												<logic:equal name="yhlx" value="xy">
													<td>
														<!-- 班主任审核分 -->
														${info.bzrshf }
													</td>
													<td>
														<!-- 学院审核分 -->
														<logic:notEmpty name="info" property="id">
															<logic:equal name="info" property="kfsh" value="yes">
																<input type="text" name="xyshf" 
																	id="xyshf_${info.id }" 
																	value="${info.xylrf }" 
																	onkeyup="checkInputNum(this)"
																	onblur="checkInputNum(this)"
																	maxlength="5" 
																	style="width : 50px;ime-mode:disabled;"/>
															</logic:equal>
															<logic:equal name="info" property="kfsh" value="no">
																${info.xylrf }
															</logic:equal>	
														</logic:notEmpty>	
													</td>
													<td>
														<!-- 审核状态 -->
														${info.xysh }
													</td>
													<td>
														<!-- 申诉 -->
														<logic:notEmpty name="info" property="id">
															<!-- 是否有申诉 -->
															<logic:empty name="info" property="sssj">
																无
															</logic:empty>
															<logic:notEmpty name="info" property="sssj">
																<!-- 是否被处理 -->
																<logic:notEmpty name="info" property="clr">
																	<a href="#" onclick="showXsssDiv('${info.id }','${info.ssnr }','${info.sssj }','${info.clr }','${info.clyj }')">
																		<font color="blue">
																			已处理
																		</font>
																	</a>
																</logic:notEmpty>
																<logic:empty name="info" property="clr">
																	<a href="#" onclick="showXsssDiv('${info.id }','${info.ssnr }','${info.sssj }','','')">
																		<font color="blue">
																			有
																		</font>
																	</a>
																</logic:empty>
															</logic:notEmpty>
														</logic:notEmpty>
													</td>
												</logic:equal>
												<!-- 学院用户 end-->
												
												<!-- 学校用户 -->
												<logic:equal name="yhlx" value="xx">
													<td>
														<!-- 班主任审核分 -->
														${info.bzrshf }
													</td>
													<td>
														<!-- 学院审核分 -->
														${info.xyshf }
													</td>
													<td>
														<!-- 学校审核分 -->
														<logic:notEmpty name="info" property="id">
															<logic:equal name="info" property="kfsh" value="yes">
																<input type="text" name="xxshf" 
																	id="xxshf_${info.id }" 
																	value="${info.xxlrf }" 
																	onkeyup="checkInputNum(this)"
																	onblur="checkInputNum(this)"
																	maxlength="5" 
																	style="width : 50px;ime-mode:disabled;"/>
															</logic:equal>
															<logic:equal name="info" property="kfsh" value="no">
																${info.xxlrf }
															</logic:equal>	
														</logic:notEmpty>	
													</td>
													<td>
														<!-- 审核状态 -->
														${info.xxsh }
													</td>
													<td>
														<!-- 申诉 -->
														<logic:notEmpty name="info" property="id">
															<!-- 是否有申诉 -->
															<logic:empty name="info" property="sssj">
																无
															</logic:empty>
															<logic:notEmpty name="info" property="sssj">
																<!-- 是否被处理 -->
																<logic:notEmpty name="info" property="clr">
																	<a href="#" onclick="showXsssDiv('${info.id }','${info.ssnr }','${info.sssj }','${info.clr }','${info.clyj }')">
																		<font color="blue">
																			已处理
																		</font>
																	</a>
																</logic:notEmpty>
																<logic:empty name="info" property="clr">
																	<a href="#" onclick="showXsssDiv('${info.id }','${info.ssnr }','${info.sssj }','','')">
																		<font color="blue">
																			有
																		</font>
																	</a>
																</logic:empty>
															</logic:notEmpty>
														</logic:notEmpty>
													</td>
												</logic:equal>
												<!-- 学校用户 end-->
												<logic:equal name="info" property="kfsh" value="yes">
													<logic:equal name="yhlx" value="bz">
														<input type="hidden" name="mrf" value="${info.sqf }"/>
													</logic:equal>
													<logic:equal name="yhlx" value="bzr">
														<input type="hidden" name="mrf" value="${info.sqf }"/>
													</logic:equal>
													<logic:equal name="yhlx" value="xy">
														<input type="hidden" name="mrf" value="${info.bzrlrf }"/>
													</logic:equal>
													<logic:equal name="yhlx" value="xx">
														<input type="hidden" name="mrf" value="${info.xylrf }"/>
													</logic:equal>
												</logic:equal>
											</tr>
										</logic:iterate>
									</logic:equal>
									<!--4.文体活动 end -->
									
									<!--5.组织能力  start-->
									<logic:equal name="xmdm" value="szyc_zznlfzb">
										<logic:iterate name="infoList" id="info">
											<tr>
												<td>
													<logic:notEmpty name="info" property="id">
														<logic:equal name="info" property="kfsh" value="yes">
															<input type="checkbox" name="checkVal" value="${info.id }" />
														</logic:equal>
														<logic:equal name="info" property="kfsh" value="no">
															<input type="checkbox" name="checkVal" disabled="disabled" />
														</logic:equal>
													</logic:notEmpty>
												</td>
												<td title="${info.allhdzt }">
													<!-- 活动主题 -->
													${info.hdzt }
												</td>
												<td>
													<!-- 活动日期 -->
													${info.hdrq }
												</td>
												<td>
													<!-- 活动等级 -->
													${info.hddj }
												</td>
												<td>
													<!-- 申请分 -->
													${info.sqf }
												</td>
												<!-- 班长用户 -->
												<logic:equal name="yhlx" value="bz">
													<td>
														<!-- 班主任审核分 -->
														<logic:notEmpty name="info" property="id">
															<logic:equal name="info" property="kfsh" value="yes">
																<input type="text" name="bzrshf" 
																	id="bzrshf_${info.id }" 
																	value="${info.bzrlrf }" 
																	onkeyup="checkInputNum(this)"
																	onblur="checkInputNum(this)"
																	maxlength="5" 
																	style="width : 50px;ime-mode:disabled;"/>
															</logic:equal>
															<logic:equal name="info" property="kfsh" value="no">
																${info.bzrlrf }
															</logic:equal>	
														</logic:notEmpty>	
													</td>	
													<td>
														<!-- 审核状态 -->
														${info.bzrsh }
													</td>
												</logic:equal>
												<!-- 班长用户 end-->
												
												<!-- 班主任用户 -->
												<logic:equal name="yhlx" value="bzr">
													<td>
														<!-- 班主任审核分 -->
														<logic:notEmpty name="info" property="id">
															<logic:equal name="info" property="kfsh" value="yes">
																<input type="text" name="bzrshf" 
																	id="bzrshf_${info.id }" 
																	value="${info.bzrlrf }" 
																	onkeyup="checkInputNum(this)"
																	onblur="checkInputNum(this)"
																	maxlength="5" 
																	style="width : 50px;ime-mode:disabled;"/>
															</logic:equal>
															<logic:equal name="info" property="kfsh" value="no">
																${info.bzrlrf }
															</logic:equal>	
														</logic:notEmpty>	
													</td>	
													<td>
														<!-- 审核状态 -->
														${info.bzrsh }
													</td>
												</logic:equal>
												<!-- 班主任用户 end-->
												
												<!-- 学院用户 -->
												<logic:equal name="yhlx" value="xy">
													<td>
														<!-- 班主任审核分 -->
														${info.bzrshf }
													</td>
													<td>
														<!-- 学院审核分 -->
														<logic:notEmpty name="info" property="id">
															<logic:equal name="info" property="kfsh" value="yes">
																<input type="text" name="xyshf" 
																	id="xyshf_${info.id }" 
																	value="${info.xylrf }" 
																	onkeyup="checkInputNum(this)"
																	onblur="checkInputNum(this)"
																	maxlength="5" 
																	style="width : 50px;ime-mode:disabled;"/>
															</logic:equal>
															<logic:equal name="info" property="kfsh" value="no">
																${info.xylrf }
															</logic:equal>	
														</logic:notEmpty>	
													</td>
													<td>
														<!-- 审核状态 -->
														${info.xysh }
													</td>
													<td>
														<!-- 申诉 -->
														<logic:notEmpty name="info" property="id">
															<!-- 是否有申诉 -->
															<logic:empty name="info" property="sssj">
																无
															</logic:empty>
															<logic:notEmpty name="info" property="sssj">
																<!-- 是否被处理 -->
																<logic:notEmpty name="info" property="clr">
																	<a href="#" onclick="showXsssDiv('${info.id }','${info.ssnr }','${info.sssj }','${info.clr }','${info.clyj }')">
																		<font color="blue">
																			已处理
																		</font>
																	</a>
																</logic:notEmpty>
																<logic:empty name="info" property="clr">
																	<a href="#" onclick="showXsssDiv('${info.id }','${info.ssnr }','${info.sssj }','','')">
																		<font color="blue">
																			有
																		</font>
																	</a>
																</logic:empty>
															</logic:notEmpty>
														</logic:notEmpty>
													</td>
												</logic:equal>
												<!-- 学院用户 end-->
												
												<!-- 学校用户 -->
												<logic:equal name="yhlx" value="xx">
													<td>
														<!-- 班主任审核分 -->
														${info.bzrshf }
													</td>
													<td>
														<!-- 学院审核分 -->
														${info.xyshf }
													</td>
													<td>
														<!-- 学校审核分 -->
														<logic:notEmpty name="info" property="id">
															<logic:equal name="info" property="kfsh" value="yes">
																<input type="text" name="xxshf" 
																	id="xxshf_${info.id }" 
																	value="${info.xxlrf }" 
																	onkeyup="checkInputNum(this)"
																	onblur="checkInputNum(this)"
																	maxlength="5" 
																	style="width : 50px;ime-mode:disabled;"/>
															</logic:equal>
															<logic:equal name="info" property="kfsh" value="no">
																${info.xxlrf }
															</logic:equal>	
														</logic:notEmpty>	
													</td>
													<td>
														<!-- 审核状态 -->
														${info.xxsh }
													</td>
													<td>
														<!-- 申诉 -->
														<logic:notEmpty name="info" property="id">
															<!-- 是否有申诉 -->
															<logic:empty name="info" property="sssj">
																无
															</logic:empty>
															<logic:notEmpty name="info" property="sssj">
																<!-- 是否被处理 -->
																<logic:notEmpty name="info" property="clr">
																	<a href="#" onclick="showXsssDiv('${info.id }','${info.ssnr }','${info.sssj }','${info.clr }','${info.clyj }')">
																		<font color="blue">
																			已处理
																		</font>
																	</a>
																</logic:notEmpty>
																<logic:empty name="info" property="clr">
																	<a href="#" onclick="showXsssDiv('${info.id }','${info.ssnr }','${info.sssj }','','')">
																		<font color="blue">
																			有
																		</font>
																	</a>
																</logic:empty>
															</logic:notEmpty>
														</logic:notEmpty>
													</td>
												</logic:equal>
												<!-- 学校用户 end-->
												<logic:equal name="info" property="kfsh" value="yes">
													<logic:equal name="yhlx" value="bz">
														<input type="hidden" name="mrf" value="${info.sqf }"/>
													</logic:equal>
													<logic:equal name="yhlx" value="bzr">
														<input type="hidden" name="mrf" value="${info.sqf }"/>
													</logic:equal>
													<logic:equal name="yhlx" value="xy">
														<input type="hidden" name="mrf" value="${info.bzrlrf }"/>
													</logic:equal>
													<logic:equal name="yhlx" value="xx">
														<input type="hidden" name="mrf" value="${info.xylrf }"/>
													</logic:equal>
												</logic:equal>
											</tr>
										</logic:iterate>
									</logic:equal>
									<!--5.组织能力  end-->
									
									<!--6.社会实践  start-->
									<logic:equal name="xmdm" value="szyc_shsjfzb">
										<logic:iterate name="infoList" id="info">
											<tr>
												<td>
													<logic:notEmpty name="info" property="id">
														<logic:equal name="info" property="kfsh" value="yes">
															<input type="checkbox" name="checkVal" value="${info.id }" />
														</logic:equal>
														<logic:equal name="info" property="kfsh" value="no">
															<input type="checkbox" name="checkVal" disabled="disabled" />
														</logic:equal>
													</logic:notEmpty>
												</td>
												<td title="${info.allhddd }">
													<!-- 活动地点 -->
													${info.hddd }
												</td>
												<td>
													<!-- 活动日期 -->
													${info.hdrq }
												</td>
												<td title="${info.allhdnr }">
													<!-- 活动内容 -->
													${info.hdnr }
												</td>
												<td>
													<!-- 活动日期 -->
													${info.hdrq }
												</td>
												<td>
													<!-- 申请分 -->
													${info.sqf }
												</td>
												<!-- 班长用户 -->
												<logic:equal name="yhlx" value="bz">
													<td>
														<!-- 班主任审核分 -->
														<logic:notEmpty name="info" property="id">
															<logic:equal name="info" property="kfsh" value="yes">
																<input type="text" name="bzrshf" 
																	id="bzrshf_${info.id }" 
																	value="${info.bzrlrf }" 
																	onkeyup="checkInputNum(this)"
																	onblur="checkInputNum(this)"
																	maxlength="5" 
																	style="width : 50px;ime-mode:disabled;"/>
															</logic:equal>
															<logic:equal name="info" property="kfsh" value="no">
																${info.bzrlrf }
															</logic:equal>	
														</logic:notEmpty>	
													</td>	
													<td>
														<!-- 审核状态 -->
														${info.bzrsh }
													</td>
												</logic:equal>
												<!-- 班长用户 end-->
												
												<!-- 班主任用户 -->
												<logic:equal name="yhlx" value="bzr">
													<td>
														<!-- 班主任审核分 -->
														<logic:notEmpty name="info" property="id">
															<logic:equal name="info" property="kfsh" value="yes">
																<input type="text" name="bzrshf" 
																	id="bzrshf_${info.id }" 
																	value="${info.bzrlrf }" 
																	onkeyup="checkInputNum(this)"
																	onblur="checkInputNum(this)"
																	maxlength="5" 
																	style="width : 50px;ime-mode:disabled;"/>
															</logic:equal>
															<logic:equal name="info" property="kfsh" value="no">
																${info.bzrlrf }
															</logic:equal>	
														</logic:notEmpty>	
													</td>	
													<td>
														<!-- 审核状态 -->
														${info.bzrsh }
													</td>
												</logic:equal>
												<!-- 班主任用户 end-->
												
												<!-- 学院用户 -->
												<logic:equal name="yhlx" value="xy">
													<td>
														<!-- 班主任审核分 -->
														${info.bzrshf }
													</td>
													<td>
														<!-- 学院审核分 -->
														<logic:notEmpty name="info" property="id">
															<logic:equal name="info" property="kfsh" value="yes">
																<input type="text" name="xyshf" 
																	id="xyshf_${info.id }" 
																	value="${info.xylrf }" 
																	onkeyup="checkInputNum(this)"
																	onblur="checkInputNum(this)"
																	maxlength="5" 
																	style="width : 50px;ime-mode:disabled;"/>
															</logic:equal>
															<logic:equal name="info" property="kfsh" value="no">
																${info.xylrf }
															</logic:equal>	
														</logic:notEmpty>	
													</td>
													<td>
														<!-- 审核状态 -->
														${info.xysh }
													</td>
													<td>
														<!-- 申诉 -->
														<logic:notEmpty name="info" property="id">
															<!-- 是否有申诉 -->
															<logic:empty name="info" property="sssj">
																无
															</logic:empty>
															<logic:notEmpty name="info" property="sssj">
																<!-- 是否被处理 -->
																<logic:notEmpty name="info" property="clr">
																	<a href="#" onclick="showXsssDiv('${info.id }','${info.ssnr }','${info.sssj }','${info.clr }','${info.clyj }')">
																		<font color="blue">
																			已处理
																		</font>
																	</a>
																</logic:notEmpty>
																<logic:empty name="info" property="clr">
																	<a href="#" onclick="showXsssDiv('${info.id }','${info.ssnr }','${info.sssj }','','')">
																		<font color="blue">
																			有
																		</font>
																	</a>
																</logic:empty>
															</logic:notEmpty>
														</logic:notEmpty>
													</td>
												</logic:equal>
												<!-- 学院用户 end-->
												
												<!-- 学校用户 -->
												<logic:equal name="yhlx" value="xx">
													<td>
														<!-- 班主任审核分 -->
														${info.bzrshf }
													</td>
													<td>
														<!-- 学院审核分 -->
														${info.xyshf }
													</td>
													<td>
														<!-- 学校审核分 -->
														<logic:notEmpty name="info" property="id">
															<logic:equal name="info" property="kfsh" value="yes">
																<input type="text" name="xxshf" 
																	id="xxshf_${info.id }" 
																	value="${info.xxlrf }" 
																	onkeyup="checkInputNum(this)"
																	onblur="checkInputNum(this)"
																	maxlength="5" 
																	style="width : 50px;ime-mode:disabled;"/>
															</logic:equal>
															<logic:equal name="info" property="kfsh" value="no">
																${info.xxlrf }
															</logic:equal>	
														</logic:notEmpty>	
													</td>
													<td>
														<!-- 审核状态 -->
														${info.xxsh }
													</td>
													<td>
														<!-- 申诉 -->
														<logic:notEmpty name="info" property="id">
															<!-- 是否有申诉 -->
															<logic:empty name="info" property="sssj">
																无
															</logic:empty>
															<logic:notEmpty name="info" property="sssj">
																<!-- 是否被处理 -->
																<logic:notEmpty name="info" property="clr">
																	<a href="#" onclick="showXsssDiv('${info.id }','${info.ssnr }','${info.sssj }','${info.clr }','${info.clyj }')">
																		<font color="blue">
																			已处理
																		</font>
																	</a>
																</logic:notEmpty>
																<logic:empty name="info" property="clr">
																	<a href="#" onclick="showXsssDiv('${info.id }','${info.ssnr }','${info.sssj }','','')">
																		<font color="blue">
																			有
																		</font>
																	</a>
																</logic:empty>
															</logic:notEmpty>
														</logic:notEmpty>
													</td>
												</logic:equal>
												<!-- 学校用户 end-->
												<logic:equal name="info" property="kfsh" value="yes">
													<logic:equal name="yhlx" value="bz">
														<input type="hidden" name="mrf" value="${info.sqf }"/>
													</logic:equal>
													<logic:equal name="yhlx" value="bzr">
														<input type="hidden" name="mrf" value="${info.sqf }"/>
													</logic:equal>
													<logic:equal name="yhlx" value="xy">
														<input type="hidden" name="mrf" value="${info.bzrlrf }"/>
													</logic:equal>
													<logic:equal name="yhlx" value="xx">
														<input type="hidden" name="mrf" value="${info.xylrf }"/>
													</logic:equal>
												</logic:equal>
											</tr>
										</logic:iterate>
									</logic:equal>
									<!--6.社会实践  end-->
									
									<!--7.5s分  start-->
									<logic:equal name="xmdm" value="szyc_5sb">
										<logic:iterate name="infoList" id="info">
											<tr>
												<td>
													<logic:notEmpty name="info" property="id">
														<logic:equal name="info" property="kfsh" value="yes">
															<input type="checkbox" name="checkVal" value="${info.id }" />
														</logic:equal>
														<logic:equal name="info" property="kfsh" value="no">
															<input type="checkbox" name="checkVal" disabled="disabled" />
														</logic:equal>
													</logic:notEmpty>
												</td>
												<td title="${info.allfzxm }">
													<!-- 分值项目 -->
													${info.fzxm }
												</td>
												<td>
													<!-- 加减分 -->
													${info.jjf }
												</td>
												<td>
													<!-- 分值 -->
													${info.sqf }
												</td>
												<td>
													<!-- 日期 -->
													${info.jfrq }
												</td>
												<td title="${info.jfyy }">
													<!--原因 -->
													${info.yy }
												</td>
												
												<!-- 学院用户 -->
												<logic:equal name="yhlx" value="xy">
													<td>
														<!-- 学院审核分 -->
														<logic:notEmpty name="info" property="id">
															<logic:equal name="info" property="kfsh" value="yes">
																<input type="text" name="xyshf" 
																	id="xyshf_${info.id }" 
																	value="${info.xylrf }" 
																	onkeyup="checkInputNum(this)"
																	onblur="checkInputNum(this)"
																	maxlength="5" 
																	style="width : 50px;ime-mode:disabled;"/>
															</logic:equal>
															<logic:equal name="info" property="kfsh" value="no">
																${info.xylrf }
															</logic:equal>	
														</logic:notEmpty>	
													</td>
													<td>
														<!-- 审核状态 -->
														${info.xysh }
													</td>
												</logic:equal>
												<!-- 学院用户 end-->
												
												<!-- 学校用户 -->
												<logic:equal name="yhlx" value="xx">
													<td>
														<!-- 学院审核分 -->
														${info.xyshf }
													</td>
													<td>
														<!-- 学校审核分 -->
														<logic:notEmpty name="info" property="id">
															<logic:equal name="info" property="kfsh" value="yes">
																<input type="text" name="xxshf" 
																	id="xxshf_${info.id }" 
																	value="${info.xxlrf }" 
																	onkeyup="checkInputNum(this)"
																	onblur="checkInputNum(this)"
																	maxlength="5" 
																	style="width : 50px;ime-mode:disabled;"/>
															</logic:equal>
															<logic:equal name="info" property="kfsh" value="no">
																${info.xxlrf }
															</logic:equal>	
														</logic:notEmpty>	
													</td>
													<td>
														<!-- 审核状态 -->
														${info.xxsh }
													</td>
													<td>
														<!-- 申诉 -->
														<logic:notEmpty name="info" property="id">
															<!-- 是否有申诉 -->
															<logic:empty name="info" property="sssj">
																无
															</logic:empty>
															<logic:notEmpty name="info" property="sssj">
																<!-- 是否被处理 -->
																<logic:notEmpty name="info" property="clr">
																	<a href="#" onclick="showXsssDiv('${info.id }','${info.ssnr }','${info.sssj }','${info.clr }','${info.clyj }')">
																		<font color="blue">
																			已处理
																		</font>
																	</a>
																</logic:notEmpty>
																<logic:empty name="info" property="clr">
																	<a href="#" onclick="showXsssDiv('${info.id }','${info.ssnr }','${info.sssj }','','')">
																		<font color="blue">
																			有
																		</font>
																	</a>
																</logic:empty>
															</logic:notEmpty>
														</logic:notEmpty>
													</td>
												</logic:equal>
												<!-- 学校用户 end-->
		
											</tr>
											<logic:equal name="info" property="kfsh" value="yes">
												<logic:equal name="yhlx" value="xy">
													<input type="hidden" name="mrf" value="${info.sqf }"/>
												</logic:equal>
												<logic:equal name="yhlx" value="xx">
													<input type="hidden" name="mrf" value="${info.xylrf }"/>
												</logic:equal>
												</logic:equal>
											</logic:iterate>
												
											</logic:equal>
									<!--7.5s分  end-->
								</table>
							</div>
						</td>
					</tr>
				</tbody>
				<tfoot>
					<tr>
						<td colspan='4'>
							<div class="btn">
								<!-- 默认分设置 -->
								<button type="button" onclick="setMrf();">
									设置默认分
								</button>
								<!-- 保存 -->
								<button type="button" onclick="saveShf();">
									<bean:message key="lable.btn_bc_space" />
								</button>
								
								<button type="button" onclick="saveShzt('tg');return false;">
									通 过
								</button>
								
								<button type="button" onclick="saveShzt('btg');return false;">
									不通过
								</button>
								
								<!-- 非5S项目 -->
								<logic:notEqual name="xmdm" value="szyc_5sb">
									<logic:equal name="yhlx" value="xy">
										<button type="button" onclick="saveShzt('th')">
											退回
										</button>
									</logic:equal>
									<logic:equal name="yhlx" value="xx">
										<button type="button" onclick="saveShzt('th')">
											退回
										</button>
									</logic:equal>
								</logic:notEqual>
								
								<!-- 5S项目 -->
								<logic:equal name="xmdm" value="szyc_5sb">
									<logic:equal name="yhlx" value="xx">
										<button type="button" onclick="saveShzt('th')">
											退回
										</button>
									</logic:equal>
								</logic:equal>
								
								<button type="button" onclick="Close();window.dialogArguments.document.getElementById('search_go').click()">
									关 闭
								</button>
							</div>
						</td>
					</tr>
				</tfoot>
			</table>
			<!-- 学生基本信息 end-->
			
			<!-- 学生申诉弹出层 -->
			<div id="div_xsss" style="display:none">
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
									申诉内容
								</th>
								<td>
									<textarea id="ssnr" rows="5" cols="" 
										style="word-break:break-all;width:100%"></textarea>
								</td>
							</tr>
							<tr id="tr_clr">
								<th width="30%">
									申诉时间
								</th>
								<td>
									<p id="p_sssj"></p>
								</td>
							</tr>
							<tr id="tr_clr">
								<th width="30%">
									处理人
								</th>
								<td>
									<p id="p_clr"></p>
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
										<button type="button" id="btn_bc" onclick="saveSscl()">
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