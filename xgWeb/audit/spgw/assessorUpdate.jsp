<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <%@ include file="/syscommon/pagehead_V4.ini"%>
    <script type="text/javascript" src="/xgxt/dwr/interface/getYhList.js"></script>
    <script type='text/javascript' src='/xgxt/dwr/interface/GetListData.js'></script>
	<script type="text/javascript" src="/xgxt/dwr/engine.js"></script>
	<script type="text/javascript" src="/xgxt/dwr/util.js"></script>
	<script type="text/javascript" src="js/AjaxFunction.js"></script>
    <script language="javascript" src="js/function.js"></script>
</head>
<body>
    <html:form action="/xtwhSpgw" method="post">
        <div class="open_win">
            <h3>
                <em> 设置用户 </em>
            </h3>
            <div style="height: 387px;">
            <span class="opencon">
                <table class="opentable" width="100%" align="center"
                    cellpadding="4" cellspacing="0" id="tab1">
                    <tbody id="rsTable">
                    <tr align="center">
                    <td colspan="5">用户名称
                    <input type="text" name="person" id="person" />
                    &nbsp;&nbsp;&nbsp;用户组
                    <html:select property="zdm" styleId="zdm" style="width:150px">
                        <html:option value=""></html:option>
                        <html:options collection="zList" property="zdm"
                            labelProperty="zmc" />
                    </html:select>
                    </td>
                    </tr>
                    <tr>
                    <td colspan="5" align="center">
                        <button type="button" class="btn_bc" id="cx" onclick="query()">
                            查 询
                        </button>
                        <button type="button" class="btn_cz" id="cz" onclick="res()">
                            重 置
                        </button>
                    </td>
                    </tr>
                    <%-- 
                        <tr>
                            <td align="right" width="85px">
                                所属岗位
                            </td>
                            <td align="left" colspan="4">
                            <html:select  property="spgw"  styleId="spgw">
                                <html:option value="所在岗位"></html:option>
                                <html:options collection="spgwList" property="id" labelProperty="mc" />
                            </html:select>
                            </td>
                        </tr>
                     --%>
                        <tr>
                            <td align="center" >
                                可<br/>选<br/>用<br/>户
                            </td>
                            <td align="left">
                                <html:select  property="yhList"  styleId="yhList" style="width:120px;" size="15" multiple="true" ondblclick="addItemList('yhList','checkVal')">
                                    <html:options collection="yhList" property="yhm" labelProperty="xm" />
                                </html:select>
                            </td>
                            <td align="center" >
                               		<button type="button"
                                        onclick="addItemList('yhList','checkVal')">
                                        -&gt;添加所选
                              		</button>
                                    <br /><br />
                                    <button type="button"
                                        onclick="addItemList('checkVal','yhList')">
                                        &lt;-删除所选
                                    </button>
                                    <br /><br />
                                    <button type="button"
                                        onclick="addAll('yhList','checkVal')">
                                        &gt;&gt;全部添加
                                    </button>
                                    <br /><br />
                                    <button type="button"
                                        onclick="addAll('checkVal','yhList')">
                                        &lt;&lt;全部删除
                                    </button>
                            </td>
                            <td align="center" width="85px">已<br/>选<br/>用<br/>户</td>
                            <td align="left">
                          		<html:select  property="checkVal"  styleId="checkVal" style="width:120px;" size="15" multiple="true" ondblclick="addItemList('checkVal','yhList')">
                                    <html:options collection="gwyhList" property="yhm" labelProperty="xm" />
                            	</html:select>
                            </td>
                        </tr>
                    </tbody>
                </table> </span>
                </div>
                <span class="btn" style="float:right">
                	<html:hidden property="spgw" styleId="spgw"/>
                    <button type="button" class="btn_bc" onclick="save();return false;">
                        保 存
                    </button>
                    <button type="button" class="btn_cz" onclick="Close();return false;">
                        关 闭
                    </button> 
               </span>
            </div>
            <logic:notEmpty name="result">
				<logic:equal value="false" name="result">
					<script language="javascript">
					alert("操作失败！");
					</script>
				</logic:equal>
				<logic:equal value="true" name="result">
					<script language="javascript">
					alert("操作成功！");
					</script>
				</logic:equal>
				<script language="javascript">			
					if(window.dialogArguments){
						if(window.dialogArguments.document.getElementById("search_go")){
							dialogArgumentsQueryChick();
						}
						window.close();
					}
				</script>
			</logic:notEmpty>
		</html:form>
</body>
<script language=javascript>


	function addItemList(from, to) {
		var from = $(from), to = $(to);
		var l = from.options.length;
		for (var i = 0;i < l;i++) {
			if (from.options[i].selected) {
				to.appendChild(from.options[i]);
				i = i - 1;
				l = l - 1;
			}
		}
	}
	
	function addAll(from, to) {
		var from = $(from), to = $(to);
		for (var i = 0,l = from.options.length;i < l;i++) {
			to.appendChild(from.options[i]);
			i = i - 1;
			l = l - 1;
		}
	}
		
	function res(){
		$("person").value = "";
		$("zdm").value = "";
	}
	
	function save(){
		if(!CheckForm()){ return false;}
		var url="/xgxt/xtwhSpgw.do?method=spgwYhManage&doType=save";
		refreshForm(url);
	}
	
	if($("save")){
		$("save").onclick=function(){
			if(!CheckForm()){ return false;}
			var pars = "spgw="+$("bean.id").value;
			var id_list = new Array();
			var mappingItems = $("mappingItems");
			for (var i = 0;i < mappingItems.options.length;i++) {
				id_list[i] = mappingItems.options[i].value;
				pars += "&id_list["+i+"]="+mappingItems.options[i].value;
			}
			var url = "xtwh_spgwYhSave.action";
			//alert(pars);
			new Request({
				url:url,
				async:false,
				data: pars,
				onComplete: saveSuccess
			}).send();
		}
	}

	function CheckForm(){
		var checkVal =$("checkVal").options.length;		
		if(checkVal<1){
			alert("当前已选用户为空，请添加用户！");
			return false;
			/*
			if(!confirm("当前已选用户为空,是否保存?")){
				return false;
			}
			*/
		}else{
			for (var i = 0;i < checkVal;i++) {
				$("checkVal").options[i].selected = "selected";
			}
		}
		return true;
	}
	
	//用户查询结果使用AJAX返回
	function query(){
		var spgw = $("spgw").value;
		var query = "";
		var txt1 = $("person").value;
		var txt2 = $("zdm").value;
		
		if(txt1 != "" && txt1 != null){
			query += " and xm like '%"+txt1+"%'";
		} 
		if(txt2 != "" && txt2 != null){
			query += " and zdm = "+txt2;
		} 
		getYhList.getQueryYhList(spgw,query,initYhList);								   
	}
	
	function initYhList(data){
		if (data != null && typeof data == 'object') {
			DWRUtil.removeAllOptions("yhList");			
			DWRUtil.addOptions("yhList",data,"yhm","xm");
		}else{
			showMsgWin("有错误出现：远程数据读取失败！");
		}
	}
	
</script>
</html>