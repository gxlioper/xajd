var RSFPFS_BJ = "bj";// �༶
var RSFPFS_NJXY = "njxy";// �꼶ѧԺ
var RSFPFS_NJZY = "njzy";// �꼶רҵ
var RSFPFS_XY = "xy";// ѧԺ
var RSFPFS_ZY = "zy";// רҵ
var RSFPFS_XX = "xx";// ѧУ
var RSFPFS_CPZ = "cpz";// ������
var flag = true;

/*������Ŀ������������(�������������ύ����) ytjrs:���ύ���� ,zrs:��������*/
var RSJSFS = jQuery("#rsjsfs").val(); 

var gridSetting;
jQuery(function() {
	RSJSFS = jQuery("#rsjsfs").val();
	var url = "xpjpy_xmwh_rssz.do?method=xmwhRsszCx&type=query";
	url += "&xmdm=" + jQuery("#xmdm").val();
	var rsfpfs = jQuery("#rsfpfs").val();
	url += "&rsfpfs=" + rsfpfs;
	url += "&czfs=" + jQuery("#czfs").val();
	if (rsfpfs == RSFPFS_NJXY) {// �꼶+ѧԺ
		gridSetting = {
			caption : "",
			pager : "pager",
			url : url,
			colList : [ {label : 'ѧԺ����',name : 'xydm',index : 'xydm',key : true,hidden : true}, 
			            {label : 'guid',name : 'guid',index : 'guid',hidden : true}, 
			            {label : 'guids',name : 'guids',index : 'guids',hidden : true,formatter : setGuids}, 
			            {label : 'ѧԺ����s',name : 'xydms',index : 'xydms',hidden : true,formatter : setXydms}, 
			            {label : jQuery("#xbmc").val()+'����',name : 'xymc',index : 'xymc',width : '35%'}, 
			            {label : '�꼶',name : 'nj',index : 'nj',width : '15%'}, 
			            {label : '�꼶s',name : 'njs',index : 'njs',hidden : true,formatter : setNjs},
			            {label : '���ύ����',name : 'ytjrs',index : 'ytjrs',width : '8%'},
			            {label : '���ύ����',name : 'ytjrss',index : 'ytjrss',hidden : true,formatter : setYtjrss},
			            {label : '��������',name : 'zrs',index : 'zrs',width : '15%'}, 
			            {label : '��������',name : 'zrss',index : 'zrss',hidden : true,formatter : setZrss},
			            {label : '����(%)',name : 'fpbl',index : 'fpbl',width : '10%',formatter : setBl}, 
			            {label : '����(%)',name : 'fpbls',index : 'fpbls',hidden : true,formatter : setBls}, 
			            {label : '��������',name : 'jsrs',index : 'to_number(jsrs)',width : '15%',noSort : true,formatter : setJsrs}, 
			            {label : '��������',name : 'zzme',index : 'to_number(zzme)',width : '10%',formatter : setZzrs} 
			            ],
			sortname : "xymc,nj",
			rowNum : 10,
			sortorder : "asc"
		}
	}else if (rsfpfs == RSFPFS_XY) {// ѧԺ
		gridSetting = {
			caption : "",
			pager : "pager",
			url : url,
			colList : [ {label : 'ѧԺ����',name : 'xydm',index : 'xydm',key : true,hidden : true},
			            {label : 'guid',name : 'guid',index : 'guid',hidden : true}, 
			            {label : 'guids',name : 'guids',index : 'guids',hidden : true,formatter : setGuids}, 
			            {label : 'ѧԺ����s',name : 'xydms',index : 'xydms',hidden : true,formatter : setXydms},
			            {label : jQuery("#xbmc").val()+'����',name : 'xymc',index : 'xymc',width : '35%'},
			            {label : '���ύ����',name : 'ytjrs',index : 'ytjrs',width : '15%'}, 
			            {label : '���ύ����',name : 'ytjrss',index : 'ytjrss',hidden : true,formatter : setYtjrss},
			            {label : '��������',name : 'zrs',index : 'zrs',width : '15%'}, 
			            {label : '��������',name : 'zrss',index : '',hidden : true,formatter : setZrss},
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
	} else{
		alert("������[�޸�]������[�������䷽ʽ]��");
	}

	jQuery("#dataTable").initGrid(gridSetting);

	setHelpTip();// ��ʾ��ʾ
	fpmeTip();// ����������ʾ
	setSpzt();//�������״̬��������
	
});

/*��ʾ��ʾ*/
function setHelpTip() {
	var tip = "";
	var rsfpfs = jQuery("#rsfpfs").val();
	if (rsfpfs == RSFPFS_NJXY) {// �꼶+ѧԺ
		tip = "�꼶+"+jQuery("#xbmc").val();
	} else if (rsfpfs == RSFPFS_XY) {// ѧԺ
		tip = jQuery("#xbmc").val();
	} 
	jQuery("#helpTip").html(tip);
}

//����������ʾ��Ϣ
function fpmeTip() {
	var rsfpfs = jQuery("#rsfpfs").val();
	var url = "xpjpy_xmwh_rssz.do?method=xmwhRsszYszrs";
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

/*�������״̬��������*/
function setSpzt(){
	var spzt = jQuery("#spzt").val();
	if(spzt == "true"){
		jQuery("#spztTip").css("display","");
	}
}

/*����guids����������*/
function setGuids(cellValue, rowObject) {
	var guid = rowObject.guid;
	if (guid == null) {
		guid = "";
	}
	cellValue = "<input type='hidden' name='guids' value='" + guid + "' >";
	return cellValue;
}

/*�����꼶����������*/
function setNjs(cellValue, rowObject) {
	var nj = rowObject.nj;
	if (nj == null) {
		nj = "";
	}
	cellValue = "<input type='hidden' name='njs' value='" + nj + "' >";
	return cellValue;
}

/*����ѧԺ����������*/
function setYtjrss(cellValue, rowObject) {
	var ytjrs = eval("rowObject."+RSJSFS); 
	if (ytjrs == null) {
		ytjrs = "";
	}
	cellValue = "<input type='hidden' name='ytjrs' value='" + ytjrs + "' >";
	return cellValue;
}

/*��������������������*/
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

/*���-ѧԺ����һ���������Ƚ�ѧ�����ޣ����Ի���*/
function checkRssxAndJesx(obj,zzrs,fpbl,jsrs){
	var message="";
	if("10335"==jQuery("#xxdm").val()){
		var url = "xpjpy_xmwh_rssz.do?method=rsszCheckAjax";
		jQuery.ajaxSetup({async:false});
		jQuery.post(url, {
			xmdm:jQuery("#xmdm").val(),
			xmje:jQuery("#xmje").val()
		}, function(data) {
			var zzrs_new = obj.value;
			
			dataObj = data;
			message=checkParam(obj,zzrs,zzrs_new,fpbl,jsrs);
			}, 'json');
		}
	
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





/* �������� */
function blsz() {
	if ((jQuery("input[name=zzmes]").length == 0)) {
		showAlert("û����Ҫ���õļ�¼��");
		return false;
	}
	setRskznj();//��ΪѧԺʱ���������������꼶
	var title = "��������";
	var content = jQuery("#blDiv").html();
	var zme=jQuery("#zme").val();
	/*zemΪ���ڵ���div�е�ֵ��Ҫ���ݹ�ȥ*/
	var rsfpfs = jQuery("#rsfpfs").val();
	if (rsfpfs == RSFPFS_XY) {// ѧԺ
		tipsWindownNew("��������","id:blDiv",380,270,"zme:"+zme);
	}
}

//��ΪѧԺʱ���������������꼶
function setRskznj() {
	var rsfpfs = jQuery("#rsfpfs").val();
	jQuery("#njTipDiv").hide();
	if (rsfpfs == RSFPFS_XY) {// ѧԺ
		jQuery("tr[name=njTr]").show();
		jQuery("#njTipDiv").show();

		var rsfpnj = jQuery("#rsfpnj").val();
		var njHtml = "";
		var njList = eval(jQuery("#njList").val());
		for ( var i = 0; i < njList.length; i++) {
			if(i != 0 && i %4== 0){
				njHtml += "<br/>";
			}
			njHtml += "<label>";
			if(rsfpnj == "" || rsfpnj.indexOf(njList[i]) > -1){
				njHtml += "<input type='checkbox' name='nj' value='"+njList[i]+"' checked='checked'>"+njList[i]+"&nbsp;";
			}else{
				njHtml += "<input type='checkbox' name='nj' value='"+njList[i]+"'>"+njList[i]+"&nbsp;";
			}
			njHtml += "</label>";
		}
		njHtml += "<br/><font color='red'><span name='njTip'></span></font>";
		jQuery("td[name=njTd]").html(njHtml);
	}
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


/* ���ò�������룬�������� */
function setCpzdms(cellValue, rowObject) {
	var cpzdm = rowObject.cpzdm;
	if (cpzdm == null) {
		cpzdm = "";
	}
	cellValue = "<input type='hidden' name='cpzdms' value='" + cpzdm + "' >";
	return cellValue;
}

/* ����רҵ���룬�������� */
function setZydms(cellValue, rowObject) {
	var zydm = rowObject.zydm;
	if (zydm == null) {
		zydm = "";
	}
	cellValue = "<input type='hidden' name='zydms' value='" + zydm + "' >";
	return cellValue;
}

/* ���ð༶���룬�������� */
function setBjdms(cellValue, rowObject) {
	var bjdm = rowObject.bjdm;
	if (bjdm == null) {
		bjdm = "";
	}
	cellValue = "<input type='hidden' name='bjdms' value='" + bjdm + "' >";
	return cellValue;
}

/* ���ü�������dd */
function setJsrsJs(cellValue, bl, ytjrs,rowObject) {
	if (bl != null && bl != "" && ytjrs != null && ytjrs != "") {
		cellValue = parseInt(ytjrs, 10) * parseInt(bl * 100) / 10000;
	}
	if (cellValue == null) {
		cellValue = "";
	}
	rowObject.jsrs=cellValue;
	cellValue = "<input type='hidden' name='jsrsHid' value='" + cellValue
			+ "'>" + cellValue;
	
	return cellValue;
}





/* ѧУ�û������������� */
function setZzrsOfXx(cellValue, rowObject) {
	if (cellValue == null) {
		cellValue = "";
	}
	cellValue = "<input type='text' style='width:60px' name='zzmes' maxlength='10' value='"
			+ cellValue + "' />";
	return cellValue;
}


/* ����ѧУ���䷽ʽ */
function setXxfs(cellValue, rowObject) {
	return "ѧУ���䷽ʽ";
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

	var url = "xpj_xmwh_rssz.do?method=xmwhRsszXg&type=save";
	ajaxSubFormWithFun("form1", url, function(data) {
		showAlert(data.message, {}, {
			"clkFun" : function(tag) {
				divLayerClose();
			}
		});
	});
	query();
}


//���䷽ʽѡ�񿪹�
function setFpfs(obj){
	var index=0;
	jQuery(obj).attr("checked","checked");
	var fpfs = jQuery(obj).val();
	if (fpfs == "bl") {// ������ʽ
		jQuery("tr[name=blTr]").eq(index).show();
		jQuery("tr[name=zmeTr]").eq(index).hide();
	}else if (fpfs == "zme") {// ������
		jQuery("tr[name=zmeTr]").eq(index).show();
		jQuery("tr[name=blTr]").eq(index).hide();
		jQuery("input[name=zmeView]").eq(index).val(jQuery("#zme").val());
	}
}
/* �������ñ��� */
function saveForm() {
	var index=0;
	var blView = jQuery.trim(jQuery("input[name=blView]")[index].value);
	jQuery("#fpbl").val(blView);
	var flag = false;
	/*jQuery����ѭ��*/
	var result = false;
	var bmdm = "";
	var zzme = "";
	var rsfpfs = jQuery("#rsfpfs").val();
	var nj = "";

	var rows = jQuery("#dataTable").getSeletRow();
	var json = "{data:[";
	for ( var i = 0; i < rows.length; i++) {
		if (flag) {
			json += ",";
		} else {
			flag = true;
		}
		json += "{";
		json += "guid:'" + rows[i].guid + "'";
		nj = rows[i].nj;
		if (nj == undefined) {
			nj = "";
		}
		/*�꼶*/
		json += ",nj:'" + nj + "'";
		/*����ѡ�е�ѧԺ����*/
		bmdm = rows[i].xydm;
		
		json += ",bmdm:'" + bmdm + "'";
		/*�����������������*/
		zzme = Math.round(parseInt(eval("rows[i]."+RSJSFS), 10) * parseInt(blView * 100)
				/ 10000);
//		zzme = Matt.round(parseInt(eval("rows[i]."+RSJSFS), 10) * blView,5);
		json += ",zzme:'" + zzme + "'";
		json += "}";
	}
	json += "]}";

	jQuery("#json").val(json);
	divLayerClose();
	var url = "xpjpy_xmwh_rssz.do?method=xmwhRsszBlsz&type=save";
	ajaxSubFormWithFun("rsszForm", url, function(data) {
		query();
		jQuery(parent.window.document).find('#search_go').click();
	});
}

/*�������������� ����*/
/*�ȷ�������Ժ�����ʱ������*/
function lnme() {
	var xmdm = jQuery("#xmdm").val();
	var url = 'xpj_xmwh_rssz.do?method=xmwhRsszLnme';
	url += "&xmdm=" + xmdm;
	var title = "��������������";
	showDialog(title, 700, 500, url);
}