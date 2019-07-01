var bzMsg = '请注明留校期间内，不在校时间'; // 备注
//初始化提示信息
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
//清空提示信息
function clearData(id, msg){
	var obj = jQuery("#" + id);	
	var v = jQuery.trim(obj.val());
	if (v == msg) {
		obj.val("");
	}
}
//选择床位
function selectCw(path, sqid){
	var xh = jQuery("#xh").val();
	if(xh===''){
		showAlert("请先选择学生！");
	}else{
		showDialog('请选择一个床位',800,500,'rcsw_jqlx.do?method=selectCwxx&sqid='+sqid+'&goto='+path+'&xh='+xh);
	}
}
function showCwxx(cwxx, sqid){
	var cwxxSetting = {
			caption:"已选择床位信息",
			multiselect:false,
			rowNum:1,
			url:"rcsw_jqlx.do?method=selectCwxx&type=query&sqid="+sqid+"&cwxx="+cwxx,
			colList:[
			         {label:'床位信息id',name:'cwxx', index: 'cwxx',key:true,hidden:true},
			         {label:'楼栋名称',name:'ldmc', index: 'ldmc'},
			         {label:'寝室号',name:'qsh', index: 'qsh',width:'6%'},
			         {label:'床位号',name:'cwh', index: 'cwh',width:'6%'},
			         {label:'床位性别',name:'qsxb', index: 'qsxb'},
			         {label:'所属年级',name:'nj', index: 'nj'},
			         {label:'所属'+jQuery("#xbmc").val(),name:'xymc', index: 'xymc'}
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
		var html = "<tr id='qzxyjzdw'><th><span class='red'>*</span>兼职单位</th><td colspan='3'><input type='text' id='lxdw' name='lxdw' style='width:500px;' maxlength='50' /></td></tr>"
		jQuery("#qzxy").append(html);
		jQuery("#qzxylxr").html("<span class='red'>*</span>兼职单位联系人");		
	}else{
		jQuery("#qzxyjzdw").remove();
	}
	if(lxyy == '7'){
		jQuery("#qzxylxr").html("<span class='red'>*</span>家庭联系人")
	}
	if(lxyy != '3' && lxyy != '7'){
		jQuery("#qzxylxr").html("<span class='red'>*</span>联系人");
	}
}