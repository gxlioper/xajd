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
                caption:"�鵥�б�",
                pager:"pager",
                url:"hdgl_hdgl_jzbgjh.do?method=jzbgjhList&type=query",
                colList:[
                    {label:'key',name:'jzjhid', index: 'jzjhid',key:true ,hidden:true},
                    {label:'����',name:'jzzt', index: 'jzzt',width:'8%'},
                    {label:'������',name:'jzzjr', index: 'jzzjr',width:'8%'},
                    {label:'�ⶨʱ��',name:'jzndsj', index: 'jzndsj',width:'10%'},
                    {label:'�ⶨ�ص�',name:'jznddd', index: 'jznddd',width:'8%'},
                    {label:'���쵥λ',name:'jzzbdw', index: 'jzzbdw',width:'8%'},
                    {label:'�����˽���',name:'jzzjrjs', index: 'jzzjrjs',width:'15%'}
                ]
            }
            gridSetting["params"]=getSuperSearch();
            jQuery("#dataTable").initGrid(gridSetting);
        });

        function searchRs(){
            var map = getSuperSearch();
            jQuery("#dataTable").reloadGrid(map);

        }
        function add(){
            var url = "hdgl_hdgl_jzjh.do?method=add";
            var title = "���ӽ�����Ϣ";
            showDialog(title,700,350,url);

        }

        function update() {
            var rows = jQuery("#dataTable").getSeletRow();
            if (rows.length != 1) {
                showAlertDivLayer("��ѡ��һ����Ҫ�޸ĵļ�¼��");
            } else {
                var url = 'hdgl_hdgl_jzjh.do?method=update&jzjhid=' + rows[0]["jzjhid"];
                showDialog("�޸Ľ�����Ϣ", 700,350, url);
            }
        }


        //ɾ��
        function del() {
            var ids = jQuery("#dataTable").getSeletIds();
            if (ids.length == 0) {
                alertInfo("��ѡ����Ҫɾ���ļ�¼��");
            } else {
                showConfirmDivLayer("��ȷ��Ҫɾ��ѡ��ļ�¼��", {
                    "okFun" : function() {
                        jQuery.post("hdgl_hdgl_jzjh.do?method=del", {
                            values : ids.toString()
                        }, function(data) {
                            alertInfo(data["message"]);
                            jQuery("#dataTable").reloadGrid();
                        }, 'json');
                    }
                });
            }
        }

        //�°浼��
        function dr() {
            toImportDataNew("IMPORT_JZJHBG");
            return false;
        }

        var DCCLBH = "hdgl_hdgl_jzbgjh.do";//dcclbh,�������ܱ��

        //�Զ��嵼�� ����
        function exportConfig() {
            //DCCLBH�������ܱ��,ִ�е�������
            customExport(DCCLBH, ExportData);
        }

        // ��������
        function ExportData() {
            setSearchTj();//���ø߼���ѯ����
            var url = "hdgl_hdgl_jzjh.do?method=exportData&dcclbh=" + DCCLBH;//dcclbh,�������ܱ��
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
<html:form action="/hdgl_hdgl_jzjh">
    <%@ include file="/comm/hiddenValue.jsp"%>
    <div class="toolbox">
        <!-- ��ť -->
        <div class="buttonbox">
            <ul>
                <%--<logic:equal name="writeAble" value="yes">--%>
                    <li>
                        <a href="javascript:void(0);" class="btn_zj" onclick="add();return false;">����</a>
                    </li>
                    <li>
                        <a href="javascript:void(0);" onclick="update();return false;" class="btn_xg">�޸�</a>
                    </li>
                    <li>
                        <a href="javascript:void(0);" onclick="del();return false;" class="btn_sc">ɾ��</a>
                    </li>
                    <li>
                        <a href="javascript:void(0);" onclick="dr();return false;" class="btn_dr">����</a>
                    </li>
                <%--</logic:equal>--%>
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
        <span>��������ƻ��б�&nbsp;&nbsp; </span>
    </h3>
    <div class="con_overlfow">
        <table id="dataTable" ></table>
        <div id="pager"></div>
    </div>
</div>
</body>
</html>
