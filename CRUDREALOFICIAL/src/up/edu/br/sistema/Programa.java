package up.edu.br.sistema;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import up.edu.br.cardapio.Cardapio;
import edu.up.br.itens.Bebidas;
import edu.up.br.itens.Pratos;
import edu.up.br.itens.Vinhos;
import up.edu.br.pedido.Pedido;

public class Programa {

	public static void main(String[] args) throws Exception {
		
		List<Pratos> listaDePratos = Cardapio.listarPratos();
		List<Bebidas> listaDeBebidas = Cardapio.listarBebidas();
		List<Vinhos> listaDeVinhos = Cardapio.listarVinhos();
		
		//Selecionar itens
		Pedido pedido = new Pedido();
		
		List<Pratos> listaDePratosSelecionados = new ArrayList<>();
		List<Bebidas> listaDeBebidasSelecionadas = new ArrayList<>();
		List<Vinhos> listaDeVinhosSelecionados = new ArrayList<>();
		
		//CRUD itens do cardapio
		Scanner leitorAdd = new Scanner (System.in);
		System.out.println("Deseja adicionar um item ao cardapio?");
		System.out.println("[1] Adicionar Prato");
		System.out.println("[2] Adicionar Bebida");
		System.out.println("[3] Adicionar Vinho");
		System.out.println("[0] Não");
		int escolha = leitorAdd.nextInt();
		//C = incluir
		switch (escolha)
		{
			case 0:
			{
				System.out.println("Prossiga");
				break;
			}
			case 1:
			{
				String nomePrato = ("Prato Especial");
				
				Pratos prato = new Pratos();
				prato.setNome(nomePrato);
				prato.setPreco(9.90);
				Cardapio.incluirPratos(prato);
				break;
			}
			case 2:
			{
				String nomeBebida = ("Bebida");
				Bebidas bebida = new Bebidas();
				bebida.setNome(nomeBebida);
				bebida.setPreco(9.90);
				Cardapio.incluirBebidas(bebida);
				break;
			}
			case 3: 
			{
				String nomeVinho = ("Vinho");
				
				Vinhos vinho = new Vinhos();
				vinho.setNome(nomeVinho);
				vinho.setPreco(9.90);
				Cardapio.incluirVinhos(vinho);				
				break;
			}
		}
		
		// R = Buscar
		//PRATOS
		String queryPratos = "Arroz";
		Pratos pratoRetornado = Cardapio.buscarPrato(queryPratos);
		
		if(pratoRetornado == null) {
			System.out.println("Prato não encontrado");
		} else {
			System.out.println("Prato: " + pratoRetornado.getNome());
		}
		//U = atualizar
		if (pratoRetornado != null) {
			pratoRetornado.setPreco(6.60);
			Cardapio.atualizarPrato(pratoRetornado);
		}
		//D = excluir
		Cardapio.excluirPrato(pratoRetornado);
		
		//BEBIDAS
			String queryBebida = "Coca";
			Bebidas bebidaRetornada = Cardapio.buscarBebida(queryBebida);
			
			if(bebidaRetornada == null) {
				System.out.println("Bebida não encontrada");
			} else {
				System.out.println("Bebida: " + bebidaRetornada.getNome());
			}
			//U = atualizar
			if (bebidaRetornada != null) {
				bebidaRetornada.setPreco(6.60);
				Cardapio.atualizarBebida(bebidaRetornada);
			}
			//D = excluir
			Cardapio.excluirBebida(bebidaRetornada);
			
		//VINHOS
		
			String queryVinhos = "Gewurztraminer";
			Vinhos vinhoRetornado = Cardapio.buscarVinhos(queryVinhos);
			
			if(vinhoRetornado == null) {
				System.out.println("Vinho não encontrado");
			} else {
				System.out.println("Vinho: " + vinhoRetornado.getNome());
			}
			//U = atualizar
			if (vinhoRetornado != null) {
				vinhoRetornado.setPreco(6.60);
				Cardapio.atualizarVinho(vinhoRetornado);
			}
			//D = excluir
			Cardapio.excluirVinho(vinhoRetornado);
			
			
		pedido.setListaDePratos(listaDePratosSelecionados);
		pedido.setListaDeBebidas(listaDeBebidasSelecionadas);
		pedido.setListaDeVinhos(listaDeVinhosSelecionados);
		
		//Logica para seleção de itens
		Scanner leitor = new Scanner (System.in);
		
		boolean desejaSelecionarItensDoCardapio = true;
		do {
			//Escrever a lógica para selecionar itens do cardápio;
			System.out.println("Escolha uma das opções abaixo!");
			System.out.println("[1] Pratos");
			System.out.println("[2] Bebidas");
			System.out.println("[3] Vinhos");
			System.out.println("[0] Sair");
			
			int opc = leitor.nextInt();
			
			switch (opc) 
			{
				case 0: 
				{
					desejaSelecionarItensDoCardapio = false;
					break;
				}
				
				case 1:
				{
					boolean desejaSelecionarPratos = true;
					do {
						System.out.println("Digite o número do prato desejado!");
						System.out.println("[0] Voltar ao menu");
							for (int i = 0; i < listaDePratos.size(); i++) 
							{
								Pratos pratos = listaDePratos.get(i);
								System.out.println((1+i)+" "+ pratos.getNome()+ ": " +pratos.getPreco());
							}
						int opcaoPrato = leitor.nextInt();
						
							if (opcaoPrato == 0)
							{
								//Se o cliente não deseja mais adicionar pratos
								desejaSelecionarPratos = false;
							} else {
							Pratos pratoSelecionado = listaDePratos.get(--opcaoPrato);
							listaDePratosSelecionados.add(pratoSelecionado);
							}
					} while(desejaSelecionarPratos);
					break;
				}
				case 2: 
				{
					boolean desejaSelecionarBebidas = true;
					do {
						System.out.println("Digite o número da bebida desejada!");
						System.out.println("[0] Voltar ao menu");
							for (int i = 0; i < listaDeBebidas.size(); i++) {
								Bebidas bebidas = listaDeBebidas.get(i);
								System.out.println((1+i)+" "+ bebidas.getNome()+ " R$:" +bebidas.getPreco());
							}
						int opcaoBebida = leitor.nextInt();
						
							if (opcaoBebida == 0)
							{
								//Se o cliente não deseja mais adicionar pratos
								desejaSelecionarBebidas = false;
							} else {
								Bebidas bebidasSelecionado = listaDeBebidas.get(--opcaoBebida);
								listaDeBebidasSelecionadas.add(bebidasSelecionado);
							}
					} while(desejaSelecionarBebidas);
					break;
				}
				case 3:
				{
					boolean desejaSelecionarVinhos = true;
					do {
						System.out.println("Digite o número do vinho desejado!");
						System.out.println("[0] Voltar ao menu");
						for (int i = 0; i < listaDeVinhos.size(); i++) {
							Vinhos vinhos = listaDeVinhos.get(i);
							System.out.println((1+i)+" "+ vinhos.getNome()+ " R$:" +vinhos.getPreco());
						}
						int opcaoVinho = leitor.nextInt();
						
						if (opcaoVinho == 0)
						{
							//Se o cliente não deseja mais adicionar pratos
							desejaSelecionarVinhos = false;
						} else {
							Vinhos vinhosSelecionado = listaDeVinhos.get(--opcaoVinho);
							listaDeVinhosSelecionados.add(vinhosSelecionado);
						}
					} while (desejaSelecionarVinhos);
					break;
				}
			}
		}	while (desejaSelecionarItensDoCardapio);
		
		System.out.println("Observação do Pedido:");
		String obs = leitor.nextLine();
		leitor.nextLine();
		pedido.setObservacao(obs);
		leitor.close();
		try {
			String nomeDoArquivo = "C:\\_ws\\Pedidos\\pedido" + pedido.getCodigo() + ".csv";
			FileWriter arquivoPedido = new FileWriter (nomeDoArquivo, StandardCharsets.UTF_8);
			PrintWriter gravador = new PrintWriter(arquivoPedido);
			
			gravador.println("Pedido" + pedido.getCodigo());
			for (Pratos p : pedido.getPratos()) {
				gravador.println(p.getNome() + ", " + p.getPreco());
			}
			
			for (Bebidas b : pedido.getBebidas()) {
				gravador.println(b.getNome() + ", " + b.getPreco());
			}
			
			for (Vinhos v : pedido.getVinhos()) {
				gravador.println(v.getNome() + ", " + v.getPreco());
			}
			
			gravador.println();
			gravador.println("Observação:" + pedido.getObservcao());
			gravador.println();
			gravador.println("Total:" + pedido.getTotal());
			
			gravador.close();
			arquivoPedido.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		System.out.println("Pedido finalizado!!");
	}	
	
}