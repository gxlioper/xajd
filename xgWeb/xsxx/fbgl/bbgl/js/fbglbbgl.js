
function ckgz(cellValue, rowObject){
	 var pzgzid=rowObject["pzgzid"];
	 
	 return "<a href='javascript:void(0);' onclick=\"ckpzgz('" + pzgzid
		+ "')\" class='name'>" + cellValue + "</a>";
 }
function ckpzgz(pzgzid){
		var url ="fbglgzpztj.do?method=showView&pzgzid=" + pzgzid;
		var cktitle ="�鿴������Ϣ";
		showDialog(cktitle, 800, 500, url);
 }
var action="fbglbbgl.do";
var title="�ְ����";
function searchRs() {
	var map = getSuperSearch();
	var bbzt = jQuery("#bbzt").val();
	if (bbzt != "") {
		map["bbzt"] = bbzt;
	}
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
}
// ��������ת
function dcmcLink(cellValue, rowObject) {
	var pk = rowObject["pk"];
	return "<a href='javascript:void(0);' onclick=\"ckxx('" + pk
			+ "')\" class='name'>" + cellValue + "</a>";
}
function zyjcFormatter(cellValue, rowObject){
	var html="<input name='zyjc' type='text' size='15px;'/>";
	return html;
}
function rssxFormatter(cellValue, rowObject){
	var html="<input name='rssx' type='text' size='10px;' onblur='checkInt(this)' maxlength='4'/>";
	return html;
}
function bjdmFormatter(cellValue, rowObject){
	var html="<font name='bjdm' color='red' size='3px;'>"+cellValue+"</font>";
	return html;
}
function bjgsFormatter(cellValue, rowObject){
	var html="<input name='bjgs' type='text' size='10px;' onblur='checkInt(this)' maxlength='4'/>";
	return html;
}

function lshFormatter(cellValue, rowObject){
	var html="<input name='lsh' type='text' size='10px;' onblur='checkInt(this)' maxlength='4'/>";
	return html;
}

// �鿴��Ϣ
function ckxx() {
	var id=jQuery("#pzgzid").val();
	var url = "fbglgzpztj.do?method=showView&pzgzid=" + id;
	var cktitle =title+"��Ϣ";
	showDialog(cktitle, 680, 400, url);
}
//�л�
function selectTab(obj, bbzt) {
	var map = getSuperSearch();
	if (bbzt == "wbb") {
		map["bbzt"]="wbb";
		gridSetting["params"] = map;
		jQuery("#dataTable").initGrid(gridSetting);
		 jQuery("#tzbb").hide();
		 jQuery("#scbj").hide();
		 jQuery("#qxbb").hide();
		 jQuery("#bb").show();
	} else {
		map["bbzt"]="ybb";
		gridSetting2["params"] = map;
		jQuery("#dataTable").initGrid(gridSetting2);
		jQuery("#tzbb").show();
		jQuery("#scbj").show();
		jQuery("#qxbb").show();
		jQuery("#bb").hide();
	}
	jQuery(".ha").removeClass("ha");
	jQuery(obj).parent().addClass("ha");
	//searchRs();
}
//���
function bb(){
	//�����ڶ�Ӧ��������Ϣ
	if (!isHaveGzxx("BBGZ")) {
		return showAlertDivLayer("������δ�趨��δ���ã�");
	}
	var ids = jQuery("#dataTable").getRowCount();
	if(ids<=0){
		return showAlertDivLayer("������ѧ����Ϣ��");
	}
	var url = action + "?method=add";
	var title = "���";
	var ids = jQuery("#dataTable").getSeletIds();
	url+="&pk="+ids.toString();
	showDialog(title, 800, 500, url);
	jQuery("#dataTable").reloadGrid();
}
//ִ�б���
function save(url){
	var isok=true;
	jQuery("[name=bjgs]").each(function(){
		if(!jQuery(this).val()){
			isok=false;
			return;
		}
	});
	if(!isok){
		return showAlertDivLayer("����д�����༶������");
	}
	if(!checkBjrssx()){
		return showAlertDivLayer("�༶����*�Զ��ְ����������ѳ�����������");
	}
	lock();
	var pzgzid=jQuery("#pzgzid").val();
	var obj=new Array();
	//��ȡ���ݲ���
	jQuery("#dataTable").find("tr[rowindex]").each(function(i){
		var trobj=new Object();
		var list=new Array();
		jQuery(this).find(":text").each(function(j){
			var data=new Object();
			//alert(jQuery(this).attr("name")+":"+jQuery(this).val());
			data.name=jQuery(this).attr("name");
			data.value=jQuery(this).val();
			list[j]=data;
		});
		trobj.tr=list;
		trobj.pk=jQuery(this).find("td").first().html();
		obj[i]=trobj;
	});
	var json=JSON.stringify(obj);
	//�������������
	//����������
	var all=0;
	jQuery("#dataTable").find("[name=bjgs]").each(function(){
		var bjgs=jQuery(this).val()==""?0:jQuery(this).val();
		all+=parseInt(bjgs);
	});
	jQuery.post(url, {
		pzgzid:pzgzid,
		json:json,
		all:all
	}, function(data) {
		if("������"!=data["message"]){
			showAlert(data["message"],{},{"clkFun":function(){
				if (parent.window){
 					refershParent();
 				}
			}});
		}
		},"json");
 	
 	//��ʼ������
 	loadBar("fbglbb"+pzgzid,function(data){
 		if(data.finish){
	 	   showAlert("�����ɣ�",{},{"clkFun":function(){
				if (parent.window){
 					refershParent();
 				}
			}});
 		}
 		return true;
 	});
}
/**
 * ���ش��ְ�����
 * @return
 */
function loadDfbsj(){
	var pzgzid=jQuery("#pzgzid").val();
	jQuery("#table").load("fbglbbgl.do?method=fbglbb&pzgzid="+pzgzid+"&tt="+new Date().getTime());
	preview();
}
/**
 * ���ɰ༶����
 * @return
 */
function gennerBjgs(){
	var mbrs=jQuery("#mbrs").val();
	if(!mbrs){
		return showAlertDivLayer("ÿ����������Ϊ�գ�");
	}
	mbrs=parseInt(mbrs);
	if(mbrs<=0){
		return showAlertDivLayer("ÿ����������һ�ˣ�");
	}
	var rows=jQuery("#dataTable").getSeletAllRow();
	jQuery(".dateline>tbody").find("tr").each(function(i){
		var rs=rows[i]["rs"];
		//����༶����
		rs=parseInt(rs);
		var bjgs=rs/mbrs;
		var syrs=rs%mbrs;
		bjgs=Math.floor(bjgs);
		if(syrs>0){
			bjgs+=1;
		}
		jQuery(this).find("[name=bjgs]").val(bjgs);
	});
}
/**
 * ���༶��������
 * @return
 */
function checkBjrssx(){
	var isok=true;
	var rows=jQuery("#dataTable").getSeletAllRow();
	jQuery(".dateline>tbody").find("tr").each(function(i){
		var rs=rows[i]["rs"];
		var bjgs=jQuery(this).find("[name=bjgs]").val();
		var rssx=jQuery(this).find("[name=rssx]").val();
		if(parseInt(bjgs)*parseInt(rssx)>parseInt(rs)){
			jQuery(this).css("background","none repeat scroll 0 0 #feeeed");
			isok= false;
		}
	});
	return isok;
}
/*************************�ѱ��*************************/
//��¼ɾ�����У��������У�
var jlsch=0;
var bakHtml = "<tr>"
				+ "<td></td>"
				+ "<td style='word-break: break-all; display: none;'></td>"
				+ "<td style='word-break: break-all; display: none;'></td>"
				+ "<td></td>"
				+ "<td></td>"
				+ "<td><input type=\"text\" maxlength=\"4\" onblur=\"checkInt(this)\" value=\"8\" name=\"rssx\"></td>"
				+ "<td></td>"
				+ "</tr>";
function addfb() {
	//�����ӵ�����
	var index=jQuery("#dataTable").find("tr[isAdd=true]").size();
	//ȥ��������ɾ����
	index=index-jlsch;
	var pzgzid=jQuery("#pzgzid").val();
	var pk=jQuery("#pk").val();
	jQuery.ajax({
		url:"fbglbbgl.do?method=getNextCode",
		data:{pzgzid:pzgzid,pk:pk,index:index},
		type:"post",
		dataType:"json",
		async:false,
		success:function(data){
			var html="";
			//�ö��ĵ��жϣ������޸�bug��д��Ϊ�˾������ٶ�Դ�����Ӱ�죬����ֱ���жϲ����ˡ�
			//��ǰû�а༶
			if(jQuery("[name=tbxx]>tbody").find("tr").last().index()<0){
				var tr=jQuery(bakHtml);
				jQuery(tr).attr("rowindex",1);
				//���
				jQuery(tr).find("td").eq(0).html(1);
				//pk
				jQuery(tr).find("td").eq(1).html(data.pk);
				//�༶���
				jQuery(tr).find("td").eq(2).html(data.bjbh);
				jQuery(tr).attr("isAdd","true");
				//�༶����
				html="<font name='bjdm' color='red' size='3px;'>"+data.bjdm+"</font>";
				jQuery(tr).find("td").eq(3).html(html);
				//�༶����
				html="<input name='bjmc' type='text' value="+data.bjmc+" />";
				jQuery(tr).find("td").eq(4).html(html);
				//ѧ����
				jQuery(tr).find("td").eq(6).html(0);
				jQuery("#dataTable").append(tr);
			}else{
				var tr=jQuery("#dataTable").find("tr").last().clone();
				jQuery(tr).attr("isAdd","true");
				var rowindex=jQuery(tr).attr("rowindex");
				jQuery(tr).attr("rowindex",parseInt(rowindex)+1);
				//���
				html=jQuery(tr).find("td").eq(0).html();
				//����������ˮ���򲻼����
				if(null!=html&&""!=html){
				html=parseInt(html)+1;
				jQuery(tr).find("td").eq(0).html(html);
				}
				//�༶����
				html="<font name='bjdm' color='red' size='3px;'>"+data.bjdm+"</font>";
				jQuery(tr).find("td").eq(3).html(html);
				//�༶����
				html="<input name='bjmc' type='text' value="+data.bjmc+" />";
				jQuery(tr).find("td").eq(4).html(html);
				//ѧ����
				jQuery(tr).find("td").eq(6).html(0);
				jQuery("#dataTable").append(tr);
			}
		}
	});	
}
/**
 * ɾ���ְ�
 * @return
 */
function delfb() {
	var lastTr=jQuery("[name=tbxx]>tbody").find("tr").last();
	//�༶����ѧ��������ɾ��
	var bjrs = jQuery(lastTr).find("td").last().text();
	var bjdm = jQuery(lastTr).find("td").eq(3).text();
	if("0"!=bjrs){
		showAlertDivLayer(bjdm+"����ѧ����������ɾ����");
		return false;
	}
	var isadd=jQuery(lastTr).attr("isAdd");
	if(isadd!="true"){
		jlsch++;
	}
	
	lastTr.remove();
}
/**
 * ɾ����
 * @param tr
 * @return
 */
function removeTr(tr){
	jQuery(tr).remove();
}
function bjmcFormatter(cellValue, rowObject){
	var html="<input name='bjmc' type='text' maxlength='50' value="+cellValue+" />";
	return html;
}
function rssxTbFormatter(cellValue, rowObject){
	if(!cellValue){
		cellValue="������";
	}
	var html="<input name='rssx' type='text' value="+cellValue+" onblur='checkN(this)' onfocus='fomartN(this)' maxlength='4'/>";
	return html;
}
function fomartN(obj){
	if(obj.value=="������"){
		jQuery(obj).val("");
	}
}
function checkN(obj){
	if(obj.value==""){
		jQuery(obj).val("������");
	}
	if(obj.value=="������"){
		return true;
	}else {
		return checkInt(obj);
	}
}
/**
 * ���ص�����Ϣ
 * @return
 */
function loadTbxx(){
	var pk=jQuery("#pk").val();
	jQuery("#table").load("fbglbbgl.do?method=fbgltbxx&pk="+pk+"&tt="+new Date().getTime());
}
function searchRs() {
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
}
/**
 * �����༶
 * @return
 */
function tzbj(){
	//�����ڶ�Ӧ��������Ϣ
	if (!isHaveGzxx("BBGZ")) {
		return showAlertDivLayer("������δ�趨��δ����,����ִ�е��࣡");
	}
	var ids = jQuery("#dataTable").getSeletIds();
	if (ids.length == 0) {
		return showAlertDivLayer("��ѡ����Ҫ������"+jQuery("#xbmc").val()+"רҵ��");
	}
	var nowpk="";
	var row = jQuery("#dataTable").getSeletRow();
	var isok=true;
	var isHaveStu=false;
	for(var i=0;i<row.length;i++){
		if(nowpk==""||nowpk==row[i]["pk"]){
			nowpk=row[i]["pk"];
		}else{
			isok=false;
			break;
		}
		if("0"!=row[i]["xss"]){
			isHaveStu=true;
			break;
		}
		
	}
	if(!isok){
		showAlertDivLayer("ֻ�ܶ�ͬһ"+jQuery("#xbmc").val()+"��רҵ���꼶��ѧ�ơ���εİ༶���е��࣡");
	}else if(isHaveStu){
		showAlertDivLayer("��ѡ�༶����ѧ�����������޸ı����Ϣ�������޸ģ����������Ӧ�ְ���Ϣ��");
	}else{
		var url=action+"?method=fbgltb&pk="+nowpk;
		showDialog("������", 800, 500, url);
	}
}
/**
 * ɾ���༶
 * @return
 */
function scbj(){
	var ids = jQuery("#dataTable").getSeletIds();
	if (ids.length == 0) {
		showAlertDivLayer("��ѡ����Ҫ�����ļ�¼��");
	} else {
		var rows = jQuery("#dataTable").getSeletRow();
		showConfirmDivLayer("��ȷ��Ҫ��ѡ��ļ�¼ȡ�������", {
			"okFun" : function() {
				jQuery.post(action+"?method=del", {
					values : ids.toString()
				}, function(data) {
					var mes="";
				
					if(data["errorObject"]!="-1"){
						var list=data["errorObject"];
						for(var i=0;i<list.length;i++){
							var messageObj=list[i];
							mes+="<font color='red'>"+messageObj["bjmc"]+"</font>";
							mes+="�İ༶ ����ѧ������ɾ��!";
							mes+="</br>";
						}
					}else{
						mes="�����ɹ���";
					}
					showAlertDivLayer(mes);
					jQuery("#ybb").click();
				}, 'json');
			}
		});
	}
}
/**
 * ���������Ϣ
 * @return
 */
function saveTbxx(){
	
	var pzgzid=jQuery("#pzgzid").val();
	var pk=jQuery("#pk").val();
	var rows=jQuery("#dataTable").getSeletAllRow();
	var list=new Array();
	var bjmcCheck = false;
	var sameBjmc="";
	checkMsg="";
	jQuery("#dataTable").find("tbody>tr").each(function(i){
		var data=new Object();
		data.pk=jQuery(this).find("td").eq(1).text();
		data.bjdm=jQuery(this).find("[name=bjdm]").text();
		data.bjmc=jQuery(this).find("[name=bjmc]").val();
		data.bjrssx=jQuery(this).find("[name=rssx]").val();
		list[i]=data;
		checkMsg=data.bjmc;
		if(sameBjmc.indexOf(checkMsg) > -1){
			bjmcCheck=true;
			return false;
		}else{
			sameBjmc+="&"+data.bjmc;
		}
	});
	if(bjmcCheck){
		showAlertDivLayer("�༶���Ʋ����ظ������޸ģ�");
		return false;
	}
	var json=JSON.stringify(list);
 	jQuery.ajax({
		url:"fbglbbgl.do?method=saveTbxx",
		data:{pzgzid:pzgzid,json:json,pk:pk},
		type:"post",
		dataType:"json",
		async:false,
		success:function(data){
			   showAlert(data["message"],{},{"clkFun":function(){
					var api = frameElement.api;
					W = api.opener;
					W.jQuery("#ybb").click();
					iFClose();
  				}});
			   unlock();
		}
	});	
}
/*************************�ѱ��end*************************/