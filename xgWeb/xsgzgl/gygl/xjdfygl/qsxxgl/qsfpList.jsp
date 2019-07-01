<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <%@ include file="/syscommon/head.ini"%>
    <script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
    <script type="text/javascript" src="js/calendar/calendar.js"></script>
    <script type="text/javascript" src="xsgzgl/comm/exportNew/import.js"></script>
    <script type="text/javascript">
        jQuery(function(){
            var gridSetting = {
                caption : "¥����Ϣ�б�",
                pager : "pager",
                url : "gygl_fygl_qsxxgl10698.do?method=qsfpList&type=query",
                colList : [
                    {label:'pk',name:'pk',index :'pk',key:true,hidden:true,width:'10%'},
                    {label:'¥������',name:'lddm',index :'lddm',hidden:true,width:'10%'},
                    {label:'¥������',name:'ldmc',index:'ldmc',width:'10%'},
                    {label:'���Һ�',name:'qsh',index:'qsh',width:'10%'},
                    {label:'���',name:'ch',index:'ch',width:'5%'},
                    {label:'��������',name:'fjlx',index:'fjlx',width:'5%',formatter:function(val,row){
                        if(val == "01"){
                            return "����";
                        } else if(val == "02"){
                            return "ֵ����";
                        } else if(val == "03"){
                            return "����";
                        } else {
                            return val;
                        }
                    }},
                    {label:'��������',name:'fjzx',index:'fjzx',width:'5%',formatter:function(val,row){
                        if(val == "1"){
                            return "��";
                        } else if(val == "2"){
                            return "��";
                        } else if(val == "3"){
                            return "��";
                        } else if(val == "4"){
                            return "��";
                        } else {
                            return val;
                        }
                    }},
                    {label:'�����Ա�',name:'qsxb',index:'qsxb',width:'5%',formatter:function(val,row){
                        if(val == "1"){
                            return "��";
                        } else if(val == "2"){
                            return "Ů";
                        } else {
                            return "��ס";
                        }
                    }},
                    {label:'��λ��',name:'cwss',index:'cwss',width:'5%'},
                    {label:'������λ',name:'blcws',index:'blcws',width:'5%'},
                    {label:'���д�λ',name:'kxcws',index:'kxcws',width:'5%'},
                    {label:'����ס',name:'ylzcws',index:'ylzcws',width:'5%'},
                    {label:'������Ժ/ѧԺ',name:'ssbm',index:'ssbm',width:'5%'}
                ],
                sortname: "qsh",
                sortorder: "asc"
            }
            var map = getSuperSearch();
            gridSetting["params"] = map;
            jQuery("#dataTable").initGrid(gridSetting);
        });
        function searchRs() {
            var map = getSuperSearch();
            jQuery("#dataTable").reloadGrid(map);
        }
        function qsfp(){
            var ids = jQuery("#dataTable").getSeletIds();
            if(ids.length == 0){
                showAlert("������ѡ��һ�����ң�");
                return false;
            }

            var url = "gygl_fygl_qsxxgl10698.do?method=qsfp&pks="+ids.toString();
            showDialog("��������",650,200,url);
        }
    </script>
</head>

<body>
<div class="tab_cur">
    <p class="location">
        <em>���ĵ�ǰλ�ã�</em><a>${title }</a>
    </p>
</div>
<html:form action="/gyglnew_wmqsxsmd_12688">
    <%@ include file="/comm/hiddenValue.jsp"%>
    <div class="toolbox">
        <!-- ��ť -->
        <div class="buttonbox">
            <ul>
                <li><a href="javascript:void(0);" onclick="qsfp();" class="btn_xg">������Ժ/ѧԺ</a></li>
            </ul>
        </div>
        <!-- �������� -->
        <%@ include file="/comm/search/superSearchArea.jsp"%>
        <!-- �������� end-->
    </div>
</html:form>

<div class="main_box">
    <h3 class=datetitle_01>
        <span></span>
    </h3>
    <div class="con_overlfow">
        <table id="dataTable" ></table>
        <div id="pager"></div>
    </div>
</div>
</body>
</html>
