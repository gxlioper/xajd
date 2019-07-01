function showTopWin(url, w, h, scrollbar) {
	var info = "Status:YES;dialogWidth:" + w + "px;dialogHeight:" + h
			+ "px;help:no;";
	if (scrollbar != null) {
		showModalDialog(url, window, info, scrollbar);
		return false;
	}
	showModalDialog(url, window, info);
}

// ���˼������ά���޸�
function checkDwwhUpdate() {

	var check = jQuery("input[type=checkbox][name=primarykey_checkVal]:checked");
	var num = check.length;
	if (num == 1) {
		var zgh = jQuery(check[0]).val();
		// showTopWin("/xgxt/szdw_dwwh.do?method=cxJzgxx&zgh="+zgh,830,600); .
		showDialog('', 830, 500, "/xgxt/szdw_dwwh.do?method=cxJzgxx&zgh=" + zgh);
		// window.open ('/xgxt/szdw_dwwh.do?method=cxJzgxx&zgh='+zgh);
	} else {
		alertError("�빴ѡ<font color='blue'>һ����¼</font>�����޸�");
		return false;
	}
}
// �������ά��
function saveDwwh(lx) {

	var zgh = jQuery("#text_zgh").val();
	var xm = jQuery("#text_xm").val();

	if (zgh == "") {
		showAlert("ְ���Ų���Ϊ�գ���ȷ��");
		return false;
	}
	var isExisting = checkInputIsExisting("fdyxxb", "zgh", zgh, "");

	if (lx == "insert" && isExisting == "true") {
		alertError("��ְ�����Ѵ��ڣ�����ȷ��");
		return false;
	}

	if (xm == "") {
		alertError("��������Ϊ�գ���ȷ��");
		return false;
	}

	// ·��
	var url = "szdw_dwwh.do?method=saveDwwh";
	// ����
	var parameter = {
		"str_zgh" : escape(jQuery("#text_zgh").val()),
		"str_xm" : escape(jQuery("#text_xm").val()),
		"str_xb" : escape(jQuery("#select_xb").val()),
		"str_bmdm" : escape(jQuery("#select_bmdm").val()),
		"str_lxdh" : escape(jQuery("#text_lxdh").val())
	};

	// jQuery.ajaxSetup({async:false});

	jQuery.post(url, parameter, function(result) {
		showAlert(result, {}, {
			"clkFun" : function() {
				if (parent.window) {
					refershParent();
				} else {
					iFClose();
				}
			}
		});
	});

	jQuery.ajaxSetup( {
		async : true
	});
}
jQuery(function() {
	onChange();
	jQuery("#kzzd16").change(function() {
		onChange();
	});

});
// �������change�¼�
function onChange() {
	if ("רְ����Ա" == jQuery("#kzzd16").val()) {
		jQuery("#sfjtlx").css("display", "");
		jQuery("#sfjtlx_th").show();
		jQuery("#sfjtlx_td").show();
		jQuery("#drsj_td").attr("colspan", "2");
	} else if ("��ְ����Ա" == jQuery("#kzzd16").val()
			|| "������" == jQuery("#kzzd16").val()) {
		jQuery("#sfjtlx").css("display", "");
		jQuery("#kzzd17").val(jQuery('#kzzd17 option:first').val());
		jQuery("#drsj_td").attr("colspan", "4");
		jQuery("#sfjtlx_th").hide();
		jQuery("#sfjtlx_td").hide();
	} else {
		jQuery("#sfjtlx").css("display", "none");
	}
}

function updateDwwh(lx) {
	var fields = jQuery("#dwwh_form").serializeArray();
	var zgh = jQuery("#zgh").val();
	var xm = jQuery("#xm").val();
	var isNull = false;
	// ����Ƿ���ڿ���
	jQuery.each(fields, function(i, field) {
		if ("dwlbdm" != jQuery(field).attr("name")
				&& "gwlbdm" != jQuery(field).attr("name") && i < 31) {
			if ("" == jQuery(field).val() || null == jQuery(field).val()) {
				isNull = true;
			}
		}
		if ("" == jQuery("#cjgzrq").val() || null == jQuery("#cjgzrq").val()
				|| "" == jQuery("#szgzsj").val()
				|| null == jQuery("#szgzsj").val()
				|| "" == jQuery("#gzjl").val()
				|| null == jQuery("#gzjl").val()) {
			isNull = true;
		}
		//����Ա������Ϣ�޸�ҳ��QQδ��֤���������֤
		if("" == jQuery("#kzzd3").val()||null == jQuery("#kzzd3").val()||
				"" == jQuery("#lxdh").val()||null == jQuery("#lxdh").val()){
			
			isNull=true;
		}
	});
	if (isNull) {
		showAlertDivLayer("�뽫��������д������");
		return false;
	}
	var jtzz = jQuery("#jtzz").val();

	var zyzz = jQuery("#zyzz").val();

	var grhjqk = jQuery("#grhjqk").val();

	var txdz = jQuery("#txdz").val();

	var gzjl = jQuery("#gzjl").val();

	var bz = jQuery("#bz").val();

	var pjpy = jQuery("#kzzd4").val();
	var fblw = jQuery("#fblw").val();
	var pxqk = jQuery("#pxqk").val();

	var sfzh = jQuery("#sfzh").val();

	var reg = /^[1-9]{1}[0-9]{14}$|^[1-9]{1}[0-9]{16}([0-9]|[xX])$/;

	if (sfzh != "" && reg != "" && !reg.exec(sfzh)) {
		alertError("���֤�����벻��ȷ");
		return false;
	}
	if (zgh == "") {
		alertError("ְ���Ų���Ϊ�գ���ȷ��");
		return false;
	}
	if (xm == "") {
		alertError("��������Ϊ�գ���ȷ��");
		return false;
	}
	var isExisting = checkInputIsExisting("fdyxxb", "zgh", zgh, "");

	if (lx == "insert" && isExisting == "true") {
		alertError("��ְ�����Ѵ��ڣ�����ȷ��");
		return false;
	}
	if (jtzz.length > 150) {
		alertError("��ͥסַֻ������150����");
		return false;
	} else if (zyzz.length > 200) {
		alertError("��Ҫְ��ֻ������200����");
		return false;
	} else if (grhjqk.length > 2000) {
		alertError("�����ֻ������2000����");
		return false;
	} else if (txdz.length > 50) {
		alertError("ͨѶ��ַֻ������50����");
		return false;
	} else if (gzjl.length > 2000) {
		alertError("��������ֻ������2000����");
		return false;
	} else if (pjpy.length > 2000) {
		alertError("��������ֻ������2000����");
		return false;
	} else if (fblw.length > 2000) {
		alertError("��������ֻ������2000����");
		return false;
	} else if (pxqk.length > 300) {
		alertError("�μ���ѵֻ������300����");
		return false;
	} else if (bz.length > 2000) {
		alertError("��עֻ������2000����");
		return false;
	}

	// ·��
	var url = "szdw_dwwh.do?method=updateDwwh";

	jQuery.ajax( {
		type : 'POST',
		url : url,
		dataType : "text",
		contentType : "application/x-www-form-urlencoded; charset=UTF-8",
		data : fields,
		success : function(datas) {
			showAlert(datas, {}, {
				"clkFun" : function() {
					if (parent.window) {
						refershParent();
					}
				}
			});
		}
	});

}

// ��������ά��DIV
function createDwwhDiv(lx) {

	var zgh = "";
	var num = jQuery("input[type=checkbox][name=primarykey_checkVal]:checked").length;

	if (lx == "update") {
		zgh = jQuery("input[type=checkbox][name=primarykey_checkVal]:checked")
				.eq(0).val();
	}

	// ·��
	var url = "szdw_dwwh.do?method=createDwwhDiv";
	// ����
	var parameter = {
		"str_lx" : lx,
		"str_zgh" : zgh
	};
	url += "&str_lx=" + lx + "&str_zgh=" + zgh;
	showDialog('', 400, 270, url);
	/*
	 * jQuery("#div_dwwh").load( url, parameter, function(){ //tipsWindown("
	 * ","id:div_dwwh","400","300","true","","true","id");
	 * showDialogDiv("","400","300","div_dwwh"); } );
	 */
}

// ɾ������ά��
function deleteDwwh() {

	var len = jQuery("[name=primarykey_checkVal]:checked").length;

	if (len != 0) {
		var flag = true;
		// jQuery("[name=primarykey_checkVal]:checked").each(function(){
		// var shzt = jQuery(this).parents().children("td").eq(8).html();
		// if(shzt != "δ���"){
		// flag = false;
		// }
		// });

		if (!flag) {
			// alertError("ֻ��ɾ��<font color='blue'>δ���</font>�ļ�¼������ȷ��");
		}

		if (flag) {

			confirmInfo(
					"����ȷ��<font color='blue'>�Ƿ�ɾ��</font>����ѡ�Ľ�ʦ��¼��ע���h���Ľ�ʦ�˻��޷��ٵ�¼ϵͳ",
					function(tag) {
						if (tag == "ok") {
							var url = "szdw_dwwh.do?method=deleteDwwh";
							var pkValue = new Array();
							var i = 0;

							jQuery("input[name=primarykey_checkVal]:checked")
									.each(function() {
										pkValue[i] = jQuery(this).val();
										i++;
									});

							var parameter = {};
							parameter["array_pkValue"] = escape(pkValue
									.join("!!array!!"));

							$("divWaiting").style.display = "";
							$("divDisable").style.display = "";

							jQuery.ajaxSetup( {
								async : false
							});

							jQuery.post(url, parameter, function(result) {
								$("divWaiting").style.display = "none";
								$("divDisable").style.display = "none";
								searchRs();
								alertInfo(result);
								closeWindown();
							});

							jQuery.ajaxSetup( {
								async : true
							});
						}
					});
		}
	} else {
		alertError("��<font color='blue'>��ѡ</font>��ϣ��ɾ���ļ�¼��");
		return false;
	}
}

// �����û���DIV
function createYhkDiv() {

	var num = jQuery("input[type=checkbox][name=primarykey_checkVal]:checked").length;
	if (num == 0) {
		alertInfo("����<font color='blue'>��ѡ</font>ϣ����ӵ��û���Ľ�ʦ��¼");
		return false;
	}

	// ·��
	var url = "szdw_dwwh.do?method=createYhkDiv";
	// ����
	var parameter = {

	};

	jQuery("#div_yhk").load(url, parameter, function() {
		tipsWindown(" ", "id:div_yhk", "400", "150", "true", "", "true", "id");
	});
}

// ���浽�û���
function saveYhk() {

	var len = jQuery("[name=primarykey_checkVal]:checked").length;

	if (len != 0) {

		var url = "szdw_dwwh.do?method=saveYhk";
		var pkValue = new Array();
		var i = 0;

		jQuery("input[name=primarykey_checkVal]:checked").each(function() {
			pkValue[i] = jQuery(this).val();
			i++;
		});

		var parameter = {};
		parameter["array_pkValue"] = escape(pkValue.join("!!array!!"));
		parameter["str_zdm"] = escape(jQuery("#select_zdm").val());

		$("divWaiting").style.display = "";
		$("divDisable").style.display = "";

		jQuery.ajaxSetup( {
			async : false
		});

		jQuery.post(url, parameter, function(result) {
			$("divWaiting").style.display = "none";
			$("divDisable").style.display = "none";
			searchRs();
			alertInfo(result);
			closeWindown();
		});

		jQuery.ajaxSetup( {
			async : true
		});

	} else {
		alertError("��<font color='blue'>��ѡ</font>��ϣ����ӵ��û���ļ�¼��");
		return false;
	}
}

// ����Ժϵ����DIV
function createYxjrDiv() {

	var num = jQuery("input[type=checkbox][name=primarykey_checkVal]:checked").length;

	if (num == 0) {
		alertInfo("����<font color='blue'>��ѡ</font>ϣ������Ժϵ��������Ľ�ʦ��¼");
		return false;
	}

	var tr = jQuery(
			"input[type=checkbox][name=primarykey_checkVal]:checked")
			.parents("tr");
	var fdyDbs = jQuery("input[name=fdyDbs]", tr).val();
	var bzrDbs = jQuery("input[name=bzrDbs]", tr).val();
	if (Number(fdyDbs) == 0 && Number(bzrDbs) == 0) {
		alertInfo("��������иò���");
		return false;
	}

	// ·��
	var url = "szdw_dwwh.do?method=createYxjrDiv";

	var pkValue = new Array();
	var i = 0;
	jQuery("input[name=primarykey_checkVal]:checked").each(function() {
		pkValue[i] = jQuery(this).val();
		i++;
	});
	url += "&array_pkValue=" + escape(pkValue.join("!!array!!"));
	// var parameter={};
	// parameter["array_pkValue"]=escape(pkValue.join("!!array!!"));
	// parameter["str_sfjryx"]=jQuery("input[name=radio_yxjr]:checked").eq(0).val();

	showDialog('ԺУ�����������', 450, 180, url);
	/*
	 * jQuery("#div_yxjr").load( url, parameter, function(){ tipsWindown("
	 * ","id:div_yxjr","450","200","true","","true","id"); } );
	 */
}

// ����ԺУ����
function saveYxjr() {

	var url = "szdw_dwwh.do?method=saveYxjr";
	// var pkValue=new Array();
	// var i=0;
	// jQuery("input[name=primarykey_checkVal]:checked").each(function(){alert(111);
	// pkValue[i]=jQuery(this).val();
	// i++;
	// });
	var parameter = {};
	parameter["array_pkValue"] = jQuery("#selectId").val();
	parameter["str_sfjryx"] = jQuery("input[name=radio_yxjr]:checked").eq(0)
			.val();
	jQuery.post(url, parameter, function(result) {
		showAlert(result, {}, {
			"clkFun" : function() {
				if (parent.window) {
					refershParent();
				}
				iFClose();
			}
		});
	});

	jQuery.ajaxSetup( {
		async : true
	});
}

// ǰ��˼������ά��
function goDwwh() {
	var url = "szdw_general_dwwh.do?ty=2";
	refreshForm(url);
}

// �����꼶Div
function createNjLvDiv() {

	var lx = jQuery("#hidden_lx").val();
	var zgh = jQuery("#hidden_zgh").val();

	// ·��
	var url = "szdw_dwwh.do?method=createNjLvDiv";
	// ����
	var parameter = {
		"str_lx" : lx,
		"str_zgh" : zgh
	};

	jQuery.ajaxSetup( {
		async : false
	});

	jQuery("#div_nj").load(url, parameter, function() {
	});

	jQuery.ajaxSetup( {
		async : true
	});
}

// ������������Div
function createOtherLvDiv(type) {

	var url = "szdw_dwwh.do?method=createOtherLvDiv";

	var njV = jQuery("#njV").val();
	var checked_nj = $("checkbox_nj_" + njV).checked;
	var lx = jQuery("#hidden_lx").val();
	var zgh = jQuery("#hidden_zgh").val();

	// ����
	var parameter = {
		"str_njV" : njV,
		"str_checked_nj" : checked_nj,
		"str_lx" : lx,
		"str_zgh" : zgh,
		"str_type" : type
	};

	jQuery.ajaxSetup( {
		async : false
	});

	jQuery("#div_other")
			.load(url,
					parameter,
					function() {
						// ������
					var dbs = jQuery("input[name=hidden_bjdm]",
							jQuery("#div_fpbj_old")).length;

					jQuery("#span_dbs").html(dbs);

					var node = $("div_fpbj_new");

					jQuery("input[type=checkbox]:checked").each(function() {

						var bjid = jQuery(this).attr("id");

						if (bjid.split("_")[1] == "bj") {

							if (!$("hidden_bjdm_" + jQuery(this).val())) {
								var tmp = document.createElement("input");
								tmp.type = "text";
								tmp.name = "hidden_bjdm";
								tmp.id = "hidden_bjdm_" + jQuery(this).val();
								tmp.value = jQuery(this).val();
								node.appendChild(tmp);
							}
						}
					});

					createBjClick();
				});

	jQuery.ajaxSetup( {
		async : true
	});

	$("divWaiting").style.display = "none";
	$("divDisable").style.display = "none";
}

// �����༶���
function createBjClick() {
	jQuery("input[name=hidden_bjdm]", jQuery("#div_fpbj_new")).each(function() {
		var bjdm = jQuery(this).val();
		if ($("checkbox_bj_" + bjdm)) {
			jQuery("#checkbox_bj_" + bjdm).attr("checked", true);
		}
	});
}

// ����꼶
function clickNj(obj, nj, count, lx) {

	$("divWaiting").style.display = "";
	$("divDisable").style.display = "";

	var id = obj.id;

	var as = getElementsByClass('current', document, 'li');
	for ( var i = 0; i < as.length; i++) {
		as[i].className = "";
	}

	obj.parentNode.className = "current";

	jQuery("#njV").val(nj);
	jQuery("#hidden_nj").val(count);

	// �μ������
	setTimeout("createOtherLvDiv('" + lx + "')", 100);
}

// ���ѧԺ
function clickXyCheckBox(bzdm) {

	var checked = $("checkbox_xy_" + bzdm).checked;

	jQuery("input[name=checkbox_zy_" + bzdm + "]").each(function() {
		var id = jQuery(this).attr("id");
		var checked_zy = $(id).checked;

		var zydm = id.replace("checkbox_zy_", "");

		if (checked) {
			if (checked_zy) {
				jQuery("input[name=checkbox_bj_" + zydm + "]").each(function() {
					jQuery(this).attr("checked", true);
					var bjdm = jQuery(this).attr("id").split("_")[2];
					clickBjCheckBox(bjdm);
				});
			} else {
				jQuery(this).attr("checked", true);

				jQuery("input[name=checkbox_bj_" + zydm + "]").each(function() {
					jQuery(this).attr("checked", true);
					var bjdm = jQuery(this).attr("id").split("_")[2];
					clickBjCheckBox(bjdm);
				});
			}
		} else {
			if (checked_zy) {
				jQuery(this).attr("checked", false);

				jQuery("input[name=checkbox_bj_" + zydm + "]").each(function() {
					jQuery(this).attr("checked", false);
					var bjdm = jQuery(this).attr("id").split("_")[2];
					clickBjCheckBox(bjdm);
				});
			} else {
				jQuery("input[name=checkbox_bj_" + zydm + "]").each(function() {
					jQuery(this).attr("checked", false);
					var bjdm = jQuery(this).attr("id").split("_")[2];
					clickBjCheckBox(bjdm);
				});
			}

		}
	});
}

// ���רҵ
function clickZyCheckBox(bzdm, sjdm) {

	var checked = $("checkbox_zy_" + bzdm).checked;
	var checked_xy = $("checkbox_xy_" + sjdm).checked;

	if (checked) {
		jQuery("input[name=checkbox_bj_" + bzdm + "]").each(function() {
			jQuery(this).attr("checked", true);
			var id = jQuery(this).attr("id");
			var bjdm = id.split("_")[2];
			clickBjCheckBox(bjdm);
		});
	} else {
		jQuery("input[name=checkbox_bj_" + bzdm + "]").each(function() {
			jQuery(this).attr("checked", false);
			var id = jQuery(this).attr("id");
			var bjdm = id.split("_")[2];
			clickBjCheckBox(bjdm);
		});
	}
}

// ����༶
function clickBjCheckBox(bzdm) {

	var checked_bj = $("checkbox_bj_" + bzdm).checked;

	if (checked_bj) {

		if (!$("hidden_bjdm_" + bzdm)) {
			var node = $("div_fpbj_new");

			var tmp = document.createElement("input");
			tmp.type = "text";
			tmp.name = "hidden_bjdm";
			tmp.id = "hidden_bjdm_" + bzdm;
			tmp.value = bzdm;
			node.appendChild(tmp);
		}
	} else {
		if ($("hidden_bjdm_" + bzdm)) {
			jQuery("#hidden_bjdm_" + bzdm).remove();
		}
	}
}

// �������༶
function disfrockFpbj() {

	// var bjdm = new Array();//�༶����
	// var i=0;
	//	
	// jQuery("input[name=hidden_bjdm]",jQuery("#div_fpbj_old")).each(function(){
	// bjdm[i]= jQuery(this).val();
	// i++;
	// });
	//	
	// jQuery("input[name=hidden_bjdm]",jQuery("#div_fpbj_new")).each(function(){
	// bjdm[i]= jQuery(this).val();
	// i++;
	// });
	//	
	// var url = "szdw_dwwh.do?method=saveFpbj";
	//
	// //����
	// var parameter = {
	// "array_bjdm":bjdm.join("!!array!!"),
	// "str_zgh":jQuery("#hidden_zgh").val(),
	// "str_lx":jQuery("#hidden_lx").val()
	// };
	//
	// jQuery.ajaxSetup({async:true});
	//	
	// jQuery.post(url,
	// parameter,
	// function(result){
	//		
	// alertInfo(result);
	//			
	// //�����꼶Div
	// createNjLvDiv();
	//			
	// jQuery("#div_fpbj_new").html("");
	//			
	// var index = jQuery("#hidden_nj").val();
	//		
	// var id = "";
	//			
	// if(index == ""){
	// id = "a_nj_0";
	// }else{
	// id = "a_nj_"+index;
	// }
	//					
	// if($(id)){
	// $(id).click();
	// }
	// }
	// );
	//
	// jQuery.ajaxSetup({async:true});

	var zgh = jQuery("#select_jzg").val();

	if (zgh == "" || zgh == null) {
		alertError("���ڽ�ְ���б���ѡ��һλ��ϣ��������ʦ");
		return false;
	}

	var bjdm_my = new Array();// ���˰༶

	var i = 0;

	jQuery("input[name=checkVal]:checked").each(function() {
		bjdm_my[i] = jQuery(this).val();
		i++;
	});

	if (i == 0) {
		alertInfo("�빴ѡ��Ҫ�����İ༶��");
		return false;
	}

	// ����
	var parameter = {
		"str_zgh" : jQuery("#select_jzg").val(),
		"array_bjdm_my" : bjdm_my.join("!!array!!"),
		"str_lx" : jQuery("#hidden_lx").val()
	};

	confirmInfo("ȷ��Ҫ��������ʦ��ѡ�а༶�ı���ϵ��?", function(ok) {
		if (ok == "ok") {

			jQuery.ajaxSetup( {
				async : true
			});

			var url = "szdw_dwwh.do?method=disfrockFpbj";

			jQuery.post(url, parameter, function(result) {
				alertInfo(result);
				onShow(zgh);
			});

			jQuery.ajaxSetup( {
				async : true
			});
		}
	});
}

// �������༶
function saveFpbj() {

	// var bjdm = new Array();//�༶����
	// var i=0;
	//	
	// jQuery("input[name=hidden_bjdm]",jQuery("#div_fpbj_old")).each(function(){
	// bjdm[i]= jQuery(this).val();
	// i++;
	// });
	//	
	// jQuery("input[name=hidden_bjdm]",jQuery("#div_fpbj_new")).each(function(){
	// bjdm[i]= jQuery(this).val();
	// i++;
	// });
	//	
	// var url = "szdw_dwwh.do?method=saveFpbj";
	//
	// //����
	// var parameter = {
	// "array_bjdm":bjdm.join("!!array!!"),
	// "str_zgh":jQuery("#hidden_zgh").val(),
	// "str_lx":jQuery("#hidden_lx").val()
	// };
	//
	// jQuery.ajaxSetup({async:true});
	//	
	// jQuery.post(url,
	// parameter,
	// function(result){
	//		
	// alertInfo(result);
	//			
	// //�����꼶Div
	// createNjLvDiv();
	//			
	// jQuery("#div_fpbj_new").html("");
	//			
	// var index = jQuery("#hidden_nj").val();
	//		
	// var id = "";
	//			
	// if(index == ""){
	// id = "a_nj_0";
	// }else{
	// id = "a_nj_"+index;
	// }
	//					
	// if($(id)){
	// $(id).click();
	// }
	// }
	// );
	//
	// jQuery.ajaxSetup({async:true});

	var zgh = jQuery("#select_jzg").val();

	if (zgh == "" || zgh == null) {
		alertError("���ڽ�ְ���б���ѡ��һλ��ϣ��������ʦ");
		return false;
	}

	var bjdm_other = new Array();// �����༶

	var bjdm_my = new Array();// ���˰༶

	var i = 0;
	jQuery("input[name=checkVal][checked=false]").each(function() {
		bjdm_other[i] = jQuery(this).val();
		i++;
	});

	i = 0;
	jQuery("input[name=checkVal]:checked").each(function() {
		bjdm_my[i] = jQuery(this).val();
		i++;
	});

	// ����
	var parameter = {
		"str_zgh" : jQuery("#select_jzg").val(),
		"array_bjdm_other" : bjdm_other.join("!!array!!"),
		"array_bjdm_my" : bjdm_my.join("!!array!!"),
		"str_lx" : jQuery("#hidden_lx").val()
	};

	jQuery.ajaxSetup( {
		async : true
	});

	var url = "szdw_dwwh.do?method=saveFpbj";

	jQuery.post(url, parameter, function(result) {
		alertInfo(result);
		onShow(zgh);
	});

	jQuery.ajaxSetup( {
		async : true
	});
}

// ǰ������Ա���
function goFdybb() {

	var num = jQuery("input[type=checkbox][name=primarykey_checkVal]:checked").length;

	if (num == 1) {
		var zgh = jQuery(
				"input[type=checkbox][name=primarykey_checkVal]:checked").eq(0)
				.val();
		var url = "general_szdw.do?method=szdwRybb&lx=fdy&zgh=" + zgh;
		refreshForm(url);
	} else {
		alertError("��<font color='blue'>��ѡһ��</font>��ϣ�����Ľ�ʦ��¼");
		return false;
	}
}

// ǰ�������α��
function goBzrbb() {
	var num = jQuery("input[type=checkbox][name=primarykey_checkVal]:checked").length;

	if (num == 1) {
		var zgh = jQuery(
				"input[type=checkbox][name=primarykey_checkVal]:checked").eq(0)
				.val();
		var url = "general_szdw.do?method=szdwRybb&lx=bzr&zgh=" + zgh;
		refreshForm(url);
	} else {
		alertError("��<font color='blue'>��ѡһ��</font>��ϣ�����Ľ�ʦ��¼");
		return false;
	}
}

// ��ʾ�༶��Ϣ
function showBjxx12(zgh, lx, num) {
	if (num == 0) {
		alertError("������Ϊ0���޷��鿴��ϸ");
		return false;
	} else {

		// ·��
		var url = "szdw_dwwh.do?method=createBjxxDiv";
		// ����
		var parameter = {
			"str_lx" : lx,
			"str_zgh" : zgh
		};

		jQuery("#div_bjxx").load(
				url,
				parameter,
				function() {
					tipsWindown(" ", "id:div_bjxx", "700", "360", "true", "",
							"true", "id");
				});
	}
}

/**
 * �޸ĵ�����ҳ��
 * 
 * @param zgh
 * @param lx
 * @param num
 * @return
 */
function showBjxx(zgh, lx, num) {
	if (num == 0) {
		alertError("������Ϊ0���޷��鿴��ϸ");
		return false;
	} else {
		var url = "szdw_dwwh.do?method=createBjxx&zgh=" + zgh + "&lx=" + lx;
		showDialog("", 700, 360, url);
	}
}

// ��ʾ��ְ����Ϣ
function showJzgxx(bjdm, lx, num) {
	if (num == 0) {
		alertError("��ʦ��Ϊ0���޷��鿴��ϸ");
		return false;
	} else {

		// ·��
		var url = "szdw_dwwh.do?method=createJzgxxDiv";
		// ����
		var parameter = {
			"str_lx" : lx,
			"str_bjV" : bjdm
		};

		jQuery("#div_jzgxx").load(
				url,
				parameter,
				function() {
					tipsWindown(" ", "id:div_jzgxx", "700", "360", "true", "",
							"true", "id");
				});
	}
}