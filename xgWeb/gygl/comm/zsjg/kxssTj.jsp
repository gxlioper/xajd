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
			allNotEmpThenGo('/xgxt/gyglZsjg.do?method=kxssTj');
		}
		
		function kqsxx(lddm){
			var url="/xgxt/gyglZsjg.do?method=kqsxx&lddm="+lddm;
			showTopWin(url,800,600);
		}
		
		function xqsxx(lddm){
			var url="/xgxt/gyglZsjg.do?method=xqsxx&lddm="+lddm;
			showTopWin(url,800,600);
		}
		
		function printKxsstj(dclx){
			
			var url='/xgxt/gyglZsjg.do?method=printKxss&dclx='+dclx;
			if(dclx!="kxqs"){
				if(curr_row){
					var lddm= curr_row.getElementsByTagName('input')[0].value;
					url+="&lddm="+lddm;
					
				}else{
					alert("请先选择需要统计的楼栋!");
					return false;
				}
			}
			
			document.forms[0].action = url;
			document.forms[0].target = "_blank";
			document.forms[0].submit();
			document.forms[0].target = "_self";
			
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
		
		<!-- 提示信息 end-->		
		<!-- 标题 end-->
		<html:form action="/gyglZsjg">
			<input type="hidden" name="fpdx" id="fpdx" value="${fpdx}"/>
			<!-- 隐藏域 -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<!-- 隐藏域 -->
			<!-- 多功能操作区 -->
			<div class="toolbox" id="dgncz">
				<div class="buttonbox">
					<ul>	
						<li><a href="#" class="btn_tj" onclick="printKxsstj('kxqs');return false;">空闲宿舍统计</a></li>
						
						<li><a href="#" class="btn_dc" onclick="printKxsstj('kqs');return false;">空寝室信息统计</a></li>
					
						<li><a href="#" class="btn_dy" onclick="printKxsstj('xqs');return false;">闲寝室信息统计</a></li>
					</ul>
				</div>
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
						<logic:empty name="rs">
							&nbsp;&nbsp;<font color="red">未找到任何记录！</font> 
						</logic:empty>
						<logic:notEmpty name="rs">
							&nbsp;&nbsp;<font color="blue"></font> 
						</logic:notEmpty>
						
<%--						<font color="blue"></font>--%>
					</span>
				</h3>
				<div class="con_overlfow" >
				<table summary="" class="dateline" align="" width="100%">
					<!-- 表头 -->
					<thead>
						<tr align="center" style="cursor:hand">
						
							<logic:equal name="czxq" value="是">
								<logic:iterate id="tit" name="topTr" offset="1" length='1'>
								<td id="<bean:write name="tit" property="en"/>"
									nowrap>
									<bean:write name="tit" property="cn" />
								</td>
							</logic:iterate>
							</logic:equal>
							<logic:equal name="czyq" value="是">
								<logic:iterate id="tit" name="topTr" offset="2"  length='1'>
								<td id="<bean:write name="tit" property="en"/>"
									nowrap>
									<bean:write name="tit" property="cn" />
								</td>
							</logic:iterate>
							</logic:equal>
							
						
							<logic:iterate id="tit" name="topTr" offset="3">
								<td id="<bean:write name="tit" property="en"/>"
									nowrap>
									<bean:write name="tit" property="cn" />
								</td>
							</logic:iterate>
						</tr>
					</thead>
					<!-- 表头 end-->
					<!--内容 -->
					<logic:notEmpty name="rs" >
						<logic:iterate name="rs" id="s" indexId="index">
							<tr onclick="rowOnClick(this);" style="cursor:hand">
								<!-- 显示信息 -->
								<td style="display:none">
								<logic:iterate id="v" name="s" offset="0" length="1">
										<input type="hidden" value="${v}"/>
								</logic:iterate>
								</td>
								
								<logic:equal name="czxq" value="是">
								<logic:iterate id="v" name="s" offset="1" length="1">
									<td align="left" nowrap="nowrap">
										${v }
									</td>
								</logic:iterate>
								</logic:equal>
								
								<logic:equal name="czyq" value="是">
								<logic:iterate id="v" name="s" offset="2" length="1">
									<td align="left" nowrap="nowrap">
										${v }
									</td>
								</logic:iterate>
								</logic:equal>
								<logic:iterate id="v" name="s" offset="3" length="2">
									<td align="left" nowrap="nowrap">
										${v }
									</td>
								</logic:iterate>
								<logic:iterate id="v" name="s" offset="5" length="1">
									<td align="left" nowrap="nowrap">
										<logic:equal name="v" value="0">
											0
										</logic:equal>
										<logic:notEqual name="v" value="0">
										<a href="#" onclick="xqsxx('<logic:iterate id="x" name="s" offset="0" length="1">${x}</logic:iterate>')"><font color="blue"><U>${v }</U></font></a>
										</logic:notEqual>
									</td>
								</logic:iterate>
								
								<logic:iterate id="v" name="s" offset="6" length="1">
									<td align="left" nowrap="nowrap">
									<logic:equal name="v" value="0">
											0
									</logic:equal>
									<logic:notEqual name="v" value="0">
									<a href="#" onclick="kqsxx('<logic:iterate id="x" name="s" offset="0" length="1">${x}</logic:iterate>')"><font color="blue"><U>${v }</U></font></a>
									</logic:notEqual>
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
				<jsp:include flush="true" page="/sjcz/turnpage.jsp?form=gyglZsjgForm"></jsp:include>
				<!--分页end-->
			</div>
			<!-- 查询结果 end-->
			
			
			<div id="fpxx" style="display: none" style="width:650px">
						<table class="formlist">
							<thead>
								<tr>
									<th>
										<span>
											分配信息
										</span>
									</th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<td>
										<p id="Bmfpxx" class="tab" style="width:100%;height:200px;overflow-x:hidden;overflow-y:auto;">
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
		</html:form>
	</body>
</html>