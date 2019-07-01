<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript" src="xsgzgl/dekt/dektxfsh/js/xfsh.js"></script>
		<script type="text/javascript">
		var gridSetting = {
				caption:"�ڶ�����ѧ�����",
				pager:"pager",
				url:"dekt_xfsh.do?method=getXfshList",
				colList:[
					{label:'key',name:'sqid', index: 'sqid',key:true ,hidden:true},
					{label:'ѧ��',name:'xh', index: 'xh',width:'12%',formatter:xhLink},
					{label:'����',name:'xm', index: 'xm',width:'8%'},
					{label:'<bean:message key="lable.xb" />',name:'xymc', index: 'xymc',width:'14%'},
					{label:'�϶�����',name:'rdnr', index: 'rdnr',width:'40%'},
					{label:'��ʱ��',name:'hjsj', index: 'hjsj',width:'10%'},
					{label:'���״̬',name:'shztmc', index: 'shztmc',width:'16%'},
					{label:'��������',name:'splc', index: 'splc',hidden:true},
					{label:'���״̬',name:'shzt', index: 'shzt',hidden:true},
					{label:'���Id',name:'shid', index: 'shid',hidden:true},
					{label:'��λId',name:'gwid', index: 'gwid',hidden:true}
				],
				params:{shzt:"dsh"},//Ĭ�ϴ����
				sortname: "hjsj",
			 	sortorder: "desc",
			 	radioselect:false
		};

		var gridSetting2 = {
				caption:"�ڶ�����ѧ�����",
				pager:"pager",
				url:"dekt_xfsh.do?method=getXfshList",
				colList:[
					{label:'key',name:'sqid', index: 'sqid',key:true ,hidden:true},
					{label:'ѧ��',name:'xh', index: 'xh',width:'12%',formatter:xhLink},
					{label:'����',name:'xm', index: 'xm',width:'8%'},
					{label:'<bean:message key="lable.xb" />',name:'xymc', index: 'xymc',width:'14%'},
					{label:'�϶�����',name:'rdnr', index: 'rdnr',width:'40%'},
					{label:'��ʱ��',name:'hjsj', index: 'hjsj',width:'10%'},
					{label:'���״̬',name:'shztmc', index: 'shztmc',width:'16%'},
					{label:'��������',name:'splc', index: 'splc',hidden:true},
					{label:'���״̬',name:'shzt', index: 'shzt',hidden:true},
					{label:'���Id',name:'shid', index: 'shid',hidden:true},
					{label:'��λId',name:'gwid', index: 'gwid',hidden:true}
				],
				params:{shzt:"ysh"},//Ĭ�ϴ����
				sortname: "hjsj",
			 	sortorder: "desc",
			 	radioselect:false
		};
			
		jQuery(function(){
			jQuery("#dataTable").initGrid(gridSetting);
		});
		
		function xfshDgsh() {
			var rows = jQuery("#dataTable").getSeletRow();
			var shzt = jQuery("#shzt").val();
			if (rows.length == 0) {
				showAlertDivLayer("��ѡ����Ҫ��˵ļ�¼")
				return false;
			}
			if (shzt == "ysh") {
				showAlertDivLayer("�Ѵ����¼�����ٴ����");
				return false;
			} else if (rows.length == 1) {
				var url = "dekt_xfsh.do?method=xfshDgsh&sqid=" + rows[0]["sqid"] +'&xh=' + rows[0]["xh"]+ '&shid=' + rows[0]["shid"];
				showDialog("���", 900, 525, url);
			} else {
				showAlertDivLayer("�빴ѡһ����Ҫ��˵�����");
				//showDialog("�������", 500, 250, "qgzx_cjffsq.do?method=cjxxPlsh");
			}
		}
		
		function cxsh(){
			var rows = jQuery("#dataTable").getSeletRow();
			if (rows.length != 1){
				showAlertDivLayer("��ѡ��һ����Ҫ��������˼�¼��");
			}else{
				var splc = rows[0]["splc"];
				var shid = rows[0]["shid"];
				var sqid = rows[0]["sqid"];
				var shzt = rows[0]["shzt"];
				showConfirmDivLayer("��ȷ��Ҫ�����Ըü�¼����˲�����",{"okFun":function(){
					jQuery.post("dekt_xfsh.do?method=cxsh",{splc:splc,shid:shid,sqid:sqid,shzt:shzt},function(data){
						showAlertDivLayer(data["message"],{},{"clkFun":function(){
							jQuery("#dataTable").reloadGrid();
						}});
				},'json');
				}});
			}
		}
		
		</script>
	</head>

	<body style="min-height: 800px;">
	
		<input type="hidden" value="dsh" id="shzt"/>
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>${title }</a>
			</p>
		</div>
		<html:form action="/dekt_xfsh">
			<%@ include file="/comm/hiddenValue.jsp"%>
			<div class="toolbox">
					
				<!-- ��ť -->
				<div class="buttonbox">
					<ul>
					<logic:equal name="writeAble" value="yes">
						<li id="li_sh">
							<a href="javascript:void(0);" onclick="xfshDgsh();return false;" 
							   title="ѡ����Ҫ��˵ļ�¼������ð�ť���Դ����ҳ�档"
							   class="btn_sh">���</a>
						</li>						
						<li id="li_qx" style="display: none;">
							<a href="javascript:void(0);" onclick="cxsh();return false;" 
							   title="ѡ��һ����¼������ð�ť�����Գ���ʧ�����˲�����"
							   class="btn_qxsh">����</a>
						</li>	
						</logic:equal>
						<li><a href="javascript:void(0);" onclick="shLcinfo();return false;" 
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
				<span>�ڶ�����ѧ�����&nbsp;&nbsp; </span>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable" ></table>
				<div id="pager"></div>
			</div>
		</div>
	</body>
</html>
