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
                caption:"",
                pager:"pager",
                url:"xyfd_fdjswh.do?method=fdjsList&type=query",
                colList:[
                    {label:'�ǼǺ�',name:'djh', index: 'djh',width:"10%",key:true,formatter:jsLink},
                    {label:'ְ����',name:'zgh', index: 'zgh',width:'10%' },
                    {label:'����',name:'xm', index: 'xm',width:'10%'},
                    {label:'�Ա�',name:'xb', index: 'xb',width:'5%',formatter:function (cell,rowObject) {
                            if(rowObject["xb"]=="1"){
                                return "��";
                            }else if(rowObject["xb"]=="2"){
                                return "Ů";
                            }else {
                                return rowObject["xb"];
                            }
                        }},
                    {label:'ְ��/ְ��',name:'zc', index: 'zc',width:'10%'},
                    {label:'���ڵ�λ',name:'bmmc', index: 'bmmc',width:'10%'},
                    {label:'�ο�����',name:'kcmc', index: 'kcmc',width:'10%'},
                    {label:'ѧ��/רҵ',name:'xkzy', index: 'xkzy',width:'10%'},
                    {label:'������Ŀ',name:'fdkm', index: 'fdkm',width:'10%'},
                    {label:'������',name:'fdsmc', index: 'fdsmc',width:'10%'}
                ]
            }
            gridSetting["params"]=getSuperSearch();
            jQuery("#dataTable").initGrid(gridSetting);
        });

        function searchRs(){
            var map = getSuperSearch();
            jQuery("#dataTable").reloadGrid(map);
        }

        function add(){
            showDialog("����������ʦ",900,550,"xyfd_fdjswh.do?method=addfdjs");
        }
        function update(){
            var rows = jQuery("#dataTable").getSeletRow();
            if(rows.length!=1){
                showAlertDivLayer("��ѡ��һ����Ҫ�޸ĵļ�¼");
                return false;
            }
            showDialog("�޸ĸ�����ʦ",900,550,"xyfd_fdjswh.do?method=updatefdjs&djh="+rows[0]["djh"] );

        }

        function jsLink(cellValue,rowObject) {
            var djh = rowObject["djh"];
            return "<a href='javascript:void(0);' onclick=\"fdjsShow('"+djh+"')\" class='name'>"+cellValue+"</a>";
        }
        function fdjsShow(djh) {
            showDialog("�鿴������ʦ",800,550,"xyfd_fdjswh.do?method=fdjsView&djh="+djh );
        }

        function deleteFdjs(){
            var ids = jQuery("#dataTable").getSeletIds();
            if (ids.length == 0) {
                showAlertDivLayer("��ѡ����Ҫɾ���ļ�¼��");
            } else {
                var rows = jQuery("#dataTable").getSeletRow();
                showConfirmDivLayer("��ȷ��Ҫɾ��ѡ��ļ�¼��", {
                    "okFun" : function() {
                        jQuery.post("xyfd_fdjswh.do?method=deleteFdjs", {
                            values : ids.toString()
                        }, function(data) {
                            var mes="�ɹ�ɾ����<font color='green'>&nbsp;"+data["num"]+"&nbsp;</font>������";
                            mes+="</br>";
                            if(data["nodel"]!="-1"){
                                mes+="<font color='red'>"+data["nodel"]+"</font>";
                                mes+="���������в���ɾ��!";
                            }
                            showAlertDivLayer(mes);
                            jQuery("#dataTable").reloadGrid();
                        }, 'json');
                    }
                });
            }
        }
    </script>
</head>
<body>
<div class="tab_cur">
    <p class="location">
        <em>���ĵ�ǰλ�ã�</em><a>${title }</a>
    </p>
</div>
<html:form action="/hdgl_hdgl_tj">
    <%@ include file="/comm/hiddenValue.jsp"%>
    <div class="toolbox">
        <!-- ��ť -->
        <div class="buttonbox">
            <ul>
                <li><a href="#" class="btn_zj" onclick="add();return false;">����</a></li>
                <li><a href="#" class="btn_xg" onclick="update();return false;">�޸�</a></li>
                <li><a href="#" class="btn_sc" onclick="deleteFdjs();return false;">ɾ��</a></li>
                <%--<li><a href="#" class="btn_dr" onclick="importConfig();return false;">����</a></li>--%>
                <%--<li><a href="#" class="btn_dc" onclick="exportConfig();return false;">����</a></li>--%>
                    <%--<li><a href="#" class="btn_dc" onclick="return false;">���ͱ�������</a></li>--%>
            </ul>
        </div>
        <!-- �������� -->
        <%@ include file="/comm/search/superSearchArea.jsp"%>
        <!-- �������� end-->
    </div>
</html:form>

<div class="main_box">
    <h3 class=datetitle_01>
        <span>�ǩ��&nbsp;&nbsp; </span>
    </h3>
    <div class="con_overlfow">
        <table id="dataTable" ></table>
        <div id="pager"></div>
    </div>
</div>
</body>
</html>
