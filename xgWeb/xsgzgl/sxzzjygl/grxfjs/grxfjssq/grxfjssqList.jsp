<%@ page language="java" contentType="text/html; charset=GBK" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <%@ include file="/syscommon/head.ini"%>
    <script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
    <script type="text/javascript" language="javascript" src="js/comm/commFunction.js"></script>
    <script type="text/javascript" src="xsgzgl/sxzzjygl/grxfjs/grxfjssq/js/grxfjssqList.js"></script>
    <script type="text/javascript">
        var gridSetting = {
            caption: "��б�",
            pager: "pager",
            url: "sxzzjy_grxfjssq.do?method=getList&type=query",
            colList: [
                {label: 'sqid', name: 'sqid', index: 'sqid',hidden:true,key:true},
                {label: '��������',name:'splc', index: 'splc',hidden:true},
                {label: 'ѧ��', name: 'xh', index: 'xh', width: '6%',formatter:xhLink},
                {label: '����', name: 'xm', index: 'xm', width: '6%'},
                {label: '�༶', name: 'bjmc', index: 'bjmc', width: '10%'},
                {label: 'ѧԺ', name: 'xymc', index: 'xymc', width: '10%'},
                {label: '����', name: 'xfjsmc', index: 'xfjsmc', width: '10%'},
                {label: '�걨����', name: 'sblxmc', index: 'sblxmc', width: '10%'},
                {label: 'ѧ��ѧ��', name: 'xnxq', index: 'xnxq', width: '10%'},
                {label: '����ʱ��', name: 'sqsj', index: 'sqsj',hidden:true},
                {label: 'shzt', name: 'shzt', index: 'shzt',hidden:true},
                {label: '���״̬', name: 'shztmc', index: 'shztmc', width: '5%'}

            ],
            sortname: "sqsj",
            sortorder: "desc"
        };
        jQuery(function () {
            var map = getSuperSearch();
            gridSetting["params"] = map;
            jQuery("#dataTable").initGrid(gridSetting);
        });

        function searchRs() {
            var map = getSuperSearch();
            jQuery("#dataTable").reloadGrid(map);
        }
    </script>

</head>
<body>
<html:form action="/sxzzjy_grxfjssq" method="post">
    <%@ include file="/comm/hiddenValue.jsp" %>
    <input type="hidden" name="isopen" id="isopen" value="${jcszModel.isopen }"/>
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
                        <a href="javascript:void(0);" class="btn_zj" onclick="add();return false;">����</a>
                    </li>
                    <li>
                        <a href="javascript:void(0);" class="btn_xg" onclick="update();return false;">�޸�</a>
                    </li>

                    <li>
                        <a href="javascript:void(0);" class="btn_sc" onclick="del();return false;">ɾ��</a>
                    </li>
                    <li>
                        <a href="javascript:void(0);" class="btn_shuc" onclick="submit();return false;" >�ύ</a>
                    </li>
                    <li>
                        <a href="javascript:void(0);" onclick="cancel();return false;" class="btn_sr">����</a>
                    </li>
                </logic:equal>
                <li><a href="javascript:void(0);" onclick="grxfjsshLcinfo();return false;"
                       title="ѡ��һ����¼������ð�ť���Բ鿴������̡�"
                       class="btn_cs">���̸���</a>
                </li>
                <li>
                    <a href="#" class="btn_down" onclick="printWord();return false;">�걨������</a>
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
        <span>�����б�&nbsp;&nbsp; </span>
    </h3>
    <div class="con_overlfow">
        <table id="dataTable"></table>
        <div id="pager"></div>
    </div>
</div>
</body>
</html>