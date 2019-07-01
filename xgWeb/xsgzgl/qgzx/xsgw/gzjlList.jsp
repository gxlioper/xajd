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
    <script type="text/javascript" src="xsgzgl/qgzx/xsgw/js/xsgwsh.js"></script>
    <script type="text/javascript" src="js/calendar/calendar.js"></script>
    <script type="text/javascript">
        jQuery(function(){
            var gridSetting = {
                caption:"ѧ��������¼�б�",
                pager:"pager",
                url:"qgzx_xsgwsh.do?method=ckgzjl&type=query&xh="+jQuery("#xh").val(),
                colList:[
                    {label:'���˵�λ',name:'yrdwmc', index: 'yrdwmc',width:'12%'},
                    {label:'Ͷ������',name:'gwmc', index: 'gwmc',width:'10%'},
                    {label:'��λ����',name:'gwdm', index: 'gwdm',width:'15%',hidden:true},
                    {label:'Ͷ��ʱ��',name:'sqsj', index: 'sqsj',width:'15%'},
                    {label:'¼��ʱ��',name:'sgsj', index: 'sgsj',width:'15%'},
                    {label:'����ʱ��',name:'gzsc', index: 'gzsc',width:'10%'},
                    {label:'�ڸ�״̬',name:'zgzt', index: 'zgzt',width:'10%',formatter:function(value,row){
                        if(value == "zg"){
                            return "�ڸ�";
                        } else {
                            return "����ְ";
                        }
                    }},
                    {label:'��ְʱ��',name:'tgsj', index: 'tgsj',width:'15%'}
                ],
                sortname: "sqsj",
                sortorder: "desc"
            };

            jQuery("#dataTable").initGrid(gridSetting);
        });
        function searchRs(){
            var map = getSuperSearch();
            jQuery("#dataTable").reloadGrid(map);
        }
        function back(){
            window.location.href="qgzx_xsgwsh.do?method=zgxsList";
        }
        function ckGzsc(){
            var rows = jQuery("#dataTable").getSeletRow();
            if(rows.length != 1){
                showAlertDivLayer("��ѡ��һ����¼��");
                return false;
            }
            var url = "qgzx_xsgwsh.do?method=ckgzsc&gwdm="+rows[0]["gwdm"]+"&xh="+jQuery("#xh").val();
            showDialog("�鿴ѧ������ʱ��",765,500,url);
        }
    </script>
</head>

<body>

<div class="tab_cur">
    <p class="location">
        <em>���ĵ�ǰλ�ã�</em><a>${title }</a>
    </p>
</div>
<html:form action="/qgzx_jfcjgl_cjff.do?method=gjcxCjff">
    <%@ include file="/comm/hiddenValue.jsp"%>
    <input type="hidden" value="${xh}" id="xh">
    <div class="toolbox">
        <div class="buttonbox">
            <ul>
                    <li>
                        <a href="javascript:void(0);" id="btn_sh" onclick="ckGzsc();return false;" class="btn_ck">�鿴����ʱ��</a>
                    </li>
                    <li>
                        <a href="javascript:void(0);" id="btn_qxsh" onclick="back();return false;" class="btn_fh">����</a>
                    </li>
            </ul>
        </div>
        <!-- �������� -->
        <%@ include file="/comm/search/superSearchArea.jsp"%>
        <!-- �������� end-->
    </div>
</html:form>
<div class="main_box">
    <!--����start-->
    <h3 class="datetitle_01">
        <span>ѧ��������¼�б�</span>
    </h3>
    <div class="con_overlfow">
        <table id="dataTable" ></table>
        <div id="pager"></div>
    </div>

</div>
</body>
</html>
