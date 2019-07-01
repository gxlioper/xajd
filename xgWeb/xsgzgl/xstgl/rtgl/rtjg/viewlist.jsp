<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/xstgl/rtgl/rtjg/js/rtjg.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/exportNew/import.js"></script>
		<script type="text/javascript">
		jQuery(function(){
			var gridSetting = {
				caption : "���Ž��ά��",
				pager : "pager",
				url : "stgl_rtgl_rtjg.do?method=getRtjgList&type=query",
				colList : [ {
					label : 'key',
					name : 'rtid',
					index : 'rtid',
					key : true,
					hidden : true
				}, {
					label : 'ѧ��',
					name : 'xh',
					index : 'xh',
					width : '15%',
					formatter : xhLink
				}, {
					label : '����',
					name : 'xm',
					index : 'xm',
					width : '15%'
				},{
					label : '�༶',
					name : 'bjmc',
					index : 'bjdm',
					width : '15%'
				}, {
					label : 'רҵ',
					name : 'zymc',
					index : 'zydm',
					width : '15%'
				}, {
					label : 'ѧԺ',
					name : 'xymc',
					index : 'xydm',
					width : '10%'
				}, {
					label : '�Ա�',
					name : 'xb',
					index : 'xb',
					hidden : true
				},{
					label : 'sjly',
					name : 'sjly',
					index : 'sjly',
					hidden : true
				},{
					label : '�������',
					name : 'stlbmc',
					index : 'stlbmc',
					width : '15%'
				},{
					label : '��Ŀ���',
					name : 'xmlbmc',
					index : 'xmlbmc',
					width : '15%'
				},{
					label : '������Ŀ����',
					name : 'stxmmc',
					index : 'stxmmc',
					width : '15%',
					formatter : xmmcLink
				},
//					{
//					label : 'ָ����ʦ',
//					name : 'zdlsxm',
//					index : 'zdlsxm',
//					width : '10%'
//				},
				{
					label : '��Чѧ��',
					name : 'rtxn',
					index : 'rtxn',
					width : '10%'
				},{
					label : '״̬',
					name : 'tnzt',
					index : 'tnzt',
					width : '6%'
				},{
					label : '����ʱ��',
					name : 'sqsj',
					index : 'sqsj',
					hidden : true
				},{
					label : 'stid',
					name : 'stid',
					index : 'stid',
					hidden : true
				},{
					label : 'sqkg',
					name : 'sqkg',
					index : 'sqkg',
					hidden : true
				}],
				sortname : "tnzt desc,rtxn",
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
		<input type="hidden" name="usertype" value="${usertype}">
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>${title }</a>
			</p>
		</div>
		<html:form action="/stglRtjg">
			<%@ include file="/comm/hiddenValue.jsp"%>
			
			<div class="toolbox">
				<!-- ��ť -->
				<div class="buttonbox">
					<ul>
						<logic:equal name="writeAble" value="yes">
						<logic:notEqual name="usertype" value="stu">
							<li>
								<a href="javascript:void(0);" onclick="del();return false;" class="btn_sc" >ɾ��</a>
							</li>
							<li><a href="javascript:void(0);" onclick="importConfig();return false;" class="btn_dr">����</a></li>
						</logic:notEqual>
						</logic:equal>
						<li><a href="javascript:void(0);" class="btn_dc" onclick="exportConfig();return false;">����</a></li>	
					</ul>
				</div>
				<!-- �������� -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- �������� end-->
			</div>
		</html:form>
		
		<div class="main_box">
			<h3 class=datetitle_01>
				<span>���Ž��ά��&nbsp;&nbsp; </span>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable" ></table>
				<div id="pager"></div>
			</div>
		</div>
	</body>
</html>
