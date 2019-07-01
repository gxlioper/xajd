<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@page import="java.util.List"%>
<%@page import="xgxt.utils.Pages"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/function.js"></script>
		<script type="text/javascript" src="js/qtsjFunction.js"></script>
		<script type="text/javascript" src="js/commit.js"></script>
		<script language="javascript">
			jQuery(function(){
				//查询
				//jQuery("#search_go").click(function(){allNotEmpThenGo("gyglnew_mrjlgl.do?method=mrjlglManage");});
				
				//重置
				jQuery("#btn_cz").click(function(){searchReset();});
				
				//增加
				jQuery("a[class='btn_zj']").click(function(){
					showTopWin('gyglnew_mrjlgl.do?method=mrjlglUpdate',800,550);return false;
				});
				
				//修改
				jQuery("a[class='btn_xg']").click(function(){
					modi('gyglnew_mrjlgl.do?method=mrjlglModi',800,550);return false;
				});
				
				//删除
				jQuery("a[class='btn_sc']").click(function(){
					batchData("primary_cbv","gyglnew_mrjlgl.do?method=mrjlglManage&doType=del","del");return false;
				});
				
				//导出
				jQuery("a[class='btn_dc']").click(function(){
					setSearchTj();
					configureExportData();
					return false;
				});
				
				//导出
				jQuery("a[class='btn_qx']").click(function(){
					choiceFields();return false;
				});
				
			});
			
				//查询结果集
			function searchRs(){
				allNotEmpThenGo("gyglnew_mrjlgl.do?method=mrjlglManage");
			}
			
		</script>
		
	</head>
	<body>
		<html:form action="/gyglnew_mrjlgl" method="post">
			<input type="hidden" name="dcqx" value="no"/>
			<!-- 隐藏域 -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			
			<div class="tab_cur" >
				<p class="location">
					<em>您的当前位置:</em><a>${title }</a>
				</p>
			</div>			
			
			<logic:equal value="yes" name="writeAble">
			<div class="toolbox" id="dgncz">
				<!-- 按钮 -->
				<div class="buttonbox">
					<ul>
						<li><a href="#" class="btn_zj">增加</a></li>
						<li><a href="#" class="btn_xg">修改</a></li>
						<li><a href="#" class="btn_sc">删除</a></li>
						<li><a href="#" class="btn_dc">导出数据</a></li>
						<li><a href="#" class="btn_qx">导出字段确认</a></li>
					</ul>
				</div>
			</div>
			</logic:equal>
			<logic:equal name="superSearch" value="yes">
		     	<%@ include file="/comm/search/superSearchArea.jsp"%>
		    </logic:equal>
	
			<div class="formbox">
					<h3 class="datetitle_01">
						<span>
							查询结果&nbsp;&nbsp;<font color="blue">单击表头可以排序;双击记录查看详细信息</font>
						</span>
					</h3>
					<table width="99%" id="rsTable" class="dateline">
						<thead>
							<tr align="center" style="cursor:hand">
								<td><input type="checkbox" name="checAll"/></td>
								<logic:iterate id="tit" name="topTr" offset="1">
									<td onclick="tableSort(this)">
										${tit}
									</td>
								</logic:iterate>
							</tr>
						</thead>
						<logic:empty name="rs">
						  <%
							for(int i=0; i<11; i++){
							%>
							<tr>
								<td>
									<input type="checkbox" disabled="disabled"></input>
								</td>
								<logic:iterate id="tit" name="topTr" offset="1">
									<td>
										&nbsp;
									</td>
								</logic:iterate>	
						 	</tr>
							<%}%>
						 </logic:empty>
						<logic:notEmpty name="rs">
							<logic:iterate name="rs" id="s">
								<tr onclick="rowOnClick(this);" style="cursor:hand" ondblclick="modi('gyglnew_mrjlgl.do?method=mrjlglModi&doType=view',800,600);">
									<td> 
										<logic:iterate id="v" name="s" offset="0" length="1">
											<input type="checkbox" name="primary_cbv" value="${v }"/>
										</logic:iterate>
									</td>
									<logic:iterate id="v" name="s" offset="1">
										<td>${v }</td>
									</logic:iterate>
								</tr>
							</logic:iterate>
							<%
							int rsNum = ((List)request.getAttribute("rs")).size();
							int pageSize = (Integer)request.getAttribute("pageSize");
							if(rsNum < pageSize){
								for(int i=0; i<(pageSize-rsNum); i++){
							%>
							<tr>
								<td><input type="checkbox" disabled="disabled"/></td>
								<logic:iterate id="tit" name="topTr" offset="1">
									<td>
										&nbsp;
									</td>
								</logic:iterate>
						 	</tr>
							<%}}%>
					</logic:notEmpty>
				</table>
				<!--分页显示-->
		   	 	<jsp:include flush="true" page="/sjcz/turnpage.jsp?form=gyglnewMrjlglForm"></jsp:include>
				<!--分页显示-->
			</div>
			
		</html:form>
		<logic:present name="message">
			<input type="hidden" id="message" name="message" value="${message }"/>
			<script type="text/javascript">
				alertInfo($('message').value);
			</script>
		</logic:present>
	</body>
</html>
