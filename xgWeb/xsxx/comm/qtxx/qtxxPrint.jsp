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
	
	//���ѧ��������Ϣ
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
	
	//������Ϣ
	function reLoadXsqtInfo(mklx,result){
		
		//Jsp·��
		var path = "/xgxt/xsxx/comm/qtxx/";
			path+=mklx;
		
		for (var i = 0 ; i < result.length; i++){
			if(i==0){
				path+="/"+result[i].jspName;
				
				if(mklx == "jtxx"){//��ͥ��Ϣ
					jQuery("#div_jtxx_Info").load(path);
				}else if (mklx == "dtjs"){//���Ž���
					jQuery("#div_dtjs_Info").load(path);
				}else if (mklx == "pjpy"){//��������
					jQuery("#div_pjpy_Info").load(path);
				}else if (mklx == "xscj"){//ѧ���ɼ�
					jQuery("#div_xscj_Info").load(path);
				}else if (mklx == "qgzx"){//�ڹ���ѧ
					jQuery("#div_qgzx_Info").load(path);
				}else if (mklx == "gygl"){//��Ԣ����
					jQuery("#div_gygl_Info").load(path);
				}else if (mklx == "xszz"){//ѧ������
					jQuery("#div_xszz_Info").load(path);
				}else if (mklx == "wjcf"){//Υ�ʹ���
					jQuery("#div_wjcf_Info").load(path);
				}else if (mklx == "xljk"){//������
					jQuery("#div_xljk_Info").load(path);
				}else if (mklx == "jygl"){//��ҵ����
					jQuery("#div_jygl_Info").load(path);
				}
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
		}else if (mklx == "xszz"){//ѧ������
			xszz= result;
			setTimeout("setXszz()",500);
		}else if (mklx == "wjcf"){//Υ�ʹ���
			wjcf= result;
			setTimeout("setWjcf()",500);
		}else if (mklx == "xljk"){//������
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