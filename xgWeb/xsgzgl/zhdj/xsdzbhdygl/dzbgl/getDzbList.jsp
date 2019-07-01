<%@ page language="java" contentType="text/html; charset=GBK" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <%@ include file="/syscommon/head.ini" %>
    <script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
    <script type="text/javascript" src="js/calendar/calendar.js"></script>
    <script type="text/javascript" src="xsgzgl/zhdj/xsdzbhdygl/dzbgl/js/dzbgl.js"></script>
    <script type="text/javascript">
        jQuery(function () {
            var gridSetting = {
                caption: "",
                pager: "pager",
                url: "dzdy_dzbgl.do?method=getDzbList&type=query",
                colList: [
                    {label: 'key', name: 'dzbid', index: 'dzbid', hidden: true},
                    {label: 'key1', name: 'dzbhjid', index: 'dzbhjid', hidden: true},
                    {label: 'key2', name: 'jcdwdm', index: 'jcdwdm', hidden: true},
                    {label: 'key3', name: 'dzbsj', index: 'dzbsj', hidden: true},
                    {label: 'key4', name: 'zzwy', index: 'zzwy', hidden: true},
                    {label: 'key5', name: 'xcwy', index: 'xcwy', hidden: true},
                    {label: 'key6', name: 'jjwy', index: 'jjwy', hidden: true},
                    {label: '��֧������', name: 'dzbmc', index: 'dzbmc', width: '18%', formatter: dzbLink},
                    {label: '��֧������', name: 'dzblx', index: 'dzblx', width: '10%'},
                    {label: '���㵳ί', name: 'jcdwmc', index: 'jcdwmc',with:'18%'},
                    {label: '����ʱ��', name: 'clsj', index: 'clsj', width: '10%'},
                    {label: '����ʱ��', name: 'hjsj', index: 'hjsj', width: '10%'},


                ],
                sortname: "clsj",
                sortorder: "desc",
                radioselect: true
            }
            var map = getSuperSearch();
            map["xydm"] = jQuery("#xydm").val();
            map["js"] = jQuery("#js").val();
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
<html:form action="/dzdy_dzbgl">
    <%@ include file="/comm/hiddenValue.jsp" %>
    <html:hidden property="js" styleId="js" value="${userType}"/>

    <div class="toolbox">
        <!-- ��ť -->
        <div class="buttonbox">
            <ul>

                <li>
                    <a href="javascript:void(0);" class="btn_zj" onclick="addDzb();return false;">����</a>
                </li>
                <li>
                    <a href="javascript:void(0);" onclick="updateDzb();return false;" class="btn_xg">�޸�</a>
                </li>
                <li>
                    <a href="javascript:void(0);" onclick="delDzb();return false;" class="btn_sc">ɾ��</a>
                </li>
                <li>
                    <a href="javascript:void(0);" onclick="ljDzb();return false;" class="btn_ck">����</a>
                </li>
                <li>
                    <a href="javascript:void(0);" onclick="hjDzb();return false;" class="btn_xg">����</a>
                </li>
                <li>
                    <a href="javascript:void(0);" onclick="importConfig();return false;" class="btn_dr">����</a>
                </li>
                <li><a href="javascript:void(0);" class="btn_dc" onclick="exportConfig();return false;">����</a></li>


            </ul>
        </div>
        <!-- �������� -->
        <%@ include file="/comm/search/superSearchArea.jsp" %>
        <!-- �������� end-->
    </div>
</html:form>

<div class="main_box">

    <div class="con_overlfow">
        <table id="dataTable"></table>
        <div id="pager"></div>
    </div>
</div>
</body>
</html>
