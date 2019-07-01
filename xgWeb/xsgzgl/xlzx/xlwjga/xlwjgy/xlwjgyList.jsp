<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <%@ include file="/syscommon/head.ini"%>
    <script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
    <script type="text/javascript" src="js/calendar/calendar.js"></script>
    <script type="text/javascript" src="xsgzgl/xlzx/xlwjga/xlwjgy/js/xlwjgy.js"></script>
    <script type="text/javascript" src="xsgzgl/comm/exportNew/import.js"></script><!-- ���빦����Ҫ -->
    <script type="text/javascript">

        jQuery(function(){
            initGridSetting();
        });
        function initGridSetting(){
            var gridSetting = {
                caption : "����Σ����Ԥ�б�",
                pager : "pager",
                url : "xlzx_xlwjgy.do?method=getList&type=query",
                colList:[
                    {label : 'id',name : 'id',index : 'id',hidden : true,key : true},
                    {label : 'ѧ��',name : 'xh',index : 'xh',formatter : xhLink},
                    {label : '����',name : 'xm',index : 'xm'},
                    {label : '�Ա�',name : 'xb',index : 'xb'},
                    {label : '�꼶',name : 'nj',index : 'nj'},
                    {label : 'ѧԺ',name : 'xymc',index : 'xymc'},
                    {label : '��Ժ',name : 'symc',index : 'symc'},
                    {label : '�༶',name : 'bjmc',index : 'bjmc'},
                    {label : '����ʱ��',name : 'bgsj',index : 'bgsj'},
                    {label : '������',name : 'bgr',index : 'bgr'},
                    {label : '��Ԥʱ��',name : 'wjgysj',index : 'bgsj'},
                    {label : '��Ԥ��Ա',name : 'wjgyry',index : 'wjgyry'},
                    {label : 'lrsj',name : 'lrsj',index : 'lrsj',hidden : true}
                ],
                sortname : "",
                sortorder : ""
            };

            gridSetting["params"]=getSuperSearch();
            jQuery("#dataTable").initGrid(gridSetting);

        }

        function searchRs() {
            var map = getSuperSearch();

            jQuery("#dataTable").reloadGrid(map);
        }
        function xhLink(cellValue, rowObject) {
            return "<a href=\"javascript:void(0);\" class=\"name\" onclick=\"view('"+ rowObject["id"]+"');\">" + cellValue + "</a>";
        }
    </script>
</head>

<body>

<div class="tab_cur">
    <p class="location">
        <em>���ĵ�ǰλ�ã�</em><a>${title }</a>
    </p>
</div>
<html:form action="/xlzx_xlwjgy">
    <%@ include file="/comm/hiddenValue.jsp"%>
    <!-- ������ -->
    <div class="toolbox">
        <!-- ��ť -->
        <div class="buttonbox">
            <ul>
                <logic:equal name="writeAble" value="yes" >
                    <li>
                        <a href="#" onclick="add();return false;" class="btn_zj">����</a>
                    </li>
                    <li>
                        <a href="#" onclick="update();return false;" class="btn_xg">�޸�</a>
                    </li>
                    <li>
                        <a href="#" onclick="del();return false;" class="btn_sc">ɾ��</a>
                    </li>
                </logic:equal>
                    <li>
                        <a href="#" onclick="exportConfig();return false;" class="btn_dc">����</a>
                    </li>
            </ul>
        </div>
        <!-- �������� -->
        <%@ include file="/comm/search/superSearchArea.jsp"%>
        <!-- �������� end-->
    </div>
</html:form>
<div class="formbox">
    <!--����start-->
    <h3 class="datetitle_01">
        <span> �б� </span>
    </h3>

    <table id="dataTable" ></table>
    <div id="pager"></div>

</div>
</body>
</html>
