function searchRs() {
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
}

/**
 * �����Link
 * @return
 */
function jcxLink(cellValue, rowObject){
	var jcxmc = "";
	if(rowObject['wsjc'] == "1"){
		jcxmc +="������顢";
	}
	if(rowObject['aqjc'] == "2"){
		jcxmc +="��ȫ��顢";
	}
	if(rowObject['jljc'] == "3"){
		jcxmc +="���ɼ�顢";
	}
	return "<font color='blue'>"+jcxmc.substring(0,jcxmc.length-1)+"</font>";
}

/**
 * ����
 * @return
 */
function addpage(){
	url="gyjc_ccrcsz.do?method=add";
	showDialog("�����ճ�", 770, 550, url,{close:function(){
		jQuery("#dataTable").reloadGrid();
	}});
	
}

/**
 * �޸�
 * @return
 */
function update(){
	var rows = jQuery("#dataTable").getSeletRow();
	document.location.href="gyjc_ccrcsz.do?method=update&ccid="+rows[0]["ccid"];
}

function updatejc(){
    var rows = jQuery("#dataTable").getSeletRow();
    url="gyjc_wsjc.do?method=update&jcrq="+rows[0]["jcrq"]+"&lddm="+rows[0]["lddm"]+"&qsh="+rows[0]["qsh"];
    var title = "�޸ļ����";
    showDialog(title, 770, 552, url);
}

/**
 * �������ްٷֱ�
 * @param obj
 * @return
 */
function calBfbOver(obj){
	var inputBfb = parseFloat(jQuery(obj).val());
	var ztxbl = parseFloat(jQuery(obj).parent().find("[name='ztxbl']").val());
	var isUpate = jQuery("#guid").length == 1 ? true :false;
	if(isUpate){
		var bcjcbl = parseFloat(jQuery(obj).parent().find("[name='bcjcbl']").val());
		ztxbl = parseFloat(ztxbl)-parseFloat(bcjcbl);
	}
	var sxBfb =parseFloat((parseFloat(100) - parseFloat(ztxbl)));
	if(!isUpate && ztxbl == 100){
		sxBfb = 100;
	}
	if((sxBfb < parseFloat(inputBfb) && (ztxbl != parseFloat(100))) || inputBfb > 100){
		showAlert("����������ٷֱ�����"+sxBfb+"%",{},{"clkFun":function(){
				obj.focus();
		}});
	}else{
		return;
	}
}

/**
 * �������ճ�
 * @return
 */
function saveCcrc(){
	var ids = "jcrq";
	if(!checkNotNull(ids)){
		return showAlert("�뽫��<font class='red'>*</font>����Ŀ��д������");
	}
	var url = "gyjc_ccrcsz.do?method=saveCcRc";
	ajaxSubFormWithFun("CcrcForm", url, function(data) {
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

/**
 * ֻ�޸�����
 * @return
 */
function saveCcrcUpdate(){
	var url = "gyjc_ccrcsz.do?method=saveCcrcUpdate";
	ajaxSubFormWithFun("CcrcForm", url, function(data) {
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

/**
 * ɾ��
 * @return
 */
function del(){
	var ids = jQuery("#dataTable").getSeletIds();
	if (ids.length == 0){
		showAlertDivLayer("��ѡ����Ҫɾ���ļ�¼��");
	} else {
		showConfirmDivLayer("��ȷ��Ҫɾ��ѡ��ļ�¼��",{"okFun":function(){
			jQuery.post("gyjc_ccrcsz.do?method=delCcrc",{rcid:ids.toString()},function(data){
				showAlertDivLayer(data["message"]);
				jQuery("#dataTable").reloadGrid();
			},'json');
		}});
	}
}

//��������
function fpqs(){
	var ccid = jQuery("#ccid").val();
	var url = "gyjc_ccrcsz.do?method=fenpei&ccid="+ccid;
	showDialog("�����б�", 770, 550, url,{close:function(){
	}});
}

//��������
function fpccr(){
	var rows = jQuery("#dataTable").getSeletRow();
	var ccid = jQuery("#ccid").val();
	if (rows.length == 0){
		showAlertDivLayer("��ѡ����Ҫ��������ң�");
	}else{
	var lddms = new Array();
	var qshs = new Array();
	for(var i =0;i<rows.length;i++){
		lddms.push(rows[i]['lddm']);
		qshs.push(rows[i]['qsh']);
	}
	document.location.href = "xg_gyjc_ccry.do?qshs="+qshs+"&lddms="+lddms+"&ccid="+ccid;
	
	}
}

//����˲鿴
function ccrLink(cellValue, rowObject) {
	return "<a href='javascript:void(0);' class='name' onclick='View(\""
			+ rowObject['ccid'] + "\",\""+rowObject['zgh']+ "\");'>" + cellValue
			+ "</a>";
}

//���Ҳ鿴
function qsLink(cellValue, rowObject) {
	return "<a href='javascript:void(0);' class='name' onclick='ViewQs(\""
	+ rowObject['ccid'] + "\",\""+rowObject['zgh']+ "\");'>" + cellValue
	+ "</a>";
}

//������޸�
function View(ccid,zgh) {
	showDialog("ѡ������", 900, 450, "gyjc_ccrcsz.do?method=updateCcrList&ccid="+ccid+"&xgzgh="+zgh);
}
//��ѡ������ת
function ViewQs(ccid,zgh) {
	showDialog("ѡ������", 900, 450, "gyjc_ccrcsz.do?method=updateQsList&ccid="+ccid+"&zgh="+zgh);
}

/**
 * ������鿴
 * @param obj
 * @return
 */
function jcjgck(){
    var rows = jQuery("#dataTable").getSeletRow();
    if(rows.length != 1){
        return showAlertDivLayer("��ѡ��һ����¼��");
    }
    var url = 'gyjc_wsjc.do?method=ckWsjc&jcrq='+rows[0]["jcrq"]+"&lddm="+rows[0]["lddm"]+"&qsh="+rows[0]["qsh"];
    var title = "������鿴";
    showDialog(title, 770, 552, url);
}



function ccjgck(){
    var rows = jQuery("#dataTable").getSeletRow();
    if(rows.length != 1){
        return showAlertDivLayer("��ѡ��һ����¼��");
    }
    var url = 'gyjc_wscc.do?method=ckWscc&ccid='+rows[0]["ccid"]+"&lddm="+rows[0]["lddm"]+"&qsh="+rows[0]["qsh"];
    var title = "������鿴";
    showDialog(title, 770, 552, url);
}

function fhjcsd(){
	document.location.href = "xg_gyjc_ccrcsz.do";
}


/**
 * ҳǩѡ��
 * @param obj
 * @param shzt
 * @return
 */
function selectTab(obj, sffp) {
	jQuery("#sffp").val(sffp);
	if (sffp == "not") {
		jQuery("#li_fp").css("display", "");
		jQuery("#li_qx").css("display", "none");
		var map = getSuperSearch();
		map["sffp"]="not";
		gridSetting["params"] = map;
		jQuery("#dataTable").initGrid(gridSetting);
	} else {
		jQuery("#li_fp").css("display", "none");
		jQuery("#li_qx").css("display", "");
		var map = getSuperSearch();
		map["sffp"]="";
		gridSetting2["params"] = map;
		jQuery("#dataTable").initGrid(gridSetting2);
	}
	jQuery(".ha").removeClass("ha");
	jQuery(obj).parent().addClass("ha");
}

/**
 * ������Ա����
 * @return
 */
function saveFyfp(){
	var rows = jQuery("#dataTable").getSeletRow();
	if(rows.length == 0){
		return showAlertDivLayer("����ѡ����Ա��");
	}
	var zgh = rows[0]['zgh'];
	
	var para = {
				zgh:zgh,
				ccid:jQuery("#ccid").val()
				
				
				};
	jQuery.post("gyjc_ccrcsz.do?method=saveRyFp",para,function(data){
		 if(data["message"]=="����ɹ���"){
    		 showAlert(data["message"],{},{"clkFun":function(){
    			 if (parent.window){
    					var api = frameElement.api,W = api.opener;
    					W.jQuery("#dataTable").reloadGrid();
    					closeDialog();
    				}
				}});
    	 }else{
    		 showAlert(data["message"]);
    		}
		
	},'json');
}

//�����޸ĵķ�����ԱupdateFyfp
function updateFyfp(){
	var rows = jQuery("#dataTable").getSeletRow();
	if(rows.length == 0){
		return showAlertDivLayer("����ѡ����Ա��");
	}
	var zgh = rows[0]['zgh'];
	
	var para = {
				zgh:zgh,
				ccid:jQuery("#ccid").val(),
				xgzgh:jQuery("#xgzgh").val()
				};
	jQuery.post("gyjc_ccrcsz.do?method=updateRyFp",para,function(data){
		 if(data["message"]=="����ɹ���"){
    		 showAlert(data["message"],{},{"clkFun":function(){
    			 if (parent.window){
    					var api = frameElement.api,W = api.opener;
    					W.jQuery("#dataTable").reloadGrid();
    					closeDialog();
    				}
				}});
    	 }else{
    		 showAlert(data["message"]);
    		}
	},'json');
}



function selectTab(obj, sffp) {
	jQuery("#sffp").val(sffp);
	if (sffp == "not") {
		jQuery("#li_fp").css("display", "");
		jQuery("#li_qx").css("display", "none");
		var map = getSuperSearch();
		map["sffp"]="not";
	
		gridSetting["params"] = map;
		jQuery("#dataTable").initGrid(gridSetting);
	} else {
		jQuery("#li_fp").css("display", "none");
		jQuery("#li_qx").css("display", "");
		var map = getSuperSearch();
		map["sffp"]="";
	
		gridSetting2["params"] = map;
		jQuery("#dataTable").initGrid(gridSetting2);
	}
	jQuery(".ha").removeClass("ha");
	jQuery(obj).parent().addClass("ha");
}



function updateQs(){
	var rows = jQuery("#dataTable").getSeletRow();
	if(rows.length == 0){
		return showAlertDivLayer("��ѡ�����ң�");
	}
	var lddms = new Array();
	var qshs = new Array();
	for(var i =0;i<rows.length;i++){
		lddms.push(rows[i]['lddm']);
		qshs.push(rows[i]['qsh']);
	}
	var para = {
				zgh:jQuery("#zgh").val(),
				ccid:jQuery("#ccid").val(),
				lds:lddms,
				qss:qshs
				
				
				};
	jQuery.post("gyjc_ccrcsz.do?method=insertQs",para,function(data){
		 if(data["message"]=="����ɹ���"){
    		 showAlert(data["message"],{},{"clkFun":function(){
    			 if (parent.window){
    					var api = frameElement.api,W = api.opener;
    					W.jQuery("#dataTable").reloadGrid();
    					closeDialog();
    				}
				}});
    	 }else{
    		 showAlert(data["message"]);
    		}
	},'json');
}


/**
 * ȡ������
 * @return
 */
function cancelFp(){
	var rows = jQuery("#dataTable").getSeletRow();
	if(rows.length == 0){
		return showAlertDivLayer("����ѡ�����ң�");
	}
	var lddms = new Array();
	var qshs = new Array();
	for(var i =0;i<rows.length;i++){
		lddms.push(rows[i]['lddm']);
		qshs.push(rows[i]['qsh']);
	}
	var para = {
			ccid:jQuery("#ccid").val(),
			lds:lddms,
			qss:qshs
	};
	jQuery.post("gyjc_ccrcsz.do?method=cancelRyfp",para,function(data){
		showAlertDivLayer(data["message"]);
		 if (parent.window){
				var api = frameElement.api,W = api.opener;
				W.jQuery("#dataTable").reloadGrid();
			}
		jQuery("#dataTable").reloadGrid();
	},'json');
}

function addConMoreUpdateTr(flszid) {

    var trHtml = '';
    jQuery("#headTr").append(trHtml);
}



function saveWsjc() {


    var ztpjs = new Array();
    var grpjs = new Array();
    var cwhs = new Array();
    jQuery("select[name='ztpj']").each(function () {
        ztpjs.push(jQuery(this).val());

    });
    var nary = ztpjs.sort();
    for (var i = 0; i < ztpjs.length; i++) {

        if (nary[i] == nary[i + 1]) {

            // alert("�����ظ����ݣ�" + nary[i]);
            return showAlertDivLayer("���ظ��������������ۣ�");
        }
    }
        var cwh = null;
        jQuery("input[name='a']").each(function () {
            cwh = jQuery(this).val();


            var y = "grpj_" + cwh;

            jQuery("select[name=" + y + "]").each(function () {
                var gtpj = cwh + "-" + jQuery(this).val();
                grpjs.push(gtpj)
            });
            cwhs.push(cwh)

        });
        var narygr = grpjs.sort();

        for (var i = 0; i < grpjs.length; i++) {

            if (narygr[i] == narygr[i + 1]) {

                // alert("�����ظ����ݣ�" + narygr[i]);
                return showAlertDivLayer("���ظ��ĸ������ۣ�");

            }
        }


            var para = {
                jcrq: jQuery("#jcrq").val(),
                qsh: jQuery("#qsh").val(),
                lddm: jQuery("#lddm").val(),
                cwhs: cwhs,
                grpjs: grpjs,
                ztpjs: ztpjs

            };
            jQuery.post("gyjc_wsjc.do?method=updateWsjc", para, function (data) {
                if(data["message"]=="����ɹ���"){
                    showAlert(data["message"],{},{"clkFun":function(){
                        if (parent.window){
                            var api = frameElement.api,W = api.opener;
                            W.jQuery("#dataTable").reloadGrid();
                            closeDialog();
                        }
                    }});
                }else{
                    showAlert(data["message"]);
                }
            }, 'json');
        }


        function updatecc() {
            var rows = jQuery("#dataTable").getSeletRow();
            url = "gyjc_wscc.do?method=update&ccid=" + rows[0]["ccid"] + "&lddm=" + rows[0]["lddm"] + "&qsh=" + rows[0]["qsh"];
            var title = "�޸ĳ����";
            showDialog(title, 770, 552, url);
        }

        function saveWscc() {
            var ztpjs = new Array();
            var grpjs = new Array();
            var cwhs = new Array();
            jQuery("select[name='ztpj']").each(function () {
                ztpjs.push(jQuery(this).val());
            });
            var nary = ztpjs.sort();
            for (var i = 0; i < ztpjs.length; i++) {

                if (nary[i] == nary[i + 1]) {
                    // alert("�����ظ����ݣ�" + nary[i]);
                    return showAlertDivLayer("���ظ��������������ۣ�");
                }
            }
            var cwh = null;
            jQuery("input[name='a']").each(function () {
                cwh = jQuery(this).val();
                var y = "grpj_" + cwh;
                jQuery("select[name=" + y + "]").each(function () {
                    var gtpj = cwh + "-" + jQuery(this).val();
                    grpjs.push(gtpj);
                });
                cwhs.push(cwh);
            });
            var narygr = grpjs.sort();
            for (var i = 0; i < grpjs.length; i++) {
                if (narygr[i] == narygr[i + 1]) {
                    // alert("�����ظ����ݣ�" + narygr[i]);
                    return showAlertDivLayer("���ظ��ĸ������ۣ�");
                }
            }
            var para = {
                ccid: jQuery("#ccid").val(),
                qsh: jQuery("#qsh").val(),
                lddm: jQuery("#lddm").val(),
                cwhs: cwhs,
                grpjs: grpjs,
                ztpjs: ztpjs

            };
            jQuery.post("gyjc_wscc.do?method=updateWscc", para, function (data) {
                // showAlertDivLayer(data["message"]);
                if(data["message"]=="����ɹ���"){
                    showAlert(data["message"],{},{"clkFun":function(){
                        if (parent.window){
                            var api = frameElement.api,W = api.opener;
                            W.jQuery("#dataTable").reloadGrid();
                            closeDialog();
                        }
                    }});
                }else{
                    showAlert(data["message"]);
                }
            }, 'json');
        }

//dcglbh,�������ܱ��
var DCGLBH = "xg_gyjc_wsjc.do";

//�Զ��嵼�� ����
function exportConfig() {
    //DCCLBH�������ܱ��,ִ�е�������
    customExport(DCGLBH, exprotData);
}

//��������
function exprotData() {
    setSearchTj();//���ø߼���ѯ����
    var url = "gyjc_wsjc.do?method=exportData&dcglbh=" + DCGLBH;//dcglbh,�������ܱ��
    url = addSuperSearchParams(url);//���ø߼���ѯ����
    jQuery("form").eq(0).attr("action", url);
    jQuery("form").eq(0).submit();
}

var DCGLBHCC = "xg_gyjc_wscc.do";
//�Զ��嵼�� ����
function exportConfigCC() {
    //DCCLBH�������ܱ��,ִ�е�������
    customExport(DCGLBHCC, exprotDataCC);
}

//��������
function exprotDataCC() {
    setSearchTj();//���ø߼���ѯ����
    var url = "gyjc_wscc.do?method=exportData&dcglbh=" + DCGLBHCC;//dcglbh,�������ܱ��
    url = addSuperSearchParams(url);//���ø߼���ѯ����
    jQuery("form").eq(0).attr("action", url);
    jQuery("form").eq(0).submit();
}

//����
function importConfig(){
    toImportDataNew("IMPORT_XSGZQK_CGBB");
    return false;
}










