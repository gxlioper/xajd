<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/szdw/gzjl/gzjlsh/gzjlsh.js"></script>
		<script type="text/javascript">
		var gridSetting = {};
		var gridSetting2 = {};
		var xxdm = ${xxdm};
		if("11842"==xxdm){
			gridSetting = {
					caption:"������¼���",
					pager:"pager",
					url:"gzjlsh.do?method=gzjlshList&type=query",
					colList:[
						{label:'key',name:'sqid', index: 'sqid',key:true ,hidden:true},
						{label:'gwdm',name:'gwdm', index: 'gwdm',hidden:true},
						{label:'��������',name:'splc', index: 'splc',hidden:true},
						{label:'ְ����',name:'zgh', index: 'zgh',width:'10%',formatter:zghLink},
						{label:'����',name:'xm', index: 'xm',width:'10%'},
						{label : '<bean:message key="lable.xb" />', name:'xymc',index: 'xymc',width:'15%'},
						{label:'�������',name:'gzlbmc', index: 'gzlbmc',width:'10%'},
						{label:'������',name:'lksmc',index:'lksmc',width:'15%'},
						{label:'����ʱ��',name:'gzsj', index: 'gzsj',width:'10%'},
						{label:'��¼ʱ��',name:'jlsj', index: 'jlsj',width:'16%'},
						{label:'����ʱ��',name:'sqsj', index: 'sqsj',width:'20%',hidden:true},
						{label:'���״̬',name:'shztmc', index: 'shztmc',width:'14%'},
						{label:'shzt',name:'shzt', index: 'shzt',hidden:true},
						{label:'���Id',name:'shid', index: 'shid',hidden:true},
						{label:'��λId',name:'gwid', index: 'gwid',hidden:true}
					],
					params:{shzt:"dsh"},
					sortname: "sqsj",
				 	sortorder: "desc",
				 	radioselect:false
			};
			gridSetting2 = {
					caption:"������¼���",
					pager:"pager",
					url:"gzjlsh.do?method=gzjlshList&type=query",
					colList:[
						{label:'key',name:'sqid', index: 'sqid',key:true ,hidden:true},
						{label:'gwdm',name:'gwdm', index: 'gwdm',hidden:true},
						{label:'��������',name:'splc', index: 'splc',hidden:true},
						{label:'ְ����',name:'zgh', index: 'zgh',width:'12%',formatter:zghLink},
						{label:'����',name:'xm', index: 'xm',width:'6%'},
						{label : '<bean:message key="lable.xb" />', name:'xymc',index: 'xymc',width:'12%'},
						{label:'�������',name:'gzlbmc', index: 'gzlbmc',width:'10%'},
						{label:'������',name:'lksmc',index:'lksmc',width:'10%'},
						{label:'����ʱ��',name:'gzsj', index: 'gzsj',width:'13%'},
						{label:'����ʱ��',name:'sqsj', index: 'sqsj',width:'20%',hidden:true},
						{label:'���״̬',name:'shztmc', index: 'shztmc',width:'6%'},
						{label:'shzt',name:'shzt', index: 'shzt',hidden:true},
						{label:'���Id',name:'shid', index: 'shid',hidden:true},
						{label:'��λId',name:'gwid', index: 'gwid',hidden:true}
					],
					params:{shzt:"ysh"},
					sortname: "sqsj",
				 	sortorder: "desc",
				 	radioselect:true
			}
		}else{
			gridSetting = {
					caption:"������¼���",
					pager:"pager",
					url:"gzjlsh.do?method=gzjlshList&type=query",
					colList:[
						{label:'key',name:'sqid', index: 'sqid',key:true ,hidden:true},
						{label:'gwdm',name:'gwdm', index: 'gwdm',hidden:true},
						{label:'��������',name:'splc', index: 'splc',hidden:true},
						{label:'ְ����',name:'zgh', index: 'zgh',width:'10%',formatter:zghLink},
						{label:'����',name:'xm', index: 'xm',width:'10%'},
						{label : '<bean:message key="lable.xb" />', name:'xymc',index: 'xymc',width:'15%'},
						{label:'�������',name:'gzlbmc', index: 'gzlbmc',width:'15%'},
						{label:'����ʱ��',name:'gzsj', index: 'gzsj',width:'20%'},
						{label:'��¼ʱ��',name:'jlsj', index: 'jlsj',width:'16%'},
						{label:'����ʱ��',name:'sqsj', index: 'sqsj',width:'20%',hidden:true},
						{label:'���״̬',name:'shztmc', index: 'shztmc',width:'14%'},
						{label:'shzt',name:'shzt', index: 'shzt',hidden:true},
						{label:'���Id',name:'shid', index: 'shid',hidden:true},
						{label:'��λId',name:'gwid', index: 'gwid',hidden:true}
					],
					params:{shzt:"dsh"},
					sortname: "sqsj",
				 	sortorder: "desc",
				 	radioselect:false
			};
			gridSetting2 = {
					caption:"������¼���",
					pager:"pager",
					url:"gzjlsh.do?method=gzjlshList&type=query",
					colList:[
						{label:'key',name:'sqid', index: 'sqid',key:true ,hidden:true},
						{label:'gwdm',name:'gwdm', index: 'gwdm',hidden:true},
						{label:'��������',name:'splc', index: 'splc',hidden:true},
						{label:'ְ����',name:'zgh', index: 'zgh',width:'12%',formatter:zghLink},
						{label:'����',name:'xm', index: 'xm',width:'6%'},
						{label : '<bean:message key="lable.xb" />', name:'xymc',index: 'xymc',width:'12%'},
						{label:'�������',name:'gzlbmc', index: 'gzlbmc',width:'12%'},
						{label:'����ʱ��',name:'gzsj', index: 'gzsj',width:'21%'},
						{label:'����ʱ��',name:'sqsj', index: 'sqsj',width:'20%',hidden:true},
						{label:'���״̬',name:'shztmc', index: 'shztmc',width:'6%'},
						{label:'shzt',name:'shzt', index: 'shzt',hidden:true},
						{label:'���Id',name:'shid', index: 'shid',hidden:true},
						{label:'��λId',name:'gwid', index: 'gwid',hidden:true}
					],
					params:{shzt:"ysh"},
					sortname: "sqsj",
				 	sortorder: "desc",
				 	radioselect:true
			}
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
		<html:form action="/gzjlsh">
			<input type="hidden" name="shkg" id="shkg" value="${shkg}"/>
			<input type="hidden" id="shzt" value="dsh"/>
			<%@ include file="/comm/hiddenValue.jsp"%>
			<div class="toolbox">
				<!-- ��ť -->
				<div class="buttonbox">
					<ul>
					<logic:equal name="writeAble" value="yes">
						<li id="li_sh">
							<a href="javascript:void(0);" onclick="gzjlsh();return false;" 
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
				<span>������¼����б�&nbsp;&nbsp; </span>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable" ></table>
				<div id="pager"></div>
			</div>
		</div>
	</body>
</html>
