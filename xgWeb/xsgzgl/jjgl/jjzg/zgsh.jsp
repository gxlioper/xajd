<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript">
			var gridSetting = {
				caption:"�ҽ��ʸ�����б�",
				pager:"pager",
				rowNum:10,
				url:"jjgl_jjzg.do?method=getZgshList",
				params:{shzt:'dsh'},
				colList:[
				   {label:'ID',name:'sqid', index: 'sqid',hidden:true,key:true},
				   {label:'splcid',name:'splcid', index: 'splcid',hidden:true},
				   {label:'ѧ��',name:'xh', index: 'xh',formatter:function(v,r){
					   
					   return "<a class='name' onclick='showDialog(\"�鿴\",700,500,\"jjgl_jjzg.do?method=jjzgView&sqid="+r["sqid"]+"\")'>"+v+"</a>";
				   }},
				   {label:'����',name:'xm', index: 'xm'},
				   {label:'�꼶',name:'nj', index: 'nj'},
				   {label:'ѧԺ',name:'xymc', index: 'xydm'},
				   {label:'רҵ',name:'zymc', index: 'zydm'},
				   {label:'�༶',name:'bjmc', index: 'bjdm'},
				   {label:'����ʱ��',name:'sqsj', index: 'sqsj'},
				   {label:'���״̬',name:'shztmc', index: 'shzt'}
				]
			};

			jQuery(function(){
				jQuery("#dataTable").initGrid(gridSetting);
			});
	
			function searchRs(){
				var map = getSuperSearch();
				map["shzt"] = jQuery("#shzt").val();
				jQuery("#dataTable").reloadGrid(map);
			}
			
			function selectTab(obj,zt){
				jQuery(".ha").removeClass("ha");
				jQuery(obj).parent().addClass("ha");
				jQuery("#shzt").val(zt);
				
				var map = getSuperSearch();
				map["shzt"] = zt;
				jQuery("#dataTable").reloadGrid(map);
			}
			
			
			function showZgsh() {
				var rows = jQuery("#dataTable").getSeletRow();
				if (rows.length == 0) {
					showAlert("��ѡ��һ����Ҫ��˵ļ�¼��");
				} else if(rows.length == 1){
					var sqid = rows[0]["sqid"];
					var url = "jjgl_jjzg.do?method=jjzgDgsh&sqid="+sqid;
					showDialog("�ҽ��ʸ����",800,500,url);
				}
			}
			
			function lcgz() {
				var ids = jQuery("#dataTable").getSeletIds();
				var rows = jQuery("#dataTable").getSeletRow();
				if (ids.length != 1) {
					showAlertDivLayer("��ѡ��һ�����̸��ټ�¼��");
				} else {
					showDialog("�ҽ��ʸ�������̸���", 600, 400, 'comm_spl.do?method=lcgz&sqid='
							+ rows[0]['sqid'] + "&splc=" + rows[0]['splcid']);
				}
			}
		</script>
	</head>

	<body>
		<html:form action="jjgl_jjzg.do?method=zgsh">
			<%@ include file="/comm/hiddenValue.jsp"%>
			<input type="hidden" name="shzt" id="shzt" value="dsh"/>
		<!-- ���� -->
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>${title }</a>
			</p>
		</div>
		<!-- ���� end-->
	
		<div class="toolbox">
			<!-- ��ť -->
			<logic:equal name="writeAble" value="yes">	
				<div class="buttonbox">
					<ul>
						<li><a href="#" class="btn_sh" onclick="showZgsh();return false;">���</a></li>
						<li><a href="#" class="btn_cs" onclick="lcgz();return false;">���̸���</a></li>
					</ul>
				</div>
			</logic:equal>
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
	
		<div>
			<h3 class="datetitle_01">
				<span>�ҽ��ʸ�����б�</span>
			</h3>
		</div>
		<div class="formbox" style="width:100%;height:330px;overflow-x:hidden;overflow-y:auto;">
			<table id="dataTable" ></table>
		</div>
		<div id="pager"></div>
		</html:form>
	</body>
</html>
