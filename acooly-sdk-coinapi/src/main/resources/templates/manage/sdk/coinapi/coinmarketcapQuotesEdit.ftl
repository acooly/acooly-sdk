<#assign jodd=JspTaglibs["http://www.springside.org.cn/jodd_form"] />
<div>
    <form id="manage_coinmarketcapQuotes_editform" class="form-horizontal" action="/manage/sdk/coinapi/coinmarketcapQuotes/<#if action=='create'>saveJson<#else>updateJson</#if>.html" method="post" >
		<@jodd.form bean="coinmarketcapQuotes" scope="request">
        <input name="id" type="hidden" />
		<div class="card-body">
			<div class="form-group row">
				<label class="col-sm-3 col-form-label">币种编码</label>
				<div class="col-sm-9">
					<input type="text" name="coinCode" placeholder="请输入币种编码..." class="easyui-validatebox form-control"  data-options="validType:['text','length[1,16]']" required="true"/>
				</div>
			</div>
			<div class="form-group row">
				<label class="col-sm-3 col-form-label">更新时间毫秒</label>
				<div class="col-sm-9">
					<input type="text" name="lastUpdatedMillis" placeholder="请输入更新时间毫秒..." class="easyui-validatebox form-control" data-options="validType:['number[0,2147483646]'],required:true"/>
				</div>
			</div>
			<div class="form-group row">
				<label class="col-sm-3 col-form-label">价格更新时间</label>
				<div class="col-sm-9">
					<input type="text" name="lastUpdated" placeholder="请输入价格更新时间..." class="easyui-validatebox form-control" value="<#if coinmarketcapQuotes.lastUpdated??>${coinmarketcapQuotes.lastUpdated?string('yyyy-MM-dd HH:mm:ss')}</#if>" onFocus="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd HH:mm:ss'})"  />
				</div>
			</div>
			<div class="form-group row">
				<label class="col-sm-3 col-form-label">当前价格（USD）</label>
				<div class="col-sm-9">
						<input type="text" name="price" placeholder="请输入当前价格（USD）..." class="easyui-validatebox form-control" data-options="validType:['length[0,30]'],required:true"/>
				</div>
			</div>
			<div class="form-group row">
				<label class="col-sm-3 col-form-label">24小时交易量</label>
				<div class="col-sm-9">
						<input type="text" name="volume24h" placeholder="请输入24小时交易量..." class="easyui-validatebox form-control" data-options="validType:['length[0,30]']"/>
				</div>
			</div>
			<div class="form-group row">
				<label class="col-sm-3 col-form-label">24小时交易量变化</label>
				<div class="col-sm-9">
						<input type="text" name="volumeChange24h" placeholder="请输入24小时交易量变化..." class="easyui-validatebox form-control" data-options="validType:['length[0,30]']"/>
				</div>
			</div>
			<div class="form-group row">
				<label class="col-sm-3 col-form-label">价格1小时变化比</label>
				<div class="col-sm-9">
						<input type="text" name="percentChange1h" placeholder="请输入价格1小时变化比..." class="easyui-validatebox form-control" data-options="validType:['length[0,30]']"/>
				</div>
			</div>
			<div class="form-group row">
				<label class="col-sm-3 col-form-label">价格24小时变化比</label>
				<div class="col-sm-9">
						<input type="text" name="percentChange24h" placeholder="请输入价格24小时变化比..." class="easyui-validatebox form-control" data-options="validType:['length[0,30]']"/>
				</div>
			</div>
			<div class="form-group row">
				<label class="col-sm-3 col-form-label">价格7天变化比</label>
				<div class="col-sm-9">
						<input type="text" name="percentChange7d" placeholder="请输入价格7天变化比..." class="easyui-validatebox form-control" data-options="validType:['length[0,30]']"/>
				</div>
			</div>
			<div class="form-group row">
				<label class="col-sm-3 col-form-label">价格30天变化比</label>
				<div class="col-sm-9">
						<input type="text" name="percentChange30d" placeholder="请输入价格30天变化比..." class="easyui-validatebox form-control" data-options="validType:['length[0,30]']"/>
				</div>
			</div>
			<div class="form-group row">
				<label class="col-sm-3 col-form-label">市值</label>
				<div class="col-sm-9">
						<input type="text" name="marketCap" placeholder="请输入市值..." class="easyui-validatebox form-control" data-options="validType:['length[0,30]']"/>
				</div>
			</div>
			<div class="form-group row">
				<label class="col-sm-3 col-form-label">市场占有率</label>
				<div class="col-sm-9">
						<input type="text" name="marketCapDominance" placeholder="请输入市场占有率..." class="easyui-validatebox form-control" data-options="validType:['length[0,30]']"/>
				</div>
			</div>
			<div class="form-group row">
				<label class="col-sm-3 col-form-label">稀释后市值</label>
				<div class="col-sm-9">
						<input type="text" name="fullyDilutedMarketCap" placeholder="请输入稀释后市值..." class="easyui-validatebox form-control" data-options="validType:['length[0,30]']"/>
				</div>
			</div>
			<div class="form-group row">
				<label class="col-sm-3 col-form-label">备注</label>
				<div class="col-sm-9">
					<input type="text" name="comments" placeholder="请输入备注..." class="easyui-validatebox form-control"  data-options="validType:['text','length[1,128]']"/>
				</div>
			</div>
        </div>
      </@jodd.form>
    </form>
</div>
