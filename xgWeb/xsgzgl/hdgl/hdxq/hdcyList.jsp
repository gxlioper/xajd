<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-tiles"
	prefix="tiles"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-nested" prefix="nested"%>
<%@ taglib uri="/WEB-INF/customTag.tld" prefix="customTag"%>
<%@ include file="/syscommon/v4_url.ini"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<%--	<head>--%>
<%--				<%@ include file="/syscommon/head.ini"%>--%>
<%--		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>--%>
<%--		<script type="text/javascript" src="js/calendar/calendar.js"></script>		--%>
<%--		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>--%>
<%--		<script type="text/javascript" src="xsgzgl/hdgl/js/hdxq.js"></script>--%>
		
<%--	</head>--%>

<%--	<body>--%>
		<script type="text/javascript">
			jQuery(function(){
				searchRy('1');
			})
			//导出有票成员
			function exportCy() {
                showConfirmDivLayer("将为您导出该活动有票人员名单", {
                    "okFun" : function() {
                        exportConfig();
                    }
                });
            }
		</script>
		<html:form action="/hdgl_hdxq" method="post" styleId="hdxqForm" onsubmit="return false;">
		<div class="apply-management-page m-t20">
			<div class="top-content">
				<div class="container padding-l0" style="width:100%;">
					<input type="hidden" id="ymmc" name="ymmc" value="hdcyList"/>
					<input type="hidden" id="hdid" name="hdid" value="${data.hdid}"/>
					<input type="hidden" id="bmlx" name="bmlx" value="${data.bmlx}"/>
					<div class="operate-process">
						<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 padding-lr0 btn-wrap text-left padding-r0">
						<logic:equal name="data" property="bmlx" value="1">
							<logic:equal name="data" property="bmsfsh" value="1">
							<button type="button" class="btn btn-primary cancel-btn" onclick="sh();return false;">审核</button>
							</logic:equal>
							<button type="button" class="btn btn-primary green-bg-btn" onclick="getPpView();return false;">派票</button>
						</logic:equal>
							<button type="button" class="btn btn-primary green-bg-btn" onclick="exportCy();return false;">导出人员</button>
						</div>

					</div>
					<div class="search-wrap m-t20 clearfix" style="margin-top:50px">
						<ul>
							<li class="col-lg-3 col-md-3 col-sm-6 col-xs-12 padding-lr0 status">
								<div class="col-lg-3 col-md-3 col-sm-3 col-xs-2 padding-20"><span>审核状态</span></div>
								<div class="col-lg-9 col-md-9 col-sm-9 col-xs-10 padding-l0">
									<select name="shzt" id="shzt" class="form-control" style="width: 150px">
										<option value="">--请选择--</option>
										<option value="5">审核中</option>
										<option value="1">已通过</option>
										<option value="2">已拒绝</option> 
									</select>
								</div>
							</li>
							<li class="col-lg-4 col-md-4 col-sm-5 col-xs-12 padding-lr0">
								<input type="text" class="form-control" placeholder="姓名/学号" name="cxzd">
							</li>
							<li class="col-lg-1 col-md-1 col-sm-1 col-xs-12 padding-r0 text-right">
								<button class="btn btn-primary blue-bg-btn search-btn" type="button" onclick="searchRy('1');return false;" id="search_go">搜索</button>
							</li>
						</ul>
					</div>
				</div>
			</div>
			<div class="container" style="width:100%;">
				<div class="table-responsive">
					<table class="table table-bordered text-center">
						<thead>
							<tr>
								<th><input type="checkbox" onchange="selectAll(this);return false;" id="qx"></th>
								<th>学号</th>
								<th>姓名</th>
								<th>学院</th>
								<th>报名时间</th>
								<logic:equal name="data" property="bmlx" value="0"><%--组队--%>
									<th>队伍编号</th>
									<th>分组</th>
								</logic:equal>
								<th>审核状态</th>
								<th>派票状态</th>
							</tr>
						</thead>
						<tbody id="ryTbody">
							
						</tbody>
					</table>
				</div>
				<div class="text-center">
					<jsp:include flush="true" page="/xsgzgl/hdgl/hdxx/turnpage.jsp?form=hdxxForm"></jsp:include>
				</div>
				
			</div>
		</div>
	</html:form>
<%--	</body>--%>
</html>
