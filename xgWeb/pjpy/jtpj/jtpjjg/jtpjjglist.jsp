<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript"
			src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="pjpy/jtpj/jtpjjg/js/jtpjjg.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/exportNew/import.js"></script>
		<script type="text/javascript">
			function getWord(){
				var rows = jQuery("#dataTable").getSeletRow();
				
				 if (rows.length == 0){
					showAlertDivLayer("��ѡ����Ҫ���صļ�¼��");
				 } else if (rows.length > 1){
					var flag = false;
					 for(var i = 0; i < rows.length; i++){
						 if(rows[i]["jtpjdw"] != 'bj'){
							 flag = true;
						 }
					 }
					 if(flag){
						 showAlertDivLayer("��ѡ���԰༶Ϊ��λ�ļ�¼��");
						 return false;
					 }
					var ids = jQuery("#dataTable").getSeletIds();
					var url="jtpjjg.do?method=getDjbZip&value="+ids;
					window.open(url);
				 } else {
					 if(rows[0]["jtpjdw"] != 'bj'){
						 showAlertDivLayer("��ѡ���԰༶Ϊ��λ�ļ�¼��");
						 return false;
					 }
					var url="jtpjjg.do?method=getDjbWord&jgid="+rows[0]["jgid"];
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
					var url="jtpjjg.do?method=getDjbZip&value="+ids;
					window.open(url);
				 } else {
					var url="jtpjjg.do?method=getDjbWord&jgid="+rows[0]["jgid"];
					window.open(url);
			     }
			}
			jQuery(function(){
			     var gridSetting = {
							pager:"pager",
							url:"jtpjjg.do?method=list&type=query",
							params:getSuperSearch(),
							colList:[
							   {label:'���id',name:'jgid', index: 'jgid',key:true,hidden:true},
							   {label:'�������� ',name:'pjjtmc', index: 'pjjtmc',formatter:pjjtmc},
							   {label:'����',name:'rs', index: 'rs'},
							   {label:'��������',name:'jxmc', index: 'jxmc'},
							   {label:'����ѧ��',name:'sqxn', index: 'sqxn',hidden:true},
							   {label:'����ѧ��',name:'sqxqmc', index: 'sqxqmc',hidden:true},
							   {label:'��������',name:'sqzq', index: 'sqxn,sqxq',formatter:sqzq},
							   <logic:equal name="xxdm" value="10466">
								{label:'����ʱ��',name:'sqsj',index:'sqsj',width:'15%'},
							   </logic:equal>
							   {label:'����ѧ��',name:'pdxn', index: 'pdxn',hidden:true},
							   {label:'����ѧ��',name:'pdxq', index: 'pdxq',hidden:true},
							   {label:'����ѧ��',name:'pdxqmc', index: 'pdxqmc',hidden:true},
							   {label:'����������λ',name:'jtpjdw', index: 'jtpjdw',hidden:true},
							   {label:'��������',name:'jxpdzq', index: 'pdxn,pdxq',formatter:jxpdzq},
							   {label:'���������',name:'sjly', index: 'sjly',formatter:sjly}
							],
							sortname: "pdxn,pdxq",
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
		<html:form action="/jtpjjg.do?method=list&type=query">
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
							<li>
								<a href="#" onclick="drxx();return false;" class="btn_csh">����</a>
							</li>
							</logic:equal>
							<li><a href="javascript:void(0);" class="btn_dc" onclick="exportConfig();return false;">����</a></li>	
							<!-- �Ϻ����ѧԺ begin -->
							<logic:equal name="xxdm" value="11458">	
								<li><a href="javascript:void(0);" onclick="getWord();return false;" class="btn_down">���صǼǱ�</a></li>	
							</logic:equal>
							<!-- �Ϻ����ѧԺ end -->
							
							<!-- Ϋ��ѧԺ begin -->
							<logic:equal name="xxdm" value="11067">	
								<li><a href="javascript:void(0);" onclick="getWord();return false;" class="btn_down">�Ƚ��༯��ǼǱ��ӡ</a></li>	
							</logic:equal>
							<!-- Ϋ��ѧԺ end -->
							<logic:notEqual name="xxdm" value="11458">	
							<logic:notEqual name="xxdm" value="11067">	
								<li><a href="javascript:void(0);" onclick="getDjb();return false;" class="btn_down">���صǼǱ�</a></li>	
							</logic:notEqual>
							</logic:notEqual>
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
				<span style="width: 50%">������������б�</span>
			</h3>
			<table id="dataTable"></table>
			<div id="pager"></div>
		</div>
	</body>
</html>
