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
			allNotEmpThenGo('/xgxt/zhcpJfsq.do?method=zcjfsh');
		}
		
		//综测加分审核
		function zcjfsh(){
			var url = "/xgxt/zhcpJfsq.do?method=zcjfshDetail";
			var doType = "sh";
			showInfo(url,doType,800,600);
		}
		
		//提交加分审核
		function submitJfsh(){
			for(var i=0;i<document.getElementsByName("checkVal").length;i++){
				if(document.getElementsByName("checkVal")[i].checked==true&&document.getElementById("shr_"+i).value=="未审核"){
					alert("选中的选项中还包含有未审核的项目，请审核后再提交！")
					return false;
				}
			}
			url = "/xgxt/zhcpJfsq.do?method=zcjfsh";
			var doType = "submit";
			sumitInfo(url,doType);
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
				只有执行过<font color="blue">提交</font>操作的审核分，才会被正式记录。
			</p>
			<a class="close" title="隐藏"
			   onclick="this.parentNode.style.display='none';"></a>
		</div>
		<!-- 提示信息 end-->	
		
		<html:form action="/zhcpJfsq">
			<!-- 隐藏域 -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<!-- 隐藏域 -->

			<!-- 多功能操作区 -->
			<div class="toolbox">
				<!-- 按钮 -->
				<div class="buttonbox">
					<ul>
						<!-- 审核 -->
						<li>
							<a href="#" id="btn_sh"
								onclick="zcjfsh();return false;"
								class="btn_shtg">
								审核
							</a>
						</li>
						<!-- 提交 -->
						<li>
							<a href="#" id="btn_tj"
								onclick="submitJfsh();return false;"
								class="btn_sh">
								提交
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
				
				<table summary="" class="dateline" align="" width="100%">
					<!-- 表头 -->
					<thead>
						<tr align="center" style="cursor:hand">
							<td width="5px">
								<input type="checkbox" id="selall" name="selall" onclick="selAll()" />
							</td>
							<logic:iterate id="tit" name="topTr">
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
										name="checkVal"  
										value="<logic:iterate id="v" name="s" offset="0" length="1">${v }</logic:iterate>"/>
									<input type="hidden" id="shr_${index }" value="<logic:iterate id="v" name="s" offset="4" length="1">${v }</logic:iterate>"/>
								</td>
								<!-- 显示信息 -->
								<logic:iterate id="v" name="s">
									<td align="left">
										${v }${xmNum }
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
				<!--分页-->
				<jsp:include flush="true" page="/sjcz/turnpage.jsp?form=zhcpZcjfForm"></jsp:include>
				<!--分页end-->
			</div>
			<!-- 查询结果 end-->
			
			<!-- 提示信息 -->
			<%@ include file="/comm/other/tsxx.jsp"%>
		</html:form>
	</body>
	<div id="div_confirm"  style="display:none">
		<div class="open_prompt">
	    	<table width="100%" border="0" class="table01">
		        <tr>
		        	<td width="109"><div class="img img_why02"></div></td>
		            <th><p id="p_confirm"></p></th>
		        </tr>
		        <tr>
		        	<td colspan="2" align="center" class="btn01">
		        		<input  name="确定" class="button" value="确 定" onclick="bef();closeWindown();"/>
		        		<input  name="取消" class="button" value="取 消" onclick="closeWindown();return false;" />
		        		<input type="hidden" id="qr" value="no"/>
		        	</td>
		        </tr>
	        </table>
		</div>
	</div>

</html>
<script language="javascript" type="text/javascript"> 
<%--window.confirm = function(txt,ddd) {--%>
<%--	if($("p_confirm")){--%>
<%--		$("p_confirm").innerHTML = txt;--%>
<%--		tipsWindown('系统提示','id:div_confirm','340','120','true','','true','id');--%>
<%--		return abc();--%>
<%--	}--%>
<%--} --%>
<%----%>
<%--function bef(){--%>
<%--	setTimeout("$('qr').value='yes'",300);--%>
<%--}--%>
<%----%>
<%--function abc(){--%>
<%----%>
<%--	var qr = $("qr").value;--%>
<%--	alert(qr);--%>
<%--	if(qr == "yes"){--%>
<%--		return true;--%>
<%--	}else {--%>
<%--		setTimeout("abc()",500);--%>
<%--	}--%>
<%--}--%>
</script> 