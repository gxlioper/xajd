

// ������
function addXs() {
	var xmdm=jQuery("#xmdm").val();
	var jcxf=jQuery("#jcxf").val();
	var xhArr = new Array();
	jQuery.each(jQuery("#tbody_xmsqxx tr"),function(i,n){
			var xh= jQuery(n).find("td").eq(1).text();
			xhArr.push(xh);
	});
	var url = 'xmsbXmsbjg.do?method=getStu&xmdm=' + xmdm+'&xhArr='+xhArr+'&jcxf='+jcxf;
	showDialog('', 800, 500, url);
	return false;
}

function delXs() {
	var sjly="";
	var xhs="";
	var xh="";
	var checkbox = jQuery('input[type=checkbox]:checked[name!=th]');
	if (checkbox.length == 0) {
		showAlertDivLayer("��ѡ����Ҫɾ��ѧ����");
		return false;
	}
	for ( var i = 0; i < checkbox.length; i++) {
		sjly=jQuery(checkbox[i]).parents("tr:eq(0)").find("input[name=sjly]").val();
		xh=jQuery(checkbox[i]).parents("tr:eq(0)").find("td[name=xh]").html();
		if("1"==sjly){
			if(i!=0){
				xhs+=",";
			}
			xhs+=xh;
		}
	}
	if(""!=xhs){
		showAlertDivLayer("<font color='red'>["+xhs+"]</font>"+"Ϊ�������ݣ�����ɾ����");
		return false;
	}
	for ( var i = 0; i < checkbox.length; i++) {
	jQuery(checkbox[i]).parents("tr:eq(0)").remove();
	}
	jQuery('input[type=checkbox][name=th]').attr('checked', false);
}


function setSqxs(rows,dataObj) {

	var html = "";
	var jcxf=jQuery("#jcxf").val();
	
	var api = frameElement.api;
	var qqxsTrLen = api.get('parentDialog').jQuery("#tbody_xmsqxx tr").length;	
	for ( var int = 0; int < rows.length; int++) {
		var index=qqxsTrLen+int;
		html += "<tr><td><input type='checkbox' id='checkbox_" + int
				+ "'/></td>";
		html += "<td name='xh'>" + rows[int]["xh"] + "</td>";
		html += "<td name='xm'>" + rows[int]["xm"] + "</td><td name='bjmc'>" + rows[int]["bjmc"] + "</td>";
		html += "<td><select id ='ylzd2_" + index + "' name='ylzd2' onchange='changeSfqq("+index+")'>";
		html += "<option value='0'>��</option>" ;
		html += "<option value='1'>��</option>" ;
		html += "</select></td>";
		html += "<td><select id ='ylzd1_" + index
				+ "' name='ylzd1' onchange='changeXmjx(" + index + ")'>";
		html += "<option value=''></option>"
		for ( var i = 0; i < dataObj.length; i++) {
			var o = dataObj[i];
			html += "<option value='" + o.jgid + "'>" + o.jxmc
					+ "</option>";
		}
		html += "</select></td>";
		html += "<td><span id='ylzd3_"+index+">"+jcxf+"</span>";
		html += "value='"+jcxf+"'"+"</td>";
		jQuery("#tbody_xmsqxx").append(html);
	}
	var W;
	var api = frameElement.api;
	if (api) {
		if (api.get('childDialog')) {
			W = api.get('parentDialog')
		} else {
			W = api.opener;
		}
	} else if (parent.window) {
		W = parent.window;
	}
	W.sqxsZj(html);

	iFClose();
}
function query(){
	var xh=jQuery("#xh").val();
	var jgid=jQuery("#jgid").val();
	var url='xmsbXmsbjg.do?method=jxbf&jgid='+jgid+"&xh="+xh;
	window.location.href=url;
	
}

function sqxsZj(html){
	jQuery("#tbody_xmsqxx").append(html);	
	}
/**
 * �����л�
 * @param index
 * @return
 */
function changeXmjx(index) {
	var jcxf=jQuery("#jcxf").val();
	var jxid=jQuery("#ylzd1_"+index).val();
	jQuery("#ylzd3_"+index).val(jcxf);
	if(""==jxid||null==jxid){
		jQuery("#ylzd3_"+index).html(jcxf);
		return ;
	}
	jQuery.each(jQuery("input[name=jxid]"),function(i,n){
		if(jxid==jQuery(n).val()){
			var zxf=parseFloat(jcxf,10)+parseFloat(jQuery(n).parent().find("td").eq(-2).text(),10);
			jQuery("#ylzd3_"+index).html(zxf);
		}
});
}

/**
 * �Ƿ�ȱ���л�
 * @param index
 * @return
 */
function changeSfqq(index) {
	var sfqq=jQuery("#ylzd2_"+index).val();;
	var jcxf=jQuery("#jcxf").val();
	if("1"==sfqq){
		jQuery("#ylzd1_"+index).val("");
		jQuery("#ylzd1_"+index).attr("disabled","disabled");
		jQuery("#ylzd3_"+index).html(0);
	}else{
		jQuery("#ylzd1_"+index).attr("disabled",false);
		jQuery("#ylzd3_"+index).html(jcxf);
	}
}

//����䷢����
function saveJxbf() {
	//���û�а佱ѧ��������Ϣ����������
	var isHaveTableData = jQuery("#tbody_xmsqxx>tr").length;
	if(isHaveTableData == 0){
		showAlert("����дѧ����Ϣ��");
		return false;
	}
	var flg=true;
	var xhs = new Array();
	var sjlys = new Array();
	var ylzd1s = new Array();
	var ylzd2s = new Array();
	jQuery.each(jQuery("#tbody_xmsqxx tr"),function(i,n){
		var obj = {};
			var xh= jQuery(n).find("td").eq(1).text();
			var ylzd2 = jQuery(n).find("select[name=ylzd2] option:selected").val();//�Ƿ�ȱ��
			var ylzd1 = jQuery(n).find("select[name=ylzd1] option:selected").val(); //��ý���
			var sjly= jQuery(n).find("input[name=sjly]").val();
			if(null==ylzd1||""==ylzd1){
				ylzd1="no";
			}
			if(null==sjly||""==sjly){
				sjly="0";
			}
			xhs.push(xh);
			ylzd1s.push(ylzd1);
			ylzd2s.push(ylzd2);
			sjlys.push(sjly);
	});
	var url = "xmsqgl_xmjg.do?method=saveJxbf&xhs="+xhs.toString()+"&ylzd1s="+ylzd1s.toString()+"&ylzd2s="+ylzd2s.toString()+"&sjlys="+sjlys.toString();
	ajaxSubFormWithFun("XsXmJgForm", url, function(data) {
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



