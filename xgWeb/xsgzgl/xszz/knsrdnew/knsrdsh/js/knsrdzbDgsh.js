


	function autoSetZbnr(){
		var nrids = jQuery("#nrids").val();
		var arr=nrids.split(",");
		//�����ֵ
		var zsfz=0;
		for(var i=0;i<arr.length;i++){
			jQuery("table[name=knsrdzb]").find("tr[name=mytr]").each(function(){
				jQuery(this).find("tbody[name=tbody_knsrdzbnr]").find("tr").each(function(){
					/*if(jQuery(this).find("input[name=fzlxH]").val()== '0'){
							jQuery(this).find("input[name=zbnrid]").attr("disabled","disabled");
					}*/
					if(jQuery(this).find("input[name=nrid]").val() == arr[i]){
						jQuery(this).find("input[name=zbnrid]").attr("checked","checked");
						jQuery(this).find("input[name=zbnrid]").click();
						jQuery(this).find("input[name=zbnrid]").attr("checked","checked");
						jQuery(this).find("#spanshfz").hide();
						jQuery(this).find("#textshfz").show(); 

						var shfz = jQuery(this).find("#textshfz").val(); 
						if(shfz == ""){
							shfz = 0;
						}
						var qzbl = jQuery(this).find("input[name=qzbl]").val();
						zsfz = parseFloat(zsfz) + parseFloat(shfz) * parseFloat(qzbl)/100;
					}
				});

			});
		}
		zsfz = formatZsfz(zsfz,2);
		jQuery("#zsfz").text(zsfz);
	}
	
	
	function saveShzt(){
		//��װjson��ʽ����
		var base=new Array();
		var i=1;
		var shfzFlag = false;
		jQuery("table[name=knsrdzb]").find("tr[name=mytr]").each(function(){
			var rdzb=new Object();
			rdzb.sxid=jQuery(this).find("input[name=sxid]").val();
			var zbnra=new Array();
			var xxnri=1;
			jQuery(this).find("tbody[name=tbody_knsrdzbnr]").find("tr").each(function(){
				var zbnr=new Object();
				if(jQuery(this).find("input[name=zbnrid]").is(":checked")){
					zbnr.nrid = jQuery(this).find("input[name=nrid]").val();
					zbnr.fz = jQuery(this).find("#spanfz").text();
					var shfz = jQuery(this).find("#textshfz").val();
					if(shfz == ""){
						shfzFlag = true;
						return false;
					}
					zbnr.shfz=jQuery(this).find("#textshfz").val();
					zbnra[xxnri-1]=zbnr;
					xxnri++;
				}
			});
			if(shfzFlag == true){
				return false;
			}
			rdzb.zbnr=zbnra;
			base[i-1]=rdzb;
			i++;
		});
		
		if(shfzFlag == true){
			showAlertDivLayer("��ѡ����ָ��������˷�ֵ����Ϊ�գ�");
			return false;
		}
		var json=JSON.stringify(base);
		var shzt = jQuery("#shjg").val();
		if ("1" == shzt && jQuery("#rddc").val() == ""){
			showAlertDivLayer("��ѡ���϶����Σ�");
			return false;
		}
		if(jQuery("#shjg").val() == "0"){
			showAlertDivLayer("��ѡ�����״̬��");
			return false;
		}
		var shyj = jQuery("#shyj").val();
		if (jQuery.trim(shyj) == ""){
			showAlertDivLayer("����д��������");
			return false;
		}
		var message;
		if(jQuery("#shjg").val() == "1"){
			message = "ͨ��";
		}
		if(jQuery("#shjg").val() == "2"){
			message = "��ͨ��";
		}
		if(jQuery("#shjg").val() == "3"){
			message = "�˻�";
		}
		var nrids = jQuery("#nrids").val();
		
		showConfirmDivLayer("��ȷ��" + message + "��������",{"okFun":function(){
			var url = "xg_xszz_knsrd_knshgl.do?method=knsrdDgsh&type=save&json="+json+"&nrids="+nrids;
			ajaxSubFormWithFun("knsrdshForm",url,function(data){
				showAlertDivLayer(data["message"],{},{"clkFun":function(){
					if (parent.window){
						refershParent();
					}
				}});
			});
		}});
	}

	//ѡ��ѡ���ʱjs�����¼�
	function xzknsfz(obj){
		if(jQuery(obj).is(":checked")){
			jQuery(obj).parents("#knsrdzbxxnr").find("#spanshfz").hide();
			jQuery(obj).parents("#knsrdzbxxnr").find("#textshfz").show();
			var fzlx=jQuery(obj).next("input[name=fzlxH]").val();
			var fz=jQuery(obj).parents("tr").find("input[name=fzH]").val();
			if(fzlx==0){
				jQuery(obj).parents("#knsrdzbxxnr").find("#spanshfz").text(fz);
				jQuery(obj).parents("#knsrdzbxxnr").find("#textshfz").val(fz);
			}
		}else{
			jQuery(obj).parents("#knsrdzbxxnr").find("#spanshfz").text("");
			jQuery(obj).parents("#knsrdzbxxnr").find("#spanshfz").show();
			jQuery(obj).parents("#knsrdzbxxnr").find("#textshfz").val("");
			jQuery(obj).parents("#knsrdzbxxnr").find("#textshfz").hide(); 
		}
		autosetShfz();
	}
	 //�ж���д��ֵ�Ƿ񳬹���Χ
	 function checkValue(obj){
		  	checkInputNum(obj);
			if(obj.value==""){
				obj.value="0";
			}
			var fz=jQuery(obj).val();
			var rcxwlbzdfz=jQuery(obj).nextAll('[name=pdfzH]').val();
			if(rcxwlbzdfz.indexOf("-")!=-1){
				//�����ֵ
				var rcxwlbzdfzs = rcxwlbzdfz.split('-');
				rcxwlbzdfzStart = rcxwlbzdfzs[0];
				rcxwlbzdfzEnd   = rcxwlbzdfzs[1];
				if(parseFloat(fz)<parseFloat(rcxwlbzdfzStart)||parseFloat(fz)>parseFloat(rcxwlbzdfzEnd)){
					showAlertDivLayer("��ֵ�����ڷ�ֵ��Χ�ڣ�");
					jQuery(obj).val('');
					autosetShfz();
					return false;
				}
			}else{
				//�������ֵ
				if(parseFloat(fz)<0||parseFloat(fz)>parseFloat(rcxwlbzdfz)){
					showAlertDivLayer("��ֵ�����ڷ�ֵ��Χ�ڣ�");
					jQuery(obj).val('');
					autosetShfz();
					return false;
				}
			}
			autosetShfz();
	  }
	  //�Զ����������
	  function autosetShfz(){
		  var zsfz=0;
			jQuery("table[name=knsrdzb]").find("tr[name=mytr]").each(function(){
				jQuery(this).find("tbody[name=tbody_knsrdzbnr]").find("tr").each(function(){
						if(jQuery(this).find("input[name=zbnrid]").is(":checked")){
							var shfz = jQuery(this).find("#textshfz").val(); 
							if(shfz=="" ){
								shfz =0;
							}
							var qzbl = jQuery(this).find("input[name=qzbl]").val();
							zsfz = parseFloat(zsfz) + parseFloat(shfz)* parseFloat(qzbl)/100;
						}
				});
			});
			zsfz = formatZsfz(zsfz,2);
			jQuery("#zsfz").text(zsfz);
	  }

	  //�����ֵ�������λ����
	  function formatZsfz(s,n){
		    n = n>0 && n<=20 ? n : 2;
		    s = parseFloat((s+"").replace(/[^\d\.-]/g,"")).toFixed(n)+"";
		    var l = s.split(".")[0].split("").reverse();
		    r = s.split(".")[1]; 
		    t = "";
		    for(i = 0;i<l.length;i++){
		        t+=l[i]+((i+1)%3==0 && (i+1) != l.length ? "" : ""); 
		    }
		    return t.split("").reverse().join("")+"."+r;
	 }
