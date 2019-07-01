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
		//页面初始化
		function onShow(){
			var userType = jQuery("#userType").val();
			if(userType == "stu"){
				$("span_stu").style.display="";
				$("span_tea").style.display="none";
			}else{
				$("span_stu").style.display="none";
				$("span_tea").style.display="";
			}
			
			searchRs();	
		}

		//查询结果集
		function searchRs(){

			jQuery.ajaxSetup({async:false});
			
			var yhlx = $("yhlx").value;
			var url = "general_pjpy_wdpj_ajax.do?method=searchPjpyWdpj";	
			var otherValue = [yhlx];

			$("divWaiting").style.display="";
			$("divDisable").style.display="";
			
			searchRsByAjax(url,otherValue);

			$("divWaiting").style.display="none";
			$("divDisable").style.display="none";
			
			jQuery.ajaxSetup({async:true});
			
			if($("operation")){
				if($("operation").value=="no"){
					if($("btn_lssb")){
						$("btn_lssb").disabled=true;
					}
					
					if($("btn_xssq")){
						$("btn_xssq").disabled=true;
					}
					
					jQuery(".a_lssh").each(function(){
						jQuery(this).attr("disabled",true);
					});
				}else{
					if($("btn_lssb")){
						$("btn_lssb").disabled=false;
						
					}
					
					if($("btn_xssq")){
						$("btn_xssq").disabled=false;
					}
					
					jQuery(".a_lssh").each(function(){
						jQuery(this).attr("disabled",false);
					});
				}
			}
		}

		//打印报表
		function printPj(xmmc,username){
				
				var num = jQuery("input[name=div_pkValue]:checked").length;
				var flag = false;
				var n = 0;
				var jxjName = xmmc;
				var xh = username;
					var xxdm = jQuery("#xxdm").val();
					
					if("11647" == xxdm || "12741" == xxdm || "10338" == xxdm || "10653" == xxdm){
						url = "general_pjpy_djbg.do?method=printDjbg";
						url+= "&str_xh=" + xh;
						url+= "&str_xmmc=" + jxjName;		
						
						document.forms[0].action = url;
						document.forms[0].target = "_blank";
						document.forms[0].submit();
					    document.forms[0].target = "_self";
					    
					}else{
						 document.forms[0].action = url+"&jxjName="+jxjName+"&xh="+xh;
						 document.forms[0].target = "_blank";
						 document.forms[0].submit();
						 document.forms[0].target = "_self";
					}
					
		    }

		//显示老师上报Div
		function showLssbDiv(){
			tipsWindown("系统提示","id:div_lssb","400","300","true","","true","id");
		}
		
		//显示学生申请(修改)div
		function showWdpjView(xmdm){
		
			var ie = "ie";
			
			var url="general_pjpy_wdpj_ajax.do?method=showWdpjView";
			
			//其他数据
		 	var parameter = {
		 		"ie":ie,
		 		"stylePath":stylePath,
				"xmdm":xmdm
			};
			jQuery("#div_detail").load(url,parameter,function(){
			
				tipsWindown("系统提示","id:div_detail","600","380","true","","true","id");
			});
		}
		
		//前往项目审核
		function showSpgw(xmdm){
		
			var url="general_wdpj_xmsh_ajax.do?method=showShgwDiv";
			
			//其他数据
		 	var parameter = {
				"xmdm":xmdm
			};
		  	
			jQuery("#div_spgw").load(url,parameter,function(){
			
				var len=jQuery("[name=spgw]").length;
			
				if(len==1){
					var spgw=jQuery("[name=spgw]:checked").val();
					goWdpjXmsh(xmdm,spgw);
					
				}else{
					tipsWindown("系统提示","id:div_spgw","300","170","true","","true","id");
				}
			});
		}
		
		function checkSpgw(){
		
			var xmdm=jQuery("#text_xmdm").val();
			var spgw=jQuery("[name=spgw]:checked").val();
			
			goWdpjXmsh(xmdm,spgw);
		}
		
		function checkLssb(){
		
			var bjdm=jQuery("#bj").val();
			var xmdm=jQuery("#xmdm").val();
			
			var flag=true;
			if(xmdm==""){
				alertInfo("上报项目不能为空!");
					
				flag= false;
				
			}
			if(bjdm=="" && flag){
				alertInfo("班级不能为空!");
				flag= false;
			}
			
			if(flag){
				goWdpjLssb();
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
				<em>您的当前位置：</em><a>${title }</a>
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
					<span id="span_stu" style="display: none">
					1.本页面展示的为<font color="blue">本评奖周期</font>内您<font color="blue">申请/被上报</font>的项目进展情况。<br/>
					2.点击<font color="blue">查看详细信息</font>可以查看该项目的具体审核情况。<br/>
					3.如果您想申请新的评奖项目，请点击<font color="blue">项目申请</font>。<br/>
					4.如果您想查看自己的评奖结果，请点击<font color="blue">评奖结果</font>。<br/>
					5.如果您的申请记录在此处查询不到，可能是本次评奖<font color="blue">已经结束</font>，请点击<font color="blue">评奖结果</font>，查看详情。<br/>
					6.如果您无法进入<font color="blue">项目申请</font>，可能是管理员还<font color="blue">未开放申请</font>。<br/>
					</span>
					
					<span id="span_tea" style="display: none">
					1.本页面展示的为<font color="blue">本评奖周期</font>内您<font color="blue">需要审核</font>的项目。<br/>
					2.需审核人数括号中<font color="blue">显示的数字</font>，标志您所在岗位目前有多少申请人<font color="blue">需要进行审核</font>。<br/>
					3.已审核人数括号中<font color="blue">显示的数字</font>，标志您所在岗位目前已经<font color="blue">审核过了</font>多少申请人。<br/>
					4.需审核是指申请记录为<font color="blue">未审核</font>与<font color="blue">需重审</font>，已审核是指申请记录为<font color="blue">通过</font>与<font color="blue">不通过</font>。<br />
					5.如果您想对某项目上报学生的话，请点击<font color="blue">老师上报</font>。<br />
					6.如果您想查看本次评奖的评奖结果，请点击<font color="blue">本次评奖结果</font>。<br />
					7.如果您想查看历史评奖的评奖结果，请点击<font color="blue">历史评奖结果</font>。<br />
					8.如果您想执行审核操作，请点击每个项目操作中的<font color="blue">审核</font>。<br />
					9.如果您不能执行<font color="blue">上报</font>和<font color="blue">审核</font>的工作，可能是由于管理员<font color="blue">已经终止</font>了该流程请联系管理员。<br />
					</span>
				</span>
			</p>
			<a class="close" title="隐藏"  onclick="this.parentNode.style.display='none';"></a>
		</div>
		<!-- 提示信息 end-->
		
		<html:form action="/general_pjpy" method="post">
			<!-- 隐藏域 -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<input type="hidden" name="operation" id="operation" value="${operation}"/>
<%--			<input type="hidden" name="xmdm" id="xmdm" value="" />--%>
			<button type="button" id="forward" onclick="goWdpjLssb()" style="display: none">跳转</button>
			<!-- 多功能操作区 -->
			<div class="toolbox">				
				<div style="display:none">
				<!-- 过滤条件 -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- 过滤条件 end-->
				</div>
			</div>
			
			<!-- 快捷方式 -->
			<div class="liucheng_xg_pj" style="">
			
				<!-- 学生版本 -->
				<logic:equal name="userType" value="stu">
					<div class="liucheng_bar">
						<h3>快<br/>捷<br/>功<br/>能</h3>
						<div class="con">
						    <div class="liucheng_font floatleft">
						    	<a href="#" onclick="if(checkItsDis(this)){goWdpjXssq();};return false;" disabled="true" id="btn_xssq">
						    		<img src="<%=stylePath%>/images/blue/48-1/Function18.png" />
									<p>项目申请</p>
								</a>   	
							</div>
							<div class="liucheng_font floatleft">
								<a href="#" onclick="goWdpjXsjg('zcjg');return false;">
						    		<img src="<%=stylePath%>/images/blue/48-1/Function61.png" />
									<p>综测结果</p>
								</a>
							</div>
							<div class="liucheng_font floatleft">
								<a href="#" onclick="goWdpjXsjg('pjjg');return false;">
						    		<img src="<%=stylePath%>/images/blue/48-1/Function12.png" />
									<p>评奖结果</p>
								</a>
							</div>
						</div>
					</div>
				</logic:equal>
				<!-- 学生版本  end-->
				
				<!-- 非学生版本 -->
				<logic:notEqual name="userType" value="stu">
					<div class="liucheng_bar">
						<h3>快<br/>捷<br/>功<br/>能</h3>
						<div class="con">
						   
							<div class="liucheng_font floatleft">
								<a href="#" onclick="if(checkItsDis(this)){showLssbDiv();};return false;" disabled="true" id="btn_lssb">
						    		<img src="<%=stylePath%>/images/blue/48-1/Function46.png" />
									<p>老师上报</p>
								</a>
							</div>
							
							<div class="liucheng_font floatleft">
								<a href="#" onclick="goWdpjBcpj();return false;">
						    		<img src="<%=stylePath%>/images/blue/48-1/Function12.png" />
									<p>本次评奖结果</p>
								</a>
							</div>
							
							<div class="liucheng_font floatleft">
								<a href="#" onclick="goWdpjLspj();return false;">
						    		<img src="<%=stylePath%>/images/blue/48-1/Function15.png" />
									<p>历史评奖结果</p>
								</a>
							</div>
							
						</div>
					</div>
				</logic:notEqual>
				<!-- 非学生版本end -->
				
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
					<table align="center" width="100%" id="wdpjTable">
						<tr style="">
							<td width="100%" valign="top" style="position: relative;">
								<div id="div_rs">
								
								</div>
							</td>
						</tr>
					</table>
					<!--分页显示-->
					<script type="text/javascript" defer="defer">
						setTimeout("$('choose').className='hide'",100);
					</script>
					<jsp:include flush="true" page="/sjcz/turnpage.jsp?form=generalPjpyGeneralForm"></jsp:include>
					<!--分页显示-->
				</div>
				<!-- 内容显示区开始 end-->
			</div>	
			
			<div id="div_detail" style="display:none">
				
			</div>
			<!-- 老师上报弹出层 begin-->
			<div id="div_lssb" style="display:none">
				<div class="open_win01" style="">
					<table width="100%" border="0" class="formlist">
						<thead>
							<tr>
								<th colspan="2">
									<span>上报相关选择</span>
								</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<th width="25%">
									<font color="red">*</font>上报项目
								</th>
								<td>
									<html:select property="xmdm" styleId="xmdm" style="width:200px">
										<html:option value=""></html:option>
										<html:options collection="xmList" property="xmdm"
											labelProperty="xmmc" />
									</html:select>
									<font color="blue">(必选)</font>
								</td>
							</tr>
							<tr>
								<th>
									年级
								</th>
								<td>
									<html:select property="nj" styleId="nj" style="width:200px"
										onchange="initZyList();initBjList();">
										<html:option value=""></html:option>
										<html:options collection="njList" property="nj"
											labelProperty="nj" />
									</html:select>
								</td>
							</tr>
							<tr>
								<th>
									院系
								</th>
								<td>
									<logic:equal name="userType" value="xy">
										<html:select property="xydm" styleId="xy" disabled="true"
											value="${userDep }" style="width:200px"
											onchange="initZyList();initBjList();">
											<html:option value=""></html:option>
											<html:options collection="xyList" property="xydm"
												labelProperty="xymc" />
										</html:select>
										<html:hidden property="xydm" value="${userDep }" />
									</logic:equal>
									<logic:notEqual name="userType" value="xy">
										<html:select property="xydm" styleId="xy" style="width:200px"
											onchange="initZyList();initBjList();">
											<html:option value=""></html:option>
											<html:options collection="xyList" property="xydm"
												labelProperty="xymc" />
										</html:select>
									</logic:notEqual>
								</td>
							</tr>
							<tr>
								<th>
									专业
								</th>
								<td>
									<html:select property="zydm" styleId="zy" style="width:200px"
										onchange="initBjList();">
										<html:option value=""></html:option>
										<html:options collection="zyList" property="zydm"
											labelProperty="zymc" />
									</html:select>
								</td>
							</tr>
							<tr>
								<th>
									<font color="red">*</font>班级
								</th>
								<td>
									<html:select property="bjdm" styleId="bj" style="width:200px">
										<html:option value=""></html:option>
										<html:options collection="bjList" property="bjdm"
											labelProperty="bjmc" />
									</html:select>
									<font color="blue">(必选)</font>
								</td>
							</tr>
						</tbody>
						<tfoot>
							<tr>
								<td colspan="2">
									<div class="btn">
										<button type="button" onclick="checkLssb()">确 认</button>
										<button type="button" onclick="closeWindown();return false;">关 闭</button>
									</div>
								</td>
							</tr>
						</tfoot>
					</table>
				</div>
			</div>
			<!-- 老师上报弹出层 end-->
				
			<!-- 岗位信息 begin -->
			<div id="div_gwxx" style="display:none">
				<div class="open_win01">
					<table width="100%" border="0" class="formlist">
						<thead>
							<tr>
								<th colspan="2">
									<span>审核岗位选择</span>
								</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<th width="25%">
									岗位选择
								</th>
								<td>
									<input type="radio" name="shgw" checked="checked"/>院系<br />
									<input type="radio" name="shgw"/>学校
								</td>
							</tr>
						</tbody>
						<tfoot>
							<tr>
								<td colspan="2">
									<div class="btn">
										<button type="button" onclick="closeWindown();return false;">确定</button>
										<button type="button" onclick="closeWindown();return false;">关闭</button>
									</div>
								</td>
							</tr>
						</tfoot>
					</table>
				</div>
			</div>
			<!-- 岗位信息 end-->
			
			<!-- 岗位信息 end-->
			
			<div id="div_spgw" style="display:none">

			</div>
				
			<!-- 提示信息 -->
			<%@ include file="/comm/other/tsxx.jsp"%>	
		</html:form>
	</body>
</html>