<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- 作者：伟大的骆 -->
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
	    <script language="javascript" src="js/check.js"></script>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script language="javascript" defer="defer">
		//初始化
		function onShow(){ 
			$("bc").style.display = "none";
			$("divWaiting").style.display="";
			$("divDisable").style.display="";
			
			setTimeout('searchRs()','2000');

		}

		//点击编辑
		function changeEdit(){
			$("edit").value="yes";
			$("bj").style.display = "none";
			$("wh").style.display = "none";
			$("dc").style.display = "none";
			$("dr").style.display = "none";
			$("bc").style.display = "";
			searchRs();
			}

		function changeIsEdit(){
			$("had_edit").value="yes";
		}
		
		function addYjtf(obj){
			var id = obj.id;
			var arr = id.split("_");
			var sjtfId = arr[0]+'_sjtf';
			var sjtf = $(sjtfId).value;
			var yjtf = obj.value;
			var qfId = arr[0]+'_qf';
			if(null==yjtf||yjtf==""){
				obj.value=0;
				yjtf=0;
				}
			$(qfId).value=parseFloat(yjtf)-parseFloat(sjtf);
		}

		function addSjtf(obj){
			var id = obj.id;
			var arr = id.split("_");
			var yjtfId = arr[0]+'_yjtf';
			var yjtf = $(yjtfId).value;
			var sjtf = obj.value;
			var qfId = arr[0]+'_qf';
			if(null==sjtf||sjtf==""){
				obj.value=0;
				sjtf=0;
				}
			$(qfId).value=parseFloat(yjtf)-parseFloat(sjtf);
		}

		//执行查询操作
		function searchRs(){
			jQuery.ajaxSetup({async:false});
			var flag = true;
			var xn_num =  jQuery("a[name=a_name_xn]").length;
			var first= $("first").value;
			
			var url = "general_dtjs_tyjf_ajax.do?method=searchTyjfResult";
			var ie = "10.0";

			var edit=$("edit").value;
			var otherValue = [ie,edit];

			$("divWaiting").style.display="";
			$("divDisable").style.display="";

			if(xn_num==1&&flag){
				searchRsByAjax(url,otherValue);
				$("first").value="yes";
				$("had_edit").value="no";
			}else if(first=="yes"&&flag){
				alertInfo("必须且只能选择一个学年！");
			}
			
			$("divWaiting").style.display="none";
			$("divDisable").style.display="none";
			
			jQuery.ajaxSetup({async:true});
		}

		//显示团员缴费保存Div
		function showTyjfDiv(){
			var num = jQuery("input[type=checkbox][name=primarykey_checkVal]:checked").length;
			var flag = true;
			
			if(num == "0"){
				alertError("请勾选希望保存的团员记录");
				flag = false;
			}

			if(flag){
				tipsWindown("系统提示","id:div_tyjf","400","250","true","","true","id");
			}		
		}

		//验证保存团员缴费
		function checkSaveTyjf(){
			//应缴团费
			var yjtf = jQuery("#yjtf").val();		  		
			//实缴团费
			var sjtf = jQuery("#sjtf").val();
			
			var flag = true;

			if(parseFloat(sjtf)>parseFloat(yjtf)){
				alertError("实缴团费不能大于应缴团费，请确认");
				flag = false;
				}

			if(yjtf == ""){
				alertError("应缴团费不能为空，请确认^_^");
				flag = false;
			}

			if(flag){
				confirmInfo("将要保存您所勾选记录的应缴金额与实缴金额，请确认",saveTyjf);
			}
		}

		//保存团员缴费
		function saveTyjf(tag){
			if(tag == "ok"){
				var objs = jQuery("input[type=checkbox][name=primarykey_checkVal]:checked");
				var RowsStr="";
				if(objs.length>0){
					for (i=0; i<objs.length; i++){
				     RowsStr+=objs[i].value+",";
					}
				}
				//应缴团费
				var yjtf = jQuery("#yjtf").val();		  		
				//实缴团费
				var sjtf = jQuery("#sjtf").val();
				//学号
				var xh = new Array();
				//学年
				var xn = new Array();
				
				jQuery.ajaxSetup({async:false});
				
				var url = "general_dtjs_tyjf_ajax.do?method=saveTyjf";
				
				//参数
			 	var parameter = {
			 		"str_yjtf":yjtf,
			 		"str_sjtf":sjtf,
			 		"str_xh":RowsStr,
			 		"str_xn":xn.join("!!@@!!")
				};
				
			 	$("divWaiting").style.display="";
				$("divDisable").style.display="";
				
				jQuery.post(url,
					parameter,
					function(result){
						$("divWaiting").style.display="none";
						$("divDisable").style.display="none";
						alertInfo(result);
						closeWindown();
						searchRs();
					}
				);
				
				jQuery.ajaxSetup({async:true});
			}
		}

		//验证编辑团员缴费
		function CheckSaveBjTyjf(){
			var qf = document.getElementsByName("qf");
			var flag = true;
			var xhs ="";
			for(var i = 0;i<qf.length;i++){
				var qfId="";
				var fy= parseFloat(qf[i].value);
				var xh ="";
				if(fy<0){
					qfId =qf[i].id;
					var arr = qfId.split("_");
					xh = arr[0];
					xhs+=xh+",";
					}
				}if(xhs!=null&&xhs!=""){
					alertError("学号为"+xhs.substring(0,xhs.length-1)+"欠费不能为负数！");
					$("had_edit").value="no";
					flag = false;
					return false;
					}

			if(flag){
				confirmInfo("将要保存当前页面的应缴金额与实缴金额，请确认",SaveBjTyjf);
			}
		}

		function saveMethod(){
			confirmInfo("您已经修改了相关分数，是否需要保存？",CheckSaveBj);
		}

		//验证编辑团员缴费
		function CheckSaveBj(tag){
			var qf = document.getElementsByName("qf");
			var flag = true;
			var xhs ="";
			for(var i = 0;i<qf.length;i++){
				var qfId="";
				var fy= parseFloat(qf[i].value);
				var xh ="";
				if(fy<0){
					qfId =qf[i].id;
					var arr = qfId.split("_");
					xh = arr[0];
					xhs+=xh+",";
					}
				}if(xhs!=null&&xhs!=""){
					alertError("学号为"+xhs.substring(0,xhs.length-1)+"欠费不能为负数！");
					$("had_edit").value="no";
					flag = false;
					return false;
					}

			if(flag&&tag=="ok"){
				SaveBj("ok");
			}else{
				if($("had_edit")){
					$("had_edit").value = "";
				}
				var fanye = $("fanye").value;
				if(fanye=="pre"){
					submitPrePage();
					$("fanye").value="";
					}
				if(fanye=="next"){
					submitNextPage();
					$("fanye").value="";
					}
				if(fanye=="first"){
					submitFirstPage();
					$("fanye").value="";
					}
				if(fanye=="last"){
					submitLastPage();
					$("fanye").value="";
					}
				
			}
		}

		//保存
		function SaveBj(tag){
			//应缴团费
			var yjtf = document.getElementsByName("yjtf");
			var yjtfStr="";
			if(yjtf.length>0){
				for (i=0; i<yjtf.length; i++){
					yjtfStr+=yjtf[i].value+",";
				}
			}
			
			//实缴团费
			var sjtf = document.getElementsByName("sjtf");
			var sjtfStr="";
			if(sjtf.length>0){
				for (i=0; i<sjtf.length; i++){
					sjtfStr+=sjtf[i].value+",";
				}
			}
			
			//学号
			var xh = document.getElementsByName("primarykey_checkVal");
			var xhStr="";
			if(xh.length>0){
				for (i=0; i<xh.length; i++){
					xhStr+=xh[i].value+",";
				}
			}
			//学年
			var xn = new Array();
			
		if(tag == "ok"){
				jQuery.ajaxSetup({async:false});
				var url = "general_dtjs_tyjf_ajax.do?method=saveBjTyjf";
				//参数
			 	var parameter = {
			 		"str_yjtf":yjtfStr,
			 		"str_sjtf":sjtfStr,
			 		"str_xh":xhStr,
			 		"str_xn":xn.join("!!@@!!")
				};
				
			 	$("divWaiting").style.display="";
				$("divDisable").style.display="";
				
				jQuery.post(url,
					parameter,
					function(result){
						$("divWaiting").style.display="none";
						$("divDisable").style.display="none";
						$("edit").value="yes";
						$("had_edit").value="no";
						$("bj").style.display = "none";
						$("wh").style.display = "none";
						$("dc").style.display = "none";
						$("dr").style.display = "none";
						$("bc").style.display = "";
						var fanye = $("fanye").value;
						if(fanye=="pre"){
							submitPrePage();
							$("fanye").value="";
							}
						if(fanye=="next"){
							submitNextPage();
							$("fanye").value="";
							}
						if(fanye=="first"){
							submitFirstPage();
							$("fanye").value="";
							}
						if(fanye=="last"){
							submitLastPage();
							$("fanye").value="";
							}
					}
				);
				jQuery.ajaxSetup({async:true});
			}
		}
		
		//保存
		function SaveBjTyjf(tag){
			//应缴团费
			var yjtf = document.getElementsByName("yjtf");
			var yjtfStr="";
			if(yjtf.length>0){
				for (i=0; i<yjtf.length; i++){
					yjtfStr+=yjtf[i].value+",";
				}
			}
			
			//实缴团费
			var sjtf = document.getElementsByName("sjtf");
			var sjtfStr="";
			if(sjtf.length>0){
				for (i=0; i<sjtf.length; i++){
					sjtfStr+=sjtf[i].value+",";
				}
			}
			
			//学号
			var xh = document.getElementsByName("primarykey_checkVal");
			var xhStr="";
			if(xh.length>0){
				for (i=0; i<xh.length; i++){
					xhStr+=xh[i].value+",";
				}
			}
			//学年
			var xn = new Array();
			
		if(tag == "ok"){
				
				jQuery.ajaxSetup({async:false});
				
				var url = "general_dtjs_tyjf_ajax.do?method=saveBjTyjf";
				
				//参数
			 	var parameter = {
			 		"str_yjtf":yjtfStr,
			 		"str_sjtf":sjtfStr,
			 		"str_xh":xhStr,
			 		"str_xn":xn.join("!!@@!!")
				};
				
			 	$("divWaiting").style.display="";
				$("divDisable").style.display="";
				
				jQuery.post(url,
					parameter,
					function(result){
						$("divWaiting").style.display="none";
						$("divDisable").style.display="none";
						alertInfo(result);
						$("edit").value="no";
						$("had_edit").value="no";
						$("bj").style.display = "";
						$("wh").style.display = "";
						$("dc").style.display = "";
						$("dr").style.display = "";
						$("bc").style.display = "none";
						searchRs();
					}
				);
				
				jQuery.ajaxSetup({async:true});
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
				<em>您的当前位置：</em><a>${title }</a>
			</p>
			<p class="help">
				<a href="#" onclick="return false;" onmousedown ="showHelpDiv()">使用帮助</a>
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
				本功能默认展示的是本学年团员缴费数据。</br>
				</span>
			</p>
			<a class="close" title="隐藏" onclick="this.parentNode.style.display='none';"></a>
		</div>
		<!-- 提示信息 end-->
		
		<html:form action="/general_dtjs" method="post">
			<!-- 隐藏域 -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<input type="hidden" name="first" id="first" value="no">
			<input type="hidden" name="edit" id="edit" value="no">
			<input type="hidden" name="had_edit" id="had_edit" value="no">
			<!-- 多功能操作区 -->
			<div class="toolbox">
				<!-- 按钮 -->
				<div class="buttonbox">
					<ul>
						<!-- 读写权 begin-->
						<logic:equal name="writeAble" value="yes">
						<li id="bj">
							<a href="#" onclick="changeEdit();return false;" class="btn_xg">
								编辑
							</a>
						</li>
						<li id="wh">
							<a href="#" onclick="showTyjfDiv();return false;" class="btn_ccg">
								维护
							</a>
						</li>
						<li id="dr">
							<a href="#" onclick="impAndChkData();return false;" class="btn_dr">
								导入
							</a>
						</li>
						</logic:equal>
						<!-- 读写权 end-->
						<li id="dc" >
							<a href="#" onclick="expData('general_dtjs_tyjf_ajax.do?method=expTyjf');return false;" class="btn_dc">
								导出
							</a>
						</li>
						<li id="bc">
							<a href="#" onclick="CheckSaveBjTyjf();return false;" class="btn_zj">
								保存
							</a>
						</li>
				
					</ul>
				</div>
				<!-- 按钮 end-->
				
				<!-- 过滤条件 -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- 过滤条件 end-->
			</div>
			
			<!-- 内容显示区开始 -->
			<div class="main_box">
				<div id="div_rs"
					style="width:100%;height:100%;overflow-x:auto;overflow-y:hidden;">
				</div>

				<!--分页显示-->
				<jsp:include flush="true" page="/sjcz/turnpage.jsp?form=dtjsGeneralForm"></jsp:include>		
				<!--分页显示-->
			</div>
			<!-- 内容显示区 end-->
			
			<!-- 团员缴费保存弹出层 begin-->
			<div id="div_tyjf" style="display:none">
				<div class="open_win01">
					<table align="center" class="formlist">
						<thead>
							<tr>
								<th colspan="2">
									<span>团费维护</span>
								</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<th width="30%">
									应缴团费
								</th>
								<td>
									<input type="text" name="str_yjtf" id="yjtf" onblur="checkInputNum(this)"/>
								</td>
							</tr>
							<tr>
								<th>
									实缴团费
								</th>
								<td>
									<input type="text" name="str_sjtf" id="sjtf" onblur="checkInputNum(this)"/>
								</td>
							</tr>
						</tbody>
						<tfoot>
							<tr>
								<td colspan="2">
									<div class="bz">
										
									</div>
									<div class="btn">
										<button type="button" onclick="checkSaveTyjf()">
											保 存
										</button>
										
										<button type="button" onclick="closeWindown();return false;">
											关 闭
										</button>
									</div>
								</td>
							</tr>
						</tfoot>
					</table>
				</div>
			</div>
			<!-- 团员缴费保存弹出层 end-->
			
			<!-- 提示信息 -->
			<%@ include file="/comm/other/tsxx.jsp"%>
		</html:form>
	</body>
</html>