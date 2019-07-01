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
                caption : "�ʲ���Ϣ�б�",
                pager : "pager",
                url : "gygl_zcgl_zcxxgl.do?method=zcxxList&type=query",
                colList : [
                    {label:'id',name:'id',index :'id',key:true,hidden:true,width:'10%'},
                    {label:'����',name:'mc',index :'mc',width:'10%'},
                    {label:'��������',name:'lxmc',index:'lxmc',width:'10%'},
                    {label:'����',name:'cjmc',index:'cjmc',width:'10%'},
                    {label:'�ʲ�����(��)',name:'zccs',index:'zccs',width:'10%'},
                    {label:'��������',name:'gxny',index:'gxny',width:'5%'},
                    {label:'�۸�(Ԫ)',name:'jg',index:'jg',width:'10%'}
                ],
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

        //��������
        function exprotData() {
            setSearchTj();//���ø߼���ѯ����
            var url = "gygl_sfqskh_jgwh.do?method=exportData";//dcglbh,�������ܱ��
            url = addSuperSearchParams(url);//���ø߼���ѯ����
            jQuery("form").eq(0).attr("action", url);
            jQuery("form").eq(0).submit();
        }
        function add() {
            showDialog("����", 700, 300, "gygl_zcgl_zcxxgl.do?method=add");
        }
        function update() {
            var ids = jQuery("#dataTable").getSeletIds();
            if(ids.length != 1){
                showAlert("��ѡ��һ����¼��");
                return false;
            }
            showDialog("�޸�", 700, 300, "gygl_zcgl_zcxxgl.do?method=update&id="+ids[0]);
        }
        function view() {
            var ids = jQuery("#dataTable").getSeletIds();
            if(ids.length != 1){
                showAlert("��ѡ��һ����¼��");
                return false;
            }
            showDialog("�鿴", 700, 300, "gygl_zcgl_zcxxgl.do?method=view&id="+ids[0]);
        }
        function del(){
            var ids = jQuery("#dataTable").getSeletIds();
            if(ids.length == 0){
                showAlert("������ѡ��һ����¼��");
                return false;
            }
            var url = "gygl_zcgl_zcxxgl.do?method=del&ids="+ids.toString();
            showConfirmDivLayer("��ȷ��Ҫɾ��ѡ��ļ�¼��", {
                "okFun" : function() {
                    jQuery.post(url,{},function(data){
                        showAlertDivLayer(data["message"]);
                        jQuery("#dataTable").reloadGrid();
                    },'json');
                }
            });

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
                <li><a href="javascript:void(0);" onclick="add();return false;" class="btn_zj">����</a></li>
                <li><a href="javascript:void(0);" onclick="update();return false;" class="btn_xg">�޸�</a></li>
                <li><a href="javascript:void(0);" onclick="view();return false;" class="btn_ck">�鿴</a></li>
                <li><a href="javascript:void(0);" onclick="del();return false;" class="btn_sc">ɾ��</a></li>
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
