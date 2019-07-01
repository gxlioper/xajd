<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/jxgl/jxglFunction.js"></script>
		<script language="javascript" src="js/check.js"></script>
		<script language="javascript" src='/xgxt/dwr/interface/getOtherData.js'></script>
		<script language="javascript">
		 function getMrz(){
			var xn = $('xn').value;
			var xq = $('xq').value;
			if(xn != ""){
				dwr.engine.setAsync(false);
				var tableName="zjjt_cxf_sz";
				var colList=["mrz"];
				var pk = "xn||xq";
				var pkValue = xn+xq;
				var query ="";
				$('pkV').value=pkValue;
				getOtherData.getTableListInfo(tableName, colList,pk, pkValue,query,function(data){
					if( data != null && data.length > 0){
						$('mrz').value=data[0].mrz;
					}else{
						$('mrz').value="";
					}
				});
				dwr.engine.setAsync(true);
	 		}
		 }
		</script>
	</head>
	<body onload="getMrz();">
		<html:form action="/zjjtPjpy">
		<!-- 隐藏域 -->
		<%@ include file="/pjpy/hiddenValue.jsp"%>
		<input type="checkbox" name="primarykey_cbv" id="pkV" value="${pk }" checked="checked" style="display : none">
		<!-- 隐藏域 end-->
			<div class="tab_cur">
				<p class="location">
					<em>您的当前位置:</em><a><bean:write name="title" /></a>
				</p>
			</div>
			<div class="tab">
			<table class="formlist" border="0" id="rsTable" align="center"
				style="width: 100%">
				<thead>
					<tr><th colspan="4"><span>参数设置</span></th></tr>
				</thead>
				<tr style="height: 23px">
					<th width="40%">
						当前学年
					</th>
					<td align="left" width="60%">
						<html:select property="save_xn" style="" styleClass="select" styleId="xn" onchange="getMrz();" value="${rs.xn}">
							<html:options collection="xnList" property="xn" labelProperty="xn" />
						</html:select>
					</td>
				</tr>
				<tr style="height: 23px">
					<th width="40%">
						当前学期
					</th>
					<td align="left" width="60%">
						<html:select property="save_xq" style="" styleClass="select" styleId="xq" onchange="getMrz();" value="${rs.xq }">
							<html:options collection="xqList" property="xqdm" labelProperty="xqmc" />
						</html:select>
					</td>
					
				</tr>
				<tr style="height: 23px">
					<th width="40%">
						操行分默认值
					</th>
					<td align="left" width="60%">
						<html:text property="save_mrz" styleId="mrz"
						onkeyup="checkInputData(this);" maxlength="5" 
						style="width:30%;ime-mode:disabled"/>
						<input type="text" style="display:none"/>
					</td>
					
				</tr>
				
				<tfoot>
			      <tr>
			        <td colspan="4">
			          <div class="btn">
			          <button id="buttonSave" onclick="saveUpdate('/xgxt/zjjtPjpy.do?method=szManage&doType=save','mrz')">
						保存
						</button>
					<button id="buttonClose" onclick="window.close();return false;">
						关闭
					</button>
			        </div></td>
			      </tr>
			    </tfoot>
			</table>
			</div>
			<div id="tmpdiv1"></div>
			
			<logic:present name="message">
				<script>
					if($("message")){
						alert($("message").value);
						dialogArgumentsQueryChick();
						window.close();
					}
				</script>
			</logic:present>
		</html:form>
		<script language="javascript" src="js/bottomButton.js"></script>
	</body>
</html>
