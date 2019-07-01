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
            return '<button type=\'button\' onclick=\'show('+rowData+');\' class=\'btn_01\' >ѡ��</button>';
        }
        jQuery(function(){
            var gridSetting = {
                caption:"����Ա��ѵ��Ŀ",
                pager:"pager",
                multiselect:false,
                url:"szdw_fdypxxmwh.do?method=pxxmList&type=query",
                colList:[
                    {label:'��Ŀ����',name:'xmdm', index: 'xmdm',width:'1%',key:true,hidden:true},
                    {label:'��Ŀ����',name:'xmmc', index: 'xmmc',width:'20%'},
                    {label:'��ѵ�ص�',name:'pxdd', index: 'pxdd',width:'15%'},
                    {label:'��֯����',name:'zzbm', index: 'zzbm',width:'20%'},
                    {label:'��ѵʱ��',name:'pxsj', index: 'pxsj',width:'12%'},
                    {label:'����ʱ��',name:'fbsj', index: 'fbsj',width:'20%'},
                    {label:'��ѵ���',name:'pxjj', index: 'pxjj',width:'1%',hidden:true},
                    {label:'��ѵѧʱ',name:'pxxs', index: 'pxxs',width:'1%',hidden:true},
                    {label:'������',name:'fbr', index: 'fbr',width:'10%'},
                    {label:'����',name:'cz', index: 'cz',width:'15',formatter:dcmcLink}
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
        <em>���ĵ�ǰλ�ã�</em><a>${title }</a>
    </p>
</div>
<input type="hidden" value="${gotoPath}" id="gotoPath"/>
<html:form action="/szdw_fdypxxmwh">
    <%@ include file="/comm/hiddenValue.jsp"%>
    <div class="toolbox">
        <!-- ��ť -->
        <!-- �������� -->
        <%@ include file="/comm/search/superSearchArea.jsp"%>
        <!-- �������� end-->
    </div>
</html:form>

<div class="main_box">
    <h3 class=datetitle_01>
        <span>�ǩ��&nbsp;&nbsp; </span>
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
