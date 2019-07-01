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
			allNotEmpThenGo('/xgxt/gyglCwgl.do?method=sxsdfpcwxs');
		}
		
		//按学生分配床位
		function axsfpcw(){
		
			var num = document.getElementsByName("pkvArr").length;
			var id = new Array();
			var blog=false;
			for(var i=0;i<num;i++){
				var obj = document.getElementsByName("pkvArr")[i];
				if(obj.checked){
					blog=true;
				}
			}
			if(!blog){
				alert("请先选择需要分配床位的学生!");
				return false;
			}
			
			refreshForm('/xgxt/gyglCwgl.do?method=cwxxtj&doType=cwsdfp');
			
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
		
		function fhSdfp(){
			if($("doType")){
				$("doType").value="";	
			}
			//查询结果集
			allNotEmpThenGo('/xgxt/gygl_cwgl_cwfp.do');
		}
		</script>
	</head>
	<body onload="">
		<!-- 标题 -->
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>公寓管理 - 床位管理 - 筛选学生</a>
			</p>
		</div>
		
		<!-- 标题 end-->
		<html:form action="/gyglCwgl">
			<!-- 隐藏域 -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<!-- 隐藏域 -->

			<!-- 多功能操作区 -->
			<div class="toolbox">
				<!-- 按钮 -->
				<div class="buttonbox">
					<ul>
						<!-- 确定 -->
						<li>
							<a href="#" id="btn_sx"
								onclick="axsfpcw();return false;"
								class="btn_sx">
								床位分配
							</a>
						</li>
						<li>
							<a href="#" id="btn_fh"
								onclick="fhSdfp();return false;"
								class="btn_fh">
								返回
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
						
						<font color="blue">请勾选需要分配床位的学生，然后点击分配按钮为其选择床位</font>
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

			<logic:present name="message">
				
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