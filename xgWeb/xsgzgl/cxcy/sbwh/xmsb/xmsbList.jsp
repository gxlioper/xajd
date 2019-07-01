<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <%@ include file="/syscommon/head.ini"%>
    <script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
    <script type="text/javascript" src="js/calendar/calendar.js"></script>
    <script type="text/javascript" src="xsgzgl/cxcy/sbwh/xmsb/js/xmsb.js"></script>
    <script type="text/javascript" src="js/jquery/plugins/upload/ajaxfileupload.js"></script>

    <script type="text/javascript">
        jQuery(function(){
            var gridSetting = {
                caption : "���¼�б�",
                pager : "pager",
                url : "cxcy_xmsb.do?method=getList&type=query",
                colList : [
                    { label : 'id', name : 'id', index : 'id',key : true, hidden : true },
                    { label : '��Ŀ����', name : 'xmmc', index : 'xmmc', width : '10%',formatter:xmmcLink },
                    { label : '������', name : 'bgr', index : 'bgr', width : '10%' },
                    { label : '��Ŀ���', name : 'xmjj', index : 'xmjj', width : '20%' ,formatter:titleLink},
                    { label : 'ѧ��', name : 'xn', index : 'xn', width : '10%' },
                    { label : 'ѧ��', name : 'xqmc', index : 'xqmc', width : '10%' },
                    { label : '���', name : 'tbrmc', index : 'tbrmc', width : '10%' },
                    { label : '��¼ʱ��', name : 'tbsj', index : 'tbsj', width : '15%' }
                ]};
            var map = getSuperSearch();
            gridSetting["params"] = map;
            jQuery("#dataTable").initGrid(gridSetting);
        });
        function searchRs() {
            var map = getSuperSearch();
            jQuery("#dataTable").reloadGrid(map);
        }


        function xmmcLink(cellValue, rowObject) {
            return "<a href='javascript:void(0);' class='name' onclick='view(\""
                + rowObject["id"]+"\");' title='"+cellValue+"'>" + cellValue
                + "</a>";
        }
        function titleLink(cellValue, rowObject) {
            var show = "";
            if(cellValue != "" && cellValue != null){
                show = cellValue;
                if(show.length > 15){
                    show = cellValue.substring(0,15)+"..."
                }
            }
            return "<span title='"+cellValue+"'>" + show
                + "</span>";
        }


    </script>
</head>

<body>
<div class="tab_cur">
    <p class="location">
        <em>���ĵ�ǰλ�ã�</em><a>${title }</a>
    </p>
</div>
<html:form action="/cxcy_jzsb">
    <%@ include file="/comm/hiddenValue.jsp"%>
    <div class="toolbox">
        <!-- ��ť -->
        <div class="buttonbox">
            <ul>
                <logic:equal name="writeAble" value="yes">
                    <li>
                        <a href="javascript:void(0);" class="btn_zj" onclick="add();return false;" >����</a>
                    </li>
                    <li>
                        <a href="javascript:void(0);" onclick="update();return false;" class="btn_xg" >�޸�</a>
                    </li>
                    <li>
                        <a href="javascript:void(0);" onclick="del();return false;" class="btn_sc" >ɾ��</a>
                    </li>
                </logic:equal>

                <li><a href="#" class="btn_dc" onclick="exportConfig();return false;">����</a></li>

            </ul>
        </div>
        <!-- �������� -->
        <%@ include file="/comm/search/superSearchArea.jsp"%>
        <!-- �������� end-->
    </div>
</html:form>
<div class="main_box">
    <h3 class=datetitle_01>
        <span>��Ŀ�ϱ��б�&nbsp;&nbsp; </span>
    </h3>
    <div class="con_overlfow">
        <table id="dataTable" ></table>
        <div id="pager"></div>
    </div>
</div>
</body>
</html>
