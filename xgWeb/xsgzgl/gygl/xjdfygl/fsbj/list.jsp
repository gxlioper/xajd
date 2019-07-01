<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <%@ include file="/syscommon/head.ini"%>
    <script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
    <script type="text/javascript" src="js/calendar/calendar.js"></script>
    <script type="text/javascript" src="xsgzgl/comm/exportNew/import.js"></script>
    <script type="text/javascript">
        jQuery(function(){
            var gridSetting = {
                caption : "¥����Ϣ�б�",
                pager : "pager",
                url : "gygl_fygl_fsbjwh.do?method=list&type=query",
                colList : [
                    {label:'pk',name:'pk',index :'pk',key:true,hidden:true,width:'10%'},
                    {label:'У������',name:'xqmc',index:'xqmc',width:'10%'},
                    {label:'¥������',name:'ldmc',index:'ldmc',width:'10%'},
                    {label:'���Һ�',name:'qsh',index:'qsh',width:'10%'},
                    {label:'��λ��',name:'cws',index:'cws',width:'5%'},
//                    {label:'���',name:'xn',index:'xn',width:'5%'},
                    {label:'��ˢ���',name:'fsbj',index:'fsbj',width:'7%',formatter:function(val,row){
                        if(val == "�ѷ�ˢ"){
                            return "�ѷ�ˢ";
                        } else {
                            return "δ��ˢ";
                        }
                    }},
                    {label:'����ˢ����',name:'fsrq',index:'fsrq',width:'10%'}
                ],
                sortname: "lddm,qsh",
                sortorder: "asc"
            }
            var map = getSuperSearch();
            gridSetting["params"] = map;
            jQuery("#dataTable").initGrid(gridSetting);
        });
        function searchRs() {
            var map = getSuperSearch();
            jQuery("#dataTable").reloadGrid(map);
        }
        function fsbj(){
            var row = jQuery("#dataTable").getSeletRow();
            var url = "gygl_fygl_fsbjwh.do?method=add";
            if(row.length != 0){
                var ids = jQuery("#dataTable").getSeletIds();
                url += "&pks="+ids.toString();
            }

            showDialog("��ˢ���",600,300,url);
        }
        var DCCLBH = "gygl_fygl_fsbj.do";
        //�Զ��嵼�� ����
        function exportConfig() {
            //DCCLBH�������ܱ��,ִ�е�������
            customExport(DCCLBH, exportData);
        }

        //��������
        function exportData() {
            setSearchTj();//���ø߼���ѯ����
            var url = "gygl_fygl_fsbjwh.do?method=exportData&dcclbh=" + DCCLBH;//dcclbh,�������ܱ��
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
<html:form action="/gyglnew_wmqsxsmd_12688">
    <%@ include file="/comm/hiddenValue.jsp"%>
    <div class="toolbox">
        <!-- ��ť -->
        <div class="buttonbox">
            <ul>
                <%--<logic:equal name="writeAble" value="yes">--%>
                    <li><a href="javascript:void(0);" onclick="fsbj();" class="btn_xg">��ˢ���</a></li>
                <%--</logic:equal>--%>
                <li><a href="#" class="btn_dc" onclick="exportData();return false;">����</a></li>
            </ul>
        </div>
        <!-- �������� -->
        <%@ include file="/comm/search/superSearchArea.jsp"%>
        <!-- �������� end-->
    </div>
</html:form>

<div class="main_box">
    <h3 class=datetitle_01>
        <span></span>
    </h3>
    <div class="con_overlfow">
        <table id="dataTable" ></table>
        <div id="pager"></div>
    </div>
</div>
</body>
</html>
