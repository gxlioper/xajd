
		//保存预约申请表、咨询信息表
		function saveYysqInfo(){
			
				if(jQuery("#xh").val()=="" || jQuery("#zxrq").val()==""){
					return showAlert("请将带<font color='red'>*</font>的项目填写完整！");
				}
				var zxurl = "xlzx_zxyyclnew.do?method=saveYyzxInfo";
				//防止undifined

				var qssj = (!jQuery("#zxqssj").val()) ? "" : jQuery("#zxqssj").val();
				var jssj = (!jQuery("#zxjssj").val()) ? "" : jQuery("#zxjssj").val();
				var sjddm = (!jQuery("#sjddm").val()) ? "" : jQuery("#sjddm").val();
				if(jQuery("#pbfs").val() == '2'){
					if(sjddm == ""){
						return showAlert("请将带<font color='red'>*</font>的项目填写完整！");
					}
				}
				if(!checkJzxx()){
                    return showAlert("请将带<font color='red'>*</font>的项目填写完整！");
				}
				var	 zxParameter ={
						zxrq:jQuery("#zxrq").val(),
						qssj:qssj,
						jssj:jssj,
						sjddm:sjddm,
						xh:jQuery("#xh").val(),
						zgh:jQuery("#zgh").val(),
						xstell:jQuery("#sjhm").val(),
						zxstatus: 1,//咨询状态   1待咨询2已咨询
						zxtell:jQuery("#zxtell").val(),
						yyfs:jQuery("#yyfs").val(),//预约方式
						zxdz:encodeURI(encodeURI(jQuery("#zxdz").val())),
						bz:encodeURI(encodeURI(jQuery("#bz").val()))
					};
				if(jQuery("#jzxx_tb").is(":visible")){
                    zxParameter["jzxm"] = jQuery("#jzxm").val();
                    zxParameter["jzxb"] = jQuery("input[name=jzxb]:checked").val();
                    zxParameter["jzlxdh"] = jQuery("#jzlxdh").val();
                    zxParameter["gx"] = jQuery("#gx").val();
                    zxParameter["jzdzyx"] = jQuery("#jzdzyx").val();
                    zxParameter["jtjq"] = jQuery("#jtjq").val();
                    zxParameter["fqzy"] = jQuery("#fqzy").val();
                    zxParameter["fxl"] = jQuery("#fxl").val();
                    zxParameter["mqzy"] = jQuery("#mqzy").val();
                    zxParameter["mxl"] = jQuery("#mxl").val();
                    zxParameter["jtdz"] = jQuery("#jtdz").val();
                    zxParameter["xssfzx"] = jQuery("input[name=xssfzx]:checked").val();
                    zxParameter["fdysfzx"] = jQuery("input[name=fdysfzx]:checked").val();
                    zxParameter["lfmd"] = jQuery("#lfmd").val();
				}
					jQuery.ajaxSetup({async:false});
						jQuery.post(zxurl,zxParameter,function(data){
							if(data == true){
								showAlert("保存成功！",{},{"clkFun":function(){
									frameElement.api.opener.refreshForm("xlzx_yysqnew_yysqnew.do");
									window.close();
								}});
							}else{
								showAlert("保存失败！");
							}
						},'json');
					jQuery.ajaxSetup({async:true});
		}
					
		function selectXh(){
			var gotoPath = jQuery("#path").val();
//			if(jQuery("#zgh").val()!=""){
//				gotoPath +="$zgh="+jQuery("#zgh").val();
//			}
			showDialog("请选择一个学生",800,600,"xlzx_tsxs.do?method=getTsxsInfo&gotoPath="+gotoPath);
		}
		
		function selectZxs(){
			var gotoPath = jQuery("#path").val();
			
			if(jQuery("#xh").val()!=""){
				gotoPath +="$xh="+jQuery("#xh").val();
			}
			showDialog("请选择一个咨询师",800,600,"xlzx_zxs.do?method=getZxsInfo&gotoPath="+gotoPath);
		}
		
		
		function delValidate(){
			var flag = false;
			var xh = jQuery("#xh").val();
			var zgh = jQuery("#zgh").val();
			var date = jQuery("#zxrq").val();
			if(zgh!="" && date!=""){
				jQuery.ajaxSetup({async:false});
				jQuery.post("xlzx_zxspb.do?method=getSfkyFlag&xh="+xh,{pbdate:date,zgh:zgh},function(data){
					var sjddmList = null;
					if(data["message"]==""){
						flag = true;
						sjddmList = data["sjddmList"];
						if(sjddmList != null && sjddmList.length > 0){
							var optionHtml = "<option></option>";
							jQuery(sjddmList).each(function(i,n){
								optionHtml += "<option value='"+n.sjddm+"'>"+n.sjdmc+"</option>";
							});
							jQuery("#sjddm").empty();
							jQuery("#sjddm").append(optionHtml);
							jQuery("#xqdm").text(data['xqmc'])
						}
					}else{
						showAlert(data["message"],{},{"clkFun":function(){
							jQuery("#zxrq").val("");
							if(jQuery("#pbfs").val() == '2'){
								jQuery("#sjddm").empty();
								jQuery("#xqdm").text("");
							}
							flag = false;
						}});
					}
				},'json');
				jQuery.ajaxSetup({async:true});
			}
		}


        function checkEmail(obj) {
            var dzyx = jQuery(obj).val();
            if(!isEmail(dzyx) && dzyx!=""){
                showAlertDivLayer("请输入正确的电子邮箱！");
                return false;
            }
        }

        function checkLxdh(){
            if(!isTelephone("jzlxdh")){
                showAlert("请填写正确的家长联系电话！");
                return false;
            }
        }

        function checkJzxx(){
        	var flag = true;
        	if(jQuery("#jzxx_tb").is(":visible")){
                var checkId = 'jzxm-jzlxdh-gx-jtdz-lfmd-jzdzyx';
                if(!checkNotNull(checkId)){
                    flag = false;
                }
                var jzxb = jQuery("input[type=radio][name='jzxb']:checked");
                var xssfzx = jQuery("input[type=radio][name='xssfzx']:checked");
                var fdysfzx = jQuery("input[type=radio][name='fdysfzx']:checked");
                if(jzxb.length<=0 || xssfzx.length<=0 ||fdysfzx.length<=0 )
                	flag = false;


			}
			return flag;
		}