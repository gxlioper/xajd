<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript">
			//查询结果集
			var gridSetting ;
			jQuery(function(){
				var url = "gygl_lfrydj.do?method=xsxxView&type=query&pk="+ encodeURI(encodeURI(jQuery("#pk").val()))+"&userType="+jQuery("#userType").val();
				gridSetting = {
						caption:"学生信息",
						pager:"pager",
						url:url,			
						colList:[
							{label:'学号',name:'xh', index: 'xh',width:'12%'},
							{label:'姓名',name:'xm', index: 'xm',width:'15%'},
							{label:'年级',name:'nj', index: 'nj',width:'10%'},
							{label:'<bean:message key="lable.xb" />',name:'xymc', index: 'xydm',width:'15%'},
							{label:'班级',name:'bjmc', index: 'bjdm',width:'15%'},
							{label:'楼栋',name:'ldmc', index: 'ldmc',width:'15%'},
							{label:'宿舍',name:'qsh', index: 'qsh',width:'10%'},
							{label:'床位号',name:'cwh', index: 'cwh',width:'5%'}
						],
						sortname: "xh",
					 	sortorder: "asc"
				};			
				jQuery("#dataTable").initGrid(gridSetting);	
			});
		</script>
	</head>

	<body>
		<html:form action="/gygl_lfrydj">
			<%@ include file="/comm/hiddenValue.jsp"%>
			<input type = "hidden" id = "pk" value = "${pk}"  />
			<input type = "hidden" id = "userType" value = "${userType}"  />
		</html:form>
		
		<div class="main_box">
			<h3 class=datetitle_01>
				<span>学生详细信息</span>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable" ></table>
				<div id="pager"></div>
			</div>
		</div>
	</body>
</html>
