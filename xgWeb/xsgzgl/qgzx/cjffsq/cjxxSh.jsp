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
					caption:"����б�",
					pager:"pager",
					url:"qgzx_cjffsq_ajax.do?method=cjxxSh",
					colList : [
								{ label : 'pkvalue', name : 'pkvalue', index : 'pkvalue',hidden : true },
								{ label : 'sqid', name : 'sqid', index : 'sqid',hidden : true },
								{ label : 'splc', name : 'splc', index : 'splc',hidden : true },
								{ label : 'ѧ��', name : 'xn', index : 'xn', width : '10%' },
								<logic:equal name="qgzq" value="xq">
								{ label : 'ѧ��', name : 'xqmc', index : 'xqmc', width : '10%' },
								</logic:equal>		
								{ label : '���˵�λ����', name : 'yrdwmc', index : 'yrdwmc', width : '15%' },
								{ label : '��������', name : 'ffny', index : 'ffny', width : '5%' },
								{ label : '�о���������', name : 'yjsrs', index : 'yjsrs', width : '8%' },
								{ label : '������������', name : 'bksrs', index : 'bksrs', width : '8%' },
								{ label : '���Ž��<Ԫ>', name : 'ffje', index : 'ffje', width : '10%' },
								{ label : '���״̬', name : 'shztmc', index : 'shztmc', width : '16%' },
								{ label : '���״̬', name : 'shzt', index : 'shzt', hidden : true},
								{ label : '���Id', name : 'shid', index : 'shid', hidden : true},
								{ label : '��λId', name : 'gwid', index : 'gwid', hidden : true}
								],
					params:{shzt:"dsh"},
					sortname: "ffny",
				 	sortorder: "desc",
				 	radioselect:false
			};
			gridSetting2 = {
					caption:"����б�",
					pager:"pager",
					url:"qgzx_cjffsq_ajax.do?method=cjxxSh",
					colList : [
								{ label : 'pkvalue', name : 'pkvalue', index : 'pkvalue',hidden : true },
								{ label : 'sqid', name : 'sqid', index : 'sqid',hidden : true },
								{ label : 'splc', name : 'splc', index : 'splc',hidden : true },
								{ label : 'ѧ��', name : 'xn', index : 'xn', width : '10%' },
								<logic:equal name="qgzq" value="xq">
								{ label : 'ѧ��', name : 'xqmc', index : 'xqmc', width : '10%' },
								</logic:equal>		
								{ label : '���˵�λ����', name : 'yrdwmc', index : 'yrdwmc', width : '15%' },
								{ label : '��������', name : 'ffny', index : 'ffny', width : '5%' },
                                { label : '�о���������', name : 'yjsrs', index : 'yjsrs', width : '8%' },
                                { label : '������������', name : 'bksrs', index : 'bksrs', width : '8%' },
								{ label : '���Ž��<Ԫ>', name : 'ffje', index : 'ffje', width : '10%' },
								{ label : '���״̬', name : 'shztmc', index : 'shztmc', width : '16%' },
								{ label : '���״̬', name : 'shzt', index : 'shzt', hidden : true},
								{ label : '���Id', name : 'shid', index : 'shid', hidden : true},
								{ label : '��λId', name : 'gwid', index : 'gwid', hidden : true}
								],
					params:{shzt:"dsh"},
					sortname: "ffny",
				 	sortorder: "desc",
				 	radioselect:false
			}	

			
		jQuery(function(){
			var map = getSuperSearch();
			map["shzt"]="dsh";
			gridSetting["params"] = map;
			jQuery("#dataTable").initGrid(gridSetting);
		});
	
		function searchRs() {
			var map = getSuperSearch();
			var shzt = jQuery("#shzt").val();
			if (null!=shzt&&shzt != "") {
				map["shzt"] = shzt;
			}else{
				map["shzt"] = "dsh";
			}
			jQuery("#dataTable").reloadGrid(map);
		}
		
		function selectTab(obj, shzt) {
			jQuery("#shzt").val(shzt);
			if (shzt == "dsh") {
				jQuery("#li_sh").css("display", "");
				jQuery("#li_qx").css("display", "none");
				var map = getSuperSearch();
				map["shzt"]="dsh";
				gridSetting["params"] = map;
				jQuery("#dataTable").initGrid(gridSetting);
			} else {
				jQuery("#li_sh").css("display", "none");
				jQuery("#li_qx").css("display", "");
				var map = getSuperSearch();
				map["shzt"]="ysh";
				gridSetting2["params"] = map;
				jQuery("#dataTable").initGrid(gridSetting2);
			}
			jQuery(".ha").removeClass("ha");
			jQuery(obj).parent().addClass("ha");
		}
		
		function showView(){
			var rows = jQuery("#dataTable").getSeletRow();
			if (rows.length != 1) {
				showAlertDivLayer("�빴ѡһ����Ҫ�鿴������")
				return false;
			}
			var url="qgzx_cjffsq.do?method=cjxxCk&pkValue="+rows[0]["pkvalue"]+"&shck=1&sqid="+rows[0]['sqid'];
			showDialog('', 900, 525, url);
		}
		
		function sbsh() {
			var rows = jQuery("#dataTable").getSeletRow();
			var shzt = jQuery("#shzt").val();
			if (rows.length == 0) {
				showAlertDivLayer("��ѡ����Ҫ��˵ļ�¼")
				return false;
			}
			if (shzt == "ysh") {
				showAlertDivLayer("�Ѵ����¼�����ٴ����");
				return false;
			} else if (rows.length == 1) {
				var url = "qgzx_cjffsq.do?method=cjxxShCk&pkValue=" + rows[0]["pkvalue"] + '&shid=' + rows[0]["shid"];
				showDialog("���", 900, 525, url);
			} else {
				showAlertDivLayer("�빴ѡһ����Ҫ��˵�����");
				//showDialog("�������", 500, 250, "qgzx_cjffsq.do?method=cjxxPlsh");
			}
		}
		
		function savePlsh(shjg, shyj) {
			var rows = jQuery("#dataTable").getSeletRow();
			var sqid = new Array();
			var gwid = new Array();
			var splc = new Array();
			jQuery.each(rows, function(i, row) {
				sqid.push(row["sqid"]);
				gwid.push(row["gwid"]);
				splc.push(row["splc"]);
			});
			jQuery.post("qgzx_cjffsq_ajax.do?method=sbPlsh&type=save", {
				shjg : shjg,
				sqid : sqid,
				gwids : gwid,
				shyj:shyj,
			}, function(data) {
				showAlertDivLayer(data["message"], {}, {
					"clkFun" : function() {
						jQuery("#dataTable").reloadGrid();
					}
				});
			}, 'json');
		}
		
		function splcInfo(){
			var ids = jQuery("#dataTable").getSeletIds();
			var rows = jQuery("#dataTable").getSeletRow();
			if (1!=ids.length){
				showAlertDivLayer("��ѡ��һ�����̸��ټ�¼��");
			} else {		
				showDialog("�������̸���",480,380,'comm_spl.do?method=lcgz&sqid='+rows[0]['sqid']+"&splc="+rows[0]['splc']);
			}
		}
		
		function cxsh(){
			var rows = jQuery("#dataTable").getSeletRow();
			if (rows.length != 1){
				showAlertDivLayer("��ѡ��һ����Ҫ��������˼�¼��");
			}else{
				var splc = rows[0]["splc"];
				var shid = rows[0]["shid"];
				var sqid = rows[0]["sqid"];
				var shzt = rows[0]["shzt"];
				showConfirmDivLayer("��ȷ��Ҫ�����Ըü�¼����˲�����",{"okFun":function(){
					jQuery.post("qgzx_cjffsq_ajax.do?method=cxsh",{splc:splc,shid:shid,sqid:sqid,shzt:shzt},function(data){
						showAlertDivLayer(data["message"],{},{"clkFun":function(){
							jQuery("#dataTable").reloadGrid();
						}});
				},'json');
				}});
			}
		}
		
		</script>
	</head>

	<body>
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>${title }</a>
			</p>
		</div>
		<html:form action="/qgzx_cjffsq">
			<input type="hidden" name="shkg" id="shkg" value="${shkg}"/>
			<input type="hidden" id="shzt" value="dsh"/>
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
							<a href="javascript:void(0);" onclick="cxsh();return false;" 
							   title="ѡ��һ����¼������ð�ť�����Գ���ʧ�����˲�����"
							   class="btn_qxsh">����</a>
						</li>	
						</logic:equal>					
						<li><a href="javascript:void(0);" onclick="splcInfo();return false;" 
							   title="ѡ��һ����¼������ð�ť���Բ鿴������̡�"
							   class="btn_cs">���̸���</a></li>	
						<li>
							<a href="#" onclick="showView();" class="btn_ck">�鿴��ϸ</a>
						</li>
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
				<span>����б�&nbsp;&nbsp; </span>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable" ></table>
				<div id="pager"></div>
			</div>
		</div>
	</body>
</html>
