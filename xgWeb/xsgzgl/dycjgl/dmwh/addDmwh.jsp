<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript">
            //新增保存
            function saveForm(){
                var checkId = 'xmdm-xmmc-cjhgfsx';
                if(!checkNotNull(checkId)){
                    showAlertDivLayer("请将必填项填写完整！");
                    return false;
                }
                var xmdm =jQuery("#xmdm").val();
                if(xmdm.length>4){
                    showAlertDivLayer("项目代码长度过长！");
                    return false;
                }
                var cjhgfsx =jQuery("#cjhgfsx").val();
                if(cjhgfsx.length>2){
                    showAlertDivLayer("成绩合格分数判定字段长度过长！");
                    return false;
                }
                var xsxh =jQuery("#xsxh").val();
                if(xsxh.length>2){
                    showAlertDivLayer("显示序号字段长度过长！");
                    return false;
                }
                var url = "dycjgl_dmwh.do?method=saveDmwh";
                ajaxSubFormWithFun("DydmwhForm",url,function(data){
                    if(data["message"]=="保存成功！"){
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
	</head>


	<body >
		<html:form action="/dycjgl_dmwh" method="post" styleId="DydmwhForm" onsubmit="return false;">
			<div style='tab'>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="2">
								<span>增加德育成绩项目</span>
							</th>
						</tr>
					</thead>
					<tbody id="tbody_jbxx">
						<tr>
							<th width="16%">
								<font color="red">*</font>德育成绩类型代码
							</th>
							<td width="34%">
								<html:text property="xmdm" styleId="xmdm" maxlength="10" styleClass="text_nor" />
							</td>
						</tr>
						<tr>
							<th width="16%">
								<font color="red">*</font>德育成绩类型名称
							</th>
							<td width="34%">
								<html:text property="xmmc" styleId="xmmc" maxlength="30" styleClass="text_nor" />
							</td>
						</tr>
						<tr>
							<th width="16%">
								<font color="red">*</font>	成绩合格分数线判定
							</th>
							<td width="34%">
								<html:text property="cjhgfsx" styleId="cjhgfsx" maxlength="10" onblur="checkInputNum(this)" styleClass="text_nor" />
							</td>
						</tr>
						<tr>
							<th width="16%">
								显示序号
							</th>
							<td width="34%">
								<html:text property="xsxh" styleId="xsxh" maxlength="10" onblur="checkInputNum(this)" styleClass="text_nor" />
							</td>
						</tr>
						<tr>
							<th>评分说明<br/><span class="red">（限500字）</span></th>
							<td colspan="3">
								<textarea name="xmsm" id="xmsm" rows="4" cols="3" style="width:99%" onblur="checkLen(this,500);"></textarea>
							</td>
						</tr>

						
					</tbody>
					<tfoot>
						<tr>
							<td colspan="2">
								<div class="bz">"<span class="red">*</span>"为必填项</div>
								<div class="btn">
									<button type="button" type="button" onclick="saveForm();return false;">
										保 存
									</button>
									<button type="button" type="button" onclick="iFClose();return false;">
										关 闭
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

