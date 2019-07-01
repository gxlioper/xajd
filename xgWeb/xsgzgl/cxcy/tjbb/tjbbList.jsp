<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <%@ include file="/syscommon/head.ini"%>
    <script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
    <script type="text/javascript" src="js/calendar/calendar.js"></script>
    <script type="text/javascript" src="xsgzgl/cxcy/sbwh/jzsb/js/jzsb.js"></script>
    <script type="text/javascript" src="js/jquery/plugins/upload/ajaxfileupload.js"></script>

    <script type="text/javascript">
        jQuery(function(){
            var gridSetting = {
                caption : "统计报表列表",
                pager : "pager",
                multiselect:false,
                url : "cxcy_tjbb.do?method=getList&type=query",
                colList : [
                    { label : 'xydm', name : 'xydm', index : 'xydm', hidden : true},
                    { label : '大队名称', name : 'xymc', index : 'xymc', width : '10%'},
                    { label : '创新创业补助数', name : 'bzs', index : 'bzs', width : '10%' ,formatter:bzLink},
                    { label : '创新创业讲座数', name : 'jzs', index : 'jzs', width : '10%',formatter:jzLink },
                    { label : '创新创业项目数', name : 'xms', index : 'xms', width : '10%',formatter:xmLink},

                ]};
            var map = getSuperSearch();
            gridSetting["params"] = map;
            jQuery("#dataTable").initGrid(gridSetting);
        });
        function searchRs() {
            var map = getSuperSearch();
            jQuery("#dataTable").reloadGrid(map);
        }
        function bzLink(cellValue, rowObject) {
            if(cellValue == 0){
                return cellValue;
            }
            return "<a href='javascript:void(0);' class='name' onclick='view(\"bz\",\""+rowObject["xydm"]+"\");' >" + cellValue
                + "</a>";
        }
        function jzLink(cellValue, rowObject) {
            if(cellValue == 0){
                return cellValue;
            }
            return "<a href='javascript:void(0);' class='name' onclick='view(\"jz\",\""+rowObject["xydm"]+"\");' >" + cellValue
                + "</a>";
        }
        function xmLink(cellValue, rowObject) {
            if(cellValue == 0){
                return cellValue;
            }
            return "<a href='javascript:void(0);' class='name' onclick='view(\"xm\",\""+rowObject["xydm"]+"\");' >" + cellValue
                + "</a>";
        }
        function view(type,xydm){
            searchRs();
            var url = "";
            if("bz" == type) url = "cxcy_tjbb.do?method=getBzList&xydm="+xydm;
            if("jz" == type) url = "cxcy_tjbb.do?method=getJzList&xydm="+xydm;
            if("xm" == type) url = "cxcy_tjbb.do?method=getXmList&xydm="+xydm;
            //url = addSuperSearchParams(url);//设置高级查询参数
            refreshForm(url);
            BatAlert.showTips('正在操作，请稍等...');
        }

    </script>
</head>

<body>
<div class="tab_cur">
    <p class="location">
        <em>您的当前位置：</em><a>${title }</a>
    </p>
</div>
<html:form action="/cxcy_tjbb">
    <%@ include file="/comm/hiddenValue.jsp"%>
    <div class="toolbox">
        <!-- 按钮 -->
        <div class="buttonbox">
            <ul>

                <%--<li><a href="#" class="btn_dc" onclick="exportConfig();return false;">导出</a></li>--%>

            </ul>
        </div>
        <!-- 过滤条件 -->
        <%@ include file="/comm/search/superSearchArea.jsp"%>
        <!-- 过滤条件 end-->
    </div>
</html:form>
<div class="main_box">
    <h3 class=datetitle_01>
        <span>列表&nbsp;&nbsp; </span>
    </h3>
    <div class="con_overlfow">
        <table id="dataTable" ></table>
        <div id="pager"></div>
    </div>
</div>
</body>
</html>
