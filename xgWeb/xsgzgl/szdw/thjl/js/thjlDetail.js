     	function save(){
	        var parameter ={};
			var url = "";
			if(jQuery("#doType").val()=="add" || jQuery("#doType").val()==""){
				if( jQuery("#xh").val()==""){
					return showAlert("�뽫��<font color='red'>*</font>����Ŀ��д������");
		     	}
			 	url = "szdw_thjl.do?method=saveThjlInfo&doType=add";
			 	parameter["xh"]=jQuery("#xh").val();
			 	parameter["zgh"]=jQuery("#zgh").val();
			}else if(jQuery("#doType").val()=="update"){
				url= "szdw_thjl.do?method=updateThjlInfo";
				parameter["id"]=jQuery("#id").val();
			}

			if(jQuery("#thsj").val()=="" || jQuery("#kssj").val()=="" || jQuery("#jssj").val()=="" || jQuery("#thlx").val()==""){
				return showAlert("�뽫��<font color='red'>*</font>����Ŀ��д������");
	     	}
			
			// ����ע�Ļ��������ע�ȼ�
     		/*clearGzdj();
     		var sfzdgzVal = jQuery("input:radio[name=sfzdgz]:checked").val();
     		var gzdjVal = jQuery("input:radio[name=gzdj]:checked").val();
			if(sfzdgzVal == '1' && (gzdjVal == undefined || gzdjVal =='')){
				showAlert("�뽫��<font color='red'>*</font>����Ŀ��д������");
				return false;
			}*/
			parameter["thsj"]=jQuery("#thsj").val();
			parameter["kssj"]=jQuery("#kssj").val();
			parameter["jssj"]=jQuery("#jssj").val();
			parameter["thsc"]=jQuery("#thsc").val();
			parameter["thlx"]=jQuery("#thlx").val();

			// parameter["mtyd"]=encodeURI(encodeURI(jQuery("#mtyd").val()));
			parameter["thnr"]=encodeURI(encodeURI(jQuery("#thnr").val()));
            parameter["yyfx"]=encodeURI(encodeURI(jQuery("#yyfx").val()));
            parameter["gjcs"]=encodeURI(encodeURI(jQuery("#gjcs").val()));
            parameter["qtjy"]=encodeURI(encodeURI(jQuery("#qtjy").val()));

            // parameter["khhwt"]=encodeURI(encodeURI(jQuery("#khhwt").val()));
			parameter["sfzdgz"] = jQuery("input:radio[name=sfzdgz]:checked").val();
            parameter["sfsdkt"] = jQuery("input:radio[name=sfsdkt]:checked").val();
            parameter["sdktsj"] = jQuery("#sdktsj").val();
            parameter["filepath"] = jQuery("#filepath").val();
			// parameter["gzdj"] = encodeURI(encodeURI(jQuery("input:radio[name=gzdj]:checked").val()));
			jQuery.ajaxSetup({async:false});
			jQuery.post(url,parameter,
				function(result){
					if(result == "true"){
						showAlert("����ɹ���",parameter,{"clkFun":function(){
									frameElement.api.opener.refreshForm("szdw_thjl_thjl.do");
									window.close();
							}});
					}else{
						showAlert("����ʧ�ܣ�");
						return false;
					}
				}
			);
			jQuery.ajaxSetup({async:true});
		}
		
		function init(){
			
			if(jQuery("#doType").val()=="view"){
				jQuery("#buttonSave").hide();
                if(jQuery("#sfsdktmc").val() == "��"){
                    jQuery("#sdktsjTh").show();
                    jQuery("#sdktsjTd").show();
                } else {
                    jQuery("#sdktsjTh").hide();
                    jQuery("#sdktsjTd").hide();
                }
			}else{
				// jQuery("th").css("width", "110px");
				// jQuery("td").css("width", "230px");
				// initGzdj();

				/*if("10351" == jQuery("#xxdm").val()){
                    initBzjg();
                    jQuery.post("szdw_thlx.do?method=getKhwtListByThlx",{ssthlx:jQuery("#thlx").val()},function(data){
                        var html = "<option value=''></option>";
                        if(data && data.length > 0){
                            for(var i = 0; i < data.length; i++){
                                html += '<option value="'+data[i].lxdm+'" ';
                                if(data[i].lxdm == jQuery("#khhwt_inp").val()){
                                    html += ' selected="selected" ';
                                }
                                html += ' >'+data[i].lxmc+'</option>';
                            }
                            changeKhwt();
                        }
                        jQuery("#khhwt").html(html);
                        changeKhwt(jQuery("#khhwt").val());

                    },'json');
				}else{*/
                    changeThsc();
                initsdktsj();
                /*if(jQuery("#doType").val()=="add"){
                    changeThlx(jQuery("#thlx").val());
				}*/

				// }

			}
            //ѧ���춯��Ϣ
            getXyjdxx();
            getBjgcj();
		}
		//��ȡ������ɼ�
		function getBjgcj(){
            jQuery.post("szdw_thjl.do?method=getBjgcj",{"xh":jQuery("#xh").val()},function(data){
                jQuery("#bjgcj").empty();
				var str = "<tr><td colspan=\"4\"><table id=\"shlccx_table\" width=\"100%\" class=\"formlist\">";
                if(data.length > 0){
                    str+="<tr><th>�γ�����</th><th>�ɼ�</th><th>���޳ɼ�</th></tr>";
                    for(var i=0;i<data.length;i++){
                        str+="<tr>";
                        str+="<td>"+data[i].kcmc+"</td>";
                        str+="<td>"+data[i].cj+"</td>";
                        str+="<td>"+data[i].cxbj+"</td>";
                        str+="<tr>";
                    }
				} else {
                    str+="<tr><td>���޲�����ɼ�</td></tr>";
				}
				str += "</table></td></tr>";
                jQuery("#bjgcj").append(str);
			},'json');
		}
		function getXyjdxx(){
            jQuery.post("szdw_thjl.do?method=getXjydxx",{"xh":jQuery("#xh").val()},function(data){
				jQuery("#xjydInfo").empty();
                var str="<tr><td colspan=\"4\"><div class=\"con_overlfow\"><table id=\"shlccx_table\" width=\"100%\" class=\"formlist\" >";
            	if(data.length > 0){
                    str+="<tr><th>ѧ��</th><th>ѧ��</th><th>�춯���</th><th>ԭ�꼶</th><th>ԭרҵ</th><th>ԭ�༶</th><th>ѧ���춯�ĺ�</th><th>�춯ʱ��</th></tr>";
            		for(var i=0;i<data.length;i++){
						str+="<tr>";
						str+="<td>"+data[i].xn+"</td>";
						str+="<td>"+data[i].xq+"</td>";
                        str+="<td>"+data[i].ydlbmc+"</td>";
                        str+="<td>"+data[i].ydqnj+"</td>";
                        str+="<td>"+data[i].ydqzymc+"</td>";
                        str+="<td>"+data[i].ydqbjmc+"</td>";
                        str+="<td>"+data[i].xjydwh+"</td>";
                        str+="<td>"+data[i].xjydsj+"</td>";
                        str+="</tr>";
                    }
				} else {
            		str+="<tr><td>����ѧ���춯��Ϣ</td></tr>";
				}
                str+="</table></div></td></tr>";
				jQuery("#xjydInfo").append(str);
			},'json');
		}
		// ��ʾ/���ع�ע�ȼ�
		function initGzdj(){
			var v = jQuery("input:radio[name=sfzdgz]:checked").val();
			if(v == '1'){
				jQuery("#gzdj_span_th").show();
				jQuery("#gzdj_span_td").show();
				jQuery("#gzdj_span_td").show();
				jQuery("#hzpd").show();
				var xxdm = jQuery("#xxdm").val();
				if(xxdm == "10351"){
					jQuery("#wzdxbz").show();
				}
			}else{
				jQuery("#gzdj_span_th").hide();
				jQuery("#gzdj_span_td").hide();
				jQuery("#hzpd").hide();
				var xxdm = jQuery("#xxdm").val();
				if(xxdm == "10351"){
					jQuery("#wzdxbz").hide();
				}
			}
		}

		function initsdktsj(){
            var v = jQuery("input:radio[name=sfsdkt]:checked").val();
            if(v=='1'){
            	jQuery("#sdktsjTh").show();
                jQuery("#sdktsjTd").show();
			} else {
                jQuery("#sdktsjTh").hide();
                jQuery("#sdktsjTd").hide();
			}
		}
		// ����ע�Ļ��������ע�ȼ�
		function clearGzdj(){
			var v = jQuery("input:radio[name=sfzdgz]:checked").val();
			if(v == '0'){
				jQuery("#gzdj_span_th").remove();
				jQuery("#gzdj_span_td").remove();
				var xxdm = jQuery("#xxdm").val();
				if(xxdm == "10351"){
					jQuery("#wzdxbz").remove();
				}
			}
		}
		

		function selectXh(){
			var gotoPath = jQuery("#path").val();
			if(jQuery("#zgh").val()!=""){
				gotoPath +="$zgh="+jQuery("#zgh").val();
			}
			showDialog("��ѡ��һ��ѧ��",800,500,"szdw_tsxs.do?method=getTsxsInfo&gotoPath="+gotoPath);
		}
		
		function selectZxs(){
			var gotoPath = jQuery("#path").val();
			if(jQuery("#xh").val()!=""){
				gotoPath +="$xh="+jQuery("#xh").val();
			}
			showDialog("��ѡ��һ����ѯʦ",800,500,"szdw_thjl.do?method=getJsInfo&gotoPath="+gotoPath);
		}
		// ���ݿ�ʼʱ�䡢����ʱ�����̸��ʱ��
		function changeThsc(){
			var kssjArr = jQuery("#kssj").val().split(":");
			var jssjArr = jQuery("#jssj").val().split(":");
			var thsc = 0;
			if(kssjArr.length == 2 && jssjArr.length == 2){
				thsc = parseInt(jssjArr[0],10)*60 + parseInt(jssjArr[1],10) - (parseInt(kssjArr[0],10)*60 + parseInt(kssjArr[1],10));				
			}
			if(thsc < 0){
				thsc = 0;
			}
			jQuery("#thscTd").html(thsc+"��");
			jQuery("#thsc").val(thsc);
		}

        //���ݴ�ѧѡ������������޸���������
        function changeKhwt(value){
            var wtms = jQuery("#wtms_inp").val();
            jQuery.post("szdw_thlx.do?method=getWtmsListByKhwt",{sskhwt:value},function(data){
                var html = "<option value=''></option>";
                if(data  && data.length > 0){
                    for(var i = 0; i < data.length; i++){
                        html += '<option value="'+data[i].lxdm+'" ';
                        if(data[i].lxdm == wtms){
                            html += ' selected="selected" ';
                        }
                        html += ' >'+data[i].lxmc+'</option>';
                    }
                }
                jQuery("#wtms").html(html);
            },'json');
        }

        //���ݴ�ѧ��ʼ��������
        function initBzjg(){
            var bzjg = jQuery("#bzjg_inp").val();
            jQuery.post("szdw_thlx.do?method=getAllBzjg",{},function(data){
				var html="";
                if(data && data.length > 0){
                    for(var i = 0; i < data.length; i++){
                        html += "<label  style=\"margin-right: 15px;\">";
                        html += "<input type=\"checkbox\" value=\""+data[i].dm+"\" name=\"bzjg_ckb\"";
                        if(bzjg != null && bzjg !=""){
							var bzjgArr = bzjg.split(",");
							for(var j = 0;j<bzjgArr.length;j++){
								if(data[i].dm == bzjgArr[j]){
									html += "checked=\"checked\"";
								}
							}
                        }
                        html += "/>"+data[i].mc+"</label>";

                    }
                }
                jQuery("#ckb_div").html(html);
            },'json');
        }
		