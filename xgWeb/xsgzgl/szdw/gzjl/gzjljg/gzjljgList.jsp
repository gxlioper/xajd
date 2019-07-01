<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/szdw/gzjl/gzjljg/gzjljg.js"></script>
		<script type="text/javascript">
		jQuery(function(){
			var gridSetting = {};
			if("11842"==jQuery("#xxdm").val()){
				gridSetting = {
						caption : "������¼�б�",
						pager : "pager",
						url : "gzjljg.do?method=gzjljgList&type=query",
						colList : [ {
							label : 'key',
							name : 'jgid',
							index : 'jgid',
							key : true,
							hidden : true
						},{
							label : 'sjly',
							name : 'sjly',
							index : 'sjly',
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
							width : '10%'
						},{
							label : '����ʱ��',
							name : 'gzsj',
							index : 'gzsj',
							width : '10%'
						},{
							label : '��¼ʱ��',
							name : 'jlsj',
							index : 'jlsj',
							width : '20%'
						},{
							label : '��ע',
							name : 'bz',
							index : 'bz',
							width : '10%',
							hidden : true
						},{
							label : '��ע',
							name : 'bzstr',
							index : 'bzstr',
							width : '15%',
							formatter : setBz
						}],
						sortname : "gzsj",
						sortorder : "desc"
					}
			}else{
				gridSetting = {
						caption : "������¼�б�",
						pager : "pager",
						url : "gzjljg.do?method=gzjljgList&type=query",
						colList : [ {
							label : 'key',
							name : 'jgid',
							index : 'jgid',
							key : true,
							hidden : true
						},{
							label : 'sjly',
							name : 'sjly',
							index : 'sjly',
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
							width : '15%'
						}, {
							label : '����ʱ��',
							name : 'gzsj',
							index : 'gzsj',
							width : '15%'
						},{
							label : '��¼ʱ��',
							name : 'jlsj',
							index : 'jlsj',
							width : '20%'
						},{
							label : '��ע',
							name : 'bz',
							index : 'bz',
							width : '10%',
							hidden : true
						},{
							label : '��ע',
							name : 'bzstr',
							index : 'bzstr',
							width : '15%',
							formatter : setBz
						}],
						sortname : "gzsj",
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
		<html:form action="/gzjljg">
			<input type="hidden" id="xbmc" value="<bean:message key="lable.xb" />"/>
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
						</logic:equal>
						<li><a href="#" class="btn_dc" onclick="exportConfig();return false;">����</a></li>
						<logic:equal name="xxdm" value="11458">
						<logic:notEqual value="xx" name="userStatus">
						<logic:notEqual value="xy" name="userStatus">
						<li>
							<a href="javascript:void(0);"
								onclick="gzjltj('js');return false;" class="btn_dy">������¼���ܱ�</a>
						</li>
						</logic:notEqual>
						</logic:notEqual>
						<logic:equal value="xy" name="userStatus">
						<li>
							<a href="javascript:void(0);"
								onclick="gzjltj('xy');return false;" class="btn_dy">Ժ��������¼���ܱ�</a>
						</li>
						</logic:equal>
						<logic:equal value="xx" name="userStatus">
						<li>
							<a href="javascript:void(0);"
								onclick="gzjltj('xx');return false;" class="btn_dy">У��������¼���ܱ�</a>
						</li>
						</logic:equal>
						<li><a href="javascript:void(0);" onclick="printGzjlb('gzjljg.do?method=printGzjlb');return false;" class="btn_down">ѧ���������ǼǱ�</a></li>
						</logic:equal>
						<%--�㽭���˴�ѧ ���һ--%>
						<logic:equal name="xxdm" value="11842">
							<li>
								<a href="javascript:void(0);"
									onclick="gzjlb('z');return false;" class="btn_dy">�ܹ�����¼��</a>
							</li>
							<li>
								<a href="javascript:void(0);"
									onclick="gzjlb('y');return false;" class="btn_dy">�¹�����¼��</a>
							</li>
						</logic:equal>
					</ul>
				</div>
				<!-- �������� -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- �������� end-->
			</div>
		</html:form>
		
		<div class="main_box">
			<h3 class=datetitle_01>
				<span>������¼�б�&nbsp;&nbsp; </span>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable" ></table>
				<div id="pager"></div>
			</div>
		</div>
	</body>
</html>
