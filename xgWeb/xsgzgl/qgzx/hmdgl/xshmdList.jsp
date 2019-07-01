<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/qgzx/hmdgl/js/hmdgl.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/exportNew/import.js"></script>
		<script type="text/javascript">
		jQuery(function(){
		var gridSetting = {
			caption:"�ڹ���ѧ�������б�",
			pager:"pager",
			url:"qgzxhmdgl.do?method=getxshmdList&type=query",
			colList:[
                {label:'key',name:'id', index: 'id',key:true ,hidden:true},
                {label:'ѧ��',name:'xh', index: 'xh',width:'8%'},
                {label:'����',name:'xm', index: 'xm',width:'8%'},
                {label:'���˵�λ',name:'mc', index: 'mc',width:'15%'},
				{label:'����ʱ��',name:'lhrq', index: 'lhrq',width:'15%'}
			],
			sortname: "lhrq",
			sortorder: "desc"
		}
		gridSetting["params"]=getSuperSearch();
		jQuery("#dataTable").initGrid(gridSetting);
});
		function addxshmd(){
            var url = "qgzxhmdgl.do?method=addxshmd";
            var title = "ѧ������������";
            showDialog(title, 700, 500, url);
		}
        // ɾ��
        function delHmd() {
            var ids = jQuery("#dataTable").getSeletIds();
            if (ids.length == 0) {
                showAlertDivLayer("��ѡ����Ҫɾ���ļ�¼��");
            } else {
                showConfirmDivLayer("��ȷ��Ҫɾ��ѡ��ļ�¼��", {
                    "okFun" : function() {
                        jQuery.post("qgzxhmdgl.do?method=delXsHmd", {
                                values : ids.toString()
                            },
                            function(data) {
                                showAlertDivLayer(data["message"]);
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
		<html:form action="/qgzxhmdgl">
		<%@ include file="/comm/hiddenValue.jsp"%>
			<div class="toolbox">
				<!-- ��ť -->
				<div class="buttonbox">
					<ul>
						<logic:equal name="writeAble" value="yes">	
						<li>
							<a href="javascript:void(0);" class="btn_zj"
							   onclick="addxshmd();return false;" >����</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="delHmd();return false;" class="btn_sc"
							>ɾ��</a>
						</li>
						</logic:equal>
					</ul>
				</div>
				<!-- �������� -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- �������� end-->
			</div>
		</html:form>
		
		<div class="main_box">
			<h3 class=datetitle_01>
				<span>�ڹ���ѧѧ���������б�&nbsp;&nbsp; </span>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable" ></table>
				<div id="pager"></div>
			</div>
		</div>
	</body>
</html>
