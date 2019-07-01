<%@ page language="java" contentType="text/html; charset=GBK" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <%@ include file="/syscommon/head.ini"%>
    <script type="text/javascript" src="js/calendar/calendar.js"></script>
    <script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
    <script type="text/javascript" language="javascript" src="js/comm/commFunction.js"></script>
    <script type="text/javascript" src="xsgzgl/sxzzjygl/mxbzb/xymxbzbgl/js/xymxbzbglList.js"></script>
    <script type="text/javascript">

    </script>

</head>
<body>
<html:form action="/sxzzjy_xymxbzbgl" method="post">
    <%@ include file="/comm/hiddenValue.jsp" %>
    <div class="tab_cur">
        <p class="location">
            <em>���ĵ�ǰλ�ã�</em><a>${title}</a>
        </p>
    </div>

    <div class="toolbox">
        <!-- ��ť -->
        <div class="buttonbox">
            <ul>
                <logic:equal name="writeAble" value="yes">
                    <li><a href="javascript:void(0);" onclick="addNews();return false;" class="btn_zj"> ���� </a></li>
                    <li><a href="javascript:void(0);" onclick="updateNews();return false;" class="btn_xg"> �޸� </a></li>
                    <li><a href="javascript:void(0);" onclick="delNews();return false;" class="btn_sc"> ɾ�� </a></li>
                    <li><a href="javascript:void(0);" class="btn_yl" onclick="newsyl();return false;">Ԥ��</a></li>
                    <li><a href="javascript:void(0);" class="btn_shtg" onclick="fb();return false;">����</a></li>
                    <li><a href="javascript:void(0);" onclick="qxfb();return false;" class="btn_shbtg"> ȡ������ </a></li>
                    <li><a href="javascript:void(0);" onclick="zd();return false;" class="btn_sy"> �ö�</a></li>
                    <li><a href="javascript:void(0);" onclick="qxzd();return false;" class="btn_xy"> ȡ���ö� </a></li>
                </logic:equal>

            </ul>
        </div>
        <!-- �������� -->
        <%@ include file="/comm/search/superSearchArea.jsp" %>
        <!-- �������� end-->
    </div>
</html:form>
<div class="main_box">
    <h3 class=datetitle_01>
        <span>�����б�&nbsp;&nbsp; </span>
    </h3>
    <div class="con_overlfow">
        <table id="dataTable"></table>
        <div id="pager"></div>
    </div>
</div>
</body>
</html>