//选中项目的className
var selectedClass = "aa";
//查询功能路径
var url = "";
//加载学院
if(ele("CNLTreeMenu1")){
	var d_html = "";
	var id = "xxid";
	var obj = ele(id);
	var data = [];
	var count = 0;
	//学院数据从学院下拉列表中拷贝
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

//加载时如果已经有了学院、专业、班级条件。则树结构中要默认选中对应的数据
defaultClick();

//==========树形结构================
function appendChilds(zyObj){	
	if(zyObj != null && zyObj != undefined && zyObj.id != null && zyObj.id != undefined){
		var id = zyObj.id;
		var parentId = zyObj.parentNode.parentNode.id;
		var bmlb = id.substring(0,2);
		var bmdm = id.substring(2,id.length);
		var xydm = parentId.substring(2,parentId.length);
		dwr.engine.setAsync(false);
		if(zyObj.children && zyObj.children.length<3){
		   if("xy" == bmlb){//加载专业
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
			if("zy" == bmlb){//加载班级信息
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
	if(bmlb=="xy"){//点击学院
		setVal('xy',bmdm);
		setVal('zy','');
		setVal('bj','');
		
	}
	if(bmlb=='zy'){//点击专业
		setVal('xy',parentdm);
		setVal('zy',bmdm);
		setVal('bj','');
	}
	if(bmlb=='bj'){//点击班级
		var zyli = ob.parentNode.parentNode.parentNode;
		var xyli = zyli.parentNode.parentNode;
		xyid = xyli.id;
		setVal('xy',xyid.substring(2,xyid.length));
		setVal('zy',parentdm);
		setVal('bj',bmdm);
	}
	//查询
	allNotEmpThenGo(url);
}

function defaultClick(){	
	try{
		if(val('xy') != null && val('xy') != "" && val('zy') == "" && val('bj') == ""){
			//选择学院
			ele("xy"+val('xy')).children[1].className = selectedClass;
		}
		if(val('zy') != null && val('zy') != "" && val('bj') == ""){
			//点击学院
			dwr.engine.setAsync(false);
			ele('xy'+val('xy')).click();
			//学院打开
			ExCls(ele('xy'+val('xy')).firstChild,'Opened','Closed',1);
			//选择专业
			ele("zy"+val('zy')).children[1].className = selectedClass;
			dwr.engine.setAsync(true);
		}
		if(val('bj') != null && val('bj') != ""){
			//选择班级
			//首先加载班级
			dwr.engine.setAsync(false);
			ele('xy'+val('xy')).click();
			//学院打开
			ExCls(ele('xy'+val('xy')).firstChild,'Opened','Closed',1);
			ele('zy'+val('zy')).click();
			//专业打开
			ExCls(ele('zy'+val('zy')).firstChild,'Opened','Closed',1);
			//然后选中班级
			ele("bj"+val('bj')).children[1].className = selectedClass;
			dwr.engine.setAsync(true);		
		}
	}catch(e){
		
	}
		
}