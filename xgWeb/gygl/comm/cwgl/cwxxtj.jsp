<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- 作者：qlj -->
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script type="text/javascript" src="/xgxt/dwr/interface/gyglCwgl.js"></script>
		<script language="javascript" defer="defer">
		//查询结果集
		function searchRs(){
			allNotEmpThenGo('/xgxt/gyglCwgl.do?method=cwxxtj&doType=query');
		}
		
		//根据结果集信息，点链接进入床位分配
		function xscwfp(num){
			//床位信息 主键
			var pkValue=$('cwzj_'+num).value;
			
			refreshForm('/xgxt/gyglCwgl.do?method=sdfpcw&pkValue='+pkValue);
		}
		
		//按学生分配
		function axsfpcw(){
			refreshForm('/xgxt/gyglCwgl.do?method=axsfpcw&doType=zdfp');
		}
				
		function cwzdfpxx(){
			
			var pkValue =new Array();
			var n=0;
			var blog=false;
			for(i=0;i<document.getElementsByName("primarykey_checkVal").length;i++){
				if(document.getElementsByName("primarykey_checkVal")[i].checked){
				pkValue[n]=document.getElementsByName("primarykey_checkVal")[i].value;
				blog=true;
				n++;
				}
			}
			if(!blog){
				alert("请选择需要自动分配床位的部门信息!");
				return false;
			}
			viewTempDiv("选择分配范围","fpxx",600,320);
			var fpdx=$("fpdx").value;
<%--			$("fpdx").value;--%>
			var html="";
			html+="<table>"
			html+="<thead><tr>"
			if("xy"==fpdx){
				html+="<td width='80px'><bean:message key="lable.xb" /></td>";
			}else if("njxy"==fpdx){
				html+="<td>年级</td>";
			    html+="<td width='80px'><bean:message key="lable.xb" /></td>";
			}else if("njzy"==fpdx){
				html+="<td>年级</td>";
			    html+="<td width='80px'>专业</td>";
			}else if("bj"==fpdx){
				html+="<td>年级</td>";
			    html+="<td width='80px'>班级</td>";
			}
			html+="<td>部门人数</td>";
		    html+="<td>分配寝室数</td>";
		    html+="<td>可住人床位数</td>";
			html+="<td>未住人床位数</td>";
			html+="<td>已住人床位数</td>";
			html+="</tr></thead>"
			
			dwr.engine.setAsync(false);
			html+="<tbody>"
			gyglCwgl.getBmxxList(fpdx, pkValue,function(data){
				for(i=0;i<data.length;i++){
					var xymc="";
					var nj="";
					var zymc="";
					var bjmc="";
					html+="<tr>";
					if("xy"==fpdx){
						xymc=data[i].xymc;
						html+="<td width='80px' title='"+xymc+"'>";
						if(xymc.length>6){
							html+=xymc.substring(0,6)+"...";
						}else{
							html+=xymc;
						}
						html+="</td>";
					}else if("njxy"==fpdx){
						nj=data[i].nj;
						xymc=data[i].xymc;
						html+="<td >";
						html+=nj;
						html+="</td>";
						html+="<td width='80px' title='"+xymc+"'>";
						if(xymc.length>6){
							html+=xymc.substring(0,6)+"...";
						}else{
							html+=xymc;
						}
						html+="</td>";
					}else if("njzy"==fpdx){
						nj=data[i].nj;
						zymc=data[i].zymc;
						html+="<td>";
						html+=nj;
						html+="</td>";
						html+="<td width='80px' title='"+zymc+"'>";
						if(xymc.length>6){
							html+=zymc.substring(0,6)+"...";
						}else{
							html+=zymc;
						}
						html+="</td>";
					}else if("bj"==fpdx){
						
						nj=data[i].nj;
						bjmc=data[i].bjmc;
						html+="<td>";
						html+=nj;
						html+="</td>";
						html+="<td width='80px' title='"+bjmc+"'>"
						if(bjmc.length>6){
							html+=bjmc.substring(0,6)+"...";
						}else{
							html+=bjmc;
						}
						html+="</td>";
					}
					var bmrs=data[i].bmrs;
					var fpqss=data[i].fpqss;
					var kzrcws=data[i].kzrcws;
					var wzrcws=data[i].wzrcws;
					var yzrcws=data[i].yzrcws;
					html+="<td>";
					html+=bmrs;
					html+="</td>";
					html+="<td>";
					html+=fpqss;
					html+="</td>";
					html+="<td>";
					html+=kzrcws;
					html+="</td>";
					html+="<td>";
					html+=wzrcws;
					html+="</td>";
					html+="<td>";
					html+=yzrcws;
					html+="</td>";
					html+="</tr>";
				}
			})
			dwr.engine.setAsync(true);
			html+="</tbody></table>";
			$("Bmfpxx").innerHTML=html;
		}
		
		//床位自动分配
		function zdfpcw(){
		
			var num = document.getElementsByName("primarykey_checkVal").length;
			var id = new Array();
			var flag = false;
			
			var n = 0;
			var blog=false;
			for(var i=0;i<num;i++){
				var obj = document.getElementsByName("primarykey_checkVal")[i];
				if(obj.checked){
					blog=true;
				}
			}
			if(!blog){
				alert("请先选择需要自动分配床位的部门!");
				return false;
			}
			if(confirm("确定要对所选部门进行分配床位吗?")){
				showLoadPage();
				refreshForm('/xgxt/gyglCwgl.do?method=cwxxtj&doType=zdfp');
			}
		}
		
		//下一步操作
		function nextCz(){
			var next = $("next_cz").value;
			
			if(next == "gb"){//关闭
				hiddenMessage(true,true);
			}else if(next == "jg"){//结果查询
				refreshForm("gyglCwgl.do?method=cwfpxxcx&go=go");
			}else if(next == "sd"){//手动分配
				refreshForm("gyglCwgl.do?method=cwsdfp&go=go");
			}
		}
		
		//显示加载页面
		function showLoadPage(){
			//多功能操作
			$("dgncz").style.display="none";
			//查询结果
			$("cxjg").style.display="none";
			//显示
			$("page_loading").style.display="";
			//提示信息
			$("xxPrompt").style.display="none";
			//设置提示信息
			$("prompt").innerHTML="正在进行床位自动分配,请稍候!";
		}
		
		function goCwfpjg(){
			refreshForm("gygl_cwgl_fpjg.do");
		}
		
		function cwsdfp(){
		
			refreshForm('/xgxt/gyglCwgl.do?method=cwxxtj&doType=cwsdfp');
		}
		
		function getBmxss(){
			var fpdx=$("fpdx").value;
			var bm=new Array();
			var bmPk=document.getElementsByName("primarykey_checkVal");
			var n=0;
			for(i=0;i<bmPk.length;i++){
				if(bmPk[i].checked){
					bm[n]=bmPk[i].value;
					n++;
				}
			}
			
			if(bm.length>0){
				gyglCwgl.Bmxss(bm,fpdx,function(data){
					if(eval(data)>50){
						if(confirm("您所选择的部门未分配人数过多，是否进行筛选?")){
							refreshForm("/xgxt/gyglCwgl.do?method=sxsdfpcwxs&go=go&doType=sxcx");
						}else{
							cwsdfp();
						}
					}else{
						cwsdfp();
					}
				})
			}else {
				alert("请选择需要分配的部门!");
				return false;
			}
		}
		</script>
	</head>
	<body>
		<!-- 标题 -->
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>${title }</a>
			</p>
		</div>
		<!-- 提示信息 end-->
		<div id="xxPrompt" class="prompt">
			<h3>
				<span>提示：</span>
			</h3>
			<p>
				本功能模块展示部门的床位分配情况。勾选希望进行分配的部门，点击“床位自动分配”，显示床位分配情况点击确定，由
				<br />
				系统自动对床位进行分配，点击“床位手动分配”，将展现已分配给勾选部门的寝室床位列表，对其进行手动分配，点击
				<br />
				“分配结果查看”，可以查看到所有已经分配的床位，并可以执行“取消分配”操作。
				<br />
			</p>
			<a class="close" title="隐藏"
				onclick="this.parentNode.style.display='none';"></a>
		</div>
		<!-- 提示信息 end-->
		<!-- 标题 end-->
		<html:form action="/gyglCwgl">
			<input type="hidden" name="fpdx" id="fpdx" value="${fpdx}" />
			<!-- 隐藏域 -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<!-- 隐藏域 -->
			<!-- 多功能操作区 -->
			<div class="toolbox" id="dgncz">
				<!-- 按钮 -->
				<div class="buttonbox">
					<ul>
						<!-- 确定 -->
						<li>
							<a href="#" id="btn_shtg" onclick="zdfpcw();return false;"
								class="btn_shtg" > 床位自动分配 </a>
						</li>
						<!-- 手动分配 -->
						<li>
							<a href="#" id="btn_xg"
								onclick="getBmxss();return false;"
								class="btn_xg"> 床位手动分配 </a>
						</li>
						<!-- 手动分配 -->
						<li>
							<a href="#" id="btn_sh"
								onclick="allNotEmpThenGo('/xgxt/gyglCwgl.do?method=cwsdfp&doType=query');return false;"
								class="btn_sh"> 床位手动调整 </a>
						</li>
						<!-- 分配结果 -->
						<li>
							<a href="#" id="btn_cx" onclick="goCwfpjg();return false;"
								class="btn_cx"> 分配结果查看 </a>
						</li>
					</ul>
				</div>
				<!-- 按钮 end-->

				<!-- 过滤条件 -->
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- 过滤条件 end-->

			</div>
			<!-- 多功能操作区 end -->

			<!-- 查询结果-->
			<div class="formbox" id="cxjg">
				<h3 class="datetitle_01">
					<span> 查询结果 <logic:empty name="rsArrList">
							&nbsp;&nbsp;<font color="red">未找到任何记录！</font>
						</logic:empty> <logic:notEmpty name="rsArrList">
							&nbsp;&nbsp;<font color="blue"></font>
						</logic:notEmpty> <%--						<font color="blue"></font>--%> </span>
				</h3>
				<div class="con_overlfow">
					<table summary="" class="dateline" align="" width="100%">
						<!-- 表头 -->
						<thead>
							<tr align="center" style="cursor:hand">
								<td>
									<input type="checkbox" />
								</td>
								<logic:iterate id="tit" name="topTr" offset="1">
									<td id="<bean:write name="tit" property="en"/>"
										 nowrap>
										<bean:write name="tit" property="cn" />
									</td>
								</logic:iterate>
							</tr>
						</thead>
						<!-- 表头 end-->
						<!--内容 -->
						<logic:notEmpty name="rsArrList">
							<logic:iterate name="rsArrList" id="s" indexId="index">
								<tr onclick="rowOnClick(this);" style="cursor:hand">
									<td>
										<input type="checkbox" name="primarykey_checkVal" id="pkV"
											value="<logic:iterate id="v" name="s" offset="0" length="1">${v}</logic:iterate>" />
									</td>
									<!-- 显示信息 -->
									<logic:iterate id="v" name="s" offset="1">
										<td align="left" nowrap="nowrap">
											${v }
										</td>
									</logic:iterate>
								</tr>
							</logic:iterate>
						</logic:notEmpty>
						<!--内容 end-->
						<!-- 补空行 -->
						<%@ include file="/comm/noRows.jsp"%>
						<!-- 补空行 end-->
					</table>
				</div>
				<!--分页-->
				<jsp:include flush="true"
					page="/sjcz/turnpage.jsp?form=gyglCwglForm"></jsp:include>
				<!--分页end-->
			</div>
			<!-- 查询结果 end-->


			<div id="fpxx" style="display: none" style="width:650px">
				<div class="tab">
					<table class="formlist">
						<thead>
							<tr>
								<th>
									<span> 分配信息 </span>
								</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td>
									<p id="Bmfpxx" class="tab"
										style="width:100%;height:200px;overflow-x:hidden;overflow-y:auto;">
									</p>
								</td>
							</tr>
						</tbody>
						<tfoot>
							<tr>
								<td>
									<div class="btn">
										<!-- 确定 -->
										<button onclick="zdfpcw();">
											<bean:message key="lable.btn_qd_space" />
										</button>
									</div>
								</td>
							</tr>
						</tfoot>
					</table>
				</div>
			</div>

			<!-- 提示信息 -->
			<logic:present name="message">
				<!-- 分配完成提示层 -->
				<div id="tsxxDiv" style="display: none">
					<div class="tab">
						<table class="formlist">
							<thead>
								<tr>
									<th>
										<span> 已经成功分配了${message }个床位，请确定下一步操作 </span>
									</th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<td>
										<input type="hidden" name="next" id="next_cz" value="jg" />
										<input type="radio" name="next" id="next_jg" value="jg"
											onclick="$('next_cz').value = this.value" checked="checked" />
										查看已经完成自动分配的床位
										</br>
										<input type="radio" name="next" id="next_sd" value="sd"
											onclick="$('next_cz').value = this.value" />
										前往"床位手动分配"模块
										</br>
										<input type="radio" name="next" id="next_gb" value="gb"
											onclick="$('next_cz').value = this.value" />
										继续为其他对象进行分配
									</td>
								</tr>
							</tbody>
							<tfoot>
								<tr>
									<td>
										<div class="btn">
											<!-- 确定 -->
											<button onclick="nextCz()">
												<bean:message key="lable.btn_qd_space" />
											</button>
										</div>
									</td>
								</tr>
							</tfoot>
						</table>
					</div>
				</div>
				<script defer="defer">
					function showNextDiv(){
						viewTempDiv("请选择下一步操作","tsxxDiv",350,200);
						if($("message") && $("message").value != ""){
							$("message").value = "";
							$("doType").value = "";	
						}
					}
					setTimeout("showNextDiv()",100);
				</script>
			</logic:present>
			<!-- 提示信息end -->

			<!-- 请等待 -->
			<%@ include file="/comm/loading.jsp"%>
			<!-- 请等待 end-->
		</html:form>
	</body>
</html>
