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
		<script language="javascript" src="/xgxt/dwr/interface/systemFunction.js"></script>
		<script language="javascript" defer="defer">
		//初始化
		function onShow(){ 		
			searchRs();
		}
		
		//查询结果集
		function searchRs(){
			jQuery.ajaxSetup({async:false});
			
			var url = "xtwh_qxgl_yhgl_ajax.do?method=yhxxSearch";
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
		
		
		//功能授权
		function gnsq(){

			var url = "xtwh_qxgl_yhgl.do?method=yhglGnsq";			
			var len=jQuery("[name=primarykey_checkVal]:checked").length;
			var flag = true;
			
			if(len==1){
				jQuery.ajaxSetup({async:false});
				jQuery.post("xtwh_qxgl_yhgl_ajax.do?method=getYhxx",{yhm:jQuery("[name=primarykey_checkVal]:checked").val()},function(data){
					if(data.zdm == "" || data.zdm == null){						
						alertError("该用户尚未分配用户组，请先分配用户组后再进行功能授权！");
					}else{
						jQuery("#pkValue").val(jQuery("[name=primarykey_checkVal]:checked").val());
						document.forms[0].action=url;
						document.forms[0].submit();
					}		
				},'json');
				jQuery.ajaxSetup({async:true});
			}else {				
				alertInfo("请勾选一条需要修改的数据！");
			}
		}

		//启用状态更新
		function qy(zt){
			
			var url = "xtwh_qxgl_yhgl_ajax.do?method=yhty";
			var n = jQuery("[name=primarykey_checkVal]:checked").length;
			var ifGly = "";
			var ifQy = "";	
			if(n>0){
				var i = 0;
				var parameter = {};
				var array = new Array();
				jQuery("[name=primarykey_checkVal]:checked").each(function(i){
					if(jQuery(this).val() == "zf01"){
						ifGly = "yes";
						return false;
					}else{	
						jQuery.ajaxSetup({async:false});
						jQuery.post("xtwh_qxgl_yhgl_ajax.do?method=getYhxx",{yhm:jQuery(this).val()},function(data){
							if(data.qx == "0"){
								ifQy = "yes";
								return false;
							}
						},'json');
						jQuery.ajaxSetup({async:true});
						array[i] = escape(jQuery(this).val());
					}
				});
				if(ifGly == "yes"){
					alertError("zf01为系统默认用户，不可停用！");
					return false; 
				}else if(ifQy == "yes"){
					alertError("已停用的用户！");
					return false; 
				}else{
					parameter["array_primarykey_checkVal"]=array.join('!!array!!');
					parameter["str_qx"]=zt;
					if(zt=='0'){
						parameter["str_zdm"]='';
					}
					confirmInfo("确定要停用所选用户吗?",function(ok){
						if(ok=="ok"){
							jQuery.post(url,parameter,function(result){
								alertInfo(result+"！");
								hiddenMessage(true,true);
								searchRs();		
							});					
						}else{
							return false;
						}
					});
				}
			}else {				
				alertInfo("请勾选需要停用的用户！");
				return false;
			}
		}

		//是否启用思政可见
		function szkj(zt){
			var url = "xtwh_qxgl_yhgl_ajax.do?method=szkj";
			var n = jQuery("[name=primarykey_checkVal]:checked").length;
			var ifGly = "";
			var ifQy = "";	
			if(n>0){
				var i = 0;
				var parameter = {};
				var zghs = new Array();
				jQuery("[name=primarykey_checkVal]:checked").each(function(i){
					if(jQuery(this).val() == "zf01"){
						ifGly = "yes";
						return false;
					}else{	
						zghs.push(jQuery(this).val());
					}
				});
				if(ifGly == "yes"){
					alertError("zf01为系统默认用户，不可停用！");
					return false; 
				}else{
					confirmInfo("确定要设置所选用户吗?",function(ok){
						if(ok=="ok"){
							jQuery.post(url,
							{sfbl:zt,
							 zghs:zghs.toString()
							 },function(result){
								alertInfo(result+"！");
								hiddenMessage(true,true);
								searchRs();		
							});					
						}else{
							return false;
						}
					});
				}
			}else {				
				alertInfo("请先勾选用户！");
				return false;
			}
		}
		jQuery(function(){
			onShow();
		});

		</script>
	</head>
	<body >
		<input type="hidden" id="pksPlHidden" value="" />
		<!-- 标题 -->
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>${title }</a>
			</p>
			<p class="help">
				<a href="#" onclick="return false;"	onmousedown ="showHelpDiv()" >使用帮助</a>
			</p>
<%--			<p class="help">--%>
<%--				<a href="#" onclick="return false;" onmouseover="showHelpDiv()"--%>
<%--					onmouseout="showHelpDiv()"> 帮助中心</a>--%>
<%--				<img src="<%=stylePath%>/images/ico_new02.gif" />--%>
<%--			</p>--%>
		</div>
		<!-- 标题 end-->
		<!-- 提示信息 end-->
			<div id="div_help" class="prompt" style="display: none">
				<h3>
					<span>提示：</span>
				</h3>
				<p>
					<span>
						1、启用状态为“启用”的用户可正常登录系统，“停用”的用户无法登录。
						2、对用户设置分组后，该用户自动为启用状态。
					</span>
				</p>
				<a class="close" title="隐藏"
				   onclick="this.parentNode.style.display='none';"></a>
			</div>
		<!-- 提示信息 end-->

		<!-- 提示信息 end-->
<%--		<div class="prompt">--%>
<%--			<h3>--%>
<%--				<span>系统提示：</span>--%>
<%--			</h3>--%>
<%--			<p>--%>
<%--				鼠标移动到右上角--%>
<%--				<font color="blue">帮助中心</font>，可查看本模块的相关说明。--%>
<%--				</br>--%>
<%--				<span id="div_help" style="display: none"> </span>--%>
<%--			</p>--%>
<%--		</div>--%>
		<!-- 提示信息 end-->

		<html:form action="/xtwh_qxgl_yhgl" method="post">

			<!-- 隐藏域 -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<!-- 隐藏域 -->
			

			<!-- 多功能操作区 -->
			<div class="toolbox" id="dgncz">
				<!-- 按钮 -->
				<logic:equal name="writeAble" value="yes">
				<div class="buttonbox">
					<ul>
						<%-- 
						<li><a href="#" onclick="viewTempDiv('用户信息增加','addYhxx',350, 480);" class="btn_zj">增加</a></li>
						<li><a href="#" onclick="updateYhxx('show');return false;" class="btn_xg">修改</a></li>
						<li><a href="#" onclick="deleteYhxx();return false;" class="btn_sc">删除</a></li>						
						--%>
						<li><a href="#" onclick="yhfz('show');return false;" class="btn_xg">分组</a></li>
						<%-- <li><a href="#" onclick="qy('1');return false;" class="btn_shtg">启用</a></li>--%>
						<li><a href="#" onclick="qy('0');return false;" class="btn_shbtg">停用</a></li>
						<li><a href="#" onclick="gnsq();return false;" class="btn_sq">功能授权</a></li>					
						<li><a href="#" onclick="mmcsh('show');return false;" class="btn_csh">密码初始化</a></li>
						<logic:equal value="12898" name="xxdm">
						<li><a href="#" onclick="szkj('0');return false;" class="btn_shtg">思政可见</a></li>
						<li><a href="#" onclick="szkj('1');return false;" class="btn_shbtg">思政屏蔽</a></li>
						</logic:equal>
					</ul>
				</div>
				</logic:equal>
				<!-- 过滤条件 -->
				<div class="searchtab">
					<table width="100%" border="0">
						<tfoot>
							<tr>
								<td colspan="4">
									<input type="hidden" name="go" value="a" />
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
						</tfoot>
						<tbody id="tbody_search_query">
							<tr>
								<th>
									用户名
								</th>
								<td>
									<input type="text" name="yhm" id="yhm" style="width:200px" maxlength="20" 
										onkeypress="if(pressEnter(event)){searchRs();return false;}"/>
								</td>								
								<th>
									姓名
								</th>
								<td>
									<input type="text" name="xm" id="xm" style="width:200px" maxlength="20" 
										onkeypress="if(pressEnter(event)){searchRs();return false;}"/>
								</td>						
							</tr>
							<tr>
								<th>
									所属组
								</th>
								<td>
									<html:select property="zdm" style="width:200px" styleId="zdm">
										<html:option value="">--请选择--</html:option>
										<html:options collection="yhzList" property="dm" labelProperty="mc" />
									</html:select>
								</td>
								<th>
									所属部门
								</th>
								<td>							
									<html:select property="szbm" style="width:200px" styleId="szbm">
										<html:option value="">--请选择--</html:option>
										<html:options collection="yjbmList" property="bmdm" labelProperty="bmqc" />
									</html:select>
								</td>	
							</tr>
							<tr>
								<th>
									是否分组
								</th>
								<td>
									<html:select property="sffz" style="width:200px" styleId="sffz">
										<html:option value="">--请选择--</html:option>
										<html:option value="是">是</html:option>
										<html:option value="否">否</html:option>
									</html:select>
								</td>
								<th>
									启用状态
								</th>
								<td>
									<html:select property="qx" style="width:200px" styleId="qx">
										<html:option value="">--请选择--</html:option>
										<html:option value="1">启用</html:option>
										<html:option value="0">停用</html:option>
									</html:select>
								</td>
							</tr>
							<tr>
								<th>
									是否辅导员
								</th>
								<td>
									<html:select property="sffdy" style="width:200px" styleId="sffdy">
										<html:option value="">--请选择--</html:option>
										<html:option value="是">是</html:option>
										<html:option value="否">否</html:option>
									</html:select>
								</td>
								<th>
									是否班主任
								</th>
								<td>
									<html:select property="sfbzr" style="width:200px" styleId="sfbzr">
										<html:option value="">--请选择--</html:option>
										<html:option value="是">是</html:option>
										<html:option value="否">否</html:option>
									</html:select>
								</td>
							</tr>
							<logic:equal value="12898" name="xxdm">
							<tr>
								<th>
									是否思政可见
								</th>
								<td>
									<html:select property="sfbl" style="width:200px" styleId="sfbl">
										<html:option value="">--请选择--</html:option>
										<html:option value="是">是</html:option>
										<html:option value="否">否</html:option>
									</html:select>
								</td>
							</tr>
							</logic:equal>
						</tbody>
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
				<jsp:include flush="true" page="/sjcz/turnpage.jsp?form=yhglNewForm"></jsp:include>
				<!--分页显示-->
			</div>
			<!-- 内容显示区 end-->
			<%@ include file="yhglCz.jsp"%>

			<!-- 提示信息 -->
			<%@ include file="/comm/other/tsxx.jsp"%>
		</html:form>
	</body>
</html>
