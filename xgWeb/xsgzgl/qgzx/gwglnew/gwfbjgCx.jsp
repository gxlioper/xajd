<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <%@ include file="/syscommon/head.ini"%>
    <script type='text/javascript' src="js/uicomm.js"></script>
    <script type='text/javascript' src="js/String.js"></script>
    <script type='text/javascript' src="js/xgutil.js"></script>
    <script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
    <script>
        var gridSetting = {
            caption:"��λ����ά���б�",
            pager:"pager",
            url:"qgzx_gwglnew.do?method=gwfbjgCx&type=query",
            colList:[
                {label:'key',name:'gwdm', index: 'gwdm',key:true,hidden:true},
                {label:'��λ����',name:'gwmc', index: 'gwmc',formatter:function(cell,rowObject){
                    return "<a href='javascript:void(0);' class='name' onclick='view(\""
                        + rowObject["gwdm"] + "\");'>" + cell
                        + "</a>";
                }},
                {label:'���˵�λ',name:'yrdwmc', index: 'yrdwmc',width:'13%'},
                {label:'��λ���',name:'gwlbmc', index: 'gwlbmc',width:'10%'},
                {label:'��������',name:'gwxzdm', index: 'gwxzdm',width:'3%',formatter:function(cell,rowObject){
                    if(cell == '0'){
                        return "��ʱ";
                    } else if(cell == '1'){
                        return "��ʽ";
                    } else {
                        return cell;
                    }
                }},
                {label:'��λ���',name:'dwlb', index: 'dwlb',formatter:function(cell,rowObject){
                    if(cell == "01"){
                        return "У�ڵ�λ";
                    }
                    return "У����ҵ";
                }},
                {label:'��Ƹ����',name:'xqrs', index: 'xqrs',width:'7%'},
                {label:'��ֹʱ��',name:'zpjssj', index: 'zpjssj',width:'13%'},
                {label:'����ʱ��',name:'fbsj', index: 'fbsj',width:'11%'},
//                {label:'���״̬',name:'shztmc', index: 'shztmc',width:'11%'},
                {label:'���״̬',name:'shzt', index: 'shzt',width:'11%',hidden:true},
                {label:'��������',name:'splc', index: 'splc',width:'11%',hidden:true},
                {label:'������Դ',name:'sjly', index: 'sjly',width:'11%',formatter:function(cell,row){
                    if(cell == '1'){
                        return "�����������"
                    } else {
                        return "���������";
                    }
                }}
            ],
            sortname: "fbsj",
            sortorder: "desc"
        };
        //��ʼ��
        jQuery(document).ready(function(){
            var map = getSuperSearch();
            gridSetting["params"] = map;
            jQuery("#dataTable").initGrid(gridSetting);
        });

        function searchRs(){
            var map = getSuperSearch();
            jQuery("#dataTable").reloadGrid(map);
        }

        function yrdwwhExportConfig() {
            //DCCLBH�������ܱ��,ִ�е�������
            customExport("qgzx_gwgl_gwfb.do", yrdwwhExportData);
        }



        // ��������
        function yrdwwhExportData() {
            //setSearchTj();//���ø߼���ѯ����
            var url = "qgzx_gwglnew.do?method=gwExportData&dcclbh=" + "qgzx_gwgl_gwfb.do";//dcclbh,�������ܱ��
            //url = addSuperSearchParams(url);//���ø߼���ѯ����
            jQuery("form").eq(0).attr("action", url);
            jQuery("form").eq(0).submit();
        }


        function zjyrdw(){
            var url="qgzx_gwglnew.do?method=gwfbjgZj";
            showDialog("��λ��Ϣ����", 800, 660, url);
        }

        function xgyrdw(){
            var row = jQuery("#dataTable").getSeletRow();
            if(row.length != 1){
                showAlertDivLayer("��ѡ��һ����Ҫ�޸ĵ����ݣ�");
                return;
            }
            if(row[0]["sjly"] != '1'){
                showAlertDivLayer("�������Դ���ݲ����޸ģ�");
                return;
            }
            var url="qgzx_gwglnew.do?method=gwfbjgXg&gwdm="+row[0]["gwdm"];
            showDialog("��λ��Ϣ�޸�", 800, 600, url);
        }
        function view(id){
            var url="qgzx_gwglnew.do?method=gwfbjgCk&gwdm="+id;
            showDialog("���˵�λ�鿴", 800, 400, url);
        }

        function yrdwSc(){
            var ids = jQuery("#dataTable").getSeletIds();
            var rows = jQuery("#dataTable").getSeletRow();
            if(ids.length == 0){
                showAlertDivLayer("��ѡ��Ҫɾ�������ݣ�");
                return false;
            }
            for(var i=0;i<rows.length;i++){
                if(rows[i]["sjly"] != "1"){
                    showAlertDivLayer("ѡ�������а����������Դ���ݣ���ȷ�ϣ�");
                    return false;
                }
            }

            showConfirmDivLayer("��ȷ��Ҫɾ��ѡ��ļ�¼��",{"okFun":function(){
                jQuery.post("qgzx_gwglnew.do?method=gwSc",
                    {
                        values:ids.toString()
                    },function(data){
                        showAlertDivLayer(data["message"]);
                        jQuery("#dataTable").reloadGrid();
                    },'json');
            }});
        }
    </script>
</head>
<body>
<div class="tab_cur" >
    <p class="location">
        <em>���ĵ�ǰλ��:</em><a>${title }</a>
    </p>
    <p class="help">
        <a href="#" onclick="return false;" onmousedown ="showHelpDiv()">ʹ�ð���</a>
    </p>
</div>

<html:form action="/qgzx_gwglnew" method="post">
    <!-- ������ -->
    <%@ include file="/comm/hiddenValue.jsp"%>
    <!-- ������ -->
    <div class="toolbox" id="dgncz">
        <!-- ��ť -->
        <div class="buttonbox">
            <ul>
                <logic:equal value="yes" name="writeAble">
                    <li><a href="#" onclick="zjyrdw();return false;" class="btn_zj">��λ����</a></li>
                    <li><a href="#" onclick="xgyrdw();return false;" class="btn_xg">��λ�޸�</a></li>
                    <li><a href="#" onclick="yrdwSc();return false;" class="btn_sc">ɾ��</a></li>
                </logic:equal>

                <li><a href="#" onclick="yrdwwhExportConfig();return false;" class="btn_dc">����</a></li>
            </ul>
        </div>
        <!-- �������� -->
        <%@ include file="/comm/search/superSearchArea.jsp"%>
    </div>

</html:form>
<div class="formbox">
    <!--����start-->
    <h3 class="datetitle_01">
        <span> ��λ�����б� </span>
    </h3>

    <table id="dataTable" ></table>
    <div id="pager"></div>

</div>
</body>
</html>
