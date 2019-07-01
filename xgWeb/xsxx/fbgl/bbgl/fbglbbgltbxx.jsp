<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript"
			src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="xsxx/fbgl/bbgl/js/fbglbbgl.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript">
			jQuery(function($){
				 var gridSetting = {
							caption:"查询结果",
							url:"fbglbbgl.do?method=fbgltbxx&type=query",
							params:getSuperSearch(),
							colList:[
							   {label:'pk',name:'pk', index: 'pk',key:true,hidden:true},
							   {label:'bjbh',name:'bjbh', index: 'bjbh',hidden:true},
							   {label:'班级代码',name:'bjdm', index: 'bjdm',formatter:bjdmFormatter},
							   {label:'班级名称',name:'bjmc', index: 'bjmc',formatter:bjmcFormatter},
							   {label:'自动分班人数上限',name:'bjrssx', index: 'bjrssx',formatter:rssxTbFormatter},
							   {label:'学生数',name:'xss', index: 'xss'}
							],
							sortname: "nj",
						 	sortorder: "asc",
						 	selectFormatter:removeCheck
				}
					var pk=jQuery("#pk").val();
			    	 var map = getSuperSearch();
			    	 map["pk"]=pk;
			    	gridSetting["params"] = map;
					$("#dataTable").initGrid(gridSetting);
					$("#gjcx").hide();
					//处理增加 删除
					var td=$(".nowrap").find("td").eq(0);
					$(td).html($("#mybutton").html());
			});
			function removeCheck(rowObject){
				return rowObject["bjbh"];
			}
		</script>
	</head>
	<body>
		<div>
			<html:form action="/fbglbbgl.do?method=fbgltbxx&type=query">
				<input type="hidden" id="pk" value="${pk}"/>
				<input type="hidden" id="xbmc" value="<bean:message key="lable.xb" />"/>
				<%@ include file="/comm/hiddenValue.jsp"%>
				<div id="gjcx" style="display: none;">
					<!-- 过滤条件 -->
					<%@ include file="/comm/search/superSearchArea.jsp"%>
				</div>
				<!-- 过滤条件 end-->
			</html:form>
			<div id="mybutton" style="display: none;">
				<img src="xsxx/fbgl/images/add-icons-1.gif" alt="增加" onclick="addfb();return false;"/>
				<img src="xsxx/fbgl/images/close-icons.gif" alt="删除" onclick="delfb();return false;"/>
			</div>
			<div class="toolbox">
				<table id="dataTable" name="tbxx" />
			</div>
		</div>
	</body>
</html>
