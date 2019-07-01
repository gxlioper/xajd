<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/szdw/gzjl/gzjlsq/gzjlsq.js"></script>
		<script type="text/javascript">

		jQuery(function(){
			var gridSetting = {};
			if("11842"==jQuery("#xxdm").val()){
				gridSetting = {
						caption : "������¼��Ϣ�����б�",
						pager : "pager",
						url : "gzjlsq.do?method=gzjlsqList&type=query",
						colList : [ {
							label : 'key',
							name : 'sqid',
							index : 'sqid',
							key : true,
							hidden : true
						},{
							label : '��������',
							name : 'splc',
							index : 'splc',
							hidden : true
						}, {
							label : 'ְ����',
							name : 'zgh',
							index : 'zgh',
							width : '10%',
							formatter : zghLink
						},{
							label : '����',
							name : 'xm',
							index : 'xm',
							width : '10%'
						}, {
							label : '<bean:message key="lable.xb" />',
							name : 'xymc',
							index : 'xymc',
							width : '15%'
						}, {
							label : '�������',
							name : 'gzlbmc',
							index : 'gzlbmc',
							width : '10%'
						}, {
							label : '������',
							name : 'lksmc',
							index : 'lksmc',
							width : '13%'
						}, {
							label : '����ʱ��',
							name : 'gzsj',
							index : 'gzsj',
							width : '12%'
						}, {
							label : '��ע',
							name : 'bz',
							index : 'bz',
							width : '7%',
							hidden : true
						},{
							label : '��ע',
							name : 'bzstr',
							index : 'bzstr',
							width : '10%',
							formatter : setBz
						},{
							label : '��¼ʱ��',
							name : 'jlsj',
							index : 'jlsj',
							width : '20%'
						}, {
							label : '���״̬',
							name : 'shztmc',
							index : 'shztmc',
							width : '4%'
						}, {
							label : '��������',
							name : 'splc',
							index : 'splc',
							hidden : true
						}, {
							label : '���״̬',
							name : 'shzt',
							index : 'shzt',
							hidden : true
						}],
						sortname : "sqsj",
						sortorder : "desc"
					}
			   }else{
				   gridSetting = {
							caption : "������¼��Ϣ�����б�",
							pager : "pager",
							url : "gzjlsq.do?method=gzjlsqList&type=query",
							colList : [ {
								label : 'key',
								name : 'sqid',
								index : 'sqid',
								key : true,
								hidden : true
							},{
								label : '��������',
								name : 'splc',
								index : 'splc',
								hidden : true
							}, {
								label : 'ְ����',
								name : 'zgh',
								index : 'zgh',
								width : '10%',
								formatter : zghLink
							},{
								label : '����',
								name : 'xm',
								index : 'xm',
								width : '10%'
							}, {
								label : '<bean:message key="lable.xb" />',
								name : 'xymc',
								index : 'xymc',
								width : '15%'
							}, {
								label : '�������',
								name : 'gzlbmc',
								index : 'gzlbmc',
								width : '20%'
							},  {
								label : '����ʱ��',
								name : 'gzsj',
								index : 'gzsj',
								width : '15%'
							}, {
								label : '��ע',
								name : 'bz',
								index : 'bz',
								width : '7%',
								hidden : true
							},{
								label : '��ע',
								name : 'bzstr',
								index : 'bzstr',
								width : '10%',
								formatter : setBz
							},{
								label : '��¼ʱ��',
								name : 'jlsj',
								index : 'jlsj',
								width : '20%'
							}, {
								label : '���״̬',
								name : 'shztmc',
								index : 'shztmc',
								width : '4%'
							}, {
								label : '��������',
								name : 'splc',
								index : 'splc',
								hidden : true
							}, {
								label : '���״̬',
								name : 'shzt',
								index : 'shzt',
								hidden : true
							}],
							sortname : "sqsj",
							sortorder : "desc"
						}
			   }
			    
			var map = getSuperSearch();
			gridSetting["params"] = map;
			jQuery("#dataTable").initGrid(gridSetting);
		})
				
		</script>
	</head>

	<body>
		<input type="hidden" name="isopen" id="isopen" value="${jcszModel.isopen }"/>
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>${title }</a>
			</p>
		</div>
		<html:form action="/gzjlsq">
			<input type="hidden" name="sqkg" id="sqkg" value="${sqkg}"/>
			<%@ include file="/comm/hiddenValue.jsp"%>
			
			<div class="toolbox">
				<!-- ��ť -->
				<div class="buttonbox">
					<ul>
						<logic:equal name="writeAble" value="yes">
						<li>
							<a href="javascript:void(0);" class="btn_zj" onclick="add();return false;"  >��д</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="update();return false;" class="btn_xg" >�޸�</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="del();return false;" class="btn_sc" >ɾ��</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="submitBusi();return false;" class="btn_shuc">�ύ</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="cancel();return false;" class="btn_sr">����</a>
						</li>
						</logic:equal>
						<li>
							<a href="javascript:void(0);" onclick="lcgz();return false;" 
								   title="ѡ��һ����¼������ð�ť���Բ鿴������̡�"
								   class="btn_cs">���̸���</a>
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
				<span>������¼��Ϣ�����б�&nbsp;&nbsp; </span>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable" ></table>
				<div id="pager"></div>
			</div>
		</div>
	</body>
</html>
