<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<%@ include file="/syscommon/head.ini"%>
	<script type='text/javascript' src="js/uicomm.js"></script>
	<script type='text/javascript' src="js/String.js"></script>
	<script type='text/javascript' src="js/xgutil.js"></script>
	<script type="text/javascript" src="js/calendar/calendar.js"></script>
	<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
	<script>
        //��ʼ��
        jQuery(document).ready(function(){
            var gridSetting = {
                caption:"ѧ����λ�б�",
                pager:"pager",
                url:"qgzx_cxtj.do?method=xsgzjlMxCx&type=query&xh="+jQuery("#xh").val(),
                colList:[
                    {label:'gwdm',name:'gwdm', index: 'gwdm',hidden:true},
                    {label:'��λ����',name:'yrdwmc', index: 'yrdwmc',width:'13%'},
                    {label:'Ͷ�ݸ�λ',name:'gwmc', index: 'gwmc',width:'7%'},
                    {label:'Ͷ��ʱ��',name:'sqsj', index: 'sqsj',width:'10%'},
                    {label:'¼��ʱ��',name:'sgsj', index: 'sgsj',width:'10%'},
                    {label:'����ʱ��',name:'gzsc', index: 'gzsc',width:'7%'},
                    {label:'�ڸ�״̬',name:'zgzt', index: 'zgzt',width:'7%',formatter:function(cell,row){
                        if(cell == "zg"){
                            return "�ڸ�";
                        } else {
                            return "����ְ";
                        }
                    }},
                    {label:'��ְʱ��',name:'tgsj', index: 'tgsj',width:'10%'}
                ],
                sortname: "sqsj",
                sortorder: "desc"
            };
            var map = getSuperSearch();
            gridSetting["params"] = map;
            jQuery("#dataTable").initGrid(gridSetting);
        });

        function searchRs(){
            var map = getSuperSearch();
            jQuery("#dataTable").reloadGrid(map);
        }

        function yrdwwhExportConfig() {
            //DCCLBH�������ܱ��,ִ�е�������
            customExport("qgzx_cxtj_xsgzjlMxCx", yrdwwhExportData);
        }

        // ��������
        function yrdwwhExportData() {
            //setSearchTj();//���ø߼���ѯ����
            var url = "qgzx_cxtj.do?method=xsgzjlMxCxExportData&dcclbh=qgzx_cxtj_xsgzjlMxCx?method=xsgzjlMxCx&xh="+jQuery("#xh").val();//dcclbh,�������ܱ��
            //url = addSuperSearchParams(url);//���ø߼���ѯ����
            jQuery("form").eq(0).attr("action", url);
            jQuery("form").eq(0).submit();
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
        function back(){
            window.location.href = "qgzx_cxtj.do?method=xsgzjlCx"
		}
	</script>
</head>
<body>
<div class="tab_cur" >
	<p class="location">
		<em>���ĵ�ǰλ��:</em><a>${title }</a>
	</p>
	<p class="help">
		<a href="#" onclick="return false;" onmousedown ="showHelpDiv()">ʹ�ð���</a>
	</p>
</div>

<html:form action="/qgzx_gwglnew" method="post">
	<!-- ������ -->
	<%@ include file="/comm/hiddenValue.jsp"%>
	<input type="hidden" value="${xh}" id="xh">
	<!-- ������ -->
	<div class="toolbox" id="dgncz">
		<!-- ��ť -->
		<div class="buttonbox">
			<ul>
				<li><a href="#" onclick="ckGzsc();return false;" class="btn_ck">�鿴����ʱ��</a></li>
				<li><a href="#" onclick="yrdwwhExportConfig();return false;" class="btn_dc">����</a></li>
				<li><a href="#" onclick="back();return false;" class="btn_fh">����</a></li>
			</ul>
		</div>
		<!-- �������� -->
		<%@ include file="/comm/search/superSearchArea.jsp"%>
	</div>

</html:form>
<div class="formbox">
	<!--����start-->
	<h3 class="datetitle_01">
		<span> ѧ����λ��Ϣ�б� </span>
	</h3>

	<table id="dataTable" ></table>
	<div id="pager"></div>

</div>
</body>
</html>
