<%@ page language="java" contentType="text/html; charset=GBK"%>
<script language="javascript" src="js/xsxx/comm/xsxxPageInfo.js"></script>
<script type="text/javascript">
	
	//���ѧ��������Ϣ
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
   		
   		//ģ������
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
	
	//������Ϣ
	function reLoadXsqtInfo(mklx,result){
		
		$("div_otherInfo").innerHTML = "";
		
		//Jsp·��
		var path = "/xgxt/xsxx/comm/qtxx/";
			path+=mklx;
		
		for (var i = 0 ; i < result.length; i++){
			if(i==0){
				path+="/"+result[i].jspName;
				jQuery("#div_otherInfo").load(path);
			}else{			
				if(mklx == "jtxx"){//��ͥ��Ϣ
					jtxx = result[i];
				}else if(mklx == "dtjs"){//Ԥ����Ա
					if(result[i].dyxxPage != null && result[i].dyxxPage == "yes"){
						dyxx = result[i];
					}else if(result[i].ybdyPage != null && result[i].ybdyPage == "yes"){
						ybdy = result[i];
					}
				}else if(mklx == "gygl"){//��Ԣ����
					gygl = result[i];
				}else if(mklx == "jygl"){//��ҵ����
					if(result[i].bysPage != null && result[i].bysPage == "yes"){
						jygl = result[i];
					}
					
					if(result[i].jyxyPage != null && result[i].jyxyPage == "yes"){
						jygl = result[i];
					}
				}
			}
		}
		if(mklx == "jtxx"){//��ͥ��Ϣ
			setTimeout("setJtxx()",500);
		}else if (mklx == "dtjs"){//���Ž���
			setTimeout("setDtjs()",500);
		}else if (mklx == "pjpy"){//��������
			pjpy=result;
			setTimeout("setPjpy()",500);
		}else if (mklx == "xscj"){//ѧ���ɼ�
			xscj=result;
			setTimeout("setXscj()",500);
		}else if (mklx == "qgzx"){//�ڹ���ѧ
			qgzx=result;
			setTimeout("setQgzx()",500);
		}else if (mklx == "gygl"){//��Ԣ����
			setTimeout("setGygl()",500);
		}else if (mklx == "xszz"){
			xszz= result;
			setTimeout("setXszz()",500);
		}else if (mklx == "wjcf"){//Υ�ʹ���
			wjcf= result;
			setTimeout("setWjcf()",500);
		}else if (mklx == "xljk"){//������
			xljk= result;
			setTimeout("setXljk()",500);
		}else if (mklx == "jygl"){//��ҵ����
			
			setTimeout("setJygl()",500);
		}
	}

</script>
<div id="div_jbxxInfo" class="formbox" >
	<%@ include file="/xsxx/comm/jbxx/xsxx_jbxx.jsp"%>
</div> 
<div id="div_otherInfo" style="height: 200px">
	
</div>