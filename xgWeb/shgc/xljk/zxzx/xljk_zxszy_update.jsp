<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script>
	function xz(){
		if(checkExist()){
			document.all["search"].value="yes";
			refreshForm('/xgxt/xljk_zxsxx_view.do?doType=View_Zxszy');
		}
	}
	
	function checkExist(){
			jQuery.ajaxSetup({async:false});
				var parameter ={};
				var flag = 0;
			    parameter["xn_id"]=escape(document.all["xn_id"].value);
			    parameter["zxxbh"]=escape(jQuery("#zxxbh").val());
			    var url = "xljk_zxsxx_view.do?doType=checkExist";
			    
			    jQuery.post(url,parameter,
						function(result){
						if(result=="老师可用！"){
							flag=1;
						}else{
							alertInfo(result,function(tag){
	     						if(tag=="ok"){
				     				return false;
	     						}
	     					});
	     					flag=0;
	     					return false;
						}	
						}
					);
					jQuery.ajaxSetup({async:true});
					if(flag==1){
						return true;
					}else{
						return false;
					}
			}
		
	function update_zxszy(){
		var zxsbh=document.all["zxxbh"].value;
		if(zxsbh==""){
			alert("请先选择咨询师");
			return false;
		}else{
			var xn_id=document.all["xn_id"].value;
			var zxxbh=document.all["return_zxxbh"].value;
			underDealWith();
			refreshForm('/xgxt/xljk_zxszy_add.do?act=xljk_zxszygl&doType=Pipei_Zxs&zxxbh='+zxxbh+'&xn_id='+xn_id);
		}
		
	}

	function setIdValue(){
		var xxdm = $("xxdm").value;
		//广州大学
		if(xxdm == "11078"){
			var id = "arrId";
			var fatherObj = window.dialogArguments.document.getElementById(id);
			$("xn_id").value = fatherObj.value;
		}
	}
	</script>
	</head>
	<body onload="setIdValue()">
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置:</em><a>心理健康 - 心理咨询 - 预约资源管理 - 匹配咨询师</a>
			</p>
		</div>


		<html:form action="/xljk_zxsxx_view">
			<input type="hidden" id="xxdm" name="xxdm" value="${xxdm }" />
			<%-- 非广州大学 --%>
			<logic:notEqual name="xxdm" value="11078">
				<input type="hidden" id="xn_id" name="xn_id"
					value="<bean:write name="xn_id" scope="request"/>" />
			</logic:notEqual>
			<%-- 广州大学 --%>
			<logic:equal name="xxdm" value="11078">
				<input type="hidden" id="xn_id" name="xn_id" value="" />
			</logic:equal>
			<input type="hidden" id="search" name="search" value="no"
				scope="request" />

			<div class="tab">
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>请选择需要匹配的咨询师</span>
							</th>
						</tr>
					</thead>
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz">
									"
									<span class="red">*</span>"为必填项
								</div>
								<div class="btn">
									<button onclick="update_zxszy()">
										进行匹配
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
					<tbody>
						<tr>
							<th>
								咨询师
							</th>
							<td>
								<html:select property="zxxbh" style="width:100px"
									styleId="zxxbh" onchange="xz()">
									<html:option value=""></html:option>
									<html:options collection="zxxList" property="zxxbh"
										labelProperty="zxxxm" />
								</html:select>
							</td>
						</tr>
					</tbody>
					<logic:notEmpty name="list" scope="request">
						<input type="hidden" id="return_zxxbh" name="return_zxxbh"
							value="<bean:write name="zxxbh" scope="request"/>" />
						<thead>
							<tr>
								<th colspan="4">
									<span>咨询师详细信息</span>
								</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<th>
									咨询师编号
								</th>
								<td>
									<html:text name="list" property="zxxbh" readonly="true" />
								</td>
								<th>
									性别
								</th>
								<td>
									<html:text name="list" property="sex" readonly="true" />
								</td>
							</tr>
							<tr>
								<th>
									资格
								</th>
								<td colspan="3">
									<html:textarea name="list" property="zxxzg" rows="8"
										style="width:100% " readonly="true">
									</html:textarea>
								</td>
							</tr>
							<tr>
								<th>
									简介
								</th>
								<td colspan="3">
									<html:textarea name="list" property="jj" rows="8"
										style="width:100%" readonly="true">
									</html:textarea>
								</td>
							</tr>
						</tbody>
					</logic:notEmpty>
				</table>
			</div>
			<div id="tmpdiv"></div>
			<logic:notEmpty name="message">
				<logic:equal name="message" value="insert_sucess">
					<script>alert("保存成功!")</script>
				</logic:equal>
				<logic:equal name="message" value="no">
					<script>alert("保存失败!")</script>
				</logic:equal>
				<logic:equal name="message" value="date error">
					<script>alert("批量日期有误!")</script>
				</logic:equal>
				<logic:equal name="message" value="pipei_seccess">
					<script>
					alert("匹配成功!");
					window.dialogArguments.document.all("search_go").click();
					Close();
				</script>
				</logic:equal>
				<logic:equal name="message" value="pipei_fail">
					<script>alert("匹配失败!")</script>
				</logic:equal>
			</logic:notEmpty>
		</html:form>
	</body>
</html>
