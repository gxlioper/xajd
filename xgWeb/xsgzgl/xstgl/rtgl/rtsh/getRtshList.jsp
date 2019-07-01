<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/xstgl/rtgl/rtsh/js/rtsh.js"></script>
		<script type="text/javascript">
		var gridSetting = {
				caption:"ѧ����������б�",
				pager:"pager",
				url:"stglRtsh.do?method=getRtshList&type=query",
				colList:[
					{
					label : 'key',
					name : 'rtid',
					index : 'rtid',
					key : true,
					hidden : true
				}, {
					label : 'ѧ��',
					name : 'xh',
					index : 'xh',
					width : '7%',
					formatter : xhLink
				}, {
					label : '����',
					name : 'xm',
					index : 'xm',
					width : '6%'
				}, {
					label : '�Ա�',
					name : 'xb',
					index : 'xb',
					hidden : true
				},{
					label : '�༶',
					name : 'bjmc',
					index : 'bjdm',
					width : '7%'
				}, {
					label : 'ѧԺ',
					name : 'xymc',
					index : 'xydm',
					hidden : true
				}, {
					label : 'רҵ',
					name : 'zymc',
					index : 'zydm',
					hidden : true
				},{
					label : ' �������',
					name : 'stlbmc',
					index : 'stlbdm',
					width : '13%'
				},{
					label : '������Ŀ����',
					name : 'stxmmc',
					index : 'stid',
					width : '10%'
				},
//					{
//					label : 'ָ����ʦ',
//					name : 'zdlsxm',
//					index : 'zdlsxm',
//					width : '10%'
//				},
					{
					label : '����ʱ��',
					name : 'sqsj',
					index : 'sqsj',
					hidden : true
				},{
					label : '��Чѧ��',
					name : 'xn',
					index : 'xn',
					width : '10%'
				},{
					label : '���״̬',
					name : 'shztmc',
					index : 'shzt',
					width : '3%'
				},{
					name : 'splc',
					index : 'splc',
					hidden : true
				},{
					name : 'stlbdm',
					index : 'stlbdm',
					hidden : true
				},{
					name : 'nj',
					index : 'nj',
					hidden : true
				},{
					name : 'xb',
					index : 'xb',
					hidden : true
				},{
					name : 'rtxq',
					index : 'rtxq',
					hidden : true
				},{
					name : 'xmlbdm',
					index : 'xmlbdm',
					hidden : true
				},{
					name : 'kssj',
					index : 'kssj',
					hidden : true
				},{
					name : 'jssj',
					index : 'jssj',
					hidden : true
				},{
					name : 'shzt',
					index : 'shzt',
					hidden : true
				},{label:'���Id',
				   name:'shid', 
				   index: 'shid',
				   hidden:true
				},{label:'��λId',
				   name:'gwid', 
				   index: 'gwid',
				   hidden:true
				},{label:'stid',
				   name:'stid', 
				   index: 'stid',
				   hidden:true}
				],
				params:{shzt:"dsh"},
				sortname: "sqsj",
			 	sortorder: "desc",
			 	radioselect:false
		}

		var gridSetting2 = {
				caption:"ѧ����������б�",
				pager:"pager",
				url:"stglRtsh.do?method=getRtshList&type=query",
				colList:[
					{
					label : 'key',
					name : 'rtid',
					index : 'rtid',
					key : true,
					hidden : true
				}, {
					label : 'ѧ��',
					name : 'xh',
					index : 'xh',
					width : '7%',
					formatter : xhLink
				}, {
					label : '����',
					name : 'xm',
					index : 'xm',
					width : '6%'
				}, {
					label : '�Ա�',
					name : 'xb',
					index : 'xb',
					hidden : true
				},{
					label : '�༶',
					name : 'bjmc',
					index : 'bjdm',
					width : '7%'
				}, {
					label : 'ѧԺ',
					name : 'xymc',
					index : 'xydm',
					hidden : true
				}, {
					label : 'רҵ',
					name : 'zymc',
					index : 'zydm',
					hidden : true
				},{
					label : ' �������',
					name : 'stlbmc',
					index : 'stlbdm',
					width : '13%'
				},{
					label : '������Ŀ����',
					name : 'stxmmc',
					index : 'stid',
					width : '10%'
				},{
					label : 'ָ����ʦ',
					name : 'zdlsxm',
					index : 'zdls',
					width : '10%'
				},{
					label : '����ʱ��',
					name : 'sqsj',
					index : 'sqsj',
					hidden : true
				},{
					label : 'ѧ��',
					name : 'xn',
					index : 'xn',
					width : '10%'
				},{
					label : '���״̬',
					name : 'shztmc',
					index : 'shzt',
					width : '3%'
				},{
					name : 'splc',
					index : 'splc',
					hidden : true
				},{
					name : 'sbbmmc',
					index : 'sbbmmc',
					hidden : true
				},{
					name : 'nj',
					index : 'nj',
					hidden : true
				},{
					name : 'xb',
					index : 'xb',
					hidden : true
				},{
					name : 'rtxq',
					index : 'rtxq',
					hidden : true
				},{
					name : 'sskmmc',
					index : 'sskmdm',
					hidden : true
				},{
					name : 'kssj',
					index : 'kssj',
					hidden : true
				},{
					name : 'jssj',
					index : 'jssj',
					hidden : true
				},{
					name : 'shzt',
					index : 'shzt',
					hidden : true
				},{label:'���Id',
				   name:'shid', 
				   index: 'shid',
				   hidden:true
				},{label:'��λId',
				   name:'gwid', 
				   index: 'gwid',
				   hidden:true}
				],
				params:{shzt:"ysh"},
				sortname: "sqsj",
			 	sortorder: "desc",
			 	radioselect:true
		}
			
		jQuery(function(){
			var map = getSuperSearch();
			map["shzt"]="dsh";
			gridSetting["params"] = map;
			jQuery("#dataTable").initGrid(gridSetting);
		});
	
			
		
		</script>
	</head>

	<body>
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>${title }</a>
			</p>
		</div>
		<html:form action="/xmsqgl_xmsh">
			<input type="hidden" id="shzt" value="dsh"/>
			<%@ include file="/comm/hiddenValue.jsp"%>
			<div class="toolbox">
				<!-- ��ť -->
				<div class="buttonbox">
					<ul>
					<logic:equal name="writeAble" value="yes">
						<li id="li_sh">
							<a href="javascript:void(0);" onclick="rtsh();return false;" 
							   title="ѡ����Ҫ��˵ļ�¼������ð�ť���Դ����ҳ�档"
							   class="btn_sh">���</a>
						</li>						
						<li id="li_qx" style="display: none;">
							<a href="javascript:void(0);" onclick="cancelSh();return false;" 
							   title="ѡ��һ����¼������ð�ť�����Գ���ʧ�����˲�����"
							   class="btn_qxsh">����</a>
						</li>	
						</logic:equal>					
						<li><a href="javascript:void(0);" onclick="splcInfo();return false;" 
							   title="ѡ��һ����¼������ð�ť���Բ鿴������̡�"
							   class="btn_cs">���̸���</a></li>	
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
				<span>ѧ����������б�&nbsp;&nbsp; </span>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable" ></table>
				<div id="pager"></div>
			</div>
		</div>
	</body>
</html>
