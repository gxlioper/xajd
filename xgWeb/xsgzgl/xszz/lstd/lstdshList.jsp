<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html>
<head>
    <title>��ɫͨ�����</title>
    <%@ include file="/syscommon/head.ini"%>
    <script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
    <script type="text/javascript" src="js/check.js"></script>
    <script type="text/javascript" src="xsgzgl/xszz/lstd/js/lstd.js"></script>
    <script type="text/javascript">
        var gridSetting = {};
//        var gridSetting2 = {};
        function initGrid(){
            gridSetting = {
                caption:"��ɫͨ�������б�",
                pager:"pager",
                url:"xszz_lstd.do?method=lstdshList&type=query",
                colList:[
                    {label:'key',name:'sqid', index: 'sqid',key:true ,hidden:true},
                    {label:'ѧ��',name:'xh', index: 'xh',width:'13%'},
                    {label:'����',name:'xm', index: 'xm',width:'8%'},
                    {label:'�Ա�',name:'xb', index: 'xb',width:'5%'},
                    {label:'ѧԺ',name:'xymc', index: 'xydm',width:'13%'},
                    {label:'��Ժ',name:'symc', index: 'symc',width:'13%'},
                    {label:'�����༶',name:'bjmc', index: 'bjdm',width:'13%'},
                    {label:'רҵ�༶',name:'zybjmc', index: 'zybjmc',width:'13%'},
//                    {label:'ѧ��',name:'xn', index: 'xn',width:'10%'},
//                    {label:'ѧ��',name:'xq', index: 'xq',width:'10%',hidden:true},
                    {label:'����ʱ��',name:'sqsj', index: 'sqsj',width:'9%'},
                    {label:'shzt',name:'shzt', index: 'shzt',hidden:true},
                    {label:'���״̬',name:'shztmc', index: 'shzt',width:'6%'},
                    {label:'shlc',name:'shlc', index: 'shlc',hidden:true},
                    {label:'shid',name:'shid', index: 'shid',hidden:true},
                    {label:'gwid',name:'gwid', index: 'gwid',hidden:true}
                ],
                sortname: "sqsj",
                sortorder: "desc"
            };
//            gridSetting2 = {
//                caption:"��ɫͨ�������б�",
//                pager:"pager",
//                url:"xszz_lstd.do?method=lstdshList&type=query",
//                colList:[
//                    {label:'key',name:'sqid', index: 'sqid',key:true ,hidden:true},
//                    {label:'ѧ��',name:'xh', index: 'xh',width:'13%'},
//                    {label:'����',name:'xm', index: 'xm',width:'8%'},
//                    {label:'�Ա�',name:'xb', index: 'xb',width:'5%'},
//                    {label:'ѧԺ',name:'xymc', index: 'xydm',width:'13%'},
//                    {label:'��Ժ',name:'symc', index: 'symc',width:'13%'},
//                    {label:'�����༶',name:'bjmc', index: 'bjdm',width:'13%'},
//                    {label:'רҵ�༶',name:'zybjmc', index: 'zybjmc',width:'13%'},
////                    {label:'ѧ��',name:'xn', index: 'xn',width:'10%'},
////                    {label:'ѧ��',name:'xq', index: 'xq',width:'10%',hidden:true},
//                    {label:'����ʱ��',name:'sqsj', index: 'sqsj',width:'9%'},
//                    {label:'shzt',name:'shzt', index: 'shzt',hidden:true},
//                    {label:'���״̬',name:'shztmc', index: 'shzt',width:'6%'},
//                    {label:'shlc',name:'shlc', index: 'shlc',hidden:true},
//                    {label:'shid',name:'shid', index: 'shid',hidden:true},
//                    {label:'gwid',name:'gwid', index: 'gwid',hidden:true},
//                ],
//                sortname: "sqsj",
//                sortorder: "desc"
//            };
        };
        function searchRs(){
            var map = getSuperSearch();
            var shzt = jQuery("#shzt").val();

            if (shzt != ""){
                map["shzt"] = shzt;
            }
            jQuery("#dataTable").reloadGrid(map);
        }
        jQuery(function(){
            initGrid();
            var map = getSuperSearch();
            map["shzt"]="dsh";
            gridSetting["params"] = map;
            jQuery("#dataTable").initGrid(gridSetting);
        })
        //�������
        function savePlshx(shzt,shyj){
            var rows = jQuery("#dataTable").getSeletRow();
            var guid = new Array();
            var gwid = new Array();
            var xhs = new Array();
            var splc = new Array();

            jQuery.each(rows,function(i,row){
                guid.push(row["sqid"]);
                gwid.push(row["gwid"]);
                xhs.push(row["xh"]);
                splc.push(row["shlc"]);
            });

            jQuery.post(
                "xszz_lstd.do?method=lstdplsh&type=save",
                {
                    shzt:shzt,
                    id:guid,
                    gwids:gwid,
                    xhs:xhs,
                    shyj:shyj,
                    shlcs:splc
                },function(data){

                    showAlertDivLayer(data["message"],{},{"clkFun":function(){
                        jQuery("#dataTable").reloadGrid();
                    }});
                },
                'json'
            );
        }
        //����
        function exportConfig(){
            var DCCLBH='xszz_lstd_sh.do';
            customExport(DCCLBH, exportData);
        }

        function exportData(){
            var DCCLBH='xszz_lstd_sh.do';
            setSearchTj();//���ø߼���ѯ����
            var shzt = jQuery("#shzt").val();
            var url = "xszz_lstd.do?method=shExport&dcclbh=" + DCCLBH + "&shzt="+shzt;//dcclbh,�������ܱ��
            url = addSuperSearchParams(url);//���ø߼���ѯ����
            jQuery("form").eq(0).attr("action", url);
            jQuery("form").eq(0).submit();
        }
    </script>
</head>
<body>
<html:form action="/xszz_lstd">
    <input type="hidden" value="${isopean}" id="isopean">
    <input type="hidden" value="dsh" id="shzt"/>
    <%@ include file="/comm/hiddenValue.jsp"%>

    <div class="toolbox">
        <!-- ��ť -->
        <div class="buttonbox">
            <ul>
                    <%--<logic:equal name="writeAble" value="yes">--%>
                        <li id="li_sh">
                            <a href="javascript:void(0);" onclick="lstdsh();return false;"
                               title="ѡ����Ҫ��˵ļ�¼������ð�ť���Դ����ҳ�档"
                               class="btn_sh">���</a>
                        </li>
                        <li id="li_qx" style="display: none;">
                            <a href="javascript:void(0);" onclick="cancelSh();return false;"
                               title="ѡ��һ����¼������ð�ť�����Գ���ʧ�����˲�����"
                               class="btn_qxsh">����</a>
                        </li>
                        <li>
                            <a href="javascript:void(0);" onclick="exportConfig();return false;"
                               class="btn_dc">����</a>
                        </li>
                        <li>
                            <a href="javascript:void(0);" onclick="print();return false;"
                               class="btn_dy">��ӡ</a>
                        </li
                    <%--</logic:equal>--%>

            </ul>
        </div>
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
