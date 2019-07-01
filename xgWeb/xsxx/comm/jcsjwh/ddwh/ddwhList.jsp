<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="xsxx/comm/jcsjwh/js/ddwh.js"></script>
		<script type="text/javascript">
			var gridSetting = {
				caption:"��ѯ���",
				pager:"pager",
				multiselect:true,
				rowNum:10,
				url:"jcsj_ddwh.do?method=ddwhList&type=query",
				colList:[
				   {label:'��Ӵ���',name:'dddm', index: 'dddm',width:'30%',key:true},
				   {label:'�������',name:'ddmc', index: 'ddmc',width:'30%'},
				   {label:'������',name:'qds', index: 'qds',width:'30%',formatter:setQd}
				],
				sortname: "dddm",
			 	sortorder: "asc"
			}
			
			jQuery(function(){
				searchRs();
			});
			
			function searchRs(){
				var map = getSuperSearch();	
				gridSetting["params"] = map;
				jQuery("#dataTable").initGrid(gridSetting);
			}
		</script>
	</head>

	<body>
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>${title }</a>
			</p>
		</div>
		<html:form action="/zjjcddwh">
			<div class="toolbox">
				<div class="buttonbox">
				  <ul>
					<logic:equal value="yes" name="writeAble">
						<li>
							<a href="javascript:void(0);" onclick="zjDdsj();return false;" class="btn_zj" >����</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="modDdsj();return false;" class="btn_xg" >�޸�</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="delDdsj();return false;" class="btn_sc" >ɾ��</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="qdwh();return false;" class="btn_sz" >����ά��</a>
						</li>
					</logic:equal>
				  </ul>
				</div>
				<!-- �������� -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- �������� end-->
			</div>
		</html:form>
		<div>
			<!--����start-->
			<h3 class="datetitle_01">
				<span> �����Ϣ�б�
				 </span>
			</h3>
		</div>
		<div class="formbox" style="width:100%;height:330px;overflow-x:hidden;overflow-y:auto;">
			<table id="dataTable" ></table>
		</div>
		<div id="pager"></div>
	</body>
</html>
