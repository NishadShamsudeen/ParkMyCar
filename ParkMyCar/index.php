<html>
<head>
<title>Bus Ticket Book</title>
<script type="text/javascript" src="js/jquery.js"></script> 
 <script> 
 $(document).ready(function(){
	var fruits = [];
	var fruitsfinal,amountfinal;
	var amount = 500;
	
	$(".seat").click(function(){
	 var total_class = $('.green').length;
	 var book_seat = $(this).attr("data-book"); 
	 var current_book = $(this).attr("data-current"); 
	 if(current_book == 1 ){	
	 var seat_no = $(this).attr("data-number");
		$(this).removeClass('green');	
		$(this).removeAttr('data-current');		
		fruits.splice(fruits.indexOf(seat_no), 1 );
		$(".book_seats").val(fruits);	
		$(".amount").val( fruits.length * amount );
		fruitsfinal = fruits.toString();
		amountfinal = fruits.length * amount;
		return true;
	 }	  
	else if(book_seat == 1){	
		alert("Already booked");
		return false;
	}	
	else if(total_class < 5){ 	
		var seat_no = $(this).attr("data-number");
        fruits.push(seat_no);
		$(".book_seats").val(fruits);
		$(".amount").val( fruits.length * amount );
		fruitsfinal = fruits.toString();
		amountfinal = fruits.length * amount;
		$(this).attr('data-current', '1');
		$(this).addClass('green');	
		return true;
	}
	else if(total_class >= 5){ 
		alert("Maximum 5 seats only");
		return false;
	}	
	});
	
	$("form").submit(function(){
		
    //document.write(fruitsfinal,amountfinal);
	 window.ob.fetchString(fruitsfinal,amountfinal);	
    });
	
	
});
</script>
<style>
.bus{width:380px;float:left;min-height:150px;border:1px solid #CCC;padding:0  0 10px 10px }
.seat{background:#CCC;float:left;margin:10px 10px 0 0;cursor:pointer;padding:4;}
.cancel_book{background:#CCC;}
.green{background:green;}
.red{background:red;}
</style> 
</head>

<body>

	<div class="bus">
		<?php
		$booked_seat=array(10,11,7,4,1);
		 for($seat= 1; $seat <=40 ;$seat++) { 
			if(in_array($seat,$booked_seat)){ $booked="red"; $book_seat="data-book='1'"; }
			else { $booked=""; $book_seat="";}
			echo "<div class='seat $booked' data-number='$seat' $book_seat ><img src='images/seat.png' width='20' height='20'></div>";
		 } ?>
	</div>
	
	<form method="post">
	Seat No :<input type="text" name="seats" class="book_seats"><br>
	Total Amount :<input type="text" name="amount" class="amount"><br>
	<input type="submit" value="Proceed" class="submit">
	</form>
	<div class="details"></div>
	<p id="demo"></p>
</body>
</html>