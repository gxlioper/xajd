<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	<%@ include file="/syscommon/head.ini"%>
	<script type='text/javascript' src="js/comm/message.js"></script>
	<script type='text/javascript' src="js/uicomm.js"></script>
	<script type='text/javascript' src="js/String.js"></script>
	<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
	<script type='text/javascript' src='/xgxt/dwr/util.js'></script>
	<link rel="stylesheet" type="text/css" href="js/jquery/themes/default/easyui.css" />
	<link rel="stylesheet" type="text/css" href="js/jquery/themes/icon.css" />
	
	<script language="javascript" src="js/String.js"></script>
	<script language="javascript" src="js/check.js"></script>
	<script language="javascript" src="js/qtsjFunction.js"></script>
	<script type="text/javascript" src="dwr/interface/systemFunction.js"></script>
	<script language="javascript" src="js/comm/commFunction.js"></script>
	<script	type="text/javascript">
	</script>
</head>
<body>
	
	<html:form action="/gyglnew_qsgl" method="post">
		<input type="hidden" id="refreshParent" value="true"/>
		<input type="hidden" name="pkValue" value="${param.pkValue }"/>	
		<input type="hidden" name="lddm" value="${rs.lddm}"/>
		<logic:notEqual value="10351" name="xxdm">
			<input type="hidden" name="qsh" value="${rs.qsh}"/>
		</logic:notEqual>
		<logic:equal value="10351" name="xxdm">
			<input type="hidden" name="yqsh" value="${rs.qsh}"/>
		</logic:equal>
		<%--<div class="tab_cur">
			<p class="location">
				<em>您的当前位置:</em><a>寝室信息修改</a>
			</p>
		</div>		
		--%><div class="prompt" id="span_qsh" style="display: none">
	       <h3><span>提示：</span></h3>
	       <p>该寝室号在本楼栋下已存在！<br/></p>
	   	</div>
		
		<div class="tab" style="width:100%;height:435px;overflow-x:hidden;overflow-y:auto;">
		<table class="formlist" width="95%">
			<thead>
				<tr style="height:22px">
					<th colspan="4">
						<span>楼栋信息</span>
					</th>
				</tr>
			</thead>
			<tbody>
			<tr>
				<th align="right" width="16%">
					楼栋名称
				</th>
				<td align="left" width="34%" nowrap="nowrap">
					${rs.ldmc }
				</td>
				<th width="16%">
					楼栋性别
				</th>
				<td width="34%" id="ldxb">
					${rs.ldxb }
				</td>
			</tr>
			<tr>
				<th>楼栋层数</th>
				<td id="ldcs">
					${rs.ldcs }
				</td>
				<th>楼栋起始层</th>
				<td id="qsch">
					${rs.qsch }
				</td>
			</tr>
			<tr>
				<th>是否含0层</th>
				<td>${rs.sfhlc }</td>
				<th></th>
				<td></td>
			</tr>
			</tbody>
			
			<thead>
				<tr style="height:22px">
					<th colspan="4">
						<span>寝室信息</span>
					</th>
				</tr>
			</thead>
			
			<tbody>
			<tr>
				<th>层号</th>
				<td>
					${rs.chmc }
				</td>
				<th>
					寝室号				
				</th>
				<!-- 温州大学个性化 -->
				<logic:equal value="10351" name="xxdm">
					<td>
					<html:text property="qsh" styleId="qsh" maxlength="20"  value="${rs.qsh }" styleClass="text_nor"></html:text>
					<html:hidden property="xxdm" value="${xxdm}"/>
					</td>
				</logic:equal>
				<logic:notEqual value="10351" name="xxdm">
					<td>
					${rs.qsh }
					</td>
				</logic:notEqual>
			</tr>
			<tr>
				<th>寝室性别</th>
				<td>
					<logic:equal value="true" name="qsqtxx" property="xbsfkxg">
						<html:select property="qsxb" name="rs" styleId="qsxb" style="width:50px;">
							<html:option value="男">男</html:option>
							<html:option value="女">女</html:option>
						</html:select>					
					</logic:equal>
					<logic:notEqual value="true" name="qsqtxx" property="xbsfkxg">
						${rs.qsxb }
					</logic:notEqual>
				</td>
				<th>床位数</th>
				<td>
					<logic:equal value="0" name="qsqtxx" property="cwfprzgs">
						<html:text property="cws" styleId="cws" maxlength="2" styleClass="text_nor" value="${rs.cws}" onkeyup="checkInputData(this);"></html:text>
					</logic:equal>
					<logic:notEqual value="0" name="qsqtxx" property="cwfprzgs">
						<html:text property="cws" styleId="cws" maxlength="2" styleClass="text_nor" value="${rs.cws}" disabled="true"></html:text>
					</logic:notEqual>
				</td>		
			</tr>
			<tr>
				<th><font color="red">*</font>收费标准</th>
				<td>
					<html:text property="sfbz" styleId="sfbz" maxlength="5" styleClass="text_nor" value="${rs.sfbz }" onkeyup="checkInputData(this);"></html:text>
				</td>
				<th>寝室电话</th>
				<td>
					<html:text property="qsdh" styleId="qsdh" maxlength="20" styleClass="text_nor" value="${rs.qsdh }" onblur="checkPhone(this);"></html:text>
				</td>
			</tr>
			<tr>
				<th>有无空调</th>
				<td>
					<div>
						<html:radio property="ywkt" value="无" styleId="ywkt" name="rs" >无</html:radio>
						<html:radio property="ywkt" value="有" styleId="ywkt" name="rs"  >有</html:radio>
					</div>
				</td>
			</tr>
			<tr>
				<th>
					备注
					<br/><font color="red">(限制在1000字内)</font>
				</th>
				<td colspan="3" >
					<html:textarea property='bz' style="width:95%;word-break:break-all;" styleId="bz" rows='7' value="${rs.bz}" onblur="chLeng(this,1000);"/>
				</td>
			</tr>
			</tbody>
		</table>
		</div>
		<table class="formlist" width="95%">
			<tfoot>
			      <tr>
			        <td colspan="4"><div class="bz">"<span class="red">*</span>"为必填项</div>
			          <div class="btn">
			          	<button type="button" name="提交" id="buttonSave" onclick="saveData('gyglnew_qsgl.do?method=qswhModi&doType=save','qsh-sfbz');">保存</button>
			            <button type="button" name="关闭" id="buttonClose" onclick="Close();return false;">关闭</button>
			          </div></td>
			      </tr>
			</tfoot>
		</table>
	</html:form>
	<logic:present name="message">
		<input type="hidden" id="message" value="${message }" />
		<script type="text/javascript">
		showAlert(jQuery("#message").val(),{},{"clkFun":function(){
			if (parent.window){
				refershParent();
				
			}
		}});
		</script>
	</logic:present>
</body>
</html>
