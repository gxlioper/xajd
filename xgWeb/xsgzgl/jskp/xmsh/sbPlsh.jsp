<%@ page language="java" pageEncoding="GBK"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript">
		
			var f = 0;
			var s = 0;
			
			jQuery(function(){
				f = parseInt('${shxhList['first'] }'||0);
				s = parseInt('${shxhList['second'] }'||0);
				if(f==0){
					jQuery("#fs").parent().parent().find("font").html("")
				}
				if(s==0){
					jQuery("#ffgz").parent().parent().hide();
				}
				//第一级审核
				jQuery("#wpf").html(f);
				//合计
				jQuery("#all").html(s+f);
			})
		
			function savePlsh(shzt){
				
				var shyj = jQuery("#shyj").val();
				var fs = jQuery("#fs").val();
				var zdf=jQuery("#zdf").val();
				var zxf=jQuery("#zxf").val();
				var ffgz = '0'; //0：未选中，1：选中
				if(jQuery("#ffgz").prop("checked")){
					ffgz = '1';
				}

				if( f==0 && fs == "" && !jQuery("#ffgz").prop("checked")){
					showAlert('"评定分数"和"赋分规则"至少选其一！');
					return false;
				}
				
				if (shyj == ""){
					showAlert("请填写审核意见！");
					return false;
				}
				if ( f>0 && fs == ""){
					showAlert("请填写分数！");
					return false;
				}
				
				if(parseFloat(fs)>zdf||parseFloat(fs)<zxf){
					showAlert("分数不在评分区间内！");
					return false;
				}
				
				var api = frameElement.api,W = api.opener;
				W.savePlsh(shzt,shyj,fs,ffgz);
				closeDialog();
			}
		</script>
	</head>
	<body>
		<html:form action="/jskpXmsh" method="post"onsubmit="return false;">
		<input type="hidden" value="${sfsh}" name="sfsh" id="sfsh"/>
			<div style="overflow-x:hidden;overflow-y:auto;">
				<table class="formlist">
					<thead>
						<tr>
							<td colspan="2">
								<span>
									批量审核
								</span>
							</td>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th width="20%">
								<font color="red">*</font>
								评定分数
							</th>
							<td>
								<html:text style="width:100px" property="fs" styleId="fs"  maxlength="10" onkeyup="checkInputNum(this)"
                          ></html:text>
                          <input type="hidden" id="zxf" value="${zxf}"/>
                          	<input type="hidden" id="zdf" value="${zdf}"/>
                          		<logic:equal value="1" name="sfsh">
                          			评分区间：&nbsp${zxf}~${zdf}
                          		</logic:equal>
							</td>
							
						</tr>
						<tr >
							<th>赋分规则</th>
							<td>
								<input type="checkbox" id="ffgz" /><br/>
								勾选：只有未评分数的（<font id="wpf" style="color:red">0</font>）人才赋予上面所填的评定分数<br/>
								未勾选：所有（<font id="all" style="color:red">0</font>）人都赋予上面所填的评定分数
							</td>
						</tr>
						<tr>
							<th width="20%">
								<font color="red">*</font>
								审核意见
								<br/>
								<font color="red">(限200字)</font>
							</th>
							<td>
								<jsp:include page="/xsgzgl/comm/shlc/cyyj.jsp?gnid=sbsh&id=shyj" />
								<html:textarea property="shyj" style="width:98%;margin-top:5px" rows="6"
											   onblur="checkLen(this,200);"  styleId="shyj"
								></html:textarea>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
		<table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">	
			<tfoot>
						<tr>
							<td colspan="2">
								<div class="btn">
									<button type="button" onclick="savePlsh('1');">
										通过
									</button>
									<button type="button" onclick="savePlsh('2');">
										不通过
									</button>
									<button type="button" name="关 闭" onclick="closeDialog();">
										关 闭
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
			</table>
		</html:form>
	</body>
</html>
