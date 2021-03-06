<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<!-- 头文件 -->
	<%@ include file="/syscommon/pagehead_V4.ini"%>
	<script language="javascript" src="js/stuinfoFunction.js"></script>
	<script type="text/javascript" src="/xgxt/dwr/interface/getXjydInfo.js"></script>
	<script type="text/javascript" src="/xgxt/dwr/interface/chkExists.js"></script>
	<script>
		function doSave(){
			var xh = document.getElementById('xh').value;
			var hkztdm = document.getElementById("hkztdm").value;
			
			if(xh==null || xh==""){
				alert("请将带*号的项目填写完整!");
				return false;
			}
			if(hkztdm==null || hkztdm==""){
				alert("请将带*号的项目填写完整!");
				return false;
			}
			chkExists.chkExist("hkztb","xh",xh,function(data){
			if(data==true){
				refreshForm('/xgxt/studentHkzt.do?method=modHkztInfo&doType=mod');			
			}else{		
				alert("不存在此学生的户口状态记录！");
				return false;
			}		
			});
		}
		
		function change(){
			var hkzt = document.getElementById("hkztdm").value;
			if(hkzt== "001"){
				document.getElementById("hkqcsj").value = "";
				document.getElementById("sfjf").value = "";
				document.getElementById("hkqcsj").disabled = "true";
				document.getElementById("sfjf").disabled = "true";
			}else{
				document.getElementById("hkqcsj").disabled = "";
				document.getElementById("sfjf").disabled = "";
			}
		}
	</script>
</head>
<body onload="change()">
	<html:form action="/studentHkzt.do">
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置:</em><a>学生信息 - 档案管理 - 户籍管理 - 信息修改</a>
			</p>
		</div>
		<div class="tab">
		<table width="100%" border="0" class="formlist">
		<thead>
			<tr>
				<th colspan="4"><span>学生户籍信息</span></th>
			 </tr>
		</thead>
		<tbody>
		<tr>
			<th><span class="red">*</span>学号</th>
			<td>
				<input type="hidden" name="xh" id="xh" value="${rs.xh}"/>
				${rs.xh}
			</td>
			<th>姓名</th>
			<td>
				${rs.xm}
			</td>
		</tr>
		
		<tr>
			<th>性别</th>
			<td>
				${rs.xb}
			</td>
			
			<th><bean:message key="lable.xsgzyxpzxy" /></th>
			<td>
				${rs.xymc}
			</td>
		</tr>
		
		<tr>
			<th>专业</th>
			<td>
				${rs.zymc}
			</td>
			<th>班级</th>
			<td>
				${rs.bjmc}
			</td>
		</tr>
		
		<tr>
			<th>年级</th>
			<td>
				${rs.nj}
			</td>		
			<th>生源地</th>
			<td>
				${rs.syd}
			</td>
		</tr>

		<tr>
			<th>身份证号</th>
			<td>
				${rs.sfzh}
			</td>		
			<th>联系电话</th>
			<td>
				${rs.lxdh}
			</td>
		</tr>
		
		<tr>
			<th><span class="red">*</span>户口状态</th>
			<td>
				<html:select property="hkztdm" styleId="hkztdm" onchange="change()" name="rs">
					<html:options collection="hkztList" property="hkztdm" labelProperty="hkztmc"/>
			    </html:select>
			</td>
			<th>户口迁入时间</th>
			<td>
				<html:text property="hkqrsj" readonly="true" onclick="return showCalendar('hkqrsj','y-mm-dd');" styleId="hkqrsj" name="rs"/>
			</td>
		</tr>
		
		<tr>
			<th>毕业迁移地</th>
			<td>
				<html:text property="hkqcd" name="rs" styleId="hkqcd" maxlength="50"/>					
			</td>
			<th>户口迁出时间</th>
			<td>
				<html:text property="hkqcsj" readonly="true" onclick="return showCalendar('hkqcsj','y-mm-dd');" styleId="hkqcsj" name="rs"/>
			</td>
		</tr>
		<tr>
			<th>是否缴费</th>
			<td colspan="3">
				<html:select property="sfjf" styleId="sfjf" name="rs">
				<html:option value=""></html:option>
				<html:option value="已缴">已缴</html:option>
				<html:option value="未缴">未缴</html:option>	
				</html:select>
			</td>			
		</tr>
		<tr>
			<th>备注</th>
			<td colspan="3">
				<html:textarea property="bz" name="rs" rows="3" cols="60" onclick="chLeng(this,300)"> </html:textarea>
			</td>			
		</tr>
		</tbody>
		<tfoot>
		      <tr>
		        <td colspan="4"><div class="bz">"<span class="red">*</span>"为必填项</div>
				  <div class="btn">
		          <logic:notEqual value="student" name="user">
				  <logic:notEqual value="view" name="doType">
					<button type="button"
						onclick="doSave()"
						style="width:80px">
						保 存
					</button>
					</logic:notEqual>
					</logic:notEqual>						
					&nbsp;&nbsp;&nbsp;&nbsp;
					<button type="button" onclick="window.close();return false;"
						style="width:80px">
						关 闭
					</button>	
		          </div>
		        </td>
		      </tr>
		    </tfoot>		
		</table>

		<logic:notEmpty name="result">
			<logic:equal value="true" name="result">
				<script>
					alert("操作成功！");
					Close();
					if(window.dialogArguments){
						window.dialogArguments.document.getElementById('search_go').click();
					}
				</script>
			</logic:equal>
			<logic:equal value="false" name="result">
				<script>
					alert("操作失败！");
				</script>
			</logic:equal>
		</logic:notEmpty>
	</html:form>
</body>
</html>
