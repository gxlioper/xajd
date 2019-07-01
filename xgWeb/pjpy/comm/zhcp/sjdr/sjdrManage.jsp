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
			var url = "zhcpSjdr.do?method=getZhcpInfoList";
			var czxm = $("czxm").value;
			var ie = "10.0";
			var lyb = $("lyb").value;
			
			var otherValue = [czxm,ie,lyb];

			showWaitingDiv("1000");
			searchRsByAjax(url,otherValue);
		}
		
		//保存相关分数
		function saveZhcpf(tag){

			if(tag=='ok'){
				var czxm = $("czxm").value;
				var xm_name = "czxm_"+czxm;
				var num =  document.getElementsByName(xm_name).length;
				
				var pk = new Array();
				var fs = new Array();
				var n=0;
				
				for(var i=0;i<num;i++){
					var obj = document.getElementsByName(xm_name)[i];
					fs[n] = obj.value;
					pk[n]=escape(obj.id.replace(xm_name+"_",""));
					n++;
				}
				
				var url="zhcpSjdr.do?method=saveZhcpf";
				
				//参数
			 	var parameter = {
			 		"czxm":czxm,
			 		"pk":pk.join("!!@@!!"),
					"fs":fs.join("!!@@!!")
				};
	
				jQuery.post(url,parameter,function(result){alertInfo(result);$("had_edit").value="no"});
			}
		}
		
		//控制项目操作
		function ctrlXmcz(lyb){
			$("lyb").value = lyb;
			if(lyb == ""){
				$("lyb").value = "noLyb";
			}
			if(lyb!=""){
				$("btn_ccg").style.display="none";
				$("btn_down").style.display="none";
				$("btn_dr").style.display="none";
				$("btn_sx").style.display="";			
			}else{
				$("btn_ccg").style.display="";
				$("btn_down").style.display="";
				$("btn_dr").style.display="";
				$("btn_sx").style.display="none";
			}
		}
		
		//询问信息回调内容(同步)
		function tbZhcpf(tag){

			if(tag=='ok'){
			
				var url="zhcpSjdr.do?method=tbZhcpf";
				var lyb=$("lyb").value;
				var czxm=$("czxm").value;
				
				//参数
			 	var parameter = {
			 		"czxm":czxm,
			 		"lyb":lyb
				};
	
				jQuery.post(url,parameter,function(result){tbResult(result);});
			}
		}
		
		//同步结果
		function tbResult(result){
			if(result == "执行成功"){
				alertInfo(result);
				searchRs();
			}else{
				alertError(result);
			}
		}
		
		//显示模板下载页面
		function showExpInfo(){
			var url = "/xgxt/zhcpSjdr.do?method=sjdrExp";
				url+= "&czxm="+$("czxm").value;
				url+= "&lyb="+$("lyb").value;
				
			showOpenWindow(url,700,550);
		}
		
		//检查是否修改
		function saveMethod(){
			confirmInfo('请问是否保存您所修改的内容?',saveZhcpf);
		}
		
		function onShow(){
			var id = "left_a_0";
			if($(id)){
				$(id).click();
			}
		}
		</script>
	</head>
	<body onload="onShow()" >
	
		<!-- 标题 -->
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>${title }</a>
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
		
		<!-- 提示信息 end-->
		<div class="prompt" id="div_help" style="display: none">
			<h3>
				<span>提示：</span>
			</h3>
			<p>
				录入分数(限<font color="blue">数字</font>)完成后请执行<font color="blue">保存</font>操作，
				导入数据请使用<font color="blue">模板下载</font>所提供的excel模板处理数据。
			</p>
			<a class="close" title="隐藏"  onclick="this.parentNode.style.display='none';"></a>
		</div>
		<!-- 提示信息 end-->		
		
		<!-- 快捷方式 -->
		<div class="liucheng_xg_pj" id="div_other_gnmk" style=""
			onmouseover="displayDiv(['liucheng_bar','liucheng_bar'],'')"
			onmouseout="displayDiv(['liucheng_bar','liucheng_bar'],'none')">
			<div class="liucheng_bar" id="liucheng_bar" style="position:absolute;top:30px;right:0px;width:auto;background:#fff;display:none">
				<div class="con">
				
					<div class="liucheng_font floatleft">
				    	<a href="#" onclick="goOtherGnmk('pjpy_zhsz.do');return false;">
				    		<img src="<%=stylePath%>/images/blue/48-1/Function19.png" />
							<p>评奖基本设置</p>
						</a>   	
					</div>
					
					<div class="liucheng_font floatleft">
				    	<a href="#" onclick="goOtherGnmk('zhcp_zczf_zccx.do');return false;">
				    		<img src="<%=stylePath%>/images/blue/48-1/Function30.png" />
							<p>综测分查询(计算)</p>
						</a>   	
					</div>
					
				</div>
			</div>
		</div>
		<!-- 快捷方式 end-->
		
		<html:form action="/zhcpSjdr" enctype='multipart/form-data' method="post">
		
			<!-- 隐藏域 -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<input type="hidden" name="czxm" id="shxm" value="${czxm }"/>
			<input type="hidden" name="lyb" id="lyb" value="noLyb"/>
			<input type="hidden" id="had_edit" value="no"/>
				
			<button type="button" id="createTj" onclick="setSearchTj()" style="display:none">生成过滤条件</button>
			<!-- 隐藏域 end-->
			
			<!-- 多功能操作区 -->
			<div class="toolbox">
			
				<!-- 按钮 -->
				<div class="buttonbox">
					<ul>
						<li>
							<a href="#" id="btn_ccg"
								onclick="confirmInfo('请确认是否要执行保存操作?',saveZhcpf);return false;"
								class="btn_ccg">
								保存
							</a>
						</li>
						<li>
							<a href="#" id="btn_down"
								onclick="showExpInfo();return false;"
								class="btn_down">
								模板下载
							</a>
						</li>
						<li>
							<a href="#" id="btn_dr"
								onclick="showTopWin('/xgxt/zhcpSjdr.do?method=sjdrImp',500,250);return false;"
								class="btn_dr">
								导入
							</a>
						</li>
						<li>
							<a href="#" id="btn_sx" style="display:none"
								onclick="confirmInfo('请您确认是否需要执行同步数据操作?注：将会覆盖已有数据，请谨慎操作。',tbZhcpf);return false;"
								class="btn_sx">
								同步数据
							</a>
						</li>
						
					</ul>
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
				<table width="100%" align="left">
					<tr>
						<td width="20%" valign="top" style="overflow-x: auto;">
							<!-- 侧边栏 -->
							<div class="listbox">	
								<div class="titlelist" style="height: 352px;">
									<ul id="left_ul">
										<logic:notEmpty name="cshXmList">
											<logic:iterate id="xmnr" name="cshXmList" indexId="index">
												<logic:equal name="xmnr" property="xmdm" value="${czxm}">
													<li id="li_${index}" class="Child" style="background:#dce8f8">
														<a href="#" name="left_a" id="left_a_${index}" 
															onclick="if(checkHadEdit()){setLiClick('${index}');ctrlXmcz('${xmnr.lyb}');searchRs()};return false;">
															<span class="ico"></span>${xmnr.xmmc}
														</a>
														<input type="hidden" id="xmdm_${index }" value="${xmnr.xmdm}"/>
													</li>
												</logic:equal>
												<logic:notEqual name="xmnr" property="xmdm" value="${czxm}">
													<li id="li_${index}" class="Child">
														<a href="#" name="left_a" id="left_a_${index}" 
															onclick="if(checkHadEdit()){setLiClick('${index}');ctrlXmcz('${xmnr.lyb}');searchRs()};return false;">
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
							<div id="div_rs" style="width:650px;height:376px;overflow-x:auto;overflow-y:auto;">
							</div>
						</td>
					</tr>
				</table>
				<!--分页显示-->
				<div style="clear:both;">
					<jsp:include flush="true" page="/sjcz/turnpage.jsp?form=zhcpSjdrForm"></jsp:include>
				</div>
				<!--分页显示-->
			</div>
			<!-- 内容显示区开始 end-->	
			
			<!-- 模板导出Div-->
			<div id="div_mbdc" style="display: none">
				<table class="formlist">
					<thead>
						<tr>
							<td colspan="2">
						
							</td>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th>
								导出形式
							</th>
							<td>
								<input type="radio" name="rad_dcxs" value="xy" onclick="$('dcxs').value=this.value"/><bean:message key="lable.xsgzyxpzxy" />
								<input type="radio" name="rad_dcxs" value="bj" checked="checked" onclick="$('dcxs').value=this.value"/>班级
								<input type="hidden" name="dcxs" id="dcxs" value="bj"/>
							</td>
						</tr>
						<tr>
							<th>
								路径选择
							</th>
							<td>
								<input type="text" name="filePath" id="filePath" readonly="readonly" style="width:400px"/>
								&nbsp;&nbsp;
								<button type="button" onclick="browseFolder(filePath)">选 择</button>
							</td>
						</tr>
						<tr>
							<th>
								说明
							</th>
							<td height="250px">
								根据前页面所选择的过滤条件输出模板，如果未选择条件的话将导出全校所有班级的信息，会严重影响
								导出效率，建议至少选择一项条件。<br/>
								导出文件以班级位单位（Excel形式）存在于你所选择的保存位置。
							</td>
						</tr>
					</tbody>
					<tfoot>
						<tr>
							<td colspan="2">
								<div class="btn">
									<!-- 确定 -->
									<button type="button" onclick="showDcInfo()">
										确 定
									</button>
									
									<!-- 关闭 -->
									<button type="button" onclick="hiddenMessage(true,true);return false;">
										关 闭
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
				</table>
			</div>
			<!-- 模板导出Div end-->
			
			<!-- 提示信息 -->
			<%@ include file="/comm/other/tsxx.jsp"%>
		</html:form>
	</body>
</html>