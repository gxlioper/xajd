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
    <script type="text/javascript" src="js/calendar/calendar.js"></script>
    <script>
        var gridSetting = {
            caption:"",
            pager:"pager",
            url:"gygl_gybz_wh.do?method=ktshList&type=query",
            colList:[
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
                {label:'���id',name:'shid', index: 'shid',width:'11%',hidden:true},
                {label:'gwid',name:'gwid', index: 'gwid',width:'11%',hidden:true},
                {label:'splc',name:'splc', index: 'splc',width:'11%',hidden:true},
                {label:'xh',name:'xh', index: 'xh',width:'11%',hidden:true},
                {label:'shztx',name:'shztx', index: 'shztx',hidden:true}
            ],
            sortname: "xn,sqsj",
            sortorder: "desc"
        };
        //��ʼ��
        jQuery(document).ready(function(){
            var map = getSuperSearch();
            map["shzt"] = "dsh";
            gridSetting["params"] = map;
            jQuery("#dataTable").initGrid(gridSetting);
        });

        function searchRs(){
            var map = getSuperSearch();
            var shzt = jQuery("#shzt").val();
            if (shzt != ""){
                map["shzt"] = shzt;
            }
            jQuery("#dataTable").reloadGrid(map);
        }

        function view(sqid){
            var url="gygl_gybz_wh.do?method=view&sqid="+sqid;
            showDialog("��λ����鿴", 700, 600, url);
        }
        function sh(){
            var rows = jQuery("#dataTable").getSeletRow();
            if(rows.length == 0){
                showAlertDivLayer("������ѡ��һ����¼��");
                return false;
            } else if(rows.length == 1){
                var url = "gygl_gybz_wh.do?method=sh&sqid="+rows[0]["sqid"]+"&xh="+rows[0]["xh"]+"&gwid="+rows[0]["gwid"]+"&shid="+rows[0]["shid"];
                showDialog("ѧ������յ��������",750,600,url);
            } else {
                showDialog("ѧ������յ������������",500,300,"gygl_gybz_wh.do?method=plsh");
            }
        }
        //�������
        function cancelSh(){
            var rows = jQuery("#dataTable").getSeletRow();
            if (rows.length != 1){
                showAlertDivLayer("��ѡ��һ����Ҫ��������˼�¼��");
            } else {
                var splc = rows[0]["splc"];
                var shid = rows[0]["shid"];
                var sqid = rows[0]["sqid"];
                var shzt = rows[0]["shztx"];
                showConfirmDivLayer("��ȷ��Ҫ�����Ըü�¼����˲�����",{"okFun":function(){
                    jQuery.post("comm_spl.do?method=cxshnew",{shlc:splc,shid:shid},function(data){
                        // �ж��Ƿ����һ������(1:���һ�������ɹ���
                        if("1" == data["cancelFlg"]){
                            jQuery.post("gygl_gybz_wh.do?method=cancelSh",{sqid:sqid,shzt:shzt},function(result){
                                showAlertDivLayer(result["message"],{},{"clkFun":function(){
                                    jQuery("#dataTable").reloadGrid();
                                }});
                            },'json');
                        }else{
                            showAlertDivLayer(data["message"],{},{"clkFun":function(){
                                jQuery("#dataTable").reloadGrid();
                            }});
                        }
                    },'json');
                }});
            }
        }
        function splcInfo(){
            var ids = jQuery("#dataTable").getSeletIds();
            var rows = jQuery("#dataTable").getSeletRow();
            if (1!=ids.length){
                showAlertDivLayer("��ѡ��һ�����̸��ټ�¼��");
            } else {
                showDialog("�������̸���",480,380,'comm_spl.do?method=lcgz&sqid='+rows[0]['sqid']+"&splc="+rows[0]['splc']);
            }
        }
        function selectTab(obj,shzt){
            jQuery("#shzt").val(shzt);

            if (shzt == "dsh"){
//                jQuery("#dataTable").initGrid(gridSetting);
                jQuery("#li_sh").css("display","");
                jQuery("#li_qx").css("display","none");
            } else {
//                jQuery("#dataTable").initGrid(gridSetting2);
                jQuery("#li_sh").css("display","none");
                jQuery("#li_qx").css("display","");
            }

            jQuery(".ha").removeClass("ha");
            jQuery(obj).parent().addClass("ha");

            searchRs();
        }
        //�������
        function savePlsh(shzt, shyj) {
            var rows = jQuery("#dataTable").getSeletRow();
            var guid = new Array();
            var gwid = new Array();
            var sqrs = new Array();
            var splcs =  new Array();
            jQuery.each(rows, function(i, row) {
                guid.push(row["sqid"]);
                gwid.push(row["gwid"]);
                sqrs.push(row["xh"]);
                splcs.push(row["splc"]);
            });
            jQuery.post("gygl_gybz_wh.do?method=plsh&type=save", {
                shzt : shzt,
                splcs : splcs,
                sqids : guid,
                gwids : gwid,
                sqrs : sqrs,
                shyj : shyj,
            }, function(data) {

                showAlertDivLayer(data["message"], {}, {
                    "clkFun" : function() {
                        jQuery("#dataTable").reloadGrid();
                    }
                });
            }, 'json');
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

<html:form action="/gygl_gybz_wh" method="post">
    <input type="hidden" value="dsh" id="shzt"/>
    <!-- ������ -->
    <%@ include file="/comm/hiddenValue.jsp"%>
    <!-- ������ -->
    <div class="toolbox" id="dgncz">
        <!-- ��ť -->
        <div class="buttonbox">
            <ul>
                <li id="li_sh">
                    <a href="javascript:void(0);" onclick="sh();return false;"
                       title="ѡ����Ҫ��˵ļ�¼������ð�ť���Դ����ҳ�档"
                       class="btn_sh">���</a>
                </li>
                <li id="li_qx" style="display: none;">
                    <a href="javascript:void(0);" onclick="cancelSh();return false;"
                       title="ѡ��һ����¼������ð�ť�����Գ���ʧ�����˲�����"
                       class="btn_qxsh">����</a>
                </li>
                <li><a href="#" onclick="splcInfo();return false;" class="btn_cs">���̸���</a></li>

            </ul>
        </div>
        <!-- �������� -->
        <%@ include file="/comm/search/superSearchArea.jsp"%>
        <div class="comp_title" id="comp_title">
            <ul style="width:90%">
                <li class="ha"><a href="javascript:void(0);" onclick="selectTab(this,'dsh');"><span>������</span></a></li>
                <li><a href="javascript:void(0);" onclick="selectTab(this,'ysh');"><span>�Ѵ���</span></a></li>
            </ul>
        </div>
    </div>

</html:form>
<div class="main_box">
    <!--����start-->
    <h3 class="datetitle_01">
        <span> �յ���������б� </span>
    </h3>
    <div class="con_overlfow">
        <table id="dataTable" ></table>
        <div id="pager"></div>
    </div>
</div>
</body>
</html>
