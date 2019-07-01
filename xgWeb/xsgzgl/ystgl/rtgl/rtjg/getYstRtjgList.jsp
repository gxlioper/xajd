<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/ystgl/rtgl/rtjg/js/rtjg.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/exportNew/import.js"></script>
		<script type="text/javascript" src="xsgzgl/ystgl/rtgl/comm/js/comm.js"></script>
		<script type="text/javascript">
		jQuery(function(){
			var gridSetting = {
				caption : "���Ž����Ϣ",
				pager : "pager",
				url : "ystglRtjg.do?method=getYstRtjgList&type=query",
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
					label : '���������',
					name : 'ystlbmc',
					index : 'ystlbmc',
					width : '15%'
				},{
					label : '��Ŀ���',
					name : 'xmlbmc',
					index : 'xmlbmc',
					width : '15%'
					//hidden : true
				},{
					label : '��������Ŀ����',
					name : 'ystxmmc',
					index : 'ystxmmc',
					width : '15%'
				},{
					label : 'ָ����ʦ',
					name : 'zdlsxm',
					index : 'zdlsxm',
					width : '10%'
				},{
					label : '��Чѧ��',
					name : 'xn',
					index : 'xn',
					width : '10%'
				},{
					label : '״̬',
					name : 'tnzt',
					index : 'tnzt',
					width : '6%'
				},{
					label : 'ystid',
					name : 'ystid',
					index : 'ystid',
					hidden : true
				},{
					label : 'sjly',
					name : 'sjly',
					index : 'sjly',
					hidden : true
				},{
					label : 'sqsj',
					name : 'sqsj',
					index : 'sqsj',
					hidden : true
				}],
				sortname : "tnzt desc,rtsj",
				sortorder : "desc"
			}
			var map = getSuperSearch();
			gridSetting["params"] = map;
			jQuery("#dataTable").initGrid(gridSetting);
		})
		</script>
	</head>

	<body>
		<input type="hidden" name="usertype" value="${usertype}">
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>${title }</a>
			</p>
		</div>
		<html:form action="/ystglRtjg.do">
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
							<a href="javascript:void(0);" onclick="del();return false;" class="btn_sc" >ɾ��</a>
						</li>
						<logic:notEqual name="usertype" value="stu">
						<li>
							<a href="javascript:void(0);" onclick="importConfig();return false;" class="btn_dr">����</a>
						</li>
						</logic:notEqual>
						</logic:equal>
						<logic:notEqual name="usertype" value="stu">
							<li><a href="#" class="btn_dc" onclick="exportConfig();return false;">����</a></li>	
						</logic:notEqual>
						<li>
							<a href="javascript:void(0);" onclick="getYstbmb();return false;" class="btn_dy">���ŵǼǱ�</a>
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
				<span>���Ž����Ϣ&nbsp;&nbsp; </span>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable" ></table>
				<div id="pager"></div>
			</div>
		</div>
	</body>
</html>
