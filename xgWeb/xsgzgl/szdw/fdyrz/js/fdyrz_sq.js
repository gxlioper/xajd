//��ʼ����ѯ
var gridSetting = {
		caption:"����Ա��ְ�����б�",
		pager:"pager",
		url:"szdw_fdyrz_sq.do?method=gjcxWdsq&type=query",
		colList:[
		   {label:'ְ����',name:'zgh', index: 'zgh',width:'10%',hidden:true},//,formatter:xhLink
		   {label:'����ʱ��',name:'sqsj', index: 'sqsj',width:'18%'},
		   {label:'���״̬',name:'shzt', index: 'shzt',width:'15%'},
		   {label:'ר��ְ',name:'zjz', index: 'zjz',width:'7%'},
		   {label:'�������',name:'sqdbgs', index: 'sqdbgs',width:'7%'},
		   {label:'��������',name:'sqly', index: 'sqly',width:'60%'},
		   {label:'splc',name:'splc', index: 'splc',hidden:true},
		   {label:'����',name:'sqid', index: 'sqid',key:true,hidden:true},
		   {label:'shztdm',name:'shztdm',index:'shztdm',hidden:true}
		],
		sortname: "sqsj",
	 	sortorder: "desc"
};

function xhLink(cellValue,rowObject){
	return "<a href='javascript:void(0);' class='name' onClick='ckXsxxgl(\""+rowObject["xxgldm"]+"\",\""+cellValue+"\")'>"+cellValue+"</a>";
}
//����Ա��ְ����
function goFdyrzsq(){
	jQuery.post("szdw_fdyrz_sq.do?method=yzFdyrzsq",{},function(data){
		var message = data["message"];
		if(message=="true"){
			showDialog("�ҵ���ְ����",765,460,"szdw_fdyrz_sq.do?method=fdyrzsq");
		}else{
			alertInfo(message);
		}
	},'json');
}

//�޸�����
/*function update(){
	jQuery.post("szdw_fdyrz_sq.do?method=yzFdyrzsq",{},function(data){
		var message = data["message"];
		if(message=="true"){
			showDialog("�ҵ���ְ����",765,530,"szdw_fdyrz_sq.do?method=fdyrzsq");
		}else{
			alertInfo(message);
		}
	},'json');
	
}*/

//�޸�����
function update() {
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1) {
		showAlertDivLayer("��ѡ��һ����Ҫ�޸ĵļ�¼��");
	} else {
		var shzt = rows[0]["shzt"];
		if ("�����" == shzt){
			showAlertDivLayer("����Ϣ��¼��������У������޸ģ�");
			return false;
		}
		if ("ͨ��" == shzt){
			showAlertDivLayer("����Ϣ��¼����Ѿ�ͨ���������޸ģ�");
			return false;
		}
		if ("��ͨ��" == shzt){
			showAlertDivLayer("����Ϣ��¼����Ѿ���ͨ���������޸ģ�");
			return false;
		}
		var url = 'szdw_fdyrz_sq.do?method=fdyrzsqxg&sqid='+rows[0]["sqid"];
		var title = "�޸���ְ����";
		showDialog(title, 720, 470, url);
	}

}



var scrollHandler = function(){  myScroll = jQuery(window).scrollTop();  };
//ȡ������
function fdyrz_qxsq(bjdm){
	var xzbj_text =jQuery("#xzbj_text").val();
	var reg = new RegExp("," + bjdm,"g");
	xzbj_text = xzbj_text.replace(reg,"");
	jQuery("#xzbj_text").val(xzbj_text);
	jQuery("#xzbj").load("szdw_fdyrz_sq.do?method=fdyrzsqbj&bjdm="+bjdm+"&type=qx");
}
/*//��ʾ����ѡ��༶
function show_xzbj(){
	if(jQuery("#bjlist").is(":hidden")){
		jQuery("#bjlist").show();
		jQuery("#bj_flag").html("��");
	}else{
		jQuery("#bjlist").hide();
		jQuery("#bj_flag").html("��");
	}
}*/
//���뱣��
function save(obj){
	var zjz = jQuery("#zjz").val();
	var sqly = jQuery("#sqly").val();
	var splc = jQuery("#splc").val();
	var sqdbgs = jQuery("#sqdbgs").val();
	if(zjz==null || zjz ==""){
		alertInfo("��ѡ��ר��ְ");
	}else if(sqly==null || sqly ==""){
		alertInfo("����д�������ɣ�");
	}else if(sqdbgs==null || sqdbgs ==""){
		alertInfo("����������༶����");
	}else if(sqly.length>500){
		alertInfo("�������ɲ��ܶ���500��");
	}else if(zjz=="��ְ" && sqdbgs>4){
		alertInfo("��ְ���������ĸ��༶");
	}else{
		var url = "";
		if(obj == "submit"){
		  url="szdw_fdyrz_sq.do?method=fdyrzsq&type=submit";
		}else{
		  url="szdw_fdyrz_sq.do?method=fdyrzsq&type=save";
		}
		jQuery.ajaxSetup({
			 contentType:"application/x-www-form-urlencoded;charset=utf-8"
		});
		jQuery("#but_save").attr("disabled","true");
		jQuery.ajax({
			   type: "POST",
			   url: url,
			   data: { zjz:zjz,sqly:sqly,splc:splc,sqdbgs:sqdbgs },
			   dataType: "json",
			   success: function(data){
			     if(data.success =="false"){
			    	 jQuery("#but_save").attr("disabled",false);
			     }else{
			    	 //refreshForm("szdw_fdyrz_sq.do?method=gjcxWdsq");
			    	// iFClose();
			    	 showAlertDivLayer(data["message"],{},{"clkFun":function(){
							if (parent.window){
								refershParent();
							}
						}});
			    	
			     }
			   }
			});
	}
}
function searchRs(){
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
}
//����
function go_back(){
	refreshForm("szdw_fdyrz_sq.do?method=gjcxWdsq");
}

//�������鿴
function lcgz(){
	var rows = jQuery("#dataTable").getSeletRow();
	var shzt = rows[0]["shzt"];
	if (rows.length != 1){
		alertInfo("��ѡ��һ����¼��");
	} else {
		if ("δ�ύ" == shzt){
			showAlertDivLayer("�ü�¼Ϊδ�ύ״̬�������ύ��");
			return false;
		}
		showDialog("�������̸���",530,310,'comm_spl.do?method=lcgz&sqid='+rows[0]['sqid']+"&splc="+rows[0]['splc']);
	}
}
//ȡ������
function qx_sh(){
	var rows = jQuery("#dataTable").getSeletRow();
	if(rows.length>=1){
		var sqids = "";
		//��ȡѡ��������Ų��� ,ƴ��
		var check = false;
		jQuery(rows).each(function(i){
			sqids = sqids+","+rows[i]["sqid"];
			
			var shzt = rows[i]['shzt'];
			if(shzt!='�����'){
				alertInfo("���������ѽ����������̲��ܳ���");
				return false;
			}else{
				check = true;
			}
		});
		if(check){
			confirmInfo("��ȷ��Ҫȡ��"+rows.length +"�������¼��?",function(ty){
				if(ty=="ok"){
					sqids= sqids.replace(",", "");
					jQuery.post("szdw_fdyrz_sq.do?method=fdyrzqxsq",{sqids:sqids},function(data){
						if(data["success"]=="true"){
							alertInfo("ȡ������ɹ�");
						}else{
							alertInfo("ȡ������ʧ��");
						}
						jQuery("#dataTable").reloadGrid();
					},'json');
				}
			});
		}
	}else{
		alertInfo("��ѡ��һ����Ҫȡ���ļ�¼");
	}
}