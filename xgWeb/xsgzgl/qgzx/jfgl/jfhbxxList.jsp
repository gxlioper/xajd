<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="xsgzgl/qgzx/jfgl/js/jfhbzjdx.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/exportNew/import.js"></script>
		<script type="text/javascript">
		jQuery(function(){
			var gridSetting = {
				caption:"���ѻ�����Ϣ�б�",
				pager:"pager",
				url:"qgzx_jfgl.do?method=jfhbxxList&type=query",
				colList:[
					{ label : 'pkvalue', name : 'pkvalue', index : 'pkvalue',key : true, hidden : true },
					{ label : '�к�', name : 'rowindex', index : 'rowindex', width : '2%'},
					{ label : '���', name : 'nd', index : 'nd', width : '4%'},
					{ label : '���˵�λ', name : 'bmmc', index : 'yrdwdm', width : '10%'},
					{ label : '�����ܽ��<Ԫ>', name : 'hbzje', index : 'hbzje', width : '10%'},
					{ label : '�ѷ��Ž��<Ԫ>', name : 'ffzje', index : 'ffzje', width : '8%'},
					{ label : 'ʣ����<Ԫ>', name : 'syje', index : 'syje', width : '8%'}
				],
				sortname : "bmmc",
			    sortorder : "asc" }
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
		<html:form action="/qgzx_jfgl">
			<%@ include file="/comm/hiddenValue.jsp"%>
			<div class="toolbox">
				<!-- ��ť -->
				<div class="buttonbox">
					<ul>
						<logic:equal name="writeAble" value="yes">
						<li>
							<a href="javascript:void(0);" class="btn_zj" onclick="add();return false;" >��������</a>
						</li>
						<li>
							<a href="javascript:void(0);" class="btn_xg" onclick="showModi('update');return false;" >�����޸�</a>
						</li>
						
						<li>
							<a href="javascript:void(0);" class="btn_dr" onclick="importJfhb();return false;" >����</a>
						</li>
						</logic:equal>
						<li>
							<a href="javascript:void(0);" class="btn_ck" onclick="view();return false;" >�鿴��ϸ</a>
						</li>	
						
						<logic:equal name="userName" value="zf01">
							<li>
								<a href="#" class="btn_dc" onclick="exportConfig();return false;" >����</a>
							</li>	
						</logic:equal>	
					</ul>
				</div>
				<!-- �������� -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- �������� end-->
			</div>
		</html:form>
		<div class="main_box">
			<h3 class=datetitle_01>
				<span>���ѻ�����Ϣ�б�&nbsp;&nbsp; </span>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable" ></table>
				<div id="pager"></div>
			</div>
		</div>
	</body>
</html>