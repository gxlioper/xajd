<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<%@ include file="/syscommon/head.ini"%>
	<script type='text/javascript' src="js/uicomm.js"></script>
	<script type='text/javascript' src="js/String.js"></script>
	<script type='text/javascript' src="js/xgutil.js"></script>
	<script type="text/javascript" src="js/calendar/calendar.js"></script>
	<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
	<script>
        var gridSetting = {
            caption:"学生岗位列表",
            pager:"pager",
            url:"qgzx_cxtj.do?method=xsgwCx&type=query",
            colList:[
                {label:'学年',name:'xn', index: 'xn',width:'10%'},
                {label:'学号',name:'xh', index: 'xh',width:'13%'},
                {label:'姓名',name:'xm', index: 'xm',width:'7%'},
                {label:'行政班级',name:'bjmc', index: 'bjmc',width:'10%'},
                {label:'专业班级',name:'zybjmc', index: 'zybjmc',width:'10%'},
                {label:'岗位名称',name:'gwmc', index: 'gwmc',width:'10%'},
                {label:'用人单位',name:'yrdwmc', index: 'yrdwmc',width:'10%'},
                {label:'录用时间',name:'sgsj', index: 'sgsj',width:'15%'},
                {label:'在岗状态',name:'zgzt', index: 'zgzt',width:'7%'}
            ],
            sortname: "",
            sortorder: "desc"
        };
        //初始化
        jQuery(document).ready(function(){
            var map = getSuperSearch();
            gridSetting["params"] = map;
            jQuery("#dataTable").initGrid(gridSetting);
        });

        function searchRs(){
            var map = getSuperSearch();
            jQuery("#dataTable").reloadGrid(map);
        }

        function yrdwwhExportConfig() {
            //DCCLBH导出功能编号,执行导出函数
            customExport("qgzx_cxtj_xsgw.do", yrdwwhExportData);
        }

        // 导出方法
        function yrdwwhExportData() {
            //setSearchTj();//设置高级查询条件
            var url = "qgzx_cxtj_ajax.do?method=xsgwcxExportData&dcclbh="+"qgzx_cxtj_xsgw.do";//dcclbh,导出功能编号
            //url = addSuperSearchParams(url);//设置高级查询参数
            jQuery("form").eq(0).attr("action", url);
            jQuery("form").eq(0).submit();
        }

        function ck(){
            var ids = jQuery("#dataTable").getSeletIds();
            if (ids.length != 1){
                showAlertDivLayer("请选择一条流程跟踪记录！");
            } else {
                showDialog("查看明细",700,500,"qgzx_cxtj.do?method=gwxxCk&gwdm="+ids[0]);
            }
        }

	</script>
</head>
<body>
<div class="tab_cur" >
	<p class="location">
		<em>您的当前位置:</em><a>${title }</a>
	</p>
	<p class="help">
		<a href="#" onclick="return false;" onmousedown ="showHelpDiv()">使用帮助</a>
	</p>
</div>

<html:form action="/qgzx_gwglnew" method="post">
	<!-- 隐藏域 -->
	<%@ include file="/comm/hiddenValue.jsp"%>
	<!-- 隐藏域 -->
	<div class="toolbox" id="dgncz">
		<!-- 按钮 -->
		<div class="buttonbox">
			<ul>

				<li><a href="#" onclick="yrdwwhExportConfig();return false;" class="btn_dc">导出</a></li>
			</ul>
		</div>
		<!-- 过滤条件 -->
		<%@ include file="/comm/search/superSearchArea.jsp"%>
	</div>

</html:form>
<div class="formbox">
	<!--标题start-->
	<h3 class="datetitle_01">
		<span> 学生岗位信息列表 </span>
	</h3>

	<table id="dataTable" ></table>
	<div id="pager"></div>

</div>
</body>
</html>
