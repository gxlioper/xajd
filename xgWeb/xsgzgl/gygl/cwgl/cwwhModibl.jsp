<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	<%@ include file="/syscommon/head.ini"%>
	<link rel="stylesheet" type="text/css" href="js/jquery/themes/default/easyui.css" />
	<link rel="stylesheet" type="text/css" href="js/jquery/themes/icon.css" />
	<script type='text/javascript' src="js/comm/message.js"></script>
	
	<script language="javascript" src="js/String.js"></script>
	<script language="javascript" src="js/check.js"></script>
	<script language="javascript" src="js/qtsjFunction.js"></script>
	<script type="text/javascript" src="dwr/interface/systemFunction.js"></script>
	
	<script	type="text/javascript">
	function plxgbl(){
		var xxdm = jQuery("#xxdm").val();
		if(xxdm == '12862'){
			var v = jQuery("#sfbl").val();
			var yllb = jQuery("#yllb").val();
			if (v == '是' && jQuery.trim(yllb) == ""){
				showAlert("请选择预留类别！");
				return false;
			}
		}
		//复选框选中学号
		var api = frameElement.api,W = api.opener;
		var xhs = W.document.getElementsByName("primarykey_checkVal");;
		
		var RowsStr="";
		var count =0;
		for (i=0; i<xhs.length; i++){
 			if(xhs[i].checked){
 				RowsStr+=xhs[i].value+"!!splitOne!!";
 				count++;
 			}
		}
		jQuery('#xhstr').val(RowsStr);

		saveData('gyglnew_cwgl.do?method=cwwhModibl&doType=save','cwh');
	}
	function showYllb(){
		var v = jQuery("#sfbl").val();
		if(v == '是'){
			jQuery("#yllb_th").show();
			jQuery("#yllb_td").show();
		}else{
			jQuery("#yllb_th").hide();
			jQuery("#yllb_td").hide();
		}
	}
	jQuery(document).ready(function(){ 
		var xxdm = jQuery("#xxdm").val();
		if(xxdm == '12862'){
			jQuery("#sfbl").change(function(){
				showYllb();
			});
			jQuery("#sfbl").change();
		}
	});
	</script>
</head>
<body>	
	<html:form action="/gyglnew_cwgl" method="post">
		<input type="hidden" id="xxdm" value="${xxdm }"/>
		<input type="hidden" id="xhstr" name="xhstr" value=""/>
		<%--<div class="tab_cur">
			<p class="location">
				<em>您的当前位置:</em><a>床位信息修改</a>
			</p>
		</div>		
		--%>
		<div class="tab">
		<table class="formlist" width="95%">
			<tbody>
			<tr>
				<th width="25%" height="30px">是否保留</th>
				<td>
					<html:select property="sfbl" styleId="sfbl" style="width:100px">
						<html:option value="是">是</html:option>
						<html:option value="否">否</html:option>
					</html:select>
				</td>
				<logic:equal value="12862" name = "xxdm">
					<th id="yllb_th" width="25%" height="30px"><span class="red">*</span>预留类别</th>
					<td id="yllb_td">
						<html:select property="yllb" styleId="yllb" style="width:150px;">
							<html:options collection="yllbList" property="lxdm" labelProperty="lxmc" />
						</html:select>
					</td>
				</logic:equal>
			</tr>
			<tr>
				<th width="25%" height="90px">备注<br/><font color="red">限制输入200字</font></th>
				<td colspan="3">
					<html:textarea onblur="chLengs(this,200);" property="blbz" style="width:100%;height:90px"></html:textarea>
				</td>
			</tr>
			</tbody>
			<tfoot>
			      <tr>
			        <td colspan="4" height="30px"><div class="bz"></div>
			          <div class="btn">
			          	<button type="button" name="提交" id="buttonSave" onclick="plxgbl();">保存</button>
			            <button type="button" name="关闭" id="buttonClose" onclick="Close();return false;">关闭</button>
			          </div></td>
			      </tr>
			    </tfoot>
		</table>
		</div>
	</html:form>
	<logic:present name="message">
		<input type="hidden" id="message" value="${message }" />
		<script type="text/javascript">
		 showAlert("操作成功！",{},{"clkFun":function(){
				if (parent.window){
					refershParent();
				}
			}});
			
		</script>
	</logic:present>
</body>
</html>
