
$(function() {
	var flag=0,flag1=0,flag2=0,flag3=0;
	$("#slider1").draggable({
		axis: 'x',
		containment: 'parent',
		drag: function(event, ui) {
			if (ui.position.left > 250&&flag1==0) {
				flag1=1;
				$("#slider1").fadeOut(500,function(){
					flag++;
					if(flag==3){
					var sub = document.getElementById("Submit");
					sub.style.visibility="visible";
					}
					//alert("haha");
				});
				//$("#well").fadeOut().fadeIn();
			} else {
			    // Apparently Safari isn't allowing partial opacity on text with background clip? Not sure.
				// $("h2 span").css("opacity", 100 - (ui.position.left / 5))
			}
		},
		stop: function(event, ui) {
			if (ui.position.left < 251) {
				//alert("not enough");
				$(this).animate({
					left: 0
				})
			}
		}
	});
	$("#slider2").draggable({
		axis: 'x',
		containment: 'parent',
		drag: function(event, ui) {
			if (ui.position.left > 435&&flag2==0) {
				flag2=1;
				$("#slider2").fadeOut(500,function(){
					flag++;
					if(flag==3){
					var sub = document.getElementById("Submit");
					sub.style.visibility="visible";
					}
				});
				//$("#well").fadeOut().fadeIn();
			} else {
			    // Apparently Safari isn't allowing partial opacity on text with background clip? Not sure.
				// $("h2 span").css("opacity", 100 - (ui.position.left / 5))
			}
		},
		stop: function(event, ui) {
			if (ui.position.left < 436) {
				//alert("not enough");
				$(this).animate({
					left: 0
				})
			}
		}
	});
	$("#slider3").draggable({
		axis: 'x',
		containment: 'parent',
		drag: function(event, ui) {
			if (ui.position.left > 590&&flag3==0) {
				flag3=1;
				$("#slider3").fadeOut(500,function(){
					flag++;
					if(flag==3){
					var sub = document.getElementById("Submit");
					sub.style.visibility="visible";
					}
				});
				//$("#well").fadeOut().fadeIn();
			} else {
			    // Apparently Safari isn't allowing partial opacity on text with background clip? Not sure.
				// $("h2 span").css("opacity", 100 - (ui.position.left / 5))
			}
		},
		stop: function(event, ui) {
			if (ui.position.left < 591) {
				//alert("not enough");
				$(this).animate({
					left: 0
				})
			}
		}
	});
	
	// The following credit: http://www.evanblack.com/blog/touch-slide-to-unlock/
	
	$('.ui-draggable')[0].addEventListener('touchmove', function(event) {
	    event.preventDefault();
	    var el = event.target;
	    var touch = event.touches[0];
	    curX = touch.pageX - this.offsetLeft - 73;
	    if(curX <= 0) return;
	    if(curX > 550){
	    	$('#well').fadeOut();
	    }
	   	el.style.webkitTransform = 'translateX(' + curX + 'px)'; 
	}, false);
	
	$('.ui-draggable')[0].addEventListener('touchend', function(event) {	
	    this.style.webkitTransition = '-webkit-transform 0.3s ease-in';
	    this.addEventListener( 'webkitTransitionEnd', function( event ) { this.style.webkitTransition = 'none'; }, false );
	    this.style.webkitTransform = 'translateX(0px)';
	    
	}, false);

});