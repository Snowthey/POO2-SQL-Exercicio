import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ConexaoBancoDeDados {

    private Connection conexao;
    private String URL_Bancodedados;
    private String usuario;
    private String senha;

    public ConexaoBancoDeDados(){
        URL_Bancodedados = "jdbc:mysql://localhost:3306/pessoa";
        usuario = "root";
        senha = "admin";
    }

    private void IniciarConexao(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conexao = DriverManager.getConnection(URL_Bancodedados, usuario, senha);
            System.out.println("Conexão realizada com sucesso!");
        } catch (Exception e){
            e.printStackTrace();
            System.out.println("Erro ao acessar o Banco de Dados!");
        }
    }


    private void EncerrarConexao() throws SQLException{
        if(conexao != null){
            conexao.close();
        }
    }

    public String InserirDados(Pessoa ObjetoPessoa) throws SQLException {
        IniciarConexao();

        if (conexao != null) {
            try {
                PreparedStatement psInsert = conexao.prepareStatement("INSERT INTO pessoa (nome, endereco, telefone) VALUES (?, ?, ?)");
                psInsert.setString(1, ObjetoPessoa.getNome());
                psInsert.setString(2, ObjetoPessoa.getEndereco());
                psInsert.setString(3, ObjetoPessoa.getTelefone());
                psInsert.execute();

                EncerrarConexao();

                return "Cadastro realizado com sucesso!";
            } catch (SQLException e) {
                e.printStackTrace(); // Imprima a exceção para diagnóstico
                return "Erro! Inserção não realizada!";
            }
        } else {
            return "Erro! Conexão não estabelecida!";
        }
    }


    public String AlterarDados(Pessoa ObjetoPesoa, int id) throws SQLException{
        IniciarConexao();

        if(conexao != null){
            PreparedStatement comandoupdate = conexao.prepareStatement("UPDATE pessoa SET endereco = ?, telefone = ?, nome = ? WHERE id= ?");
            comandoupdate.setString(1, ObjetoPesoa.getEndereco());
            comandoupdate.setString(2, ObjetoPesoa.getTelefone());
            comandoupdate.setString(3, ObjetoPesoa.getNome());
            comandoupdate.setInt(4, id);
            comandoupdate.execute();

            EncerrarConexao();

            return "Alteração realizada com sucesso";
        } else{
            return "Erro! Alteração não realizada";
        }
    }

    public String RemoverDados(int id) throws SQLException{
        IniciarConexao();

        if(conexao != null){
            PreparedStatement comandodelete = conexao.prepareStatement("DELETE FROM pessoa WHERE id = ?");
            comandodelete.setInt(1, id);
            comandodelete.execute();

            EncerrarConexao();

            return "Remoção realizada com sucesso";
        } else{
            return "Erro! Alteração não realizada";
        }
    }

    public ArrayList<String> Relatorio() throws SQLException{
        IniciarConexao();

        ArrayList<String> relatorioBancoDeDados = new ArrayList<String>();

        if(conexao != null){

            Statement comandoconsulta = conexao.createStatement();
            ResultSet resultadoconsultaBD = comandoconsulta.executeQuery("SELECT * FROM pessoa");
            String resultado;
            String nome;
            String endereco;
            String telefone;
            String id;
            while(resultadoconsultaBD.next()){
                nome = resultadoconsultaBD.getString("nome");
                endereco = resultadoconsultaBD.getString("endereco");
                telefone = resultadoconsultaBD.getString("telefone");
                id = resultadoconsultaBD.getString("id");

                resultado = "# ID: " + id + " Nome: " + nome + " Endereço: " + endereco + " Telefone: " + telefone;
                relatorioBancoDeDados.add(resultado);
            }

            EncerrarConexao();

            return relatorioBancoDeDados;
        }
        return null;
    }
}

