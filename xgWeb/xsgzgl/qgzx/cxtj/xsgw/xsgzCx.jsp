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
        //初始化
        jQuery(document).ready(function(){
            var gridSetting = {
                caption:"学生工资列表",
                pager:"pager",
                url:"qgzx_cxtj.do?method=xsgzCx&type=query",
                colList:[
                    {label:'学号',name:'xh', index: 'xh',key:true,width:'13%',formatter:function(cell,row){
                        return "<a href='javascript:void(0);' class='name' onClick='seeInfo(\""+row["xh"]+"\")'>"+cell+"</a>";
					}},
                    {label:'姓名',name:'xm', index: 'xm',width:'13%'},
                    {label:'班级',name:'bjmc', index: 'bjmc',width:'7%'},
                    {label:'年份',name:'nf', index: 'bf',width:'7%'},
                    {label:'月份',name:'yf', index: 'yf',width:'7%'},
                    {label:'工时',name:'gs', index: 'gs',width:'7%'},
                    {label:'工资（元）',name:'je', index: 'je',width:'7%'},
                    {label:'银行名称',name:'yhmc', index: 'yhmc',width:'10%'},
                    {label:'银行卡号',name:'yhkh', index: 'yhkh',width:'15%'}
                ],
                sortname: "xh",
                sortorder: "desc"
            };
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
            customExport("qgzx_cxtj_xsgzCx", yrdwwhExportData);
        }

        // 导出方法
        function yrdwwhExportData() {
            setSearchTj();//设置高级查询条件
            var url = "qgzx_cxtj.do?method=xsgzCxExportData&dcclbh=qgzx_cxtj_xsgzCx";//dcclbh,导出功能编号
            url = addSuperSearchParams(url);//设置高级查询参数
            jQuery("form").eq(0).attr("action", url);
            jQuery("form").eq(0).submit();
        }
        function seeInfo(xh){
			showDialog("工资发放查看",765,500,"qgzx_cxtj.do?method=xsgzffCk&xh="+xh);
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
	<input type="hidden" value="${xh}" id="xh">
	<!-- 隐藏域 -->
	<div class="toolbox" id="dgncz">
		<!-- 按钮 -->
		<div class="buttonbox">
			<ul>
				<li><a href="#" onclick="yrdwwhExportConfig();return false;" class="btn_dc">导出月学生工资</a></li>
			</ul>
		</div>
		<!-- 过滤条件 -->
		<%@ include file="/comm/search/superSearchArea.jsp"%>
	</div>

</html:form>
<div class="formbox">
	<!--标题start-->
	<h3 class="datetitle_01">
		<span> 学生工资列表 </span>
	</h3>

	<table id="dataTable" ></table>
	<div id="pager"></div>

</div>
</body>
</html>
