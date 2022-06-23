$(document).ready(function(e) { 

    console.log("page is loading now"); 
    
	var dayNames = [ "Domingo", "Segunda", "Ter&ccedil;a", "Quarta", "Quinta",
			"Sexta", "S&aacute;bado", "Domingo" ];
	var dayNamesMin = [ "D", "S", "T", "Q", "Q", "S", "S", "D" ];
	var dayNamesShort = [ "Dom", "Seg", "Ter", "Qua", "Qui", "Sex",
			"S&aacute;b", "Dom" ];
	var monthNames = [ "Janeiro", "Fevereiro", "Mar&ccedil;o", "Abril", "Maio",
			"Junho", "Julho", "Agosto", "Setembro", "Outubro", "Novembro",
			"Dezembro" ];
	var monthNamesShort = [ "Jan", "Fev", "Mar", "Abr", "Mai", "Jun", "Jul",
			"Ago", "Set", "Out", "Nov", "Dez" ];

	$(".datepicker").datepicker({
		dateFormat : "dd/mm/yy",
		showOtherMonths : true,
		selectOtherMonths : true,
		dayNames : dayNames,
		dayNamesMin : dayNamesMin,
		dayNamesShort : dayNamesShort,
		monthNames : monthNames,
		monthNamesShort : monthNamesShort,
		showAnim : "fade",
		nextText : 'Pr&oacute;ximo',
		prevText : 'Anterior',
		numberOfMonths : 2,
		showButtonPanel : true,
		changeYear : true,
		closeText : "Fechar",
		currentText : "Hoje",
		beforeShow : function() {
			$(".ui-datepicker").css('font-size', 12)
		},
		onSelect : function(dateText) {
			// Caso necessário alguma acao no selcionar
			// dataChange(dateText, $(this).attr('id'));

		}
	});
	
	$('.mascaraHora').mask('00:00');

    var decimal = $('.js-decimal');
	decimal.maskMoney({
		decimal : ",",
		thousands : ".",
		formatOnBlur: true,
		allowEmpty: true,
		allowZero: true
	});

    

});


