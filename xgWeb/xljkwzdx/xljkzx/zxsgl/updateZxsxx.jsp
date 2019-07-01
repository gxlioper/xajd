<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
   <head>
	  <%@ include file="/syscommon/head.ini"%>
	  <script type="text/javascript" src="js/jquery/jquery.form.js"></script>
	  <script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
	  <script type="text/javascript" src="js/check.js"></script>
	  <script type="text/javascript" src="xljkwzdx/xljkzx/js/zxsgl.js"></script>
	  <script type="text/javascript">
	  </script>
  </head>
  
  <body>
     <html:form action="/xljk_zxsgl" method="post" styleId="zxsxxForm">
    	<table width="100%" border="0" class="formlist">
    		<thead>
				<tr>
					<th colspan="4">
						<span>教师基本信息</span>
					</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<th width="18%">
						<span class="red">*</span>职工号
					</th>
					<td width="32%">
						<html:text property="zgh" styleId="zgh" styleClass="text_nor" readonly="true" style="width:110px;" value="${zxsInfo.zgh}"/>
					</td>
					<th width="18%">
						姓名
					</th>
					<td width="32%">
						${zxsInfo.xm}
					</td>
				</tr>
				<tr>
					<th>
						性别
					</th>
					<td>
						${zxsInfo.xb}
					</td>
					<th>
						年龄
					</th>
					<td>
						${zxsInfo.age}
					</td>
				</tr>
				<tr>
					<th>
						联系电话
					</th>
					<td>
						${zxsInfo.lxdh}
						<html:hidden property="lxdh" styleId="lxdh" value="${zxsInfo.lxdh}"/>
					</td>
					<th>
						部门
					</th>
					<td>
						${zxsInfo.bmmc}
					</td>
				</tr>
			</tbody>
    	</table>
    	<table width="100%" border="0" class="formlist">
    		<thead>
				<tr>
					<th colspan="2">
						<span>咨询师信息</span>
					</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<th width="18%">
						<span class="red">*</span>在岗状态
					</th>
					<td>
						<html:select  property="status" styleId="status">
							<html:option value="1">在岗</html:option>
							<html:option value="0">不在岗</html:option>
						</html:select>
					</td>
				</tr>
				<tr>
					<th>
						日预约上限
					</th>
					<td>
						<html:text property="kjdrs" styleId="kjdrs" styleClass="text_nor" style="width:110px;" onchange="checkInt(this)" maxlength="3"/>
						人&nbsp;&nbsp;<span style="color: blue;font-weight: bold;">(若不填则默认无上限)</span>
					</td>
				</tr>
				<tr>
					<th>
						咨询详细地址
					</th>
					<td>
						<html:text property="address" styleId="address" styleClass="text_nor" style="width:500px;" maxlength="50"/>
					</td>
				</tr>
				<tr>
					<th>
						任职资质
					</th>
					<td>
						<html:select  property="zxszg" styleId="zxszg">
							<html:option value="无">无</html:option>
							<html:option value="心理咨询师资格证书二级">心理咨询师资格证书二级</html:option>
							<html:option value="心理咨询师资格证书三级">心理咨询师资格证书三级</html:option>
						</html:select>
					</td>
				</tr>
				<tr>
					<th align="right">
						简介&nbsp;&nbsp;<br/>
						<span class="red">(限500字)</span>
					</th>
					<td>
						<html:textarea property="zxsjj" styleId="zxsjj" style="width:95%;" rows="5" onkeypress="checkLen(this,500);"></html:textarea>
					</td>
				</tr>
			</tbody>
			<tfoot>
				<tr>
					<td colspan="2">
						<div class="bz">"<span class="red">*</span>"为必填项</div>
						<div class="btn">
							<button id="submit_button" type="button"  onclick="updateZxsxxAction();">
								保 存
							</button>
							<button type="button" name="关 闭" onclick="iFClose();">
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
