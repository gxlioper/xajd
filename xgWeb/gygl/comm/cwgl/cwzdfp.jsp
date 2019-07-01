<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- 作者：qlj -->
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script language="javascript" defer="defer">
		//查询结果集
		function searchRs(){
			allNotEmpThenGo('/xgxt/gyglCwgl.do?method=cwzdfp&doType=query');
		}
		
		//床位自动分配
		function zdfpcw(){
		
			var num = document.getElementsByName("pkvArr").length;
			var id = new Array();
			var flag = false;
			
			var n = 0;
			var blog=false;
			for(var i=0;i<num;i++){
				var obj = document.getElementsByName("pkvArr")[i];
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
				refreshForm('/xgxt/gyglCwgl.do?method=cwzdfp&doType=zdfp');
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
			//设置提示信息
			$("prompt").innerHTML="正在进行床位自动分配,请稍候!";
		}
		
		function goCwxxtj(){
			refreshForm("gygl_cwgl_cwfp.do");
		}
		</script>
	</head>
	<body onload="">
		<!-- 标题 -->
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>${title }</a>
			</p>
		</div>
		<!-- 标题 end-->
		<html:form action="/gyglCwgl">
			<!-- 隐藏域 -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<!-- 隐藏域 -->
			
			<!-- 多功能操作区 -->
			<div class="toolbox" id="dgncz">
				<!-- 按钮 -->
				<div class="buttonbox" >
					<ul>
						<!-- 确定 -->
						<li>
							<a href="#" id="btn_qd"
								onclick="zdfpcw();return false;"
								class="btn_qd" title="勾选需要分配床位的部门，点击“确定”按钮对床位自动分配">
								<bean:message key="lable.btn_qd" />
							</a>
						</li>
						<li>
							<a href="#" id="btn_fh" onclick="goCwxxtj();return false;"
								class="btn_fh"> 返回 </a>
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
					<span>
						查询结果
						<logic:empty name="rsArrList">
							&nbsp;&nbsp;<font color="red">未找到任何记录！</font> 
						</logic:empty>
						<logic:notEmpty name="rsArrList">
							&nbsp;&nbsp;<font color="blue"></font> 
						</logic:notEmpty>
					</span>
				</h3>
				<div class="con_overlfow" >
				<table summary="" class="dateline" align="" width="100%">
					<!-- 表头 -->
					<thead>
						<tr align="center" style="cursor:hand">
							<td width="5px">
								<input type="checkbox" id="selall" name="selall" onclick="selAll()" />
							</td>
							<logic:iterate id="tit" name="topTr" offset="1" length="9">
								<td id="<bean:write name="tit" property="en"/>"
									onclick="tableSort(this)"
									nowrap>
									<bean:write name="tit" property="cn" />
								</td>
							</logic:iterate>
						</tr>
					</thead>
					<!-- 表头 end-->
					<!--内容 -->
					<logic:notEmpty name="rsArrList" >
						<logic:iterate name="rsArrList" id="s" indexId="index">
							<tr onclick="rowOnClick(this);" style="cursor:hand">
								<td align="center" width="5px">
									<input type="checkbox" id="pk_${index }"
										name="pkvArr"  
										value="<logic:iterate id="v" name="s" offset="0" length="1">${v }</logic:iterate>"/>
								</td>
								<!-- 显示信息 -->
								<logic:iterate id="v" name="s" offset="1" length="${showNum }">
									<td align="left">
										${v }
									</td>
								</logic:iterate>
								
								<!-- 需分配床位数 -->
								<logic:iterate id="v" name="s" offset="${xfprs }" length="1">
									<td style="display:none">
										<input type="hidden" id="xfprs_${index }" value="${v }"/>
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
				<jsp:include flush="true" page="/sjcz/turnpage.jsp?form=gyglCwglForm"></jsp:include>
				<!--分页end-->
			</div>
			<!-- 查询结果 end-->
			<!-- 提示信息 -->
			<logic:present name="message">
				<!-- 分配完成提示层 -->
				<div id="tsxxDiv" style="display: none">
					<div class="tab">
						<table class="formlist">
							<thead>
								<tr>
									<th>
										<span>
											已经成功分配了${message }个床位，请确定下一步操作
										</span>
									</th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<td>
										<input type="hidden"  name="next" id="next_cz" value="jg"/>
										<input type="radio" name="next" id="next_jg" value="jg" onclick="$('next_cz').value = this.value" checked="checked"/>
										查看已经完成自动分配的床位
										</br>
										<input type="radio" name="next" id="next_sd" value="sd" onclick="$('next_cz').value = this.value"/>
										前往"床位手动分配"模块
										</br>
										<input type="radio" name="next" id="next_gb" value="gb" onclick="$('next_cz').value = this.value"/>
										关闭本页面，继续为其他对象进行分配
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
					viewTempDiv("请选择下一步操作","tsxxDiv",350,200);
					if($("message") && $("message").value != ""){
						$("message").value = "";
						$("doType").value = "";	
					}
				</script>
			</logic:present>
			<!-- 提示信息end -->
			
			<!-- 请等待 -->
			<%@ include file="/comm/loading.jsp"%>
			<!-- 请等待 end-->
		</html:form>
	</body>
</html>