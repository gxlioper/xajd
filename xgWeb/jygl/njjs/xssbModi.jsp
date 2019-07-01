<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	<%@ include file="/syscommon/pagehead_V4.ini"%>
	<script language="javascript" src="js/String.js"></script>
	<script language="javascript" src="js/check.js"></script>
	<script language="javascript" src="js/qtsjFunction.js"></script>
	<script type="text/javascript" src="/xgxt/dwr/interface/systemFunction.js"></script>
	<script	type="text/javascript">
		jQuery(function(){
		
			// 保存按钮
			jQuery('#buttonSave').click(function(){
				saveData('njjs_jygl.do?method=xssbModi&doType=save','bynd-sfzh');
			});
			
			// 关闭按钮
			jQuery('#buttonClose').click(function(){
				window.close();
			});
			
			jQuery('#sfzh').blur(function(){
				checkSfzh(this);
			});
			
			if(!jQuery("#shi").val()){
				jQuery("#shi").hide();
			}
			
			// 省加载
			jQuery("#shen").change(function(){
				if(jQuery(this).val()){
					jQuery.getJSON('njjs_jygl.do?method=loadShi',{shen:this.value},function(data){
						jQuery("#shi").empty().show();
						if(data != null && data.length>0){
							for(var i=0; i<data.length; i++){
								var option = jQuery("<option value='"+ data[i].dm+"'>"+ data[i].mc+"</option>");
								jQuery("#shi").append(option);
							}
						}else{
							jQuery("#shi").hide();
						}
					});
				}else{
					jQuery("#shi").empty().hide();
				}
			});
		});
	</script>
	<style type="text/css">
		table{
			border-collapse:collapse;
		}
		table th{
			width:20%;
		}
		
		table td{
			width:30%;
		}
		
		table span{
			color:red;
		}
		#shen{
			width:200px;
		}
		
		#shi{
			width:200px;
		}
		
		#jtszd,#lxfs{
			width:88%;
		}
	</style>

</head>
<body>
	<html:form action="/njjs_jygl" method="post">
		<input type="hidden" name="pkValue" value="${param.pkValue }"/>
		
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置:</em><a>毕业生信息修改</a>
			</p>
		</div>
		<div class="tab">
		<table class="formlist">
			<thead>
				<tr>
					<th colspan="4">
						<span>毕业生信息</span>
					</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<th>学号</th>
					<td>
						${rs.xh }
						<input type="hidden" name="xh" value="${rs.xh }"/>
					</td>
					<th><span>*</span>身份证号</th>
					<td>
						<html:text styleId="sfzh" property="sfzh" value="${rs.sfzh }"></html:text>
					</td>
				</tr>
				<tr>
					<th>姓名</th>
					<td>
						${rs.xm }
						<input type="hidden" name="xm" value="${rs.xm }"/>
					</td>
					<th>性别</th>
					<td>
						${rs.xb }
						<input type="hidden" name="xb" value="${rs.xb }"/>
					</td>
				</tr>
				<tr>
					<th><bean:message key="lable.xb" /></th>
					<td>
						<input type="hidden" name="xydm" value="${rs.xydm }"/>
						<input type="hidden" name="xymc" value="${rs.xymc }"/>
						${rs.xymc }
					</td>
					<th>专业</th>
					<td>
						<input type="hidden" name="zydm" value="${rs.zydm }"/>
						<input type="hidden" name="zymc" value="${rs.zymc }"/>
						${rs.zymc }
					</td>
				</tr>
				<tr>
					<th>班级</th>
					<td>
						<input type="hidden" name="bjdm" value="${rs.bjdm }"/>
						<input type="hidden" name="bjmc" value="${rs.bjmc }"/>
						${rs.bjmc }
					</td>
					<th>
						<span>*</span>技能等级
					</th>
					<td>
						<html:select property="jndj" value="${rs.jndj}">
							<html:option value=""></html:option>
							<html:options collection="pyccList" labelProperty="mc" property="dm"/>
						</html:select>
					</td>
				</tr>
				<tr>
					<th>学制</th>
					<td>
						<input type="hidden" name="xz" value="${rs.xz }"/>
						${rs.xz }
					</td>
					<th>年级</th>
					<td>
						<input type="hidden" name="nj" value="${rs.nj }"/>
						${rs.nj }
					</td>
				</tr>
				<tr>
					<th><span>*</span>毕业年度</th>
					<td>
						<html:text property="bynd" styleId="bynd" value="${rs.bynd}"></html:text>
					</td>
					<th>培养方式</th>
					<td>
						<html:select property="pyfs" value="${rs.pyfs}">
							<html:option value=""></html:option>
							<html:options collection="pyfsList" property="dm" labelProperty="mc"/>
						</html:select>
					</td>
				</tr>
				<tr>
				<th>
						<span></span>学历
					</th>
					<td colspan="3">
						<html:select property="xl" value="${rs.xl}">
							<html:option value=""></html:option>
							<html:options collection="xlList" labelProperty="mc" property="dm"/>
						</html:select>
					</td>
				</tr>
				<tr>
					<th>生源所在地</th>
					<td colspan="3">
						<html:select property="shen" styleId="shen" value="${shen}">
							<html:option value=""></html:option>
							<html:options collection="shenList" property="dm" labelProperty="mc"/>
						</html:select>
						<html:select property="sydq" styleId="shi" value="${rs.sydq}">
							<html:option value=""></html:option>
							<html:options collection="shiList" property="dm" labelProperty="mc"/>
						</html:select>
					</td>
				</tr>
				<tr>
					<th>家庭地址</th>
					<td colspan="3">
						<html:text property="jtszd" styleId="jtszd" value="${rs.jtszd }" maxlength="40"></html:text>
					</td>
				</tr>
				<tr>
					<th>联系方式</th>
					<td colspan="3">
						<html:text property="lxfs" styleId="lxfs" value="${rs.lxfs }" maxlength="25"></html:text>
					</td>
				</tr>
			</tbody>
			<tfoot>
			      <tr>
			        <td colspan="4"><div class="bz">"<span class="red">*</span>"为必填项</div>
			          <div class="btn">
			          	<logic:notEqual value="view" name="doType">
			          		<button id="buttonSave">保存</button>
			          	</logic:notEqual>
			            <button id="buttonClose">关闭</button>
			          </div>
			        </td>
			      </tr>
			    </tfoot>
		</table>
		</div>
	</html:form>
	<logic:present name="message">
		<input type="hidden" id="message" value="${message }" />
		<script type="text/javascript">
			jQuery(function (){
				alertInfo(jQuery('#message').val(), function(){
					window.close();
					if(window.dialogArguments && window.dialogArguments.document.all("search_go")){
						window.dialogArguments.document.getElementById('search_go').click();
					}
				});
			});
		</script>
	</logic:present>
</body>
</html>
