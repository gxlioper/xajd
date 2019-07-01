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
		<script type='text/javascript' src='/xgxt/dwr/interface/systemFunction.js'></script>
		<script type="text/javascript">

		function autoFillTeaInfo(cod){
			if(cod == 13){
				var url = $('url').value;
				document.forms[0].action = url;
				document.forms[0].submit();
			}
		}

		//保存
		function save(){
			var xh = document.getElementById('save_xh').value;
			systemFunction.checkExists("qgzx_zgdzdx_hmdb", "xh", xh, function(data){
				if(data == true){
					alert("黑名单中的学生不能申请岗位！");
					return false;
				}else{
					var sqsyrs = document.getElementById('sqsyrs').value; //申请使用人数
					var syknss = document.getElementById('syknss').value; //使用困难生数
					var save_sfpks = document.getElementById('save_sfpks').value; //是否贫困生
					if(sqsyrs=='' || sqsyrs==null || sqsyrs==0){
						alert('该岗位目前不招人，不能申请！');
						return false;
					}else if(sqsyrs==syknss && save_sfpks=='否'){
						alert('不是困难生，无法申请该岗位！');
						return false;
					}else{
						saveDataShowTips('qgzx_gwsqwh.do?method=xssqUpdate&doType=save','save_xh-save_kcjqgzxsj-save_xssq','正在保存请稍候！');
					}
				}
			});
		}
	</script>
</head>
<body>
	<html:form action="/qgzx_gwsqwh" method="post">
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置:</em><a>${title }</a>
			</p>
		</div>
		<input type="hidden" id="url" name="url" value="/xgxt/qgzx_gwsqwh.do?method=xssqUpdate"/>
		<button type="button" id="disbutton" onclick="autoFillTeaInfo(13);" style="display: none"></button>
		
		<div class="tab"/>
		<table class="formlist" width="93%">
			<%@include file="gwxx.jsp" %>
			<%@include file="xsxx.jsp" %>
			<!-- 查看与修改分别加载不同界面展示申请信息 -->
			<logic:notEqual value="yes" name="stuck" scope="request">
			<logic:equal value="0" name="shcount" scope="request">
				<%@include file="sqxx.jsp" %>
			</logic:equal>
			<logic:notEqual value="0" name="shcount" scope="request">
				<%@include file="sqxxview.jsp" %>
			</logic:notEqual>
			</logic:notEqual>
			<logic:equal value="yes" name="stuck" scope="request">
				<%@include file="sqxxview.jsp" %>
			</logic:equal>
			
			<tfoot>
		      <tr>
		        <td colspan="4"><div class="bz">"<span class="red">*</span>"为必填项</div>
		          <div class="btn">
		          <logic:notEqual value="yes" name="stuck" scope="request">
		          	<logic:equal value="0" name="shcount" scope="request">
		          			<button type="button" name="提交" id="buttonSave" onclick="save();return false;">保 存</button>
		          	</logic:equal>
		          	<logic:notEqual value="0" name="shcount" scope="request">
		          	 	<font color="red">已经开始审核，不能进行修改！</font>
		          	</logic:notEqual>
		          </logic:notEqual>
		            <!-- 
		            <button type="button" name="关闭" onclick="window.close();return false;">关 闭</button>
		             -->
		             <logic:equal value="yes" name="gb">
		            	 <button type="button" name="关闭" onclick="window.close();return false;">关 闭</button>
		             </logic:equal>
		          </div>
		        </td>
		      </tr>
		    </tfoot>
		</table>
	</html:form>
	<logic:present name="message">
		<script type="text/javascript">
			alertInfo('${message }');
			if (window.dialogArguments && window.dialogArguments.document.getElementById('search_go')) {
				window.dialogArguments.document.getElementById('search_go').click();
			}
		</script>
	</logic:present>
</body>
</html>
