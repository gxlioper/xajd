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
			allNotEmpThenGo('/xgxt/gyglCwgl.do?method=sdfpcw&doType=query');
		}
		
		//床位自动分配
		function cwsdfp(){
		
			var num = document.getElementsByName("checkVal").length;
			var id = new Array();
			var flag = false;
			
			var n = 0;
			var blog=false;
			for(var i=0;i<num;i++){
				var obj = document.getElementsByName("checkVal")[i];
				if(obj.checked){
					blog=true;
				}
			}
			if(!blog){
				alert("请先选择需要自动分配床位的部门!");
			}
			
			saveUpdate('/xgxt/gyglCwgl.do?method=cwzdfp&doType=zdfp','');
			
		}
		
		//将床位分配给选定学生
		function xscwfp(){
			var url='/xgxt/gyglCwgl.do?method=sdfpcw&doType=cwfp';
			if(curr_row != null){
				var xh=curr_row.getElementsByTagName('input')[0].value;
				var xm=curr_row.getElementsByTagName('input')[1].value;
				if(confirm("确定要将"+xm+"同学，分配到"+$('ldmc').value+","+$('qsh').value+"寝室"+$('cwh').value+"号床位吗？")){
					refreshForm(url + '&xh='+xh,700,500);
					return true;
				}else {
					return false;
				}
			}else{
				alert('请选择要修改的数据行！');
				return false;
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
		
		function fhSdfp(){
			//查询结果集
			allNotEmpThenGo('/xgxt/gyglCwgl.do?method=cwsdfp&doType=query');
		}
		</script>
	</head>
	<body>
		<!-- 标题 -->
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>公寓管理 - 床位管理 - 学生床位分配</a>
			</p>
			<p class="help">
				<a href="#" onclick="return false;" onmousedown ="showHelpDiv();">使用帮助</a>
			</p>
		</div>
		<!-- 标题 end-->
		<html:form action="/gyglCwgl">
			<!-- 提示信息 start-->
			<div class="prompt" id="div_help" style="display: none">
				<h3>
					<span>提示：</span>
				</h3>
				<p>
					请选择一个需要分配床位的学生,然后点击"确认分配"按钮将已选择的床位分配给学生。
				</p>
				<a class="close" title="隐藏"
					onclick="this.parentNode.style.display='none';"></a>
			</div>
		<!-- 提示信息 end-->
			<!-- 隐藏域 -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<input type="hidden" name="ldmc" id="ldmc" value="${cwxxMap.ldmc }"/>
			<input type="hidden" name="cs" id="cs" value="${cwxxMap.cs }"/>
			<input type="hidden" name="qsh" id="qsh" value="${cwxxMap.qsh }"/>
			<input type="hidden" name="cwh" id="cwh" value="${cwxxMap.cwh }"/>
			<!-- 隐藏域 -->

			<!-- 多功能操作区 -->
			<div class="toolbox">
				<!-- 按钮 -->
				<div class="buttonbox">
					<ul>
						<!-- 确定 -->
						<li>
							<a href="#" id="btn_qd"
								onclick="xscwfp();return false;"
								class="btn_qd">
								确认分配
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
						<font color="blue">(分配床位 
						<bean:message key="lable.ld" />：${cwxxMap.ldmc}&nbsp;&nbsp;&nbsp;&nbsp;
						<bean:message key="lable.cs" />：${cwxxMap.cs}&nbsp;&nbsp;&nbsp;&nbsp;
						<bean:message key="lable.qs" />：${cwxxMap.qsh}&nbsp;&nbsp;&nbsp;&nbsp;
						床位号：${cwxxMap.cwh})</font> 
					</span>
				</h3>
				<div class="con_overlfow" >
				<table summary="" class="dateline" align="" width="100%">
					<!-- 表头 -->
					<thead>
						<tr align="center" style="cursor:hand">
<%--							<td width="5px">--%>
<%--								<input type="checkbox" id="selall" name="selall" onclick="selAll()" />--%>
<%--							</td>--%>
							<logic:iterate id="tit" name="topTr" offset="1" length="5">
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
								<td align="center" style="display:none">
									<input type="hidden" id="xh_${index }"
										name="xh"  
										value="<logic:iterate id="v" name="s" offset="0" length="1">${v }</logic:iterate>"/>
									<input type="hidden" id="xm_${index }"
										name="xm"  
										value="<logic:iterate id="v" name="s" offset="2" length="1">${v }</logic:iterate>"/>
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

			<logic:present name="result">
				<logic:equal name="result" value="true">
					<script>
						alert("操作成功！");
					</script>
				</logic:equal>
				<logic:equal name="result" value="false">
					<script>
						alert("操作失败！");
					</script>
				</logic:equal>
			</logic:present>
			<!-- 提示信息end -->
		</html:form>
	</body>
</html>