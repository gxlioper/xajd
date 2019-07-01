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
                caption:"�ҷü�¼�б�",
                pager:"pager",
                url:"szdw_jfxxwh.do?method=jfjlList&type=query",
                colList:[
                    {label:'jgid',name:'jgid', index: 'jgid',width:'10%',key:true,hidden:true},
                    {label:'ѧ��',name:'xh', index: 'xh',width:'10%',formatter:function(cell,row){
                        return "<a href='javascript:void(0);' onclick='view(\""+row["jgid"]+"\")'>"+cell+"</a>";
                    }},
                    {label:'����',name:'xm', index: 'xm',width:'10%'},
                    {label:'�Ա�',name:'xb', index: 'xb',width:'10%'},
                    {label:'��Ժ',name:'symc', index: 'symc',width:'10%'},
                    {label:'ѧԺ',name:'xymc', index: 'xymc',width:'10%'},
                    {label:'�����༶',name:'bjmc', index: 'bjmc',width:'10%'},
                    {label:'רҵ�༶',name:'zybjmc', index: 'zybjmc',width:'10%'},
                    {label:'�ҷ�����',name:'jfxzmc', index: 'jfxzmc',width:'10%'},
                    {label:'�ҷ�ʱ��',name:'jfsj', index: 'jfsj',width:'10%'},
                    {label:'����Ա',name:'fdyxm', index: 'fdyxm',width:'10%'},
                    {label:'�ص��ע',name:'zdgz', index: 'zdgz',width:'10%'}

                ],
                sortname: "xh",
                sortorder: "asc"
            }
            jQuery("#dataTable").initGrid(gridSetting);
        });
        function searchRs(){
            var map = getSuperSearch();
            jQuery("#dataTable").reloadGrid(map);
        }
        function add(){
            showDialog("���Ӽҷü�¼",700,550,"szdw_jfxx.do?method=add");
        }
        function update(){
            var rows = jQuery("#dataTable").getSeletRow();
            if(rows.length != 1){
                showAlertDivLayer("��ѡ��һ����Ҫ�޸ĵļ�¼��");
                return false;
            }
            showDialog("���Ӽҷü�¼",700,550,"szdw_jfxx.do?method=update&jgid="+rows[0]["jgid"]);
        }
        function view(val){
            showDialog("�ҷü�¼�鿴",700,550,"szdw_jfxx.do?method=view&jgid="+val);
        }
        function del() {
            var ids = jQuery("#dataTable").getSeletIds();
            if(ids == 0){
                showAlertDivLayer("��ѡ��һ����Ҫɾ���ļ�¼��");
                return false;
            }
            confirmInfo("��ȷ��Ҫɾ��"+ids.length +"����¼��?",function(ty){
                if(ty=="ok"){
                    jQuery.post("szdw_jfxx.do?method=del",{values:ids.toString()},function(data){
                        alertInfo(data["message"]);
                        jQuery("#dataTable").reloadGrid();
                    },'json');
                }
            });
        }
        //����
        function importConfig(){
            toImportDataNew("IMPORT_XSJFJL");
            return false;
        }

        //dcglbh,�������ܱ��
        var DCGLBH = "szdw_jfxxwh.do";

        //�Զ��嵼�� ����
        function exportConfig() {
            //DCCLBH�������ܱ��,ִ�е�������
            customExport(DCGLBH, exprotData);
        }

        //��������
        function exprotData() {
            setSearchTj();//���ø߼���ѯ����
            var url = "szdw_jfxx.do?method=exportData&dcglbh=" + DCGLBH;//dcglbh,�������ܱ��
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
                    <li><a href="javascript:void(0);" class="btn_dc" onclick="exportConfig();return false;">����</a></li>
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
        <span>�ҷü�¼�б�</span>
    </h3>

    <table id="dataTable" ></table>
    <div id="pager"></div>

</div>
</body>
</html>
