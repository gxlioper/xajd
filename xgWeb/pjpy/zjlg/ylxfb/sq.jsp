<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
<script type="text/javascript" src="/xgxt/dwr/interface/getPjpyDao.js"></script>
<script type="text/javascript" src="/xgxt/dwr/interface/getXjydInfo.js"></script>
<script type='text/javascript' src='/xgxt/dwr/interface/GetListData.js'></script>
<script type="text/javascript" src="/xgxt/dwr/engine.js"></script>
<script type="text/javascript" src="/xgxt/dwr/util.js"></script>
<script language="javascript" src="js/function.js"></script>
<script language="javascript" src="js/pjpyFunction.js"></script>
<script type="text/javascript" src="js/AjaxFunction.js"></script>
<script type="text/javascript" src="pjpy/zjjd/zjjdjs/zjjdjs.js"></script>
<script type="text/javascript">
/*
 显示年级、<bean:message key="lable.xsgzyxpzxy" />、专业、班级列表 
*/
function showItems(){
	var items = document.getElementById("items");
	items.style.left = (screen.availwidth)/6;
	items.style.top = ((screen.availheight)/6)-20;
	items.style.display = "block";
	if ($("userType").value == "xy") {
		var tmp = 'xy';
		document.getElementById('xy').disabled = true;
	}
}
/*
 隐藏年级、<bean:message key="lable.xsgzyxpzxy" />、专业、班级列表 
*/
function hideItems(){
	var items = document.getElementById("items");
	//items.style.left = (screen.availWidth)/2;
	//items.style.top = (screen.availHeigh)/2;
	items.style.display = "none";
	document.getElementById("bjdm").value = document.getElementById("bj").value;
	refreshForm("pjpy_zjlg_ylxfbsq.do");
}

function hideDiv(){
	var items = document.getElementById("items");
	//items.style.left = (screen.availWidth)/2;
	//items.style.top = (screen.availHeigh)/2;
	items.style.display = "none";
}

function xjbjSave(){
    var bjdm = "";
    var bjqk = "";
    var xn   = "";
    if($("bjdm")){
       bjdm = $("bjdm").value;
    }
    if($("xn")){
       xn=$("xn").value;
    }
    if($("bjqk")){
       bjqk = $("bjqk").value;
    }
    if(bjdm==""){
       alert("班级代码不能为空！");
       return false;
    }
    if(xn==""){
       alert("学年不能为空！");
       return false;
    }
    if(bjqk.length>500){
       alert("班级情况字数过大，限500字内！");
       return false;
    }
    var tem = bjdm+xn;
     getPjpyDao.getInfoEx("zjlg_xjbjb","bjdm||xn",tem," yxsh='通过' ",function(tag){
		     if(tag){
		        alert("该学年、该班级已申请,且已通过相关部门审核\n或正在审核中,不能再次申请！");		           	         			        
		     }else{
		        if(confirm("确定要保存数据！")){
		           refreshForm("/xgxt/zjlgPjpy.do?method=xjbjSq&doType=save");
                   if($("buttonSave")){
                     $("buttonSave").disabled =true;
                   }
		        }              
		     }
    	});	    
}
</script>
</head>
<body style="font-size:14px"  onload="xyDisabled('xy');">
	<html:form action="/zjlgPjpyylxfb" method="post">
		<input type="hidden" name="njV" id="njV"/>
		<input type="hidden" name="xyV" id="xyV"/>
		<input type="hidden" name="zyV" id="zyV"/>
		<input type="hidden" name="bjV" id="bjV"/>
		<input type="hidden" name="message" id="message" value="${message }"/>
		<input type="hidden" id="isFdy" name="isFdy" value="${fdyQx }" />
		<input type="hidden" id="userName" name="userName" value="${userName }"/>
		<input type="hidden" id="userType" name="userType" value="${userType }" />
		<input type="hidden" id="isBzr" name="isBzr" value="${bzrQx }" />
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置:</em><a>评奖评优 - 荣誉称号申请 - 优良学风班级申请</a>
			</p>
		</div>
		<div id="items" style="display:none; position: absolute;background-color: #AFEEEE; ">
			<table class="tbstyle">
				<tr>
					<td>
						年级
					</td>
					<td>
						<html:select property="nj" onchange="initZyList();initBjList();"
							styleId="nj">
							<html:option value=""></html:option>
							<html:options collection="njList" property="nj"
								labelProperty="nj" />
						</html:select>
					</td>
				</tr>
				<tr>
					<td>
						<bean:message key="lable.xsgzyxpzxy" />
					</td>
					<td>
						<html:select property="xydm" onchange="initZyList();initBjList();"
							styleId="xy">
							<html:option value=""></html:option>
							<html:options collection="xyList" property="xydm"
								labelProperty="xymc" />
						</html:select>
					</td>
				</tr>
				<tr>
					<td>
						专业
					</td>
					<td>
						<html:select property="zydm" onchange="initBjList();" styleId="zy">
							<html:option value=""></html:option>
							<html:options collection="zyList" property="zydm"
								labelProperty="zymc" />
						</html:select>
					</td>
				</tr>
				<tr>
					<td>
						班级
					</td>
					<td>
						<html:select property="bj" styleId="bj">
							<html:option value=""></html:option>
							<html:options collection="bjList" property="bjdm"
								labelProperty="bjmc" />
						</html:select>
					</td>
				</tr>
				<tr>
					<td colspan=2 align=center>
						<button type="button" class="button2" onclick="hideItems();">
							确 定
						</button>
						<button type="button" class="button2" onclick="hideDiv();">
							关 闭
						</button>
					</td>
				</tr>
			</table>
		</div>
		<input type="hidden" name="save_xn" value="${xn }"/>
		<div class="tab">
		<table width="100%" class="formlist">
			<thead>
				<tr style="height:22px">
					<th colspan="4">
						<span>填写申请表</span>
					</th>
				</tr>
			</thead>
			<tbody>
			<tr>
				<th width="25%">
					<font color="red">*</font>班级代码
				</th>
				<td align="left" width="35%">
					<html:text property="save_bjdm" readonly="true" onclick="showItems()"
						styleId="bjdm" />
				</td>
				<th width="25%">
					学年
				</th>
				<td align="left" style="width:25%">
					${xn }
				</td>
			</tr>
			<tr>
				<th>
					年级
				</th>
				<td align="left">
					${rs.nj}
				</td>
				<th>
					班级名称
				</th>
				<td align="left">
					${rs.bjmc}
				</td>
			</tr>
			<tr>
				<th>
					<bean:message key="lable.xsgzyxpzxy" />
				</th>
				<td align="left">
					${rs.xymc}
				</td>
				<th>
					辅导员
				</th>
				<td align="left">
					${rs.fdyxm}
				</td>
			</tr>
			
			<tr>
				<th>
					年度考试课优秀率
				</th>
				<td align="left">
					${map.kskyxl }
				</td>
				<th>
					年度考试课及格率
				</th>
				<td align="left">
					${map.kskjgl }
				</td>
			</tr>
			<tr>
				<th>
					年度考查课优秀率
				</th>
				<td align="left">
					${map.kckyxl }
				</td>
				<th>
					年度考查课及格率
				</th>
				<td align="left">
					${map.kckjgl }
				</td>
			</tr>
			<tr>
				<th>
					一、二年级英语课程平均分
				</th>
				<td align="left">
					 ${map.ynj }&nbsp;/&nbsp;${map.enj }
				</td>
				<th>
					班级学生处分次数
				</th>
				<td align="left">
					 ${map.wjcs }
				</td>
			</tr>
			<tr>
				<th>
					班级A级寝室比率
				</th>
				<td align="left">
					  ${map.ajl }
				</td>
				<th>
					班级文明寝室比率
				</th>
				<td align="left">
					 ${map.wml }
				</td>
			</tr>
			<tr>
				<th>
					班级特色寝室比率
				</th>
				<td align="left">
					  ${map.tsl }
				</td>
				<th>
					
				</th>
				<td align="left">
					
				</td>
			</tr>
			
			<tr>
				<th>
					班级出勤率
				</th>
				<td align="left">
					<html:text property="save_cql" styleId="cql" maxlength="5" onkeyup="ckinpdata(this)" style="width:50px"></html:text>
					(%)
				</td>
				<th>
					三、四、五年级&nbsp;&nbsp;<br/>英语四级过425分比例
				</th>
				<td align="left">
					<html:text property="save_sjtgl" styleId="sjtgl" maxlength="5" onkeyup="ckinpdata(this)" style="width:50px"></html:text>
					(%)
				</td>
			</tr>
			<tr>
				<th>
					计算机二级通过率
				</th>
				<td align="left">
					<html:text property="save_jsjtgl" styleId="jsjtgl" maxlength="5" onkeyup="ckinpdata(this)" style="width:50px"></html:text>
					(%)
				</td>
				<th>
					考研报考率
				</th>
				<td align="left">
					<html:text property="save_bkl" styleId="bkl" maxlength="5" onkeyup="ckinpdata(this)" style="width:50px"></html:text>
					(%)
				</td>
			</tr>
			<tr>
				<th>
					考研上线率
				</th>
				<td align="left">
					<html:text property="save_sxl" styleId="sxl" maxlength="5" onkeyup="ckinpdata(this)" style="width:50px"></html:text>
					(%)
				</td>
				<th>
					
				</th>
				<td align="left">
					
				</td>
			</tr>
			<tr>
				<th>
					班级学风建设&nbsp;<br/>主题班会活动
					<br/>
					<font color="red">(限制在1000以内)&nbsp;</font>
				</th>
				<td align="left" colspan="3">
					<html:textarea property="save_bhhd" styleId="bhhd" rows="5" style="width:600px" onkeyup="checkLen(this,1000)"></html:textarea>
				</td>
			</tr>
			<tr>
				<th>
					任课教师对&nbsp;<br/>班级学风评价
					<br/>
					<font color="red">(限制在1000以内)&nbsp;</font>
				</th>
				<td align="left" colspan="3">
					<html:textarea property="save_jsbjpj" styleId="jsbjpj" rows="5" style="width:600px" onkeyup="checkLen(this,1000)"></html:textarea>
				</td>
			</tr>
			<tr>
				<th>
					备注
					<br/>
					<font color="red">(限制在1000以内)&nbsp;</font>
				</th>
				<td align="left" colspan="3">
					<html:textarea property="save_bz" styleId="bz" rows="5" style="width:600px" onkeyup="checkLen(this,1000)"></html:textarea>
				</td>
			</tr>
			</tbody>
			<tfoot>
		      <tr>
		        <td colspan="4"><div class="bz">"<span class="red">*</span>"为必填项</div>
		          <div class="btn">
		            <button type="button" id="btn_save" onclick="saveinfo('pjpy_zjlg_ylxfbsq.do?act=save','bjdm')">
						保存
					</button>
		          </div></td>
		      </tr>
		    </tfoot>
		</table>
		</div>
	</html:form>
</body>
<logic:notEmpty name="result">
			<script>
				alert("" + $('message').value);
				if(window.dialogArguments && window.dialogArguments.document.all("search_go")){
						Close();
						window.dialogArguments.document.getElementById('search_go').click();	
				}
			</script>
		</logic:notEmpty>
</html>
