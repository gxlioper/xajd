<%@ page language="java" contentType="text/html; charset=GBK" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <%@ include file="/syscommon/head.ini" %>
    <script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
    <script type="text/javascript" src="js/calendar/calendar.js"></script>
    <script type="text/javascript">
        jQuery(function () {
            var gridSetting = {
                caption: "",
                pager: "pager",
                url: "xjyd_zsy.do?method=zsyList&type=query",
                colList: [
                    {label:'id',name:'id',index:'id',key:'id',hidden:true},
                    {label: 'ѧ��', name: 'xh', index: 'xh'},
                    {label: '����', name: 'xm', index: 'xm'},
                    {label: 'ԭ��Ժ', name: 'ysy', index: 'ysy'},
                    {label: 'ԭ�༶', name: 'ybj', index: 'ybj'},
                    {label: '��ת����Ժ', name: 'xsy', index: 'xsy'},
                    {label: '��ת��༶', name: 'xbj', index: 'xbj'},
                    {label: '�Ƿ��ѵ���', name: 'czmc', index: 'czmc'},
                    {label: '����ʱ��', name: 'czsj', index: 'czsj'}
                ],
                sortname: "xm",
                sortorder: "asc"
            }
            var map = getSuperSearch();
            gridSetting["params"] = map;
            jQuery("#dataTable").initGrid(gridSetting);
        })

        function searchRs() {
            var map = getSuperSearch();
            jQuery("#dataTable").reloadGrid(map);
        }
        var dcclbh = "xjyd_zsy.do";
        function exportConfig() {
            customExport(dcclbh, exportData);
        }
        // ��������
        function exportData() {
            setSearchTj();//���ø߼���ѯ����
            var url = "xjyd_zsy.do?method=exportData&dcclbh=" + dcclbh;//dcclbh,�������ܱ��
            url = addSuperSearchParams(url);//���ø߼���ѯ����
            jQuery("form").eq(0).attr("action", url);
            jQuery("form").eq(0).submit();
        }
        function tz() {
            var param=jQuery("#dataTable").getSeletIds();
            if(param===null||param.length===0){
                showAlert("��ѡ����Ҫ�����ļ�¼��");
            }
            jQuery.post("xjyd_zsy.do?method=tz",{values:param.toString()},function (data) {
                showAlert(data["message"]);
                jQuery("#dataTable").reloadGrid();
            },'json');
        }
    </script>
</head>

<body>
<html:form action="/xjyd_zsy">
    <%@ include file="/comm/hiddenValue.jsp" %>
    <div class="toolbox">
        <!-- ��ť -->
        <div class="buttonbox">
            <ul>
                <li><a href="#" class="btn_dc" onclick="exportConfig();return false;">����</a></li>
                <li><a href="#" class="btn_dc" onclick="tz();return false;">�����༶</a></li>
            </ul>
        </div>
        <!-- �������� -->
        <%@ include file="/comm/search/superSearchArea.jsp" %>
        <!-- �������� end-->
    </div>
</html:form>

<div class="main_box">
    <h3 class=datetitle_01>
        <span></span>
    </h3>
    <div style="overflow-x:scroll;height: 550px;">
        <table id="dataTable"></table>
        <div id="pager"></div>
    </div>
</div>
</body>
</html>
