<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>	
		<script type='text/javascript' src='/xgxt/dwr/interface/getSztzData.js'></script>
		<script type="text/javascript"
			src="/xgxt/dwr/interface/getStuDetails.js"></script>
		<script type='text/javascript' src='/xgxt/js/check.js'></script>
		<script type="text/javascript">

		//保存 
		function sjbc(){
			
			//所有字段不能为空
			var array = jQuery(".bcClass");
			var flag = true;
			jQuery(array).each(function (i,n) {
				if (jQuery(n).val()=="" || jQuery(n).val()==null) {
					flag = false;
					alertError("带*号字段必须填写！");
					return false;	
				}
			});	

			//对家庭成员信息进行判断
			var fxm = jQuery('#fxm').val();
			if (fxm != null && fxm != "") {
				jQuery('.fClass').each(function(i,n){
					if (jQuery(n).val()=="" || jQuery(n).val()==null) {
						flag = false;
						alertError("家庭成员一的信息必须填写完整！");
						return false;	
					}
				});
			}
			var sxm = jQuery('#sxm').val();
			if (sxm != null && sxm != "") {
				jQuery('.sClass').each(function(i,n){
					if (jQuery(n).val()=="" || jQuery(n).val()==null) {
						flag = false;
						alertError("家庭成员二的信息必须填写完整！");
						return false;	
					}
				});
			}
			var txm = jQuery('#txm').val();
			if (txm != null && txm != "") {
				jQuery('.tClass').each(function(i,n){
					if (jQuery(n).val()=="" || jQuery(n).val()==null) {
						flag = false;
						alertError("家庭成员三的信息必须填写完整！");
						return false;	
					}
				});
			}

			if ((fxm==''&&sxm==''&&txm=='')) {
				flag = false;
				alertError("请至少填写一项家庭成员信息！");
				return false;	
			} else {
				if ((fxm==sxm&&fxm!='') || (sxm==txm&&sxm!='')) {
					flag = false;
					alertError("家庭成员姓名重复填写，请确认！");
					return false;	
				}
			}
			
			//进行保存操作
			if (flag) {
				refreshForm('xszz_jhzy_xgJtqkdz.do?act=save');
			}
		}
		</script>
		
	</head>
	<body >
		<html:form action="/jtqkdzGl" method="post" >
					<input type="hidden" name="message" id="message" value="${message }">		
					<input type="hidden" name="doType" id="doType"  >
		
		<div style="width:100%;height:630px;overflow-x:hidden;overflow-y:auto;">
		
					<table class="formlist" border="0" align="center" style="width: 100%">
				<tr style="height: 23px">
					<td align="center" colspan="4">
						<font size="5">
							${rs.xn }学年家庭情况调查 
						</font>
					</td>
				</tr>
			</table>
				
					<jsp:include page="/xsgzgl/xszz/jhzy/jtqkdz/xsxxxg.jsp" flush="true"></jsp:include>
					
				
						<table width="100%" border="0" class="formlist">
						<thead>
							<tr>
								<th colspan="9">
									<span>家庭调查情况</span>
								</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<th>序号</th>
								<th align="" >
									<div align="center"><font color="red">*</font>姓名</div>
								</th>
								<th align="right" >
									<div align="center"><font color="red">*</font>年龄</div>
								</th>
								<th align="right" >
									<div align="center"><font color="red">*</font>关系</div>
								</th>
								<th align="right" >
									<div align="center"><font color="red">*</font>工作(学习)单位</div>
								</th>
								<th align="right" >
									<div align="center"><font color="red">*</font>职业</div>
								</th>
								<th align="right" >
									<div align="center"><font color="red">*</font>联系电话</div>
								</th>
								<th align="right" >
									<div align="center"><font color="red">*</font>年收入</div>
								</th>
								<th align="right" >
									<div align="center"><font color="red">*</font>健康状况</div>
								</th>
								</tr>
									<tr>
								<td>1</td>
								<td>
									<html:text property="cyxm" styleClass="fClass" styleId="fxm" maxlength="10" style="width:75px" value="${jtxxList1.cyxm}"></html:text>
								</td>
								<td>
									<html:text property="cynl" styleClass="fClass" onkeyup="checkInputData(this)" maxlength="2"  style="width:40px" value="${jtxxList1.cynl}"></html:text>
								</td>
								<td>
									<html:select property="cygx"  style="width:73px" styleClass="fClass" value="${jtxxList1.cygx}">
										<html:option value=""></html:option>
										<html:option value="父亲">父亲</html:option>
										<html:option value="母亲">母亲</html:option>
										<html:option value="配偶">配偶</html:option>
										<html:option value="爷爷">爷爷</html:option>
										<html:option value="奶奶">奶奶</html:option>
										<html:option value="外公">外公</html:option>
										<html:option value="外婆">外婆</html:option>
										<html:option value="其他">其他</html:option>
									</html:select>
								</td>
								<td>
									<html:text property="cygzxxdw" styleClass="fClass" maxlength="15" style="width:150px" value="${jtxxList1.cygzxxdw}"></html:text>
								</td>
								<td>
									<html:text property="cyzy" styleClass="fClass" maxlength="10" style="width:70px" value="${jtxxList1.cyzy}"></html:text>
								</td>
								<td>
									<html:text property="cylxdh" styleClass="fClass" maxlength="20" style="width:70px" value="${jtxxList1.cylxdh}"></html:text>
								</td>
								<td>
									<html:text property="cynsr"  styleClass="fClass" onkeyup="checkInputNum(this)" maxlength="8" style="width:70px" value="${jtxxList1.cynsr}"></html:text>
								</td>
								<td>
									<html:text property="cyjkzk" styleClass="fClass" maxlength="8" style="width:70px" value="${jtxxList1.cyjkzk}"></html:text>
								</td>
							</tr>
							
							<tr>
								<td>2</td>
								<td>
									<html:text property="cyxm"  styleId="sxm" styleClass="sClass" maxlength="10" style="width:75px" value="${jtxxList2.cyxm}" ></html:text>
								</td>
								<td>
									<html:text property="cynl" styleClass="sClass" onkeyup="checkInputData(this)" maxlength="2" style="width:40px" value="${jtxxList2.cynl}"></html:text>
								</td>
								<td>
									<html:select property="cygx"  style="width:73px" styleClass="sClass" value="${jtxxList2.cygx}">
										<html:option value=""></html:option>
										<html:option value="父亲">父亲</html:option>
										<html:option value="母亲">母亲</html:option>
										<html:option value="配偶">配偶</html:option>
										<html:option value="爷爷">爷爷</html:option>
										<html:option value="奶奶">奶奶</html:option>
										<html:option value="外公">外公</html:option>
										<html:option value="外婆">外婆</html:option>
										<html:option value="其他">其他</html:option>
									</html:select>
								</td>
								<td>
									<html:text property="cygzxxdw"  styleClass="sClass" maxlength="15" style="width:150px" value="${jtxxList2.cygzxxdw}"></html:text>
								</td>
								<td>
									<html:text property="cyzy" styleClass="sClass" maxlength="10" style="width:70px" value="${jtxxList2.cyzy}"></html:text>
								</td>
								<td>
									<html:text property="cylxdh"  styleClass="sClass" maxlength="20" style="width:70px" value="${jtxxList2.cylxdh}"></html:text>
								</td>
								<td>
									<html:text property="cynsr" styleClass="sClass" onkeyup="checkInputNum(this)"  maxlength="8" style="width:70px" value="${jtxxList2.cynsr}"></html:text>
								</td>
								<td>
									<html:text property="cyjkzk"  styleClass="sClass" maxlength="8" style="width:70px" value="${jtxxList2.cyjkzk}"></html:text>
								</td>
							</tr>
							
							<tr>
								<td>3</td>
								<td>
									<html:text property="cyxm" styleId="txm" styleClass="tClass" maxlength="10" style="width:75px" value="${jtxxList3.cyxm}"></html:text>
								</td>
								<td>
									<html:text property="cynl" styleClass="tClass" onkeyup="checkInputData(this)" maxlength="2" style="width:40px" value="${jtxxList3.cynl}"></html:text>
								</td>
								<td>
									<html:select property="cygx"  style="width:73px" styleClass="tClass" value="${jtxxList3.cygx}">
										<html:option value=""></html:option>
										<html:option value="父亲">父亲</html:option>
										<html:option value="母亲">母亲</html:option>
										<html:option value="配偶">配偶</html:option>
										<html:option value="爷爷">爷爷</html:option>
										<html:option value="奶奶">奶奶</html:option>
										<html:option value="外公">外公</html:option>
										<html:option value="外婆">外婆</html:option>
										<html:option value="其他">其他</html:option>
									</html:select>
								</td>
								<td>
									<html:text property="cygzxxdw"  styleClass="tClass" maxlength="15" style="width:150px" value="${jtxxList3.cygzxxdw}"></html:text>
								</td>
								<td>
									<html:text property="cyzy" styleClass="tClass" maxlength="10" style="width:70px" value="${jtxxList3.cyzy}"></html:text>
								</td>
								<td>
									<html:text property="cylxdh"  styleClass="tClass" maxlength="20" style="width:70px" value="${jtxxList3.cylxdh}"></html:text>
								</td>
								<td>
									<html:text property="cynsr" styleClass="tClass" onkeyup="checkInputNum(this)"  maxlength="8" style="width:70px" value="${jtxxList3.cynsr}"></html:text>
								</td>
								<td>
									<html:text property="cyjkzk"  styleClass="tClass" maxlength="8" style="width:70px" value="${jtxxList3.cyjkzk}"></html:text>
								</td>
							</tr>
						</tbody>
					</table>
					
					<table width="100%" border="0" class="formlist">
						<thead>
							<tr>
								<th colspan="4">
									<span>家庭调查情况</span>
								</th>
							</tr>
						</thead>
						<tbody>
					<tr>
						<th align="right" width="20%">
							<font color="red">*</font>是否孤残
						</th>
						<td align="left" width="30%">
							
							<input type="radio" name="sfgc" value="是" <logic:equal value="是" name="rs" property="sfgc">checked</logic:equal>/>是
							<input type="radio" name="sfgc" value="否" <logic:equal value="否" name="rs" property="sfgc">checked</logic:equal>/>否
						</td>
						<th align="right" width="20%">
							<font color="red">*</font>是否单亲
						</th>
						<td align="left" width="30%">
							<input type="radio" name="sfdq" value="是" <logic:equal value="是" name="rs" property="sfdq">checked</logic:equal>/>是
							<input type="radio" name="sfdq" value="否" <logic:equal value="否" name="rs" property="sfdq">checked</logic:equal>/>否
						</td>
					</tr>
					<tr>
						<th align="right" width="20%">
							<font color="red">*</font>烈士子女
						</th>
						<td align="left" width="30%">
							<input type="radio" name="sflszn" value="是" <logic:equal value="是" name="rs" property="sflszn">checked</logic:equal>/>是
							<input type="radio" name="sflszn" value="否" <logic:equal value="否" name="rs" property="sflszn">checked</logic:equal>/>否
						</td>
						<th align="right" width="20%">
							<font color="red">*</font>是否低保
						</th>
						<td align="left" width="30%">
							<input type="radio" name="sfdb" value="是" <logic:equal value="是" name="rs" property="sfdb">checked</logic:equal>/>是
							<input type="radio" name="sfdb" value="否" <logic:equal value="否" name="rs" property="sfdb">checked</logic:equal>/>否
						</td>
					</tr>
					<tr>
						<th align="right" width="20%">
							<font color="red">*</font>家庭户口
						</th>
						<td align="left" width="30%">
							<input type="radio" name="jthk" value="城镇" <logic:equal value="城镇" name="rs" property="jthk">checked</logic:equal>/>城镇
							<input type="radio" name="jthk" value="农村" <logic:equal value="农村" name="rs" property="jthk">checked</logic:equal>/>农村
						</td>
						<th></th>
						<td></td>
						
					</tr>
					<tr>
						<th align="right" width="20%">
							<font color="red">*</font>所在省市县
						</th>
						<td align="left" colspan="3">
							<html:select  property="syshen" styleId="syshen" value="${rs.syshen}"
										onchange="loadShi('syshen','syshi','syxian')" style="width:200px">
										<html:option value="">--请选择--</html:option>
										<html:options collection="ssList" property="ssdm"
											labelProperty="ssmc" />
									</html:select>
							&nbsp;
							<html:select  property="syshi" styleId="syshi" value="${rs.syshi}"
										onchange="loadXian('syshi','syxian')" style="width:200px">
										<html:options collection="shiList" property="shidm"
											labelProperty="shimc" />
									</html:select>
							&nbsp;
							<html:select  property="syxian" styleId="syxian" value="${rs.syxian}" style="width:200px">
										<html:options collection="xianList" property="xiandm"
											labelProperty="xianmc" />
									</html:select>
						</td>
					</tr>
					
					<tr>
						<th align="right" width="20%">
							<font color="red">*</font>家庭地址
						</th>
						<td align="left" width="30%">
							<html:text property="jtdz" styleId="jtdz" maxlength="40" value="${rs.jtdz}"></html:text>
						</td>
						<th align="right" width="20%">
							<font color="red">*</font>家庭电话
						</th>
						<td align="left" width="30%">
							<html:text property="jtdh" styleId="jtdh" maxlength="20" value="${rs.jtdh}"></html:text>
						</td>
					</tr>
					<tr>
						<th align="right" width="20%">
							<font color="red">*</font>家庭邮编
						</th>
						<td align="left" width="30%">
							<html:text property="jtyb" styleId="jtyb" maxlength="7" onkeyup="checkInputData(this)" value="${rs.jtyb}"></html:text>
						</td>
						<th align="right" width="20%">
							<font color="red">*</font>家庭人口数
						</th>
						<td align="left" width="30%">
							<html:text property="jtrks" onkeyup="checkInputData(this)" styleId="jtrks" maxlength="2" value="${rs.jtrks}"></html:text>
						</td>
					</tr>
					<tr>
						<th align="right" width="20%">
							<font color="red">*</font>家庭人均收入
						</th>
						<td align="left" width="30%">
							<html:text property="jtrjsr" styleId="jtrjsr" maxlength="10" onkeyup="checkInputNum(this)" value="${rs.jtrjsr}"></html:text>（元）
						</td>
						<th align="right" width="20%">
							<font color="red">*</font>家庭月总收入
						</th>
						<td align="left" width="30%">
							<html:text property="jtnzsr" styleId="jtnzsr" maxlength="10" onkeyup="checkInputNum(this)" value="${rs.jtnzsr}"></html:text>（元）
						</td>
					</tr>
					<tr>
						<th align="right" width="20%">
							<font color="red">*</font>收入来源
						</th>
						<td align="left" width="30%">
							<html:select property="srly" styleId="srly" style="width:148px" value="${rs.srly}">
								<html:option value=""></html:option>
								<html:option value="政府救济">政府救济</html:option>
								<html:option value="亲朋资助">亲朋资助</html:option>
								<html:option value="务农">务农</html:option>
								<html:option value="打工">打工</html:option>
								<html:option value="小买卖">小买卖</html:option>
								<html:option value="其他">其他</html:option>
							</html:select>
						</td>
						<th align="right" width="20%">
							
						</th>
						<td align="left" width="30%">
							
						</td>
					</tr>
					<tr>
						<th align="right" width="20%">
							<font color="red">*</font>已获资助情况</br><font color="red">(限制500字以内)</font>
						</th>
						<td align="left" colspan="3">
							<textarea name="yhzzqk" id="yhzzqk" style="word-break:break-all;width:97%"
										rows='4' onblur="checkLen(this,500)" type="_moz">${rs.yhzzqk}</textarea>
						</td>
					</tr>
					<tr>
						<th align="right" width="20%">
							<font color="red">*</font>家庭受灾情况</br><font color="red">(限制500字以内)</font>
						</th>
						<td align="left" colspan="3">
							<textarea name="jtszqk" id="jtszqk" style="word-break:break-all;width:97%"
										rows='4' onblur="checkLen(this,500)" type="_moz">${rs.jtszqk }</textarea>
						</td>
					</tr>
					<tr>
						<th align="right" width="20%">
							<font color="red">*</font>突发事件情况</br><font color="red">(限制500字以内)</font>
						</th>
						<td align="left" colspan="3">
							<textarea name="tfsjqk" id="tfsjqk" style="word-break:break-all;width:97%"
										rows='4' onblur="checkLen(this,500)" type="_moz">${rs.tfsjqk }</textarea>
						</td>
					</tr>
					<tr>
						<th align="right" width="20%">
							<font color="red">*</font>残疾年迈情况</br><font color="red">(限制500字以内)</font>
						</th>
						<td align="left" colspan="3">
							<textarea name="cjnmqk" id="cjnmqk" style="word-break:break-all;width:97%"
										rows='4' onblur="checkLen(this,500)" type="_moz">${rs.cjnmqk }</textarea>
						</td>
					</tr>
					<tr>
						<th align="right" width="20%">
							<font color="red">*</font>家庭事业情况</br><font color="red">(限制500字以内)</font>
						</th>
						<td align="left" colspan="3">
							<textarea name="jtsyqk" id="jtsyqk" style="word-break:break-all;width:97%"
										rows='4' onblur="checkLen(this,500)" type="_moz">${rs.jtsyqk }</textarea>
						</td>
					</tr>
					<tr>
						<th align="right" width="20%">
							<font color="red">*</font>家庭欠债情况</br><font color="red">(限制500字以内)</font>
						</th>
						<td align="left" colspan="3">
							<textarea name="jtqzqk" id="jtqzqk" style="word-break:break-all;width:97%"
										rows='4' onblur="checkLen(this,500)" type="_moz">${rs.jtqzqk }</textarea>
						</td>
					</tr>
					<tr>
						<th align="right" width="20%">
							<font color="red">*</font>家庭其他情况</br><font color="red">(限制500字以内)</font>
						</th>
						<td align="left" colspan="3">
							<textarea name="jtqtqk" id="jtqtqk" style="word-break:break-all;width:97%"
										rows='4' onblur="checkLen(this,500)" type="_moz">${rs.jtqtqk }</textarea>
						</td>
					</tr>
					</tbody>
				</table>
				</div>
				<table class="formlist">
								<tfoot>
					<tr>
						<td colspan="4">
							<div class="bz">
								"<span class="red">*</span>"为必填项
							</div>
							<div class="btn">
								<button type="button" onclick="sjbc();" id="buttonSave">
									保 存
								</button>
								&nbsp;&nbsp;&nbsp;&nbsp;
								<button type="button" onclick="Close();return false;" id="buttonClose">
									关 闭
								</button>
							</div>
						</td>
					</tr>
				</tfoot>
				</table>
			<%@ include file="/comm/other/tsxx.jsp"%>
		</html:form>
	</body>
</html>
