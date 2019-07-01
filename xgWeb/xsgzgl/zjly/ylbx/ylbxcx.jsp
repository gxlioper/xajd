<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/zjly/ylbx/js/ylbx.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/exportNew/import.js"></script>
		<script type="text/javascript">
		jQuery(function(){
			var gridSetting = {
				caption : "ҽ�Ʊ���",
				pager : "pager",
				url : "zjly_ylbx.do?method=getYlbxCx&type=query",
				colList : [ {
					label : 'key',
					name : 'id',
					index : 'id',
					key : true,
					hidden : true
				},{
					label : 'ѧ��',
					name : 'xn',
					index : 'xn',
					width : '8%'
				},{
					label : '�༶',
					name : 'bjmc',
					index : 'bjmc',
					width : '10%'
				},{
					label : 'ѧ��',
					name : 'xh',
					index : 'xh',
					width : '10%',
					formatter : xhLink
				},{
					label : '����',
					name : 'xm',
					index : 'xm',
					width : '6%'
				},{
					label : '֤������',
					name : 'zlbh',
					index : 'zlbh',
					width : '8%'
				}/*,{
					label : '��֤��Ϣ',
					name : 'czxx',
					index : 'czxx',
					width : '8%'
				},{
					label : '��֤֤�����',
					name : 'czzjbh',
					index : 'czzjbh',
					width : '15%'
				},{
					label : '��֤��ʼʱ��',
					name : 'czkssj',
					index : 'czkssj',
					width : '17%'
				},{
					label : '��֤����ʱ��',
					name : 'czzzsj',
					index : 'czzzsj',
					width : '17%'
				}*/,{
					label : '���������',
					name : 'cxblb',
					index : 'cxblb',
					width : '8%'
				},{
					label : '��˱�־',
					name : 'shbz',
					index : 'shbz',
					hidden : true
				}]
			}
			var map = getSuperSearch();
			gridSetting["params"] = map;
			jQuery("#dataTable").initGrid(gridSetting);
		})
		</script>
	</head>

	<body>
		<input type="hidden" name="isopen" id="isopen" value="${jcszModel.isopen }"/>
		<input type="hidden" name="cxblb" id="cxblb" value=""/>
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>${title }</a>
			</p>
		</div>
		<html:form action="/zjly_ylbx">
			<%@ include file="/comm/hiddenValue.jsp"%>
			
			<div class="toolbox">
				<!-- ��ť -->
				<div class="buttonbox">
					<ul>
						<logic:equal name="writeAble" value="yes">
						<li>
							<a href="javascript:void(0);" onclick="add()" class="btn_zj">����</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="update();" class="btn_xg">�޸�</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="del();return false" class="btn_sc" >ɾ��</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="importXX();return false" class="btn_dr" >����</a>
						</li>
						</logic:equal>
						<li><a href="#" class="btn_dc" onclick="exportConfig('1');return false;">�²α�����</a></li>	
						<li><a href="#" class="btn_dc" onclick="exportConfig('0');return false;">��������</a></li>
					
					</ul>
				</div>
				<!-- �������� -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- �������� end-->
			</div>
		</html:form>
		
		<div class="main_box">
			<h3 class=datetitle_01>
				<span>ҽ�Ʊ���&nbsp;&nbsp; </span>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable" ></table>
				<div id="pager"></div>
			</div>
		</div>
	</body>
</html>
