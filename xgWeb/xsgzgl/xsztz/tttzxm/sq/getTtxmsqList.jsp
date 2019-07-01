<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/xsztz/tttzxm/sq/js/tttzxm.js"></script>
		<script type="text/javascript" src="xsgzgl/xsztz/tttzxm/comm/js/comm.js"></script>
		<script type="text/javascript">
		jQuery(function(){
			var gridSetting = {
				caption : "������չ��Ŀ�����б�",
				pager : "pager",
				url : "ttxm_sq.do?method=getTtxmsqList&type=query",
				colList : [ {
					label : 'key',
					name : 'ttsqid',
					index : 'ttsqid',
					key : true,
					hidden : true
				}, {
					label : 'key',
					name : 'xmdm',
					index : 'xmdm',
					hidden : true
				}, {
					label : '�Ŷ�����',
					name : 'tdmc',
					index : 'tdmc',
					width : '10%',
					formatter:ttsqLink
				}, {
					label : '�ӳ�ѧ��',
					name : 'xh',
					index : 'xh',
					width : '10%',
					formatter:dzxhLink
				},{
					label : '�ӳ�����',
					name : 'dzxm',
					index : 'dzxm',
					width : '10%'
				}, {
					label : '��Ա��',
					name : 'tdnum',
					index : 'tdnum',
					width : '6%'
				}, {
					label : '�ӳ�����Ժϵ',
					name : 'xymc',
					index : 'xydm',
					width : '5%'
				}, {
					label : '��Ŀ����',
					name : 'xmmc',
					index : 'xmmc',
					width : '15%'
				},{
					label : '�����',
					name : 'xmjbmc',
					index : 'xmjbmc',
					width : '15%'
				},{
					label : '����ʱ��',
					name : 'sqsj',
					index : 'sqsj',
					width : '10%'
				},
				{
					label : '���״̬',
					name : 'shztmc',
					index : 'shztmc',
					width : '10%'
				},
				{
					label : 'shzt',
					name : 'shzt',
					index : 'shzt',
					hidden : true
				},	
				{
					name : 'splc',
					index : 'splc',
					hidden : true
				},
				{
					name : 'sqkg',
					index : 'sqkg',
					hidden : true
				}
				],
				sortname : "sqsj",
				sortorder : "desc"
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
		<html:form action="/gygl_xyzsjggl">
			
			
			<div class="toolbox">
				<!-- ��ť -->
				<div class="buttonbox">
					<ul>
						<logic:equal name="writeAble" value="yes">
						<li>
							<a href="javascript:void(0);" class="btn_zj" onclick="add();return false;"  >����</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="update();return false;" class="btn_xg" >�޸�</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="del();return false" class="btn_sc" >ɾ��</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="submitBusi();return false" class="btn_shuc" >�ύ</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="cancel();return false" class="btn_sr" >����</a>
						</li>
						</logic:equal>
						<li>
							<a href="javascript:void(0);" onclick="lcgz();return false;" 
								   title="ѡ��һ����¼������ð�ť���Բ鿴������̡�"
								   class="btn_cs">���̸���</a>
						</li>	
						<li><a href="#" class="btn_dc" onclick="exportConfig();return false;">����</a></li>	
					</ul>
				</div>
				<!-- �������� -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- �������� end-->
			</div>
		<%@include file="/comm/hiddenValue.jsp"%></html:form>
		
		<div class="main_box">
			<h3 class=datetitle_01>
				<span>������չ��Ŀ�����б�&nbsp;&nbsp; </span>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable" ></table>
				<div id="pager"></div>
			</div>
		</div>
	</body>
</html>
