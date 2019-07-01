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
    <script type="text/javascript" src="xsgzgl/qgzx/xsgw/js/xsgwsh.js"></script>
    <script type="text/javascript" src="js/calendar/calendar.js"></script>
    <script type="text/javascript">
        jQuery(function(){
            var gridSetting = {
                caption:"学生工作记录列表",
                pager:"pager",
                url:"qgzx_xsgwsh.do?method=ckgzsc&type=query&xh="+jQuery("#xh").val()+"&gwdm="+jQuery("#gwdm").val(),
                colList:[
                    {label:'工作日期',name:'gzrq', index: 'gzrq',width:'12%'},
                    {label:'工作时段',name:'gzsd', index: 'gzsd',width:'15%'},
                    {label:'工时',name:'gs', index: 'gs',width:'10%'},
                    {label:'岗位类别',name:'gwlb', index: 'gwlb',width:'10%'},
                    {label:'岗位名称',name:'gwmc', index: 'gwmc',width:'10%'},
                    {label:'用人单位',name:'yrdwmc', index: 'yrdwmc',width:'10%'},
                ],
                sortname: "gzrq",
                sortorder: "desc",
                multiselect: false
            };

            jQuery("#dataTable").initGrid(gridSetting);
        });
        function searchRs(){
            var map = getSuperSearch();
            jQuery("#dataTable").reloadGrid(map);
        }
    </script>
</head>

<body>
<html:form action="/qgzx_jfcjgl_cjff.do?method=gjcxCjff">
    <%@ include file="/comm/hiddenValue.jsp"%>
    <input type="hidden" value="${xh}" id="xh">
    <input type="hidden" value="${gwdm}" id="gwdm">
    <div class="toolbox">
        <!-- 过滤条件 -->
        <div style="display: none">
            <%@ include file="/comm/search/superSearchArea.jsp"%>
        </div>
        <!-- 过滤条件 end-->
    </div>
</html:form>
<div class="tab_cur">
    <p class="location">
        <em>您的当前位置：</em><a>${title }</a>
    </p>
</div>
<div class="main_box">
    <!--标题start-->
    <h3 class="datetitle_01">
        <span>学生工作记录列表</span>
    </h3>
    <div class="con_overlfow">
        <table id="dataTable" ></table>
        <div id="pager"></div>
    </div>
</div>
</body>
</html>
