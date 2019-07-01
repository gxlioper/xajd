<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript">
	function isEmail(sEmail){	      
 	      var p = /^[_\.0-9a-z-]+@([0-9a-z][0-9a-z-]+\.){1,4}[a-z]{2,3}$/i; 
 	      return p.test(sEmail);
     }
	
	function zpxxupdate(){
      var email = document.getElementById("email").value;   
      var zpzw = document.getElementById("zpzw").value;   
      var gsmc = document.getElementById("gsmc").value;  
      var day = document.getElementById("day").value;
      var hour = document.getElementById("hour").value;
      var min = document.getElementById("min").value; 
     
      if(zpzw==""){
      alert("招聘职位不能为空！");
      return false;
      }
      if(gsmc==""){
      alert("公司名称不能为空！");
      return false;
      }      
     if(!isEmail(email)){
     alert("电子邮箱不合法！");
     return false;
     }
     if((day==""&&(hour!=""||min!=""))||(day!=""&&hour==""&&min!="")||(day!=""&&hour!=""&&min=="")){
     alert("面试时间输入错误！");
     return false;
     }
     
		 document.forms[0].action = "/xgxt/zpxxupdate.do?doType=update&act=update";
		 document.forms[0].submit();
        
    }
	
	</script>
	</head>
	<body>

		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置:</em><a>招聘信息修改</a>
			</p>
		</div>

		<html:form action="/data_search" method="post">
			<div class="tab">
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>招聘信息修改</span>
							</th>
						</tr>
					</thead>
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="btn">
									<button onclick="zpxxupdate()">
										提 交
									</button>
									<button type="reset">
										重 置
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
					<tbody>
						<tr>
							<th>
								发布时间
							</th>
							<td colspan="3">
								<html:text name="rs" property="fbsj" style="width:162px"
									readonly="true" />
							</td>
						</tr>
						<tr>
							<th width="13%">
								招聘职位
							</th>
							<td width="37%">
								<html:text name="rs" property="zpzw" style="width=100%"
									readonly="true" />
							</td>
							<th width="13%">
								公司名称
							</th>
							<td width="37%">
								<html:text name="rs" property="gsmc" style="width=100%"
									readonly="true" />
							</td>
						</tr>
						<tr>
							<th>
								电子邮箱
							</th>
							<td>
								<html:text name="rs" property="email" style="width=100%"
									maxlength="30" />
							</td>
							<th>
								联系电话
							</th>
							<td>
								<html:text name="rs" property="lxdh" style="width=100%"
									maxlength="15" />
							</td>
						</tr>
						<tr>
							<th>
								工作地点
							</th>
							<td>
								<html:text name="rs" property="gzdd" style="width=100%"
									maxlength="25" />
							</td>
							<th>
								招聘人数
							</th>
							<td>
								<html:text name="rs" property="zprs" style="width=100%"
									maxlength="3" />
							</td>
						</tr>
						<tr>
							<th>
								行业分类
							</th>
							<td>
								<html:select name="rs" property="hyfl" styleId="hyfl"
									style="width=100%">
									<html:option value=""></html:option>
									<html:options collection="hyflList" property="hyfl"
										labelProperty="hyfl" />
								</html:select>
							</td>
							<th>
								外语要求
							</th>
							<td>
								<html:text name="rs" property="wyyq" style="width=100%"
									maxlength="10" />
							</td>
						</tr>
						<tr>
							<th>
								试用期薪水
							</th>
							<td>
								<html:select name="rs" property="syxs" style="width=100%">
									<html:option value=""></html:option>
									<html:option value="面议">面议</html:option>
									<html:option value="1000以下">1000以下</html:option>
									<html:option value="1000-1500">1000-1500</html:option>
									<html:option value="1500-2500">1500-2500</html:option>
									<html:option value="2500-3500">2500-3500</html:option>
									<html:option value="3500-5000">3500-5000</html:option>
									<html:option value="5000-7000">5000-7000</html:option>
									<html:option value="7000-10000">7000-10000</html:option>
									<html:option value="10000以上">10000以上</html:option>
								</html:select>
							</td>
							<th>
								转正后薪水
							</th>
							<td>
								<html:select name="rs" property="zzxs" style="width=100%">
									<html:option value=""></html:option>
									<html:option value="面议">面议</html:option>
									<html:option value="1000以下">1000以下</html:option>
									<html:option value="1000-1500">1000-1500</html:option>
									<html:option value="1500-2500">1500-2500</html:option>
									<html:option value="2500-3500">2500-3500</html:option>
									<html:option value="3500-5000">3500-5000</html:option>
									<html:option value="5000-7000">5000-7000</html:option>
									<html:option value="7000-10000">7000-10000</html:option>
									<html:option value="10000以上">10000以上</html:option>
								</html:select>
							</td>
						</tr>
						<tr>
							<th>
								学历要求
							</th>
							<td>
								<html:select name="rs" property="xlyq" style="width=100%">
									<html:option value=""></html:option>
									<html:option value="专科">专科</html:option>
									<html:option value="本科">本科</html:option>
									<html:option value="硕士">硕士</html:option>
									<html:option value="博士">博士</html:option>
								</html:select>
							</td>
							<th>
								面试时间
							</th>
							<td>
								<html:text name="rs" style="cursor:hand; width:105px;"
									styleId="day" property="day"
									onclick="return showCalendar('day','y-mm-dd');" readonly="true" />
								<html:select name="rs" property="hour">
									<html:option value=""></html:option>
									<html:option value="06">06</html:option>
									<html:option value="07">07</html:option>
									<html:option value="08">08</html:option>
									<html:option value="09">09</html:option>
									<html:option value="10">10</html:option>
									<html:option value="11">11</html:option>
									<html:option value="12">12</html:option>
									<html:option value="13">13</html:option>
									<html:option value="14">14</html:option>
									<html:option value="15">15</html:option>
									<html:option value="16">16</html:option>
									<html:option value="17">17</html:option>
									<html:option value="18">18</html:option>
									<html:option value="19">19</html:option>
									<html:option value="20">20</html:option>
									<html:option value="21">21</html:option>
									<html:option value="22">22</html:option>
								</html:select>
								时
								<html:select name="rs" property="min">
									<html:option value=""></html:option>
									<html:option value="00">00</html:option>
									<html:option value="10">10</html:option>
									<html:option value="20">20</html:option>
									<html:option value="30">30</html:option>
									<html:option value="40">40</html:option>
									<html:option value="50">50</html:option>
								</html:select>
								分
							</td>
						</tr>
						<tr>
							<th>
								面试携带
							</th>
							<td>
								<html:text name="rs" property="msxd" style="width:225px"
									maxlength="30" />
							</td>
							<th>
								面试地点
							</th>
							<td>
								<html:text name="rs" property="msdd" style="width:200px"
									maxlength="30" />
							</td>
						</tr>
						<logic:notEqual name="xxdm" value="10690" scope="session">
							<tr>
								<th>
									岗位职责
								</th>
								<td colspan="3" >
									<html:textarea name="rs" property="gwzz" rows="6"
										style="width:500px" />
								</td>
							</tr>
						</logic:notEqual>
						<tr>
							<th>
								职位要求
							</th>
							<td colspan="3">
								<html:textarea name="rs" property="zwyq" rows="6"
									style="width:500px" />
							</td>
						</tr>
						<tr>
							<th>
								公司简介
							</th>
							<td colspan="3">
								<html:textarea name="rs" property="gsjj" rows="6"
									style="width:500px" />
							</td>
						</tr>
						</tbody>
				</table>
		</html:form>
		<logic:notEmpty name="update">
			<logic:equal name="update" value="ok">
				<script>
                      alert("修改成功！");
                    </script>
			</logic:equal>
			<logic:equal name="update" value="no">
				<script>
                      alert("操作失败!");
                    </script>
			</logic:equal>
		</logic:notEmpty>

	</body>
</html>
