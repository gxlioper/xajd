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
            url:"qgzx_gwglnew.do?method=gwfbCx&type=query",
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
                {label:'���״̬',name:'shztmc', index: 'shztmc',width:'11%'},
                {label:'���״̬',name:'shzt', index: 'shzt',width:'11%',hidden:true},
                {label:'��������',name:'splc', index: 'splc',width:'11%',hidden:true}
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
            var url = "qgzx_gwglnew.do?method=hmdyz";
            jQuery.post(url,{},function(data){
                if(data["message"] == "success"){//���Ǻ�������λ
                    var url="qgzx_gwglnew.do?method=gwfbZj";
                    showDialog("��λ��Ϣ����", 800, 600, url);
                } else {
                    showAlertDivLayer(data["message"]);
                }
            },'json')
        }

        function xgyrdw(){
            var row = jQuery("#dataTable").getSeletRow();
            if(row.length != 1){
                showAlertDivLayer("��ѡ��һ����Ҫ�޸ĵ����ݣ�");
                return;
            }
            if(row[0]["shzt"] != '0' && row[0]["shzt"] != '3'){
                showAlertDivLayer("������Լ���������ݲ����޸ģ�");
                return;
            }
            var url="qgzx_gwglnew.do?method=gwfbXg&gwdm="+row[0]["gwdm"];
            showDialog("��λ��Ϣ�޸�", 800, 600, url);
        }
        function view(id){
            var url="qgzx_gwglnew.do?method=gwfbCk&gwdm="+id;
            showDialog("���˵�λ�鿴", 800, 400, url);
        }
        function submit(){
            var ids = jQuery("#dataTable").getSeletIds();
            if (ids.length != 1){
                showAlertDivLayer("��ѡ��һ����Ҫ�ύ�ļ�¼��");
            }else{
                var rows = jQuery("#dataTable").getSeletRow();
                var url = "qgzx_gwglnew.do?method=submitGwfb";
                for(var i=0;i<ids.length;i++){
                    if(rows[i]['shzt']!='0' && rows[i]['shzt']!='3' ){
                        showAlertDivLayer(jQuery("#lable_wjt_yth_tj").val());
                        return false;
                    }
                }
                showConfirmDivLayer("��ȷ��Ҫ�ύѡ��ļ�¼��",{"okFun":function(){
                    jQuery.post(url,
                        {
                            values:ids.toString(),
                            splc : rows[0]['splc'],
                            shzt : rows[0]['shzt']
                        },function(data){
                            showAlertDivLayer(data["message"]);
                            jQuery("#dataTable").reloadGrid();
                        },'json');
                }});
            }
        }
        function cancel(){
            var ids = jQuery("#dataTable").getSeletIds();
            if (ids.length == 0) {
                showAlertDivLayer("��ѡ����Ҫ�����ļ�¼��");
            } else if (ids.length >1 ) {
                showAlertDivLayer("��ѡ��һ����Ҫ�����ļ�¼��");
            } else {
                var rows = jQuery("#dataTable").getSeletRow();
                for(var i=0;i<ids.length;i++){
                    if(rows[i]['shzt']!='5'){
                        showAlertDivLayer("ֻ������еļ�¼���ܱ�������");
                        return false;
                    }
                }
                showConfirmDivLayer("��ȷ��Ҫ����ѡ��ļ�¼��",{"okFun":function(){
                    jQuery.post("qgzx_gwglnew.do?method=cancelGwfb",
                        {
                            values:ids.toString(),
                            splc : rows[0]['splc']
                        },function(data){
                            showAlertDivLayer(data["message"]);
                            jQuery("#dataTable").reloadGrid();
                        },'json');
                }});
            }
        }
        function Lcinfo(){
            var ids = jQuery("#dataTable").getSeletIds();
            var rows = jQuery("#dataTable").getSeletRow();
            if (ids.length != 1){
                showAlertDivLayer("��ѡ��һ�����̸��ټ�¼��");
            } else {
                var shzt = rows[0]["shzt"];
                if ("0" == shzt){
                    showAlertDivLayer(jQuery("#lable_wxglcxx").val());
                    return false;
                }
                showDialog("��ɫͨ���������̸���",480,380,'comm_spl.do?method=lcgz&sqid='+rows[0]['gwdm']+"&splc="+rows[0]['splc']);
            }
        }
        function yrdwSc(){
            var ids = jQuery("#dataTable").getSeletIds();
            var rows = jQuery("#dataTable").getSeletRow();
            if(ids.length == 0){
                showAlertDivLayer("��ѡ��Ҫɾ�������ݣ�");
                return false;
            }
            for(var i=0;i<rows.length;i++){
                if(rows[i]["shzt"] != "0" && rows[i]["shzt"] != "3"){
                    showAlertDivLayer("������ݲ���ɾ����");
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
                <logic:equal value="1" name="sfkfsq">
                    <li><a href="#" onclick="zjyrdw();return false;" class="btn_zj">��λ����</a></li>
                    <li><a href="#" onclick="xgyrdw();return false;" class="btn_xg">��λ�޸�</a></li>
                    <li><a href="#" onclick="yrdwSc();return false;" class="btn_sc">ɾ��</a></li>
                    <li><a href="#" onclick="submit();return false;" class="btn_shuc">�ύ</a></li>
                    <li><a href="#" onclick="cancel();return false;" class="btn_sr">����</a></li>
                    <li><a href="#" onclick="Lcinfo();return false;" class="btn_cs">���̸���</a></li>
                    <%--<li><a href="#" onclick="mmcsh();return false;" class="btn_dr">����</a></li>--%>
                </logic:equal>
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
