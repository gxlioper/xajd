var gridSetting = {
		caption:"����Ա��ѵ��Ŀ����",
		pager:"pager",
		url:"szdw_fdypxxmsq.do?method=fdypxxmsqList&type=query",
		colList:[
		   {label:'�������',name:'sqid', index: 'sqid',width:'1%',key:true,hidden:true},
		   {label:'��Ŀ����',name:'xmmc', index: 'xmmc',width:'20%',formatter:xhLink},
		   {label:'��ѵ�ص�',name:'pxdd', index: 'pxdd',width:'20%'},
		   {label:'��ѵʱ��',name:'pxsj', index: 'pxsj',width:'20%'},
		   {label:'������',name:'fbr', index: 'fbr',width:'15%'},
		   {label:'����ʱ��',name:'sqsj', index: 'sqsj',width:'18%'},
		   {label:'���״̬',name:'shzt', index: 'shzt',width:'15%'},
		   {label:'��Ŀ����',name:'xmdm', index: 'xmdm',width:'1%',hidden:true},
		   {label:'��������',name:'splc', index: 'splc',width:'1%',hidden:true},
		   {label:'shztdm', name:'shztdm', index:'shztdm',hidden:true} 
		],
		sortname: "sqsj",
	 	sortorder: "desc"
	}
function xhLink(cellValue,row){
	return "<a href='javascript:void(0);' class='name' onClick='view(\""+row["xmdm"]+"\")'>"+cellValue+"</a>";
}
function add(){
	jQuery.post("szdw_fdypxxmsq.do?method=yzFdypxsq",{},function(data){
		var message = data["message"];
		if(message=="true"){
			showDialog("��ѵ��Ŀ����",700,505,"szdw_fdypxxmsq.do?method=fdypxxmSq");
		}else{
			alertInfo(message);
		}
	},'json');
	
}

function xzpxxm(){
	showDialog("��ѵ��Ŀѡ��",700,500,"szdw_fdypxxmwh.do?method=fdypxxmsqList");
}

function view(xmdm){
	showDialog("�鿴��ѵ��Ŀ",700,300,'szdw_fdypxxmwh.do?method=fdypxxmView&xmdm='+xmdm);
}

function save(method){
	if(yanzheng()){
		jQuery.post("szdw_fdypxxmsq.do?method=yzFdypxsq",{xmdm:jQuery("#xmdm").val()},function(data){
			if(data.message!="true"){
				alertInfo(data.message);
			}else{
				//��֤�ɹ�����ܽ��б���
				var url = "szdw_fdypxxmsq.do?method="+method+"&type=save";
				ajaxSubFormWithFun("demoForm",url,function(data){
					 showAlertDivLayer(data["message"],{},{"clkFun":function(){
							if (parent.window){
								refershParent();
								
							}
							//refreshForm("szdw_fdypxxmsq.do?method=fdypxxmsqList");
						}});
				});
			}
		},'json');
		
	}
}
function yanzheng(){
	var xmdm = jQuery("#xmdm").val();
	var sqly = jQuery("#sqly").val();
	if(xmdm=="" || xmdm == undefined){
		alertInfo("��ѡ����ѵ��Ŀ");
	}else if(sqly.length>=2000){
		alertInfo("�������ɲ��ܳ���2000��");
	}else{
		return true;
	}
	return false;
}
function searchRs(){
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
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
				//alert(ty);
				if(ty=="ok"){
					jQuery.post("szdw_fdypxxmsq.do?method=qxsq",{sqids:sqids},function(data){
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
		var url = 'szdw_fdypxxmsq.do?method=fdypxxmsqXg&sqid='+rows[0]["sqid"]+'&xmdm='+rows[0]["xmdm"];
		var title = "�޸ĸ���Ա��ѵ����";
		showDialog(title, 720, 505, url);
	}

}