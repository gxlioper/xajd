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
                caption : "¥����Ϣ�б�",
                pager : "pager",
                url : "gygl_gybz_wh.do?method=ktsqList&type=query",
                colList : [
                    {label:'sqid',name:'sqid',index :'sqid',key:true,hidden:true,width:'10%'},
                    {label:'ѧ��',name:'xn',index:'xn',width:'10%',formatter:function(val,row){
                        return "<a href='javascript:void(0);' onclick='view(\""+row["sqid"]+"\")'>"+val+"</a>";
                    }},
                    {label:'¥������',name:'ldmc',index:'ldmc',width:'10%'},
                    {label:'���Һ�',name:'qsh',index:'qsh',width:'10%'},
                    {label:'��������',name:'rs',index:'rs',width:'5%'},
                    {label:'���᳤',name:'sszmc',index:'sszmc',width:'5%'},
                    {label:'������',name:'xm',index:'xm',width:'5%'},
                    {label:'����ʱ��',name:'sqsj',index:'sqsj',width:'10%'},
                    {label:'���״̬',name:'shztmc',index:'shztmc',width:'10%'},
                    {label:'shzt',name:'shzt',index:'shzt',width:'10%',hidden:true},
                    {label:'splc',name:'splc',index:'splc',width:'10%',hidden:true}
                ],
                sortname: "xn,qsh",
                sortorder: "asc"
            }
            var map = getSuperSearch();
            gridSetting["params"] = map;
            jQuery("#dataTable").initGrid(gridSetting);
        });
        function searchRs() {
            var map = getSuperSearch();
            jQuery("#dataTable").reloadGrid(map);
        }
        function add(){
            showDialog("����",700,550,"gygl_gybz_wh.do?method=add");
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
            showDialog("�޸�",700,550,"gygl_gybz_wh.do?method=update&sqid="+rows[0]["sqid"]);
        }
        function view(sqid){
            var url="gygl_gybz_wh.do?method=view&sqid="+sqid;
            showDialog("�鿴", 700, 600, url);
        }
        function del() {
            var ids = jQuery("#dataTable").getSeletIds();
            if(ids == 0){
                showAlertDivLayer("��ѡ��һ����Ҫɾ���ļ�¼��");
                return false;
            }
            var rows = jQuery("#dataTable").getSeletRow();
            var arr = [];
            for(var i=0;i<rows.length;i++){
                if(rows[i]["shzt"] != '0' && rows[i]["shzt"] != '3'){
                    showAlertDivLayer("��������ݲ���ɾ������ȷ�ϣ�");
                    return false;
                }
                var key = rows[i]["lddm"]+"@!!"+rows[i]["qsh"]+"@!!"+rows[i]["xn"];
                arr.push(key);
            }
            confirmInfo("��ȷ��Ҫɾ��"+ids.length +"����¼��?",function(ty){
                if(ty=="ok"){
                    jQuery.post("gygl_gybz_wh.do?method=del",{values:ids.toString(),keys:arr.toString()},function(data){
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
                var url = "gygl_gybz_wh.do?method=submit";
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
                    jQuery.post("gygl_gybz_wh.do?method=cancel",
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
<html:form action="/gyglnew_wmqsxsmd_12688">
    <%@ include file="/comm/hiddenValue.jsp"%>
    <div class="toolbox">
        <!-- ��ť -->
        <div class="buttonbox">
            <ul>
                <li><a href="javascript:void(0);" onclick="add();" class="btn_xg">����</a></li>
                <li><a href="javascript:void(0);" onclick="update()" class="btn_xg">�޸�</a></li>
                <li><a href="javascript:void(0);" onclick="del()" class="btn_xg">ɾ��</a></li>
                <li><a href="javascript:void(0);" onclick="submit();return false;" class="btn_shuc">�ύ</a></li>
                <li><a href="javascript:void(0);" onclick="cancel();return false;" class="btn_sr">����</a></li>
                <li><a href="javascript:void(0);" onclick="Lcinfo();return false;" class="btn_cs">���̸���</a></li>
            </ul>
        </div>
        <!-- �������� -->
        <%@ include file="/comm/search/superSearchArea.jsp"%>
        <!-- �������� end-->
    </div>
</html:form>

<div class="main_box">
    <h3 class=datetitle_01>
        <span>��������ѧ������</span>
    </h3>
    <div class="con_overlfow">
        <table id="dataTable" ></table>
        <div id="pager"></div>
    </div>
</div>
</body>
</html>
