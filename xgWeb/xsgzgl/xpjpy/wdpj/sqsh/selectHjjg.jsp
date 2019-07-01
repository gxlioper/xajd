<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript">
			var gridSetting = {
				caption : "",
				pager : "pager",
				url : "xpj_sqsh.do?method=searchHjjg",
				colList : [ {
					label : 'key',
					name : 'id',
					index : 'id',
					key : true,
					hidden : true
				},{
					label : 'ѧ��',
					name : 'xn',
					index : 'xn',
					width : '5%'
				}
				,{
					label : 'ѧ��',
					name : 'xqmc',
					index : 'xqmc',
					width : '5%'
				}
				, {
					label : 'ѧ��',
					name : 'xh',
					index : 'xh',
					width : '10%'
				}, {
					label : '����',
					name : 'xm',
					index : 'xm',
					width : '10%'
				}, {
					label : 'ѧԺ',
					name : 'xymc',
					index : 'xydm',
					width : '10%'
				},{
					label : '�༶',
					name : 'bjmc',
					index : 'bjdm',
					width : '10%'
				},{
					label : '������',
					name : 'hjmc',
					index : 'hjmc',
					width : '10%'
				},{
					label : '�佱��λ',
					name : 'fjdw',
					index : 'fjdw',
					width : '15%'
				},{
					label : '��ʱ��',
					name : 'hjsj',
					index : 'hjsj',
					width : '10%'
				}
				],
				sortname : "hjsj",
				sortorder : "desc"
			}
			var gridSetting2 = {
					caption : "",
					pager : "pager",
					url : "xpj_sqsh.do?method=searchHjjg",
					colList : [ {
						label : 'key',
						name : 'id',
						index : 'id',
						key : true,
						hidden : true
					},{
						label : 'ѧ��',
						name : 'xn',
						index : 'xn',
						width : '5%'
					}
					,{
						label : 'ѧ��',
						name : 'xqmc',
						index : 'xqmc',
						width : '5%'
					}
					, {
						label : 'ѧ��',
						name : 'xh',
						index : 'xh',
						width : '10%'
					}, {
						label : '����',
						name : 'xm',
						index : 'xm',
						width : '10%'
					}, {
						label : 'ѧԺ',
						name : 'xymc',
						index : 'xydm',
						width : '10%'
					},{
						label : '�༶',
						name : 'bjmc',
						index : 'bjdm',
						width : '10%'
					},{
						label : '������',
						name : 'hjmc',
						index : 'hjmc',
						width : '10%'
					},{
						label : '�佱��λ',
						name : 'fjdw',
						index : 'fjdw',
						width : '15%'
					},{
						label : '��ʱ��',
						name : 'hjsj',
						index : 'hjsj',
						width : '10%'
					}
					],
					sortname : "hjsj",
					sortorder : "desc"
				}
		jQuery(function(){
			var map = getSuperSearch();
			gridSetting["params"] = map;
			map['xh'] = jQuery("#xh").val();
			map["sfysq"]="yes";
			jQuery("#dataTable").initGrid(gridSetting);

		});
		function selectTab(obj, sfysq) {
			jQuery("#sfysq").val(sfysq);
			if (sfysq == "yes") {
				jQuery("#li_sc").css("display", "");
				jQuery("#li_bc").css("display", "none");
				var map = getSuperSearch();
				map["sfysq"]="yes";
				map['xh'] = jQuery("#xh").val();
				gridSetting["params"] = map;
				jQuery("#dataTable").initGrid(gridSetting);
			} else {
				jQuery("#li_sc").css("display", "none");
				jQuery("#li_bc").css("display", "");
				var map = getSuperSearch();
				map["sfysq"]="no";
				map['xh'] = jQuery("#xh").val();
				gridSetting2["params"] = map;
				jQuery("#dataTable").initGrid(gridSetting2);
			}
			jQuery(".ha").removeClass("ha");
			jQuery(obj).parent().addClass("ha");
		}


		//����
		function add(){
			var rows = jQuery("#dataTable").getSeletRow();
			if(rows.length == 0){
				return showAlert("������ѡ��һ����¼��");
			}
			var xh = jQuery("#xh").val();
			var ids = new Array();
			var html = "";
			jQuery(rows).each(function(i,row){
				var id = row['id'];
				ids.push(id);
				
			})
		    var rs = null;
			var api = frameElement.api;
		    var sqid = jQuery(api.get('parentDialog').document).find("#sqid").val();
			jQuery.post("xpj_sqsh.do?method=saveHjxx", {
				xh : xh,
				ids : ids,
				sqid : sqid
			}, function(data) {
				rs = data;
				if(rs["message"] == "true"){
					var parentTbody = jQuery(api.get('parentDialog').document).find("#hjxx");
					var rsList = rs["rsList"];
					for(var i = 0;i < rsList.length;i++){
						var rsMap = rsList[i];
						html += "<tr><td>"+rsMap["hjmc"]+"</td>";
						html += "<td>"+rsMap["hjsj"]+"</td>";
						html += "<td>"+rsMap["fjdw"]+"</td></tr>";
					}
					jQuery("tr",parentTbody).remove();
					parentTbody.append(html);
					api.close();
				}else{
					return showAlert("���ݱ���ʧ�ܣ�");
				}
			}, 'json');
			
		}

		function del() {
			var ids = jQuery("#dataTable").getSeletIds();
			var rows = jQuery("#dataTable").getSeletRow();
			var rs = null;
			var api = frameElement.api;
			var html = "";
			var xh = jQuery("#xh").val();
			if (ids.length == 0){
				showAlertDivLayer("��ѡ����Ҫɾ���ļ�¼��");
			} else {
				showConfirmDivLayer("��ȷ��Ҫɾ��ѡ��ļ�¼��",{"okFun":function(){
					jQuery.post("xpj_sqsh.do?method=delHjxx",{values:ids.toString(),xh:xh},function(data){
						rs = data;
						var parentTbody = jQuery(api.get('parentDialog').document).find("#hjxx");
						var rsList = rs["rsList"];
						for(var i = 0;i < rsList.length;i++){
							var rsMap = rsList[i];
							html += "<tr><td>"+rsMap["hjmc"]+"</td>";
							html += "<td>"+rsMap["hjsj"]+"</td>";
							html += "<td>"+rsMap["fjdw"]+"</td></tr>";
						}
						jQuery("tr",parentTbody).remove();
						parentTbody.append(html);
						api.close();
					},'json');
				}});
			}
		}
		</script>
	</head>

	<body>
		
		<html:form action="/xpj_sqsh">
			<%@ include file="/comm/hiddenValue.jsp"%>
			<html:hidden property="xh" styleId="xh" value="${xh}"/>
			<html:hidden property="sfysq" styleId="sfysq" value="yes"/>
			<div class="toolbox">
				<!-- ��ť -->
				<div class="buttonbox">
					<ul>
						<li id="li_bc" style="display: none;"><a href="#" class="btn_zj" onclick="add();return false;">����</a></li>	
						<li id="li_sc">
							<a href="javascript:void(0);" onclick="del();return false" class="btn_sc" >ɾ��</a>
						</li>
					</ul>
				
				</div>
				<!-- �������� -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- �������� end-->
				<div class="comp_title" id="comp_title">
			      <ul style="width:90%">
			        <li class="ha"><a href="javascript:void(0);" onclick="selectTab(this,'yes');"><span>��ѡ��</span></a></li>
			        <li><a href="javascript:void(0);" onclick="selectTab(this,'no');"><span>δѡ��</span></a></li>
			      </ul>
			    </div>
			</div>
		</html:form>
		
		<div class="main_box">
			<h3 class=datetitle_01>
				<span>�������б�&nbsp;&nbsp; </span>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable" ></table>
				<div id="pager"></div>
			</div>
		</div>
	</body>
</html>
