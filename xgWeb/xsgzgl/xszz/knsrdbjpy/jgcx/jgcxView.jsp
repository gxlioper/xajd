<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript">

		jQuery(function(){
			var gridSetting = {
					caption:"学生列表",
					pager:"pager",
					url:"xszz_knsrdbjpy_jgcxgl.do?method=jgcxView&type=query&xn=${rs.xn}&xq=${rs.xq}&sqr=${rs.sqr}&shztbjpy=${rs.shztbjpy}",
					colList:[      
				         {label:'key',name:'guid', index: 'guid',hidden:true,key:true},
				         {label:'学号',name:'xh', index: 'xh',width:'10%'},
						   {label:'姓名',name:'xm', index: 'xm',width:'8%'},
						   {label:'年级',name:'nj', index: 'nj',width:'7%'},
<%--						   {label:'<bean:message key="lable.xb" />',name:'xymc', index: 'xydm',width:'14%'},--%>
<%--						   {label:'专业',name:'zymc', index: 'zydm',width:'16%'},--%>
						   {label:'班级',name:'bjmc', index: 'bjdm',width:'20%'},
						   {label:'是否学生代表',name:'sfxsdbmc', index: 'sfxsdbmc',width:'10%'},
						   {label:'评议结果',name:'pyjgmc', index: 'pyjgmc',width:'10%'},
						   {label:'认定理由',name:'ylzd3', index: 'ylzd3',width:'26%',formatter:function(cellValue,rowObject){
							   if(!cellValue){
								   cellValue = "";
							   }
							   var cellValueTemp = cellValue;
							   if(cellValue.length > 15){
								   cellValueTemp = cellValue.substring(0,15)+"...";
							   }
							    return jQuery("<span title='"+cellValue+"'>"+cellValueTemp+"</span>");
							 }
							},
						   {label:'是否学生代表',name:'sfxsdb', index: 'sfxsdb',hidden:true},
						   {label:'班级',name:'bjdm', index: 'bjdm',hidden:true}
						],
						multiselect:false,
						radioselect:false,
						sortname: "xh",
					 	sortorder: "asc"
				};
			gridSetting["params"]=getSuperSearch();
			jQuery("#dataTable").initGrid(gridSetting);
		});
		function searchRs(){
			var map = getSuperSearch();
			jQuery("#dataTable").reloadGrid(map);
		}

		</script>
	</head>

	<body>
		<html:form action="/xszz_knsrdbjpy_jgcxgl">
			<input type="hidden" id="xymc" value="<bean:message key="lable.xy" />"/>
			<%@ include file="/comm/hiddenValue.jsp"%>
			<div class="toolbox">
				<!-- 按钮 -->
<%--				<div class="buttonbox">--%>
<%--					<ul>--%>
<%--						<logic:equal name="writeAble" value="yes">--%>
<%--						<li>--%>
<%--						</li>--%>
<%--						</logic:equal>--%>
<%--					</ul>--%>
<%--				</div>--%>
				<!-- 过滤条件 -->	
				<%@ include file="/comm/search/superSearchAreaStu.jsp"%>
				<!-- 过滤条件 end-->
			</div>
		</html:form>
		
		<div class="main_box">
			<h3 class=datetitle_01>学生列表&nbsp;&nbsp;<font color="#0000ff">(申请人:&nbsp;${xsxxMap.xh }&nbsp;${xsxxMap.xm })</font> </h3>
			<div class="con_overlfow">
				<table id="dataTable" ></table>
				<div id="pager"></div>
			</div>
		</div>
	</body>
</html>
