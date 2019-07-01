<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@page import="java.util.List"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script language="javascript" src="js/function.js"></script>
		<script type="text/javascript" src="js/qtsjFunction.js"></script>
		<script type="text/javascript" src="js/commit.js"></script>
		<script language="javascript">
		//查询结果集
		function searchRs(){
			allNotEmpThenGo('gyglnew_xszsgl_xszsgl.do');
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
		//单击学号查看
		function zxsxxView(xh){
			
				var pkValue=xh;
				var url="xsxx_tygl.do?method=ckZxsxx";
				url+="&xh="+pkValue;
				var width=850;
				//showTopWin(url,850,640);
				showDialog('查看学生详细信息', 850, 500, url)
		}
		function bzxbzsz(){
			var checkbox=document.getElementsByName("primarykey_checkVal");
			var num=0;
			var xhs="";
			for(i=0; i<checkbox.length;i++){
				if(checkbox[i].checked==true){
					num++;
					xhs+=checkbox[i].value+"-";
				}
			}
			if(num==0){
				alertInfo("请勾选要设置的学生！");
				return false;
			}
			var url="gyglnew_xszsgl.do?method=bzxbzsz";
			url+="&xhs="+xhs;
			//showTopWin(url,500,300);
			showDialog('走读备注设置', 500, 250, url);
		}
		function view(url,h,w){
			//showTopWin(url + '&pkValue='+curr_row.getElementsByTagName('input')[0].value,h,w);
			//600,400
			showDialog('走读备注', 600, 300, url+ '&pkValue='+curr_row.getElementsByTagName('input')[0].value);
		}	
		
		
		function xszsxxExportConfig() {
			//DCCLBH导出功能编号,执行导出函数 
			//showOpenWindow('configureExportData.do?method=choiceExportFields&tableName='+tableName,1000,600);
			customExport("gyglnew_xszsgl_xszsgl.do", xszsxxExportData);
			}
			
		
			
		// 导出方法
		function xszsxxExportData() {
			setSearchTj();//设置高级查询条件
			var url = "gyglnew_xszsgl.do?method=xszsxxExportData&dcclbh=" + "gyglnew_xszsgl_xszsgl.do";//dcclbh,导出功能编号
			url = addSuperSearchParams(url);//设置高级查询参数
			jQuery("form").eq(0).attr("action", url);
			jQuery("form").eq(0).submit();
		}
		</script>
	</head>
	<body>
		<html:form action="/gyglnew_xszsgl" method="post">
			<!-- 隐藏域 -->
			<%@ include file="/comm/hiddenValue.jsp"%>			
			<input type="hidden" id="searchTjstr" value="<bean:write name="searchTjstr"/>"/>
			<input type="hidden" id="num" value="<bean:write name="num"/>"/>
			<!-- 隐藏域 -->
			<div class="tab_cur" >
				<p class="location">
					<em>您的当前位置:</em><a>${title }</a>
				</p>
			</div>
			<!-- 提示信息 end-->
<%--			<div class="prompt" id="promptTs">--%>
<%--				<h3>--%>
<%--					<span>系统提示：</span>--%>
<%--				</h3>--%>
<%--				<p>--%>
<%--					不勾选则默认退宿全部数据集，勾选则退宿选中学生，勾选单个学生退宿时可同时操作其入住寝室--%>
<%--				</p>--%>
<%--				<a class="close" title="隐藏"--%>
<%--				   onclick="this.parentNode.style.display='none';"></a>--%>
<%--			</div>--%>
			<!-- 提示信息 end-->	
			<div class="toolbox" id="dgncz">
				<!-- 按钮 -->
				<div class="buttonbox">
					<ul>
<%--						<li><a href="#" class="btn_dc" onclick="showTopWin('gyglnew_tsgl.do?method=tsglPladd',700,500);return false;">退宿</a></li>--%>
						<%--<li><a href="#" class="btn_dc" onclick="setSearchTj();configureExportData();return false;">导出数据</a></li>--%>
						<logic:equal name="writeAble" value="yes">
						<li><a href="#" class="btn_dc" onclick="xszsxxExportConfig();return false;">导出</a></li>
						</logic:equal>
						<li><a href="#" class="btn_sz" onclick="bzxbzsz();return false;">走读备注设置</a></li>
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
								<tr onclick="rowOnClick(this);" style="cursor:hand" ondblclick="view('gyglnew_xszsgl.do?method=xszsxxView',600,400);">
									<logic:equal name="xxdm" value="11052">
										<logic:iterate id="z" name="s" offset="10" length="1">
											<logic:equal value="否" name="z">
												<logic:iterate id="v" name="s" offset="0" length="1">
												<td align="center" width="5px">
													<input type="checkbox" id="pk_${index }"
													name="primarykey_checkVal" value="${v }"/>
												</td>
												</logic:iterate>
											</logic:equal>
											<logic:equal value="是" name="z">
												<logic:iterate id="v" name="s" offset="0" length="1">
												<td align="center" width="5px">
													<input type="checkbox" id="pk_${index }"
													name="primarykey_checkVal" value="${v }" disabled="disabled"/>
												</td>
												</logic:iterate>
											</logic:equal>
										</logic:iterate>									
										<!-- 显示信息 -->								
										
										<logic:iterate id="v" name="s" offset="0" length="10">
											<td align="left">
												${v }
											</td>
										</logic:iterate>
									</logic:equal>
									<logic:notEqual name="xxdm" value="11052">
										<logic:iterate id="z" name="s" offset="9" length="1">
											<logic:equal value="否" name="z">
												<logic:iterate id="v" name="s" offset="0" length="1">
												<td align="center" width="5px">
													<input type="checkbox" id="pk_${index }"
													name="primarykey_checkVal" value="${v }"/>
												</td>
												</logic:iterate>
											</logic:equal>
											<logic:equal value="是" name="z">
												<logic:iterate id="v" name="s" offset="0" length="1">
												<td align="center" width="5px">
													<input type="checkbox" id="pk_${index }"
													name="primarykey_checkVal" value="${v }" disabled="disabled"/>
												</td>
												</logic:iterate>
											</logic:equal>
										</logic:iterate>									
										<!-- 显示信息 -->								
										
										<logic:iterate id="v" name="s" offset="0" length="1">
											<td>
											<a class="name" style="cursor:hand" href="#" onclick="zxsxxView('${v}')" return false ;>
												${v }
											</a>
											</td>
										</logic:iterate>							
										
										<logic:iterate id="v" name="s" offset="1" length="8">
											<td align="left">
												${v }
											</td>
										</logic:iterate>
										<logic:iterate id="v" name="s" offset="10" length="2">
											<td align="left">
												${v }
											</td>
										</logic:iterate>
									</logic:notEqual>
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
			   	 	<jsp:include flush="true" page="/sjcz/turnpage.jsp?form=gyglnewXszsglForm"></jsp:include>
					<!--分页显示-->
				</div>
			</div>
		</html:form>
		
		<%@ include file="/comm/delMessage.jsp"%>
		<%@ include file="/comm/loading.jsp"%>
	</body>
</html>
