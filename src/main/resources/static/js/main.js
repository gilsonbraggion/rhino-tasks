$(document).ready(function(e) {

	console.log("page is loading now");

	var dayNames = ["Domingo", "Segunda", "Ter&ccedil;a", "Quarta", "Quinta",
		"Sexta", "S&aacute;bado", "Domingo"];
	var dayNamesMin = ["D", "S", "T", "Q", "Q", "S", "S", "D"];
	var dayNamesShort = ["Dom", "Seg", "Ter", "Qua", "Qui", "Sex",
		"S&aacute;b", "Dom"];
	var monthNames = ["Janeiro", "Fevereiro", "Mar&ccedil;o", "Abril", "Maio",
		"Junho", "Julho", "Agosto", "Setembro", "Outubro", "Novembro",
		"Dezembro"];
	var monthNamesShort = ["Jan", "Fev", "Mar", "Abr", "Mai", "Jun", "Jul",
		"Ago", "Set", "Out", "Nov", "Dez"];

	$(".datepicker").datepicker({
		dateFormat: "dd/mm/yy",
		showOtherMonths: true,
		selectOtherMonths: true,
		dayNames: dayNames,
		dayNamesMin: dayNamesMin,
		dayNamesShort: dayNamesShort,
		monthNames: monthNames,
		monthNamesShort: monthNamesShort,
		showAnim: "fade",
		nextText: 'Pr&oacute;ximo',
		prevText: 'Anterior',
		numberOfMonths: 2,
		showButtonPanel: true,
		changeYear: true,
		closeText: "Fechar",
		currentText: "Hoje",
		beforeShow: function() {
			$(".ui-datepicker").css('font-size', 12)
		},
		onSelect: function(dateText) {
			// Caso necessário alguma acao no selcionar
			// dataChange(dateText, $(this).attr('id'));

		}
	});

	$('.mascaraHora').mask('00:00');

	var decimal = $('.js-decimal');
	decimal.maskMoney({
		decimal: ",",
		thousands: ".",
		formatOnBlur: true,
		allowEmpty: true,
		allowZero: true
	});


	$("#buscarGraficoResumoAtividades").click(
		function() {

			var contexto = window.location.origin;

			var idSubProjeto = $('select[name=idSubProjeto]').val();

			$.ajax({
				url: contexto + "/relatorio/buscarGraficoResumoAtividades",
				data: {
					idSubProjeto: idSubProjeto
				},
				dataType: 'json',
				type: "POST",
				success: function(resultado) {

					const ctx = document.getElementById('myChart').getContext('2d');

					var result = resultado.reduce(function(r, e) {
						r[e.label + ' ' + e.valorPercentual] = e.valorPercentual;
						return r;
					}, {});

					const myChart = new Chart(ctx, {
						type: 'bar',
						responsive: true,
						maintainAspectRatio: false,


						
						data: {
							labels: Object.keys(result),
							datasets: [
								{
									label: 'Resumo das atividades',
									data: Object.values(result),
									backgroundColor: [
										'rgba(255, 99, 132, 0.2)',
										'rgba(54, 162, 235, 0.2)',
										'rgba(255, 206, 86, 0.2)',
										'rgba(75, 192, 192, 0.2)',
										'rgba(153, 102, 255, 0.2)',
										'rgba(255, 159, 64, 0.2)'
									],
									borderColor: [
										'rgba(255, 99, 132, 1)',
										'rgba(54, 162, 235, 1)',
										'rgba(255, 206, 86, 1)',
										'rgba(75, 192, 192, 1)',
										'rgba(153, 102, 255, 1)',
										'rgba(255, 159, 64, 1)'
									],
									borderWidth: 1
								},
							],
						},
					});

				},
				error: function() {
					alert("Erro na chamada");
				}
			});

		});

});


