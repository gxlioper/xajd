<!--ѧ�Ѽ�������-->
<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <%@ include file="/syscommon/head.ini"%>
    <script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
    <script type="text/javascript" src="js/calendar/calendar.js"></script>
    <script type="text/javascript" src="xsgzgl/xszz/xfjm/js/xfjmjglist.js"></script>
    <script type="text/javascript" src="xsgzgl/comm/exportNew/import.js"></script>
    <script type="text/javascript">
    </script>
</head>

<body>
<div class="tab_cur">
    <p class="location">
        <em>���ĵ�ǰλ�ã�</em><a>��������-ѧ�Ѽ�����˽��</a>
    </p>

</div>
<html:form action="/xszz_new_xfjm">
    <input type="hidden" name="status" id="status" value="${status}"/>
    <%@ include file="/comm/hiddenValue.jsp"%>
    <div class="toolbox">
        <!-- ��ť -->
        <div class="buttonbox">
            <ul>
                <li>
                    <a href="javascript:void(0);" class="btn_zj" onclick="xfjmzj();return false;"  title="����ð�ť�����������дҳ�档">����</a>
                </li>
                <li>
                    <a href="javascript:void(0);" onclick="xfjmxg();return false;" class="btn_xg" title="ѡ��һ����¼������ð�ť���޸������">�޸�</a>
                </li>
                <li>
                    <a href="javascript:void(0);" onclick="xfjmsc();return false;" class="btn_sc" title="ֻ��ȡ��δ��˵ļ�¼���Ѿ�����˵Ĳ���ȡ����" >ɾ��</a>
                </li>
                <li>
                    <a href="javascript:void(0);" onclick="xfjmdr();return false;" class="btn_dr" >����</a>
                </li>
                <li>
                    <a href="javascript:void(0);" onclick="xfjmdc();return false;" class="btn_dc" >����</a>
                </li>
            </ul>
        </div>
        <!-- �������� -->
        <%@ include file="/comm/search/superSearchArea.jsp"%>
        <!-- �������� end-->
    </div>
</html:form>

<div class="main_box">
    <h3 class=datetitle_01>
        <span>ѧ��������˽���б�&nbsp;&nbsp; </span>
    </h3>
    <div class="con_overlfow">
        <table id="dataTable" ></table>
        <div id="pager"></div>
    </div>
</div>
</body>
</html>

