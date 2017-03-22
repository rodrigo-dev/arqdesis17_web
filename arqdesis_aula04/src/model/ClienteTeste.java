package model;

public class ClienteTeste {
	public static void main(String[] args) {
		Cliente cliente = new Cliente();
		cliente.setNome("Batista Cepelos");
		cliente.setEmail("");
		cliente.criar();
		cliente.carregar();
		System.out.println(cliente);
		cliente.setFone("123123333");
		cliente.setEmail("bcp"+cliente.getId()+"@usjt.br");
		cliente.atualizar();
		cliente.carregar();
		System.out.println(cliente);
		cliente.excluir();
		cliente.carregar();
		System.out.println(cliente);		
	}
}
