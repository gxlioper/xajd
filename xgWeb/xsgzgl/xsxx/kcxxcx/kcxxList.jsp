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
                url: "xsxx_xsxxgl_kcxxwh.do?method=kcxxList&type=query",
                colList: [
                    {label: 'ѧ��', name: 'xn', index: 'xn'},
                    {label: 'ѧ��', name: 'xq', index: 'xq'},
                    {label: 'ѧ��', name: 'xh', index: 'xh'},
                    {label: '����', name: 'xm', index: 'xm'},
                    {label: '��Ժ', name: 'symc', index: 'symc'},
                    {label: 'ѧԺ', name: 'xymc', index: 'xymc'},
                    {label: 'רҵ', name: 'zymc', index: 'zymc'},
                    {label: 'רҵ�༶', name: 'zybjmc', index: 'zybjmc'},
                    {label: '�����༶', name: 'bjmc', index: 'bjmc'},
                    {label: '�γ�����', name: 'kcmc', index: 'kcmc'},
                    {label: '���ν�ʦ����', name: 'dkjsxm', index: 'dkjsxm'},
                    {label: '�ܴ�', name: 'zc', index: 'zc'},
                    {label: '�ڴ�', name: 'jc', index: 'jc'},
                    {label: '�ص�', name: 'dd', index: 'dd'}
                ],
                sortname: "xn,xh",
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
        var dcclbh = "xsxx_xsxxgl_kcxxcx.do";
        function exportConfig() {
            customExport(dcclbh, exportData);
        }
        // ��������
        function exportData() {
            setSearchTj();//���ø߼���ѯ����
            var url = "xsxx_xsxxgl_kcxxwh.do?method=exportData&dcclbh=" + dcclbh;//dcclbh,�������ܱ��
            url = addSuperSearchParams(url);//���ø߼���ѯ����
            jQuery("form").eq(0).attr("action", url);
            jQuery("form").eq(0).submit();
        }
    </script>
</head>

<body>
<html:form action="/xscjcx">
    <%@ include file="/comm/hiddenValue.jsp" %>
    <div class="toolbox">
        <!-- ��ť -->
        <div class="buttonbox">
            <ul>
                <li><a href="#" class="btn_dc" onclick="exportConfig();return false;">����</a></li>
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
