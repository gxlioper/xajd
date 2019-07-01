<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript"
			src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript">
		
	var gridSetting_1 = {
		pager : "pager",
		url : "xljk_xlwygl_xxsbglwh.do?method=query&sblx=0",
		colList : [
				{ label : 'sbsqid', name : 'sbsqid', index : 'sbsqid',key : true, hidden : true },
				{ label : 'sblx', name : 'sblx', index : 'sblx',hidden : true },
				{ label : 'sblxx', name : 'sblxx', index : 'sblxx',hidden : true },
				{ label : 'ѧ��', name : 'xn', index : 'xn', width : '15%'},
				{ label : 'ѧ��', name : 'xqmc', index : 'xqmc', width : '10%' },
				{ label : '�ܴ�', name : 'zbzc', index : 'zbzc', width : '10%' , formatter : link},
				{ label : '��ʼ����', name : 'zbksrq', index : 'zbksrq', width : '10%' },
				{ label : '��������', name : 'zbjsrq', index : 'zbjsrq', width : '10%' },
				{ label : '�ϱ�ʱ��', name : 'sbsj', index : 'sbsj', width : '10%' },
				{ label : '���״̬', name : 'shztmc', index : 'shztmc', width : '8%' },
				{ label : 'shzt', name : 'shzt', index : 'shzt', hidden : true },
				{ label : 'splcid', name : 'splcid', index : 'splcid', hidden : true },
				{ label : 'zbid', name : 'zbid', index : 'zbid', hidden : true }],
		sortname : "sbsj", sortorder : "desc" }

	var gridSetting_2 = {
			pager : "pager",
			url : "xljk_xlwygl_xxsbglwh.do?method=query&sblx=1",
			colList : [
					{ label : 'sbsqid', name : 'sbsqid', index : 'sbsqid',key : true, hidden : true },
				{ label : 'sblx', name : 'sblx', index : 'sblx',hidden : true },
				{ label : 'sblxx', name : 'sblxx', index : 'sblxx',hidden : true },
				{ label : 'ѧ��', name : 'xn', index : 'xn', width : '15%'},
				{ label : 'ѧ��', name : 'xqmc', index : 'xqmc', width : '10%' },
				{ label : '�ܴ�', name : 'zbzc', index : 'zbzc', width : '10%',formatter : link},
				{ label : '��ʼ����', name : 'zbksrq', index : 'zbksrq', width : '10%' },
				{ label : '��������', name : 'zbjsrq', index : 'zbjsrq', width : '10%' },
				{ label : '�ϱ�ʱ��', name : 'sbsj', index : 'sbsj', width : '10%' },
				{ label : '���״̬', name : 'shztmc', index : 'shztmc', width : '8%' },
				{ label : 'shzt', name : 'shzt', index : 'shzt', hidden : true },
				{ label : 'splcid', name : 'splcid', index : 'splcid', hidden : true },
				{ label : 'zbid', name : 'zbid', index : 'zbid', hidden : true }],
			sortname : "sbsj", sortorder : "desc" }

	var gridSetting_3 = {
			pager : "pager",
			url : "xljk_xlwygl_xxsbglwh.do?method=query&sblx=2",
			colList : [
					{ label : 'sbsqid', name : 'sbsqid', index : 'sbsqid',key : true, hidden : true },
					{ label : 'sblx', name : 'sblx', index : 'sblx',hidden : true },
					{ label : 'sblxx', name : 'sblxx', index : 'sblxx',hidden : true },
					{ label : 'ѧ��', name : 'xn', index : 'xn', width : '15%'},
					{ label : 'ѧ��', name : 'xqmc', index : 'xqmc', width : '10%' },
					{ label : 'ѧ��', name : 'xh', index : 'xh', width : '15%',formatter : link},
					{ label : '����', name : 'xm', index : 'xm', width : '20%'},
					{ label : '�ϱ�ʱ��', name : 'sbsj', index : 'sbsj', width : '35%' },
					{ label : '���״̬', name : 'shztmc', index : 'shztmc', width : '25%' },
					{ label : 'shzt', name : 'shzt', index : 'shzt', hidden : true },
					{ label : 'splcid', name : 'splcid', index : 'splcid', hidden : true }],
			sortname : "sbsj", sortorder : "desc" }
	
	jQuery(function() {
		//gridSetting_1["params"] = getSuperSearch();
		//jQuery("#dataTable").initGrid(gridSetting_1);
		jQuery('#tab_title li:first').css('ha');
		jQuery('#tab_title li:first').children().click();
	});

	/**
	 * �������Ѵ�����ǩ�л�
	 * @param obj
	 * @param shzt
	 * @return
	 */
	function selectTab(obj,query_type){
		jQuery("#query_type").val(query_type);

		if (query_type == "0"){
			jQuery("#dataTable").initGrid(gridSetting_1);
		} else if(query_type == "1"){
			jQuery("#dataTable").initGrid(gridSetting_2);
		}else if(query_type == "2"){
			jQuery("#dataTable").initGrid(gridSetting_3);
		}
		jQuery(".ha").removeClass("ha");
		jQuery(obj).parent().addClass("ha");
		
		searchRs();
	}
	
	/**
	 * �߼���ѯ
	 * @return
	 */
	function searchRs() {
		var map = getSuperSearch();
		jQuery("#dataTable").reloadGrid(map);
	}

	/**
	 * �ϱ�
	 * @return
	 */
	function sb(){
		var sblx = jQuery("#query_type").val();
		var rows = jQuery("#dataTable").getSeletRow();
		if ((sblx=='1' || sblx == '0') && rows.length != 1){
			showAlertDivLayer("��ѡ��һ���ϱ��ܴΣ�");
			return false;
		} else{
			if(sblx == '1' || sblx == '0'){
				var shzt = rows[0]['shzt'];
				if(shzt != '' && shzt != null){
					showAlertDivLayer("��ѡ��δ�ϱ����ܴΣ�");
					return false;
				}
				showDialog('�ϱ�',680,430,'xljk_xlwygl_xxsbglwh.do?method=sb&sblx=' + rows[0]['sblxx'] + "&sbzbid=" + rows[0]['zbid']);
			}else{

					if(jQuery('#sfxypssb').val() == '0'){
						showAlertDivLayer("�ݲ������ϱ���Ϣ!");
						return false;
					}
				
				showDialog('�ϱ�',680,430,'xljk_xlwygl_xxsbglwh.do?method=sb&sblx=2');
			}
		}
	}

	/**
	 * ɾ��
	 * @return
	 */
	function del(){
		var rows = jQuery("#dataTable").getSeletRow();
		var ids = jQuery("#dataTable").getSeletIds();
		if (rows.length == 0){
			showAlertDivLayer("��ѡ��һ����Ҫɾ���ļ�¼��");
			return false;
		} else{
			showConfirmDivLayer("��ȷ��Ҫɾ����ѡ��¼��",{"okFun":function(){
				jQuery.post("xljk_xlwygl_xxsbglwh.do?method=delAction",{pks:ids.toString()},function(data){
					showAlertDivLayer(data["message"]);
					jQuery("#dataTable").reloadGrid();
				},'json');
			}});
		}
	}
	
	/**
	 * �޸�
	 * @return
	 */
	function update(){
		var rows = jQuery("#dataTable").getSeletRow();
		if (rows.length != 1){
			showAlertDivLayer("��ѡ��һ����Ҫ�޸ĵļ�¼��");
			return false;
		} else{
			var shzt = rows[0]['shzt'];
			if(shzt == '' || shzt == null){
				showAlertDivLayer("��ѡ��δ�ύ�������˻صļ�¼��");
				return false;
			}
			if(shzt != '0' && shzt != '3'){
				showAlertDivLayer("��ѡ��δ�ύ�������˻صļ�¼��");
				return false;
			}
			showDialog('�޸�',680,430,'xljk_xlwygl_xxsbglwh.do?method=xg&sbsqid=' + rows[0]['sbsqid'] + '&sblx=' + rows[0]['sblx']);
		}
	}

	/**
	 * ����
	 * @return
	 */
	function cancle(){
		var rows = jQuery("#dataTable").getSeletRow();
		if (rows.length != 1) {
			showAlertDivLayer("��ѡ��һ��Ҫ�����ļ�¼��");
		} else {
			for(var i=0;i<rows.length;i++){
				if(rows[i]['shzt'] != '5'){
					showAlertDivLayer("ֻ������еļ�¼���ܱ�������");
					return false;
				}
			}
			
			showConfirmDivLayer("��ȷ��Ҫ����ѡ��ļ�¼��", {
				"okFun" : function() {
					jQuery.post("xljk_xlwygl_xxsbglwh.do?method=cancelAction", {
						sbsqid : rows[0]['sbsqid']
					}, function(data) {
						showAlertDivLayer(data["message"]);
						jQuery("#dataTable").reloadGrid();
					}, 'json');
				}
			});
		}
	}

	/**
	 * ���̸���
	 * @return
	 */
	function lcinfo(){
		var rows = jQuery("#dataTable").getSeletRow();
		if (rows.length != 1){
			showAlertDivLayer("��ѡ��һ����¼��");
		} else {
			if(rows[0]["shzt"]=="0" || rows[0]["shzt"]=="" || rows[0]["shzt"]==null){
				showAlertDivLayer("��ѡ�����ύ�ļ�¼��");
				return false;
			}
			showDialog("�������̸���",530,310,'comm_spl.do?method=lcgz&sqid='+rows[0]['sbsqid']+"&splc="+rows[0]['splcid']);
		}
	}
	/**
	 * �ύ
	 * @return
	 */
	function submitBusi(){
		var rows = jQuery("#dataTable").getSeletRow();
		if (rows.length != 1) {
			showAlert("��ѡ��һ��Ҫ�ύ�ļ�¼��");
			return false;
		} else {
			if(rows[0]['shzt']!='0'&&rows[0]['shzt']!='3'){
				showAlert("��ѡ��δ�ύ�������˻صļ�¼��");
				return false;
			}
			
			showConfirmDivLayer("��ȷ��Ҫ�ύѡ��ļ�¼��", {
				"okFun" : function() {
					jQuery.post("xljk_xlwygl_xxsbglwh.do?method=submitAction", {
						sbsqid : rows[0]['sbsqid'],
						sblx : rows[0]['sblxx']
					}, function(data) {
						if((data||{})['code'] == '-1'){
							showAlertDivLayer("��������δ����,����ϵ����Ա!");
							return false;
						}else if((data||{})['code'] == '-2'){
							showAlertDivLayer("��ְ�����ѹ��������ϰ�༶�ܱ�!");
							return false;
						}else if((data||{})['code'] == '-3'){
							showAlertDivLayer("�޷��ϱ�ƽʱ���!");
							return false;
						}else if((data||{})['code'] == '0'){
							showAlertDivLayer(data["message"]);
							return false;
						}else if((data||{})['code'] == '1'){
							showAlertDivLayer(data["message"]);
							jQuery("#dataTable").reloadGrid();
						}else{
							showAlertDivLayer("δ֪ϵͳ����!");
							return false;
						}
					}, 'json');
				}
			});
		}
	}

	/**
	 * ����
	 * @param cellValue
	 * @param rowObject
	 * @return
	 */

	function link(cellValue,rowObject){

		//return "<a href='javascript:void(0);' class='name' onclick='knsView(\""+rowObject["id"]+"\",\""+cellValue+"\");'>"+cellValue+"</a>";
		var onclickfn = "onclick=\"" + "showDialog('��ϸ��Ϣ' , 680,430 , 'xljk_xlwygl_xxsbglwh.do?method=ck&sbsqid=" + rowObject['sbsqid'] + "')" + "\"";
		if(rowObject['sbsqid'] == '' || rowObject['sbsqid'] == null){
			onclickfn = "onclick=\"" + "showAlertDivLayer('��Ϣδ�ϱ���')" + "\"";
		}
		var href = "href = 'javascript:void(0);'";

		var el = "<a " + href + " class='name' " + onclickfn + " >" + cellValue + "</a>";
		
		return el;
	}

	function query(){
		var map = {};
		map["xn"] = jQuery("#xn").val();
		map["xq"] = jQuery("#xq").val();
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
		<html:form action="/xljk_xlwygl_xxsbglwh">
			<input type="hidden" id="query_type" value="0"/>
			<%@ include file="/comm/hiddenValue.jsp"%>
			<div class="toolbox">
				<!-- ��ť -->
				<div class="buttonbox">
					<ul>
						<logic:equal name="writeAble" value="yes">
						<li>
							<a href="javascript:void(0);" class="btn_zj"
								onclick="sb();return false;">�ϱ�</a>
						</li>
						<li>
							<a href="javascript:void(0);"
								onclick="update();return false;" class="btn_xg"
							>�޸�</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="submitBusi();return false;" class="btn_shuc">�ύ</a>
						</li>
						
						<li>
							<a href="javascript:void(0);" onclick="cancle();return false;" class="btn_sr">����</a>
						</li>
						</logic:equal>
						<li>
							<a href="javascript:void(0);" onclick="lcinfo();return false;" class="btn_cs">���̸���</a>
						</li>
					</ul>
				</div>
				<!-- �������� -->
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- �������� end-->
				<div class="comp_title" id="comp_title">
			      <ul style="width:90%" id="tab_title">
			       <logic:notEmpty name="xssqCheck">
			       		<logic:equal value="Y" 	name="xssqCheck" property="bjxlwy">
			       			<li><a href="javascript:void(0);" onclick="selectTab(this,'0');"><span>�༶�����ܱ�</span></a></li>
			       		</logic:equal>
				       	<logic:equal value="Y" 	name="xssqCheck" property="gygly">
				       		<li><a href="javascript:void(0);" onclick="selectTab(this,'1');"><span>��Ԣ�����ܱ�</span></a></li>
				        </logic:equal>
				        <logic:equal value="Y" 	name="xssqCheck" property="tsxs">
				        	<input type="hidden" id="sfxypssb" value="${xssqCheck.sfxypssb}"/>
				        	<li><a href="javascript:void(0);" onclick="selectTab(this,'2');"><span>ƽʱ����ϱ�</span></a></li>
				        </logic:equal>
			       </logic:notEmpty>
			      </ul>
			    </div>
				<div class="searchtab">
					<table width="100%" border="0">
						<tr>
							<th width="10%">ѧ��</th>
							<td>
								<html:select property="xn" styleId="xn" style="width:155px">
									<html:options collection="xnList" property="xn"
										labelProperty="xn" />
								</html:select>
							</td>
							<th width="10%">ѧ��</th>
							<td>
								<html:select property="xq" styleId="xq" style="width:155px">
									<html:options collection="xqList" property="xqdm"
										labelProperty="xqmc" />
								</html:select>								
							</td>
							<td>
								<div class="btn">
									<button type="button" class="btn_cx" id="search_go" onclick="query()">
										�� ѯ
									</button>
								</div>
							</td>
						</tr>
					</table>
				</div>
			</div>
		</html:form>

		<div class="main_box">
			<h3 class=datetitle_01>
				<span>��ѯ���&nbsp;&nbsp; </span>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable"></table>
				<div id="pager"></div>
			</div>
		</div>
	</body>
</html>
