<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="xsgzgl/zjly/zhf/js/zhfsh.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript">
		function getGridSetting(){
			var gridSetting = {
				caption:"综合素质计分项目明细列表",
				pager:"pager",
				url:"zhf_sh.do?method=getZhfshList&type=query&lb=xg",
				sortname: "shzt",
			 	sortorder: "desc",
			 	radioselect:false
			};
		
		var colList = [
						{ label : '学号', name : 'xh', index : 'xh', width : '20%',formatter:xgLink },
						{ label : '姓名', name : 'xm', index : 'xm', width : '20%' },
						{ label : '学院', name : 'xymc', index : 'xymc', width : '15%',hidden:true },
						{ label : '专业', name : 'zymc', index : 'zymc', width : '15%',hidden:true },
						{ label : '班级', name : 'bjmc', index : 'bjmc', width : '20%' }				
					  ];
			 
		var xmmk = jQuery("font[name=xmmk]");
		jQuery.each(xmmk,function(i,n){
			var xmfsJson = {
					label:jQuery(n).attr("xmmkmc"),
					name:"fs"+i,
					index:"fs"+i,
					width:'10%'
			};
			colList.push(xmfsJson);
		});
		colList.push(
				{label : '总分', name : 'zf', index : 'zf', width : '10%'},
				{label : '审定状态', name : 'shztmc', index : 'shztmc', width : '10%' },
				{label : 'shzt', name : 'shzt', index : 'shzt', hidden:true}
				); 
		gridSetting["colList"] = colList;
		return gridSetting;
	}
				
		jQuery(function(){
			var gridSetting = getGridSetting();
			var map = getSuperSearch();
			gridSetting["params"] = map;
			jQuery("#dataTable").initGrid(gridSetting);
		});
		</script>
	</head>

	<body>
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>${title }</a>
			</p>
		</div>
		<logic:present name="xmmkList">
		<div id="xmDiv">		
			<logic:iterate id="z" name="xmmkList">
				<font style="display:none;" xmmkdm="${z.xmmkdm }" xmmkmc="${z.xmmkmc }" name="xmmk"></font>
			</logic:iterate>
		</div>
		</logic:present>
		<html:form action="/zhf_sh" >
			<%@ include file="/comm/hiddenValue.jsp"%>
			<div class="toolbox">
				<!-- 按钮 -->
				<div class="buttonbox">
					<ul>
					<!--<logic:equal name="writeAble" value="yes">-->
						<li id="li_xg">
							<a href="javascript:void(0);" class="btn_xg" onclick="xg();return false;" >修改</a>
						</li>
						<!--</logic:equal>-->
					</ul>
					
				</div>
			    <!-- 过滤条件 -->
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- 过滤条件 end-->
			</div>
			</html:form>
		<div class="main_box">
			<h3 class=datetitle_01>
				<span>综合素质计分项目明细列表&nbsp;&nbsp; </span>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable" ></table>
				<div id="pager"></div>
			</div>
		</div>
	</body>
</html>
