
<%@ page language="java" contentType="text/html; charset=GBK" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <%@ include file="/syscommon/head.ini"%>
    <script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
    <script type="text/javascript" language="javascript" src="js/comm/commFunction.js"></script>
    <script type="text/javascript" src="xsgzgl/sxzzjygl/grxfjs/grxfjshb/js/grxfjshbList.js"></script>
    <script type="text/javascript" src="xsgzgl/comm/exportNew/import.js"></script>
    <script type="text/javascript">
        var gridSetting = {
            caption: "����ѧ�罨��㱨�б�",
            pager: "pager",
            url: "sxzzjy_grxfjshb.do?method=getList&type=query",
            colList: [
                {label: 'nzhbid', name: 'nzhbid', index: 'nzhbid',hidden:true},
                {label: 'nzzjid', name: 'nzzjid', index: 'nzzjid',hidden:true},
                {label: 'jgid', name: 'jgid', index: 'jgid',hidden:true,key:true},
                {label: 'ѧ��', name: 'xh', index: 'xh', width: '10%',formatter:xhLink},
                {label: '����', name: 'xm', index: 'xm', width: '10%'},
                {label: '�༶', name: 'bjmc', index: 'bjmc', width: '10%'},
                {label: 'ѧԺ', name: 'xymc', index: 'xymc', width: '10%'},
                {label: '����', name: 'xfjsmc', index: 'xfjsmc', width: '10%'},
                {label: '�걨����', name: 'sblxmc', index: 'sblxmc', width: '10%'},
                {label: 'ѧ��ѧ��', name: 'xnxq', index: 'xnxq', width: '10%'},
                {label: '¼��ʱ��', name: 'lrsj', index: 'lrsj',hidden:true},
                {label: '���ڻ㱨״̬', name: 'nzhbshztmc', index: 'nzhbshztmc', width: '10%'},
                {label: '�����ܽ�״̬', name: 'nzzjshztmc', index: 'nzzjshztmc', width: '10%'},
                {label: 'nzhbshzt', name: 'nzhbshzt', index: 'nzhbshzt', hidden:true},
                {label: 'nzzjshzt', name: 'nzzjshzt', index: 'nzzjshzt', hidden:true},
                {label: 'nzhbsplc', name: 'nzhbsplc', index: 'nzhbsplc', hidden:true},
                {label: 'nzzjsplc', name: 'nzzjsplc', index: 'nzzjsplc', hidden:true}

            ],
            sortname: "lrsj",
            sortorder: "desc"
        };
        jQuery(function () {
            jQuery("#dataTable").initGrid(gridSetting);

        });

        function searchRs() {
            var map = getSuperSearch();
            jQuery("#dataTable").reloadGrid(map);
        }
        //����
        /* function importConfig(){
             toImportDataNew("IMPORT_SXZZJYGL_BJXFJSJG");
             return false;
         }*/
    </script>

</head>
<body>
<html:form action="/sxzzjy_grxfjshb" method="post">
    <input type="hidden" name="isopen_nzzj" id="isopen_nzzj" value="${jcszModel.isopen_nzzj }"/>
    <input type="hidden" name="isopen_nzhb" id="isopen_nzhb" value="${jcszModel.isopen_nzhb }"/>
    <%@ include file="/comm/hiddenValue.jsp" %>
    <div class="tab_cur">
        <p class="location">
            <em>���ĵ�ǰλ�ã�</em><a>${title}</a>
        </p>
    </div>

    <div class="toolbox">
        <!-- ��ť -->
        <div class="buttonbox">
            <ul>
                <logic:equal name="writeAble" value="yes">
                    <li>
                        <a href="javascript:void(0);" class="btn_zj" onclick="add('nzhb');return false;">���ڻ㱨����</a>
                    </li>
                    <li>
                        <a href="javascript:void(0);" class="btn_zj" onclick="add('nzzj');return false;">����ܽ�����</a>
                    </li>
                    <li>
                        <a href="javascript:void(0);" class="btn_xg" onclick="update('nzhb');return false;">���ڻ㱨�޸�</a>
                    </li>
                    <li>
                        <a href="javascript:void(0);" class="btn_xg" onclick="update('nzzj');return false;">����ܽ��޸�</a>
                    </li>
                    <li>
                        <a href="javascript:void(0);" class="btn_sc" onclick="selectHblx('del');return false;">ɾ��</a>
                    </li>
                    <li>
                        <a href="javascript:void(0);" class="btn_shuc" onclick="selectHblx('submit');return false;" >�ύ</a>
                    </li>
                    <li>
                        <a href="javascript:void(0);" onclick="selectHblx('cancel');return false;" class="btn_sr">����</a>
                    </li>

                </logic:equal>
                <li><a href="javascript:void(0);" onclick="grxfjshbLcinfo();return false;"
                       title="ѡ��һ����¼������ð�ť���Բ鿴������̡�"
                       class="btn_cs">���̸���</a>
                </li>
            </ul>
            <ul>
                <li>
                    <a href="#" class="btn_down" onclick="printWord('nzhb');return false;">���ڻ㱨����</a>
                </li>
                <li>
                    <a href="#" class="btn_down" onclick="printWord('nzzj');return false;">�����ܽ�����</a>
                </li>
            </ul>
        </div>
        <!-- �������� -->
        <%@ include file="/comm/search/superSearchArea.jsp" %>
        <!-- �������� end-->
    </div>
</html:form>
<div class="main_box">
    <h3 class=datetitle_01>
        <span>����ѧ�罨��㱨�б�&nbsp;&nbsp; </span>
    </h3>
    <div class="con_overlfow">
        <table id="dataTable"></table>
        <div id="pager"></div>
    </div>
</div>
</body>
</html>