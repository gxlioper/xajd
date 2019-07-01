<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="xsgzgl/dtjs/tsdzb/js/tsdzb.js"></script>
		<script type="text/javascript">
		jQuery(function(){
			var gridSetting = {
				caption : "��ɫ��֧���걨�б�",
				pager : "pager",
				url : "dtjs_tsdzb.do?method=getTsdzbList&type=query",
				colList : [
							{ label : 'dzbid', name : 'dzbid', index : 'dzbid',key : true, hidden : true },
							{ label : '��֧��', name : 'dzbmc', index : 'dzbmc', width : '15%',formatter:xhLink },
							{ label : '������', name : 'fzr', index : 'fzr', width : '10%' },
							{ label : '��ϵ��ʽ', name : 'lxfs', index : 'lxfs', width : '15%' },
							{ label : '����ʱ��', name : 'cjsj', index : 'cjsj', width : '15%' },
							{ label : '��Ͻ�༶', name : 'bmmc', index : 'bmmc', width : '35%' },
							{ label : '���״̬', name : 'shztmc', index : 'shztmc', width : '5%' },
							{ label : 'shzt', name : 'shzt', index : 'shzt', hidden : true },
							{ label : '����', name : 'pf', index : 'pf', width : '5%' }
						  ]
						      }
			var map = getSuperSearch();
			gridSetting["params"] = map;
			jQuery("#dataTable").initGrid(gridSetting);
		})
		</script>
	</head>

	<body>
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>${title }</a>
			</p>
		</div>
		<html:form action="/dtjs_tsdzb">
			<%@ include file="/comm/hiddenValue.jsp"%>
			
			<div class="toolbox">
				<!-- ��ť -->
				<div class="buttonbox">
					<ul>
						<logic:equal name="writeAble" value="yes">
							<li>
								<a href="javascript:void(0);" class="btn_zj" onclick="add();return false;"  >����</a>
							</li>
							<li>
								<a href="javascript:void(0);" onclick="update();return false;" class="btn_xg" >�޸�</a>
							</li>
							<li>
								<a href="javascript:void(0);" onclick="del();return false;" class="btn_sc" >ɾ��</a>
							</li>
						</logic:equal>
							<li><a href="#" class="btn_dc" onclick="exportConfig();return false;">����</a></li>	
						
					</ul>
				</div>
				<!-- �������� -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- �������� end-->
			</div>
		</html:form>
		<div class="main_box">
			<h3 class=datetitle_01>
				<span>��ɫ��֧���걨�б�&nbsp;&nbsp; </span>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable" ></table>
				<div id="pager"></div>
			</div>
		</div>
	</body>
</html>
