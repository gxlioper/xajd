<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script type="text/javascript"
			src="/xgxt/dwr/interface/getStuDetails.js"></script>
		<script language="javascript">
	function loadShi(){
		var shen = document.getElementById("jgshen").value;	
		getStuDetails.getShiList(shen,function(data){
			if (data.shiList != null) {
					var objId = "jgshi";				
					if($(objId) && $(objId).tagName.toLowerCase() == "select"){
						DWRUtil.removeAllOptions(objId);					
						DWRUtil.addOptions(objId,data.shiList,"shidm","shimc");
					}
				}else{
					showMsgWin("有错误出现：远程数据读取失败！");
				}	
			if (data.xianList !=null){
				var objId = "jgxian";
					if($(objId) && $(objId).tagName.toLowerCase() == "select"){
						DWRUtil.removeAllOptions(objId);					
						DWRUtil.addOptions(objId,data.xianList,"xiandm","xianmc");
					}
			}
		});
	}	
	function add(){
      var dwmc = document.getElementById("dwmc").value;   
      var dwxz = document.getElementById("dwxz").value; 
      var zgbm = document.getElementById("zgbm").value; 
      var dz = document.getElementById("dz").value;
      var lxbm = document.getElementById("lxbm").value;
      var lxr = document.getElementById("lxr").value;
      var dwjj = document.getElementById("dwjj").value;
     
      if(dwmc==""){
      alert("单位名称不能为空！");
      return false;
      }
      if(dwxz==""){
      alert("请填写单位性质选项！");
      return false;
      }
      if(zgbm==""){
      alert("主管部门不能为空！");
      return false;
      }
      if(dz==""){
      alert("通讯地址不能为空！");
      return false;
      }
      if(lxbm==""){
      alert("联系部门不能为空！");
      return false;
      }
      if(lxr==""){
      alert("联系人不能为空！");
      return false;
      }
      if(dwjj==""){
      alert("单位较少不能为空！");
      return false;
      }
      
         BatAlert.showTips('正在提交，请稍侯...');
		 document.forms[0].action = "dwxxInput.do?act=save&dwupdate=dwupdate";
		 document.forms[0].submit();
        
    }
    
	</script>
	</head>
	<body>
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置:</em><a>单位信息录入</a>
			</p>
		</div>

		<html:form action="/stuxsjyxxinput" method="post">
			<div class="tab">
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>单位信息录入</span>
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
							<th width="16%">
								<font color="red">*</font>单位名称
								<html:hidden name="rs" property="dwid" />
							</th>
							<td width="30%">
								<html:text name="rs" property="dwmc" />
							</td>
							<th width="16%">
								<font color="red">*</font>单位性质
							</th>
							<td width="30%">
								<html:select name="rs" property="dwxz" styleId="dwxz"
									style="width:180px">
									<html:option value=""></html:option>
									<html:options collection="dwxxdm" property="dwxz" />
								</html:select>
							</td>
						</tr>
						<tr>
							<th>
								单位类别
							</th>
							<td>
								<html:text name="rs" property="dwlb" />
							</td>
							<th>
								<font color="red">*</font>主管部门
							</th>
							<td>
								<html:select name="rs" property="zgbm" styleId="zgbm"
									style="width:180px">
									<html:option value=""></html:option>
									<html:options collection="sydzgbmList" property="zgbm"
										labelProperty="zgbm" />
								</html:select>
							</td>
						</tr>
						<tr>
							<th>
								<font color="red">*</font>所属行业
							</th>
							<td colspan="3">
								<html:select name="rs" property="hyfl" styleId="sshy"
									style="width:225px">
									<html:option value=""></html:option>
									<html:options collection="hyflList" property="hyfl"
										labelProperty="hyfl" />
								</html:select>
							</td>
						</tr>
						<tr>
							<th>
								所在地区
							</th>
							<td colspan="3">
								<html:select name="rs" property="szdqsh" onchange="loadShi()"
									styleId="jgshen">
									<html:option value=""></html:option>
									<html:options collection="ssList" property="ssdm"
										labelProperty="ssmc" />
								</html:select>
								<html:select name="rs" property="szdqsi" styleId="jgshi">
									<html:option value=""></html:option>
									<html:options collection="shiList" property="shidm"
										labelProperty="shimc" />
								</html:select>
							</td>
						</tr>
						<tr>
							<th>
								成立日期
							</th>
							<td>
								<html:text name="rs" property="clrq"
									onclick="return showCalendar('clrq','y-mm-dd');" />
							</td>
							<th>
								<font color="red">*</font>通讯地址
							</th>
							<td>
								<html:text name="rs" property="dz" />
							</td>
						</tr>
						<tr>
							<th>
								邮政编码
							</th>
							<td>
								<html:text name="rs" property="yb" />
							</td>
							<th>
								<font color="red">*</font>联系部门
							</th>
							<td>
								<html:text name="rs" property="lxbm" />
							</td>
						</tr>
						<tr>
							<th>
								<font color="red">*</font>联系人
							</th>
							<td>
								<html:text name="rs" property="lxr" />
							</td>
							<th>
								<font color="red">*</font>联系电话
							</th>
							<td>
								<html:text name="rs" property="lxdh" />
							</td>
						</tr>
						<tr>
							<th>
								单位传真
							</th>
							<td>
								<html:text name="rs" property="cz" />
							</td>
							<th>
								邮箱
							</th>
							<td>
								<html:text name="rs" property="email" />
							</td>
						</tr>
						<tr>
							<th>
								单位主页
							</th>
							<td>
								<html:text name="rs" property="dwzy" />
							</td>
							<th>
								档案邮政编码
							</th>
							<td>
								<html:text name="rs" property="dayzbm" />
							</td>
						</tr>
						<tr>
							<th>
								档案转寄地址
							</th>
							<td colspan="3">
								<html:text name="rs" property="dazjdz"  style="width:500px"/>
							</td>
						</tr>
						<tr>
							<th>
								<font color="red">*</font>单位介绍
							</th>
							<td colspan="3">
								<html:textarea name="rs" property="dwjj" style="width:500px"
									rows="5"></html:textarea>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
		</html:form>

		<logic:notEmpty name="update">
			<logic:equal name="update" value="ok">
				<script>
                      alert("提交成功！");
                      Close();
                      window.dialogArguments.document.getElementById('search_go').click();
                    </script>
			</logic:equal>
			<logic:equal name="update" value="no">
				<script>
                      alert("重复提交！操作失败!");
                      Close();
                       window.dialogArguments.document.getElementById('search_go').click();
                    </script>
			</logic:equal>
		</logic:notEmpty>
	</body>
</html>

