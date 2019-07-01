<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <%@ include file="/syscommon/head.ini"%>
    <script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
    <script type="text/javascript" src="js/calendar/calendar.js"></script>
    <script type="text/javascript" src="xsgzgl/gyjc/jcjg/qskq/js/qskqList.js"></script>
    <script type="text/javascript">

    </script>
</head>

<body>
<div class="tab_cur">
    <p class="location">
        <em>���ĵ�ǰλ�ã�</em><a>${title }</a>
    </p>
</div>
<html:form action="/gyjc_qskq">
    <%@ include file="/comm/hiddenValue.jsp"%>

    <div class="toolbox">
        <!-- ��ť -->
        <div class="buttonbox">
            <ul>

                <%--<li>
                    <a href="javascript:void(0);" class="btn_sz" onclick="ccjgck();return false;"  >�鿴</a>
                </li>--%>
                <logic:equal name="writeAble" value="yes">
                    <li>
                        <a href="javascript:void(0);" onclick="update();return false;" class="btn_sz" >�޸�</a>
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
        <span><logic:equal name="xxdm" value="12061">���Ҳ�ҹ�б�</logic:equal>
            <logic:notEqual name="xxdm" value="12061">���ҿ����б�</logic:notEqual>&nbsp;&nbsp;</span>
    </h3>
    <div class="con_overlfow">
        <table id="dataTable" ></table>
        <div id="pager"></div>
    </div>
</div>
</body>
</html>
