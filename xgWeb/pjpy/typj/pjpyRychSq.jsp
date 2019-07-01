<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
<!-- 头文件 -->
<script language="javascript" src="/xgxt/pjpy/zjjd/zjjdjs/zjjdjs.js"></script>
<script type="text/javascript" src="js/rcsw/rcswFunction.js"></script>
<script type='text/javascript' src='/xgxt/dwr/interface/getDtjsInfo.js'></script>
<script type="text/javascript" src="/xgxt/dwr/interface/checkMjJxj.js"></script>
<script type="text/javascript" src="js/check.js"></script>
<script type="text/javascript">
 function jxjSave(){
        var rychdm = "";
        var xh="";
        var doType="";
        var xxdm=$("xxdm").value;
        if($("xh")){
        	xh=$("xh").value;
        }
        if($("rychdm")){
        	rychdm=$("rychdm").value;
        }
        if(xh==""){
        	alert("学号不能为空！");
        	return false;
        }
        if(rychdm==""){
        	alert("奖学金不能为空！");
        	return false;
        }
        if($("doType")){
        	doType=$("doType").value;
        }
        if("modi"==doType){
        	refreshForm("/xgxt/mjxyRych.do?method=rychXx&doType=save");
        }else{
        	refreshForm("/xgxt/mjxyRych.do?method=rychSq&doType=save");
        }
        if($("buttonSave")){
          $("buttonSave").disabled=true;
        }
     }
</script>
</head>
<body>
	<html:form action="/mjxyRych" method="post">
		<div class="tab_cur" id="jd">
				<p class="location">
					<em>您的当前位置:</em><a>${title }</a>
				</p>
			</div>
		<input type="hidden" id="url" name="url" value="/mjxyRych.do?method=rychSq" />
		<input type="hidden" id="getStuInfo" name="getStuInfo"
			value="xm-xb-zymc-xymc-bjmc" />
		<input type="hidden" name="message" id="message" value="${message }" />
		<input type="hidden" name="save_sqsj" id="save_sqsj" value="${sqsj}"/>
		<logic:notEqual name="doType" value="modi">
		<input type="hidden" name="save_xq" id="save_xq" value="${xq}"/>
		<input type="hidden" name="save_xn" id="save_xn" value="${xn}"/>
		</logic:notEqual>
		<logic:equal name="doType" value="modi">
		<input type="hidden" name="save_xq" id="save_xq" value="${rs.xq}"/>
		<input type="hidden" name="save_xn" id="save_xn" value="${rs.xn}"/>
		</logic:equal>
		<input type="hidden" name="xh" id="xh" value="${xh}"/>
		<input type="hidden" name="xxdm" id="xxdm" value="${xxdm}" styleId="xxdm"/>
		<input type="hidden" name="doType" id="doType" value="${doType }" styleId="doType"/>
		<input type="hidden" name="pkValue" id="pkValue" value="${pkValue }" styleId="pkValue"/>
		<div class="tab">
		<table width="100%"  border="0" class="formlist">
		 <tfoot>
	      <tr>
	        <td colspan="4"><div class="bz">"<span class="red">*</span>"为必填项</div>
	          <div class="btn">
	          	<logic:notEqual name="writeAbled" value="no">
					<logic:notEqual name="doType" value="view">
					<button   id="buttonSave"
						onclick="jxjSave();">
						保&nbsp;&nbsp;存
					</button>
					</logic:notEqual>
				</logic:notEqual>
	          </div>
	          </td>
	      </tr>
	    </tfoot>
		<logic:equal name="xxdm" value="10395">
			<jsp:include flush="true" page="/pjpy/typj/manyInfo.jsp"></jsp:include>
			<jsp:include flush="true" page="/pjpy/typj/otherRych.jsp"></jsp:include>
		</logic:equal>
		</table>
		</div>
	</html:form>
	<logic:present name="result">
		<script>
				alert(''+$('message').value);
				if (window.dialogArguments) {
					window.close();
					window.dialogArguments.document.getElementById('search_go').click();
				}
			</script>
	</logic:present>
</body>
