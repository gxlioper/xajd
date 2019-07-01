<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/dagl/cqxxdaxxgl/js/cqxxdaxxgl.js"></script>
		<script type="text/javascript">
			var gridSetting = {
				caption : "��Ϣ�б�",
				pager : "pager",
				url : "cqxxDaxxgl.do?method=getdalist&type=query",
				colList:[							
						    { label :'key',name:'bjid', index: 'bjid',hidden:true,key:true},
							{ label : 'ѧԺ', name : 'xymc', index : 'xymc', width : '12%'},
							{ label : 'רҵ', name : 'zymc', index : 'zymc', width : '10%'},
							{ label : '�꼶', name : 'nj', index : 'nj', width : '10%'},
							{ label : '�༶', name : 'bjmc', index : 'bjmc', width : '10%'},
							{ label : '�༶������', name : 'bjrs', index : 'bjrs', width : '8%',formatter : bjrsLink	 },
							{ label : '��ά������', name : 'ywhrs', index : 'ywhrs', width : '8%'},
							{ label : 'δά������', name : 'wwhrs', index : 'wwhrs', width : '8%',formatter : wwhrsLink },
							{ label : '�༶',name : 'bjdm',index : 'bjdm',hidden : true}
							],
							sortname: "nj,zymc,xymc,bjmc",
					 		sortorder: "desc",
					 		radioselect:true
					 	}	
			jQuery(function(){
				var map = getSuperSearch();
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
		<html:form action="/cqxxDaxxgl">
			<%@ include file="/comm/hiddenValue.jsp"%>
			<div class="toolbox">
				<!-- ��ť -->
				<div class="buttonbox">
					<ul>
						<logic:equal name="writeAble" value="yes">
						<li>
							<a href="javascript:void(0);" class="btn_xg" onclick="wh();return false;"  >ά��������Ϣ</a>
						</li>
						</logic:equal>
							<li>
							<a href="#" class="btn_dc" onclick="exportWH();return false;">����</a>
						</li>	
					</ul>
				</div>
				<!-- �������� -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- �������� end-->
			</div>
		</html:form>
		
		<div class="main_box">
			<h3 class=datetitle_01>
				<span>�༶�б�&nbsp;&nbsp; </span>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable" ></table>
				<div id="pager"></div>
			</div>
		</div>
	</body>
</html>
