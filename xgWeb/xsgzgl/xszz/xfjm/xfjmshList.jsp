<!--ѧ�Ѽ�������-->
<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <%@ include file="/syscommon/head.ini"%>
    <script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
    <script type="text/javascript" src="js/calendar/calendar.js"></script>
    <script type="text/javascript" src="xsgzgl/xszz/xfjm/js/xfjmshlist.js"></script>
    <script type="text/javascript">
    </script>
</head>

<body>
<div class="tab_cur">
    <p class="location">
        <em>���ĵ�ǰλ�ã�</em><a>��������-ѧ�Ѽ������</a>
    </p>

</div>
<html:form action="/xszz_new_xfjm">
    <input type="hidden" name="status" id="status" value="${status}"/>
    <%@ include file="/comm/hiddenValue.jsp"%>
    <div class="toolbox">
        <!-- ��ť -->
        <div class="buttonbox">
            <ul>
                <li id="xfjmsh" >
                    <a href="javascript:void(0);" onclick="xfjmsh();return false;" class="btn_sh">���</a>
                </li>
                <li id="xfjmcx" style="display: none">
                    <a href="javascript:void(0);" onclick="cancelSh();return false;" class="btn_qxsh">����</a>
                </li>
                <li>
                    <a href="javascript:void(0);" onclick="shlcck();return false;"
                       title="ѡ��һ����¼������ð�ť���Բ鿴������̡�"
                       class="btn_cs">���̸���</a>
                </li>
            </ul>
        </div>
        <!-- �������� -->
        <%@ include file="/comm/search/superSearchArea.jsp"%>
        <!-- �������� end-->
        <div class="comp_title" id="comp_title">
            <ul style="width:90%">
                <li class="ha"><a href="javascript:void(0);" onclick="selectTab(this,'dsh');"><span>������</span></a></li>
                <li><a href="javascript:void(0);" onclick="selectTab(this,'ysh');"><span>�Ѵ���</span></a></li>
            </ul>
        </div>
    </div>
</html:form>

<div class="main_box">
    <h3 class=datetitle_01>
        <span>ѧ����������б�&nbsp;&nbsp; </span>
    </h3>
    <div class="con_overlfow">
        <table id="dataTable"></table>
        <div id="pager"></div>
    </div>
</div>
</body>
</html>

