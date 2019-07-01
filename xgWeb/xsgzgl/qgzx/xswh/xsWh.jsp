<%@ page language="java" contentType="text/html; charset=GBK" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <%@ include file="/syscommon/head.ini" %>
    <script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
    <script type="text/javascript" src="xsgzgl/qgzx/xswh/js/xsWh.js"></script>
    <script type="text/javascript" src="xsgzgl/comm/exportNew/import.js"></script>
    <script type="text/javascript">

        var gridSetting = {
            caption: "ѧ����Ϣ�б�",
            pager: "pager",
            url: "qgzx_xsgl.do?method=xswh&type=query",
            colList: [
                {label: 'ѧ��', name: 'xh', index: 'xh', width: '10%', key: true, formatter: xhLink},
                {label: '����', name: 'xm', index: 'xm', width: '10%'},
                {label: '�Ա�', name: 'xb', index: 'xb', width: '8%'},
                {label: '�꼶', name: 'nj', index: 'nj', width: '8%'},
                {label: '<bean:message key="lable.xb" />', name: 'xymc', index: 'xydm', width: '12%'},
                {label: 'רҵ', name: 'zymc', index: 'zydm', width: '12%'},
                {label: 'רҵ�༶', name: 'zybjmc', index: 'zybjdm', width: '12%'},
                {label: '�����༶', name: 'bjmc', index: 'bjdm', width: '12%'},
                {label: '��Ժ', name: 'symc', index: 'sydm', width: '12%'},
                {label: '�Ƿ�����', name: 'sfgmbx', index: 'sfgmbx', width: '4%'}

            ],
            sortname: "nj,xymc,zymc,bjmc,zybjmc,symc",
            sortorder: "desc"
        }

        jQuery(function () {

            gridSetting["params"] = getSuperSearch();
            jQuery("#dataTable").initGrid(gridSetting);
        });

        //��ѯ
        function searchRs() {
            var map = getSuperSearch();
            jQuery("#dataTable").reloadGrid(map);
        }
    </script>
</head>

<body>
<div class="tab_cur">
    <p class="location">
        <em>���ĵ�ǰλ�ã�</em><a>${title }</a>
    </p>
</div>
<html:form action="/qgzx_xsgl">
    <%@ include file="/comm/hiddenValue.jsp" %>

    <div class="toolbox">
        <!-- ��ť -->
        <logic:equal name="writeAble" value="yes">
            <div class="buttonbox">
                <ul>
                    <li><a href="javascript:void(0);" onclick="add();" class="btn_zj">����</a></li>
                    <li><a href="javascript:void(0);" onclick="del();" class="btn_sc">ɾ��</a></li>
                    <li><a href="javascript:void(0);" onclick="importConfig();" class="btn_dr">����</a></li>
                    <li><a href="javascript:void(0);" onclick="dc();" class="btn_dc">����</a></li>
                    <li><a href="javascript:void(0);" onclick="xg();" class="btn_xg">������Ϣά��</a></li>
                </ul>
            </div>
        </logic:equal>
        <!-- �������� -->
        <%@ include file="/comm/search/superSearchArea.jsp" %>
        <!-- �������� end-->
    </div>
</html:form>

<div class="main_box">
    <h3 class=datetitle_01>
        <span>�ڹ�ѧ���б�</span>
    </h3>
    <div class="con_overlfow">
        <table id="dataTable"></table>
        <div id="pager"></div>
    </div>
</div>
</body>
</html>
