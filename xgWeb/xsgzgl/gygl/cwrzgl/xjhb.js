//新疆厚博学院按语种类别分配寝室
		function selectCwForHbxy(obj,type,new_cw_count){
			var xss=document.getElementsByName("checkbox_xh");
			var cws=document.getElementsByName("checkbox_cwh");
			var xschecks = jQuery("input[name='checkbox_xh']:checked").length;//学生选中数
			var xsnums = 0;
			var qsxsnums = 0;
			var xsnamediv=document.getElementById("table_ld").getElementsByTagName("div");
			if(obj.checked){
				var xs_index=0;
				var cw_index=0;
				var tr_xs;
				var new_xs_count=0;//当前选择还未预入住的学生
				var xh_wrz = [];
				var j = 0;
				var xh_wrz_second = [];
				for(var i=0;i<cws.length;i++){
					if(xh_wrz.length > 0 && xsnums == xschecks){//第二次分配						
						if(cws[i].checked&&cws[i].parentNode.getElementsByTagName("div").length==0){
							for(;j<xh_wrz.length;j++){
								if(xh_wrz[j].checked&&xh_wrz[j].parentNode.parentNode.style.display==""
									&&xh_wrz[j].parentNode.getElementsByTagName("input")[1].value=="未入住"){
									var flg = true;
									var curryzlbdm = jQuery(xh_wrz[j]).parents("tr:eq(0)").find("td:eq(3)").find("input[name='xs_yzlbdm']").val();
									var yzlbdms = jQuery(cws[i]).parents("tr:eq(1)").find("td:eq(0)").find("input[name='yzlbdms']");
									if(curryzlbdm != null && curryzlbdm != "" && yzlbdms.length>0){//当前学生有语种类别且寝室里已住着语种类别的学生											
										jQuery(yzlbdms).each(function(i,n){
											if(jQuery(n).val() == curryzlbdm){//如果当前寝室已安排相同语种的学生
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
									new_xs_count++;//++当前选择还未预入住的学生
									break;
								}
							}
							if(!xh_wrz[j]&& xh_wrz_second.length < 1){
								if(new_xs_count==0){
									alertInfo("请选择需要入住的学生！");
								}else {
									alertInfo("选中的可入住床位数为<font color='red'>"+new_cw_count+"</font>个，<br/>"+
											"入住所选的<font color='red'>"+new_xs_count+"</font>个学生后，<br/>"+
											"仍有<font color='red'>"+(new_cw_count-new_xs_count)+"</font>个床位可入住。");
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
								if(tr_xs.cells[3].getElementsByTagName("input")[0].value != null && tr_xs.cells[3].getElementsByTagName("input")[0].value != ""){//如果有语种类别
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
							var qsh = jQuery(cws[i]).parents("tr:eq(1)").find("td:eq(0)").find("input[name='checkbox_qsh']").val();//取得当前床位所在寝室 
							for(;xs_index<xss.length;xs_index++){
								if(xss[xs_index].checked&&xss[xs_index].parentNode.parentNode.style.display==""
									&&xss[xs_index].parentNode.getElementsByTagName("input")[1].value=="未入住"){
									if(type != "qs"){										
										xsnums++;
									}
									var flg = true;
									var curryzlbdm = jQuery(xss[xs_index]).parents("tr:eq(0)").find("td:eq(3)").find("input[name='xs_yzlbdm']").val();
									var yzlbdms = jQuery(cws[i]).parents("tr:eq(1)").find("td:eq(0)").find("input[name='yzlbdms']");
									if(curryzlbdm != null && curryzlbdm != "" && yzlbdms.length>0){//当前学生有语种类别且寝室里已住着语种类别的学生											
										jQuery(yzlbdms).each(function(i,n){
											if(jQuery(n).val() == curryzlbdm){//如果当前寝室已安排相同语种的学生
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
									new_xs_count++;//++当前选择还未预入住的学生
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
									alertInfo("请选择需要入住的学生！");
								}else {
									alertInfo("选中的可入住床位数为<font color='red'>"+new_cw_count+"</font>个，<br/>"+
											"入住所选的<font color='red'>"+new_xs_count+"</font>个学生后，<br/>"+
											"仍有<font color='red'>"+(new_cw_count-new_xs_count)+"</font>个床位可入住。");
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
								if(tr_xs.cells[3].getElementsByTagName("input")[0].value != null && tr_xs.cells[3].getElementsByTagName("input")[0].value != ""){//如果有语种类别
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
				
				
				//取消由于学生不足对应的床位
				for(var i=cw_index;i<cws.length;i++){
					if(cws[i].parentNode.getElementsByTagName("div").length==0){
						cws[i].checked=false;
					}
				}
			}else{//床位单个取消
				var xsnamediv;
				var xh;
				for(var i=0;i<cws.length;i++){
					xsnamediv=cws[i].parentNode.getElementsByTagName("div");
					if(!cws[i].checked&&xsnamediv.length==1){
						if(xsnamediv[0].getElementsByTagName("input").length>0){//已入住保存的床位的div没有隐藏域
							xh=xsnamediv[0].getElementsByTagName("input")[0].value;
							document.getElementById("tr_xs_"+xh).style.display="";
							if(jQuery(xsnamediv[0]).find("input[name='xsyzlbdm']").length > 0){
							  var yzlbdms = jQuery(cws[i]).parents("tr:eq(1)").find("td:eq(0)").find("input[name='yzlbdms']");
							  	jQuery(yzlbdms).each(function(i,n){
								  if(jQuery(n).val() == jQuery(xsnamediv[0]).find("input[name='xsyzlbdm']").val()){
									  jQuery(n).remove();//删除已入住床位的语种类别
								  }								  
							  })								
							}
							xsnamediv[0].parentNode.removeChild(xsnamediv[0]);							
						}
					}
				}
			}
		}
		
		//第二次分配
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
							var num = 0;//当前学生有语种类别且寝室里已住着语种类别的学生											
							jQuery(yzlbdms).each(function(i,n){
								if(jQuery(n).val() == curryzlbdm){//如果当前寝室已安排相同语种的学生
									num++;									
								}
							})
							if(num>2){
								wrz_xs_second.push(xh_wrz[j]);
								continue;
							}
						}
						new_xs_count++;//++当前选择还未预入住的学生
						break;						
					}
					if(!xh_wrz[j]&& wrz_xs_second.length < 1){
						if(new_xs_count==0){
							alertInfo("请选择需要入住的学生！");
						}else {
							alertInfo("选中的可入住床位数为<font color='red'>"+new_cw_count+"</font>个，<br/>"+
									"入住所选的<font color='red'>"+new_xs_count+"</font>个学生后，<br/>"+
									"仍有<font color='red'>"+(new_cw_count-new_xs_count)+"</font>个床位可入住。");
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
						if(tr_xs.cells[3].getElementsByTagName("input")[0].value != null && tr_xs.cells[3].getElementsByTagName("input")[0].value != ""){//如果有语种类别
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
		
		//第三次分配
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
							var num = 0;//当前学生有语种类别且寝室里已住着语种类别的学生											
							jQuery(yzlbdms).each(function(i,n){
								if(jQuery(n).val() == curryzlbdm){//如果当前寝室已安排相同语种的学生
									num++;									
								}
							})
							if(num>2){
								wrz_xs_third.push(xh_wrz_second[j]);
								continue;
							}
						}
						new_xs_count++;//++当前选择还未预入住的学生
						break;						
					}
					if(!xh_wrz_second[j]&& wrz_xs_third.length < 1){
						if(new_xs_count==0){
							alertInfo("请选择需要入住的学生！");
						}else {
							alertInfo("选中的可入住床位数为<font color='red'>"+new_cw_count+"</font>个，<br/>"+
									"入住所选的<font color='red'>"+new_xs_count+"</font>个学生后，<br/>"+
									"仍有<font color='red'>"+(new_cw_count-new_xs_count)+"</font>个床位可入住。");
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
						if(tr_xs.cells[3].getElementsByTagName("input")[0].value != null && tr_xs.cells[3].getElementsByTagName("input")[0].value != ""){//如果有语种类别
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
		
		//最后一次插入，不做语种判断
		function fpcwForLastTime(wrz_xs_third,new_cw_count,new_xs_count){
			var j = 0;
			var cws=document.getElementsByName("checkbox_cwh");
			for(var i=0;i<cws.length;i++){	
				if(cws[i].checked&&cws[i].parentNode.getElementsByTagName("div").length==0){				
					for(;j<wrz_xs_third.length;j++){
						new_xs_count++;//++当前选择还未预入住的学生
						break;						
					}
					if(!wrz_xs_third[j]){
						if(new_xs_count==0){
							alertInfo("请选择需要入住的学生！");
						}else {
							alertInfo("选中的可入住床位数为<font color='red'>"+new_cw_count+"</font>个，<br/>"+
									"入住所选的<font color='red'>"+new_xs_count+"</font>个学生后，<br/>"+
									"仍有<font color='red'>"+(new_cw_count-new_xs_count)+"</font>个床位可入住。");
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
						if(tr_xs.cells[3].getElementsByTagName("input")[0].value != null && tr_xs.cells[3].getElementsByTagName("input")[0].value != ""){//如果有语种类别
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
		
		
	