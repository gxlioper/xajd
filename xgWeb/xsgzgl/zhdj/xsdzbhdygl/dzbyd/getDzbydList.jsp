<%@ page language="java" contentType="text/html; charset=GBK" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <%@ include file="/syscommon/head.ini" %>
    <script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
    <script type="text/javascript" src="js/calendar/calendar.js"></script>
    <script type="text/javascript" src="xsgzgl/zhdj/xsdzbhdygl/dzbyd/js/dzbyd.js"></script>
    <script type="text/javascript">
        jQuery(function () {
            var gridSetting = {
                caption: "",
                pager: "pager",
                url: "dzdy_dzbyd.do?method=getDzbydList&type=query",
                colList: [
                    {label: 'ѧ��', name: 'xh', index: 'xh', width:'18%',formatter: dzbydLink},
                    {label: '����', name: 'xm', index: 'xm', width: '18%'},
                    {label: 'ԭ��֧��', name: 'dzbmc', index: 'dzbmc',width: '10%'},
                    {label: '�춯��֧��', name: 'yddzbmc', index: 'yddzbmc', width: '10%'},
                ],
                sortname: "xh",
                sortorder: "desc",
                radioselect: true
            }
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
<html:form action="/dzdy_dzbyd">
    <%@ include file="/comm/hiddenValue.jsp" %>
    <div class="toolbox">
        <!-- ��ť -->
        <div class="buttonbox">
            <ul>
                <li>
                    <a href="javascript:void(0);" onclick="updateDzbyd();return false;" class="btn_xg">�޸�</a>
                </li>
                <li>
                    <a href="javascript:void(0);" onclick="delDzbyd();return false;" class="btn_sc">ɾ��</a>
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
