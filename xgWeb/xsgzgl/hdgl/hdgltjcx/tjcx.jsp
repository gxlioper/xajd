<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <%@ include file="/syscommon/head.ini"%>
    <script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
    <script type="text/javascript" src="js/calendar/calendar.js"></script>
    <script type="text/javascript" src="xsgzgl/comm/exportNew/import.js"></script>
    <script type="text/javascript">
        jQuery(function(){
            var gridSetting = {
                caption:"四个100统计列表",
                pager:"pager",
                url:"hdgl_hdgl_tj.do?method=tjList&type=query",
                colList:[
                    {label:'key',name:'', index: '',key:true ,hidden:true},
                    {label:'年级',name:'nj', index: 'nj',width:'8%'},
                    {label:'学号',name:'xh', index: 'xh',width:'15%'},
                    {label:'姓名',name:'xm', index: 'xm',width:'10%'},
                    {label:'书院',name:'symc', index: 'symc',width:'15%'},
                    {label:'学院',name:'xymc', index: 'xymc',width:'15%'},
                    {label:'专业',name:'zymc', index: 'zymc',width:'15%'},
                    {label:'行政班级',name:'bjmc', index: 'bjmc',width:'15%'},
                    {label:'专业班级',name:'zybjmc', index: 'zybjmc',width:'15%'},
                    {label:'100场活动',name:'hdgs', index: 'hdgs',width:'8%'},
                    {label:'100场讲座',name:'jzgs', index: 'jzgs',width:'8%'},
                    {label:'100名老师',name:'pjjss', index: 'pjjss',width:'8%'},
                    {label:'100本书',name:'ydbs', index: 'ydbs',width:'8%'},
                    {label:'是否已达标',name:'sfdb', index: 'sfdb',width:'8%'}
                ]
            }
            gridSetting["params"]=getSuperSearch();
            jQuery("#dataTable").initGrid(gridSetting);
        });

        function searchRs(){
            var map = getSuperSearch();
            jQuery("#dataTable").reloadGrid(map);
        }

        var DCCLBH = "hdgl_hdgl_sgybtj.do";//dcclbh,导出功能编号

        //自定义导出 功能
        function exportConfig() {
            //DCCLBH导出功能编号,执行导出函数
            customExport(DCCLBH, ExportData);
        }

        // 导出方法
        function ExportData() {
            setSearchTj();//设置高级查询条件
            var url = "hdgl_hdgl_tj.do?method=exportData&dcclbh=" + DCCLBH;//dcclbh,导出功能编号
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
<html:form action="/hdgl_hdgl_tj">
    <%@ include file="/comm/hiddenValue.jsp"%>
    <div class="toolbox">
        <!-- 按钮 -->
        <div class="buttonbox">
            <ul>
                <li><a href="#" class="btn_dc" onclick="exportConfig();return false;">导出</a></li>
            </ul>
        </div>
        <!-- 过滤条件 -->
        <%@ include file="/comm/search/superSearchArea.jsp"%>
        <!-- 过滤条件 end-->
    </div>
</html:form>

<div class="main_box">
    <h3 class=datetitle_01>
        <span>四个100统计列表&nbsp;&nbsp; </span>
    </h3>
    <div class="con_overlfow">
        <table id="dataTable" ></table>
        <div id="pager"></div>
    </div>
</div>
</body>
</html>
