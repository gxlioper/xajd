//�½���ѧԺ����������������
		function selectCwForHbxy(obj,type,new_cw_count){
			var xss=document.getElementsByName("checkbox_xh");
			var cws=document.getElementsByName("checkbox_cwh");
			var xschecks = jQuery("input[name='checkbox_xh']:checked").length;//ѧ��ѡ����
			var xsnums = 0;
			var qsxsnums = 0;
			var xsnamediv=document.getElementById("table_ld").getElementsByTagName("div");
			if(obj.checked){
				var xs_index=0;
				var cw_index=0;
				var tr_xs;
				var new_xs_count=0;//��ǰѡ��δԤ��ס��ѧ��
				var xh_wrz = [];
				var j = 0;
				var xh_wrz_second = [];
				for(var i=0;i<cws.length;i++){
					if(xh_wrz.length > 0 && xsnums == xschecks){//�ڶ��η���						
						if(cws[i].checked&&cws[i].parentNode.getElementsByTagName("div").length==0){
							for(;j<xh_wrz.length;j++){
								if(xh_wrz[j].checked&&xh_wrz[j].parentNode.parentNode.style.display==""
									&&xh_wrz[j].parentNode.getElementsByTagName("input")[1].value=="δ��ס"){
									var flg = true;
									var curryzlbdm = jQuery(xh_wrz[j]).parents("tr:eq(0)").find("td:eq(3)").find("input[name='xs_yzlbdm']").val();
									var yzlbdms = jQuery(cws[i]).parents("tr:eq(1)").find("td:eq(0)").find("input[name='yzlbdms']");
									if(curryzlbdm != null && curryzlbdm != "" && yzlbdms.length>0){//��ǰѧ���������������������ס����������ѧ��											
										jQuery(yzlbdms).each(function(i,n){
											if(jQuery(n).val() == curryzlbdm){//�����ǰ�����Ѱ�����ͬ���ֵ�ѧ��
												xh_wrz_second.push(xh_wrz[j]);
												flg = false;
												return false;
											}
										})
										if(!flg){
											continue;
										}
									}
//									}
									new_xs_count++;//++��ǰѡ��δԤ��ס��ѧ��
									break;
								}
							}
							if(!xh_wrz[j]&& xh_wrz_second.length < 1){
								if(new_xs_count==0){
									alertInfo("��ѡ����Ҫ��ס��ѧ����");
								}else {
									alertInfo("ѡ�еĿ���ס��λ��Ϊ<font color='red'>"+new_cw_count+"</font>����<br/>"+
											"��ס��ѡ��<font color='red'>"+new_xs_count+"</font>��ѧ����<br/>"+
											"����<font color='red'>"+(new_cw_count-new_xs_count)+"</font>����λ����ס��");
								}
								break;
							};
							if(j==xh_wrz.length){
								fpcwForThirdTime(xh_wrz_second,new_cw_count,new_xs_count);
								break;
							}else{								
								tr_xs = xh_wrz[j].parentNode.parentNode;
								var div=document.createElement("div");
								div.name="xsxsnamediv";
								div.id="div_xs_"+xh_wrz[j].value;
								div.onmouseover=function(){BatAlert.showInfo(tr_xs.cells[2].getElementsByTagName("input")[0].value);};
								div.onmouseout=BatAlert.showInfo;						
								if(tr_xs.cells[3].getElementsByTagName("input")[0].value != null && tr_xs.cells[3].getElementsByTagName("input")[0].value != ""){//������������
									jQuery(cws[i]).parents("tr:eq(1)").find("td:eq(0)").append("<input type='hidden' name='yzlbdms' value='"+tr_xs.cells[3].getElementsByTagName("input")[0].value+"'></input>");
									div.innerHTML="<input type='hidden' name='div_xs_xh' value='"+xh_wrz[j].value+"'/><nobr>"+tr_xs.cells[2].innerText+"</nobr>"+"</br>"+tr_xs.cells[1].innerHTML+"</br>"+tr_xs.cells[3].innerText+"<input type='hidden' name='xsyzlbdm' value='"+jQuery(xh_wrz[j]).parents("tr:eq(0)").find("td:eq(3)").find("input[name='xs_yzlbdm']").val()+"'>";						
								}else{
									div.innerHTML="<input type='hidden' name='div_xs_xh' value='"+xh_wrz[j].value+"'/><nobr>"+tr_xs.cells[2].innerText+"</nobr>"+"</br>"+tr_xs.cells[1].innerHTML+"</br>"+tr_xs.cells[3].innerText;
								}
								cws[i].parentNode.appendChild(div);
								document.getElementById("tr_xs_"+xh_wrz[j].value).style.display="none";
								j++;
							}
						}
					}else{						
						if(cws[i].checked&&cws[i].parentNode.getElementsByTagName("div").length==0){						
							var qsh = jQuery(cws[i]).parents("tr:eq(1)").find("td:eq(0)").find("input[name='checkbox_qsh']").val();//ȡ�õ�ǰ��λ�������� 
							for(;xs_index<xss.length;xs_index++){
								if(xss[xs_index].checked&&xss[xs_index].parentNode.parentNode.style.display==""
									&&xss[xs_index].parentNode.getElementsByTagName("input")[1].value=="δ��ס"){
									if(type != "qs"){										
										xsnums++;
									}
									var flg = true;
									var curryzlbdm = jQuery(xss[xs_index]).parents("tr:eq(0)").find("td:eq(3)").find("input[name='xs_yzlbdm']").val();
									var yzlbdms = jQuery(cws[i]).parents("tr:eq(1)").find("td:eq(0)").find("input[name='yzlbdms']");
									if(curryzlbdm != null && curryzlbdm != "" && yzlbdms.length>0){//��ǰѧ���������������������ס����������ѧ��											
										jQuery(yzlbdms).each(function(i,n){
											if(jQuery(n).val() == curryzlbdm){//�����ǰ�����Ѱ�����ͬ���ֵ�ѧ��
												xh_wrz.push(xss[xs_index]);
												flg = false;
												return false;
											}
										})
										if(!flg){
											if(type == "qs"){
												qsxsnums++;
											}
											continue;
										}
									}
									new_xs_count++;//++��ǰѡ��δԤ��ס��ѧ��
									break;
								}
							}
//							if(type == "qs"){
//								xsnums--;
//							}
							if(!xss[xs_index] && type == "qs" && xh_wrz.length > 0 && qsxsnums == xh_wrz.length){
								fpcwForSecondTime(xh_wrz,new_cw_count,new_xs_count);
								break;
							}
							if(!xss[xs_index]&& xh_wrz.length < 1){
								if(new_xs_count==0){
									alertInfo("��ѡ����Ҫ��ס��ѧ����");
								}else {
									alertInfo("ѡ�еĿ���ס��λ��Ϊ<font color='red'>"+new_cw_count+"</font>����<br/>"+
											"��ס��ѡ��<font color='red'>"+new_xs_count+"</font>��ѧ����<br/>"+
											"����<font color='red'>"+(new_cw_count-new_xs_count)+"</font>����λ����ס��");
								}
								break
							}
							if(xss[xs_index]){								
								tr_xs=xss[xs_index].parentNode.parentNode;
								var div=document.createElement("div");
								div.name="xsxsnamediv";
								div.id="div_xs_"+xss[xs_index].value;
								div.onmouseover=function(){BatAlert.showInfo(tr_xs.cells[2].getElementsByTagName("input")[0].value);};
								div.onmouseout=BatAlert.showInfo;						
								if(tr_xs.cells[3].getElementsByTagName("input")[0].value != null && tr_xs.cells[3].getElementsByTagName("input")[0].value != ""){//������������
									jQuery(cws[i]).parents("tr:eq(1)").find("td:eq(0)").append("<input type='hidden' name='yzlbdms' value='"+tr_xs.cells[3].getElementsByTagName("input")[0].value+"'></input>");
									div.innerHTML="<input type='hidden' name='div_xs_xh' value='"+xss[xs_index].value+"'/><nobr>"+tr_xs.cells[2].innerText+"</nobr>"+"</br>"+tr_xs.cells[1].innerHTML+"</br>"+tr_xs.cells[3].innerText+"<input type='hidden' name='xsyzlbdm' value='"+jQuery(xss[xs_index]).parents("tr:eq(0)").find("td:eq(3)").find("input[name='xs_yzlbdm']").val()+"'>";						
								}else{
									div.innerHTML="<input type='hidden' name='div_xs_xh' value='"+xss[xs_index].value+"'/><nobr>"+tr_xs.cells[2].innerText+"</nobr>"+"</br>"+tr_xs.cells[1].innerHTML+"</br>"+tr_xs.cells[3].innerText;
								}
								cws[i].parentNode.appendChild(div);
								document.getElementById("tr_xs_"+xss[xs_index].value).style.display="none";
								xs_index++;
							}
						}
					}
					cw_index++;
				}
				
				
				//ȡ������ѧ�������Ӧ�Ĵ�λ
				for(var i=cw_index;i<cws.length;i++){
					if(cws[i].parentNode.getElementsByTagName("div").length==0){
						cws[i].checked=false;
					}
				}
			}else{//��λ����ȡ��
				var xsnamediv;
				var xh;
				for(var i=0;i<cws.length;i++){
					xsnamediv=cws[i].parentNode.getElementsByTagName("div");
					if(!cws[i].checked&&xsnamediv.length==1){
						if(xsnamediv[0].getElementsByTagName("input").length>0){//����ס����Ĵ�λ��divû��������
							xh=xsnamediv[0].getElementsByTagName("input")[0].value;
							document.getElementById("tr_xs_"+xh).style.display="";
							if(jQuery(xsnamediv[0]).find("input[name='xsyzlbdm']").length > 0){
							  var yzlbdms = jQuery(cws[i]).parents("tr:eq(1)").find("td:eq(0)").find("input[name='yzlbdms']");
							  	jQuery(yzlbdms).each(function(i,n){
								  if(jQuery(n).val() == jQuery(xsnamediv[0]).find("input[name='xsyzlbdm']").val()){
									  jQuery(n).remove();//ɾ������ס��λ���������
								  }								  
							  })								
							}
							xsnamediv[0].parentNode.removeChild(xsnamediv[0]);							
						}
					}
				}
			}
		}
		
		//�ڶ��η���
		function fpcwForSecondTime(xh_wrz,new_cw_count,new_xs_count){
			var j = 0;
			var wrz_xs_second = [];
			var cws=document.getElementsByName("checkbox_cwh");
			for(var i=0;i<cws.length;i++){	
				if(cws[i].checked&&cws[i].parentNode.getElementsByTagName("div").length==0){				
					for(;j<xh_wrz.length;j++){
						var curryzlbdm = jQuery(xh_wrz[j]).parents("tr:eq(0)").find("td:eq(3)").find("input[name='xs_yzlbdm']").val();
						var yzlbdms = jQuery(cws[i]).parents("tr:eq(1)").find("td:eq(0)").find("input[name='yzlbdms']");
						if(curryzlbdm != null && curryzlbdm != "" && yzlbdms.length>0){
							var num = 0;//��ǰѧ���������������������ס����������ѧ��											
							jQuery(yzlbdms).each(function(i,n){
								if(jQuery(n).val() == curryzlbdm){//�����ǰ�����Ѱ�����ͬ���ֵ�ѧ��
									num++;									
								}
							})
							if(num>2){
								wrz_xs_second.push(xh_wrz[j]);
								continue;
							}
						}
						new_xs_count++;//++��ǰѡ��δԤ��ס��ѧ��
						break;						
					}
					if(!xh_wrz[j]&& wrz_xs_second.length < 1){
						if(new_xs_count==0){
							alertInfo("��ѡ����Ҫ��ס��ѧ����");
						}else {
							alertInfo("ѡ�еĿ���ס��λ��Ϊ<font color='red'>"+new_cw_count+"</font>����<br/>"+
									"��ס��ѡ��<font color='red'>"+new_xs_count+"</font>��ѧ����<br/>"+
									"����<font color='red'>"+(new_cw_count-new_xs_count)+"</font>����λ����ס��");
						}
						break;
					}
					if(j==xh_wrz.length){
						fpcwForThirdTime(wrz_xs_second,new_cw_count,new_xs_count);
						break;
					}
					
					if(xh_wrz[j]){						
						tr_xs=(xh_wrz[j].parentNode).parentNode;
						var div=document.createElement("div");
						div.name="xsxsnamediv";
						div.id="div_xs_"+xh_wrz[j].value;
						div.onmouseover=function(){BatAlert.showInfo(tr_xs.cells[2].getElementsByTagName("input")[0].value);};
						div.onmouseout=BatAlert.showInfo;						
						if(tr_xs.cells[3].getElementsByTagName("input")[0].value != null && tr_xs.cells[3].getElementsByTagName("input")[0].value != ""){//������������
							jQuery(cws[i]).parents("tr:eq(1)").find("td:eq(0)").append("<input type='hidden' name='yzlbdms' value='"+tr_xs.cells[3].getElementsByTagName("input")[0].value+"'></input>");
							div.innerHTML="<input type='hidden' name='div_xs_xh' value='"+xh_wrz[j].value+"'/><nobr>"+tr_xs.cells[2].innerText+"</nobr>"+"</br>"+tr_xs.cells[1].innerHTML+"</br>"+tr_xs.cells[3].innerText+"<input type='hidden' name='xsyzlbdm' value='"+jQuery(xh_wrz[j]).parents("tr:eq(0)").find("td:eq(3)").find("input[name='xs_yzlbdm']").val()+"'>";						
						}else{
							div.innerHTML="<input type='hidden' name='div_xs_xh' value='"+xh_wrz[j].value+"'/><nobr>"+tr_xs.cells[2].innerText+"</nobr>"+"</br>"+tr_xs.cells[1].innerHTML+"</br>"+tr_xs.cells[3].innerText;
						}
						cws[i].parentNode.appendChild(div);
						document.getElementById("tr_xs_"+xh_wrz[j].value).style.display="none";
						j++;
					}
				}
			}			
		}
		
		//�����η���
		function fpcwForThirdTime(xh_wrz_second,new_cw_count,new_xs_count){
			var j = 0;
			var wrz_xs_third = [];
			var cws=document.getElementsByName("checkbox_cwh");
			for(var i=0;i<cws.length;i++){	
				if(cws[i].checked&&cws[i].parentNode.getElementsByTagName("div").length==0){				
					for(;j<xh_wrz_second.length;j++){
						var curryzlbdm = jQuery(xh_wrz_second[j]).parents("tr:eq(0)").find("td:eq(3)").find("input[name='xs_yzlbdm']").val();
						var yzlbdms = jQuery(cws[i]).parents("tr:eq(1)").find("td:eq(0)").find("input[name='yzlbdms']");
						if(curryzlbdm != null && curryzlbdm != "" && yzlbdms.length>0){
							var num = 0;//��ǰѧ���������������������ס����������ѧ��											
							jQuery(yzlbdms).each(function(i,n){
								if(jQuery(n).val() == curryzlbdm){//�����ǰ�����Ѱ�����ͬ���ֵ�ѧ��
									num++;									
								}
							})
							if(num>2){
								wrz_xs_third.push(xh_wrz_second[j]);
								continue;
							}
						}
						new_xs_count++;//++��ǰѡ��δԤ��ס��ѧ��
						break;						
					}
					if(!xh_wrz_second[j]&& wrz_xs_third.length < 1){
						if(new_xs_count==0){
							alertInfo("��ѡ����Ҫ��ס��ѧ����");
						}else {
							alertInfo("ѡ�еĿ���ס��λ��Ϊ<font color='red'>"+new_cw_count+"</font>����<br/>"+
									"��ס��ѡ��<font color='red'>"+new_xs_count+"</font>��ѧ����<br/>"+
									"����<font color='red'>"+(new_cw_count-new_xs_count)+"</font>����λ����ס��");
						}
						break;
					}
					if(j==xh_wrz_second.length){
						fpcwForLastTime(wrz_xs_third,new_cw_count,new_xs_count);
						break;
					}
					
					if(xh_wrz_second[j]){						
						tr_xs=(xh_wrz_second[j].parentNode).parentNode;
						var div=document.createElement("div");
						div.name="xsxsnamediv";
						div.id="div_xs_"+xh_wrz_second[j].value;
						div.onmouseover=function(){BatAlert.showInfo(tr_xs.cells[2].getElementsByTagName("input")[0].value);};
						div.onmouseout=BatAlert.showInfo;						
						if(tr_xs.cells[3].getElementsByTagName("input")[0].value != null && tr_xs.cells[3].getElementsByTagName("input")[0].value != ""){//������������
							jQuery(cws[i]).parents("tr:eq(1)").find("td:eq(0)").append("<input type='hidden' name='yzlbdms' value='"+tr_xs.cells[3].getElementsByTagName("input")[0].value+"'></input>");
							div.innerHTML="<input type='hidden' name='div_xs_xh' value='"+xh_wrz_second[j].value+"'/><nobr>"+tr_xs.cells[2].innerText+"</nobr>"+"</br>"+tr_xs.cells[1].innerHTML+"</br>"+tr_xs.cells[3].innerText+"<input type='hidden' name='xsyzlbdm' value='"+jQuery(xh_wrz_second[j]).parents("tr:eq(0)").find("td:eq(3)").find("input[name='xs_yzlbdm']").val()+"'>";						
						}else{
							div.innerHTML="<input type='hidden' name='div_xs_xh' value='"+xh_wrz_second[j].value+"'/><nobr>"+tr_xs.cells[2].innerText+"</nobr>"+"</br>"+tr_xs.cells[1].innerHTML+"</br>"+tr_xs.cells[3].innerText;
						}
						cws[i].parentNode.appendChild(div);
						document.getElementById("tr_xs_"+xh_wrz_second[j].value).style.display="none";
						j++;
					}
				}
			}			
		}
		
		//���һ�β��룬���������ж�
		function fpcwForLastTime(wrz_xs_third,new_cw_count,new_xs_count){
			var j = 0;
			var cws=document.getElementsByName("checkbox_cwh");
			for(var i=0;i<cws.length;i++){	
				if(cws[i].checked&&cws[i].parentNode.getElementsByTagName("div").length==0){				
					for(;j<wrz_xs_third.length;j++){
						new_xs_count++;//++��ǰѡ��δԤ��ס��ѧ��
						break;						
					}
					if(!wrz_xs_third[j]){
						if(new_xs_count==0){
							alertInfo("��ѡ����Ҫ��ס��ѧ����");
						}else {
							alertInfo("ѡ�еĿ���ס��λ��Ϊ<font color='red'>"+new_cw_count+"</font>����<br/>"+
									"��ס��ѡ��<font color='red'>"+new_xs_count+"</font>��ѧ����<br/>"+
									"����<font color='red'>"+(new_cw_count-new_xs_count)+"</font>����λ����ס��");
						}
						break;
					};
					if(wrz_xs_third[j]){						
						tr_xs=(wrz_xs_third[j].parentNode).parentNode;
						var div=document.createElement("div");
						div.name="xsxsnamediv";
						div.id="div_xs_"+wrz_xs_third[j].value;
						div.onmouseover=function(){BatAlert.showInfo(tr_xs.cells[2].getElementsByTagName("input")[0].value);};
						div.onmouseout=BatAlert.showInfo;						
						if(tr_xs.cells[3].getElementsByTagName("input")[0].value != null && tr_xs.cells[3].getElementsByTagName("input")[0].value != ""){//������������
							jQuery(cws[i]).parents("tr:eq(1)").find("td:eq(0)").append("<input type='hidden' name='yzlbdms' value='"+tr_xs.cells[3].getElementsByTagName("input")[0].value+"'></input>");
							div.innerHTML="<input type='hidden' name='div_xs_xh' value='"+wrz_xs_third[j].value+"'/><nobr>"+tr_xs.cells[2].innerText+"</nobr>"+"</br>"+tr_xs.cells[1].innerHTML+"</br>"+tr_xs.cells[3].innerText+"<input type='hidden' name='xsyzlbdm' value='"+jQuery(wrz_xs_third[j]).parents("tr:eq(0)").find("td:eq(3)").find("input[name='xs_yzlbdm']").val()+"'>";						
						}else{
							div.innerHTML="<input type='hidden' name='div_xs_xh' value='"+wrz_xs_third[j].value+"'/><nobr>"+tr_xs.cells[2].innerText+"</nobr>"+"</br>"+tr_xs.cells[1].innerHTML+"</br>"+tr_xs.cells[3].innerText;
						}
						cws[i].parentNode.appendChild(div);
						document.getElementById("tr_xs_"+wrz_xs_third[j].value).style.display="none";
						j++;
					}
				}
			}	
		}
		
		
	