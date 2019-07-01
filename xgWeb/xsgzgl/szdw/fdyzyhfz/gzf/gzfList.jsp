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
                url:"szdw_fdy_gzfwh.do?method=gzfList&type=query",
                colList:[
                    {label:'id',name:'id', index: 'id',width:'10%',key:true,hidden:true},
                    {label:'ְ����',name:'zgh', index: 'zgh',width:'10%',formatter:function(cell,row){
                        return "<a href='javascript:void(0);' onclick='view(\""+row["id"]+"\")'>"+cell+"</a>";
                    }},
                    {label:'����',name:'xm', index: 'xm',width:'10%'},
                    {label:'�Ա�',name:'xb', index: 'xb',width:'10%'},
                    {label:'������Ժ',name:'symc', index: 'symc',width:'10%'},
                    {label:'����ѧԺ',name:'bmmc', index: 'bmmc',width:'10%'},
                    {label:'����',name:'mc', index: 'mc',width:'10%'},
                    {label:'�о�����',name:'yjfx', index: 'yjfx',width:'10%'},
                    {label:'����',name:'jbmc', index: 'jbmc',width:'10%'},
                    {label:'����ʱ��',name:'clsj', index: 'clsj',width:'10%'},
                    {label:'�����ܶ�',name:'jfze', index: 'jfze',width:'10%'},
                    {label:'���˳е���ɫ',name:'cdjsmc', index: 'cdjsmc',width:'10%'},
                    {label:'�Ƿ񿼺�ͨ��',name:'sfkhtgmc', index: 'sfkhtgmc',width:'10%'}
                ],
                sortname: "zgh",
                sortorder: "asc"
            }
            jQuery("#dataTable").initGrid(gridSetting);
        });
        function searchRs(){
            var map = getSuperSearch();
            jQuery("#dataTable").reloadGrid(map);
        }
        function add(){
            showDialog("���ӹ�����/��������Ϣ",700,550,"szdw_fdy_gzfwh.do?method=add");
        }
        function update(){
            var rows = jQuery("#dataTable").getSeletRow();
            if(rows.length != 1){
                showAlertDivLayer("��ѡ��һ����Ҫ�޸ĵļ�¼��");
                return false;
            }
            showDialog("�޸Ĺ�����/��������Ϣ",700,550,"szdw_fdy_gzfwh.do?method=update&id="+rows[0]["id"]);
        }
        function view(val){
            showDialog("������/��������Ϣ�鿴",700,550,"szdw_fdy_gzfwh.do?method=view&id="+val);
        }
        function del() {
            var ids = jQuery("#dataTable").getSeletIds();
            if(ids == 0){
                showAlertDivLayer("��ѡ��һ����Ҫɾ���ļ�¼��");
                return false;
            }

            confirmInfo("��ȷ��Ҫɾ��"+ids.length +"����¼��?",function(ty){
                if(ty=="ok"){
                    jQuery.post("szdw_fdy_gzfwh.do?method=del",{values:ids.toString()},function(data){
                        alertInfo(data["message"]);
                        jQuery("#dataTable").reloadGrid();
                    },'json');
                }
            });
        }
        //����
        function importConfig(){
            toImportDataNew("IMPORT_FDYGZF");
            return false;
        }

        //dcglbh,�������ܱ��
        var DCGLBH = "szdw_fdy_gzfwh.do";

        //�Զ��嵼�� ����
        function exportConfig() {
            //DCCLBH�������ܱ��,ִ�е�������
            customExport(DCGLBH, exprotData);
        }

        //��������
        function exprotData() {
            setSearchTj();//���ø߼���ѯ����
            var url = "szdw_fdy_gzfwh.do?method=exportData&dcglbh=" + DCGLBH;//dcglbh,�������ܱ��
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
<html:form action="/szdw_jfxx.do">
    <%@ include file="/comm/hiddenValue.jsp"%>
    <div class="toolbox">
            <%--<logic:equal value="yes" name="writeAble">--%>
        <div class="buttonbox">
            <ul>
                <li><a href="javascript:void(0);" id="btn_zj" class="btn_zj" onclick="add();return false;">����</a></li>
                <li><a href="javascript:void(0);" id="btn_xg" class="btn_xg" onclick="update();return false;">�޸�</a></li>
                <li><a href="javascript:void(0);" id="btn_sc" class="btn_sc" onclick="del();return false;">ɾ��</a></li>
                <li><a href="javascript:void(0);" onclick="importConfig();return false;" class="btn_dr">����</a></li>
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

    <table id="dataTable" ></table>
    <div id="pager"></div>

</div>
</body>
</html>
