<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/5/30
  Time: 14:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=GBK" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <%@ include file="/syscommon/head.ini"%>
    <script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
    <script type="text/javascript" language="javascript" src="js/comm/commFunction.js"></script>
    <script type="text/javascript" src="xsgzgl/zhdj/xszbhd/zbhdfb/js/hdfbList.js"></script>
    <script type="text/javascript">
        var gridSetting = {
            caption: "��б�",
            pager: "pager",
            url: "xszbhd_hdfb.do?method=getHdfbList&type=query",
            colList: [
                {label: 'hdid', name: 'hdid', index: 'hdid',hidden:true,key:true},
                {label: 'ѧ��', name: 'xn', index: 'xn', width: '10%'},
                {label: 'ѧ��', name: 'xqmc', index: 'xqmc', width: '10%'},
                {label: '����', name: 'hdzt', index: 'hdzt', width: '10%'},
                //{label: '�μ���Ա', name: 'cjry', index: 'cjry', width: '10%'},
                {label: '��ʼʱ��', name: 'kssj', index: 'kssj', width: '10%'},
                {label: '��ֹʱ��', name: 'jzsj', index: 'jzsj', width: '10%'},
                {label: '����', width: '5%',formatter:xqLink},
                {label: '����', width: '5%',formatter:jdLink}

            ],
            sortname: "kssj",
            sortorder: "asc"
        }
        jQuery(function () {
            jQuery("#dataTable").initGrid(gridSetting);
        });
    </script>

</head>
<body>
    <html:form action="/xszbhd_hdfb" method="post">
    <%@ include file="/comm/hiddenValue.jsp" %>
        <div class="tab_cur">
            <p class="location">
                <em>���ĵ�ǰλ�ã�</em><a>${title }</a>
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
                        <a href="#" class="btn_zj" onclick="selectDzb();return false;">ѡ������֧��</a>
                    </li>

                    <li>
                        <a href="javascript:void(0);" class="btn_sc" onclick="del();return false;">ɾ��</a>
                    </li>

                 </logic:equal>


                     <%--<logic:equal value="zf01" name="userName">--%>
                        <%--<li>--%>
                            <%--<a href="#" class="btn_dc" onclick="exportConfig();return false;">����</a>--%>
                        <%--</li>--%>
                    <%--</logic:equal>--%>
                </ul>
            </div>
            <!-- �������� -->
            <%@ include file="/comm/search/superSearchArea.jsp" %>
            <!-- �������� end-->
        </div>
    </html:form>
    <div class="main_box">
        <h3 class=datetitle_01>
            <span>��б�&nbsp;&nbsp; </span>
        </h3>
        <div class="con_overlfow">
            <table id="dataTable"></table>
            <div id="pager"></div>
        </div>
    </div>
</body>
</html>