package up.edu.br.cardapio;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import edu.up.br.itens.Bebidas;
import edu.up.br.itens.Pratos;
import edu.up.br.itens.Vinhos;

public class Cardapio {
	
	
	private static String arquivoPratos = "C:\\_ws\\CRUDTESTE\\Docs\\pratos.csv";
	private static String arquivoBebidas = "C:\\_ws\\CRUDTESTE\\Docs\\bebidas-tabuladas.txt";
	private static String arquivoVinhos = "C:\\_ws\\CRUDTESTE\\Docs\\vinhos-tabulados.txt";
	
	private static List<Pratos> pratos;
	static {
		carregarPratos();
	}
	
	public static List<Pratos> listarPratos() {
		return pratos;
	}
	
	private static List<Bebidas> bebidas;
	static {
		carregarBebidas();
	}
	
	public static List<Bebidas> listarBebidas() {
		return bebidas;
	}
	
	private static List<Vinhos> vinhos;
	static {
		carregarVinhos();
	}
	
	public static List<Vinhos> listarVinhos() {
		return vinhos;
	}
	
	
	private static List<Pratos> carregarPratos(){
		
		List<Pratos> lista = new ArrayList<>();
		
		try {
			File arquivo = new File(arquivoPratos);
			Scanner leitor = new Scanner (new FileInputStream(arquivo), "UTF-8");
			leitor.nextLine();
			
			while (leitor.hasNext()) {
				String linha = leitor.nextLine();
				String[] partes = linha.split(";");
				
				String nome = partes[0];
				double preco = Double.parseDouble(partes[1]);
				
				Pratos prato = new Pratos();
				prato.setNome(nome);
				prato.setPreco(preco);
				
				lista.add(prato);		
			}
			leitor.close();
			
		} catch (FileNotFoundException e) {
			System.out.println("Arquivo não encontrado!");
		}
		pratos = lista;
		return lista;
	}
	
	public static List<Bebidas> carregarBebidas(){
		List<Bebidas> lista = new ArrayList<>();
		
		try {
			File arquivo = new File (arquivoBebidas);
			Scanner leitor = new Scanner (new FileInputStream (arquivo), "UTF-8");
			leitor.nextLine();
			
			while (leitor.hasNext()) {
				String linha = leitor.nextLine();
				String[] partes = linha.split("\t");
				
				String nome = partes[1];
				double preco = Double.parseDouble(partes[1].replace ("," , "."));
				
				Bebidas bebida = new Bebidas();
				bebida.setNome(nome);
				bebida.setPreco(preco);
				
				lista.add(bebida);
			}
			bebidas = lista;
			leitor.close();
		} catch (FileNotFoundException e) {
			System.out.println("Arquivo não encontrado!");
	}
		return lista;
}
	
	public static List<Vinhos> carregarVinhos(){
		List<Vinhos> lista = new ArrayList<>();
		
		try {
			File arquivo = new File(arquivoVinhos);
			Scanner leitor = new Scanner(new FileInputStream(arquivo), "UTF-8");
			leitor.nextLine();
			
			while (leitor.hasNext()) {
				String linha = leitor.nextLine();
				String[] partes = linha.split("\t");
				
				String nome = partes [1];
				double preco = Double.parseDouble(partes[1].replace ("," , "."));
				
				Vinhos vinho = new Vinhos();
				vinho.setNome(nome);
				vinho.setPreco(preco);
				
				lista.add(vinho);
			}
			vinhos = lista;
			leitor.close();
			
		} catch (FileNotFoundException e) {
			System.out.println("Arquivo não encontrado!");
	}
		return lista;
	}
	
	public static void incluirPratos (Pratos prato) {
		try {
			FileWriter arquivo = new FileWriter(arquivoPratos, true);
			PrintWriter gravador = new PrintWriter(arquivo);
			gravador.println("\n" + prato.getNome() + ";" + prato.getPreco());
			gravador.close();
			arquivo.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void incluirBebidas (Bebidas bebida) {
		try {
			FileWriter arquivo = new FileWriter(arquivoBebidas, true);
			PrintWriter gravador = new PrintWriter(arquivo);
			gravador.println("\n" + bebida.getPreco() + "\t" + bebida.getNome());
			gravador.close();
			arquivo.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void incluirVinhos (Vinhos vinho) {
		try {
			FileWriter arquivo = new FileWriter(arquivoVinhos, true);
			PrintWriter gravador = new PrintWriter(arquivo);
			gravador.println("\n" + vinho.getPreco() + "\t" + vinho.getNome());
			gravador.close();
			arquivo.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static Pratos buscarPrato(String query) {
		
		List<Pratos> pratos = Cardapio.listarPratos();
		
		Pratos pratoEncontrado = null;
		
		for(Pratos prato : pratos) {
		
			if (prato.getNome().contains(query)) {
				pratoEncontrado = prato;
				break;
			}
		}
		return pratoEncontrado;
	}
	
	public static void atualizarPrato(Pratos prato) {
		
		try {
			//Atualizar o prato na lista de memoria
			List<Pratos> pratos = Cardapio.listarPratos();
			for (Pratos p : pratos) {
				if (p.getNome().equals(prato.getNome())) {
				p.setPreco(prato.getPreco());
				}
			}
			//Gravar a lista de pratos atualizadad no disco
			FileWriter arquivo = new FileWriter(arquivoPratos);
			PrintWriter gravador = new PrintWriter(arquivo);
			gravador.println ("NOME;PRECO");
			for (Pratos p : pratos) {
				gravador.println(p.getNome() + ";" + p.getPreco());	
			}
			gravador.close();
			arquivo.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	public static void excluirPrato(Pratos pratoRetornado) {
		
		List<Pratos> pratos = Cardapio.listarPratos();
		pratos.remove(pratoRetornado);
		
		try {
			//Gravar a lista de pratos atualizadad no disco
			FileWriter arquivo = new FileWriter(arquivoPratos);
			PrintWriter gravador = new PrintWriter(arquivo);
			gravador.println ("NOME;PRECO");
			for (Pratos p : pratos) {
				gravador.println(p.getNome() + ";" + p.getPreco());	
			}
			gravador.close();
			arquivo.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	public static Bebidas buscarBebida(String queryBebida) {
		List<Bebidas> bebidas = Cardapio.listarBebidas();
		
		Bebidas bebidaEncontrada = null;
		
		for(Bebidas bebida : bebidas) {
			if (bebida.getNome().contains(queryBebida)) {
				bebidaEncontrada = bebida;
				break;
			}
		}
		return bebidaEncontrada;
	}

	public static void atualizarBebida(Bebidas bebida) {
		
		try {
			//Atualizar o prato na lista de memoria
			List<Bebidas> bebidas = Cardapio.listarBebidas();
			for (Bebidas b : bebidas) {
				if (b.getNome().equals(bebida.getNome())) {
				b.setPreco(bebida.getPreco());
				}
			}
			//Gravar a lista de pratos atualizadad no disco
			FileWriter arquivo = new FileWriter(arquivoBebidas);
			PrintWriter gravador = new PrintWriter(arquivo);
			gravador.println ("NOME\tPRECO");
			for (Bebidas b : bebidas) {
				gravador.println(b.getNome() + "	" + b.getPreco());	
			}
			gravador.close();
			arquivo.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void excluirBebida(Bebidas bebidaRetornada) {
		
		List<Bebidas> bebidas = Cardapio.listarBebidas();
		bebidas.remove(bebidaRetornada);
		
		try {
			//Gravar a lista de pratos atualizadad no disco
			FileWriter arquivo = new FileWriter(arquivoBebidas);
			PrintWriter gravador = new PrintWriter(arquivo);
			gravador.println ("NOME\tPRECO");
			for (Bebidas b : bebidas) {
				gravador.println(b.getNome() + "	" + b.getPreco());	
			}
			gravador.close();
			arquivo.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	public static Vinhos buscarVinhos(String queryVinhos) {
		
		List<Vinhos> vinhos = Cardapio.listarVinhos();
		
		Vinhos vinhoEncontrado = null;
		
		for(Vinhos vinho : vinhos) {
		
			if (vinho.getNome().contains(queryVinhos)) {
				vinhoEncontrado = vinho;
				break;
			}
		}
		return vinhoEncontrado;	
	}

	public static void atualizarVinho(Vinhos vinhoRetornado) {
		
		try {
			//Atualizar o prato na lista de memoria
			List<Vinhos> vinhos = Cardapio.listarVinhos();
			for (Vinhos v : vinhos) {
				if (v.getNome().equals(v.getNome())) {
				v.setPreco(v.getPreco());
				}
			}
			//Gravar a lista de pratos atualizadad no disco
			FileWriter arquivo = new FileWriter(arquivoVinhos);
			PrintWriter gravador = new PrintWriter(arquivo);
			gravador.println ("NOME;PRECO");
			for (Vinhos v : vinhos) {
				gravador.println(v.getNome() + "	" + v.getPreco());	
			}
			gravador.close();
			arquivo.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	public static void excluirVinho(Vinhos vinhoRetornado) {
		
		List<Vinhos> vinhos = Cardapio.listarVinhos();
		vinhos.remove(vinhoRetornado);
		
		try {
			//Gravar a lista de pratos atualizadad no disco
			FileWriter arquivo = new FileWriter(arquivoVinhos);
			PrintWriter gravador = new PrintWriter(arquivo);
			gravador.println ("NOME;PRECO");
			for (Vinhos v : vinhos) {
				gravador.println(v.getNome() + "	" + v.getPreco());	
			}
			gravador.close();
			arquivo.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
