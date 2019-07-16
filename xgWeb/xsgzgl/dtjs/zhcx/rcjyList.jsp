<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <%@ include file="/syscommon/head.ini"%>
    <script type='text/javascript' src="js/xgutil.js"></script>
    <script type='text/javascript' src='dwr/engine.js'></script>
    <script type='text/javascript' src='dwr/util.js'></script>
    <script type='text/javascript' src='dwr/interface/exportData.js'></script>
    <script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
    <script type="text/javascript" src="js/calendar/calendar.js"></script>
    <script type="text/javascript" src="xsgzgl/comm/exportNew/import.js"></script>
    <script type="text/javascript">
        jQuery(function(){
            var gridSetting = {
                caption:"",
                pager:"pager",
                url:"dtjs_rcjyZhcx.do?method=rcjyList&type=query",
                colList:[
                    {label:'�꼶',name:'nj', index: 'nj',width:'5%'},
                    {label:'ѧ��',name:'xh', index: 'xh',width:'10%',formatter : xhLink},
                    {label:'����',name:'xm', index: 'xm',width:'10%'},
                    {label:'��Ժ',name:'symc1', index: 'symc1',width:'10%'},
                    {label:'�����༶',name:'bjmc', index: 'bjmc',width:'10%'},
                    {label:'������ò',name:'zzmmmc', index: 'zzmmmc',width:'10%'},
                    {label:'����ѧϰ����',name:'llxxcs', index: 'llxxcs',width:'10%'},
                    {label:'���ʵ������',name:'shsjcs', index: 'shsjcs',width:'10%'},
                    {label:'־Ը�����',name:'zycs', index: 'zycs',width:'10%'},
                    {label:'־Ը�ʱ��',name:'fwsc', index: 'fwsc',width:'10%'}
                ],
                sortname: "nj",
                sortorder: "asc"
            }
            var map = getSuperSearch();
            gridSetting["params"] = map;
            jQuery("#dataTable").initGrid(gridSetting);
        });
        function searchRs(){
            var map = getSuperSearch();
            jQuery("#dataTable").reloadGrid(map);
        }


        //dcglbh,�������ܱ��
        var DCGLBH = "dtjs_rcjy_zhcx.do";

        //�Զ��嵼�� ����
        function exportConfig() {
            //DCCLBH�������ܱ��,ִ�е�������
            customExport(DCGLBH, exprotData);
        }

        //��������
        function exprotData() {
            setSearchTj();//���ø߼���ѯ����
            var url = "dtjs_rcjyZhcx.do?method=exportData&dcglbh=" + DCGLBH;//dcglbh,�������ܱ��
            url = addSuperSearchParams(url);//���ø߼���ѯ����
            jQuery("form").eq(0).attr("action", url);
            jQuery("form").eq(0).submit();
        }
        function xhLink(cellValue, rowObject) {
            return "<a href='javascript:void(0);' class='name' onclick='view(\""
                + cellValue + "\");'>" + cellValue
                + "</a>";
        }
        //�鿴
        function view(xh) {
            showDialog("�ճ�������¼", 800, 550, "dtjs_rcjyZhcx.do?method=rcjyView&xh=" + xh);
        }
    </script>
</head>

<body>

<div class="tab_cur">
    <p class="location">
        <em>���ĵ�ǰλ�ã�</em><a>${title }</a>
    </p>
</div>
<html:form action="/szdw_fdyjcxgztj">
    <%@ include file="/comm/hiddenValue.jsp"%>
    <div class="toolbox">
            <%--<logic:equal value="yes" name="writeAble">--%>
        <div class="buttonbox">
            <ul>
                <li><a href="javascript:void(0);" onclick="exportConfig();return false;" class="btn_dc">����</a></li>
            </ul>
        </div>
            <%--</logic:equal>--%>
        <!-- �������� -->
        <%@ include file="/comm/search/superSearchArea.jsp"%>
        <!-- �������� end-->
    </div>
</html:form>
<div class="formbox">
    <!--����start-->
    <h3 class="datetitle_01">
        <span></span>
    </h3>
    <div style="overflow-x:scroll;">
    <table id="dataTable" ></table>
    </div>
    <div id="pager"></div>

</div>
</body>
</html>
