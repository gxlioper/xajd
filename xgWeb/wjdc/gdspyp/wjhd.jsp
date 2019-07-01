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
		//控制不可以选择同一个选项
		function radio_click(obj,type,stid,xx){
			if("zfh"==type){
				$("zbf_"+stid+"_"+xx).checked="";
			}else{
				$("zfh_"+stid+"_"+xx).checked="";
			}
		}
		//提交
		function tj(){
			var zfh;//最符合
			var zbf;//最不符
			for(var i=1;i<100;i++){
				zfh=document.getElementsByName("zfh_"+i);
				if(zfh!=null&&zfh.length==3){
					if(!(zfh[0].checked||zfh[1].checked||zfh[2].checked)){
						alert("请选择试题"+i+"的最符合选项！");
						return false;
					}
				}
				zbf=document.getElementsByName("zbf_"+i);
				if(zbf!=null&&zbf.length==3){
					if(!(zbf[0].checked||zbf[1].checked||zbf[2].checked)){
						alert("请选择试题"+i+"的最不符选项！");
						return false;
					}
				}
			}
			document.forms[0].submit();
		}

		//问卷答案赋值
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
  	   		<tr><td colspan="3" align="center"><h3>马氏量表</h3></td></tr>
  	   		<tr><td colspan="3">
  	   			<p>下面有20组陈述，每一组包括三句话，每一句话代表了有关人或事的一般性看法。这些陈述只反映了不同的观点，不一定反映事实，故没有对错之分。各人对这些陈述可以有自己的见解。</p>
  	   			<p>请阅读每组陈述中的每一句话，首先决定其中哪一句话最符合或接近你的信念，在答卷的相应“+”号上画圈。</p>
  	   			<p>然后再决定余下的两句话中的哪句最不符合或最偏离你的信念，并在答卷的相应“—”号上画圈。</p>
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
	  	   	<button type="button" name="提交" onclick="tj();return false;">提交</button>
	  	   	<button type="reset" name="重置">重置</button>
  	   	</logic:equal>
  	   </div>
  	  </form>
  </body>
</html>
