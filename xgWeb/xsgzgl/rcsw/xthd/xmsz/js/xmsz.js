var tjszDialog;
var gridSetting = {
	caption : "��Ŀ�б�",
	pager : "pager",
	url : "rcsw_txhd_xmszCx.do?method=xmszCx&type=query",
	colList : [ {
		label : 'ѧ��',
		name : 'xn',
		index : 'xn',
		width : '12%'
	}, {
		label : 'ѧ��',
		name : 'xqmc',
		index : 'xq',
		width : '5%'
	}, {
		label : '��Ŀ����',
		name : 'xmdm',
		index : 'xmdm',
		key : true,
		hidden : true
	}, {
		label : '��Ŀ����',
		name : 'xmmc',
		index : 'xmmc',
		width : '23%',
		formatter:show
	}, {
		label : '�ʱ��',
		name : 'hdsj',
		index : 'hdsj',
		width : '25%'
	}, {
		label : '��ص�',
		name : 'hddd',
		index : 'hddd',
		width : '19%'
	}, {
		label : '����',
		name : 'lbmc',
		index : 'lbmc',
		width : '10%'
	}, {
		label : '���뿪��',
		name : 'sqkg',
		index : 'sqkg',
		width : '8%',
		formatter : setSqkg
	}, 
	{label:'��������',name:'sjly', index: 'sjly',hidden:true},
	{
		label : '��˿���',
		name : 'shkg',
		index : 'shkg',
		width : '8%',
		formatter : setShkg
	} ],
	sortname : "xmmc",
	sortorder : "asc"
}

function searchRs() {
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
}
function show(cellValue, rowObject){
	return "<a href='javascript:void(0);' onclick=\"ckxx('" + rowObject["xmdm"]+ "')\" class='name'>" + cellValue + "</a>";
}
function ckxx(xmdm){
	var url ="rcsw_txhd_xmszCx.do?method=showView&xmdm=" + xmdm;
	showDialog("�鿴���Ŀ", 790,470, url);
}
// ���������ʽӰ�죬��ɫ����д��Ԫ����
function setColor(value, status) {
	var color;
	if (status == '1') {
		color = "#004400";
	} else {
		color = "red";
	}
	return value = "<font color='" + color + "'>" + value + "</font>";
}

/*
 * ���뿪��
 */
function setSqkg(cellValue, rowObject) {
	var xmdm = rowObject.xmdm;
	var value = "δ����";
	var status = '0';
	var color;
	if (cellValue == '1') {
		var currDate = jQuery("#currDate").val();
		if (rowObject.sqkssj != null && currDate < rowObject.sqkssj
				|| rowObject.sqjssj != null && currDate > rowObject.sqjssj) {
		} else {
			value = "�ѿ���";
			status = '1';
		}
	}
	value = setColor(value, status);
	value = "<a  href='javascript:void(0);' onclick='return sjkg(\"" + xmdm
			+ "\");' >" + value + "</a>";
	return value;
}

/*
 * ��˿���
 */
function setShkg(cellValue, rowObject) {
	var xmdm = rowObject.xmdm;
	var value = "δ����";
	var status = '0';
	var color;
	if (cellValue == '1') {
		var currDate = jQuery("#currDate").val();
		if (rowObject.shkssj != null && currDate < rowObject.shkssj
				|| rowObject.shjssj != null && currDate > rowObject.shjssj) {
		} else {
			value = "�ѿ���";
			status = '1';
		}
	}
	value = setColor(value, status);
	value = "<a  href='javascript:void(0);' onclick='return sjkg(\"" + xmdm
			+ "\");' >" + value + "</a>";
	return value;
}

/*
 * ʱ�俪��
 */
function sjkg(xmdm) {
	if (xmdm == null) {// �����ť
		var rows = jQuery("#dataTable").getSeletRow();
		if (rows.length != 1) {
			showAlertDivLayer("��ѡ��һ����Ҫ���õļ�¼��");
			return false;
		}
		xmdm = rows[0]["xmdm"];
	}
	var url = 'rcsw_txhd_xmszCx.do?method=xmszSjkg&xmdm=' + xmdm;
	var title = "��Ŀʱ�����";
	showDialog(title, 600, 445, url);
}

function onShow() {
	jQuery("[name=shlc]").change(function() {// �������Ƽ��𣬼�ÿ��Ƽ��𣬸���������̽�����ʾ
				setKzlc(jQuery(this).val());
			});
	defaultShlc = jQuery("[name=shlc]:checked").val();
	jQuery("[name=shlc]").change();

}


/**
 * ȡ�������ķ�����Ӧ�øĳ�ͨ�õ�
 * @param value
 * @return
 */
function setKzlc(value) {
	if (value == "") {
		jQuery("#rskzjbTd").html("");
		return;
	}
	var url = "xpj_xmwh.do?method=xmwhShfw";
	jQuery
			.post(
					url,
					{
						value : value
					},
					function(data) {
						var sHtml = "";
						var radio1 = "";
						if (data != null) {
							for ( var i = 0; i < data.length; i++) {
								var o = data[i];
								radio1 += "<label><input type='radio' name='rskzjbView' value='"
										+ o.spgwdm + "'/>";
								radio1 += o.spgwmc;
								radio1 += "</label>";
								if (i != data.length - 1) {
									radio1 += "<br/>";
								}
							}
						}
						sHtml += radio1;
						jQuery("#rskzjbTd").html(sHtml);
						// �������Ƽ���
						jQuery(
								"input:radio[name=rskzjbView][value="
										+ jQuery("#rskzjb").val() + "]").attr(
								"checked", "checked");
						if (defaultShlc == value) {

						}
						setSpzt();//��������״̬
					}, 'json');
}

/**
 * ����
 * 
 * @return
 */
function add() {
	var url = "rcsw_txhd_xmszCx.do?method=xmszZj";
	var title = "���ӻ��Ŀ";
	showDialog(title, 790,476, url);
}

/**
 * ����
 * 
 * @param method
 *            ��������
 * @param type
 *            ������
 * @return
 */
function save(method, type) {
	if (yanzheng()) {
		var url = "rcsw_txhd_xmszCx.do?method=" + method + "&type=" + type;
		ajaxSubFormWithFun("demoForm", url, function(data) {
			if (data["success"] != undefined && (data["success"] == false || data["success"] == "false" )) {
				showAlert(data["message"]);
			} else {
				showAlert(data["message"], {}, {
					"clkFun" : function(tag) {
						if (tag == "ok") {
							refershParent();
						}
					}
				});
			}
		});
	}
}

/**
 * ��֤
 */
function yanzheng() {
	var xmmc = jQuery("#xmmc").val();
	var hdkssj = jQuery("#hdkssj").val();
	var hdjssj = jQuery("#hdjssj").val();
	var lbdm = jQuery("#lbdm").val();
	var hddd = jQuery("#hddd").val();

	if (xmmc == "") {
		showAlert("��Ŀ���Ʋ���Ϊ�գ�");
	} else if (hdkssj == "" || hdjssj == "") {
		showAlert("��������ֹʱ��");
	} else if (lbdm == "") {
		showAlert("������Ϊ�գ�");
	} else if (hddd == "") {
		showAlert("��ص㲻��Ϊ�գ�");
	} else if(jQuery("#hdmdyy").val().length > 200){
		showAlert("�Ŀ�ļ����岻�ܳ���200�֣�");
	} else if(jQuery("#hdfa").val().length > 500){
		showAlert("��������ܳ���500�֣�");
	} else if(jQuery("#hdsm").val().length > 1000){
		showAlert("�˵�����ܳ���1000�֣�");
	} else {
		return true;
	}

	return false;
}

/**
 * �޸�
 * 
 * @return
 */
function update() {
	var rows = jQuery("#dataTable").getSeletRow();
	var sjly = rows[0]["sjly"];
	if (rows.length != 1) {
		showAlertDivLayer("��ѡ��һ����Ҫ�޸ĵļ�¼��");
	} else if(sjly == '1'){
		showAlertDivLayer("�������ݲ����޸ģ�");
	} else {
		var url = 'rcsw_txhd_xmszCx.do?method=xmszXg&xmdm=' + rows[0]["xmdm"];
		var title = "�޸Ļ��Ŀ";
		showDialog(title, 790,480, url);
	}
}

/**
 * ɾ��
 * 
 * @return
 */
function del() {
	var ids = jQuery("#dataTable").getSeletIds();
	if (ids.length == 0) {
		showAlertDivLayer("��ѡ����Ҫɾ���ļ�¼��");
	} else {
		var rows = jQuery("#dataTable").getSeletRow();
		for(var i=0;i<ids.length;i++){
			if(rows[i]['sjly']=='1'){
				showAlertDivLayer("�������ݲ���ɾ����");
				return false;
			}
		}
		showConfirmDivLayer("�Ƿ�ȷ��ɾ����ѡ�ļ�¼��", {
			"okFun" : function() {
				var url = "rcsw_txhd_xmszCx.do?method=xmszSc";
				jQuery.post(url, {
					values : ids.toString()
				}, function(data) {
					if (data["success"] == false) {
						showAlertDivLayer(data["message"]);
					} else {
						showAlertDivLayer(data["message"], {}, {
							"clkFun" : function(tag) {
								jQuery("#dataTable").reloadGrid();
							}
						});
					}
				}, 'json');
			}
		});
	}
}


/**
 * ʱ�俪�ر�������
 * @return
 */
function saveForm() {
	var shlc = jQuery("#shlc").val();

	// �������ؼ�������ֵ���Ա��ύ��
	var rskzjb = jQuery("input:radio[name=rskzjbView]:checked").val();
	jQuery("#rskzjb").val(rskzjb);
	if (shlc == "") {
		showAlert("������̲���Ϊ�գ�");
		return false;
	}
	if (rskzjb == "" || rskzjb == null) {
		showAlert("�������Ƽ�����Ϊ�գ�");
		return false;
	}
	if(jQuery("#kgbz").val().length > 100){
		showAlert("���뿪�ر�ע���ܳ���100�֣�");
		return false;
	}
	var url = "rcsw_txhd_xmszCx.do?method=xmszSjkg&type=update";
	ajaxSubFormWithFun("form1", url, function(data) {
		showAlert(data["message"],{},{"clkFun":  function(tag) {
			if (tag == "ok") {
				refershParent();
			}
		}});
	});
}


/*
 * �������״̬�����ò�����
 * 
 */
function setSpzt(){
	var spzt = jQuery("#spzt").val();
	if(spzt == "true"){
		jQuery(".prompt").css("display","");
		jQuery(".promptYsq").css("display","");
		jQuery("table input,select").not(jQuery("#sqrssx,#shrssx")).attr(
				"disabled", "disabled");
	}
}

var DCCLBH = "rcsw_txhd_xmsz.do";//dcclbh,�������ܱ��

//�Զ��嵼�� ����
function exportConfig() {
	//DCCLBH�������ܱ�ţ�ִ�е����ĺ���
	customExport(DCCLBH, exportData);
}

// ��������
function exportData() {
	setSearchTj();//���ø߼���ѯ����
	var url = "rcsw_txhd_xmszCx.do?method=exportData&dcclbh=" + DCCLBH;//dcclbh,�������ܱ��
	url = addSuperSearchParams(url);//���ø߼���ѯ����
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}

//����
function copystxx(){
	var rows = jQuery("#dataTable").getSeletRow();
	if(rows.length != 1){
		showAlertDivLayer("��ѡ��һ��������Ŀ��");
		return false;
	}
	var url = "rcsw_txhd_xmszCx.do?method=copeOfXmxx&xmmc="+rows[0]['xmmc']+"&xmdm="+rows[0]['xmdm'];
	var title = "���Ž������";
	showDialog(title, 400, 180, url);
}


//������Ŀ���
function saveCopyXmjg(){
	if(jQuery("#xmmc").val()=="" || jQuery("#xn").val()=="" || jQuery("#xq").val()==""){
		showAlert("��Ŀ����,ѧ��,ѧ�ڲ���Ϊ�գ�")
		return false;
	}
	var url = "rcsw_txhd_xmszCx.do?method=saveCopeXmxx";
		ajaxSubFormWithFun("TxhdXmszForm", url, function(data) {
			 if(data["message"]=="����ɹ���"){
		   		 showAlert(data["message"],{},{"clkFun":function(){
							if (parent.window){
								refershParent();
							}
						}});
		   	 }else{
		   		 showAlert(data["message"]);
		   	 }
		});
	
}
