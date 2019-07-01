<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/exportNew/import.js"></script><!-- 导入功能需要 -->
		<script type="text/javascript" src="xsgzgl/dagl/xzmzdx/dazcjg/js/dazcjg.js"></script>
		<script type="text/javascript">
			var gridSetting = {
					caption:"档案转出结果列表",
					pager:"pager",
					url:"dagl_dazcjg.do?method=dazcjgList",
					colList:[
					   {label:'guid',name:'guid', index: 'guid',key:true,hidden:true},
					   {label:'数据来源',name:'sjly', index: 'sjly',hidden:true},
					   {label:'数据录入时间',name:'sjlrsj', index: 'sjlrsj',hidden:true},
					   {label:'学号',name:'xh', index: 'xh',width:'6%',formatter:xhLink},
					   {label:'姓名',name:'xm', index: 'xm',width:'6%'},
					   {label:'学院',name:'xymc', index: 'xymc',width:'8%'},
					   {label:'班级',name:'bjmc', index: 'bjmc',width:'8%'},
					   {label:'转出方式',name:'zcfsmc', index: 'zcfsmc',width:'4%'},
					   {label:'档案转成信息',name:'dazcxxmc', index: 'dazcxxmc',width:'4%'}
					],
					sortname: "sjlrsj",
				 	sortorder: "desc"
			};
			jQuery(function(){
				var map = getSuperSearch();
				gridSetting["params"] = map;
				jQuery("#dataTable").initGrid(gridSetting);
			})
		</script>
	</head>

	<body>
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>${title}</a>
			</p>
		</div>
		<html:form action="/dagl_dazcjg">
			<%@ include file="/comm/hiddenValue.jsp"%>
			<div class="toolbox">
				<!-- 按钮 -->
				<div class="buttonbox">
					<ul>
						<logic:equal name="writeAble" value="yes">
							<li>
								<a href="javascript:void(0);" class="btn_zj" onclick="dazcjgAdd();return false;">增加</a>
							</li>
							<li>
								<a href="javascript:void(0);" class="btn_xg" onclick="dazcjgUpdate();return false;">修改</a>
							</li>
							<li>
								<a href="javascript:void(0);" class="btn_sc" onclick="dazcjgDelete();return false;">删除</a>
							</li>
							<li>
								<a href="javascript:void(0);" class="btn_ck" onclick="dazcsqView();return false;">查看</a>
							</li>
							<li>
								<a href="javascript:void(0);" class="btn_dr" onclick="dazcjgImport();return false">导入</a>
							</li>
						</logic:equal>
							<li>
								<a href="javascript:void(0);" class="btn_dc" onclick="dazcjgExport();return false">导出</a>
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
				<span>档案转出结果列表&nbsp;&nbsp;</span>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable" ></table>
				<div id="pager"></div>
			</div>
		</div>
	</body>
</html>