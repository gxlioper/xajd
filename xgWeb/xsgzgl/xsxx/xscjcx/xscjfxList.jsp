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
                url: "xscjcx.do?method=xscjfxList&type=query",
                colList: [
                    {label: '学年', name: 'xn', index: 'xn'},
                    {label: '学期', name: 'xqmc', index:'xqmc'},
                    {label:'学期代码', name: 'xq' ,index: 'xq',hidden:true},
                    {label: '学号', name: 'xh', index: 'xh',formatter : xhLink},
                    {label: '姓名', name: 'xm', index: 'xm',width:'10%'},
                    // {label: '书院', name: 'symc', index: 'symc'},
                    // {label: '学院', name: 'xymc', index: 'xymc'},
                    {label: '行政班级', name: 'bjmc', index: 'bjmc'},
                    // {label: '专业', name: 'zymc', index: 'zymc'},
                    {label: '专业班级', name: 'zybjmc', index: 'zybjmc'},
                    {label: '不及格课程数', name: 'bjg', index: 'bjg', formatter : bjgLink},
                    {label: '优秀课程数', name: 'yx', index: 'yx',formatter : yxLink},
                    {label: '良好课程数', name: 'lh', index: 'lh',formatter : lhLink},
                    {label: '中等课程数', name: 'zd', index: 'zd',formatter : zdLink},
                    {label: '及格课程数', name: 'jg', index: 'jg',formatter : jgLink},
                    {label: '重修补考数', name: 'cxck', index: 'cxck',formatter : cxckLink},
                    {label: '重修补考及格数', name: 'cxckjg', index: 'cxckjg',formatter : cxckjgLink},

                ],
                sortname: "xn,xq,xm",
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
        var dcclbh = "xsxx_xsxxgl_cjfx.do";
        function exportConfig() {
            customExport(dcclbh, exportData);
        }
        // 导出方法
        function exportData() {
            setSearchTj();//设置高级查询条件
            var url = "xscjcx.do?method=exportCjfxData&dcclbh=" + dcclbh;//dcclbh,导出功能编号
            url = addSuperSearchParams(url);//设置高级查询参数
            jQuery("form").eq(0).attr("action", url);
            jQuery("form").eq(0).submit();
        }
        function xhLink(cellValue, rowObject) {
            return  "<a href='javascript:void(0);' class='name' onclick='showDetail(\""
                + rowObject["xh"] +"\,"+rowObject["xn"]+"\,"+rowObject["xq"]+"\");'>" + cellValue
                + "</a>";
        }
        function bjgLink(cellValue, rowObject) {
            return  "<a href='javascript:void(0);' class='name' onclick='showDetail(\""
                + rowObject["xh"] +"\,"+rowObject["xn"]+"\,"+rowObject["xq"]+ "\,1"+"\");'>" + cellValue
                + "</a>";
        }
        function yxLink(cellValue, rowObject) {
            return  "<a href='javascript:void(0);' class='name' onclick='showDetail(\""
                + rowObject["xh"] +"\,"+rowObject["xn"]+"\,"+rowObject["xq"]+ "\,2"+"\");'>" + cellValue
                + "</a>";
        }
        function lhLink(cellValue, rowObject) {
            return  "<a href='javascript:void(0);' class='name' onclick='showDetail(\""
                + rowObject["xh"] +"\,"+rowObject["xn"]+"\,"+rowObject["xq"]+ "\,3"+"\");'>" + cellValue
                + "</a>";
        }
        function zdLink(cellValue, rowObject) {
            return  "<a href='javascript:void(0);' class='name' onclick='showDetail(\""
                + rowObject["xh"] +"\,"+rowObject["xn"]+"\,"+rowObject["xq"]+ "\,4"+"\");'>" + cellValue
                + "</a>";
        }
        function jgLink(cellValue, rowObject) {
            return  "<a href='javascript:void(0);' class='name' onclick='showDetail(\""
                + rowObject["xh"] +"\,"+rowObject["xn"]+"\,"+rowObject["xq"]+ "\,5"+"\");'>" + cellValue
                + "</a>";
        }
        function cxckLink(cellValue, rowObject) {
            return  "<a href='javascript:void(0);' class='name' onclick='showDetail(\""
                + rowObject["xh"] +"\,"+rowObject["xn"]+"\,"+rowObject["xq"]+ "\,6"+"\");'>" + cellValue
                + "</a>";
        }
        function cxckjgLink(cellValue, rowObject) {
            return  "<a href='javascript:void(0);' class='name' onclick='showDetail(\""
                + rowObject["xh"] +"\,"+rowObject["xn"]+"\,"+rowObject["xq"]+ "\,7"+"\");'>" + cellValue
                + "</a>";
        }
        function showDetail(xh) {
            var param=xh.split(",");
            var url="xscjcx.do?method=getXscj&xh=" + param[0]+"&xn="+param[1]+"&xq="+param[2];
            if(param.length>3){
                url=url+"&type="+param[3];
            }
            showDialog("成绩详情", 700, 400,
                url);
        }
    </script>
</head>

<body>
<html:form action="/xsxfcjcx">
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
    <div style="overflow-x:auto;height: 550px;">
        <table id="dataTable"></table>
        <div id="pager"></div>
    </div>
</div>
</body>
</html>
