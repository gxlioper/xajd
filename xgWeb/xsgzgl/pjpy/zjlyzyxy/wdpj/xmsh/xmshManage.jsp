<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- 作者：伟大的骆 -->
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
	    <script language="javascript" src="js/check.js"></script>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script language="javascript" src="js/xsgzgl/pjpy/pjpyComm.js"></script>
		<script language="javascript" src="js/xsgzgl/xtwh/commUtil.js"></script>
		<script language="javascript" defer="defer">
		//页面初始化
		function onShow(){
			var id = "left_a_0";
			
			var xmdm=$("xmdm").value;
			
			var first=$("first").value;
			// 判断是否是从我的审核模块跳转至此
			if(first!=""){
				// 将标志位制空
				$("first").value="";
				
				// 判断需要选中的项目
				var xmdmArr=jQuery("input[name=xmdmArr]").each(function(i){
					var xmdmVal=jQuery(this).val();
					
					if(xmdmVal==xmdm){
						
						// 选中后设置颜色
						setLiClick(i);
					}
				});

				// 查询
				searchRs();
				
			}else{// 非跳转至此（在审核模块中切换项目）
				// 未选中
				if($(id) && xmdm==""){
					$(id).click();
				}else if(xmdm!=""){// 有选中项目
					
					var xmdmArr=jQuery("input[name=xmdmArr]").each(function(i){
						var xmdmVal=jQuery(this).val();
						if(xmdmVal==xmdm){
							$("left_a_"+i).click();
						}
					});
					
					searchRs();
				}
			}
			
		}

		//查询结果集
		function searchRs(){
			
			jQuery.ajaxSetup({async:false});
			
			var url = "general_wdpj_xmsh_ajax.do?method=searchWdpjXmsh";
			var ie = "ie";// ie版本
			var v4Path = stylePath;//v4样式路径
			var xmdm = $("xmdm").value;//项目代码
			var spgw = $("spgw").value;//审批岗位
			// 需要传入后台的其它数据
			var otherValue = [ie,v4Path,xmdm,spgw];

			// loding
			$("divWaiting").style.display="";
			$("divDisable").style.display="";
			
			// 查询操作
			searchRsByAjax(url,otherValue);

			// 隐藏 loding
			$("divWaiting").style.display="none";
			$("divDisable").style.display="none";
			
			// 判断是否是第一级审核岗位(控制退回按钮读写权)
			if($("hid_firstSpgw")){
			
				var div_firstSpgw=$("hid_firstSpgw").value;
				// 第一级审核不允许退回
				if(div_firstSpgw=="true"){
					$("btn_th").style.display="none";
				}else if(div_firstSpgw=="false"){
					$("btn_th").style.display="";
				}
			}
			
			// 审核时间、开关控制
			if($("shkz")){
				
				if($("shkz").value=="true"){
					// 取消禁用审核按钮
					$("btn_sh").disabled=false;
					
				}else{
					// 禁用按钮
					$("btn_sh").disabled=true;
				
				}
			}
			
			jQuery.ajaxSetup({async:true});
			
			if($("span_note")){
				$("span_note").innerHTML = "查询结果&nbsp;&nbsp;【注：点击学号可查看该学生的详细信息】";
			}
		}

		//评奖项目审核(单个审核、批量审核)
		function showShxxDiv(){
			var pk = jQuery("input[name=div_pkValue]:checked").eq(0).val();
			var len = jQuery("input[name=div_pkValue]:checked").length;
			if(len==1){// 单个审核
				showWdpjXmsh(pk);
			}else if(len>1){// 批量审核
				tipsWindown("系统提示","id:div_002","500","300","true","","true","id");
			}else{
				alertError("请<font color='blue'>勾选</font>需要审核的记录！");
				return false;
			}
		}

		//显示岗位信息Div
		function showGwxxDiv(){
			
			var xmdm=$("xmdm").value;
			// 根据项目代码弹出审批岗位
			showSpgw(xmdm);
			
		}
		
		

		//前往项目审核
		function showSpgw(xmdm){
		
			var url="general_wdpj_xmsh_ajax.do?method=showShgwDiv";
			
			//其他数据
		 	var parameter = {
				"xmdm":xmdm
			};
		  	
			jQuery("#div_spgw").load(url,parameter,function(){
			
				var len=jQuery("[name=spgw][type=radio]").length;
		
				if(len==1){
					$("btn_sx").disabled=true;
					var spgw=jQuery("[name=spgw]:checked").val();
					jQuery("#xmdm").val(xmdm);
					searchRs();
					
				}else if(len>0 ){
				    $("btn_sx").disabled=false;
					tipsWindown("系统提示","id:div_spgw","300","170","true","","true","id");
				}
			});
		}
		
		function checkSpgw(){
		
			$("xmdm").value=jQuery("#text_xmdm").val();
			$("spgw").value=jQuery("[name=spgw]:checked").val();
			
			czSearch();
			
			jQuery("#tj_shzt_需重审").click();
			jQuery("#tj_shzt_未审核").click();
			
			closeWindown();
			
			searchRs();
			
		}
		
		function saveShzt(shzt){

			jQuery.ajaxSetup({async:false});
			
			var xmdm = $("xmdm").value;
			var spgw = $("spgw").value;
		
			confirmInfo("是否要保存已修改的数据？",function(tag){
				
				if(tag=="ok"){
				
					closeWindown();
					
					//主键
					var xh=new Array();
					
					jQuery("[name=div_pkValue]:checked").each(function(i){
						xh[i]=jQuery(this).val();
					});
					
					jQuery.ajaxSetup({async:false});
					
					// 得到JSON对象
			        var parameter ={};
					
					parameter["str_xmdm"]=xmdm;
					
					parameter["str_spgw"]=spgw;
					
					parameter["array_xh"]=xh.join("!!array!!");
					
					parameter["str_shzt"]=shzt;
					
					parameter["str_shyj"]=escape(jQuery("#shyj").val());
					
					var url = "general_wdpj_xmsh_ajax.do?method=savePlShzt";
		          	
				 	$("divWaiting").style.display="";
					$("divDisable").style.display="";
					
					jQuery.post(url,parameter,
						function(result){
							$("divWaiting").style.display="none";
							$("divDisable").style.display="none";
							
							alertInfo(result,function(tag){
								
								if(tag=="ok"){
								
									
									searchRs();
								}
								
							});
							
						}
					);
					
					jQuery.ajaxSetup({async:true});
				}
				
			});
			
		}
		
		
		//重置过滤条件
		function czSearch(){

		
			//重置模糊查询
			if($("input_mhcx")){
				$("input_mhcx").value = "";
			}
			
			//重置高级查询
			$("yxtj_div").style.display = "none";
			$("yxtj_dl").innerHTML = "";
			if($("yxtj_gxh_div")){
				$("yxtj_gxh_div").style.display  = "none";
				$("yxtj_gxh_dl").innerHTML = "";
			}
			
			var a_num = document.getElementsByTagName('a').length;
	
			for(var i=0;i<a_num;i++){
				
				var a_className = document.getElementsByTagName('a')[i].className;
				
				if(a_className == "selectedValue"){
					document.getElementsByTagName('a')[i].className = "";
				}
			}
			
			var kssj_count = document.getElementsByName("searchModel.search_tj_kssj").length;
			var jssj_count = document.getElementsByName("searchModel.search_tj_jssj").length;
			
			//开始时间
			for(var i=0;i<kssj_count;i++){
				var obj = document.getElementsByName("searchModel.search_tj_kssj")[i];
				obj.value = "";
			}
			
			//结束时间
			for(var i=0;i<jssj_count;i++){
				var obj = document.getElementsByName("searchModel.search_tj_jssj")[i];
				obj.value = "";
			}
		}
		
		function checkItsDis(obj){
			
			if(obj.disabled){
				
				return false;
			}else{
				
				return true;
			}		
		}
		
		jQuery(function(){
			onShow();
		})
		</script>
	</head>
	<body  ondrag="return false">
	
		<!-- 标题 -->
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>评奖评优 - 我的评奖 - 项目审核</a>
			</p>
			<p class="help">
				<a href="#" onclick="return false;" onmousedown ="showHelpDiv();">使用帮助</a>
			</p>
		</div>
		<!-- 标题 end-->
		
		<!-- 提示信息 end-->
		<div class="prompt"  id="div_help" style="display: none">
			<h3>
				<span>提示：</span>
			</h3>
			<p>
				<span>
				1.左侧展示的项目，为您<font color="blue">有资格审核</font>的所有项目，其中<font color="blue">灰色</font>的项目为关闭审核或者是当前时间不在审核时间内的项目。<br/>
				2.如果您是一人多岗的情况，请点击<font color="blue">切换岗位按钮</font>进行身份切换。<br/>
				3.系统默认查询的是<font color="blue">未审核</font>和<font color="blue">需重审</font>，查询出来的记录数需要与我的评奖中的需审核人数一致。<br/>
				4.勾选<font color="blue">一条</font>记录，点击<font color="blue">审核</font>按钮，用户在弹出页面中做弹跳审核。<br/>
				5.勾选<font color="blue">多条</font>记录，点击<font color="blue">审核</font>按钮，执行批量审核。<br/>
				6.前一级用户审核<font color="blue">通过</font>后，后一级别才可以进行审核。<br/>
				7.后一级别如果已经审核过了，前一级别<font color="blue">不可</font>再修改。<br/>
				8.如果后级别审核<font color="blue">退回</font>的话，前一级别需要<font color="blue">重新审核</font>。<br/>
				</span>
			</p>
			<a class="close" title="隐藏"  onclick="this.parentNode.style.display='none';"></a>
		</div>
		<!-- 提示信息 end-->
		
		<html:form action="/general_pjpy" method="post">
		
			<!-- 隐藏域 -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<input type="hidden" name="czxm" id="shxm" value="${xmdm}"/>
			<input type="hidden" name="spgw" id="spgw" value="${spgw }"/>
			<input type="hidden" name="xmdm" id="xmdm" value="${xmdm }"/>
			<input type="hidden" name="shjb" id="shjb" value="${shjb }"/>
			<input type="hidden" name="first" id="first" value="${first }"/>
			
			<!-- 多功能操作区 -->
			<div class="toolbox">
			
				<!-- 按钮 -->
				<div class="buttonbox">
					<ul>
						<li>
							<a href="#" onclick="goPjpyWdpj();return false;" class="btn_fh">
								返回我的评奖
							</a>
						</li>
						<li>
							<a href="#" id="btn_sh" disabled="true"
								onclick="if(checkItsDis(this)){showShxxDiv();};return false;" 
								class="btn_sh">
								<span>审核</span>
							</a>
						</li>
						<li>
							<a href="#" id="btn_sx" disabled="true"
								onclick="if(checkItsDis(this)){showGwxxDiv();};return false;"
								class="btn_sx">
								<span>切换审核岗位</span>
							</a>
						</li>
					</ul>
					<button type="button" class="btn_cx" id="search_go" style="display: none" onclick="searchRs();return false;"></button>
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
<!--					<tr>-->
<!--						<td colspan="2">-->
<!--							<h3 class="datetitle_01">-->
<!--								<span>-->
<!--									-->
<!--								</span>-->
<!--							</h3>-->
<!--						</td>-->
<!--					</tr>-->
					<tr>
						<td width="20%" valign="top" style="overflow-x: auto;">
							<!-- 侧边栏 -->
							<div class="listbox">	
								<div class="titlelist" style="height: 372px;" style="overflow-y: auto;overflow-x:hidden">
									<ul id="left_ul">
										<logic:notEmpty name="cshXmList">
											<logic:iterate id="xmnr" name="cshXmList" indexId="index">	
												<li id="li_${index}" class="Child">
													<logic:equal name="xmnr" property="shkz" value="yes">
													<a href="#" name="left_a" id="left_a_${index}"  title="${xmnr.xmmcxx}"
														onclick="setLiClick('${index}');showSpgw('${xmnr.xmdm}');return false;">
														<span class="ico"></span>${xmnr.xmmc}
													</a>
													</logic:equal>
													<logic:equal name="xmnr" property="shkz" value="no">
													<a href="#" name="left_a" id="left_a_${index}" title="${xmnr.xmmcxx}"
														onclick="setLiClick('${index}');showSpgw('${xmnr.xmdm}');return false;">
														<span class="ico"></span><font style="color: gray;">${xmnr.xmmc}</font>
													</a>
													</logic:equal>
													<input type="hidden" name="xmdmArr" id="xmdm_${index }" value="${xmnr.xmdm}"/>
													<input type="hidden" id="xmmc_${xmnr.xmdm}" value="${xmnr.xmmc}"/>
												</li>
											</logic:iterate>
										</logic:notEmpty>
									</ul>
								</div>
							</div>
							<!-- 侧边栏 end-->
						</td>
						<td width="85%" valign="top" style="position: relative;">
							<div id="div_rs" style="width:100%;height:400px;overflow-x:auto;overflow-y:auto;">
							</div>
						</td>
					</tr>
				</table>
				<!--分页显示-->
				<jsp:include flush="true" page="/sjcz/turnpage.jsp?form=generalPjpyGeneralForm"></jsp:include>
				<!--分页显示-->
			</div>
			<!-- 内容显示区 end-->
				
			<!-- 002 begin -->
			<div id="div_002" style="display:none">
				<div class="open_win01">
					<table width="100%" border="0" class="formlist">
						<thead>
							<tr>
								<th colspan="2">
									<span>项目批量审核</span>
								</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<th width="25%">
									审核意见
								</th>
								<td>
									<textarea rows="5" name="shyj" id="shyj" cols="" style="width:400px"></textarea>
								</td>
							</tr>
						</tbody>
						<tfoot>
							<tr>
								<td colspan="2">
									<div class="btn">
										<button type="button" onclick="saveShzt('tg');return false;">通过</button>
										<button type="button" onclick="saveShzt('btg');return false;">不通过</button>
										
										<button type="button" id="btn_th" onclick="saveShzt('th')">退回</button>
										
										<button type="button" onclick="closeWindown();return false;">关闭</button>
									</div>
								</td>
							</tr>
						</tfoot>
					</table>
				</div>
			</div>
			<!-- 002 end-->
			
			<div id="div_spgw" style="display:none">

			</div>
			
			<!-- 提示信息 -->
			<%@ include file="/comm/other/tsxx.jsp"%>
		</html:form>
	</body>
</html>