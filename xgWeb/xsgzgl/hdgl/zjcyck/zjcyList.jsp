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
                    {label:'�����',name:'hdmc', index: 'hdmc',width:'15%'},
                    {label:'�׶�����',name:'jdmc', index: 'jdmc',width:'15%'},
                    {label:'ְ����',name:'zgh', index: 'zgh'},
                    {label:'ר��',name:'xm', index: 'xm',width:'15%'}
                ]
            }
            gridSetting["params"]=getSuperSearch();
            jQuery("#dataTable").initGrid(gridSetting);
        });

        function searchRs(){
            var map = getSuperSearch();
            jQuery("#dataTable").reloadGrid(map);
        }
        //����
        function importConfig(){
            toImportDataNew("IMPORT_HDQDXX");
            return false;
        }


        var DCCLBH = "hdgl_hdgl_hdqd.do";//dcclbh,�������ܱ��

        //�Զ��嵼�� ����
        function exportConfig() {
            //DCCLBH�������ܱ��,ִ�е�������
            customExport(DCCLBH, ExportData);
        }

        // ��������
        function ExportData() {
            setSearchTj();//���ø߼���ѯ����
            var url = "hdgl_hdgl_hdqd_wh.do?method=export&dcclbh=" + DCCLBH;//dcclbh,�������ܱ��
            url = addSuperSearchParams(url);//���ø߼���ѯ����
            jQuery("form").eq(0).attr("action", url);
            jQuery("form").eq(0).submit();
        }
        function add(){
            showDialog("���ӻѧ��",800,500,"hdgl_hdgl_hdqd_wh.do?method=add");
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
                <%--<li><a href="#" class="btn_zj" onclick="add();return false;">����</a></li>--%>
                <%--<li><a href="#" class="btn_xg" onclick=";return false;">�޸�</a></li>--%>
                <%--<li><a href="#" class="btn_sc" onclick=";return false;">ɾ��</a></li>--%>
                <%--<li><a href="#" class="btn_dr" onclick="importConfig();return false;">����</a></li>--%>
                <%--<li><a href="#" class="btn_dc" onclick="exportConfig();return false;">����</a></li>--%>
                <%--<li><a href="#" class="btn_dc" onclick="return false;">���ͱ�������</a></li>--%>
            </ul>
        </div>
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
        <table id="dataTable" ></table>
        <div id="pager"></div>
    </div>
</div>
</body>
</html>
