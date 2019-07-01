<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/dtjs/tsdzb/js/tsdzbsh.js"></script>
		<script type="text/javascript">
		var gridSetting = {
				caption:"��ɫ��֧������б�",
				pager:"pager",
				url:"dtjs_tsdzbsh.do?method=getTsdzbShList&type=query",
				colList : [
							{ label : 'dzbid', name : 'dzbid', index : 'dzbid',key : true, hidden : true },
							{ label : '��֧��', name : 'dzbmc', index : 'dzbmc', width : '15%',formatter:xhLink },
							{ label : '������', name : 'fzr', index : 'fzr', width : '10%' },
							{ label : '��ϵ��ʽ', name : 'lxfs', index : 'lxfs', width : '15%' },
							{ label : '����ʱ��', name : 'cjsj', index : 'cjsj', width : '15%' },
							{ label : '��Ͻ�༶', name : 'bmmc', index : 'bmmc', width : '35%' },
							{ label : '���״̬', name : 'shztmc', index : 'shztmc', width : '10%' },
							{ label : 'shzt', name : 'shzt', index : 'shzt', hidden : true },
							{ label : '����', name : 'pf', index : 'pf', width : '5%' }
						  ],
				params:{shzt:"dsh"},
			 	radioselect:false
		}

		var gridSetting2 = {
				caption:"��ɫ��֧������б�",
				pager:"pager",
				url:"dtjs_tsdzbsh.do?method=getTsdzbShList&type=query",
				colList : [
							{ label : 'dzbid', name : 'dzbid', index : 'dzbid',key : true, hidden : true },
							{ label : '��֧��', name : 'dzbmc', index : 'dzbmc', width : '15%',formatter:xhLink },
							{ label : '������', name : 'fzr', index : 'fzr', width : '10%' },
							{ label : '��ϵ��ʽ', name : 'lxfs', index : 'lxfs', width : '15%' },
							{ label : '����ʱ��', name : 'cjsj', index : 'cjsj', width : '15%' },
							{ label : '��Ͻ�༶', name : 'bmmc', index : 'bmmc', width : '35%' },
							{ label : '���״̬', name : 'shztmc', index : 'shztmc', width : '10%' },
							{ label : 'shzt', name : 'shzt', index : 'shzt', hidden : true },
							{ label : '����', name : 'pf', index : 'pf', width : '5%' }
						  ],
				params:{shzt:"ysh"},
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
		<input type="hidden" id="shzt" value="dsh"/>
		<html:form action="/dtjs_tsdzbsh">		
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
						<li><a href="#" class="btn_dc" onclick="exportConfig();return false;">����</a></li>
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
				<span>��ɫ��֧������б�&nbsp;&nbsp; </span>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable" ></table>
				<div id="pager"></div>
			</div>
		</div>
	</body>
</html>
