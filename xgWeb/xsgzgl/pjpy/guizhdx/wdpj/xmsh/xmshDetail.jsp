<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- 作者：伟大的骆 -->
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script language="javascript" src="js/check.js"></script>
		<script language="javascript">
		
		//查看操作时将输入框与文本域设为只读
		jQuery(function(){
			if(jQuery("#doType") && jQuery("#doType").val()=="view"){
				jQuery("input,textArea").attr("readonly",true);
			}
			
			if(jQuery("#userType") && jQuery("#userType").val()=="xy"){
				jQuery("#xxshyj").attr("readonly",true);
				
				jQuery("#sqly").attr("readonly",true);
			}else{
				jQuery("#sqly").attr("readonly",true);
				
				jQuery("#xyshyj").attr("readonly",true);
			}
		});
		
		//保存顺延项目信息
		function saveSyxm(){
			url = "/xgxt/pjpyXmsh.do?method=xmshDetail&doType=xmsy";
			saveUpdate(url,'');
		}
		
		//保存审核状态
		function saveShzt(shzt){
			
			
			var xh=jQuery("#xh").val();
			
			var xmdm=jQuery("#xmdm").val();
			
			var message="";
			
			if(shzt=="通过"){
				var xhArr=new Array();
				xhArr[0]=xh;
				message=checkShxz(xhArr,xmdm);
			}
			
			if(message!="" && message!="null"){
				
				flag=false;
				
			}else{
				flag=true;
			}
			
			if(flag){
			
				var parameter={}
				var shyj="";
				var userType=jQuery("#userType").val();
				
				if(userType=="xy"){
					shyj=jQuery("#xyshyj").val();
				}else{
					shyj=jQuery("#xxshyj").val();
				}
				parameter["xh"]=xh;
				parameter["xmdm"]=jQuery("#xmdm").val();
				parameter["shzt"]=escape(shzt);
				parameter["shyj"]=escape(shyj);
				var url = "general_wdpj_xmsh_ajax.do?method=savePlShzt";
				
				jQuery.post(url,
					parameter,
					function(result){
						
						alertInfo(result);
						
					}
				);
				
				jQuery.ajaxSetup({async:true});
			
			}else{
			
				confirmInfo(message+"是否要进行顺延操作？",function(tag){
					if(tag=="ok"){
						showXmsyDiv();
					}
				
				});
				
			}
		}
		
		//判断审核限制信息（人数、兼得）
		function checkShxz(xhArr,xmdm){
			
			var parameter={}
			
			parameter["array_xh"]=xhArr.join(" !!@@!! ");;
			
			parameter["str_xmdm"]=xmdm;
			
			//保存URL
			jQuery.ajaxSetup({async:false});
			
			var url = "general_pjpy_xmsz_ajax.do?method=checkShxz";
			
			var message="";
			
			//------------条件判断 begin -------------
			jQuery.ajaxSetup({async:false});
				jQuery.post(url,
				parameter,
				function(result){
					
					$("divWaiting").style.display="none";
					$("divDisable").style.display="none";
					
					message=result;
					
				}
			);
			jQuery.ajaxSetup({async:true});
			
			return  message;
		}
		
		//显示项目顺延DIV
		function showXmsyDiv(){
			
			var url="general_pjpy_xmsz_ajax.do?method=showXmsyDiv";
			
			var xmdm=jQuery("#xmdm").val();
			
			var xh=jQuery("#xh").val();
			//其他数据
		 	var parameter = {
				"str_xmdm":xmdm,
				"array_xh":xh
			};
		  	
		  	
		  	jQuery.ajaxSetup({async:false});
		  	
			jQuery.post(url,
				parameter,
				function(result){
					
					if(result!="" && result!=null){
						
						jQuery("#div_xmsy").html(result);
						tipsWindown("系统提示","id:div_xmsy","350","250","true","","true","id");
					}
					
				}
			);
			
			jQuery.ajaxSetup({async:true});
			
		}
		
		function saveXmsy(xh){
			
			var url="general_pjpy_xmsz_ajax.do?method=saveXmsy";
			var xmdm=jQuery("input[name=xmsyArr]:checked").val();
			//其他数据
		 	var parameter={}
			
			parameter["array_xh"]=xh;
			
			parameter["str_xmdm"]=xmdm;
			
			jQuery.ajaxSetup({async:false});
			jQuery.post(url,
				parameter,
				function(result){
					$("divWaiting").style.display="none";
					$("divDisable").style.display="none";
					alertInfo(result);
					closeWindown();	
				}
			);
			jQuery.ajaxSetup({async:true});
		}
		
		</script>
	</head>
	<body style="overflow-x:hidden">
		<!-- 标题 -->
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>评奖评优 - 我的评奖 - 学生申请信息</a>
			</p>
		</div>
		<!-- 标题 end-->
		<html:form action="/pjpyXmsh">
			<!-- 隐藏域 -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<!-- 审核项目 -->
			<input type="hidden" name="xmdm" id="xmdm"
				value="${wdpjXssqInfo.xmInfo.xmdm }" />
			<!-- 审核级别 -->
			<input type="hidden" name="shjb" id="shjb" value="${shjb }" />
			<!-- 审核状态 -->
			<input type="hidden" name="shzt" id="shzt" value="" />
			<!-- 审核状态 -->
			<input type="hidden" name="xh" id="xh"
				value="${wdpjXssqInfo.stuInfo.xh }" />
			<!-- 详细信息 -->
			<div class="tab" style="width:100%;height:450px;overflow-x:hidden;overflow-y:auto;">

				<table class="formlist" width="">
					<%@include file="../xssq/xmxx.jsp"%>
					<%@include file="../xssq/xsxx.jsp"%>
					<%@include file="../xssq/zcxx.jsp"%>
					<%@include file="../xssq/cjxx.jsp"%>
					<%@include file="../xssq/sqxx.jsp"%>
					<%@include file="../xssq/qtxx.jsp"%>
					<thead onclick="hiddenMk('tb_shinfo')">
						<tr style="cursor:hand">
							<th colspan="4">
								<span> 项目审核 </span>
							</th>
						</tr>
					</thead>
					<tbody id="tb_shinfo">
						<tr>
							<th widht="30%">
								<bean:message key="lable.xb" />审核
							</th>
							<td colspan="3">
								<logic:equal name="wdpjXssqInfo" property="xmshInfo.xysh"
									value="未审核">
									<p>
										<img src="<%=stylePath%>images/ico_dsh.gif" width="52"
											height="18" />
									</p>
								</logic:equal>
								<logic:equal name="wdpjXssqInfo" property="xmshInfo.xysh"
									value="通过">
									<p>
										<img src="<%=stylePath%>images/ico_shtg.gif" width="52"
											height="18" />
									</p>
								</logic:equal>
								<logic:equal name="wdpjXssqInfo" property="xmshInfo.xysh"
									value="不通过">
									<p>
										<img src="<%=stylePath%>images/ico_shbtg.gif" width="52"
											height="18" />
									</p>
								</logic:equal>
								<logic:equal name="wdpjXssqInfo" property="xmshInfo.xysh"
									value="退回">
									<p>
										<img src="<%=stylePath%>images/ico_shth.gif" width="52"
											height="18" />
									</p>
								</logic:equal>
								<logic:equal name="wdpjXssqInfo" property="xmshInfo.xysh"
									value="需重审">
									<p>
										<img src="<%=stylePath%>images/ico_shxcs.gif" width="52"
											height="18" />
									</p>
								</logic:equal>
							</td>
						</tr>
						<tr>
							<th>
								<bean:message key="lable.xb" />审核意见
							</th>
							<td colspan="3">
								<textarea name='xyshyj' id="xyshyj"
									style="word-break:break-all;width:85%"
									onblur="chLeng(this,500);" rows='4' type="_moz">${wdpjXssqInfo.xmshInfo.xyshyj }</textarea>
							</td>
						</tr>

						<tr>
							<th>
								学校审核
							</th>
							<td colspan="3">
								<logic:equal name="wdpjXssqInfo" property="xmshInfo.xxsh"
									value="未审核">
									<p>
										<img src="<%=stylePath%>images/ico_dsh.gif" width="52"
											height="18" />
									</p>
								</logic:equal>
								<logic:equal name="wdpjXssqInfo" property="xmshInfo.xxsh"
									value="通过">
									<p>
										<img src="<%=stylePath%>images/ico_shtg.gif" width="52"
											height="18" />
									</p>
								</logic:equal>
								<logic:equal name="wdpjXssqInfo" property="xmshInfo.xxsh"
									value="不通过">
									<p>
										<img src="<%=stylePath%>images/ico_shbtg.gif" width="52"
											height="18" />
									</p>
								</logic:equal>
								<logic:equal name="wdpjXssqInfo" property="xmshInfo.xxsh"
									value="退回">
									<p>
										<img src="<%=stylePath%>images/ico_shth.gif" width="52"
											height="18" />
									</p>
								</logic:equal>
								<logic:equal name="wdpjXssqInfo" property="xmshInfo.xxsh"
									value="需重审">
									<p>
										<img src="<%=stylePath%>images/ico_shxcs.gif" width="52"
											height="18" />
									</p>
								</logic:equal>
							</td>
						</tr>
						<tr>
							<th>
								学校审核
							</th>
							<td colspan="3">
								<textarea name='xxshyj' id="xxshyj"
									style="word-break:break-all;width:85%"
									onblur="chLeng(this,500);" rows='4' type="_moz">${wdpjXssqInfo.xmshInfo.xxshyj }</textarea>
							</td>
						</tr>
					</tbody>
					<!-- 审核信息 end-->
				</table>
				<!-- 操作 end-->
			</div>
			<div>
				<table class="formlist" width="">	
				<!-- 操作 -->
					<tfoot>
						<tr>
							<td colspan='4'>
								<div class="bz">

								</div>
								<div class="btn">
									<!-- 非查看 -->
									<logic:notEqual name="doType" value="view">
										<button type="button" onclick="saveShzt('通过')">
											通 过
										</button>
										<button type="button" onclick="saveShzt('不通过')">
											不通过
										</button>
										<!-- 第一级不可退回 -->
										<logic:notEqual name="userType" value="xy">
											<button type="button" onclick="saveShzt('退回')">
												退 回
											</button>
										</logic:notEqual>
									</logic:notEqual>
									<button type="button" onclick="Close();return false;" id="btn_gb">
										<bean:message key="lable.btn_gb_space" />
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
				</table>
			</div>
			<!-- 详细信息 end-->

			
			<logic:empty name="xmsy">
				<!-- 提示信息 -->
				<%@ include file="/comm/other/tsxx.jsp"%>
			</logic:empty>
			
			<div id="div_xmsy">
			</div>
		</html:form>
	</body>
</html>
