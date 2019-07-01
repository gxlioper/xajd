<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/function.js"></script>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script language="javascript">
		function savePjf(){
			if(confirm("确定要保存评分信息吗?")){
				var message="正在保存评分信息,请稍候!";
				showLoadPage(message)
				refreshForm("/xgxt/zhcpPdbx.do?method=pdbxLr&doType=save");
			}
		}
		
		function tjPjf(){
			var array = jQuery("[name=div_pkValue]");
			var num = 0;
			for (var i=0;i<array.length;i++) {
					var fs = jQuery(array[i]).find("td").eq(8).find("span").html();
					if(fs==0){
						num +=1;
					}
			}
			if(confirm("尚有"+num+"位学生的分数未录入，是否仍然执行提交操作，一旦提交就不可再行修改,所有未评分项将被置零,是否继续?")){
				var message="正在进行提交操作,请稍候!";
				showLoadPage(message)
				refreshForm("/xgxt/zhcpPdbx.do?method=pdbxLr&doType=tjpf");
			}
		}
		
		//显示加载页面
		function showLoadPage(message){
			//
			if($("div_help"))
			$("div_help").style.display="none";
			//多功能操作
			$("dgncz").style.display="none";
			//查询结果
			$("cxjg").style.display="none";
			//显示
			$("page_loading").style.display="";
			//设置提示信息
			$("prompt").innerHTML=message;
		}
		
		function changeText(len){
			var inputArr=document.getElementsByTagName("input");
			var sum=0;
			for(i=0;i<inputArr.length;i++){
				var name=inputArr[i].name;
				var id=inputArr[i].id;
				if(inputArr[i].title=="请录入数字"
					&& (name+"_"+len)==id){
					if(!inputArr[i].value==""){
						sum+=eval(inputArr[i].value);
					}
				}
			}
		
		
			$("span_zf_"+len).innerHTML=sum;
		}
		
		function checkPdbxf(obj){
			
			var pdbxxmdm=document.getElementsByName("pdbxxmdm");
			var pdbxxxf=document.getElementsByName("pdbxxxf");
			var pdbxsxf=document.getElementsByName("pdbxsxf");
			var num=eval(obj.value);
			
			for(i=0;i<pdbxxmdm.length;i++){
				
				if(pdbxxmdm[i].value==obj.name){
					if(num<eval(pdbxxxf[i].value)){
						obj.value=pdbxxxf[i].value;
						return false;
					}
					
					if(num>eval(pdbxsxf[i].value)){
						obj.value=pdbxsxf[i].value;
						return false;
					}
				}
			}
					
		}
		</script>
	</head>
	<body onload="">

		<html:form action="/zhcpPdbx" method="post">
			<div class="tab_cur">
				<p class="location">
					<em>您的当前位置:</em><a>${title }</a>
				</p>
			</div>


			<logic:equal name="stuInfo" property="jsqr" value="退回">
				<div id="div_help" class="prompt">
					<h3>
						<span>提示：</span>
					</h3>
					<p>
						<logic:iterate name="pdbxszList" id="pdbxsz">
						${pdbxsz.xmmc}限制分(${pdbxsz.xxf}-${pdbxsz.sxf})
						</logic:iterate>
						。大于上限分，将以上限分为准；小于下限分，则以下限分为准。
						<font color="blue"><B>
						该次评分已被${stuInfo.jsxm}老师退回重评，请确认。
						</B>
						</font>
					</p>
					<a class="close" title="隐藏"
						onclick="this.parentNode.style.display='none';"></a>
				</div>
			</logic:equal>
			<logic:notEqual name="stuInfo" property="jsqr" value="退回">
				<logic:equal name="stuInfo" property="sftj" value="是">
					<div id="div_help" class="prompt">
						<h3>
							<span>提示：</span>
						</h3>
						<p>
							<logic:iterate name="pdbxszList" id="pdbxsz">
							${pdbxsz.xmmc}限制分(${pdbxsz.xxf}-${pdbxsz.sxf})
							</logic:iterate>
							。大于上限分，将以上限分为准；小于下限分，则以下限分为准。
							<font color="blue"><B>
							该次评分已提交,不能再做修改,请确认。</B> </font>
						</p>
						<a class="close" title="隐藏"
							onclick="this.parentNode.style.display='none';"></a>
					</div>
				</logic:equal>
				<logic:notEqual name="stuInfo" property="sftj" value="是">
					<div id="div_help" class="prompt">
						<h3>
							<span>提示：</span>
						</h3>
						<p>
							<logic:iterate name="pdbxszList" id="pdbxsz">
							${pdbxsz.xmmc}限制分(${pdbxsz.xxf}-${pdbxsz.sxf})
							</logic:iterate>
							。大于上限分，将以上限分为准；小于下限分，则以下限分为准。
						</p>
						<a class="close" title="隐藏"
							onclick="this.parentNode.style.display='none';"></a>
					</div>
				</logic:notEqual>
			</logic:notEqual>

			<!-- 隐藏域 -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<logic:iterate name="pdbxszList" id="pdbxsz" >
				<input type="hidden" name="pdbxxmdm"  value="${pdbxsz.xmdm }"/>
				<input type="hidden" name="pdbxxxf"  value="${pdbxsz.xxf }"/>
				<input type="hidden" name="pdbxsxf"  value="${pdbxsz.sxf }"/>
			</logic:iterate>
			<!-- 隐藏域 -->
			
			<!-- 多功能操作区 -->
			<logic:equal name="stuInfo" property="sftj" value="否">
				<div class="toolbox" id="dgncz">
					<!-- 按钮 -->
					<div class="buttonbox">
						<ul>
<%--							<logic:notEqual name="stuInfo" property="sftj" value="否">--%>
							<li>
								<a href="#" onclick="savePjf();return false;" class="btn_ccg">保存</a>
							</li>
<%--							</logic:notEqual>--%>
							<li>
								<a href="#" onclick="tjPjf();return false;" class="btn_xg">提交
								</a>
							</li>
							<!-- 隐藏查询按钮，保存后自动刷新 -->
							<button type="button" class="btn_cx" id="search_go" style='display:none'
								onclick="refreshForm('/xgxt/zhcpPdbx.do?method=pdbxLr')">
								查 询
							</button>
						</ul>
					</div>
				</div>
			</logic:equal>
			<logic:equal name="stuInfo" property="jsqr" value="退回">
				<div class="toolbox" id="dgncz">
					<!-- 按钮 -->
					<div class="buttonbox">
						<ul>
							<li>
								<a href="#" onclick="savePjf();return false;" class="btn_ccg">保存</a>
							</li>
							<li>
								<a href="#" onclick="tjPjf();return false;" class="btn_xg">提交
								</a>
							</li>
							<!-- 隐藏查询按钮，保存后自动刷新 -->
							<button type="button" class="btn_cx" id="search_go" style='display:none'
								onclick="refreshForm('/xgxt/zhcpPdbx.do?method=pdbxLr')">
								查 询
							</button>
						</ul>
					</div>
				</div>
			</logic:equal>

			<div class="formbox" id="cxjg">
				<h3 class="datetitle_01">
					<span> <font color="blue">
							操作人学号：${stuInfo.xh}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							操作人姓名：${stuInfo.xm}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							所在班级：${stuInfo.bjmc} </font> </span>
				</h3>

				<logic:notEmpty name="rs">
					<div class="con_overlfow" style="overflow-x:auto;overflow-y:auto;height: 350px " >
						<table summary="" class="dateline" align="" width="100%">
							<thead>
								<tr align="center" style="cursor:hand">

									<logic:iterate id="tit" name="topTr" offset="2" length="2">
										<td id="<bean:write name="tit" property="en"/>" nowrap>
											<bean:write name="tit" property="cn" />
										</td>
									</logic:iterate>
									<logic:iterate id="tit" name="topTr" offset="5">
										<td id="<bean:write name="tit" property="en"/>" nowrap>
											<bean:write name="tit" property="cn" />
										</td>
									</logic:iterate>
									<logic:iterate id="tit" name="topTr" offset="4" length="1">
										<td id="<bean:write name="tit" property="en"/>" nowrap>
											<bean:write name="tit" property="cn" /> 
										</td>
									</logic:iterate>
								</tr>
							</thead>
							<tbody>
								<logic:iterate name="rs" id="s" indexId="index">
									<tr onclick="rowMoreClick(this,'',event);" style="cursor:hand" name="div_pkValue">
										<td style="display:none">
											<input type="hidden" name="primary_key" id="pkV"
												value="<logic:iterate id="v" name="s" offset="0" length="1">${v}</logic:iterate>" />
										</td>
										<logic:iterate id="v" name="s" offset="2" length="2">
											<td nowrap>
												<bean:write name="v" />
											</td>
										</logic:iterate>
										<logic:iterate id="v" name="s" offset="5" indexId="indexlen">
											<td nowrap>
												<input type="text" name="zd${indexlen-4}"
													id="zd${indexlen-4}_${index}"
													onkeydown="return onlyNum(this,2)"
													onmousedown="return onlyNum(this,2)" maxlenght="2"
													onkeyup="checkPdbxf(this);changeText('${index}');"
													onblur="checkPdbxf(this);changeText('${index}');"
													<logic:iterate name="s" offset="1" length="1" id="m" >${m}</logic:iterate>
													style="width:50px" value="${v }" title="请录入数字">
											</td>
										</logic:iterate>
										<logic:iterate id="v" name="s" offset="4" length="1">
											<td nowrap>
												<span id='span_zf_${index}'><bean:write name="v" />
												</span>
											</td>
										</logic:iterate>
									</tr>
								</logic:iterate>
							</tbody>
						</table>
					</div>
				</logic:notEmpty>
			</div>
		</html:form>
		<!-- 请等待 -->
		<%@ include file="/comm/loading.jsp"%>
		<logic:present name="message">
			<script>
			alert('${message}');
			</script>
		</logic:present>
		<!-- 请等待 end-->
	</body>
</html>
