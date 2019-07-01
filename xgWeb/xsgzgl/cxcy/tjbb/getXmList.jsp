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
                url : "cxcy_tjbb.do?method=getXmList&type=query",
                colList : [
                    { label : 'id', name : 'id', index : 'id',key : true, hidden : true },
                    { label : '项目名称', name : 'xmmc', index : 'xmmc', width : '10%',formatter:xmmcLink },
                    { label : '报告人', name : 'bgr', index : 'bgr', width : '10%' },
                    { label : '项目简介', name : 'xmjj', index : 'xmjj', width : '20%' ,formatter:titleLink},
                    { label : '学年', name : 'xn', index : 'xn', width : '10%' },
                    { label : '学期', name : 'xqmc', index : 'xqmc', width : '10%' },
                    { label : '填报人', name : 'tbrmc', index : 'tbrmc', width : '10%' },
                    { label : '记录时间', name : 'tbsj', index : 'tbsj', width : '15%' }
                ]};
            var map = getSuperSearch();
            gridSetting["params"] = map;
            jQuery("#dataTable").initGrid(gridSetting);
        });
        function searchRs() {
            var map = getSuperSearch();
            jQuery("#dataTable").reloadGrid(map);
        }


        function xmmcLink(cellValue, rowObject) {
            return "<a href='javascript:void(0);' class='name' onclick='view(\""
                + rowObject["id"]+"\");' title='"+cellValue+"'>" + cellValue
                + "</a>";
        }
        function titleLink(cellValue, rowObject) {
            var show = "";
            if(cellValue != "" && cellValue != null){
                show = cellValue;
                if(show.length > 15){
                    show = cellValue.substring(0,15)+"..."
                }
            }
            return "<span title='"+cellValue+"'>" + show
                + "</span>";
        }
        function exportConfig(){
            var DCCLBH='cxcy_sbwh_xmsb.do';
            customExport(DCCLBH, exportData);
        }
        function exportData(){
            var DCCLBH='cxcy_sbwh_xmsb.do';
            setSearchTj();//设置高级查询条件
            var url = "cxcy_tjbb.do?method=exportData&type=xm&dcclbh=" + DCCLBH+
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
<html:form action="/cxcy_jzsb">
    <%@ include file="/comm/hiddenValue.jsp"%>
    <html:hidden property='xydm' styleId="xydm" />
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
        <span>项目上报列表&nbsp;&nbsp; </span>
    </h3>
    <div class="con_overlfow">
        <table id="dataTable" ></table>
        <div id="pager"></div>
    </div>
</div>
</body>
</html>
