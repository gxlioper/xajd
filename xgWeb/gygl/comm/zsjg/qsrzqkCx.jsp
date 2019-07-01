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
			allNotEmpThenGo('/xgxt/gyglZsjg.do?method=qsrzqkCx');
		}
		
		//导出生成Excel
		function expToExcel(){
			if (confirm("将要根据您所选择的过滤条件将结果输出到Excel，请确认操作")) {
				var url = "/xgxt/gyglZsjg.do?method=qsrzqkCx&doType=exp";
				document.forms[0].action = url;
				document.forms[0].target = "_blank";
				document.forms[0].submit();
				document.forms[0].target = "_self";
			}
		}
		</script>
	</head>
	<body>
		<!-- 标题 -->
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>公寓管理 - 住宿结果 - 寝室入住情况查看</a>
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
				本功能模块仅对有学生入住的寝室进行查询。<br/>	
			</p>
			<a class="close" title="隐藏"
			   onclick="this.parentNode.style.display='none';"></a>
		</div>
		<!-- 提示信息 end-->		
		
		<html:form action="/gyglZsjg">
			<!-- 隐藏域 -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<!-- 隐藏域 -->

			<!-- 多功能操作区 -->
			<div class="toolbox">
				<!-- 按钮 -->
				<div class="buttonbox">
					<ul>
						<!-- 自动分配 -->
						<li>
							<a href="#" id="btn_dc"
								onclick="expToExcel();return false;"
								class="btn_dc">
								导出
							</a>
						</li>
						<!-- 返回 -->
						<li>
							<a href="#" id="btn_fh"
								onclick="refreshForm('gyglZsjg.do?method=zsxxTj&mklx=fh');return false;"
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
								<td onclick="" width="20px" nowrap>
									<bean:message key="lable.ld" />
								</td>
								<td onclick="" width="10px" nowrap>
									<bean:message key="lable.qs" />
								</td>
								<td onclick="" width="10px" nowrap>
									性别
								</td>
								<td onclick="" colspan="${maxCws }" nowrap>
									班级及姓名
								</td>
							</tr>
							<tr align="center" style="cursor:hand">
								<td onclick="" colspan="3" nowrap>
									床位号码
								</td>
								<logic:iterate name="cwInfoList" id="cwInfo">
									<td>
										${cwInfo.mc }
									</td>
								</logic:iterate>
							</tr>
						</thead>
						<!-- 表头 end-->
						
						<!--内容 -->
						<logic:iterate name="qsrzInfoList" id="qsInfo" indexId="index">
							<tr onclick="" style="cursor:hand">
								<td rowspan="3">
									${qsInfo.ldmc }
								</td>
								<td rowspan="3">
									${qsInfo.qsh }
								</td>
								<td rowspan="3">
									${qsInfo.xbxz }
								</td>
								<logic:iterate name="qsInfo" property="cwsList" id="rzInfo">
									<td>
										${rzInfo.xm }&nbsp;
									</td>
								</logic:iterate>
							</tr>
							<tr onclick="" style="cursor:hand">
								<logic:iterate name="qsInfo" property="cwsList" id="rzInfo">
									<td>
										${rzInfo.xh }&nbsp;
									</td>
								</logic:iterate>
							</tr>
							<tr onclick="" style="cursor:hand">
								<logic:iterate name="qsInfo" property="cwsList" id="rzInfo">
									<td>
										${rzInfo.bjmc }&nbsp;
									</td>
								</logic:iterate>
							</tr>
						</logic:iterate>
						<!--内容 end-->
					</table>
				</div>
				<!--分页-->
				<jsp:include flush="true" page="/sjcz/turnpage.jsp?form=gyglZsjgForm"></jsp:include>
				<!--分页end-->
			</div>
			<!-- 查询结果 end-->
			
		</html:form>
	</body>
</html>