<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript">

		jQuery(function(){
			var gridSetting = {
					caption:"ѧ���б�",
					pager:"pager",
					url:"xszz_xszzbjpy_xzszgl.do?method=addXzsz&type=query&bjdm=${rs.bjdm}",
					colList:[      
				         {label:'key',name:'xh', index: 'xh',hidden:true,key:true},
						   {label:'ѧ��',name:'xh', index: 'xh',width:'10%'},
						   {label:'����',name:'xm', index: 'xm',width:'8%'},
						   {label:'�꼶',name:'nj', index: 'nj',width:'7%'},
						   {label:'<bean:message key="lable.xb" />',name:'xymc', index: 'xydm',width:'16%'},
						   {label:'רҵ',name:'zymc', index: 'zydm',width:'22%'},
						   {label:'�༶',name:'bjmc', index: 'bjdm',width:'22%'},
						   {label:'�༶',name:'bjdm', index: 'bjdm',hidden:true}
						],
						sortname: "xh",
					 	sortorder: "asc"
				};
			gridSetting["params"]=getSuperSearch();
			jQuery("#dataTable").initGrid(gridSetting);
		});

		function searchRs(){
			var map = getSuperSearch();
			jQuery("#dataTable").reloadGrid(map);
		}

		function addXzszBc() {
			var ids = jQuery("#dataTable").getSeletIds();
			var rows = jQuery("#dataTable").getSeletRow();
			if (ids.length == 0) {
				showAlertDivLayer("��ѡ��ѧ����");
			} else {
				jQuery.post("xszz_xszzbjpy_xzszgl.do?method=addXzszBc", { values : ids.toString(), bjdm: "${rs.bjdm}" },
						function(data) {
							if(data["message"]=="����ɹ���"){
					    		 showAlert(data["message"],{},{"clkFun":function(){
										if (parent.window){
											refershParent();
										}
									}});
					    	 }else{
					    		 showAlert(data["message"]);
					    	 }
						}, 'json');
			}
		}

		</script>
	</head>

	<body>
		<html:form action="/xszz_xszzbjpy_xzszgl">
			<input type="hidden" id="xymc" value="<bean:message key="lable.xy" />"/>
			<%@ include file="/comm/hiddenValue.jsp"%>
			<div class="prompt">
				<h3>
					<span>��ʾ��</span>
				</h3>
				<p>
					<font color="red">����</font>ѧ���󣬰༶����С����ύ״̬��Ϊ<font color="red">δ�ύ</font>�������½���<font color="red">�ύ</font>������
				</p>
				<a class="close" title="����" onclick="this.parentNode.style.display='none';"></a>
			</div>
			<div class="toolbox">
				<!-- ��ť -->
				<div class="buttonbox">
					<ul>
						<li>
							<a href="javascript:void(0);" class="btn_ccg" onclick="addXzszBc();return false;" >����</a>
						</li>
					</ul>
				</div>
				<!-- �������� -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- �������� end-->
			</div>
		</html:form>
		
		<div class="main_box">
			<h3 class=datetitle_01>
				<span>ѧ���б�&nbsp;&nbsp; </span>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable" ></table>
				<div id="pager"></div>
			</div>
		</div>
	</body>
</html>
