<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/commit.js"></script>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xszz/tjgy/xfjm/js/xfjmjg.js"></script>
		<script type="text/javascript">
			//查询结果集
			function searchRs(){
				allNotEmpThenGo('tjgy_xfjm.do?method=jgcxManage');
			}
		</script>
	</head>

	<body >
	
		<!-- 标题 -->
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>${title }</a>
			</p>
		</div>
		<!-- 标题 end-->
		
		
		<html:form action="/tjgy_xfjm" method="post">
			<%@ include file="/comm/hiddenValue.jsp"%>
			
			<div class="toolbox">
				<!-- 按钮 -->
					<div class="buttonbox">
						<ul>
							<logic:equal name="writeAble" value="yes">
								<li>
									<a href="#" class="btn_sc"
										onclick="batchData('primarykey_cbv','tjgy_xfjm.do?method=delXmsq','del');return false;"
										id="btn_qx">删除</a>
								</li>
								<li>
									<a href="#" class="btn_tj"
										onclick="expData('tjgy_xfjm.do?method=expJgcx');return false;"
										id="btn_qx">汇总统计</a>
								</li>
							</logic:equal>
							<li>
								<a href="javascript:void(0);" class="btn_down" 
									onclick="getWord('xj');return false;">下载校级登记表</a>
							</li>
							<li>
								<a href="javascript:void(0);" class="btn_down" 
									onclick="getWord('yj');return false;">下载院级登记表</a>
							</li>
						</ul>
					</div>
				<!-- 过滤条件 -->
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				</div> 

			<div class="formbox">
				<h3 class="datetitle_01">
					<span> 查询结果&nbsp;&nbsp; 
					<logic:notEmpty name="rs">
							<font color="blue">双击可以查看详细;</font>
					</logic:notEmpty> </span>
				</h3>


				<div class="con_overlfow">
					<table class="dateline tablenowrap" width="100%" id="dataTable">
						<thead>
							<tr>
								<td width="17px">
									<input type="checkbox" disabled="disabled" alt=""/>
									
								</td>
								<td onclick="tableSort(this)">
									减免项目
								</td>
								<td onclick="tableSort(this)">
									学号
								</td>
								<td onclick="tableSort(this)">
									姓名
								</td>
								<td onclick="tableSort(this)">
									年级 
								</td>
								<td onclick="tableSort(this)">
									<bean:message key="lable.xb" />
								</td>
								<td onclick="tableSort(this)">
									班级
								</td>
								<td onclick="tableSort(this)">
									<bean:message key="lable.xb" />审核
								</td>
								<td onclick="tableSort(this)">
									学校审核
								</td>
							</tr>
						</thead>
						<tbody>
							<logic:notEmpty name="rs">
								<logic:iterate name="rs" id="s">
									<tr onclick="rowOnClick(this)" 
										style="cursor:hand"
										ondblclick="showTopWin('tjgy_xfjm.do?method=xmdgsh&doType=view&pkValue=${s.xmid }${s.xh}${s.xn}','750','580');return false;"
									>
										<td>
											<input type="hidden" name="h_xh" value="${s.xh}"/>
											<input type="hidden" name="h_xmid" value="${s.xmid}"/>
											<input type="checkbox" name="primarykey_cbv" value="${s.xmid }${s.xh}${s.xn}"
												<logic:notEqual value="未审核" name="s" property="xysh">disabled="true"</logic:notEqual>
											/>
										</td>
										<td>${s.xmmc }</td>
										<td>${s.xh }</td>
										<td>${s.xm }</td>
										<td>${s.nj }</td>
										<td>${s.xymc }</td>
										<td>${s.bjmc }</td>
										<td>${s.xysh }</td>
										<td>${s.xxsh }</td>
									</tr>
								</logic:iterate>

								<%
											for (int i = 0; i < (Integer) request
											.getAttribute("maxNum")
											- ((List) request.getAttribute("rs")).size(); i++) {
								%>
								<tr>
									<td>
										<input type="checkbox" disabled="disabled" />
									</td>
									<td>&nbsp;</td>
									<td>&nbsp;</td>
									<td>&nbsp;</td>
									<td>&nbsp;</td>
									<td>&nbsp;</td>
									<td>&nbsp;</td>
									<td>&nbsp;</td>
									<td>&nbsp;</td>
								</tr>
								<%
								}
								%>

							</logic:notEmpty>
							<logic:empty name="rs">
								<%
											for (int i = 0; i < (Integer) request
											.getAttribute("maxNum"); i++) {
								%>
								<tr>
									<td>
										<input type="checkbox" disabled="disabled" />
									</td>
									<td>&nbsp;</td>
									<td>&nbsp;</td>
									<td>&nbsp;</td>
									<td>&nbsp;</td>
									<td>&nbsp;</td>
									<td>&nbsp;</td>
									<td>&nbsp;</td>
									<td>&nbsp;</td>
								</tr>
								<%
								}
								%>
							</logic:empty>
						</tbody>
					</table>
				</div>

				<jsp:include flush="true"
					page="/sjcz/turnpage.jsp?form=xfjmForm"></jsp:include>
			</div>
			
			
			<logic:present name="message">
				<script defer="defer">
					alertInfo('${message}');
				</script>
			</logic:present>
		</html:form>
	</body>
</html>
