var bzMsg = '��ע����У�ڼ��ڣ�����Уʱ��'; // ��ע
//��ʼ����ʾ��Ϣ
function initData(id, msg){	
	jQuery("#" + id).focus( function() {
		var v = jQuery.trim(jQuery(this).val());
		if (v == msg) {
			jQuery(this).val("");
			jQuery(this).css("color", "");
		}
	});
	jQuery("#" + id).blur( function() {
		var v = jQuery.trim(jQuery(this).val());
		if (v == "") {
			jQuery(this).val(msg);
			jQuery(this).css("color", "#999");
		}
	});
	jQuery("#" + id).blur();
}
//�����ʾ��Ϣ
function clearData(id, msg){
	var obj = jQuery("#" + id);	
	var v = jQuery.trim(obj.val());
	if (v == msg) {
		obj.val("");
	}
}
//ѡ��λ
function selectCw(path, sqid){
	var xh = jQuery("#xh").val();
	if(xh===''){
		showAlert("����ѡ��ѧ����");
	}else{
		showDialog('��ѡ��һ����λ',800,500,'rcsw_jqlx.do?method=selectCwxx&sqid='+sqid+'&goto='+path+'&xh='+xh);
	}
}
function showCwxx(cwxx, sqid){
	var cwxxSetting = {
			caption:"��ѡ��λ��Ϣ",
			multiselect:false,
			rowNum:1,
			url:"rcsw_jqlx.do?method=selectCwxx&type=query&sqid="+sqid+"&cwxx="+cwxx,
			colList:[
			         {label:'��λ��Ϣid',name:'cwxx', index: 'cwxx',key:true,hidden:true},
			         {label:'¥������',name:'ldmc', index: 'ldmc'},
			         {label:'���Һ�',name:'qsh', index: 'qsh',width:'6%'},
			         {label:'��λ��',name:'cwh', index: 'cwh',width:'6%'},
			         {label:'��λ�Ա�',name:'qsxb', index: 'qsxb'},
			         {label:'�����꼶',name:'nj', index: 'nj'},
			         {label:'����'+jQuery("#xbmc").val(),name:'xymc', index: 'xymc'}
			         ],
			         sortname: "sfrz",
			         sortorder: "desc"
	}
	jQuery("#cwxxTable").initGrid(cwxxSetting);
	jQuery("#yxzcwxx").show();
	jQuery("#cwxx").val(cwxx);
}

function lxyyChange(obj){
	var lxyy = jQuery(obj).val();
	if(lxyy == '3'){
		var html = "<tr id='qzxyjzdw'><th><span class='red'>*</span>��ְ��λ</th><td colspan='3'><input type='text' id='lxdw' name='lxdw' style='width:500px;' maxlength='50' /></td></tr>"
		jQuery("#qzxy").append(html);
		jQuery("#qzxylxr").html("<span class='red'>*</span>��ְ��λ��ϵ��");		
	}else{
		jQuery("#qzxyjzdw").remove();
	}
	if(lxyy == '7'){
		jQuery("#qzxylxr").html("<span class='red'>*</span>��ͥ��ϵ��")
	}
	if(lxyy != '3' && lxyy != '7'){
		jQuery("#qzxylxr").html("<span class='red'>*</span>��ϵ��");
	}
}