<!--����֯�ճ��������-->
<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <%@ include file="/syscommon/head.ini"%>
    <script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
    <script type="text/javascript" src="js/calendar/calendar.js"></script>
    <script type="text/javascript" src="xsgzgl/zhdj/dzzhd/js/dzzhdJgList.js"></script>
    <script type="text/javascript">
    </script>
</head>

<body>
<div class="tab_cur">
    <p class="location">
        <em>���ĵ�ǰλ�ã�</em><a>���Ž���-����֯�ճ�����-�����</a>
    </p>

</div>
<html:form action="/zhdj_dzzhd">
    <input type="hidden" id="status" value="${status}"/>
    <input type="hidden" id="userType" value="${userType}"/>
    <%@ include file="/comm/hiddenValue.jsp"%>

    <div class="toolbox">
        <!-- ��ť -->
        <div class="buttonbox">
            <ul>
                <logic:notEqual value="stu" name="userType">
                <li>
                    <a href="javascript:void(0);" onclick="hdRygl();return false;" class="btn_ck">��Ա����</a>
                </li>
                </logic:notEqual>
                <logic:equal value="stu" name="userType">
                    <li>
                        <a href="javascript:void(0);" onclick="hdXd();return false;" class="btn_tj">��ĵ�</a>
                    </li>
                </logic:equal>
            </ul>
        </div>
        <!-- �������� -->
        <%@ include file="/comm/search/superSearchArea.jsp"%>
        <!-- �������� end-->
    </div>
</html:form>

<div class="main_box">
    <h3 class=datetitle_01>
        <span>�ѷ�����б�&nbsp;&nbsp; </span>
    </h3>
    <div class="con_overlfow">
        <table id="dataTable" ></table>
        <div id="pager"></div>
    </div>
</div>
</body>
</html>

