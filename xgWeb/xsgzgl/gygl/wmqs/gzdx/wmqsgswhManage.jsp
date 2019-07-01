<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- 作者：伟大的骆 -->
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script language="javascript" src="dwr/interface/getCommGygl.js"></script>
		<script language="javascript" src="js/check.js"></script>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script language="javascript" defer="defer">
		//查询结果集
		function searchRs(){
			$("doType").value="";
			allNotEmpThenGo('gzdx_gygl_wmqs_qsgswh.do');
		}
		
		//保存文明寝室个数
		function save(){
			$("doType").value="save";
			allNotEmpThenGo('gzdx_gygl_wmqs_qsgswh.do');
		}
		
		//设置文明寝室百分比
		function setWmqsbfb(){
			var obj = {csdm:"gzdx_wmqsbfb"};
			jQuery.getJSON('gzdxWmqs.do?method=getWmqsbfb', obj, function(data){
				if(data != null){
					jQuery('#wmqsbfb').val(data.csz);
				}	
			});
			tipsWindown("保存提示","id:tempDiv","300","150","true","","true","id");
		}	
		function saveSetWmqsbfb(){
			var wmqsbfb=jQuery('#wmqsbfb').val().trim();
			if(isNaN(wmqsbfb.replace("%",""))){
				alert("请输入正确的格式！");
				return false;
			}
			var obj = {csz:jQuery('#wmqsbfb').val().trim()};
			jQuery.post('gzdxWmqs.do?method=saveWmqsbfb', obj, function(data){
				if(data != null){
					alert(data);
					searchRs();
				}	
			});
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
				用于设置各<bean:message key="lable.xb" />文明寝室审核通过的数量
			</p>
			<a class="close" title="隐藏"
			   onclick="this.parentNode.style.display='none';"></a>
		</div>
		<!-- 提示信息 end-->		
		
		<html:form action="/gzdxWmqs.do?method=qsgswhManage">
			<!-- 隐藏域 -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<input type="hidden" name="primarykey_checkVal" id="primarykey_checkVal" />
			<!-- 隐藏域 -->

			<!-- 多功能操作区 -->
			<div class="toolbox">
				<!-- 按钮 -->
				<logic:equal value="yes" name="writeAble">
				<div class="buttonbox">
					<ul>						
						<!-- 手动分配 -->
						<li>
							<a href="#" id="btn_xg"
								onclick="save();return false;"
								class="btn_xg">
								保存
							</a>
						</li>
						<li>
							<a href="#" id="btn_sz"
								onclick="setWmqsbfb();return false;"
								class="btn_sz">
								参数设置
							</a>
						</li>
					</ul>
				</div>
				</logic:equal>		
				<!-- 按钮 end-->	
				
				<!-- 过滤条件 -->
				
<%--				<%@ include file="/comm/search/superSearchArea.jsp"%>--%>
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
							<td width="5px" style="display: none;">
								<input type="checkbox" id="selall" name="selall" onclick="selAll()" />
							</td>
							<logic:iterate id="tit" name="topTr" offset="0" >
								<td id="<bean:write name="tit" property="en"/>"
									onclick="tableSort_hc(this,1)"
									nowrap>
									<bean:write name="tit" property="cn" />
								</td>
							</logic:iterate>
						</tr>
					</thead>
					<!-- 表头 end-->
					<!--内容 -->
					<logic:empty name="rsArrList">
					  	<%
						for(int i=0; i<11; i++){
						%>
						<tr>
							<td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td>
							<td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td>
					 	</tr>
						<%}%>
					 </logic:empty>
					<logic:iterate name="rsArrList" id="s" indexId="index">
						<tr onclick="rowOnClick(this);" style="cursor:hand">
							<logic:iterate id="v" name="s" offset="0" length="1">
								<td align="center" width="5px" style="display: none;">
									<input type="checkbox" id="pk_${index }"
									value="${v }"/>
									<input type="hidden"  name="xydms" value="${v }"/>
								</td>
							</logic:iterate>
							<!-- 显示信息 -->
							<logic:iterate id="v" name="s" offset="1" length="3">
								<td align="left">
									${v }
								</td>
							</logic:iterate>
							<logic:iterate id="v" name="s" offset="4" length="1">
								<td align="left">
									<input type="text" name="wmqsgss" value="${v }" onkeyup="checkInputData(this);" maxlength="4"/>
								</td>
							</logic:iterate>
						</tr>
					</logic:iterate>
					<!--内容 end-->
					<!-- 补空行 -->
					<%--<%@ include file="/comm/noRows.jsp"%>
					--%><!-- 补空行 end-->
				</table>
				</div>
				<!--分页-->
<%--				<jsp:include flush="true" page="/sjcz/turnpage.jsp?form=gzdxGyglWmqsForm"></jsp:include>--%>
				<script type="text/javascript">
					//$('choose').className="hide";
				</script>
				<!--分页end-->
			</div>
			<!-- 查询结果 end-->
			<logic:present name="back">
				<script type="text/javascript">
					alert("${back}");				
				</script>
			</logic:present>
			
			
			
						<div id="tempDiv" style="display: none">
				<div class="open_win01">
					<table align="center" class="formlist">
						<thead>
							<tr>
								<th colspan="2">
									<span id="th_span_lable">请设置文明寝室百分比</span>
								</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<th id="th_rzsj">
									文明寝室百分比
								</th>
								<td>
									<input type="text" name="wmqsbfb" id="wmqsbfb" maxlength="6"/>
								</td>
							</tr>
						</tbody>
						<tfoot>
							<tr>
								<td colspan="2">
									<div id="submit_bz" class="bz" style="color: red;">
										
									</div>
									<div class="btn">
										<button type="button" name="保存"  onclick="saveSetWmqsbfb()">
											确 定 
										</button>
										<button type="button" name="取消"  onclick="closeWindown();return false;">
											取 消
										</button>
									</div>
								</td>
							</tr>
						</tfoot>
					</table>
				</div>
			</div>
			
			
			
		</html:form>
	</body>
</html>