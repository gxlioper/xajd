<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- 作者：伟大的骆 -->
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script language="javascript" src="/xgxt/dwr/interface/getCommGygl.js"></script>
		<script language="javascript" defer="defer">
		//查询结果集
		function searchRs(){
			allNotEmpThenGo('/xgxt/gyglQsgl.do?method=qsfptj');
		}
		
		//跳转到寝室自动分配
		function goQszdfp(){
			var num = document.getElementsByName("primarykey_checkVal").length;
			var flag = false;
			
			for(var i=0;i<num;i++){
				var obj = document.getElementsByName("primarykey_checkVal")[i];
				if(obj.checked == true){
					flag = true;
					break;
				}
			}
			
			if(flag){
				$("div_nr").style.display="none";
				$("div_btn").style.display="none";
				$("div_tsxx").style.display="";
				
				viewTempDiv("选择分配范围","zdscDiv",600,480);
				//显示
				setBmtjInfo();
			}else{
				alert("请选择需要自动分配的部门！");
			}
		}
		
		//跳转到寝室手动分配
		function goQssdfp(){
			refreshForm("gyglQsgl.do?method=qssdfp");
		}
		
		//跳转到寝室手动分配
		function goQssdfpByHand(){
			var pkValue=document.getElementsByName("primarykey_checkVal");
			var bool=false;
			for(i=0;i<pkValue.length;i++){
				if(pkValue[i].checked){
					bool=true;
				}
			}
			if(!bool){
				alert("请至少选择一个需要分配寝室的部门!");
				return false;
			}
			
			refreshForm("gyglQsgl.do?method=qssdfpByHand");
		}
		
		//寝室自动分配
		function qszdfp(){
			if (confirm("将要根据所设置的范围对所选对象进行自动分配寝室，请确认！")) {
				$("div_nr").style.display="none";
				$("div_btn").style.display="none";
				$("div_tsxx").style.display="";
				saveUpdate('/xgxt/gyglQsgl.do?method=qsfptj&doType=save','');
			}
		}
		
		//寝室分配结果
		function goQsfpjg(){
			refreshForm("gyglQsgl.do?method=qsfpjg");
		}
		
		//下一步操作
		function nextCz(){
			var next = $("next_cz").value;
			
			if(next == "gb"){//关闭
				hiddenMessage(true,true);
			}else if(next == "jg"){//结果查询
				refreshForm("gyglQsgl.do?method=qsfpjg&go=go");
			}else if(next == "sd"){//手动分配
				refreshForm("gyglQsgl.do?method=qssdfp");
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
			<p class="help">
				<a href="#" onclick="return false;" onmousedown ="showHelpDiv();">使用帮助</a>
			</p>
		</div>			
		<!-- 标题 end-->
		<!-- 提示信息 START-->
		<div class="prompt" id="div_help" style="display: none">
			<h3>
				<span>提示：</span>
			</h3>
			<p>
				本功能模块展示部门的寝室分配情况。勾选希望进行分配的部门，点击“寝室自动分配”，在弹出页面确定规则后，由<br/>
				系统自动对寝室进行分配，点击“寝室手动调整”，将展现已分配给勾选部门的寝室列表，对其进行手动的分配，点击<br/>
				“分配结果查看”，可以查看到所有已经被分配的寝室，并可以执行“取消分配”操作。选择一条以上的部门记录后点<br/>
				击“寝室手动分配”按钮可对选中部门进行寝室分配。
			</p>
			<a class="close" title="隐藏"  onclick="this.parentNode.style.display='none';"></a>
		</div>
		<!-- 提示信息 END-->
		
		<html:form action="/gyglQsgl">
			<!-- 隐藏域 -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<!-- 分配对象 -->
			<input type="hidden" name="fpdx" id="fpdx" value="${fpdx }"/>
			<!-- 隐藏域 -->

			<!-- 多功能操作区 -->
			<div class="toolbox">
				<!-- 按钮 -->
				<div class="buttonbox">
					<ul>
						<!-- 自动分配 -->
						<li>
							<a href="#" id="btn_qd"
								onclick="goQszdfp();return false;"
								class="btn_shtg">
								寝室自动分配
							</a>
						</li>
						<!-- 手动分配 -->
						<li>
							<a href="#" id="btn_xg"
								onclick="goQssdfpByHand();return false;"
								class="btn_sh">
								寝室手动分配
							</a>
						</li>
						<logic:equal name="fpfs" value="1">
						<!-- 手动调整 -->
							<li>
								<a href="#" id="btn_qd"
									onclick="goQssdfp();return false;"
									class="btn_xg">
									寝室手动调整
								</a>
							</li>
						</logic:equal>
						<!-- 分配结果 -->
						<li>
							<a href="#" id="btn_qd"
								onclick="goQsfpjg();return false;"
								class="btn_cx">
								分配结果查看
							</a>
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
			<div class="formbox">		
				<h3 class="datetitle_01">
					<span>
						查询结果
						<logic:empty name="rsArrList">
							&nbsp;&nbsp;<font color="red">未找到任何记录！</font> 
						</logic:empty>
						<logic:notEmpty name="rsArrList">
							&nbsp;&nbsp;
							<font color="blue">
							
							</font> 
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
					<logic:equal name="hadRs" value="yes">
						<logic:iterate name="rsArrList" id="s" indexId="index">
							<tr onclick="rowOnClick(this);" style="cursor:hand">
								<td align="center" width="5px">
									<input type="checkbox" id="pk_${index }"
										name="primarykey_checkVal"  
										value="<logic:iterate id="v" name="s" offset="0" length="1">${v }</logic:iterate>"/>
								</td>
								<!-- 显示信息 -->
								<logic:iterate id="v" name="s" offset="1">
									<td align="left">
										${v }
									</td>
								</logic:iterate>
							</tr>
						</logic:iterate>
					</logic:equal>
					<!--内容 end-->
					<!-- 补空行 -->
					<%@ include file="/comm/noRows.jsp"%>
					<!-- 补空行 end-->
				</table>
				</div>
				<!--分页-->
				<jsp:include flush="true" page="/sjcz/turnpage.jsp?form=gyglQsglForm"></jsp:include>
				<!--分页end-->
			</div>
			<!-- 查询结果 end-->

			<!-- 自动分配Div-->
			<div id="zdscDiv" style="display: none">
				<div id="div_tsxx" align="center">
					</br></br></br></br></br></br>
					<img src="<%=stylePath%>images/ico_loading.gif"/>
					</br>
					<img src="<%=stylePath%>images/loadingli.gif"/>
					<p id="p_tsxx"><B>数据处理中，请稍后。。。</B></p>
					
					<!-- 构建已选条件 -->
					<script language="javascript" defer="defer">
						function showNr(){
							$("div_nr").style.display="";
							$("div_btn").style.display="";
							$("div_tsxx").style.display="none";
						}
						setTimeout("showNr()",2000);
					</script>
				</div>
				<div id="div_nr" class="tab" style="width:100%;height:400px;overflow-x:hidden;overflow-y:auto;display: none">
					<%@ include file="qsfpInfo.jsp"%>
					<%@ include file="qsfpRule.jsp"%>
				</div>
				<div id="div_btn" style="display: none">
					<table class="formlist">
						<tfoot>
							<tr>
								<td>
									<div class="btn">
										<!-- 确定 -->
										<button onclick="qszdfp()">
											<bean:message key="lable.btn_qd_space" />
										</button>
									</div>
								</td>
							</tr>
						</tfoot>
					</table>
				</div>
			</div>
			<!-- 自动分配Div end-->
			
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
											已经成功分配了${message }个寝室，请确定下一步操作
										</span>
									</th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<td>
										<input type="hidden"  name="next" id="next_cz" value="jg"/>
										<input type="radio" name="next" id="next_jg" value="jg" onclick="$('next_cz').value = this.value" checked="checked"/>
										前往"分配结果查看"模块
										</br>
										<input type="radio" name="next" id="next_sd" value="sd" onclick="$('next_cz').value = this.value"/>
										前往"寝室手动分配"模块
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
			
			<%@ include file="/comm/other/exception.jsp"%>
			
		</html:form>
	</body>
</html>