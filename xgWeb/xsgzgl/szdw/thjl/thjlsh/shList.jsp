<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <%@ include file="/syscommon/head.ini"%>
    <script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
    <script type="text/javascript" src="js/calendar/calendar.js"></script>
    <script type='text/javascript' src='dwr/engine.js'></script>
    <script type='text/javascript' src='dwr/interface/exportData.js'></script>
    <%--<script type="text/javascript" src='xsgzgl/szdw/thjl/js/thjlManage.js'></script>--%>
    <script type="text/javascript">
        jQuery(function(){
            var gridSetting = {
                caption : "̸����¼�б�",
                pager : "pager",
                url : "szdw_thjl_sh.do?method=shList&type=query",
                colList : [
                    {label : 'sqid',name : 'sqid',index : 'sqid',hidden : true,key : true},
                    {label : 'ѧ��',name : 'xh',index : 'xh'},
                    {label : '����',name : 'xm',index : 'xm'},
                    {label : '�Ա�',name : 'xb',index : 'xb'},
                    {label : '�꼶',name : 'nj',index : 'nj'},
                    {label : 'ѧԺ',name : 'xymc',index : 'xymc'},
                    {label : '��Ժ',name : 'symc',index : 'symc'},
                    {label : 'רҵ',name : 'zymc',index : 'zymc'},
                    {label : '�����༶',name : 'bjmc',index : 'bjmc'},
                    {label : 'רҵ�༶',name : 'zybjmc',index : 'zybjmc'},
                    {label : '̸������',name : 'thsj',index : 'thsj'},
                    {label : '̸����ʦ',name : 'jsxm',index : 'jsxm'},
                    {label : '���״̬',name : 'shztmc',index : 'shztmc'},
                    {label:'���id',name:'shid', index: 'shid',width:'11%',hidden:true},
                    {label:'gwid',name:'gwid', index: 'gwid',width:'11%',hidden:true},
                    {label:'shzt',name:'shztx', index: 'shztx',width:'10%',hidden:true},
                    {label:'splc',name:'splc', index: 'splc',width:'10%',hidden:true}
                ],

                sortname : "",
                sortorder : ""
            };
            var map = getSuperSearch();
            map["shzt"] = "dsh";
            gridSetting["params"] = map;
            jQuery("#dataTable").initGrid(gridSetting);
        })
        function searchRs(){
            var map = getSuperSearch();
            var shzt = jQuery("#shzt").val();
            if (shzt != ""){
                map["shzt"] = shzt;
            }
            jQuery("#dataTable").reloadGrid(map);
        }
        function selectTab(obj,shzt){
            jQuery("#shzt").val(shzt);

            if (shzt == "dsh"){
                jQuery("#li_sh").css("display","");
                jQuery("#li_qx").css("display","none");
            } else {
                jQuery("#li_sh").css("display","none");
                jQuery("#li_qx").css("display","");
            }

            jQuery(".ha").removeClass("ha");
            jQuery(obj).parent().addClass("ha");

            searchRs();
        }

        function sh(){
            var rows = jQuery("#dataTable").getSeletRow();
            if(rows.length == 0){
                showAlertDivLayer("������ѡ��һ����¼��");
                return false;
            } else if(rows.length == 1){
                var url = "szdw_thjl_sh.do?method=sh&sqid="+rows[0]["sqid"]+"&gwid="+rows[0]["gwid"]+"&shid="+rows[0]["shid"];
                showDialog("��λ�������",750,550,url);
            } else {
                showDialog("��λ�����������",500,300,"szdw_thjl_sh.do?method=plsh");
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
                var shzt = rows[0]["shzt"];
                showConfirmDivLayer("��ȷ��Ҫ�����Ըü�¼����˲�����",{"okFun":function(){
                    jQuery.post("comm_spl.do?method=cxshnew",{shlc:splc,shid:shid},function(data){
                        // �ж��Ƿ����һ������(1:���һ�������ɹ���
                        if("1" == data["cancelFlg"]){
                            jQuery.post("szdw_thjl_sh.do?method=cancelSh",{sqid:sqid,shzt:shzt},function(result){
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
        //�������
        function savePlsh(shzt, shyj) {
            var rows = jQuery("#dataTable").getSeletRow();
            var sqids = new Array();
            var gwids = new Array();
            var sqrs = new Array();
            var splcids =  new Array();
            jQuery.each(rows, function(i, row) {
                sqids.push(row["sqid"]);
                gwids.push(row["gwid"]);
                sqrs.push(row["xh"]);
                splcids.push(row["splc"]);
            });
            jQuery.post("szdw_thjl_sh.do?method=plsh&type=save", {
                shzt : shzt,
                splcids : splcids,
                ids : sqids,
                gwids : gwids,
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
<div class="tab_cur">
    <p class="location">
        <em>���ĵ�ǰλ�ã�</em><a>${title }</a>
    </p>
</div>
<html:form action="/szdw_thjl" styleId="form">
    <%@ include file="/comm/hiddenValue.jsp"%>
    <input type="hidden" value="dsh" id="shzt"/>
    <!-- ������ -->
    <input type="hidden" id="searchTjstr" value="${searchTjstr}"/>
    <div class="toolbox">
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
            <%--</logic:equal>--%>
        <!-- �������� -->
        <%@ include file="/comm/search/superSearchArea.jsp"%>
        <!-- �������� end-->
        <div class="comp_title" id="comp_title">
            <ul style="width:90%">
                <li class="ha"><a href="javascript:void(0);" onclick="selectTab(this,'dsh');"><span>������</span></a></li>
                <li><a href="javascript:void(0);" onclick="selectTab(this,'ysh');"><span>�Ѵ���</span></a></li>
            </ul>
        </div>
    </div>
</html:form>
<div class="mainbox">
    <!--����start-->
    <h3 class="datetitle_01">
        <span> ̸����¼��Ϣ�б� </span>
    </h3>

    <table id="dataTable" ></table>
    <div id="pager"></div>

</div>
</body>
</html>
