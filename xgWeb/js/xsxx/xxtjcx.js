function thisClick(id){	
	var nameStr = ele(id).name
	var bmlb = nameStr.substring(0,2);
	var bmdm = nameStr.substring(2,nameStr.length);
	
	if("zy" == bmlb){
		//���רҵʱ����Ϊרҵ����û�����ݣ����Ի����һ��������ÿ�������
		idIndex = ele(id).id.substring(2,ele(id).id.length);
		eDiv	= document.getElementById('dd'+ idIndex);
		eDiv.style.display = 'none';
		//end
		
		//�����רҵʱ�����רҵ���������ݣ�������ɾ��
		var len = ele(id).parentNode.childNodes.length;				
		for(var i=0; i<len; i++){
			var next = ele(id).nextSibling;
			if(next != null && next != undefined && next.id.substring(0,2) == 'bj'){
				ele(id).parentNode.removeChild(next);
			}else{
				break;
			}
		}
		//end
		
		if(len ==4){
			//aIndex�ĳ��ȴ���������
			d.aIndent = ['1','1'];
			d.aIndent.pop();
			d.aIndent.push(1);
			GetListData.getBjList("%"+''+"%!!-!!%"+bmdm+"%!!-!!%"+val('nj')+"%",val('userName'),val('isFdy'),val('isBzr'),function(bjData){
				for(var k =1; k<bjData.length; k++){
					d.add("bj"+bjData[k].dm,"zy"+bmdm,bjData[k].mc,'#','onClickAct');
					var div = document.createElement("div");
					div.id = "bj_"+bjData[k].dm;
					if(k == bjData.length-1){
						d.aNodes[d.aNodes.length-1]._ls = true;
					}
					div.innerHTML=d.node(d.aNodes[d.aNodes.length-1],d.aNodes.length-1);									
					ele(id).parentNode.appendChild(div);
				}
			});						
		}
	}
}

function onClickAct(id){
	var nameStr = ele(id).name
	var bmlb = nameStr.substring(0,2);
	var bmdm = nameStr.substring(2,nameStr.length);
	
	//ɾ��
	if(ele('xxP').firstChild){
		ele('xxP').removeChild(ele('xxP').firstChild);
	}
	if("xy" == bmlb){
		//����רҵ��Ϣ		
		xsxxtjDWR.getZyGoupList(bmdm,val('fdyQx'),val('userName'),function(data){			
			//����
			var table = document.createElement("div");
			var dHtml = "<table class=formlist>";
			dHtml += getBodyHTML(data,"רҵ");
			dHtml += "</table>"
			
			table.innerHTML = dHtml;
			ele('xxP').appendChild(table);	
		});
	}
	if("zy" == bmlb){
		//���ذ༶
		xsxxtjDWR.getBjGoupList(bmdm,val('fdyQx'),val('userName'),function(data){			
			//����
			var table = document.createElement("div");
			var dHtml = "<table class=formlist>";
			dHtml += getBodyHTML(data,"�༶");
			dHtml += "</table>"
			
			table.innerHTML = dHtml;
			ele('xxP').appendChild(table);	
		});
	}
	if("bj" == bmlb){//����ѧ��
		
	}
}

//˫���¼�
function dbclickAction(id){
	var nameStr = ele(id).name
	var bmlb = nameStr.substring(0,2);
	var bmdm = nameStr.substring(2,nameStr.length);
	
	if("bj" == bmlb){
		refreshForm('xsxxcx.do?method=bjxsxx&bjdm='+bmdm);
	}
}

function getBodyHTML(data,bm){
	var mHtml = "";
	
	for(var i=0; i<data.length; i++){
		var trHtml = "<tr><td><table class=formlist>";
		var dHtml = "";
		var hHtml = "";
		
		for(var k =0; k<data[i].length; k++){			
			dHtml += "<tr>";
			if(data[i][k].en == bm+'����' && hHtml == ''){
				hHtml += "<thead><tr><th colspan='4'>";
				hHtml += data[i][k].cn;
				hHtml += "</th><tr></thead>";
				k++;
			}
			if(data[i][k]!= null && data[i][k]!= undefined && data[i][k].en!=bm+'����' && data[i][k].en != bm+'����'){				
				dHtml += "<th>";
				dHtml += data[i][k].en;
				dHtml += "</th>";
				dHtml += "<td>";
				dHtml += data[i][k++].cn;
				dHtml += "</td>";		
			}else{
				dHtml += "<th></th>";
				dHtml += "<td></td>";
			}
			if(data[i][k].en == bm+'����' && hHtml == ''){
				hHtml += "<thead><tr><th colspan='4'><span>";
				hHtml += data[i][k].cn;
				hHtml += "</span></th><tr></thead>";
				k++;
			}
			if(data[i][k] != null && data[i][k] != undefined && data[i][k].en!=bm+'����' && data[i][k].en != bm+'����'){				
				dHtml += "<th>";
				dHtml += data[i][k].en;
				dHtml += "</th>";
				dHtml += "<td>";
				dHtml += data[i][k].cn;
				dHtml += "</td>";
			}else{
				dHtml += "<th></th>";
				dHtml += "<td></td>";
			}
			dHtml += "</tr>";
		}
		trHtml += hHtml + "<tbody>" + dHtml + "</tbody>" + "</table></td></tr>";
		mHtml += trHtml;
	}
	
	return mHtml;
}
