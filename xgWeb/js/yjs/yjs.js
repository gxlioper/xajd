function initZylist(){
				
				var msg = "----«Î—°‘Ò----";
				var tableName = "yjs_zydmb";
				var dm = "zydm";
				var mc = "zymc";
				if($("nj").value!=""&&$("xy").value!=""){
					var pk = "nj||xydm"; 
					var pkValue = $("nj").value+$("xy").value;
				}else if($("nj").value!=""){
					var pk = "nj"; 
					var pkValue = $("nj").value;
				}else if($("xy").value!=""){
					var pk = "xydm"; 
					var pkValue = $("xy").value;
				}else{
					var pk = ""; 
					var pkValue = "";
				}
				getXszzInfo.getXszzList(tableName, dm, mc, msg, pk, pkValue,function(data){
					if(data!=null){
						DWRUtil.removeAllOptions("zy");
						DWRUtil.addOptions("zy",data,"dm","mc");
					}
				});
					
			}