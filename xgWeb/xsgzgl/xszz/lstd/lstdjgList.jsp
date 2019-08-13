<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html>
<head>
    <title>��ɫͨ�����</title>
    <%@ include file="/syscommon/head.ini"%>
    <script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
    <script type="text/javascript" src="js/check.js"></script>
    <script type="text/javascript" src="xsgzgl/xszz/lstd/js/lstd.js"></script>
    <script type="text/javascript">
        function initGrid(){
            var gridSetting = {
                caption:"��ɫͨ������б�",
                pager:"pager",
                url:"xszz_lstd.do?method=lstdjgList&type=query",
                colList:[
                    {label:'key',name:'jgid', index: 'jgid',key:true ,hidden:true},
                    {label:'sqid',name:'sqid', index: 'sqid',hidden:true},
                    {label:'ѧ��',name:'xh', index: 'xh',width:'13%'},
                    {label:'ѧ��',name:'xn', index: 'xn',width:'8%'},
                    {label:'����',name:'xm', index: 'xm',width:'8%'},
                    {label:'�Ա�',name:'xb', index: 'xb',width:'5%'},
                    {label:'ѧԺ',name:'xymc', index: 'xydm',width:'13%'},
                    {label:'��Ժ',name:'symc', index: 'symc',width:'13%'},
                    {label:'�����༶',name:'bjmc', index: 'bjdm',width:'13%'},
                    {label:'רҵ�༶',name:'zybjmc', index: 'zybjmc',width:'13%'},
                    {label:'������Դ',name:'sjly', index: 'sjly',width:'13%',hidden:true}
                ],
                sortname: "xh",
                sortorder: "desc"
            };
            gridSetting["params"]=getSuperSearch();
            jQuery("#dataTable").initGrid(gridSetting);
        };
        function searchRs(){
            var map = getSuperSearch();
            jQuery("#dataTable").reloadGrid(map);
        }
        function lstdjgzj(){
            showDialog('��ɫͨ���������',780,450,'xszz_lstd.do?method=lstdjgZj');
        }
        function lstdjgDelete() {
            var ids = jQuery("#dataTable").getSeletIds();
            if (ids.length == 0){
                showAlertDivLayer("��ѡ����Ҫɾ���������¼��");
            } else {
                var rows = jQuery("#dataTable").getSeletRow();

                for(var i=0;i<ids.length;i++){
                    if(rows[i]['sjly']=='1'){
                        showAlertDivLayer("��������ݲ���ɾ����");
                        return false;
                    }
                }

                showConfirmDivLayer("��ȷ��Ҫɾ����������",{"okFun":function(){
                    jQuery.post("xszz_lstd.do?method=delLstdjg",{values:ids.toString()},function(data){
                        showAlertDivLayer(data["message"]);
                        jQuery("#dataTable").reloadGrid();
                    },'json');
                }});
            }
        }
        //����
        function exportConfig(){
            var DCCLBH='xszz_lstd_jg.do';
            customExport(DCCLBH, exportData);
        }

        function exportData(){
            var DCCLBH='xszz_lstd_jg.do';
            setSearchTj();//���ø߼���ѯ����
            var url = "xszz_lstd.do?method=export&dcclbh=" + DCCLBH;//dcclbh,�������ܱ��
            url = addSuperSearchParams(url);//���ø߼���ѯ����
            jQuery("form").eq(0).attr("action", url);
            jQuery("form").eq(0).submit();
        }
        jQuery(function(){
            initGrid();
        })
    </script>
</head>
<body>
<html:form action="/xszz_lstd">
    <%@ include file="/comm/hiddenValue.jsp"%>
    <div class="toolbox">
        <!-- ��ť -->
        <div class="buttonbox">
            <ul>
                <li>
                    <a href="javascript:void(0);" onclick="lstdjgzj();return false;"
                       class="btn_zj">����</a>
                </li>
                <li>
                    <a href="javascript:void(0);" onclick="lstdjgDelete();return false;"
                       class="btn_sc">ɾ��</a>
                </li>
                <%--<li>
                    <a href="javascript:void(0);" onclick="return false;"
                       class="btn_dr">����</a>
                </li>--%>
                <li>
                    <a href="javascript:void(0);" onclick="exportConfig();return false;"
                       class="btn_dc">����</a>
                </li>
                <li>
                    <a href="javascript:void(0);" onclick="print();return false;"
                    class="btn_dy">��ӡ</a>
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
        <span>��ɫͨ������б�&nbsp;&nbsp; </span>
    </h3>
    <div class="con_overlfow">
        <table id="dataTable" ></table>
        <div id="pager"></div>
    </div>
</div>
</body>
</html>
