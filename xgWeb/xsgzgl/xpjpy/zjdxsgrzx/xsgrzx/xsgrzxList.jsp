<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/exportNew/import.js"></script><!-- 导入功能需要 -->
		<script type="text/javascript" src="xsgzgl/xsxx/yrgl/js/yrgl.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>  <!-- 高级查询时间需要 -->
		<script type="text/javascript">
			var gridSetting = {
				caption:"结果列表",
				pager:"pager",
				url:"xpjpy_xsgrzx.do?method=getXsgrzx&type=query",
				colList:[
				   {label:'guid',name:'guid', index: 'guid',hidden:true,key:true},
				   {label:'学号',name:'xh', index: 'xh',width:'12%',formatter:xhLink},
				   {label:'姓名',name:'xm', index: 'xm',width:'12%'},
				   {label:'年级',name:'nj', index: 'nj',width:'10%'},
				   {label:'学院',name:'xymc', index: 'xymc',width:'15%'},
				   {label:'专业',name:'zymc', index: 'zymc',width:'15%'},
				   {label:'班级',name:'bjmc', index: 'bjmc',width:'15%'},
				   {label:'申请学年',name:'xn', index: 'xn',width:'10%'}
				],
				sortname: "xh",
			 	sortorder: "asc"
			}
			function xhLink(cellValue,rowObject){
				return "<a href='javascript:void(0);' class='name' onClick='Yrglview(\""+rowObject["guid"]+"\",\""+cellValue+"\")'>"+cellValue+"</a>";
			}
			jQuery(function(){
				jQuery("#dataTable").initGrid(gridSetting);
			});
			function searchRs(){
				var map = getSuperSearch();
				jQuery("#dataTable").reloadGrid(map);
			}					
		</script>
	</head>
	<body>	
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>${title }</a>
			</p>
		</div>
		<html:form action="/demo">
			<%@ include file="/comm/hiddenValue.jsp"%>
			<div class="toolbox">
				<!-- 按钮 -->
				<div class="buttonbox">
					<ul>
						<logic:equal name="writeAble" value="yes">	
							<li><a href="javascript:void(0);" onclick="add()" class="btn_zj">增加</a></li>
							<li><a href="javascript:void(0);" onclick="update();" class="btn_xg">修改</a></li>
							<li><a href="javascript:void(0);" onclick="del()" class="btn_sc">删除</a></li>						
							<li><a href="#" class="btn_dr" onclick="importConfig();return false;">导入</a></li>
						</logic:equal>
						
						<li><a href="#" class="btn_dc" onclick="exportConfig();return false;">导出</a></li>
						
					</ul>
				</div>
				<!-- 过滤条件 -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- 过滤条件 end-->
			</div>
		</html:form>
		<div class="formbox">
			<!--标题start-->
			<h3 class="datetitle_01">
				<span> 蕴瑞管理列表 </span>
			</h3>
			<table id="dataTable" ></table>
			<div id="pager"></div>
		</div>
	</body>
</html>
