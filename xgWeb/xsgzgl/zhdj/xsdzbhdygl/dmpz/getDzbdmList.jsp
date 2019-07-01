<%@ page language="java" contentType="text/html; charset=GBK" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <%@ include file="/syscommon/head.ini" %>
    <script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
    <script type="text/javascript" src="js/calendar/calendar.js"></script>
    <script type="text/javascript" src="xsgzgl/zhdj/xsdzbhdygl/dmpz/js/dmpz.js"></script>
    <script type="text/javascript">
        var gridSetting = {
            caption: "",
            pager: "pager",
            url: "dzdy_dmpz.do?method=getDmpzList&type=query",
            colList: [
                {label: '���㵳ί����', name: 'dm', index: 'dm', width: '15%'},
                {label: '����', name: 'mc', index: 'mc', width: '15%'},
                {label: '�̹���֧����', name: 'jgdzbs', index: 'jgdzbs', width: '15%'},
                {label: 'ѧ����֧����', name: 'xsdzbs', index: 'jgdzbs', width: '15%'},
                {label: '�̹���Ա��', name: 'jgdrs', index: 'jgdrs', width: '15%'},
                {label: 'ѧ����Ա��', name: 'xsdrs', index: 'xsdrs', width: '15%'}
            ],
            sortname: "dm",
            sortorder: "asc",
            radioselect: false
        }


        jQuery(function () {
            var map = {};
            map["js"] = jQuery("#userType").val();
            gridSetting["params"] = map;
            jQuery("#dataTable").initGrid(gridSetting);
        });
    </script>
</head>

<body>
<html:form action="/dzdy_dmpz">
    <%@ include file="/comm/hiddenValue.jsp" %>
    <input type="hidden" id="userType" value="${userType}"/>
    <div class="toolbox">
        <!-- ��ť -->
        <div class="buttonbox">
            <ul>
                <li>
                    <a href="javascript:void(0);" class="btn_zj" onclick="addPage();return false;">����</a>
                </li>
                <li>
                    <a href="javascript:void(0);" onclick="update();return false;"
                       class="btn_xg">�޸�</a>
                </li>
                <li>
                    <a href="javascript:void(0);" onclick="del();return false;"
                       class="btn_sc">ɾ��</a>
                </li>
                <li><a href="javascript:void(0);" class="btn_dc" onclick="exportConfig();return false;">����</a></li>
                <li>
                    <a href="javascript:void(0);" onclick="ljDzb();return false;" class="btn_ck">����</a>
                </li>
                <li>
                    <a href="javascript:void(0);" onclick="hjDzz();return false;" class="btn_xg">����</a>
                </li>


            </ul>
        </div>
    </div>
</html:form>

<div class="main_box">
    <h3 class=datetitle_01>
        <span>��������&nbsp;&nbsp; </span>
    </h3>
    <div class="con_overlfow">
        <table id="dataTable"></table>
        <div id="pager"></div>
    </div>
</div>
</body>
</html>
