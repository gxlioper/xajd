<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="xsgzgl/xszz/jtqkdc/js/jtqkdc.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript">
			var gridSetting = {
				caption:"��ͥ�����Ϣ�б�",
				pager:"pager",
				url:"xszz_jtqkdc.do?method=dcxxManage&type=query",
				colList:[
				   {label:'ѧ��',name:'xh', index: 'xh',width:'13%',formatter:xhLink,key:true},
				   {label:'����',name:'xm', index: 'xm',width:'10%'},
				   {label:'�Ա�',name:'xb', index: 'xb',width:'5%'},
				   {label:'<bean:message key="lable.xb" />',name:'xymc', index: 'xydm',width:'20%'},
                    {label:'��Ժ',name:'symc', index: 'symc',width:'20%'},
				   {label:'רҵ',name:'zymc', index: 'zydm',width:'20%'},
				   {label:'�����༶',name:'bjmc', index: 'bjdm',width:'15%'},
                    {label:'רҵ�༶',name:'zybjmc', index: 'zybjmc',width:'15%'},
				   {label:'����ʱ��',name:'dcsj', index: 'dcsj',width:'12%'}
				],
				sortname: "dcsj",
			 	sortorder: "desc"
			};
			
			jQuery(function(){
				
<%--				jQuery.ajaxSetup({async:false,cache:true});--%>
<%--					var initSetting = getQuerySetting("XSZZ_JTQKDC");--%>
<%--					gridSetting = jQuery.extend(gridSetting,initSetting || {});--%>
<%--				jQuery.ajaxSetup({async:true});--%>
				
				jQuery("#dataTable").initGrid(gridSetting);
			});

			
			function getWord(){
				
				var rows = jQuery("#dataTable").getSeletRow();
				
				 if (rows.length == 0){
					showAlertDivLayer("��ѡ��һ����¼��");
				 } else if (rows.length > 1){
					var url="xszz_jtqkdc.do?method=getJtqkdcZip";
					var ids = jQuery("#dataTable").getSeletIds();
					var url= url+"&value="+ids;
					window.open(url);
				 } else {
					var url="xszz_jtqkdc.do?method=getJtqkdcWord";
					
					var url= url+"&xh="+rows[0]["xh"];
					window.open(url);
			     }
			}
			// ��ӡ��չ�滮��������������ݴ�ѧ��
			function getFzghyzzsqbWord(){
				
				var rows = jQuery("#dataTable").getSeletRow();
				
				 if (rows.length == 0){
					showAlertDivLayer("��ѡ��һ����¼��");
				 } else if (rows.length > 1){
					var url="xszz_jtqkdc.do?method=getFzghyzzsqbZip";
					var ids = jQuery("#dataTable").getSeletIds();
					var url= url+"&value="+ids;
					window.open(url);
				 } else {
					var url="xszz_jtqkdc.do?method=getFzghyzzsqbWord";
					
					var url= url+"&xh="+rows[0]["xh"];
					window.open(url);
			     }
			}
			
			
			function jtknmd(){
				showDialog("�������ѱ�׼", 500, 700, "xszz_jtqkdc.do?method=jtknmd");
			}
			
		</script>
	</head>

	<body>
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>${title }</a>
			</p>
		</div>
		<html:form action="/xszz_jtqkdc">
			<% String xxdm = (String) request.getAttribute("xxdm"); %>
			<input type="hidden" name="tableName" id="tableName" value="view_xg_xszz_new_jtqkdcb"/>
			<%@ include file="/comm/hiddenValue.jsp"%>
			<div class="toolbox">
				<!-- ��ť -->
				
				<div class="buttonbox">
					<ul>
						<logic:equal name="writeAble" value="yes">	
							<li><a href="javascript:void(0);" onclick="showDialog('��ͥ�������',945,500,'xszz_jtqkdc.do?method=dcxxModify');return false;" class="btn_zj">����</a></li>
							<li><a href="javascript:void(0);" onclick="jtqkUpdate();return false;" class="btn_xg">�޸�</a></li>
							<li><a href="javascript:void(0);" onclick="jtqkDelete();return false;" class="btn_sc">ɾ��</a></li>	
						</logic:equal>						
<%--						<li><a href="javascript:void(0);" onclick="printJtqkdc();return false;" class="btn_dy">��ӡ����</a></li>						--%>
						<logic:equal value="13431" name="xxdm">
							<li><a href="javascript:void(0);" onclick="getWord();return false;" class="btn_down">���ؼ�ͥ��������</a></li>	
						</logic:equal>
						<logic:notEqual name="xxdm" value="10264">
							<logic:notEqual value="13431" name="xxdm">
								<li><a href="javascript:void(0);" onclick="getWord();return false;" class="btn_down">���صǼǱ�</a></li>	
							</logic:notEqual>
						</logic:notEqual>					
						<logic:equal name="xxdm" value="10351">			
						<!-- ��ӡ��չ�滮��������������ݴ�ѧ�� begin -->
							<li><a href="javascript:void(0);" onclick="getFzghyzzsqbWord();return false;" class="btn_down">���ص����</a></li>						
						<!-- ��ӡ��չ�滮��������������ݴ�ѧ�� end -->			
						</logic:equal>
						
						<logic:equal value="10335" name="xxdm">
							<logic:equal value="zf01" name="userName">
								<li>
									<a href="#" class="btn_dc" onclick="exportConfig();return false;">����</a>
								</li>
							</logic:equal>
						</logic:equal>
						<logic:notEqual value="10335" name="xxdm">
							<li>
								<a href="#" class="btn_dc" onclick="exportConfig();return false;">����</a>
							</li>
						</logic:notEqual>
					</ul>
				</div>
				
				<!-- �������� -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- �������� end-->
			</div>
		</html:form>
		
		<div class="main_box">
			<h3 class=datetitle_01>
				<span>ѧ����ͥ���������Ϣ�б�&nbsp;&nbsp; </span>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable" ></table>
				<div id="pager"></div>
			</div>
		</div>
	</body>
</html>
