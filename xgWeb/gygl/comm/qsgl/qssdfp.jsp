<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- 作者：伟大的骆 -->
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script language="javascript" defer="defer">
		
		//查询结果集
		function searchRs(){
			allNotEmpThenGo('/xgxt/gyglQsgl.do?method=qssdfp');
		}
		
		//页面跳转到手动分配页面
		function showQssdfp(){
			refreshForm("gyglQsgl.do?method=qssdfpdDetail");
		}
		
		//取消分配
		function qxfp(){
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
				if (confirm("将要取消所勾选寝室的分配情况，请确认！")) {
					saveUpdate('/xgxt/gyglQsgl.do?method=qssdfp&doType=del','');
				}
			}else{
				alert("请勾选需要取消分配的寝室！");
			}
		}
		
		//下一步操作
		function nextCz(){
			var next = $("next_cz").value;
			
			if(next==""){
				next=$("next_1").value;
			}
			
			if(next == "gb"){//关闭
				hiddenMessage(true,true);
			}else {
				next+="&doType=scjc";
				refreshForm(next);
			}
		}
		</script>
	</head>
	<body>
		<!-- 标题 -->
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>寝室分配 - 手动调整</a>
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
				结果集中展现信息为寝室分配记录。勾选希望重调整的寝室分配记录，点击“寝室调整”，可以针对勾选寝室完成调整操作，
				如果没有勾选的话，则针对全部寝室进行调整操作。
			</p>
			<a class="close" title="隐藏"  onclick="this.parentNode.style.display='none';"></a>
		</div>
		<!-- 提示信息 END-->
		
		<html:form action="/gyglQsgl">
			<!-- 隐藏域 -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<!-- 隐藏域 -->
			<input type="hidden" id="czxq" name="czxq" value="${czxq}"/><!-- 存在校区 -->
			<input type="hidden" id="czyq" name="czyq" value="${czyq}"/><!-- 存在园区 -->
			<input type="hidden" id="xqdm" name="xqdm" value="${xqdm}"/><!-- 校区代码 -->
			<input type="hidden" id="yqdm" name="yqdm" value="${yqdm}"/><!-- 园区代码 -->
			<input type="hidden" id="lddm" name="lddm" value="${lddm}"/><!-- 楼栋代码 -->
			<input type="hidden" id="cs" name="cs" value="${cs}"/><!-- 层数 -->
			<input type="hidden" id="qsh" name="qsh" value="${qsh}"/><!-- 寝室号 -->
			<input type="hidden" id="dm" name="dm" value="${dm}"/><!-- 存在园区 -->
			<!-- 多功能操作区 -->
			<div class="toolbox">
				<!-- 按钮 -->
				<div class="buttonbox">
					<ul>
						<!-- 分配 -->
						<li>
							<a href="#" id="btn_fp"
								onclick="showQssdfp();return false;"
								class="btn_sx">
								寝室调整
							</a>
						</li>
						<!-- 取消 -->
						<li>
							<a href="#" id="btn_qx"
								onclick="setSearchTj();qxfp();return false;"
								class="btn_qx">
								取消分配
							</a>
						</li>
						<!-- 返回 -->
						<li>
							<a href="#" id="btn_fh"
								onclick="refreshForm('gyglQsgl.do?method=qsfptj&mklx=fh');return false;"
								class="btn_fh">
								<bean:message key="lable.btn_fh" />
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
							<logic:iterate id="tit" name="topTr" offset="0" length="8">
								<td id="<bean:write name="tit" property="en"/>"
									onclick="tableSort(this)"
									nowrap>
									<bean:write name="tit" property="cn" />
								</td>
							</logic:iterate>
							<td id="rzzt" onclick="tableSort(this)" nowrap>
								 入住状态
							</td>
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
										value="<logic:iterate id="v" name="s" offset="0" length="1">${v }</logic:iterate>_<logic:iterate id="v" name="s" offset="2" length="1">${v }</logic:iterate>"
									/>
								</td>
								<!-- 显示信息 -->
								<logic:iterate id="v" name="s" offset="0" length="8">
									<td align="left">
										${v }
									</td>
								</logic:iterate>
								
								<!-- 入住状态 -->
								<logic:iterate id="v" name="s" offset="9" length="1">
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
			
			<!-- 提示信息 -->
			<%@ include file="/comm/delMessage.jsp"%>
			<logic:notPresent name="delMessage">
				<logic:present name="message">
				<script>
					alert($("message").value);	
					$("message").value = "";
					$("doType").value = "";	
				</script>
				</logic:present>
			</logic:notPresent>		
		</html:form>
	</body>
</html>