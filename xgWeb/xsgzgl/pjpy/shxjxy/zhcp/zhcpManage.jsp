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
		
		jQuery(function(){
		
			var operation='${operation}';
			
			if(operation=="no"){
				jQuery(".buttonbox").children().find("li").each(function(){
					jQuery(this).attr("disabled",true);
				});
			}
		

			onShow();

		});
		
		//初始化
		function onShow(){ 
		
			
			var id = "left_a_0";
			if($(id)){
				$(id).click();
			}else{
				$("czxm").value = 'zd1';
				$("czxm").value = 'zd1';
				searchRs();
			}
			
			//
		}

		function initBtn(){
		
			if($("li_bc") && $("li_tb")){
				
				if($("hid_lrf")){
					if($("hid_lrf").value=="no"){
						$("a_btn_bc").disabled=true;
					}else{
						$("a_btn_bc").disabled="";
					}
				}
				
				if($("hid_lyf")){
					if($("hid_lyf").value=="no"){
						$("a_btn_tb").disabled=true;
					}else{
						$("a_btn_tb").disabled="";
					}
				}
				
			}
		}


		//查询结果集
		function searchRs(){

			//按钮控制
			if(checkHadEdit()){
				//controlBtn();
				var url = "general_zhcp_ajax.do?method=searchZhcpZcxx";
				var xmdm = $("shxm").value;
				var xmmc = escape($("xmmc_"+xmdm).value);
				var lyb = escape($("lyb_"+xmdm).value);
				var xmjb = escape($("xmjb_"+xmdm).value);
				var ie = "ie";
	
				var otherValue = [ie,xmdm,xmmc,lyb,xmjb];
	
				//showWaitingDiv("1000");
				
	<%--			if(checkSearch()){--%>
				searchRsByAjax(url,otherValue);
				$("is_default").value = "no";
				
				setTimeout("initBtn()","1500");
	<%--			}--%>
			}
		}
		
		//检查是否修改
		function saveMethod(){
			confirmInfo('您已经修改了相关分数，请问是否需要保存？',save);
		}


		//检验可否查询
		function checkSearch(){
		
			var flag = true;
			var is_default = $("is_default").value;
			
			if(is_default!=""){
				
				var xn_num =  jQuery("a[name=a_name_xn]").length;
				
				if(xn_num != "1"){
					alertError("学年条件不可为空，且只能选择一个！");
					flag = false;
				}
			}
			return flag;
		}
		
		//显示字段修改
		function showEditDiv(zd,zdid){
			
			var url="general_zhcp_ajax.do?method=showZdxgDiv";
			
			//其他数据
		 	var parameter = {
				"zd":zd,
				"zdid":zdid
			};
		  	
			jQuery("#div_zdxg").load(url,parameter,function(){
			
				var sqqx = jQuery("#sqqx").val();
				
				if(sqqx == "no"){
					$("btn_bc").style.display = "none";
				}
				
				jQuery("#"+zd+"_id").val(jQuery("#"+zdid).val());
				
				tipsWindown("系统提示","id:div_zdxg","350","250","true","","true","id");
			});
		}
		
		
		function affirmValue(zd,zdid){
			jQuery("#"+zdid).val(jQuery("#"+zd+"_id").val());
			closeWindown();
		}
		
		//保存综合测评信息
		function saveZhcpInfo(){
			
			confirmInfo("该操作将会更新相关信息，是否确定继续操作？",save);
			
		}
		
		function save(tag){
			if(tag=="ok"){
				var xmdm=jQuery("#shxm").val();
				//主键
				var pkValue=new Array();
	
				jQuery.ajaxSetup({async:false});
				
				// 得到JSON对象
		        var parameter ={};
				
				//遍历扩展字段数组
				jQuery("input[name=kzzdArr]").each(function(i){
						// 获取扩展字段数组的值
						var kzzd=jQuery(this).val();
						
						// 创建一个用来存放信息的数组;
						var array=new Array();
						
						jQuery("input[name="+kzzd+"],select[name="+kzzd+"]").each(function(j){
							
								array[j] =escape(jQuery(this).val());
							
						});
					
						//将该扩展字段存入JSON对象
						parameter[kzzd]=array.join(" !!@@!! ");
						
				});
				
				//遍历综测子项
				jQuery("input[name=bczdArr]").each(function(i){
				
						// 获取扩展字段数组的值
						var zczx=jQuery(this).val();
						
						// 创建一个用来存放信息的数组;
						var array=new Array();
						
						jQuery("input[name="+zczx+"],select[name="+zczx+"]").each(function(j){
							
								array[j] =escape(jQuery(this).val());
							
						});
					
						//将该综测子项存入JSON对象
						parameter[zczx]=array.join(" !!@@!! ");
						
				});
					
					
					
				jQuery("[name=div_pkValue]").each(function(i){
							
					pkValue[i] =escape(jQuery(this).val());
							
				});
				
				
				var url = "general_zhcp_ajax.do?method=saveZhcpInfo";
	          	 
			 	 parameter["xmdm"]=xmdm;
				 parameter["pkValue"]=pkValue.join("!!@@!!");
	
			 	$("divWaiting").style.display="";
				$("divDisable").style.display="";
				
				jQuery.post(url,
					parameter,
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
			$("had_edit").value="no";
		}
		
		//同步基本数据
		function updateLybInfo(){
			
			confirmInfo("该操作将会同步其它来源数据，是否确定继续操作？",function(tag){
				if(tag=="ok"){
				
					$("divWaiting").style.display="";
					$("divDisable").style.display="";
				
					setTimeout("tbcz()","1000")
				}
			});
			
		}
		
		function tbcz(){
		
			var xmdm=jQuery("#shxm").val();
					//主键
					var pkValue=new Array();
		
					jQuery.ajaxSetup({async:false});
					
					// 得到JSON对象
			        var parameter ={};
					
					var url = "general_zhcp_ajax.do?method=updateLybInfo";
		          	 
				 	 parameter["xmdm"]=xmdm;
				
		
				 	
					
					jQuery.post(url,
						parameter,
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
		
		function checkItsDis(obj){
			
			if(obj.disabled){
				
				return false;
			}else{
				
				return true;
			}		
		}
		</script>
	</head>
	<body >

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
					1.本功能默认展示的是<font color="blue">本评奖周期</font>的信息。 <br/>
					2.<font color="blue">保存</font>功能：对修改的综测分信息进行保存操作。 <br/>
					3.<font color="blue">同步</font>功能：同步其他来源数据到综测中。<br/>
					4.如果查询结果为<font color="blue">空</font>，可能是<font color="blue">评奖人员</font>未设置，请联系管理员。<br/>
					5.如果综测项目为<font color="blue">空</font>，可能是<font color="blue">综测项目</font>未设置，请联系管理员。<br/>
				</span>
			</p>
			<a class="close" title="隐藏"  onclick="this.parentNode.style.display='none';"></a>
		</div>
		<!-- 提示信息 end-->

		<html:form action="/general_pjpy" method="post">

			<!-- 隐藏域 -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<!-- 是否初始化  -->
			<input type="hidden" name="had_edit" id="had_edit" value="no" />
			<input type="hidden" name="is_default" id="is_default" value="" />
			<input type="hidden" name="shxm" id="shxm" value="${czxm }" />
			<input type="hidden" name="pjxn" id="pjxn" value="${pjxn }" />
			<input type="hidden" name="pjxq" id="pjxq" value="${pjxq }" />
			<input type="hidden" name="operation" id="operation" value="${operation }" />
			<logic:empty name="cshXmList">
				<input type="hidden" name="xmmc_zd1" id="xmmc_zd1" value="${xmmc}" />
				<input type="hidden" name="lyb_zd1" id="lyb_zd1"  value="${lyb}" />
				<input type="hidden" name="xmjb_zd1" id="xmjb_zd1"   value="${xmjb}"/>
			</logic:empty>
			<!-- 隐藏域 end-->

			<!-- 多功能操作区 -->
			<div class="toolbox">
				<!-- 按钮 -->
				<logic:equal name="writeAble" value="yes">
				<div class="buttonbox">
					<ul>
						<!-- 页面来源 -->
						<logic:equal name="forward" value="jbsz">
							<li>
								<a href="#" onclick="goPjpyJbsz();return false;" class="btn_fh">
									返回设置
								</a>
							</li>
						</logic:equal>
						<!-- 页面来源 end-->
						<!-- 读写权 -->
						<logic:equal name="writeAble" value="yes">
							<!-- 可否执行 -->
							<li id="li_bc" >
								<a href="#" id="a_btn_bc" disabled="true" onclick="if(checkItsDis(this)){saveZhcpInfo();}return false;" class="btn_yl">
									保 存 </a>
							</li>
							<li id="li_tb"  >
								<a href="#" id="a_btn_tb" disabled="true" onclick="if(checkItsDis(this)){updateLybInfo();}return false;" class="btn_sx">
									同 步 </a>
							</li>
							<!-- 可否执行 end-->
						</logic:equal>
						<!-- 读写权 end-->
					</ul>
				</div>
				</logic:equal>
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
				<logic:notEmpty name="cshXmList">
					<tr>
						<table align="center" width="100%">
						<td width="15%" valign="top" style="overflow-x: auto;">
							<!-- 侧边栏 -->
							<div class="listbox">
								<div class="titlelist" style="height: 352px;">
									<ul id="left_ul">
										<logic:notEmpty name="cshXmList">
											<logic:iterate id="xmnr" name="cshXmList" indexId="index">
												<li id="li_${index}" class="Child">
													<a href="#" name="left_a" id="left_a_${index}"
														onclick="if(checkHadEdit()){setLiClick('${index}');searchRs();};return false;">
														<span class="ico"></span>${xmnr.xmmc} </a>
													<input type="hidden" id="xmdm_${index }"
														value="${xmnr.xmdm}" />
													<input type="hidden" id="xmmc_${xmnr.xmdm}"
														value="${xmnr.xmmc}" />
													<input type="hidden" id="xmjb_${xmnr.xmdm}"
														value="${xmnr.xmjb}" />
													<input type="hidden" id="lyb_${xmnr.xmdm}"
														value="${xmnr.lyb}" />
												</li>
											</logic:iterate>
										</logic:notEmpty>
									</ul>
								</div>
							</div>
							<!-- 侧边栏 end-->
						</td>
						</logic:notEmpty>
						<td width="85%" valign="top" style="position: relative;">
							<logic:empty name="cshXmList">	
								<script language="javascript" defer="defer">			
								jQuery(function(){
									$("div_rs").style.width="100%";
								});
								</script>
							</logic:empty>
							<div id="div_rs"
								style="width:680px;height:400px;overflow-x:auto;overflow-y:auto;">
							</div>
						</td>
					</tr>
				</table>
				
				<!--分页显示-->
				<jsp:include flush="true"
					page="/sjcz/turnpage.jsp?form=generalPjpyGeneralForm"></jsp:include>
				<script type="text/javascript">
						$('choose').className="hide";
				</script>
				<!--分页显示-->
			</div>
			<!-- 内容显示区 end-->

			<!-- 字段修改弹出层 -->
			<div id="div_zdxg" style="display:none">
			</div>
			<!-- 提示信息 -->
			<%@ include file="/comm/other/tsxx.jsp"%>
		</html:form>
	</body>
</html>
