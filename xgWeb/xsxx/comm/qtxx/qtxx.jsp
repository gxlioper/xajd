<%@ page language="java" contentType="text/html; charset=GBK"%>
<script language="javascript" src="js/xsxx/comm/xsxxPageInfo.js"></script>
<script type="text/javascript">
	
	//获得学生其他信息
	function getPageInfo(obj){
	
		var id = obj.parentNode.id;
    	obj.parentNode.className = 'ha';
    	
    	var nodeId = obj.id.substring(0,obj.id.length-2);
    	var elements = ele('ul1').children;
    	for(var i=0; i<elements.length; i++){
    		if(elements[i].id!= id){
    			elements[i].className = '';
    			var trId = elements[i].id.substring(0,elements[i].id.length-2);
    			if(document.getElementById(trId)){
    				document.getElementById(trId).style.display = "none";	    			
    			}
    		}
    	}
   		
   		//模块类型
   		var mklx = obj.id.replace("_a","");
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
			$("div_jbxxInfo").style.display="none";
			$("div_otherInfo").style.display="";
		}else{
			$("div_jbxxInfo").style.display="";
			$("div_otherInfo").style.display="none";
			obj.className = 'ha';
		}
	}
	
	//重载信息
	function reLoadXsqtInfo(mklx,result){
		
		$("div_otherInfo").innerHTML = "";
		
		//Jsp路径
		var path = "/xgxt/xsxx/comm/qtxx/";
			path+=mklx;
		
		for (var i = 0 ; i < result.length; i++){
			if(i==0){
				path+="/"+result[i].jspName;
				jQuery("#div_otherInfo").load(path);
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
		}else if (mklx == "xszz"){
			xszz= result;
			setTimeout("setXszz()",500);
		}else if (mklx == "wjcf"){//违纪处分
			wjcf= result;
			setTimeout("setWjcf()",500);
		}else if (mklx == "xljk"){//心理健康
			xljk= result;
			setTimeout("setXljk()",500);
		}else if (mklx == "jygl"){//就业管理
			
			setTimeout("setJygl()",500);
		}
	}

</script>
<div id="div_jbxxInfo" class="formbox" >
	<%@ include file="/xsxx/comm/jbxx/xsxx_jbxx.jsp"%>
</div> 
<div id="div_otherInfo" style="height: 200px">
	
</div>