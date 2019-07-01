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
            caption:"��λ��������б�",
            pager:"pager",
            url:"qgzx_gwglnew.do?method=gwfbshCx&type=query",
            colList:[
                {label:'key',name:'gwdm', index: 'gwdm',key:true,hidden:true},
                {label:'��λ����',name:'gwmc', index: 'gwmc',formatter:function(cell,rowObject){
                    return "<a href='javascript:void(0);' class='name' onclick='view(\""
                        + rowObject["gwdm"] + "\",\""+ rowObject["yrdwid"] +"\");'>" + cell
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
                {label:'���id',name:'shid', index: 'shid',width:'11%',hidden:true},
                {label:'gwid',name:'gwid', index: 'gwid',width:'11%',hidden:true},
                {label:'splc',name:'splc', index: 'splc',width:'11%',hidden:true},
                {label:'yrdwid',name:'yrdwid', index: 'yrdwid',width:'11%',hidden:true},
                {label:'shzt',name:'shzt', index: 'shzt',hidden:true}
            ],
            sortname: "fbsj",
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

        function view(gwdm,yrdwid){
            var url="qgzx_gwglnew.do?method=gwfbshCk&gwdm="+gwdm+"&yrdwid="+yrdwid;
            showDialog("��λ����鿴", 800, 400, url);
        }
        function sh(){
            var rows = jQuery("#dataTable").getSeletRow();
            if(rows.length == 0){
                showAlertDivLayer("������ѡ��һ����¼��");
                return false;
            } else if(rows.length == 1){
                var url = "qgzx_gwglnew.do?method=gwfbsh&gwdm="+rows[0]["gwdm"]+"&yrdwid="+rows[0]["yrdwid"]+"&gwid="+rows[0]["gwid"]+"&shid="+rows[0]["shid"];
                showDialog("��λ�������",750,600,url);
            } else {
                showDialog("��λ�����������",500,300,"qgzx_gwglnew.do?method=plsh");
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
                var sqid = rows[0]["gwdm"];
                var shzt = rows[0]["shzt"];
                showConfirmDivLayer("��ȷ��Ҫ�����Ըü�¼����˲�����",{"okFun":function(){
                    jQuery.post("comm_spl.do?method=cxshnew",{shlc:splc,shid:shid},function(data){
                        // �ж��Ƿ����һ������(1:���һ�������ɹ���
                        if("1" == data["cancelFlg"]){
                            jQuery.post("qgzx_gwglnew.do?method=cancelGwSh",{sqid:sqid,shzt:shzt},function(result){
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
                showDialog("�������̸���",480,380,'comm_spl.do?method=lcgz&sqid='+rows[0]['gwdm']+"&splc="+rows[0]['splc']);
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
            var yrdwids = new Array();
            var splcids =  new Array();
            jQuery.each(rows, function(i, row) {
                guid.push(row["gwdm"]);
                gwid.push(row["gwid"]);
                yrdwids.push(row["yrdwid"]);
                splcids.push(row["splc"]);
            });
            jQuery.post("qgzx_gwglnew.do?method=plsh&type=save", {
                shzt : shzt,
                splcids : splcids,
                id : guid,
                gwids : gwid,
                sqrs : yrdwids,
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

<html:form action="/qgzx_gwglnew" method="post">
    <input type="hidden" value="dsh" id="shzt"/>
    <!-- ������ -->
    <%@ include file="/comm/hiddenValue.jsp"%>
    <!-- ������ -->
    <div class="toolbox" id="dgncz">
        <!-- ��ť -->
        <div class="buttonbox">
            <ul>
                <logic:equal value="yes" name="writeAble">
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
                </logic:equal>

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
        <span> ��λ��������б� </span>
    </h3>
    <div class="con_overlfow">
        <table id="dataTable" ></table>
        <div id="pager"></div>
    </div>
</div>
</body>
</html>
