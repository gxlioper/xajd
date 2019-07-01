<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript"
			src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="pjpy/jtpj/jtpjsq/js/jtpjsq.js"></script>
		<script type="text/javascript">
			function getWord(){
				var rows = jQuery("#dataTable").getSeletRow();
				
				 if (rows.length == 0){
					showAlertDivLayer("��ѡ����Ҫ���صļ�¼��");
				 } else if (rows.length > 1){
					var ids = jQuery("#dataTable").getSeletIds();
					var url="jtpjsq.do?method=getDjbZip&value="+ids;
					window.open(url);
				 } else {
					var url="jtpjsq.do?method=getDjbWord&sqid="+rows[0]["sqid"];
					window.open(url);
			     }
			}
			//ͨ�õǼǱ����ع���
			function getDjb(){
				var rows = jQuery("#dataTable").getSeletRow();
				
				 if (rows.length == 0){
					showAlertDivLayer("��ѡ����Ҫ���صļ�¼��");
				 } else if (rows.length > 1){
					var ids = jQuery("#dataTable").getSeletIds();
					var url="jtpjsq.do?method=getDjbZip&value="+ids;
					window.open(url);
				 } else {
					var url="jtpjsq.do?method=getDjbWord&sqid="+rows[0]["sqid"];
					window.open(url);
			     }
			}
			jQuery(function(){
			     var gridSetting = {
							pager:"pager",
							url:"jtpjsq.do?method=list&type=query",
							params:getSuperSearch(),
							colList:[
							   {label:'��������id',name:'sqid', index: 'sqid',key:true,hidden:true},
							   {label:'��������',name:'splcid', index: 'splcid',hidden:true},
							   {label:'���뼯�� ',name:'pjjtmc', index: 'pjjtmc',formatter:pjjtmc},
							   {label:'����',name:'rs', index: 'rs'},
							   {label:'���뽱������',name:'jxmc', index: 'jxmc'},
							   {label:'����ѧ��',name:'pdxn', index: 'pdxn',hidden:true},
							   {label:'����ѧ��',name:'pdxqmc', index: 'pdxqmc',hidden:true},
							   {label:'������������',name:'jxpdzq', index: 'pdxn,pdxq',formatter:jxpdzq},
							   {label:'����ʱ��',name:'sqsj', index: 'sqsj'},
							   {label:'���״̬',name:'shzt', index: 'shzt',hidden:true},
							   {label:'���뽱��ID',name:'jxid', index: 'jxid',hidden:true},
							   {label:'���״̬',name:'shztmc', index: 'shzt',formatter:shzt}
							],
							sortname: "pdxn,pdxq,sqsj",
						 	sortorder: "desc"
						}
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
		<html:form action="/jtpjsq.do?method=list&type=query">
			<%@ include file="/comm/hiddenValue.jsp"%>
			<div class="toolbox">
				<!-- ��ť -->
				<div class="buttonbox">
				<ul>
					<logic:equal name="writeAble" value="yes">	
						<li>
							<a href="javascript:void(0);" onclick="add();return false;" class="btn_zj">����</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="update();return false;" class="btn_xg">�޸�</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="del();return false;" class="btn_sc">ɾ��</a>
						</li>
						<li style="display:none">
							<a href="javascript:void(0);" onclick="tj();return false;" class="btn_tj">�ύ</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="cancle();return false;" class="btn_sr">����</a>
						</li>
					</logic:equal>
					<li><a href="javascript:void(0);" onclick="rcxwshLcinfo();return false;" 
						   title="ѡ��һ����¼������ð�ť���Բ鿴������̡�"
						   class="btn_cs">���̸���</a></li>
					<!-- �Ϻ����ѧԺ begin -->
					<logic:equal name="xxdm" value="11458">	
						<li><a href="javascript:void(0);" onclick="getWord();return false;" class="btn_down">���صǼǱ�</a></li>	
					</logic:equal>
					<logic:notEqual name="xxdm" value="11458">	
					<logic:notEqual name="xxdm" value="11067">	
						<li><a href="javascript:void(0);" onclick="getDjb();return false;" class="btn_down">���صǼǱ�</a></li>	
					</logic:notEqual>
					</logic:notEqual>
					<!-- �Ϻ����ѧԺ end -->
				</ul>
				</div>
				<!-- �������� -->
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- �������� end-->
			</div>
		</html:form>
		<div class="toolbox">
			<!--����start-->
			<h3 class="datetitle_01">
				<span style="width: 50%">�������������б�</span>
			</h3>
			<table id="dataTable"></table>
			<div id="pager"></div>
		</div>
	</body>
</html>
