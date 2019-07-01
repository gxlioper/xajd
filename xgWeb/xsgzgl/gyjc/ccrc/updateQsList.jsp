<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/gyjc/ccrc/js/ccrc.js"></script>
		<script type="text/javascript">
		var gridSetting = null;
		var gridSetting2 = null;
		jQuery(function(){
		var ccid = jQuery("#ccid").val();
		var zgh = jQuery("#zgh").val();
			 gridSetting = {
				caption : "",
				pager : "pager",
				url : "gyjc_ccrcsz.do?method=updateQsList&type=query&ccid="+ccid,
				colList : [ {
					label : 'key1',
					name : 'lddm',
					index : 'lddm',
					key : true,
					hidden : true
				}, {
					label : 'key2',
					name : 'qsh',
					index : 'qsh',
					key : true,
					hidden : true
				}, {
					label : '¥��',
					name : 'ldmc',
					index : 'ldmc',
					width : '10%'
				}, {
					label : '��',
					name : 'ch',
					index : 'ch',
					width : '20%',
				}, {
					label : '���Һ�',
					name : 'qsh',
					index : 'qsh',
					width : '10%',
				}, {
					label : '����ѧԺ',
					name : 'bmmc',
					index : 'jcxy',
					width : '20%',
				}, {
					label : '�����Ѳ����',
					name : 'wtjs',
					index : 'wtjs',
					width : '10%',
				}, {
					label : '��ѧ���Ѳ����',
					name : 'wtjs',
					index : 'wtjs',
					width : '10%',
				}
				],
				params:{sffp:""},
				sortname : "lddm",
				sortorder : "desc"
				
			}
			
			 gridSetting2 = {
				caption : "",
				pager : "pager",
				url : "gyjc_ccrcsz.do?method=updateQsList&type=yfp&ccid="+ccid+"&zgh="+zgh,
				colList : [ {
					label : 'key1',
					name : 'lddm',
					index : 'lddm',
					key : true,
					hidden : true
				}, {
					label : 'key2',
					name : 'qsh',
					index : 'qsh',
					key : true,
					hidden : true
				}, {
					label : '¥��',
					name : 'ldmc',
					index : 'ldmc',
					width : '10%'
				}, {
					label : '��',
					name : 'ch',
					index : 'ch',
					width : '20%',
				}, {
					label : '���Һ�',
					name : 'qsh',
					index : 'qsh',
					width : '10%',
				}, {
					label : '����ѧԺ',
					name : 'bmmc',
					index : 'jcxy',
					width : '20%',
				}, {
					label : '�����Ѳ����',
					name : 'wtjs',
					index : 'wtjs',
					width : '10%',
				}, {
					label : '��ѧ���Ѳ����',
					name : 'wtjs',
					index : 'wtjs',
					width : '10%',
				}
				],
				params:{sffp:""},
				sortname : "lddm",
				sortorder : "desc"
				
			}
			
			var map = getSuperSearch();
			map["xydm"]=jQuery("#xydm").val();
			map["js"]=jQuery("#js").val();
			map["zgh"]=jQuery("#zgh").val();
			map["ccid"]=jQuery("#ccid").val();
			gridSetting["params"] = map;
			jQuery("#dataTable").initGrid(gridSetting);
		});
		</script>
	</head>

	<body>
		<html:form action="/gyjc_ccrcsz">
			<%@ include file="/comm/hiddenValue.jsp"%>
			<html:hidden property="js" styleId="js" value="${userType}"/>
			<input type="hidden" id="zgh" value="${zgh}"/>
			<input type="hidden" id="ccid" value="${ccid}"/>
			<input type="hidden" id="sffp" value="not"/>
			<div class="toolbox">
				<!-- ��ť -->
				<div class="buttonbox">
					<ul>
					
						<li id="li_fp">
							<a href="javascript:void(0);" class="btn_sz" onclick="updateQs();return false;">����</a>
						</li>	
						<li id="li_qx" style="display: none;">
							<a href="javascript:void(0);" onclick="cancelFp();return false;" 
							   class="btn_qxsh">ȡ������</a>
						</li>	
						
						
					</ul>
				</div>
				<!-- �������� -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- �������� end-->
					<div class="comp_title" id="comp_title">
			      <ul style="width:90%">
			        <li class="ha"><a href="javascript:void(0);" onclick="selectTab(this,'not');"><span>�ɷ�������</span></a></li>
			        <li><a href="javascript:void(0);" onclick="selectTab(this,'');"><span>�ѷ�������</span></a></li>
			      </ul>
			    </div>
			</div>
		</html:form>
		
		<div class="main_box">
			<h3 class=datetitle_01>
				<span>�����б�&nbsp;&nbsp;</span>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable" ></table>
				<div id="pager"></div>
			</div>
		</div>
	</body>
</html>
