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
				url:"szdw_xsgb_dwwh.do?method=dwwhList&type=query",
				colList:[
                    {label:'行政班级',name:'bjdm', index: 'bjdm',key:true,width:'10%',hidden:true},
                    {label:'年级',name:'nj', index: 'nj',width:'10%'},
				   {label:'行政班级',name:'bjmc', index: 'bjmc',width:'10%'},
				   {label:'所属书院',name:'symc', index: 'symc',width:'10%'},
                    {label:'辅导员',name:'fdyxm', index: 'fdyxm',width:'10%'},
                    {label:'班干部',name:'bgb', index: 'bgb',width:'15%'}
				],
				sortname: "nj",
			 	sortorder: "asc"
		};
			jQuery(function(){
				jQuery("#dataTable").initGrid(gridSetting);
				jQuery("#btn_ck").click(go_ck);
				jQuery("#btn_xg").click(update);
				jQuery("#btn_zj").click(add);
				jQuery("#btn_sc").click(deletes);
			});

			/**
			导入
			*/
			function importConfig(){
                toImportDataNew("IMPORT_SZDW_ZWWH");
				return false;
			}

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
						<logic:equal name="writeAble" value="yes">
						<li><a href="javascript:void(0);" id="btn_zj" class="btn_zj">增加</a></li>
						<li><a href="javascript:void(0);" id="btn_xg" class="btn_xg">修改</a></li>
						<li><a href="javascript:void(0);" id="btn_sc" class="btn_sc">删除</a></li>	
						</logic:equal>
						<li><a href="javascript:void(0);" id="btn_ck" class="btn_ck">查看</a></li>
						<logic:equal name="writeAble" value="yes">
						<li>
							<a href="javascript:void(0);" onclick="importConfig();return false;" class="btn_dr">导入</a>
						</li>
							<logic:equal value="11067" name="xxdm">
								<li><a href="javascript:void(0);" onclick="printXsgbbab('szdw_zwsq.do?method=printXsgbbab');return false;" class="btn_down">下载备案表</a></li>
							</logic:equal>
						</logic:equal>
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
