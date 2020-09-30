package up.edu.br.pedido;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import edu.up.br.itens.Bebidas;
import edu.up.br.itens.Pratos;
import edu.up.br.itens.Vinhos;

public class Pedido {
	
	private String codigo;
	private String observacao;
	
	private List<Pratos> pratos    = new ArrayList<>();
	private List<Bebidas> bebidas = new ArrayList<>();
	private List<Vinhos> vinhos   = new ArrayList<>();
	
	public Pedido() {
		DateFormat df = new SimpleDateFormat ("yyymmddhhmmss");
		this.setCodigo(df.format(Calendar.getInstance().getTime()));
	}
	
	public List<Pratos> getPratos() {
		return pratos;
	}
	
	public void setListaDePratos (List<Pratos> listaDePratosSelecionados) {
		this.pratos = listaDePratosSelecionados;
	}
	
	public List<Bebidas> getBebidas(){
		return bebidas;
	}
	
	public void setListaDeBebidas (List<Bebidas> listaDeBebidasSelecionadas) {
		this.bebidas = listaDeBebidasSelecionadas;
	}
	
	public List<Vinhos> getVinhos() {
		return vinhos;
	}
	
	public void setListaDeVinhos (List<Vinhos> listaDeVinhosSelecionados) {
		this.vinhos = listaDeVinhosSelecionados;
	}
	
	public String getObservcao() {
		return observacao;
	}
	
	public void setObservacao (String obs) {
		this.observacao = obs;
	}
	
	public String getTotal() {
		double valorTotal = 0;
		
		for (Pratos prato : pratos) {
			valorTotal += prato.getPreco();
		}
		
		for (Bebidas bebida : bebidas) {
			valorTotal += bebida.getPreco();
		}
		
		for (Vinhos vinho : vinhos) {
			valorTotal += vinho.getPreco();
		}
		
		DecimalFormat df = new DecimalFormat ("#,###.00");
		return df.format(valorTotal);
	}
	
	public String getCodigo() {
		return codigo;
	}
	
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public void incluirObservacao(String observacao2) {
		
	}

	public double getValorTotal() {
		return 0;
	}
	
}
