// ��ѯ
function searchRs(){
	
	var url = "axcswpsqxs.do?method=wpsqList&type=query";
	jQuery.post(url, {
		xn:jQuery("#xn").val(),
		xmlb:jQuery("#xmlb").val(),
		xmmc:jQuery("#xmmc").val(),
		sqzt:jQuery("#sqzt").val()
	}, function(data) {
		dataObj = data;
			createHtml();
		}, 'json');
}
function createHtml() {
	var parTbody = jQuery("#sqTbody");
	jQuery("tr",parTbody).remove();
	var sHtml = "";
	var xmmc="";
	var sqly="";
	var sqtj="";
	var Btn_Left_Name = "";
	var Btn_Right_Name = "";
	for ( var i = 0; i < dataObj.length; i++) {
		var o = dataObj[i];
		xmmc=o.xmmc;
		if(i%2==0){
			sHtml+="<tr>";
		}
		if(o.xmmc.length>10){
			xmmc= o.xmmc.substring(0,8)+"...";
		}
		sHtml+="";
		sHtml+="<td style='height:160px;'><div class='resources' style='width:381px;height:176px;float:none;'><p class='pic' style='float:left;padding-left:10px;padding-top: 10px;'>";
		sHtml+="<img style='width:170px;height:148px;margin:0;float:left;padding-right: 2px;' id='axwptp' src='axcsWpsz.do?method=showPhoto&xmdm="+o.xmdm+"&type=view'></p>";
		sHtml+="<ul style='padding-left:70px;height:170px; margin-top:5px; padding-top:5px; float: left; width: 45%;'><li title="+o.xmmc+"><span>��Ʒ����:"+xmmc+"</span></li>";
		sHtml+="<li><span>��Ʒ���:"+o.xmlbmc+"</span></li>";
		
		if("ysq"==jQuery("#sqzt").val()){
			jQuery(".xnSea").show();
			sqly=o.sqly;
			if(null!=o.sqly&&o.sqly.length>7){
				sqly=o.sqly.substr(0, 6) + "������";
			}
			if("5"==o.shzt){
				Btn_Left_Name="����";
			}
			if("1"==o.shzt){
				Btn_Left_Name="��ͨ��";
			}
			if("2"==o.shzt){
				Btn_Left_Name="��ͨ��";
			}
			sHtml+="<li><span>����ʱ��:"+o.sqsj+"</span></li>";
			sHtml+="<li><span>���״̬:"+o.shztmc+"</span></li>";
			sHtml+="<li><span title='"+o.sqly+"'>��������:"+sqly+"</span></li>";
			sHtml+="<li><span><button type='button' onclick='ysqOperate(\""+o.sqid+"\",\""+o.splc+"\",\""+o.shzt+"\");return false;'>"+Btn_Left_Name+"</button>";
			sHtml+="<button type='button' onclick='sqxqck(\""+o.sqid+"\",\""+o.xh+"\");return false;'>�鿴����</button></span></li>";	
		}
		else{
			jQuery(".xnSea").hide();
			sqtj=o.sqtj;
			if(null!=o.sqtj&&o.sqtj.length>7){
				sqtj=o.sqtj.substr(0, 6) + "������";
			}
			
			if("0"==o.shzt){
				Btn_Left_Name="�ύ";
			}
			if("3"==o.shzt){
				Btn_Left_Name="�ύ";
			}
			if(""==o.shzt||null==o.shzt){
				Btn_Left_Name="����";
			}
			sHtml+="<li><span title='"+o.sqtj+"'>��������:"+sqtj+"</span></li>";
			sHtml+="<li><span>����ʱ��:"+o.sqsjfw+"</span></li>";
			
			sHtml+="<li style='width:100%'><span><button type='button' onclick='wsqOperate(\""+o.xmdm+"\",\""+o.shzt+"\",\""+o.isopen+"\",\""+o.sqid+"\");return false;'>"+Btn_Left_Name+"</button></span>";
			sHtml+="<span><button type='button' onclick='Wpck(\""+o.xmdm+"\");return false;'>�鿴</button></span>";
			
			if("0"==o.shzt){
			sHtml+="<span id='scBtn'><button type='button' onclick='del(\""+o.sqid+"\");return false;'>ɾ��</button></span>"	;
			}
			sHtml+="</li>";
			
		}
		
		sHtml+="</ul></div></td>";
		if(i%2==1){
			sHtml+="</tr>";	
		}
		parTbody.html(sHtml);
	}
}

function del(sqid) {
	
		showConfirmDivLayer("��ȷ��Ҫɾ��ѡ��ļ�¼��", {
			"okFun" : function() {
				jQuery.post("axcswpsqjs.do?method=delWpsq", {
					values : sqid
				},
						function(data) {
							var mes = "�ɹ�ɾ����<font color='green'>&nbsp;"
									+ data["num"] + "&nbsp;</font>������";
							showAlertDivLayer(mes);
							searchRs();
						}, 'json');
			}
		});
	
}
function wsqOperate(xmdm,shzt,isopen,sqid) {
	var flag=true;
	jQuery.ajaxSetup({async:false});
	jQuery.post("axcswpsqxs.do?method=checkSqTj&xmdm="+xmdm, {
		xh:jQuery("#xh").val(),
		xmdm:xmdm
	}, function(data) {
		dataObj = data;
			if(dataObj.length>0&&(dataObj[0].result=="false")){
				flag=false;
			}
		}, 'json');
	if(false==flag){
		showAlertDivLayer("������������������ȷ�Ϻ������룡");
		return false;
	}
	
	if ('3' != shzt && "false" == isopen) {
		showAlertDivLayer("��ǰ��Ʒ�ѹر����룬�������Ա��ϵ��");
		return false;
	}
	else if("0"==shzt){
		submitBusi(xmdm,shzt,sqid);
	}else{
	var url = "axcswpsqxs.do?method=wpsqxsZj&xmdm="+xmdm;
	var title = "��Ʒ����";
	showDialog(title, 700, 500, url);
	}
	jQuery.ajaxSetup({async:true});
}



/**
 * �л�tabҳ
 * @param obj
 * @param shzt
 * @return
 */
function selectTab(obj, sqzt) {
	jQuery("#sqzt").val(sqzt);
	jQuery(".ha").removeClass("ha");
	jQuery(obj).parent().addClass("ha");
	searchRs();
}

function saveWpsq(type) {
	var xh = jQuery("#xh").val();
	if (jQuery("#sqly").val() == "" || xh == "") {
		showAlert("�뽫��������д������");
		return false;
	}
	
	if (jQuery("#xmdm").val() == "" || null == jQuery("#xmdm").val()) {
		showAlert("��ѡ��������Ʒ��");
		return false;
	}
	if (jQuery("#sqly").val().length>500) {
		showAlert("���������������500�֣�");
		return false;
	}
	var url = "axcswpsqxs.do?method=saveWpsq&type=" + type;
	ajaxSubFormWithFun("WpsqForm", url, function(data) {
		showAlert(data["message"], {}, {
			"clkFun" : function() {
				if (parent.window) {
					refershParent();
				}
			}
		});
	});
}
function update() {
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1) {
		showAlertDivLayer("��ѡ��һ����Ҫ�޸ĵļ�¼��");
	} else {

		var shzt = rows[0]["shzt"];

		if ("0" != shzt&&"3" != shzt) {
			showAlertDivLayer("ֻ��δ�ύ�����˻صļ�¼�����޸ģ�");
			return false;
		}

		var url = 'axcswpsqxs.do?method=wpsqUpdate&sqid=' + rows[0]["sqid"]
				+ '&xh=' + rows[0]["xh"];
		var title = "��Ʒ�����޸�";
		showDialog(title, 800, 600, url);
	}

}

function Wpck(xmdm){
	showDialog("��Ʒ����鿴",600,300,"axcsWpsz.do?method=ckWp&xmdm="+xmdm);
}

function sqxqck(sqid,xh){
	showDialog("���ĳ��������ѯ", 700, 480, "axcswpsqjs.do?method=wpsqView&sqid="
			+ sqid + "&xh=" + xh);
	
}

// �ύ
function submitBusi(xmdm,shzt,sqid) {

	var ids = jQuery("#dataTable").getSeletIds();
	var rows = jQuery("#dataTable").getSeletRow();

	if (shzt!= "0" && shzt != "3") {
		showAlertDivLayer("��ѡ��δ�ύ�������˻صļ�¼��");
		return false;
	}
	 else {
		showConfirmDivLayer("��ȷ��Ҫ�ύѡ��ļ�¼��", {
			"okFun" : function() {
				jQuery.post("axcswpsqxs.do?method=saveUpdateWpsq&type=tj", {
					values : sqid,
					xmdm : xmdm
				}, function(data) {
					showAlertDivLayer(data["message"]);
					searchRs();
				}, 'json');
			}
		});
	}

}

function ysqOperate(sqid,splc,shzt) {
		if("1"==shzt){
			showAlertDivLayer("����Ʒ������ͨ����");
			return false;
		}
		if("2"==shzt){
			showAlertDivLayer("����Ʒ����δͨ����");
			return false;
		}
		if("5"==shzt){
		showConfirmDivLayer("��ȷ��Ҫ����ѡ��ļ�¼��", {
			"okFun" : function() {
				jQuery.post("axcswpsqxs.do?method=cancelWpsq", {
					values : sqid,
					splcid : splc
				}, function(data) {
					showAlertDivLayer(data["message"]);
					searchRs();
				}, 'json');
			}
		});
		}
	}



