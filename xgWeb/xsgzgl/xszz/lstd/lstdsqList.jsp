<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html>
<head>
    <title>��ɫͨ������</title>
    <%@ include file="/syscommon/head.ini"%>
    <script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
    <script type="text/javascript" src="js/check.js"></script>
    <script type="text/javascript" src="xsgzgl/xszz/lstd/js/lstd.js"></script>
    <script type="text/javascript">
        function initGrid(){
            var gridSetting = {
                caption:"��ɫͨ�������б�",
                pager:"pager",
                url:"xszz_lstd.do?method=lstdsqList&type=query",
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
                    {label:'shlc',name:'shlc', index: 'shlc',hidden:true}
                ],
                sortname: "sqsj",
                sortorder: "desc"
            };
            gridSetting["params"]=getSuperSearch();
            jQuery("#dataTable").initGrid(gridSetting);
        };
        function searchRs(){
            var map = getSuperSearch();
            jQuery("#dataTable").reloadGrid(map);
        }
        jQuery(function(){
            initGrid();
        })
    </script>
</head>
<body>
<html:form action="/xszz_lstd">
    <%@ include file="/comm/hiddenValue.jsp"%>

    <div class="toolbox">
        <!-- ��ť -->
        <div class="buttonbox">
            <ul>
                <logic:equal name="isOpean" value="1">
                    <li>
                        <a href="javascript:void(0);" class="btn_zj" onclick="lstdsq();return false;"  title="����ð�ť�����������дҳ�档">����</a>
                    </li>
                    <li>
                        <a href="javascript:void(0);" onclick="lstdxg();return false;" class="btn_xg" title="ѡ��һ����¼������ð�ť���޸������">�޸�</a>
                    </li>
                    <li>
                        <a href="javascript:void(0);" onclick="lstdsqDelete();return false;" class="btn_sc" title="ֻ��ȡ��δ��˵ļ�¼���Ѿ�����˵Ĳ���ȡ����" >ɾ��</a>
                    </li>
                    <li>
                        <a href="javascript:void(0);" onclick="submit();return false;" class="btn_shuc">�ύ</a>
                    </li>
                    <li>
                        <a href="javascript:void(0);" onclick="cancel();return false;" class="btn_sr">����</a>
                    </li>
                </logic:equal>
                <li>
                    <a href="javascript:void(0);" onclick="Lcinfo();return false;"
                       title="ѡ��һ����¼������ð�ť���Բ鿴������̡�"
                       class="btn_cs">���̸���</a>
                </li>
            </ul>
        </div>
        <!-- �������� -->
        <%@ include file="/comm/search/superSearchArea.jsp"%>
        <!-- �������� end-->
    </div>
</html:form>

<div class="main_box">
    <h3 class=datetitle_01>
        <span>��ɫͨ�������б�&nbsp;&nbsp; </span>
    </h3>
    <div class="con_overlfow">
        <table id="dataTable" ></table>
        <div id="pager"></div>
    </div>
</div>
</body>
</html>
