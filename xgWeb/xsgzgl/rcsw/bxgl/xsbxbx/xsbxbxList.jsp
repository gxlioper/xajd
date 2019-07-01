<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
			<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/rcsw/bxgl/xsbxbx/js/xsbxbx.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/exportNew/import.js"></script><!-- ���빦����Ҫ -->
				<script type="text/javascript">
					jQuery(function(){
					     var gridSetting = {
									caption:"ѧ�����ձ�����Ϣ",
									pager:"pager",
									url:"rcswBxglXsbxbx.do?method=xsbxbxList&type=query",
									params:getSuperSearch(),
									colList:[
									   {label:'����id',name:'bxid', index: 'bxid',key:true,hidden:true},
									   {label:'ѧ��',name:'xh', index: 'xh', width : '10%',formatter:xhLink},
									   {label:'����',name:'xm', index: 'xm'},
									   <logic:notEqual name="xxdm" value="13871">
									   	{label:'֤������',name:'zlbh', index: 'zlbh',width : '18%'},
									   </logic:notEqual>
									   <logic:equal name="xxdm" value="13871">
									   	{label:'����(סԺ)��',name:'zlbh', index: 'zlbh',width : '18%'},
									   </logic:equal>
									    <logic:notEqual name="xxdm" value="13871">
										   {label:'����׼��',name:'clzbmc', index: 'clzbmc', width : '6%'},
										   </logic:notEqual>
										   <logic:equal name="xxdm" value="13871">
										   {label:'����ԭ��',name:'clzbmc', index: 'clzbmc', width : '6%'},
										   </logic:equal>
									   {label:'��������',name:'bxxzmc', index: 'bxxzmc', width : '6%'},
									   <logic:equal name="xxdm" value="13871">
									   {label:'ҽ�Ʒ���/Ԫ',name:'ylzd1', index: 'ylzd1', width : '6%'},
									   </logic:equal>
									   {label:'�������/Ԫ',name:'bxje', index: 'bxje', width : '6%'},
									   {label:'����',name:'lxmc', index: 'lxmc'},
									   <logic:equal name="xxdm" value="13871">
									   {label:'��Ʊʱ��',name:'csfysj', index: 'csfysj'},
									   </logic:equal>
									   <logic:notEqual name="xxdm" value="13871">
									   {label:'��������ʱ��',name:'csfysj', index: 'csfysj'},
									   {label:'�����',name:'bxshr', index: 'bxshr'},
									   </logic:notEqual>
									   {label:'sjly',name:'sjly', index: 'sjly',hidden:true}
									],
									sortname: "csfysj",
								 	sortorder: "desc"
								}
							jQuery("#dataTable").initGrid(gridSetting);
					});
					
					//�°浼��
					function dr() {
						// ����ͨ�õĵ���function�������ǵ��빦��ģ����롣
						toImportDataNew("IMPORT_N732502");
						return false;
					}
				</script>
	</head>
	<body>
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>${title }</a>
			</p>
		</div>
	<html:form action="/rcswBxglXsbxbx?method=xsbxbxList&type=query">
		<%@ include file="/comm/hiddenValue.jsp"%>
		<div class="toolbox">
			<!-- ��ť -->
			<div class="buttonbox">
				<ul>
					<logic:equal name="writeAble" value="yes">	
						<li><a href="javascript:void(0);" onclick="addBx();return false;" class="btn_zj">����</a></li>
						<li><a href="javascript:void(0);" onclick="updateBx();return false;" class="btn_xg">�޸�</a></li>
						<li><a href="javascript:void(0);" onclick="delBx();return false;" class="btn_sc">ɾ��</a></li>
						<logic:notEqual name="xxdm" value="13871">
						<li><a href="javascript:void(0);" onclick="importBx();return false;" class="btn_dr">����</a></li>
						</logic:notEqual>
						<logic:equal name="xxdm" value="13871">
						<li><a href="javascript:void(0);" onclick="dr();" class="btn_dr">����</a></li>
						</logic:equal>
					</logic:equal>
						<li>
							<a href="javascript:void(0);" onclick="exportConfig();return false;" class="btn_dc">����</a>
						</li>
					<logic:equal value="13023" name="xxdm">
						<li>
							<a href="javascript:void(0);" onclick="printBxbxZm();return false;" class="btn_dy">ҽ��֤����ӡ</a>
						</li>
					</logic:equal>		   
				</ul>
			</div>
				<!-- �������� -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- �������� end-->
		</div>
		</html:form>
		<div class="toolbox">
			<!--����start-->
			<h3 class="datetitle_01">
				<span style="width: 50%"> ѧ�����ձ�����Ϣ<a id="title" href="javascript:;" style="float:right;margin-right:30px;color: blue"></a> </span>
			</h3>
			<table id="dataTable"></table>
			<div id="pager"></div>
		</div>
	</body>
</html>
