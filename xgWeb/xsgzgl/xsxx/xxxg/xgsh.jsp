<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/xsxx/xxxg/js/xgsh.js"></script>
		<script type="text/javascript">

		var gridSetting = {
				caption : "ѧ����Ϣ�޸�����б�",
				pager : "pager",
				url : "xsxx_xsxxxg.do?method=xgsh&type=query",
				colList : [ {
					label : 'key',
					name : 'sqid',
					index : 'sqid',
					key : true,
					hidden : true
				}, {
					label : 'ѧ��',
					name : 'xh',
					index : 'xh',
					width : '12%',
					formatter : setXh
				}, {
					label : '����',
					name : 'xm',
					index : 'xm',
					width : '10%'
				}, {
					label : '�Ա�',
					name : 'xb',
					index : 'xb',
					width : '5%'
				}, {
					label : '<bean:message key="lable.xb" />',
					name : 'xymc',
					index : 'xydm',
					width : '20%'
				}, {
					label : '�༶',
					name : 'bjmc',
					index : 'bjdm',
					width : '20%'
				}, 
				<logic:equal name='xxdm' value='12309'>
				{
					label : '����Ա',
					name : 'fdy',
					index : 'fdy',
					width : '10%'
				}, 
				</logic:equal>
				{
					label : '����ʱ��',
					name : 'xgsj',
					index : 'xgsj',
					width : '18%'
				}, {
					label : 'gwid',
					name : 'gwid',
					index : 'gwid',
					hidden : true
				}, {
					label : 'lcid',
					name : 'lcid',
					index : 'lcid',
					hidden : true
				}, {
					label : 'ywid',
					name : 'ywid',
					index : 'ywid',
					hidden : true
				}, {
					label : '��˸�λ',
					name : 'mc',
					index : 'mc',
					hidden : true
				}, {
					label : '���id',
					name : 'guid',
					index : 'guid',
					hidden : true
				}, {
					label : '���״̬',
					name : 'shzt',
					index : 'shzt',
					width : '15%',
					formatter : setShzt
				} ],
				params : {
					shzt : "dsh"
				},// Ĭ�ϴ����
				sortname : "xgsj",
				sortorder : "desc"
			}
			var gridSetting2 = {
				caption : "ѧ����Ϣ�޸�����б�",
				pager : "pager",
				url : "xsxx_xsxxxg.do?method=xgsh&type=query",
				colList : [ {
					label : 'key',
					name : 'sqid',
					index : 'sqid',
					key : true,
					hidden : true
				}, {
					label : 'ѧ��',
					name : 'xh',
					index : 'xh',
					width : '12%',
					formatter : setXh
				}, {
					label : '����',
					name : 'xm',
					index : 'xm',
					width : '10%'
				}, {
					label : '�Ա�',
					name : 'xb',
					index : 'xb',
					width : '5%'
				}, {
					label : '<bean:message key="lable.xb" />',
					name : 'xymc',
					index : 'xydm',
					width : '15%'
				}, {
					label : '�༶',
					name : 'bjmc',
					index : 'bjdm',
					width : '20%'
				},
				<logic:equal name='xxdm' value='12309'>
				{
					label : '����Ա',
					name : 'fdy',
					index : 'fdy',
					width : '10%'
				}, 
				</logic:equal>
				 {
					label : '���ʱ��',
					name : 'shsj',
					index : 'shsj',
					width : '18%'
				}, {
					label : 'gwid',
					name : 'gwid',
					index : 'gwid',
					hidden : true
				}, {
					label : '���id',
					name : 'guid',
					index : 'guid',
					hidden : true
				}, {
					label : 'lcid',
					name : 'lcid',
					index : 'lcid',
					hidden : true
				}, {
					label : 'ywid',
					name : 'ywid',
					index : 'ywid',
					hidden : true
				}, {
					label : '��˸�λ',
					name : 'mc',
					index : 'mc',
					hidden : true
				}, {
					label : '���״̬',
					name : 'shzt',
					index : 'shzt',
					width : '15%',
					formatter : setShzt

				} ],
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
		<input type="hidden" name="shkg" id="shkg" value='${shkg}'/>
		
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
							<a href="javascript:void(0);" onclick="xgshZj();return false;" 
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
							   
						<logic:equal value="10335" name="xxdm">
							<logic:equal value="zf01" name="userName">
								<li>
									<a href="#" class="btn_dc" onclick="exportConfigSh();return false;">����</a>
								</li>
							</logic:equal>
						</logic:equal>
						<logic:notEqual value="10335" name="xxdm">
							<li>
								<a href="#" class="btn_dc" onclick="exportConfigSh();return false;">����</a>
							</li>
						</logic:notEqual>
		
					</ul>
				</div>
				<!-- �������� -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- �������� end-->
				<div class="comp_title" id="comp_title">
			      <ul style="width:90%">
			        <li class="ha"><a href="javascript:void(0);" onclick="selectTab(this,'dsh');"><span>������</span></a></li>
			        <li><a href="javascript:void(0);" onclick="selectTab(this,'ysh');"><span>�Ѵ���</span></a></li>
			      </ul>
			    </div>
			</div>
		</html:form>
		
		<div class="main_box">
			<h3 class=datetitle_01>
				<span>��Ϣ�޸�����б�&nbsp;&nbsp; </span>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable" ></table>
				<div id="pager"></div>
			</div>
		</div>
	</body>
</html>
