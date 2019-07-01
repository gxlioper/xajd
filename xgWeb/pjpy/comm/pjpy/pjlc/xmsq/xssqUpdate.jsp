<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script language="javascript" src="js/String.js"></script>
		<script language="javascript" src="js/check.js"></script>
		<script language="javascript" src="js/qtsjFunction.js"></script>
		<script type="text/javascript">

		function tipsAndSave(url){
			BatAlert.showTips("正在保存，请稍后！");
			saveData(url,'');
		}
		
		function autoFillTeaInfo(cod){
			if(cod == 13){
				var url = $('url').value;
				document.forms[0].action = url;
				document.forms[0].submit();
			}
		}
	</script>
</head>
<body>
	<html:form action="/pjpy_comm_xmsq" method="post">
		<div class="tab_cur" style="width: 100%">
			<p class="location">
				<em>您的当前位置:</em><a>${title }</a>
			</p>
		</div>
		<input type="hidden" name="tjr" value="${userName }"/>
		<input type="hidden" name="xh" value="${stuJbxx.xh }"/>
		<input type="hidden" name="xmdm" value="${xmxx.xmdm }"/>
		<input type="hidden" name="sqxn" value="${xmxx.sqxn }"/>
		<input type="hidden" name="sqxq" value="${xmxx.sqxq }"/>
		<input type="hidden" name="sqnd" value="${xmxx.sqnd }"/>
		<input type="hidden" name="pjxn" value="${pjxtsz.pjxn }"/>
		<input type="hidden" name="pjxq" value="${pjxtsz.pjxq }"/>
		<input type="hidden" name="pjnd" value="${pjxtsz.pjnd }"/>
		<input type="hidden" name="opera" value="${opera }" />
		<input type="hidden" name="sqsj" value="${sqsj }" />
		<input type="hidden" id="url" name="/xgxt/pjpy_comm_xmsq.do?method=xssqUpdate" />
		<input type="hidden" id="refreshParent" value="yes" />
		
		<button type="button" id="disbutton" onclick="autoFillTeaInfo(13);" style="display: none"></button>
		
		<div class="tab" style="width:99%;height:450px;overflow-x:hidden;overflow-y:auto;"/>
		<table class="formlist" width="99%">
			<%@include file="xmxx.jsp" %>
			<%@include file="xsxx.jsp" %>
			<%@include file="zcxx.jsp" %>
			<%@include file="cjxx.jsp" %>
			<%@include file="sqxx.jsp" %>
			<%@include file="qtxx.jsp" %>

		</table>
		</div>
		<table  class="formlist" width="100%">
		<tfoot>
		      <tr>
		        <td colspan="4"><div class="bz">"<span class="red">*</span>"为必填项</div>
		          <div class="btn">
		          	 <logic:equal value="add" name="opera">
		          		<button type="button" name="提交" id="buttonSave" onclick="saveDataShowTips('pjpy_comm_xmsq.do?method=xssqUpdate&doType=save','xh','正在保证请稍候！');">保 存</button>
		        	 </logic:equal>
		        	 <logic:equal value="update" name="opera">
		          		<button type="button" name="提交" onclick="saveDataShowTips('pjpy_comm_xmsq.do?method=xssqUpdate&doType=update','','正在保证请稍候！');">修 改</button>
		        	 </logic:equal>
		            <button type="button" name="关闭" onclick="window.close();return false;">关 闭</button>
		          </div></td>
		      </tr>
		    </tfoot>
		</table>
	</html:form>
	<logic:present name="message">
		<script type="text/javascript">
			alertInfo('${message}');
		</script>
	</logic:present>
	<logic:notEqual value="" name="tjMessage">
		<script type="text/javascript">
			jQuery('#buttonSave').attr('disabled','disabled');
			jQuery('#buttonSave').attr('class','disabled');
			alertInfo('${tjMessage }');
		</script>
	</logic:notEqual>
</body>
</html>
