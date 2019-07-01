<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/check.js"></script>
		<script language="javascript" src="js/function.js"></script>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script language="javascript" src="js/Basic.js"></script>
	</head>
	<body >
		<html:form action="/xtwh_wdgz" method="post">
			<div class="tab_cur" >
				<p class="location">
					<em>您的当前位置:</em><a>首页-
						<logic:equal value="stu" name="userType">
							我的办结申请
						</logic:equal>
						<logic:notEqual value="stu" name="userType">
							待办工作
						</logic:notEqual>
					</a>
				</p>
			</div>
			
			<button type="button" style="display:none;" id="search_go" onclick="allNotEmpThenGo('/xgxt/xtwh_wdgz.do?method=getMoreJobs');" </button>
			
			
			<!-- 提示信息 end-->	
			<!-- 模块类型 -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<div class="toolbox" id="dgncz">
			</div>
			<div class="formbox" id="cxjg">
<%--				<h3 class="datetitle_01">--%>
<%--					<span>--%>
<%--						查询结果&nbsp;&nbsp;--%>
<%--					</span>--%>
<%--				</h3>--%>
				<div class="con_overlfow">
					<table width="99%" id="rsTable" class="dateline tablenowrap">
						<thead>
							<tr align="center" style="cursor:hand">
								<td>
									<logic:equal value="stu" name="userType">
										我的办结申请
									</logic:equal>
									<logic:notEqual value="stu" name="userType">
										待办工作
									</logic:notEqual>
								</td>
							</tr>
						</thead>
						<tbody>
							<logic:notEmpty name="jobsList">
								<logic:iterate id="j" name="jobsList">
									<tr>
										<td>
											
											<logic:equal value="stu" name="userType">
												<a class="name" href="${j.gnmkpath }">【${j.gznr }】${j.shztmc }。</a>
											</logic:equal>
											<logic:notEqual value="stu" name="userType">
												<logic:equal value="szdw_thjl_zdgzxsk.do" property="gnmkpath" name="j">
													<a class="name" href="${j.gnmkpath }">【${j.gznr}】中，有&nbsp;<font color='red'>${j.dbs } </font>&nbsp;人需要您关注。</a>
												</logic:equal>
												<logic:notEqual value="szdw_thjl_zdgzxsk.do" property="gnmkpath" name="j">
													<a class="name" href="${j.gnmkpath }">【${j.gznr}】审批流程中，有&nbsp;<font color='red'>${j.dbs } </font>&nbsp;人需要您审核。</a>
												</logic:notEqual>
											</logic:notEqual>
										</td>
									</tr>
								</logic:iterate>
							</logic:notEmpty>
						</tbody>
					</table>
				</div>

				<!--分页显示-->
				<jsp:include flush="true" page="/sjcz/turnpage.jsp?form=myJobsForm"></jsp:include>
				<script type="text/javascript">
					$('choose').className="hide";
				</script>
				<!--分页显示-->
			</div>
		</html:form>

	</body>
</html>
