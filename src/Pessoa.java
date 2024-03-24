public class Pessoa {

    private String Nome;
    private String Endereco;
    private String Telefone;
    private int id;

    public Pessoa(String Nome, String Endereco, String Telefone){
        this.Nome = Nome;
        this.Endereco = Endereco;
        this.Telefone = Telefone;
    }

    public String getNome(){
        return Nome;
    }

    public String getEndereco(){
        return Endereco;
    }

    public String getTelefone(){
        return Telefone;
    }
}
