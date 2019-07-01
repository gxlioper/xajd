<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@page import="common.Globals"%>
<%@page import="xgxt.action.Base"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- 作者：伟大的骆 -->
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script language="javascript" src="dwr/interface/getCommGygl.js"></script>
		
		<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
		<script type='text/javascript' src='/xgxt/dwr/util.js'></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		
		<script language="javascript" defer="defer">
		//查询结果集
		function searchRs(){
			allNotEmpThenGo('gyglnew_tsgl_tsgl.do');
		}
		
		//删除退宿信息
		function deleteTsglInfo(){
		
			var pkArr=document.getElementsByName("primarykey_checkVal");
			
			var flag=false;
			for(i=0;i<pkArr.length;i++){
				
				if(pkArr[i].checked){
					
					flag=true;
				}
			}
			
			if(flag){
				
				confirmInfo("该操作将会删除您所勾选的数据，是否确定继续操作？",function(tag){
					if(tag=="ok"){
						refreshForm('gyglnew_tsgl_tsgl.do?doType=delete');
					}
				});
				
			}else{
				alertInfo("请勾选需要删除的数据!",function(tag){
					if(tag=="ok"){
						return false;
					}
				});
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
		
		function tsxxcxExportConfig() {
			//DCCLBH导出功能编号,执行导出函数 
			customExport("gyglnew_tsgl_tsgl.do", tsxxcxExportData);
		}
				
			
		
		// 导出方法
		function tsxxcxExportData() {
			setSearchTj();//设置高级查询条件
			var url = "gyglnew_tsgl.do?method=tsxxcxExportData&dcclbh=" + "gyglnew_tsgl_tsgl.do";//dcclbh,导出功能编号
			url = addSuperSearchParams(url);//设置高级查询参数
			jQuery("form").eq(0).attr("action", url);
			jQuery("form").eq(0).submit();
		}

		function modi1(url,h,w){
			if(curr_row != null){
				//showTopWin(;
				showDialog("退宿信息查看", 760, 400, url + '&pkValue='+curr_row.getElementsByTagName('input')[0].value);
				return true;
			}else{
				alertInfo('请选择要修改的数据行！');
				return false;
			}
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
		
		
		<html:form action="/gyglnew_tsgl">
			<!-- 隐藏域 -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<!-- 隐藏域 -->

			<!-- 多功能操作区 -->
			<div class="toolbox">
				<!-- 按钮 -->
				<div class="buttonbox">
					<ul>
						<logic:equal name="writeAble" value="yes">
							<li><a href="#" class="btn_sc" onclick="deleteTsglInfo();return false;">删除</a></li>
						</logic:equal>
						
						<%--<li><a href="#" class="btn_dc" onclick="setSearchTj();configureExportData();return false;">导出数据</a></li>
						--%><li><a href="#" class="btn_dc" onclick="tsxxcxExportConfig();return false;">导出</a></li>
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
				<div class="con_overlfow" >
				<table summary="" class="dateline" align="" width="100%">
					<!-- 表头 -->
					<thead>
						<tr align="center" style="cursor:hand">
							<td width="5px">
								<input type="checkbox" id="selall" name="selall" onclick="selAll()" />
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
					<!-- 表头 end-->
					<!--内容 -->
					<logic:iterate name="rsArrList" id="s" indexId="index">
						<tr onclick="rowOnClick(this);" ondblclick="modi1('gyglnew_tsgl.do?method=tsglView',700,500);" style="cursor:hand">
							<logic:iterate id="v" name="s" offset="0" length="1">
								<td align="center" width="5px">
									<input type="checkbox" id="pk_${index }"
									name="primarykey_checkVal" value="${v }"/>
								</td>
							</logic:iterate>
							<!-- 显示信息 -->
							<logic:iterate id="v" name="s" offset="1" length="1">
									<td>
									<a class="name" style="cursor:hand" href="#" onclick="zxsxxView('${v}')" return false ;>
										${v }
									</a>
									</td>
							</logic:iterate>
							
							
							<logic:iterate id="v" name="s" offset="2" length="8">
								<td align="left">
									${v }
								</td>
							</logic:iterate>
							<logic:iterate id="v" name="s" offset="10" length="1">
								<td title="<logic:iterate id="vv" name="s" offset="11" length="1">${vv }</logic:iterate>">${v }</td>
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
				</table>
				<!--分页-->
				<jsp:include flush="true" page="/sjcz/turnpage.jsp?form=gyglnewTsglForm"></jsp:include>
				<!--分页end-->
				</div>
			</div>
			<!-- 查询结果 end-->
		
		<!-- 提示信息 -->
		<%@ include file="/comm/other/tsxx.jsp"%>
		</html:form>
		
	</body>
</html>