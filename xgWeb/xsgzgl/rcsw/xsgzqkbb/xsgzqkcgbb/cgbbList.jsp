<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <%@ include file="/syscommon/head.ini"%>
    <script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
    <script type="text/javascript" src="js/calendar/calendar.js"></script>
    <script type="text/javascript" src="xsgzgl/comm/exportNew/import.js"></script>
    <script type="text/javascript" src="xsgzgl/rcsw/xsgzqkbb/xsgzqkcgbb/js/cgbb.js"></script>
    <script type="text/javascript">
        jQuery(function(){
            var gridSetting = {
                caption : "${gnmkmc}�б�",
                pager : "pager",
                url : "rcsw_xsgzqkbb_cgbbgl.do?method=getCgbbListData",
                colList : [
                    { label : 'key', name : 'id', index : 'id',key : true, hidden : true },
                    { label : 'ѧ��', name : 'xn', index : 'xn', width : '15%' },
                    { label : 'ѧ��', name : 'xqmc', index : 'xq', width : '15%' },
                    { label : '��������', name : 'bszt', index : 'bszt', width : '25%' },
                    { label : '������', name : 'bsrmc', index : 'bsr', width : '15%' },
                    { label : '����ʱ��', name : 'bssj', index : 'bssj', width : '15%' },
                    { label : '���͵�λ', name : 'bsdwmc', index : 'bsdw',width:'15%'}],
                sortname : "bssj",
                sortorder : "desc" }
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

<html:form action="/rcsw_xsgzqkbb_cgbbgl">
    <%@ include file="/comm/hiddenValue.jsp"%>
    <input type="hidden" id="gnmkmc" value="${gnmkmc}"/>

    <div class="toolbox">
        <!-- ��ť -->
        <div class="buttonbox">
            <ul>
                <logic:equal name="writeAble" value="yes">
                    <li>
                        <a href="javascript:void(0);" onclick="add();return false;" class="btn_zj">����</a>
                    </li>
                    <li>
                        <a href="javascript:void(0);" onclick="edit();return false;" class="btn_xg" >�޸�</a>
                    </li>
                    <li>
                        <a href="javascript:void(0);" onclick="view();return false;" class="btn_ck" >�鿴</a>
                    </li>
                    <li>
                        <a href="javascript:void(0);" onclick="del();return false;" class="btn_sc" >ɾ��</a>
                    </li>
                    <li>
                        <a href="javascript:void(0);" onclick="importConfig();return false;" class="btn_dr" >����</a>
                    </li>
                </logic:equal>

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
        <span>${gnmkmc}�б�&nbsp;&nbsp; </span>
    </h3>
    <div class="con_overlfow">
        <table id="dataTable" ></table>
        <div id="pager"></div>
    </div>
</div>
</body>
</html>
