<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/rcsw/hdkhgl/jlygx/js/jlygx.js"></script>
		<script type="text/javascript">
		jQuery(function(){
			var gridSetting = {
				caption : "��μ�ѧ����ϸ",
				pager : "pager",
				url : "hdkhgl_jlygx.do?method=getJlyGxList&type=query",
				colList : [ {
					label : 'key',
					name : 'id',
					index : 'id',
					key : true,
					hidden : true
				}, {
					label : 'ѧ��',
					name : 'xn',
					index : 'xn',
					width : '13%'
					//formatter : xhLink
				}, {
					label : 'ѧ��',
					name : 'xq',
					index : 'xq',
					width : '10%'
					//formatter : xhLink
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
				}, {
					label : '�Ա�',
					name : 'xb',
					index : 'xb',
					width : '5%'
				},{
					label : '�༶',
					name : 'bjmc',
					index : 'bjdm',
					width : '17%'
				},{
					label : '��Ŀ����',
					name : 'xmmc',
					index : 'xmmc',
					width : '13%'
				},{
					label : '�ʱ��',
					name : 'hdsj',
					index : 'hdsj',
					width : '23%'
				},{
					label : '�Ƿ�μ�',
					name : 'sfcj',
					index : 'sfcj',
					width : '5%'
				},{
					label : 'hdjgid',
					name : 'hdjgid',
					index : 'hdjgid',
					hidden : true
				}],
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
			<%@ include file="/comm/hiddenValue.jsp"%>
			
			<div class="toolbox">
				<!-- ��ť -->
				<div class="buttonbox">
					<ul>
						<logic:equal name="writeAble" value="yes">
							<logic:equal name="usertype" value="stu">
								<li>
									<a href="javascript:void(0);" class="btn_zj" onclick="XsJlGxWh();return false;"  >��¼�����</a>
								</li>
							</logic:equal>
						</logic:equal>
						<li>
							<a href="javascript:void(0);" class="btn_ck" onclick="JlgxView();return false;"  >�鿴</a>
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
				<span>��μ�ѧ����ϸ&nbsp;&nbsp; </span>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable" ></table>
				<div id="pager"></div>
			</div>
		</div>
	</body>
</html>
