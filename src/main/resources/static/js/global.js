$(document).ready(function(){
	/* MENU */
	
		$('.ui.sidebar')
		  .sidebar({
		    context: $('.bottom.segment')
		  }).sidebar('attach events', ' #menuOpener');
	
		 $('#menuOpener').click(function(){
			$('.pusher').removeClass('dimmed');
		 });
		 $('.pusher').removeClass('dimmed');

	/* END MENU */
		 
	
		
});
