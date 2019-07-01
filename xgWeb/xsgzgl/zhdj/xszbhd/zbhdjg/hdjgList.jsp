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
            url: "xszbhd_hdjg.do?method=getJgList&type=query",
            colList: [
                {label: 'hdsbid', name: 'hdsbid', index: 'hdsbid',hidden:true,key:true},
                {label: '����', name: 'lx', index: 'lx',hidden:true},
                {label: 'ѧ��', name: 'xn', index: 'xn', width: '5%'},
                {label: 'ѧ��', name: 'xqmc', index: 'xqmc', width: '5%'},
                {label: 'ѧԺ', name: 'xymc', index: 'xymc', width: '10%'},
                //{label: '�μ���Ա', name: 'cjry', index: 'cjry', width: '10%'},
                {label: '֧��', name: 'dzbmc', index: 'dzbmc', width: '10%'},
                {label: '����һ��/���ջ', name: 'shykdrhdmc', index: 'shykdrhdmc', width: '10%'},
                {label: '�����', name: 'hdlxmc', index: 'hdlxmc', width: '10%'},
                {label: '����', name: 'hdzt', index: 'hdzt', width: '10%',formatter:viewLink}

            ],
            sortname: "hdzt",
            sortorder: "asc"
        };
        function viewLink(cellValue, rowObject) {
            return "<a href='javascript:void(0);' class='name' onclick='view(\""
                + rowObject["hdsbid"] + "\",\"" + rowObject["lx"] + "\");'>" + cellValue
                + "</a>";
        }
        function view(hdsbid,type){
            var url = "xszbhd_hdjg.do?method=view&hdsbid="+hdsbid+"&type="+type;
            var title = "�鿴";
            showDialog(title, 750, 420, url);
        }
        jQuery(function () {
            jQuery("#dataTable").initGrid(gridSetting);
        });

        //����
        function exportConfig(){
            var DCCLBH='zhdj_xszbhdjg.do';
            customExport(DCCLBH, exportData);
        }
        function exportData(){
            var shzt=jQuery("#shzt").val();
            var DCCLBH='zhdj_xszbhdjg.do';
            setSearchTj();//���ø߼���ѯ����
            var url = "xszbhd_hdjg.do?method=exportData&dcclbh=" + DCCLBH;//dcclbh,�������ܱ��
            url = addSuperSearchParams(url);//���ø߼���ѯ����
            jQuery("form").eq(0).attr("action", url);
            jQuery("form").eq(0).submit();
        }
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
                <li>
                    <a href="#" class="btn_dc" onclick="exportConfig();return false;">����</a>
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
        <span>��ϱ�����б�&nbsp;&nbsp; </span>
    </h3>
    <div class="con_overlfow">
        <table id="dataTable"></table>
        <div id="pager"></div>
    </div>
</div>
</body>
</html>