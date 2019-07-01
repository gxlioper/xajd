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
				saveData('njjs_jygl.do?method=byqxUpdate&doType=save','byqx-yb-lxdz');
			});
			
			// 关闭按钮
			jQuery('#buttonClose').click(function(){
				window.close();
			});	
			
			jQuery("input[name='sjhm']").keyup(function(){
				checkInputData(this);
			});
			
			jQuery("input[name='lxdh']").keyup(function(){
				checkInputData(this);
			});
			
			jQuery("input[name='yb']").keyup(function(){
				checkInputData(this);
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
		
		#jydw{
			width:88%;
		}
	</style>

</head>
<body>
	<html:form action="/njjs_jygl" method="post">
		<input type="hidden" name="pkValue" value="${param.pkValue }"/>
		<input type="hidden" name="csrq" value="${rs.csrq }"/>
		
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置:</em><a>毕业去向录入</a>
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
					<th>身份证号</th>
					<td>
						${rs.sfzh }
					</td>
				</tr>
				<tr>
					<th>姓名</th>
					<td>
						${rs.xm }
					</td>
					<th>性别</th>
					<td>
						${rs.xb }
					</td>
				</tr>
				<tr>
					<th><bean:message key="lable.xb" /></th>
					<td>
						${rs.xymc }
					</td>
					<th>专业</th>
					<td>
						${rs.zymc }
					</td>
				</tr>
				<tr>
					<th>班级</th>
					<td>
						${rs.bjmc }
					</td>
					<th>
						学历
					</th>
					<td>
						${rs.xlmc }
					</td>
				</tr>
				<tr>
					<th>学制</th>
					<td>
						${rs.xz }
					</td>
					<th>年级</th>
					<td>
						${rs.nj }
					</td>
				</tr>
				<tr>
					<th>毕业年度</th>
					<td>
						${rs.bynd }
					</td>
					<th>培养方式</th>
					<td>${rs.pyfsmc }</td>
				</tr>
				<tr>
					<th>家庭地址</th>
					<td colspan="3">${rs.jtszd }</td>
				</tr>
				<tr>
					<th>联系方式</th>
					<td colspan="3">${rs.lxfs }</td>
				</tr>
			</tbody>
			<thead>
				<tr>
					<th colspan="4">
						<span>毕业去向信息</span>
					</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<th><span>*</span>毕业去向</th>
					<td>
						<html:select property="byqx" styleId="byqx" value="${rs.byqx}">
							<html:option value=""></html:option>
							<html:options collection="byqxList" labelProperty="mc" property="dm"></html:options>
						</html:select>
					</td>
					<th><span>*</span>联系地址</th>
					<td>
						<html:text property="lxdz" styleId="lxdz" maxlength="100" value="${rs.lxdz}"></html:text>
					</td>
				</tr>
				<tr>
					<th><span>*</span>邮政编码</th>
					<td>
						<html:text property="yb" styleId="yb" maxlength="6" value="${rs.yb}"></html:text>
					</td>
					<th>电子邮箱</th>
					<td>
						<html:text property="email" maxlength="100" value="${rs.email}"></html:text>
					</td>
				</tr>
				<tr>
					<th>联系电话</th>
					<td>
						<html:text property="lxdh" maxlength="20" value="${rs.lxdh}"></html:text>
					</td>
					<th>移动电话</th>
					<td>
						<html:text property="sjhm" maxlength="20" value="${rs.sjhm}"></html:text>
					</td>
				</tr>
				<tr>
					<th>就业单位</th>
					<td colspan="3">
						<html:text property="jydw" maxlength="200" styleId="jydw" value="${rs.jydw}"></html:text>
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
