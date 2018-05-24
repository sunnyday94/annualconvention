var nametxt = $('.slot');
var userNameTxt = $('.name');
var pcount = xinm.length;//参加人数
var runing = true;
var trigger = true;
var num = 0;

$(function () {
	nametxt.css('background-image','url('+prizePic+')');
	userNameTxt.html(prizeLevelName);
	
	//预览奖品图放大(显示遮罩层)
    $("#luckuser").click(function(e){
    	$("#previewLargeImg").css('display','block');
    });
    
    //隐藏奖品预览图(遮罩隐藏)
    $("#previewLargeImg").click(function(e){
    	$("#previewLargeImg").css('display','none');
    });
    
});

// 开始停止
function start() {
	if (runing) {
		runing = false;
		$('#start').text('停止');
		startNum()
	} else {
		$('#start').text('自动抽取中('+ Lotterynumber+')');
		zd(updateUsersGetPrizeStatus);
	}
	
}


// 循环参加名单
function startNum() {
	num = Math.floor(Math.random() * pcount);
	nametxt.css('background-image','url('+xinm[num]+')');
	userNameTxt.text(userNames[num]+"("+jobNums[num]+")");
	t = setTimeout(startNum, 0);
}

// 停止跳动
function stop() {
	clearInterval(t);
	t = 0;
}

// 输入中奖人到列表
function zd(callback) {
	if (trigger) {
		trigger = false;
		if (  pcount >= Lotterynumber ) {
			stopTime = window.setInterval(function () {
				if (runing) {
					runing = false;
					$('#btntxt').removeClass('start').addClass('stop');
					startNum();
				} else {
					runing = true;
					$('#btntxt').removeClass('stop').addClass('start');
					stop();
					Lotterynumber--;
					$('#start').text('自动抽取中('+ Lotterynumber+')');

					if ( Lotterynumber == 0 ) {
						console.log("抽奖结束");
						window.clearInterval(stopTime);    //清除该定时器
						$("#start").text("抽奖结束");
						$('#start').attr('disabled',"true"); //添加disabled属性
					};
					//输出中奖者名单至列表
					$('.luck-user-list').prepend("<li><div class='portrait' style='background-image:url("+xinm[num]+")'></div><div class='luckuserName'>"+userNames[num]+"("+jobNums[num]+")</div></li>");
					//将中奖id加入数组中
					getPrizeUserIds.push(userIds[num]);
					//将已中奖者从数组中"删除",防止二次中奖
					xinm.splice($.inArray(xinm[num], xinm), 1);   
					userNames.splice($.inArray(userNames[num], userNames), 1);
					userIds.splice($.inArray(userIds[num], userIds), 1);
					jobNums.splice($.inArray(jobNums[num],jobNums), 1);
					//重新获取参加抽奖人数
					pcount = xinm.length;
					//当抽奖结束会，执行回调函数
					if(Lotterynumber == 0){
						callback.apply();
					}
				}
			},1000);
		};
	}
}

//更新用户获奖状态
function updateUsersGetPrizeStatus(){
	$.ajax({
		type:"POST",
		url:"savePrizeRecord?getPrizeUserIds="+getPrizeUserIds+"&prizeLevelId="+prizeLevelId,
		success:function(msg){
			if(msg.code == 10000){
				console.log("用户获奖状态更新成功");
			}
		}
	});
}
