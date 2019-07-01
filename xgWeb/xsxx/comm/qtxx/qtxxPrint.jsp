<%@ page language="java" contentType="text/html; charset=GBK"%>
<script language="javascript" src="js/xsxx/comm/xsxxPageInfo.js"></script>
<script type="text/javascript">
	
	function showMk(){
		var mk = document.getElementsByName('hidden_mk');
		for(var i=0; i<mk.length; i++){
			var mklx = mk[i].value;
			setPrintInfo(mklx);
		}		
	}
	
	//获得学生其他信息
	function setPrintInfo(mklx){

   		var xh = $("xh").value;
   		
   		var url = "xsxx_ajax.do?method=getQtxxInfo";
   			url+= "&mklx="+mklx;
   			url+= "&xh="+xh;
   		if(mklx!="xsxx"){	
			jQuery.ajax({
				type:'post',
				url:url,
				dataType:'json',
				async: false,
				success:function(result){
					reLoadXsqtInfo(mklx,result);
				}
			});
		}
	}
	
	//重载信息
	function reLoadXsqtInfo(mklx,result){
		
		//Jsp路径
		var path = "/xgxt/xsxx/comm/qtxx/";
			path+=mklx;
		
		for (var i = 0 ; i < result.length; i++){
			if(i==0){
				path+="/"+result[i].jspName;
				
				if(mklx == "jtxx"){//家庭信息
					jQuery("#div_jtxx_Info").load(path);
				}else if (mklx == "dtjs"){//党团建设
					jQuery("#div_dtjs_Info").load(path);
				}else if (mklx == "pjpy"){//评奖评优
					jQuery("#div_pjpy_Info").load(path);
				}else if (mklx == "xscj"){//学生成绩
					jQuery("#div_xscj_Info").load(path);
				}else if (mklx == "qgzx"){//勤工助学
					jQuery("#div_qgzx_Info").load(path);
				}else if (mklx == "gygl"){//公寓管理
					jQuery("#div_gygl_Info").load(path);
				}else if (mklx == "xszz"){//学生资助
					jQuery("#div_xszz_Info").load(path);
				}else if (mklx == "wjcf"){//违纪处分
					jQuery("#div_wjcf_Info").load(path);
				}else if (mklx == "xljk"){//心理健康
					jQuery("#div_xljk_Info").load(path);
				}else if (mklx == "jygl"){//就业管理
					jQuery("#div_jygl_Info").load(path);
				}
			}else{			
				if(mklx == "jtxx"){//家庭信息
					jtxx = result[i];
				}else if(mklx == "dtjs"){//预备党员
					if(result[i].dyxxPage != null && result[i].dyxxPage == "yes"){
						dyxx = result[i];
					}else if(result[i].ybdyPage != null && result[i].ybdyPage == "yes"){
						ybdy = result[i];
					}
				}else if(mklx == "gygl"){//公寓管理
					gygl = result[i];
				}else if(mklx == "jygl"){//就业管理
					if(result[i].bysPage != null && result[i].bysPage == "yes"){
						jygl = result[i];
					}
					
					if(result[i].jyxyPage != null && result[i].jyxyPage == "yes"){
						jygl = result[i];
					}
				}
			}
		}
		
		if(mklx == "jtxx"){//家庭信息
			setTimeout("setJtxx()",500);
		}else if (mklx == "dtjs"){//党团建设
			setTimeout("setDtjs()",500);
		}else if (mklx == "pjpy"){//评奖评优
			pjpy=result;
			setTimeout("setPjpy()",500);
		}else if (mklx == "xscj"){//学生成绩
			xscj=result;
			setTimeout("setXscj()",500);
		}else if (mklx == "qgzx"){//勤工助学
			qgzx=result;
			setTimeout("setQgzx()",500);
		}else if (mklx == "gygl"){//公寓管理
			setTimeout("setGygl()",500);
		}else if (mklx == "xszz"){//学生资助
			xszz= result;
			setTimeout("setXszz()",500);
		}else if (mklx == "wjcf"){//违纪处分
			wjcf= result;
			setTimeout("setWjcf()",500);
		}else if (mklx == "xljk"){//心理健康
			xljk= result;
			setTimeout("setXljk()",500);
		}else if (mklx == "jygl"){	
			setTimeout("setJygl()",500);
		}
	}

</script>
<div id="div_jbxxInfo" class="formbox">
	<%@ include file="/xsxx/comm/jbxx/xsxx_jbxx.jsp"%>
</div> 
<div id="div_gygl_Info" style="">
	
</div>
<div id="div_jtxx_Info" style="">
	
</div>
<div id="div_dtjs_Info" style="">
	
</div>
<div id="div_pjpy_Info" style="">
	
</div>
<div id="div_xscj_Info" style="">
	
</div>
<div id="div_qgzx_Info" style="">
	
</div>
<div id="div_xszz_Info" style="">
	
</div>
<div id="div_wjcf_Info" style="">
	
</div>
<div id="div_xljk_Info" style="">
	
</div>
<div id="div_jygl_Info" style="">
	
</div>