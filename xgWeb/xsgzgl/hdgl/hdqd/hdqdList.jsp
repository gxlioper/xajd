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
                url:"hdgl_hdgl_hdqd_wh.do?method=hdqdList&type=query",
                colList:[
                    {label:'id',name:'id', index: 'id',key:true,hidden:true },
                    {label:'ѧ��',name:'xh', index: 'xh',width:'15%'},
                    {label:'����',name:'xm', index: 'xm',width:'10%'},
                    {label:'�id',name:'hdid', index: 'hdid',hidden:true},
                    {label:'�����',name:'hdmc', index: 'hdmc',width:'8%'},
                    {label:'��ص�',name:'hddd', index: 'hddd',width:'8%'},
                    {label:'���ʼʱ��',name:'hdkssj', index: 'hdkssj',width:'8%'},
                    {label:'�����ʱ��',name:'hdjssj', index: 'hdjssj',width:'8%'},
                    {label:'ǩ��״̬',name:'qdztmc', index: 'qdztmc',width:'8%',formatter:function(cell,rowObject){
						if(rowObject["qdsj"]!=null&&rowObject["qdztmc"]=="δǩ��"){
							return "ǩ����Ч";
						}
					   return rowObject["qdztmc"];
					}},
                    {label:'ǩ��ʱ��',name:'qdsj', index: 'qdsj',width:'8%'},
                    {label:'ǩ��״̬',name:'qtztmc', index: 'qtztmc',width:'8%',formatter:function(cell,rowObject){
						if(rowObject["qtsj"]!=null&&rowObject["qtztmc"]=="δǩ��"){
							return "ǩ����Ч";
						}
					   return rowObject["qtztmc"];
					}},
                    {label:'ǩ��ʱ��',name:'qtsj', index: 'qtsj',width:'8%'}
                ]
            }
            gridSetting["params"]=getSuperSearch();
            jQuery("#dataTable").initGrid(gridSetting);
        });

        function searchRs(){
            var map = getSuperSearch();
            jQuery("#dataTable").reloadGrid(map);
        }
        //����
        function importConfig(){
            toImportDataNew("IMPORT_HDQDXX");
            return false;
        }


        var DCCLBH = "hdgl_hdgl_hdqd.do";//dcclbh,�������ܱ��

        //�Զ��嵼�� ����
        function exportConfig() {
            //DCCLBH�������ܱ��,ִ�е�������
            customExport(DCCLBH, ExportData);
        }

        // ��������
        function ExportData() {
            setSearchTj();//���ø߼���ѯ����
            var url = "hdgl_hdgl_hdqd_wh.do?method=export&dcclbh=" + DCCLBH;//dcclbh,�������ܱ��
            url = addSuperSearchParams(url);//���ø߼���ѯ����
            jQuery("form").eq(0).attr("action", url);
            jQuery("form").eq(0).submit();
        }
        function add(){
            showDialog("���ӻѧ��",800,500,"hdgl_hdgl_hdqd_wh.do?method=add");
        }
        function update(){
        	var rows = jQuery("#dataTable").getSeletRow();
        	if(rows.length!=1){
        		showAlertDivLayer("��ѡ��һ����Ҫ�޸ĵļ�¼");
				return false;
        	}
		
        	showDialog("�޸Ļѧ��",800,500,"hdgl_hdgl_hdqd_wh.do?method=updateqdView&hdid="+rows[0].hdid+
			"&xh="+rows[0].xh + "&id=" + rows[0].id);
        	
        }
		function deleteQd(){
			var rows = jQuery("#dataTable").getSeletRow();
			var qdxxs = new Array();
			if(rows.length<1){
				showAlertDivLayer("��ѡ����Ҫɾ���ļ�¼");
				return false;
			}
			for(var i=0;i<rows.length;i++){
				qdxxs.push(rows[i].xh+"_"+rows[i].hdid);
			}
			showConfirmDivLayer("�Ƿ�ȷ��ɾ����ѡ��"+rows.length+"����¼��", {
					"okFun" : function() {
					var url = "hdgl_hdgl_hdqd_wh.do?method=deleteQd";
					jQuery.post(url, {
						qdxxlist : qdxxs.toString()
					}, function(data) {
						if (data["success"] == false) {
							showAlertDivLayer(data["message"]);
						} else {
							showAlertDivLayer(data["message"], {}, {
								"clkFun" : function(tag) {
									jQuery("#dataTable").reloadGrid();
								}
							});
						}
					}, 'json');
				
			}});
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
                <li><a href="#" class="btn_sc" onclick="deleteQd();return false;">ɾ��</a></li>
                <li><a href="#" class="btn_dr" onclick="importConfig();return false;">����</a></li>
                <li><a href="#" class="btn_dc" onclick="exportConfig();return false;">����</a></li>
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
