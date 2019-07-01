<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <%@ include file="/syscommon/head.ini"%>
    <script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
    <script type="text/javascript" src="js/calendar/calendar.js"></script>
    <script type="text/javascript" src="js/jquery/plugins/upload/ajaxfileupload.js"></script>

    <script type="text/javascript">
        jQuery(function(){
            var gridSetting = {
                caption : "活动补录列表",
                pager : "pager",
                url : "cxcy_tjbb.do?method=getBzList&type=query",
                colList : [
                    { label : 'jgid', name : 'jgid', index : 'jgid',key : true, hidden : true },
                    { label : '学号', name : 'xh', index : 'xh', width : '10%',formatter:xhLink },
                    { label : '姓名', name : 'xm', index : 'xm', width : '8%' },
                    { label : '项目名称', name : 'xmmc', index : 'xmmc', width : '14%' },
                    { label : '补助金额（元）', name : 'bzje', index : 'bzje', width : '9%' },
                    { label : '学年', name : 'xn', index : 'xn', width : '9%' },
                    { label : '学期', name : 'xqmc', index : 'xqmc', width : '7%' },
                    { label : '大队名称', name : 'xymc', index : 'xymc', width : '12%' },
                    { label : '填报人', name : 'tbrmc', index : 'tbrmc', width : '8%' },
                    { label : '记录时间', name : 'lrsj', index : 'lrsj', width : '15%' },
                    { label : '数据来源', name : 'sjly', index : 'sjly', hidden : true}]
            };
            var map = getSuperSearch();
            gridSetting["params"] = map;
            jQuery("#dataTable").initGrid(gridSetting);
        });
        function searchRs() {
            var map = getSuperSearch();
            jQuery("#dataTable").reloadGrid(map);
        }
        function xhLink(cellValue, rowObject) {
            return "<a href='javascript:void(0);' class='name' onclick='view(\""
                + rowObject["jgid"]+"\");'>" + cellValue
                + "</a>";
        }

        function view(jgid) {
            showDialog("创新创业补助申报查看", 700, 450, "cxcy_bzsbwhjg.do?method=bzsbwhjgView&jgid="+jgid);
        }
        function exportConfig(){
            var DCCLBH='cxcy_bzsbwh_jg.do';
            customExport(DCCLBH, exportData);
        }
        function exportData(){
            var DCCLBH='cxcy_bzsbwh_jg.do';
            setSearchTj();//设置高级查询条件
            var url = "cxcy_tjbb.do?method=exportData&type=bz&dcclbh=" + DCCLBH+
                "&pkValue="+jQuery("#pkValue").val();//dcclbh,导出功能编号
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
<html:form action="/cxcy_bzsbwhjg">
    <%@ include file="/comm/hiddenValue.jsp"%>
    <div class="toolbox">
        <!-- 按钮 -->
        <div class="buttonbox">
            <ul>
                <li><a href="#" class="btn_dc" onclick="exportConfig();return false;">导出</a></li>
                <li><a href="cxcy_tjbb_tjbb.do" id="btn_fh" class="btn_fh">返回 </a></li>
            </ul>
        </div>
        <!-- 过滤条件 -->
        <%@ include file="/comm/search/superSearchArea.jsp"%>
        <!-- 过滤条件 end-->
    </div>
</html:form>
<div class="main_box">
    <h3 class=datetitle_01>
        <span>创新创业补助结果列表&nbsp;&nbsp; </span>
    </h3>
    <div class="con_overlfow">
        <table id="dataTable" ></table>
        <div id="pager"></div>
    </div>
</div>
</body>
</html>
