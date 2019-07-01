<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <%@ include file="/syscommon/head.ini"%>
    <script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
    <script type="text/javascript" src="js/calendar/calendar.js"></script>
    <script type="text/javascript">
        jQuery(function(){
            var gridSetting = {
                caption:"����ѧ�����б�",
                pager:"pager",
                url:"qgzx_dgxsk.do?method=dgxskManage&type=query",
                colList:[
                    { label : 'ѧ��', name : 'xh', index : 'xh' ,width : '10%',formatter:xhLink},
                    { label : '����', name : 'xm', index : 'xm', width : '10%'},
                    { label : '�Ա�', name : 'xb', index : 'xb', width : '7%'},
                    { label : 'ѧԺ', name : 'xymc', index : 'xymc', width : '16%'},
                    { label : 'רҵ', name : 'zymc', index : 'zymc', width : '16%'},
                    { label : '�༶', name : 'bjmc', index : 'bjmc', width : '16%'},
                    { label : '��λ', name : 'gwmc', index : 'gwmc', width : '15%'},
                    { label : '״̬', name : 'ztmc', index : 'ztmc', width : '10%'},
                    { label : '��λ����', name : 'gwdm', index : 'gwdm', hidden:true},
                    { label : '������', name : 'sqbh', index : 'sqbh', hidden:true}
                ],
                sortname : "xh",
                sortorder : "asc" };
            var map = getSuperSearch();
            gridSetting["params"] = map;
            jQuery("#dataTable").initGrid(gridSetting);
        });
        function searchRs(){
            var map = getSuperSearch();
            jQuery("#dataTable").reloadGrid(map);
        }
        function xhLink(cellValue,rowObject){
            return "<a href='javascript:void(0);' class='name' onclick=\"viewKysj('"+rowObject["xh"]+"','"+rowObject["sqbh"]+"');\">"+cellValue+"</a>";
        }
        function viewKysj(xh,sqbh){
            if("" != sqbh && null != sqbh && "null" != sqbh){
                showDialog("�鿴",765,500,"qgzx_xsgwsh.do?method=xsgwSh&type=ck&xh="+xh+"&sqbh="+sqbh);
            }else{
                var url = "qgzx_dgxsk.do?method=dgxskView&xh="+xh;
                var title = "�鿴";
                showDialog(title, 765,600, url);
            }

        }
        //����
        function exportConfig(){
            var DCCLBH='qgzx_dgxsk.do';
            customExport(DCCLBH, exportData);
        }
        function exportData(){
            var DCCLBH='qgzx_dgxsk.do';
            setSearchTj();//���ø߼���ѯ����
            var url = "qgzx_dgxsk.do?method=exportData&dcclbh=" + DCCLBH;//dcclbh,�������ܱ��
            url = addSuperSearchParams(url);//���ø߼���ѯ����
            jQuery("form").eq(0).attr("action", url);
            jQuery("form").eq(0).submit();
        }
    </script>
</head>

<body>
<div class="tab_cur">
    <p class="location">
        <em>���ĵ�ǰλ�ã�</em><a>${title }</a>
    </p>
</div>
<html:form action="/qgzx_dgxsk">
    <%@ include file="/comm/hiddenValue.jsp"%>
    <div class="toolbox">
        <!-- ��ť -->
        <div class="buttonbox">
            <ul>
                <li>
                    <a href="#" class="btn_dc" onclick="exportConfig();return false;">����</a>
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
        <span>����ѧ�����б�&nbsp;&nbsp; </span>
    </h3>
    <div class="con_overlfow">
        <table id="dataTable" ></table>
        <div id="pager"></div>
    </div>
</div>
</body>
</html>