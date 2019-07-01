<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <%@ include file="/syscommon/head.ini"%>
    <script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
    <script type="text/javascript" src="js/calendar/calendar.js"></script>
    <script type="text/javascript" src="xsgzgl/comm/exportNew/import.js"></script>
    <script type="text/javascript" src="xsgzgl/rcsw/xsgzqkbb/xsgzqkybb/bjybb/js/bjYbb.js"></script>
    <script type="text/javascript">
        jQuery(function(){
            var gridSetting = {
                caption : "${gnmkmc}�б�",
                pager : "pager",
                url : "rcsw_xsgzqkbb_bjybbgl.do?method=getBjYbbListData&xyybbid=${xsgzqkBjYbbForm.xyybbid}",
                colList : [
                    { label : 'key', name : 'id', index : 'id',key : true, hidden : true },
                    { label : 'xyybbid', name : 'xyybbid', index : 'xyybbid',  hidden : true },
                    { label : '����Ա', name : 'dbfdy', index : 'dbfdy', width : '8%' },
                    { label : '�༶', name : 'bjmc', index : 'bjdm', width : '8%' },
                    { label : '��', name : 'mxss', index : 'mxss', width : '8%' },
                    { label : 'Ů', name : 'wxss', index : 'wxss', width : '8%' },
                    { label : '�ٿ����<br/>����', name : 'zkbhcs', index : 'zkbhcs', width : '8%' },
                    { label : '�༶�<br/>��չ����', name : 'bjhdkzcs', index : 'bjhdkzcs', width : '8%' },
                    { label : '��������<br/>����', name : 'srsscs', index : 'srsscs', width : '8%' },
                    { label : 'ʦ��̸��<br/>����', name : 'ssthcs', index : 'ssthcs', width : '8%' },
                    { label : '��������<br/>����', name : 'gbtkcs', index : 'gbtkcs', width : '8%' },
                    { label : '��ҳ���ϵ<br/>���', name : 'yjzlxqk', index : 'yjzlxqk', width : '8%' },
                    { label : 'ѧ��Υ��<br/>��ͻ���¼��������', name : 'tfsjclqk', index : 'tfsjclqk', width : '8%' },
                    { label : '��ѧ����', name : 'xxrs', index : 'xxrs', width : '8%' },
                    { label : '��ѧ����', name : 'fxrs', index : 'fxrs', width : '8%' },
                    { label : '��ѧ����', name : 'txrs', index : 'txrs', width : '8%' },
                    { label : '��������', name : 'qtrs', index : 'qtrs', width : '8%' }
                ],
                usedefined:true,
                usercols:16,
                sortname : "bjdm",
                sortorder : "asc" }
            var map = getSuperSearch();
            gridSetting["params"] = map;
            jQuery("#dataTable").initGrid(gridSetting);
        })
    </script>
</head>

<body>
<div class="tab_cur">
    <p class="location">
        <em>���ĵ�ǰλ�ã�</em><a>${title }</a>
    </p>
</div>

<html:form action="/rcsw_xsgzqkbb_bjybbgl" method="post" styleId="xsgzqkBjYbbForm">
    <%@ include file="/comm/hiddenValue.jsp"%>
    <input type="hidden" id="gnmkmc" value="${gnmkmc}"/>
    <input type="hidden" id="xyybbid" value="${xsgzqkBjYbbForm.xyybbid}"/>

    <div class="toolbox">
        <!-- ��ť -->
        <div class="buttonbox">
            <ul>
                <li>
                    <a href="javascript:void(0);" onclick="add();return false;" class="btn_zj">����</a>
                </li>
                <li>
                    <a href="javascript:void(0);" onclick="edit();return false;" class="btn_xg" >�޸�</a>
                </li>
                <li>
                    <a href="javascript:void(0);" onclick="del();return false;" class="btn_sc" >ɾ��</a>
                </li>

                <li><a href="#" class="btn_dc" onclick="exportConfig();return false;">����</a></li>
                <li><a href="#" class="btn_fh" onclick="fhxyYbbList();return false;">����</a></li>
            </ul>
        </div>
        <!-- �������� -->
        <%@ include file="/comm/search/superSearchArea.jsp"%>
        <!-- �������� end-->
    </div>
</html:form>

<div class="main_box">
    <h3 class=datetitle_01>
        <span>${gnmkmc}�б�&nbsp;&nbsp; </span>
    </h3>
    <div class="con_overlfow">
        <table id="dataTable" >
            <thead>
            <tr class="nowrap">
                <th width="4%" style="text-align:center" rowspan="2"><input type="checkbox" name="grid_selectAll"></th>
                <th width="12%" style="text-align:center" rowspan="2">����Ա</th>
                <th width="16%" style="text-align:center" rowspan="2">�༶</th>
                <th width="10%" style="text-align:center" colspan="2">ѧ����</th>
                <th width="7%" style="text-align:center" rowspan="2">�ٿ�<br/>���<br/>����</th>
                <th width="7%" style="text-align:center" rowspan="2">�༶��<br/>����չ<br/>����</th>
                <th width="7%" style="text-align:center" rowspan="2">������<br/>�����</th>
                <th width="7%" style="text-align:center" rowspan="2">ʦ��̸<br/>������</th>
                <th width="7%" style="text-align:center" rowspan="2">������<br/>�δ���</th>
                <th width="7%" style="text-align:center" rowspan="2">��ҳ���<br/>ϵ���</th>
                <th width="7%" style="text-align:center" rowspan="2">ѧ��Υ�ͼ�ͻ��<br/>�¼��������</th>
                <th width="16%" style="text-align:center" colspan = "4">ѧ���춯�������������</th>
            </tr>
            <tr>
                <th width="5%" style="text-align:center">��</th>
                <th width="5%" style="text-align:center">Ů</th>
                <th width="5%" style="text-align:center">��ѧ</th>
                <th width="5%" style="text-align:center">��ѧ</th>
                <th width="5%" style="text-align:center">��ѧ</th>
                <th width="5%" style="text-align:center">����</th>
            </tr>
            </thead>
        </table>
        <div id="pager"></div>
    </div>
</div>
</body>
</html>
