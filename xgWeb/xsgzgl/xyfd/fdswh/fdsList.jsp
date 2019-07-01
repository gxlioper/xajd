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
                caption:"",
                pager:"pager",
                url:"xyfd_fdswh.do?method=fdsList&type=query",
                colList:[
                    {label:'id',name:'id', index: 'id',key:true,hidden:true },
                    {label:'����������',name:'fdsmc', index: 'fdsmc',width:'10%'},
                    {label:'�����ҵص�',name:'fdsdd', index: 'fdsdd',width:'20%'},
                    {label:'ʹ������',name:'syksrq', index: 'syksrq',width:'10%',formatter:function (cell,rowObject) {
                        return rowObject["syksrq"] + "-" + rowObject["syjsrq"];
                    }},
                    {label:'ʹ�ý�������',name:'syjsrq', index: 'syjsrq',width:'1%',hidden:true},
                    {label:'ʹ��ʱ��',name:'sykssj', index: 'sykssj',width:'10%',formatter:function(cell,rowObject){
                        return rowObject["sykssj"] + "-" + rowObject["syjssj"];
                    }},
                    {label:'ʹ�ý���ʱ��',name:'syjssj', index: 'syjssj',width:'1%',hidden:true},
                    {label:'�������',name:'qkms', index: 'qkms',width:'1%',hidden:true},
                    {label:'�������',name:'yxzt', index: 'yxzt',width:'10%',formatter:function(cell,rowObject){
                        if(rowObject["yxzt"]=="1"){
                            return "��������";
                        }else if(rowObject["yxzt"]=="2"){
                            return "ֹͣ����";
                        }else {
                            return rowObject["qkms"];
                        }
                    }}
                ]
            }
            gridSetting["params"]=getSuperSearch();
            jQuery("#dataTable").initGrid(gridSetting);
        });

        function searchRs(){
            var map = getSuperSearch();
            jQuery("#dataTable").reloadGrid(map);
        }

        var DCCLBH = "hdgl_hdgl_hdqd.do";//dcclbh,�������ܱ��

        //�Զ��嵼�� ����
        function exportConfig() {
            //DCCLBH�������ܱ��,ִ�е�������
            customExport(DCCLBH, ExportData);
        }

        // ��������
        function ExportData() {
            setSearchTj();//���ø߼���ѯ����
            var url = "hdgl_hdgl_hdqd_wh.do?method=export&dcclbh=" + DCCLBH;//dcclbh,�������ܱ��
            url = addSuperSearchParams(url);//���ø߼���ѯ����
            jQuery("form").eq(0).attr("action", url);
            jQuery("form").eq(0).submit();
        }
        function add(){
            showDialog("����������",700,350,"xyfd_fdswh.do?method=addfds");
        }
        function update(){
            var rows = jQuery("#dataTable").getSeletRow();
            if(rows.length!=1){
                showAlertDivLayer("��ѡ��һ����Ҫ�޸ĵļ�¼");
                return false;
            }

            showDialog("�޸Ļѧ��",700,350,"xyfd_fdswh.do?method=updatefds&id="+rows[0].id );

        }
        function deleteQd(){
            var rows = jQuery("#dataTable").getSeletRow();
            var qdxxs = new Array();
            if(rows.length<1){
                showAlertDivLayer("��ѡ����Ҫɾ���ļ�¼");
                return false;
            }
            for(var i=0;i<rows.length;i++){
                qdxxs.push(rows[i].xh+"_"+rows[i].hdid);
            }
            showConfirmDivLayer("�Ƿ�ȷ��ɾ����ѡ��"+rows.length+"����¼��", {
                "okFun" : function() {
                    var url = "hdgl_hdgl_hdqd_wh.do?method=deleteQd";
                    jQuery.post(url, {
                        qdxxlist : qdxxs.toString()
                    }, function(data) {
                        if (data["success"] == false) {
                            showAlertDivLayer(data["message"]);
                        } else {
                            showAlertDivLayer(data["message"], {}, {
                                "clkFun" : function(tag) {
                                    jQuery("#dataTable").reloadGrid();
                                }
                            });
                        }
                    }, 'json');

                }});
        }
    </script>
</head>
<body>
<div class="tab_cur">
    <p class="location">
        <em>���ĵ�ǰλ�ã�</em><a>${title }</a>
    </p>
</div>
<html:form action="/hdgl_hdgl_tj">
    <%@ include file="/comm/hiddenValue.jsp"%>
    <div class="toolbox">
        <!-- ��ť -->
        <div class="buttonbox">
            <ul>
                <li><a href="#" class="btn_zj" onclick="add();return false;">����</a></li>
                <li><a href="#" class="btn_xg" onclick="update();return false;">�޸�</a></li>
                <li><a href="#" class="btn_sc" onclick="deleteQd();return false;">ɾ��</a></li>
                <%--<li><a href="#" class="btn_dr" onclick="importConfig();return false;">����</a></li>--%>
                <%--<li><a href="#" class="btn_dc" onclick="exportConfig();return false;">����</a></li>--%>
                    <%--<li><a href="#" class="btn_dc" onclick="return false;">���ͱ�������</a></li>--%>
            </ul>
        </div>
        <!-- �������� -->
        <%@ include file="/comm/search/superSearchArea.jsp"%>
        <!-- �������� end-->
    </div>
</html:form>

<div class="main_box">
    <h3 class=datetitle_01>
        <span>�ǩ��&nbsp;&nbsp; </span>
    </h3>
    <div class="con_overlfow">
        <table id="dataTable" ></table>
        <div id="pager"></div>
    </div>
</div>
</body>
</html>
