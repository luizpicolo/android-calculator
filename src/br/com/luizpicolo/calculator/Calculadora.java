package br.com.luizpicolo.calculator;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import java.lang.Math;

import com.calculator.myCalculator.R;

import android.content.Context;
import android.os.Vibrator;

public class Calculadora extends Activity {

	final int MaxViewableNumber = 9999999;
	Button B1, B2, B3, B4, B5, B6, B7, B8, B9, B0, BSoma, BDiferenca,
			BMultiplicacao, BDivisao, BLimpar, BPonto, BEquacoes, Bexponent,
			BPorcentagem, BtrocaValor;
	TextView Resultado;
	double num1 = -1, num2 = -1, result = 0;
	char op = ' ';
	int BotaoPressionado;
	boolean wrongOp = false;
	boolean pontoPressionado = false;
	boolean erroDeSintaxe = false;
	boolean semMaisNumeros = false;
	int countDecDigits = 0;

	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		initLayout();
		final Vibrator mVibracao = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
		final int duracaoDaVibracao = 33;

		OnClickListener myListenerB1 = new OnClickListener() {
			public void onClick(View v) {
				mVibracao.vibrate(duracaoDaVibracao);
				NumeroPrecionado(1);

			}
		};

		OnClickListener myListenerB2 = new OnClickListener() {
			public void onClick(View v) {
				mVibracao.vibrate(duracaoDaVibracao);
				NumeroPrecionado(2);
			}
		};

		OnClickListener myListenerB3 = new OnClickListener() {
			public void onClick(View v) {
				mVibracao.vibrate(duracaoDaVibracao);
				NumeroPrecionado(3);
			}
		};

		OnClickListener myListenerB4 = new OnClickListener() {
			public void onClick(View v) {
				mVibracao.vibrate(duracaoDaVibracao);
				NumeroPrecionado(4);
			}
		};

		OnClickListener myListenerB5 = new OnClickListener() {
			public void onClick(View v) {
				mVibracao.vibrate(duracaoDaVibracao);
				NumeroPrecionado(5);
			}
		};

		OnClickListener myListenerB6 = new OnClickListener() {
			public void onClick(View v) {
				mVibracao.vibrate(duracaoDaVibracao);
				NumeroPrecionado(6);
			}
		};

		OnClickListener myListenerB7 = new OnClickListener() {
			public void onClick(View v) {
				mVibracao.vibrate(duracaoDaVibracao);
				NumeroPrecionado(7);
			}
		};

		OnClickListener myListenerB8 = new OnClickListener() {
			public void onClick(View v) {
				mVibracao.vibrate(duracaoDaVibracao);
				NumeroPrecionado(8);
			}
		};

		OnClickListener myListenerB9 = new OnClickListener() {
			public void onClick(View v) {
				mVibracao.vibrate(duracaoDaVibracao);
				NumeroPrecionado(9);
			}
		};

		OnClickListener myListenerB0 = new OnClickListener() {
			public void onClick(View v) {
				mVibracao.vibrate(duracaoDaVibracao);
				NumeroPrecionado(0);
			}
		};

		OnClickListener myListenerBSum = new OnClickListener() {
			public void onClick(View v) {
				mVibracao.vibrate(duracaoDaVibracao);
				ChangeOp("+");
				OperadorPrecionado("+");
			}
		};

		OnClickListener myListenerBDif = new OnClickListener() {
			public void onClick(View v) {
				mVibracao.vibrate(duracaoDaVibracao);
				ChangeOp("-");
				OperadorPrecionado("-");
			}
		};

		OnClickListener myListenerBMul = new OnClickListener() {
			public void onClick(View v) {
				mVibracao.vibrate(duracaoDaVibracao);
				ChangeOp("*");
				OperadorPrecionado("*");
			}
		};

		OnClickListener myListenerBDiv = new OnClickListener() {
			public void onClick(View v) {
				mVibracao.vibrate(duracaoDaVibracao);
				ChangeOp("/");
				OperadorPrecionado("/");
			}
		};

		OnClickListener myListenerBExponente = new OnClickListener() {
			public void onClick(View v) {
				mVibracao.vibrate(duracaoDaVibracao);
				ChangeOp("^");
				OperadorPrecionado("^");
			}
		};
		
		OnClickListener myListenerBPorcentagem = new OnClickListener() {
			public void onClick(View v) {
				mVibracao.vibrate(duracaoDaVibracao);
				ChangeOp("%");
				OperadorPrecionado("%");
			}
		};
		
		OnClickListener myListenerBtrocaValor = new OnClickListener() {
			public void onClick(View v) {
				mVibracao.vibrate(duracaoDaVibracao);
				double valor = num1 * -1;
				Resultado.setText("");
				Resultado.append(String.valueOf(valor));
			}
		};

		OnClickListener myListenerBDot = new OnClickListener() {
			public void onClick(View v) {
				mVibracao.vibrate(duracaoDaVibracao);
				if (num1 == -1) {
					num1 = 0;
				} else if (num2 == -1 && op != ' ') {
					num2 = 0;
					Resultado.append("0");
				}

				if (erroDeSintaxe) {
					Resultado.setText("0.");
				} else if (!pontoPressionado) {
					Resultado.append(".");
				}

				if (num1 == 0
						&& num2 == -1
						&& op == ' '
						&& Float.parseFloat(Resultado.getText().toString()) != 0) {
					Resultado.setText("0");
				}

				erroDeSintaxe = false;
				pontoPressionado = true;
			}
		};

		OnClickListener myListenerBClear = new OnClickListener() {
			public void onClick(View v) {
				mVibracao.vibrate(duracaoDaVibracao);
				LimparTodos();
			}
		};

		OnClickListener myListenerBEqual = new OnClickListener() {
			public void onClick(View v) {
				mVibracao.vibrate(duracaoDaVibracao);
				if (num1 != -1 && num2 != -1) {
					switch (op) {
					case ('+'):
						result = num1 + num2;
						MostrarResultado();
						break;
					case ('-'):
						result = num1 - num2;
						MostrarResultado();
						break;
					case ('*'):
						result = num1 * num2;
						MostrarResultado();
						break;
					case ('/'):
						result = num1 / num2;
						MostrarResultado();
						break;
					case ('^'):
						result = Math.pow(num1, num2);
						MostrarResultado();
						break;
					case ('%'):
						result = num1 * num2 / 100.0;  
						MostrarResultado();
						break;
					default:
						wrongOp = true;
					}
				} else if (num1 != -1 && num2 == -1 && op == ' ') {
					result = num1;
					MostrarResultado();
				} else {
					char c = Resultado
							.getText()
							.toString()
							.charAt(Resultado.getText().toString().length() - 1);
					if (c == '+' || c == '-' || c == '*' || c == '/') {
						wrongOp = true;
						Resultado.setText("Erro de Sintaxe.");
						erroDeSintaxe = true;
					}
				}
				num1 = -1;
				num2 = -1;
				op = ' ';
				pontoPressionado = false;
				semMaisNumeros = false;
				countDecDigits = 0;
			}
		};

		B1.setOnClickListener(myListenerB1);
		B2.setOnClickListener(myListenerB2);
		B3.setOnClickListener(myListenerB3);
		B4.setOnClickListener(myListenerB4);
		B5.setOnClickListener(myListenerB5);
		B6.setOnClickListener(myListenerB6);
		B7.setOnClickListener(myListenerB7);
		B8.setOnClickListener(myListenerB8);
		B9.setOnClickListener(myListenerB9);
		B0.setOnClickListener(myListenerB0);
		
		BSoma.setOnClickListener(myListenerBSum);
		BDiferenca.setOnClickListener(myListenerBDif);
		BMultiplicacao.setOnClickListener(myListenerBMul);
		BDivisao.setOnClickListener(myListenerBDiv);
		BPonto.setOnClickListener(myListenerBDot);
		Bexponent.setOnClickListener(myListenerBExponente);
		BPorcentagem.setOnClickListener(myListenerBPorcentagem);
		BtrocaValor.setOnClickListener(myListenerBtrocaValor);
		
		BLimpar.setOnClickListener(myListenerBClear);
		BEquacoes.setOnClickListener(myListenerBEqual);
		
	}

	public void onStart() {
		super.onStart();
	}

	public void onResume() {
		super.onResume();
	}

	public void onPause() {
		super.onPause();
	}

	public void onStop() {
		super.onStop();
	}

	public void onRestart() {
		super.onRestart();
	}

	public void onDestroy() {
		super.onDestroy();
	}

	private void initLayout() {
		B1 = (Button) findViewById(R.id.button1);
		B2 = (Button) findViewById(R.id.button2);
		B3 = (Button) findViewById(R.id.button3);
		B4 = (Button) findViewById(R.id.button4);
		B5 = (Button) findViewById(R.id.button5);
		B6 = (Button) findViewById(R.id.button6);
		B7 = (Button) findViewById(R.id.button7);
		B8 = (Button) findViewById(R.id.button8);
		B9 = (Button) findViewById(R.id.button9);
		B0 = (Button) findViewById(R.id.button0);

		BSoma = (Button) findViewById(R.id.buttonSum);
		BDiferenca = (Button) findViewById(R.id.buttonDif);
		BMultiplicacao = (Button) findViewById(R.id.buttonMul);
		BDivisao = (Button) findViewById(R.id.buttonDiv);
		Bexponent = (Button) findViewById(R.id.Button01);
		BPorcentagem = (Button) findViewById(R.id.Button02);
		BtrocaValor = (Button) findViewById(R.id.Button04);

		BLimpar = (Button) findViewById(R.id.buttonClear);
		BPonto = (Button) findViewById(R.id.buttonDot);
		BEquacoes = (Button) findViewById(R.id.buttonEq);
		Resultado = (TextView) findViewById(R.id.textView1);
	}

	private void MostrarResultado() {
		long resultInt = Math.round(result);
		if (result - resultInt == 0)
			Resultado.setText(Double.toString(Math.round(result)).subSequence(
					0, Double.toString(result).indexOf(".")));
		else
			Resultado
					.setText(Double.toString(Math.round(result * 1000.0) / 1000.0));
		if (result > MaxViewableNumber) {
			Resultado.setText(Double.toString(result).subSequence(0, 3));
			Resultado.append(Double.toString(result).subSequence(
					Double.toString(result).indexOf("E"),
					Double.toString(result).length()));
		} else if (result < -MaxViewableNumber) {
			Resultado.setText(Double.toString(result).subSequence(0, 4));
			Resultado.append(Double.toString(result).subSequence(
					Double.toString(result).indexOf("E"),
					Double.toString(result).length()));
		}
	}
	
	private void NumeroPrecionado(int buttonPressed) {
		if (num1 == -1) {
			num1 = buttonPressed;
			Resultado.setText(Integer.toString(buttonPressed));
		} else if (num2 == -1 && !pontoPressionado && op != ' ') {
			num2 = buttonPressed;
			Resultado.append(Integer.toString(buttonPressed));
		} else if (op == ' ') {
			if (pontoPressionado) {
				countDecDigits--;
				num1 = num1 + buttonPressed
						* (float) Math.pow(10, countDecDigits);
			} else {
				num1 = num1 * 10 + buttonPressed;
			}
			Resultado.append(Integer.toString(buttonPressed));
		} else if (op != ' ') {
			if (pontoPressionado) {
				countDecDigits--;
				num2 = num2 + buttonPressed
						* (float) Math.pow(10, countDecDigits);
			} else {
				num2 = num2 * 10 + buttonPressed;
			}
			Resultado.append(Integer.toString(buttonPressed));
		}
	}

	private void ChangeOp(String newOp) {
		if (op != ' ' && semMaisNumeros && num2 == -1) {
			op = newOp.charAt(0);
			Resultado.setText(Resultado.getText().toString()
					.substring(0, Resultado.getText().toString().length() - 1));
			Resultado.append(newOp);
		}
	}

	private void OperadorPrecionado(String insertedOp) {
		while (!semMaisNumeros) {
			if (Resultado
					.getText()
					.toString()
					.equals(Double.toString(Math.round(result * 1000.0) / 1000.0))) {
				num1 = result;
			} else if (Resultado.getText().toString()
					.equals(Integer.toString((int) result))) {
				num1 = result;
			}

			if (num1 == -1)
				num1 = 0;
			if (Resultado.getText().toString()
					.charAt(Resultado.getText().length() - 1) == '.')
				Resultado.append("0");

			pontoPressionado = false;
			countDecDigits = 0;
			Resultado.append(insertedOp);
			op = insertedOp.charAt(0);
			semMaisNumeros = true;
		}
	}

	private void LimparTodos() {
		num1 = -1;
		num2 = -1;
		op = ' ';
		pontoPressionado = false;
		erroDeSintaxe = false;
		semMaisNumeros = false;
		countDecDigits = 0;
		Resultado.setText("0");
	}
}
