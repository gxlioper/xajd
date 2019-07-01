<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/function.js"></script>
		<script language="javascript">
		function searchRs(){
		
			allNotEmpThenGo('/xgxt/rcswSwbl.do?method=swblSh&xmdm='+$("xmdm").value);
		}
		
		function delSwbl(){
			var primaryKey=document.getElementsByName("primary_key");
			var flag=false;
			for(i=0;i<primaryKey.length;i++){
				if(primaryKey[i].checked){
					flag=true;
				}
			}
			if(!flag){
				alert("请先勾选需要删除的数据!");
				return false;
			}
			refreshForm("/xgxt/rcswSwbl.do?method=swblCx&doType=del");
		}
		
		function modi(url){
				if(curr_row != null){
					showTopWin(url + '&pkValue='+curr_row.getElementsByTagName('input')[0].value,700,500);
					return true;
				}else{
					alert('请选择要修改的数据行！');
					return false;
				}
			}
			
		function showView(url){
				if(curr_row != null){
					showTopWin(url + '&pkValue='+curr_row.getElementsByTagName('input')[0].value,700,500);
					return true;
				}else{
					alert('请选择要修改的数据行！');
					return false;
				}
			}
			
			function showSpgw(){
				var spgwidArr=document.getElementsByName('spgwidArr');
				var spgwArr=document.getElementsByName('spgwArr');
				var len=spgwArr.length;
				
				var html="<div style='width:100%;height:70px;overflow:auto;overflow-x:hidden'>";
				html+="<table width='100%'><tr>";
				for(i=1;i<=len;i++){
					html+="<td style='width:33%'>";
					html+="<input type='radio' name='shgw' value='"+spgwidArr[i-1].value+"' >"+spgwArr[i-1].value;
					html+="</td>";
					
					if(i%3==0 ){
						html+="</tr><tr>";
					}
				}
				
				if(len%3!=0){
					
						for(i=1;i<=3-(len%3);i++){
							html+="<td></td>"
						}
				
				}
				html+="</tr></table></div>";
				$("div_spgw").innerHTML=html;
			}
			
			function chooseSpgw(){
				var spgw=document.getElementsByName('shgw');
				var flag=false;
				for(var i=0;i<spgw.length;i++){
					if(spgw[i].checked){
						flag=true;
					}
				}
				if(!flag){
					alert("请先选择审批岗位!");
					return false;
				}
				refreshForm("/xgxt/rcswSwbl.do?method=swblSh");
			}
			
			function swblSh(){
				var primaryKey=document.getElementsByName("primary_key");
				var xmdm=$("xmdm").value;
				var shgw=$("shgw").value;
				var n=0;
				var pkValue="";
				for(var i=0;i<primaryKey.length;i++){
					if(primaryKey[i].checked){
						pkValue=primaryKey[i].value;
						n++;
					}
				}
				
				if(n==0){//未选
					
					alert("请勾选需要审核的记录!");
					return false;
					
				}else if(n==1){//单审
				
					showTopWin("/xgxt/rcswSwbl.do?method=swblDgsh&doType=dgsh&shgw="+shgw+"&xmdm="+xmdm+"&pkValue="+pkValue,800,600);
					return false;
				}else {//批量审
					viewTempDiv('请选择审核岗位','div_plsh',350,200)
				}
				
			}
			
			function plsh(shzt){
				refreshForm("/xgxt/rcswSwbl.do?method=swblSh&doType=plsh&shzt="+shzt);
			}
			
			function show(){
				viewTempDiv('请选择审核岗位','spgwChoose',350,200)
				$("windown-close").style.display="none";
				if($("message") && $("message").value != ""){
					$("message").value = "";
					$("doType").value = "";	
				}
			}
		</script>
	</head>
	<body onload="">

		<html:form action="/rcswSwbl" method="post">
			<div class="tab_cur">
				<p class="location">
					<em>您的当前位置:</em><a>${title }</a>
				</p>
			</div>
			<!-- 隐藏域 -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<input type="hidden" name="xmdm" id="xmdm" value="${xmdm}" />
			<!-- 隐藏域 -->

			<div class="toolbox" id="dgncz">
				<!-- 按钮 -->
				<div class="buttonbox">
					<ul>
						<li>
							<a href="#" onclick="swblSh();return false;" class="btn_sh">审核</a>
						</li>
						<logic:present name="dspgw">
						<li>
							<a href="#" onclick="show();showSpgw();return false;" class="btn_sh">更换审核岗位</a>
						</li>
						</logic:present>
					</ul>
				</div>
				<!-- 过滤条件 -->
				<%@ include file="/comm/search/superSearchArea.jsp"%>
			</div>
			<div class="formbox" id="cxjg">
				<h3 class="datetitle_01">
					<span> 查询结果&nbsp;&nbsp; <logic:empty name="rs">
							<font color="red">未找到任何记录！</font>
						</logic:empty> </span>
				</h3>


				<div class="con_overlfow">
					<table summary="" class="dateline" align="" width="100%">
						<thead>
							<tr align="center" style="cursor:hand">
								<td>
									<input type="checkbox" name="all" value="all" onclick="chec()" />
								</td>
								<logic:iterate id="tit" name="topTr" offset="0">
									<td id="<bean:write name="tit" property="en"/>" nowrap>
										<bean:write name="tit" property="cn" />
									</td>
								</logic:iterate>
							</tr>
						</thead>
						<tbody>
							<logic:notEmpty name="rs">
								<logic:iterate name="rs" id="s" indexId="index">
									<tr onclick="rowMoreClick(this,'',event);"
										ondblclick="showView('/xgxt/rcswSwbl.do?method=swblXg&xmdm=${xmdm}&doType=view',800,600);return false;"
										style="cursor:hand">
										<!-- checkbox begin -->
										<td>
											<input type="checkbox" name="primary_key" id="pkV"
												value="<logic:iterate id="v" name="s" offset="0" length="1">${v}</logic:iterate>"
												<logic:iterate id="v" name="s" offset="0" length="1">${v}</logic:iterate> />
										</td>
										<!-- checkbox end -->

										<!-- 结果集 begin -->
										<logic:iterate id="v" name="s" offset="1" length="10">
											<td nowrap>
												<bean:write name="v" />
											</td>
										</logic:iterate>
										<!-- 结果集 end -->

										<!-- 审核结果图片显示 begin -->
										<logic:iterate id="v" name="s" offset="11" length="1">
											<td align="left" nowrap="nowrap">
												<!-- 未审核 begin -->
												<logic:equal name="v" value="未审核">
													<p>
														<img src="<%=stylePath%>images/ico_dsh.gif" width="52"
															height="18" />
													</p>
												</logic:equal>
												<!-- 未审核 end -->
												<!-- 通过 begin -->
												<logic:equal name="v" value="通过">
													<p>
														<img src="<%=stylePath%>images/ico_shtg.gif" width="52"
															height="18" />
													</p>
												</logic:equal>
												<!-- 通过 end -->
												<!-- 不通过 begin -->
												<logic:equal name="v" value="不通过">
													<p>
														<img src="<%=stylePath%>images/ico_shbtg.gif" width="52"
															height="18" />
													</p>
												</logic:equal>
												<!-- 不通过 end -->
												<!-- 退回 begin -->
												<logic:equal name="v" value="退回">
													<p>
														<img src="<%=stylePath%>images/ico_shth.gif" width="52"
															height="18" />
													</p>
												</logic:equal>
												<!-- 退回 end -->
												<!-- 需重审 begin -->
												<logic:equal name="v" value="需重审">
													<p>
														<img src="<%=stylePath%>images/ico_shxcs.gif" width="52"
															height="18" />
													</p>
												</logic:equal>
												<!-- 需重审 begin -->
											</td>
										</logic:iterate>
										<!-- 审核结果图片显示 end -->
									</tr>
								</logic:iterate>
							</logic:notEmpty>
							<!-- 补空行 -->
							<%@ include file="/comm/noRows.jsp"%>
							<!-- 补空行 end-->
						</tbody>

					</table>
				</div>
				<jsp:include flush="true"
					page="/sjcz/turnpage.jsp?form=rcswSwblForm"></jsp:include>
			</div>

			<!-- 审核岗位隐藏域 -->
			<logic:present name="spgw">
				<html:hidden property="shgw" styleId="shgw" />
			</logic:present>
			<!-- 审核岗位控制 end -->


		</html:form>
		
		<!-- 分配完成提示层 -->
		<div id='spgwChoose' style='display:none'>
			<div class="tab">
				<table class="formlist">
					<thead>
						<tr>
							<th>
								<span> 请先选择当前审核岗位! </span>
							</th>
						</tr>
					</thead>
					<tbody>
						<td id="div_spgw">
						</td>
					</tbody>
					<tfoot>
						<tr>
							<td>
								<div class="btn">
									<!-- 确定 -->
									<button type="button" onclick="chooseSpgw()">
										<bean:message key="lable.btn_qd_space" />
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
				</table>
			</div>
		</div>
		<!-- 审核岗位控制 begin -->
		<logic:iterate name="spgwList" id="spgw">
			<input type="hidden" name="spgwidArr" value="${spgw.id}" />
			<input type="hidden" name="spgwArr" value="${spgw.mc}" />
		</logic:iterate>
		<logic:present name="spgwList">
			<logic:notPresent name="spgw">
				<script defer="defer">
					setTimeout("show()","500");
					showSpgw();
				</script>
			</logic:notPresent>
		</logic:present>
		<div id='div_plsh' style='display:none'>
			<div class="tab">
				<table class="formlist">
					<thead>
						<tr>
							<th>
								<span> 批量审核 </span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td>
								<textarea name='shyj' id="shyj" rows='4'
									style="word-break:break-all;width:98%"
									onblur="chLeng(this,500);"></textarea>

							</td>
						</tr>
					</tbody>
					<tfoot>
						<tr>
							<td>
								<div class="btn">
									<!-- 确定 -->
									<button type="button" onclick="plsh('通过')">
										通 过
									</button>
									<button type="button" onclick="plsh('不通过')">
										不通过
									</button>
									<button type="button" onclick="plsh('退回')">
										退 回
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
				</table>
			</div>
		</div>
	</body>
</html>
