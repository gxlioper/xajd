<%@ page language="java" contentType="text/html; charset=GBK" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <%@ include file="/syscommon/head.ini"%>
    <script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
    <script type="text/javascript" language="javascript" src="js/comm/commFunction.js"></script>
    <script type="text/javascript" src="xsgzgl/zhdj/xszbhd/zbhdfb/js/selectDzb.js"></script>
    <script type="text/javascript">

    </script>

</head>
<body>
<html:form action="/xszbhd_hdfb" method="post">
<div style='width:100%; height:500px; overflow-y:auto;overflow-x:hidden'>
    <%@ include file="/comm/hiddenValue.jsp" %>
    <html:hidden property="hdid" styleId="hdid"  value="${hdid}"/>
    <div class="tab_cur">
        <p class="location">
            <em>���ĵ�ǰλ�ã�</em><a>${title }</a>
        </p>
    </div>

    <div class="toolbox">
        <!-- ��ť -->
        <!-- �������� -->
        <%@ include file="/comm/search/superSearchArea.jsp" %>
        <!-- �������� end-->
    </div>
    <div class="comp_title" id="comp_title">
        <ul style="width:90%">
            <li class="ha"><a href="javascript:void(0);" onclick="query(this,'wxz');"><span>δѡ��</span></a></li>
            <li><a href="javascript:void(0);" onclick="query(this,'yxz');"><span>��ѡ��</span></a></li>
        </ul>
    </div>

    <div class="main_box">
        <h3 class=datetitle_01>
            <span>��֧���б�&nbsp;&nbsp; </span>
        </h3>
        <div class="con_overlfow">
            <table id="dataTable"></table>
            <div id="pager"></div>
        </div>
    </div>


</div>

</html:form>
<table width="100%" border="0" class="formlist">
    <tfoot>
    <tr>
        <td colspan="4">
            <div class="bz">
                "<span class="red">*</span>"Ϊ������
            </div>
            <div class="btn">
                <button id="buttonSave" style="margin-right: 50px;" onclick="edit('save');return false;">
                    ѡ ��
                </button>
                <button id="buttonDel" style="margin-right: 50px;display: none;" onclick="edit('del');return false;">
                    ɾ ��
                </button>
                <button onclick="Close();return false;">
                    ȷ ��
                </button>

            </div>
        </td>
    </tr>
    </tfoot>
</table>
</body>
</html>