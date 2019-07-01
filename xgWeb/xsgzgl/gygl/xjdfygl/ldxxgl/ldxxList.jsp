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
                url : "gygl_fygl_ldxxgl10698.do?method=ldxxList&type=query",
                colList : [
                    {label:'¥������',name:'lddm',index :'lddm',key:true,width:'10%'},
                    {label:'¥������',name:'ldmc',index:'ldmc',width:'10%'},
                    {label:'¥�����',name:'ldjc',index:'ldjc',width:'10%'},
                    {label:'¥���Ա�',name:'ldxb',index:'ldxb',width:'5%',formatter:function(val,row){
                        if(val == "1"){
                            return "��";
                        } else if(val == "2"){
                            return "Ů";
                        } else {
                            return "��ס";
                        }
                    }},
                    {label:'¥������',name:'ldzx',index:'ldzx',width:'5%',formatter:function(val,row){
                        if(val == "1"){
                            return "��";
                        } else if(val == "2"){
                            return "��";
                        } else if(val == "3"){
                            return "��";
                        } else {
                            return "��";
                        }
                    }},
                    {label:'¥������',name:'ldcs',index:'ldcs',width:'5%'},
                    {label:'��ʼ���',name:'qsch',index:'qsch',width:'15%'},
                    {label:'�Ƿ�0��',name:'sfhlc',index:'sfhlc',width:'7%',formatter:function(val,row){
                        if(val == "1"){
                            return "��";
                        } else {
                            return "��";
                        }
                    }},
                    {label:'ѧ������',name:'pyccmc',index:'pyccmc',width:'7%'}
                ],
                sortname: "lddm",
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
            showDialog("����¥��",600,400,"gygl_fygl_ldxxgl10698.do?method=add");
        }
        function update(){
            var rows = jQuery("#dataTable").getSeletRow();
            if(rows.length != 1){
                alertInfo("��ѡ��һ����Ҫ�޸ĵļ�¼��");
                return false;
            }
            showDialog("�޸�¥��",600,400,"gygl_fygl_ldxxgl10698.do?method=update&lddm="+rows[0]["lddm"]);
        }
        function del(){
            var ids = jQuery("#dataTable").getSeletIds();
            if(ids.length == 0){
                alertInfo("��ѡ����Ҫɾ���ļ�¼��");
                return false;
            }
            showConfirmDivLayer("��ȷ��Ҫɾ��ѡ��ļ�¼��",{"okFun":function(){
                jQuery.post("gygl_fygl_ldxxgl10698.do?method=del",{values:ids.toString()},function(data){
                    showAlert(data["message"]);
                    jQuery("#dataTable").reloadGrid();
                },'json');
            }});
        }
        function qssc(){
            var ids = jQuery("#dataTable").getSeletIds();
            if(ids.length != 1){
                alertInfo("��ѡ��һ����¼��");
                return false;
            }
            showDialog("�޸�¥��",600,550,"gygl_fygl_ldxxgl10698.do?method=qssc&lddm="+ids.toString());
        }
        var DCCLBH = "gygl_fygl_ldxxgl10698.do";
        //�Զ��嵼�� ����
        function exportConfig() {
            //DCCLBH�������ܱ��,ִ�е�������
            customExport(DCCLBH, exportData);
        }

        //��������
        function exportData() {
            setSearchTj();//���ø߼���ѯ����
            var url = "gygl_fygl_ldxxgl10698.do?method=exportData&dcclbh=" + DCCLBH;//dcclbh,�������ܱ��
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
                    <li><a href="javascript:void(0);" onclick="qssc();" class="btn_xg">��������</a></li>
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
