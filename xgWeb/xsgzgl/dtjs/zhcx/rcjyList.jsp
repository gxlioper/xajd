<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <%@ include file="/syscommon/head.ini"%>
    <script type='text/javascript' src="js/xgutil.js"></script>
    <script type='text/javascript' src='dwr/engine.js'></script>
    <script type='text/javascript' src='dwr/util.js'></script>
    <script type='text/javascript' src='dwr/interface/exportData.js'></script>
    <script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
    <script type="text/javascript" src="js/calendar/calendar.js"></script>
    <script type="text/javascript" src="xsgzgl/comm/exportNew/import.js"></script>
    <script type="text/javascript">
        jQuery(function(){
            var gridSetting = {
                caption:"",
                pager:"pager",
                url:"dtjs_rcjyZhcx.do?method=rcjyList&type=query",
                colList:[
                    {label:'年级',name:'nj', index: 'nj',width:'5%'},
                    {label:'学号',name:'xh', index: 'xh',width:'10%',formatter : xhLink},
                    {label:'姓名',name:'xm', index: 'xm',width:'10%'},
                    {label:'书院',name:'symc1', index: 'symc1',width:'10%'},
                    {label:'行政班级',name:'bjmc', index: 'bjmc',width:'10%'},
                    {label:'政治面貌',name:'zzmmmc', index: 'zzmmmc',width:'10%'},
                    {label:'理论学习次数',name:'llxxcs', index: 'llxxcs',width:'10%'},
                    {label:'社会实践次数',name:'shsjcs', index: 'shsjcs',width:'10%'},
                    {label:'志愿活动次数',name:'zycs', index: 'zycs',width:'10%'},
                    {label:'志愿活动时长',name:'fwsc', index: 'fwsc',width:'10%'}
                ],
                sortname: "nj",
                sortorder: "asc"
            }
            var map = getSuperSearch();
            gridSetting["params"] = map;
            jQuery("#dataTable").initGrid(gridSetting);
        });
        function searchRs(){
            var map = getSuperSearch();
            jQuery("#dataTable").reloadGrid(map);
        }


        //dcglbh,导出功能编号
        var DCGLBH = "dtjs_rcjy_zhcx.do";

        //自定义导出 功能
        function exportConfig() {
            //DCCLBH导出功能编号,执行导出函数
            customExport(DCGLBH, exprotData);
        }

        //导出方法
        function exprotData() {
            setSearchTj();//设置高级查询条件
            var url = "dtjs_rcjyZhcx.do?method=exportData&dcglbh=" + DCGLBH;//dcglbh,导出功能编号
            url = addSuperSearchParams(url);//设置高级查询参数
            jQuery("form").eq(0).attr("action", url);
            jQuery("form").eq(0).submit();
        }
        function xhLink(cellValue, rowObject) {
            return "<a href='javascript:void(0);' class='name' onclick='view(\""
                + cellValue + "\");'>" + cellValue
                + "</a>";
        }
        //查看
        function view(xh) {
            showDialog("日常教育记录", 800, 550, "dtjs_rcjyZhcx.do?method=rcjyView&xh=" + xh);
        }
    </script>
</head>

<body>

<div class="tab_cur">
    <p class="location">
        <em>您的当前位置：</em><a>${title }</a>
    </p>
</div>
<html:form action="/szdw_fdyjcxgztj">
    <%@ include file="/comm/hiddenValue.jsp"%>
    <div class="toolbox">
            <%--<logic:equal value="yes" name="writeAble">--%>
        <div class="buttonbox">
            <ul>
                <li><a href="javascript:void(0);" onclick="exportConfig();return false;" class="btn_dc">导出</a></li>
            </ul>
        </div>
            <%--</logic:equal>--%>
        <!-- 过滤条件 -->
        <%@ include file="/comm/search/superSearchArea.jsp"%>
        <!-- 过滤条件 end-->
    </div>
</html:form>
<div class="formbox">
    <!--标题start-->
    <h3 class="datetitle_01">
        <span></span>
    </h3>
    <div style="overflow-x:scroll;">
    <table id="dataTable" ></table>
    </div>
    <div id="pager"></div>

</div>
</body>
</html>
