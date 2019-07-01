<%@ page language="java" contentType="text/html; charset=GBK" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <%@ include file="/syscommon/head.ini" %>
    <script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
    <script type="text/javascript" src="js/calendar/calendar.js"></script>
    <script type="text/javascript">
        jQuery(function () {
            var gridSetting = {
                caption: "",
                pager: "pager",
                url: "general_szdw.do?method=szdwSzbb&type=query",
                colList: [
                    {label: 'pk', name: 'pk', index: 'pk', width: '10%',hidden:true},
                    {label: '�꼶', name: 'nj', index: 'nj', width: '10%'},
                    {label: '��Ժ', name: 'symc', index: 'symc', width: '10%'},
                    {label: '��������', name: 'bjdm', index: 'bjdm', width: '10%',hidden:true},
                    {label: '�����༶', name: 'bjmc', index: 'bjmc', width: '10%'},
                    {label: '�����༶����', name: 'rs', index: 'rs', width: '10%',formatter:function(cellVal,row){
                        return "<a href='#' onclick='xsxxView(\""+cellVal+"\",\""+row["bjdm"]+"\")'>"+cellVal+"</a>";
                    }},
                    {label: '����Ա', name: 'fdyxm', index: 'fdyxm', width: '10%'}
                ],
                sortname: "nj",
                sortorder: "asc",
                radioselect: true
            }
            var map = getSuperSearch();
            gridSetting["params"] = map;
            jQuery("#dataTable").initGrid(gridSetting);
        })

        function searchRs() {
            var map = getSuperSearch();
            jQuery("#dataTable").reloadGrid(map);
        }
        function fdybbSetting(){
            /*var isopen = jQuery("#isopen").val();
            if(isopen==null||isopen==''){
                showAlertDivLayer('��������δ��ʼ��������ϵ����Ա��');
                return false;
            }
            if ("false" == isopen){
                showAlertDivLayer("��ǰδ���ű�࣬����ϵ����Ա��");
                return false;
            }*/
            var rows = jQuery("#dataTable").getSeletRow();
            var nj="";
            var xydm="";
            var zydm="";
            var bjdm="";
            var sydm="";
            if(rows.length>1){

                alertInfo("����ѡ��һ���༶!");
                return false;
            }else if(rows.length==1){
                var pkArr=new Array();
                pkArr=rows[0]["pk"].split('!!luojw!!');
                nj=pkArr[0];
                xydm=pkArr[1];
                zydm=pkArr[2];
                bjdm=pkArr[3];
                sydm=pkArr[4];

                if(!sydm){
                    alertInfo("�ð༶δ������Ժ����ȷ�ϣ�");
                    return false;
                }
            }

            var url = "general_szdw.do?method=szbbSetting&fplx=fdy";
            url+="&nj="+nj;
            url+="&bjdm="+bjdm
            url+="&sydm="+sydm
            refreshForm(url);

        }
        // ѧ����Ϣ
        function xsxxView(n,bjdm){

            if(0 == n) {
                alertError("�༶����Ϊ0���޷��鿴���飡");
                return false;
            }
            showDialog("ѧ����ϸ��Ϣ",900,500,"general_szdw.do?method=xsxxList&bjdm="+bjdm+"&bbType=fdy");
        }
        function szdwbbExportConfig() {
            customExport("general_szdw.do", szdwbbExportData);
        }

        // ��������
        function szdwbbExportData() {
            setSearchTj();//���ø߼���ѯ����
            var url = "szdw_szbb.do?method=szdwbbExportData&dcclbh=" + "general_szdw.do";//dcclbh,�������ܱ��
            url = addSuperSearchParams(url);//���ø߼���ѯ����
            jQuery("form").eq(0).attr("action", url);
            jQuery("form").eq(0).submit();
        }
    </script>
</head>

<body>
<html:form action="/ztbhgl_ztbhjg">
    <%@ include file="/comm/hiddenValue.jsp" %>
    <div class="toolbox">
        <!-- ��ť -->
        <div class="buttonbox">
            <ul>
                <li>
                    <a href="#" class="btn_sr" onclick="fdybbSetting();return false;">
                        ���Ӹ���Ա
                    </a>
                </li>
                <li><a href="#" class="btn_dc" onclick="szdwbbExportConfig();return false;">����</a></li>
            </ul>
        </div>
        <!-- �������� -->
        <%@ include file="/comm/search/superSearchArea.jsp" %>
        <!-- �������� end-->
    </div>
</html:form>

<div class="main_box">
    <h3 class=datetitle_01>
        <span>��Ա&nbsp;&nbsp;</span>
    </h3>
    <div class="con_overlfow">
        <table id="dataTable"></table>
        <div id="pager"></div>
    </div>
</div>
</body>
</html>
