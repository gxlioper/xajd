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
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script language="javascript" defer="defer">	
		//查询结果集
		function searchRs(){
	
			var url = "general_wdpj_lssb_ajax.do?method=getXmsbXsList";
			var xmdm = $("xmdm").value;
			var bjdm = $("bjdm").value;
			
			var search_condition = jQuery("#search_condition").val();
			var arrange = jQuery("#arrange").val();
			var fashion = jQuery("#fashion").val();
			
			if(arrange == ""){
				arrange = "no";
			}
			
			var otherValue = [xmdm,bjdm,arrange,fashion,search_condition];

			jQuery("#div_rs").html("");
			searchRsByAjax(url,otherValue);
		}		

		//保存相关分数
		function saveXmsb(tag){

			if(tag=='ok'){
				var xmdm = $("xmdm").value;
				var num =  document.getElementsByName("checkVal").length;
				
				var xh = new Array();
				var n=0;
				
				for(var i=0;i<num;i++){
					var obj = document.getElementsByName("checkVal")[i];
					if(obj.checked && obj.disabled != true){
						xh[n] = obj.value;
						n++;
					}
				}
				
				if(checkPjtj(xh,xmdm)){
				
					var url="general_wdpj_lssb_ajax.do?method=saveXmsb";
	
					//参数
				 	var parameter = {
				 		"xmdm":xmdm,
				 		"xh":xh.join(" !!@@!! ")
					};
		
					$("divWaiting").style.display="";
					$("divDisable").style.display="";
						
					jQuery.post(url,parameter,function(result){
						ymPrompt.alert({message:result,winPos:"t"});
						searchRs();
						$("divWaiting").style.display="none";
						$("divDisable").style.display="none";
					});
				}
			}
		}
		
		//执行上报
		function doSave(){
		
			var xh = new Array();
			var num =  document.getElementsByName("checkVal").length;
			
			var n=0;
				
			for(var i=0;i<num;i++){
				var obj = document.getElementsByName("checkVal")[i];
				if(obj.checked && obj.disabled != true){
					xh[n] = obj.value;
					n++;
				}
			}
				
			if(n==0){
				ymPrompt.errorInfo({message:"请至少勾选一名希望上报的学生",winPos:"t"});
				return false;
			}
			
			ymPrompt.confirmInfo({message:"请确认是否上报您所勾选的学生?",winPos:"t",title:"确认内容",handler:saveXmsb});
		}

		//显示成绩信息
		function showCjInfo(xh){
			jQuery("#hidden_xh").val(xh);
			defaultXscj();
			tipsWindown("成绩查看","id:div_xscj","350","350","true","","true","id");
		}

		//初始化学生成绩
		function defaultXscj(){
			
			jQuery.ajaxSetup({async:false});

			//学号
			var xh = jQuery("#hidden_xh").val();
			//路径
			var url = "pjpyXmsb.do?method=defaultXscj";
			//参数
		 	var parameter = {
				"xh":xh
			};
			
			$("divWaiting").style.display="";
			$("divDisable").style.display="";

			jQuery("#div_xscj_info").load(
				url,
				parameter,
				function(){
					$("divWaiting").style.display="none";
					$("divDisable").style.display="none";
				}
			);

			jQuery.ajaxSetup({async:true});
		}

		//过滤学生（根据条件）
		function searchXsByTj(){
			var search_condition = jQuery("#search_condition").val();
			if(search_condition == "yes"){
				jQuery("#search_condition").val("no");
				jQuery("#btn_cx").html("过滤学生(符合条件)");
				searchRs();
			}else{
				confirmInfo("将要根据评奖条件过滤出符合条件的学生，可能会执行较长时间，请问是否继续?，",doSearchXsByTj);	
			}	
		}

		//执行过滤
		function doSearchXsByTj(tag){
			if(tag == "ok"){
				jQuery("#search_condition").val("yes");
				jQuery("#btn_cx").html("取消过滤");
				searchRs();
			}
		}
		
		function checkPjtj(xhArr,xmdm){
			
			var parameter={}
			
			parameter["array_xh"]=xhArr.join(" !!@@!! ");
			
			parameter["str_xmdm"]=xmdm;
			
			//保存URL
			jQuery.ajaxSetup({async:false});
			
			var url = "general_xmsz_pjtj_ajax.do?method=checkPjtj";
			
			var flag=false;
			
			//------------条件判断 begin -------------
			jQuery.ajaxSetup({async:false});
				jQuery.post(url,
				parameter,
				function(result){
					
					$("divWaiting").style.display="none";
					$("divDisable").style.display="none";
					
					if(result!=""){
						alertInfo(result);
						flag=false;
					}else{
						flag=true;
					}
					
				}
			);
			jQuery.ajaxSetup({async:true});
			
			return  flag;
		}
		</script>
	</head>
	<body onload="searchRs()" >
	
		<!-- 标题 -->
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>评奖评优 - 我的评奖 - 老师上报</a>
			</p>
			<p class="help">
				<a href="#" onclick="return false;" onmousedown ="showHelpDiv();">使用帮助</a>
			</p>
			
			<!-- 相关功能 -->
			<p class="other" style="position:relative;">
				<a href="#" onclick="return false;" 
					onmouseover ="displayDiv(['liucheng_bar','liucheng_bar'],'')"
					style="display:block;height:30px;">相关功能</a>
			</p>
			<!-- 相关功能 end-->
			
		</div>
		<!-- 标题 end-->
		
		<!-- 快捷方式 -->
		<div class="liucheng_xg_pj" id="div_other_gnmk"
			onmouseover="displayDiv(['liucheng_bar','liucheng_bar'],'')"
			onmouseout="displayDiv(['liucheng_bar','liucheng_bar'],'none')">
		
			<div class="liucheng_bar" id="liucheng_bar" style="position:absolute;top:30px;right:0px;width:auto;background:#fff;display:none;">
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
						<a href="#" onclick="goOtherGnmk('pjpy_pjlc_jgcx.do');return false;">
				    		<img src="<%=stylePath%>/images/blue/48-1/Function12.png" />
							<p>结果查询</p>
						</a>
					</div>
					
				</div>
			</div>
		</div>
		<!-- 快捷方式 end-->
		
		<!-- 提示信息 end-->
		<div class="prompt" id="div_help" style="display: none">
			<h3>
				<span>提示：</span>
			</h3>
			<p>
				1.学生显示顺序根据综合素质<font color="blue">班级排名</font>，顺序排列。</br>
				2.可以执行<font color="blue">过滤学生</font>，来过滤掉不符合条件的学生。</br>
				3.如果评奖条件设置过多，可能导致过滤速度较慢，请耐心等待。
			</p>
			<a class="close" title="隐藏"  onclick="this.parentNode.style.display='none';"></a>
		</div>
		<!-- 提示信息 end-->		
		
		<html:form action="/pjpyXmsb">
		
			<!-- 隐藏域 -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<input type="hidden" name="bjdm" id="bjdm" value="${bjdm }"/>
			<input type="hidden" name="xmdm" id="xmdm" value="${xmdm }" />
			<input type="hidden" name="hidden_xh" id="hidden_xh"/>
			<input type="hidden" name="search_condition" id="search_condition" value="no"/>
			<button type="button" class="btn_cx" id="search_go" onclick="searchRs();return false;" style="display: none"> 查 询 </button>
			<!-- 隐藏域 end-->
			
			<!-- 多功能操作区 -->
			<div class="toolbox">
			
				<!-- 按钮 -->
				<div class="buttonbox">
					<ul>
						<li>
							<a href="#" id="btn_ccg"
								onclick="doSave();return false;"
								class="btn_ccg">
								确定上报
							</a>
						</li>
						<li>
							<a href="#" id="btn_down"
								onclick="showTopWin('/xgxt/general_pjpy.do?method=lssbFwChoose',600,550);return false;"
								class="btn_down">
								更改上报项目
							</a>
						</li>
						<li>
							<a href="#" id="btn_cx"
								onclick="searchXsByTj();return false;"
								class="btn_cx">
								过滤学生(符合条件)
							</a>
						</li>	
					</ul>
				</div>
				<!-- 按钮 end-->
							
				<!-- 过滤条件 -->
				<div style="display:none">
					<%@ include file="/comm/search/superSearchArea.jsp"%>
				</div>
				<!-- 过滤条件 end-->
				
			</div>
			<!-- 多功能操作区 end-->
			
			<!-- 内容显示区开始 -->
			<div class="main_box">
				<table align="center" width="100%">
					<tr style="">
						<td width="100%" valign="top" style="">
							<div id="div_rs" style="">
							</div>
						</td>
					</tr>
				</table>
			</div>
			<!-- 内容显示区开始 end-->	
			
			<!-- 成绩显示弹出层 -->
			<div id="div_xscj" style="display:none">
				<div class="open_win01">
					<table align="center" class="formlist">
						<thead>
							<tr>
								<th colspan="2">
									<span>${pjxn }学年成绩</span>
								</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td colspan="2" width="">
									<div id="div_xscj_info" style="width:100%;height:230px;overflow-x:hidden;overflow-y:auto;">
									
									</div>
								</td>
							</tr>
						</tbody>
						<tfoot>
							<tr>
								<td colspan="2">
									<div class="bz">
										
									</div>
									<div class="btn">								
										<button type="button" onclick="closeWindown();return false;">
											关  闭
										</button>
									</div>
								</td>
							</tr>
						</tfoot>
					</table>
				</div>
			</div>
			<!-- 成绩显示弹出层 end-->
			
			<!-- 提示信息 -->
			<%@ include file="/comm/other/tsxx.jsp"%>
		</html:form>
	</body>
</html>