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
    <script type="text/javascript" src="xsgzgl/qgzx/xsgw/js/xsgwsh.js"></script>
    <script type="text/javascript" src="js/calendar/calendar.js"></script>
    <script type="text/javascript" src="xsgzgl/comm/exportNew/import.js"></script>
    <script type="text/javascript">
        jQuery(function(){
            var gridSetting = {
                caption:"ѧ����λ�����б�",
                pager:"pager",
                url:"qgzx_xsgwsh.do?method=zgxsList&type=query",
                colList:[
                    {label:'ѧ��',name:'xh', index: 'xh',width:'12%',formatter:function(value,row){
                        return "<a href='javascript:void(0);' class='name' onClick='seeInfo(\""+row["gwdm"]+"\",\""+row["xh"]+"\")'>"+value+"</a>";
                    }},
                    {label:'����',name:'xm', index: 'xm',width:'8%'},
                    {label:'���',name:'pycc', index: 'pycc',width:'7%',formatter:function(value,row){
                        if(value == "1"){
                            return "��ʿ��";
                        } else if(value == "2"){return "˶ʿ��";} else if(value == "3"){return "������"}
                        else if(value == "4"){
                            return "ר����"
                        } else if(value == "9"){
                            return "����";
                        } else {
                            return value;
                        }
                    }},
                    {label:'�����༶',name:'bjmc', index: 'bjmc',width:'12%'},
                    {label:'רҵ�༶',name:'zybjmc', index: 'zybjmc',width:'10%'},
                    {label:'��λ����',name:'gwmc', index: 'gwmc',width:'10%'},
                    {label:'���˵�λ',name:'yrdwmc', index: 'yrdwmc',width:'12%'},
                    {label:'��λ��ϵ��ʽ',name:'lxdh', index: 'lxdh',width:'12%'},
                    {label:'¼��ʱ��',name:'sgsj', index: 'sgsj',width:'15%'},
                    {label:'��λ����',name:'gwdm', index: 'gwdm',width:'15%',hidden:true},
                    {label:'pk',name:'pk', index: 'pk',width:'15%',hidden:true}
                ],
                sortname: "xh",
                sortorder: "desc"
            };

            jQuery("#dataTable").initGrid(gridSetting);
        });
        function searchRs(){
            var map = getSuperSearch();
            jQuery("#dataTable").reloadGrid(map);
        }
        function seeInfo(gwdm,xh){
            showDialog("ѧ����λ���",765,500,"qgzx_xsgwsh.do?method=xsgwmxck&xh="+xh+"&gwdm="+gwdm);
        }
        function pljg() {
            var rows = jQuery("#dataTable").getSeletRow();
            if(rows.length == 0){
                showAlertDivLayer("��ѡ��ѧ����");
                return false;
            }
            var list = [];
            for(var i=0;i<rows.length;i++){
                var map = {};
                map["gwdm"] = rows[i]["gwdm"];
                map["xh"] = rows[i]["xh"];
                list.push(map);
            }

            var jgList = encodeURIComponent(encodeURIComponent(JSON.stringify(list)));
            var url = "qgzx_xsgwsh.do?method=pljg&jgList="+jgList;
            showDialog("ѧ����ְ����",765,500,url);
        }
        function ckgzjl(){
            var rows = jQuery("#dataTable").getSeletRow();
            if(rows.length != 1){
                showAlertDivLayer("��ѡ��һ����¼��");
                return false;
            }
            window.location.href="qgzx_xsgwsh.do?method=ckgzjl&xh="+rows[0]["xh"];
        }
        function add(){
            showDialog("�����ڸ�ѧ��",800,500,"qgzx_xsgwsh.do?method=zjzgxs");
        }
        //dcglbh,�������ܱ��
        var DCGLBH = "qgzx_zgxs_dc";

        //�Զ��嵼�� ����
        function exportConfig() {
            //DCCLBH�������ܱ��,ִ�е�������
            customExport(DCGLBH, exprotData);
        }

        //��������
        function exprotData() {
            setSearchTj();//���ø߼���ѯ����
            var url = "qgzx_xsgwsh.do?method=exportData&dcglbh=" + DCGLBH;//dcglbh,�������ܱ��
            url = addSuperSearchParams(url);//���ø߼���ѯ����
            jQuery("form").eq(0).attr("action", url);
            jQuery("form").eq(0).submit();
        }
        //���뷽��
        function importConfig() {
            toImportDataNew("IMPORT_QGZX_ZGXS");
            return false;
        }
    </script>
</head>

<body>

<div class="tab_cur">
    <p class="location">
        <em>���ĵ�ǰλ�ã�</em><a>${title }</a>
    </p>
</div>
<html:form action="/qgzx_jfcjgl_cjff.do?method=gjcxCjff">
    <%@ include file="/comm/hiddenValue.jsp"%>
    <div class="toolbox">
        <div class="buttonbox">
            <ul>
                <logic:equal value="yes" name="writeAble">
                    <li>
                        <a href="javascript:void(0);" id="btn_sh" onclick="pljg();return false;" class="btn_sc">�������</a>
                    </li>
                    <li>
                        <a href="javascript:void(0);" id="btn_qxsh" onclick="ckgzjl();return false;" class="btn_sh">�鿴������¼</a>
                    </li>
                    <li>
                        <a href="javascript:void(0);" onclick="add();return false;" class="btn_zj">�����ڸ�ѧ��</a>
                    </li>
                    <li>
                        <a href="javascript:void(0);" onclick="importConfig();return false;" class="btn_dr">�����ڸ�ѧ��</a>
                    </li>
                </logic:equal>
                <li>
                    <a href="javascript:void(0);" onclick="exportConfig();return false;" class="btn_dc">����</a>
                </li>
            </ul>
        </div>
        <!-- �������� -->
        <%@ include file="/comm/search/superSearchArea.jsp"%>
        <!-- �������� end-->
    </div>
</html:form>
<div class="main_box">
    <!--����start-->
    <h3 class="datetitle_01">
        <span> �ڸ�ѧ���б�</span>
    </h3>
    <div class="con_overlfow">
        <table id="dataTable" ></table>
        <div id="pager"></div>
    </div>

</div>
</body>
</html>
