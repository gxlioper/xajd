<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript" src="xsgzgl/rcsw/xszbb/xszbbsh/js/xszbbshManage.js"></script>
		<script type="text/javascript">
		var gridSetting = {
				caption:"֤���������",
				pager:"pager",
				url:"rcsw_xszbb_bbshgl.do?method=xszbbshManage&type=query",
				colList:[
					{label:'key',name:'bbsqid', index: 'bbsqid',key:true ,hidden:true},
					{label:'��������',name:'splc', index: 'splc',hidden:true},
					{label:'ѧ��',name:'xh', index: 'xh',width:'12%',formatter:xhLink},
					{label:'����',name:'xm', index: 'xm',width:'10%'},
					{label:'�꼶',name:'nj', index: 'nj',width:'7%'},
					{label:'<bean:message key="lable.xb" />',name:'xymc', index: 'xymc',width:'12%'},
					{label:'רҵ',name:'zymc', index: 'zymc',width:'15%'},
					{label:'�����༶',name:'bjmc', index: 'bjdm',width:'15%'},
                    {label:'רҵ�༶',name:'zybjmc', index: 'zybjmc',width:'15%'},
					{label:'����ʱ��',name:'sqsj', index: 'sqsj',width:'12%'},
					{label:'����֤��',name:'xszbblxmc', index: 'xszbblxmc',width:'10%'},
					{label:'���״̬',name:'shztmc', index: 'shztmc',width:'7%'},
					{label:'���״̬����',name:'shzt', index: 'shzt',hidden:true},
					{label:'����֤������',name:'xszbblxdm', index: 'xszbblxdm',hidden:true},
					{label:'���Id',name:'shid', index: 'shid',hidden:true},
					{label:'��λId',name:'gwid', index: 'gwid',hidden:true}
				],
				params:{shzt:"dsh"},//Ĭ�ϴ����
				sortname: "sqsj",
			 	sortorder: "desc",
			 	radioselect:false
			 		
		}

		var gridSetting2 = {
				caption:"֤���������",
				pager:"pager",
				url:"rcsw_xszbb_bbshgl.do?method=xszbbshManage&type=query",
				colList:[
					{label:'key',name:'bbsqid', index: 'bbsqid',key:true ,hidden:true},
					{label:'��������',name:'splc', index: 'splc',hidden:true},
					{label:'ѧ��',name:'xh', index: 'xh',width:'14%',formatter:xhLink},
					{label:'����',name:'xm', index: 'xm',width:'10%'},
					{label:'�Ա�',name:'xb', index: 'xb',width:'7%'},
                    {label:'�����༶',name:'bjmc', index: 'bjdm',width:'15%'},
                    {label:'רҵ�༶',name:'zybjmc', index: 'zybjmc',width:'15%'},
					{label:'����֤��',name:'xszbblxmc', index: 'xszbblxmc',width:'12%'},
					{label:'���벹��ʱ��',name:'sqsj', index: 'sqsj',width:'19%'},
					{label:'���״̬',name:'shztmc', index: 'shztmc',width:'16%'},
					{label:'���״̬����',name:'shzt', index: 'shzt',hidden:true},
					{label:'����֤������',name:'xszbblxdm', index: 'xszbblxdm',hidden:true},
					{label:'���Id',name:'shid', index: 'shid',hidden:true}
				],
				params:{shzt:"ysh"},//Ĭ�ϴ����
				sortname: "sqsj",
			 	sortorder: "desc",
			 	radioselect:true
		}
			
		jQuery(function(){
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
							<a href="javascript:void(0);" onclick="xszbbSh();return false;" 
							   title="ѡ����Ҫ��˵ļ�¼������ð�ť���Դ����ҳ�档"
							   class="btn_sh">���</a>
						</li>						
						<li id="li_qx" style="display: none;">
							<a href="javascript:void(0);" onclick="cancelShnew();return false;" 
							   title="ѡ��һ����¼������ð�ť�����Գ���ʧ�����˲�����"
							   class="btn_qxsh">����</a>
						</li>	
						</logic:equal>					
						<li><a href="javascript:void(0);" onclick="xszbbshLcinfo();return false;" 
							   title="ѡ��һ����¼������ð�ť���Բ鿴������̡�"
							   class="btn_cs">���̸���</a></li>	
						<!-- <logic:equal name="writeAble" value="yes">	   
						<li><a href="#" class="btn_dc" onclick="exportConfig();return false;">����</a></li>	
						</logic:equal>		-->		
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
				<span>֤����������б�&nbsp;&nbsp; </span>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable" ></table>
				<div id="pager"></div>
			</div>
		</div>
	</body>
</html>
