var api = frameElement.api, W = api.opener;
function browser(basefunction) {
	/*
	 * if($("rzksrq").value==""){ showAlertDivLayer("��ѡ����ְʱ�䣡"); return false; }
	 * eval(basefunction);
	 */
}
/*****************************������µ������ڼ��ݾɰ汾div����******************************/
/*
 * ԭdiv��������
 * 			tipsWindown("������ʾ","id:tempDiv","350","200","true","","true","id");
	�滻Ϊ	tipsWindownNew("������ʾ","id:tempDiv",450,260);
	id Ϊ tempDiv ��div �������ر��淽�� ���� save
	save�����ж�div�������������֤�����ڱ������������·��� �磺save_browser
	Ȼ�����div���÷���Ϊsave_browser
	�ڴ��·���������ԭ��֤(����ֱ��copy����)
	��ֱ��ͨ�� W�������ԭsave���� ���� W.save();
	
	�����ǰ�ԭdiv html ����copy����ҳ�� ���������֤Ҫ������������֤��Ȼ�����ԭҳ���ԭ����
	
	����Ƕ�㵯�� �� ���� W=getBase();����
	�������Ҫ���ݵĲ�������ʹ�� getParam(id)������ȡ��Ӧֵ
	setParam ���÷���ֵ(�������Զ����ݴ��ݹ����ļ�ֵ�����ã������Ҫ�����Լ��ο������ض�ֵ) �ο�browser.js
 * [982] �Ų�·
 * */
/**
 * ��Ԣ����-���ҳ�ά��-���ҳ�ά�������ҳ���������ȷ�Ϸ��䴰����ʽ
 * @author 982 �Ų�·
 * @return
 */
function szdwjr() {
	W = getBase();
	if ($("rzksrq").value == "") {
		showAlert("��ѡ����ְʱ�䣡");
		return false;
	}
	//����֤���ֵ���ݻ�ԭҳ��
	W.document.getElementById('rzksrq').value = jQuery("#rzksrq").val();
	W.jQuery("input[type=radio][name=czfs]:checked").val(jQuery("input[type=radio][name=czfs]:checked").val());
	W.document.getElementById('bz').value = jQuery("#bz").val();
	
	//�رյ�����
	iFClose();
	//����ԭ����
	W.glryfp_submit();
}

function backform() {
	var thyj = jQuery("#thyj").val();
	if(thyj==""){
		showAlert("����д�˻������");
		return false;
	}
	W = getBase();
	//����֤���ֵ���ݻ�ԭҳ��
	W.document.getElementById('thyj').value = jQuery("#thyj").val();
	//�رյ�����
	iFClose();
	//����ԭ����
	W.backForm();
}
/**
 *��Ԣ����-ס�޹���-��λ��ס���� ������ѧ����ס�������/ȡ������
 * @author 982 �Ų�·
 * @return
 */
function saveQscwfpxx_submit_browser() {
	var doType = W.doType_temp;
	
	var rzsj = $("rzsj").value;
	var rzyy = $("rzyy").value;
	if ("qxfp" == doType) {
		if (rzsj == "") {
			showAlert("��ѡ������ʱ�䣡");
			return false;
		}
		var tsyy = $("tsyy").value;
		if (tsyy == "") {
			showAlert("��ѡ������ԭ��");
			return false;
		}

		var xn=$("xn").value;
		if(xn==""){
			alertInfo("��ѡ��ѧ�꣡");
			return false;
		}
		var xq=$("xq").value;
		if(xq==""){
			alertInfo("��ѡ��ѧ�ڣ�");
			return false;
		}
		
		W.jQuery("#tsyy").val(tsyy);
		W.jQuery("#xn").val(xn);
		W.jQuery("#xq").val(xq);
		var bz=$("bz").value;
		W.jQuery("#bz").val(bz);
	} else {
		if (rzsj == "") {
			showAlert("��ѡ����סʱ�䣡");
			return false;
		}
	}
	W.jQuery("#rzyy").val(rzyy);
	W.jQuery("#rzsj").val(rzsj);
	// setParam(W);
	api.close();
	W.saveQscwfpxx_submit();
}
/**
 *��������(New)-��������-��Ŀ���� -��������-��������
 * @return
 */
function saveForm(){
	W = getBase();
	var index=0;
	var fpfs = jQuery.trim(jQuery("input:radio[name=fpfs]:checked")[index].value);
	if (fpfs == "bl") {// ������ʽ
		var blView = jQuery.trim(jQuery("input[name=blView]")[index].value);
		if (blView == "") {
			jQuery("span[name=blTip]").html("�����������ֵ");
			return false;
		}
		var reg = /^(([1-9]\d{0,2})|[0-9])([.]\d{1,2})?$/;
		if (!blView.match(reg) || blView > 100) {
			jQuery("span[name=blTip]").html("������0-100�����֣������λС��");
			return false;
		}
		W.jQuery("input[name=blView]")[index].value=blView;
	} else if (fpfs == "zme") {// ���������
		var zmeView = jQuery.trim(jQuery("input[name=zmeView]")[index].value);
		if (zmeView == "") {
			jQuery("span[name=zmeTip]").html("������������");
			return false;
		}
		var reg = /^\d*$/;
		if (!zmeView.match(reg) || zmeView < 0) {
			jQuery("span[name=zmeTip]").html("����������");
			return false;
		}
		W.jQuery("#zme").val(zmeView);
	}
	var rsfpfs = W.jQuery("#rsfpfs").val();
	if (rsfpfs == W.RSFPFS_XY) {// ѧԺ
		var rsfpnj = jQuery(jQuery("tr[name=njTr]")[0]).find("input:checkbox[name=nj]:checked").map(function() {
		  return jQuery(this).val();
		}).get().join(',');
		if (rsfpnj == "") {
			jQuery("span[name=njTip]").html("��ѡ����Ҫ���Ƶ��꼶");
			return false;
		}
		W.jQuery("#rsfpnj").val(rsfpnj);
	}
	W.jQuery("span[name$=Tip]").html("");
	W.jQuery("#fpfs").val(fpfs);
	W.saveForm();
	api.close();
}
//���䷽ʽѡ�񿪹�
/*��������(New)-��������-��Ŀ���� -��������-��������
 * ����ҳ��ʹ�����js����
 * ���ݾ������΢�� �����ȡtr����� 1�±�΢��Ϊ0
 * */
//���䷽ʽѡ�񿪹�
function setFpfs(obj){
	W = getBase();
	var index=0;
	jQuery(obj).attr("checked","checked");
	var fpfs = jQuery(obj).val();
	if (fpfs == "bl") {// ������ʽ
		jQuery("tr[name=blTr]").eq(index).show();
		jQuery("tr[name=zmeTr]").eq(index).hide();
	}else if (fpfs == "zme") {// ������
		jQuery("tr[name=zmeTr]").eq(index).show();
		jQuery("tr[name=blTr]").eq(index).hide();
		jQuery("input[name=zmeView]").eq(index).val(W.jQuery("#zme").val());
	}
}

//Υ�ʹ���-������ʽ��-����ά��
//�������ߵ�����
/**********************begin**********************/
function ssjgsave(){
	var sswh = $("sswh").value;//�����ĺ�
	var sssj = $("sssj").value;//����ʱ��
	var ssjg = $("ssjg").value;//���߽��
	
	var cflbdm = W.$("cflbdm").value;//����������
	if("block"==W.document.getElementById('cfggw').style.display){
		if(null==sssj||""==sssj||null==sswh||""==sswh||null==ssjg||""==ssjg||null==cflbdm||""==cflbdm){
			alertError("�뽫��*����Ŀ��д������");
			return false;
		}
	}else{
		if(null==sssj||""==sssj||null==sswh||""==sswh||null==ssjg||""==ssjg){
			alertError("�뽫��*����Ŀ��д������");
			return false;
		}
	}
	var id=jQuery("#content").val();
	callBack(W,id);
	//W.ssjgsave();
	//var pkValue=jQuery("[name=div_pkValue]:checked").val();
	//refreshForm('saveWjcfssjg.do?cfid='+pkValue);
}
function discfgg() {
	var ssjg = jQuery("#ssjg").val();
	if (ssjg =='���Ĵ���') {
		W.document.getElementById('3we').style.display = "block";
	} else {
		document.getElementById('cfggw').style.display = "none";
		document.getElementById('cflbdm').value = "";
	}
}
/***************************end**********************************/
/***************��Ԣ����-��Ԣ����-��Ԣ������Ϣ���*******************/
//�������
function gyjlPlsh(){
		var shzt = jQuery("#shzt").val();
		confirmInfo("ȷ��Ҫ����ѹ�ѡ�ļ�¼��?",function(tag){
			if(tag=="ok"){
				//��ǰҳ��������ظ�ԭҳ�档����ԭ����
				W.jQuery("#shzt").val(shzt);
				W.jQuery("#shyj").val(jQuery("#shyj").val());
//				var id=jQuery("#content").val();
//				callBack(W,id);
				W.gyjlPlsh();
			}else {
				return false;
			}
			Close();
		});
	}
/*******************************end*************************/
/***************��Ԣ����-��Ԣ����-��Ԣ������Ϣ����*******************/

//��������
function saveShzt(){
	var cljg=jQuery("#cljg").val();
	if (cljg == null || cljg == "") {
		alertInfo("��ѡ������!",function(){return false;});
		return false;
	} 
	if (jQuery("#dcqk").val()==null ||jQuery("#dcqk").val()=='') {
		alertInfo("����д���������",function(){return false;});
		return false;
	}
	confirmInfo("ȷ��Ҫ�����ѹ�ѡ�ļ�¼��?",function(tag){
		if(tag=="ok"){
			W.jQuery("#cljg").val(cljg);
			W.jQuery("#dcqk").val(jQuery("#dcqk").val());
			W.jQuery("#ylzd1").val(jQuery("#ylzd1").val());
			W.saveShzt();
		}else {
			return false;
		}
		Close();
	});
}
/*******************************end*************************/
/**********************8*/
 //��Ƭ�ϴ�
function uploadStuPic(){
	W=getBase();
	jQuery.ajaxSetup({async:false,dataType:'text'});
	var zgh = W.jQuery("#zgh").val();
	jQuery.ajaxFileUpload({
	  url:'szdw_teaInfo.do?method=uploadTeaPic&zgh='+zgh,//�������˳���
	  secureuri:false,
	  fileElementId:'teaPic',//input���ID
	  success:function(data,type){
		if (type=='success'){
			W.jQuery("#zhaopian").attr("src","");
			W.jQuery("#zhaopian").attr("src","/xgxt/teaPic.jsp?zgh="+zgh+"&tt"+new Date());
			Close();
		}
	  }
	});
	jQuery.ajaxSetup({async:true});
}
