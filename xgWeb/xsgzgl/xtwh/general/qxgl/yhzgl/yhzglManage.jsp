<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- 作者：伟大的骆 -->
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
		<script type='text/javascript' src='/xgxt/dwr/util.js'></script>
		<script language="javascript" src="js/check.js"></script>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script language="javascript" src="js/Basic.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/exportNew/import.js"></script>
		<script language="javascript" defer="defer">
		//初始化
		function onShow(){ 		
			searchRs();
		}
		function teshuzifu(str)
		{
			//输入框特殊字符过滤
			var str_ne =jQuery(str).val();
			var pat="[`~!@#\$%\^&\-\*\(\)_\+<>\?:\"{},\.\/;'\[\\]]";
		    var pattern = new RegExp(pat);
		    //  
		    if(pattern.test(str_ne)||str_ne=='-'){  
		    	jQuery(str).val(str_ne.replaceAll(pat,'').replaceAll('-','')); 
		        alert("不能输入特殊字符");   
		    }     
		    
		}
		//查询结果集
		function searchRs(){
			jQuery.ajaxSetup({async:false});
			
			var url = "xtwh_qxgl_yhzgl_ajax.do?method=yhzxxSearch";
			var ie = "ie";
		
			var parameter = {"ie":ie};
			
			jQuery("select,input",jQuery("#tbody_search_query")).each(function(){				
				parameter["str_"+jQuery(this).attr("name")]=escape(jQuery(this).val());
			});
		
			$("divWaiting").style.display="";
			$("divDisable").style.display="";
			
			searchGo(url,parameter);

			$("divWaiting").style.display="none";
			$("divDisable").style.display="none";
			
			jQuery.ajaxSetup({async:true});
			
		}
		
		function addYhzxx(){
			var url = "xtwh_qxgl_yhzgl_ajax.do?method=yhzxxAdd";
			var zmc = jQuery("#zmc_add").val();
			if (trim(zmc) == "") {
				showAlert("组名称不能为空！");
				jQuery("#zmc_add").focus();
				return false;
			}
			var parameter={"str_zmc":escape(zmc)};
			jQuery.post(url,parameter,function(result){
				if(result == "exist"){
					showAlert("用户组名称已存在！");
					jQuery("#zmc_add").focus();
					return false;
				}else{
					alertInfo(result+"！");
					hiddenMessage(true,true);
					searchRs();
				}
					
			});
		}
		function copyYhzxx(act){
			var url = "xtwh_qxgl_yhzgl_ajax.do?method=yhzxxCopy";
			
			var len=jQuery("[name=primarykey_checkVal]:checked").length;
			if(len==1){
				var zdm = jQuery("[name=primarykey_checkVal]:checked").val();
				var zmc_old = jQuery("[name=primarykey_checkVal]:checked").parents().children("td").eq(1).html();
				
				if(act=="show"){
					//viewTempDiv('用户组复制','copyYhzxx',320, 110)
					showDialog("用户组复制", 420, 240, "xtwh_qxgl_yhzgl.do?method=ymcl&type=copy&zdm="+zdm+"&zmc_old="+zmc_old);
					jQuery("#zmc_copy").val("复制 "+zmc_old);
				}else{					
					var zmc_new = jQuery("#zmc_copy").val();
					if(trim(zmc_new) == trim(zmc_old)){
						alertInfo("保存成功！");
						hiddenMessage(true,true);
						return false;
					}else{
						var parameter={"zdm":zdm,"zmc":encodeURI(zmc_new)};
						
						jQuery.post(url,parameter,function(result){
							if(result == "exist"){
								alertInfo("用户组名称已存在！");
								document.getElementById('zmc_copy').focus();
								return false;
							}else{
								alertInfo(result+"！");
								hiddenMessage(true,true);
								searchRs();
							}						
						});
					}
				}
			}else {				
				alertInfo("请勾选一条需要复制的用户组！");
			}
		}

		function xsxxts(){
			alertInfo('请到"学生信息-学生信息-在校生信息 "下查看具体信息 ');			
		}
		
		function updateYhzxx(act){

			var url = "xtwh_qxgl_yhzgl_ajax.do?method=yhzxxUpdate";
			
			var len=jQuery("[name=primarykey_checkVal]:checked").length;
			
			if(len==1){
				var zdm = jQuery("[name=primarykey_checkVal]:checked").val();
				var zmc_old = jQuery("[name=primarykey_checkVal]:checked").parents().children("td").eq(1).html();
				
				if(act=="show"){
					showDialog('用户组修改',  360, 180, "xtwh_qxgl_yhzgl.do?method=ymcl&type=update&zdm="+zdm+"&zmc_old="+zmc_old);
					//viewTempDiv('用户组修改','updateYhzxx',320, 110)
					jQuery("#zmc_update").val(zmc_old);
				}else{					
					var zmc_new = jQuery("#zmc_update").val();
					if(trim(zmc_new) == trim(zmc_old)){
						alertInfo("保存成功！");
						hiddenMessage(true,true);
						return false;
					}else{
						var parameter={"str_zdm":zdm,"str_zmc":escape(zmc_new)};
						
						jQuery.post(url,parameter,function(result){
							if(result == "exist"){
								alertInfo("用户组名称已存在！");
								document.getElementById('zmc_update').focus();
								return false;
							}else{
								alertInfo(result+"！");
								hiddenMessage(true,true);
								searchRs();
							}						
						});
					}
				}
			}else {				
				alertInfo("请勾选一条需要修改的数据！");
			}
		}
		
		function deleteYhzxx(){
			var url = "xtwh_qxgl_yhzgl_ajax.do?method=yhzxxDelete";
			
			var n = jQuery("[name=primarykey_checkVal]:checked").length;
			
			if(n>0){
				var i = 0;
				var ifXsz = "";
				var ifHyh = "";
				var parameter = {};
				var array = new Array();
				jQuery("[name=primarykey_checkVal]:checked").each(function(i){	
					if(jQuery(this).val() == "6727"){//学生组不可删除
						ifXsz = "yes";
						return false;
					}else if(jQuery(this).parents().children("td").eq(2).children("a").children("b").html() != "0"){//已有用户的用户组不可删除
						ifHyh = "yes";
						return false;
					}else{		
						array[i] = escape(jQuery(this).val());
					}
				});
				if(ifXsz == "yes"){ 
					alertInfo("“学生”组为系统默认用户组<br>不可删除！");
				}else if(ifHyh == "yes"){
					alertInfo("已维护用户的用户组不可删除！");
				}else{		
					parameter["array_primarykey_checkVal"]=array.join('!!array!!');
					
					confirmInfo("确定要删除选中的记录吗?",function(ok){
						if(ok=="ok"){		
							jQuery.post(url,parameter,function(result){
								alertInfo(result+"！");
								searchRs();		
							});					
						}
					});
				}
			}else{				
				alertInfo("请勾选需要删除的数据！");
			}
		}

		//功能授权
		function gnsq(){

			var url = "xtwh_qxgl_yhzgl.do?method=yhzglGnsq";
			
			var len=jQuery("[name=primarykey_checkVal]:checked").length;
			
			if(len==1){
				jQuery("#pkValue").val(jQuery("[name=primarykey_checkVal]:checked").val());
				document.forms[0].action=url;
				document.forms[0].submit();
			}else {				
				alertInfo("请勾选一条需要修改的数据！");
			}
		}
		//分配用户
		function fpyh(){

			var url = "xtwh_qxgl_yhzgl.do?method=yhzglFpyh";
			
			var len=jQuery("[name=primarykey_checkVal]:checked").length;
			var zdm = jQuery("[name=primarykey_checkVal]:checked").val();
			
			if(len==1){
				if("6727"==zdm){
					alertInfo("学生组不可分配用户！");
					return false;
				}
				jQuery("#pkValue").val(zdm);
				document.forms[0].action=url;
				document.forms[0].submit();
			}else {
				alertInfo("请勾选一条需要分配用户的组！");
				return false;
			}
		}

		function viewYhxx(zdm){
			showDialog('', 800, 500, "xtwh_qxgl_yhzgl.do?method=yhzglViewYhxx&zdm="+zdm);

		}
		function add(){
			showDialog('用户组增加', 360, 180, "xtwh_qxgl_yhzgl.do?method=ymcl&type=add");
		}
		jQuery(function(){
			onShow();
			var btndr=jQuery("#btn_dr");
			//导入
			if(btndr!=null){
				btndr.click(function () {
					//调用通用的导入function，参数是导入功能模块代码。
					toImportData("IMPORT_N700201_YHZGL");
					return false;
				});
			}
		});
		
		
		</script>
	</head>
	<body >
		<!-- 标题 -->
		<div class="tab_cur" >
			<p class="location">
				<em>您的当前位置:</em><a>${title }</a>
			</p>
			<p class="help">
<%--			<a href="#" onclick="return false;" onmousedown ="showHelpDiv()">使用帮助</a>--%>
			</p>
		</div>			
		<!-- 标题 end-->
		<!-- 提示信息 end-->
		<div class="prompt" id="div_help" style="display: none">
			<h3>
				<span>提示：</span>
			</h3>
			<p>
				未填写
			</p>
			<a class="close" title="隐藏"  onclick="this.parentNode.style.display='none';"></a>
		</div>
		<!-- 提示信息 end-->

		<html:form action="/xtwh_qxgl_yhzgl" method="post">

			<!-- 隐藏域 -->
			<%@ include file="/comm/hiddenValue.jsp"%>			
			<!-- 隐藏域 -->
			

			<!-- 多功能操作区 -->
			<div class="toolbox" id="dgncz">
				<!-- 按钮 -->
				<logic:equal name="writeAble" value="yes">
				<div class="buttonbox">
					<ul>

						<li>
							<a href="#" onclick="add();" class="btn_zj">增加</a>
						</li>
						<li>
							<a href="#" onclick="updateYhzxx('show');return false;" class="btn_xg">修改</a>
						</li>
						<li>
							<a href="#" onclick="deleteYhzxx();return false;" class="btn_sc">删除</a>
						</li>
						<li>
							<a href="#" onclick="copyYhzxx('show');return false;" class="btn_fz">复制</a>
						</li>						
						<li>
							<a href="#" onclick="gnsq();return false;" class="btn_sq">功能授权</a>
						</li>
						<li>
							<a href="#" onclick="fpyh();return false;" class="btn_sq">组用户维护</a>
						</li>
						<li><a href="#" class="btn_dr" id="btn_dr">导入</a></li>
					</ul>
				</div>
				</logic:equal>
				<!-- 过滤条件 -->
				<div class="searchtab">
					<table width="100%" border="0">
						<tbody id="tbody_search_query">
							<tr>
								<th>
									组名称
								</th>
								<td>
									<input type="text" name="zmc" id="zmc" style="width:200px" maxlength="20" 
										onkeypress="if(pressEnter(event)){searchRs();return false;}"/>
								</td>	
								<td >
									<div class="btn">
										<button type="button"  class="btn_cx" id="search_go" onclick="searchRs();return false;">
											查 询
										</button>
										&nbsp;&nbsp;&nbsp;&nbsp;
										<button type="button"  class="btn_cz" id="btn_cz" onclick="searchReset();return false;">
											重 置
										</button>
									</div>
								</td>						
							</tr>
						</tbody>
						<%--
						<tfoot>
							<tr>
								<td colspan="2">
									<input type="hidden" name="go" value="a" />
									<div class="btn">
										<button type="button" class="btn_cx" id="search_go" onclick="searchRs();return false;">
											查 询
										</button>
										&nbsp;&nbsp;&nbsp;&nbsp;
										<button type="button" class="btn_cz" id="btn_cz" onclick="searchReset();return false;">
											重 置
										</button>
									</div>
								</td>
							</tr>
						</tfoot>
						--%>
					</table>
				</div>
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
				<div id="div_rs"
					style="width:100%;overflow-x:auto;overflow-y:hidden;">
				</div>

				<!--分页显示-->
				<jsp:include flush="true" page="/sjcz/turnpage.jsp?form=yhzglNewForm"></jsp:include>
				<!--分页显示-->
			</div>
			<!-- 内容显示区 end-->
			
			<!-- 添加组弹出层 -->
			<div class="open_win01" style="display:none;" id="addYhzxx">
				<table width='80%' class='formlist'>
<%--					<thead>--%>
<%--						<tr>--%>
<%--							<th colspan="2">--%>
<%--								<span>用户组增加</span>--%>
<%--							</th>--%>
<%--						</tr>--%>
<%--					</thead>--%>
					<tbody>
						<tr>
							<th>
								<font color='red'>*</font>组名称
							</th>
							<td>
								<input type='text' name='zmc_add' id='zmc_add' onkeyup="teshuzifu(this);"  onkeypress="if(pressEnter(event)){return false;}" onblur="teshuzifu(this);"  class='text_nor' maxlength="10"/>
							</td>
						</tr>
					<tbody>
					<tfoot>
						<tr>
							<td colspan="2">
								<div class="bz">
									"<span class="red">*</span>"为必填项
								</div>
								<div class="btn">
									<button type="button"  onclick="addYhzxx()">
										添加
									</button>
									&nbsp;&nbsp;
									<button type="button"  onclick="hiddenMessage(true,true);return false;">
										关闭
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
				</table>
			</div>
			<!-- 修改组弹出层 -->
			<div class="open_win01" style="display:none;" id="updateYhzxx">
				<table width="80%" class="formlist">
<%--					<thead>--%>
<%--						<tr>--%>
<%--							<th colspan="2">--%>
<%--								<span>用户组修改</span>--%>
<%--							</th>--%>
<%--						</tr>--%>
<%--					</thead>--%>
					<tbody>
						<tr height='30'>
							<th>
								<font color="red">*</font>组名称
							</th>
							<td>
								<input type="text" name="zmc_update" id="zmc_update" onkeyup="teshuzifu(this);" onkeypress="if(pressEnter(event)){return false;}"  onblur="teshuzifu(this);" class="text_nor" maxlength="10"/>
								<input type="hidden" name="yymc" id="yymc"/>
							</td>
						</tr>
					<tbody>
					<tfoot>
						<tr>
							<td colspan="2">
								<div class="bz">
									"<span class="red">*</span>"为必填项
								</div>
								<div class="btn">
									<button type="button"  onclick="updateYhzxx('do')">
										修改
									</button>
									&nbsp;&nbsp;
									<button type="button"  onclick="hiddenMessage(true,true);return false;">
										关闭
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
				</table>
			</div>
			<div class="open_win01" style="display:none;" id="copyYhzxx">
				<table width="80%" class="formlist">
<%--					<thead>--%>
<%--						<tr>--%>
<%--							<th colspan="2">--%>
<%--								<span>用户组修改</span>--%>
<%--							</th>--%>
<%--						</tr>--%>
<%--					</thead>--%>
					<tbody>
						<tr height='30'>
							<th>
								<font color="red">*</font>组名称
							</th>
							<td>
								<input type="text" name="zmc_copy" id="zmc_copy" onkeyup="teshuzifu(this);" onkeypress="if(pressEnter(event)){return false;}"  onblur="teshuzifu(this);" class="text_nor" maxlength="10"/>
								<input type="hidden" name="yymc" id="yymc"/>
							</td>
						</tr>
					<tbody>
					<tfoot>
						<tr>
							<td colspan="2">
								<div class="bz">
									"<span class="red">*</span>"为必填项
								</div>
								<div class="btn">
									<button type="button"  onclick="copyYhzxx('do')">
										复制
									</button>
									&nbsp;&nbsp;
									<button type="button"  onclick="hiddenMessage(true,true);return false;">
										关闭
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
				</table>
			</div>
			<!-- 提示信息 -->
			<%@ include file="/comm/other/tsxx.jsp"%>
		</html:form>
	</body>
</html>
