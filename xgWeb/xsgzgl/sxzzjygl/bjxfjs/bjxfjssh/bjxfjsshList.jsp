<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <%@ include file="/syscommon/head.ini"%>
    <script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
    <script type="text/javascript" src="js/calendar/calendar.js"></script>
    <script type="text/javascript" src="js/check.js"></script>
    <script type="text/javascript" src="xsgzgl/sxzzjygl/bjxfjs/bjxfjssh/js/bjxfjsshList.js"></script>

    <script type="text/javascript">
    </script>
</head>

<body style="min-height: 800px;">

<input type="hidden" value="dsh" id="shzt" name="shzt"/>
<div class="tab_cur">
    <p class="location">
        <em>���ĵ�ǰλ�ã�</em><a>${title}</a>
    </p>
</div>
<html:form action="/sxzzjy_bjxfjssh">
    <%@ include file="/comm/hiddenValue.jsp"%>
    <div class="toolbox">

        <!-- ��ť -->
        <div class="buttonbox">
            <ul>
                <logic:equal name="writeAble" value="yes">
                    <li id="li_sh">
                        <a href="javascript:void(0);" onclick="bjxfjsSh();return false;"
                           title="ѡ����Ҫ��˵ļ�¼������ð�ť���Դ����ҳ�档"
                           class="btn_sh">���</a>
                    </li>
                    <li id="li_qx" style="display: none;">
                        <a href="javascript:void(0);" onclick="cancelShnew();return false;"
                           title="ѡ��һ����¼������ð�ť�����Գ���ʧ�����˲�����"
                           class="btn_qxsh">����</a>
                    </li>
                </logic:equal>
                <li><a href="javascript:void(0);" onclick="bjxfjsshLcinfo();return false;"
                       title="ѡ��һ����¼������ð�ť���Բ鿴������̡�"
                       class="btn_cs">���̸���</a></li>
                <%--<logic:equal name="writeAble" value="yes">
                    <li><a href="#" class="btn_dc" onclick="exportConfig();return false;">����</a></li>
                </logic:equal>--%>
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
        <span>�༶ѧ�罨������б�&nbsp;&nbsp; </span>
    </h3>
    <div class="con_overlfow">
        <table id="dataTable" ></table>
        <div id="pager"></div>
    </div>
</div>
</body>
</html>
