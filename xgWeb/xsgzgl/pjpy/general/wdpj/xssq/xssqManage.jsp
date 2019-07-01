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
			searchRs();
		}

		//查询结果集
		function searchRs(){
			
			jQuery.ajaxSetup({async:false});
			
			var url = "general_wdpj_xssq_ajax.do?method=searchWdpjXssq";
			var ie = "ie";
			var xmlx = jQuery("#xmlx").val();//项目类型
			if(xmlx == ""){
				xmlx = " ";
			}
			var xmxz = jQuery("#xmxz").val();//项目性质
			if(xmxz == ""){
				xmxz = " ";
			}
			var xmmc = escape(jQuery("#xmmc").val());//项目名称
			if(xmmc == ""){
				xmmc = " ";
			}
			
			var otherValue = [ie,xmlx,xmxz,xmmc];
		
			$("divWaiting").style.display="";
			$("divDisable").style.display="";
			
			searchRsByAjax(url,otherValue);

			$("divWaiting").style.display="none";
			$("divDisable").style.display="none";
			
			jQuery.ajaxSetup({async:true});
		}

		//显示学生申请(修改)div
		function showDiv(lx,xmdm){

			var url="general_wdpj_xssq_ajax.do?method=showXssqDiv";
			
			var opera=lx;
			
			//其他数据
		 	var parameter = {
				"opera":opera,
				"xmdm":xmdm
			};
		  	
			jQuery("#div_modi").load(url,parameter,function(){
			
				tipsWindown("系统提示","id:div_modi","600","380","true","","true","id");
			});
		}
		
		//判断学生申请项目资格
		function checkXssqZg(lx,xmdm){

			var url="general_wdpj_xssq_ajax.do?method=checkXssqZg";
			
			var opera=lx;
			
			//其他数据
		 	var parameter = {
				"xmdm":xmdm
			};
		  	
			jQuery.post(url,parameter,
				function(result){
					$("divWaiting").style.display="none";
					$("divDisable").style.display="none";
					if(result==""){
						showDiv(lx,xmdm);
					}else{
						alertInfo(result);
					}
					
				}
			);
		}
		
		function yzSqly(){
			var sqlylen = jQuery("#sqly").val();
			if(sqlylen.length<300){
				alertInfo("请输入至少300个字!");
				return false;
			}else if(sqlylen.length>500){
				alertInfo("不能多于500个字!");
				return false;
			}else{
				return true;
			}
		}
	     
		 function save(opera,xmdm){
     		
     		var message="";
     		
     		if(opera=="add"){
     		
     			message="是否确定要<font color=\"blue\">申请</font>该评奖项目？";
     		
     		}else{
     		
     			message="是否确定要<font color=\"blue\">修改</font>评奖申请信息？";
     		
     		}
     		
     		confirmInfo(message,function(tag){
				
				if(tag=="ok"){
					
					//主键
					var pkValue=new Array();
		
					jQuery.ajaxSetup({async:false});
					
					// 得到JSON对象
			        var parameter ={};
					
					parameter["xmdm"]=xmdm;
					
					parameter["opera"]=opera;
					
					parameter["sqly"]=escape(jQuery("#sqly").val());
					
					var url = "general_wdpj_xssq_ajax.do?method=saveXssqInfo";

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
		
		function saveBJLH(opera,xmdm){
			if(!yzSqly()){
				return false;
			}
     		
     		var message="";
     		
     		if(opera=="add"){
     		
     			message="是否确定要<font color=\"blue\">申请</font>该评奖项目？";
     		
     		}else{
     		
     			message="是否确定要<font color=\"blue\">修改</font>评奖申请信息？";
     		
     		}
     		
     		confirmInfo(message,function(tag){
				
				if(tag=="ok"){
					
					//主键
					var pkValue=new Array();
		
					jQuery.ajaxSetup({async:false});
					
					// 得到JSON对象
			        var parameter ={};
					
					parameter["xmdm"]=xmdm;
					
					parameter["opera"]=opera;
					
					parameter["sqly"]=escape(jQuery("#sqly").val());
					
					var url = "general_wdpj_xssq_ajax.do?method=saveXssqInfo";
		          	
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
		
		function disfrockXssqInfo(pkValue){
			
			confirmInfo("该操作将会撤销申请信息，是否确定继续操作？",function(tag){
				
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
				1.结果集中展示的评奖项目是<font color="blue">本次评奖周期</font>内，申请方式为<font color="blue">学生申请</font>的所有项目。<br/>
				2.如果您想对某项目进行申请的话，请点击每个项目自带的<font color="blue">申请</font>链接。<br/>
				3.如果您因为某中情况<font color="blue">不能申请</font>某项目的话，在<font color="blue">点击申请</font>的时候，系统会对您进行相关提示。<br/>
				4.如果您已经申请了某项目，而没有任一用户进行审核的话，您可以对申请记录进行<font color="blue">修改</font>或<font color="blue">撤销</font>操作。<br/>
				</span>
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
						<li>
							<a href="#" onclick="goPjpyWdpj();return false;" class="btn_fh">
								返回我的评奖
							</a>
						</li>
					</ul>
				</div>
				<!-- 按钮 end-->
				
				<div class="searchtab">
					<table width="100%" border="0">
						<tbody>
							<tr>
								<th>
									项目类型
								</th>
								<td>
									<select id="xmlx" style="width:150px">
										<option value=""></option>
										<option value="01">
											奖学金
										</option>
										<option value="02">
											荣誉称号
										</option>
									</select>
								</td>
								<th>
									项目性质
								</th>
								<td>
									<select id="xmxz" style="width:150px">
										<option value=""></option>
										<logic:iterate name="xmxzList" id="xmxz">
											<option value="${xmxz.dm }">
												${xmxz.mc }
											</option>
										</logic:iterate>
									</select>
								</td>
								<th>
									项目名称
								</th>
								<td>
									<input type="text" id="xmmc" style="width:150px" />
									<input type="text" id="hidden_xmmc" style="display:none"/>
								</td>
							</tr>
						</tbody>
						<tfoot>
							<tr>
								<td colspan="6">
									<div class="btn">
										<input type="hidden" name="go" value="a" />
										<button type="button"  class="btn_cx" id="search_go" onclick="searchRs();return false;">
											查 询
										</button>
										<button type="button"  class="btn_cz" id="btn_cz" onclick="searchReset();return false;">
											重 置
										</button>
									</div>
								</td>
							</tr>
						</tfoot>
					</table>
				</div>
				
				<div style="display:none">
				<!-- 过滤条件 -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- 过滤条件 end-->
				</div>
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
				<div id="div_rs" style="">
				
				</div>
				<!--分页显示-->
				<jsp:include flush="true" page="/sjcz/turnpage.jsp?form=generalPjpyGeneralForm"></jsp:include>
				<script type="text/javascript">
						$('choose').className="hide";
				</script>
				<!--分页显示-->
			</div>
			<!-- 内容显示区开始 end-->
			
			<!-- 我的评奖弹出层 -->
			<div id="div_001" style="display:none">
				<div class="open_win01" style="">
					<table width="100%" border="0" class="formlist">
						<thead>
							<tr>
								<th>
									<span>申请受限信息</span>
								</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td>
									1.申请时间未到，不能申请<br />
									2.该项目要求无不及格科目，您在评奖周期中存在语文课58分，不能申请<br />
									3.你已经申请了一等奖学金，与本项目不可兼得<br />
									4.本项目人数上限为3人(班级)，目前已经达到上限，您不能申请
								</td>
							</tr>
						</tbody>
						<tfoot>
							<tr>
								<td>
									<div class="btn">
										<button type="button"  onclick="closeWindown();return false;">关 闭</button>
									</div>
								</td>
							</tr>
						</tfoot>
					</table>
				</div>
			</div>
				
			<!-- 我的评奖弹出层 -->
			<div id="div_002" style="display:none">
				<div class="open_win01" style="">
					<table width="100%" border="0" class="formlist">
						<thead>
							<tr>
								<th colspan="2">
									<span>项目说明</span>
								</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<th width="25%">
									项目说明
								</th>
								<td style="word-break:break-all;">
									牛B的奖项！牛B的奖项！牛B的奖项！牛B的奖项！牛B的奖项！牛B的奖项！牛B的奖项！牛B的奖项！牛B的奖项！牛B的奖项！牛B的奖项！牛B的奖项！牛B的奖项！牛B的奖项！
								</td>
							</tr>
							<tr>
								<th>
									申请理由
								</th>
								<td style="word-break:break-all;">
									<textarea rows="8" cols="" style="width:99%"></textarea>
								</td>
							</tr>
						</tbody>
						<tfoot>
							<tr>
								<td colspan="2">
									<div class="btn">
										<button type="button"  onclick="closeWindown();return false;">保 存</button>
										<button type="button"  onclick="closeWindown();return false;">关 闭</button>
									</div>
								</td>
							</tr>
						</tfoot>
					</table>
				</div>
			</div>
			<!-- 我的评奖弹出层 end-->
				
			<!-- 我的评奖弹出层 -->
			<div id="div_modi" style="display:none">
				
			</div>
			<!-- 我的评奖弹出层 end-->
					
				
				
			<!-- 提示信息 -->
			<%@ include file="/comm/other/tsxx.jsp"%>
		</html:form>
	</body>
</html>