<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <%@ include file="/syscommon/head.ini"%>
    <script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
    <script type="text/javascript" src="js/calendar/calendar.js"></script>
    <script language="javascript" src="js/comm/dateUtils.js"></script>

    <script type="text/javascript">
        jQuery(function(){
            var gridSetting = {
                pager:"pager",
                url:"xsxx_xsxxgl.do?method=viewJtcyLsjl&type=query&xh="+jQuery("#xh").val(),
                colList:[
                    { label : 'key', name : 'id', index : 'id',key : true, hidden : true },
                    { label : '����',name : 'cyxm', index : 'cyxm',width : '10%'},
                    { label : '��������', name : 'csny', index : 'csny', width : '10%'},
                    { label : '��ϵ', name : 'jtcygxmc', index : 'jtcygxmc', width : '10%'},
                    { label : '֤������', name : 'jtcyzjlx', index : 'jtcyzjlx', width : '10%'},
                    { label : '֤������', name : 'jtcyzjhm', index : 'jtcyzjhm', width : '10%'},
                    { label : '�Ļ��̶�', name : 'ylzd1', index : 'ylzd1', width : '10%'},
                    { label : '������λ', name : 'cyxxdw', index : 'cyxxdw', width : '10%'},
                    { label : 'ְ��', name : 'cyzy', index : 'cyzy', width : '10%'},
                    { label : '������ò', name : 'zzmmmc', index : 'zzmmmc', width : '10%'},
                    { label : '��ϵ�绰', name : 'cylxdh', index : 'cylxdh', width : '10%'},
                    { label : '������', name : 'czrxm', index : 'czrxm', width : '10%'},
                    { label : '����ʱ��', name : 'czsj', index : 'czsj', width : '10%'},
                    { label : '��������', name : 'czlx', index : 'czlx', width : '10%'}
                ],
                sortname : "czsj",
                sortorder : "desc",
                multiselect:false};
            var map = getSuperSearch();
            gridSetting["params"] = map;
            jQuery("#dataTable").initGrid(gridSetting);
        });

        function searchRs(){
            var map = getSuperSearch();
            jQuery("#dataTable").reloadGrid(map);
        }

    </script>
</head>

<body>

<div class="tab_cur">
    <p class="location">
        <em>���ĵ�ǰλ�ã�</em><a>${title }</a>
    </p>
</div>
<html:form action="/xsxx_xsxxgl" method="post">
    <!-- ������ -->
    <html:hidden property="xh" styleId="xh"/>
    <html:hidden property="zd" styleId="zd"/>

    <div class="toolbox">
        <!-- ��ť -->
        <div class="buttonbox">
            <ul>

            </ul>
        </div>
    </div>
    <!-- �������� -->
    <div style="display: none">
        <%@ include file="/comm/search/superSearchArea.jsp"%>
    </div>

    <!-- �������� end-->
</html:form>
<div class="formbox">
    <!--����start-->
    <h3 class="datetitle_01">
        <span> ��ʷ��¼�б� </span>
    </h3>

    <table id="dataTable" ></table>
    <div id="pager"></div>

</div>
<table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
    <tfoot>
    <tr>
        <td colspan="5">
            <div class="bz">"<span class="red">*</span>"Ϊ������  </div>
            <div class="btn">
                <button type="button"  name="�ر�" onclick="Close()" id="buttonClose">�� ��</button>
            </div>
        </td>
    </tr>
    </tfoot>
</table>
</body>
</html>
