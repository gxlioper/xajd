
<%@ page language="java" contentType="text/html; charset=GBK" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <%@ include file="/syscommon/head.ini"%>
    <script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
    <script type="text/javascript" language="javascript" src="js/comm/commFunction.js"></script>
    <script type="text/javascript" src="xsgzgl/sxzzjygl/grxfjs/grxfjsjg/js/grxfjsjgList.js"></script>
    <script type="text/javascript" src="xsgzgl/comm/exportNew/import.js"></script>
    <script type="text/javascript">
        //����
        function importConfig(){
            toImportDataNew("IMPORT_SXZZJYGL_GRXFJSJG");
            return false;
        }
    </script>

</head>
<body>
<html:form action="/sxzzjy_grxfjsjg" method="post">
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
                    <li>
                        <a href="javascript:void(0);" class="btn_zj" onclick="add();return false;">����</a>
                    </li>
                    <li>
                        <a href="javascript:void(0);" class="btn_xg" onclick="update();return false;">�޸�</a>
                    </li>

                    <li>
                        <a href="javascript:void(0);" class="btn_sc" onclick="del();return false;">ɾ��</a>
                    </li>
                    <li>
                        <a href="javascript:void(0);" onclick="importConfig();return false;" class="btn_dr">����</a>
                    </li>

                </logic:equal>
                <li>
                    <a href="javascript:void(0);" class="btn_dc" onclick="exportConfig();return false;" >����</a>
                </li>
                <li>
                    <a href="#" class="btn_down" onclick="printWord('sq');return false;">�걨������</a>
                </li>
                <li>
                    <a href="#" class="btn_down" onclick="printWord('nzhb');return false;">���ڻ㱨����</a>
                </li>
                <li>
                    <a href="#" class="btn_down" onclick="printWord('nzzj');return false;">�����ܽ�����</a>
                </li>
            </ul>
        </div>
        <!-- �������� -->
        <%@ include file="/comm/search/superSearchArea.jsp" %>
        <!-- �������� end-->
    </div>
</html:form>
<div class="main_box">
    <h3 class=datetitle_01>
        <span>����ѧ�罨�����б�&nbsp;&nbsp; </span>
    </h3>
    <div class="con_overlfow">
        <table id="dataTable"></table>
        <div id="pager"></div>
    </div>
</div>
</body>
</html>