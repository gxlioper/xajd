<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	<%@ include file="/syscommon/pagehead_V4.ini"%>
	<script type="text/javascript" src="/xgxt/dwr/interface/getStuDetails.js"></script>
	<script language="javascript" src="js/pjpy/pjpy_zjsyzy.js"></script>
	<script language="javascript">	     
     function rychSqSave(){
        var xh = "";
        var rychdm = "";
        var bz  = "";
        if($("xh")){
           xh = $("xh").value;
        }
        if($("rychdm")){
           rychdm = $("rychdm").value;
        }
        if($("bz")){
          bz = $("bz").value;
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
        refreshForm("/xgxt/zjlgPjpy.do?method=rychSq&doType=save");
        if($("buttonSave")){
          $("buttonSave").disabled=true;
        }
     }
     function rychBbPrint(){
         var rychdm ="";
         if($("rychdm")){rychdm = $("rychdm").value;}
         var xh     = "";
         if($("xh")){xh = $("xh").value;}
         var xn  = "";
         if($("xn")){ xn = $("xn").value;}
         if(rychdm==""){
            alert("请选择荣誉称号！");
            return false;
         }
         var url = "/xgxt/zjlgPjpy.do?method=rychDjb";
             url +="&xn="+xn;
         //"/xgxt/zjlgPjpy.do?method=rychDjb&rychdm="+rychdm;
            // url += "&xh="+xh;
             
             //url +="&bz="+bz;
        document.forms[0].action = url;
	    document.forms[0].target = "_blank";
	    document.forms[0].submit();
	    document.forms[0].target = "_self";
     }
</script>
</head>
	<body>
		<html:form action="/zjlgPjpy" method="post">
			<div class="tab_cur">
				<p class="location">
					<em>您的当前位置:</em><a>评奖评优 - 荣誉称号申请 - 填写申请表</a>
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
				value="/zjlgPjpy.do?method=rychSq" />
			<input type="hidden" id="stab" name="stab" value="stab" />
			<%--					<input type="hidden" id="xxdm" name="xxdm" value="${xxdm }" />--%>
			
			<div class="tab">
			<table width="99%" id="rsT" class="formlist">
				<thead>
					<tr style="height:22px">
						<th colspan="4">
							<span>填写申请表</span>
						</th>
					</tr>
				</thead>
				<tbody>
				<tr style="height:22px">
					<th align="right" style="width: 10%">
						<font color="red">*</font>学号
					</th>
					<logic:equal name="userOnLine" value="teacher" scope="session">
						<td align="left">
							<html:text name='rs' property="xh" styleId="xh"
								onblur="dctStuXh()"
								onkeypress="autoFillStuInfo(event.keyCode,this)" />
							<button type="button" onclick="showTopWin('/xgxt/stu_info.do',750,550);"
								class="btn_01" id="buttonFindStu">
								选择
							</button>
						</td>
					</logic:equal>
					<logic:equal name="userOnLine" value="student" scope="session">
						<td align="left">
							<html:hidden name='rs' property="xh" styleId="xh" />
							<bean:write name='rs' property="xh" />
						</td>
					</logic:equal>

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
						<html:select property="rychdm" styleId="rychdm">
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
					</td>				</tr>
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
						<bean:message key="lable.xsgzyxpzxy" />
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
						<html:textarea rows="8" style="width:98%" property="bz" />
					</td>
				</tr>
				</tbody>
				<tfoot>
			      <tr>
			        <td colspan="4"><div class="bz">"<span class="red">*</span>"为必填项</div>
			          <div class="btn">
			          	<button type="button" name="提交" id="buttonSave" onclick="rychSqSave();">提 交</button>
			            <button type="button" id="buttonSave" onclick="rychBbPrint()">
							报  表
						</button>
			          </div></td>
			      </tr>
			    </tfoot>
			</table>
			</div>
			
			<logic:equal name="done" value="true">
				<script>
			          alert("申请成功！");
			        </script>
			</logic:equal>
			<logic:equal name="done" value="false">
				<script>
			          alert("申请失败！");
			        </script>
			</logic:equal>
			<logic:equal name="isExist" value="no">
				<script>
			        alert("该荣誉称号已申请,且已通过相关部门审核\n或正在审核中,不能再次申请！");			    
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
