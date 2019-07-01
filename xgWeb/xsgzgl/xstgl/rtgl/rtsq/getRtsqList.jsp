<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/xstgl/rtgl/rtsq/js/rtsq.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/exportNew/import.js"></script>
		<script type="text/javascript">
		jQuery(function(){
			var gridSetting = {
				caption : "����������Ϣ",
				pager : "pager",
				url : "stgl_rtgl_rtsq.do?method=getRtsqList&type=query",
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
					width : '10%',
					formatter : xhLink
				}, {
					label : '����',
					name : 'xm',
					index : 'xm',
					width : '10%'
				},{
					label : '�༶',
					name : 'bjmc',
					index : 'bjdm',
					//width : '15%'
					hidden : true
				}, {
					label : 'רҵ',
					name : 'zymc',
					index : 'zydm',
					//width : '15%'
					hidden:true
				}, {
					label : 'ѧԺ',
					name : 'xymc',
					index : 'xydm',
					//width : '10%'
					hidden:true
				}, {
					label : '�Ա�',
					name : 'xb',
					index : 'xb',
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
					//hidden : true
				},{
					label : '������Ŀ����',
					name : 'stxmmc',
					index : 'stxmmc',
					width : '15%'
				},
//					{
//					label : 'ָ����ʦ',
//					name : 'zdlsxm',
//					index : 'zdlsxm',
//					width : '10%'
//				},
					{
					label : '��Чѧ��',
					name : 'xn',
					index : 'xn',
					width : '10%'
				},{
					label : '���״̬',
					name : 'shztmc',
					index : 'shzt',
					width : '10%'
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
				},{
					label : 'sqsj',
					name : 'sqsj',
					index : 'sqsj',
					hidden : true
				},{
					label : 'shzt',
					name : 'shzt',
					index : 'shzt',
					hidden : true
				},{
					label : 'splc',
					name : 'splc',
					index : 'splc',
					hidden : true
				}],
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
		<input type="hidden" name="usertype" value="${usertype}">
		<input type="hidden" id="flag" value="wsq"/>
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
						<logic:notEqual name="usertype" value="stu">
							<logic:equal name="writeAble" value="yes">
							<li><a href="javascript:void(0);" onclick="importConfig();return false;" class="btn_dr">����</a></li>
							</logic:equal>
							<li><a href="#" class="btn_dc" onclick="exportConfig();return false;">����</a></li>	
						</logic:notEqual>
						<!--  
						<li>
							<a href="javascript:void(0);" onclick="btn_dy();return false;" class="btn_dr">���������</a>
						</li>
						-->
					</ul>
				</div>
				<!-- �������� -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- �������� end-->
			</div>
		</html:form>
		
		<div class="main_box">
			<h3 class=datetitle_01>
				<span>����������Ϣ&nbsp;&nbsp; </span>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable" ></table>
				<div id="pager"></div>
			</div>
		</div>
	</body>
</html>
