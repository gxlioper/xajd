<%@ page language="java" contentType="text/html; charset=GBK" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <%@ include file="/syscommon/head.ini" %>
    <script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
    <script type="text/javascript" src="js/calendar/calendar.js"></script>
    <script type="text/javascript" src="xsgzgl/zhdj/xsdzbhdygl/cygl/js/cygl.js"></script>
    <script type="text/javascript" src="js/jquery/plugins/upload/ajaxfileupload.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath() %>/xsgzgl/comm/exportNew/import.js"></script>
    <script type="text/javascript">
        jQuery(function () {
            var gridSetting = {
                caption: "",
                pager: "pager",
                url: "dzdy_cygl.do?method=getCyList&type=query",
                colList: [
                    {label: 'ѧ��', name: 'xh', index: 'xh', width: '10%'},
                    {label: '����', name: 'xm', index: 'xm', width: '10%'},
                    {label: '�Ա�', name: 'xb', index: 'xb', width: '10%'},
                    {label: '���֤��', name: 'sfzh', index: 'sfzh', width: '10%'},
                    {label: '��Ժ', name: 'symc', index: 'symc', width: '18%'},
                    {label: '�༶', name: 'bjmc', index: 'bjmc', width: '10%'},
                    {label: '��ϵ�绰', name: 'lxdh', index: 'lxdh', width: '10%'},
                    {label: '������ò', name: 'zzmmmc', index: 'zzmmmc', width: '10%'},
                    {label: '��֧������', name: 'dzbmc', index: 'dzbmc', width: '10%'},
                    {label: '״̬', name: 'djzt', index: 'djzt', width: '10%'},
                    {label: '�Ƿ�ʧ��', name: 'sl', index: 'sl', width: '10%'},
                    {label: '�Ƿ�����', name: 'ld', index: 'ld', width: '10%'}
                ],
                sortname: "xh",
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
<html:form action="/dzdy_cygl">
    <%@ include file="/comm/hiddenValue.jsp" %>
    <html:hidden property="js" styleId="js" value="${userType}"/>

    <div class="toolbox">
        <!-- ��ť -->
        <div class="buttonbox">
            <ul>

                <li>
                    <a href="javascript:void(0);" class="btn_zj" onclick="addCy();return false;">������Ա</a>
                </li>
                <li>
                    <a href="javascript:void(0);" onclick="updateCy();return false;" class="btn_xg">�޸�</a>
                </li>
                <li>
                    <a href="javascript:void(0);" onclick="delCy();return false;" class="btn_sc">ɾ��</a>
                </li>
                <li><a href="javascript:void(0);" class="btn_xg" onclick="tb();return false;">ͬ���춯��Ϣ</a></li>
                <li><a href="javascript:void(0);" class="btn_dr" onclick="dr();return false;">����</a></li>
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
