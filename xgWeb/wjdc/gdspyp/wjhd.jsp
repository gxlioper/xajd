<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
    <%@ include file="/syscommon/pagehead_V4.ini"%>
    <style type="text/css">
    	p
    	{
    		text-indent: 25px;
    		width: 95%;
    	}
    </style>
	<script language="javascript" src="js/function.js"></script>
	<script type="text/javascript">
		//���Ʋ�����ѡ��ͬһ��ѡ��
		function radio_click(obj,type,stid,xx){
			if("zfh"==type){
				$("zbf_"+stid+"_"+xx).checked="";
			}else{
				$("zfh_"+stid+"_"+xx).checked="";
			}
		}
		//�ύ
		function tj(){
			var zfh;//�����
			var zbf;//���
			for(var i=1;i<100;i++){
				zfh=document.getElementsByName("zfh_"+i);
				if(zfh!=null&&zfh.length==3){
					if(!(zfh[0].checked||zfh[1].checked||zfh[2].checked)){
						alert("��ѡ������"+i+"�������ѡ�");
						return false;
					}
				}
				zbf=document.getElementsByName("zbf_"+i);
				if(zbf!=null&&zbf.length==3){
					if(!(zbf[0].checked||zbf[1].checked||zbf[2].checked)){
						alert("��ѡ������"+i+"�����ѡ�");
						return false;
					}
				}
			}
			document.forms[0].submit();
		}

		//�ʾ�𰸸�ֵ
		function wjdafz(){
			<logic:present name="wjda">
				<logic:iterate id="s" name="wjda">
					$("zfh_${s.id}_${s.zfh}").checked="checked";
					$("zbf_${s.id}_${s.zbf}").checked="checked";
				</logic:iterate>
			</logic:present>
		}
	</script>
  </head>
  
  <body onload="wjdafz()">
  	<form action="wjdc_msxldc_wjhd.do?doType=save" method="post">
  	   <table>
  	   		<tr><td colspan="3" align="center"><h3>��������</h3></td></tr>
  	   		<tr><td colspan="3">
  	   			<p>������20�������ÿһ��������仰��ÿһ�仰�������й��˻��µ�һ���Կ�������Щ����ֻ��ӳ�˲�ͬ�Ĺ۵㣬��һ����ӳ��ʵ����û�жԴ�֮�֡����˶���Щ�����������Լ��ļ��⡣</p>
  	   			<p>���Ķ�ÿ������е�ÿһ�仰�����Ⱦ���������һ�仰����ϻ�ӽ��������ڴ�����Ӧ��+�����ϻ�Ȧ��</p>
  	   			<p>Ȼ���پ������µ����仰�е��ľ�����ϻ���ƫ�����������ڴ�����Ӧ���������ϻ�Ȧ��</p>
  	   		</td></tr>
			<logic:iterate id="s" name="rs">
				<tr><td colspan="3"><hr/></td></tr>
				<tr>
					<td>${s.id }.</td>
					<td>A.${s.xxa}</td>
					<td>
						<input type="radio" id="zfh_${s.id}_A" name="zfh_${s.id}" onclick="radio_click(this,'zfh','${s.id}','A')" value="A"/>+
						<input type="radio" id="zbf_${s.id}_A" name="zbf_${s.id}" onclick="radio_click(this,'zbf','${s.id}','A')" value="A"/>-
					</td>
				</tr>
				<tr>
					<td></td>
					<td>B.${s.xxb}</td>
					<td>
						<input type="radio" id="zfh_${s.id}_B" name="zfh_${s.id}" onclick="radio_click(this,'zfh','${s.id}','B')" value="B"/>+
						<input type="radio" id="zbf_${s.id}_B" name="zbf_${s.id}" onclick="radio_click(this,'zbf','${s.id}','B')" value="B"/>-
					</td>
				</tr>
				<tr>
					<td></td>
					<td>C.${s.xxc}</td>
					<td>
						<input type="radio" id="zfh_${s.id}_C" name="zfh_${s.id}" onclick="radio_click(this,'zfh','${s.id}','C')" value="C"/>+
						<input type="radio" id="zbf_${s.id}_C" name="zbf_${s.id}" onclick="radio_click(this,'zbf','${s.id}','C')" value="C"/>-
					</td>
				</tr>
			</logic:iterate>
  	   </table>
  	   <div align="center">
  	   	<logic:equal value="yes" name="sfkhd">
	  	   	<button type="button" name="�ύ" onclick="tj();return false;">�ύ</button>
	  	   	<button type="reset" name="����">����</button>
  	   	</logic:equal>
  	   </div>
  	  </form>
  </body>
</html>
