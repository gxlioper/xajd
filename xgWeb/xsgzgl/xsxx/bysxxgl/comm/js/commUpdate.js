jQuery(function() { 
	if ("40030" == jQuery("#xxdm").val()) {
		onChange();
		if("isStu"==jQuery("#isStu").val()){
		jQuery("#qtyy").attr("readonly","readonly");
		jQuery("#qtyy").attr("title","�ò����ɰ�������д");
		}
	}else if ("14073" == jQuery("#xxdm").val()) {
		onChange_14073();
	}else if ("11458" == jQuery("#xxdm").val()) {
		if("isStu"==jQuery("#isStu").val()){
			jQuery("#byxx").parents("tr").hide();
		}
		jQuery("#zsjj").css("margin-top", "5px");
		var helpMsg = '��������������������򡢺ӱ����������Ϻ������ա��㽭��������ɽ�����㶫�ͺ��ϵ�11��ʡ�У�\n';
		helpMsg += '�в���������ɽ�������֡������������ա����������ϡ����������ϵ�8ʡ��\n';
		helpMsg += '���������������졢�Ĵ������ݡ����ϡ����ء����������ࡢ�ຣ�����ġ��½������������ɹŵ�12��ʡ����������';
		var helpHtml = '<div class="tab_cur" style="display: inline;background-image: none;" title="'+helpMsg+'">';
		helpHtml += '<p class="help" style="margin-right:50px;">';
		helpHtml += '<a href="#" onclick="return false;" onmousedown ="showHelpDiv();" >����</a>';
		helpHtml += '</p>';
		helpHtml += '</div>';
		jQuery("#zsjj").parent().append(helpHtml);
	}
	xsGkPic();

});
//�������ù���ְҵѧԺ
function onChange_14073() {
	jQuery("#sfzsb").change(function() {
		if ("�˳�����" == jQuery(this).val()) {
			jQuery("#sfzfx").parents("tr").show();
		}else{
			jQuery("#sfzfx").val("");
			jQuery("#sfzfx").parents("tr").hide();
		}
	});
	jQuery("#sfzsb").change();
}
// ������óѧУѧ������Ͷ������ѵ���Ի�
function onChange() {
	if ("��" == jQuery("#zd1").val()) {
		jQuery("#zd3").parents("tr").show();
		jQuery("#zd2").parents("tr").show();
	} else {
		jQuery("#zd3").parents("tr").hide();
		jQuery("#zd2").parents("tr").hide();
	}
	if ("��" == jQuery("#sftb").val()) {
		jQuery("#tbsj").parents("tr").hide();
		jQuery("#sfyqrzs").parents("tr").show();
	} else {
		jQuery("#tbsj").parents("tr").show();
		jQuery("#sfyqrzs").parents("tr").hide();
	}
	jQuery("#sftb").change(function() {
		if ("��" == jQuery("#sftb").val()) {
			jQuery("#tbsj").val("");
			jQuery("#bxxz").val("");
			jQuery("#tbsj").parents("tr").hide();
			jQuery("#sfyqrzs").parents("tr").show();
		} else {
			jQuery("#qtyy").val("");
			jQuery("#sfyqrzs").val("");
			jQuery("#tbsj").val("yyyy-mm-dd��yyyy-mm-dd");
			jQuery("#tbsj").css("color", "#7D7D7D");
			jQuery("#tbsj").parents("tr").show();
			jQuery("#sfyqrzs").parents("tr").hide();
		}
	});
	jQuery("#zd1").change(function() {
		if ("��" == jQuery("#zd1").val()) {
			jQuery("#zd3").val("yyyy-mm-dd��yyyy-mm-dd");
			jQuery("#zd3").css("color", "#7D7D7D");
			jQuery("#zd3").parents("tr").show();
			jQuery("#zd2").parents("tr").show();
			
		} else {
			jQuery("#zd3").val("");
			jQuery("#zd2").val("");
			jQuery("#zd3").parents("tr").hide();
			jQuery("#zd2").parents("tr").hide();
		}
	});
	jQuery("#tbsj").focus(function() {
		if ("yyyy-mm-dd��yyyy-mm-dd" == jQuery("#tbsj").val()) {
			jQuery("#tbsj").val("");
			jQuery("#tbsj").css("color", "");
		}
	});
	jQuery("#tbsj").blur(function() {
		if ("" == jQuery("#tbsj").val()) {
			jQuery("#tbsj").val("yyyy-mm-dd��yyyy-mm-dd");
			jQuery("#tbsj").css("color", "#7D7D7D");
		}
	});
	if ("" == jQuery("#tbsj").val()) {
		jQuery("#tbsj").val("yyyy-mm-dd��yyyy-mm-dd");
		jQuery("#tbsj").css("color", "#7D7D7D");
	}
	jQuery("#zd3").focus(function() {
		if ("yyyy-mm-dd��yyyy-mm-dd" == jQuery("#zd3").val()) {
			jQuery("#zd3").val("");
			jQuery("#zd3").css("color", "");
		}
	});
	jQuery("#zd3").blur(function() {
		if ("" == jQuery("#zd3").val()) {
			jQuery("#zd3").val("yyyy-mm-dd��yyyy-mm-dd");
			jQuery("#zd3").css("color", "#7D7D7D");
		}
	});
	if ("" == jQuery("#zd3").val()) {
		jQuery("#zd3").val("yyyy-mm-dd��yyyy-mm-dd");
		jQuery("#zd3").css("color", "#7D7D7D");
	}

}
// ����ʦ���߿���Ƭ���Ի�
function xsGkPic() {
	if ("10511" != jQuery("#xxdm").val()) {
		jQuery("#addGkPic").css("display", "none");
		jQuery("#stuGkImg").css("display", "none");
		jQuery("#gkzpscbtn").css("display", "none");
	}
	if("10511" == jQuery("#xxdm").val()&&"isStu"==jQuery("#isStu").val()){
		jQuery("#gkzpscbtn").css("display", "none");
	}
}
//������ó���Ի�Ͷ������ѵ�ֶ�ֵ����
function initParam(){
	if ("40030" == jQuery("#xxdm").val()) {
		if ("yyyy-mm-dd��yyyy-mm-dd" == jQuery("#tbsj").val()) {
			jQuery("#tbsj").val("");

		}
		if ("yyyy-mm-dd��yyyy-mm-dd" == jQuery("#zd3").val()) {
			jQuery("#zd3").val("");
		}
	}
	
}