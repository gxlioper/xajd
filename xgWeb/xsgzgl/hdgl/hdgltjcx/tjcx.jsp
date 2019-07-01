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
                caption:"�ĸ�100ͳ���б�",
                pager:"pager",
                url:"hdgl_hdgl_tj.do?method=tjList&type=query",
                colList:[
                    {label:'key',name:'', index: '',key:true ,hidden:true},
                    {label:'�꼶',name:'nj', index: 'nj',width:'8%'},
                    {label:'ѧ��',name:'xh', index: 'xh',width:'15%'},
                    {label:'����',name:'xm', index: 'xm',width:'10%'},
                    {label:'��Ժ',name:'symc', index: 'symc',width:'15%'},
                    {label:'ѧԺ',name:'xymc', index: 'xymc',width:'15%'},
                    {label:'רҵ',name:'zymc', index: 'zymc',width:'15%'},
                    {label:'�����༶',name:'bjmc', index: 'bjmc',width:'15%'},
                    {label:'רҵ�༶',name:'zybjmc', index: 'zybjmc',width:'15%'},
                    {label:'100���',name:'hdgs', index: 'hdgs',width:'8%'},
                    {label:'100������',name:'jzgs', index: 'jzgs',width:'8%'},
                    {label:'100����ʦ',name:'pjjss', index: 'pjjss',width:'8%'},
                    {label:'100����',name:'ydbs', index: 'ydbs',width:'8%'},
                    {label:'�Ƿ��Ѵ��',name:'sfdb', index: 'sfdb',width:'8%'}
                ]
            }
            gridSetting["params"]=getSuperSearch();
            jQuery("#dataTable").initGrid(gridSetting);
        });

        function searchRs(){
            var map = getSuperSearch();
            jQuery("#dataTable").reloadGrid(map);
        }

        var DCCLBH = "hdgl_hdgl_sgybtj.do";//dcclbh,�������ܱ��

        //�Զ��嵼�� ����
        function exportConfig() {
            //DCCLBH�������ܱ��,ִ�е�������
            customExport(DCCLBH, ExportData);
        }

        // ��������
        function ExportData() {
            setSearchTj();//���ø߼���ѯ����
            var url = "hdgl_hdgl_tj.do?method=exportData&dcclbh=" + DCCLBH;//dcclbh,�������ܱ��
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
<html:form action="/hdgl_hdgl_tj">
    <%@ include file="/comm/hiddenValue.jsp"%>
    <div class="toolbox">
        <!-- ��ť -->
        <div class="buttonbox">
            <ul>
                <li><a href="#" class="btn_dc" onclick="exportConfig();return false;">����</a></li>
            </ul>
        </div>
        <!-- �������� -->
        <%@ include file="/comm/search/superSearchArea.jsp"%>
        <!-- �������� end-->
    </div>
</html:form>

<div class="main_box">
    <h3 class=datetitle_01>
        <span>�ĸ�100ͳ���б�&nbsp;&nbsp; </span>
    </h3>
    <div class="con_overlfow">
        <table id="dataTable" ></table>
        <div id="pager"></div>
    </div>
</div>
</body>
</html>
