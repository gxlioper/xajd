<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <%@ include file="/syscommon/head.ini"%>
    <script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
    <script type="text/javascript" src="js/calendar/calendar.js"></script>
    <script type="text/javascript" src="js/jquery/plugins/upload/ajaxfileupload.js"></script>

    <script type="text/javascript">
        jQuery(function(){
            var gridSetting = {
                caption : "���¼�б�",
                pager : "pager",
                url : "cxcy_stubz.do?method=getList&type=query",
                colList : [
                    { label : 'id', name : 'id', index : 'id',key : true, hidden : true },
                    { label : 'splc', name : 'splc', index : 'splc',hidden : true },
                    { label : '��Ŀ����', name : 'xmmc', index : 'xmmc', width : '14%' },
                    { label : '������Ԫ��', name : 'bzje', index : 'bzje', width : '9%' },
                    { label : 'ѧ��', name : 'xn', index : 'xn', width : '9%' },
                    { label : 'ѧ��', name : 'xqmc', index : 'xqmc', width : '7%' },
                    { label : '�������', name : 'xymc', index : 'xymc', width : '12%' },
                    { label : '���', name : 'tbrmc', index : 'tbrmc', width : '8%' },
                    { label : '��¼ʱ��', name : 'tbsj', index : 'tbsj', width : '15%' },
                    { label : '������Դ', name : 'sjly', index : 'sjly', hidden : true},
                    { label : '���״̬', name : 'shztmc', index : 'shztmc', width : '8%' },
                    { label : '���״̬', name : 'shzt', index : 'shzt', hidden : true}

                ]
            };
            var map = getSuperSearch();
            gridSetting["params"] = map;
            jQuery("#dataTable").initGrid(gridSetting);
        });
        function searchRs() {
            var map = getSuperSearch();
            jQuery("#dataTable").reloadGrid(map);
        }
        function view(jgid) {
            var rows = jQuery("#dataTable").getSeletRow();
            if (rows.length != 1) {
                showAlertDivLayer("��ѡ��һ����Ҫ�鿴�ļ�¼��");
            }else {
                var sjly = rows[0]["sjly"]
                if("1" == sjly){
                    showDialog("���´�ҵ�����걨�鿴", 700, 450, "cxcy_bzsbwhjg.do?method=bzsbwhjgView&jgid="+rows[0]["id"]);
                }else{
                    showDialog("���´�ҵ�����걨�鿴", 700, 450, "cxcy_bzsbwhsq.do?method=bzsbwhsqView&sqid="+rows[0]["id"]);
                }
            }

        }

    </script>
</head>

<body>
<div class="tab_cur">
    <p class="location">
        <em>���ĵ�ǰλ�ã�</em><a>${title }</a>
    </p>
</div>
<html:form action="/cxcy_stubz">
    <%@ include file="/comm/hiddenValue.jsp"%>
    <div class="toolbox">
        <!-- ��ť -->
        <div class="buttonbox">
            <ul>
                <li>
                    <a href="javascript:void(0);" onclick="view();return false;" class="btn_ck" >�鿴</a>
                </li>
                <%--<li><a href="#" class="btn_dc" onclick="exportConfig();return false;">����</a></li>--%>

            </ul>
        </div>
        <!-- �������� -->
        <%@ include file="/comm/search/superSearchArea.jsp"%>
        <!-- �������� end-->
    </div>
</html:form>
<div class="main_box">
    <h3 class=datetitle_01>
        <span>�ҵĴ��´�ҵ�����б�&nbsp;&nbsp; </span>
    </h3>
    <div class="con_overlfow">
        <table id="dataTable" ></table>
        <div id="pager"></div>
    </div>
</div>
</body>
</html>
