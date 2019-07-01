<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@page import="java.util.List"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- 作者：zhangh -->
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script language="javascript" src="dwr/interface/getCommGygl.js"></script>
		<script type="text/javascript" src="js/commit.js"></script>
		<script language="javascript" defer="defer">
		//查询结果集
		function searchRs(){
			$("doType").value="";
			allNotEmpThenGo('gzdx_gygl_wmqs_qssh.do');
		}
		
		function update(url){
			if(curr_row != null){
				url += "&pk="+curr_row.getElementsByTagName('input')[0].value;
				url += "&xh="+curr_row.getElementsByTagName('input')[1].value;
				showTopWin(url,800,600);
				return true;
			}else{
				alertInfo('请选择要操作的数据行！');
				return false;
			}
		}

		function saveWmqssh(){
			if(curr_row != null){
				$("primarykey_checkVal").value=curr_row.getElementsByTagName('input')[0].value;
				tipsWindown("保存提示","id:tempDiv","350","200","true","","true","id");
				return true;
			}else{
				alertInfo('请选择要操作的数据行！');
				return false;
			}

			
<%--			var checkbox=document.getElementsByName("primarykey_checkVal");--%>
<%--			var count=0;--%>
<%--			for(var i=0;i<checkbox.length;i++){--%>
<%--				if(checkbox[i].checked){--%>
<%--					count++;--%>
<%--				}--%>
<%--			}--%>
<%--			if(count==0){--%>
<%--				alertInfo("请选择需要审核的文明寝室！");--%>
<%--				return false;--%>
<%--			}--%>
<%--			if(count>1){--%>
<%--				alertInfo("只能操作一个寝室！");--%>
<%--				return false;--%>
<%--			}--%>
<%--			tipsWindown("保存提示","id:tempDiv","350","200","true","","true","id");--%>
		}

		function saveWmqsshSubmit(){
			if($("shzt").value==""){
				alertInfo("请选择审核状态！");
				return false;
			}
			if($("shbz").value.length>100){
				alertInfo("备注信息最多100字！");
				return false;
			}
			$("doType").value="save";
			allNotEmpThenGo('gzdx_gygl_wmqs_qssh.do');
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
		<!-- 标题 end-->
		
		
		<html:form action="/gzdxWmqs.do?method=qsshManage">
			<!-- 隐藏域 -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<input type="hidden" id="primarykey_checkVal" name="primarykey_checkVal" value=""/>
			<!-- 隐藏域 -->

			<!-- 多功能操作区 -->
			<div class="toolbox">
				<!-- 按钮 -->
				<logic:equal value="yes" name="writeAble">						
				<div class="buttonbox">
					<ul>
						<li><a href="#" onclick="saveWmqssh();return false;" class="btn_sh">审核 </a></li>
					</ul>
				</div>
				</logic:equal>
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
				<table summary="" class="dateline" width="100%">
					<!-- 表头 -->
					<thead>
						<tr align="center" style="cursor:hand">
							<td width="5px" style="display: none;">
								<input type="checkbox" id="selall" name="selall" onclick="selAll()" />
							</td>
							<logic:iterate id="tit" name="topTr" offset="0" >
								<td id="<bean:write name="tit" property="en"/>"
									onclick="tableSort(this)">
									<bean:write name="tit" property="cn" />
								</td>
							</logic:iterate>
						</tr>
					</thead>
					<!-- 表头 end-->
					<!--内容 -->
					<logic:iterate name="rsArrList" id="s" indexId="index">
						<tr onclick="rowOnClick(this);" ondblclick="" style="cursor:hand">
							<td align="center" width="5px" style="display: none;">
								<logic:iterate id="v" name="s" offset="0" length="1">
									<input type="checkbox" id="pk_${index }" name="primarykey_checkVal_checkbox" value="${v }"/>
								</logic:iterate>
								
								<logic:iterate id="v" name="s" offset="4" length="1">
									<input type="hidden" value="${v }"/>
								</logic:iterate>
							</td>
							<!-- 显示信息 -->
							<logic:iterate id="v" name="s" offset="1">
								<td align="left">
									${v }
								</td>
							</logic:iterate>
						</tr>
					</logic:iterate>
					<%
					int rsNum = ((List)request.getAttribute("rsArrList")).size();
					int pageSize = (Integer)(request.getAttribute("pageSize"));
					if(rsNum < pageSize){
						for(int i=0; i<(pageSize-rsNum); i++){
					%>
					<tr>
						<td style="display: none;">
							<input type="checkbox" disabled="disabled"></input>
						</td>
						<logic:iterate id="tit" name="topTr" offset="0">
							<td>
								&nbsp;
							</td>
						</logic:iterate>
				 	</tr>
					<%}}%>
				</table>
				<!--分页-->
				<jsp:include flush="true" page="/sjcz/turnpage.jsp?form=gzdxGyglWmqsForm"></jsp:include>
				<script type="text/javascript">
					$('choose').className="hide";
				</script>
				<!--分页end-->
				</div>
			</div>
			<!-- 查询结果 end-->
			
			
			
			
			
			
			<div id="tempDiv" style="display: none">
				<div class="open_win01">
					<table align="center" class="formlist">
						<thead>
							<tr>
								<th colspan="2">
									<span id="th_span_lable">请选择审核状态</span>
								</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<th id="th_rzsj">
									审核状态
								</th>
								<td>
									<select name="shzt" id="shzt" style="width: 185px;">
										<option value=""></option>
										<option value="通过">通过</option>
										<option value="不通过">不通过</option>
									</select>
								</td>
							</tr>
							<tr id="tr_tsyy">
								<th>
									备注
								</th>
								<td>
									<textarea rows="3" cols="23" name="shbz" id="shbz"></textarea>
								</td>
							</tr>
						</tbody>
						<tfoot>
							<tr>
								<td colspan="2">
									<div id="submit_bz" class="bz" style="color: red;">
										
									</div>
									<div class="btn">
										<button type="button" name="保存"  onclick="saveWmqsshSubmit()">
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
			
			<logic:present name="message">
				<script>
					alert("${message}");
				</script>
			</logic:present>
			
		</html:form>
	</body>
</html>