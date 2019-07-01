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
		<script language="javascript" defer="defer">
		
		//初始化
		function onShow(){ 
			//
			searchRs();
		}
		
		//查询结果集
		function searchRs(){
			
			jQuery.ajaxSetup({async:false});
			
			var url = "general_pjsz_cpxz_ajax.do?method=searchPjszCpxz";
			var ie = "ie";
			
			var otherValue = [ie];

			$("divWaiting").style.display="";
			$("divDisable").style.display="";
			
			searchRsByAjax(url,otherValue);

			$("divWaiting").style.display="none";
			$("divDisable").style.display="none";
			
			jQuery.ajaxSetup({async:true});
		}

		//检测显示参评组Div
		function checkShowCpzDiv(){
			jQuery("#cpzmc").val("");
			tipsWindown("系统提示","id:div_cpxz","400","300","true","","true","id");
		}
		
		//检测保存参评小组设置
		function checkSaveCpxz(){
			var cpzsz = jQuery("#cpzsz_check").val();
			var num = jQuery("input[type=checkbox][name=primarykey_checkVal]:checked").length;

			if(num == 0){//整体
				//年级
				var nj_num = jQuery("a[name=a_name_nj]").length; 			  		
				//学院
				var xy_num = jQuery("a[name=a_name_xy]").length; 		
				//专业
				var zy_num = jQuery("a[name=a_name_zy]").length; 
				//班级
				var bj_num = jQuery("a[name=a_name_bj]").length; 
				
				if(cpzsz == "new"){	
					var cpzmc = jQuery("#cpzmc").val();//参评组名称
					if(cpzmc == ""){
						alertError("参评组名称不能为空，请您确认^_^!!");
						return false;
					}
	
					var obj = $("select_cpzmc");
					for(i=0;i<obj.options.length;i++){
						var mc = obj.options[i].value;
						if(mc == cpzmc){
							alertError("您输入的参评组名称已存在，请换一个^_^!!");
							return false;
						}
					}
				}
					
				saveCpxzNoChecked("ok");
				
			}else{//勾选
				if(cpzsz == "new"){
					var cpzmc = jQuery("#cpzmc").val();//参评组名称
					if(cpzmc == ""){
						alertError("参评组名称不能为空，请您确认^_^!!");
						return false;
					}
	
					var obj = $("select_cpzmc");
					for(i=0;i<obj.options.length;i++){
						var mc = obj.options[i].value;
						if(mc == cpzmc){
							alertError("您输入的参评组名称已存在，请换一个^_^!!");
							return false;
						}
					}
					saveCpxzChecked("ok");
				}else{
					saveCpxzChecked("ok");
				}
			}
		}

		//保存参评小组设置（未选中）
		function saveCpxzNoChecked(tag){
			if(tag == "ok"){

				jQuery.ajaxSetup({async:false});

				//年级
				var nj = new Array();  
				var i = 0;				  
				jQuery("a[name=a_name_nj]").each(function(){
					var nj_id = jQuery(this).attr("id");
					nj[i] = nj_id.replace("a_id_","");
					i++;
				});
				
				//学院
				var xy = new Array(); 
				i = 0;			  
				jQuery("a[name=a_name_xy]").each(function(){
					var xy_id = jQuery(this).attr("id");
					xy[i] = xy_id.replace("a_id_","");
					i++;
				});

				//专业
				var zy = new Array(); 
				i = 0;					  
				jQuery("a[name=a_name_zy]").each(function(){
					var zy_id = jQuery(this).attr("id");
					zy[i] = zy_id.replace("a_id_","");
					i++;
				});

				//班级
				var bj = new Array();  				  
				jQuery("a[name=a_name_bj]").each(function(){
					var bj_id = jQuery(this).attr("id");
					bj[i] = bj_id.replace("a_id_","");
					i++;
				});
				
				var cpzsz = jQuery("#cpzsz_check").val();
				
				var cpzmc = "";//参评组名称

				if(cpzsz == "new"){
					cpzmc = jQuery("#cpzmc").val();
				}else{
					cpzmc = jQuery("#select_cpzmc").val();
				}

				var url = "general_pjsz_cpxz_ajax.do?method=saveCpxzNoChecked";
				
				//参数
			 	var parameter = {
			 		"cpzmc":escape(cpzmc),
			 		"nj":nj.join("!!@@!!"),
			 		"xy":xy.join("!!@@!!"),
			 		"zy":zy.join("!!@@!!"),
			 		"bj":bj.join("!!@@!!")
				};

			 	$("divWaiting").style.display="";
				$("divDisable").style.display="";
				
				jQuery.post(url,
					parameter,
					function(result){
						$("divWaiting").style.display="none";
						$("divDisable").style.display="none";
						alertInfo(result);
						if(cpzsz == "new"){
							var html = "<option value=\""+cpzmc+"\">"+cpzmc+"</option>";
							jQuery("#select_cpzmc").append(html);
							tempHTML = jQuery("#windown-content").html();
						}
						searchRs();
						closeWindown();	
					}
				);
				
				jQuery.ajaxSetup({async:true});
			}
		}
		
		//保存参评小组设置（选中）
		function saveCpxzChecked(tag){
			if(tag == "ok"){
				jQuery.ajaxSetup({async:false});
				
				var bjdm = new Array();//班级代码
				var bjdm_num = jQuery("input[type=checkbox][name=primarykey_checkVal]:checked").length;
				var count = 0;
				for(var i=0;i<bjdm_num;i++){
					var obj = jQuery("input[type=checkbox][name=primarykey_checkVal]:checked")[i];
					bjdm[count] = obj.value;
					count++;
				}

				var cpzsz = jQuery("#cpzsz_check").val();
				
				var cpzmc = "";//参评组名称

				if(cpzsz == "new"){
					cpzmc = jQuery("#cpzmc").val();
				}else{
					cpzmc = jQuery("#select_cpzmc").val();
				}
					
				var url = "general_pjsz_cpxz_ajax.do?method=saveCpxzChecked";
				
				//参数
			 	var parameter = {
			 		"cpzmc":escape(cpzmc),
			 		"bjdm":bjdm.join("!!@@!!")
				};

			 	$("divWaiting").style.display="";
				$("divDisable").style.display="";
				
				jQuery.post(url,
					parameter,
					function(result){
						$("divWaiting").style.display="none";
						$("divDisable").style.display="none";
						alertInfo(result);
						if(cpzsz == "new"){
							var html = "<option value=\""+cpzmc+"\">"+cpzmc+"</option>";
							jQuery("#select_cpzmc").append(html);
							tempHTML = jQuery("#windown-content").html();
						}
						searchRs();
						closeWindown();	
					}
				);

				jQuery.ajaxSetup({async:true});
			}
		}

		//检测删除参评组设置
		function checkDeleteCpzsz(){
			var num = jQuery("input[type=checkbox][name=primarykey_checkVal]:checked").length;

			if(num == 0){//整体
				confirmInfo("请您确认是否取消<font color='blue'>过滤条件中指定班级</font>的参评组设置",deleteCpxzNoChecked);
			}else{
				confirmInfo('请您确认是否取消所勾选班级的参评组设置',deleteCpxzChecked);
			}
		}

		//删除参评小组设置（选中）
		function deleteCpxzChecked(tag){
			if(tag == "ok"){
				jQuery.ajaxSetup({async:false});
				
				var bjdm = new Array();//班级代码
				var bjdm_num = jQuery("input[type=checkbox][name=primarykey_checkVal]:checked").length;
				var count = 0;
				for(var i=0;i<bjdm_num;i++){
					var obj = jQuery("input[type=checkbox][name=primarykey_checkVal]:checked")[i];
					bjdm[count] = obj.value;
					count++;
				}
					
				var url = "general_pjsz_cpxz_ajax.do?method=deleteCpxzChecked";
				
				//参数
			 	var parameter = {
			 		"bjdm":bjdm.join("!!@@!!")
				};

			 	$("divWaiting").style.display="";
				$("divDisable").style.display="";
				
				jQuery.post(url,
					parameter,
					function(result){
						$("divWaiting").style.display="none";
						$("divDisable").style.display="none";
						alertInfo(result);
						goPjszCpxz();
						closeWindown();	
					}
				);

				jQuery.ajaxSetup({async:true});
			}
		}

		//取消参评小组设置（未选中）
		function deleteCpxzNoChecked(tag){
			if(tag == "ok"){

				jQuery.ajaxSetup({async:false});

				//年级
				var nj = new Array();  
				var i = 0;				  
				jQuery("a[name=a_name_nj]").each(function(){
					var nj_id = jQuery(this).attr("id");
					nj[i] = nj_id.replace("a_id_","");
					i++;
				});
				
				//学院
				var xy = new Array(); 
				i = 0;			  
				jQuery("a[name=a_name_xy]").each(function(){
					var xy_id = jQuery(this).attr("id");
					xy[i] = xy_id.replace("a_id_","");
					i++;
				});

				//专业
				var zy = new Array(); 
				i = 0;					  
				jQuery("a[name=a_name_zy]").each(function(){
					var zy_id = jQuery(this).attr("id");
					zy[i] = zy_id.replace("a_id_","");
					i++;
				});

				//班级
				var bj = new Array();  				  
				jQuery("a[name=a_name_bj]").each(function(){
					var bj_id = jQuery(this).attr("id");
					bj[i] = bj_id.replace("a_id_","");
					i++;
				});

				var url = "general_pjsz_cpxz_ajax.do?method=deleteCpxzNoChecked";
				
				//参数
			 	var parameter = {
			 		"nj":nj.join("!!@@!!"),
			 		"xy":xy.join("!!@@!!"),
			 		"zy":zy.join("!!@@!!"),
			 		"bj":bj.join("!!@@!!")
				};

			 	$("divWaiting").style.display="";
				$("divDisable").style.display="";
				
				jQuery.post(url,
					parameter,
					function(result){
						$("divWaiting").style.display="none";
						$("divDisable").style.display="none";
						alertInfo(result);
						goPjszCpxz();
						closeWindown();	
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
	<body >

		<!-- 标题 -->
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>评奖评优 - 基本设置 - 参评小组设置</a>
			</p>
			<p class="help">
				<a href="#" onclick="return false;" onmousedown ="showHelpDiv();">使用帮助</a>
			</p>
		</div>			
		<!-- 标题 end-->
		<!-- 提示信息 START-->
		<div class="prompt" id="div_help" style="display: none">
			<h3>
				<span>提示：</span>
			</h3>
			<p>
				<span> 1.本功能默认展示的是本评奖学年学期的数据。</br> </span>
			</p>
			<a class="close" title="隐藏"  onclick="this.parentNode.style.display='none';"></a>
		</div>
		<!-- 提示信息 end-->

		<html:form action="/general_pjpy" method="post">
			<!-- 隐藏域 -->
			<%@ include file="/comm/hiddenValue.jsp"%>

			<!-- 多功能操作区 -->
			<div class="toolbox">
				<!-- 按钮 -->
				<div class="buttonbox">
					<ul>
						<!-- 页面来源 -->
						<logic:equal name="forward" value="jbsz">
							<li>
								<a href="#" onclick="goPjpyJbsz();return false;" class="btn_fh">
									返回设置 </a>
							</li>
						</logic:equal>
						<!-- 页面来源end -->
						<li>
							<a href="#" onclick="showCpxzZdszDiv();return false;"
								class="btn_sz"> 自动设置参评组 </a>
						</li>
						<li>
							<a href="#" onclick="checkShowCpzDiv();return false;"
								class="btn_ccg"> 设置参评组 </a>
						</li>
						<li>
							<a href="#" onclick="checkDeleteCpzsz();return false;"
								class="btn_sc"> 取消参评组 </a>
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
				<div class="mid_box">
					<div class="title">
						<p>
							<!-- 查询得到的数据量显示区域 -->
						</p>
					</div>
				</div>
				<!-- From内容 -->
				<div id="div_rs"
					style="height: 380px; overflow-x: auto; overflow-y: auto;">
				</div>

				<!--分页显示-->
				<div style="clear: both;">
					<jsp:include flush="true"
						page="/sjcz/turnpage.jsp?form=generalPjpyGeneralForm"></jsp:include>
					<script type="text/javascript">
				     //$('choose').className="hide";
				     </script>
				</div>
				<!--分页显示-->
			</div>
			<!-- 内容显示区开始 end-->

			<!-- 参评小组设置（勾选记录）弹出层 -->
			<div id="div_cpxz" style="display: none">
				<div class="open_win01">
					<table align="center" class="formlist">
						<thead>
							<tr>
								<th colspan="2">
									<span>参评小组设置</span>
								</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<th width="30%">
									参评组设置
								</th>
								<td width="">
									<input type="radio" name="cpzsz" id="cpzsz_new" value="yes"
										onclick="setCheckedValue(this);$('tr_new').style.display='';$('tr_old').style.display='none'"
										checked="checked" />
									新的参评小组
									<br />
									<input type="radio" name="cpzsz" id="cpzsz_old" value="no"
										onclick="setCheckedValue(this);$('tr_new').style.display='none';$('tr_old').style.display=''" />
									已有的参评小组
									<input type="hidden" id="cpzsz_check" value="new" />
								</td>
							</tr>
							<tr id="tr_new">
								<th width="30%">
									参评组名称
								</th>
								<td width="">
									<input type="text" id="cpzmc" maxlength="20" />
								</td>
							</tr>
							<tr id="tr_old" style="display: none">
								<th width="30%">
									参评组名称
								</th>
								<td width="">
									<select id="select_cpzmc">
										<logic:notEmpty name="cpzList">
											<logic:iterate name="cpzList" id="cpz">
												<option value="${cpz.mc }">
													${cpz.mc }
												</option>
											</logic:iterate>
										</logic:notEmpty>
									</select>
								</td>
							</tr>
						</tbody>
						<tfoot>
							<tr>
								<td colspan="2">
									<div class="bz">

									</div>
									<div class="btn">
										<button type="button"  onclick="checkSaveCpxz();return false;">
											确 定
										</button>

										<button type="button"  onclick="closeWindown();return false;">
											关 闭
										</button>
									</div>
								</td>
							</tr>
						</tfoot>
					</table>
				</div>
			</div>
			<!-- 参评小组设置弹出层 end-->

			<!-- 自动设置弹出层 -->
			<div id="div_cpxz_zdsz" style="display: none">
				<div class="open_win01">
					<table align="center" class="formlist">
						<thead>
							<tr>
								<th colspan="2">
									<span>自动设置规则选择(针对无参评组的班级)</span>
								</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td width="">
									<input type="radio" name="cpzgz" value="nj" />
									以年级为单位划分参评组
								</td>
							</tr>
							<tr>
								<td width="">
									<input type="radio" name="cpzgz" value="njxy" />
									以年级+<bean:message key="lable.xb" />为单位划分参评组
								</td>
							</tr>
							<tr>
								<td width="">
									<input type="radio" name="cpzgz" value="xy" />
									以<bean:message key="lable.xb" />为单位划分参评组
								</td>
							</tr>
							<tr>
								<td width="">
									<input type="radio" name="cpzgz" value="njzy" />
									以年级+专业为单位划分参评组
								</td>
							</tr>
							<tr>
								<td width="">
									<input type="radio" name="cpzgz" value="bj" checked="checked" />
									以班级为单位划分参评组
								</td>
							</tr>
						</tbody>
						<tfoot>
							<tr>
								<td colspan="2">
									<div class="bz">

									</div>
									<div class="btn">
										<button type="button"  onclick="saveCpxzZdsz();return false;">
											确 定
										</button>

										<button type="button"  onclick="closeWindown();return false;">
											关 闭
										</button>
									</div>
								</td>
							</tr>
						</tfoot>
					</table>
				</div>
			</div>
			<!-- 自动设置弹出层  -->

			<!-- 提示信息 -->
			<%@ include file="/comm/other/tsxx.jsp"%>
		</html:form>
	</body>
</html>