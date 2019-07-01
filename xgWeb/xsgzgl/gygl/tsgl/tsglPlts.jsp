<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@page import="java.util.List"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/function.js"></script>
		<script type="text/javascript" src="js/qtsjFunction.js"></script>
		<script type="text/javascript" src="js/commit.js"></script>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script language="javascript">
		//查询结果集
		function searchRs(){
			allNotEmpThenGo('gyglnew_tsgl_plts.do');
		}
		
		function modi(url,h,w){
			if(curr_row != null){
				showTopWin(url + '&pkValue='+curr_row.getElementsByTagName('input')[0].value,h,w);
				return true;
			}else{
				alert('请选择要操作的数据行！');
				return false;
			}
		}
		</script>
	</head>
	<body>
		<html:form action="/gyglnew_tsgl" method="post">
			<!-- 隐藏域 -->
			<%@ include file="/comm/hiddenValue.jsp"%>			
			<input type="hidden" id="searchTjstr" value="<bean:write name="searchTjstr"/>"/>
			<input type="hidden" id="num" value="<bean:write name="num"/>"/>
			<!-- 隐藏域 -->
			<div class="tab_cur" >
				<p class="location">
					<em>您的当前位置:</em><a>${title }</a>
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
					不勾选则默认退宿全部数据集，勾选则退宿选中学生，勾选单个学生退宿时可同时操作其入住寝室
				</p>
				<a class="close" title="隐藏"
				   onclick="this.parentNode.style.display='none';"></a>
			</div>
			<!-- 提示信息 end-->	
			<div class="toolbox" id="dgncz">
				<!-- 按钮 -->
				<div class="buttonbox">
					<ul>
						<li><a href="#" class="btn_dc" onclick="showTopWin('gyglnew_tsgl.do?method=tsglPladd',700,500);return false;">退宿</a></li>
						<li><a href="gyglnew_tsgl_tsgl.do" class="btn_cx" >查看退宿信息</a></li>
					</ul>
				</div>
				<!-- 过滤条件 -->
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- 过滤条件 end-->
			</div>
				<div class="formbox">
					<h3 class="datetitle_01">
						<span>
							查询结果&nbsp;&nbsp;<font color="blue">单击表头可以排序;</font>
						</span>
					</h3>
					<table width="99%" id="rsTable" class="dateline">
						<thead>
							<tr align="center" style="cursor:hand">
								<td>
									<input type="checkbox" name="all" value="all" onclick="chec()"/>
								</td>
								<logic:iterate id="tit" name="topTr" offset="0" >
									<td id="<bean:write name="tit" property="en"/>"
										onclick="tableSort(this)"
										nowrap>
										<bean:write name="tit" property="cn" />
									</td>
								</logic:iterate>
							</tr>
						</thead>
						<logic:empty name="rsArrList">
						  <%
							for(int i=0; i<11; i++){
							%>
							<tr>
								<td>
									<input type="checkbox" name="pk" value="" disabled="disabled"></input>
								</td>
								<logic:iterate id="tit" name="topTr" offset="0">
									<td>
										&nbsp;
									</td>
								</logic:iterate>	
						 	</tr>
							<%}%>
						 </logic:empty>
						<logic:notEmpty name="rsArrList">
							<logic:iterate name="rsArrList" id="s">
								<logic:iterate name="s" id="dis" offset="1" length="1"></logic:iterate>
								<tr onclick="rowOnClick(this);" style="cursor:hand" >
									<logic:iterate id="v" name="s" offset="0" length="1">
										<td align="center" width="5px">
											<input type="checkbox" id="pk_${index }"
											name="primarykey_checkVal" value="${v }"/>
										</td>
									</logic:iterate>
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
								<td>
									<input type="checkbox" name="primarykey_cbv" value="" disabled="disabled"></input>
								</td>
								<logic:iterate id="tit" name="topTr" offset="0">
									<td>
										&nbsp;
									</td>
								</logic:iterate>
						 	</tr>
							<%}}%>
						</logic:notEmpty>
					</table>
					<!--分页显示-->
			   	 	<jsp:include flush="true" page="/sjcz/turnpage.jsp?form=gyglnewTsglForm"></jsp:include>
					<!--分页显示-->
				</div>
			</div>
		</html:form>
		
		<%@ include file="/comm/delMessage.jsp"%>
		<%@ include file="/comm/loading.jsp"%>
	</body>
</html>
