		
		function addYyInfo(zgh){
			var pbdate = $("pbdate").value;
			showDialog2("ԤԼ������Ϣ",700,600,"xlzx_yysq.do?method=addYysqInfo&yyzxrq="+pbdate+"&zgh="+zgh);
		}
		
		function init(){
			jQuery("button[name=buttonSave]").hide();
			if(jQuery("#doType").val()!="view"){
				jQuery("input[name=kjdrs]").each(function(i){
					var kjdrs = jQuery("input[name=kjdrs][id=kjdrs"+i+"]").val();
					var yjdrs = jQuery("input[name=yjdrs][id=yjdrs"+i+"]").val();
					if(kjdrs=="" || parseInt(yjdrs) < parseInt(kjdrs)){
						jQuery("button[name=buttonSave][id=buttonSave"+i+"]").show();
					}
				});
			}
		}
		
		function returnPage(){
			document.location.href="xlzx_zxspb.do?method=zxsPbbForXs";
		}
		