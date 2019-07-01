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
                caption : "���¼�б�",
                pager : "pager",
                url : "cxcy_tjbb.do?method=getXmList&type=query",
                colList : [
                    { label : 'id', name : 'id', index : 'id',key : true, hidden : true },
                    { label : '��Ŀ����', name : 'xmmc', index : 'xmmc', width : '10%',formatter:xmmcLink },
                    { label : '������', name : 'bgr', index : 'bgr', width : '10%' },
                    { label : '��Ŀ���', name : 'xmjj', index : 'xmjj', width : '20%' ,formatter:titleLink},
                    { label : 'ѧ��', name : 'xn', index : 'xn', width : '10%' },
                    { label : 'ѧ��', name : 'xqmc', index : 'xqmc', width : '10%' },
                    { label : '���', name : 'tbrmc', index : 'tbrmc', width : '10%' },
                    { label : '��¼ʱ��', name : 'tbsj', index : 'tbsj', width : '15%' }
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
            setSearchTj();//���ø߼���ѯ����
            var url = "cxcy_tjbb.do?method=exportData&type=xm&dcclbh=" + DCCLBH+
                "&pkValue="+jQuery("#pkValue").val();//dcclbh,�������ܱ��
            url = addSuperSearchParams(url);//���ø߼���ѯ����
            jQuery("form").eq(0).attr("action", url);
            jQuery("form").eq(0).submit();
        }

    </script>
</head>

<body>
<div class="tab_cur">
    <p class="location">
        <em>���ĵ�ǰλ�ã�</em><a>${title }</a>
    </p>
</div>
<html:form action="/cxcy_jzsb">
    <%@ include file="/comm/hiddenValue.jsp"%>
    <html:hidden property='xydm' styleId="xydm" />
    <div class="toolbox">
        <!-- ��ť -->
        <div class="buttonbox">
            <ul>
                <li><a href="#" class="btn_dc" onclick="exportConfig();return false;">����</a></li>
                <li><a href="cxcy_tjbb_tjbb.do" id="btn_fh" class="btn_fh">���� </a></li>
            </ul>
        </div>
        <!-- �������� -->
        <%@ include file="/comm/search/superSearchArea.jsp"%>
        <!-- �������� end-->
    </div>
</html:form>
<div class="main_box">
    <h3 class=datetitle_01>
        <span>��Ŀ�ϱ��б�&nbsp;&nbsp; </span>
    </h3>
    <div class="con_overlfow">
        <table id="dataTable" ></table>
        <div id="pager"></div>
    </div>
</div>
</body>
</html>
