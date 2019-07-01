<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <%@ include file="/syscommon/head.ini"%>
    <script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
    <script type="text/javascript" src="js/calendar/calendar.js"></script>
    <script type="text/javascript" src="xsgzgl/cxcy/sbwh/jzsb/js/jzsb.js"></script>
    <script type="text/javascript" src="js/jquery/plugins/upload/ajaxfileupload.js"></script>

    <script type="text/javascript">
        jQuery(function(){
            var gridSetting = {
                caption : "ͳ�Ʊ����б�",
                pager : "pager",
                multiselect:false,
                url : "cxcy_tjbb.do?method=getList&type=query",
                colList : [
                    { label : 'xydm', name : 'xydm', index : 'xydm', hidden : true},
                    { label : '�������', name : 'xymc', index : 'xymc', width : '10%'},
                    { label : '���´�ҵ������', name : 'bzs', index : 'bzs', width : '10%' ,formatter:bzLink},
                    { label : '���´�ҵ������', name : 'jzs', index : 'jzs', width : '10%',formatter:jzLink },
                    { label : '���´�ҵ��Ŀ��', name : 'xms', index : 'xms', width : '10%',formatter:xmLink},

                ]};
            var map = getSuperSearch();
            gridSetting["params"] = map;
            jQuery("#dataTable").initGrid(gridSetting);
        });
        function searchRs() {
            var map = getSuperSearch();
            jQuery("#dataTable").reloadGrid(map);
        }
        function bzLink(cellValue, rowObject) {
            if(cellValue == 0){
                return cellValue;
            }
            return "<a href='javascript:void(0);' class='name' onclick='view(\"bz\",\""+rowObject["xydm"]+"\");' >" + cellValue
                + "</a>";
        }
        function jzLink(cellValue, rowObject) {
            if(cellValue == 0){
                return cellValue;
            }
            return "<a href='javascript:void(0);' class='name' onclick='view(\"jz\",\""+rowObject["xydm"]+"\");' >" + cellValue
                + "</a>";
        }
        function xmLink(cellValue, rowObject) {
            if(cellValue == 0){
                return cellValue;
            }
            return "<a href='javascript:void(0);' class='name' onclick='view(\"xm\",\""+rowObject["xydm"]+"\");' >" + cellValue
                + "</a>";
        }
        function view(type,xydm){
            searchRs();
            var url = "";
            if("bz" == type) url = "cxcy_tjbb.do?method=getBzList&xydm="+xydm;
            if("jz" == type) url = "cxcy_tjbb.do?method=getJzList&xydm="+xydm;
            if("xm" == type) url = "cxcy_tjbb.do?method=getXmList&xydm="+xydm;
            //url = addSuperSearchParams(url);//���ø߼���ѯ����
            refreshForm(url);
            BatAlert.showTips('���ڲ��������Ե�...');
        }

    </script>
</head>

<body>
<div class="tab_cur">
    <p class="location">
        <em>���ĵ�ǰλ�ã�</em><a>${title }</a>
    </p>
</div>
<html:form action="/cxcy_tjbb">
    <%@ include file="/comm/hiddenValue.jsp"%>
    <div class="toolbox">
        <!-- ��ť -->
        <div class="buttonbox">
            <ul>

                <%--<li><a href="#" class="btn_dc" onclick="exportConfig();return false;">����</a></li>--%>

            </ul>
        </div>
        <!-- �������� -->
        <%@ include file="/comm/search/superSearchArea.jsp"%>
        <!-- �������� end-->
    </div>
</html:form>
<div class="main_box">
    <h3 class=datetitle_01>
        <span>�б�&nbsp;&nbsp; </span>
    </h3>
    <div class="con_overlfow">
        <table id="dataTable" ></table>
        <div id="pager"></div>
    </div>
</div>
</body>
</html>
