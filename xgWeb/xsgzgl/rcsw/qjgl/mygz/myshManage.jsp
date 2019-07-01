<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- 作者：伟大的骆 -->
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
	    <script language="javascript" src="js/check.js"></script>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script language="javascript" defer="defer">
		//初始化
		function onShow(){
		
			var czxm = $("shxm").value;
			
			if(czxm == ""){
				var id = "left_a_0";
				if($(id)){
					$(id).click();
				}
			}else{
				var click_li = $("click_li").value;
				var id = "left_a_"+click_li;
				$(id).click();
			}
		}
		
		function getShgwInfo(){
		
			if($('shgw').value == ""){/*
			
				var czxm = $("shxm").value;
				var url="rcsw_qjgl.do?method=getShgwInfo";
					url+="&czxm="+czxm;
				
				jQuery.ajax({
					type:'post',
					url:url,
					dataType:'json',
					async: false,
					success:function(result){
						if(result.length==1){
							$('shgw').value=result[0].gwid;
							searchRs();
						}else{
							$("p_gwxx").innerHTML="";
							
							var html = "";
							for(var i=0;i<result.length;i++){
								var gwid = result[i].gwid;
								var gwmc = result[i].gwmc;
								
								html+="<input type=\"radio\" name=\"rad_gwid\" value=\""+gwid+"\" onclick=\"$('hid_gwid').value=this.value\"/>";
								html+=gwmc;
								html+="<br/>";
							}
							
							html+="<input type=\"hidden\" id=\"hid_gwid\" value=\"\"/>";
							$("p_gwxx").innerHTML=html;
							
							tipsWindown("系统提示","id:div_gwxx","350","250","true","","true","id");
						}
					}
				});*/
				var czxm = $("shxm").value;
				var url="rcsw_qjgl.do?method=getShgwInfo";
					url+="&czxm="+czxm;
				showDialog("", 300, 200, url);
			} else {
				searchRs();
			}
		}
		
		//查询结果集
		function searchRs(){
			var url = "rcsw_qjgl.do?method=getMyshList";
			var czxm = $("shxm").value;
			var shgw = $("shgw").value;
			var ie = "ie";

			var otherValue = [czxm,shgw,ie,stylePath];

			showWaitingDiv("1000");
			searchRsByAjax(url,otherValue);
		}
		
		//判断审核情况
		function judgeShqk(){
			var num = jQuery("input[name=checkVal]:checked").length;
				
			if(num == 0||num > 1){
				alertError("请勾选一条您希望审核的记录");
				flag = false;
			}else if(num == 1){
				
				var sqid = jQuery("input[name=checkVal]:checked")[0].value;
				var shgw = $("shgw").value;
				
				var url = "rcsw_qjgl.do?method=myshDetail";
					url+= "&sqid="+sqid;
					url+= "&shgw="+shgw;
					
				showDialog('',800,500,url);
				
			}else{
				tipsWindown("系统提示","id:div_shxx","350","250","true","","true","id");
			}		
		}
		
		//保存审核状态
		function saveShzt(){
		
			var num = jQuery("input[name=checkVal]:checked").length;
			
			if(num == 0){
				alertError("请勾选您希望审核的记录");
				flag = false;
			}	
			
			var shzt = $("shzt").value;
			var shgw = $("shgw").value;
			var pk = new Array();
			var n=0;
			
			for(var i=0;i<num;i++){
				var obj = jQuery("input[name=checkVal]:checked")[i];
				pk[n] = obj.value;
				n++;
			}
			
			var url="rcsw_qjgl.do?method=saveShzt";
			
			$("divWaiting").style.display="";
			$("divDisable").style.display="";
				
			//参数
		 	var parameter = {
				"shzt":escape(shzt),
				"shgw":shgw,
				"pk":pk.join("!!@@!!")
			};
			
			jQuery.post(url,parameter,function(result){
				$("divWaiting").style.display="none";
				$("divDisable").style.display="none";
				alertInfo(result);
				searchRs();
			});
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
				<em>您的当前位置：</em><a>日常事务 - 请假管理 - 我的审核</a>
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
				1.数据仅展您需要审核的申请记录，比如上一级别审核通过的记录。</br>
				2.勾选单条记录，点击<font color="blue">审核</font>按钮，将展开详细页面进行单条记录审核。</br>
				3.勾选多条记录，点击<font color="blue">审核</font>按钮，将执行批量审核操作。</br>
				4.如果您是一人多岗的话，在开始查询前，需要先指明您以何种身份进行审核。
				</span>
			</p>
			<a class="close" title="隐藏" onclick="this.parentNode.style.display='none';"></a>
		</div>
		<!-- 提示信息 end-->
		
		<html:form action="/rcsw_qjgl" method="post">

			<!-- 隐藏域 -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<!-- 是否初始化  -->
			<input type="hidden" name="is_default" id="is_default" value=""/>
			<!-- 是否修改 -->
			<input type="hidden" id="had_edit" value="no"/>
			<input type="hidden" name="czxm" id="shxm" value="${czxm }"/>
			<html:hidden property="shgw" styleId="shgw" />
			<input type="hidden" name="shzt" id="shzt" value=""/>
			
			<!-- 隐藏域 end-->
			
			<!-- 多功能操作区 -->
			<div class="toolbox">
				<!-- 按钮 -->
				<div class="buttonbox">
					<ul>
						<li id="li_js">
							<a href="#" onclick="refreshForm('rcsw_qjgl_mygz_tea.do');return false;" class="btn_fh">
								返回
							</a>
						</li>
						<li id="li_js">
							<a href="#" onclick="judgeShqk();return false;" class="btn_sh">
								审核
							</a>
						</li>
					</ul>
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
									&nbsp;
								</span>
							</h3>
						</td>
					</tr>
					<tr>
						<td width="15%" valign="top" style="overflow-x: auto;">
							<!-- 侧边栏 -->
							<div class="listbox">	
								<div class="titlelist" style="height: 380px;">
									<ul id="left_ul">
										<logic:notEmpty name="cshXmList">
											<logic:iterate id="xmnr" name="cshXmList" indexId="index">
												<logic:equal name="xmnr" property="xmdm" value="${czxm}">
													<li id="li_${index}" class="Child" style="background:#dce8f8">
														<a href="#" name="left_a" id="left_a_${index}" 
															onclick="setLiClick('${index}');getShgwInfo();return false;">
															<input type="hidden" id="click_li" value="${index}"/>
															<span class="ico"></span>${xmnr.xmmc}
														</a>
														<input type="hidden" id="xmdm_${index }" value="${xmnr.xmdm}"/>
													</li>
												</logic:equal>
												<logic:notEqual name="xmnr" property="xmdm" value="${czxm}">
													<li id="li_${index}" class="Child">
														<a href="#" name="left_a" id="left_a_${index}" 
															onclick="setLiClick('${index}');getShgwInfo();return false;">
															<span class="ico"></span>${xmnr.xmmc}
														</a>
														<input type="hidden" id="xmdm_${index }" value="${xmnr.xmdm}"/>
													</li>
												</logic:notEqual>
											</logic:iterate>
										</logic:notEmpty>
									</ul>
								</div>
							</div>
							<!-- 侧边栏 end-->
						</td>
						<td width="85%" valign="top" style="position: relative;">
							<div id="div_rs" style="width:100%;height:380px;overflow-x:auto;overflow-y:auto;">
							</div>
						</td>
					</tr>
				</table>
				<!--分页显示-->
				<jsp:include flush="true" page="/sjcz/turnpage.jsp?form=rcswQjglForm"></jsp:include>
				<script type="text/javascript">
						$('choose').className="hide";
				</script>
				<!--分页显示-->
			</div>
			<!-- 内容显示区 end-->
					
			<!-- 提示信息 -->
			<%@ include file="/comm/other/tsxx.jsp"%>
			
			<!-- 岗位信息弹出层 -->
			<div id="div_gwxx" style="display:none">
				<div class="open_win01">
					<table align="center" class="formlist">
						<thead>
							<tr>
								<th colspan="2">
									<span>岗位信息</span>
								</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<th width="30%">
									您的岗位
								</th>
								<td>
									<p id="p_gwxx" style="height: 80px">
									
									</p>
								</td>
							</tr>
						</tbody>
						<tfoot>
							<tr>
								<td colspan="2">
									<div class="bz">
										
									</div>
									<div class="btn">
										<button type="button" id="btn_bc" onclick="$('shgw').value=$('hid_gwid').value;searchRs();closeWindown();">
											确 定
										</button>
										
										<button type="button" id="btn_gb" onclick="closeWindown();">
											关 闭
										</button>
									</div>
								</td>
							</tr>
						</tfoot>
					</table>
				</div>
			</div>
			
			<!-- 岗位信息弹出层 -->
			<div id="div_shxx" style="display:none">
				<div class="open_win01">
					<table align="center" class="formlist">
						<thead>
							<tr>
								<th colspan="2">
									<span>审核状态</span>
								</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<th width="30%">
									审核状态
								</th>
								<td>
									<input type="radio" name="rad_shzt" value="通过" onclick="$('hid_shzt').value=this.value" checked="checked"/>通过
									<br />
									<input type="radio" name="rad_shzt" value="不通过" onclick="$('hid_shzt').value=this.value"/>不通过
									
									<input type="hidden" id="hid_shzt" value="通过"/>
								</td>
							</tr>
						</tbody>
						<tfoot>
							<tr>
								<td colspan="2">
									<div class="bz">
										
									</div>
									<div class="btn">
										<button type="button" id="btn_bc" onclick="$('shzt').value=$('hid_shzt').value;saveShzt();closeWindown();">
											确 定
										</button>
										
										<button type="button" id="btn_gb" onclick="closeWindown();">
											关 闭
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