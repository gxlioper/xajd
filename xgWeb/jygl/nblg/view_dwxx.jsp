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
      var txdz = document.getElementById("txdz").value;
      var lxbm = document.getElementById("lxbm").value;
      var lxr = document.getElementById("lxr").value;
      var dwjs = document.getElementById("dwjs").value;
     
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
      if(txdz==""){
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
      if(dwjs==""){
      alert("单位较少不能为空！");
      return false;
      }
      
         BatAlert.showTips('正在提交，请稍侯...');
		 document.forms[0].action = "dwxxInput.do?act=save";
		 document.forms[0].submit();
        
    }
    
	</script>
	</head>

	<body>
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置:</em><a>单位信息查看</a>
			</p>
		</div>

		<html:form action="/stuxsjyxxinput" method="post">
			<div class="tab">
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>单位信息查看</span>
							</th>
						</tr>
					</thead>
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="btn">
									<button onclick="close()">
										关 闭
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
					<tbody>
						<tr>
							<th width="16%">
								<font color="red">*</font>单位名称
							</th>
							<td width="30%">
								<html:text name="rs" property="dwmc" readonly="true" />
							</td>
							<th width="16%">
								<font color="red">*</font>单位性质
							</th>
							<td width="30%">
								<html:text name="rs" property="dwxz" readonly="true"/>
							</td>
						</tr>
						<tr>
							<th>
								单位类别
							</th>
							<td>
								<html:text name="rs" property="dwlb" readonly="true" />
							</td>
							<th>
								<font color="red">*</font>主管部门
							</th>
							<td>
								<html:text name="rs" property="zgbm" readonly="true" />
							</td>
						</tr>
						<tr>
							<th>
								<font color="red">*</font>所属行业
							</th>
							<td>
								<html:text name="rs" property="hyfl"  readonly="true" />
							</td>
							<th>
								所在地区
							</th>
							<td>
								<html:text property="sshy" value="${rs.szdqsh}${rs.szdqsi}"
									readonly="true" />
							</td>
						</tr>
						<tr>
							<th>
								成立日期
							</th>
							<td>
								<html:text name="rs" property="clrq" readonly="true" />
							</td>
							<th>
								<font color="red">*</font>通讯地址
							</th>
							<td>
								<html:text name="rs" property="dz" readonly="true" />
							</td>
						</tr>
						<tr>
							<th>
								邮政编码
							</th>
							<td>
								<html:text name="rs" property="yb" readonly="true" />
							</td>
							<th>
								<font color="red">*</font>联系部门
							</th>
							<td>
								<html:text name="rs" property="lxbm" readonly="true" />
							</td>
						</tr>
						<tr>
							<th>
								<font color="red">*</font>联系人
							</th>
							<td>
								<html:text name="rs" property="lxr" readonly="true" />
							</td>
							<th>
								<font color="red">*</font>联系电话
							</th>
							<td>
								<html:text name="rs" property="lxdh" readonly="true" />
							</td>
						</tr>
						<tr>
							<th>
								单位传真
							</th>
							<td>
								<html:text name="rs" property="cz" readonly="true" />
							</td>
							<th>
								邮箱
							</th>
							<td>
								<html:text name="rs" property="email" readonly="true" />
							</td>
						</tr>
						<tr>
							<th>
								单位主页
							</th>
							<td>
								<html:text name="rs" property="dwzy"
									readonly="true" />
							</td>
							<th>
								档案邮政编码
							</th>
							<td>
								<html:text name="rs" property="dayzbm" readonly="true" />
							</td>
						</tr>
						<tr>
							<th>
								档案转寄地址
							</th>
							<td colspan="3">
								<html:text name="rs" property="dazjdz" readonly="true" style="width:80%"/>
							</td>
						</tr>
						<tr>
							<th>
								<font color="red">*</font>单位介绍
							</th>
							<td colspan="3">
								<html:textarea name="rs" property="dwjj" rows="5" style="width:80%"
									readonly="true"></html:textarea>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
		</html:form>

		<logic:notEmpty name="save">
			<logic:equal name="save" value="ok">
				<script>
                      alert("提交成功！");
                      Close();
                      window.dialogArguments.document.getElementById('search_go').click();
                    </script>
			</logic:equal>
			<logic:equal name="save" value="no">
				<script>
                      alert("重复提交！操作失败!");
                      Close();
                       window.dialogArguments.document.getElementById('search_go').click();
                    </script>
			</logic:equal>
		</logic:notEmpty>
	</body>
</html>

