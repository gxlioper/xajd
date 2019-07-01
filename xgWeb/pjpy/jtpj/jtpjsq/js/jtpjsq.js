var action = "jtpjsq.do";
function searchRs() {
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
}
function pjjtmc(cellValue, rowObject) {
	return "<a href='javascript:void(0);' onclick=\"ckxx('" + rowObject["sqid"]
			+ "')\" class='name'>" + cellValue + "</a>";
}
// �鿴��Ϣ
function ckxx(sqid) {
	var url = action + "?method=showView&sqid=" + sqid;
	showDialog("������Ϣ", 800, 500, url);
}
/**
 * ����������Ϣ��ʾ
 * 
 * @return
 */
function setQsxxShow(bjdm) {
	var iswzdx = jQuery("#iswzdx").val();
	// ��������ݴ�ѧ
	if (iswzdx == "1") {
		jQuery("#qswmxx").load("jtpjsq.do?method=qsxxlist&bjdm=" + bjdm);
	}
}
function jxpdzq(cellValue, rowObject) {
	var pdxq = rowObject["pdxqmc"];
	if (!pdxq) {
		pdxq = "";
	}
	return rowObject["pdxn"] + pdxq;
}
function shzt(cellValue, rowObject) {
	var shzt = rowObject["shzt"];
	var shztmc = "";
	switch (shzt) {
	case "1":
		shztmc = "ͨ��";
		break;
	case "2":
		shztmc = "��ͨ��";
		break;
	case "3":
		shztmc = "���˻�";
		break;
	case "5":
		shztmc = "�����";
		break;
	default:
		shztmc = "δ�ύ";
		break;
	}
	return shztmc;
}
// ����
function add() {
	var url = action + "?method=add";
	showDialog("������������", 800, 500, url);
}
/**
 * ���ؽ�����Ϣ
 * 
 * @return
 */
function loadJxxx() {
	var jxid = jQuery("#jxid").val();
	jQuery.post("jtpjsz.do?method=loadJxxx", {
		jxid : jxid
	}, function(data) {
		/*
		 * jQuery("#sqxn").text(data.sqxn); jQuery("#sqxq").text(data.sqxqmc);
		 */
		var pdzq="";
		if(data.pdxn){
			pdzq=data.pdxn + " " + data.pdxqmc;
		}
		jQuery("#pdzq").text(pdzq);
		jQuery("#jxsm").text(data.jxsm);
		jQuery("#splcid").val(data.splcid);
		if (data.jtpjdw == "bj") {
			loadBjpjxx();
		} else if (data.jtpjdw == "xy") {
			loadXypjxx();
		} else if (data.jtpjdw == "qs") {
			loadQspjxx();
		} else {
			loadOther();
		}

		// �˻غ���ɵ���
		if(jQuery("#shzt").val()=='3'){
			jQuery("#jxid").attr("disabled","disabled");
		}
	}, 'json');
}
function loadUpdate() {
	jQuery.post("jtpjsq.do?method=loadUpdate"+"&tt="+new Date().getTime(), {
		sqid : jQuery("#sqid").val()
	}, function(data) {
		if (data) {
			autoSetParam(data, false);
		}
	}, 'json');
}
/**
 * ���ذ༶������Ϣ
 * 
 * @return
 */
function loadBjpjxx() {
	jQuery.ajaxSetup( {
		async : false
	});
	var bjdm = jQuery("#pjjtid").val();
	var type = jQuery("#type").val();
	var sqid = jQuery("#sqid").val();
	if (!bjdm) {
		bjdm = jQuery("#mrpjjtiid").val();
	}
	jQuery("#jtpjxx").load(
			"jtpjsq.do?method=loadBjpjxx&bjdm=" + bjdm + "&type=" + type
					+ "&sqid=" + sqid + "&tt=" + new Date().getTime(),
			function() {
				 fomartForm();
				// ����������Ϣ
				setQsxxShow(jQuery("#pjjtid").val());
				loadUpdate();
			});
	 pjjtidchange();
	jQuery.ajaxSetup( {
		async : true
	});
	
}
/**
 * ����ѧԺ������Ϣ
 * 
 * @return
 */
function loadXypjxx() {
	jQuery.ajaxSetup( {
		async : false
	});
	var xydm = jQuery("#pjjtid").val();
	var type = jQuery("#type").val();
	var sqid = jQuery("#sqid").val();
	if (!xydm) {
		xydm = jQuery("#mrpjjtiid").val();
	}
	jQuery("#jtpjxx").load(
			"jtpjsq.do?method=loadXypjxx&xydm=" + xydm + "&type=" + type
					+ "&sqid=" + sqid + "&tt=" + new Date().getTime(),
			function() {
				fomartForm();
				loadUpdate();
			});
	jQuery.ajaxSetup( {
		async : true
	});
}
function loadOther() {
	jQuery.ajaxSetup( {
		async : false
	});
	var type = jQuery("#type").val();
	var sqid = jQuery("#sqid").val();
	jQuery("#jtpjxx").load(
			"jtpjsq.do?method=loadOtherPjxx&type=" + type + "&sqid=" + sqid
					+ "&tt=" + new Date().getTime(), function() {
				fomartForm();
				loadUpdate();
			});
	jQuery.ajaxSetup( {
		async : true
	});
}
/**
 * �޸�
 * 
 * @return
 */
function update() {
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1) {
		showAlert("��ѡ��һ����Ҫ�޸ĵļ�¼��");
	} else {
		var shzt = rows[0]["shzt"];
		if ("0" != shzt && "3" != shzt) {
			showAlertDivLayer("ֻ���޸�δ�ύ�����˻صļ�¼��");
			return false;
		}
		var url = action + '?method=update&sqid=' + rows[0]["sqid"];
		var title = "�޸ļ�����������";
		showDialog(title, 800, 560, url);
	}
}
/**
 * ���ü�����������
 */
function setJtpjmc() {
	var pjjtmc = jQuery("#pjjtid>option:selected").text();
	if(pjjtmc){
		jQuery("#pjjtmc").val(pjjtmc);
	}
}
/**
 * ����
 * 
 * @param url
 * @param checkId
 * @return
 */
function save(url, checkId) {
	setJtpjmc();
	if (!checkNull(checkId)) {
		return false;
	}
	if (!checkother()) {
		return false;
	}
	if(!bjyzts()){
		return false;
	}
	lock();
	jQuery("#form").ajaxSubmit( {
		url : url,
		type : "post",
		dataType : "json",
		success : function(data) {
		 if(data["message"] == "�ύ�ɹ���"){
				showAlert(data["message"], {}, {
					"clkFun" : function() {
						if (parent.window) {
							refershParent();
						}
					}
				});
		 }else{
			 showAlert(data["message"]);
		 }
		
		},
		contentType : "application/x-www-form-urlencoded;charset=utf-8"
	});
	unlock();
}
/**
 * ȥ����Ƕ��form
 * 
 * @return
 */
function fomartForm() {
	var html = jQuery("#innerForm").html();
	if (html) {
		jQuery("#jtpjxx").html(html);
	}
}
function checkother() {
	return true;
}
// ɾ��
function del() {
	var ids = jQuery("#dataTable").getSeletIds();
	if (ids.length == 0) {
		showAlertDivLayer("��ѡ����Ҫɾ���ļ�¼��");
	} else {
		var rows = jQuery("#dataTable").getSeletRow();
		for ( var i = 0; i < ids.length; i++) {
			if (rows[i]['shzt'] != '0') {
				showAlertDivLayer("ֻ��ɾ��δ�ύ�ļ�¼��");
				return false;
			}
		}
		showConfirmDivLayer("��ȷ��Ҫɾ��ѡ��ļ�¼��", {
			"okFun" : function() {
				jQuery.post(action + "?method=del", {
					values : ids.toString()
				}, function(data) {
					var mes = "�ɹ�ɾ����<font color='green'>&nbsp;"
							+ data["successDel"] + "&nbsp;</font>������";
					mes += "</br>";
					showAlertDivLayer(mes, {}, {
						"clkFun" : function() {
							jQuery("#dataTable").reloadGrid();
						}
					});
				}, 'json');
			}
		});

	}
}
/**
 * �ύ
 * 
 * @return
 */
function tj() {
	var ids = jQuery("#dataTable").getSeletIds();
	if (ids.length != 1) {
		showAlertDivLayer("��ѡ��һ����Ҫ�ύ�ļ�¼��");
	} else {
		var rows = jQuery("#dataTable").getSeletRow();
		for ( var i = 0; i < ids.length; i++) {
			if (rows[i]['shzt'] != '0' && rows[i]['shzt'] != '3') {
				showAlertDivLayer("��ѡ��δ�ύ�������˻صļ�¼��");
				return false;
			}
		}
		var shzt = rows[0]["shzt"];
		
		
		showConfirmDivLayer("��ȷ��Ҫ�ύѡ��ļ�¼��", {
			"okFun" : function() {
				if(shzt!="3"){
					// ��֤�Ƿ���ύ
					jQuery.post(action + "?method=checkSfktj", {
						jxid:rows[0]["jxid"]
					}, function(data) {
						if(data ==null || data=="false"){
							showAlertDivLayer("������Ľ����ѹر����룬�޷��ύ����������ϵ����Ա��");
							return false;
						}else{
							// �ύ
							jQuery.post(action + "?method=subBusi", {
								values : ids.toString(),
								sqid : rows[0]['sqid']
							}, function(data) {
								showAlertDivLayer(data["message"]);
								jQuery("#dataTable").reloadGrid();
							}, 'json');
						}
					});
					
				}else{
	
					// �ύ
					jQuery.post(action + "?method=subBusi", {
						values : ids.toString(),
						sqid : rows[0]['sqid']
					}, function(data) {
						showAlertDivLayer(data["message"]);
						jQuery("#dataTable").reloadGrid();
					}, 'json');
				}			

			}
		});
	}
}
/**
 * ����
 * 
 * @return
 */
function cancle() {
	var ids = jQuery("#dataTable").getSeletIds();
	if (ids.length != 1) {
		showAlertDivLayer("��ѡ��һ����Ҫ�����ļ�¼��");
	} else {
		var rows = jQuery("#dataTable").getSeletRow();
		for ( var i = 0; i < ids.length; i++) {
			if (rows[i]['shzt'] != '5') {
				showAlertDivLayer("ֻ������еļ�¼���ܱ�������");
				return false;
			}
		}
		showConfirmDivLayer("��ȷ��Ҫ����ѡ��ļ�¼��", {
			"okFun" : function() {
				jQuery.post(action + "?method=cancle", {
					values : ids.toString(),
					lcid : rows[0]['splcid']
				}, function(data) {
					showAlertDivLayer(data["message"]);
					jQuery("#dataTable").reloadGrid();
				}, 'json');
			}
		});
	}
}
function rcxwshLcinfo() {
	var ids = jQuery("#dataTable").getSeletIds();
	var rows = jQuery("#dataTable").getSeletRow();
	if (ids.length != 1) {
		showAlertDivLayer("��ѡ��һ�����̸��ټ�¼��");
	} else if (rows[0]["shzt"] == "0") {
		showAlertDivLayer("��δ�ύ�������������Ϣ��");
	} else {
		showDialog("����������̸���", 600, 400, 'comm_spl.do?method=lcgz&sqid='
				+ rows[0]['sqid'] + "&splc=" + rows[0]['splcid']);
	}
}

/**
 * ��������������Ϣ
 */
function loadQspjxx() {
	jQuery.ajaxSetup( {
		async : false
	});
	var type=jQuery("#type").val();
	var sqid = jQuery("#sqid").val();
	jQuery("#jtpjxx").load("jtpjsq.do?method=loadld&type=" + type+"&sqid=" + sqid,function(){
			fomartForm();
		});
	loadLdxx();
	jQuery.ajaxSetup({async : true});
	loadUpdate();
}

function loadLdxx(){
	var pjjtid=jQuery("#mrpjjtiid").val();
	var first=jQuery("#first").val();
	if("1"==first&&""!=pjjtid&&null!=pjjtid){
		var pjjtids=pjjtid.split("@@");
		 jQuery('#lddm').val(pjjtids[0]);
	}
	jQuery('#ldxb').empty();
	jQuery('#ch').empty();
	jQuery('#qsh').empty();
	jQuery.post('gyglnew_qsgl.do?method=loadLdxx', { lddm : jQuery('#lddm').val() }, function(data) {
		var qsch = parseInt(data.qsch); //��ʼ���
		var ldcs = parseInt(data.ldcs); //¥������
		var count = 0;
		jQuery('#ldxb').text(data.ldxb);
		var option = "<option value=''></option>"
		for(var i=qsch;i<=ldcs;i++){
			option += "<option value='" +i+"'>"+i+"</option>";
		}
		jQuery('#ch').append(option);
	}, 'json');
	loadChxx();
}


function loadChxx() {
	jQuery('#qsh').empty();
	var lddm=jQuery('#lddm').val();
	if(!(lddm==null||lddm=="")){
		jQuery.getJSON('gyglnew_cwgl.do?method=getQshForLddm', {
			lddm : lddm, ch : jQuery('#ch').val() },function(data) {
					var option = "<option value=''></option>";
					if (data != null && data.length != 0) {
						for ( var i = 0; i < data.length; i++) {
							option +="<option value=\""+data[i].qsh+"\">"	+data[i].qsh+"</option>";
						}
					}
					jQuery('#qsh').append(option);
				});
	} else {
		var option = "<option value=''></option>";
		jQuery('#qsh').append(option);
	}
	loadQsxx();
}

function loadQsxx(){
	jQuery.ajaxSetup( {
		async : false
	});
	var pjjtid=jQuery("#mrpjjtiid").val();
	var first=jQuery("#first").val();
	if("1"==first&&""!=pjjtid&&null!=pjjtid){
		var pjjtids=pjjtid.split("@@");
		 jQuery('#qsh').val(pjjtids[1]);
	}
	jQuery("#qsxsxx").load("jtpjsq.do?method=qsxsxxList");
	var lddm=jQuery('#lddm').val();
	var qsh=jQuery('#qsh').val();
	if(qsh==null||qsh==""){
		jQuery("#pjjtid").val("");
		jQuery("#pjjtmc").val("");
	}else{
		jQuery("#pjjtid").val(jQuery('#lddm').val()+"@@"+jQuery('#qsh').val());
		jQuery("#pjjtmc").val(jQuery("#lddm option:selected").text()+"-"+jQuery('#qsh').val());
	}
	pjjtidchange();
	jQuery("#rowConut").val(jQuery("#rowConut").text());
	jQuery("#first").val("0");
	jQuery.ajaxSetup( {
		async : true
	});
}