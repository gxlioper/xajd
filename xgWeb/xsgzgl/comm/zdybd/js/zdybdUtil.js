/*
 * ����hidden������html
 */
function createHiddenHtml(id, szz) {
	var sHtml = "";
	sHtml += "<input type='hidden' name='" + id + "' id='" + id + "'";
	if (szz != null) {
		sHtml += " value='" + szz + "' ";
	}
	sHtml += ">&nbsp;";
	return sHtml;
}

/*
 * ����hidden������html��������id
 */
function createHiddenNoIdHtml(id, szz) {
	var sHtml = "";
	sHtml += "<input type='hidden' name='" + id + "'";
	if (szz != null) {
		sHtml += " value='" + szz + "' ";
	}
	sHtml += ">&nbsp;";
	return sHtml;
}

/*
 * ����text�ı���html
 */
function createTextHtml(id, szz, bdkd) {
	var sHtml = "";
	sHtml += "<input type='text' name='" + id + "'  value='' id='" + id
			+ "' class='text_nor'";
	if (bdkd != null && bdkd != "") {
		sHtml += " style='width: " + bdkd + "px'";
	}
	sHtml += ">";
	return sHtml;
}

/*
 * ����text�ı���html
 */
function createTextHtmlByObj(obj) {
	var zd = obj.zd;
	var bdkd = obj.bdkd;
	var szz = obj.szz;
	var sHtml = "";
	sHtml += "<input type='text' name='" + zd + "' class='text_nor'";
	if (bdkd != null && bdkd != "") {
		sHtml += " style='width: " + bdkd + "px'";
	}
	var includeId = obj['includeId'];
	if (includeId != null && includeId != false) {
		sHtml += " id='" + zd + "'";
	}
	var max = getMaxLength(szz);
	if (max != "") {
		sHtml += " maxlength='" + max + "'";
	}
	sHtml += ">";
	return sHtml;
}

function getMaxLength(szz) {
	var max = "";
	if (szz != null && szz != "") {// ��󳤶�����
		var szzs = szz.split(",");
		if (szzs.length > 1) {
			max = szzs[1];
		}
	}
	return max;
}

/*
 * ����text�����ı���html
 */
function createSzTextHtml(id, szz, bdkd) {
	var sHtml = "";
	sHtml += "<input type='text' name='" + id + "'  value='' id='" + id
			+ "' class='text_nor'";
	if (bdkd != null && bdkd != "") {
		sHtml += " style='width: " + bdkd + "px'";
	}
	sHtml += ">";
	return sHtml;
}

/*
 * ����textarea�ı���html
 */
function createTextareaHtml(id, szz, bdkd) {
	var sHtml = "";
	sHtml += "<textarea  id='"
			+ id
			+ "' name='"
			+ id
			+ "'  rows='4'   style='word-break:break-all;width:97%'></textarea>";
	return sHtml;
}

/*
 * ����text�����ı���html
 */
function createDateTextHtml(id, szz, bdkd) {
	var sHtml = "";
	if (szz == null || szz == "") {
		szz = "yyyyMMdd";
	}
	sHtml += "<input type='text' id='" + id + "' name='" + id
			+ "'  onfocus='WdatePicker({dateFmt:\"" + szz
			+ "\"})' class='text_nor'";
	if (bdkd != null && bdkd != "") {
		sHtml += " style='width: " + bdkd + "px'";
	}
	sHtml += ">";
	return sHtml;
}

/*
 * ����select������html
 */
function createSelectHtml(id, szz, szlx, bdkd) {
	var sHtml = "";
	if (bdkd == null || bdkd == "") {
		bdkd = 150;
	}
	sHtml += "<select  id='" + id + "' name='" + id + "' style='width:" + bdkd
			+ "px'>";
	if (szlx == "10" || szlx == "20" || szlx == "30") {
		sHtml += getSelectDefaultOpt();
	}
	if (szz != null) {
		var szzList = eval(szz);
		if (szzList != null) {
			for ( var j = 0; j < szzList.length; j++) {
				var szzObj = szzList[j];
				var dm = szzObj.dm;
				var mc = szzObj.mc;
				sHtml += "<option value='" + dm + "'>" + mc + "</option>";
			}
		}
	}
	sHtml += "</select>";
	return sHtml;
}

function createSelectHtmlByObj(obj) {
	var zd = obj.zd;
	var szz = obj.szz;
	var szlx = obj.szlx;
	var bdkd = obj.bdkd;
	var sHtml = "";
	if (bdkd == null || bdkd == "") {
		bdkd = 150;
	}
	sHtml += "<select  name='" + zd + "' style='width:" + bdkd + "px'";
	var includeId = obj['includeId'];
	if (includeId != null && includeId != false) {
		sHtml += " id='" + zd + "'";
	}
	sHtml += ">";
	if (szlx == "10" || szlx == "20" || szlx == "30") {
		sHtml += getSelectDefaultOpt();
	}
	if (szz != null) {
		var szzList = eval(szz);
		if (szzList != null) {
			for ( var j = 0; j < szzList.length; j++) {
				var szzObj = szzList[j];
				var dm = szzObj.dm;
				var mc = szzObj.mc;
				sHtml += "<option value='" + dm + "'>" + mc + "</option>";
			}
		}
	}
	sHtml += "</select>";
	return sHtml;
}

/*
 * ����radio��ѡ��html
 */
function createRadioHtml(id, szz) {
	var sHtml = "";
	if (szz != null) {
		var szzList = eval(szz);
		if (szzList != null) {
			for ( var j = 0; j < szzList.length; j++) {
				var szzObj = szzList[j];
				var dm = szzObj.dm;
				var mc = szzObj.mc;
				sHtml += "<label>";
				sHtml += "<input type='radio' name='" + id + "' value='" + dm
						+ "'>";
				sHtml += mc;
				sHtml += "</label>";
			}
		}
	}
	sHtml += "</select>";
	return sHtml;
}

/*
 * ����checkbox��ѡ��html
 */
function createCheckboxHtml(id, szz) {
	var sHtml = "";
	if (szz != null) {
		var szzList = eval(szz);
		if (szzList != null) {
			for ( var j = 0; j < szzList.length; j++) {
				var szzObj = szzList[j];
				var dm = szzObj.dm;
				var mc = szzObj.mc;
				sHtml += "<label>";
				sHtml += "<input type='checkbox' name='" + id
						+ "' value='" + dm + "'>";
				sHtml += mc;
				sHtml += "</label>";
			}
		}
	}
	return sHtml;
}

/*
 * ����ѧԺרҵ�༶html
 */
function createXyzybjHtml(id, szz) {
	var sHtml = "";
	if (id == "nj") {
		sHtml += "<input type='text' id='nj' name='nj' readonly='readonly' class='text_nor'>";
	} else if (id == "xydm") {
		sHtml += "<input type='text' id='xymc' name='xymc' readonly='readonly' class='text_nor'>";
		sHtml += "<input type='hidden' id='xydm' name='xydm' >";
	} else if (id == "zydm") {
		sHtml += "<input type='text' id='zymc' name='zymc' readonly='readonly' class='text_nor'>";
		sHtml += "<input type='hidden' id='zydm' name='zydm' >";
	} else if (id == "bjdm") {
		sHtml += "<input type='text' id='bjmc' name='bjmc' readonly='readonly' class='text_nor'>";
		sHtml += "<input type='hidden' id='bjdm' name='bjdm' >";
		sHtml += "<button type='button' class='btn_01' id='button_bj' onclick='njxyzybjSelect();'>ѡ��</button>";
	} else if (id == "zybj"){
        sHtml += "<input type='text' id='zybjmc' name='zybjmc' readonly='readonly' class='text_nor'>";
        sHtml += "<input type='hidden' id='zybj' name='zybj' >";
        sHtml += "<button type='button' class='btn_01' id='button_zybj' onclick='zybjSelect();'>ѡ��</button>";
	}
	return sHtml;
}

function njxyzybjSelect() {
	// ����Ŀǰʹ�ð༶ѡ�񵯲����ԭ����������������һ���ֱ�ӱ����ݴ���ʽ������һ�����ݿ���ֵ����ֱ��ȫ����ʾ
	/*if (jQuery("#xymc").val() == "" || jQuery("#zymc").val() == ""
			|| jQuery("#bjmc").val() == "") {
		jQuery("#xymc").val("");
		jQuery("#zymc").val("");
		jQuery("#bjmc").val("");
		jQuery("#xydm").val("");
		jQuery("#zydm").val("");
		jQuery("#bjdm").val("");
	}*/
	getBj();
}

function zybjSelect(){
    // ����Ŀǰʹ�ð༶ѡ�񵯲����ԭ����������������һ���ֱ�ӱ����ݴ���ʽ������һ�����ݿ���ֵ����ֱ��ȫ����ʾ
   /* if (jQuery("#xymc").val() == "" || jQuery("#zymc").val() == ""
        || jQuery("#bjmc").val() == "") {
        jQuery("#xymc").val("");
        jQuery("#zymc").val("");
        jQuery("#bjmc").val("");
        jQuery("#xydm").val("");
        jQuery("#zydm").val("");
        jQuery("#bjdm").val("");
    }*/
    getZyBj();
}
/*
 * ����22:ʡ����ѡ�� html
 */
function createSsxHtml(id, szz) {
	var sHtml = "";
	sHtml += "<select id='" + id + "_province' name='" + id
			+ "_province' style='width:120px'>";
	sHtml += "</select>";
	sHtml += "&nbsp;&nbsp;&nbsp;&nbsp;";

	sHtml += "<select id='" + id + "_city' name='" + id
			+ "_city' style='width:120px'>";
	sHtml += "</select>";
	sHtml += "&nbsp;&nbsp;&nbsp;&nbsp;";

	sHtml += "<select id='" + id + "_locale' name='" + id
			+ "_locale' style='width:120px'>";
	sHtml += "</select>";
	sHtml += "&nbsp;&nbsp;&nbsp;&nbsp;";

	sHtml += "<input type='hidden' name='" + id + "'  id='" + id + "'>";
	return sHtml;
}


//����32 ����
function createFjHtml(zd, curFlszid){
	var curList = curFlszid.split("_");
	var cur = curList[curList.length-1];
	var sHtml = "";
	sHtml += "<input type='hidden' name='"+zd+"' id='hid_"+cur+"_"+zd+"_index' />";
	sHtml += "<input type='file' id='"+cur+"_"+zd+"_index' name='"+cur+"_"+zd+"_index' />";
	sHtml += "<script type='text/javascript'>" +
			"jQuery(function(){" +
			  "jQuery('#"+cur+"_"+zd+"_index').multiUploader({"+
				"maxcount : 3,"+
				"accept : 'png|gif|jpg|zip|rar|doc|docx|pdf',"+
				"maxsize: 10,"+
				//������ʾ
				"hideTS : true,"+
				"elementid : 'hid_"+cur+"_"+zd+"_index',"+
				"eid : '"+cur+"_"+zd+"_index'});"+
		    "});"+
		    "jQuery('#tipsxlshjlxxxg_"+zd+"_"+index+"').hide();"+
	       "</script>";
	return sHtml;
}

/*
 * �õ�selectĬ��option,
 */
function getSelectDefaultOpt() {
	return "<option value=''>--��ѡ��--</option>";
}

/*
 * ����ʡ��������
 */
function setSsxLiandong(id, szz) {
	jQuery("#" + id + "_province").append(getSelectDefaultOpt());
	jQuery("#" + id + "_city").append(getSelectDefaultOpt());
	jQuery("#" + id + "_locale").append(getSelectDefaultOpt());

	var sHtml = "";
	if (szz == null) {
		return;
	}
	for ( var i = 0; i < szz.length; i++) {
		var shObj = szz[i];
		var shdm = shObj.value;
		var shmc = shObj.treeNode;
		var shChild = shObj.childNode;
		sHtml += "<option value='" + shdm + "'>" + shmc + "</option>";

		if (shChild == null) {
			continue;
		}

		for ( var j = 0; j < shChild.length; j++) {
			var shiObj = shChild[j];
			var shidm = shiObj.value;
			var shimc = shiObj.treeNode;
			var shiChild = shiObj.childNode;
		}
	}

	jQuery("#" + id + "_province").append(sHtml);

	jQuery("#" + id + "_province").change(
			function() {
				var sShiHtml = "";
				var curSh = jQuery(this).val();
				for ( var i = 0; i < szz.length; i++) {
					var shObj = szz[i];
					var shdm = shObj.value;
					var shmc = shObj.treeNode;
					var shChild = shObj.childNode;
					if (curSh == shdm) {
						if (shChild == null) {
							break;
						}
						for ( var j = 0; j < shChild.length; j++) {
							var shiObj = shChild[j];
							var shidm = shiObj.value;
							var shimc = shiObj.treeNode;
							var shiChild = shiObj.childNode;
							sShiHtml += "<option value='" + shidm + "'>"
									+ shimc + "</option>";
						}
					}
				}
				jQuery("#" + id + "_locale").empty().append(
						getSelectDefaultOpt());

				jQuery("#" + id + "_city").empty()
						.append(getSelectDefaultOpt()).append(sShiHtml);
			});

	jQuery("#" + id + "_city").change(function() {
		var sXiHtml = "";
		var curSh = jQuery("#" + id + "_province").val();// ��ǰʡ
			var curShi = jQuery(this).val();// ��ǰ��
			for ( var i = 0; i < szz.length; i++) {
				var shObj = szz[i];
				var shdm = shObj.value;
				var shmc = shObj.treeNode;
				var shChild = shObj.childNode;
				if (curSh != shdm) {
					continue;
				}
				if (shChild == null) {
					break;
				}
				for ( var j = 0; j < shChild.length; j++) {
					var shiObj = shChild[j];
					var shidm = shiObj.value;
					var shimc = shiObj.treeNode;
					var shiChild = shiObj.childNode;

					if (shidm != curShi) {
						continue;
					}
					if (shiChild == null) {
						break;
					}

					for ( var k = 0; k < shiChild.length; k++) {
						var xiObj = shiChild[k];
						var xidm = xiObj.value;
						var ximc = xiObj.treeNode;

						sXiHtml += "<option value='" + xidm + "'>" + ximc
								+ "</option>";
					}

				}
			}

			jQuery("#" + id + "_locale").empty().append(getSelectDefaultOpt())
					.append(sXiHtml);
		});

	var zddm = "";
	jQuery("#" + id + "_province").change(function() {// ��ʡֵ����������
				jQuery("#" + id).val(jQuery(this).val());
			});

	jQuery("#" + id + "_city").change(function() {// ����ֵ����������
				zddm = jQuery(this).val();
				if(zddm == ""){
					zddm = jQuery("#" + id + "_province").val();
				}
				jQuery("#" + id).val(zddm);
			});
	jQuery("#" + id + "_locale").change(function() {// ����ֵ����������
				zddm = jQuery(this).val();
				if(zddm == ""){
					zddm = jQuery("#" + id + "_city").val();
				}
				jQuery("#" + id).val(zddm);
			});
}


/**
 * ͨ�����룬��ȡʡ����
 * 
 * @param xi
 * @return
 */
function getSsx(szz, dm) {
	var result = {};
	var sHtml = "";
	if (szz == null || dm == null || dm == "" || dm.length < 6) {
		return result;
	}
	if(dm.substring(2,6) == "0000"){//ʡ
		result = getSsxBySh(szz, dm);
	}else if(dm.substring(4,6) == "00"){//��
		result = getSsxByShi(szz, dm);
	}else{//��
		result = getSsxByXi(szz, dm);
	}
	return result;
}

/**
 * ͨ��ʡ���룬��ȡʡ����
 * 
 * @param xi
 * @return
 */
function getSsxBySh(szz, sh) {
	var result = {};
	var sHtml = "";
	for ( var i = 0; i < szz.length; i++) {
		var shObj = szz[i];
		var shdm = shObj.value;
		var shmc = shObj.treeNode;
		var shChild = shObj.childNode;
		if (shChild == null) {
			continue;
		}
		if (shdm == sh) {
			result.shdm = shdm;
			result.shidm = "";
			result.xidm = "";
			result.shmc = shmc;
			result.shimc = "";
			result.ximc = "";
			break;
		}
	}
	return result;
}


/**
 * ͨ���д��룬��ȡʡ����
 * 
 * @param xi
 * @return
 */
function getSsxByShi(szz, shi) {
	var result = {};
	var sHtml = "";
	for ( var i = 0; i < szz.length; i++) {
		var shObj = szz[i];
		var shdm = shObj.value;
		var shmc = shObj.treeNode;
		var shChild = shObj.childNode;
		if (shChild == null) {
			continue;
		}
		for ( var j = 0; j < shChild.length; j++) {
			var shiObj = shChild[j];
			var shidm = shiObj.value;
			var shimc = shiObj.treeNode;
			var shiChild = shiObj.childNode;
			if (shidm == shi) {
				result.shdm = shdm;
				result.shidm = shidm;
				result.xidm = "";
				result.shmc = shmc;
				result.shimc = shimc;
				result.ximc = "";
				break;
			}
		}
	}
	return result;
}


/**
 * ͨ���ش��룬��ȡʡ����
 * 
 * @param xi
 * @return
 */
function getSsxByXi(szz, xi) {
	var result = {};
	var sHtml = "";
	for ( var i = 0; i < szz.length; i++) {
		var shObj = szz[i];
		var shdm = shObj.value;
		var shmc = shObj.treeNode;
		var shChild = shObj.childNode;
		if (shChild == null) {
			continue;
		}
		for ( var j = 0; j < shChild.length; j++) {
			var shiObj = shChild[j];
			var shidm = shiObj.value;
			var shimc = shiObj.treeNode;
			var shiChild = shiObj.childNode;
			if (shiChild == null) {
				continue;
			}

			for ( var k = 0; k < shiChild.length; k++) {
				var xiObj = shiChild[k];
				var xidm = xiObj.value;
				var ximc = xiObj.treeNode;

				if (xidm == xi) {
					result.shdm = shdm;
					result.shidm = shidm;
					result.xidm = xidm;
					result.shmc = shmc;
					result.shimc = shimc;
					result.ximc = ximc;
					break;
				}
			}
		}
	}
	return result;
}

/*
 * �Զ������֤-�ǿ�
 */
function checkZdybdYxwk(o, value) {
	var result = {};
	result.success = true;

	var zd = o.zd;
	var bdmc = o.bdmc;
	var szlx = o.szlx;
	var szz = o.szz;
	var zdlx = o.zdlx;
	var yxwk = o.yxwk;
	if (yxwk != "0") {
		return result;
	}
	if (zdlx == "3" || zdlx == "4") {// ��ѡ�򣬸�ѡ��
		var length = jQuery("#" + CONTENT_ID + " [name='" + zd + "']:checked").length;
		if (length == 0) {
			result.success = false;
			result.message = "[" + bdmc + "]������Ϊ�գ�";
		}
	} else if (zdlx == "2" || zdlx == "5" || zdlx == "6" || zdlx == "11"
			|| zdlx == "13" || zdlx == "22" || zdlx == "24") {
		if (value == null) {
			var value = jQuery.trim(jQuery("#" + zd).val());
			if(zdlx == "22" && value != "710000" && value != "810000" && value != "820000"){
				if(szlx == "51"){
					value = jQuery.trim(jQuery("#" + zd + "_city").val());
					bdmc += "ʡ��";
				} else if(szlx == "52"){
					if(value != "235000" && value != "236000" && value != "441900" && value != "442000" && value != "460400"){
						value = jQuery.trim(jQuery("#" + zd + "_locale").val());
						bdmc += "ʡ����";
					}
				}
			}
		}
		if (value == "") {
			result.success = false;
			result.message = "[" + bdmc + "]������Ϊ�գ�";
		}
	}else if(zdlx=="21"){//��Ƭ
		var sczp=jQuery("#zpsfcz").val();//�Ƿ����ϴ���Ƭ
		if("true"!=sczp){
			result.success = false;
			result.message = "[" + bdmc + "]������Ϊ�գ�";
		}
	}
	return result;
}

/*
 * �Զ������֤
 */
function checkZdybd(o, value) {
	var result = {};
	result.success = true;
	var zd = o.zd;
	var bdmc = o.bdmc;
	var zdlx = o.zdlx;
	var szlx = o.szlx;
	var szz = o.szz;
	var yxwk = o.yxwk;

	/*
	 * 11:���֡���ĸ���»��� 12:����ĸ 13:������ 14:С�� 16��΢�ź� 21:���� 22:�绰���� 23:�ֻ����� 24:���֤�� 25:�ʱ�
	 * 99:������ʽ����ǰ�ֶ�ֵ��Ϊ���� 31:����Ȼ��
	 */
	if (szlx == "11") {
		result = checkZdybdSzx(zd, bdmc, value);
	} else if (szlx == "12") {
		result = checkZdybdZm(zd, bdmc, value);
	} else if (szlx == "13"||szlx == "31") {
		result = checkZdybdSz(zd, bdmc, value);
	} else if (szlx == "14") {

	} else if (szlx == "15") {
		result = checkZdybdZmKg(zd, bdmc, value);
	} else if (szlx == "16") {
		result = checkZdybdWxh(zd, bdmc, value);
	} else if (szlx == "21") {
		if("12898" == jQuery("#xxdm").val()){
			result = checkEmail(zd, bdmc, value);
		}
	} else if (szlx == "22") {
		result = checkZdybdDhhm(zd, bdmc, value);
	} else if (szlx == "23") {
		result = checkSjhm(zd, bdmc, value);
	} else if (szlx == "24") {
		result = checkZdybdSfzh(zd, bdmc, value);

	} else if (szlx == "25") {

	} else if (szlx == "99") {
		result = checkZdybdZzbds(zd, bdmc, szz, value);
	}
	return result;
}
/*
 * �Զ������֤-�ֻ�����
 */
function checkSjhm(zd, bdmc, value) {
	var result = {};
	result.success = true;
	var reg = "";
	var message = "[" + bdmc + "]��ʽ����";
	if (value == null) {
		var value = jQuery.trim(jQuery("#" + zd).val());
	}
		reg = /^((1[3,4,5,6,7,8,9][0-9])|(14[5,7])|(17[0,6,7,8])|(19[7]))\d{8}$/;
		message += "��ֻ֧���ֻ����룡";
	if (reg != "" && !reg.exec(value)) {
		result.success = false;
		result.message = message;
	}
	return result;
}
/*
 * �Զ������֤-Email
 */
function checkEmail(zd, bdmc, value) {
	var result = {};
	result.success = true;
	var reg = "";
	var message = "[" + bdmc + "]��ʽ����";
	if (value == null) {
		var value = jQuery.trim(jQuery("#" + zd).val());
	}
		reg = /^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+((\.[a-zA-Z0-9_-]{2,3}){1,2})$/;
		message += "�����������ʽ��";
	if (reg != "" && !reg.exec(value)) {
		result.success = false;
		result.message = message;
	}
	return result;
}
/*
 * �Զ������֤-������ĸ�»���
 */
function checkZdybdSzx(zd, bdmc, value) {
	var result = {};
	result.success = true;
	var reg = "";
	var message = "[" + bdmc + "]��ʽ����";
	if (value == null) {
		var value = jQuery.trim(jQuery("#" + zd).val());
	}
	if("10335" == jQuery("#xxdm").val()){
		reg = /^((\w)*|\u65e0)$/;
		message += "��ֻ�����������֡���ĸ���»��ߣ����û������д�ޣ�";
	}else{
		reg = /^(\w)*$/;
		message += "��ֻ�����������֡���ĸ���»��ߣ�";
	}
	if (reg != "" && !reg.exec(value)) {
		result.success = false;
		result.message = message;
	}
	return result;
}

/*
 * �Զ������֤-����ĸ
 */
function checkZdybdZm(zd, bdmc, value) {
	var result = {};
	result.success = true;
	var reg = "";
	var message = "[" + bdmc + "]��ʽ����";
	if (value == null) {
		var value = jQuery.trim(jQuery("#" + zd).val());
	}
	if("10335" == jQuery("#xxdm").val()){
		reg = /^([A-Za-z]*|\u65e0)$/;
		message += "��ֻ����������ĸ�����û������д�ޣ�";
	}else{
		reg = /^[A-Za-z]*$/;
		message += "��ֻ����������ĸ��";
	}
	if (reg != "" && !reg.exec(value)) {
		result.success = false;
		result.message = message;
	}
	return result;
}

/*
 * �Զ������֤-����ĸ �ո�
 */
function checkZdybdZmKg(zd, bdmc, value) {
	var result = {};
	result.success = true;
	var reg = "";
	var message = "[" + bdmc + "]��ʽ����";
	if (value == null) {
		var value = jQuery.trim(jQuery("#" + zd).val());
	}
	if("10335" == jQuery("#xxdm").val()){
		reg = /^(([A-Za-z]+\s|[A-Za-z])*|\u65e0)$/;
		message += "��ֻ����������ĸ�����û������д�ޣ�";
	}else{
		reg = /^([A-Za-z]+\s|[A-Za-z])*$/;
		message += "��ֻ����������ĸ��";
	}
	if (reg != "" && !reg.exec(value)) {
		result.success = false;
		result.message = message;
	}
	return result;
}


/*
 * �Զ������֤-΢�ź�
 */
function checkZdybdWxh(zd, bdmc, value) {
	var result = {};
	result.success = true;
	var reg = "";
	var message = "[" + bdmc + "]��ʽ����";
	if (value == null) {
		var value = jQuery.trim(jQuery("#" + zd).val());
	}
	
	reg = /^[a-zA-Z0-9_]+$/;
	message += "����������ȷ΢�źţ�";
	
	if (reg != "" && !reg.exec(value)) {
		result.success = false;
		result.message = message;
	}
	return result;
}

/*
 * �Զ������֤-������
 */
function checkZdybdSz(zd, bdmc, value) {
	var result = {};
	result.success = true;
	var reg = "";
	var message = "[" + bdmc + "]��ʽ����";
	if (value == null) {
		var value = jQuery.trim(jQuery("#" + zd).val());
	}
	if("10335" == jQuery("#xxdm").val()){
		reg = /^([0-9]*|\u65e0)$/;
		message += "��ֻ�����������֣����û������д�ޣ�";
	}else{
		reg = /^[0-9]*$/;
		message += "��ֻ�����������֣�";
	}
	if (reg != "" && !reg.exec(value)) {
		result.success = false;
		result.message = message;
	}
	return result;
}

/*
 * �Զ������֤-�绰����
 */
function checkZdybdDhhm(zd, bdmc, value) {
	var result = {};
	result.success = true;
	var reg = "";
	var message = "[" + bdmc + "]��ʽ����";
	if (value == null) {
		var value = jQuery.trim(jQuery("#" + zd).val());
	}
	if("10335" == jQuery("#xxdm").val()){
		reg = /^(\d{0,4}\-{0,1}\d{7,8}|\u65e0)$/;
		message += "�����û������д�ޣ�";
	}else{
		reg = /^\d{0,4}\-{0,1}\d{7,8}$/;
		message += "��";
	}
	if (reg != "" && !reg.exec(value)) {
		result.success = false;
		result.message = message;
	}
	return result;
}

/*
 * �Զ������֤-���֤��
 */
function checkZdybdSfzh(zd, bdmc, value) {
	var result = {};
	result.success = true;
	var reg = "";
	var message = "[" + bdmc + "]��ʽ����";
	if (value == null) {
		var value = jQuery.trim(jQuery("#" + zd).val());
	}
	if("10335" == jQuery("#xxdm").val()){
		reg = /^([1-9]{1}[0-9]{14}$|^[1-9]{1}[0-9]{16}([0-9]|[xX])|\u65e0)$/;
		message += "�����û������д�ޣ�";
	}else{
		reg = /^[1-9]{1}[0-9]{14}$|^[1-9]{1}[0-9]{16}([0-9]|[xX])$/;
		message += "��";
	}
	if (reg != "" && !reg.exec(value)) {
		result.success = false;
		result.message = message;
	}
	return result;
}

/*
 * �Զ������֤-������ʽ
 */
function checkZdybdZzbds(zd, bdmc, szz, value) {
	var result = {};
	result.success = true;
	var reg = "";
	var message = "[" + bdmc + "]��ʽ����";
	if (value == null) {
		var value = jQuery.trim(jQuery("#" + zd).val());
	}
	reg = eval(szz);
	message += "��";
	if (reg != "" && !reg.exec(value)) {
		result.success = false;
		result.message = message;
	}
	return result;
}

/**
 * �Զ������֤-������ĸ�»���-focus��ǰ����
 */
function checkSzx(obj, bdmc) {
	var zd = jQuery(obj).attr("id");
	var result = checkZdybdSzx(zd, bdmc);
	if (result != null && result.success == false) {
		showAlertDivLayer(result.message, {}, {
			"clkFun" : function() {
				jQuery("#" + zd).focus();
			}
		});
	}
}

/**
 * �Զ������֤-����ĸ-focus��ǰ����
 */
function checkZm(obj, bdmc) {
	var result = {};
	result.success = true;
	var reg = "";
	var message = "[" + bdmc + "]��ʽ����";
	var value = jQuery.trim(jQuery("#" + zd).val());
	reg = /^[A-Za-z]*$/;
	message += "��ֻ����������ĸ��";
	if (reg != "" && !reg.exec(value)) {
		result.success = false;
		result.message = message;
	}
	return result;
}

/**
 * �Զ������֤-������-focus��ǰ����
 */
function checkSz(obj, bdmc) {
	var zd = jQuery(obj).attr("id");
	var result = checkZdybdSz(zd, bdmc);
	if (result != null && result.success == false) {
		showAlertDivLayer(result.message, {}, {
			"clkFun" : function() {
				jQuery("#" + zd).focus();
			}
		});
	}
}

/**
 * �Զ������֤-�绰����-focus��ǰ����
 */
function checkDhhm(obj, bdmc) {
	var zd = jQuery(obj).attr("id");
	var result = checkZdybdDhhm(zd, bdmc);
	if (result != null && result.success == false) {
		showAlertDivLayer(result.message, {}, {
			"clkFun" : function() {
				jQuery("#" + zd).focus();
			}
		});
	}
}

/**
 * �Զ������֤-���֤��-focus��ǰ����
 */
function checkSfzh(obj, bdmc) {
	var zd = jQuery(obj).attr("id");
	var result = checkZdybdSfzh(zd, bdmc);
	if (result != null && result.success == false) {
		showAlertDivLayer(result.message, {}, {
			"clkFun" : function() {
				jQuery("#" + zd).focus();
			}
		});
	}
}

/**
 * �Զ������֤-������ʽ-focus��ǰ����
 */
function checkZzbds(obj, bdmc, szz) {
	var zd = jQuery(obj).attr("id");
	var result = checkZdybdZzbds(zd, bdmc, szz);
	if (result != null && result.success == false) {
		showAlertDivLayer(result.message, {}, {
			"clkFun" : function() {
				jQuery("#" + zd).focus();
			}
		});
	}
}

/**
 * ֻ������������
 * 
 * @param obj
 * @return
 */
function limitSz(obj) {
	if("10335" == jQuery("#xxdm").val()){
		jQuery(obj).val(jQuery(obj).val().replace(/[^\d,^\u65e0]/g, ''));
	}else{
		jQuery(obj).val(jQuery(obj).val().replace(/[^\d]/g, ''));
	}
}

/**
 * ֻ�����������֡���ĸ���»���
 * 
 * @param obj
 * @return
 */
function limitSzZmXhx(obj) {
	if("10335" == jQuery("#xxdm").val()){
		jQuery(obj).val(jQuery(obj).val().replace(/[^\w,^\u65e0]/g, ''));
	}
	else{
		jQuery(obj).val(jQuery(obj).val().replace(/[^\w]/g, ''));
	}
}

/**
 * ֻ��������С��
 * 
 * @param obj
 * @return
 */
function limitXsXhx(obj) {
	if("10335" == jQuery("#xxdm").val()){
		jQuery(obj).val(jQuery(obj).val().replace(/[[^0-9.],^\u65e0]/g, ''));
	}
	else{
		jQuery(obj).val(jQuery(obj).val().replace(/[^0-9.]/g, ''));
	}
}

/*
 * ������֤
 */
function checkZdybdLength(o) {
	var result = {};
	result.success = true;
	var zd = o.zd;
	var bdmc = o.bdmc;
	var szlx = o.szlx;
	var szz = o.szz;
	var zdlx = o.zdlx;
	var value = jQuery.trim(jQuery("#" + zd).val());

	if (szz != null) {
		var szzs = szz.split(",");
		var min = 0;
		var max = 0;
		var length = value.length;
		if (szzs.length > 0) {
			min = szzs[0];
		}
		if (szzs.length > 1) {
			max = szzs[1];
		}

		if (min != 0 && max == 0 && length < min) {
			result.success = false;
			result.message = "[" + bdmc + "]��ʽ����������Ϊ" + min + "�֣�";
		} else if (min == 0 && max != 0 && length > max) {
			result.success = false;
			result.message = "[" + bdmc + "]��ʽ����������Ϊ" + max + "�֣�";
		} else if (min != 0 && max != 0 && (length < min || length > max)) {
			result.success = false;
			result.message = "[" + bdmc + "]��ʽ������Ϊ" + min + "-" + max + "�֣�";
		}
	}
	return result;
}

function getXyZyBj(obj) {
	var url = "";
	var nj = "";
	var xydm = "";
	var zydm = "";

	if (jQuery('#nj')) {
		nj = jQuery('#nj').val();
	}
	if (jQuery('#xydm')) {
		nj = jQuery('#xydm').val();
	}

	if (jQuery('#zydm')) {
		nj = jQuery('#zydm').val();
	}

	url = "/xgxt/commXgInfo.do?method=xzBj";
	url += "&nj=" + nj;
	url += "&xydm=" + xydm;
	url += "&zydm=" + zydm;
	url += "&zdpzstr=" + obj;

	// showTopWin(url,750,600);
	showDialog("", 750, 600, url);
	// jQuery.dialog({title:'B����',content:'url:/xgxt/commXgInfo.do?method=xzBj',parent:this});
}

/**
 * ͨ�������ȡ����
 * 
 * @param dm
 * @param szz
 * @return
 */
function getNameByDm(dm, szz) {
	var mc = dm;
	if (dm == null) {
		return "";
	}
	if (szz == null || szz == "") {
		return mc;
	}

	var szzList = eval(szz);
	if (szzList != null) {
		for ( var j = 0; j < szzList.length; j++) {
			var szzObj = szzList[j];
			var dmTemp = szzObj.dm;
			var mcTemp = szzObj.mc;
			if (dmTemp == dm) {
				mc = mcTemp;
			}
		}
	}
	return mc;
}

/*
 * js��Ч
 */
function navAA() {
	jQuery(".college_title").toggle(function() {
		// jQuery(this).siblings().hide();
			// jQuery(this).children(".up").attr("class", "down").text("չ��");
			return false;
		}, function() {
			// jQuery(this).siblings().show();
			// jQuery(this).children(".down").attr("class", "up").text("����");
		})
	jQuery(".college_title a:eq()").toggle(function() {
		jQuery(this).siblings().hide();
		// jQuery(".btn").attr("class", "down");
			// jQuery(".btn").text("չ��");
			return false;
		}, function() {
			jQuery(".demo_college .con").show();
			// jQuery(".btn").attr("class", "up");
			// jQuery(".btn").text("����");
		})
	jQuery(".demo_college li .college_li").hover(function() {
		jQuery(this).next().show();
		jQuery(this).parent().css("position", "relative");
	}, function() {
		jQuery(this).next().hide();
		jQuery(this).parent().css("position", "");
	})
	jQuery(".list_xxxx li").hover(function() {
		jQuery(this).children(".list_xxxx_downmenu").show();
		jQuery(this).css("position", "relative");
	}, function() {
		jQuery(this).children(".list_xxxx_downmenu").hide();
		jQuery(this).css("position", "");
	})
	jQuery(".list_xxxx_downmenu").hover(function() {
		jQuery(this).show();
		jQuery(this).prev().attr("class", "hover");
	}, function() {
		jQuery(this).hide();
		jQuery(this).prev().removeClass("hover");
	});

	jQuery('#position-fixed').find(".list_xxxx li").hover(function() {

		jQuery(this).children(".list_xxxx_downmenu").show();
		jQuery(this).css("position", "relative")
	}, function() {
		jQuery(this).children(".list_xxxx_downmenu").hide();
		jQuery(this).css("position", "")
	});
	jQuery('#position-fixed').find(".list_xxxx_downmenu").hover(function() {
		jQuery(this).show();
		jQuery(this).prev().attr("class", "hover")
	}, function() {
		jQuery(this).hide();
		jQuery(this).prev().removeClass("hover")
	});
	jQuery(".list_xxxx_downmenu dd").live('click', function() {
		jQuery(this).parent().parent().hide();
	});

	var sfxsgban = jQuery.trim(jQuery("#sfxsgban").val());
	if (sfxsgban != null && sfxsgban != "") {
		// ����IEҳ�浱�еĶ�λ����
		jQuery("a").each(function() {
			var link = jQuery(this);
			var href = link.attr("href");
			if (href && href[0] == "#") {
				var name = href.substring(1);
				jQuery(this).click(function() {
					var nameElement = jQuery("[name='" + name + "']");
					var idElement = jQuery("#" + name);
					var element = null;
					if (nameElement.length > 0) {
						element = nameElement;
					} else if (idElement.length > 0) {
						element = idElement;
					}

					if (element) {
						var offset = element.offset();
						window.parent.scrollTo(offset.left, offset.top + 125);
					}
					return false;
				});
			}
		});
	}

	jQuery(".smooth").click(function() {
		var href = jQuery(this).attr("href");
		var _href = jQuery("[name='" + href.substr(1) + "']");
		if (_href.length > 0) {
			var pos = jQuery("[name='" + href.substr(1) + "']").position().top;
			jQuery("html,body").animate( {
				scrollTop : pos - 110
			}, 400);
		}
		return false;
	});
}

/**
 * json����
 */
var JSON = function() {
	var m = {
		'\b' : '\\b',
		'\t' : '\\t',
		'\n' : '\\n',
		'\f' : '\\f',
		'\r' : '\\r',
		'"' : '\\"',
		'\\' : '\\\\'
	}, s = {
		'boolean' : function(x) {
			return String(x);
		},
		number : function(x) {
			return isFinite(x) ? String(x) : 'null';
		},
		string : function(x) {
			if (/["\\\x00-\x1f]/.test(x)) {
				x = x.replace(/([\x00-\x1f\\"])/g, function(a, b) {
					var c = m[b];
					if (c) {
						return c;
					}
					c = b.charCodeAt();
					return '\\u00' + Math.floor(c / 16).toString(16)
							+ (c % 16).toString(16);
				});
			}
			return '"' + x + '"';
		},
		object : function(x) {
			if (x) {
				var a = [], b, f, i, l, v;
				if (x instanceof Array) {
					a[0] = '[';
					l = x.length;
					for (i = 0; i < l; i += 1) {
						v = x[i];
						f = s[typeof v];
						if (f) {
							v = f(v);
							if (typeof v == 'string') {
								if (b) {
									a[a.length] = ',';
								}
								a[a.length] = v;
								b = true;
							}
						}
					}
					a[a.length] = ']';
				} else if (x instanceof Object) {
					a[0] = '{';
					for (i in x) {
						v = x[i];
						f = s[typeof v];
						if (f) {
							v = f(v);
							if (typeof v == 'string') {
								if (b) {
									a[a.length] = ',';
								}
								a.push(s.string(i), ':', v);
								b = true;
							}
						}
					}
					a[a.length] = '}';
				} else {
					return;
				}
				return a.join('');
			}
			return 'null';
		}
	};
	return {
		copyright : '',
		license : '',
		stringify : function(v) {
			var f = s[typeof v];
			if (f) {
				v = f(v);
				if (typeof v == 'string') {
					return v;
				}
			}
			return null;
		},
		parse : function(text) {
			try {
				return !(/[^,:{}\[\]0-9.\-+Eaeflnr-u \n\r\t]/.test(text
						.replace(/"(\\.|[^"\\])*"/g, '')))
						&& eval('(' + text + ')');
			} catch (e) {
				return false;
			}
		}
	};
}();

function htmlJsEncode(str){
	var s = "";
	if (str == null || str.length == 0){
		return "";
	}
	s = str.replace(/&/g, "&gt;");
	s = s.replace(/</g, "&lt;");
	s = s.replace(/>/g, "&gt;");
	s = s.replace(/ /g, "&nbsp;");
	s = s.replace(/\'/g, "&#39;");
	s = s.replace(/\"/g, "&quot;");
	s = s.replace(/\n/g, "<br/>");
	s = s.replace(/\t/g, "<br/>");
	return s;
}

function htmlJsDecode(str){
	var s = "";
	if (str == null || str.length == 0){
		return "";
	}
	s = str.replace(/&gt;/g, "&");
	s = s.replace(/&lt;/g, "<");
	s = s.replace(/&gt;/g, ">");
	s = s.replace(/&nbsp;/g, " ");
	s = s.replace(/&#39;/g, "\'");
	s = s.replace(/&quot;/g, "\"");
	s = s.replace(/<br>/g, "\n");
	return s;
}

function checkyhkh(obj,checkid1,checkid2) {
	if(jQuery(obj).attr('name') == checkid1){
		if(jQuery(obj).val() != jQuery("#"+checkid2).val()){
			showAlertDivLayer("�������п������벻һ�£�", {}, {
				"clkFun" : function() {
					jQuery(obj).val("");
					jQuery(obj).focus();
				}
			});
		}
	}
}
