jQuery(function() {
	onShow();
	changeJgzq();
});

function onShow() {
	jQuery("#splcOld").val(jQuery("#splc").val());

	jQuery("input:radio[name=sqkg]").change(function() {// ���뿪�ؿ���
				setSqkg(jQuery(this).val());
			});

	jQuery("input:radio[name=rskg]").change(function() {// �������ƿ��ؿ���
				setRskg(jQuery(this).val());
			});

	jQuery("select#splc").change(function() {// �������Ƽ��𣬼�ÿ��Ƽ��𣬸���������̽�����ʾ
		setRsJdView(jQuery(this).val());
			});
	jQuery("select#splc").change();

	jQuery("#rskzfwOld").val(jQuery("#rskzfw").val());//����ԭ�����������Ʒ�Χ
	
	//Ĭ�����õ�ǰѧ�꣬ѧ��
	var sqkgLength = jQuery("input:radio[name=sqkg]:checked").length;//���뿪��
	if(sqkgLength == 0){//���뿪��δ����
		jQuery("select#sqxn").val(jQuery("#currXn").val());
		jQuery("select#sqxq").val(jQuery("#currXq").val());
	}
}

/*
 * �������״̬�����ز�����
 * 
 */
function setSpzt(){
	var spzt = jQuery("#spzt").val();
	if(spzt == "true"){
		jQuery("#spztTip").css("display","");
		jQuery("table input:not(input:radio[name=sqkg],input:radio[name=xslb],#sqkssj,#sqjssj),select").attr(
				"disabled", "disabled");
		jQuery("#jgsqzq").attr("disabled",false);
	}
}

/*
 * ���뿪������
 */
function setSqkg(value) {
	if (value == 0) {
		jQuery("table select,input:not(input:radio[name=sqkg])").attr(
				"disabled", "disabled");
		if("14008" == jQuery("#xxdm").val()) {
			jQuery("table div select,input:radio[name=shkg]").attr("disabled",false);
			jQuery("table div select,input:text[name=shkssj]").attr("disabled",false);
			jQuery("table div select,input:text[name=shjssj]").attr("disabled",false);
		}
		
	} else {
		jQuery("table select,input:not(input:radio[name=sqkg])").attr(
				"disabled",false);
		setRskg(jQuery("input:radio[name=rskg]:checked").val());// �������ƿ���Ĭ��
		setSpzt();//�������״̬�����ز�����
		if("14008" == jQuery("#xxdm").val()) {
			jQuery("table div select,input:radio[name=shkg]").attr("disabled",false);
			jQuery("table div select,input:text[name=shkssj]").attr("disabled",false);
			jQuery("table div select,input:text[name=shjssj]").attr("disabled",false);
		}
	}
}

/*
 * �������ƿ�������
 */
function setRskg(value) {
	if (value == 0) {
		jQuery("#rskzjbTd input").add("select#rskzfw").attr("disabled", "disabled");
	} else {
		jQuery("#rskzjbTd input").add("select#rskzfw").attr("disabled",false);
	}
}

/*
 * ��ÿ��ƿ�������
 */
function setJdkg(value) {
	if (value == 0) {
		jQuery("#jdkzjbTd input").attr("disabled", "disabled");
	} else {
		jQuery("#jdkzjbTd input").attr("disabled",false);
	}
}


// �������Ƽ��𣬼�ÿ��Ƽ��𣬸���������̽�����ʾ
function setRsJdView(value) {
	var url = "xszz_xmwh.do?method=xmwhShfw";
	jQuery.post(url, {
		value : value
	}, function(data) {
		var radio1 = "";
		var radio2 = "";
		if(data != null){
			for ( var i = 0; i < data.length; i++) {
				var o = data[i];
				radio1 += "<label><input type='radio' name='rskzjbView' value='"
						+ o.spgwdm + "'/>";
				radio1 += o.spgwmc;
				radio1 += "</label>";
				if (i != data.length - 1) {
					radio1 += "<br/>";
				}
				
				radio2 += "<label><input type='radio' name='jekzfw' value='"
					+ o.spgwdm + "'/>";
				radio2 += o.spgwmc;
				radio2 += "</label>";
				if (i != data.length - 1) {
					radio2 += "<br/>";
				}
			}
		}
		jQuery("#rskzjbTd").html(radio1);
		jQuery("#jfkzjbTd").html(radio2);

		// �������Ƽ��𣬼�ÿ��Ƽ������ó�ֵ
			jQuery("input:radio[name=rskzjbView][value=" + jQuery("#rskzjb").val() + "]").attr("checked","checked");
			jQuery("input:radio[name=jekzfw][value=" + jQuery("#jekzfwOld").val() + "]").attr("checked","checked");
			setSqkg(jQuery("input:radio[name=sqkg]:checked").val());// ���뿪��Ĭ��
		}, 'json');
}

//�����������϶����ڣ���ѯ��������Ŀ�����ѵ���
function setKnsrddc(value,type) {
	var xn,xq;
	if(type == "xn"){
		xn = value;
		xq = jQuery("#knsbdxq").val();
	}else if(type == "xq"){
		xn = jQuery("#knsbdxn").val();
		xq = value;
	}else{
		xn = jQuery("#knsbdxn").val();
		xq = jQuery("#knsbdxq").val();
	}	
	var url = "xszz_xmwh.do?method=xmwhKnsrddc";
	jQuery.post(url, {
		xn : xn,
		xq : xq
	}, function(data) {
		var html = "";
		for ( var i = 0; i < data.length; i++) {
			var o = data[i];
			html += "<input type='checkbox' name='knsbddcView' value='"+o.dm+"'/>";
			if(o.mc != null){
				html += o.mc + " ";
			}
		}
		jQuery("#knsrddcTd").html(html);
				
		//������Ŀ�����ѵ��Σ�����ֵ
		var knsbddc = jQuery("#knsbddc").val();
		var arr = knsbddc.split(",");
		jQuery.each(arr,function(index,value){
			jQuery("input:checkbox[name=knsbddcView][value="+value+"]").attr("checked","checked");
		});

		setSqkg(jQuery("input:radio[name=sqkg]:checked").val());// ���뿪��Ĭ��
		}, 'json');
}

function saveForm() {
	var sqkg = jQuery("input:radio[name=sqkg]:checked").val();//���뿪��
	var splc = jQuery("select#splc").val();
	var rskzfw = jQuery("#rskzfw").val();
	var jgsqzq = jQuery("select#jgsqzq").val();
	// �������ؼ�������ֵ���Ա��ύ��
	var rskzjb = jQuery("input:radio[name=rskzjbView]:checked").val();
	var jekzfw = jQuery("input:radio[name=jekzfw]:checked");
	jQuery("#rskzjb").val(rskzjb);
	
	if (sqkg == undefined || splc == null || rskzfw == null || rskzfw == "" || rskzjb == null || rskzjb == "" || jekzfw.size() == 0|| jgsqzq == "") {
		showAlert("�뽫��*����Ŀ��д������");
		return false;
	}
	if("14008" == jQuery("#xxdm").val()) {
		var shkg = jQuery("input:radio[name=shkg]:checked").val();
		if(shkg == undefined) {
			showAlert("�뽫��*����Ŀ��д������");
			return false;
		}
	}
	if(sqkg == 1){//���뿪�ؿ���״̬����Ҫ��֤
		var sqkssj = jQuery("#sqkssj").val();
		var sqjssj = jQuery("#sqjssj").val();
		
//		2014.7.4 ȡ��ʱ��������
//		if (splc == null|| sqkssj == null|| sqjssj == null || 
//				splc == ""|| sqkssj == ""|| sqjssj == "") {
//			showAlert("�뽫��*����Ŀ��д������");
//			return false;
//		}
		
		if(sqkssj != "" && sqjssj != "" && sqkssj > sqjssj){
			showAlert("��ʼʱ�䲻�ܴ��ڽ���ʱ�䣡");
			return false;
		}
		var pdxn = jQuery("#pdxn").val();
		if(pdxn == null|| pdxn == ""){
			showAlert("��Ŀ����ѧ�겻��Ϊ�գ�");
			return false;
		}
		
		var sfkns = jQuery("input:radio[name=sfkns]:checked").val();//�Ƿ�����������
		if(sfkns == 1){//��ѡ��
			var knsbdxn = jQuery("select#knsbdxn").val();//�������϶�����-ѧ��
			if (knsbdxn == null || knsbdxn == "") {
				showAlert("����д[�������϶�����-ѧ��]��");
				return false;
			}
			var knsbdxq = jQuery("select#knsbdxq").val();//�������϶�����-ѧ��
			if (knsbdxq == null || knsbdxq == "") {
				showAlert("����д[�������϶�����-ѧ��]��");
				return false;
			}
			
			var rskgLeng = jQuery("input:radio[name=rskg]:checked").length;//�������ƿ���
			if(rskgLeng == 0){
				showAlert("��ѡ��[�������ƿ���]��");
				return false;
			}
			
			var jdkgLeng = jQuery("input:radio[name=jdkg]:checked").length;//��ÿ��ƿ���
			if(jdkgLeng == 0){
				showAlert("��ѡ��[��ÿ��ƿ���]��");
				return false;
			}
		}
		
		var rskg = jQuery("input:radio[name=rskg]:checked").val();//��������
		if(rskg == 1){//��ѡ��
			var rskzjbView = jQuery("input:radio[name=rskzjbView]:checked").length;//�������Ƽ���
			if(rskzjbView == 0){
				showAlert("��ѡ��[�������Ƽ���]��");
				return false;
			}
		}
	}
	
	//����������ѡ��ֵ�������Ա��ύ��̨
	var knsbddcView = "";
	var flag = false;
	jQuery("input:checkbox[name=knsbddcView]:checked").each(function(index){
		if(flag){
			knsbddcView += ",";
		}else{
			flag = true;
		}
		knsbddcView += jQuery(this).val();
	});
	jQuery("#knsbddc").val(knsbddcView);	
	
//ȡ�������ã��Ա��ύ
	jQuery("table select,input").attr(
			"disabled",false);
	
	//��ÿ��ؼ�������ֵ���Ա��ύ��
	var jdkzjb = jQuery("input:radio[name=jdkzjbView]:checked").val();
	jQuery("#jdkzjb").val("sq");
	//�������ؼ�������ֵ���Ա��ύ��
	var rskzjb = jQuery("input:radio[name=rskzjbView]:checked").val();
	jQuery("#rskzjb").val(rskzjb);
	
	var url = "xszz_xmwh.do?method=xmwhJbsz&type=save";
	ajaxSubFormWithFun("form1", url, function(data) {
		showAlert(data["message"],{},{"clkFun":  function(tag) {
			if (tag == "ok") {
				refershParent();
			}
		}});
	});


}

function changeJgzq(){
	
	if(""==jQuery("#pdxq").val()){
		jQuery("#jgsqzq option[value=0]").show();
	}else{
		if("0"==jQuery("#jgsqzq").val()){
			jQuery("#jgsqzq").val("1");
		}
		
		jQuery("#jgsqzq option[value=0]").hide();
	}
}