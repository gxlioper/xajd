<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
<%@ include file="/syscommon/pagehead.ini"%>
<!-- ͷ�ļ� -->
<script language="javascript" src="/xgxt/pjpy/zjjd/zjjdjs/zjjdjs.js"></script>
<script type="text/javascript" src="js/rcsw/rcswFunction.js"></script>
<script type='text/javascript' src='/xgxt/dwr/interface/getDtjsInfo.js'></script>
<script type="text/javascript" src="/xgxt/dwr/interface/checkMjJxj.js"></script>
<script type="text/javascript" src="js/check.js"></script>
<script type="text/javascript">
 function jxjSave(){
        var jxjdm = "";
        var xh="";
        var doType="";
        var xxdm=$("xxdm").value;
        if($("xh")){
        	xh=$("xh").value;
        }
        if($("doType")){
        	doType=$("doType").value;
        }
        if($("jxjdm")){
        	jxjdm=$("jxjdm").value;
        }
        if(xh==""){
        	alert("ѧ�Ų���Ϊ�գ�");
        	return false;
        }
        if(jxjdm==""){
        	alert("��ѧ����Ϊ�գ�");
        	return false;
        }
      
        if(xxdm=="10395" && doType!="modi"){
				var xh = document.getElementById('xh').value;
				var jxjdm = document.getElementById('jxjdm').value;
				checkMjJxj.getJxjTjList(xh,jxjdm,checkBytjs)
				
		}else{
       		 refreshForm("/xgxt/mjxyJxj.do?method=jxjXx&doType=save");
       		 BatAlert.showTips('���ڲ�������ȴ�...');
       		   if($("buttonSave")){
          $("buttonSave").disabled=true;
        }
        }
      
     }
    function checkBytjs(data){
    	if(data.tydb=="false"){
    		alert("�����ɼ�û�д�꣡");
    		return false;
    	}
    	if(data.tydb=="nojl"){
    		alert("û���ҵ������ɼ���¼��");
    		return false;
    	}
    	if(data.pmbl=="false"){
			alert("�۲�ɼ�����û�дﵽҪ��");
			return false;
		}
		if(data.bjgkms=="nocj"){
			alert("��ѧ��û�п�����Ϣ��");
			return false;
		}
		if(data.bjgkms=="false"){
			alert("�������Ŀ���������ƣ�");
			return false;
		}
		if(data.cfcs=="false"){
			alert("���ִ����������ƣ�");
			return false;
		}
		refreshForm("/xgxt/mjxyJxj.do?method=jxjSq&doType=save");
		BatAlert.showTips('���ڲ�������ȴ�...');
	    if($("buttonSave")){
         $("buttonSave").disabled=true;
        }
	}
</script>
</head>
<body>
	<html:form action="/mjxyJxj" method="post">
		<div class="tab_cur" id="jd">
				<p class="location">
					<em>���ĵ�ǰλ��:</em><a>${title }</a>
				</p>
			</div>
		<input type="hidden" id="url" name="url" value="/mjxyJxj.do?method=jxjSq" />
		<input type="hidden" id="getStuInfo" name="getStuInfo"
			value="xm-xb-zymc-xymc-bjmc" />
		<input type="hidden" name="message" id="message" value="${message }" />
		<input type="hidden" name="save_sqsj" id="save_sqsj" value="${sqsj}"/>
		<input type="hidden" name="save_xq" id="save_xq" value="${xq}"/>
		<input type="hidden" name="save_xn" id="save_xn" value="${xn}"/>
		<input type="hidden" name="xh" id="xh" value="${xh}" styleId="xh"/>
		<input type="hidden" name="xxdm" id="xxdm" value="${xxdm}" styleId="xxdm"/>
		<input type="hidden" name="doType" id="doType" value="${doType}" styleId="doType"/>
		<input type="hidden" name="pkValue" id="pkValue" value="${pkValue }" styleId="pkValue"/>
		<div class="tab">
		<table width="100%"  border="0" class="formlist">
		 <tfoot>
	      <tr>
	        <td colspan="4"><div class="bz">"<span class="red">*</span>"Ϊ������</div>
	          <div class="btn">
	          	<logic:notEqual name="writeAbled" value="no">
					<logic:notEqual name="doType" value="view">
					<button  id="buttonSave"
						onclick="jxjSave();">
						��&nbsp;&nbsp;��
					</button>
					</logic:notEqual>
				</logic:notEqual>
	          </div>
	          </td>
	      </tr>
	    </tfoot>
		<logic:equal name="xxdm" value="10395">
			<jsp:include flush="true" page="/pjpy/typj/manyInfo.jsp"></jsp:include>
			<jsp:include flush="true" page="/pjpy/typj/otherInfo.jsp"></jsp:include>
		</logic:equal>
		</table>
	</html:form>
	<logic:present name="result">
			<script>
				alert(document.getElementById('message').value);
				if(window.dialogArguments){
			 		window.dialogArguments.document.getElementById('search_go').onclick();
			 	}
				Close();
			</script>
			</logic:present>
</body>
