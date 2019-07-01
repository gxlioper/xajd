<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
				<script type="text/javascript" src="xsgzgl/gygl/gypynew/yxxs/js/yxxs.js"></script>
				<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
				<script type="text/javascript" src="js/check.js"></script>
		<script>
			function sendXxGy(){
				var tableName=$("tableName").value;
				var url = "gyjl_gyjlglnew_gyjlxscx.do";
				//showTopWin(url,800,600);
				showDialog("", 800, 525, url);
			}
			function getStuInfo(){
				jQuery.ajaxSetup({async:false});
				var parameter = {};
				parameter["xh"]=escape(jQuery("#wjxh").val());
				var url="gypy.do?method=loadXsxx";
				jQuery.getJSON(url,parameter,
						function(data){
							if(data!=null){
								jQuery("#xm").html(data.xm);
								jQuery("#xb").html(data.xb);
								jQuery("#xymc").html(data.xymc);
								jQuery("#zymc").html(data.zymc);
								jQuery("#bjmc").html(data.bjmc);
								jQuery("#ldmc").html(data.ldmc);
								jQuery("#qsh").html(data.qsh);
							}
						}
					);
				jQuery.ajaxSetup({async:true});
			}
		</script>
	</head>
	<body>
		<html:form action="/gypy" method="post" styleId="form">
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ��:</em><a>��������ѧ��</a>
			</p>
		</div>
		<html:hidden property="pylx" value="3"/>
		<input type="hidden" name="tableName" id="tableName" value="view_xsjbxx"/>	
		<div style='tab'>
			<table class="formlist"  width="95%">
			<tbody>
				<tr>
					<th width="20%" align="right"><font color="red">*</font>ѧ�ţ�</th>
					<td width="30%">
					<button type="button" id="btn_getStuInfo" onclick="getStuInfo();" style="display: none;"></button>
					<html:text  property="gldm" styleId="wjxh" value="${rs.xh}"  
							 maxlength="20" readonly="true" style="width:60%"/>
							<button type="button" onclick="sendXxGy()"
								class="btn_01" id="buttonFindStu">
								ѡ��
							</button>
					</td>
					<th width="20%">������</th>
					<td width="30%">
						<span  id="xm"></span>
					</td>
				</tr>
				<tr>
					<th>�Ա�</th>
					<td>
						<span  id="xb"></span>
					</td>
					<th><bean:message key="lable.xb" />��</th>
					<td>
						<span  id="xymc"></span>
					</td>
				</tr>
				<tr>
					<th>�༶��</th>
					<td>
						<span  id="bjmc"></span>
					</td>
					<th>רҵ��</th>
					<td>
						<span  id="zymc"></span>
					</td>
				</tr>
				<tr>
					<th>¥����</th>
					<td>
						<span  id="ldmc"></span>
					</td>
					<th>���Һţ�</th>
					<td>
						<span  id="qsh"></span>
					</td>
				</tr>
				<tr>
					<th><font color="red">*</font>ѧ�꣺</th>
					<td>
						<html:select property="xn" styleId="xn" >
							<html:options collection="xnList" labelProperty="xn" property="xn"/>
						</html:select>
					</td>
					<th><font color="red">*</font>ѧ��:</th>
					<td>
						<html:select property="xqdm" styleId="xqdm">
							<html:options collection="xqList" labelProperty="xqmc" property="xqdm"/>
						</html:select>
					</td>
				</tr>
				<tr>
					<th><font color="red">*</font>��ѡ���ɣ�</th>
					<td colspan="3">
						<html:textarea styleId="pyly" property="pyly" rows="4" style="width:95%" onblur="checkLen(this,100)"/>
					</td>
				</tr>
				</tbody>
				<tfoot>
			      <tr>
			        <td colspan="4"><div class="bz">"<span class="red">*</span>"Ϊ������</div>
			          <div class="btn">
			          	<button type="button" name="�ύ" id="buttonSave"  onclick="save('gypy.do?method=add&type=save','wjxh-xn-xqdm-pyly');">����</button>
			            <button type="button" name="�ر�" id="buttonClose" onclick="Close();return false;">�ر�</button>
			          </div></td>
			      </tr>
			   </tfoot>
			</table>
		</div>
		</html:form>
	</body>

</html>