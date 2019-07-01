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
                url: "xscjcx.do?method=xscjfxList&type=query",
                colList: [
                    {label: 'ѧ��', name: 'xn', index: 'xn'},
                    {label: 'ѧ��', name: 'xqmc', index:'xqmc'},
                    {label:'ѧ�ڴ���', name: 'xq' ,index: 'xq',hidden:true},
                    {label: 'ѧ��', name: 'xh', index: 'xh',formatter : xhLink},
                    {label: '����', name: 'xm', index: 'xm',width:'10%'},
                    // {label: '��Ժ', name: 'symc', index: 'symc'},
                    // {label: 'ѧԺ', name: 'xymc', index: 'xymc'},
                    {label: '�����༶', name: 'bjmc', index: 'bjmc'},
                    // {label: 'רҵ', name: 'zymc', index: 'zymc'},
                    {label: 'רҵ�༶', name: 'zybjmc', index: 'zybjmc'},
                    {label: '������γ���', name: 'bjg', index: 'bjg', formatter : bjgLink},
                    {label: '����γ���', name: 'yx', index: 'yx',formatter : yxLink},
                    {label: '���ÿγ���', name: 'lh', index: 'lh',formatter : lhLink},
                    {label: '�еȿγ���', name: 'zd', index: 'zd',formatter : zdLink},
                    {label: '����γ���', name: 'jg', index: 'jg',formatter : jgLink},
                    {label: '���޲�����', name: 'cxck', index: 'cxck',formatter : cxckLink},
                    {label: '���޲���������', name: 'cxckjg', index: 'cxckjg',formatter : cxckjgLink},

                ],
                sortname: "xn,xq,xm",
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
        var dcclbh = "xsxx_xsxxgl_cjfx.do";
        function exportConfig() {
            customExport(dcclbh, exportData);
        }
        // ��������
        function exportData() {
            setSearchTj();//���ø߼���ѯ����
            var url = "xscjcx.do?method=exportCjfxData&dcclbh=" + dcclbh;//dcclbh,�������ܱ��
            url = addSuperSearchParams(url);//���ø߼���ѯ����
            jQuery("form").eq(0).attr("action", url);
            jQuery("form").eq(0).submit();
        }
        function xhLink(cellValue, rowObject) {
            return  "<a href='javascript:void(0);' class='name' onclick='showDetail(\""
                + rowObject["xh"] +"\,"+rowObject["xn"]+"\,"+rowObject["xq"]+"\");'>" + cellValue
                + "</a>";
        }
        function bjgLink(cellValue, rowObject) {
            return  "<a href='javascript:void(0);' class='name' onclick='showDetail(\""
                + rowObject["xh"] +"\,"+rowObject["xn"]+"\,"+rowObject["xq"]+ "\,1"+"\");'>" + cellValue
                + "</a>";
        }
        function yxLink(cellValue, rowObject) {
            return  "<a href='javascript:void(0);' class='name' onclick='showDetail(\""
                + rowObject["xh"] +"\,"+rowObject["xn"]+"\,"+rowObject["xq"]+ "\,2"+"\");'>" + cellValue
                + "</a>";
        }
        function lhLink(cellValue, rowObject) {
            return  "<a href='javascript:void(0);' class='name' onclick='showDetail(\""
                + rowObject["xh"] +"\,"+rowObject["xn"]+"\,"+rowObject["xq"]+ "\,3"+"\");'>" + cellValue
                + "</a>";
        }
        function zdLink(cellValue, rowObject) {
            return  "<a href='javascript:void(0);' class='name' onclick='showDetail(\""
                + rowObject["xh"] +"\,"+rowObject["xn"]+"\,"+rowObject["xq"]+ "\,4"+"\");'>" + cellValue
                + "</a>";
        }
        function jgLink(cellValue, rowObject) {
            return  "<a href='javascript:void(0);' class='name' onclick='showDetail(\""
                + rowObject["xh"] +"\,"+rowObject["xn"]+"\,"+rowObject["xq"]+ "\,5"+"\");'>" + cellValue
                + "</a>";
        }
        function cxckLink(cellValue, rowObject) {
            return  "<a href='javascript:void(0);' class='name' onclick='showDetail(\""
                + rowObject["xh"] +"\,"+rowObject["xn"]+"\,"+rowObject["xq"]+ "\,6"+"\");'>" + cellValue
                + "</a>";
        }
        function cxckjgLink(cellValue, rowObject) {
            return  "<a href='javascript:void(0);' class='name' onclick='showDetail(\""
                + rowObject["xh"] +"\,"+rowObject["xn"]+"\,"+rowObject["xq"]+ "\,7"+"\");'>" + cellValue
                + "</a>";
        }
        function showDetail(xh) {
            var param=xh.split(",");
            var url="xscjcx.do?method=getXscj&xh=" + param[0]+"&xn="+param[1]+"&xq="+param[2];
            if(param.length>3){
                url=url+"&type="+param[3];
            }
            showDialog("�ɼ�����", 700, 400,
                url);
        }
    </script>
</head>

<body>
<html:form action="/xsxfcjcx">
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
    <div style="overflow-x:auto;height: 550px;">
        <table id="dataTable"></table>
        <div id="pager"></div>
    </div>
</div>
</body>
</html>
