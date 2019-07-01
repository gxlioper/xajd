<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="gygl/sspy/pyjg/js/sspyjg.js"></script>
		<script type="text/javascript">
			var gridSetting = {
					caption:"宿舍评优结果",
					pager:"pager",
					url:"sspyjg.do?method=seachForSspyjg",
					colList:[
					   {label:'guid',name:'guid', index: 'guid',key:true,hidden:true},
					   {label:'数据来源',name:'sjly', index: 'sjly',hidden:true},
					   {label:'数据录入时间',name:'sjlrsj', index: 'sjlrsj',hidden:true},
					   {label:'寝室号',name:'qsh', index: 'qsh',width:'8%',formatter:qshLink},
					   {label:'楼栋',name:'ldmc', index: 'ldmc',width:'8%'},
					   {label:'学年',name:'xn', index: 'xn',width:'8%'},
					   {label:'学期',name:'xqmc', index: 'xqmc',width:'8%'},
					   {label:'评优项目',name:'pyxmmc', index: 'pyxmmc',width:'10%'}
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
		<html:form action="/sspyjg">
			<%@ include file="/comm/hiddenValue.jsp"%>
			<div class="toolbox">
				<!-- 按钮 -->
				<div class="buttonbox">
					<ul>
						<logic:equal name="writeAble" value="yes">
							<li>
								<a href="javascript:void(0);" class="btn_zj" onclick="sspyjgAdd();return false;">增加</a>
							</li>
							<li>
								<a href="javascript:void(0);" class="btn_xg" onclick="sspyjgUpdate();return false;">修改</a>
							</li>
							<li>
								<a href="javascript:void(0);" class="btn_sc" onclick="sspyjgDelete();return false;">删除</a>
							</li>
							<!--  
							<li>
								<a href="javascript:void(0);" class="btn_dr" onclick="sspyjgImport();return false">导入</a>
							</li>
							-->
						</logic:equal>
							<li>
								<a href="javascript:void(0);" class="btn_dc" onclick="sspyjgExport();return false">导出</a>
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
				<span>宿舍评优结果列表&nbsp;&nbsp;</span>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable" ></table>
				<div id="pager"></div>
			</div>
		</div>
	</body>
</html>