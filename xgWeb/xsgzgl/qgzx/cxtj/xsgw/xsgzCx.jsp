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
                caption:"ѧ�������б�",
                pager:"pager",
                url:"qgzx_cxtj.do?method=xsgzCx&type=query",
                colList:[
                    {label:'ѧ��',name:'xh', index: 'xh',key:true,width:'13%',formatter:function(cell,row){
                        return "<a href='javascript:void(0);' class='name' onClick='seeInfo(\""+row["xh"]+"\")'>"+cell+"</a>";
					}},
                    {label:'����',name:'xm', index: 'xm',width:'13%'},
                    {label:'�༶',name:'bjmc', index: 'bjmc',width:'7%'},
                    {label:'���',name:'nf', index: 'bf',width:'7%'},
                    {label:'�·�',name:'yf', index: 'yf',width:'7%'},
                    {label:'��ʱ',name:'gs', index: 'gs',width:'7%'},
                    {label:'���ʣ�Ԫ��',name:'je', index: 'je',width:'7%'},
                    {label:'���˵�λ',name:'yrdw', index: 'yrdw',width:'7%'},
                    {label:'��������',name:'yhmc', index: 'yhmc',width:'10%'},
                    {label:'���п���',name:'yhkh', index: 'yhkh',width:'15%'}
                ],
                sortname: "xh",
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
            customExport("qgzx_cxtj_xsgzCx", yrdwwhExportData);
        }

        // ��������
        function yrdwwhExportData() {
            setSearchTj();//���ø߼���ѯ����
            var url = "qgzx_cxtj.do?method=xsgzCxExportData&dcclbh=qgzx_cxtj_xsgzCx";//dcclbh,�������ܱ��
            url = addSuperSearchParams(url);//���ø߼���ѯ����
            jQuery("form").eq(0).attr("action", url);
            jQuery("form").eq(0).submit();
        }
        function seeInfo(xh){
			showDialog("���ʷ��Ų鿴",765,500,"qgzx_cxtj.do?method=xsgzffCk&xh="+xh);
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
				<li><a href="#" onclick="yrdwwhExportConfig();return false;" class="btn_dc">������ѧ������</a></li>
			</ul>
		</div>
		<!-- �������� -->
		<%@ include file="/comm/search/superSearchArea.jsp"%>
	</div>

</html:form>
<div class="formbox">
	<!--����start-->
	<h3 class="datetitle_01">
		<span> ѧ�������б� </span>
	</h3>

	<table id="dataTable" ></table>
	<div id="pager"></div>

</div>
</body>
</html>
