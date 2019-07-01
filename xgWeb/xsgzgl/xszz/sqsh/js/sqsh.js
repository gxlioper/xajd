/**
 * ȡ������
 * @return
 */
function xmsqDelete(){
	var ids = jQuery("#dataTable").getSeletIds();
	if (ids.length == 0){
		showAlertDivLayer("��ѡ����Ҫɾ���ļ�¼��");
	} else {
		
		var rows = jQuery("#dataTable").getSeletRow();
		
		for(var i=0;i<ids.length;i++){
			if(rows[i]['shzt']!='0'){
				showAlertDivLayer("ֻ��ɾ��δ�ύ�ļ�¼��");
				return false;
			}
		}
		
		showConfirmDivLayer("��ȷ��Ҫɾ��ѡ��ļ�¼��",{"okFun":function(){
			jQuery.post("xszz_sqsh.do?method=delXmsq",{values:ids.toString()},function(data){
				showAlertDivLayer(data["message"]);
				jQuery("#dataTable").reloadGrid();
			},'json');
		}});
	}
}

/**
 * ȡ������ ѧ����
 * @return
 */
function xmsqDeleteStu(){
	var rows = jQuery("#dataTable").getSeletRow();
	
	var ids = [];
	for ( var int = 0; int < rows.length; int++) {
		ids.push(rows[int]['guid'])
	}
	
	if (ids.length == 0){
		showAlertDivLayer("��ѡ����Ҫȡ������ļ�¼��");
	} else {

		for(var i=0;i<ids.length;i++){
			if(rows[i]['shzt']!='0'&&rows[i]['shzt']!='3'){
				showAlertDivLayer("ֻ��ɾ��δ�ύ�������˻صļ�¼��");
				return false;
			}
		}
		
		showConfirmDivLayer("��ȷ��Ҫȡ����������",{"okFun":function(){
			jQuery.post("xszz_sqsh.do?method=delXmsq",{values:ids.toString()},function(data){
				showAlertDivLayer(data["message"]);
				jQuery("#dataTable").reloadGrid();
			},'json');
		}});
	}
}

/**
 * ���̸���
 * @return
 */
function xmsqLcgz(){
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1){
		showAlertDivLayer("��ѡ��һ����¼��");
	} else {
		if(rows[0]["shzt"]=="0" || rows[0]["shzt"]=="6" || rows[0]["shzt"]=="7"){
			showAlertDivLayer("�����������Ϣ��");
			return false;
		}
		showDialog("�������̸���",530,310,'comm_spl.do?method=lcgz&sqid='+rows[0]['guid']+"&splc="+rows[0]['shlc']);
	}
}

/**
 * ��Ŀ����
 * @return
 */
function xmsq(){
	showDialog("������Ŀ����",700,500,"xszz_sqsh.do?method=xszzXmsq");
}


/**
 * �޸����� 
 * @return
 */
function updateXmsq(){

	var rows = jQuery("#dataTable").getSeletRow();

	if (rows.length != 1){
		showAlertDivLayer("��ѡ��һ����Ҫ�޸ĵļ�¼��");
	} else {
		
		var shzt = rows[0]["shzt"];
		
		if ("0" != shzt&&"3" != shzt){
			showAlertDivLayer("ֻ���޸�δ�ύ�������˻صļ�¼��");
			return false;
		}
		
		showDialog("������Ŀ�޸�",750,500,"xszz_sqsh.do?method=updateXmsq&guid="+rows[0]["guid"]);
	}
}


/**
 * ѡ��������Ŀ
 * @return
 */
function showXmxz(){
	var xh = jQuery("#xh").val();
	if (jQuery.trim(xh) != ""){
		showDialog("ѡ��������Ŀ",500,350,"xszz_sqsh.do?method=getXmsqInfo&xh="+xh);
	} else {
		showAlertDivLayer("����ѡ��ѧ����");
	}
}

/**
 * չʾ��ͥ���������Ϣ
 * @param obj
 * @return
 */
function showJtqk(obj){
	var className = jQuery(obj).attr("class");
	var newClass = className == "up" ? "down" : "up";

	jQuery(obj).attr("class",newClass);
	jQuery("#t_jtqk").toggle();
}

/**
 * ȷ��ѡ����Ŀ
 * @return
 */
function selectXm(){
	var api = frameElement.api;
	var selectBox = jQuery("#wsq input:checkbox:checked");
	
	var tbody = jQuery(api.get('parentDialog').document).find("#xmInfo");
		tbody.find("tr").remove();
		
	jQuery.each(selectBox,function(i,e){
		var jesfxssq=jQuery(e).parents("tr").eq(0).find("td").eq(2).find("input").val();
		if(jesfxssq=='1'){
			var trHtml = "<tr>";
			trHtml+="<td style='width: 30%'>";
			trHtml+="<input type='hidden' name='xmdmArray' value='"+jQuery(e).val()+"'/>";
			trHtml+=jQuery(e).parents("tr").eq(0).find("td").eq(1).html();
			trHtml+="</td><td style='width: 30%'>";
			trHtml+="<input type='hidden' name='je' value='"+jQuery(e).parents("tr").eq(0).find("td").eq(2).text()+"'/>";
			trHtml+="<input type='text' name='ylzd1'  onkeyup='checkInputData(this);checkJesx(this)' style='width: 40%'/>";
			trHtml+="<font color='blue'>���޽�"+jQuery(e).parents("tr").eq(0).find("td").eq(2).text()+"<font/>";
			trHtml+="</td>";
			trHtml+="<td style='width: 30%'>";
			trHtml+=jQuery(e).parents("tr").eq(0).find("td").eq(3).html();
			trHtml+="</td>";
			trHtml+="</tr>";
			tbody.append(trHtml);
		}else{
			var trHtml = "<tr>";
			trHtml+="<td style='width: 30%'>";
			trHtml+="<input type='hidden' name='xmdmArray' value='"+jQuery(e).val()+"'/>";
			trHtml+=jQuery(e).parents("tr").eq(0).find("td").eq(1).html();
			trHtml+="</td><td style='width: 30%'>";
			trHtml+="<input type='hidden' name='ylzd1' value='"+jQuery(e).parents("tr").eq(0).find("td").eq(2).text()+"'/>";
			trHtml+=jQuery(e).parents("tr").eq(0).find("td").eq(2).html();
			trHtml+="</td>";
			trHtml+="<td style='width: 20%'>";
			trHtml+=jQuery(e).parents("tr").eq(0).find("td").eq(3).html();
			trHtml+="</td>";
			trHtml+="</tr>";
			tbody.append(trHtml);
		}
	});
	iFClose();
}

//��֤ѧ���������Ƿ񳬹��������
function checkJesx(obj){
	var ylzd1= jQuery(obj).val();
	var je = jQuery(obj).parent("td").find("input").eq(0).val();
	if(parseFloat(ylzd1)>parseFloat(je)){
		showAlertDivLayer("���������Ŀ������ޣ�");
		jQuery(obj).val('');
	}
}
//��֤ѧ���������Ƿ񳬹�������ޣ����ҳ�棩
function checkJesxSh(obj){
	var ylzd1= jQuery(obj).val();
	var jesx = jQuery("#jesx").val();
	if(parseFloat(ylzd1)>parseFloat(jesx)){
		showAlertDivLayer("���������Ŀ������ޣ�");
		jQuery(obj).val('');
	}
}


/**
 * ������Ŀ����
 * @return
 */
function saveXmsq(type){
	var xh = jQuery("#xh").val();
	var xmdmArray = jQuery("input[name=xmdmArray]");
	
	if (xh == ""){
		showAlert("����ѡ��ѧ����");
		return false;
	}
	
	if (xmdmArray.length == 0){
		showAlert("����ѡ��ѧ����Ҫ�����������Ŀ��",{},{"clkFun":function(){
			showDialog("������Ŀ����",500,350,"xszz_sqsh.do?method=getXmsqInfo&xh="+xh);
		}});
		return false;
	}
	
	if (!checkMustNotNull()){
		showAlertDivLayer("�뽫��������д������");
		return false;
	}
	
	var url = "xszz_sqsh.do?method=saveXmsq&type="+type;
	ajaxSubFormWithFun("xmsqForm",url,function(data){
		showAlertDivLayer(data["message"],{},{"clkFun":function(){
			if (parent.window){
				refershParent();
			}
		}});
	});
}
//�ύ
function submitBusi(){
	var ids = jQuery("#dataTable").getSeletIds();
	if (ids.length == 0) {
		showAlertDivLayer("��ѡ����Ҫ�ύ�ļ�¼��");
	} else if (ids.length >1 ) {
		showAlertDivLayer("��ѡ��һ����Ҫ�ύ�ļ�¼��");
	} else {
		var rows = jQuery("#dataTable").getSeletRow();
		
		if('3'!=rows[0]['shzt']&&"false"==rows[0]['isopen']){
			showAlertDivLayer("��ǰ������Ŀʱ���ѹرգ��������Ա��ϵ��");
			return false;
		}
		
		for(var i=0;i<ids.length;i++){
			if(rows[i]['shzt']!='0'&&rows[i]['shzt']!='3'){
				showAlertDivLayer("��ѡ��δ�ύ�������˻صļ�¼��");
				return false;
			}
		}
		
		showConfirmDivLayer("��ȷ��Ҫ�ύѡ��ļ�¼��", {
			"okFun" : function() {
				jQuery.post("xszz_sqsh.do?method=subBusi", {
					values : ids.toString(),
					lcid : rows[0]['shlc'],
					xh : rows[0]['xh']
				}, function(data) {
					showAlertDivLayer(data["message"]);
					jQuery("#dataTable").reloadGrid();
				}, 'json');
			}
		});
	}
}

//�ύ --ѧ����
function submitBusiStu(){
	var rows = jQuery("#dataTable").getSeletRow();
	var ids = [];
	for ( var int = 0; int < rows.length; int++) {
		ids.push(rows[int]['guid'])
	}
	if (ids.length == 0) {
		showAlertDivLayer("��ѡ����Ҫ�ύ�ļ�¼��");
	} else if (ids.length >1 ) {
		showAlertDivLayer("��ѡ��һ����Ҫ�ύ�ļ�¼��");
	} else {
		var rows = jQuery("#dataTable").getSeletRow();
		
		if(rows[0]["sqkg"]!="1"&&"3"!=rows[0]["shzt"]){
			showAlertDivLayer("��ѡ��Ŀ����״̬δ���ţ�������ѡ��");
			return false;
		}
		
		for(var i=0;i<ids.length;i++){
			if(rows[i]['shzt']!='0'&&rows[i]['shzt']!='3'){
				showAlertDivLayer("��ѡ��δ�ύ�������˻صļ�¼��");
				return false;
			}
		}
		
		showConfirmDivLayer("��ȷ��Ҫ�ύѡ��ļ�¼��", {
			"okFun" : function() {
				jQuery.post("xszz_sqsh.do?method=subBusi", {
					values : ids.toString(),
					lcid : rows[0]['shlc'],
					xh : jQuery("input[name='xh']").val()
				}, function(data) {
					showAlertDivLayer(data["message"]);
					jQuery("#dataTable").reloadGrid();
				}, 'json');
			}
		});
	}
}

//����
function cancle(){
	var ids = jQuery("#dataTable").getSeletIds();
	if (ids.length == 0) {
		showAlertDivLayer("��ѡ����Ҫ�����ļ�¼��");
	} else if (ids.length >1 ) {
		showAlertDivLayer("��ѡ��һ����Ҫ�����ļ�¼��");
	} else {
		var rows = jQuery("#dataTable").getSeletRow();
		var SFBJPY_Y = jQuery("#SFBJPY_Y").val();
		for(var i=0;i<ids.length;i++){
			if(SFBJPY_Y == 'true'){
				if(rows[i]['shzt']!='6'){
					showAlertDivLayer("ֻ�а༶�����еļ�¼���ܱ�������");
					return false;
				}
			}else{
				if(rows[i]['shzt']!='5'){
					showAlertDivLayer("ֻ������еļ�¼���ܱ�������");
					return false;
				}
			}
		}
		
		showConfirmDivLayer("��ȷ��Ҫ����ѡ��ļ�¼��", {
			"okFun" : function() {
				jQuery.post("xszz_sqsh.do?method=cancle", {
					values : ids.toString(),
					lcid : rows[0]['shlc']
				}, function(data) {
					showAlertDivLayer(data["message"]);
					jQuery("#dataTable").reloadGrid();
				}, 'json');
			}
		});
	}
}

/**
 * ѡ����Ŀҳ���л������롢δ����
 * @param obj
 * @param tabId
 * @return
 */
function selectSqxm(obj,tabId){

	jQuery("#comp_title li").removeClass("ha");
	jQuery(obj).parent().addClass("ha");

	jQuery("#xmList tbody").css("display","none");
	jQuery("#"+tabId).css("display","");
	
	if (tabId == "ysq"){
		jQuery("#titleTr td").eq(-2).css("display","none");
		jQuery("#titleTr td").last().css("display","");
	} else {
		jQuery("#titleTr td").last().css("display","none");
		jQuery("#titleTr td").eq(-2).css("display","");
	}
}


/**
 * �����޸�����
 * @return
 */
function saveSqxg(type){
	if (!checkMustNotNull()){
		showAlertDivLayer("�뽫��������д������");
		return false;
	}
	
	var isopen = jQuery("#isopen").val();
	var shzt = jQuery("#shzt").val();
	
	if("submit"==type&&"false"==isopen&&"3"!=shzt){
		showAlertDivLayer("��ǰ������Ŀʱ���ѹرգ����ڹ���Ա��ϵ��");
		return false;
	}

	var url = "xszz_sqsh.do?method=saveSqxg&type="+type;
	ajaxSubFormWithFun("xmsqForm",url,function(data){
		showAlertDivLayer(data["message"],{},{"clkFun":function(){
			if (parent.window){
				refershParent();
			}
		}});
	});
}


/**
 * �л����ҳtabҳ
 * @param obj
 * @param shzt
 * @return
 */
function selectTab(obj,shzt){
	jQuery("#shzt").val(shzt);

	if (shzt == "dsh"){
		jQuery("#li_sh").css("display","");
		jQuery("#li_qx").css("display","none");
		var gridSetting = getDclGird();
		var map = getSuperSearch();
		map["shzt"]="dsh";
		gridSetting["params"] = map;
		jQuery("#dataTable").initGrid(gridSetting);
	} else {
		jQuery("#li_sh").css("display","none");
		jQuery("#li_qx").css("display","");
		var gridSetting2 = getYclGrid();
		var map = getSuperSearch();
		map["shzt"]="ysh";
		gridSetting2["params"] = map;
		jQuery("#dataTable").initGrid(gridSetting2);
	}
	
	jQuery(".ha").removeClass("ha");
	jQuery(obj).parent().addClass("ha");
	searchRs();
}


/**
 * ������Ŀ���
 * @return
 */
function zzxmSh(){
	var rows = jQuery("#dataTable").getSeletRow();

	if (rows.length == 0){
		showAlertDivLayer("��ѡ����Ҫ��˼�¼��");
	} else if (rows.length == 1){
		showDialog("������Ŀ���",750,500,"xszz_sqsh.do?method=zzxmDgsh&guid="+rows[0]["guid"]+"&xtgwid="+rows[0]["xtgwid"]+"&shid="+rows[0]["shid"]+"&shlc="+rows[0]["shlc"]);
	} else {
		//showAlertDivLayer("��ѡ��һ����Ҫ��˵ļ�¼��");
		showDialog("�������",500,340,"xszz_sqsh.do?method=zzxmPlsh");
	}
}


/**
 * �������
 * @return
 */
function cancelXmsh(){
	var rows = jQuery("#dataTable").getSeletRow();

	if (rows.length != 1){
		showAlertDivLayer("��ѡ��һ����Ҫ��������˼�¼��");
	} else {
		
		//���һ��������˺���õ�·��
		var cancelPath = "xszz_sqsh.do?method=cancelXmsh";
		confirmInfo("��ȷ��Ҫ����������?",function(ty){
			if(ty=="ok"){
				jQuery.post("xszz_sqsh.do?method=cxshnew",{shlc:rows[0]["shlc"],shid:rows[0]["shid"],xh:rows[0]["xh"],xtgwid:rows[0]["xtgwid"]},function(data){
						// �ж��Ƿ����һ������(1:���һ�������ɹ���
						if("1" == data["cancelFlg"]){
							jQuery.post(cancelPath,{guid:rows[0]["guid"]},function(result){
								showAlertDivLayer(result["message"],{},{"clkFun":function(){
									jQuery("#dataTable").reloadGrid();
								}});
							},'json');
						}else{
							showAlertDivLayer(data["message"],{},{"clkFun":function(){
								jQuery("#dataTable").reloadGrid();
							}});
						}
					
				},'json');
			}
		});
		
	}
}


/**
 * �����������
 * @param shzt
 * @return
 */
function savePlsh(shzt,shyj,shxmje){
	
	
	var rows = jQuery("#dataTable").getSeletRow();
	var guid = new Array();
	var gwid = new Array();
	var xhs = new Array();
	var thgw = "";
	if(shzt == '3'){
		thgw = "-1";
	}
	jQuery.each(rows,function(i,row){
		guid.push(row["guid"]);
		gwid.push(row["xtgwid"]);
		xhs.push(row["xh"]);
	});

	jQuery.post(
		"xszz_sqsh.do?method=zzxmPlsh&type=save",
		{id:guid,
		 gwid:gwid,
		 xhs:xhs,
		 shyj:shyj,
		 shzt:shzt,
		 shxmje:shxmje,
		 thgw:thgw
		},function(data){
			showAlertDivLayer(data["message"],{},{"clkFun":function(){
				jQuery("#dataTable").reloadGrid();
			}});
		},
		'json'
	)
}


/**
 * ������˲���
 * @param shzt
 * @param message
 * @return
 */
function saveZzsh(){

	if(jQuery("#shxmje").css("display")!="none"){
		if(jQuery("#shxmje").val()==""){
			showAlertDivLayer("����д��Ŀ��");
			return false;
		}
	}
	if (jQuery("#shyj").val() == ""){
		showAlertDivLayer("����д��������");
		return false;
	}
	
	//showConfirmDivLayer("��ȷ����˸�������",{"okFun":function(){
		var url = "xszz_sqsh.do?method=saveXmsh";
		ajaxSubFormWithFun("sqshForm",url,function(data){
			showAlertDivLayer(data["message"],{},{"clkFun":function(){
				if (parent.window){
					refershParent();
				}
			}});
		});
	//}});
}


/**
 * ѧ������
 * @param cellValue
 * @param rowObject
 * @return
 */
function xhLink(cellValue,rowObject){
	return "<a href='javascript:void(0);' class='name' onclick='xmsqView(\""+rowObject["guid"]+"\");'>"+cellValue+"</a>";
}

/**
 * �������϶���ѯ
 * @param guid
 * @return
 */
function xmsqView(guid){
	showDialog('��������鿴',750,500,'xszz_sqsh.do?method=viewXmsq&guid='+guid);
}

/**
 * �������
 * @return
 */
function checkCondition(){
	var xh = jQuery("#xh").val();
	var checkbox = jQuery("#wsq input:checkbox");
	var _check = function(object){
		var _self = jQuery(object);
		var xmdm = _self.val();
		jQuery.post("xszz_sqsh.do?method=checkCondition",{xmdm:xmdm,xh:xh},function(data){
			if (""==data||data.length == 0){
				_self.parents("tr").eq(0).find("td").eq(-2).html("<font color='green'>����������</font>");
			} else {
				
				var html="";
				
				for (var j = 0, m = data.length ; j<m ; j++){
					if (data[j]["result"] == "true"){
						html+="<img src='images/ico_38.gif' title='��������'/>";
					} else {
						html+="<img src='images/ico_39.gif' name='faidImg' title='����������'/>";
						_self.attr("disabled","disabled");
					}
					html+= "  ";
					html+= (j+1)+"��";
					html+= data[j]["sqts"];
					html+= ";";
					html+="<br/>";
				}
				
				_self.parents("tr").eq(0).find("td").eq(-2).html(html);
				
			}
			_self.parents("tr").eq(0).find("td").last().css("display","none");
		},'json');
	}
	
	
	jQuery.ajaxSetup({async:false});
	
	jQuery.each(checkbox,function(i,e){
		_check(e);
	});
	
	jQuery.ajaxSetup({async:true});
}

//��ӡ����
function printXmsq(url){
	var rows = jQuery("#dataTable").getSeletRow();

	if (rows.length != 1){
		showAlertDivLayer("��ѡ��һ����Ҫ��ӡ�ļ�¼��");
	} else {

		var url= url+"&guid="+rows[0]["guid"]+"&xh="+rows[0]["xh"]+"&xmmc="+rows[0]["xmmc"]+"&xn="+rows[0]["xn"]+"&xq="+rows[0]["xq"]
      //�жϸ���Ŀ�Ƿ��б���
        jQuery.post("xszz_xmwh.do?method=getXszzdm",{"xmmc":rows[0]["xmmc"],"xn":rows[0]["xn"],"xq":rows[0]["xq"]},function(data){
			if(data["message"] != null && data["message"]!=""){
				window.open(url);
			}else{
				showAlertDivLayer("����Ŀ��δ���ñ�������ϵ����Ա��");
				return false;
			}
		},'json');
    }
}


/**
 * ��ÿ���
 * @param obj
 * @return
 */
function checkJdsz(obj){
	var xmdm = jQuery(obj).val();
	
	jQuery.post("xszz_xmwh_jdsz.do?method=getKjdxm",{xmdm:xmdm},function(jsonMap){

		var data = jsonMap["data"];
		var kgzt = jsonMap["jdkg"];
		
		if (kgzt != "1"){
			return ;
		}
		
		jQuery.each(data,function(i,n){
			var bjdxm = data[i]["dm"];
			
			var yhdxm = jQuery("#ysq input:checkbox[value="+bjdxm+"]");
			if (yhdxm.length >0){
				
				showAlert("��������������Ŀ��"+data[i]["mc"]+"�������������뵱ǰ��Ŀ��",{},{"clkFun":function(){
					jQuery(obj).attr("checked",false);
				}});
			}

			var yxzxm = jQuery("#wsq input:checked[value="+bjdxm+"]");

			if (yxzxm.length >0){
				
				showAlert("����ѡ��������Ŀ��"+jQuery.trim(yxzxm.parent().next().html())+"����������ѡ��ǰ��Ŀ��",{},{"clkFun":function(){
					jQuery(obj).attr("checked",false);
				}});
			}
		});
	},'json')
}

function searchRs(){
	var map = getSuperSearch();
	var shzt = jQuery("#shzt").val();
	
	if (shzt != ""){
		map["shzt"] = shzt;
	}
	jQuery("#dataTable").reloadGrid(map);
}



var DCCLBH = "xszz_sqsh_xmsq.do";//dcclbh,�������ܱ��
//�Զ��嵼�� ����
function exportConfig() {
	//DCCLBH�������ܱ�ţ�ִ�е����ĺ���
	customExport(DCCLBH, exportData);
}

// ��������
function exportData() {
	setSearchTj();//���ø߼���ѯ����
	var url = "xszz_sqsh.do?method=exportData&dcclbh=" + DCCLBH;//dcclbh,�������ܱ��
	url = addSuperSearchParams(url);//���ø߼���ѯ����
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}

//��˵���
function exportConfigSh(){
	var DCCLBH='xszz_sqsh_xmsh.do';
	customExport(DCCLBH, exportDataSh);
}

//��˵�������
function exportDataSh(){
	var shzt=jQuery("#shzt").val();
	var DCCLBH='xszz_sqsh_xmsh.do';
	
	setSearchTj();//���ø߼���ѯ����
	
	var url = "xszz_sqsh.do?method=exportDataSh&shzt="+shzt+"&dcclbh=" + DCCLBH;//dcclbh,�������ܱ��
	url = addSuperSearchParams(url);//���ø߼���ѯ����
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}

//��ӡword
function getWord(){	
	var rows = jQuery("#dataTable").getSeletRow();	
	 if (rows.length == 0){
		showAlertDivLayer("��ѡ��һ����¼��");
	 } else if (rows.length > 1){
		var url="xszz_sqsh.do?method=downloadZip";
		var ids = jQuery("#dataTable").getSeletIds();
		url += "&value="+ids;
		window.open(url);
	 } else {
		var url="xszz_sqsh.do?method=downloadWord";		
		url += "&guid="+rows[0]["guid"];
		window.open(url);
   }
}

//��ӡѧ��������������ͼ
function printSqlct(url){
	
	var url = "xszz_sqsh.do?method=printLct";
	window.open(url);
}


/**
 * ���ͳ��
 */
function zzxmShqk(){
	showDialog("ѧ���������ͳ�����",550,400,"xszz_sqsh.do?method=zzxmShtj",{max:false,min:false});
}
//��֤��Ŀ����Ƿ���޸�
function jeSfkt(obj){
	
	
	var xmdm=jQuery(obj).val();
	jQuery.post('xszz_sqsh.do?method=jeSfkt',{"xmdm":xmdm},function(data){
		if(data["jesfxssq"]=='1'){
			jQuery('.je').css("display","");
			jQuery('#jesx').val(data["je"]);
			if(jQuery("#shxmdm").val()==jQuery("#oldshxmdm").val()){
				getXmje();
			}
			else{
				jQuery("#shxmje").val('');
			}
			
			jQuery('#message').text('���޽��:'+data["je"]);
		}else{
			jQuery('.je').css("display","none");
			jQuery('#shxmje').val('');
		}
		
	},'json');
}
//��ȡ��Ŀ���
function getXmje(){
	if(jQuery("#shlccx_table tr").length>2){
		jQuery("#shxmje").val(jQuery.trim(jQuery("#shlccx_table tr").eq(-2).find("td").eq(-1).text()));
		}
	else{
	jQuery("#shxmje").val(jQuery("#sqje").val());
	}
	
}

/**
 * �쳣���ݴ���
 * @return
 */
function exceptionDataResolve(){

        showConfirmDivLayer("��ȷ��Ҫ�����ύ�쳣���ݴ�����",{"okFun":function(){
            jQuery.post("xszz_sqsh.do?method=exceptionDataResolve",function(data){
                showAlertDivLayer(data["message"]);
            },'json');
        }});
}