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
                caption:"ѧ��������¼�б�",
                pager:"pager",
                url:"qgzx_xsgwsh.do?method=ckgzsc&type=query&xh="+jQuery("#xh").val()+"&gwdm="+jQuery("#gwdm").val(),
                colList:[
                    {label:'��������',name:'gzrq', index: 'gzrq',width:'12%'},
                    {label:'����ʱ��',name:'gzsd', index: 'gzsd',width:'15%'},
                    {label:'��ʱ',name:'gs', index: 'gs',width:'10%'},
                    {label:'��λ���',name:'gwlb', index: 'gwlb',width:'10%'},
                    {label:'��λ����',name:'gwmc', index: 'gwmc',width:'10%'},
                    {label:'���˵�λ',name:'yrdwmc', index: 'yrdwmc',width:'10%'},
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
        <!-- �������� -->
        <div style="display: none">
            <%@ include file="/comm/search/superSearchArea.jsp"%>
        </div>
        <!-- �������� end-->
    </div>
</html:form>
<div class="tab_cur">
    <p class="location">
        <em>���ĵ�ǰλ�ã�</em><a>${title }</a>
    </p>
</div>
<div class="main_box">
    <!--����start-->
    <h3 class="datetitle_01">
        <span>ѧ��������¼�б�</span>
    </h3>
    <div class="con_overlfow">
        <table id="dataTable" ></table>
        <div id="pager"></div>
    </div>
</div>
</body>
</html>
