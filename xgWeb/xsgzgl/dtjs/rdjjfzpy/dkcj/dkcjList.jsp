<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <%@ include file="/syscommon/head.ini"%>
    <script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
    <script type="text/javascript" src="js/calendar/calendar.js"></script>
    <script type="text/javascript" src="xsgzgl/comm/exportNew/import.js"></script>
    <script type="text/javascript" src="xsgzgl/dtjs/rdjjfzpy/dkcj/js/dkcj.js"></script>
    <script type="text/javascript">
        var gridSetting = {
            caption:"�б�",
            pager:"pager",
            url:"dtjs_dkcj.do?method=getList&type=query",
            colList:[
                {label:'id',name:'id', index: 'id',key:true,hidden:true},
                {label:'ѧ��',name:'xh', index: 'xh',width:'10%',formatter:xhLink},
                {label:'����',name:'xm', index: 'xm',width:'10%'},
                {label:'�Ա�',name:'xb', index: 'xb',width:'6%'},
                {label:'��Ժ',name:'symc', index: 'symc',width:'10%'},
                {label:'ѧԺ',name:'xymc', index: 'xymc',width:'10%'},
                {label:'רҵ',name:'zymc', index: 'zymc',width:'10%'},
                {label:'רҵ�༶',name:'zybjmc', index: 'zybjmc',width:'10%'},
                {label:'�����༶',name:'bjmc', index: 'bjmc',width:'10%'},
                {label:'�ɼ�',name:'dkcj', index: 'dkcj',width:'10%'},
                {label:'����ʱ��',name:'kssj', index: 'kssj',width:'10%'},
                {label:'¼��ʱ��',name:'lrsj', index: 'lrsj',hidden:true}
            ],
            sortname: "lrsj",
            sortorder: "desc"
        };

        jQuery(function(){
            var map = getSuperSearch();
            gridSetting["params"] = map;
            jQuery("#dataTable").initGrid(gridSetting);
        });

    </script>
</head>

<body>

<div class="tab_cur">
    <p class="location">
        <em>���ĵ�ǰλ�ã�</em><a>${title }</a>
    </p>
</div>
<html:form action="/dtjs_dkcj">
    <%@ include file="/comm/hiddenValue.jsp"%>
    <div class="toolbox">
        <!-- ��ť -->
        <div class="buttonbox">
            <ul>
                <logic:equal name="writeAble" value="yes">
                    <li>
                        <a href="javascript:void(0);" class="btn_zj" onclick="add();return false;"  >����</a>
                    </li>
                    <li>
                        <a href="javascript:void(0);" onclick="update();return false;" class="btn_xg" >�޸�</a>
                    </li>
                    <li>
                        <a href="javascript:void(0);" onclick="del();return false;" class="btn_sc" >ɾ��</a>
                    </li>
                    <li>
                        <a href="javascript:void(0);" onclick="dr1();return false;" class="btn_dr">����</a>
                    </li>
                    <li>
                        <a href="javascript:void(0);" onclick="exportConfig();return false;" class="btn_dc">����</a>
                    </li>
                </logic:equal>
            </ul>
        </div>
        <!-- �������� -->
        <%@ include file="/comm/search/superSearchArea.jsp"%>
        <!-- �������� end-->
    </div>
</html:form>
<div class="formbox">
    <!--����start-->
    <h3 class="datetitle_01">
        <span> �б� </span>
    </h3>

    <table id="dataTable" ></table>
    <div id="pager"></div>

</div>
</body>
</html>
