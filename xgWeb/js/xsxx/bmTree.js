//ѡ����Ŀ��className
var selectedClass = "aa";
//��ѯ����·��
var url = "";
//����ѧԺ
if(ele("CNLTreeMenu1")){
	var d_html = "";
	var id = "xxid";
	var obj = ele(id);
	var data = [];
	var count = 0;
	//ѧԺ���ݴ�ѧԺ�����б��п���
	for(var s=0; s<ele("xy").options.length; s++){
		mc = ele("xy").options[s].text;
		dm = ele("xy").options[s].value;
		if(dm != ""){
			data[count++] = {dm:dm, mc:mc};
		}
	}
	for(var i=0; i<data.length; i++){
		if(data[i].dm != "xy"){
			d_html += "<li id='xy" + data[i].dm + "' onclick='appendChilds(this);'><a href=\"#\" onclick=\"clickBm(this,'xy" + data[i].dm+ "');\">" + data[i].mc+ "</a>";
			d_html += "</li>";
		}
	}
	var ul = document.createElement("ul");
	ul.innerHTML = d_html;
	obj.appendChild(ul);
	MyCNLTreeMenu1.InitCss("Opened","Closed","Child","images/s.gif");
}

//����ʱ����Ѿ�����ѧԺ��רҵ���༶�����������ṹ��ҪĬ��ѡ�ж�Ӧ������
defaultClick();

//==========���νṹ================
function appendChilds(zyObj){	
	if(zyObj != null && zyObj != undefined && zyObj.id != null && zyObj.id != undefined){
		var id = zyObj.id;
		var parentId = zyObj.parentNode.parentNode.id;
		var bmlb = id.substring(0,2);
		var bmdm = id.substring(2,id.length);
		var xydm = parentId.substring(2,parentId.length);
		dwr.engine.setAsync(false);
		if(zyObj.children && zyObj.children.length<3){
		   if("xy" == bmlb){//����רҵ
//			   var nj = val('nj');
			   var nj = '';
			   GetListData.getZyList(bmdm,nj,val('userName'),val('isFdy'),val('isBzr'),function(zydata){					
				   var d_html = "";
				   for(var m=0; m<zydata.length; m++){						
						if(zydata[m].dm != "zy"){
							d_html += "<li onclick='appendChilds(this)' id='zy" + zydata[m].dm + "'><a onclick=\"clickBm(this,'zy" + zydata[m].dm + "')\">" + zydata[m].mc+ "</a></li>";
						}
					}
					var ul = document.createElement("ul");
					ul.innerHTML = d_html;
					zyObj.appendChild(ul);
					MyCNLTreeMenu1.InitCss("Opened","Closed","Child","images/s.gif",id);
					
				});
		   }
			if("zy" == bmlb){//���ذ༶��Ϣ
				var xy = xydm === "" ? "%" : xydm;
				var zy = bmdm === "" ? "%" : bmdm;
//				var nj = val("nj") === "" ? "%" : val("nj");
				var nj ='%'; 
				var query = xy+"!!-!!"+zy+"!!-!!"+nj;
				GetListData.getBjList(query,val('userName'),val('isFdy'),val('isBzr'),function (data){
					var d_html = "";
					for(var n=0; n<data.length; n++){
						if(data[n].dm != "bj"){
							d_html += "<li class='Child' id='bj" + data[n].dm + "'><a href='#1' onclick=\"clickBm(this,'bj" + data[n].dm+ "')\">" + data[n].mc+ "</a></li>";
						}
					}
					var ul = document.createElement("ul");
					ul.innerHTML = d_html;
					zyObj.appendChild(ul);
					MyCNLTreeMenu1.InitCss("Opened","Closed","Child","images/s.gif",id);
				});
			} 
		 }
		dwr.engine.setAsync(true);
	}
}

function clickBm(ob,id){
	var parentId = ob.parentNode.parentNode.parentNode.id;
	var bmlb = id.substring(0,2);
	var bmdm = id.substring(2,id.length);
	var parentdm = parentId.substring(2,parentId.length);
	if(bmlb=="xx"){
		setVal('xy','');
		setVal('zy','');
		setVal('bj','');
	}
	if(bmlb=="xy"){//���ѧԺ
		setVal('xy',bmdm);
		setVal('zy','');
		setVal('bj','');
		
	}
	if(bmlb=='zy'){//���רҵ
		setVal('xy',parentdm);
		setVal('zy',bmdm);
		setVal('bj','');
	}
	if(bmlb=='bj'){//����༶
		var zyli = ob.parentNode.parentNode.parentNode;
		var xyli = zyli.parentNode.parentNode;
		xyid = xyli.id;
		setVal('xy',xyid.substring(2,xyid.length));
		setVal('zy',parentdm);
		setVal('bj',bmdm);
	}
	//��ѯ
	allNotEmpThenGo(url);
}

function defaultClick(){	
	try{
		if(val('xy') != null && val('xy') != "" && val('zy') == "" && val('bj') == ""){
			//ѡ��ѧԺ
			ele("xy"+val('xy')).children[1].className = selectedClass;
		}
		if(val('zy') != null && val('zy') != "" && val('bj') == ""){
			//���ѧԺ
			dwr.engine.setAsync(false);
			ele('xy'+val('xy')).click();
			//ѧԺ��
			ExCls(ele('xy'+val('xy')).firstChild,'Opened','Closed',1);
			//ѡ��רҵ
			ele("zy"+val('zy')).children[1].className = selectedClass;
			dwr.engine.setAsync(true);
		}
		if(val('bj') != null && val('bj') != ""){
			//ѡ��༶
			//���ȼ��ذ༶
			dwr.engine.setAsync(false);
			ele('xy'+val('xy')).click();
			//ѧԺ��
			ExCls(ele('xy'+val('xy')).firstChild,'Opened','Closed',1);
			ele('zy'+val('zy')).click();
			//רҵ��
			ExCls(ele('zy'+val('zy')).firstChild,'Opened','Closed',1);
			//Ȼ��ѡ�а༶
			ele("bj"+val('bj')).children[1].className = selectedClass;
			dwr.engine.setAsync(true);		
		}
	}catch(e){
		
	}
		
}