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
        function dcmcLink(cellValue, rowObject) {
            var rowData = JSON.stringify(rowObject);
            return '<button type=\'button\' onclick=\'show('+rowData+');\' class=\'btn_01\' >选择</button>';
        }
        jQuery(function(){
            var gridSetting = {
                caption:"辅导员培训项目",
                pager:"pager",
                multiselect:false,
                url:"szdw_fdypxxmwh.do?method=pxxmList&type=query",
                colList:[
                    {label:'项目代码',name:'xmdm', index: 'xmdm',width:'1%',key:true,hidden:true},
                    {label:'项目名称',name:'xmmc', index: 'xmmc',width:'20%'},
                    {label:'培训地点',name:'pxdd', index: 'pxdd',width:'15%'},
                    {label:'组织部门',name:'zzbm', index: 'zzbm',width:'20%'},
                    {label:'培训时间',name:'pxsj', index: 'pxsj',width:'12%'},
                    {label:'发布时间',name:'fbsj', index: 'fbsj',width:'20%'},
                    {label:'培训简介',name:'pxjj', index: 'pxjj',width:'1%',hidden:true},
                    {label:'培训学时',name:'pxxs', index: 'pxxs',width:'1%',hidden:true},
                    {label:'发布人',name:'fbr', index: 'fbr',width:'10%'},
                    {label:'操作',name:'cz', index: 'cz',width:'15',formatter:dcmcLink}
                ],
                sortname: "fbsj",
                sortorder: "desc"
            }
            gridSetting["params"]=getSuperSearch();
            jQuery("#dataTable").initGrid(gridSetting);
        });

        function searchRs(){
            var map = getSuperSearch();
            jQuery("#dataTable").reloadGrid(map);
        }
        function show(rowData){
            var api = frameElement.api;
            var W = api.get('parentDialog');
            W.showpxxmsNotF5CallBack(rowData);
            api.close();
        }
    </script>
</head>
<body>
	
<div class="tab_cur">
    <p class="location">
        <em>您的当前位置：</em><a>${title }</a>
    </p>
</div>
<input type="hidden" value="${gotoPath}" id="gotoPath"/>
<html:form action="/szdw_fdypxxmwh">
    <%@ include file="/comm/hiddenValue.jsp"%>
    <div class="toolbox">
        <!-- 按钮 -->
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
        <div style="overflow-x:scroll;">
            <table id="dataTable" ></table>
        </div>
        <div id="pager"></div>
    </div>
</div>
</body>
</html>
