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
                url : "gygl_fygl_cwxxgl10698.do?method=list&type=query",
                colList : [
                    {label:'pk',name:'pk',index :'pk',key:true,hidden:true,width:'10%'},
                    {label:'¥������',name:'ldmc',index:'ldmc',width:'10%'},
                    {label:'���Һ�',name:'qsh',index:'qsh',width:'10%'},
                    {label:'��λ��',name:'cwh',index:'cwh',width:'5%'},
                    {label:'�����꼶',name:'nj',index:'nj',width:'5%'},
                    {label:'xh',name:'xh',index:'xh',width:'5%',hidden:true},
                    {label:'��סѧ��',name:'xm',index:'xm',width:'5%'},
                    {label:'�Ա�',name:'xb',index:'xb',width:'5%'},
                    {label:'��סʱ��',name:'rzsj',index:'rzsj',width:'10%'},
                    {label:'ס�޵���ʱ��',name:'zsdqsj',index:'zsdqsj',width:'10%'}
                ],
                sortname: "lddm,qsh,cwh",
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
        function cwsscsh(){
            var rows = jQuery("#dataTable").getSeletRow();
            if(rows.length == 0){
                showAlert("��ѡ��λ��");
                return false;
            }
            var ids = jQuery("#dataTable").getSeletIds();
            var url = "gygl_fygl_cwxxgl10698.do?method=szdqsj&pks="+ids.toString();
            showDialog("���õ���ʱ��",650,200,url);
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
                <li><a href="javascript:void(0);" onclick="cwsscsh();" class="btn_xg">���õ���ʱ��</a></li>
            </ul>
        </div>
        <!-- �������� -->
        <%@ include file="/comm/search/superSearchArea.jsp"%>
        <!-- �������� end-->
    </div>
</html:form>

<div class="main_box">
    <h3 class=datetitle_01>
        <span>��������ѧ������</span>
    </h3>
    <div class="con_overlfow">
        <table id="dataTable" ></table>
        <div id="pager"></div>
    </div>
</div>
</body>
</html>
