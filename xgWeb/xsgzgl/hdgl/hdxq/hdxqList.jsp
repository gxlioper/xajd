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
		
		
		<script type="text/javascript" src="js/function.js"></script>
		<%@ include file="/syscommon/jquery-1.11.1_migrate.ini"%>
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
		<script type="text/javascript" src="xsgzgl/hdgl/js/hdxq.js"></script>
		<script type="text/javascript" src="xsgzgl/hdgl/js/dist/zoomify.js"></script>
		<script type="text/javascript">
			//³õÊ¼»¯
			jQuery(document).ready(function(){ 
				searchHd('1');
			})
		</script>
		<style type="text/css">
			.div_hdbq{
				float: left;


			}
		</style>
	</head>

	<body>
		<html:form action="/hdgl_hdxq" method="post" styleId="hdxqForm" onsubmit="return false;">
			<input type="hidden" id="ymmc" name="ymmc" value="hdxqList"/>
            <div class="container" style="padding-right:-5px;padding-left:-5px;">
                <div class="row">
                    <div class="col-lg-10 col-md-10 col-sm-12 col-xs-12 padding-lr0">
                        <div class="col-lg-5 col-md-5 col-sm-6 col-xs-12 padding-lr0">
                            <select id="hdlxSelect" name="hdlx" class="selectpicker show-tick form-control">
                                <option value=""></option>
                                <logic:iterate id="item" collection="${hdlxList}">
                                    <option value="${item.hdlxdm}">${item.hdlxmc}</option>
                                </logic:iterate>
                            </select>
                        </div>
                        <div class="col-lg-1 col-md-1 col-sm-1 col-xs-12 search-btn-wrap">
                            <button type="button" class="btn btn-primary blue-bg-btn search-btn" id="search_go" onclick="searchHd('1')">ËÑË÷</button>
                        </div>
                    </div>
                </div>
            </div>
			<div class="container" id="activity-list" style="width:100%;">
				
<%--				<div class="text-center">--%>
<%--					<jsp:include flush="true" page="/xsgzgl/hdgl/hdxx/turnpage.jsp?form=hdxxForm"></jsp:include>--%>
<%--				</div>								--%>
			</div>
			<div class="text-center">
				<jsp:include flush="true" page="/xsgzgl/hdgl/hdxx/turnpage.jsp?form=hdxxForm"></jsp:include>
			</div>
		</html:form>
	</body>
</html>
