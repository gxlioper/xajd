<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type='text/javascript' src="js/xgutil.js"></script>
		<script type='text/javascript' src='dwr/engine.js'></script> 
		<script type='text/javascript' src='dwr/util.js'></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type='text/javascript' src='dwr/interface/exportData.js'></script>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="xsgzgl/szdw/xsgbgl/js/dwwh.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/exportNew/import.js"></script><!-- 导入功能需要 -->
		<script type="text/javascript">
		var gridSetting = {
				caption:"学生干部队伍维护列表",
				pager:"pager",
				url:"szdw_xsgb_dwwh.do?method=dwwhxsList&type=query",
				colList:[
                    {label:'学号',name:'xh', index: 'xh',key:true,width:'10%'},
                    {label:'姓名',name:'xm', index: 'xm',width:'10%'},
				   {label:'性别',name:'xb', index: 'xb',width:'10%'},
                    {label:'年级',name:'nj', index: 'nj',width:'10%'},
                    {label:'行政班级',name:'bjmc', index: 'bjmc',width:'10%'},
                    {label:'职务',name:'zwmc', index: 'zwmc',width:'10%'},
				   {label:'所属书院',name:'symc', index: 'symc',width:'10%'}
				],
				sortname: "nj",
			 	sortorder: "asc"
		};
			jQuery(function(){
				jQuery("#dataTable").initGrid(gridSetting);
			});

        //dcglbh,导出功能编号
        var DCGLBH = "szdw_xsgb_dwwh_10698.do";

        //自定义导出 功能
        function exportConfig() {
            //DCCLBH导出功能编号,执行导出函数
            customExport(DCGLBH, exprotData);
        }

        //导出方法
        function exprotData() {
            setSearchTj();//设置高级查询条件
            var url = "szdw_xsgb_dwwh.do?method=exportData&dcglbh=" + DCGLBH;//dcglbh,导出功能编号
            url = addSuperSearchParams(url);//设置高级查询参数
            jQuery("form").eq(0).attr("action", url);
            jQuery("form").eq(0).submit();
        }
		</script>
	</head>

	<body>
	
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>${title }</a>
			</p>
		</div>
		<html:form action="/szdw_fdypxxmwh.do">
			<%@ include file="/comm/hiddenValue.jsp"%>
			<div class="toolbox">
				<!-- 按钮 -->
				<div class="buttonbox">
					<ul>
						<li><a href="javascript:void(0);" class="btn_dc" onclick="exportConfig();return false;">导出</a></li>
						<%--<li><a href="#" class="btn_dc" onclick="ZwdwwhExportConfig();return false;">导出</a></li>--%>
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
				<span>学生干部队伍维护</span>
			</h3>

			<table id="dataTable" ></table>
			<div id="pager"></div>

		</div>
	</body>
</html>
