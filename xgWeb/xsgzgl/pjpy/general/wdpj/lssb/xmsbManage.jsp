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
			searchRs();
		}

		//查询结果集
		function searchRs(){

			jQuery.ajaxSetup({async:false});
			
			var yhlx = $("yhlx").value;
			var url = "general_wdpj_lssb_ajax.do?method=searchWdpjLssb";
			var ie = "ie";
			var xmdm = $("xmdm").value;
			var bjdm = $("bjdm").value;
			
			var otherValue = [ie,xmdm,bjdm];

			$("divWaiting").style.display="";
			$("divDisable").style.display="";
			
			searchRsByAjax(url,otherValue);

			$("divWaiting").style.display="none";
			$("divDisable").style.display="none";
			
			jQuery.ajaxSetup({async:true});
			
			if($("tsxxInfo")){
				jQuery("#span_xmts").html($("tsxxInfo").value);
			}
		}

		 function save(opera,xmdm,xh){
     		
     		confirmInfo("您是否确认要<font color='blue'>上报该学生</font>？",function(tag){
				
				if(tag=="ok"){
					
					//主键
					var pkValue=new Array();
		
					jQuery.ajaxSetup({async:false});
					
					// 得到JSON对象
			        var parameter ={};
					
					parameter["xmdm"]=xmdm;
					
					parameter["xh"]=xh;
					
					parameter["opera"]=opera;
					
					parameter["sqly"]=escape(jQuery("#sqly").val());
					parameter["kzzd1"]=escape(jQuery("#hjqk").val());
					parameter["kzzd2"]=escape(jQuery("#yjjl").val());
					parameter["kzzd3"]=escape(jQuery("#xjjl").val());
					parameter["kzzd4"]=escape(jQuery("#sjjl").val());
					
					var url = "general_wdpj_lssb_ajax.do?method=saveXmsb";
		          	
				 	$("divWaiting").style.display="";
					$("divDisable").style.display="";
					
					jQuery.post(url,parameter,
						function(result){
							$("divWaiting").style.display="none";
							$("divDisable").style.display="none";
							alertInfo(result,function(tag){
							
								if(tag=="ok"){
									
									closeWindown();
								
									searchRs();
								}
							});
							
						}
					);
					
					jQuery.ajaxSetup({async:true});
				}
				
			});
		}
		
		//显示学生详细信息
		function showXsxxDiv(xh){
		
			var url="general_wdpj_lssb_ajax.do?method=showXsxxDiv";
			
			var xmdm=$("xmdm").value;
			//其他数据
		 	var parameter = {
				"xh":xh,
				"xmdm":xmdm
			};
			
			jQuery("#div_xsxx").load(url,parameter,function(){
			
				tipsWindown("系统提示","id:div_xsxx","600","400","true","","true","id");
			});
		}
		
		//显示学生申请(修改)div
		function showDiv(lx,xh){

			var url="general_wdpj_lssb_ajax.do?method=showLssbDiv";
			// 操作类型
			var opera=lx;
			// 项目代码
			var xmdm=$("xmdm").value;
			
			//其他数据
		 	var parameter = {
				"opera":opera,
				"xh":xh,
				"xmdm":xmdm
			};
		  	
			jQuery("#div_modi").load(url,parameter,function(){
			
				tipsWindown("系统提示","id:div_modi","600","380","true","","true","id");
			});
		}
		
		//判断学生申请项目资格
		function checkXssqZg(lx,xh){

			var url="general_wdpj_lssb_ajax.do?method=checkXssbZg";
			var xmdm=$("xmdm").value;
			var opera=lx;
			
			//其他数据
		 	var parameter = {
				"xmdm":xmdm,
				"xh":xh
			};
		  	
			jQuery.post(url,parameter,
				function(result){
					$("divWaiting").style.display="none";
					$("divDisable").style.display="none";
					if(result==""){
						showDiv(lx,xh);
					}else{
						alertInfo(result);
					}
					
				}
			);
		}
		
		// 撤销上报
		function disfrockXssqInfo(pkValue){
			
			confirmInfo("该操作将会撤销申请信息，是否确定继续该操作？",function(tag){
				
				if(tag=="ok"){
					
					var xh=new Array();
					
					var parameter={}
					
					
					parameter["pkValue"]=escape(pkValue);
					
					var url= "general_wdpj_xssq_ajax.do?method=disfrockXssqInfo";
					
					jQuery.ajaxSetup({async:false});	
					
					jQuery.post(url,
						parameter,
						function(result){
						
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
		
		function checkLssb(){
		
			var bjdm=jQuery("#bj").val();
			var xmdm=jQuery("#xmmc").val();
			
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
				var url = "general_pjpy.do?method=xmsbManage";
					url+= "&xmdm="+xmdm;
					url+= "&bjdm="+bjdm;
					
				refreshForm(url);
			}
		}
		
		//显示老师上报Div
		function showLssbDiv(){
			tipsWindown("系统提示","id:div_lssb","400","300","true","","true","id");
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
		<div class="prompt"  id="div_help" style="display: none">
			<h3>
				<span>提示：</span>
			</h3>
			<p>
				<span id="span_xmts"></span>
				<span>
				1.本页面展示您在前页面所选班级的<font color="blue">所有学生</font>，根据综合分班级排名<font color="blue">升序排列</font>。<br/>
				2.如果您觉得某学生可以被上报该项目，请点击<font color="blue">上报</font>。<br/>
				3.已经上报的学生，如果还没有被任一用户审核过的话，可以点击<font color="blue">撤销</font>取消上报。<br/>
				4.<font color="blue">点击学生的学号</font>，可以展示该学生的详细信息。<br/>
				</span>
			</p>
			<a class="close" title="隐藏"  onclick="this.parentNode.style.display='none';"></a>
		</div>
		<!-- 提示信息 end-->
		
		<html:form action="/general_pjpy" method="post">
			<!-- 隐藏域 -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<input type="hidden" name="xmdm" id="xmdm" value="${xmdm}"/>
			<input type="hidden" name="bjdm" id="bjdm" value="${bjdm}"/>
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
							<a href="#" id="btn_sx" 
								onclick="showLssbDiv();return false;"
								class="btn_sx">
								<span>切换班级或项目</span>
							</a>
						</li>
					</ul>
				</div>
				<!-- 按钮 end-->
				
				<div style="display:none">
				<!-- 过滤条件 -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- 过滤条件 end-->
				</div>
			</div>
			
			<!-- 内容显示区开始 -->
			<div class="main_box" style="overflow-x:hidden;overflow-y:auto;height:480px;width:99.5%;vertical-align: top">
				<table align="center" width="98%">
					<tr style="">
						<td width="100%" valign="top" style="">
							<div id="div_rs" style="">
							</div>
						</td>
					</tr>
				</table>
			</div>
			<!-- 内容显示区开始 end-->	
			<div id="div_modi" style="display:none">
				
			</div>
			
			<div id="div_xsxx" style="display:none">
				
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
									<html:select property="xmmc" styleId="xmmc" style="width:200px">
										<html:option value=""></html:option>
										<html:options collection="xmList" property="xmdm"
											labelProperty="xmmc" />
									</html:select>
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
								</td>
							</tr>
						</tbody>
						<tfoot>
							<tr>
								<td colspan="2">
									<div class="btn">
										<button type="button"  onclick="checkLssb()">确 认</button>
										<button type="button"  onclick="closeWindown();return false;">关 闭</button>
									</div>
								</td>
							</tr>
						</tfoot>
					</table>
				</div>
			</div>
			<!-- 老师上报弹出层 end-->
			<!-- 提示信息 -->
			<%@ include file="/comm/other/tsxx.jsp"%>
		</html:form>
	</body>
</html>