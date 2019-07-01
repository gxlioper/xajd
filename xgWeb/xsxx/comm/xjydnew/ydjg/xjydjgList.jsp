<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type='text/javascript' src="js/xgutil.js"></script>
		<script type='text/javascript' src='dwr/engine.js'></script> 
		<script type='text/javascript' src='dwr/util.js'></script>
		<script type='text/javascript' src='dwr/interface/exportData.js'></script>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script language="javascript" src="xsxx/comm/xjydnew/js/xjydjg.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript">

			//初始化查询
			var gridSetting = {
					caption:"学籍异动结果列表",
					pager:"pager",
					url:"xjydjg.do?method=xjydjgList&type=query",
					colList:[
							   {label:'学号',name:'xh', index: 'xh',formatter:xhLink},
							   {label:'姓名',name:'xm', index: 'xm'},
							   {label:'原年级',name:'ydqnj', index: 'ydqnj'},
							   {label:'原<bean:message key="lable.xb" />',name:'ydqxymc', index: 'ydqxymc',formatter:xyfmt},
                        	   {label:'原专业',name:'ydqzymc', index: 'ydqzymc'},
							   {label:'原行政班级',name:'ydqbjmc', index: 'ydqbjmc'},
							   {label:'原专业班级',name:'ydqzybjmc', index: 'ydqzybjmc'},
                        	   {label:'原书院',name:'ydqsymc', index: 'ydqsymc'},
							   {label:'异动类别',name:'ydlbmc', index: 'ydlbmc'},
							   {label:'学籍异动文号',name:'xjydwh', index: 'xjydwh'},
							   {label:'异动时间',name:'xjydsj', index: 'xjydsj'},
							   {label:'sjly',name:'sjly', index: 'sjly',hidden:true},
							   {label:'xjydjgid',name:'xjydjgid', index: 'xjydjgid',hidden:true,key:true}
					],
					sortname: "jrsj",
				 	sortorder: "desc"
			}

			function getWord(){
				var rows = jQuery("#dataTable").getSeletRow();

				var ids = jQuery("#dataTable").getSeletIds();
				
				 if (rows.length == 0){
					showAlertDivLayer("请选择您要下载的记录！");
					return;
				 }
				var url="xjydjg.do?method=doPrint&value="+ids ;
				window.open(url);
			}
			
		
			jQuery(function(){
				gridSetting.params = getSuperSearch();
				jQuery("#dataTable").initGrid(gridSetting);
			});
		</script>
	</head>
	<body>
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>${title }</a>
				<input type="hidden" name="shlx" id="shlx" value="dsh"/>
			</p>
		</div>
		<html:form action="/szdw_zwsh.do">
			<%@ include file="/comm/hiddenValue.jsp"%>
			<div class="toolbox">
				<!-- 按钮 -->
				<div class="buttonbox">
					<ul>
						<logic:equal value="yes" name="writeAble">
							<li>
								<a href="javascript:void(0);" onclick="addYdjg();return false;" class="btn_zj">增加</a>
							</li>
							<li>
								<a href="javascript:void(0);" onclick="updYdjg();return false;" class="btn_xg">修改</a>
							</li>
							<li>
								<a href="javascript:void(0);" onclick="delYdjg();return false;" class="btn_sc">删除</a>
							</li>
							<logic:notEqual value="12309" name="xxdm">
							<li>
								<a href="javascript:void(0);" onclick="plXjyd();return false;" class="btn_xg">批量学籍异动</a>
							</li>
							</logic:notEqual>
						</logic:equal>
						<!-- 
						<li>
							<a href="javascript:void(0);" onclick="ckYdjg();return false;" class="btn_ck">查看</a>
						</li>
						 -->
						<logic:equal name="writeAble" value="yes">	
						<li><a href="#" class="btn_dc" onclick="exportConfig();return false;">导出</a></li>
						</logic:equal>
						<li><a href="javascript:void(0);" onclick="getWord();return false;" class="btn_down">下载登记表</a></li>
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
				<span>学籍异动结果列表</span>
			</h3>

			<table id="dataTable" ></table>
			<div id="pager"></div>

		</div>
	</body>
</html>
