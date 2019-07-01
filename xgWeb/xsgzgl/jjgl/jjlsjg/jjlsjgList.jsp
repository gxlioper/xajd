<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <%@ include file="/syscommon/head.ini"%>
    <script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
    <script type="text/javascript" src="js/calendar/calendar.js"></script>
    <script type="text/javascript" src="xsgzgl/comm/exportNew/import.js"></script>
    <script type="text/javascript" src="xsgzgl/jjgl/jjlsjg/js/jjlsjg.js"></script>
    <script type="text/javascript">
        jQuery(function(){
            var gridSetting = {
                caption : "${gnmkmc}�б�",
                pager : "pager",
                url : "jjgl_jjlsjggl.do?method=getJjlsjgListData",
                colList : [
                    { label : 'ѧ��', name : 'xh', index : 'xh', width : '10%', key:true , formatter : xhLink},
                    { label : '����', name : 'xm', index : 'xm', width : '10%' },
                    { label : '�Ա�', name : 'xb', index : 'xb', width : '5%' },
                    { label : '�꼶', name : 'nj', index : 'nj', width : '8%' },
                    { label : '��Ժ', name : 'symc', index : 'sydm', width : '12%' },
                    { label : '�༶', name : 'bjmc', index : 'bjdm', width : '15%' },
                    { label : '�ó���Ŀ', name : 'sckmmcs', index : 'sckmmcs', width : '20%' },
                    { label : '�Ǽ���', name : 'djrxm', index : 'djr', width : '10%' },
                    { label : '�Ǽ�ʱ��', name : 'djsj', index : 'djsj', width : '10%' }],
                sortname : "djsj",
                sortorder : "desc" }
            var map = getSuperSearch();
            gridSetting["params"] = map;
            jQuery("#dataTable").initGrid(gridSetting);
        })
    </script>
</head>

<body>
<div class="tab_cur">
    <p class="location">
        <em>���ĵ�ǰλ�ã�</em><a>${title }</a>
    </p>
</div>

<html:form action="/jjgl_jjlsjggl">
    <input type="hidden" id="gnmkmc" value="${gnmkmc}"/>
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
                        <a href="javascript:void(0);" onclick="edit();return false;" class="btn_xg" >�޸�</a>
                    </li>
                    <li>
                        <a href="javascript:void(0);" onclick="del();return false;" class="btn_sc" >ɾ��</a>
                    </li>
                    <li>
                        <a href="javascript:void(0);" onclick="importConfig();return false;" class="btn_dr">����</a>
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
        <span>${gnmkmc}�б�&nbsp;&nbsp; </span>
    </h3>
    <div class="con_overlfow">
        <table id="dataTable" ></table>
        <div id="pager"></div>
    </div>
</div>
</body>
</html>
