/*����ȫ�ֱ���*/
var flag = true;

/*������Ŀ������������(�������������ύ����) ytjrs:���ύ���� ,zrs:��������*/
var RSJSFS = jQuery("#rsjsfs").val();

jQuery(function() {
	RSJSFS = jQuery("#rsjsfs").val();
	var url = "xpjpy_xyrssz.do?method=xyrszCx&type=query";
	url += "&xmdm=" + jQuery("#xmdm").val();
	var rsfpfs = jQuery("#rsfpfs").val();
	url += "&rsfpfs=" + rsfpfs;
	url += "&czfs=" + jQuery("#czfs").val();
	gridSetting = {
			caption : "",
			pager : "pager",
			url : url,
			colList : [ 
			    {label : 'ѧԺ����',name : 'xydm',index : 'xydm',key : true,hidden : true}, 
				{label : 'guid',name : 'guid',index : 'guid',hidden : true}, 
				{label : 'guids',name : 'guids',index : 'guids',hidden : true,formatter : setGuids}, 
				{label : 'ѧԺ����s',name : 'xydms',index : 'xydms',hidden : true,formatter : setXydms}, 
				{label : jQuery("#xbmc").val()+'����',name : 'xymc',index : 'xymc',width : '35%'},
				{label : '���ύ����',name : 'ytjrs',index : 'ytjrs',width : '15%'}, 
				{label : '���ύ����',name : 'ytjrss',index : 'ytjrss',hidden : true,formatter : setYtjrss},
				{label : '��������',name : 'zrs',index : 'zrs',width : '15%'}, 
				{label : '��������',name : 'zrss',index : 'zrss',hidden : true,formatter : setZrss}, 
				{label : '��������(%)',name : 'fpbl',index : 'fpbl',width : '10%',formatter : setBl}, 
				{label : '����(%)',name : 'fpbls',index : 'fpbls',hidden : true,formatter : setBls}, 
				{label : '�����������',name : 'zd3',index : 'zd3',hidden : true},
				{label : '��������',name : 'jsrs',index : 'to_number(jsrs)',width : '15%',noSort : true,formatter : setJsrs}, 
				{label : '��������',name : 'zzme',index : 'to_number(zzme)',width : '10%',formatter : setZzrs} 
			],
			sortname : "xymc",
			rowNum : 10,
			sortorder : "asc"
		}
	jQuery("#dataTable").initGrid(gridSetting);
	
	setHelpTip();// ��ʾ��ʾ
	fpmeTip();// ����������ʾ
	setSpzt();//�������״̬��������
});

/* ����guids���������� */
function setGuids(cellValue, rowObject) {
	var guid = rowObject.guid;
	if (guid == null) {
		guid = "";
	}
	cellValue = "<input type='hidden' name='guids' value='" + guid + "' >";
	return cellValue;
}

/* ����ѧԺ���룬�������� */
function setXydms(cellValue, rowObject) {
	var xydm = rowObject.xydm;
	if (xydm == null) {
		xydm = "";
	}
	cellValue = "<input type='hidden' name='xydms' value='" + xydm + "' >";
	return cellValue;
}

/*���ύ����*/
function setYtjrss(cellValue, rowObject) {
	var ytjrs = eval("rowObject."+RSJSFS); 
	if (ytjrs == null) {
		ytjrs = "";
	}
	cellValue = "<input type='hidden' name='ytjrs' value='" + ytjrs + "' >";
	return cellValue;
}

/* �������������������� */
function setZrss(cellValue, rowObject) {
	var zrs = rowObject.zrs;
	if (zrs == null) {
		zrs = "";
	}
	cellValue = "<input type='hidden' name='zrss' value='" + zrs + "' >";
	return cellValue;
}

/* ����bl��Ϊ�˶�λ���¸�ֵ */
function setBl(cellValue, rowObject) {
	var fpbl = rowObject.fpbl;
	if (fpbl == null) {
		fpbl = "";
	}
	cellValue = "<input type='hidden' name='blHid' value='" + fpbl + "'>" + fpbl;
	return cellValue;
}

/* ����bls���������� */
function setBls(cellValue, rowObject) {
	var fpbl = rowObject.fpbl;
	if (fpbl == null) {
		fpbl = "";
	}
	cellValue = "<input type='hidden' name='fpbls' value='" + fpbl + "' >";
	return cellValue;
}

/* ���ü������� */
function setJsrs(cellValue, rowObject) {
	var bl = rowObject.fpbl;
	var ytjrs = eval("rowObject."+RSJSFS);
	var xxdm = jQuery("#xxdm").val();
	var cellValue = rowObject.zd3;
	if("10335"==xxdm){
		rowObject.jsrs=cellValue;
		cellValue = "<input type='hidden' name='jsrsHid' value='" + cellValue
				+ "'>" + cellValue;
	}else{
		cellValue = setJsrsJs(cellValue, bl, ytjrs,rowObject);
	}
	return cellValue;
}

/* ������������ */
function setZzrs(cellValue, rowObject) {
	if (cellValue == null) {
		cellValue = "";
	}
	cellValue = "<input type='text' id='srrs' style='width:60px' name='zzmes' maxlength='10' value='"
			+ cellValue + "' onblur='checkRssxAndJesx(this,\"" + cellValue + "\",\""+rowObject["fpbl"]+"\",\""+rowObject["jsrs"]+"\")'/>";
	return cellValue;
}

//ѧԺ����һ���������Ƚ�ѧ������
function checkRssxAndJesx(obj,zzrs,fpbl,jsrs){
	var message="";

		var url = "xpjpy_xyrssz.do?method=rsszCheckAjax";
		jQuery.ajaxSetup({async:false});
		jQuery.post(url, {
			xmdm:jQuery("#xmdm").val(),
			xmje:jQuery("#xmje").val()
		}, function(data) {
			var zzrs_new = obj.value;
			dataObj = data;
			message=checkParam(obj,zzrs,zzrs_new,fpbl,jsrs);
			}, 'json');
	
	if(""!=message){
		  showAlertDivLayer(message,{},{"clkFun":function(){
			  obj.focus();
			}});
		  return false;
	}
	jQuery.ajaxSetup({async:true});
}

function checkParam(obj,zzrs,zzrs_new,fpbl,jsrs){
	
	var rssx=0;//��������
	var sxje=0;//���޽��
	for ( var i = 0; i < dataObj.length; i++) {
		var res = dataObj[i];
		//��ѧ���ܽ��=�޸�ǰ��ѧ��ʵ���ܽ��-�޸�ǰ���޸Ľ����ܽ��+�޸ĺ����޸Ľ����ܽ��
		var sxje = res.jxsjze-(zzrs*jQuery("#xmje").val())+(zzrs_new*jQuery("#xmje").val());
		if(res.xmmc==jQuery("#xmmc").val()){
		   rssx=parseFloat(jsrs)+parseFloat(res.rssx);
		   if(rssx<zzrs_new){
			  message="����������������"+parseInt(rssx)+"��";
				return message;
		   }
		}
	 if(sxje>res.jxjze){
		   message="��ѧ���ܽ��"+sxje+"��������"+res.jxjze+"Ԫ,"+"���޸�����������";
			return message;
	   }
	}	   	   
}


/* ���� */
function update() {
	var blsFlag = false;
	jQuery("input[name=fpbls]").each(function(index) {
		if (jQuery(this).val() == null || jQuery(this).val() == "") {
			blsFlag = true;
			return false;
		}
	});
	
	var flag = false;
	if ((jQuery("input[name=zzmes]").length == 0)) {
		showAlert("û����Ҫ����ļ�¼��");
		return false;
	}
	jQuery("input[name=zzmes]").each(
			function(index) {
				var value = jQuery.trim(jQuery(this).val());
				if (value != "" && chkNumInput2(value)) {
					showAlert("��" + (index + 1) + "�м�¼����������[" + value
							+ "]��ʽ����������Ϊ���֣�");
					flag = true;
					return false;
				}
				var zrs = jQuery(this).parent().parent().find("[name=zrss]")
						.val();
				if (parseInt(value, 10) > parseInt(zrs, 10)) {
					showAlert("��" + (index + 1) + "�м�¼����������[" + value
							+ "]���ܴ���������[" + zrs + "]��");
					flag = true;
					return false;
				}
			});

	if (flag) {
		return false;
	}

	var url = "xpjpy_xyrssz.do?method=xmwhRsszXg&type=save";
	ajaxSubFormWithFun("xyrsszForm", url, function(data) {
		showAlert(data.message, {}, {
			"clkFun" : function(tag) {
				divLayerClose();
			}
		});
	});
	query();
}

function query() {
	var map = {};
	map["xmmc"] = jQuery("#xmmc").val();
	map["xmdm"] = jQuery("#xmdm").val();
	
	map["njq"] = jQuery("#nj").val();
	map["xydm"] = jQuery("#xy").val();
	map["zydm"] = jQuery("#zy").val();
	map["bjdm"] = jQuery("#bj").val();
	map["sfysz"] = jQuery("#sfysz").val();
	map["cpzmc"] = jQuery("#cpzmc").val();
	jQuery("#dataTable").reloadGrid(map);
	fpmeTip();
}

//����������ʾ��Ϣ
function fpmeTip() {
	var rsfpfs = jQuery("#rsfpfs").val();
	var url = "xpjpy_xyrssz.do?method=xmwhRsszYszrs";
	var sTip = "ע��";
	jQuery.post(url, {xmdm : jQuery("#xmdm").val()},
		function(data){
			var zme = data.zme;
			jQuery("#zme").val(zme);
			var yszrs = data.yszrs;
			if (zme == null || zme == "null" || zme == "") {
				sTip += "��ǰ��Ŀδ���������";
				sTip += "�ѷ�������<font color='red'>" + yszrs
						+ "</font>��";
			} else {
				sTip += "��ǰ��Ŀ����������Ϊ<font color='red'>" + zme
						+ "</font>�ˣ�";
				sTip += "�ѷ�������<font color='red'>" + yszrs
						+ "</font>�ˣ�";
				var syme = zme - yszrs;
				if (syme < 0) {
					sTip += "�ѳ���������<font color='red'>"
							+ (syme * (-1)) + "</font>��";
				} else {
					sTip += "ʣ��ɷ�������<font color='red'>" + syme
							+ "</font>��";
				}
			}
			jQuery("#fpmeTip").html(sTip);
		}, 'json');
}

//��ʾ��ʾ
function setHelpTip() {
	var tip = "";
	var rsfpfs = jQuery("#rsfpfs").val();
	tip = jQuery("#xbmc").val();
	jQuery("#helpTip").html(tip);
}

//�������״̬��������
function setSpzt(){
	var spzt = jQuery("#spzt").val();
	if(spzt == "true"){
		jQuery("#spztTip").css("display","");
	}
}
