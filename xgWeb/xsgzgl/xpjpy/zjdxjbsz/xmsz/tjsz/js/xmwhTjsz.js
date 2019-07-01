var dataObj;
/*tbody��������*/
var tbodyTr = "#dataTable>tbody>tr";
var xnTip = "���ѡ��...";

/*ִ��onShow*/
jQuery(function() {
	onShow();
});

/*������ʾ*/
function onShow() {
	var url = "xpjpy_xmwh_tjsz.do?method=xmwhTjszSy";
	url += "&xmdm=" + jQuery("#xmdm").val();
	jQuery.post(url, {
		xmdm : jQuery("#xmdm").val()
	}, function(data) {
		dataObj = data;
		/*���ó�ʼֵ*/
		setInit();
		}, 'json');
}

/*���ó�ֵ*/
function setInit() {
	var tjszList = dataObj['tjszList'];
	var tjgxList = dataObj['tjgxList'];// ������ϵ����
	var xqList = dataObj['xqList'];
	var zhcpTjxmList = dataObj['zhcpTjxmList'];// �۲�������Ŀ
	if (tjszList == null || tjszList.length == 0) {
		var sTr = "<tr id='nodata' align='center'>";
		sTr += "<td colSpan='7'>����ʾ����</td>";
		sTr += "</tr>";
		jQuery("#dataTable>tbody").html(sTr);
	}
	if (tjszList == null) {
		return;
	}
	for ( var i = 0; i < tjszList.length; i++) {
		var o = tjszList[i];
		createRow(dataObj);
		jQuery(jQuery(tbodyTr)[i]).find("select[name=tjdm]").val(o.tjdm);
		jQuery(jQuery(tbodyTr)[i]).find("select[name=tjdm]").change();
		jQuery(jQuery(tbodyTr)[i]).find("select[name=gxdm]").val(o.gxdm);
		jQuery(jQuery(tbodyTr)[i]).find("select[name=gxdm]").change();
		/*����ֵ��ֵ*/
		for ( var j = 0; j < tjgxList.length; j++) {
			var tjgxObj = tjgxList[j];
			if (o.tjdm == tjgxObj.tjdm && o.gxdm == tjgxObj.gxdm) {
				var gxz = tjgxObj.gxz;
				if (tjgxObj.gxlx == '1' || tjgxObj.gxlx == '2'
						|| tjgxObj.gxlx == '4') {// �����ı���������С���ı���
					jQuery(jQuery(tbodyTr)[i]).find("[name=tjz]").val(o.tjz);
				} else if (tjgxObj.gxlx == '3') {// checkbox��tjz�����ʽΪ���ŷָ�
					var arr = o.tjz.split(",");
					jQuery.each(arr, function(index, value) {
						jQuery(jQuery(tbodyTr)[i]).find(
								"[name=tjz][value='" + value + "']").attr(
								"checked", "checked");
					});
				} else if (tjgxObj.gxlx == '5') {// checkbox��tjz�����ʽΪ���ŷָ�,���㸴ѡ��
					jQuery(jQuery(tbodyTr)[i]).find("[name=tjz]").val(o.tjz);
					var gxzs = "";
					if (gxz != null) {
						gxzs = gxz.split("|");
					}
					var tjzView = "";
					var tjzarr = o.tjz.split(',');
					for ( var k = 0; k < gxzs.length; k = k + 2) {
						var dm = gxzs[k];
						var mc = "";
						/*ģ��ƥ�������޸�  ��Ϊ��ȷ����*/
						for ( var m = 0; m < tjzarr.length; m++) {
							if(tjzarr[m]==dm){
								if (gxzs[k + 1] != undefined) {
									mc = gxzs[k + 1];
								}else{
									mc = dm;
								}
								if(tjzView != ""){
									tjzView += ",";
								}
								tjzView += mc;
								break;
							}
						}
					}
					jQuery(jQuery(tbodyTr)[i]).find("[name='tjzView']").val(tjzView);
				}
				break;
			}
		}
		var tjList = dataObj['tjList'];
		var zqlx = "";// ��������
		for ( var j = 0; j < tjList.length; j++) {
			var tjObj = tjList[j];
			if (tjObj.tjdm == o.tjdm) {
				zqlx = tjObj.zqlx;
				break;
			}
		}
		var xn = o.ylzq;
		var xnView = xn;
		if (zqlx == "3") {// �۲�������Ŀ
			for ( var j = 0; j < zhcpTjxmList.length; j++) {
				var zhcpTjxmObj = zhcpTjxmList[j];
				if (zhcpTjxmObj.dm == xn) {
					xnView = zhcpTjxmObj.mc;
					break;
				}
			}
		} else {
			for ( var j = 0; j < xqList.length; j++) {
				var xqObj = xqList[j];
				var reg = new RegExp(";" + xqObj.xqdm, "g");
				if (xnView != null && xnView != "") {
					xnView = xnView.replace(reg, " " + xqObj.xqmc);
				}
			}
		}
		jQuery(jQuery(tbodyTr)[i]).find("[name=xn]").val(xn);
		if (xnView != null && xnView != "") {
			jQuery(jQuery(tbodyTr)[i]).find("[name=xnView]").val(xnView);
			jQuery(jQuery(tbodyTr)[i]).find("[name=xnView]").attr("title",xnView);
		}
		// Ӧ�÷�Χ
		var yyfw = o.yyfw;
		jQuery(jQuery(tbodyTr)[i]).find("[name=yyfw]").val(yyfw);
	}
}

/*����*/
function add() {
	createRow(dataObj);
}

/*����һ�м�¼ @return*/
function createRow(dataObj) {
	var html = "";
	var tjList = dataObj['tjList'];
	var gxList = dataObj['gxList'];
	var tjgxList = dataObj['tjgxList'];
	var tjszList = dataObj['tjszList'];
	var xnList = dataObj['xnList'];
	var xqList = dataObj['xqList'];
	var bjdlList = dataObj['bjdlList'];
	var sfqyList = dataObj['sfqyList'];
	var tjObj = tjList[0];
	html += "<tr>";
	// ��ѡ��
	html += "<td>";
	html += "<input type='checkbox' name='grid_key'>";
	html += "</td>";
	// ����
	html += "<td>";
	html += createTj();
	html += "</td>";
	// ��ϵ
	html += "<td name='gxdmTd'>";
	html += "";
	html += "</td>";
	// ����ֵ
	html += "<td name='tjzTd'>";
	html += "";
	html += "</td>";
	// ��������
	html += "<td name='ylzqTd'>";
	html += "";
	html += "</td>";
	// Ӧ�÷�Χ
	var flagpath = jQuery("#flagpath").val();
	if(flagpath == "jtpj"){
		html += "<td style='display:none'>";
		html += createYyfw();
		html += "</td>";
	}else{
		html += "<td >";
		html += createYyfw();
		html += "</td>";
	}
	// ����˵��
	html += "<td name='tjsmTd'>";
	html += "";
	html += "</td>";
	html += "</tr>";
	jQuery("#nodata").remove();
	jQuery("#dataTable>tbody").append(html);
	var length = jQuery(tbodyTr).length;
	var tjObj = jQuery(jQuery(tbodyTr)[length - 1]).find("select[name=tjdm]");
	jQuery(tjObj).change(function() {
		tjSet(jQuery(this));// ��ʾ��ϵ
		});
	tjSet(tjObj);// ��ʾ��ϵ
	var gxObj = jQuery(jQuery(tbodyTr)[length - 1]).find("select[name=gxdm]");
	gxSet(gxObj);// ��ʾ����ֵ
}

//��������cell
function createTj() {
	var tjList = dataObj['tjList'];
	var html = "";
	html += "<select style='width:200px' name='tjdm' class='tjdm'>";
	for ( var i = 0; i < tjList.length; i++) {
		var o = tjList[i];
		var name = o.tjmc;
		var value = o.tjdm;
		html += "<option value='" + value + "'>" + name + "</option>";
	}
	html += "</select>";
	return html;
}

//Ӧ�÷�Χcell
function createYyfw() {
	var yyfwList = dataObj['yyfwList'];
	var html = "";
	//���������ж�
	var flagpath = jQuery("#flagpath").val();
	if(flagpath == "jtpj"){
		html += "<select name='yyfw' class='yyfw'>";
		html += "<option value='all'>����</option>";
		html += "</select>";
	}else{
		html += "<select name='yyfw' class='yyfw'>";
		html += "<option value='all'>����</option>";
		for ( var i = 0; i < yyfwList.length; i++) {
			var o = yyfwList[i];
			html += "<option value='" + o.lxdm + "'>" + o.lxmc + "</option>";
		}
		html += "</select>";
	}
	return html;
}

//��������ʾ��ϵ
function tjSet(tjObj) {
	var tjList = dataObj['tjList'];
	var tjdm = jQuery(tjObj).val();
	var gxdmHtml = createGx(tjdm);
	jQuery(tjObj).parent().parent().find("td[name=gxdmTd]").html(gxdmHtml);
	// ����˵��
	var tjsmHtml = "";
	var zqlx = "";
	for ( var i = 0; i < tjList.length; i++) {
		var o = tjList[i];
		if (tjdm == o.tjdm) {
			tjsmHtml = o.tjsm;
			zqlx = o.zqlx;
			break;
		}
	}
	jQuery(tjObj).parent().parent().find("td[name=tjsmTd]").html(tjsmHtml);
	// ������������
	jQuery(tjObj).parent().parent().find("td[name=ylzqTd]").html(createXn(zqlx));
	// ��ʾ��ϵ
	jQuery(tjObj).change(function() {
		gxSet(jQuery(this).parent().parent().find("select[name=gxdm]"));
	});
}

//���ɹ�ϵcell
function createGx(tjdm) {
	var gxList = dataObj['gxList'];
	var tjgxList = dataObj['tjgxList'];

	var html = "";
	html += "<select name='gxdm' class='gxdm'>";
	for ( var i = 0; i < tjgxList.length; i++) {
		var name = "";
		var value = "";
		var o = tjgxList[i];
		if (o.tjdm == tjdm) {
			value = o.gxdm;
			for ( var j = 0; j < gxList.length; j++) {
				var gxObj = gxList[j];
				if (gxObj.gxdm == o.gxdm) {
					name = gxObj.gxmc;
					break;
				}
			}
			html += "<option value='" + value + "'>" + name + "</option>";
		}
	}
	html += "</select>";
	return html;
}

//����ѧ��cell
function createXn(zqlx) {
	var xnList = dataObj['xnList'];
	var xqList = dataObj['xqList'];

	var html = "";
	html += "<input type='hidden' name='xnCur' value='0'/>";
	html += "<input type='hidden' name='xn' value=''/>";
	if (zqlx == '1') {// ѧ��ѧ�ڵ�ѡ
		html += "<input type='text' title='"+xnTip+"' name='xnView' class='xnView' onfocus='xnSelect(this,"
				+ zqlx + ");' value='" + xnTip + "'/>";
	} else if (zqlx == '2') {// ѧ��ѧ�ڶ�ѡ
		html += "<input type='text' title='"+xnTip+"' name='xnView' class='xnView' onfocus='xnSelect(this,"
				+ zqlx + ");' value='" + xnTip + "'/>";
	} else if (zqlx == '3') {//
		html += "<input type='text' title='"+xnTip+"' name='xnView' class='xnView' onfocus='xnSelect(this,"
				+ zqlx + ");' value='" + xnTip + "'/>";
	} else {// ��
		html += "��";
	}
	return html;
}

//��ϵ��������ʾ����ֵ
function gxSet(gxObj) {
	var gxdm = jQuery(gxObj).val();
	var tjdm = jQuery(gxObj).parent().parent().find("select[name=tjdm]").val();
	var tjzHtml11 = createTjz(tjdm, gxdm);
	jQuery(gxObj).parent().parent().find("td[name=tjzTd]").html(tjzHtml11);

	jQuery(gxObj).change(function() {
		var tjzHtml = createTjz(tjdm, jQuery(this).val());
		jQuery(this).parent().parent().find("td[name=tjzTd]").html(tjzHtml);
	});
}

//��������ֵ
function createTjz(tjdm, gxdm) {
	var tjgxList = dataObj['tjgxList'];
	var html = "";
	for ( var i = 0; i < tjgxList.length; i++) {
		var name = "";
		var value = "";
		var o = tjgxList[i];
		if (o.tjdm == tjdm && o.gxdm == gxdm) {
			html += "<input type='hidden' name='gxlx' value='" + o.gxlx + "'>";// ��ϵ����
			if (o.gxlx == "1") {// �����ı�������
				html += createTjzLx1();
			} else if (o.gxlx == "2") {// �����б�
				html += createTjzLx2(o.gxz);
			} else if (o.gxlx == "3") {// checkbox��ѡ��
				html += createTjzLx3(o.gxz);
			} else if (o.gxlx == "4") {// С���ı���
				html += createTjzLx4(o.gxz);
			} else if (o.gxlx == "5") {// checkbox��ѡ��-����ѡ��
				html += createTjzLx5(o.gxz);
			}
		}
	}
	return html;
}

//��������ֵ������1����������
function createTjzLx1() {
	var html = "";
	html += "<input type='text' name='tjz' class='tjz1' maxlength='10'>";
	return html;
}

// ��������ֵ������4��С���ı���
function createTjzLx4() {
	var html = "";
	html += "<input type='text' name='tjz' class='tjz1' maxlength='10'>";
	return html;
}

// ��������ֵ������2�������б�
function createTjzLx2(gxz) {
	var html = "";
	var gxzs = "";
	if (gxz != null) {
		gxzs = gxz.split("|");
	}
	html += "<select name='tjz' class='tjz2'>";
	for ( var i = 0; i < gxzs.length; i = i + 2) {
		var name = gxzs[i];
		if (gxzs[i + 1] != undefined) {
			name = gxzs[i + 1];
		}
		html += "<option value='" + gxzs[i] + "'>" + name + "</option>";
	}
	html += "</select>";
	return html;
}

// ��������ֵ������3��checkbox
function createTjzLx3(gxz) {
	var html = "";
	var gxzs = "";
	if (gxz != null) {
		gxzs = gxz.split("|");
	}
	for ( var i = 0; i < gxzs.length; i = i + 2) {
		var name = gxzs[i];
		if (gxzs[i + 1] != undefined) {
			name = gxzs[i + 1];
		}
		html += "<label>";
		html += "<input type='checkbox' name='tjz' value='" + gxzs[i] + "' >";
		html += name;
		html += "</label>";
		html += "<br/>";

	}
	return html;
}

// ��������ֵ������5��checkbox��ѡ��-����ѡ��
function createTjzLx5(gxz) {
	var html = "";
	html += "<input type='hidden' name='tjzCur' value='0'/>";
	html += "<input type='hidden' name='tjz' value=''/>";
	html += "<input type='text' name='tjzView' class='tjzView' onfocus='tjzSelect(this,\""
			+ gxz + "\");' value='"+xnTip+"'/>";
	return html;
}

//ѧ�굯��
function xnSelect(obj, zqlx) {
	// obj.blur();
	var tjdm = jQuery(obj).parent().parent().find(".tjdm option:selected").val();
	var xn = jQuery(obj).parent().find("[name=xn]").val();
	jQuery("[name=xnCur]").val("0");
	jQuery(obj).parent().find("[name=xnCur]").val("1");
	var xmdm = jQuery("#xmdm").val();
	var url = 'xpjpy_xmwh_tjsz.do?method=xmwhTjszXn';
	url += "&xmdm=" + xmdm;
	url += "&xnVal=" + xn;
	url += "&zqlx=" + zqlx;
	url += "&tjdm=" + tjdm;
	var title = "��������";
	showDialog(title, 600, 400, url);
}

//����ֵ������5��checkbox��ѡ��-����
function tjzSelect(obj, gxz) {
	var tjz = jQuery(obj).parent().find("[name=tjz]").val();
	jQuery("[name=tjzCur]").val("0");
	jQuery(obj).parent().find("[name=tjzCur]").val("1");
	jQuery("[name=curGxz]").remove();
	var sHtml = "<input type='hidden' name='curGxz' value='" + gxz + "'/>";
	jQuery(obj).parent().append(sHtml);
	var xmdm = jQuery("#xmdm").val();
	var url = 'xpjpy_xmwh_tjsz.do?method=xmwhTjszTjzDiv';
	url += "&xmdm=" + xmdm;
	url += "&tjzVal=" + tjz;
	var title = "����ֵѡ��";
	showDialog(title, 600, 400, url);
}

/*ɾ��*/
function del() {
	var length = jQuery("#dataTable input:checkbox[name=grid_key]:checked").length;
	if (length == 0) {
		showAlert("��ѡ����Ҫɾ���ļ�¼��");
		return false;
	}
	showConfirmDivLayer("�Ƿ�ȷ��ɾ����ѡ�ļ�¼��", {
		"okFun" : function() {
			jQuery("#dataTable input[name=grid_key]:checked").each(
					function(index) {
						jQuery(this).parent().parent().remove();
						var tjdm = jQuery(this).parent().parent().find(
								"[name=tjdm]").val();
						var url = "xpjpy_xmwh_tjsz.do?method=xmwhTjszSc";
						jQuery.post(url, {
							xmdm : jQuery("#xmdm").val(),
							tjdm : tjdm
						}, function(data) {
						}, 'json');
					});
		}
	});
}

/* ���� */
function update() {
	var length = jQuery(tbodyTr).not("#nodata").length;
	if (length == 0) {
		showAlert("û����Ҫ����ļ�¼��");
		return false;
	}

	var json = "{data:[";
	var flag = false;
	var result = false;// jQuery����ѭ��
	var message = "";
	var tjdms = "";
	jQuery(tbodyTr)
			.each(function(index) {//
						var tjdm = jQuery(this).find("select[name=tjdm]").val();
						var gxdm = jQuery(this).find("select[name=gxdm]").val();
						var tjz = jQuery.trim(jQuery(this).find("[name=tjz]")
								.val());
						var xn = jQuery(this).find("[name=xn]").val();
						var yyfw = jQuery(this).find("select[name=yyfw]").val();
						var gxlx = jQuery(this).find("input[name=gxlx]").val();
						if (tjdm == "") {
							message = "��" + (index + 1) + "�м�¼[����]������Ϊ�գ�";
							result = true;
							return false;
						}

						if (tjdms.indexOf(gxdm + ":" + tjdm + ":" + xn + ":"
								+ yyfw + ":" + tjz ) > -1) {
							message = "��" + (index + 1) + "�м�¼[����]�ظ���";
							result = true;
							return false;
						}
						tjdms += gxdm + ":" + tjdm + ":" + xn + ":" + yyfw + ":" + tjz
								+ "," ;

						if (gxlx == "1") {// ��������
							if (tjz == "") {
								message = "��" + (index + 1) + "�м�¼[����ֵ]������Ϊ�գ�";
								result = true;
								return false;
							}
							var reg = /^\d{0,10}$/;
							if (!tjz.match(reg)) {
								message = "��" + (index + 1)
										+ "�м�¼[����ֵ]��ʽ���������������ָ�ʽ��";
								result = true;
								return false;
							}
						} else if (gxlx == "2" || gxlx == "5") {// ������,���㸴ѡ��
							if (tjz == "") {
								message = "��" + (index + 1) + "�м�¼[����ֵ]������Ϊ�գ�";
								result = true;
								return false;
							}
						} else if (gxlx == "3") {// checkbox
							tjz = "";
							var flag1 = false;
							jQuery(this).find(
									"input:checkbox[name=tjz]:checked").each(
									function(index) {
										if (flag1) {
											tjz += ",";
										} else {
											flag1 = true;
										}
										tjz += jQuery(this).val();
									});
							if (tjz == "") {
								message = "��" + (index + 1) + "�м�¼[����ֵ]������Ϊ�գ�";
								result = true;
								return false;
							}
						} else if (gxlx == "4") {// С������
							if (tjz == "") {
								message = "��" + (index + 1) + "�м�¼[����ֵ]������Ϊ�գ�";
								result = true;
								return false;
							}
							var reg = /^\d+\.?\d*$/;
							if (!tjz.match(reg)) {
								message = "��" + (index + 1)
										+ "�м�¼[����ֵ]��ʽ������������������С����ʽ��";
								result = true;
								return false;
							}
						}
						var tjList = dataObj['tjList'];
						// ����˵��
						var tjsmHtml = "";
						var zqlx = "";
						for ( var i = 0; i < tjList.length; i++) {
							var o = tjList[i];
							if (tjdm == o.tjdm) {
								if (o.zqlx == "3" && (xn == null || xn == "")) {
									message = "��" + (index + 1)
											+ "�м�¼[��������]������Ϊ�գ�";
									result = true;
									return false;
								}
							}
						}

						if (flag) {
							json += ",";
						} else {
							flag = true;
						}

						json += "{";
						json += "tjdm:'" + encodeURI(encodeURI(tjdm)) + "'";
						json += ",gxdm:'" + gxdm + "'";
						json += ",tjz:'" + encodeURI(encodeURI(tjz)) + "'";
						json += ",ylzq:'" + xn + "'";
						json += ",yyfw:'" + yyfw + "'";
						json += "}";
					});
	json += "]}";

	if (result) {// ��֤ʧ�ܣ���������
		showAlert(message);
		return false;
	}
	var url = "xpjpy_xmwh_tjsz.do?method=xmwhTjszXg&type=save";
	jQuery.post(url, {
		xmdm : jQuery("#xmdm").val(),
		json : json
	}, function(data) {
		if(data["success"] == false){
			showConfirm(data["message"]);
		}else{
			showAlert(data["message"],{},{"clkFun": function(tag) {
			}});
		}
	}, 'json');
}
