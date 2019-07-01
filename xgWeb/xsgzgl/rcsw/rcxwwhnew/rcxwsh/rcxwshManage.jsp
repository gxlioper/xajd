<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript">
			var gridSetting = {
					caption:"�ճ���Ϊ���",
				pager:"pager",
				url:"rcsw_rcxwwhnew_rcxwshgl.do?method=rcxwshManage&type=query",
				colList:[
				   {label:'key',name:'id', index: 'id',key:true ,hidden:true},
				   {label:'ѧ��',name:'xh', index: 'xh',width:'10%',formatter:xhLink},
				   {label:'����',name:'xm', index: 'xm',width:'6%'},
				   {label:'�Ա�',name:'xb', index: 'xb',width:'5%'},
				   {label:'�༶',name:'bjmc', index: 'bjdm',width:'12%'},
				   {label:'��Ϊ���',name:'rcxwlbmc', index: 'rcxwlbmc',width:'8%'},
                   {label:'��Ϊ����',name:'dlxx', index: 'dlxx',width:'15%'},
                   {label:'��Ϊ����',name:'rcxwlbdlmc', index: 'rcxwlbdlmc',width:'8%' ,hidden:true},
				   {label:'��ΪС��',name:'rcxwlbxlmc', index: 'rcxwlbxlmc',width:'8%'},
				   {label:'����ʱ��',name:'fssj', index: 'fssj',width:'12%'},
				   {label:'����������ֵ',name:'fz', index: 'fz',width:'8%'},
				   {label:'��������',name:'gfly', index: 'gfly',width:'8%',formatter:gflyText},
				   {label:'���״̬����',name:'shzt', index: 'shzt' ,hidden:true},
				   {label:'���״̬',name:'shztmc', index: 'shztmc',width:'14%'},
				   {label:'��Ϊ�������',name:'rcxwlbdldm', index: 'rcxwlbdldm',hidden:true},
				   {label:'��������',name:'splc', index: 'splc',hidden:true},
				   {label:'���Id',name:'shid', index: 'shid',hidden:true},
				   {label:'rcxwlbdm',name:'rcxwlbdm', index: 'rcxwlbdm',hidden:true},
				   {label:'rcxwlbdldm',name:'rcxwlbdldm', index: 'rcxwlbdldm',hidden:true},
				   {label:'rcxwlbxldm',name:'rcxwlbxldm', index: 'rcxwlbxldm',hidden:true}
				],
				params:{shzt:"dsh"},//Ĭ�ϴ����
				sortname: "rcxwjlsj",
			 	sortorder: "desc"
			}
		var gridSetting2 = {
				caption:"�ճ���Ϊ���",
				pager:"pager",
				url:"rcsw_rcxwwhnew_rcxwshgl.do?method=rcxwshManage&type=query",
				colList:[
				   {label:'key',name:'id', index: 'id',key:true ,hidden:true},
				   {label:'ѧ��',name:'xh', index: 'xh',width:'10%',formatter:xhLink},
				   {label:'����',name:'xm', index: 'xm',width:'10%'},
				   {label:'�Ա�',name:'xb', index: 'xb',width:'5%'},
				   {label:'�༶',name:'bjmc', index: 'bjdm',width:'12%'},
				   {label:'��Ϊ���',name:'rcxwlbmc', index: 'rcxwlbmc',width:'8%'},
                   {label:'��Ϊ����',name:'dlxx', index: 'dlxx',width:'15%'},
				   {label:'��Ϊ����',name:'rcxwlbdlmc', index: 'rcxwlbdlmc',width:'8%' ,hidden:true},
				   {label:'��ΪС��',name:'rcxwlbxlmc', index: 'rcxwlbxlmc',width:'8%'},
				   {label:'����ʱ��',name:'fssj', index: 'fssj',width:'12%'},
				   {label:'����������ֵ',name:'fz', index: 'fz',width:'8%'},
				   {label:'��������',name:'gfly', index: 'gfly',width:'8%',formatter:gflyText},
				   {label:'���״̬����',name:'shzt', index: 'shzt' ,hidden:true},
				   {label:'���״̬',name:'shztmc', index: 'shztmc',width:'14%'},
				   {label:'��λid',name:'gwid', index: 'gwid',hidden:true},
				   {label:'�����',name:'shr', index: 'shr',hidden:true},
				   {label:'��������',name:'splc', index: 'splc',hidden:true},
				   {label:'���Id',name:'shid', index: 'shid',hidden:true},
				   {label:'rcxwlbdm',name:'rcxwlbdm', index: 'rcxwlbdm',hidden:true},
				   {label:'rcxwlbdldm',name:'rcxwlbdldm', index: 'rcxwlbdldm',hidden:true},
				   {label:'rcxwlbxldm',name:'rcxwlbxldm', index: 'rcxwlbxldm',hidden:true}
				],
				params:{shzt:"ysh"},//Ĭ�������
				sortname: "rcxwjlsj",
			 	sortorder: "desc"
			}
			
		jQuery(function(){
			var map = getSuperSearch();
				map["shzt"] = "dsh";
			gridSetting["params"] = map;
			if(jQuery("#xxdm").val() == '10704'){
				gridSetting["caption"] = "�ۺϲ������";
				gridSetting2["caption"] = "�ۺϲ������";
				gridSetting["colList"] = [
				       				   {label:'key',name:'id', index: 'id',key:true ,hidden:true},
				    				   {label:'ѧ��',name:'xh', index: 'xh',width:'10%',formatter:xhLink},
				    				   {label:'����',name:'xm', index: 'xm',width:'10%'},
				    				   {label:'�Ա�',name:'xb', index: 'xb',width:'5%'},
				    				   {label:'�༶',name:'bjmc', index: 'bjdm',width:'12%'},
				    				   {label:'�ۺϲ������',name:'rcxwlbmc', index: 'rcxwlbmc',width:'8%'},
				    				   {label:'�ۺϲ�������',name:'rcxwlbdlmc', index: 'rcxwlbdlmc',width:'8%'},
				    				   {label:'�ۺϲ���С��',name:'rcxwlbxlmc', index: 'rcxwlbxlmc',width:'8%'},
				    				   {label:'����ʱ��',name:'fssj', index: 'fssj',width:'12%'},
				    				   {label:'����������ֵ',name:'fz', index: 'fz',width:'8%'},
				    				   {label:'��������',name:'gfly', index: 'gfly',width:'8%',formatter:gflyText},
				    				   {label:'���״̬����',name:'shzt', index: 'shzt' ,hidden:true},
				    				   {label:'���״̬',name:'shztmc', index: 'shztmc',width:'14%'},
				    				   {label:'�ۺϲ����������',name:'rcxwlbdldm', index: 'rcxwlbdldm',hidden:true},
				    				   {label:'��������',name:'splc', index: 'splc',hidden:true},
				    				   {label:'���Id',name:'shid', index: 'shid',hidden:true},
				    				   {label:'rcxwlbdm',name:'rcxwlbdm', index: 'rcxwlbdm',hidden:true},
				    				   {label:'rcxwlbdldm',name:'rcxwlbdldm', index: 'rcxwlbdldm',hidden:true},
				    				   {label:'rcxwlbxldm',name:'rcxwlbxldm', index: 'rcxwlbxldm',hidden:true}
				    				];
				gridSetting2["colList"] = [
				        				   {label:'key',name:'id', index: 'id',key:true ,hidden:true},
				        				   {label:'ѧ��',name:'xh', index: 'xh',width:'10%',formatter:xhLink},
				        				   {label:'����',name:'xm', index: 'xm',width:'10%'},
				        				   {label:'�Ա�',name:'xb', index: 'xb',width:'5%'},
				        				   {label:'�༶',name:'bjmc', index: 'bjdm',width:'12%'},
				        				   {label:'�ۺϲ������',name:'rcxwlbmc', index: 'rcxwlbmc',width:'8%'},
				        				   {label:'�ۺϲ�������',name:'rcxwlbdlmc', index: 'rcxwlbdlmc',width:'8%'},
				        				   {label:'�ۺϲ���С��',name:'rcxwlbxlmc', index: 'rcxwlbxlmc',width:'8%'},
				        				   {label:'����ʱ��',name:'fssj', index: 'fssj',width:'12%'},
				        				   {label:'����������ֵ',name:'fz', index: 'fz',width:'8%'},
				        				   {label:'��������',name:'gfly', index: 'gfly',width:'8%',formatter:gflyText},
				        				   {label:'���״̬����',name:'shzt', index: 'shzt' ,hidden:true},
				        				   {label:'���״̬',name:'shztmc', index: 'shztmc',width:'14%'},
				        				   {label:'��λid',name:'gwid', index: 'gwid',hidden:true},
				        				   {label:'�����',name:'shr', index: 'shr',hidden:true},
				        				   {label:'��������',name:'splc', index: 'splc',hidden:true},
				        				   {label:'���Id',name:'shid', index: 'shid',hidden:true},
				        				   {label:'rcxwlbdm',name:'rcxwlbdm', index: 'rcxwlbdm',hidden:true},
				        				   {label:'rcxwlbdldm',name:'rcxwlbdldm', index: 'rcxwlbdldm',hidden:true},
				        				   {label:'rcxwlbxldm',name:'rcxwlbxldm', index: 'rcxwlbxldm',hidden:true}
				        				]
			}
			jQuery("#dataTable").initGrid(gridSetting);
	
		});
			
		function gflyText(cellValue, rowObject){
			var cellValueNotNull = cellValue ? cellValue : "";
			var cellValueNew = cellValueNotNull.length > 10 ? cellValue.substring(0,10)+"..." : cellValueNotNull;
			return "<span title='"+cellValueNotNull+"' >" + cellValueNew + "</span>";
		}
	
		function searchRs(){
			var map = getSuperSearch();
			var shzt = jQuery("#shzt").val();
			if (shzt != ""){
				map["shzt"] = shzt;
			}
			jQuery("#dataTable").reloadGrid(map);
		}
			
		function selectTab(obj,shzt){
			jQuery("#shzt").val(shzt);
			var map = getSuperSearch();
			map["shzt"] = shzt;
			if (shzt == "dsh"){
				jQuery("#li_sh").css("display","");
				jQuery("#li_plsh").css("display","");
				jQuery("#li_qx").css("display","none");
				gridSetting["params"] = map;
				jQuery("#dataTable").initGrid(gridSetting);
			} else {			
				jQuery("#li_sh").css("display","none");
				jQuery("#li_plsh").css("display","none");
				jQuery("#li_qx").css("display","");
				gridSetting2["params"] = map;
				jQuery("#dataTable").initGrid(gridSetting2);
			}
			
			jQuery(".ha").removeClass("ha");
			jQuery(obj).parent().addClass("ha");
			
			searchRs();
		}
	
		function rcxwSh(){
			
			var rows = jQuery("#dataTable").getSeletRow();
			var shzt = jQuery("#shzt").val();
			if(shzt=="ysh"){
				showAlertDivLayer("�Ѵ����¼�����ٴ����");
				return false;
			}
			else if (rows.length == 0){
				showAlertDivLayer("��ѡ��һ����Ҫ��˵ļ�¼��");
			}else if(rows.length == 1 ){
				var url = "rcsw_rcxwwhnew_rcxwshgl.do?method=rcxwDgsh&id="+rows[0]["id"]+'&xh=' + rows[0]["xh"]+'&rcxwlbdldm=' + rows[0]["rcxwlbdldm"] + '&shid=' +rows[0]["shid"] ;
				var title = "�ճ���Ϊ���";
				if(${xxdm=="12970"}){
					title = "����ѧ�����";
				}
				//�����Ƽ���ѧ
				if(jQuery("#xxdm").val() == '10704'){
					title = "�ۺϲ������";
				}
				showDialog(title,700,500,url);
			} else {
				plsh();
			}
		}
		function rcxwshLcinfo(){
			var ids = jQuery("#dataTable").getSeletIds();
			var rows = jQuery("#dataTable").getSeletRow();
			if (ids.length != 1){
				showAlertDivLayer("��ѡ��һ�����̸��ټ�¼��");
			} else {
				if(jQuery("#xxdm").val() == '10704'){
					showDialog("�ۺϲ����������̸���",600,380,'comm_spl.do?method=lcgz&sqid='+rows[0]['id']+"&splc="+rows[0]['splc']);
				}else{
					showDialog("�ճ���Ϊ�������̸���",600,380,'comm_spl.do?method=lcgz&sqid='+rows[0]['id']+"&splc="+rows[0]['splc']);
				}		
			}
		}
	
		function cancelSh(){
			var rows = jQuery("#dataTable").getSeletRow();
			if (rows.length != 1){
				showAlertDivLayer("��ѡ��һ����Ҫ��������˼�¼��");
			} else {
				showConfirmDivLayer("��ȷ��Ҫ�����Ըü�¼����˲�����",{"okFun":function(){
					jQuery.post("rcsw_rcxwwhnew_rcxwshgl.do?method=cancelRcxwsh",
						{
						 id:rows[0]["id"],
						 gwid:rows[0]["gwid"],
						 shr:rows[0]["shr"],
						 splc:rows[0]["splc"]
						},
						function(data){
							showAlertDivLayer(data["message"]);
							jQuery("#dataTable").reloadGrid();
						},
					'json');
				}});
			}
		}
	
		function xwxxView(id, xh) {
			var title = "�鿴�ճ���Ϊ�����Ϣ";
			if(${xxdm=="12970"}){
				title = "�鿴����ѧ�������Ϣ";
			}
			if(jQuery("#xxdm").val() == '10704'){
				title = "�鿴�ۺϲ��������Ϣ";
			}
			showDialog(title, 700, 480, "rcsw_rcxwwhnew_rcxwshgl.do?method=viewXwsh&id=" + id
					+ "&xh=" + xh);
		}
	
		function xhLink(cellValue, rowObject) {
			return "<a href='javascript:void(0);' class='name' onclick='xwxxView(\""
					+ rowObject["id"] + "\",\"" + cellValue + "\");'>" + cellValue
					+ "</a>";
		}
		var DCCLBH = "rcsw_rcxwwhnew_rcxwshgl.do";//dcclbh,�������ܱ��
	
		//�Զ��嵼�� ����
		function exportConfig() {
			//DCCLBH�������ܱ��,ִ�е������� 
			customExport(DCCLBH, rcxwxshExportData);
		}
	
		// ��������
		function rcxwxshExportData() {
			var shlx = jQuery("#shzt").val();
			setSearchTj();//���ø߼���ѯ����
			var url = "rcsw_rcxwwhnew_rcxwshgl.do?method=exportData&dcclbh=" + DCCLBH + "&shlx=" + shlx;//dcclbh,�������ܱ��
			url = addSuperSearchParams(url);//���ø߼���ѯ����
			jQuery("form").eq(0).attr("action", url);
			jQuery("form").eq(0).submit();
		}
		function cancelShnew(){
			var rows = jQuery("#dataTable").getSeletRow();
			if (rows.length != 1){
				showAlertDivLayer("��ѡ��һ����Ҫ��������˼�¼��");
			} else {				
				var splc = rows[0]["splc"];
				var shid = rows[0]["shid"];
				var xxwhid = rows[0]["id"];
				var shzt =  rows[0]["shzt"];
				showConfirmDivLayer("��ȷ��Ҫ�����Ըü�¼����˲�����",{"okFun":function(){
					jQuery.post("comm_spl.do?method=cxshnew",{shlc:splc,shid:shid},function(data){
						// �ж��Ƿ����һ������(1:���һ�������ɹ���
						if("1" == data["cancelFlg"]){
							jQuery.post("rcsw_rcxwwhnew_rcxwshgl.do?method=cancelRcxwsh",{xxwhid:xxwhid,shzt:shzt},function(result){
								showAlertDivLayer(result["message"],{},{"clkFun":function(){
									jQuery("#dataTable").reloadGrid();
								}});
							},'json');
						}else{
							showAlertDivLayer(data["message"],{},{"clkFun":function(){
								jQuery("#dataTable").reloadGrid();
							}});
						}
					
				},'json');
				}});
			}
		}
	
		function plsh(){
			var rows = jQuery("#dataTable").getSeletRow();
			if (rows.length == 0){
				showAlertDivLayer("��ѡ����Ҫ��˵ļ�¼��");
			} else {
				var title = "�ճ���Ϊ�������";
				if(${xxdm=="12970"}){
					title = "����ѧ���������";
				}
				if(jQuery("#xxdm").val() == '10704'){
					title = "�ۺϲ����������";
				}
				showDialog(title,500,300,"rcsw_rcxwwhnew_rcxwshgl.do?method=toPlsh");
			}
		}
		
		function tjsh(shzt,shyj){
			
			var rows = jQuery("#dataTable").getSeletRow();
			
			jQuery.post("rcsw_rcxwwhnew_rcxwshgl.do?method=plsh",{shzt:shzt,shyj:shyj,info:JSON.stringify(rows)},function(data){
				showAlertDivLayer(data.message);
				searchRs();
			});
		}
		
		</script>
	</head>

	<body style="min-height: 800px;">
	
		<input type="hidden" value="dsh" id="shzt"/>
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>${title }</a>
			</p>
		</div>
		<html:form action="/rcsw_rcxwwhnew_rcxwshgl">
			<%@ include file="/comm/hiddenValue.jsp"%>
			<div class="toolbox">
				
				<!-- ��ť -->
				<div class="buttonbox">
					<ul>
					<logic:equal name="writeAble" value="yes">	
						<li id="li_sh">
							<a href="javascript:void(0);" onclick="rcxwSh();return false;" 
							   title="ѡ����Ҫ��˵ļ�¼������ð�ť���Դ����ҳ�档"
							   class="btn_sh">���</a>
						</li>						
						<li id="li_qx" style="display: none;">
							<a href="javascript:void(0);" onclick="cancelShnew();return false;" 
							   title="ѡ��һ����¼������ð�ť�����Գ���ʧ�����˲�����"
							   class="btn_qxsh">����</a>
						</li>	
						</logic:equal>					
						<li><a href="javascript:void(0);" onclick="rcxwshLcinfo();return false;" 
							   title="ѡ��һ����¼������ð�ť���Բ鿴������̡�"
							   class="btn_cs">���̸���</a></li>	
						<logic:equal name="writeAble" value="yes">		   
						<li><a href="#" class="btn_dc" onclick="exportConfig();return false;">����</a></li>	
						</logic:equal>				
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
				<logic:equal name="xxdm" value="12970">	
					<span>����ѧ������б�&nbsp;&nbsp; </span>
				</logic:equal>
				<logic:notEqual name="xxdm" value="12970">
					<span>
						<logic:equal value="10704" name="xxdm">
							�ۺϲ�������б�&nbsp;&nbsp;
						</logic:equal>
						<logic:notEqual value="10704" name="xxdm">
							�ճ���Ϊ����б�&nbsp;&nbsp; 
						</logic:notEqual>
					</span>
				</logic:notEqual>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable" ></table>
				<div id="pager"></div>
			</div>
		</div>
		<div id="plsh" style="display:none;">
			<table class="formlist">
				<thead>
					<tr>
						<th colspan="2">
							<span>�������</span>
						</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<th align="right" width="30%">
							������
						</th>
						<td>
							<jsp:include page="/xsgzgl/comm/shlc/cyyj.jsp?gnid=rcxw&id=shyj" /><br/><br/>
							<textarea rows="5" style="width:95%;" id="shyj"></textarea>
						</td>
					</tr>
				</tbody>
			</table>
		</div>
	</body>
</html>
