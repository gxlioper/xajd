function setDivHeight() {
	if ($("table_rs")) {
		jQuery("#div_rs").height(jQuery("#table_rs").height() + 20);
	}
}

function xhLink(cellValue,rowObject){
	return "<a href='javascript:void(0);' class='name' onclick='zxsxxView(\""+rowObject["xh"]+"\");'>"+cellValue+"</a>";
}
function zxsxxView(xh) {
	showDialog("ѧ����Ϣ��ѯ", 850, 500, "xsxx_xsxxgl.do?method=xsxxglCk&xh=" + xh
			+ "&xs");
}
// ���ֽ���޸�
function cfjgXg() {
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length == 1) {
		var sjly = rows[0]["sjly"];
		var cfid = rows[0]["cfid"];
		var url = "wjcf_cfjg.do?method=cfjgXg";
		url += "&cfid=" + cfid+"&xh="+rows[0]["xh"]+"&sjly="+sjly;
		jQuery.ajaxSetup( {
			async : false
		});
		var count = 0;
		jQuery.post("wjcf_cfjg.do?method=cfsssjCx", {
			cfid : cfid
		}, function(result) {
			var json = eval(result);
			if (json[0].sswh != "" && json[0].sswh != null) {
				count = 1;
				showAlertDivLayer("�ô��������߲����ٽ����޸ģ�");
				return false;
			} else if (json[0].zzwh != "" && json[0].zzwh != null) {
				count = 1;
				showAlertDivLayer("�ô�������ֹ�����ٽ����޸ģ�");
				return false;
			} else if (json[0].jcwh != "" && json[0].jcwh != null) {
				count = 1;
				showAlertDivLayer("�ô�����" + jQuery("#text").val() + "�����ٽ����޸ģ�");
				return false;
			} else if (json[0].jclc != "0" && json[0].jclc != null) {
				count = 1;
				showAlertDivLayer("�ô���" + jQuery("#text").val()
						+ "����У������ٽ����޸ģ�");
				return false;
			} else if (json[0].sslc != "0" && json[0].sslc != null) {
				count = 1;
				showAlertDivLayer("�ô�����������У������ٽ����޸ģ�");
				return false;
			}
		});
		jQuery.ajaxSetup( {
			async : true
		});
		if (count == 0) {
			showDialog("�޸Ĵ��ֽ��", 800, 500, url);
		}
	} else {
		showAlertDivLayer("�빴ѡһ����Ҫ�޸ĵļ�¼��");
		return false;
	}
}
function showCfjg(cfid){
	showDialog("���ֽ���鿴",800,500,"wjcf_cfjg.do?method=cfjgCk&cfid="+cfid);
}
function showView() {
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length == 1) {
		var cfid = rows[0]["cfid"];
		var url = "wjcf_cfjg.do?method=cfjgCk&cfid=";
		url += cfid;
		showDialog("�鿴���ֽ��", 800, 500, url);
	} else {

		showAlertDivLayer("�빴ѡһ����Ҫ�鿴�ļ�¼��");

		return false;
	}
}
// ����ɾ��
function cfjgSc() {

	var ids = jQuery("#dataTable").getSeletIds();
	var rows = jQuery("#dataTable").getSeletRow();
	var blog = true;
	if (rows.length > 0) {
		var count = 0;
		for ( var i = 0; i < rows.length; i++) {
			var sjly = rows[i]["sjly"];
			if (sjly == "1") {
				count = count + 1;
			}
		}
		if (count > 0) {
			showAlertDivLayer("ѡ������ݼ�¼�������������ݣ�����ɾ����");
			return false;
		}
		showConfirmDivLayer("�ò�������ɾ��������Ϣ���Ƿ�ȷ������������", {
			"okFun" : function() {
				var url = "wjcf_cfjg.do?method=cfjgSc";
				jQuery.post(url, {
					values : ids.toString()
				}, function(data) {
					showAlertDivLayer(data["message"]);
					jQuery("#dataTable").reloadGrid();
				}, 'json');
			}
		});

	} else {
		showAlertDivLayer("�빴ѡ��Ҫɾ�������ݣ�");
		return false;
	}
}
// ��������
function cfsscl() {
	var rows = jQuery("#dataTable").getSeletRow();
	;
	if (rows.length == 1) {
		jQuery.ajaxSetup( {
			async : false
		});
		var count = 0;
		jQuery.post("wjcf_cfjg.do?method=cfsssjCx", {
			cfid : rows[0]["cfid"]
		}, function(result) {
			var json = eval(result);
			if (json[0].jcwh != "" && json[0].jcwh != null) {
				alertInfo("�ô�����" + jQuery("#text").val() + "�����ٽ������ߣ�");
				count = 1;
				return false;
			}
			if (json[0].zzwh != "" && json[0].zzwh != null) {
				alertInfo("�ô�������ֹ�����ٽ������ߣ�");
				count = 1;
				return false;
			}
			if (json[0].ssjg == '���Ĵ���') {
				jQuery("#cfggw").css("display","block");
			}
		});

		if (count == 0) {
			var url = "wjcf_cfjg.do?method=cfjgSs&&cfid=" + rows[0]["cfid"];
			showDialog("", 450, 280, url);
		}
	} else {
		alertInfo("�빴ѡһ����Ҫ�����ļ�¼��");
		return false;
	}

}

// ���ֽ��
function cfjccl() {
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length == 1) {
		jQuery.ajaxSetup( {
			async : false
		});
		count = 0;
		var cfid = rows[0]["cfid"];

		jQuery.post("wjcf_cfjg.do?method=cfsssjCx", {
			cfid : cfid
		}, function(result) {
			var json = eval(result);
			if (json[0].zzwh != "" && json[0].zzwh != null) {
				showAlertDivLayer("�ô�������ֹ�����ٽ���" + jQuery("#text").val() + "��");
				count = 1;
				return false;
			}
		});
		jQuery.ajaxSetup( {
			async : true
		});
		if (count == 0) {
			var url = "wjcf_cfjg.do?method=cfjgJc&cfid=" + cfid;
			showDialog("", 400, 240, url);
		}

	} else {

		showAlertDivLayer("�빴ѡһ����Ҫ�����ļ�¼��");

		return false;
	}
}
function cfzzcl() {
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length == 1) {
		var cfid = rows[0]["cfid"];
		jQuery.ajaxSetup( {
			async : false
		});
		var count = 0;
		jQuery.post("wjcf_cfjg.do?method=cfsssjCx", {
			cfid : cfid
		}, function(result) {
			var json = eval(result);
			if (json[0].jcwh != "" && json[0].jcwh != null) {
				showAlertDivLayer("�ô�����" + jQuery("#text").val() + "�����ٽ�����ֹ��");
				count = 1;
				return false;
			}
			if (!sfkzzFlag(json[0])) {
				count = 1;
				return false;
			}
		}, 'json');

		jQuery.ajaxSetup( {
			async : true
		});
		if (count == 0) {
			var url = "wjcf_cfjg.do?method=cfjgZz&cfid=" + cfid;
			showDialog("", 400, 240, url);
		}
	} else {

		showAlertDivLayer("�빴ѡһ����Ҫ�����ļ�¼��");

		return false;
	}
}

function sfkzzFlag(cfInfo) {
	var flag = true;
	jQuery.ajaxSetup( {
		async : false
	});
	jQuery.post("wjcf_cfjg.do?method=getKzzFlag", {
		cfsj : cfInfo.cfsj,
		zzsj : cfInfo.zzsj,
		cflbmc : cfInfo.cflbmc
	}, function(data) {
		if (data["message"] != null && data["message"] != "") {
			showAlertDivLayer(data["message"]);
			flag = false;
		}
	}, 'json');
	jQuery.ajaxSetup( {
		async : true
	});
	return flag;
}

/**
 * ����������
 * 
 * @return
 */
function getWord() {
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1) {
		showAlertDivLayer("��ѡ��һ����¼��");
		return false;
	}

	var jcwh = rows[0]["jcwh"];
	if (jcwh == null || jQuery.trim(jcwh) == '') {
		showAlertDivLayer("��ѡ��һ��" + jQuery("#text").val() + "���ּ�¼��");
		return false;
	}
	var cfid = rows[0]["cfid"];
	var url = "wjcf_cfjg.do?method=doPrint&&cfid=" + cfid;
	window.open(url);
}
/**
 * Υ�ʹ���֪ͨ���ӡ
 * 
 * @return
 */
function getWjCfWord() {
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1) {
		showAlertDivLayer("��ѡ��һ����¼��");
		return false;
	}
	var cfid = rows[0]["cfid"];
	var url = "wjcf_cfjg.do?method=doPrintWjcfWord&&cfid=" + cfid;
	window.open(url);

}
/**
 * �㽭��ҵְҵ����ѧԺ���Ի���ѧ�����־���������
 * 
 * @return
 */
function getCfjdWord() {
	var cfid="";
	var url=null;
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length ==0) {
		showAlertDivLayer("��ѡ����Ҫ��ӡ�ļ�¼��");
		return false;
	}else if(rows.length ==1){
	cfid = rows[0]["cfid"];
	var url = "wjcf_cfjg.do?method=doPrintCfjdWord&&cfid=" + cfid;
	}
	else{
		for(var i=0;i<rows.length;i++){
			if(i==rows.length-1){
				cfid +=rows[i]["cfid"];
			}else{
				cfid +=rows[i]["cfid"]+",";
			}
		}
		url = "wjcf_cfjg.do?method=doPrintCfjdWordZip&&cfid=" + cfid;
	}
	window.open(url);

}

function initCfwh(nd){
	var xn = jQuery("#xn").val();
	var xq = jQuery("#xq").val();
	var cfid= jQuery("#cfid").val();
	if(xn==null||xn==""){
		showAlertDivLayer("����ѡ��ѧ�꣡");
		return false;
	}
	if(xq==null||xq==""){
		showAlertDivLayer("����ѡ��ѧ�ڣ�");
		return false;
	}
	
	jQuery.post("wjcf_cfjg.do?method=initCfwh",{nd:nd,xn:xn,xq:xq,cfid:cfid},function(data){
			jQuery("#cfwh").val(data["message"]);
	},"json");
	
}

//��ʼ�����ֵ���ʱ�䣬��ô��ֵ���ʱ��Ĭ��ֵ������ʱ��+�������ޣ����û�д�����������ʾ���ֵ���ʱ�䣩
function defaultCfdqsj(){
	var cfsj = jQuery("#cfsj").val();
	var cflbdm = jQuery("#cflbdm").val();
	
	jQuery.post("wjcf_cfsh.do?method=defaultCfdqsj",{cfsj:cfsj,cflbdm:cflbdm},function(data){
		//jQuery("#cfdqsj").val(data["message"]);
		if(data["message"]!="hidden"){
			var html = "<th><font color=\"red\">*</font>���ֵ���ʱ��</th><td colspan=\"3\"><input name=\"cfdqsj\" id=\"cfdqsj\" "
			+" style=\"cursor:hand;\" onclick=\"return showCalendar('cfdqsj','y-mm-dd',false,'cfsj');\" value=\""+data["message"]+"\"/></td>";
			jQuery("#cffw_tr3").html(html);
		}else{
			jQuery("#cffw_tr3").html("");
		}
		
	},'json');
}

/**
 * �����ļ�����
 * ���־��������أ��ں�ְҵ����ѧԺ��ͬ���ͬ�ĺŶ�����¼�ϲ�Ϊ�����ļ���
 * �������������أ��ں�ְҵ����ѧԺ����Ҫȡ���������Ϣ��
 */
function cfwjDownload(url) {
	var rows = jQuery("#dataTable").getSeletRow();
	var cfids = jQuery("#dataTable").getSeletIds();

	if (rows.length == 0) {
		showAlertDivLayer("��ѡ����Ҫ���صļ�¼��");
		return false;
	}

	for (var i = 0; i < rows.length; i++) {
		if (rows[i]["fwjg"] != '���ֳ���') {
			showAlertDivLayer("��ѡ�񴦷ֳ����ļ�¼��");
			return false;
		}
	}
	
	var url = url + "&cfids=" + cfids;
	window.open(url);
}

/**
 * �㽭����ְҵѧԺ����ѧ������������
 * @return
 */
function printXscfspb(){
	/*ѡ��ļ�¼*/
	var rows = jQuery("#dataTable").getSeletRow();
	/*��ѡ��¼*/
	var ids = jQuery("#dataTable").getSeletIds();
	/*ѡ���¼�����������ȣ�*/
	var len = ids.length;
	if(0 == len ){/*ѡ��0����ʾ*/
		showAlertDivLayer("��ѡ����Ҫ���صļ�¼��");
		return false;
	}else if(1 == len){/*ѡ��һ����¼*/
		var url = "wjcf_cfjg.do?method=getXscfspbOne"+"&id="+rows[0]["cfid"];
		window.open(url);
	}else{
		var url = "wjcf_cfjg.do?method=getXscfspbZip&value="+ids;
		window.open(url);
	}
}