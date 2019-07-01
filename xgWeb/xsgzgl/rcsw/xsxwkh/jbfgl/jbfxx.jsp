<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
			<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
			<script type="text/javascript" src="xsgzgl/comm/exportNew/import.js"></script><!-- ���빦����Ҫ -->
		<script type="text/javascript" src="js/calendar/calendar.js"></script>  <!-- �߼���ѯʱ����Ҫ -->
			<script type="text/javascript" src="xsgzgl/rcsw/xsxwkh/jbfgl/js/jbfgl.js"></script>
			<script type="text/javascript">
				var gridSetting = {
					caption : "չʾ��Ϣ�б�",
					pager : "pager",
					url : "xsxwkhJbfgl.do?method=getjbflist&type=query",
					colList:[										
					    { label:'jbfid',name:'jbfid', index: 'jbfid',hidden:true,key:true},
						{ label : 'ѧ��', name : 'xh', index : 'xh', width : '10%',formatter:xhLink},
						{ label : 'ѧ��', name : 'xn', index : 'xn', width : '10%'},
						{ label : '����', name : 'xm', index : 'xm', width : '8%' },
						{ label : 'ѧԺ', name : 'xymc', index : 'xymc', width : '12%'},
						{ label : '�༶', name : 'bjmc', index : 'bjmc', width : '10%'},
						{ label : '�����θ���Ա�����ȼ�', name : 'bzrdj', index : 'bzrcpdj', width : '10%'},
						{ label : '�༶ѧ�������ȼ�', name : 'xsdj', index : 'xscpdj', width : '10%'},
						{ label : '�ܷ�', name : 'zf', index : 'zf', width : '8%'}
						],
						sortname: "zf,xn,xymc,bjmc",
				 		sortorder: "desc"
				 	}	
				jQuery(function(){
					var map = getSuperSearch();
					gridSetting["params"] = map;
					jQuery("#dataTable").initGrid(gridSetting);
							
				});
	
				function xhLink(cellValue,rowObject){
					return "<a href='javascript:void(0);' class='name' onClick='viewJbfgl(\""+rowObject["jbfid"]+"\",\""+cellValue+"\")'>"+cellValue+"</a>";
				
				}
				function searchRs(){
					var map = getSuperSearch();
					jQuery("#dataTable").reloadGrid(map);
				}
			</script>
	</head>
	<body>
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>${title }</a>
			</p>
		</div>
		<html:form action="/demo">
			<%@ include file="/comm/hiddenValue.jsp"%>
			
			<div class="toolbox">		
				<div class="buttonbox">
					<ul>
						<logic:equal name="writeAble" value="yes">
						<li><a href="javascript:void(0);" onclick="add();" class="btn_zj">����</a></li>
						<li>
							<a href="javascript:void(0);" onclick="update();" class="btn_xg" >�޸�</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="del();" class="btn_sc" >ɾ��</a>
						</li>
						<li>
							<a href="#" onclick="importXX();return false;" class="btn_dr">����</a>
						</li>
						</logic:equal>
						<li>
							<a href="#" class="btn_dc" onclick="exportXX();return false;">����</a>
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
				<span>��������Ϣ�б�&nbsp;&nbsp; </span>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable" ></table>
				<div id="pager"></div>
			</div>
		</div>
	
	</body>
</html>
