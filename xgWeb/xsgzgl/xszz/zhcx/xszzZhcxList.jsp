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
                url:"xszz_newZhcx.do?method=zhcxList&type=query",
                colList:[
                    {label:'ѧ��',name:'xh', index: 'xh',width:'10%'},
                    {label:'����',name:'xm', index: 'xm',width:'10%'},
                    {label:'ѧ��',name:'xn', index: 'xn',width:'10%'},
                    {label:'ѧԺ����',name:'xydm', index: 'xydm',width:'10%',hidden:'true'},
                    {label:'ѧԺ',name:'xymc', index: 'xymc',width:'10%'},
                    {label:'רҵ����',name:'zydm', index: 'zydm',width:'10%',hidden:'true'},
                    {label:'רҵ',name:'zymc', index: 'zymc',width:'10%'},
                    {label:'רҵ�༶����',name:'zybj', index: 'zybj',width:'10%',hidden:'true'},
                    {label:'רҵ�༶',name:'zybjmc', index: 'zybjmc',width:'10%'},
                    {label:'��Ժ����',name:'sydm', index: 'sydm',width:'10%',hidden:'true'},
                    {label:'��Ժ',name:'symc', index: 'symc',width:'10%'},
                    {label:'�����༶����',name:'bjdm', index: 'bjdm',width:'10%',hidden:'true'},
                    {label:'�����༶',name:'bjmc', index: 'bjmc',width:'10%'},

                    {label:'��ѧ��',name:'jxj', index: 'jxj',width:'10%'},
                    {label:'��ѧ��',name:'zxj', index: 'zxj',width:'10%'},
                    {label:'�ڹ���ѧ��ʱ',name:'qggs', index: 'qggs',width:'10%'},
                    {label:'�ڹ���ѧ����',name:'qgje', index: 'qgje',width:'10%'},
                    {label:'���ѳ̶�',name:'rddc', index: 'rddc',width:'10%',hidden:'true'},
                    {label:'�������ѳ̶�',name:'kncd', index: 'kncd',width:'10%'},
                    {label:'��Դ�ش���',name:'syddkje', index: 'syddkje',width:'10%'},
                    {label:'���Ҵ���',name:'gjdkje', index: 'gjdkje',width:'10%'},
                    {label:'�Ƿ񽨵�����',name:'sfjdlk', index: 'sfjdlk',width:'10%'}
                ],
                sortname: "xn",
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
        var DCGLBH = "xszz_new_zhcx.do";

        //�Զ��嵼�� ����
        function exportConfig() {
            //DCCLBH�������ܱ��,ִ�е�������
            customExport(DCGLBH, exprotData);
        }

        //��������
        function exprotData() {
            setSearchTj();//���ø߼���ѯ����
            var url = "xszz_newZhcx.do?method=exportData&dcglbh=" + DCGLBH;//dcglbh,�������ܱ��
            url = addSuperSearchParams(url);//���ø߼���ѯ����
            jQuery("form").eq(0).attr("action", url);
            jQuery("form").eq(0).submit();
        }
    </script>
</head>

<body>

<div class="tab_cur">
    <p class="location">
        <em>���ĵ�ǰλ�ã�</em><a>${title }</a>
    </p>
</div>
<html:form action="/xszz_newZhcx">
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
