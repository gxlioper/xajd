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
	<head>
		<script type="text/javascript">
			var stylePath = "<%=stylePath%>";
		</script>
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<link href="assets/plugins/bootstrap/css/bootstrap.min.css" rel="stylesheet">
		<link href="assets/css/style.css" rel="stylesheet">

		<%@ include file="/syscommon/jquery-1.11.1_migrate.ini"%>
		<script type="text/javascript" src="js/function.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/jquery/plugins/upload/ajaxfileupload.js"></script>
		<script type="text/javascript" src="js/comm/ymPrompt.js"></script>
		<script type='text/javascript' src="js/comm/message.js"></script>
		<script type='text/javascript' src="js/comm/watermark.js"></script>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script type="text/javascript" src="js/json.js"></script>
		<link rel="stylesheet" href="comm/skin/zfstyle/ymPrompt.css" type="text/css" media="all" />
		<script type="text/javascript" src="<%=stylePath%>js/lhgdialog/lhgdialog.min.js?skin=iblue"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="xsgzgl/dekt/qnzyhd/js/yfbhd.js"></script>
		<script type="text/javascript">
			//初始化
			jQuery(document).ready(function(){ 
				searchRs('1');
			})
	</script>
	</head>

	<body>
		<html:form action="/zyhd" method="post" styleId="qnzyhdForm" onsubmit="return false;">
		<%@ include file="/comm/hiddenValue.jsp"%>
			<div class="activity-list-page m-t20">	
<%--				<div class="search-wrap">--%>
<%--					<div class="container">--%>
<%--						<div class="col-lg-10 col-md-10 col-sm-12 col-xs-12 padding-lr0">--%>
<%--							<div class="col-lg-6 col-md-6 col-sm-7 col-xs-12 activity-time">--%>
<%--								<div class="col-lg-2 col-md-3 col-sm-2 col-xs-12 padding-lr0"><span>活动时间</span></div>--%>
<%--								<div class="col-lg-10 col-md-9 col-sm-4 col-xs-12 padding-lr0">--%>
<%--									<input type="text" class="form-control" name="hdkssj" id="hdkssj" onfocus="showCalendar('hdkssj','yyyy-MM-dd HH:mm',true,'hdjssj');"/>--%>
<%--								</div>--%>
<%--								<div class="col-lg-10 col-md-9 col-sm-1 col-xs-12 padding-lr0" style="margin-left: 18px">--%>
<%--									<span>至</span>--%>
<%--								</div>--%>
<%--								<div class="col-lg-10 col-md-9 col-sm-4 col-xs-12 padding-lr0" style="margin-left:1px;">								--%>
<%--									<input type="text" class="form-control" name="hdjssj" id="hdjssj" onfocus="showCalendar('hdjssj','yyyy-MM-dd HH:mm',false,'hdkssj');"/>--%>
<%--								</div>--%>
<%--							</div>--%>
<%--							<div class="col-lg-5 col-md-5 col-sm-4 col-xs-12 padding-lr0">--%>
<%--								<input type="text" class="form-control" placeholder="组织部门、活动名称、服务对象、活动负责人" title="组织部门、活动名称、服务对象、活动负责人" id="mhcx" name="mhcx" maxleng="20" onkeypress="if(pressEnter(event)){searchRs('1');return false;}"/>--%>
<%--							</div>--%>
<%--							<div class="col-lg-1 col-md-1 col-sm-1 col-xs-12 search-btn-wrap">--%>
<%--								<button type="button" class="btn btn-primary blue-bg-btn search-btn" id="search_go" onclick="searchRs('1')">搜索</button>--%>
<%--							</div>--%>
<%--						</div>--%>
<%--					</div>--%>
<%--				</div>--%>
								
				<div class="search-wrap">
					<div class="col-lg-3 col-md-3 col-sm-3 col-xs-12 padding-r0">
						<div class="col-lg-4 col-md-4 col-sm-4 col-xs-12 padding-lr0">
							<span>审核状态</span>
						</div>
						<div class="col-lg-8 col-md-8 col-sm-8 col-xs-12 padding-lr0">
							<select id="shzt" name="shzt" class="form-control">
								<option value="">
									--请选择--
								</option>
								<option value="0">
									未审核
								</option>
								<option value="1">
									通过
								</option>
								<option value="2">
									不通过
								</option>
								<option value="3">
									退回
								</option>
							</select>
						</div>
					</div>
					<div class="col-lg-4 col-md-5 col-sm-5 col-xs-12 padding-lr0">

						<div class="col-lg-4 col-md-4 col-sm-3 col-xs-12 padding-lr0">
							<span style="margin-left: 10px">活动时间</span>
						</div>
						<div class="col-lg-8 col-md-8 col-sm-9 col-xs-12 padding-lr0">

							<div class="col-lg-5 col-md-5 col-sm-5 col-xs-12 padding-lr0">
								<input type="text" name="hdkssj" id="hdkssj"
									onfocus="showCalendar('hdkssj','yyyy-MM-dd HH:mm',true,'hdjssj');"
									class="form-control" realvalue="2017-07-04 16:56:56">
							</div>
							<div class="col-lg-2 col-md-2 col-sm-2 col-xs-12">
								<span>至</span>
							</div>
							<div class="col-lg-5 col-md-5 col-sm-5 col-xs-12 padding-lr0">
								<input type="text" name="hdjssj" id="hdjssj"
									onfocus="showCalendar('hdjssj','yyyy-MM-dd HH:mm',false,'hdkssj');"
									class="form-control">
							</div>
						</div>
					</div>
					<div class="col-lg-4 col-md-3 col-sm-3 col-xs-12 padding-r0">
						<input type="text" placeholder="组织部门、活动名称、服务对象、活动负责人"
							title="组织部门、活动名称、服务对象、活动负责人" id="mhcx" name="mhcx" maxleng="20"
							onkeypress="if(pressEnter(event)){searchRs('1');return false;}}"
							class="form-control">
					</div>
					<div class="col-lg-1 col-md-1 col-sm-1 col-xs-12">
						<button style="padding: 5px 10px;"
							class="btn btn-primary blue-bg-btn search-btn" type="button"
							id="search_go" onclick="searchRs('1')">
							搜索
						</button>
					</div>
				</div>
					
				
				<div class="container" style="width:100%;padding-left:0px;padding-right:0px;">
					<div class="activity-list">
						<div id="activity-list">
							
						</div>
						
						<div class="text-center">
							<jsp:include flush="true" page="/xsgzgl/dekt/qnzyhd/turnpage.jsp?form=qnzyhdForm"></jsp:include>
						</div>
					</div>
				</div>
			</div>
	</html:form>
	
</html>
