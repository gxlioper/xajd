<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript">
	function add(){
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
     if((email != null) && (email != "") && (!isEmail(email))){
     alert("电子邮箱不合法！");
     return false;
     }
     if((day==""&&(hour!=""||min!=""))||(day!=""&&hour==""&&min!="")||(day!=""&&hour!=""&&min=="")){
     alert("面试时间输入错误！");
     return false;
     }
     if($("xxdm").value=="10333"){
     	 var zphfs= $("zphfs").value;
     	 var zpsj= $("zpsj").value;
     	 var zpdd= $("zpdd").value; 
     	 if(zphfs==""){
     		alert("招聘会方式不能为空！");
     		return false;
    	 }else if(zphfs=="现场招聘会"){
     		if(zpsj=="" || zpdd==""){
				alert("招聘会时间和地点不能为空！");  
				return false;		
     		}
    	 }
     }
	document.forms[0].action = "/xgxt/zpxxsave.do?act=save";
	document.forms[0].submit();
        
    }
    
     //exclude left and right space; 
	function trim(s){
 		return rtrim(ltrim(s)); 
	}
	//exclude left space; 
	function ltrim(s){
 		return s.replace( /^\s*/, ""); 
	} 
	//exclude right space; 
	function rtrim(s){ 
 		return s.replace( /\s*$/, ""); 
	}
    
    function isEmail(s){
	s = trim(s); 
 	var p = /^[_\.0-9a-z-]+@([0-9a-z][0-9a-z-]+\.){1,4}[a-z]{2,3}$/i; 
 	return p.test(s);
    }
	
	function checklx(){
		$("zplx").value=$("zphfs").value;
		var zplx=$("zplx").value;
		if(zplx=="现场招聘会"){
			$("zpxxs").style.display = "";
		}else{
			$("zpxxs").style.display = "none";
		}
	}
	</script>
	</head>
	<body>

		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置:</em><a>就业管理 - 就业招聘 - 招聘信息发布</a>
			</p>
		</div>


		<html:form action="/data_search" method="post">
			<input type="hidden" name="xxdm" id="xxdm" styleId="xxdm"
				value="${xxdm }" />




			<div class="tab">
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>招聘信息发布</span>
							</th>
						</tr>
					</thead>
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="btn">
									<button onclick="add()">
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
							<th width="15%">
								<font color="red">*</font>招聘职位
							</th>
							<td width="35%">
								<html:text name="rs" property="zpzw" maxlength="50" />
							</td>
							<th width="15%">
								<font color="red">*</font>公司名称
							</th>
							<td width="35%">
								<html:text name="rs" property="gsmc" maxlength="50" />
								<logic:equal name="xxdm" value="10491" scope="session">
									<button onclick="showTopWin('/xgxt/zpxxdw.do',750,550);"
										class="btn_01" id="buttonFindStu">
										选择
									</button>
								</logic:equal>
							</td>
						</tr>
						<tr>
							<th>
								电子邮箱
							</th>
							<td>
								<html:text name="rs" property="email" style="width:225px"
									maxlength="50" />
							</td>
							<th>
								联系电话
							</th>
							<td>
								<html:text name="rs" property="lxdh" style="width:225px"
									maxlength="50" />
							</td>
						</tr>
						<tr>
							<th>
								联系人
							</th>
							<td>
								<html:text name="rs" property="lxr" style="width:225px"
									maxlength="20" />
							</td>
							<th>
								移动电话
							</th>
							<td>
								<html:text name="rs" property="yddh" style="width:225px"
									maxlength="50" />
							</td>
						</tr>
						<tr>
							<th>
								传真
							</th>
							<td>
								<html:text name="rs" property="cz" style="width:225px"
									maxlength="50" />
							</td>
							<th>
								公司网址
							</th>
							<td>
								<html:text name="rs" property="gswz" style="width:225px"
									maxlength="50" />
							</td>
						</tr>
						<tr>
							<th>
								工作地点
							</th>
							<td>
								<html:text name="rs" property="gzdd" style="width:225px"
									maxlength="25" />
							</td>
							<th>
								招聘人数
							</th>
							<td>
								<html:text name="rs" property="zprs" style="width:225px"
									maxlength="3" />
							</td>
						</tr>
						<tr>
							<th>
								行业分类
							</th>
							<td>
								<html:select name="rs" property="hyfl" styleId="hyfl"
									style="width:225px">
									<html:option value=""></html:option>
									<html:options collection="hyflList" property="hyfl"
										labelProperty="hyfl" />
								</html:select>
							</td>
							<th>
								外语要求
							</th>
							<td>
								<html:text name="rs" property="wyyq" style="width:225px"
									maxlength="10" />
							</td>
						</tr>
						<tr>
							<th>
								试用期薪水
							</th>
							<td>
								<html:select name="rs" property="syxs" style="width:225px">
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
								<html:select name="rs" property="zzxs" style="width:225px">
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
								</tdh>
							<td>
								<html:select name="rs" property="xlyq" style="width:225px">
									<html:option value=""></html:option>
									<html:option value="专科">专科</html:option>
									<html:option value="本科">本科</html:option>
									<html:option value="硕士">硕士</html:option>
									<html:option value="博士">博士</html:option>
								</html:select>
							</td>
							<th>
								面试时间：
							</th>
							<td>
								<html:text name="rs" style="cursor:hand; width:120px;"
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
								<html:text name="rs" property="msdd" style="width:225px"
									maxlength="30" />
							</td>
						</tr>
						<logic:equal name="xxdm" value="10333">
							<tr>
								<th>
									招聘会方式
								</th>
								<td>
									<html:select name="rs" property="zphfs" style="width:225px"
										styleId="zphfs" onchange="checklx()">
										<html:option value="">--请选择--</html:option>
										<html:option value="现场招聘会">现场招聘会</html:option>
										<html:option value="毕业生自行联系用人单位">毕业生自行联系用人单位</html:option>
									</html:select>
									<input type="hidden" name="zplx" id="zplx" styleId="zplx" />
								</td>
								<td>
								</td>
								<td>
								</td>
							</tr>
							<tr id="zpxxs" style="display:none">
								<th>
									时间
								</th>
								<td>
									<html:text name="rs" style="width:225px" property="zpsj"
										styleId="zpsj"
										onclick="return showCalendar('zpsj','y-mm-dd');"
										onblur="dateFormatChg(this)" readonly="true" />
								</td>
								<th>
									地点
								</th>
								<td>
									<html:text name="rs" style="width:225px" styleId="zpdd"
										property="zpdd" />
								</td>
							</tr>
						</logic:equal>
						<logic:notEqual name="xxdm" value="10690" scope="session">
							<tr>
								<th>
									岗位职责
								</th>
								<td colspan="3">
									<html:textarea name="rs" property="gwzz" rows="8" cols="100%" />
								</td>
							</tr>
						</logic:notEqual>
						<tr>
							<th>
								职位要求
							</th>
							<td colspan="3">
								<html:textarea name="rs" property="zwyq" rows="8" cols="100%" />
							</td>
						</tr>
						<tr>
							<th>
								公司简介
							</th>
							<td colspan="3">
								<html:textarea name="rs" property="gsjj" rows="8" cols="100%" />
							</td>
						</tr>
				</table>
		</html:form>

		<logic:notEmpty name="insert">
			<logic:equal name="insert" value="ok">
				<script>
                      alert("提交成功！");
                    </script>
			</logic:equal>
			<logic:equal name="insert" value="no">
				<script>
                      alert("重复提交！操作失败!");
                    </script>
			</logic:equal>
		</logic:notEmpty>
	</body>
</html>

