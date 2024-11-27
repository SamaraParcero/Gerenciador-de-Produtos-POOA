package br.com.ucsal.model;

// Classe que representa um Produto.
public class Produto {
	    private Integer id;
	    private String nome;
	    private Double preco;


	    // Construtor padrão.     
	    public Produto() {
		}

	    //Construtor para inicializar os atributos.  
		public Produto(Integer id, String nome, double preco) {
			super();
			this.id = id;
			this.nome = nome;
			this.preco = preco;
		}


		// Métodos getter e setter
		public Integer getId() {
			return id;
		}


		public void setId(Integer id) {
			this.id = id;
		}


		public String getNome() {
			return nome;
		}


		public void setNome(String nome) {
			this.nome = nome;
		}


		public Double getPreco() {
			return preco;
		}


		public void setPreco(Double preco) {
			this.preco = preco;
		}

		
		// Retorna uma representação em String do Produto.
		@Override
		public String toString() {
			return "Produto [id=" + id + ", nome=" + nome + ", preco=" + preco + "]";
		}
		
		
	    
	    

}
