<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
			<script type="text/javascript" src="xsgzgl/comm/exportNew/import.js"></script><!-- 导入功能需要 -->
		<script type="text/javascript" src="xsgzgl/dagl/sxdaxxgl/js/sxdaxxgl.js"></script>
		<script type="text/javascript">
		jQuery(function(){
			var gridSetting = {
				caption : "学生列表",
				pager : "pager",
				url : "sxDaxxjg.do?method=getjglist&type=query",
				colList : [ {
					label : 'key',
					name : 'daxxid',
					index : 'daxxid',
					key : true,
					hidden : true
				}, {
					label : '学号',
					name : 'xh',
					index : 'xh',
					width : '10%',
					formatter:xhLink
				}, {
					label : '姓名',
					name : 'xm',
					index : 'xm',
					width : '6%'
				},{
					label : '班级',
					name : 'bjmc',
					index : 'bjmc',
					width : '12%'
				},{
					label : '快递单号',
					name : 'kddh',
					index : 'kddh',
					width : '10%'
				},{
					label : '邮寄地址',
					name : 'yjdz',
					index : 'yjdz',
					width : '15%'
				}, {
					label : '邮寄人',
					name : 'yjr',
					index : 'yjr',
					width : '6%'
				}, {
					label : '时间',
					name : 'sj',
					index : 'sj',
					width : '8%'
				}, {
					label : '备注',
					name : 'bz',
					index : 'bz',
					width : '8%'
				},{
					label : '班级',
					name : 'bjdm',
					index : 'bjdm',
					hidden : true
				}],
				multiselect:false,//禁掉勾选
				sortname: "nj,xymc,bjmc,zymc,xh",
		 		sortorder: "asc"
			}
			var map = getSuperSearch();
			map['bjdms']=jQuery("#bjdms").val();
			map['xh']=jQuery('#xh').val();
			gridSetting["params"] = map;
			jQuery("#dataTable").initGrid(gridSetting);
		})
		function xhLink(cellValue,rowObject){
					return "<a href='javascript:void(0);' class='name' onClick='viewDajg(\""+rowObject["daxxid"]+"\",\""+cellValue+"\")'>"+cellValue+"</a>";
				
				}
		</script>
	</head>

	<body>
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>${title }</a>
			</p>
		</div>
		<html:form action="/sxDaxxjg">
			<%@ include file="/comm/hiddenValue.jsp"%>
			<div class="toolbox">
				<!-- 按钮 -->
				<div class="buttonbox">
					<ul>
						<logic:equal name="writeAble" value="yes">
						<li>
							<a href="#" onclick="importXX();return false;" class="btn_dr">导入</a>
						</li>
						</logic:equal>
						<li>
							<a href="#" class="btn_dc" onclick="exportXX();return false;">导出</a>
						</li>	
					</ul>
				</div>
				<!-- 过滤条件 -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- 过滤条件 end-->
			</div>
		</html:form>
		
		<div class="main_box">
			<h3 class=datetitle_01>
				<span>学生档案列表&nbsp;&nbsp; </span>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable" ></table>
				<div id="pager"></div>
			</div>
		</div>
	</body>
</html>
