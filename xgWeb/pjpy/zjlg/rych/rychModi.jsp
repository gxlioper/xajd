<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	<%@ include file="/syscommon/pagehead_V4.ini"%>
	
	<script type="text/javascript" src="/xgxt/dwr/interface/getPjpyDao.js"></script>
	<script type="text/javascript" src="/xgxt/dwr/interface/getStuDetails.js"></script>
	<script language="javascript" src="js/pjpy/pjpy_zjsyzy.js"></script>
	<script language="javascript">	     
     function rychSqSave(){
        var xh = "";
        var rychdm = "";
        var bz  = "";
        var xn  = "";
        var pkValue= "";
        if($("xh")){
           pkValue = $("pkValue").value;
        }
        if($("xh")){
           xh = $("xh").value;
        }
        if($("rychdm")){
           rychdm = $("rychdm").value;
        }
        if($("bz")){
          bz = $("bz").value;
        }
        if($("xn")){
          xn= $("xn").value;
        }
        if(xh==""){
          alert("学号不能为空！");
          return false;
        }
        if(rychdm==""){
          alert("荣誉称号不能为空！");
          return false;
        }  
        if(bz.length>1000){
          alert("备注字数过大，限1000字内！");
          return false;
        }
        if(xn==""){
          alert("学年不能为空！");
          return false;
        }
        var url= "/xgxt/zjlgPjpy.do?method=rychModi&doType=save";
        var tem = xh+xn+rychdm; 
        getPjpyDao.getInfoEx("xsrychb","xh||xn||rychdm",pkValue," xysh='通过'",function(tag){
		     if(tag){
		        alert("该信息已经通过审核，不能再进行操作！");
		     }else{
		        if(confirm("确定要保存数据！")){
		           refreshForm(url);
		           if($("buttonSave")){
                     $("buttonSave").disabled=true;
                   }
		        }              
		     }
    	});	                  
     }
     function viewLoad(){
        var act = "";
        if($("act")){
          act = $("act").value;
        }
        if(act=="view"){
          $("buttonSave").style.display="none";
        }
     }
</script>
</head>
	<%--		<input type="hidden" id="printpk" value="${printpk }"/>--%>
	<body onload="viewLoad()">
		<html:form action="/zjlgPjpy" method="post">
			<div class="tab_cur">
				<p class="location">
					<em>您的当前位置:</em><a>评奖评优 - 荣誉称号申请 - 申请结果查询 - 学生荣誉称号信息增加</a>
				</p>
			</div>

			<logic:equal name="rs" property="stuExists" value="no">
				<script>
			    alert("您输入的学号无效!");
			    </script>
			</logic:equal>
			<input type="hidden" id="disableEle" name="disableEle"
				value="xm-xb-xy-nj-zy-bj" />
			<input type="hidden" id="getStuInfo" name="getStuInfo"
				value="xm-xb-xymc-nj-zymc-bjmc" />
			<input type="hidden" id="url" name="url"
				value="/zjlgPjpy.do?method=rychAdd" />
			<input type="hidden" id="pkValue" name="pkValue"
				value="<bean:write name="pkValue"/>" />
			<input type="hidden" id="act" name="act"
				value="<bean:write name="act"/>" />
			<%--				<div align="left">--%>
			<%--				<br>--%>
			<%--				<font color="red">--%>
			<%--				提示：此功能只有已分配权限的学校用户或管理员可操作，且添加信息后不需要审核即可通过。--%>
			<%--				</font>--%>
			<%--				</div>		--%>
			<div class="tab">
			<table width="99%" id="rsT" class="formlist">
				<thead>
					<tr style="height:22px">
						<th colspan="4">
							<span>填写信息表</span>
						</th>
					</tr>
				</thead>
				<tbody>
				<tr style="height:22px">
					<th align="right" style="width: 10%">
						<font color="red">*</font>学号
					</th>
					<td align="left">
						<html:text name='rs' property="xh" styleId="xh" readonly="true" />
					</td>
					<th align="right" style="width: 10%">
						学年
					</th>
					<td align="left" style="width: 40%">
						<html:select name="rsOther" property="xn" disabled="true">
							<html:options collection="xnList" property="xn"
								labelProperty="xn" />
						</html:select>
					</td>
				</tr>
				<tr style="height:22px">
					<th align="right">
						姓名
					</th>
					<td align="left">
						<bean:write name='rs' property="xm" />
					</td>
					<th align="right">
						<font color="red">*</font>荣誉称号
					</th>
					<td align="left">
						<html:select name='rsOther' property="rychdm" styleId="rychdm">
							<option value=""></option>
							<html:options collection="rychList" property="dm"
								labelProperty="mc" />
						</html:select>
					</td>
				</tr>
				<tr style="height:22px">
					<th align="right">
						性别
					</th>
					<td align="left">
						<bean:write name='rs' property="xb" />
					</td>
					<th align="right">
						生源地
					</th>
					<td align="left">
						<bean:write name='rs' property="syd" />
					</td>
				</tr>
				<tr style="height:22px">
					<th align="right">
						年级
					</th>
					<td align="left">
						<bean:write name='rs' property="nj" />
					</td>
					<th align="right">
						联系电话
					</th>
					<td align="left">
						<bean:write name='rs' property="lxdh" />
					</td>
				</tr>
				<tr style="height:22px">
					<th align="right">
						<bean:message key="lable.xsgzyxpzxy" />：
					</th>
					<td align="left">
						<bean:write name='rs' property="xymc" />
					</td>
					<th align="right">
						担任职务
					</th>
					<td align="left">
						<bean:write name='rs' property="zw" />
					</td>
				</tr>
				<tr style="height:22px">
					<th align="right">
						专业
					</th>
					<td align="left">
						<bean:write name='rs' property="zymc" />
					</td>
					<th align="right">
						政治面貌
					</th>
					<td align="left">
						${rs.zzmmmc }
					</td>
				</tr>
				<tr style="height:22px">
					<th align="right">
						班级
					</th>
					<td align="left">
						<bean:write name='rs' property="bjmc" />
					</td>
					<th align="right">
						特长
					</th>
					<td align="left">
						<bean:write name='rs' property="tc" />
					</td>
				</tr>
				<tr style="height:22px">
					<th align="right">
						家庭地址
					</th>
					<td align="left" colspan="3" align="left">
						<bean:write name='rs' property="jtdz" />
					</td>

				</tr>
				<tr style="height:22px">
					<th align="right">
						综测排名
					</th>
					<td align="left">
						<bean:write name='rsOther' property="zcbjpm" />
					</td>
					<th align="right">
						综测分
					</th>
					<td align="left">
						<bean:write name='rsOther' property="zhcj" />
					</td>
				</tr>
				<tr style="height:22px">
					<th align="right">
						智育排名
					</th>
					<td align="left">
						<bean:write name='rsOther' property="zypm" />
					</td>
					<th align="right">
						智育分
					</th>
					<td align="left">
						<bean:write name='rsOther' property="zycj" />
					</td>
				</tr>
				<tr style="height:22px">
					<th align="right">
						德育排名
					</th>
					<td align="left">
						<bean:write name='rsOther' property="dypm" />
					</td>
					<th align="right">
						德育分
					</th>
					<td align="left">
						<bean:write name='rsOther' property="dycj" />
					</td>
				</tr>
               <tr style="height:22px">
					<th align="right">
						体育分
					</th>
					<td align="left">
						<bean:write name='rsOther' property="tycj" />
					</td>
					<th align="right">
						
					</th>
					<td align="left">
						
					</td>
				</tr>				
				<tr style="height:22px">
					<th align="right">
						本人简介
						<br />
						<span class="style1">(限1000字)&nbsp;</span>
					</th>
					<td colspan="3" align="left">
						<html:textarea rows="8" style="width:98%" name='rsOther'
							property="bz" />
					</td>
				</tr>
				</tbody>
				
				<tfoot>
			      <tr>
			        <td colspan="4"><div class="bz">"<span class="red">*</span>"为必填项</div>
			          <div class="btn">
			          	<button type="button" name="提交" id="buttonSave" onclick="rychSqSave();">提 交</button>
			            <button type="button" name="关闭" id="buttonClose" onclick="window.close();return false;">关闭</button>
			          </div></td>
			      </tr>
			    </tfoot>
				
			</table>
			</div>
			
			<logic:equal name="done" value="true">
				<script>
			          alert("修改成功！");
                      if(window.dialogArguments && window.dialogArguments.document.all("search_go")){
    	                    window.dialogArguments.document.all("search_go").click();
    	                    Close();
                      }			          
			        </script>
			</logic:equal>
			<logic:equal name="done" value="false">
				<script>
			          alert("修改失败！");
			        </script>
			</logic:equal>
			<logic:equal name="pass" value="no">
				<script>
			        alert("该生不满足该项荣誉称号申请条件！");			    
			        </script>
			</logic:equal>
		</html:form>
	</body>
</html>
