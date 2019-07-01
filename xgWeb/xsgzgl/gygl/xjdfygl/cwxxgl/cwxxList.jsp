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
                url : "gygl_fygl_cwxxgl10698.do?method=cwxxList&type=query",
                colList : [
                    {label:'pk',name:'pk',index :'pk',key:true,hidden:true,width:'10%'},
                    {label:'¥������',name:'ldmc',index:'ldmc',width:'10%'},
                    {label:'���Һ�',name:'qsh',index:'qsh',width:'10%'},
                    {label:'��λ��',name:'cwh',index:'cwh',width:'5%'},
                    {label:'�����꼶',name:'nj',index:'nj',width:'5%'},
                    {label:'xh',name:'xh',index:'xh',width:'5%',hidden:true},
                    {label:'��סѧ��',name:'xm',index:'xm',width:'5%'},
                    {label:'��סʱ��',name:'rzsj',index:'rzsj',width:'10%'},
                    {label:'��ע',name:'bz',index:'bz',width:'10%'},
                    {label:'�Ƿ�����λ',name:'sfbl',index:'sfbl',width:'10%',formatter:function(val,row){
                        if(val == "1"){
                            return "��";
                        } else if(val == "0"){
                            return "��";
                        } else {
                            return val;
                        }
                    }}
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
        function plbl(){
            var row = jQuery("#dataTable").getSeletRow();
            var ids = jQuery("#dataTable").getSeletIds();
            if(row.length == 0){
                showAlert("������ѡ��һ����¼!");
                return false;
            }
            for(var i = 0;i<row.length;i++){
                if(row[i]["xm"] != null || row[i]["nj"] != null){
                    showAlert("��ѡ��δ������δ��ס�Ĵ�λ����!");
                    return false;
                }
            }
            showDialog("����������λ",600,300,"gygl_fygl_cwxxgl10698.do?method=plbl&pks="+ids.toString());
        }
        function cwsscsh(){
            var url = "gygl_fygl_cwxxgl10698.do?method=cwsscsh";
            var rows = jQuery("#dataTable").getSeletRow();
            if(rows.length != 0){
                var ids = jQuery("#dataTable").getSeletIds();
                url= url+"&pks="+ids.toString();
            }
            showDialog("��λ������ʼ��",650,200,url);
        }

        function rz(){
            var rows = jQuery("#dataTable").getSeletRow();
            if(rows.length != 1){
                showAlert("��ѡ��һ����¼!");
                return false;
            }
            if(rows[0]["xm"] != null){
                showAlert("��λ����ס����������!");
                return false;
            }
            var url = "gygl_fygl_cwxxgl10698.do?method=rz&pk="+rows[0]["pk"];
            showDialog("ѧ����ס",650,500,url);
        }

        function ts(){
            var ids = jQuery("#dataTable").getSeletIds();
            var rows = jQuery("#dataTable").getSeletRow();
            if(ids.length == 0){
                showAlert("��ѡ��һ����¼��");
                return false;
            }
            var xhs = [];
            for(var i=0;i<rows.length;i++){
                if(rows[i]["xm"] == null){
                    showAlert("ѡ���¼�а���δ��ס��λ����ѡ������ס��λ��");
                    return false;
                }
                xhs.push(rows[i]["xh"]);
            }
            var url = "gygl_fygl_cwxxgl10698.do?method=ts&pks="+ids.toString() +"&xhs="+xhs.toString();
            showDialog("ѧ������",650,400,url);
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
                <logic:equal name="writeAble" value="yes">
                    <li><a href="javascript:void(0);" onclick="plbl();" class="btn_xg">��������</a></li>
                    <li><a href="javascript:void(0);" onclick="rz()" class="btn_xg">��ס</a></li>
                    <li><a href="javascript:void(0);" onclick="ts()" class="btn_xg">����</a></li>
                    <li><a href="javascript:void(0);" onclick="cwsscsh();" class="btn_xg">��λ������ʼ��</a></li>
                    <li><a href="javascript:void(0);" onclick="" class="btn_dr">������ס��Ϣ</a></li>
                </logic:equal>
                <li><a href="#" class="btn_dc" onclick="exportConfig();return false;">����</a></li>
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
