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
			allNotEmpThenGo('/xgxt/gyglQsgl.do?method=qszdfp');
		}
		
		//选择部门
		function choseBm(){
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
				viewTempDiv("选择分配范围","zdscDiv",500,400);	
			}else{
				alert("请选择需要自动分配的部门！");
			}
		}
		
		//点击校区
		function clickXqYq(lx){
		
			var xqNum = document.getElementsByName("xiaoqu").length;
			var xqdm = new Array();
			var n = 0;
			
			for(var i=0;i<xqNum;i++){
				var obj = document.getElementsByName("xiaoqu")[i];
				if(obj.checked == true){
					xqdm[n] = obj.value;
					n++;
				}
			}
			
			var yqNum = document.getElementsByName("yuanqu").length;
			var yqdm = new Array();
			var m = 0;
			
			for(var i=0;i<yqNum;i++){
				var obj = document.getElementsByName("yuanqu")[i];
				if(obj.checked == true){
					yqdm[n] = obj.value;
					m++;
				}
			}
			
			//存在园区
			if($("p_yq") && lx == "xq"){
				displayNewYq(xqdm);
			}
			
			//存在楼栋
			if($("p_ld")){
				displayNewLd(xqdm,yqdm,"checkbox");
			}
		}
		
		//生成园区
		function displayNewYq(xqdm){
		
			var userStatus = $("userStatus").value;
			var userName = $("userName").value;
			var userDep = $("userDep").value;
			
			$("p_yq").innerHTML = "";
			var divHtml = "";
			
			dwr.engine.setAsync(false);
			
			//根据校区取得园区
			searchUtil.getGyglInfo("yq",xqdm,null,null,null,userStatus,userName,userDep,function(data){
				if(data !=null && data.length >0){
					for(var i=0;i<data.length;i++){
						divHtml += "<input type='checkbox' name='yuanqu' onclick='clickXqYq()' value='"+data[i].yqdm+"'/>"+data[i].yqmc;
					}
				}
			});
			
			dwr.engine.setAsync(true);
			
			$("p_yq").innerHTML = divHtml;
		}
		
		//生成楼栋
		function displayNewLd(xqdm,yqdm,lx){
		
			var userStatus = $("userStatus").value;
			var userName = $("userName").value;
			var userDep = $("userDep").value;
			
			$("p_ld").innerHTML = "";
			var divHtml = "";
			
			dwr.engine.setAsync(false);
			
			//根据校区园区取得新楼栋
			searchUtil.getGyglInfo("ldqs",xqdm,yqdm,null,null,userStatus,userName,userDep,function(data){
				if(data !=null && data.length >0){
					for(var i=0;i<data.length;i++){
						if(lx == "checkbox"){
							divHtml += "<input type='checkbox' name='ld' value='"+data[i].lddm+"'/>"+data[i].ldmc;
							divHtml += "(";
							divHtml += data[i].num;
							divHtml += ")";
							if((i+1)%3==0){
								divHtml += "</br>";
							}
						}
					}
				}
			});
			
			dwr.engine.setAsync(true);
			
			$("p_ld").innerHTML = divHtml;
		}
		
		//寝室自动分配
		function qszdfp(){
		
			var num = document.getElementsByName("primarykey_checkVal").length;
			var id = new Array();
			var flag = false;
			
			var n = 0;
			
			for(var i=0;i<num;i++){
				var obj = document.getElementsByName("primarykey_checkVal")[i];
				if(obj.checked == true){
					id[n] = "xfprs_"+obj.id.replace("pk_","");
					n++;
				}
			}
			
			if (confirm("将要根据所设置的范围对所选对象进行自动分配寝室，请确认！")) {
			
				for(var i=0;i<n;i++){
					var tmp = document.createElement("input");
					tmp.type = "hidden";
					tmp.name = "xfprs";
					tmp.value = $(id[i]).value;
					document.forms[0].appendChild(tmp);
				}
				saveUpdate('/xgxt/gyglQsgl.do?method=qszdfp&doType=save','');
			}
		}
		
		//下一步操作
		function nextCz(){
			var next = $("next_cz").value;
			
			if(next == "gb"){//关闭
				hiddenMessage(true,true);
			}else if(next == "jg"){//结果查询
				refreshForm("gyglQsgl.do?method=qszdfpjg&go=go");
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
				在查询结果集中勾选希望自动分配寝室的部门，点击“分配寝室”，确定分配限制，完成本模块操作。
			</p>
			<a class="close" title="隐藏" onclick="this.parentNode.style.display='none';"></a>
		</div>
		<!-- 提示信息 END-->
		
		<html:form action="/gyglQsgl">
			<!-- 隐藏域 -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<!-- 隐藏域 -->

			<!-- 多功能操作区 -->
			<div class="toolbox">
				<!-- 按钮 -->
				<div class="buttonbox">
					<ul>
						<!-- 分配寝室 -->
						<li>
							<a href="#" id="btn_qd"
								onclick="choseBm();return false;"
								class="btn_qd">
								分配寝室
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
							剩余未分配寝室：${wfpQsNum }
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
			
			<div id="zdscDiv" style="display: none">
				<div class="tab">
					<table class="formlist">
						<thead>
							<tr>
								<th colspan="2">
									<span>范围(楼栋名称括号内为未分配寝室数)</span>
								</th>
							</tr>
						</thead>
						<tbody>
						
							<!-- 从属关系1（校区--园区--楼栋） -->
							<logic:equal name="csgx" value="1">
								<!-- 校区 -->
								<tr>
									<th width="20%">
										<bean:message key="lable.xiaoqu" />
									</th>
									<td>
										<logic:notEmpty name="xqdmList">
											<logic:iterate name="xqdmList" id="xiaoqu" indexId="xqNum">
												<logic:notEqual name="xqNum" value="0">
													<input type="checkbox" name="xiaoqu" onclick="clickXqYq('xq')" value="${xiaoqu.dm }"/>${xiaoqu.mc }
												</logic:notEqual>
												<%if((xqNum.intValue()+1)%5==0){%>
													<% out.print("</br>"); %>
												<%}%> 
											</logic:iterate>
										</logic:notEmpty>
									</td>
								</tr>
								<!-- 园区 -->
								<tr>
									<th width="20%">
										<bean:message key="lable.yuanqu" />
									</th>
									<td>
										<p id="p_yq">
											<logic:notEmpty name="yqList">
												<logic:iterate name="yqList" id="yq" indexId="yqNum">
													<logic:notEqual name="yqNum" value="0">
														<input type="checkbox" name="yuanqu" onclick="clickXqYq('yq')" value="${yq.dm }"/>${yq.mc }
													</logic:notEqual>
													<%if((yqNum.intValue()+1)%5==0){%>
														<% out.print("</br>"); %>
													<%}%> 
												</logic:iterate>
											</logic:notEmpty>
										</p>
									</td>
								</tr>
							</logic:equal>
							
							<!-- 从属关系2（校区--楼栋） -->
							<logic:equal name="csgx" value="2">
								<!-- 校区 -->
								<tr>
									<th width="20%">
										<bean:message key="lable.xiaoqu" />
									</th>
									<td>
										<logic:notEmpty name="xqdmList">
											<logic:iterate name="xqdmList" id="xiaoqu" indexId="xqNum">
												<logic:notEqual name="xqNum" value="0">
													<input type="checkbox" name="xiaoqu" onclick="clickXqYq('xq')" value="${xiaoqu.dm }"/>${xiaoqu.mc }
												</logic:notEqual>
												<%if((xqNum.intValue()+1)%5==0){%>
													<% out.print("</br>"); %>
												<%}%> 
											</logic:iterate>
										</logic:notEmpty>
									</td>
								</tr>
							</logic:equal>
							
							<!-- 从属关系3（园区--楼栋） -->
							<logic:equal name="csgx" value="3">
								<!-- 园区 -->
								<tr>
									<th width="20%">
										<bean:message key="lable.yuanqu" />
									</th>
									<td>
										<logic:notEmpty name="yqList">
											<logic:iterate name="yqList" id="yq" indexId="yqNum">
												<logic:notEqual name="yqNum" value="0">
													<input type="checkbox" name="yuanqu" onclick="clickXqYq('yq')" value="${yq.dm }"/>${yq.mc }
												</logic:notEqual>
												<%if((yqNum.intValue()+1)%5==0){%>
													<% out.print("</br>"); %>
												<%}%> 
											</logic:iterate>
										</logic:notEmpty>
									</td>
								</tr>
							</logic:equal>
							
							<!-- 楼栋 -->
							<tr>
								<th width="20%">
									<bean:message key="lable.ld" />
								</th>
								<td>
									<p id="p_ld">
										<logic:notEmpty name="ldQsList">
											<logic:iterate name="ldQsList" id="ld" indexId="ldNum">
												<input type="checkbox" name="ld" value="${ld.lddm }"/>${ld.ldmc }(${ld.num})
												<%if((ldNum.intValue()+1)%3==0){%>
													<% out.print("</br>"); %>
												<%}%> 
											</logic:iterate>
										</logic:notEmpty>
									</p>
								</td>
							</tr>
							<!-- 楼栋 end-->
							
							<!-- 性别 -->
							<tr>
								<th width="20%">
									性别限制
								</th>
								<td>
									<input type="hidden" id="xb" name="xb" value="不限制"/>
									<input type="radio" name="xb" id="xb_no" value="不限制" onclick="$('xb').value = this.value" checked="checked"/>不限制
									<input type="radio" name="xb" id="xb_man" value="男" onclick="$('xb').value = this.value"/>男
									<input type="radio" name="xb" id="xb_woman" value="女" onclick="$('xb').value = this.value"/>女	
								</td>
							</tr>
							<!-- 性别 end-->
							
							<!-- 可否混住 -->
							<tr>
								<th width="20%">
									可否混住
								</th>
								<td>
									<input type="hidden" id="kfhz" name="kfhz" value="不限制"/>
									<input type="radio" name="kfhz" id="kfhz_no" value="不限制" onclick="$('kfhz').value = this.value" checked="checked"/>不限制
									<input type="radio" name="kfhz" id="kfhz_no" value="不可" onclick="$('kfhz').value = this.value"/>不可
									<input type="radio" name="kfhz" id="kfhz_yes" value="可以" onclick="$('kfhz').value = this.value"/>可以	
								</td>
							</tr>
							<!-- 可否混住 end-->
							
						</tbody>
						<tfoot>
						<tr>
							<td colspan='2'>
								<div class="btn">
									<!-- 确定 -->
									<button onclick="qszdfp()">
										<bean:message key="lable.btn_qd_space" />
									</button>
									<!-- 关闭 -->
									<button onclick="hiddenMessage(true,true);return false;">
										<bean:message key="lable.btn_gb_space" />
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
										查看已经完成自动分配的寝室
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
					viewTempDiv("请选择下一步操作","tsxxDiv",350,200);
					if($("message") && $("message").value != ""){
						$("message").value = "";
						$("doType").value = "";	
					}
				</script>
			</logic:present>
			<!-- 提示信息end -->
		</html:form>
	</body>
</html>