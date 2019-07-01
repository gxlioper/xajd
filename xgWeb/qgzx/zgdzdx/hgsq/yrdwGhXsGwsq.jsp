<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<!-- 头文件 -->
	<%@ include file="/syscommon/pagehead_V4.ini"%>
	<script type='text/javascript' src='/xgxt/dwr/interface/getOtherData.js'></script>
	<script language="javascript" src="js/qgzxFunction.js"></script>
	<script language="javascript" src="/xgxt/dwr/interface/cqkjFunc.js"></script>
	<script type="text/javascript">
		function chk(tmp){
		var i=tmp;
			if($('gwdmgwsbsj')){
				if ($("gwdmgwsbsj").selectedIndex <= 0) {
					alert("请选择岗位！");
					return false;
				}
			}
			
			if($('xh'+i)){
				if ($('xh'+i).value == "") {
					alert("请填写学号！");
					return false;
				}
			}
			
			if($('lxdh'+i)){
				if ($("lxdh"+i).value == "") {
					alert("请填写联系方式！");
					return false;
				}
			}
			return true;
		}
		
		var n=0;
		function add(str){
			var res="";
			var tmp = str.split("-");
			var table = tmp[0];
			var xh = $(tmp[1]).value;
			var lxdh = $(tmp[4]).value;
			var gw = $('gwdmgwsbsj').options[$('gwdmgwsbsj').selectedIndex].value;
			var gwdm = gw.split("-")[0];
			var gwsbsj = gw.split("-")[1];
			var userName = $('userName').value;
			var cz=""
			if($("ct").checked){
				cz = $('ct').value;
			}else{
				cz = $('hg').value;
			}
			
			dwr.engine.setAsync(false);
			getOtherData.insertYrdwghxslsb(xh,gwdm,gwsbsj,lxdh,cz,userName,table,function(data){
				if(data!=null && (data == "true" || data==true)){
					res = true;
				}else{
					res = false;
					
				}
				if(res==false){
					alert(data);
					return false;
				}
				n++;
				var _row = $(table).insertRow();
				for(i = 1;i<tmp.length;i++){
					_row.insertCell().innerHTML = '<input type="hidden" id="'+table+''+n+''+i+'" value="'+document.getElementById(tmp[i]).value+'"/>'+document.getElementById(tmp[i]).value;
				}
				i=i-4;
				_row.insertCell().innerHTML = '<button type="button" class="button2" onclick="deleteRow(this,'+table+''+n+''+i+'.value)">-</button>';
			});		
			dwr.engine.setAsync(true);
		}
		function deleteRow(obj,num){
			var xh = num;
			var gw = $('gwdmgwsbsj').options[$('gwdmgwsbsj').selectedIndex].value;
			var gwdm = gw.split("-")[0];
			var gwsbsj = gw.split("-")[1];
			
 			dwr.engine.setAsync(false);
 			getOtherData.deleteYrdwghxslsb(xh,gwdm,gwsbsj,function(data){
 				if(data!=null){
 					res = data;
 				}
 			});
 			dwr.engine.setAsync(true);
 			if(!res){
				alert(res);
				return false;
			}
 			//alert(obj.parentElement.parentElement.parentElement.parentElement.id);
 			//alert(obj.parentElement.parentElement.rowIndex);
 			obj.parentElement.parentElement.parentElement.deleteRow(obj.parentElement.parentElement.rowIndex);
		}
	</script>
</head>
	<body>
		<html:form action="/data_search" method="post">
		<input type="hidden" name="xxdm" id="xxdm" value="${xxdm}" />
		<input type="hidden" name="userName" id="userName" value="${rs.userName}"/>
		<input type="hidden" id="xbmc" value="<bean:message key="lable.xb" />"/>
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置:</em><a>${title }</a>
			</p>
		</div>
			<logic:present name="insert" >
				<script>
   				alert("所选学生中有重复或无效!");
   				</script>
			</logic:present>
			<input type="hidden" id="url" name="url" value="/qgzxYrdwGhXsGwsq.do" />
			
			<div class="tab">
			<table width="100%" id="rsT" class="formlist">
				<thead>
					<tr>
						<th colspan="4">
							<span>填写申请表</span>
						</th>
					</tr>
				</thead>
				<tbody>
				<tr>
					<th><span class="red">*</span>岗位名称</th>
					<td>
						<html:select name="rs" property="gwdmgwsbsj" styleId="xmdm"
							style="width:150px">
							<html:option value=""></html:option>
							<html:options collection="gwList" property="gwdmgwsbsj"
								labelProperty="gwdm" />
						</html:select>
					</td>
					<th>用人单位负责人</th>
					<td>
						<bean:write name='rs' property="lxr" />
					</td>
				</tr>
				<tr>
					<th>联系电话</th>
					<td>
						<bean:write name="rs" property="lxdh"/>
					</td>
					<th>单位地点</th>
					<td width="25%">
						<bean:write name="rs" property="yrdwdz"/>
					</td>
				</tr>
				<tr>
					<th colspan="4">
						用人单位拟更换人员详细信息:(<input type="radio" name="cz" id="ct" value="辞退" checked="checked"/>辞退  <input type="radio" name="cz" id="hg" value="换岗"/>换岗 )
					</th>
				</tr>
				<tr>
					<td colspan="4">
					<table width="800px" class="formlist">
						<tr>
							<td style="width: 200px">学号:<br/><input type="text" id="xh1" name="xh1" value="" /></td>
							<td style="width: 200px">姓名:<br/><input type="text" id="xm1" name="xm1" value="" /></td>
							<td style="width: 200px"><bean:message key="lable.xsgzyxpzxy" />:<br/><input type="text" id="xymc1" name="xymc1" value="" /></td>
							<td style="width: 200px">联系方式:<br/><input type="text" id="lxdh1" name="lxdh1" value="" />
							<td>
							<button type="button" class="button2" onclick="if(chk(1)){add('table1-xh1-xm1-xymc1-lxdh1')}">+</button>
							</td>
						</tr>
					</table>
					</td>
				</tr>
				<tr>
					<td colspan="4" >
						<table border="1" width="800px" id="table1" class="formlist">
							<colgroup>
								<col style="width: 200px"></col>
								<col style="width: 200px"></col>
								<col style="width: 200px"></col>
								<col style="width: 200px"></col>
								<col></col>
							</colgroup>
						</table>
					</td>
				</tr>
				<tr>
					<th colspan="4">用人单位拟聘用人员的详细信息</th>
				</tr>
				<tr>
					<td colspan="4">
					<table width="800px" class="formlist">
						<tr>
						<td style="width: 200px">学号:<br/><input type="text" id="xh2" name="xh2" value=""/></td>
						<td style="width: 200px">姓名:<br/><input type="text" id="xm2" name="xm2" value=""/></td>
						<td style="width: 200px"><bean:message key="lable.xsgzyxpzxy" />:<br/><input type="text" id="xymc2" name="xymc2" value=""/></td>
						<td style="width: 200px">联系方式:<br/><input type="text" id="lxdh2" name="lxdh2" value=""/>
						<td>
						<button type="button" class="button2" onclick="if(chk(2)){add('table2-xh2-xm2-xymc2-lxdh2')}">+</button>
						</td>
						</tr>
					</table>
					</td>
				</tr>
				<tr>
					<td colspan="4" >
						<table border="1" width="800px" id="table2" class="formlist">
							<colgroup>
								<col style="width: 200px"></col>
								<col style="width: 200px"></col>
								<col style="width: 200px"></col>
								<col style="width: 200px"></col>
								<col></col>
							</colgroup>
						</table>
					</td>
				</tr>
				<tr>
					<th><span class="red">*</span>更换人员理由</th>
					<td colspan="3">
						<html:textarea name='rs' property='sqly' styleId="bz"
							style="width:99%" rows='5' />
					</td>
				</tr>
				<tr>
					<th>岗位具体要求</th>
					<td colspan="3">
						<html:textarea name='rs' property='bz' styleId="bz"
							style="width:99%" rows='5' />
					</td>
				</tr>
				<tr>
					<td colspan="4">
						<table width="100%" class="formlist">
							<tr>
								<td width="20">用<br/>人<br/>单<br/>位<br/>负<br/>责<br/>老<br/>师<br/>意<br/>见</td>
								<td valign="bottom">签名盖章：<br/><br/>日期：</td>
								<td width="20">勤<br/>工<br/>助<br/>学<br/>办<br/>公<br/>室<br/>意<br/>见</td>
								<td valign="bottom">签名盖章：<br/><br/>日期：</td>
							</tr>
						</table>
					</td>
				</tr>
				<tr>
					<th>附注</th>
					<td colspan="3">
						请用人单位认真做好岗位考勤记录，并于次月6日前由单位将考
						勤表及酬金发放表送勤工助学办公室（新峰学生服务大厅106室），有其它情况与勤
						工助学办公室联系，联系人：高艳丽，电话67883311。
					</td>
				</tr>
				</tbody>
				<tfoot>
			      <tr>
			        <td colspan="4"><div class="bz">"<span class="red">*</span>"为必填项</div>
			          <div class="btn">
			            <button type="button" class="button2" onclick="zgdzdx_yrdwghxssq_chkisDataExist();">
							提 交 申 请
						</button>
						<button type="button" class="button2" onclick="expAppTab('rsT','用人单位增加（更换）学生岗位申请表','')">
							打 印 预 览
						</button>
			          </div>
			        </td>
			      </tr>
			    </tfoot>
			</table>
			</div>
		</html:form>
	</body>
</html>
