var gridSetting = {
		caption:"ѧ����λ�����б�",
		pager:"pager",
		url:"xsgwshnew_sh.do?method=getXsgwShList&type=query",
		colList:[
		   {label:'',name:'sqbh', index: 'sqbh',width:'10%',key:true,hidden:true},
		   {label:'ѧ��',name:'xh', index: 'xh',width:'12%',formatter:xhLink},
		   {label:'����',name:'xm', index: 'xm',width:'8%'},
		   {label:'�Ա�',name:'xb', index: 'xb',width:'7%'},
		   {label:'����ʱ��',name:'sqsj', index: 'sqsj',width:'18%'},
		   {label:'���˵�λ',name:'yrdwmc', index: 'yrdwmc',width:'18%'},
		   {label:'��λ����',name:'gwmc', index: 'gwmc',width:'15%'},
		   {label:'��λ����',name:'gwdm', index: 'gwdm',width:'5%',hidden:true},
		   {label:'��λ����',name:'gwxzmc', index: 'gwxzmc',width:'12%'},
		   {label:'����״̬',name:'shzt', index: 'shzt',width:'18%'},
		   {label:'gwid',name:'gwid', index: 'gwid',hidden:true},
		   {label:'shid',name:'shid', index: 'shid',hidden:true},
		   {label:'��������',name:'splc', index: 'splc',hidden:true}
		],
		params:{shlx:"dsh"},
		sortname: "sqsj",
	 	sortorder: "desc"
	};
	var gridSetting2 = {
		caption:"ѧ����λ�����б�",
		pager:"pager",
		url:"xsgwshnew_sh.do?method=getXsgwShList&type=query",
		colList:[
		   {label:'',name:'sqbh', index: 'sqbh',width:'10%',key:true,hidden:true},
		   {label:'ѧ��',name:'xh', index: 'xh',width:'12%',formatter:xhLink},
		   {label:'����',name:'xm', index: 'xm',width:'8%'},
		   {label:'����ʱ��',name:'sqsj', index: 'sqsj',width:'18%'},
		   {label:'���˵�λ',name:'yrdwmc', index: 'yrdwmc',width:'18%'},
		   {label:'��λ����',name:'gwmc', index: 'gwmc',width:'12%'},
		   {label:'��λ����',name:'gwdm', index: 'gwdm',width:'5%',hidden:true},
		   {label:'��λ����',name:'gwxzmc', index: 'gwxzmc',width:'12%'},
		   {label:'���ʱ��',name:'shsj', index: 'shsj',width:'18%'},
		   {label:'����״̬',name:'shzt', index: 'shzt',width:'18%'},
		   {label:'shid',name:'shid', index: 'shid',hidden:true},
		   {label:'gwid',name:'gwid', index: 'gwid',hidden:true},
		   {label:'��������',name:'splc', index: 'splc',hidden:true}
		  
		],
		params:{shlx:"ysh"},
		sortname: "shsj",
	 	sortorder: "desc"
	};
	
function query(obj,shlx){
	jQuery("#comp_title li").removeClass();
	jQuery(obj).parent().attr("class","ha");
	jQuery("#shlx").val(shlx);
	if(shlx=='ysh'){
		jQuery("#btn_sh").hide();
		jQuery("#btn_qxsh").show();
		jQuery("#dataTable").initGrid(gridSetting2);
	}else{
		jQuery("#btn_sh").show();
		jQuery("#btn_qxsh").hide();
		jQuery("#dataTable").initGrid(gridSetting);
	}
	searchRs();
}

function xhLink(cellValue,row){
	return "<a href='javascript:void(0);' class='name' onClick='seeInfo(\""+row["sqbh"]+"\",\""+row["xh"]+"\")'>"+cellValue+"</a>";
}

//�����Ϣ�鿴
function seeInfo(sqbh,xh){
	if(sqbh==""){
		alertInfo("����ѡ��һ��Ҫ�鿴�ĸ�λ���룡");
	} else {
		showDialog("ѧ����λ���",765,500,"xsgwshnew_sh.do?method=xsgwSh&type=ck&xh="+xh+"&sqbh="+sqbh,{
			close:function(){
			}
		});
	}
}

function plsh(shzt){
	var ids = jQuery("#dataTable").getSeletIds();
	var flag=true;
	var shgws="";
	var message="";
	if (ids.length == 0) {
		showAlertDivLayer("��ѡ����Ҫ��˵ļ�¼��");
	} else {
		var rows = jQuery("#dataTable").getSeletRow();
		var gwdm = rows[0]["gwdm"];
		var xh = rows[0]["xh"];
		var splc = rows[0]["splc"];
		for ( var i = 1; i < rows.length; i++) {
			if(rows[i]["gwdm"]!=rows[i-1]["gwdm"]){
				flag=false;
				break;			
			}
		}
		if(!flag){
			gwdm = "null";
		}
		for(var i=0;i<ids.length;i++){
			shgws+=rows[i]["gwid"];
			message+=rows[i]["xh"]+"-"+rows[i]["xm"]+"�����<font color='red'>["+rows[i]["gwmc"]+"]</font>��λ";
			if(i+1!=ids.length){
				shgws+=",";
				message+=",";
			}
		}
		showDialog("�������",600,300,"xsgwshnew_sh.do?method=plsh&ids="+ids+"&splc="+splc+"&shgws="+shgws+"&xh="+xh+"&gwdm="+gwdm+"&message="+message+"&shzt="+shzt);
	}
}
function go_sh(){
	var rows = jQuery("#dataTable").getSeletRow();
	var shlx = jQuery("#shlx").val();
	if(shlx=="ysh"){
		showAlertDivLayer("�Ѵ����¼�����ٴ����");
		return false;
	}else if (rows.length != 1){
		plsh();
	} else {
		showDialog("ѧ����λ���",765,530,"xsgwshnew_sh.do?method=xsgwSh&sqbh="+rows[0]["sqbh"]+"&shid="+rows[0]["shid"]+"&gwid="+rows[0]["gwid"]+"&xh="+rows[0]["xh"]+"&tt="+new Date().getTime(),{
			close:function(){
			}
		});
	}
}
/*
 * ����
 */
function cxshnew(){
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1){
		alertInfo("��ѡ��һ����Ҫ������˵ļ�¼��");
	} else {
		splc_cx_new(rows[0]["splc"],rows[0]["shid"]);
	}
}
/*
 * �������̳���
 * shid ���id
 * splc ��������id 
 */
function splc_cx_new(splc,shid){
	//���һ��������˺���õ�·��
	var cancelPath = jQuery("#cancelPath").val();
	confirmInfo("��ȷ��Ҫ����������?",function(ty){
		if(ty=="ok"){
			jQuery.post("comm_spl.do?method=cxshnew",{shlc:splc,shid:shid},function(data){
					jQuery.post("xsgwshnew_sh.do?method=cxRollBack",{shid:data["shid"]},function(){
						// �ж��Ƿ����һ������(1:���һ�������ɹ���
						if("1" == data["cancelFlg"]){
							jQuery.post(cancelPath,{splc:splc,shid:shid},function(result){
								showAlertDivLayer(result["message"],{},{"clkFun":function(){
									jQuery("#dataTable").reloadGrid();
								}});
							},'json');
						}else{
							showAlertDivLayer(data["message"],{},{"clkFun":function(){
								jQuery("#dataTable").reloadGrid();
							}});
						}
					});
			},'json');
		}
	});
}
function searchRs(){
	var map = getSuperSearch();
	map["shlx"] = jQuery.trim(jQuery("#shlx").val());
	jQuery("#dataTable").reloadGrid(map);
}
/**
 * ������˲���
 * @param shzt
 * @param message
 * @return
 */
function save_sh(){
	var shzt=jQuery("#shjg").val();
	jQuery("#shzt").val(shzt);
	if (jQuery("#shyj").val() == ""){
		showAlertDivLayer("����д��������");
		return false;
	}
	if (jQuery("#shyj").val().length>200){
		showAlertDivLayer("���������ܳ���200��");
		return false;
	}
	if (jQuery("#splc").val() == "" || jQuery("#sqbh").val() == ""||  jQuery("#gwid").val() == ""){
		showAlertDivLayer("ϵͳ�쳣���Ժ�");
		return false;
	}
	if(shzt=="1"||shzt==1){//���ѡ�����ͨ����Ҫ������֤
		if(!yzgwsh()){
			return false;
		}
	}
	//�ύ���
	showConfirmDivLayer("��ȷ����˸�������",{"okFun":function(){
		var url = "xsgwshnew_sh.do?method=xsgwSh&type=save&tt="+new Date();
		ajaxSubFormWithFun("demoForm",url,function(data){
			showAlertDivLayer(data["message"],{},{"clkFun":function(){
				//if (parent.window){
					refershParent();
				//}
			}});
		});
	}});
	
}
//���ǰ��֤
function yzgwsh(){
	
	var flg = false;
	
	jQuery.ajax({
		   type: "POST",
		   url: "xsgwshnew_sh.do?method=yzgwsh",
		   data: {xh:jQuery("#xh").val(),gwdm:jQuery("#gwdm").val(),splc:jQuery("#splc").val(),sqbh:jQuery("#sqbh").val()},
		   dataType: "json",
		   async: false,
		   success: function(data){
			   var message = data["message"];
				if(message!="true"&&message!=""){
					showAlert(message);
				}else{
					flg = true;
				}
		   }
		});
	return flg;
}

//�������鿴
function lcgz(){
	var ids = jQuery("#dataTable").getSeletIds();
	var rows = jQuery("#dataTable").getSeletRow();
	if (ids.length != 1){
		alertInfo("��ѡ��һ����¼��");
	} else {
		//showWindow("�������̸���",530,310,'comm_spl.do?method=lcgz&sqid='+ids.toString(),null);
		showDialog("�������̸���",530,310,'comm_spl.do?method=lcgz&sqid='+rows[0]['sqbh']+"&splc="+rows[0]['splc']);
	}
}


function savePlsh(shzt,gwdm,shyj,splc){
	
	var rows = jQuery("#dataTable").getSeletRow();
	var guid = new Array();
	var gwids = new Array();
	var xhs = new Array();
	
	jQuery.each(rows,function(i,row){
		guid.push(row["sqbh"]);
		gwids.push(row["gwid"]);
		xhs.push(row["xh"]);
	});

	jQuery.post(
		"xsgwshnew_sh.do?method=savePlsh",
		{
		 shzt:shzt,
		 id:guid,
		 gwids:gwids,
		 xhs:xhs,
		 gwdm:gwdm,
		 shyj:shyj,
		 splc:splc
		},function(data){
			
			showAlertDivLayer(data["message"],{},{"clkFun":function(){
				jQuery("#dataTable").reloadGrid();
			}});
		},
		'json'
	);
}

