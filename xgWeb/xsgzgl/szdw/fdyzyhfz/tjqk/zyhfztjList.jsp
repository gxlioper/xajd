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
                url:"szdw_fdyzyhfztj.do?method=zyhfztjList&type=query",
                colList:[
                    {label:'ѧ��',name:'xn', index: 'xn',width:'10%'},
                    {label:'ѧ��',name:'xq', index: 'xq',width:'10%'},
                    {label:'���Ŵ���',name:'bmdm', index: 'bmdm',width:'10%',hidden:'true'},
                    {label:'����',name:'bmmc', index: 'bmmc',width:'10%'},
                    {label:'ְ����',name:'zgh', index: 'zgh',width:'10%'},
//                        ,formatter:function(cell,row){
//                        return "<a href='javascript:void(0);' onclick='view(\""+row["sqid"]+"\")'>"+cell+"</a>";
//                    }},
                    {label:'����',name:'xm', index: 'xm',width:'10%'},
                    {label:'��ѧ�ܿ�ʱ',name:'jxks', index: 'jxks',width:'10%'},
                    {label:'����������',name:'lws', index: 'lws',width:'10%'},
                    {label:'��������',name:'gzfs', index: 'gzfs',width:'10%'},
                    {label:'�е�������',name:'kts', index: 'kts',width:'10%'},
                    {label:'����������',name:'zzs', index: 'zzs',width:'10%'},
                    {label:'У��������',name:'xjjls', index: 'xjjls',width:'10%'},
                    {label:'ʡ��������',name:'sjjls', index: 'sjjls',width:'10%'},
                    {label:'���Ҽ�������',name:'gjjls', index: 'gjjls',width:'10%'},
                    {label:'������',name:'tkcs', index: 'tkcs',width:'10%'}
                ],
                sortname: "zgh",
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
        var DCGLBH = "szdw_fdy_zyhfztj.do";

        //�Զ��嵼�� ����
        function exportConfig() {
            //DCCLBH�������ܱ��,ִ�е�������
            customExport(DCGLBH, exprotData);
        }

        //��������
        function exprotData() {
            setSearchTj();//���ø߼���ѯ����
            var url = "szdw_fdyzyhfztj.do?method=exportData&dcglbh=" + DCGLBH;//dcglbh,�������ܱ��
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
<html:form action="/szdw_fdyzyhfztj">
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
