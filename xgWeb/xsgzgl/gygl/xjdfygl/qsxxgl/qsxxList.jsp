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
                url : "gygl_fygl_qsxxgl10698.do?method=qsxxList&type=query",
                colList : [
                    {label:'¥������',name:'lddm',index :'lddm',hidden:true,width:'10%'},
                    {label:'¥������',name:'ldmc',index:'ldmc',width:'10%'},
                    {label:'���Һ�',name:'qsh',index:'qsh',width:'10%'},
                    {label:'���',name:'ch',index:'ch',width:'5%'},
                    {label:'��������',name:'fjlx',index:'fjlx',width:'5%',formatter:function(val,row){
                        if(val == "01"){
                            return "����";
                        } else if(val == "02"){
                            return "ֵ����";
                        } else if(val == "03"){
                            return "����";
                        } else {
                            return val;
                        }
                    }},
                    {label:'��������',name:'fjzx',index:'fjzx',width:'5%',formatter:function(val,row){
                        if(val == "1"){
                            return "��";
                        } else if(val == "2"){
                            return "��";
                        } else if(val == "3"){
                            return "��";
                        } else if(val == "4"){
                            return "��";
                        } else {
                            return val;
                        }
                    }},
                    {label:'�����Ա�',name:'qsxb',index:'qsxb',width:'5%',formatter:function(val,row){
                        if(val == "1"){
                            return "��";
                        } else if(val == "2"){
                            return "Ů";
                        } else {
                            return "��ס";
                        }
                    }},
                    {label:'�Ƿ�0��',name:'sfhlc',index:'sfhlc',width:'7%',formatter:function(val,row){
                        if(val == "1"){
                            return "��";
                        } else {
                            return "��";
                        }
                    }},
                    {label:'��λ��',name:'cwss',index:'cwss',width:'5%'},
                    {label:'�շѱ�׼',name:'sfbz',index:'sfbz',width:'5%'},
                    {label:'���ҵ绰',name:'qsdh',index:'qsdh',width:'5%'},
                    {label:'�Ƿ��пյ�',name:'sfykt',index:'sfykt',width:'5%',formatter:function(val,row){
                        if(val == "1"){
                            return "��";
                        } else {
                            return "��";
                        }
                    }},
                    {label:'�Ƿ���������',name:'sfywsj',index:'sfywsj',width:'5%',formatter:function(val,row){
                        if(val == "1"){
                            return "��";
                        } else {
                            return "��";
                        }
                    }},
                    {label:'������λ��',name:'wsjwz',index:'wsjwz',width:'5%',formatter:function(val,row){
                        if(val == "01"){
                            return "����";
                        } else if(val == "02"){
                            return "��̨";
                        } else {
                            return val;
                        }
                    }},
                    {label:'����������',name:'wsjzx',index:'wsjzx',width:'5%',formatter:function(val,row){
                        if(val == "1"){
                            return "���Ͻ�";
                        } else if(val == "2"){
                            return "���Ͻ�";
                        } else if(val == "3"){
                            return "������";
                        } else if(val == "4"){
                            return "������";
                        } else {
                            return val;
                        }
                    }}
                ],
                sortname: "qsh",
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
        function add(){
            showDialog("����¥��",650,450,"gygl_fygl_qsxxgl10698.do?method=add");
        }
        function update(){
            var rows = jQuery("#dataTable").getSeletRow();
            if(rows.length != 1){
                alertInfo("��ѡ��һ����Ҫ�޸ĵļ�¼��");
                return false;
            }
            showDialog("�޸�¥��",650,450,"gygl_fygl_qsxxgl10698.do?method=update&lddm="+rows[0]["lddm"]+"&qsh="+rows[0]["qsh"]);
        }
        function del(){
            var rows = jQuery("#dataTable").getSeletRow();
            if(rows.length == 0){
                alertInfo("��ѡ����Ҫɾ���ļ�¼��");
                return false;
            }
            var a = [];
            for(var i=0;i<rows.length;i++){
                var pk = rows[i]["lddm"]+"@!!!"+rows[i]["qsh"];
                a.push(pk);
            }
            showConfirmDivLayer("��ȷ��Ҫɾ��ѡ��ļ�¼��",{"okFun":function(){
                jQuery.post("gygl_fygl_qsxxgl10698.do?method=del",{values:a.toString()},function(data){
                    showAlert(data["message"]);
                    jQuery("#dataTable").reloadGrid();
                },'json');
            }});
        }
        function plxgqs(){
            var rows = jQuery("#dataTable").getSeletRow();
            if(rows.length == 0){
                alertInfo("������ѡ��һ����¼����");
                return false;
            }
            var a = [];
            for(var i=0;i<rows.length;i++){
                var pk = rows[i]["lddm"]+"@!!!"+rows[i]["qsh"];
                a.push(pk);
            }
            showDialog("�����޸�����",650,300,"gygl_fygl_qsxxgl10698.do?method=plxgqs&pks="+a.toString());
        }

        function xgcw(){
            var rows = jQuery("#dataTable").getSeletRow();
            if(rows.length != 1){
                alertInfo("��ѡ��һ����¼����");
                return false;
            }
            var pk = rows[0]["lddm"]+"@!!!"+rows[0]["qsh"];
            showDialog("�����޸�����",650,300,"gygl_fygl_qsxxgl10698.do?method=xgcw&pk="+pk);
        }
        function qssscsh(){
            var url = "gygl_fygl_qsxxgl10698.do?method=qssscsh";
            var rows = jQuery("#dataTable").getSeletRow();
            if(rows.length != 0){
                var a = [];
                for(var i=0;i<rows.length;i++){
                    var pk = rows[i]["lddm"]+"@!!!"+rows[i]["qsh"];
                    a.push(pk);
                }
                url= url+"&pks="+a.toString();
            }
            showDialog("����������ʼ��",650,200,url);
        }
        var DCCLBH = "gygl_fygl_qsxxgl10698.do";
        //�Զ��嵼�� ����
        function exportConfig() {
            //DCCLBH�������ܱ��,ִ�е�������
            customExport(DCCLBH, exportData);
        }

        //��������
        function exportData() {
            setSearchTj();//���ø߼���ѯ����
            var url = "gygl_fygl_qsxxgl10698.do?method=exportData&dcclbh=" + DCCLBH;//dcclbh,�������ܱ��
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
                <logic:equal name="writeAble" value="yes">
                    <li><a href="javascript:void(0);" onclick="add();" class="btn_zj">����</a></li>
                    <li><a href="javascript:void(0);" onclick="update();" class="btn_xg">�޸�</a></li>
                    <li><a href="javascript:void(0);" onclick="del();" class="btn_sc">ɾ��</a></li>
                    <li><a href="javascript:void(0);" onclick="plxgqs();" class="btn_xg">�����޸�����</a></li>
                    <li><a href="javascript:void(0);" onclick="xgcw();" class="btn_xg">�޸Ĵ�λ</a></li>
                    <li><a href="javascript:void(0);" onclick="qssscsh();" class="btn_xg">����������ʼ��</a></li>
                    <li><a href="javascript:void(0);" onclick="" class="btn_dr">��������</a></li>
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
