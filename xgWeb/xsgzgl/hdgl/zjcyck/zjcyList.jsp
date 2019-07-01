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
                caption:"",
                pager:"pager",
                url:"hdgl_hdgl_zjcy.do?method=zjcyList&type=query",
                colList:[
                    {label:'hdid',name:'hdid', index: 'hdid',hidden:true},
                    {label:'jdid',name:'jdid', index: 'jdid',hidden:true},
                    {label:'hdsqid',name:'hdsqid', index: 'hdsqid',hidden:true},
                    {label:'活动名称',name:'hdmc', index: 'hdmc',width:'15%'},
                    {label:'阶段名称',name:'jdmc', index: 'jdmc',width:'15%'},
                    {label:'职工号',name:'zgh', index: 'zgh'},
                    {label:'专家',name:'xm', index: 'xm',width:'15%'}
                ]
            }
            gridSetting["params"]=getSuperSearch();
            jQuery("#dataTable").initGrid(gridSetting);
        });

        function searchRs(){
            var map = getSuperSearch();
            jQuery("#dataTable").reloadGrid(map);
        }
        //导入
        function importConfig(){
            toImportDataNew("IMPORT_HDQDXX");
            return false;
        }


        var DCCLBH = "hdgl_hdgl_hdqd.do";//dcclbh,导出功能编号

        //自定义导出 功能
        function exportConfig() {
            //DCCLBH导出功能编号,执行导出函数
            customExport(DCCLBH, ExportData);
        }

        // 导出方法
        function ExportData() {
            setSearchTj();//设置高级查询条件
            var url = "hdgl_hdgl_hdqd_wh.do?method=export&dcclbh=" + DCCLBH;//dcclbh,导出功能编号
            url = addSuperSearchParams(url);//设置高级查询参数
            jQuery("form").eq(0).attr("action", url);
            jQuery("form").eq(0).submit();
        }
        function add(){
            showDialog("增加活动学生",800,500,"hdgl_hdgl_hdqd_wh.do?method=add");
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
                <%--<li><a href="#" class="btn_zj" onclick="add();return false;">增加</a></li>--%>
                <%--<li><a href="#" class="btn_xg" onclick=";return false;">修改</a></li>--%>
                <%--<li><a href="#" class="btn_sc" onclick=";return false;">删除</a></li>--%>
                <%--<li><a href="#" class="btn_dr" onclick="importConfig();return false;">导入</a></li>--%>
                <%--<li><a href="#" class="btn_dc" onclick="exportConfig();return false;">导出</a></li>--%>
                <%--<li><a href="#" class="btn_dc" onclick="return false;">推送报名数据</a></li>--%>
            </ul>
        </div>
        <!-- 过滤条件 -->
        <%@ include file="/comm/search/superSearchArea.jsp"%>
        <!-- 过滤条件 end-->
    </div>
</html:form>

<div class="main_box">
    <h3 class=datetitle_01>
        <span>活动签到&nbsp;&nbsp; </span>
    </h3>
    <div class="con_overlfow">
        <table id="dataTable" ></table>
        <div id="pager"></div>
    </div>
</div>
</body>
</html>
