<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html>
<head>
    <title>��ʦ��Ϣ�б�</title>
    <%@ include file="/syscommon/head.ini"%>
    <script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
    <script type="text/javascript" src="js/check.js"></script>
    <script language="javascript" src="xsgzgl/szdw/fdypx/js/fdypxjg.js"></script>
    <script language="javascript" src="js/xsgzgl/szdw/dwwh.js"></script>
    <script type="text/javascript" src="xsgzgl/comm/exportNew/import.js"></script>
    <script type="text/javascript">
        function initGrid(){
            var gridSetting = {
                caption:"��ʦ��Ϣ�б�",
                pager:"pager",
                url:"general_szdw.do?method=fdyxxwh&type=query",
                colList:[
                    {label:'ְ����',name:'zgh', index: 'zgh',key:true,width:'10%',formatter:function(val,row){
                        return "<a class='name' href='#' onclick='viewFdyxx(\"" + val + "\")'>"+val+"</a>";
                    }},
                    {label:'����',name:'xm', index: 'xm',width:'8%'},
                    {label:'�Ա�',name:'xb', index: 'xb',width:'5%'},
                    {label:'��ϵ�绰',name:'lxdh', index: 'lxdh',width:'10%'},
                    {label:'��������',name:'bmmc', index: 'bmmc',width:'13%'},
                    {label:'����Ա������',name:'fdydbs', index: 'fdydbs',width:'5%'},
                    {label:'�����δ�����',name:'bzrdbs', index: 'bzrdbs',width:'5%'},
                    {label:'�û����',name:'yhsf', index: 'yhsf',width:'5%'},
                    {label:'ϵͳ��¼����',name:'dls', index: 'dls',width:'5%'}
                ]
            };
            gridSetting["params"]=getSuperSearch();
            jQuery("#dataTable").initGrid(gridSetting);
        };
        function searchRs(){
            var map = getSuperSearch();
            jQuery("#dataTable").reloadGrid(map);
        }
        jQuery(function(){
            initGrid();
        })
        function viewFdyxx(zgh){
            var url='szdw_fdyxx.do?method=fdyxxView&zgh='+zgh;
            showDialog('', 830, 500, url);

        }
        function view(){
            var row = jQuery("#dataTable").getSeletRow();
            if(row.length == 1){
                viewFdyxx(row[0]["zgh"]);
            }else{
                showAlertDivLayer("�빴ѡ<font color='blue'>һ����¼</font>���в鿴");
                return false;
            }
        }
        function configureExportData() {
            customExport("szdw_general_dwwh.do", fdyxxwhExportData);
        }

        // ��������
        function fdyxxwhExportData() {
            setSearchTj();//���ø߼���ѯ����
//            var ids = jQuery("#dataTable").getSeletIds();
            var url = "szdw_dwwh.do?method=fdyxxwhExportData&dcclbh=" + "szdw_general_dwwh.do";//dcclbh,�������ܱ��
            /*if(ids.length > 0){
                url += "&zghs="+ids.toString();
            }*/
            url = addSuperSearchParams(url);//���ø߼���ѯ����
            jQuery("form").eq(0).attr("action", url);
            jQuery("form").eq(0).submit();
        }
        function jsxxdy(){
            var ids = jQuery("#dataTable").getSeletIds();
            var len = ids.length;
            if(len == 0){
                showAlertDivLayer("���ȹ�ѡ���ݣ��ٽ��е���������");
                return false;
            }
            var url = "xsxx_hzdc.do?method=xsxxDcPrepare&zghs="+ids+"&type=tea";
            showDialog("��ʦ��Ϣ����", 690, 500, url);
        }
    </script>
</head>
<body>
<html:form action="/general_szdw">
    <%@ include file="/comm/hiddenValue.jsp"%>

    <div class="toolbox">
        <!-- ��ť -->
        <div class="buttonbox">
            <ul>
                <li>
                    <a href="#" class="btn_zj"
                       onclick="createDwwhDiv('insert');return false;"> ���� </a>
                </li>
                <li>
                    <a href="#" class="btn_xg"
                       onclick="checkDwwhUpdate();return false;"> �޸� </a>
                </li>
                <li>
                    <a href="#" class="btn_sc" onclick="deleteDwwh();return false;">
                        ɾ�� </a>
                </li>
                <li>
                    <a href="#" class="btn_ck" onclick="view();return false;">
                        �鿴 </a>
                </li>
                <li>
                    <a href="#" id="btn_dr"
                       onclick="toImportData('IMPORT_N100108'); return false;"
                       class="btn_dr">����</a>
                </li>
                <li>
                    <a href="#" class="btn_dc" onclick="configureExportData();return false;">����</a>
                </li>
                <li>
                    <a href="#" onclick="jsxxdy();return false;" class="btn_dy">��ʦ��Ϣ��ӡ</a>
                </li>
            </ul>
        </div>
        <!-- �������� -->
        <%@ include file="/comm/search/superSearchArea.jsp"%>
        <!-- �������� end-->
    </div>
</html:form>

<div class="main_box">
    <h3 class=datetitle_01>
        <span>��ʦ��Ϣ�б�</span>
    </h3>
    <div class="con_overlfow">
        <table id="dataTable" ></table>
        <div id="pager"></div>
    </div>
</div>
</body>
</html>
