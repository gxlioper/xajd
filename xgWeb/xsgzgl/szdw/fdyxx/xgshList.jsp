<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/szdw/fdyxx/js/xgsh.js"></script>
		<script type="text/javascript">

		var gridSetting = {
				caption : "����Ա��Ϣ�޸�����б�",
				pager : "pager",
				url : "szdw_fdyxx.do?method=getXgshList",
				colList : [ {label : 'key',name : 'sqid',index : 'sqid',key : true,hidden : true}, 
				            {label : 'ְ����',name : 'zgh',index : 'zgh',width : '12%',formatter : setXh},
							{label : '����',name : 'xm',index : 'xm',width : '10%'}, 
							{label : '�Ա�',name : 'xbmc',index : 'xb',width : '5%'},
							{label : '����',name : 'bmmc',index : 'bmmc',width : '10%'}, 
							{label : '����ʱ��',name : 'xgsj',index : 'xgsj',width : '18%'}, 
							{label : 'gwid',name : 'gwid',index : 'gwid',hidden : true}, 
							{label : 'lcid',name : 'lcid',index : 'lcid',hidden : true}, 
							{label : 'ywid',name : 'ywid',index : 'ywid',hidden : true}, 
							{label : '��˸�λ',name : 'mc',index : 'mc',hidden : true}, 
							{label : '���id',name : 'guid',index : 'guid',hidden : true}, 
							{label : '���״̬',name : 'shzt',index : 'shzt',width : '15%',formatter : setShzt} 
				],
				params : {
					shzt : "dsh"
				},// Ĭ�ϴ����
				sortname : "xgsj",
				sortorder : "desc"
			};
			var gridSetting2 = {
				caption : "����Ա��Ϣ�޸�����б�",
				pager : "pager",
				url : "szdw_fdyxx.do?method=getXgshList",
				colList : [ {label : 'key',name : 'sqid',index : 'sqid',key : true,hidden : true}, 
				            {label : 'ְ����',name : 'zgh',index : 'zgh',width : '12%',formatter : setXh}, 
				            {label : '����',name : 'xm',index : 'xm',width : '10%'}, 
				            {label : '�Ա�',name : 'xbmc',index : 'xb',width : '5%'}, 
				            {label : '����',name : 'bmmc',index : 'bmmc',width : '10%'}, 
				            {label : '���ʱ��',name : 'shsj',index : 'shsj',width : '18%'}, 
				            {label : 'gwid',name : 'gwid',index : 'gwid',hidden : true}, 
				            {label : '���id',name : 'guid',index : 'guid',hidden : true}, 
				            {label : 'lcid',name : 'lcid',index : 'lcid',hidden : true}, 
				            {label : 'ywid',name : 'ywid',index : 'ywid',hidden : true}, 
				            {label : 'shjg',name : 'shjg',index : 'shjg',hidden : true}, 
				            {label : '��˸�λ',name : 'mc',index : 'mc',hidden : true}, 
				            {label : '���״̬',name : 'shzt',index : 'shzt',width : '15%',formatter : setShzt} 
				],
				params : {
					shzt : "ysh"
				},// �����
				sortname : "shsj",
				sortorder : "desc"
			};
			jQuery(function() {
				var map = getSuperSearch();
				map["shzt"] = "dsh";
				gridSetting["params"] = map;
				jQuery("#dataTable").initGrid(gridSetting);
			});
		
		</script>
	</head>

	<body style="min-height: 800px;">
	
		<input type="hidden" value="dsh" id="shzt"/>
	
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>${title }</a>
			</p>
		</div>
		<html:form action="/xszz_jtqkdc">
			<input type="hidden" id="xbmc" value="<bean:message key="lable.xb" />"/>
			<%@ include file="/comm/hiddenValue.jsp"%>
			<div class="toolbox">
				<!-- ��ť -->
					
				<div class="buttonbox">
					<ul>
					<logic:equal name="writeAble" value="yes">
						<li id="li_sh">
							<a href="javascript:void(0);" onclick="xgsh();return false;" 
							   title="ѡ����Ҫ��˵ļ�¼������ð�ť���Դ����ҳ�档"
							   class="btn_sh">���</a>
						</li>						
						<li id="li_qx" style="display: none;">
							<a href="javascript:void(0);" onclick="cxshnew();return false;" 
							   title="ѡ��һ����¼������ð�ť�����Գ���ʧ�����˲�����"
							   class="btn_qxsh">����</a>
						</li>		
						</logic:equal>				
						<li><a href="javascript:void(0);" onclick="shlcInfo();return false;" 
							   title="ѡ��һ����¼������ð�ť���Բ鿴������̡�"
							   class="btn_cs">���̸���</a></li>			
					</ul>
				</div>
				<!-- �������� -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- �������� end-->
				<div class="comp_title" id="comp_title">
			      <ul style="width:90%">
			        <li class="ha"><a href="javascript:void(0);" onclick="selectTab(this,'dsh');"><span>�����</span></a></li>
			        <li><a href="javascript:void(0);" onclick="selectTab(this,'ysh');"><span>�����</span></a></li>
			      </ul>
			    </div>
			</div>
		</html:form>
		
		<div class="main_box">
			<h3 class=datetitle_01>
				<span>����Ա��Ϣ�޸�����б�&nbsp;&nbsp; </span>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable" ></table>
				<div id="pager"></div>
			</div>
		</div>
	</body>
</html>
