
//���Ӽ����
function addConMoreUpdateTr(type,mkid){
	var qsh =jQuery("#qsh").val();
	if(null==qsh || ''==qsh){
		showAlert("����ѡ�����ң�");
		return false;
	}
	jQuery.ajaxSetup({async:false});
	jQuery.post("gyjc_ccjgcx.do?method=getJclxList", {xydm:jQuery("#xydm").val(),jjlx:type,js:jQuery("#js").val()}, function(data) {
		dataObj = data;
		if(data.length==0){
			showAlert("δ���ü�����ȷ�ϣ�");
			return false;
		}
		setJclx(mkid);
	}, 'json');
	jQuery.ajaxSetup({async:true});
}
//��ʼ�������
function setJclx(mkid){
	var jclxid = "";
	var html="";
	html += "<tr class='jcxmTr'>";
	html += "<td><select style='width:80%' class='jclxSel' onchange='changeJclx(this,\""+mkid+"\")'>";
	for ( var i = 0; i < dataObj.length; i++) {
		var o = dataObj[i];
		if(i==0){
			jclxid=o.dm;	
		}
		html += "<option value='" + o.dm + "'>" + o.mc
				+ "</option>";
	}
	html += "</select>";
	if(jQuery("#sfcc").length == 1){
		html +="<input type='hidden' name='mxid' value='"+jQuery("#"+mkid).val()+"' />"
	}
	html +="</td><td colspan='2'>"
	var pfbzHtml=getPfbz(jclxid);
	html+=pfbzHtml;
	html+="</td><td align='center'><a class='name' href='javascript:;' onclick='deleteTr(this)'>ɾ��</a></td></tr>";
	jQuery("#"+mkid+"Tbody").append(html);
}
//��������л�
function changeJclx(obj,mkid){
	jQuery.ajaxSetup({async:false});
	var jclx=obj.value;
	var pfhtml =getPfbz(jclx);
	jQuery(obj).parent().next().html(pfhtml);
	jQuery.ajaxSetup({async:true});
	
}
function getPfbz(jclxid){
	var html="";
	jQuery.post("gyjc_pfbz.do?method=getPfbzListAjax", {fjid:jclxid}, function(data) {
		html += "<select class ='pfidSel' style='width:80%' name='pfid'>";
			for ( var i = 0; i < data.length; i++) {
				var o = data[i];
				html += "<option value='" + o.dm + "'>" + o.mc
						+ "</option>";
			}
			html += "</select>";
	}, 'json');
	return html;
}
//ɾ�������
function deleteTr(obj){
	jQuery(obj).parents("tr").remove();
}

//���ظ���
function downloadFile(obj){
	var fid = jQuery(obj).parent().find("[name='fid']").eq(0).val();
	var url = "gyjc_jcjglr.do?method=downloadFile&fid="+fid
	window.open(url);
}

//ɾ��
function delFile(obj){
	jQuery(obj).parent().parent().remove();
}

function addFj(){
	var index = jQuery("[name='addfjtr']").length;
	var openerObj = jQuery("#tbody_fj");
	var html = "<tr name='addfjtr'>" +
					"<td width = '50%' colspan='2'>"+
					"<input type='file' onchange = 'checkFileType(this)'  name = 'fjid"+index+"' >"+
					"<input type='hidden'  name = 'index' value='"+index+"' >"+
			        "</td>"+
			        "<td width = '50%' colspan='2'>"+
			        "<a href='javascript:void(0)' onclick='delFile(this)'>ɾ��</a>" +
			        "</td>"+
		        "</tr>";
    jQuery(openerObj).append(html);
};

//�ļ����Ϳ���
function checkFileType(obj){
	var type = obj.value.substr(obj.value.lastIndexOf(".")+1);
	var types = ["jgeg","jpg","gif","png","bmp"];
	if (jQuery.inArray(type, types) == -1){
		/*����������ϴ�����,���input file��������д��������ie��chrome*/
		var file = jQuery(obj);
		file.after(file.clone().val("")); 
		file.remove(); 
		showAlert("ֻ�����ϴ�jpg|jpeg|gif|png|bmp���͵��ļ���");
		return false;
	}
}

