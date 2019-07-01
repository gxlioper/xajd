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
                url: "xjyd_zsy.do?method=zsyList&type=query",
                colList: [
                    {label:'id',name:'id',index:'id',key:'id',hidden:true},
                    {label: '学号', name: 'xh', index: 'xh'},
                    {label: '姓名', name: 'xm', index: 'xm'},
                    {label: '原书院', name: 'ysy', index: 'ysy'},
                    {label: '原班级', name: 'ybj', index: 'ybj'},
                    {label: '拟转入书院', name: 'xsy', index: 'xsy'},
                    {label: '拟转入班级', name: 'xbj', index: 'xbj'},
                    {label: '是否已调整', name: 'czmc', index: 'czmc'},
                    {label: '调整时间', name: 'czsj', index: 'czsj'}
                ],
                sortname: "xm",
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
        var dcclbh = "xjyd_zsy.do";
        function exportConfig() {
            customExport(dcclbh, exportData);
        }
        // 导出方法
        function exportData() {
            setSearchTj();//设置高级查询条件
            var url = "xjyd_zsy.do?method=exportData&dcclbh=" + dcclbh;//dcclbh,导出功能编号
            url = addSuperSearchParams(url);//设置高级查询参数
            jQuery("form").eq(0).attr("action", url);
            jQuery("form").eq(0).submit();
        }
        function tz() {
            var param=jQuery("#dataTable").getSeletIds();
            if(param===null||param.length===0){
                showAlert("请选择您要调整的记录！");
            }
            jQuery.post("xjyd_zsy.do?method=tz",{values:param.toString()},function (data) {
                showAlert(data["message"]);
                jQuery("#dataTable").reloadGrid();
            },'json');
        }
    </script>
</head>

<body>
<html:form action="/xjyd_zsy">
    <%@ include file="/comm/hiddenValue.jsp" %>
    <div class="toolbox">
        <!-- 按钮 -->
        <div class="buttonbox">
            <ul>
                <li><a href="#" class="btn_dc" onclick="exportConfig();return false;">导出</a></li>
                <li><a href="#" class="btn_dc" onclick="tz();return false;">调整班级</a></li>
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
