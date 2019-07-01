<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript" src="xsgzgl/rcsw/jqlx/js/jqlxsh.js"></script>
		<script type="text/javascript">
			var lsxq = true;
			var sqly = false;
			if(jQuery('#xxdm').val="11647"){
				lsxq = false;
				sqly = true;
			}
			
			var gridSetting = {
					caption:"ѧ��������У���",
					pager:"pager",
					url:"rcsw_jqlx.do?method=jqlxShManage&type=query",
					colList:[
						{label:'key',name:'sqid', index: 'sqid',key:true ,hidden:true},
						{label:'��������',name:'lcid', index: 'lcid',hidden:true},
						{label:'gwid',name:'gwid', index: 'gwid',hidden:true},
						{label:'���Id',name:'shid', index: 'shid',hidden:true},
						{label:'ѧ��',name:'xh', index: 'xh',width:'12%',formatter:xhLink},
					    {label:'����',name:'xm', index: 'xm',width:'8%'},
					    {label:'�Ա�',name:'xb', index: 'xb',width:'5%'},
					    {label:'����',name:'mzmc', index: 'mz',width:'6%'},
					    {label:'�����༶',name:'bjmc', index: 'bjdm',width:'13%'},
                        {label:'רҵ�༶',name:'zybjmc', index: 'zybjmc',width:'13%'},
					    {label:'��ϵ�绰',name:'sjhm', index: 'sjhm',width:'9%'},
					    {label:'��������',name:'sqly', index: 'sqly',width:'14%',hidden:sqly},
					    <logic:notEqual value="10344" name="xxdm">
					    {label:'����У��',name:'lsxqmc', index: 'lsxqmc',width:'14%',hidden:lsxq},
					    </logic:notEqual>
					    {label:'��У��ʼʱ��',name:'lxkssj', index: 'lxkssj',width:'11%'},
					    {label:'��У��ֹʱ��',name:'lxjzsj', index: 'lxjzsj',width:'11%'},
					    <logic:equal value="12309" name="xxdm">
					    {label:'����Ա',name:'fdyxm', index: 'fdyxm',width:'10%'},
					    </logic:equal>
					    {label:'���״̬',name:'shztmc', index: 'shztmc',width:'11%'}
					],
					params:{shzt:"dsh"},//Ĭ�ϴ����
					sortname: "sqsj",
				 	sortorder: "desc"
			}
	
			var gridSetting2 = {
					caption:"ѧ��������У���",
					pager:"pager",
					url:"rcsw_jqlx.do?method=jqlxShManage&type=query",
					colList:[
						{label:'key',name:'sqid', index: 'sqid',key:true ,hidden:true},
						{label:'��������',name:'lcid', index: 'lcid',hidden:true},
						{label:'gwid',name:'gwid', index: 'gwid',hidden:true},
						{label:'���Id',name:'shid', index: 'shid',hidden:true},
						{label:'ѧ��',name:'xh', index: 'xh',width:'12%',formatter:xhLink},
					    {label:'����',name:'xm', index: 'xm',width:'8%'},
					    {label:'�Ա�',name:'xb', index: 'xb',width:'5%'},
					    {label:'����',name:'mzmc', index: 'mz',width:'6%'},
					    {label:'�༶',name:'bjmc', index: 'bjdm',width:'13%'},
					    {label:'��ϵ�绰',name:'sjhm', index: 'sjhm',width:'9%'},
					    {label:'����ʱ��',name:'sqsj', index: 'sqsj',width:'14%',hidden:sqly},
					    <logic:notEqual value="10344" name="xxdm">
					    {label:'����У��',name:'lsxqmc', index: 'lsxqmc',width:'14%',hidden:lsxq},
					    </logic:notEqual>
					    {label:'��У��ʼʱ��',name:'lxkssj', index: 'lxkssj',width:'11%'},
					    {label:'��У��ֹʱ��',name:'lxjzsj', index: 'lxjzsj',width:'11%'},
					    <logic:equal value="12309" name="xxdm">
					    {label:'����Ա',name:'fdyxm', index: 'fdyxm',width:'10%'},
					    </logic:equal>
					    {label:'���״̬',name:'shztmc', index: 'shztmc',width:'11%'}
					],
					params:{shzt:"ysh"},//Ĭ�ϴ����
					sortname: "sqsj",
				 	sortorder: "desc"
			}
				
			jQuery(function(){
				var map = getSuperSearch();
					map["shzt"] = "dsh";
				gridSetting["params"] = map;
				jQuery("#dataTable").initGrid(gridSetting);
			});
	
		</script>
	</head>

	<body style="min-height: 800px;">
	
		<input type="hidden" value="dsh" id="shzt"/>
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>${title }</a>
			</p>
		</div>
		<html:form action="/rcsw_rcxwwh_rcxwshgl">
			<%@ include file="/comm/hiddenValue.jsp"%>
			<div class="toolbox">
				
				<!-- ��ť -->
				<div class="buttonbox">
					<ul>
						<logic:equal name="writeAble" value="yes">	
						<li id="li_sh">
							<a href="javascript:void(0);" onclick="lxsqSh();return false;" 
							   title="ѡ����Ҫ��˵ļ�¼������ð�ť���Դ����ҳ�档"
							   class="btn_sh">���</a>
						</li>						
						<li id="li_qx" style="display: none;">
							<a href="javascript:void(0);" onclick="cancelShnew();return false;" 
							   title="ѡ��һ����¼������ð�ť�����Գ���ʧ�����˲�����"
							   class="btn_qxsh">����</a>
						</li>	
						</logic:equal>					
						<li><a href="javascript:void(0);" onclick="lcgzInfo();return false;" 
							   title="ѡ��һ����¼������ð�ť���Բ鿴������̡�"
							   class="btn_cs">���̸���</a></li>	
						<li>
							<a href="javascript:void(0);" onclick="exportConfig();return false;" class="btn_dc">����</a>
						</li>		
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
				<span>ѧ��������У����б�&nbsp;&nbsp; </span>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable" ></table>
				<div id="pager"></div>
			</div>
		</div>
	</body>
</html>
