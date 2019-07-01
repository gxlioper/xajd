<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/jskp/xmsh/js/sbsh.js"></script>
		<script type="text/javascript">
		var gridSetting = {
				caption:"�걨���",
				pager:"pager",
				url:"jskpXmsh.do?method=getSbshList&type=query",
				colList:[
					{label:'key',name:'sqid', index: 'sqid',key:true ,hidden:true},
					{label:'��������',name:'splc', index: 'splc',hidden:true},
					{label:'xmid',name:'xmid', index: 'xmid',hidden:true},
					{label:'zdf',name:'zdf', index: 'zdf',hidden:true},
					{label:'zdf',name:'zxf', index: 'zxf',hidden:true},
					{ label : '�꼶', name : 'nj', index : 'nj', width : '4%' },
					{ label : '<bean:message key="lable.xb" />', name : 'xymc', index : 'xymc', width : '12%' },
					{label:'ѧ��',name:'xh', index: 'xh',width:'8%',formatter:xhLink},
					{label:'����',name:'xm', index: 'xm',width:'6%'},
					{label:'�걨��Ŀ',name:'xmmc', index: 'xmmc',width:'10%'},
					{label:'��Ŀ����',name:'xmdlmc', index: 'xmdlmc',width:'8%'},
					{label:'��ʱ��',name:'hjsj', index: 'hjsj',width:'10%'},
					{label:'���״̬',name:'shztmc', index: 'shztmc',width:'10%'},
					{label:'shzt',name:'shzt', index: 'shzt',hidden:true},
					{label:'���Id',name:'shid', index: 'shid',hidden:true},
					{label:'��λId',name:'gwid', index: 'gwid',hidden:true}
				],
				params:{shzt:"dsh"},
				sortname: "sbsj",
			 	sortorder: "desc",
			 	radioselect:false
		}

		var gridSetting2 = {
				caption:"�걨���",
				pager:"pager",
				url:"jskpXmsh.do?method=getSbshList&type=query",
				colList:[
					{label:'key',name:'sqid', index: 'sqid',key:true ,hidden:true},
					{label:'��������',name:'splc', index: 'splc',hidden:true},
					{label:'xmid',name:'xmid', index: 'xmid',hidden:true},
					{ label : '�꼶', name : 'nj', index : 'nj', width : '5%' },
					{ label : '<bean:message key="lable.xb" />', name : 'xymc', index : 'xymc', width : '18%' },
					{ label : 'רҵ', name : 'zymc', index : 'zymc', width : '18%' },
					{ label : '�༶', name : 'bjmc', index : 'bjmc', width : '15%'},
					{label:'ѧ��',name:'xh', index: 'xh',width:'12%',formatter:xhLink},
					{label:'����',name:'xm', index: 'xm',width:'6%'},
					{label:'�걨��Ŀ',name:'xmmc', index: 'xmmc',width:'5%'},
					{label:'��ʱ��',name:'hjsj', index: 'hjsj',width:'5%'},
					{label:'���״̬',name:'shztmc', index: 'shztmc',width:'6%'},
					{label:'shzt',name:'shzt', index: 'shzt',hidden:true},
					{label:'���Id',name:'shid', index: 'shid',hidden:true},
					{label:'��λId',name:'gwid', index: 'gwid',hidden:true}
				],
				params:{shzt:"ysh"},
				sortname: "sbsj",
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
		<html:form action="/jskpXmsh">
			<input type="hidden" name="shkg" id="shkg" value="${shkg}"/>
			<input type="hidden" id="shzt" value="dsh"/>
			<input type="hidden" value="${sfsh}" name="sfsh" id="sfsh"/>
			<%@ include file="/comm/hiddenValue.jsp"%>
			<div class="toolbox">
				<!-- ��ť -->
				<div class="buttonbox">
					<ul>
					<logic:equal name="writeAble" value="yes">
						<li id="li_sh">
							<a href="javascript:void(0);" onclick="sbsh();return false;" 
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
				<span>�걨����б�&nbsp;&nbsp; </span>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable" ></table>
				<div id="pager"></div>
			</div>
		</div>
	</body>
</html>
