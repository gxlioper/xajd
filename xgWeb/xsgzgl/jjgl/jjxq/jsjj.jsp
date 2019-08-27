<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<%@ include file="/syscommon/head.ini"%>
	<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
	<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
	<script type="text/javascript" src="js/check.js"></script>
</head>
<body style="width: 100%">
<html:form action="/jjgl_jjxq" method="post" styleId="jjxqForm" onsubmit="return false;">
	<input type="hidden" name="pjjsqid" value="${jjxqForm.sqid}"/>
	<input type="hidden" name="xqid" value="${xqwhMap.xqid}"/>
	<input type="hidden" name="jjcz" value="${jjxqForm.jjcz}"/>
	<div style='width:100%;overflow-x:hidden;overflow-y:auto;'>
		<table width="100%" border="0" class="formlist">
			<thead>
			<tr>
				<th colspan="4">
					<span>家教信息</span>
				</th>
			</tr>
			</thead>
			<tbody>
			<tr>
				<th width="18%">家教编号</th>
				<td width="82%" colspan="3">
						${xqwhMap.xqid}
				</td>
			</tr>
			<tr>
				<th width="18%">
					家长姓名
				</th>
				<td width="32%">
						${xqwhMap.jzxm}
				</td>
				<th width="18%">
					登记人
				</th>
				<td width="32%">
						${xqwhMap.djr}
				</td>
			</tr>
			<tr>
				<th width="18%">
					子女姓名
				</th>
				<td width="32%">
						${xqwhMap.znxm}
				</td>
				<th width="18%">
					子女性别
				</th>
				<td width="32%">
						${xqwhMap.znxb}
				</td>
			</tr>

			<tr>
				<th width="18%">
					需补科目
				</th>
				<td width="32%">
						${xqwhMap.jjxk}
				</td>
				<th width="18%">
					子女年级
				</th>
				<td width="32%">
						${xqwhMap.jjnj}
				</td>
			</tr>

			<tr>
				<th width="18%">
					家教地点
				</th>
				<td width="32%">
						${xqwhMap.jjdd}
				</td>
				<th width="18%">
					最低时薪
				</th>
				<td width="32%">
						${xqwhMap.jjsx}
				</td>
			</tr>

			<tr>
				<th width="18%">
					联系电话
				</th>
				<td width="32%" colspan="3">
						${xqwhMap.lxdh}
				</td>
			</tr>

			<tr>
				<th width="18%">
					家教要求
				</th>
				<td colspan="3" width="82%">
						${xqwhMap.jjlsyq}
				</td>

			</tr>
			<tr>
				<th width="18%">
					备注
				</th>
				<td colspan="3" width="82%">
						${xqwhMap.bz}
				</td>

			</tr>
			</tbody>
			<thead>
			<tr>
				<th colspan="4">
					<span>申请信息</span>
				</th>
			</tr>
			</thead>
			<tbody>
			<tr>
				<th>
					家教操作
				</th>
				<td colspan="3">
					<logic:equal name="jjxqForm" property="jjcz" value="0">
						退家教
					</logic:equal>
					<logic:equal name="jjxqForm" property="jjcz" value="4">
						关闭家教
					</logic:equal>
				</td>
			</tr>
			<tr>
				<th>
					<span class="red">*</span>申请理由
					<br />
					<font color="red">(不小于50字)</font>
				</th>
				<td colspan="3">
					<textarea id="sqly" name="sqly" rows="4" style="width:99%;" onblur="checkLen(this,350);"></textarea>
				</td>
			</tr>
			</tbody>
		</table>
	</div>
	<div style="height:35px"></div>
	<div>
		<table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
			<tfoot>
			<tr>
				<td colspan="4">
					<div class="bz">
						"<span class="red">*</span>"为必填项
					</div>
					<div class="btn">
						<button type="button" onclick="xssqSave();">
							提交申请
						</button>
						<button type="button" onclick="iFClose();">
							关闭
						</button>
					</div>
				</td>
			</tr>
			</tfoot>
		</table>
	</div>
</html:form>

<script type="text/javascript">
    /**
     * 学生退家教、结束家教申请的保存
     * @returns {boolean}
     */
    function xssqSave(){
        if (jQuery("#sqly").val() == ""){
            showAlert("请将必填项填写完整！");
            return false;
        }
//        else if (jQuery("#sqly").val().length < 50){
//            showAlert("申请理由少于50字！");
//            return false;
//		}
        var url = "jjgl_jjxq.do?method=saveJsjj";
        ajaxSubFormWithFun("jjxqForm",url,function(data){
            if(data["message"]=="提交成功！"){
                showAlert(data["message"],{},{"clkFun":function(){
                    if (parent.window){
                        refershParent();
                    }
                }});
            }else{
                showAlert(data["message"]);
            }
        });
    }
</script>
</body>
</html>

