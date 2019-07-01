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
		//显示我的评奖详细
		function showLnzcInfo(pkValue){
		
			var ie = "ie";
			
			
			var url="general_pjpy_wdpj_ajax.do?method=showLnzcInfo";
			
			//其他数据
		 	var parameter = {
		 		"ie":ie,
		 		"stylePath":stylePath,
				"pkValue":pkValue
			};
		  	
			jQuery("#div_detail").load(url,parameter,function(){
			
				tipsWindown("系统提示","id:div_detail","400","386","true","","true","id");
			});
		}
		
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
		</script>
	</head>
	<body onload="" ondrag="return false">
	
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
				<span>
				1.本页面展示您本次评奖周期内以及历年来的<font color="blue">评奖结果</font>和<font color="blue">综测结果</font>。<br/>
				<logic:equal name="ckxx" value="pjjg">
				2.点击本次评奖的<font color="blue">查看</font>，可以显示<font color="blue">详细的申请审核情况</font>。<br/>
				</logic:equal>
				<logic:equal name="ckxx" value="zcjg">
				2.点击历史综测的<font color="blue">查看</font>，可以显示<font color="blue">详细的综合分情况</font>。<br/>
				</logic:equal>
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
			</div>
			
			<div class="tab" style="">
				<table class="formlist" width="99%">
				
					<logic:equal name="ckxx" value="pjjg">
						<!-- 本次评奖 begin -->
						<thead onclick="hiddenMk('mk_xmxx')">
							<tr style="height:22px" style="cursor:hand">
								<th colspan="6">
									<span>本次评奖信息</span>
								</th>
							</tr>
						</thead>
						<tbody id="mk_xmxx">
							<tr>
								<th width="12%">
									<div align="center" width="12%">
										项目名称
									</div>
								</th>
								<th width="12%">
									<div align="center" width="12%">
										项目类型
									</div>
								</th>
								<th  width="12%">
									<div align="center" width="12%">
										项目性质
									</div>
								</th>
								<th  width="35%">
									<div align="center">
										审核流程
									</div>
								</th>
								<th width="8%">
									<div align="center" width="8%">
										获得金额
									</div>
								</th>
								<th width="8%">
									<div align="center" width="8%">
										操作
									</div>
								</th>
							</tr>
							<logic:notEmpty name="xssqByZqInfo">
								<logic:iterate name="xssqByZqInfo" id="bzqRs">
									<tr align="center">
										<td>
											${bzqRs.xmmc }
										</td>
										<td>
											${bzqRs.xmlx }
										</td>
										<td>
											${bzqRs.xmxz }
										</td>
										<td>
											${bzqRs.shlc }
										</td>
										<td>
											${bzqRs.xmje }
										</td>
										<td>
											<a href="#" onclick="showWdpjView('${bzqRs.xmdm}');return false;"><font color="blue">查看</font></a>
										</td>
									</tr>
								</logic:iterate>						
							</logic:notEmpty>
							<logic:empty name="zcxxByZqInfo">
								<tr>
									<td align="center" style="vertical-align: center;height:100px" colspan="6">
										
										暂无评奖信息
									</td>
								</tr>
							</logic:empty>
						</tbody>
						<!-- 本次评奖 end -->
					</logic:equal>
					<logic:equal name="ckxx" value="zcjg">
					<!-- 本次综测 begin -->
					<thead onclick="hiddenMk('mk_bczc')">
						<tr style="height:22px" style="cursor:hand">
							<th colspan="6">
								<span>本次综测信息</span>
							</th>
						</tr>
					</thead>
					<tbody id="mk_bczc">
						<tr>
							<td colspan="6">
							<div style="overflow-x :auto;overflow-y:hidden;width:780px;height:100px" >
								<table class="formlist" width="100%"  >
									<logic:notEmpty name="zcxxByZqInfo">
										<logic:iterate name="zcxxByZqInfo" id="zcxxByzq">
										<tr>
											<logic:iterate name="zcxxByzq" id="zcxx">
												<td>
												${zcxx}
												</td>
											</logic:iterate>	
										</tr>
										</logic:iterate>						
									</logic:notEmpty>
									<logic:empty name="zcxxByZqInfo">
										<tr>
											<td align="center" style="vertical-align: center;height:100px">
												
												暂无综测信息
											</td>
										</tr>
									</logic:empty>
								</table>
							</div>
							</td>
						</tr>
					</tbody>
					<!-- 本次综测 end -->
					</logic:equal>
					
					<logic:equal name="ckxx" value="pjjg">
					<!-- 历史评奖 begin -->
					<thead onclick="hiddenMk('mk_lsxx')">
						<tr style="height:22px" style="cursor:hand">
							<th colspan="6">
								<span>历史评奖信息</span>
							</th>
						</tr>
					</thead>
					<tbody id="mk_lsxx" >
						<tr>
							<td colspan="6">
								<table class="formlist" width="100%" >
									<logic:notEmpty name="xssqInfo">
										<tr>
										<td>
											<div align="center">
												获得时间
											</div>
										</td>
										<td>
											<div align="center">
												项目名称
											</div>
										</td>
										<td>
											<div align="center">
												项目类型
											</div>
										</td>
										<td>
											<div align="center">
												获得金额
											</div>
										</td>
									</tr>
									<logic:iterate name="xssqInfo" id="xssq">
										<tr>
											<td>
												<div align="center">
													${xssq.hdsj}
												</div>
											</td>
											<td>
												<div align="center">
													${xssq.xmmc}
												</div>
											</td>
											<td>
												<div align="center">
													${xssq.xmlx}
												</div>
											</td>
											<td>
												<div align="center">
													${xssq.xmje}
												</div>
											</td>
										</tr>
									</logic:iterate>
									</logic:notEmpty>
									<logic:empty name="xssqInfo">
										<tr>
											<td align="center" style="vertical-align: center;height:100px">
												
												无历史评奖信息
											</td>
										</tr>
									</logic:empty>
								</table>
							</td>
						</tr>
					</tbody>
					<!-- 历史评奖 end -->
					</logic:equal>
					
					<logic:equal name="ckxx" value="zcjg">
					<!-- 历史综测 begin -->
					<thead onclick="hiddenMk('mk_lszc')">
						<tr style="height:22px" style="cursor:hand">
							<th colspan="6">
								<span>历史综测信息</span>
							</th>
						</tr>
					</thead>
					<tbody id="mk_lszc" >
						<tr>
							<td colspan="6">
								<table class="formlist" width="100%">
									<logic:notEmpty name="xszcInfo">
										<logic:iterate name="xszcInfo" id="xszc" indexId="titIndex" >
										<tr>
											<logic:iterate name="xszc" id="zcxx" length="5" indexId="index">
												
												<logic:notEqual name="titIndex" value="0">
													<logic:equal name="index" value="4">
													<td>
													<a href="#" onclick="showLnzcInfo('${zcxx}');return false;"><font color="blue">查看</font></a>
													</td>
													</logic:equal>
												</logic:notEqual>
												<logic:equal name="titIndex" value="0">
													<logic:equal name="index" value="4">
													<td>
													${zcxx}
													</td>
													</logic:equal>
												</logic:equal>
												
												
												<logic:notEqual name="index" value="4">
												<td>
												${zcxx}
												</td>
												</logic:notEqual>
											</logic:iterate>	
										</tr>
										</logic:iterate>						
									</logic:notEmpty>
									<logic:empty name="xszcInfo">
										<tr>
											<td align="center" style="vertical-align: center;height:100px">
												
												无历史综测信息
											</td>
										</tr>
									</logic:empty>
								</table>
							</td>
						</tr>
					</tbody>
					<!-- 历史综测 end -->
					</logic:equal>
				</table>
			</div>
			
			<div id="div_detail"  style="display:none">
				
			</div>
			<!-- 我的评奖弹出层 end-->
				
			<!-- 提示信息 -->
			<%@ include file="/comm/other/tsxx.jsp"%>
		</html:form>
	</body>
</html>