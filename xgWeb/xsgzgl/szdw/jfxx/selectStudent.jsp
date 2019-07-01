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
                caption:"ѧ���б�",
                pager:"pager",
                url:"szdw_jfxx.do?method=selectStudent&type=query",
                colList:[
                    {label:'ѧ��',name:'xh', index: 'xh',width:'10%',formatter:function(cell,row){
                        return "<a href='javascript:void(0);' onclick='view(\""+row["jgid"]+"\")'>"+cell+"</a>";
                    }},
                    {label:'����',name:'xm', index: 'xm',width:'10%'},
                    {label:'�Ա�',name:'xb', index: 'xb',width:'10%'},
                    {label:'��Ժ',name:'symc', index: 'symc',width:'10%'},
                    {label:'ѧԺ',name:'xymc', index: 'xymc',width:'10%'},
                    {label:'�����༶',name:'bjmc', index: 'bjmc',width:'10%'},
                    {label:'רҵ�༶',name:'zybjmc', index: 'zybjmc',width:'10%'},
                    {label:'����Ա',name:'fdyxm', index: 'fdyxm',width:'10%'},
                    {label:'����',name:'', index: '',width:'10%',formatter:function(cell,row){
                        return "<button class=\"btn_01\" type=\"button\" onclick=\"select("+row+");\">ѡ��</button>";
                    }},
                ],
                sortname: "xh",
                sortorder: "asc"
            }
            jQuery("#dataTable").initGrid(gridSetting);
        });
        function searchRs(){
            var map = getSuperSearch();
            jQuery("#dataTable").reloadGrid(map);
        }
        function select(){
            var rows = jQuery("#dataTable").getSeletRow();
            if(rows.length != 1){
                showAlertDivLayer("��ѡ��һ����Ҫ�޸ĵļ�¼��");
                return false;
            }
            showDialog("���Ӽҷü�¼",700,550,"szdw_jfxx.do?method=update&jgid="+rows[0]["jgid"]);
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
            <%--</logic:equal>--%>
        <!-- �������� -->
        <%@ include file="/comm/search/superSearchArea.jsp"%>
        <!-- �������� end-->
    </div>
</html:form>
<div class="formbox">
    <!--����start-->
    <h3 class="datetitle_01">
        <span>�ҷü�¼�б�</span>
    </h3>

    <table id="dataTable" ></table>
    <div id="pager"></div>

</div>
</body>
</html>
