<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- 作者：伟大的骆 -->
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script type='text/javascript' src='/xgxt/dwr/interface/getCommPjpy.js'></script>
		<script language="javascript" defer="defer">
		//初始化
		function onShow(){
			
			var id = "left_a_0";
			var xmdm=$("czxm").value;
			if($(id) && xmdm==""){
				$(id).click();
			}else if(xmdm!=""){
				var xmdmArr=jQuery("input[name=xmdmArr]").each(function(i){
					var xmdmVal=jQuery(this).val();
					if(xmdmVal==xmdm){
						$("left_a_"+[i]).click();
					}
				});
				searchRs();
			}
			
			
		}

		//查询结果集
		function searchRs(){
			//按钮控制
<%--			controlBtn();--%>
			var url = "general_wdpj_xmsh_ajax.do?method=searchPjpyXmsh";
			var xmdm = $("shxm").value;
			var xmmc = escape($("xmmc_"+xmdm).value);
			var lyb = escape($("lyb_"+xmdm).value);
			var xmjb = escape($("xmjb_"+xmdm).value);
			var ie = "ie";
			
			var otherValue = [ie,xmdm,xmmc,stylePath];

			//showWaitingDiv("1000");
			searchRsByAjax(url,otherValue);
		}
		
		function pjxmsh(){
			
			var n=0;
			var pk=jQuery("input[name=pkValue]:checked");
			if(pk.length==0){
				alertInfo("请勾选需要审核的数据！");
				return false;
			}else if(pk.length==1){//勾选单条记录审核
				var xmdm=jQuery("#shxm").val();
				showWdpjXssq(xmdm,"sh",jQuery(pk[0]).val());
			}else{//勾选多条记录
				
				tipsWindown("系统提示","id:div_plsh","350","200","true","","true","id");
			}
			
			return false;
		}
		
		function saveShzt(shzt){
			
			var xh=new Array();
			jQuery("input[name=pkValue]:checked").each(function(i){
			
				xh[i]=jQuery(this).val();
			});
			
			
			var xmdm=jQuery("#shxm").val();
			
			var flag=true;
			
			if(shzt=="通过"){
			
				flag=checkShxz(xh,xmdm);
				
			}
			
			if(flag){
				var parameter={}
				
				parameter["xh"]=xh.join(" !!@@!! ");
				parameter["xmdm"]=xmdm;
				parameter["shzt"]=escape(shzt);
				parameter["shyj"]=escape(jQuery("#shyj").val());
				var url = "general_wdpj_xmsh_ajax.do?method=savePlShzt";
				
			 	$("divWaiting").style.display="";
				$("divDisable").style.display="";
					
				jQuery.post(url,
					parameter,
					function(result){
						$("divWaiting").style.display="none";
						$("divDisable").style.display="none";
						
						alertInfo(result);
						
						searchRs();
						closeWindown();	
					}
				);
				
				jQuery.ajaxSetup({async:true});
			}
			
		}
		
		//显示学生申请
		function showWdpjXssq(xmdm,opera,xh){
			
			var url = "general_pjpy.do?method=wdpjXmshDetail&xmdm="+xmdm;
			url+="&opera="+opera;
			url+="&xh="+xh
			showTopWin(url,"800","600");
		}
		
		function checkShxz(xhArr,xmdm){
			//判断学生学号
			
			var parameter={}
			
			parameter["array_xh"]=xhArr.join("!!array!!");;
			
			parameter["str_xmdm"]=xmdm;
			
			//保存URL
			jQuery.ajaxSetup({async:false});
			
			var url = "general_pjpy_xmsz_ajax.do?method=checkShxz";
			
			var flag=false;
			
			//------------条件判断 begin -------------
			jQuery.ajaxSetup({async:false});
				jQuery.post(url,
				parameter,
				function(result){
					
					$("divWaiting").style.display="none";
					$("divDisable").style.display="none";
					
					if(result!="null" && result!=""){
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
		
		jQuery(function(){
			onShow();
		})
		</script> 	
	</head>
	<body  >
		<!-- 标题 -->
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>评奖评优 - 我的评奖 - 项目审核</a>
			</p>

			<!-- 在线帮助 -->
			<p class="help">
				<a href="#" onclick="return false;" onmousedown ="showHelpDiv();">使用帮助</a>
			</p>
			<!-- 在线帮助 end -->
			
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
				<span>
				1.侧边栏中列出的项目为您<font color="blue">有资格审核,并且在审核时间内</font>的所有项目。</br>
				2.如果项目过多的话，您可以点击<font color="blue">查询项目</font>进行过滤。</br>
				3.如果您拥有多重审核身份，在审核前，系统会提示您确定需要以何种<font color="blue">身份</font>进行审核。</br>
				4.如果您勾选<font color="blue">一条</font>记录，点击<font color="blue">审核</font>，系统将展现该学生详细的审核页面。</br>
				5.如果您勾选<font color="blue">多条</font>记录，点击<font color="blue">审核</font>，系统将不展现详细页面，而执行批量审核操作。</br>
				6.如果您想查看某学生的详细申请信息，可以<font color="blue">双击</font>该条记录。
				</span>
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
						<a href="#" onclick="showTopWin('/xgxt/pjpyXmsb.do?method=sbfwChoose',600,550);return false;">
				    		<img src="<%=stylePath%>/images/blue/48-1/Function46.png" />
							<p>老师上报</p>
						</a>
					</div>
					
					<div class="liucheng_font floatleft">
						<a href="#" onclick="goOtherGnmk('pjpy_pjlc_jgcx.do?shzt=');return false;">
				    		<img src="<%=stylePath%>/images/blue/48-1/Function12.png" />
							<p>结果查询</p>
						</a>
					</div>
					
				</div>
			</div>
		</div>
		<!-- 快捷方式 end-->

		<html:form action="/pjpyXmsh">
		
			<!-- 隐藏域 -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<input type="hidden" name="shxm" id="shxm" value="${xmdm}"/>
			<input type="hidden" name="spgw" id="spgw" value="${spgw }"/>
			<input type="hidden" name="shjb" id="shjb" value="${shjb }"/>
			
			<input type="hidden" id="hid_xmdm" value="${hid_xmdm }"/>
			<input type="hidden" id="hid_xmmc" value="${hid_xmmc }"/>
			<input type="hidden" id="hid_ywmc" value="${hid_ywmc }"/>
			<input type="hidden" id="hid_xmxz" value="${hid_xmxz }"/>
			<input type="hidden" id="hid_xmfw" value="${hid_xmfw }"/>
			<input type="hidden" id="hid_xmlx" value="${hid_xmlx }"/>
			<input type="hidden" name="bjdm" id="bjdm" value=""/>
			
			<!-- 隐藏域 end-->
			<!-- 多功能操作区 -->
			<div class="toolbox">
			
				<!-- 按钮 -->
				<div class="buttonbox">
					<ul>
						<li>
							<a href="#" id="btn_sh"
								onclick="pjxmsh();return false;"
								class="btn_sh">
								<bean:message key="lable.btn_sh" />
							</a>
							<logic:equal name="fwfs" value="homepage">
								<input type="hidden" name="fwfs" id="fwfs" value="${fwfs}"/>
								<li>
									<a href="#" onclick="returnHomPage('');return false;" class="btn_fh">返回</a>
								</li>
							</logic:equal>
							
						</li>
					</ul>
					<button type="button" class="btn_cx" id="search_go" style="display: none" onclick="searchRs();return false;"></button>
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
				<table align="center" width="100%">
					<tr>
						<td colspan="2">
							<h3 class="datetitle_01">
								<span>
									
								</span>
							</h3>
						</td>
					</tr>
					<tr>
						<td width="20%" valign="top" style="overflow-x: auto;">
							<!-- 侧边栏 -->
							<div class="listbox">	
								<div class="titlelist" style="height: 352px;">
									<ul id="left_ul">
										<logic:notEmpty name="cshXmList">
											<logic:iterate id="xmnr" name="cshXmList" indexId="index">	
												<li id="li_${index}" class="Child">
													<a href="#" name="left_a" id="left_a_${index}" 
														onclick="if(checkHadEdit()){setLiClick('${index}');searchRs();};return false;">
														<span class="ico"></span>${xmnr.xmmc}
													</a>
													<input type="hidden" name="xmdmArr" id="xmdm_${index }" value="${xmnr.xmdm}"/>
													<input type="hidden" id="xmmc_${xmnr.xmdm}" value="${xmnr.xmmc}"/>
													<input type="hidden" id="xmjb_${xmnr.xmdm}" value="${xmnr.xmjb}"/>
													<input type="hidden" id="lyb_${xmnr.xmdm}" value="${xmnr.lyb}"/>
												</li>
											</logic:iterate>
										</logic:notEmpty>
									</ul>
								</div>
							</div>
							<!-- 侧边栏 end-->
						</td>
						<td width="85%" valign="top" style="position: relative;">
							<div id="div_rs" style="width:100%;height:360px;overflow-x:auto;overflow-y:auto;">
							</div>
						</td>
					</tr>
				</table>
				<!--分页显示-->
				<jsp:include flush="true" page="/sjcz/turnpage.jsp?form=generalPjpyGeneralForm"></jsp:include>
<%--				<script type="text/javascript">--%>
<%--						$('choose').className="hide";--%>
<%--				</script>--%>
				<!--分页显示-->
			</div>
			<!-- 内容显示区 end-->
			<!-- 提示信息 -->
			<%@ include file="/comm/other/tsxx.jsp"%>
						<!-- 批量审核 DIV -->
			<div id="div_plsh" style="display:none">
				<div class="open_win01">
					<table align="center" class="formlist">
						<thead>
							<tr>
								<th colspan="2" >
									<span>批量审核</span>
								</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<th style="width:30%">
									审核意见
								</th>
								<td>
									<textarea  name='shyj' 
									id="shyj" style="word-break:break-all;width:85%" onblur="chLeng(this,500);"
										rows='4' ></textarea>
								</td>
							</tr>
						</tbody>
						<tfoot>
							<tr>
								<td colspan="2">
									<div class="bz">
										
									</div>
									<div class="btn">
										<logic:notEqual name="usertType" value="xy">
										<button type="button" name="退回" onclick="saveShzt('退回')">
											退  回
										</button>
										</logic:notEqual>
										<button type="button" name="通过" onclick="saveShzt('通过')">
											通  过
										</button>
										<button type="button" name="不通过" onclick="saveShzt('不通过')">
											不通过
										</button>
									</div>
								</td>
							</tr>
						</tfoot>
					</table>
				</div>
			</div>
		</html:form>
	</body>
</html>