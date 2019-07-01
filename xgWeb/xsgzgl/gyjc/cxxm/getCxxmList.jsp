<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/gyjc/cxxm/js/cxxm.js"></script>
		<script type="text/javascript">
		var gridSetting = {
				caption:"",
				pager:"pager",
				url:"gyjc_ccxmsz.do?method=getCxxmList&type=query",
				colList:[
					{label:'���',name:'xh', index: 'xh',width:'20%'},
					{label:'dm',name:'dm', index: 'dm',hidden:true},
					{label:'��Ŀ/Ҫ��',name:'mc', index: 'mc',width:'80%'},
				],
				radioselect:false,
				pageList:[10000]
		}

	
		
			
		jQuery(function(){
			var map = {};
			map["js"] = jQuery("#userType").val();
			gridSetting["params"] = map;
			jQuery("#dataTable").initGrid(gridSetting);
		});
		</script>
	</head>

	<body>
		<html:form action="/gyjc_ccxmsz">
		<%@ include file="/comm/hiddenValue.jsp"%>
			<input type="hidden" id="userType" value="${userType}"/>
			<div class="toolbox">
				<!-- ��ť -->
				<div class="buttonbox">
					<ul>
						<li>
							<a href="javascript:void(0);" class="btn_fh" onclick="fhjcsd();return false;"  >����</a>
						</li>	
						<li>
							<a href="javascript:void(0);" class="btn_zj" onclick="addPage();return false;"  >����</a>
						</li>	
						<li>
							<a href="javascript:void(0);" onclick="update();return false;" 
							   class="btn_xg">�޸�</a>
						</li>	
						<li>
							<a href="javascript:void(0);" onclick="del();return false;" 
							   class="btn_sc">ɾ��</a>
						</li>	
						
					</ul>
				</div>
					<div class="searchtab">
			</div>
				<div class="comp_title" id="comp_title">
			      <ul style="width:90%">
			        <li class="ha"><a href="javascript:void(0);" onclick="selectTab(this,'1');"><span>����������������</span></a></li>
			        <li><a href="javascript:void(0);" onclick="selectTab(this,'2');"><span>����ϰ������</span></a></li>
			      </ul>
			    </div>
			</div>
		</html:form>
		
		<div class="main_box">
			<h3 class=datetitle_01>
				<span>���ֱ�׼�б�&nbsp;&nbsp; </span>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable" ></table>
				<div id="pager"></div>
			</div>
		</div>
	</body>
</html>
