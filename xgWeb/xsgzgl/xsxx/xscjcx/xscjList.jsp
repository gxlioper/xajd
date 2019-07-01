<%@ page language="java" contentType="text/html; charset=GBK" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <%@ include file="/syscommon/head.ini" %>
    <script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
    <script type="text/javascript" src="js/calendar/calendar.js"></script>
    <script type="text/javascript">
        jQuery(function () {
            var gridSetting = {
                caption: "",
                pager: "pager",
                url: "xscjcx.do?method=xscjList&type=query",
                colList: [
                    {label: '学年', name: 'xn', index: 'xn'},
                    {label: '学期', name: 'xqmc', index: 'xqmc'},
                    {label: '学号', name: 'xh', index: 'xh'},
                    {label: '姓名', name: 'xm', index: 'xm'},
                    {label: '书院', name: 'symc', index: 'symc'},
                    {label: '学院', name: 'xymc', index: 'xymc'},
                    {label: '专业', name: 'zymc', index: 'zymc'},
                    {label: '专业班级', name: 'zybjmc', index: 'zybjmc'},
                    {label: '行政班级', name: 'bjmc', index: 'bjmc'},
                    {label: '课程名称', name: 'kcmc', index: 'kcmc'},
                    {label: '课程类别', name: 'kclx', index: 'kclx'},
                    {label: '课程性质', name: 'kcxz', index: 'kcxz'},
                    {label: '修读类型', name: 'xdlx', index: 'xdlx'},
                    {label: '学分', name: 'xf', index: 'xf'},
                    {label: '成绩', name: 'cj', index: 'cj'},
                    {label: '绩点', name: 'jd', index: 'jd'},
                    {label: '考试日期', name: 'ksrq', index: 'ksrq'},
                    {label: '重修重考', name: 'cxck', index: 'cxck'},
                    {label: '是否及格', name: 'sfjg', index: 'sfjg'},
                    {label: '是否有效', name: 'sfyx', index: 'sfyx'}
                ],
                sortname: "xn,xh",
                sortorder: "asc"
            }
            var map = getSuperSearch();
            gridSetting["params"] = map;
            jQuery("#dataTable").initGrid(gridSetting);
        })

        function searchRs() {
            var map = getSuperSearch();
            jQuery("#dataTable").reloadGrid(map);
        }
        var dcclbh = "xsxx_xsxxgl_cjcx.do";
        function exportConfig() {
            customExport(dcclbh, exportData);
        }
        // 导出方法
        function exportData() {
            setSearchTj();//设置高级查询条件
            var url = "xscjcx.do?method=exportData&dcclbh=" + dcclbh;//dcclbh,导出功能编号
            url = addSuperSearchParams(url);//设置高级查询参数
            jQuery("form").eq(0).attr("action", url);
            jQuery("form").eq(0).submit();
        }
    </script>
</head>

<body>
<html:form action="/xscjcx">
    <%@ include file="/comm/hiddenValue.jsp" %>
    <div class="toolbox">
        <!-- 按钮 -->
        <div class="buttonbox">
            <ul>
                <li><a href="#" class="btn_dc" onclick="exportConfig();return false;">导出</a></li>
            </ul>
        </div>
        <!-- 过滤条件 -->
        <%@ include file="/comm/search/superSearchArea.jsp" %>
        <!-- 过滤条件 end-->
    </div>
</html:form>

<div class="main_box">
    <h3 class=datetitle_01>
        <span></span>
    </h3>
    <div style="overflow-x:scroll;height: 550px;">
        <table id="dataTable"></table>
        <div id="pager"></div>
    </div>
</div>
</body>
</html>
