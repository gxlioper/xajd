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
                url:"szdw_fdy_ywxxypx.do?method=ywxxypxSqList&type=query",
                colList:[
                    {label:'sqid',name:'sqid', index: 'sqid',width:'10%',key:true,hidden:true},
                    {label:'ְ����',name:'zgh', index: 'zgh',width:'10%',formatter:function(cell,row){
                        return "<a href='javascript:void(0);' onclick='view(\""+row["sqid"]+"\")'>"+cell+"</a>";
                    }},
                    {label:'����',name:'xm', index: 'xm',width:'10%'},
                    {label:'�Ա�',name:'xb', index: 'xb',width:'10%'},
                    {label:'������Ժ',name:'symc', index: 'symc',width:'10%'},
                    {label:'����ѧԺ',name:'bmmc', index: 'bmmc',width:'10%'},
                    {label:'��ѵ����',name:'pxmc', index: 'pxmc',width:'10%'},
                    {label:'��ѵʱ��',name:'pxsj', index: 'pxsj',width:'10%'},
                    {label:'��֯����',name:'zzbmmc', index: 'zzbmmc',width:'10%'},
                    {label:'ѧʱ',name:'xs', index: 'xs',width:'10%'},
                    {label:'״̬',name:'shztmc', index: 'shztmc',width:'10%'},
                    {label:'shzt',name:'shzt', index: 'shzt',width:'10%',hidden:true},
                    {label:'splc',name:'splc', index: 'splc',width:'10%',hidden:true}
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
            showDialog("������ѵ����",700,550,"szdw_fdy_ywxxypx.do?method=add");
        }
        function update(){
            var rows = jQuery("#dataTable").getSeletRow();
            if(rows.length != 1){
                showAlertDivLayer("��ѡ��һ����Ҫ�޸ĵļ�¼��");
                return false;
            }
            if(rows[0]["shzt"] != '0' && rows[0]["shzt"] != '3'){
                showAlertDivLayer("��������ݲ����޸ģ�");
                return;
            }
            showDialog("�޸���ѵ����",700,550,"szdw_fdy_ywxxypx.do?method=update&sqid="+rows[0]["sqid"]);
        }
        function view(val){
            showDialog("��ѵ�����鿴",700,550,"szdw_fdy_ywxxypx.do?method=view&sqid="+val);
        }
        function del() {
            var ids = jQuery("#dataTable").getSeletIds();
            if(ids == 0){
                showAlertDivLayer("��ѡ��һ����Ҫɾ���ļ�¼��");
                return false;
            }
            var rows = jQuery("#dataTable").getSeletRow();
            for(var i=0;i<rows.length;i++){
                if(rows[i]["shzt"] != '0' && rows[i]["shzt"] != '3'){
                    showAlertDivLayer("��������ݲ���ɾ������ȷ�ϣ�");
                    return false;
                }
            }
            confirmInfo("��ȷ��Ҫɾ��"+ids.length +"����¼��?",function(ty){
                if(ty=="ok"){
                    jQuery.post("szdw_fdy_ywxxypx.do?method=del",{values:ids.toString()},function(data){
                        alertInfo(data["message"]);
                        jQuery("#dataTable").reloadGrid();
                    },'json');
                }
            });
        }
        function submit(){
            var ids = jQuery("#dataTable").getSeletIds();
            if (ids.length != 1){
                showAlertDivLayer("��ѡ��һ����Ҫ�ύ�ļ�¼��");
            }else{
                var rows = jQuery("#dataTable").getSeletRow();
                var url = "szdw_fdy_ywxxypx.do?method=submit";
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
                    jQuery.post("szdw_fdy_ywxxypx.do?method=cancel",
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
                showDialog("��ɫͨ���������̸���",480,380,'comm_spl.do?method=lcgz&sqid='+rows[0]['sqid']+"&splc="+rows[0]['splc']);
            }
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
                <li><a href="javascript:void(0);" onclick="submit();return false;" class="btn_shuc">�ύ</a></li>
                <li><a href="javascript:void(0);" onclick="cancel();return false;" class="btn_sr">����</a></li>
                <li><a href="javascript:void(0);" onclick="Lcinfo();return false;" class="btn_cs">���̸���</a></li>
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
